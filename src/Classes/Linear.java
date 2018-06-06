package Classes;

import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

public class Linear {
	private int n;
	private Drivers drivers;
	private CostMatrices C;
	private Hours hours;

	public Linear(int nPersons, Drivers dr, CostMatrices cost, Hours h) {
		this.n = nPersons;
		this.drivers = dr;
		this.C = cost;
		this.hours = h;
		try {
			IloCplex cplex = new IloCplex();

			// DATAS
			int O = n;
			int D = 2 * n;
			int U = 2 * n;

			int K = 3 * n;
			int L = 4 * n;
			int W = 4 * n;

			int V = 4 * n;

			int WaitingTimeA = 40;
			int WaitingTimeR = 20;

			int M = 9999;

			// DECISION VARIABLES
			IloNumVar[][][] x = new IloNumVar[O][V][V];
			for (int k = 0; k < n; k++) {
				for (int i = 0; i < 4 * n; i++) {
					x[k][i] = cplex.boolVarArray(V);
				}
			}

			IloNumVar[] y = new IloNumVar[O];
			y = cplex.boolVarArray(O);

			IloNumVar[][] z = new IloNumVar[O][V];
			for (int k = 0; k < n; k++) {
				z[k] = cplex.boolVarArray(V);
			}

			IloNumVar[][] b = new IloNumVar[O][V];
			for (int k = 0; k < n; k++) {
				b[k] = cplex.intVarArray(V, 0, Integer.MAX_VALUE);
			}

			// OBJECTIVE
			IloLinearNumExpr objective = cplex.linearNumExpr();
			for (int k = 0; k < n; k++) {
				for (int i = 0; i < U; i++) {
					for (int j = 0; j < U; j++) {
						objective.addTerm(C.A()[i][j], x[k][i][j]);
					}
				}
				for (int i = U; i < W; i++) {
					for (int j = U; j < W; j++) {
						objective.addTerm(C.R()[i-2*n][j-2*n], x[k][i][j]);
					}
				}
			}

			cplex.addMinimize(objective);
			
			// CONSTRAINTS
			// N'importe quelle origine a au maximum 1 arc sortant
			for (int o = 0; o < V; o++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int k = 0; k < O; k++) {
					for (int j = 0; j < V; j++) {
						expr.addTerm(1, x[k][o][j]);
					}
				}
				cplex.addLe(expr, 1);
			}

			// On ne se rend pas d'un sommet a lui meme
			for (int k = 0; k < O; k++) {
				for (int i = 0; i < V; i++) {
					if (i != k && i != k + 2 * n) {
						IloLinearNumExpr expr = cplex.linearNumExpr();
						expr.addTerm(1, x[k][i][i]);
						cplex.addEq(expr, 0);
					}
				}
			}
			
			// Un sommet i n'est pris que par un et un seul conducteur
			for (int i = 0; i < V; i++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int k = 0; k < O; k++) {
					expr.addTerm(1, z[k][i]);
				}
				cplex.addEq(expr, 1);
			}
			
			// Interdiction que si x[i][j] alors x[j][i] (pas de cycle)
			for (int k = 0; k < O; k++) {
				for (int i = 0; i < V; i++) {
					for (int j = 0; j < V; j++) {
						IloLinearNumExpr expr = cplex.linearNumExpr();
						if (i != j) {
							expr.addTerm(1, x[k][i][j]);
						}
						expr.addTerm(1, x[k][j][i]);
						cplex.addLe(expr, 1);
					}
				}
			}

			// Un meme sommet : 1 fois en destination si visite, 2 fois si conducteur
			for (int k = 0; k < O; k++) {
				for (int i = 0; i < V; i++) {
					IloLinearNumExpr expr = cplex.linearNumExpr();
					for (int j = 0; j < V; j++) {
						expr.addTerm(1, x[k][i][j]);
					}
					cplex.addLe(expr, 2);
				}
			}
			
			// Un meme sommet : 1 fois en destination si visite, 2 fois si conducteur
			for (int k = 0; k < O; k++) {
				for (int i = 0; i < V; i++) {
					IloLinearNumExpr expr = cplex.linearNumExpr();
					for (int j = 0; j < V; j++) {
						if (j != k && j != k + 2 * n) {
							expr.addTerm(1, x[k][i][j]);
						}
					}
					cplex.addLe(expr, 1);
				}
			}

			// L'heure vaut autre chose que 0 uniquement si le sommet est pris
			for (int k = 0; k < O; k++) {
				for (int i = 0; i < V; i++) {
					IloLinearNumExpr expr = cplex.linearNumExpr();
					expr.addTerm(1, b[k][i]);
					IloLinearNumExpr expr2 = cplex.linearNumExpr();
					expr2.addTerm(M, z[k][i]);
					cplex.addLe(expr, expr2);
				}
			}
			
			// Chaque personne arrive forcement une et une seule fois a destination 0
			for (int d = O; d < D; d++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int k = 0; k < O; k++) {
					for (int i = 0; i < U; i++) {
						expr.addTerm(1, x[k][i][d]);
					}
				}
				cplex.addEq(expr, 1);
			}
			for (int d = K; d < L; d++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int k = 0; k < O; k++) {
					for (int i = U; i < W; i++) {
						expr.addTerm(1, x[k][i][d]);
					}
				}
				cplex.addEq(expr, 1);
			}
			
			// Chaque personne part forcement une et une seule fois de son origine
			for (int o = 0; o < O; o++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int k = 0; k < O; k++) {
					for (int j = 0; j < U; j++) {
						if (j != o) {
							expr.addTerm(1, x[k][o][j]);
						}
					}
				}
				cplex.addEq(expr, 1);
			}
			for (int o = U; o < K; o++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int k = 0; k < O; k++) {
					for (int j = U; j < W; j++) {
						if (j != o) {
							expr.addTerm(1, x[k][o][j]);
						}
					}
				}
				cplex.addEq(expr, 1);
			}
			
			// Un conducteur part forcement de chez lui a un moment 0
			for (int k = 0; k < O; k++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				IloLinearNumExpr expr2 = cplex.linearNumExpr();
				for (int j = 0; j < U; j++) {
					if (j != k) {
						expr.addTerm(1, x[k][k][j]);
					}
				}
				expr2.addTerm(1, y[k]);
				cplex.addEq(expr, expr2);
			}
			for (int k = U; k < K; k++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				IloLinearNumExpr expr2 = cplex.linearNumExpr();
				for (int j = U; j < W; j++) {
					if (j != k) {
						expr.addTerm(1, x[k - 2 * n][k][j]);
					}
				}
				expr2.addTerm(1, y[k - 2 * n]);
				cplex.addEq(expr, expr2);
			}
			
			// Le conducteur k qui prend le passager o le depose a sa destination d
			for (int k = 0; k < O; k++) {
				for (int o = 0; o < O; o++) {
					IloLinearNumExpr expr = cplex.linearNumExpr();
					IloLinearNumExpr expr2 = cplex.linearNumExpr();
					expr.addTerm(1, z[k][o]);
					expr2.addTerm(1, z[k][o + n]);
					cplex.addEq(expr, expr2);
				}
			}
			for (int k = 0; k < O; k++) {
				for (int o = U; o < K; o++) {
					IloLinearNumExpr expr = cplex.linearNumExpr();
					IloLinearNumExpr expr2 = cplex.linearNumExpr();
					expr.addTerm(1, z[k][o]);
					expr2.addTerm(1, z[k][o + n]);
					cplex.addEq(expr, expr2);
				}
			}
			
			// Un conducteur se prend lui meme
			for (int k = 0; k < O; k++) {
				cplex.addEq(y[k], z[k][k]);
				cplex.addEq(y[k], z[k][k + 2 * n]);
				cplex.addEq(y[k], x[k][k][k]);
				cplex.addEq(y[k], x[k][k + 2 * n][k + 2 * n]);
				cplex.addEq(z[k][k], x[k][k][k]);
				cplex.addEq(z[k][k], x[k][k + 2 * n][k + 2 * n]);
			}
			
			// Un conducteur ne part pas de sa destination
			for (int k = 0; k < O; k++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int j = 0; j < U; j++) {
					expr.addTerm(1, x[k][k + n][j]);
				}
				cplex.addEq(expr, 0);
			}
			for (int k = 0; k < O; k++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int j = U; j < W; j++) {
					expr.addTerm(1, x[k][k + 3 * n][j]);
				}
				cplex.addEq(expr, 0);
			}
			
			// Si i est pris par k alors k se rend a i une et une seule fois
			for (int k = 0; k < O; k++) {
				for (int i = 0; i < U; i++) {
					IloLinearNumExpr expr = cplex.linearNumExpr();
					expr.addTerm(1, z[k][i]);
					IloLinearNumExpr expr2 = cplex.linearNumExpr();
					for (int j = 0; j < U; j++) {
						expr2.addTerm(1, x[k][j][i]);
					}
					cplex.addEq(expr, expr2);
				}
			}
			for (int k = 0; k < O; k++) {
				for (int i = U; i < W; i++) {
					IloLinearNumExpr expr = cplex.linearNumExpr();
					expr.addTerm(1, z[k][i]);
					IloLinearNumExpr expr2 = cplex.linearNumExpr();
					for (int j = U; j < W; j++) {
						expr2.addTerm(1, x[k][j][i]);
					}
					cplex.addEq(expr, expr2);
				}
			}
			
			// Chaque conducteur se prend lui meme au retour
			for (int k = U; k < K; k++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				cplex.addEq(x[k - 2 * n][k][k], y[k - 2 * n]);
			}
			
			// TEMPPPPPPPPPPPPPPPPPPPPS ******************************

			
			// La somme des trajets d'un conducteur ne doit pas depasser son temps max de
			// trajet
			for (int k = 0; k < O; k++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int i = 0; i < U; i++) {
					for (int j = 0; j < U; j++) {
						expr.addTerm(C.A()[i][j], x[k][i][j]);
					}
				}
				cplex.addLe(expr, drivers.getG()[k]);
			}
			for (int k = 0; k < O; k++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int i = U; i < W; i++) {
					for (int j = U; j < W; j++) {
						expr.addTerm(C.R()[i - 2 * n][j - 2 * n], x[k][i][j]);
					}
				}
				cplex.addLe(expr, drivers.getR()[k]);
			}
			
			// Suite : Ordonnancement des heures de passages
			for (int k = 0; k < O; k++) {
				for (int i = 0; i < U; i++) {
					for (int j = 0; j < U; j++) {
						if (j != i) {
							IloLinearNumExpr expr = cplex.linearNumExpr();
							expr.addTerm(1, b[k][i]);
							expr.addTerm((-1), b[k][j]);
							expr.addTerm(C.A()[i][j], x[k][i][j]);
							expr.addTerm(hours.getB()[k], x[k][i][j]);
							expr.addTerm((-1) * C.A()[j][i], x[k][j][i]);
							expr.addTerm(hours.getB()[k], x[k][j][i]);
							cplex.addGe(expr,hours.getB()[k]);
							System.out.println(hours.getB()[k]);
						}
					}
				}
			}
			for (int k = 0; k < O; k++) {
				for (int i = U; i < W; i++) {
					for (int j = U; j < W; j++) {
						if (j != i) {
							IloLinearNumExpr expr = cplex.linearNumExpr();
							expr.addTerm(1, b[k][i]);
							expr.addTerm((-1), b[k][j]);
							expr.addTerm(C.R()[i - 2 * n][j - 2 * n], x[k][i][j]);
							expr.addTerm(M, x[k][i][j]);
							expr.addTerm(M, x[k][j][i]);
							expr.addTerm((-1) * C.R()[j - 2 * n][i - 2 * n], x[k][j][i]);
							cplex.addGe(M, expr);
						}
					}
				}
			}
			
			// L'heure de passage d'un conducteur a un sommet est inferieure ou egale a
			// l'heure de debut de travail si k passe par i
			for (int k = 0; k < O; k++) {
				for (int i = 0; i < U; i++) {
					IloLinearNumExpr expr = cplex.linearNumExpr();
					expr.addTerm(hours.getB()[k], z[k][i]);
					cplex.addLe(b[k][i], expr);
				}
			}
			for(int k=0;k<O;k++) {
				for(int i=U;i<W;i++) {
					IloLinearNumExpr expr = cplex.linearNumExpr();
					expr.addTerm(hours.getE()[k], z[k][i]);
					cplex.addGe(b[k][i], expr);
					System.out.println(hours.getE()[k]);
				}
			}
			
			// Forcer tout le monde a arriver entre 0 et x min d'avance
			for (int v = 0; v < O; v++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int k = 0; k < O; k++) {
					expr.addTerm(1, b[k][v+n]);
				}
				cplex.addLe(expr,hours.getB()[v]);
				int temp = (hours.getB()[v])-WaitingTimeA;
				cplex.addGe(expr,temp);
			}
			for (int v = U; v < K; v++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int k = 0; k < O; k++) {
					expr.addTerm(1, b[k][v]);
				}
				cplex.addGe(expr,hours.getE()[v-2*n]);
			}
			for (int v = U; v < K; v++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int k = 0; k < O; k++) {
					expr.addTerm(1, b[k][v]);
				}
				int temp = (hours.getE()[v-2*n])+WaitingTimeR;
				cplex.addLe(expr,temp);
			}
			
			 //L'heure de depart ne peut pas etre inferieure a l'heure d'arrivee - le temps max de trajet
			for (int v = 0; v < O; v++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int k = 0; k < O; k++) {
					expr.addTerm(1, b[k][v+n]);
					expr.addTerm((-1), b[k][v]);
				}
				cplex.addLe(expr,drivers.getG()[v]);
				cplex.addGe(expr,C.A()[v][v+n]);
			}
			for (int v = U; v < K; v++) {
				IloLinearNumExpr expr = cplex.linearNumExpr();
				for (int k = 0; k < O; k++) {
					expr.addTerm(1, b[k][v+n]);
					expr.addTerm((-1), b[k][v]);
				}
				cplex.addLe(expr,drivers.getR()[v-2*n]);
				cplex.addGe(expr,C.R()[v-2*n][v-2*n+n]);
			}
			
			
			// SOLVE
			if (cplex.solve()) {
				System.out.println("Objective = " + cplex.getObjValue());
				System.out.print("z=[\n[");
				for (int k = 0; k < O; k++) {
					for (int i = 0; i < V; i++) {
						if (i != V - 1) {
							System.out.print((int) cplex.getValue(z[k][i]) + " ");
						} else {
							System.out.print((int) cplex.getValue(z[k][i]));
						}
					}
					if (k != O - 1) {
						System.out.print("]\n[");
					} else {
						System.out.println("]");
					}
				}
				System.out.print("b=[\n[");
				for (int k = 0; k < O; k++) {
					for (int i = 0; i < V; i++) {
						if (i != V - 1) {
							System.out.print((int) cplex.getValue(b[k][i]) + " ");
						} else {
							System.out.print((int) cplex.getValue(b[k][i]));
						}
					}
					if (k != O - 1) {
						System.out.print("]\n[");
					} else {
						System.out.println("]");
					}
				}
			} else {
				System.out.println("No solution");
			}
			cplex.end();
		} catch (

		IloException exc) {
			exc.printStackTrace();
		}
	}
}

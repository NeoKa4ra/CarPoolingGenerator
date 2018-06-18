package Classes.Instanciation;

import java.awt.Point;
import java.util.Random;

import Classes.Constants;

public class CostMatrices {
	private int morningM[][];
	private int eveningM[][];
	private int rdmRange = 10;
	private int rdmCloseRange = 1;
	Vertices v;
	private int diagonal = 0;

	// Generate a random matrix[n][n] and the range r of the random numbers
	public CostMatrices(Vertices vertices, int mode, int range) {
		Random randomGenerator = new Random();
		this.v = vertices;
		this.rdmRange = (int) range / 2;
		this.morningM = new int[this.v.getOriginsW() + this.v.getDestinationW()][this.v.getOriginsW()
				+ this.v.getDestinationW()];
		this.eveningM = new int[this.v.getOriginsH() + this.v.getDestinationH()][this.v.getOriginsH()
				+ this.v.getDestinationH()];

		// For the close work and houses
		int percentageClosure = 5;
		this.rdmCloseRange = (percentageClosure * range / 100) > 1 ? (percentageClosure * range / 100) : 1;
		int rdmNumber = 0;
		Point[] V = new Point[4*v.nPersons];

		// Generates a scenario following the mode given
		switch (mode) {
		case Constants.RW: // random matrix to go to work
			for (int i = 0; i < this.v.getOriginsW() + this.v.getDestinationW(); i++) {
				for (int j = i; j < this.v.getOriginsW() + this.v.getDestinationW(); j++) {
					if (i == j) {
						this.morningM[i][j] = diagonal;
					} else {
						// to get a number between 1 and rdmRange
						rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						// Symmetric matrix
						this.morningM[i][j] = rdmNumber;
						this.morningM[j][i] = rdmNumber;
					}
				}
			}

			for (int i = 0; i < this.v.getOriginsH() + this.v.getDestinationH(); i++) {
				for (int j = i; j < this.v.getOriginsH() + this.v.getDestinationH(); j++) {
					if (i == j) {
						this.eveningM[i][j] = diagonal;
					} else {
						// to get a number between 1 and rdmRange
						rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						// Symmetric matrix
						this.eveningM[i][j] = rdmNumber;
						this.eveningM[j][i] = rdmNumber;
					}
				}
			}
			break;

		case Constants.RCW: // random matrix with close houses and close works
			for (int i = 0; i < this.v.getOriginsW() + this.v.getDestinationW(); i++) {
				for (int j = i; j < this.v.getOriginsW() + this.v.getDestinationW(); j++) {
					if (i == j) {
						this.morningM[i][j] = diagonal;
					} else {
						if (i < v.getOriginsW() && j < v.getOriginsW()) {
							// Vertices from homes to homes
							rdmNumber = randomGenerator.nextInt(rdmCloseRange) + 1;
						} else if (i >= v.getOriginsW() && j >= v.getOriginsW()) {
							// Vertices from works to works
							rdmNumber = randomGenerator.nextInt(rdmCloseRange) + 1;
						} else {
							rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						}
						this.morningM[i][j] = rdmNumber;
						this.morningM[j][i] = rdmNumber;
					}
				}
			}

			for (int i = 0; i < this.v.getOriginsH() + this.v.getDestinationH(); i++) {
				for (int j = i; j < this.v.getOriginsH() + this.v.getDestinationH(); j++) {
					if (i == j) {
						this.eveningM[i][j] = diagonal;
					} else {
						if (i < v.getOriginsH() && j < v.getOriginsH()) {
							// Vertices from homes to homes
							rdmNumber = randomGenerator.nextInt(rdmCloseRange) + 1;
						} else if (i >= v.getOriginsH() && j >= v.getOriginsH()) {
							// Vertices from works to works
							rdmNumber = randomGenerator.nextInt(rdmCloseRange) + 1;
						} else {
							rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						}
						this.eveningM[i][j] = rdmNumber;
						this.eveningM[j][i] = rdmNumber;
					}
				}
			}
			break;

		case Constants.RSW: // random matrix with the same work
			for (int i = 0; i < this.v.getOriginsW() + this.v.getDestinationW(); i++) {
				for (int j = i; j < this.v.getOriginsW() + this.v.getDestinationW(); j++) {
					if (i == j) {
						this.morningM[i][j] = diagonal;
					} else {
						if (i < v.getOriginsW() && j < v.getOriginsW()) {
							// Vertices from homes to homes
							rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						} else if (i >= v.getOriginsW() && j >= v.getOriginsW()) {
							// Vertices from works to works
							rdmNumber = 0;
						} else {
							rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						}
						this.morningM[i][j] = rdmNumber;
						this.morningM[j][i] = rdmNumber;
					}
				}
			}

			for (int i = 0; i < this.v.getOriginsH() + this.v.getDestinationH(); i++) {
				for (int j = i; j < this.v.getOriginsH() + this.v.getDestinationH(); j++) {
					if (i == j) {
						this.eveningM[i][j] = diagonal;
					} else {
						if (i < v.getOriginsH() && j < v.getOriginsH()) {
							// Vertices from works to works
							rdmNumber = 0;
						} else if (i >= v.getOriginsH() && j >= v.getOriginsH()) {
							// Vertices from homes to homes
							rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						} else {
							rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						}
						this.eveningM[i][j] = rdmNumber;
						this.eveningM[j][i] = rdmNumber;
					}
				}
			}
			break;

		// ************************ RANDOM POINT CLOUD *************************//
		case Constants.RPC:
			for (int i = 0; i < 4*this.v.getPersons(); i++) {
				V[i] = new Point(randomGenerator.nextInt(rdmRange - 1) + 1,
						randomGenerator.nextInt(rdmRange - 1) + 1);
			}

			for (int i = 0; i < 2*this.v.getPersons(); i++) {
				for (int j = i; j < 2*this.v.getPersons(); j++) {
					if (i == j) {
						this.morningM[i][j] = diagonal;
					} else {

						// Vertices from homes to homes
						rdmNumber = (int) Math.sqrt(Math.pow(V[i].getX() - V[j].getX(), 2)
								+ Math.pow(V[i].getY() - V[j].getY(), 2));
						this.morningM[i][j] = rdmNumber;
						this.morningM[j][i] = rdmNumber;
					}
				}
			}

			for (int i = 0; i < 2*this.v.getOriginsH(); i++) {
				for (int j = i; j < 2*this.v.getOriginsH(); j++) {
					if (i == j) {
						this.eveningM[i][j] = diagonal;
					} else {

						// Vertices from homes to homes
						rdmNumber = (int) Math.sqrt(Math.pow(V[i+2*this.v.getPersons()].getX() - V[j+2*this.v.getPersons()].getX(), 2)
								+ Math.pow(V[i+2*this.v.getPersons()].getY() - V[j+2*this.v.getPersons()].getY(), 2));
						this.eveningM[i][j] = rdmNumber;
						this.eveningM[j][i] = rdmNumber;
					}
				}
			}
			break;

		default: // random matrix to go to work
			for (int i = 0; i < this.v.getOriginsW() + this.v.getDestinationW(); i++) {
				for (int j = i; j < this.v.getOriginsW() + this.v.getDestinationW(); j++) {
					if (i == j) {
						this.morningM[i][j] = diagonal;
					} else {
						// to get a number between 1 and rdmRange
						rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						// Symmetric matrix
						this.morningM[i][j] = rdmNumber;
						this.morningM[j][i] = rdmNumber;
					}
				}
			}

			for (int i = 0; i < this.v.getOriginsH() + this.v.getDestinationH(); i++) {
				for (int j = i; j < this.v.getOriginsH() + this.v.getDestinationH(); j++) {
					if (i == j) {
						this.eveningM[i][j] = diagonal;
					} else {
						// to get a number between 1 and rdmRange
						rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						// Symmetric matrix
						this.eveningM[i][j] = rdmNumber;
						this.eveningM[j][i] = rdmNumber;
					}
				}
			}
		}

	}

	// Print the matrix in the carpooling needed form
	public String toString() {
		String str = "//ARCS\n";
		str += "// C[1..2][i][j] = cost to travel from i to j to work\n";
		str += "C=[[\n";
		for (int i = 0; i < this.v.getOriginsW() + this.v.getDestinationW(); i++) {
			str += '[';
			for (int j = 0; j < this.v.getOriginsW() + this.v.getDestinationW(); j++) {
				str += this.morningM[i][j];
				if (j != this.v.getOriginsW() + this.v.getDestinationW() - 1) {
					str += ", ";
				}
			}
			if (i != this.v.getOriginsW() + this.v.getDestinationW() - 1) {
				str += "],\n";
			} else {
				str += "]\n";
			}
		}
		str += "],[\n";
		for (int i = 0; i < this.v.getOriginsH() + this.v.getDestinationH(); i++) {
			str += '[';
			for (int j = 0; j < this.v.getOriginsH() + this.v.getDestinationH(); j++) {
				str += this.eveningM[i][j];
				if (j != this.v.getOriginsH() + this.v.getDestinationH() - 1) {
					str += ", ";
				}
			}
			if (i != this.v.getOriginsH() + this.v.getDestinationH() - 1) {
				str += "],\n";
			} else {
				str += "]\n";
			}
		}
		str += "]];\n";
		return str;
	}

	// ********************* GETTERS/SETTERS ********************* //
	public int[][] A() {
		return this.morningM.clone();
	}

	public int[][] R() {
		return this.eveningM.clone();
	}

	public Vertices getV() {
		return this.v;
	}

	public int getRange() {
		return this.rdmRange;
	}

	public int getDiagonal() {
		return this.diagonal;
	}
}

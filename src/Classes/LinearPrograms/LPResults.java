package Classes.LinearPrograms;

public class LPResults {
	private int n;
	private double objective;
	private boolean x[][][];
	private boolean y[];
	private boolean z[][];
	private int b[][];
	private int q[][];
	private double ExecTime;

	public LPResults(int nPersons) {
		this.n = nPersons;
		this.x = new boolean[nPersons][4 * nPersons][4 * nPersons];
		this.y = new boolean[nPersons];
		this.z = new boolean[nPersons][4 * nPersons];
		this.b = new int[nPersons][4 * nPersons];
		this.q = new int[nPersons][4 * nPersons];
		this.objective = 0.0;
	}

	public String toString() {
		String str;
		str = "Objective = " + this.objective + "\n";
		str += "x=[[\n[";
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < 4 * n; i++) {
				for (int j = 0; j < 4 * n; j++) {
					str += (this.x[k][i][j]==true)?1:0;
					if (j != 4 * n - 1) {
						str += " ";
					}
				}
				if (i != 4 * n - 1) {
					str += "]\n[";
				} else {
					str += "]";
				}
			}
			if (k != n - 1) {
				str += "]\n[";
			} else {
				str += "]";
			}
		}
		str += "\n];\n";
		str += "y=[";
		for (int k = 0; k < n; k++) {
			str += (this.y[k]==true)?1:0;
			if (k != n - 1) {
				str += " ";
			}
		}
		str += "];\n";
		str += "z=[\n[";
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < 4 * n; i++) {
				str += (this.z[k][i]==true)?1:0;
				if (i != 4 * n - 1) {
					str += " ";
				}
			}
			if (k != n - 1) {
				str += "]\n[";
			} else {
				str += "]";
			}
		}
		str += "\n];\n";
		str += "b=[\n[";
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < 4 * n; i++) {
				str += this.b[k][i];
				if (i != 4 * n - 1) {
					str += " ";
				}
			}
			if (k != n - 1) {
				str += "]\n[";
			} else {
				str += "]";
			}
		}
		str += "\n];\n";
		str += "q=[\n[";
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < 4 * n; i++) {
				str += this.q[k][i];
				if (i != 4 * n - 1) {
					str += " ";
				}
			}
			if (k != n - 1) {
				str += "]\n[";
			} else {
				str += "]";
			}
		}
		str += "\n];\n";
		return str;
	}

	// SETTERS
	public void setObjective(double newObj) {
		this.objective = newObj;
	}

	public void setx(int k, int i, int j, boolean value) {
		this.x[k][i][j] = value;
	}

	public void sety(int k, boolean value) {
		this.y[k] = value;
	}

	public void setz(int k, int v, boolean value) {
		this.z[k][v] = value;
	}

	public void setq(int k, int v, int value) {
		this.q[k][v] = value;
	}

	public void setb(int k, int v, int value) {
		this.b[k][v] = value;
	}
	
	public void setExecTime(double ET) {
		this.ExecTime = ET;
	}

	// GETTERS
	public double getObjective() {
		return this.objective;
	}

	public double getExecTime() {
		return this.ExecTime;
	}
	
	public boolean[][][] getx() {
		return this.x.clone();
	}

	public boolean[] gety() {
		return this.y.clone();
	}

	public boolean[][] getz() {
		return this.z.clone();
	}

	public int[][] getq() {
		return this.q.clone();
	}

	public int[][] getb() {
		return this.b.clone();
	}
}

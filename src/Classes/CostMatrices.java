package Classes;

import java.util.Random;

public class CostMatrices {
	private int mW[][];
	private int mH[][];
	private int rdmRange = 10;
	private int rdmCloseRange = 1;
	Vertices v;
	private int diagonal = 0;

	// Generate a random matrix[n][n] and the range r of the random numbers
	public CostMatrices(Vertices vertices, int mode, int range) {
		Random randomGenerator = new Random();
		this.v = vertices;
		this.rdmRange = (int) range / 2;
		this.mW = new int[this.v.getOriginsW() + this.v.getDestinationW()][this.v.getOriginsW()
				+ this.v.getDestinationW()];
		this.mH = new int[this.v.getOriginsH() + this.v.getDestinationH()][this.v.getOriginsH()
				+ this.v.getDestinationH()];

		// For the close work and houses
		int percentageClosure = 5;
		this.rdmCloseRange = (percentageClosure * range / 100) > 1 ? (percentageClosure * range / 100) : 1;
		int rdmNumber = 0;

		// Generates a scenario following the mode given
		switch (mode) {
		case Constants.RW: // random matrix to go to work
			for (int i = 0; i < this.v.getOriginsW() + this.v.getDestinationW(); i++) {
				for (int j = i; j < this.v.getOriginsW() + this.v.getDestinationW(); j++) {
					if (i == j) {
						this.mW[i][j] = diagonal;
					} else {
						// to get a number between 1 and rdmRange
						rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						// Symmetric matrix
						this.mW[i][j] = rdmNumber;
						this.mW[j][i] = rdmNumber;
					}
				}
			}

			for (int i = 0; i < this.v.getOriginsH() + this.v.getDestinationH(); i++) {
				for (int j = i; j < this.v.getOriginsH() + this.v.getDestinationH(); j++) {
					if (i == j) {
						this.mH[i][j] = diagonal;
					} else {
						// to get a number between 1 and rdmRange
						rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						// Symmetric matrix
						this.mH[i][j] = rdmNumber;
						this.mH[j][i] = rdmNumber;
					}
				}
			}
			break;

		case Constants.RCW: // random matrix with close houses and close works
			for (int i = 0; i < this.v.getOriginsW() + this.v.getDestinationW(); i++) {
				for (int j = i; j < this.v.getOriginsW() + this.v.getDestinationW(); j++) {
					if (i == j) {
						this.mW[i][j] = diagonal;
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
						this.mW[i][j] = rdmNumber;
						this.mW[j][i] = rdmNumber;
					}
				}
			}

			for (int i = 0; i < this.v.getOriginsH() + this.v.getDestinationH(); i++) {
				for (int j = i; j < this.v.getOriginsH() + this.v.getDestinationH(); j++) {
					if (i == j) {
						this.mH[i][j] = diagonal;
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
						this.mH[i][j] = rdmNumber;
						this.mH[j][i] = rdmNumber;
					}
				}
			}
			break;

		case Constants.RSW: // random matrix with the same work
			for (int i = 0; i < this.v.getOriginsW() + this.v.getDestinationW(); i++) {
				for (int j = i; j < this.v.getOriginsW() + this.v.getDestinationW(); j++) {
					if (i == j) {
						this.mW[i][j] = diagonal;
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
						this.mW[i][j] = rdmNumber;
						this.mW[j][i] = rdmNumber;
					}
				}
			}

			for (int i = 0; i < this.v.getOriginsH() + this.v.getDestinationH(); i++) {
				for (int j = i; j < this.v.getOriginsH() + this.v.getDestinationH(); j++) {
					if (i == j) {
						this.mH[i][j] = diagonal;
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
						this.mH[i][j] = 0;
						this.mH[j][i] = 0;
					}
				}
			}
			break;

		case Constants.RxSW: // random matrix with between 1 and x same works

			break;

		default: // random matrix to go to work
			for (int i = 0; i < this.v.getOriginsW() + this.v.getDestinationW(); i++) {
				for (int j = i; j < this.v.getOriginsW() + this.v.getDestinationW(); j++) {
					if (i == j) {
						this.mW[i][j] = diagonal;
					} else {
						// to get a number between 1 and rdmRange
						rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						// Symmetric matrix
						this.mW[i][j] = rdmNumber;
						this.mW[j][i] = rdmNumber;
					}
				}
			}

			for (int i = 0; i < this.v.getOriginsH() + this.v.getDestinationH(); i++) {
				for (int j = i; j < this.v.getOriginsH() + this.v.getDestinationH(); j++) {
					if (i == j) {
						this.mH[i][j] = diagonal;
					} else {
						// to get a number between 1 and rdmRange
						rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						// Symmetric matrix
						this.mH[i][j] = rdmNumber;
						this.mH[j][i] = rdmNumber;
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
				str += this.mW[i][j];
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
				str += this.mH[i][j];
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
	public int[][] getMW() {
		return this.mW.clone();
	}

	public int[][] getMH() {
		return this.mH.clone();
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

package Classes;

import java.util.Random;

public class CostMatrices {
	private int m[][];
	private int rdmRange = 10;
	private int rdmCloseRange = 1;
	private int mLenY = 10;
	private int mLenX = 10;
	private int diagonal = 0;

	// Generate a random symmetric square matrix[n][n]
	public CostMatrices(int nSommets) {
		// Initialize the random generator
		Random randomGenerator = new Random();
		this.mLenY = nSommets;
		this.mLenX = this.mLenY;
		this.m = new int[mLenX][mLenY];
		int rdmNumber;
		for (int i = 0; i < mLenY; i++) {
			for (int j = i; j < mLenY; j++) {
				if (i == j) {
					this.m[i][j] = diagonal;
				} else {
					// to get a number between 1 and rdmRange
					rdmNumber = randomGenerator.nextInt(rdmRange - 1) + 1;
					// Symmetric matrix
					this.m[i][j] = rdmNumber;
					this.m[j][i] = rdmNumber;
				}
			}
		}
	}

	// Generate a random matrix[n][n] and the range r of the random numbers
	public CostMatrices(Vertices v, int mode, int range) {
		Random randomGenerator = new Random();
		this.mLenX = v.getVertices();
		this.mLenY = v.getVertices();
		this.rdmRange = range;
		this.m = new int[mLenX][mLenY];

		int percentageClosure = 5;
		this.rdmCloseRange = (percentageClosure * range / 100) > 1 ? (percentageClosure * range / 100) : 1;
		int rdmNumber = 0;

		// Generates a scenario following the mode given
		switch (mode) {
		case Constants.RW: // random matrix to go to work
			for (int i = 0; i < mLenY; i++) {
				for (int j = i; j < mLenY; j++) {
					if (i == j) {
						this.m[i][j] = diagonal;
					} else {
						// to get a number between 1 and rdmRange
						rdmNumber = randomGenerator.nextInt(rdmRange - 1) + 1;
						// Symmetric matrix
						this.m[i][j] = rdmNumber;
						this.m[j][i] = rdmNumber;
					}
				}
			}
			break;

		case Constants.RCW: // random matrix with close houses and close works
			for (int i = 0; i < mLenY; i++) {
				for (int j = i; j < mLenY; j++) {
					if (i == j) {
						this.m[i][j] = diagonal;
					} else {
						if (i < v.getOriginsW() && j < v.getOriginsW()) {
							// Vertices from homes to homes
							rdmNumber = randomGenerator.nextInt(rdmCloseRange) + 1;
						} else if (i >= v.getOriginsW() && j >= v.getOriginsW()) {
							// Vertices from works to works
							rdmNumber = randomGenerator.nextInt(rdmCloseRange) + 1;
						} else {
							rdmNumber = randomGenerator.nextInt(rdmRange - 1) + 1;
						}
						this.m[i][j] = rdmNumber;
						this.m[j][i] = rdmNumber;
					}
				}
			}
			break;

		case Constants.RSW: // random matrix with the same work

			break;

		case Constants.RxSW: // random matrix with between 1 and x same works

			break;

		default: // random matrix to go to work
			for (int i = 0; i < mLenY; i++) {
				for (int j = i; j < mLenY; j++) {
					if (i == j) {
						this.m[i][j] = diagonal;
					} else {
						// to get a number between 1 and rdmRange
						rdmNumber = randomGenerator.nextInt(rdmRange - 1) + 1;
						// Symmetric matrix
						this.m[i][j] = rdmNumber;
						this.m[j][i] = rdmNumber;
					}
				}
			}
		}

	}

	// Print the matrix in the carpooling needed form
	public String toString() {
		String str = "//ARCS\n";
		str += "// cA[i][j] = cost to travel from i to j to work\n";
		str += "cA=[\n";
		for (int i = 0; i < mLenY; i++) {
			str += '[';
			for (int j = 0; j < mLenX; j++) {
				str += this.m[i][j];
				if (j != mLenX - 1) {
					str += ", ";
				}
			}
			if (i != mLenY - 1) {
				str += "],\n";
			} else {
				str += "]\n";
			}
		}
		str += "];\n";
		return str;
	}

	// ********************* GETTERS/SETTERS ********************* //
	public int[][] getM() {
		return this.m.clone();
	}

	public int getRange() {
		return this.rdmRange;
	}

	public int getLengthX() {
		return this.mLenX;
	}

	public int getLengthY() {
		return this.mLenY;
	}

	public int getDiagonal() {
		return this.diagonal;
	}
}

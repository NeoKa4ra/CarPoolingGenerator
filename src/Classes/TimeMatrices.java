package Classes;

import java.util.Random;

public class TimeMatrices {
	private int mW[][];
	private int mH[][];
	private int rdmRange = 10;
	private Vertices v;
	private int diagonal = 0;

	public TimeMatrices(CostMatrices mC, int gapPercentage) {
		Random randomGenerator = new Random();
		this.mW = mC.getMW();
		this.mH = mC.getMH();
		this.v = mC.getV();
		this.rdmRange = mC.getRange();
		this.diagonal = mC.getDiagonal();
		int rdmNumber;
		for (int i = 0; i < this.v.getOriginsW()+this.v.getDestinationW(); i++) {
			for (int j = 0; j < this.v.getOriginsW()+this.v.getDestinationW(); j++) {
				rdmNumber = randomGenerator.nextInt((2 * gapPercentage * this.mW[i][j]) / 100 + 1);
				this.mW[i][j] = this.mW[i][j] + rdmNumber - (gapPercentage * this.mW[i][j]) / 100;
			}
		}
		for (int i = 0; i < this.v.getOriginsH()+this.v.getDestinationH(); i++) {
			for (int j = 0; j < this.v.getOriginsH()+this.v.getDestinationH(); j++) {
				rdmNumber = randomGenerator.nextInt((2 * gapPercentage * this.mH[i][j]) / 100 + 1);
				this.mH[i][j] = this.mH[i][j] + rdmNumber - (gapPercentage * this.mH[i][j]) / 100;
			}
		}
	}

	// Print the matrix in the carpooling needed form
	public String toString() {
		String str = "//tA[i][j] = time to travel from i to j to work\n";
		str += "tA=[\n";
		for (int i = 0; i < this.v.getOriginsW()+this.v.getDestinationW(); i++) {
			str += '[';
			for (int j = 0; j < this.v.getOriginsW()+this.v.getDestinationW(); j++) {
				str += this.mW[i][j];
				if (j != this.v.getOriginsW()+this.v.getDestinationW() - 1) {
					str += ", ";
				}
			}
			if (i != this.v.getOriginsW()+this.v.getDestinationW() - 1) {
				str += "],\n";
			} else {
				str += "]\n";
			}
		}
		str += "];\n";

		str += "//tR[i][j] = time to travel from i to j to home\n";
		str += "tR=[\n";
		for (int i = 0; i < this.v.getOriginsH()+this.v.getDestinationH(); i++) {
			str += '[';
			for (int j = 0; j < this.v.getOriginsH()+this.v.getDestinationH(); j++) {
				str += this.mH[i][j];
				if (j != this.v.getOriginsH()+this.v.getDestinationH() - 1) {
					str += ", ";
				}
			}
			if (i != this.v.getOriginsH()+this.v.getDestinationH() - 1) {
				str += "],\n";
			} else {
				str += "]\n";
			}
		}
		str += "];\n";
		return str;
	}

	// Getters
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

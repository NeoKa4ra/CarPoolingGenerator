package Classes;

import java.util.Random;

public class TimeMatrices {
	private int m[][];
	private int rdmRange = 10;
	private int mLenY = 10;
	private int mLenX = 10;
	private int diagonal = 0;

	public TimeMatrices(CostMatrices mC, int gapPercentage) {
		Random randomGenerator = new Random();
		this.m = mC.getM();
		this.mLenY = mC.getLengthY();
		this.mLenX = mC.getLengthX();
		this.rdmRange = mC.getRange();
		this.diagonal = mC.getDiagonal();
		int rdmNumber;
		for (int i = 0; i < mLenY; i++) {
			for (int j = 0; j < mLenY; j++) {
				rdmNumber = randomGenerator.nextInt((2*gapPercentage  * this.m[i][j])/ 100 + 1);
				this.m[i][j] = this.m[i][j] + rdmNumber - (gapPercentage  * this.m[i][j])/ 100;
			}
		}
	}

	// Print the matrix in the carpooling needed form
	public void printMatrix() {
		System.out.println("tA=[");
		for (int i = 0; i < mLenY; i++) {
			System.out.print('[');
			for (int j = 0; j < mLenX; j++) {
				System.out.print(this.m[i][j]);
				if (j != mLenX - 1) {
					System.out.print(", ");
				}
			}
			if (i != mLenY - 1) {
				System.out.println("],");
			} else {
				System.out.println("]");
			}
		}
		System.out.println("];");
	}
	
	//Getters
		public int[][] getM(){
			return this.m.clone();
		}
		public int getRange(){
			return this.rdmRange;
		}
		public int getLengthX(){
			return this.mLenX;
		}
		public int getLengthY(){
			return this.mLenY;
		}
		public int getDiagonal(){
			return this.diagonal;
		}

}

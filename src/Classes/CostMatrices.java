package Classes;

import java.util.Random;

public class CostMatrices {
	private int m[][];
	private int rdmRange = 10;
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

	// Generate a random symmetric matrix[n][n] and the range r of the random
	// numbers
	public CostMatrices(int nSommets, int range) {
		Random randomGenerator = new Random();
		this.mLenY = nSommets;
		this.mLenX = this.mLenY;
		this.rdmRange = range;
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

	// Print the matrix in the carpooling needed form
	public void printMatrix() {
		System.out.println("cA=[");
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

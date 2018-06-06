package Classes;

import java.util.Random;

public class Drivers {
	private int[] capacity;
	private int[] G;
	private int[] R;
	private int nPotentialDrivers;
	private float multiplicator;
	private Vertices v;

	private void Initialisation() {
		this.nPotentialDrivers = v.getOriginsW();
		this.capacity = new int[nPotentialDrivers];
		this.G = new int[nPotentialDrivers];
		this.R = new int[nPotentialDrivers];
		this.multiplicator = (float) 1.5;
	}

	public Drivers(CostMatrices tM) {
		this.v = tM.getV();
		Initialisation();
		Random randomGenerator = new Random();
		for (int i = 0; i < nPotentialDrivers; i++) {
			this.capacity[i] = 2 + randomGenerator.nextInt(3);
			this.G[i] = (int) (tM.A()[i][i + v.getOriginsW()] * this.multiplicator);
		}
		for (int i = 0; i < v.getOriginsH(); i++) {
			this.R[i] = (int) (tM.R()[i][i + v.getOriginsH()] * this.multiplicator);
		}
	}

	public Drivers(TimeMatrices tM, int capacite) {
		this.v = tM.getV();
		Initialisation();
		for (int i = 0; i < v.getOriginsW(); i++) {
			this.capacity[i] = capacite;
			this.G[i] = (int) (tM.getMW()[i][i + v.getOriginsW()] * (1.5));
		}
		for (int i = 0; i < v.getOriginsH(); i++) {
			this.R[i] = (int) (tM.getMH()[i][i + v.getOriginsH()] * this.multiplicator);
		}
	}

	// Print the potential drivers in the carpooling needed form
	public String toString() {
		String str = "//DRIVERS\n";
		str += "//Capacity of the car\n";
		str += "Q=[";
		for (int i = 0; i < nPotentialDrivers; i++) {
			str += this.capacity[i];
			if (i != nPotentialDrivers - 1) {
				str += ",";
			}
		}
		str += "];\n";
		str += "//Maximal travel time of the driver\n";
		str += "G=[";
		for (int i = 0; i < nPotentialDrivers; i++) {
			str += this.G[i];
			if (i != nPotentialDrivers - 1) {
				str += ",";
			}
		}
		str += "];\n";
		
		str += "R=[";
		for (int i = 0; i < nPotentialDrivers; i++) {
			str += this.R[i];
			if (i != nPotentialDrivers - 1) {
				str += ",";
			}
		}
		str += "];\n";
		return str;
	}
	
	int[] getQ() {
		return this.capacity.clone();
	}
	
	int[] getG() {
		return this.G.clone();
	}
	
	int[] getR() {
		return this.R.clone();
	}

}

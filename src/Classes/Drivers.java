package Classes;

import java.util.Random;

public class Drivers {
	private int[] capacite;
	private int[] tempsTrajetMax;
	private int nPotentialDrivers;

	private void Initialisation(int n) {
		this.nPotentialDrivers = n;
		this.capacite = new int[nPotentialDrivers];
		this.tempsTrajetMax = new int[nPotentialDrivers];
	}

	public Drivers(int nOrigines, TimeMatrices tM) {
		Initialisation(nOrigines);
		Random randomGenerator = new Random();
		for (int i = 0; i < nOrigines; i++) {
			this.capacite[i] = 2 + randomGenerator.nextInt(3);
			this.tempsTrajetMax[i] = (int) (tM.getM()[i][i + nOrigines] * (1.5));
		}
	}

	public Drivers(int nOrigines, TimeMatrices tM, int capacite) {
		Initialisation(nOrigines);
		for (int i = 0; i < nOrigines; i++) {
			this.capacite[i] = capacite;
			this.tempsTrajetMax[i] = (int) (tM.getM()[i][i + nOrigines] * (1.5));
		}
	}

	// Print the potential drivers in the carpooling needed form
	public String toString() {
		String str = "//DRIVERS\n";
		str += "//Capacity of the car\n";
		str += "qV=[";
		for (int i = 0; i < nPotentialDrivers; i++) {
			str += this.capacite[i];
			if (i != nPotentialDrivers - 1) {
				str += ",";
			}
		}
		str += "];\n";
		str += "//Maximal travel time of the driver\n";
		str += "tV=[";
		for (int i = 0; i < nPotentialDrivers; i++) {
			str += this.tempsTrajetMax[i];
			if (i != nPotentialDrivers - 1) {
				str += ",";
			}
		}
		str += "];\n";
		return str;
	}

}

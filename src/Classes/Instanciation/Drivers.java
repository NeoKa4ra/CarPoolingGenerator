package Classes.Instanciation;

import java.util.Random;

public class Drivers {
	private int[] capacity;
	private int nPotentialDrivers;
	private Vertices v;

	public Drivers(CostMatrices cM) {
		this.v = cM.getV();
		this.nPotentialDrivers = v.getPersons();
		this.capacity = new int[nPotentialDrivers];
		Random randomGenerator = new Random();
		for (int i = 0; i < nPotentialDrivers; i++) {
			this.capacity[i] = 2 + randomGenerator.nextInt(3);
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
		return str;
	}

	public int[] getQ() {
		return this.capacity.clone();
	}
}

package Classes.Instanciation;

import java.util.Random;

public class Instance {
	private int n;
	private Matrice c;
	private Hours h;
	private int[] capacity;
	private int[] nPassengers;

	public Instance(int nbP, MatriceSettings MS, HoursSettings HS) {
		this.n = nbP;
		MS.setN(n);
		HS.setN(n);
		
		// Generation of the cost matrix
		this.c = new Matrice(MS);
		
		// Generation of the hours
		this.h = new Hours(HS);

		this.nPassengers = new int[2 * n];
		for (int i = 0; i < 2 * n; i++) {
			this.nPassengers[i] = (i < n) ? 1 : -1;
		}

		this.capacity = new int[n];
		Random randomGenerator = new Random();
		for (int i = 0; i < n; i++) {
			this.capacity[i] = 2 + randomGenerator.nextInt(3);
		}
	}

	public String toString() {
		String str = "//NUMBER OF VERTICES\n";
		str += "n=" + n + "; //Number of vertices origin to work\n";
		str = "//DRIVERS\n";
		str += "//Capacity of the car\n";
		str += "Q=[";
		for (int i = 0; i < n; i++) {
			str += this.capacity[i];
			if (i != n - 1) {
				str += ",";
			}
		}
		str += "];\n";
		str += "//NUMBER OF PARTICIPANTS ON EACH VERTEX\n";
		str += "nPassagers=[";
		for (int i = 0; i < 2 * n; i++) {
			str += this.nPassengers[i];
			if (i != 2 * n - 1) {
				str += ",";
			}
		}
		str += "];\n";
		return this.c.toString() + this.h.toString() + str;
	}

	// GETTERS

	public int getnPersons() {
		return this.n;
	}

	public Matrice getCostMatrices() {
		return this.c;
	}

	public Hours getHours() {
		return this.h;
	}

	public int[] getQ() {
		return this.capacity.clone();
	}

	public int[] getP() {
		return this.nPassengers.clone();
	}
}

package Classes;

import java.util.Random;

public class Passengers {
	private int[] nPassagers;
	private int nPersonnes;

	private void Initialisation(int nSommets) {
		this.nPersonnes = nSommets;
		this.nPassagers = new int[nPersonnes];
	}

	public Passengers(int nSommets) {
		Initialisation(nSommets);
		for (int i = 0; i < nPersonnes; i++) {
			this.nPassagers[i] = 1;
		}
	}

	public Passengers(int nSommets, int range) {
		Initialisation(nSommets);
		Random randomGenerator = new Random();
		for (int i = 0; i < nPersonnes; i++) {
			this.nPassagers[i] = 1 + randomGenerator.nextInt(range);
		}
	}

	// Print the generated number of passengers in the carpooling needed form
	public String toString() {
		String str = "//NUMBER OF PARTICIPANTS ON EACH VERTEX\n";
		str += "nPassagers=[";
		for (int i = 0; i < nPersonnes; i++) {
			str += this.nPassagers[i];
			str += ",";
		}
		for (int i = 0; i < nPersonnes; i++) {
			str += (-1) * this.nPassagers[i];
			if (i != nPersonnes - 1) {
				str += ",";
			}
		}
		str += "];\n";
		return str;
	}

}

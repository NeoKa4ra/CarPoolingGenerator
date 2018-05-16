package Classes;

import java.util.Random;

public class Hours {
	private int[] latestArrivalTime;
	private int nPersonnes;

	private void Initialisation(int nSommets) {
		this.nPersonnes = nSommets;
		this.latestArrivalTime = new int[nPersonnes];
	}

	public Hours(int nSommets) {
		Initialisation(nSommets);
		Random randomGenerator = new Random();
		for (int i = 0; i < nPersonnes; i++) {
			this.latestArrivalTime[i] = 850 + randomGenerator.nextInt(50);
		}
	}

	// Print the generated hours in the carpooling needed form
	public String toString() {
		String str = "//HOURS\n";
		str += "//Hour of arrival at the latest to work\n";
		str += "hDDA=[";
		for (int i = 0; i < nPersonnes; i++) {
			str += this.latestArrivalTime[i];
			if (i != nPersonnes - 1) {
				str += ",";
			}
		}
		str += "];\n";
		str += "//Hour of arrival at the latest to home\n";
		str += "hDOR=[";
		str += "];\n";
		return str;
	}
}

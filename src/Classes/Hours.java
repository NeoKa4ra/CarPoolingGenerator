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
	public void printHours() {
		System.out.print("hDDA=[");
		for (int i = 0; i < nPersonnes; i++) {
				System.out.print(this.latestArrivalTime[i]);
			if (i != nPersonnes - 1) {
				System.out.print(",");
			} 
		}
		System.out.println("];");
		System.out.print("hDOR=[");
		System.out.println("];");
	}


}

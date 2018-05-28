package Classes;

import java.util.Random;

public class Hours {
	private int[] latestArrivalWork;
	private int[] latestArrivalHome;
	Vertices v;

	private void Initialisation() {
		this.latestArrivalWork = new int[this.v.getDestinationW()];
		this.latestArrivalHome = new int[this.v.getDestinationH()];
	}

	public Hours(Vertices vertices) {
		this.v = vertices;
		Initialisation();
		Random randomGenerator = new Random();
		for (int i = 0; i < this.v.getDestinationW(); i++) {
			this.latestArrivalWork[i] = 850 + randomGenerator.nextInt(50);
		}
		for (int i = 0; i < this.v.getDestinationH(); i++) {
			this.latestArrivalHome[i] = 1500 + randomGenerator.nextInt(500);
		}
	}

	// Print the generated hours in the carpooling needed form
	public String toString() {
		String str = "//HOURS\n";
		str += "//Hour of arrival at the latest to work\n";
		str += "B=[";
		for (int i = 0; i < this.v.getDestinationW(); i++) {
			str += this.latestArrivalWork[i];
			if (i != this.v.getDestinationW() - 1) {
				str += ",";
			}
		}
		str += "];\n";
		str += "//Hour of departure at the earliest to home\n";
		str += "E=[";
		for (int i = 0; i < this.v.getDestinationH(); i++) {
			str += this.latestArrivalHome[i];
			if (i != this.v.getDestinationH() - 1) {
				str += ",";
			}
		}
		str += "];\n";
		return str;
	}
}

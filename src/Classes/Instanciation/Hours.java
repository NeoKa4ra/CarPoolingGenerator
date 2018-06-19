package Classes.Instanciation;

import java.util.Random;

import Classes.Constants;

public class Hours {
	private int[] B;
	private int[] E;
	Vertices v;

	private void Initialisation() {
		this.B = new int[this.v.getPersons()];
		this.E = new int[this.v.getPersons()];
	}

	public Hours(Vertices vertices, int hoursMode) {
		this.v = vertices;
		Initialisation();
		Random randomGenerator = new Random();
		switch (hoursMode) {
		// NEARLY MORNING RANDOM EVENING
		case Constants.NMRE:
			for (int i = 0; i < this.v.getPersons(); i++) {
				this.B[i] = 850 + randomGenerator.nextInt(50);
			}
			for (int i = 0; i < this.v.getPersons(); i++) {
				this.E[i] = 1500 + randomGenerator.nextInt(500);
			}
			break;
		// SAME HOURS
		case Constants.SH:
			for (int i = 0; i < this.v.getPersons(); i++) {
				this.B[i] = 900;
			}
			for (int i = 0; i < this.v.getPersons(); i++) {
				this.E[i] = 1700;
			}
			break;
		}
	}

	// Print the generated hours in the carpooling needed form
	public String toString() {
		String str = "//HOURS\n";
		str += "//Hour of arrival at the latest to work\n";
		str += "B=[";
		for (int i = 0; i < this.v.getPersons(); i++) {
			str += this.B[i];
			if (i != this.v.getPersons() - 1) {
				str += ",";
			}
		}
		str += "];\n";
		str += "//Hour of departure at the earliest to home\n";
		str += "E=[";
		for (int i = 0; i < this.v.getPersons(); i++) {
			str += this.E[i];
			if (i != this.v.getPersons() - 1) {
				str += ",";
			}
		}
		str += "];\n";
		return str;
	}

	public int[] getB() {
		return this.B.clone();
	}

	public int[] getE() {
		return this.E.clone();
	}
}

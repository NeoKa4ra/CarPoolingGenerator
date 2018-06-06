package Classes.Instanciation;

import java.util.Random;

public class Hours {
	private int[] B;
	private int[] E;
	Vertices v;

	private void Initialisation() {
		this.B = new int[this.v.getDestinationW()];
		this.E = new int[this.v.getDestinationH()];
	}

	public Hours(Vertices vertices) {
		this.v = vertices;
		Initialisation();
		Random randomGenerator = new Random();
		for (int i = 0; i < this.v.getDestinationW(); i++) {
			this.B[i] = 850 + randomGenerator.nextInt(50);
		}
		for (int i = 0; i < this.v.getDestinationH(); i++) {
			this.E[i] = 1500 + randomGenerator.nextInt(500);
		}
	}

	// Print the generated hours in the carpooling needed form
	public String toString() {
		String str = "//HOURS\n";
		str += "//Hour of arrival at the latest to work\n";
		str += "B=[";
		for (int i = 0; i < this.v.getDestinationW(); i++) {
			str += this.B[i];
			if (i != this.v.getDestinationW() - 1) {
				str += ",";
			}
		}
		str += "];\n";
		str += "//Hour of departure at the earliest to home\n";
		str += "E=[";
		for (int i = 0; i < this.v.getDestinationH(); i++) {
			str += this.E[i];
			if (i != this.v.getDestinationH() - 1) {
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

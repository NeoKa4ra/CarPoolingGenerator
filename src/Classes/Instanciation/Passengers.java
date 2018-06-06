package Classes.Instanciation;

import java.util.Random;

public class Passengers {
	private int[] nPassengers;
	private Vertices v;

	private void Initialisation() {
		this.nPassengers = new int[this.v.getOriginsW() + this.v.getDestinationW()];
	}

	public Passengers(Vertices vertices) {
		this.v = vertices;
		Initialisation();
		for (int i = 0; i < (this.v.getOriginsW() + this.v.getDestinationW()); i++) {
			this.nPassengers[i] = (i<this.v.getOriginsW())?1:-1;
		}
	}

	public Passengers(Vertices vertices, int range) {
		this.v = vertices;
		Initialisation();
		Random randomGenerator = new Random();
		for (int i = 0; i < this.v.getVertices(); i++) {
			this.nPassengers[i] = 1 + randomGenerator.nextInt(range);
		}
		for (int i = 0; i < (this.v.getOriginsW() + this.v.getDestinationW()); i++) {
			this.nPassengers[i] = (i<this.v.getOriginsW())?1 + randomGenerator.nextInt(range):(-1)*this.nPassengers[i-this.v.getOriginsW()];
		}
	}

	// Print the generated number of passengers in the carpooling needed form
	public String toString() {
		String str = "//NUMBER OF PARTICIPANTS ON EACH VERTEX\n";
		str += "nPassagers=[";
		for (int i = 0; i < (this.v.getOriginsW() + this.v.getDestinationW()); i++) {
			str += this.nPassengers[i];
			if (i !=  (this.v.getOriginsW() + this.v.getDestinationW()) - 1) {
				str += ",";
			}
		}
		str += "];\n";
		return str;
	}
	
	public int[] getP() {
		return this.nPassengers.clone();
	}

}

package Classes;

import java.util.Random;

public class Passengers {
	private int[] nPassengersW;
	private int[] nPassengersH;
	private Vertices v;

	private void Initialisation() {
		this.nPassengersW = new int[this.v.getOriginsW() + this.v.getDestinationW()];
		this.nPassengersH = new int[this.v.getOriginsH() + this.v.getDestinationH()];
	}

	public Passengers(Vertices vertices) {
		this.v = vertices;
		Initialisation();
		for (int i = 0; i < (this.v.getOriginsW() + this.v.getDestinationW()); i++) {
			this.nPassengersW[i] = (i<this.v.getOriginsW())?1:-1;
		}
		for (int i = 0; i < (this.v.getOriginsH() + this.v.getDestinationH()); i++) {
			this.nPassengersH[i] = (i<this.v.getOriginsH())?1:-1;
		}

	}

	public Passengers(Vertices vertices, int range) {
		this.v = vertices;
		Initialisation();
		Random randomGenerator = new Random();
		for (int i = 0; i < this.v.getVertices(); i++) {
			this.nPassengersW[i] = 1 + randomGenerator.nextInt(range);
		}
		for (int i = 0; i < (this.v.getOriginsW() + this.v.getDestinationW()); i++) {
			this.nPassengersW[i] = (i<this.v.getOriginsW())?1 + randomGenerator.nextInt(range):(-1)*this.nPassengersW[i-this.v.getOriginsW()];
		}
		for (int i = 0; i < (this.v.getOriginsH() + this.v.getDestinationH()); i++) {
			this.nPassengersH[i] = (i<this.v.getOriginsH())?1 + randomGenerator.nextInt(range):(-1)*this.nPassengersH[i-this.v.getOriginsH()];
		}
	}

	// Print the generated number of passengers in the carpooling needed form
	public String toString() {
		String str = "//NUMBER OF PARTICIPANTS ON EACH VERTEX\n";
		str += "nPassagersA=[";
		for (int i = 0; i < (this.v.getOriginsW() + this.v.getDestinationW()); i++) {
			str += this.nPassengersW[i];
			if (i !=  (this.v.getOriginsW() + this.v.getDestinationW()) - 1) {
				str += ",";
			}
		}
		str += "];\n";
		str += "nPassagersR=[";
		for (int i = 0; i < (this.v.getOriginsH() + this.v.getDestinationH()); i++) {
			str += this.nPassengersH[i];
			if (i !=  (this.v.getOriginsH() + this.v.getDestinationH()) - 1) {
				str += ",";
			}
		}
		str += "];\n";
		return str;
	}

}

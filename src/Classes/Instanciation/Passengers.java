package Classes.Instanciation;

import java.util.Random;

public class Passengers {
	private int[] nPassengers;
	private Vertices v;

	private void Initialisation() {
		this.nPassengers = new int[this.v.getPersons() + this.v.getPersons()];
	}

	public Passengers(Vertices vertices) {
		this.v = vertices;
		Initialisation();
		for (int i = 0; i < (this.v.getPersons() + this.v.getPersons()); i++) {
			this.nPassengers[i] = (i<this.v.getPersons())?1:-1;
		}
	}

	public Passengers(Vertices vertices, int range) {
		this.v = vertices;
		Initialisation();
		Random randomGenerator = new Random();
		for (int i = 0; i < this.v.getPersons(); i++) {
			this.nPassengers[i] = 1 + randomGenerator.nextInt(range);
		}
		for (int i = 0; i < (this.v.getPersons() + this.v.getPersons()); i++) {
			this.nPassengers[i] = (i<this.v.getPersons())?1 + randomGenerator.nextInt(range):(-1)*this.nPassengers[i-this.v.getPersons()];
		}
	}

	// Print the generated number of passengers in the carpooling needed form
	public String toString() {
		String str = "//NUMBER OF PARTICIPANTS ON EACH VERTEX\n";
		str += "nPassagers=[";
		for (int i = 0; i < (this.v.getPersons() + this.v.getPersons()); i++) {
			str += this.nPassengers[i];
			if (i !=  (this.v.getPersons() + this.v.getPersons()) - 1) {
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

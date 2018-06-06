package Classes.Instanciation;

import Classes.Constants;

public class Vertices {
	// ********************* DATAS ********************* //
	int nPersons;
	int mode;
	// ********************* LOGICAL ********************* //
	// TO WORK
	int nOriginsW = 0;
	int nDestinationsW = 0;
	// TO HOME
	int nOriginsH = 0;
	int nDestinationsH = 0;

	int nVertices;

	// ********************* CONSTRUCTOR ********************* //
	public Vertices(int nP, int m) {
		this.nPersons = nP;
		this.mode = m;

		switch (mode) {
		case Constants.W:
			this.nOriginsW = this.nPersons;
			this.nDestinationsW = this.nPersons;
			break;
		case Constants.WH:
			this.nOriginsW = this.nPersons;
			this.nDestinationsW = this.nPersons;
			this.nOriginsH = this.nPersons;
			this.nDestinationsH = this.nPersons;
			break;
		case Constants.WS:
			this.nOriginsW = this.nPersons;
			this.nDestinationsW = this.nPersons;
			break;
		case Constants.WHS:
			this.nOriginsW = this.nPersons;
			this.nDestinationsW = this.nPersons;
			this.nOriginsH = this.nPersons;
			this.nDestinationsH = this.nPersons;
			break;
		default:
		}
		;
		this.nVertices = this.nOriginsW + this.nDestinationsW + this.nOriginsH + this.nDestinationsH;
	}
	
	// Prints the vertices caracteristics
	public String toString() {
		String str = "//NUMBER OF VERTICES\n";
		str +="n=" + this.nOriginsW + "; //Number of vertices origin to work\n";
		return str;
	}
	
	// ********************* GETTERS/SETTERS ********************* //
	public int getPersons() {
		return this.nPersons;
	}
	public int getMode() {
		return this.mode;
	}
	public int getOriginsW() {
		return this.nOriginsW;
	}
	public int getDestinationW() {
		return this.nDestinationsW;
	}
	public int getOriginsH() {
		return this.nOriginsH;
	}
	public int getDestinationH() {
		return this.nDestinationsH;
	}
	public int getVertices() {
		return this.nVertices;
	}
}

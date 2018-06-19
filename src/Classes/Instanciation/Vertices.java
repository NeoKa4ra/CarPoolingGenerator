package Classes.Instanciation;

public class Vertices {
	// ********************* DATAS ********************* //
	private int nPersons;
	private int mode;

	// ********************* CONSTRUCTOR ********************* //
	public Vertices(int nP, int m) {
		this.nPersons = nP;
		this.mode = m;
	}

	// Prints the vertices caracteristics
	public String toString() {
		String str = "//NUMBER OF VERTICES\n";
		str += "n=" + this.nPersons + "; //Number of vertices origin to work\n";
		return str;
	}

	// ********************* GETTERS/SETTERS ********************* //
	public int getPersons() {
		return this.nPersons;
	}

	public int getMode() {
		return this.mode;
	}
}

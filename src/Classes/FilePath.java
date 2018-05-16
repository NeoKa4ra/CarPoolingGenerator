package Classes;

public class FilePath {
	String path = "";

	public FilePath(Vertices v, int matrixMode, int wayMode, int rdmRange) {
		this.path = "P:\\Travaux\\Modelisation\\Tests\\";

		switch (matrixMode) {
		case Constants.RW: // random matrix to go to work
			this.path += "RW";
			break;

		case Constants.RCW: // random matrix with close houses and close works
			this.path += "RCW";
			break;

		case Constants.RSW: // random matrix with the same work
			this.path += "RSW";
			break;

		case Constants.RxSW:
			this.path += "RxSW";
			break;
		}
		switch (wayMode) {
		case Constants.W:
			this.path += "-W";
			break;
		case Constants.WH:
			this.path += "-WH";
			break;
		case Constants.WS:
			this.path += "-WS";
			break;
		case Constants.WHS:
			this.path += "-WHS";
			break;
		}
		this.path += "-";
		this.path += v.getPersons();
		this.path += "--";
		this.path += v.getOriginsW();
		this.path += "-";
		this.path += v.getDestinationW();
		this.path += "-";
		this.path += v.getSatellites();
		this.path += "-";
		this.path += v.getOriginsH();
		this.path += "-";
		this.path += v.getDestinationH();
		this.path += "--";
		this.path += v.getVertices();
		this.path += "--";
		this.path += rdmRange;
	}
	
	public String toString() {
		return this.path;
	}
}

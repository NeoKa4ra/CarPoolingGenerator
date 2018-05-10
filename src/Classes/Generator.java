package Classes;

public class Generator {
	final static int ALLER = 0;
	final static int ALLERRETOUR = 1;
	final static int ALLERSATELLITES = 2;
	final static int ALLERRETOURSATELLITES = 3;

	public Generator() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// ********************* DATAS TO GIVE ********************* //
		// Vertices
		int nPersonnes = 10;
		int mode = ALLER;
		// SATELLITES
		int nSatellites = 0;
		// Range of the randomness of the costs and times matrices
		int rdmRange = 200;

		int passengersRange = 2;

		// ********************* VARIABLES ********************* //
		// ALLER
		int nOriginesAller = 0;
		int nDestinationsAller = 0;

		// RETOUR
		int nOriginesRetour = 0;
		int nDestinationsRetour = 0;

		switch (mode) {
		case ALLER:
			nOriginesAller = nPersonnes;
			nDestinationsAller = nPersonnes;
			nSatellites = 0;
			break;
		case ALLERRETOUR:
			nOriginesAller = nPersonnes;
			nDestinationsAller = nPersonnes;
			nOriginesRetour = nPersonnes;
			nDestinationsRetour = nPersonnes;
			nSatellites = 0;
			break;
		case ALLERSATELLITES:
			nOriginesAller = nPersonnes;
			nDestinationsAller = nPersonnes;
			break;
		case ALLERRETOURSATELLITES:
			nOriginesAller = nPersonnes;
			nDestinationsAller = nPersonnes;
			nOriginesRetour = nPersonnes;
			nDestinationsRetour = nPersonnes;
			break;
		default:
		}
		;
		int nSommets = nOriginesAller + nDestinationsAller + nSatellites + nOriginesRetour + nDestinationsRetour;

		// ********************* GENERATIONS AND PRINTS ********************* //
		//
		System.out.println("nSOA=" + nOriginesAller + ";");
		System.out.println("nSDA=" + nDestinationsAller + ";");
		System.out.println("nSS=" + nSatellites + ";");
		System.out.println("nSOR=" + nOriginesRetour + ";");
		System.out.println("nSDR=" + nDestinationsRetour + ";");

		//
		Passengers p = new Passengers(nOriginesAller);
		p.printPassengers();

		// Generation of the cost matrix
		CostMatrices cM = new CostMatrices(nSommets, rdmRange);
		cM.printMatrix();

		// Generation of the time matrix
		TimeMatrices tM = new TimeMatrices(cM, 20);
		tM.printMatrix();

		// Generation of the hours
		Hours h = new Hours(nOriginesAller);
		h.printHours();

		// Generation of the drivers capacity and maximal travel time
		Drivers d = new Drivers(nOriginesAller, tM);
		d.printDrivers();
	}

}

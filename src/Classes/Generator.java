package Classes;

import java.io.File;
import java.io.FileWriter;

public class Generator {
	public static void main(String[] args) {
		// ********************* DATAS TO GIVE ********************* //
		// Vertices
		int nPersonnes = 25;
		int wayMode = Constants.W;
		int matrixMode = Constants.RCW;
		// SATELLITES
		int nSatellites = 0;
		// Range of the randomness of the costs and times matrices
		int rdmRange = 200;

		// int passengersRange = 2;

		// ********************* GENERATIONS AND PRINTS ********************* //
		// Generation of the characteristics of the scenario
		Vertices v = new Vertices(nPersonnes, wayMode, nSatellites);
		System.out.println(v);

		// Generation of the number of passengers
		Passengers p = new Passengers(v.getOriginsW());
		System.out.println(p);

		// Generation of the cost matrix
		CostMatrices cM = new CostMatrices(v, matrixMode, rdmRange);
		System.out.println(cM);

		// Generation of the time matrix
		TimeMatrices tM = new TimeMatrices(cM, 20);
		System.out.println(tM);

		// Generation of the hours
		Hours h = new Hours(v.getOriginsW());
		System.out.println(h);

		// Generation of the drivers capacity and maximal travel time
		Drivers d = new Drivers(v.getOriginsW(), tM);
		System.out.println(d);

		FilePath FP = new FilePath(v, matrixMode, wayMode, rdmRange);
		String path = FP.toString();
		File f = new File(path+".txt");
		int i=1;
		while(f.exists()) {
			f = new File(path+" ("+(i++)+")"+".txt");
		}
		try {
			// Creation of the file
			f.createNewFile();
			// creation of the writer
			final FileWriter writer = new FileWriter(f);
			try {
				writer.write(v+"\n"+p+"\n"+cM+"\n"+tM+"\n"+h+"\n"+d);
			} finally {
				writer.close();
			}
		} catch (Exception e) {
			System.out.println("File creation impossible");
		}
	}

}

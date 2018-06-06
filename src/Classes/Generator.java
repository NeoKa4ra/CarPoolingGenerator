package Classes;

import ilog.concert.*;
import ilog.cplex.*;

import java.io.File;
import java.io.FileWriter;

public class Generator {
	public static void main(String[] args) {
		// ********************* DATAS TO GIVE ********************* //
		// Vertices
		int n = 7;
		// Way W : Going to work; WS : with satellites; WH : Going to work and to home;
		// WHS : with satellites
		int wayMode = Constants.WH;
		// RW : Random matrix to work; RCW : with close houses and close works; RSW :
		// only one work; RxSW : with 1 to x works
		int matrixMode = Constants.RCW;
		// Range of the randomness of the costs and times matrices
		int rdmRange = 200;

		// int passengersRange = 2;

		// ********************* GENERATIONS AND PRINTS ********************* //
		// Generation of the characteristics of the scenario
		Vertices vertices = new Vertices(n, wayMode);
		System.out.println(vertices);

		// Generation of the cost matrix
		CostMatrices C = new CostMatrices(vertices, matrixMode, rdmRange);
		System.out.println(C);

		// Generation of the hours
		Hours hours = new Hours(vertices);
		System.out.println(hours);

		// Generation of the drivers capacity and maximal travel time
		Drivers drivers = new Drivers(C);
		System.out.println(drivers);

		Passengers passengers = new Passengers(vertices);
		System.out.println(passengers);

		FilePath FP = new FilePath(vertices, matrixMode, wayMode, rdmRange);
		String path = FP.toString();
		File f = new File(path + ".txt");
		int numFichier = 1;
		while (f.exists()) {
			f = new File(path + " (" + (numFichier++) + ")" + ".txt");
		}
		try {
			// Creation of the file
			f.createNewFile();
			// creation of the writer
			final FileWriter writer = new FileWriter(f);
			try {
				writer.write(vertices + "\n" + C + "\n" + hours + "\n" + drivers + "\n" + passengers);
			} finally {
				writer.close();
			}
		} catch (Exception e) {
			System.out.println("File creation impossible");
		}

		//Linear l = new Linear(n, drivers, C, hours);
	}
}

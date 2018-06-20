package Classes;

import java.awt.Point;
import java.util.LinkedList;

import Classes.Instanciation.HoursSettings;
import Classes.Instanciation.MatriceSettings;
import Classes.LinearPrograms.LPSettings;
import Classes.LinearPrograms.LPVariationsSettings;

public class Main {

	public static void main(String[] args) {
		// Usage : GlobalSettings(nRuns, execTimeMax, minutesByExecutionMax, fileSuffix)
		GlobalSettings GS = null;

		// RW : Random matrix; RCW : close together; RSW : RxSW :
		MatriceSettings MS = null;
		HoursSettings HS = null;

		// Usage : LPSettings(advance, waitingTime, deviationPercentage, deviationValue)
		LPSettings LPS = null;

		// Usage : LPVariationsSettings(varyNUsers, varyAdvance, varyWaitingTime,
		// varyDeviationPercentage, varyDeviationValue)
		LPVariationsSettings LPVS = null;

		// Matrix settings
		int choice = 0;
		LinkedList<Point> cities = new LinkedList<Point>();
		LinkedList<Point> workplaces = new LinkedList<Point>();
		workplaces.add(new Point(0, 0)); // GRENOBLE
		switch (choice) {
		case 0:
			cities.add(new Point(-3, 5)); // VOIRON
			cities.add(new Point(-1, -4)); // VIF
			cities.add(new Point(4, 4)); // CROLLES
			break;
		case 1:
			cities.add(new Point(-3, 5)); // VOIRON
			cities.add(new Point(-5, 1)); // VINAY
			cities.add(new Point(-2, 7)); // ST LAURENT DU PONT
			break;
		case 2:
			cities.add(new Point(10, 10)); // PONTCHARRA
			cities.add(new Point(7, 7)); // LE TOUVET
			cities.add(new Point(4, 4)); // CROLLES
			break;
		case 3:
			cities.add(new Point(10, 10)); // VIZILLE
			cities.add(new Point(7, 7)); // PONT DE CLAIX
			cities.add(new Point(4, 4)); // VIF
			break;
		}

		// SINGLE RUN
		int nUsers = 15;
		GS = new GlobalSettings(Constants.SINGLERUN, 1, 300, 6, "SingleRun");
		MS = new MatriceSettings(Constants.MCPWP, nUsers, 200, cities, workplaces, 20, 5);
		HS = new HoursSettings(Constants.HW, nUsers);
		LPS = new LPSettings(50, 25, 20, 5);
		LPVS = new LPVariationsSettings(0, 0, 0, 0, 0);
		Generator g = new Generator(GS, MS, HS, LPS, LPVS);
		new GraphPrinter(g.getInstance().getCostMatrices(), g.getResults());

		if (false) {
			// USERS TEST
			GS = new GlobalSettings(Constants.USERS, 1, 600, 60, "VarNUsers");
			LPS = new LPSettings(50, 25, 20, 10);
			LPVS = new LPVariationsSettings(1, 0, 0, 0, 0);
			new Generator(GS, MS, HS, LPS, LPVS);

			// USERS TEST
			GS = new GlobalSettings(Constants.LP, 1, 600, 60, "VarNUsers");
			LPS = new LPSettings(50, 25, 20, 10);
			LPVS = new LPVariationsSettings(0, 1, 0, 0, 0);
			new Generator(GS, MS, HS, LPS, LPVS);

			// Print latex data
			// P1: NBUSERS P2&P3: WAITING TIME P4: PERCENTAGE P5: DEVIATION VALUE
			int nbFiles = 7;
			String xAxis = "P2";
			String filePath = "data/Performances/RandomCloseHouses/VariationNbUsers/resRCW-WH-23-200-VarNUsers";

			new LatexDataPrinter(nbFiles, xAxis, filePath);
		}
	}
}

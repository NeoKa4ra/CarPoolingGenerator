package Classes;

import java.awt.Point;
import java.util.LinkedList;

import Classes.Instanciation.HoursSettings;
import Classes.Instanciation.MatriceSettings;
import Classes.LinearPrograms.LPSettings;
import Classes.LinearPrograms.LPVariationsSettings;

public class Main {

	public static void main(String[] args) {
		GlobalSettings GS = null;
		MatriceSettings MS = null;
		HoursSettings HS = null;
		LPSettings LPS = null;
		LPVariationsSettings LPVS = null;
		Generator g = null;

		// Matrix settings
		int choice = 0;

		int compteurY = 0;
		int compteurNpersons = 0;

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
			cities.add(new Point(1, -5)); // VIZILLE
			cities.add(new Point(-1, -3)); // PONT DE CLAIX
			cities.add(new Point(-2, -6)); // VIF
			break;
		}

		int nUsers = 5;
		// Usage:(modeLP,modeInstance,numberofRun,execInstanceTimeMax,execTotalTimeMax,nMaxUsers,suffix)
		GS = new GlobalSettings(Constants.GLPWR, Constants.GDI, 30, 300, 6, 10, "samePool");
		// (matrixMode,nbPersons,matrixRange,citiesList,workplacesList,probScdWork,probScdHome)
		MS = new MatriceSettings(Constants.MCPWP, nUsers, 200, cities, workplaces, 20, 5);
		// Usage:(nPersons,morningHour,morningHourRange,eveningHour,eveningHourRange)
		HS = new HoursSettings(nUsers, 800, 100, 1500, 100);
		// Usage:LPSettings(advance, waitingTime, deviationPercentage, deviationValue)
		LPS = new LPSettings(50, 25, 20, 5);
		// (varyNUsers,varyAdvance,varyWaitingTime,varyDeviationPercentage,varyDeviationValue)
		LPVS = new LPVariationsSettings(0, 0, 0, 0, 0);
		g = new Generator(GS, MS, HS, LPS, LPVS);
		//new GraphPrinter(g.getInstance().getCostMatrices(), g.getResults());
		//new GraphPrinterReturn(g.getInstance().getCostMatrices(), g.getResults());

		// Usage:(modeLP,modeInstance,numberofRun,execInstanceTimeMax,execTotalTimeMax,nMaxUsers,suffix)
		//GS = new GlobalSettings(Constants.GLPOW, Constants.GDI, 30, 300, 6, 13, "varyUsersOW");
		// (matrixMode,nbPersons,matrixRange,citiesList,workplacesList,probScdWork,probScdHome)
		//MS = new MatriceSettings(Constants.MCPWP, nUsers, 200, cities, workplaces, 20, 5);
		// Usage:(nPersons,morningHour,morningHourRange,eveningHour,eveningHourRange)
		//HS = new HoursSettings(nUsers, 900, 100, 1500, 100);
		// Usage:LPSettings(advance, waitingTime, deviationPercentage, deviationValue)
		//LPS = new LPSettings(50, 25, 20, 5);
		// (varyNUsers,varyAdvance,varyWaitingTime,varyDeviationPercentage,varyDeviationValue)
		//LPVS = new LPVariationsSettings(1, 0, 0, 0, 0);
		// g = new Generator(GS, MS, HS, LPS, LPVS);
		// new GraphPrinter(g.getInstance().getCostMatrices(), g.getResults());

		// Print latex data
		// P1: NBUSERS P2&P3: WAITING TIME P4: PERCENTAGE P5: DEVIATION VALUE
		int nbFiles = 28;
		String xAxis = "P1";
		String filePath = "data/newcouple/resMCPWP-12-200-varyUsersOW";

		//new LatexDataPrinter(nbFiles, xAxis, filePath);
	}
}

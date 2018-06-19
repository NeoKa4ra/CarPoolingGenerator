package Classes;

import Classes.Instanciation.InstanceSettings;
import Classes.LinearPrograms.LPSettings;
import Classes.LinearPrograms.LPVariationsSettings;

public class Main {

	public static void main(String[] args) {
		// Usage : GlobalSettings(nRuns, execTimeMax, minutesByExecutionMax, fileSuffix)
		GlobalSettings GS = null;

		// Usage : InstanceSettings(nUsers, wayMode, matrixMode, rdmRange)
		InstanceSettings IS = null;

		// Usage : LPSettings(advance, waitingTime, deviationPercentage, deviationValue)
		LPSettings LPS = null;

		// Usage : LPVariationsSettings(varyNUsers, varyAdvance, varyWaitingTime,
		// varyDeviationPercentage, varyDeviationValue)
		LPVariationsSettings LPVS = null;

		boolean print = true;

		int nRuns = 1;
		int execInstanceTimeMax = 300;
		int execTotalTimeMax = 6;
		int nUsers = 20;
		int hoursMode = Constants.NMRE;
		int rdmRange = 200;
		// Matrix settings
		// RW : Random matrix; RCW : close together; RSW : RxSW :
		int matrixMode = Constants.RCPCSW;
		int nbCities = 2;
		int nbWorks = 2;
		int[] matrixModeArgs = { matrixMode, nbCities, nbWorks};

		// Others
/*
		// SINGLE RUN
		GS = new GlobalSettings(nRuns, execInstanceTimeMax, execTotalTimeMax, "SingleRun");
		IS = new InstanceSettings(nUsers, matrixModeArgs, hoursMode, rdmRange);
		LPS = new LPSettings(50, 25, 20, 5);
		LPVS = new LPVariationsSettings(0, 0, 0, 0, 0);
		Generator g = new Generator(GS, IS, LPS, LPVS, Constants.SINGLERUN);
		new GraphPrinter(g.getInstance().getCostMatrices(), g.getResults());
*/
		nRuns = 20;
		execInstanceTimeMax = 300;
		execTotalTimeMax = 6;
		nUsers = 19;
		// RW : Random matrix; RCW : close together; RSW : RxSW :
		matrixMode = Constants.RCW;
		matrixModeArgs[0] = matrixMode;
		rdmRange = 200;

		if (!print) {

			// LP TEST
			GS = new GlobalSettings(nRuns, execInstanceTimeMax, execTotalTimeMax, "VarPerDev");
			IS = new InstanceSettings(nUsers, matrixModeArgs, hoursMode, rdmRange);
			LPS = new LPSettings(50, 25, 0, 0);
			LPVS = new LPVariationsSettings(0, 0, 0, 1, 0);
			new Generator(GS, IS, LPS, LPVS, Constants.LP);

			// LP TEST
			GS = new GlobalSettings(nRuns, execInstanceTimeMax, execTotalTimeMax, "VarDevVal");
			IS = new InstanceSettings(nUsers, matrixModeArgs, hoursMode, rdmRange);
			LPS = new LPSettings(50, 25, 0, 0);
			LPVS = new LPVariationsSettings(0, 0, 0, 0, 1);
			new Generator(GS, IS, LPS, LPVS, Constants.LP);

			// LP TEST
			GS = new GlobalSettings(nRuns, execInstanceTimeMax, execTotalTimeMax, "VarWT");
			IS = new InstanceSettings(nUsers, matrixModeArgs, hoursMode, rdmRange);
			LPS = new LPSettings(0, 0, 20, 10);
			LPVS = new LPVariationsSettings(0, 1, 1, 0, 0);
			new Generator(GS, IS, LPS, LPVS, Constants.LP);

			matrixMode = Constants.RPC;
			matrixModeArgs[0] = matrixMode;
			// LP TEST
			GS = new GlobalSettings(nRuns, execInstanceTimeMax, execTotalTimeMax, "VarPerDev");
			IS = new InstanceSettings(nUsers, matrixModeArgs, hoursMode, rdmRange);
			LPS = new LPSettings(50, 25, 0, 0);
			LPVS = new LPVariationsSettings(0, 0, 0, 1, 1);
			new Generator(GS, IS, LPS, LPVS, Constants.LP);

			// LP TEST
			GS = new GlobalSettings(nRuns, execInstanceTimeMax, execTotalTimeMax, "VarDevVal");
			IS = new InstanceSettings(nUsers, matrixModeArgs, hoursMode, rdmRange);
			LPS = new LPSettings(50, 25, 0, 0);
			LPVS = new LPVariationsSettings(0, 0, 0, 0, 1);
			new Generator(GS, IS, LPS, LPVS, Constants.LP);

			// LP TEST
			GS = new GlobalSettings(nRuns, execInstanceTimeMax, execTotalTimeMax, "VarWT");
			IS = new InstanceSettings(nUsers, matrixModeArgs, hoursMode, rdmRange);
			LPS = new LPSettings(0, 0, 20, 10);
			LPVS = new LPVariationsSettings(0, 1, 1, 0, 0);
			new Generator(GS, IS, LPS, LPVS, Constants.LP);

			matrixMode = Constants.RW;
			matrixModeArgs[0] = matrixMode;

			// USERS TEST
			GS = new GlobalSettings(nRuns, 600, 60, "VarNUsers");
			IS = new InstanceSettings(5, matrixModeArgs, hoursMode, rdmRange);
			LPS = new LPSettings(50, 25, 20, 10);
			LPVS = new LPVariationsSettings(1, 0, 0, 0, 0);
			new Generator(GS, IS, LPS, LPVS, Constants.USERS);

			matrixMode = Constants.RCW;
			matrixModeArgs[0] = matrixMode;

			// USERS TEST
			GS = new GlobalSettings(nRuns, 600, 60, "VarNUsers");
			IS = new InstanceSettings(5, matrixModeArgs, hoursMode, rdmRange);
			LPS = new LPSettings(50, 25, 20, 10);
			LPVS = new LPVariationsSettings(1, 0, 0, 0, 0);
			new Generator(GS, IS, LPS, LPVS, Constants.USERS);

			matrixMode = Constants.RPC;
			matrixModeArgs[0] = matrixMode;

			// USERS TEST
			GS = new GlobalSettings(nRuns, 600, 60, "VarNUsers");
			IS = new InstanceSettings(5, matrixModeArgs, hoursMode, rdmRange);
			LPS = new LPSettings(50, 25, 20, 10);
			LPVS = new LPVariationsSettings(1, 0, 0, 0, 0);
			new Generator(GS, IS, LPS, LPVS, Constants.USERS);
		} else {
			// Print latex data
			// P1: NBUSERS P2&P3: WAITING TIME P4: PERCENTAGE P5: DEVIATION VALUE
			int nbFiles = 7;
			String xAxis = "P2";
			String filePath = "data/Performances/RandomCloseHouses/VariationNbUsers/resRCW-WH-23-200-VarNUsers";

			new PrintLatexData(nbFiles, xAxis, filePath);
		}
	}
}

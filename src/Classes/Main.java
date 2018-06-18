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

		boolean print = false;

		int nRuns = 20;
		int execInstanceTimeMax = 300;
		int execTotalTimeMax = 6;
		int nUsers = 19;
		// Way W : Going; WS : with satellites; WH : With return; WHS : with satellites
		// RW : Random matrix; RCW : close together; RSW : RxSW :
		int wayMode = Constants.WH;
		int matrixMode = Constants.RCW;
		int rdmRange = 200;

		// LP TEST
		GS = new GlobalSettings(nRuns, execInstanceTimeMax, execTotalTimeMax, "VarPerDev");
		IS = new InstanceSettings(nUsers, wayMode, Constants.RPC, rdmRange);
		LPS = new LPSettings(50, 25, 0, 0);
		LPVS = new LPVariationsSettings(0, 0, 0, 1, 1);
		new Generator(GS, IS, LPS, LPVS, Constants.LP);

		if (!print) {
			// USERS TEST
			GS = new GlobalSettings(nRuns, 600, 60, "VarNUsers");
			IS = new InstanceSettings(5, wayMode, Constants.RW, rdmRange);
			LPS = new LPSettings(50, 25, 20, 10);
			LPVS = new LPVariationsSettings(1, 0, 0, 0, 0);
			new Generator(GS, IS, LPS, LPVS, Constants.USERS);

			// LP TEST
			GS = new GlobalSettings(nRuns, execInstanceTimeMax, execTotalTimeMax, "VarPerDev");
			IS = new InstanceSettings(nUsers, wayMode, matrixMode, rdmRange);
			LPS = new LPSettings(50, 25, 0, 0);
			LPVS = new LPVariationsSettings(0, 0, 0, 1, 0);
			new Generator(GS, IS, LPS, LPVS, Constants.LP);

			// LP TEST
			GS = new GlobalSettings(nRuns, execInstanceTimeMax, execTotalTimeMax, "VarDevVal");
			IS = new InstanceSettings(nUsers, wayMode, matrixMode, rdmRange);
			LPS = new LPSettings(50, 25, 0, 0);
			LPVS = new LPVariationsSettings(0, 0, 0, 0, 1);
			new Generator(GS, IS, LPS, LPVS, Constants.LP);

			// LP TEST
			GS = new GlobalSettings(nRuns, execInstanceTimeMax, execTotalTimeMax, "VarWT");
			IS = new InstanceSettings(nUsers, wayMode, matrixMode, rdmRange);
			LPS = new LPSettings(0, 0, 20, 10);
			LPVS = new LPVariationsSettings(0, 1, 1, 0, 0);
			new Generator(GS, IS, LPS, LPVS, Constants.LP);

			// USERS TEST
			GS = new GlobalSettings(nRuns, 600, 60, "VarNUsers");
			IS = new InstanceSettings(5, wayMode, matrixMode, rdmRange);
			LPS = new LPSettings(50, 25, 20, 10);
			LPVS = new LPVariationsSettings(1, 0, 0, 0, 0);
			new Generator(GS, IS, LPS, LPVS, Constants.USERS);
		} else {
			// Print latex data
			// P1: NBUSERS P2&P3: WAITING TIME P4: PERCENTAGE P5: DEVIATION VALUE
			int nbFiles = 10;
			String xAxis = "P5";
			String filePath = "data/Performances/RandomCloseHouses/VariationDeviationValue/10/resRCW-WH-10-40-200-VarDevVal10";

			new PrintLatexData(nbFiles, xAxis, filePath);
		}
	}
}

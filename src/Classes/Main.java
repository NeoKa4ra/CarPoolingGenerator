package Classes;

import Classes.Instanciation.InstanceSettings;
import Classes.LinearPrograms.LPSettings;
import Classes.LinearPrograms.LPVariationsSettings;

public class Main {

	public static void main(String[] args) {
		// Usage : GlobalSettings(nRuns, execTimeMax, minutesByExecutionMax, fileSuffix)
		GlobalSettings GS = null;

		// Way W : Going; WS : with satellites; WH : With return; WHS : with satellites
		// RW : Random matrix; RCW : close together; RSW : RxSW :
		// Usage : InstanceSettings(nUsers, wayMode, matrixMode, rdmRange)
		InstanceSettings IS = null;

		// Usage : LPSettings(advance, waitingTime, deviationPercentage, deviationValue)
		LPSettings LPS = null;

		// Usage : LPVariationsSettings(varyNUsers, varyAdvance, varyWaitingTime,
		// varyDeviationPercentage, varyDeviationValue)
		LPVariationsSettings LPVS = null;

		int nRuns = 10;
		int execTimeMax = 300;
		int nUsers = 10;
		int wayMode = Constants.WH;
		int matrixMode = Constants.RCW;
		int rdmRange = 200;

		// TEST
		GS = new GlobalSettings(nRuns, execTimeMax, 6, "VarPerDev10");
		IS = new InstanceSettings(nUsers, wayMode, matrixMode, rdmRange);
		LPS = new LPSettings(50, 25, 0, 0);
		LPVS = new LPVariationsSettings(0, 0, 0, 1, 0);
		new Generator(GS, IS, LPS, LPVS);

		// TEST
		GS = new GlobalSettings(nRuns, execTimeMax, 6, "VarDevVal10");
		IS = new InstanceSettings(nUsers, wayMode, matrixMode, rdmRange);
		LPS = new LPSettings(50, 25, 0, 0);
		LPVS = new LPVariationsSettings(0, 0, 0, 0, 1);
		new Generator(GS, IS, LPS, LPVS);

		// TEST
		GS = new GlobalSettings(nRuns, execTimeMax, 6, "VarWT10");
		IS = new InstanceSettings(nUsers, wayMode, matrixMode, rdmRange);
		LPS = new LPSettings(0, 0, 20, 10);
		LPVS = new LPVariationsSettings(0, 1, 1, 0, 0);
		new Generator(GS, IS, LPS, LPVS);
		
		// OTHERRRRRRRRRRRRRRRRR
		nUsers = 15;

		// TEST
		GS = new GlobalSettings(nRuns, execTimeMax, 6, "VarPerDev15");
		IS = new InstanceSettings(nUsers, wayMode, matrixMode, rdmRange);
		LPS = new LPSettings(50, 25, 0, 0);
		LPVS = new LPVariationsSettings(0, 0, 0, 1, 0);
		new Generator(GS, IS, LPS, LPVS);

		// TEST
		GS = new GlobalSettings(nRuns, execTimeMax, 6, "VarDevVal15");
		IS = new InstanceSettings(nUsers, wayMode, matrixMode, rdmRange);
		LPS = new LPSettings(50, 25, 0, 0);
		LPVS = new LPVariationsSettings(0, 0, 0, 0, 1);
		new Generator(GS, IS, LPS, LPVS);

		// TEST
		GS = new GlobalSettings(nRuns, execTimeMax, 6, "VarWT15");
		IS = new InstanceSettings(nUsers, wayMode, matrixMode, rdmRange);
		LPS = new LPSettings(0, 0, 20, 10);
		LPVS = new LPVariationsSettings(0, 1, 1, 0, 0);
		new Generator(GS, IS, LPS, LPVS);
		
		// OTHERRRRRRRRRRRRRRRRR

		// TEST
		GS = new GlobalSettings(nRuns, 3600, 60, "VarNUsers");
		IS = new InstanceSettings(5, wayMode, matrixMode, rdmRange);
		LPS = new LPSettings(50, 25, 20, 10);
		LPVS = new LPVariationsSettings(1, 0, 0, 0, 0);
		new Generator(GS, IS, LPS, LPVS);

	}
}

package Classes;

import java.util.ArrayList;

import Classes.FileManagement.FileSettings;
import Classes.FileManagement.RESFile;
import Classes.FileManagement.TESTFile;
import Classes.Instanciation.Instance;
import Classes.LinearPrograms.LPResults;
import Classes.LinearPrograms.LPSettings;
import Classes.LinearPrograms.LPWithReturn;

public class Generator {
	public static void main(String[] args) {
		// *********** DATA GENERATION VARIABLES ********** //
		// Way W : Going; WS : with satellites; WH : With return; WHS : with satellites
		int wayMode = Constants.WH;
		// RW : Random matrix; RCW : close together; RSW : RxSW :
		int matrixMode = Constants.RCW;
		// Range of the randomness of the costs and times matrices
		int rdmRange = 200;

		// *********** LINEAR PROGRAM SETTINGS ********** //
		// Waiting times permitted to go (home-to-work trip) and to return
		int WaitingTimeG = 0;
		int WaitingTimeR = 0;
		// Percentage of the travel duration deviation allowed and constant
		int percentageTravelTime = 20;
		int constantDeviation = 20;

		// *********** GLOBAL SETTINGS ********** //
		// Number of vertices
		int n = 10;
		// Number of tests
		int nTests = 10;
		// Number of result variables wanted
		int nVarRes = 2;
		// Suffix of the res file
		String suffix = "WTG";
		// Number of variables varying
		int nvaryingValues = 2;
		ArrayList varyingValues = new ArrayList();

		// ****************** OBJECTS CREATION ****************** //
		Instance instance = null;
		LPSettings var = null;
		LPWithReturn LP = null;
		ArrayList res = new ArrayList();
		FileSettings FS = null;

		// ********* CONSTANT INSTANCIATIONS ********* //
		instance = new Instance(n, wayMode, matrixMode, rdmRange);
		FS = new FileSettings(n, matrixMode, wayMode, rdmRange, Constants.TEST, suffix);
		TESTFile f = new TESTFile(instance, FS);

		// **************** EXPERIMENTATIONS **************** //
		for (int i = 0; i < nTests; i++) {
			var = new LPSettings(WaitingTimeG, WaitingTimeR, percentageTravelTime, constantDeviation);
			LP = new LPWithReturn(n, instance, var);
			res.add( LP.getRes());
			varyingValues = new int[nvaryingValues];
			varyingValues[0] = Integer.valueOf(WaitingTimeG);
			varyingValues[1] = Integer.valueOf(WaitingTimeR);
			res[i].setVaryingValue(varyingValues);

			System.out.println((i + 1) + ": " + "O : " + res[i].getObjective() + " ET : " + res[i].getExecTime());

			// **************** EXPERIMENTATIONS SETTINGS ****************** //
			WaitingTimeR += 1;
			WaitingTimeG += 1;
		}
		FS = new FileSettings(n, matrixMode, wayMode, rdmRange, Constants.RES, suffix);
		RESFile rf = new RESFile(nTests, nVarRes, res, FS);
	}
}

package Classes;

import java.util.LinkedList;

import Classes.FileManagement.FileSettings;
import Classes.FileManagement.RESFile;
import Classes.FileManagement.TESTFile;
import Classes.Instanciation.Instance;
import Classes.LinearPrograms.LPSettings;
import Classes.LinearPrograms.LPWithReturn;

public class Generator {
	// ****************** OBJECTS CREATION ****************** //
	// Number of variables varying
	public static LinkedList<LinkedList<Integer>> varyingValues = new LinkedList<LinkedList<Integer>>();
	public static LinkedList<Integer> tempVaryingValues = null;

	// Get every results
	public static LinkedList<LinkedList<Double>> everyResults = new LinkedList<LinkedList<Double>>();
	public static LinkedList<Double> tempEveryResults = null;
	public static Instance instance = null;
	public static LPSettings var = null;
	public static LPWithReturn LP = null;
	public static FileSettings FS = null;

	public static void main(String[] args) {
		// *********** GLOBAL SETTINGS ********** //
		// Number of vertices
		int n = 10;
		// Number of tests
		int nTests = 10;
		// Suffix of the res file
		String suffix = "WTG";

		// *********** DATA GENERATION SETTINGS ********** //
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

		// ********* CONSTANT INSTANCIATIONS ********* //
		instance = new Instance(n, wayMode, matrixMode, rdmRange);
		FS = new FileSettings(n, matrixMode, wayMode, rdmRange, Constants.TEST, suffix);
		TESTFile TF = new TESTFile(instance, FS);

		// **************** EXPERIMENTATIONS **************** //
		for (int i = 0; i < nTests; i++) {
			tempVaryingValues = new LinkedList<Integer>();
			tempEveryResults = new LinkedList<Double>();
			// To execute the LP
			var = new LPSettings(WaitingTimeG, WaitingTimeR, percentageTravelTime, constantDeviation);
			LP = new LPWithReturn(n, instance, var);
			// Add here every varying value you want
			tempVaryingValues.add(Integer.valueOf(WaitingTimeG));
			tempVaryingValues.add(Integer.valueOf(WaitingTimeR));
			// Add here every results you want
			tempEveryResults.add(LP.getRes().getObjective());
			tempEveryResults.add(LP.getRes().getExecTime());
			// Save the current values
			varyingValues.add(tempVaryingValues);
			everyResults.add(tempEveryResults);

			System.out.println(
					(i + 1) + ": " + "O : " + LP.getRes().getObjective() + " ET : " + LP.getRes().getExecTime());

			// **************** EXPERIMENTATIONS SETTINGS ****************** //
			WaitingTimeR += 1;
			WaitingTimeG += 1;
		}
		FS = new FileSettings(n, matrixMode, wayMode, rdmRange, Constants.RES, suffix);
		RESFile RF = new RESFile(everyResults, varyingValues, FS);
	}
}

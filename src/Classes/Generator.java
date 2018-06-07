package Classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Classes.FileManagement.FilePath;
import Classes.FileManagement.TestFile;
import Classes.Instanciation.Instance;
import Classes.LinearPrograms.LPResults;
import Classes.LinearPrograms.LPSettings;
import Classes.LinearPrograms.LPWithReturn;

public class Generator {
	public static void main(String[] args) {
		// ******************************************************* //
		// ********************* SETTINGS ********************* //
		// ******************************************************* //

		// *********** DATA GENERATION VARIABLES ********** //
		// Way W : Going to work; WS : with satellites; WH : Going to work and to home;
		// WHS : with satellites
		int wayMode = Constants.WH;
		// RW : Random matrix to work; RCW : with close houses and close works; RSW :
		// only one work; RxSW : with 1 to x works
		int matrixMode = Constants.RCW;
		// Range of the randomness of the costs and times matrices
		int rdmRange = 200;

		// *********** LINEAR PROGRAM VARIABLES ********** //
		// Waiting times permitted to go (home-to-work trip) and to return
		int WaitingTimeG = 40;
		int WaitingTimeR = 20;
		// Percentage of the travel duration deviation allowed and constant
		int percentageTravelTime = 20;
		int constantDeviation = 20;

		// *********** PRINT AND WRITE SETTINGS ********** //
		// Number of vertices
		int n = 7;
		// Number of tests
		int nTests = 4;
		// Each execution write the test file
		boolean write = false;
		// Shows everything
		boolean showALL = false;
		// Shows instance
		boolean showInstance = false;
		// Shows the linear program variables
		boolean showLPVariables = false;
		// Shows the file path
		boolean showFP = false;
		// Shows the results of the Linear Program
		boolean showRes = false;

		// ******************************************************* //
		// ****************** GENERATOR OF INSTANCES ****************** //
		// ******************************************************* //
		FilePath FILENAME = new FilePath(n, matrixMode, wayMode, rdmRange, Constants.RES);
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			
			fw = new FileWriter(FILENAME.toString());
			bw = new BufferedWriter(fw);
			
			for (int i = 0; i < nTests; i++) {
				// Creation of an instance
				Instance instance = new Instance(n, wayMode, matrixMode, rdmRange);
				if (showInstance || showALL) {
					System.out.println(i);
				}

				// Creation of test files
				if (write) {
					TestFile f = new TestFile(instance, matrixMode, wayMode, rdmRange);
					if (showFP || showALL) {
						System.out.println(f);
					}
				}

				// ******************************************************* //
				// ********************* LINEAR PROGRAMS ********************* //
				// ******************************************************* //
				// Set the variables for the LP
				LPSettings var = new LPSettings(WaitingTimeG, WaitingTimeR, percentageTravelTime, constantDeviation);
				if (showLPVariables || showALL) {
					System.out.println(var);
				}

				// Launches the LP
				LPWithReturn LP = new LPWithReturn(n, instance, var);
				LPResults res = LP.getRes();
				if (showRes || showALL) {
					System.out.println(res);
				}
				
				bw.write(String.valueOf(res.getObjective()) + " ");
				System.out.println("Objective : " + res.getObjective());
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (bw != null)
					bw.close();
				
				if (fw != null)
					fw.close();
				
			} catch (IOException ex) {
				ex.printStackTrace();
				
			}
		}
	}
}

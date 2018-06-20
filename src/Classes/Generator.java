package Classes;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;

import Classes.FileManagement.RESFile;
import Classes.FileManagement.TESTFile;
import Classes.Instanciation.HoursSettings;
import Classes.Instanciation.Instance;
import Classes.Instanciation.MatriceSettings;
import Classes.LinearPrograms.LP;
import Classes.LinearPrograms.LPOneWay;
import Classes.LinearPrograms.LPResults;
import Classes.LinearPrograms.LPSettings;
import Classes.LinearPrograms.LPVariationsSettings;

public class Generator {
	// ****************** OBJECTS CREATION ****************** //
	// Number of variables varying
	public static LinkedList<LinkedList<Integer>> varyingValues = null;
	public static LinkedList<Integer> tempVaryingValues = null;

	// Get every results
	public static LinkedList<LinkedList<Double>> everyResults = null;
	public static LinkedList<Double> tempEveryResults = null;
	public Instance instance = null;
	public static LPSettings var = null;
	public LP LPWT = null; // LP With Return
	public LPOneWay LPOW = null;
	// ************ VARIATIONS ************ //
	public static int tempNUsers;
	public static int tempAdvance;
	public static int tempWaitingTime;
	public static int tempDeviationPercentage;
	public static int tempDeviationValue;

	public static Instant start = null;

	public Generator(GlobalSettings GS, MatriceSettings MS, HoursSettings HS, LPSettings LPS,
			LPVariationsSettings LPVS) {
		int initialN = MS.getN();
		// **************** EXPERIMENTATIONS **************** //
		for (int i = 0; i < GS.getNR(); i++) {
			// ************ VARIATIONS ************ //
			tempNUsers = initialN;
			tempAdvance = LPS.getWTA();
			tempWaitingTime = LPS.getWTR();
			tempDeviationPercentage = (int) (LPS.getDP() * 100) - 100;
			tempDeviationValue = LPS.getDV();

			if (GS.getModeInstance() == Constants.GSI || GS.getModeInstance() == Constants.GSR) {
				// ********* CONSTANT INSTANCIATIONS LP ********* //
				instance = new Instance(tempNUsers, MS, HS);
				new TESTFile(instance, MS, GS.getFS());
			}
			// Save the current values
			varyingValues = new LinkedList<LinkedList<Integer>>();
			everyResults = new LinkedList<LinkedList<Double>>();
			start = Instant.now();
			do {
				tempVaryingValues = new LinkedList<Integer>();
				tempEveryResults = new LinkedList<Double>();
				if (GS.getModeInstance() == Constants.GDI) {
					// ********* CONSTANT INSTANCIATIONS USERS ********* //
					instance = new Instance(tempNUsers, MS, HS);
					new TESTFile(instance, MS, GS.getFS());
				}
				// To execute the LP
				LPWT = new LP(instance,
						new LPSettings(tempAdvance, tempWaitingTime, tempDeviationPercentage, tempDeviationValue));
				// Add here every varying value you want
				tempVaryingValues.add(Integer.valueOf(tempNUsers));
				tempVaryingValues.add(Integer.valueOf(tempAdvance));
				tempVaryingValues.add(Integer.valueOf(tempWaitingTime));
				tempVaryingValues.add(Integer.valueOf(tempDeviationPercentage));
				tempVaryingValues.add(Integer.valueOf(tempDeviationValue));
				// Add here every results you want
				tempEveryResults.add(LPWT.getRes().getObjective());
				tempEveryResults.add(LPWT.getRes().getExecTime());
				// Save the current values
				varyingValues.add(tempVaryingValues);
				everyResults.add(tempEveryResults);
				// ************ VARIATIONS ************ //
				tempNUsers += LPVS.getVNU();
				tempAdvance += LPVS.getVA();
				tempWaitingTime += LPVS.getVWT();
				tempDeviationPercentage += LPVS.getVDP();
				tempDeviationValue += LPVS.getvDV();
				System.out.println("O : " + LPWT.getRes().getObjective() + " ET : " + LPWT.getRes().getExecTime());
			} while (LPWT.getRes().getExecTime() <= GS.getETM()
					&& (Duration.between(start, Instant.now()).compareTo(Duration.ofMinutes(GS.getMBEM())) < 0)
					&& tempNUsers < GS.getNMaxUsers() && !GS.getSingleRun());

			new RESFile(everyResults, varyingValues, MS, GS.getFS());
		}
	}

	// GETTERS
	public LPResults getResults() {
		return this.LPWT.getRes();
	}

	public Instance getInstance() {
		return this.instance;
	}
}

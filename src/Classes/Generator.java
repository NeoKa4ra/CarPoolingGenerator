package Classes;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;

import Classes.FileManagement.FileSettings;
import Classes.FileManagement.RESFile;
import Classes.FileManagement.TESTFile;
import Classes.Instanciation.Instance;
import Classes.Instanciation.InstanceSettings;
import Classes.LinearPrograms.LPSettings;
import Classes.LinearPrograms.LPVariationsSettings;
import Classes.LinearPrograms.LPWithReturn;

public class Generator {
	// ****************** OBJECTS CREATION ****************** //
	// Number of variables varying
	public static LinkedList<LinkedList<Integer>> varyingValues = null;
	public static LinkedList<Integer> tempVaryingValues = null;

	// Get every results
	public static LinkedList<LinkedList<Double>> everyResults = null;
	public static LinkedList<Double> tempEveryResults = null;
	public static Instance instance = null;
	public static LPSettings var = null;
	public static LPWithReturn LP = null;
	public static FileSettings FS = null;
	// ************ VARIATIONS ************ //
	public static int tempNUsers;
	public static int tempAdvance;
	public static int tempWaitingTime;
	public static int tempDeviationPercentage;
	public static int tempDeviationValue;

	public static InstanceSettings tempIS;
	public static Instant start = null;

	public Generator(GlobalSettings GS, InstanceSettings IS, LPSettings LPS, LPVariationsSettings LPVS, int swap) {
		// **************** EXPERIMENTATIONS **************** //
		for (int i = 0; i < GS.getNR(); i++) {
			// ************ VARIATIONS ************ //
			tempNUsers = IS.getNU();
			tempAdvance = LPS.getWTA();
			tempWaitingTime = LPS.getWTR();
			tempDeviationPercentage = (int) LPS.getDP() * 100 - 100;
			tempDeviationValue = LPS.getDV();

			if (swap == Constants.LP) {
				// ********* CONSTANT INSTANCIATIONS LP ********* //
				tempIS = new InstanceSettings(tempNUsers, IS.getWM(), IS.getMM(), IS.getRR());
				instance = new Instance(tempIS);
				FS = new FileSettings(tempNUsers, tempIS, Constants.TEST, GS.getFS());
				TESTFile tf = new TESTFile(instance, FS);
			}
			// Save the current values
			varyingValues = new LinkedList<LinkedList<Integer>>();
			everyResults = new LinkedList<LinkedList<Double>>();
			start = Instant.now();
			do {
				tempVaryingValues = new LinkedList<Integer>();
				tempEveryResults = new LinkedList<Double>();

				if (swap == Constants.USERS) {
					// ********* CONSTANT INSTANCIATIONS USERS ********* //
					tempIS = new InstanceSettings(tempNUsers, IS.getWM(), IS.getMM(), IS.getRR());
					instance = new Instance(tempIS);
					FS = new FileSettings(tempNUsers, tempIS, Constants.TEST, GS.getFS());
					new TESTFile(instance, FS);
				}
				// To execute the LP
				LP = new LPWithReturn(instance,
						new LPSettings(tempAdvance, tempWaitingTime, tempDeviationPercentage, tempDeviationValue));
				// Add here every varying value you want
				tempVaryingValues.add(Integer.valueOf(tempNUsers));
				tempVaryingValues.add(Integer.valueOf(tempAdvance));
				tempVaryingValues.add(Integer.valueOf(tempWaitingTime));
				tempVaryingValues.add(Integer.valueOf(tempDeviationPercentage));
				tempVaryingValues.add(Integer.valueOf(tempDeviationValue));
				// Add here every results you want
				tempEveryResults.add(LP.getRes().getObjective());
				tempEveryResults.add(LP.getRes().getExecTime());
				// Save the current values
				varyingValues.add(tempVaryingValues);
				everyResults.add(tempEveryResults);
				// ************ VARIATIONS ************ //
				tempNUsers += LPVS.getVNU();
				tempAdvance += LPVS.getVA();
				tempWaitingTime += LPVS.getVWT();
				tempDeviationPercentage += LPVS.getVDP();
				tempDeviationValue += LPVS.getvDV();
				System.out.println("O : " + LP.getRes().getObjective() + " ET : " + LP.getRes().getExecTime());
			} while (LP.getRes().getExecTime() <= GS.getETM()
					&& (Duration.between(start, Instant.now()).compareTo(Duration.ofMinutes(GS.getMBEM())) < 0)
					&& tempNUsers < 23);

			FS = new FileSettings(tempNUsers, IS, Constants.RES, GS.getFS());
			new RESFile(everyResults, varyingValues, FS);
		}
	}
}

package Classes.Instanciation;

import Classes.Constants;

public class InstanceSettings {
	// *********** DATA GENERATION SETTINGS ********** //
	// Number of users
	private int nUsers;
	// RW : Random matrix; RCW : close together; RSW : RxSW :
	private int matrixMode = Constants.RCW;
	private int[] modeArg;

	private int hoursMode = Constants.NMRE;
	// Range of the randomness of the costs and times matrices
	private int rdmRange = 200;

	public InstanceSettings(int nU, int[] mA, int hM, int rR) {
		this.nUsers = nU;
		this.matrixMode = mA[0];
		this.modeArg = mA;
		this.hoursMode = hM;
		this.rdmRange = rR;
	}

	// GETTERS
	public int getNU() {
		return this.nUsers;
	}

	public int getMM() {
		return this.matrixMode;
	}

	public int getHM() {
		return this.hoursMode;
	}

	public int getRR() {
		return this.rdmRange;
	}
	
	public int[] getMA() {
		return this.modeArg;
	}
}

package Classes.Instanciation;

import Classes.Constants;

public class InstanceSettings {
	// *********** DATA GENERATION SETTINGS ********** //
	// Number of users
	private int nUsers;
	// Way W : Going; WS : with satellites; WH : With return; WHS : with satellites
	private int wayMode = Constants.WH;
	// RW : Random matrix; RCW : close together; RSW : RxSW :
	private int matrixMode = Constants.RCW;
	// Range of the randomness of the costs and times matrices
	private int rdmRange = 200;

	public InstanceSettings(int nU, int wM, int mM, int rR) {
		this.nUsers = nU;
		this.wayMode = wM;
		this.matrixMode = mM;
		this.rdmRange = rR;
	}

	// GETTERS
	public int getNU() {
		return this.nUsers;
	}

	public int getWM() {
		return this.wayMode;
	}

	public int getMM() {
		return this.matrixMode;
	}

	public int getRR() {
		return this.rdmRange;
	}
}

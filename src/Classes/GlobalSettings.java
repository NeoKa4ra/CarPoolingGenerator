package Classes;

public class GlobalSettings {
	// *********** GLOBAL SETTINGS ********** //

	// LaunchesTimes
	private int nLaunches;
	// Execution time
	private int execTimeMax;
	private int minutesByExecutionMax;
	// Suffix of the res file
	private String fileSuffix;

	public GlobalSettings(int nL, int eTM, int mBEM, String fS) {
		this.nLaunches = nL;
		this.execTimeMax = eTM;
		this.minutesByExecutionMax = mBEM;
		this.fileSuffix = fS;
	}
	// GETTERS

	public int getNR() {
		return this.nLaunches;
	}

	public int getETM() {
		return this.execTimeMax;
	}

	public int getMBEM() {
		return this.minutesByExecutionMax;
	}

	public String getFS() {
		return this.fileSuffix;
	}
}

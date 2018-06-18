package Classes;

public class GlobalSettings {
	// *********** GLOBAL SETTINGS ********** //

	// LaunchesTimes
	private int nLaunches;
	// Execution time
	private int execInstanceTimeMax;
	private int execTotalTimeMax;
	// Suffix of the res file
	private String fileSuffix;

	public GlobalSettings(int nL, int eTM, int mBEM, String fS) {
		this.nLaunches = nL;
		this.execInstanceTimeMax = eTM;
		this.execTotalTimeMax = mBEM;
		this.fileSuffix = fS;
	}
	// GETTERS

	public int getNR() {
		return this.nLaunches;
	}

	public int getETM() {
		return this.execInstanceTimeMax;
	}

	public int getMBEM() {
		return this.execTotalTimeMax;
	}

	public String getFS() {
		return this.fileSuffix;
	}
}

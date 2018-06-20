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
	private int mode;
	private boolean singlerun;

	public GlobalSettings(int m, int nL, int eTM, int mBEM, String fS) {
		this.mode = m;
		this.nLaunches = nL;
		this.execInstanceTimeMax = eTM;
		this.execTotalTimeMax = mBEM;
		this.fileSuffix = fS;
		this.singlerun = this.mode == Constants.SINGLERUN ;
	}
	// GETTERS
	public int getMode() {
		return this.mode;
	}

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
	
	public boolean getSingleRun() {
		return this.singlerun;
	}
}

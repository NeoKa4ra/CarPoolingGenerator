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
	private int modeInstance;
	private int modeLP;
	private boolean singlerun;
	private int nMaxUsers;

	public GlobalSettings(int mLP, int mInstance, int nL, int eTM, int mBEM, int nMU, String fS) {
		this.modeInstance = mInstance;
		this.nMaxUsers = nMU;
		this.modeLP = mLP;
		this.nLaunches = nL;
		this.execInstanceTimeMax = eTM;
		this.execTotalTimeMax = mBEM;
		this.fileSuffix = fS;
		this.singlerun = (this.modeInstance == Constants.GSR);
	}

	// GETTERS
	public int getModeLP() {
		return this.modeLP;
	}

	public int getModeInstance() {
		return this.modeInstance;
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

	public int getNMaxUsers() {
		// TODO Auto-generated method stub
		return this.nMaxUsers;
	}
}

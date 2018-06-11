package Classes.FileManagement;

import Classes.Instanciation.InstanceSettings;

public class FileSettings {
	private int n;
	private int matrixMode;
	private int wayMode;
	private int rdmRange;
	private int mode;
	private String suffix;

	public FileSettings(int nP, InstanceSettings IS, int m, String s) {
		this.n = nP;
		this.matrixMode = IS.getMM();
		this.wayMode = IS.getWM();
		this.rdmRange = IS.getRR();
		this.mode = m;
		this.suffix = s;
	}

	// GETTERS
	public int getN() {
		return this.n;
	}

	public int getMM() {
		return this.matrixMode;
	}

	public int getWM() {
		return this.wayMode;
	}

	public int getRDMR() {
		return this.rdmRange;
	}

	public int getM() {
		return this.mode;
	}

	public String getSuffix() {
		return this.suffix;
	}
}

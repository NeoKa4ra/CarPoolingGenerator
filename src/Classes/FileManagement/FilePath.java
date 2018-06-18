package Classes.FileManagement;

import java.io.File;

import Classes.Constants;

public class FilePath {
	String path = "";

	public FilePath(FileSettings FS) {
		if (FS.getM() == Constants.TEST) {
			this.path = "P:\\Travaux\\Modelisation\\Tests\\test";
		} else if (FS.getM() == Constants.RES) {
			this.path = "P:\\Travaux\\Modelisation\\Results\\res";
		}
		
		switch (FS.getMM()) {
		case Constants.RW: // random matrix to go to work
			this.path += "RW";
			break;

		case Constants.RCW: // random matrix with close houses and close works
			this.path += "RCW";
			break;

		case Constants.RSW: // random matrix with the same work
			this.path += "RSW";
			break;

		case Constants.RxSW:
			this.path += "RxSW";
			break;
		}
		switch (FS.getWM()) {
		case Constants.W:
			this.path += "-W";
			break;
		case Constants.WH:
			this.path += "-WH";
			break;
		case Constants.WS:
			this.path += "-WS";
			break;
		case Constants.WHS:
			this.path += "-WHS";
			break;
		}
		this.path += "-";
		this.path += FS.getN();
		this.path += "-";
		this.path += FS.getRDMR();
		this.path += "-";
		this.path += FS.getSuffix();
		String FILENAME = this.path.toString() + ".txt";
		int numFichier = 1;
		File FILE = new File(FILENAME);
		while (FILE.exists()) {
			FILENAME = this.path.toString() + " (" + (numFichier++) + ")" + ".txt";
			FILE = new File(FILENAME);
		}
		this.path = FILENAME;
	}

	public String toString() {
		return this.path;
	}
}

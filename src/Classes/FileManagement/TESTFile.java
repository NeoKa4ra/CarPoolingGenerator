package Classes.FileManagement;

import java.io.File;
import java.io.FileWriter;

import Classes.Constants;
import Classes.Instanciation.Instance;
import Classes.Instanciation.MatriceSettings;

public class TESTFile {
	private String str;

	public TESTFile(Instance instance, MatriceSettings MS, String suffix) {

		str = "";
		str += "P:\\Travaux\\Modelisation\\Tests\\test";
		switch (MS.getMode()) {
		case Constants.MM: // random matrix to go to work
			str += "MM";
			break;

		case Constants.MC: // random matrix with close houses and close works
			str += "MC";
			break;

		case Constants.MP: // random matrix with the same work
			str += "MP";
			break;

		case Constants.MCP:
			str += "MCP";
			break;

		case Constants.MCPW:
			str += "MCPW";
			break;

		case Constants.MCPWP:
			str += "MCPWP";
			break;
		}
		str += "-";
		str += MS.getN();
		str += "-";
		str += MS.getMaxRange();
		str += "-";
		str += suffix;
		String FILENAME = str.toString() + ".txt";
		int numFichier = 1;
		File FILE = new File(FILENAME);
		while (FILE.exists()) {
			FILENAME = str.toString() + " (" + (numFichier++) + ")" + ".txt";
			FILE = new File(FILENAME);
		}
		str = FILENAME;

		File f = new File(str);

		try {
			// Creation of the file
			f.createNewFile();
			// creation of the writer
			final FileWriter writer = new FileWriter(f);
			try {
				writer.write(instance.toString());
			} finally {
				writer.close();
			}
		} catch (Exception e) {
			System.out.println("File creation impossible");
		}
	}
}

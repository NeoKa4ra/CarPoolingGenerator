package Classes.FileManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import Classes.Constants;
import Classes.Instanciation.MatriceSettings;

public class RESFile {
	private String str;
	public RESFile(LinkedList<LinkedList<Double>> everyResults, LinkedList<LinkedList<Integer>> varyingValues,
			MatriceSettings MS, String suffix) {
		
		str = "";
		str += "P:\\Travaux\\Modelisation\\Results\\res";
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
		BufferedWriter bw = null;
		FileWriter fw = null;
		int nbKeyVV = 0;
		int nbKeyR = 0;
		try {
			fw = new FileWriter(FILENAME.toString());
			bw = new BufferedWriter(fw);
			// Number of easting
			ListIterator<Integer> tempVV = varyingValues.get(0).listIterator();
			while (tempVV.hasNext()) {
				bw.write("P" + ++nbKeyVV + " ");
				tempVV.next();
			}
			// Number of northing
			ListIterator<Double> tempR = everyResults.get(0).listIterator();
			while (tempR.hasNext()) {
				bw.write("$x" + ++nbKeyR + "$ ");
				tempR.next();
			}
			bw.write("\n");
			// Values
			ListIterator<LinkedList<Integer>> lVV = varyingValues.listIterator();
			ListIterator<LinkedList<Double>> lR = everyResults.listIterator();
			while (lVV.hasNext()) {
				tempVV = lVV.next().listIterator();
				while (tempVV.hasNext()) {
					bw.write(tempVV.next() + " ");
				}
				tempR = lR.next().listIterator();
				while (tempR.hasNext()) {
					bw.write(tempR.next() + " ");
				}
				bw.write("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {
				ex.printStackTrace();

			}
		}
	}
}

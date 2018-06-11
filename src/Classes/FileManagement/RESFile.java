package Classes.FileManagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

public class RESFile {

	public RESFile(LinkedList<LinkedList<Double>> everyResults, LinkedList<LinkedList<Integer>> varyingValues,
			FileSettings FS) {
		FilePath FILENAME = new FilePath(FS);
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

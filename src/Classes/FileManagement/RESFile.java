package Classes.FileManagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Classes.LinearPrograms.LPResults;

public class RESFile {

	public RESFile(int nTests, int nVarRes, LPResults[] res, FileSettings FS) {
		FilePath FILENAME = new FilePath(FS);
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(FILENAME.toString());
			bw = new BufferedWriter(fw);
			// Number of easting
			for (int i = 0; i < res[0].getVaryingValue().length; i++) {
				bw.write("P" + (i + 1) + " ");
			}
			// Number of northing
			for (int i = 0; i < nVarRes - 1; i++) {
				bw.write("$x" + (i + 1) + "$ ");
			}
			bw.write("$x" + nVarRes + "$\n");
			// Values
			for (int i = 0; i < nTests; i++) {
				for (int j = 0; j < res[0].getVaryingValue().length; j++) {
					bw.write(res[i].getVaryingValue()[j] + " ");
				}
				bw.write(res[i].getObjective() + " " + res[i].getExecTime() + "\n");
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

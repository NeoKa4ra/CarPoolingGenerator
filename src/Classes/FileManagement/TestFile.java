package Classes.FileManagement;

import java.io.File;
import java.io.FileWriter;

import Classes.Constants;
import Classes.Instanciation.Instance;

public class TestFile {
	private String str;

	public TestFile(Instance instance, int matrixMode, int wayMode, int rdmRange) {
		
		FilePath FP = new FilePath(instance.getnPersons(), matrixMode, wayMode, rdmRange, Constants.TEST);
		File f = new File(FP.toString());

		try {
			// Creation of the file
			f.createNewFile();
			// creation of the writer
			final FileWriter writer = new FileWriter(f);
			try {
				writer.write(instance.getVertices() + "\n" + instance.getCostMatrices() + "\n" + instance.getHours()
						+ "\n" + instance.getDrivers() + "\n" + instance.getPassengers());
			} finally {
				writer.close();
			}
		} catch (Exception e) {
			System.out.println("File creation impossible");
		}
	}

	public String toString() {
		return this.str;
	}
}

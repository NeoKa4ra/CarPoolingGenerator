package Classes.FileManagement;

import java.io.File;
import java.io.FileWriter;

import Classes.Instanciation.Instance;

public class TESTFile {
	private String str;

	public TESTFile(Instance instance, FileSettings FS) {
		
		FilePath FP = new FilePath(FS);
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

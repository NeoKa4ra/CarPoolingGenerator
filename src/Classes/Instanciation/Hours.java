package Classes.Instanciation;

import java.util.Random;

public class Hours {
	private int[] B;
	private int[] E;
	private int n;

	public Hours(HoursSettings HS) {
		this.n = HS.getN();
		this.B = new int[n];
		this.E = new int[n];
		Random randomGenerator = new Random();

		for (int i = 0; i < n; i++) {
			this.B[i] = HS.getMorningHour() + randomGenerator.nextInt(HS.getMorningHourRange());
		}
		for (int i = 0; i < n; i++) {
			this.E[i] = HS.getEveningHour() + randomGenerator.nextInt(HS.getEveningHourRange());
		}

	}

	// Print the generated hours in the carpooling needed form
	public String toString() {
		String str = "//HOURS\n";
		str += "//Hour of arrival at the latest to work\n";
		str += "B=[";
		for (int i = 0; i < n; i++) {
			str += this.B[i];
			if (i != n - 1) {
				str += ",";
			}
		}
		str += "];\n";
		str += "//Hour of departure at the earliest to home\n";
		str += "E=[";
		for (int i = 0; i < n; i++) {
			str += this.E[i];
			if (i != n - 1) {
				str += ",";
			}
		}
		str += "];\n";
		return str;
	}

	public int[] getB() {
		return this.B.clone();
	}

	public int[] getE() {
		return this.E.clone();
	}
}

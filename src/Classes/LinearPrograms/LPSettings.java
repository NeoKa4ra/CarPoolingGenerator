package Classes.LinearPrograms;

public class LPSettings {
	private int WaitingTimeA;
	private int WaitingTimeR;

	private double travelTime;
	private int deviation;

	private int M = 9999;

	public LPSettings(int wta, int wtr, int travelT, int dev) {
		this.WaitingTimeA = wta;
		this.WaitingTimeR = wtr;
		this.travelTime = (double) (1 +  ((double)travelT / 100));
		this.deviation = dev;
	}

	// GETTERS
	public int getWTA() {
		return this.WaitingTimeA;
	}

	public int getWTR() {
		return this.WaitingTimeR;
	}

	public double getTravelTime() {
		return this.travelTime;
	}

	public int getDeviation() {
		return this.deviation;
	}

	public int getM() {
		return this.M;
	}

	public String toString() {
		String str;
		str = "Variable used in the Linear Program : \n";
		str += "Allowed waiting time for the home-to-work trip : " + this.WaitingTimeA + "\n";
		str += "Allowed waiting time for the return trip : " + this.WaitingTimeR + "\n";
		str += "Allowed deviation's percentage of the travel  : " + ((this.travelTime - 1) * 100) + "\n";
		str += "Allowed deviation's constant : " + this.deviation + "\n";
		str += "And the large M value used : " + this.M + "\n";
		return str;
	}
}

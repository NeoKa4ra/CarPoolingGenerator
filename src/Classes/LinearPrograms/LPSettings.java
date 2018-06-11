package Classes.LinearPrograms;

public class LPSettings {
	// *********** LINEAR PROGRAM STARTING SETTINGS ********** //
	// Waiting times permitted to go (home-to-work trip) and to return
	private int advance = 50;
	private int waitingTime = 25;
	// Percentage of the travel duration deviation allowed and constant
	private double deviationPercentage = 0;
	private int deviationValue = 0;

	private int M = 9999;

	public LPSettings(int wta, int wtr, int travelT, int dev) {
		this.advance = wta;
		this.waitingTime = wtr;
		this.deviationPercentage = (double) (1 + ((double) travelT / 100));
		this.deviationValue = dev;
	}

	// GETTERS
	public int getWTA() {
		return this.advance;
	}

	public int getWTR() {
		return this.waitingTime;
	}

	public double getDP() {
		return this.deviationPercentage;
	}

	public int getDV() {
		return this.deviationValue;
	}

	public int getM() {
		return this.M;
	}

	public String toString() {
		String str;
		str = "Variable used in the Linear Program : \n";
		str += "Allowed waiting time for the home-to-work trip : " + this.advance + "\n";
		str += "Allowed waiting time for the return trip : " + this.waitingTime + "\n";
		str += "Allowed deviation's percentage of the travel  : " + ((this.deviationPercentage - 1) * 100) + "\n";
		str += "Allowed deviation's constant : " + this.deviationValue + "\n";
		str += "And the large M value used : " + this.M + "\n";
		return str;
	}
}

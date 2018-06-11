package Classes.LinearPrograms;

public class LPVariationsSettings {
	// ************ VARIATIONS ************ //
	private int varyAdvance;
	private int varyWaitingTime;
	private int varyDeviationPercentage;
	private int varyDeviationValue;
	private int varyNUsers;

	public LPVariationsSettings(int vNU, int vA, int vWT, int vDP, int vDV) {
		this.varyAdvance = vA;
		this.varyWaitingTime = vWT;
		this.varyDeviationPercentage = vDP;
		this.varyDeviationValue = vDV;
		this.varyNUsers = vNU;
	}

	// GETTERS
	public int getVA() {
		return this.varyAdvance;
	}

	public int getVWT() {
		return this.varyWaitingTime;
	}

	public int getVDP() {
		return this.varyDeviationPercentage;
	}

	public int getvDV() {
		return this.varyDeviationValue;
	}

	public int getVNU() {
		return this.varyNUsers;
	}
}

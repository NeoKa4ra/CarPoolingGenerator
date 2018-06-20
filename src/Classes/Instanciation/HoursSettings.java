package Classes.Instanciation;

public class HoursSettings {
	private int n;
	private int mode;
	private int morningHour = 850;
	private int morningHourRange = 50;
	private int eveningHour = 1500;
	private int eveningHourRange = 500;

	public HoursSettings(int hoursMode, int nPersons) {
		this.n = nPersons;
		this.mode = hoursMode;
	}

	public HoursSettings(int hoursMode, int nPersons, int morningHour, int morningHourRange, int eveningHour,
			int eveningHourRange) {
		this.n = nPersons;
		this.mode = hoursMode;
		this.morningHour = morningHour;
		this.morningHourRange = morningHourRange;
		this.eveningHour = eveningHour;
		this.eveningHourRange = eveningHourRange;
	}

	// GETTERS
	public int getN() {
		return this.n;
	}

	public int getMode() {
		return this.mode;
	}

	public int getMorningHour() {
		return this.morningHour;
	}

	public int getMorningHourRange() {
		return this.morningHourRange;
	}

	public int getEveningHour() {
		return this.eveningHour;
	}

	public int getEveningHourRange() {
		return this.eveningHourRange;
	}
}

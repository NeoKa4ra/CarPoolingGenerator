package Classes.Instanciation;

import java.awt.Point;
import java.util.LinkedList;

import Classes.Constants;

public class MatriceSettings {
	private int mode;
	private int n;
	private int maxRange;
	private int nearRange = 0;
	private int nbCities = 0;
	private int nbWorks = 0;
	private LinkedList<Point> cities = null;
	private LinkedList<Point> workplaces = null;
	private int pScdWork = 0;
	private int pScdHome = 0;

	public void Initialize(int m, int nbP, int mR) {
		this.mode = m;
		this.n = nbP;
		this.maxRange = mR;
	}

	public MatriceSettings(int matrixMode, int nbPersons, int matrixRange) {
		Initialize(matrixMode, nbPersons, matrixRange);
		if (mode != Constants.MM)
			throw new RuntimeException("Incompatible mode with arguments");
	}

	public MatriceSettings(int matrixMode, int nbPersons, int matrixRange, int nearestRange) {
		Initialize(matrixMode, nbPersons, matrixRange);
		if (mode != Constants.MC)
			throw new RuntimeException("Incompatible mode with arguments");
		this.nearRange = nearestRange;
	}

	public MatriceSettings(int matrixMode, int nbPersons, int matrixRange, int nCities, int nWorks) {
		Initialize(matrixMode, nbPersons, matrixRange);
		if (mode != Constants.MP && mode != Constants.MCP && mode != Constants.MCPW)
			throw new RuntimeException("Incompatible mode with arguments");
		this.nbCities = nCities;
		this.nbWorks = nWorks;
	}

	public MatriceSettings(int matrixMode, int nbPersons, int matrixRange, LinkedList<Point> citiesList,
			LinkedList<Point> workplacesList, int probScdWork, int probScdHome) {
		Initialize(matrixMode, nbPersons, matrixRange);
		if (mode != Constants.MCPWP)
			throw new RuntimeException("Incompatible mode with arguments");
		this.cities = citiesList;
		this.workplaces = workplacesList;
		this.pScdWork = probScdWork;
		this.pScdHome = probScdHome;
	}

	// GETTERS
	public int getMode() {
		return this.mode;
	}

	public int getN() {
		return this.n;
	}

	public int getMaxRange() {
		return this.maxRange;
	}

	public int getNearRange() {
		return this.nearRange;
	}

	public int getNbCities() {
		return this.nbCities;
	}

	public int getNbWorks() {
		return this.nbWorks;
	}

	public LinkedList<Point> getCities() {
		return this.cities;
	}

	public LinkedList<Point> getWorkplaces() {
		return this.workplaces;
	}

	public int getPScdWork() {
		return this.pScdWork;
	}

	public int getPScdHome() {
		return this.pScdHome;
	}
}

package Classes.Instanciation;

import java.awt.Point;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import Classes.Constants;

public class Matrice {
	private int morningM[][];
	private int eveningM[][];
	private int n;
	private Random randomGenerator;
	private int diagonal = 0;
	Point[] Points;
	private MatriceSettings MS;
	private int distanceInterCity = 2;
	private int distanceBetweenCities = 5;

	// Generate a random matrix[n][n] and the range r of the random numbers
	public Matrice(MatriceSettings ms) {
		randomGenerator = new Random();
		this.MS = ms;
		this.n = MS.getN();
		this.morningM = new int[2 * n][2 * n];
		this.eveningM = new int[2 * n][2 * n];
		this.Points = new Point[4 * n];

		int rdmNumber = 0;
		// Generates a scenario following the mode given
		switch (MS.getMode()) {
		case Constants.MM: // random matrix to go to work
			for (int i = 0; i < n + n; i++) {
				for (int j = i; j < n + n; j++) {
					if (i == j) {
						this.morningM[i][j] = diagonal;
						this.eveningM[i][j] = diagonal;
					} else {
						rdmNumber = randomGenerator.nextInt(MS.getMaxRange());
						// Symmetric matrix
						this.morningM[i][j] = rdmNumber;
						this.morningM[j][i] = rdmNumber;
						rdmNumber = randomGenerator.nextInt(MS.getMaxRange());
						// Symmetric matrix
						this.eveningM[i][j] = rdmNumber;
						this.eveningM[j][i] = rdmNumber;
					}
				}
			}
			break;

		case Constants.MC: // random matrix with close houses and close works
			for (int i = 0; i < 2 * n; i++) {
				for (int j = i; j < 2 * n; j++) {
					if (i == j) {
						this.morningM[i][j] = diagonal;
						this.eveningM[i][j] = diagonal;
					} else {
						if (i < n && j < n) {
							// Vertices from homes to homes
							rdmNumber = randomGenerator.nextInt(MS.getNearRange()) + 1;
						} else if (i >= n && j >= n) {
							// Vertices from works to works
							rdmNumber = randomGenerator.nextInt(MS.getNearRange()) + 1;
						} else {
							rdmNumber = MS.getNearRange() + randomGenerator.nextInt(MS.getNearRange() - 1) + 1;
						}
						this.morningM[i][j] = rdmNumber;
						this.morningM[j][i] = rdmNumber;
						this.eveningM[i][j] = rdmNumber;
						this.eveningM[j][i] = rdmNumber;
					}
				}
			}
			break;

		// ************************ RANDOM POINT CLOUD *************************//
		case Constants.MP:
			for (int i = 0; i < 2 * n; i++) {
				Points[i] = new Point(randomGenerator.nextInt(MS.getMaxRange() - 1) + 1,
						randomGenerator.nextInt(MS.getMaxRange() - 1) + 1);
			}
			returnGeneration();
			CrowFliesDistance();
			break;

		case Constants.MCP:
			CitiesGeneration(null, MS.getNbCities());
			for (int i = n; i < 2 * n; i++) {// Generation of the workplaces
				Points[i] = new Point(randomGenerator.nextInt(MS.getMaxRange() - 1) + 1,
						randomGenerator.nextInt(MS.getMaxRange() - 1) + 1);
			}
			returnGeneration();
			CrowFliesDistance();
			break;

		case Constants.MCPW:// ** Matrix Cities and Workplaces **//
			CitiesGeneration(MS.getCities(), MS.getNbCities());
			WorkplacesGeneration(MS.getWorkplaces(), MS.getNbWorks());
			ShuffleWorkplacePoints();
			returnGeneration();
			CrowFliesDistance();
			break;

		case Constants.MCPWP:// ** Matrix Cities and Workplaces given with probabilities **//
			CitiesGeneration(MS.getCities(), MS.getNbCities());
			WorkplacesGeneration(MS.getWorkplaces(), MS.getNbWorks());
			ShuffleWorkplacePoints();
			returnGeneration();
			CrowFliesDistance();
			break;

		default:
			throw new RuntimeException("Matrix mode error");
		}
	}

	public void CrowFliesDistance() {
		int number = 0;
		for (int i = 0; i < 2 * n; i++) {
			for (int j = i; j < 2 * n; j++) {
				number = (int) Math.sqrt(Math.pow(Points[i].getX() - Points[j].getX(), 2)
						+ Math.pow(Points[i].getY() - Points[j].getY(), 2));
				this.morningM[i][j] = number;
				this.morningM[j][i] = number;
				if (i == j) {
					this.morningM[i][j] = diagonal;
				}
			}
		}
		for (int i = 2 * n; i < 4 * n; i++) {
			for (int j = i; j < 4 * n; j++) {
				number = (int) Math.sqrt(Math.pow(Points[i].getX() - Points[j].getX(), 2)
						+ Math.pow(Points[i].getY() - Points[j].getY(), 2));
				this.eveningM[i - 2 * n][j - 2 * n] = number;
				this.eveningM[j - 2 * n][i - 2 * n] = number;
				if (i == j) {
					this.eveningM[i - 2 * n][j - 2 * n] = diagonal;
				}
			}
		}
	}

	private void returnGeneration() {
		int distanceFromOldWork = 15;
		int distanceFromOldHome = 30;
		for (int i = 2 * n; i < 3 * n; i++) {
			if (randomGenerator.nextInt(100) < MS.getPScdWork()) {
				Points[i] = new Point(
						randomGenerator.nextInt(2 * distanceFromOldWork) - distanceFromOldWork
								+ (int) Points[i - n].getX(),
						randomGenerator.nextInt(2 * distanceFromOldWork) - distanceFromOldWork
								+ (int) Points[i - n].getY());
			} else {
				Points[i] = (Point) Points[i - n].clone();
			}
		}
		for (int i = 3 * n; i < 4 * n; i++) {
			if (randomGenerator.nextInt(100) < MS.getPScdHome()) {
				Points[i] = new Point(
						randomGenerator.nextInt(2 * distanceFromOldHome) - distanceFromOldHome
								+ (int) Points[i - 3 * n].getX(),
						randomGenerator.nextInt(2 * distanceFromOldHome) - distanceFromOldHome
								+ (int) Points[i - 3 * n].getY());
			} else {
				Points[i] = (Point) Points[i - 3 * n].clone();
			}
		}
	}

	private void WorkplacesGeneration(LinkedList<Point> workplaces, int nbWorks) {
		int creatednbWorks = 0;
		int distanceBetweenCities = 30;
		LinkedList<Point> prevPoints = new LinkedList<Point>();
		boolean nearAnotherCity = false;
		if (nbWorks > 0) {
			for (int i = n; i < 2 * n; i++) { // Generation of the homes
				if (i % (n / nbWorks) == 0 && creatednbWorks < nbWorks) {
					creatednbWorks++;
					int nbIterations = 0;
					do {
						Points[i] = new Point(randomGenerator.nextInt(MS.getMaxRange() - 1) + 1,
								randomGenerator.nextInt(MS.getMaxRange() - 1) + 1);
						ListIterator<Point> prevPointsIT = prevPoints.listIterator();
						nearAnotherCity = false;
						while (prevPointsIT.hasNext() && !nearAnotherCity) {
							Point temp = prevPointsIT.next();
							if (Math.sqrt(Math.pow(Points[i].getX() - temp.getX(), 2)
									+ Math.pow(Points[i].getY() - temp.getY(), 2)) <= distanceBetweenCities) {
								nearAnotherCity = true;
							} else {
								nearAnotherCity = false;
							}
						}
						if (nbIterations++ > 50) {
							distanceBetweenCities = (distanceBetweenCities <= 0) ? 0 : distanceBetweenCities - 1;
						}
					} while (nearAnotherCity);
					prevPoints.add((Point) Points[i].clone());
				} else {
					Points[i] = (Point) Points[i - 1].clone();
				}
			}
		} else {
			ListIterator<Point> workplacesIT = workplaces.listIterator();
			for (int i = n; i < 2 * n; i++) { // Generation of the homes
				if (i % (n / workplaces.size()) == 0 && creatednbWorks < workplaces.size()) {
					creatednbWorks++;
					Points[i] = workplacesIT.next();
				} else {
					Points[i] = (Point) Points[i - 1].clone();
				}
			}
		}
		creatednbWorks = 0;
	}

	private void CitiesGeneration(LinkedList<Point> Cities, int nbCities) {

		Point prevPoint = null;
		int createdCities = 0;
		LinkedList<Point> prevPoints = new LinkedList<Point>();
		boolean nearAnotherCity = false;
		if (nbCities > 0) {
			for (int i = 0; i < n; i++) { // Generation of the homes
				if (i % (n / nbCities) == 0 && createdCities < nbCities) {
					createdCities++;
					int nbIterations = 0;
					do {
						Points[i] = new Point(randomGenerator.nextInt(MS.getMaxRange()),
								randomGenerator.nextInt(MS.getMaxRange()));
						ListIterator<Point> prevPointsIT = prevPoints.listIterator();
						nearAnotherCity = false;
						while (prevPointsIT.hasNext() && !nearAnotherCity) {
							Point temp = prevPointsIT.next();
							if (Math.sqrt(Math.pow(Points[i].getX() - temp.getX(), 2)
									+ Math.pow(Points[i].getY() - temp.getY(), 2)) <= distanceBetweenCities) {
								nearAnotherCity = true;
							} else {
								nearAnotherCity = false;
							}
						}
						if (nbIterations++ > 50) {
							distanceBetweenCities = (distanceBetweenCities <= 0) ? 0 : distanceBetweenCities - 1;
						}
					} while (nearAnotherCity);
					prevPoints.add((Point) Points[i].clone());
					prevPoint = (Point) Points[i].clone();
				} else {
					int x;
					int y;
					do {
						x = (int) (randomGenerator.nextInt((2 * distanceInterCity - 1)) + prevPoint.getX()
								- distanceInterCity);
						y = (int) (randomGenerator.nextInt((2 * distanceInterCity - 1)) + prevPoint.getY()
								- distanceInterCity);
					} while (Math.sqrt(Math.pow(x - prevPoint.getX(), 2)
							+ Math.pow(y - prevPoint.getY(), 2)) <= distanceInterCity);
					Points[i] = new Point(x, y);
				}
			}
			createdCities = 0;
		} else {
			ListIterator<Point> citiesIT = Cities.listIterator();
			boolean placeAlreadyTaken;
			int nbIt;
			for (int i = 0; i < n; i++) { // Generation of the homes
				nbIt = 0;
				if (i % (n / Cities.size()) == 0 && createdCities < Cities.size()) {
					createdCities++;
					Points[i] = citiesIT.next();
					prevPoint = (Point) Points[i].clone();
				} else {
					int x;
					int y;
					do {
						if (nbIt++ == 50) {
							distanceInterCity++;
						}
						x = (int) (randomGenerator.nextInt((2 * distanceInterCity - 1)) + prevPoint.getX()
								- distanceInterCity);
						y = (int) (randomGenerator.nextInt((2 * distanceInterCity - 1)) + prevPoint.getY()
								- distanceInterCity);
						placeAlreadyTaken = false;
						for (int k = 0; k < i; k++) {
							if (Math.sqrt(
									Math.pow(x - Points[k].getX(), 2) + Math.pow(y - Points[k].getY(), 2)) <= 0.5) {
								placeAlreadyTaken = true;
							}
						}
					} while (Math.sqrt(
							Math.pow(x - prevPoint.getX(), 2) + Math.pow(y - prevPoint.getY(), 2)) <= distanceInterCity
							|| placeAlreadyTaken);
					Points[i] = new Point(x, y);
				}
			}
		}
		createdCities = 0;
	}

	private void ShuffleWorkplacePoints() {
		// Shuffle the homes
		for (int i = 1; i < n; i++) {
			int j;
			do {
				j = randomGenerator.nextInt(i);
			} while (j < 0 || j >= n);
			Point temp = Points[i];
			Points[i] = Points[j];
			Points[j] = temp;
		}
		// Shuffle the workplaces
		for (int i = n + 1; i < 2 * n; i++) {
			int j;
			do {
				j = randomGenerator.nextInt(i) + n - 1;
			} while (j >= 2 * n || j < n);
			Point temp = Points[i];
			Points[i] = Points[j];
			Points[j] = temp;
		}
	}

	// Print the matrix in the carpooling needed form
	public String toString() {
		String str = "//ARCS\n";
		str += "// C[1..2][i][j] = cost to travel from i to j to work\n";
		str += "C=[[\n";
		for (int i = 0; i < n + n; i++) {
			str += '[';
			for (int j = 0; j < n + n; j++) {
				str += this.morningM[i][j];
				if (j != n + n - 1) {
					str += ", ";
				}
			}
			if (i != n + n - 1) {
				str += "],\n";
			} else {
				str += "]\n";
			}
		}
		str += "],[\n";
		for (int i = 0; i < n + n; i++) {
			str += '[';
			for (int j = 0; j < n + n; j++) {
				str += this.eveningM[i][j];
				if (j != n + n - 1) {
					str += ", ";
				}
			}
			if (i != n + n - 1) {
				str += "],\n";
			} else {
				str += "]\n";
			}
		}
		str += "]];\n";
		return str;
	}

	// ********************* GETTERS/SETTERS ********************* //
	public int[][] A() {
		return this.morningM.clone();
	}

	public int[][] R() {
		return this.eveningM.clone();
	}

	public int getDiagonal() {
		return this.diagonal;
	}

	public Point[] getPoints() {
		return this.Points;
	}

	public int getN() {
		return this.n;
	}
}

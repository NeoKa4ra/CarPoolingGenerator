package Classes.Instanciation;

import java.awt.Point;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import Classes.Constants;

public class CostMatrices {
	private int morningM[][];
	private int eveningM[][];
	private int rdmRange = 10;
	private int rdmCloseRange = 1;
	private int n;
	private Random randomGenerator;
	Vertices v;
	private int diagonal = 0;
	Point[] Points;

	public void Initialize(Vertices vertices, int range) {
		randomGenerator = new Random();
		this.v = vertices;
		this.n = v.getPersons();
		this.rdmRange = (int) range / 2;
		this.morningM = new int[n + n][n + n];
		this.eveningM = new int[n + n][n + n];
		this.Points = new Point[4 * v.getPersons()];
	}

	// Generate a random matrix[n][n] and the range r of the random numbers
	public CostMatrices(Vertices vertices, int mode, int range) {
		Initialize(vertices, range);
		int rdmNumber = 0;

		// Generates a scenario following the mode given
		switch (mode) {
		case Constants.RW: // random matrix to go to work
			for (int i = 0; i < n + n; i++) {
				for (int j = i; j < n + n; j++) {
					if (i == j) {
						this.morningM[i][j] = diagonal;
					} else {
						// to get a number between 1 and rdmRange
						rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						// Symmetric matrix
						this.morningM[i][j] = rdmNumber;
						this.morningM[j][i] = rdmNumber;
					}
				}
			}
			for (int i = 0; i < n + n; i++) {
				for (int j = i; j < n + n; j++) {
					if (i == j) {
						this.eveningM[i][j] = diagonal;
					} else {
						// to get a number between 1 and rdmRange
						rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						// Symmetric matrix
						this.eveningM[i][j] = rdmNumber;
						this.eveningM[j][i] = rdmNumber;
					}
				}
			}
			break;

		case Constants.RCW: // random matrix with close houses and close works
			for (int i = 0; i < n + n; i++) {
				for (int j = i; j < n + n; j++) {
					if (i == j) {
						this.morningM[i][j] = diagonal;
					} else {
						if (i < v.getPersons() && j < v.getPersons()) {
							// Vertices from homes to homes
							rdmNumber = randomGenerator.nextInt(rdmCloseRange) + 1;
						} else if (i >= v.getPersons() && j >= v.getPersons()) {
							// Vertices from works to works
							rdmNumber = randomGenerator.nextInt(rdmCloseRange) + 1;
						} else {
							rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						}
						this.morningM[i][j] = rdmNumber;
						this.morningM[j][i] = rdmNumber;
					}
				}
			}

			for (int i = 0; i < n + n; i++) {
				for (int j = i; j < n + n; j++) {
					if (i == j) {
						this.eveningM[i][j] = diagonal;
					} else {
						if (i < v.getPersons() && j < v.getPersons()) {
							// Vertices from homes to homes
							rdmNumber = randomGenerator.nextInt(rdmCloseRange) + 1;
						} else if (i >= v.getPersons() && j >= v.getPersons()) {
							// Vertices from works to works
							rdmNumber = randomGenerator.nextInt(rdmCloseRange) + 1;
						} else {
							rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						}
						this.eveningM[i][j] = rdmNumber;
						this.eveningM[j][i] = rdmNumber;
					}
				}
			}
			break;

		case Constants.RSW: // random matrix with the same work
			for (int i = 0; i < n + n; i++) {
				for (int j = i; j < n + n; j++) {
					if (i == j) {
						this.morningM[i][j] = diagonal;
					} else {
						if (i < v.getPersons() && j < v.getPersons()) {
							// Vertices from homes to homes
							rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						} else if (i >= v.getPersons() && j >= v.getPersons()) {
							// Vertices from works to works
							rdmNumber = 0;
						} else {
							rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						}
						this.morningM[i][j] = rdmNumber;
						this.morningM[j][i] = rdmNumber;
					}
				}
			}

			for (int i = 0; i < n + n; i++) {
				for (int j = i; j < n + n; j++) {
					if (i == j) {
						this.eveningM[i][j] = diagonal;
					} else {
						if (i < v.getPersons() && j < v.getPersons()) {
							// Vertices from works to works
							rdmNumber = 0;
						} else if (i >= v.getPersons() && j >= v.getPersons()) {
							// Vertices from homes to homes
							rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						} else {
							rdmNumber = rdmRange + randomGenerator.nextInt(rdmRange - 1) + 1;
						}
						this.eveningM[i][j] = rdmNumber;
						this.eveningM[j][i] = rdmNumber;
					}
				}
			}
			break;

		// ************************ RANDOM POINT CLOUD *************************//
		case Constants.RPC:
			for (int i = 0; i < 2 * n; i++) {
				Points[i] = new Point(randomGenerator.nextInt(rdmRange - 1) + 1,
						randomGenerator.nextInt(rdmRange - 1) + 1);
			}
			SamePlaces();
			break;

		default:
			System.out.println("Matrix mode error");
			break;
		}
	}

	// ****************** RANDOM CITIES POINT CLOUD ********************//
	public CostMatrices(Vertices vertices, int mode, int range, int nbCities) {
		Initialize(vertices, range);
		switch (mode) {
		case Constants.RCPC:
			CitiesGeneration(nbCities);
			for (int i = n; i < 2 * n; i++) {// Generation of the workplaces
				Points[i] = new Point(randomGenerator.nextInt(rdmRange - 1) + 1,
						randomGenerator.nextInt(rdmRange - 1) + 1);
			}
			SamePlaces();
			break;
		}
	}

	// ****************** RANDOM CITIES POINT CLOUD SAME WORK ***************//
	public CostMatrices(Vertices vertices, int mode, int range, int nbCities, int nbWorks) {
		Initialize(vertices, range);
		switch (mode) {
		case Constants.RCPCSW:
			CitiesGeneration(nbCities);
			WorkplacesGeneration(nbWorks);
			ShuffleWorkplacePoints();
			SamePlaces();
			break;
		}
	}

	private void WorkplacesGeneration(int nbWorks) {
		int distanceBetweenCities = 30;
		LinkedList<Point> prevPoints = new LinkedList<Point>();
		boolean nearAnotherCity = false;
		int creatednbWorks = 0;
		for (int i = n; i < 2 * n; i++) { // Generation of the homes
			if (i % (n / nbWorks) == 0 && creatednbWorks < nbWorks) {
				creatednbWorks++;
				int nbIterations = 0;
				do {
					Points[i] = new Point(randomGenerator.nextInt(rdmRange - 1) + 1,
							randomGenerator.nextInt(rdmRange - 1) + 1);
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
		creatednbWorks = 0;
	}

	private void CitiesGeneration(int nbCities) {
		int distanceBetweenCities = 30;
		int distanceInterCity = 10;
		LinkedList<Point> prevPoints = new LinkedList<Point>();
		Point prevPoint = null;
		boolean nearAnotherCity = false;
		int createdCities = 0;
		for (int i = 0; i < n; i++) { // Generation of the homes
			if (i % (n / nbCities) == 0 && createdCities < nbCities) {
				createdCities++;
				int nbIterations = 0;
				do {
					Points[i] = new Point(randomGenerator.nextInt(rdmRange - 1) + 1,
							randomGenerator.nextInt(rdmRange - 1) + 1);
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
				} while (Math.sqrt(
						Math.pow(x - prevPoint.getX(), 2) + Math.pow(y - prevPoint.getY(), 2)) <= distanceInterCity);
				Points[i] = new Point(x, y);
			}
		}
		createdCities = 0;
	}

	public void SamePlaces() {
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
		int[][] tmpIJ = new int[n][n];
		int[][] tmpIJN = new int[n][n];
		int[][] tmpINJ = new int[n][n];
		int[][] tmpINJN = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tmpIJ[i][j] = morningM[i][j];
				tmpIJN[i][j] = morningM[i][j + n];
				tmpINJ[i][j] = morningM[i + n][j];
				tmpINJN[i][j] = morningM[i + n][j + n];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				eveningM[i][j] = tmpINJN[j][i];
				eveningM[i][j + n] = tmpIJN[j][i];
				eveningM[i + n][j] = tmpINJ[j][i];
				eveningM[i + n][j + n] = tmpIJ[j][i];
			}
		}
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

	public Vertices getV() {
		return this.v;
	}

	public int getRange() {
		return this.rdmRange;
	}

	public int getDiagonal() {
		return this.diagonal;
	}

	public Point[] getPoints() {
		return this.Points;
	}
}

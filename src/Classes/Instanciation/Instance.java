package Classes.Instanciation;

public class Instance {
	private Vertices v;
	private CostMatrices c;
	private Hours h;
	private Drivers d;
	private Passengers p;

	public Instance(int n, int wayMode, int matrixMode, int rdmRange) {
		// Generation of the characteristics of the scenario
		this.v = new Vertices(n, wayMode);
		// System.out.println(vertices);

		// Generation of the cost matrix
		this.c = new CostMatrices(v, matrixMode, rdmRange);
		// System.out.println(C);

		// Generation of the hours
		this.h = new Hours(v);
		// System.out.println(hours);

		// Generation of the drivers capacity and maximal travel time
		this.d = new Drivers(c);
		// System.out.println(drivers);

		this.p = new Passengers(v);
		// System.out.println(passengers);
	}
	
	public String toString() {
		return this.v.toString() + this.c.toString() + this.h.toString() + this.d.toString() + this.p.toString();
	}

	// GETTERS
	public Vertices getVertices() {
		return this.v;
	}

	public CostMatrices getCostMatrices() {
		return this.c;
	}

	public Hours getHours() {
		return this.h;
	}

	public Drivers getDrivers() {
		return this.d;
	}

	public Passengers getPassengers() {
		return this.p;
	}
}

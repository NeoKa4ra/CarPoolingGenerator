package Classes.Instanciation;

import Classes.Constants;

public class Instance {
	private int n;
	private Vertices v;
	private CostMatrices c;
	private Hours h;
	private Drivers d;
	private Passengers p;

	public Instance(InstanceSettings IS) {
		this.n = IS.getNU();
		// Generation of the characteristics of the scenario
		this.v = new Vertices(n, IS.getMM());
		// System.out.println(vertices);

		// Generation of the cost matrix
		switch (IS.getMM()) {
		case Constants.RW:
			this.c = new CostMatrices(v, IS.getMM(), IS.getRR());
			break;
		case Constants.RCW:
			this.c = new CostMatrices(v, IS.getMM(), IS.getRR());
			break;
		case Constants.RSW:
			this.c = new CostMatrices(v, IS.getMM(), IS.getRR());
			break;
		case Constants.RPC:
			this.c = new CostMatrices(v, IS.getMM(), IS.getRR());
			break;
		case Constants.RCPC:
			this.c = new CostMatrices(v, IS.getMM(), IS.getRR(), IS.getMA()[1]);
			break;
		case Constants.RCPCSW:
			this.c = new CostMatrices(v, IS.getMM(), IS.getRR(), IS.getMA()[1], IS.getMA()[2]);
			break;
		}

		// Generation of the hours
		this.h = new Hours(v, IS.getHM());
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

	public int getnPersons() {
		return this.n;
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

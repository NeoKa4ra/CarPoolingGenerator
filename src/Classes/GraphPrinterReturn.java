package Classes;

import Classes.Instanciation.Matrice;
import Classes.LinearPrograms.LPResults;

public class GraphPrinterReturn {
	private Matrice CM;
	private LPResults LPres;

	public GraphPrinterReturn(Matrice cm, LPResults lpr) {
		this.CM = cm;
		this.LPres = lpr;
		System.out.println(this.printGraph());
	}

	private String printGraph() {
		int n = CM.getN();
		String str = "";
		str += "\\begin{figure}[H]\n";
		str += "\t\t\\centering\n";
		str += "\t\t\\begin{tikzpicture}\n";
		for (int i = 2 * n; i < 3 * n; i++) {
			str += "\t\t\t\\node[draw,rectangle,rounded corners=1pt,fill=gray!100,label=above:{$$}] (W" + (i + 1 - 2 * n)
					+ ")at(" + (CM.getPoints()[i].getX()) + "," + (CM.getPoints()[i].getY()) + ") {W\\_" + (i + 1 - 2 * n)
					+ "};\n";
		}
		for (int i = 3 * n; i < 4 * n; i++) {
			str += "\t\t\t\\node[draw,circle,fill=gray!25,label=above:{$$}] (" + (i - 3 * n + 1) + ")at("
					+ (CM.getPoints()[i].getX()) + "," + (CM.getPoints()[i].getY()) + ") {" + (i - 3 * n + 1)
					+ "};\n";
		}
		for (int k = 0; k < n; k++) {
			for (int i = 2 * n; i < 4 * n; i++) {
				for (int j = 2 * n; j < 4 * n; j++) {
					if (LPres.getx()[k][i][j] && i != j) {
						if (i < 3*n && j < 3*n) {
							str += "\t\t\t\\draw[->,>=stealth] (W" + (i + 1 - 2 * n) + ") -- (W" + (j + 1 - 2 * n)
									+ ");\n";
						} else if (i >= 3*n && j < 3*n) {
							str += "\t\t\t\\draw[->,>=stealth] (" + (i + 1 - 3 * n) + ") -- (W" + (j + 1 - 2 * n)
									+ ");\n";
						} else if (i < 3*n && j >= 3*n) {
							str += "\t\t\t\\draw[->,>=stealth] (W" + (i + 1 - 2 * n) + ") -- (" + (j + 1 - 3 * n)
									+ ");\n";
						} else { // i & j >= n
							str += "\t\t\t\\draw[->,>=stealth] (" + (i + 1 - 3 * n) + ") -- (" + (j + 1 - 3 * n)
									+ ");\n";
						}
					}
				}
			}
		}
		str += "\t\t\\end{tikzpicture}\n";
		str += "\t\t\\caption{GraphReturn}\n";
		str += "\t\t\\label{fig:GraphReturn}\n";
		str += "\t\\end{figure}\n";
		return str;
	}
}

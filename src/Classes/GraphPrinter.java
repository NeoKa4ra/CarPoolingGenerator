package Classes;

import Classes.Instanciation.Matrice;
import Classes.LinearPrograms.LPResults;

public class GraphPrinter {
	private Matrice CM;
	private LPResults LPres;

	public GraphPrinter(Matrice cm) {
		this.CM = cm;
		this.LPres = null;
		System.out.println(this.printVertices());
	}

	public GraphPrinter(Matrice cm, LPResults lpr) {
		this.CM = cm;
		this.LPres = lpr;
		System.out.println(this.printGraph());
	}

	private String printVertices() {
		int n = CM.getN();
		String str = "";
		str += "\\begin{figure}[H]\n";
		str += "\t\t\\centering\n";
		str += "\t\t\\begin{tikzpicture}\n";
		for (int i = 0; i < n; i++) {
			str += "\t\t\t\\node[draw,circle,fill=gray!25,label=above:{$$}] (" + (i + 1) + ")at("
					+ (CM.getPoints()[i].getX()) + "," + (CM.getPoints()[i].getY()) + ") {" + (i + 1) + "};\n";
		}
		for (int i = n; i < 2 * n; i++) {
			str += "\t\t\t\\node[draw,rectangle,rounded corners=1pt,fill=gray!100,label=above:{$$}] (W" + (i - n + 1)
					+ ")at(" + (CM.getPoints()[i].getX()) + "," + (CM.getPoints()[i].getY()) + ") {W\\_" + (i - n + 1)
					+ "};\n";
		}
		str += "\t\t\\end{tikzpicture}\n";
		str += "\t\t\\caption{Graph}\n";
		str += "\t\t\\label{fig:Graph}\n";
		str += "\t\\end{figure}\n";
		return str;
	}

	private String printGraph() {
		int n = CM.getN();
		String str = "";
		str += "\\begin{figure}[H]\n";
		str += "\t\t\\centering\n";
		str += "\t\t\\begin{tikzpicture}\n";
		for (int i = 0; i < n; i++) {
			str += "\t\t\t\\node[draw,circle,fill=gray!25,label=above:{$$}] (" + (i + 1) + ")at("
					+ (CM.getPoints()[i].getX()) + "," + (CM.getPoints()[i].getY()) + ") {" + (i + 1)
					+ "};\n";
		}
		for (int i = n; i < 2 * n; i++) {
			str += "\t\t\t\\node[draw,rectangle,rounded corners=1pt,fill=gray!100,label=above:{$$}] (W" + (i - n + 1)
					+ ")at(" + (CM.getPoints()[i].getX()) + "," + (CM.getPoints()[i].getY()) + ") {W\\_"
					+ (i - n + 1) + "};\n";
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < 2 * n; i++) {
				for (int j = 0; j < 2 * n; j++) {
					if (LPres.getx()[k][i][j] && i != j) {
						if (i < n && j < n) {
							str += "\t\t\t\\draw[->,>=stealth] (" + (i + 1) + ") -- (" + (j + 1) + ");\n";
						} else if (i >= n && j < n) {
							str += "\t\t\t\\draw[->,>=stealth] (W" + (i + 1 - n) + ") -- (" + (j + 1) + ");\n";
						} else if (i < n && j >= n) {
							str += "\t\t\t\\draw[->,>=stealth] (" + (i + 1) + ") -- (W" + (j + 1 - n) + ");\n";
						} else { // i & j >= n
							str += "\t\t\t\\draw[->,>=stealth] (W" + (i + 1 - n) + ") -- (W" + (j + 1 - n) + ");\n";
						}
					}
				}
			}
		}
		str += "\t\t\\end{tikzpicture}\n";
		str += "\t\t\\caption{Graph}\n";
		str += "\t\t\\label{fig:Graph}\n";
		str += "\t\\end{figure}\n";
		return str;
	}
}

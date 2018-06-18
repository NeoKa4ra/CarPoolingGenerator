package Classes;

public class PrintLatexData {

	public PrintLatexData(int nbFiles, String xAxis, String filePath) {
		System.out.println("\\begin{figure}[H]");
		System.out.println("\t\t\\centering");
		System.out.println("\t\t\\begin{subfigure}{.5\\textwidth}");
		System.out.println("\t\t\t\\centering");
		System.out.println("\t\t\t\\begin{tikzpicture}");
		System.out.println("\t\t\t\t\\begin{axis}[");
		System.out.println("\t\t\t\t\txlabel=Allowed waiting time(\\% of an hour),");
		System.out.println("\t\t\t\t\tylabel=Objective value]");
		System.out.println("\t\t\t\t\t\\addplot table [x=" + xAxis + " ,y=$" + "x1" + "$]{" + filePath + ".txt};");
		for (int i = 0; i < nbFiles - 1; i++) {
			System.out.println("\t\t\t\t\t\\addplot table [x=" + xAxis + " ,y=$" + "x1" + "$]{" + filePath + " ("
					+ (i + 1) + ").txt};");
		}
		System.out.println("\t\t\t\t\\end{axis}");
		System.out.println("\t\t\t\\end{tikzpicture}");
		System.out.println("\t\t\t\\caption{Objective}");
		System.out.println("\t\t\\end{subfigure}%");
		System.out.println("\t\t\\begin{subfigure}{.5\\textwidth}");
		System.out.println("\t\t\t\\centering");
		System.out.println("\t\t\t\\begin{tikzpicture}");
		System.out.println("\t\t\t\t\\begin{axis}[");
		System.out.println("\t\t\t\t\txlabel=Allowed waiting time(\\% of an hour),");
		System.out.println("\t\t\t\t\tylabel=Objective value]");
		System.out.println("\t\t\t\t\t\\addplot table [x=" + xAxis + " ,y=$" + "x2" + "$]{" + filePath + ".txt};");
		for (int i = 0; i < nbFiles - 1; i++) {
			System.out.println("\t\t\t\t\t\\addplot table [x=" + xAxis + " ,y=$" + "x2" + "$]{" + filePath + " ("
					+ (i + 1) + ").txt};");
		}
		System.out.println("\t\t\t\t\\end{axis}");
		System.out.println("\t\t\t\\end{tikzpicture}");
		System.out.println("\t\t\t\\caption{Execution time}");
		System.out.println("\t\t\\end{subfigure}%");
		System.out.println("\t\t\\caption{Varying the waiting time random}");
		System.out.println("\t\t\\label{fig:var wt}");
		System.out.println("\t\\end{figure}");
	}
}

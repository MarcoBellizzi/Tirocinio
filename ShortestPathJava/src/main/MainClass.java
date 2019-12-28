package main;

import java.util.ArrayList;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import predicates.Edge;
import predicates.From;
import predicates.Path;
import predicates.To;

public class MainClass {

	private static int from, to;
	private static ArrayList<Integer> sortedPath;

	public static void main(String[] args) {

		try {

			Handler handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2"));

			ASPMapper.getInstance().registerClass(Edge.class);
			ASPMapper.getInstance().registerClass(Path.class);
			ASPMapper.getInstance().registerClass(From.class);
			ASPMapper.getInstance().registerClass(To.class);

			InputProgram input = new ASPInputProgram();

			from = 0;   // source node
			to = 7;     // destination node

			input.addProgram("from(" + from + "). to(" + to + ").");
			
			input.addFilesPath("encoding/rules");
			
			for(Edge edge : getEdges()) {
				input.addObjectInput(edge);
			}

			handler.addProgram(input);

			AnswerSets answerSets = (AnswerSets) handler.startSync();

			for(AnswerSet answerSet : answerSets.getOptimalAnswerSets()) {
				
				ArrayList<Path> path = new ArrayList<Path>();  // edges in the shortest path (unsorted)
				int sum = 0;                                   // total weight of the path

				for(Object obj : answerSet.getAtoms()) {
					if(obj instanceof Path) {
						path.add((Path)obj);
						sum += ((Path)obj).getWeight();
					}
				}

				sortedPath = new ArrayList<Integer>();    // edges in the shorted path (sorted)
				sortedPath.add(from);
				
				join(from,path,sortedPath);   // sorts the edges
				print(sortedPath,sum);        // show the result
			}  

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static ArrayList<Edge> getEdges() {
		ArrayList<Edge> edges = new ArrayList<Edge>();

		edges.add(new Edge(0,1,1));
		edges.add(new Edge(0,2,4));
		edges.add(new Edge(1,2,2));
		edges.add(new Edge(1,3,4));
		edges.add(new Edge(1,4,1));
		edges.add(new Edge(2,4,4));
		edges.add(new Edge(3,5,6));
		edges.add(new Edge(3,6,1));
		edges.add(new Edge(4,3,1));
		edges.add(new Edge(6,4,5));
		edges.add(new Edge(6,5,9));
		edges.add(new Edge(6,7,1));
		edges.add(new Edge(7,5,2));

		return edges;
	}

	private static void join(int from, ArrayList<Path> path, ArrayList<Integer> sortedPath) {
		for(Path p : path) {
			if(p.getFrom() == from) {
				sortedPath.add(p.getTo());
				if(p.getTo() == to) {
					return;
				}
				join(p.getTo(), path, sortedPath);
				return;
			}
		}
	}

	private static void print(ArrayList<Integer> path, int sum) {
		boolean first = true;
		System.out.print("path = ");
		for(int n : path) {
			if(!first)
				System.out.print(" - ");
			else
				first = false;
			System.out.print(n);
		}
		System.out.println("\nsum = " + sum);
	}


}

package lab3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {
	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return Double.compare(o1.getPathCost(), o2.getPathCost());

			}

		});
		frontier.add(root);
		ArrayList<Node> ex = new ArrayList<>();
		while (!frontier.isEmpty()) {
			Node curent = frontier.poll();
			if (curent.getLabel().equals(goal)) {
				return curent;
			} else {
				ex.add(curent);

				List<Edge> children = curent.getChildren();
				for (Edge child : children) {
					Node end = child.getEnd();
					double PastCothNew = curent.getPathCost() + end.getPathCost();
					if (!frontier.contains(child) && !ex.contains(child)) {
						frontier.add(end);
						end.setParent(curent);
					} else if (end.getPathCost() > PastCothNew) {
						end.setPathCost(PastCothNew);
						frontier.add(end);
						end.setParent(curent);

					}

				}

			}

		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		Node n = execute(root, start);
		n.setParent(null);
		Node node = execute(n, goal);

		return node;
	}

	public static void main(String[] args) {
		Node nS = new Node("S");
		Node nA = new Node("A");
		Node nB = new Node("B");
		Node nC = new Node("C");
		Node nD = new Node("D");
		Node nE = new Node("E");
		Node nG = new Node("G");
		Node nF = new Node("F");
		Node nH = new Node("H");
		nS.addEdge(nA, 5);
		nS.addEdge(nB, 2);
		nS.addEdge(nC, 4);
		nA.addEdge(nD, 9);
		nD.addEdge(nH, 7);
		nA.addEdge(nE, 4);
		nE.addEdge(nG, 6);
		nB.addEdge(nG, 6);
		nC.addEdge(nF, 2);
		nF.addEdge(nG, 1);
		UniformCostSearchAlgo go = new UniformCostSearchAlgo();
		Node node = go.execute(nS, "G");
		NodeUtils n1 = new NodeUtils();
		System.out.println(n1.printPath(node));
		Node node2 = go.execute(nS, "A", "G");
		System.out.println(n1.printPath(node2));
	}

}

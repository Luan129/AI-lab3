package lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthLimitedSearch {
	public Node execute(Node root, String goal, int limitedDepth) {
		Stack<Node> frontier = new Stack<>();
		frontier.push(root);
		ArrayList<Node> ex = new ArrayList<>();
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			if (current.getLabel().equals(goal)) {
				return current;
			} else {
				ex.add(current);
				if (current.getDepth() < limitedDepth) {
					List<Node> children = current.getChildrenNodes();
					for (Node child : children) {
						if (!frontier.contains(child) && !ex.contains(child)) {
							frontier.add(child);
							child.setParent(current);
							child.setDepth(current.getDepth() + 1);
						}

					}

				}
			}
		}
		return null;

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
		DepthLimitedSearch de = new DepthLimitedSearch();
		Node node = de.execute(nS, "H", 3);
		NodeUtils n1 = new NodeUtils();
		System.out.println(n1.printPath(node));
	}
}

package com.sooncode.apipath.code.newtree;

import java.util.ArrayList;
import java.util.List;

public class Tree<N> implements Node<N> {

	private N nodeData;

	private Node<N> parentNode;

	private int nodeSize = 0;

	private List<Node<N>> nodes = new ArrayList<Node<N>>();

	public Tree() {

	}

	public Tree(Node<N> thisNode) {

		thisNode.setParentNode(null);
		nodes.add(thisNode);
		nodeSize = 1;

	}

	public Node<N> addNode(Node<N> thisNode) {

		thisNode.setParentNode(null);
		nodes.add(thisNode);
		nodeSize++;
		return thisNode;

	}

	public Node<N> addNode(Node<N> parentNode, Node<N> thisNode) {
		
		if(parentNode == null) {
			return addNode(thisNode);
		}

		if (nodeSize > 0 || existThisNode(parentNode)) {
			thisNode.setParentNode(parentNode);
			nodes.add(thisNode);
			nodeSize++;
			return thisNode;
		}

		return null;
	}

	public boolean existThisNode(Node<N> thisNode) {

		for (int i = 0; i < nodes.size(); i++) {

			if (nodes.get(i) == thisNode) {
				return true;
			}
		}
		return false;
	}

	public N getNodeData() {
		return nodeData;
	}

	public void setNodeData(N nodeData) {
		this.nodeData = nodeData;
	}

	public Node<N> getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node<N> parentNode) {
		this.parentNode = parentNode;
	}

	public int getNodeSize() {
		return nodeSize;
	}

	public void setNodeSize(int nodeSize) {
		this.nodeSize = nodeSize;
	}

	public List<Node<N>> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node<N>> nodes) {
		this.nodes = nodes;
	}

	// ----------------------------------------------------------

}

package com.sooncode.apipath.code.newtree;

public class GeneralNode<N> implements Node<N> {

	private N nodeData ;
	
	private Node<N> parentNode ;

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

	 
 
	 
}

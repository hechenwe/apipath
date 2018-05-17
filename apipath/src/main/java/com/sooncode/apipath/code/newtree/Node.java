package com.sooncode.apipath.code.newtree;

public interface Node<N> {
	public N getNodeData();

	public void setNodeData(N nodeData);

	public Node<N> getParentNode();

	public void setParentNode(Node<N> parentNode);

}

package com.sooncode.apipath.code.tree;

public class TreeNode<T> {

	private T data;
	private int parent;

	public TreeNode(T data, int parent) {
		this.data = data;
		this.parent = parent;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

}

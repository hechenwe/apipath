package com.sooncode.apipath.code.tree;

import java.util.ArrayList;
import java.util.List;
public class Tree<E> {

	private final int DEFAULT_TREE_SIZE = 100;
	private int treeSize = 0;

	private TreeNode<E>[] nodes;

	private int nodeNums;

	 
	@SuppressWarnings("unchecked")
	public Tree(E data) {
		treeSize = DEFAULT_TREE_SIZE;
		nodes = new TreeNode[treeSize];
		nodes[0] = new TreeNode<E>(data, -1);
		nodeNums++;
	}

	 
	@SuppressWarnings("unchecked")
	public Tree(E data, int treeSize) {
		this.treeSize = treeSize;
		nodes = new TreeNode[treeSize];
		nodes[0] = new TreeNode<E>(data, -1);
		nodeNums++;
	}
 
	
	public TreeNode<E> addNode(TreeNode<E> parentNode , E data ) {
		for (int i = 0; i < treeSize; i++) {
			// 找到数组中第一个为null的元素，该元素保存新节点
			if (nodes[i] == null) {
				// 创建新节点，并用指定的数组元素保存它
				nodes[i] = new TreeNode<>(data, pos(parentNode));
				nodeNums++;
				return nodes[i];
			}
		}
		return null;
		 
	}

	// 判断树是否为空
	public boolean empty() {
		// 根结点是否为null
		return nodes[0] == null;
	}

	// 返回根节点
	public TreeNode<E> getRootNode() {
		// 返回根节点
		return nodes[0];
	}

	// 返回指定节点（非根结点）的父节点
	public TreeNode<E> getParentNode(TreeNode<E> node) {
		// 每个节点的parent记录了其父节点的位置
		return nodes[node.getParent()];
	}

	// 返回指定节点（非叶子节点）的所有子节点
	public List<TreeNode<E>> getChildrenNodes(TreeNode<E> parent) {
		List<TreeNode<E>> list = new ArrayList<TreeNode<E>>();
		for (int i = 0; i < treeSize; i++) {
			// 如果当前节点的父节点的位置等于parent节点的位置
			if (nodes[i] != null && nodes[i].getParent() == pos(parent)) {
				list.add(nodes[i]);
			}
		}
		return list;
	}

	// 返回该树的深度
	public int deep() {
		// 用于记录节点的最大深度
		int max = 0;
		for (int i = 0; i < treeSize && nodes[i] != null; i++) {
			// 初始化本节点的深度
			int def = 1;
			// m 记录当前节点的父节点的位置
			int m = nodes[i].getParent();
			// 如果其父节点存在
			while (m != -1 && nodes[m] != null) {
				// 向上继续搜索父节点
				m = nodes[m].getParent();
				def++;
			}
			if (max < def) {
				max = def;
			}
		}
		return max;
	}

	// 返回包含指定值的节点
	public int pos(TreeNode<E> node) {
		for (int i = 0; i < treeSize; i++) {
			// 找到指定节点
			if (nodes[i] == node) {
				return i;
			}
		}
		return -1;
	}

}
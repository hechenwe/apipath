package com.sooncode.apipath.code.newtree;

public class TestTree {

	public static void main(String[] args) {
		
		GeneralNode<String> gn1 = new GeneralNode<>();
		gn1.setNodeData("A");
		
		GeneralNode<String> gn2 = new GeneralNode<>();
		gn2.setNodeData("B");
		
		GeneralNode<String> gn3 = new GeneralNode<>();
		gn3.setNodeData("C");
		
		GeneralNode<String> gn4 = new GeneralNode<>();
		gn4.setNodeData("D");
		
		GeneralNode<String> gn5 = new GeneralNode<>();
		gn5.setNodeData("E");
		
		GeneralNode<String> gn6 = new GeneralNode<>();
		gn6.setNodeData("F");
		
		Tree<String> mainTree = new Tree<>(gn1);
		mainTree.addNode(gn1, gn2);
		mainTree.addNode(gn2, gn3);
		
		Tree<String> othorTree = new Tree<>();
		othorTree.addNode(gn4);
		othorTree.addNode(gn5);
		
		mainTree.addNode(gn3, othorTree);
		
		mainTree.addNode(othorTree, gn6);
		
		mainTree.toString();
		
		
		
		
		
		
		
	}
}

package com.sooncode.apipath.code.model.method;

import java.util.List;

import com.sooncode.apipath.code.model.ClassModel;
import com.sooncode.apipath.code.model.MethodModel;
import com.sooncode.apipath.code.model.TreeMethodModel;
import com.sooncode.apipath.code.newtree.Node;
import com.sooncode.apipath.code.newtree.Tree;
import com.sun.source.tree.StatementTree;
import com.sun.tools.javac.tree.JCTree.JCBlock;
import com.sun.tools.javac.tree.JCTree.JCIf;
import com.sun.tools.javac.tree.JCTree.JCStatement;

public class If implements MethodParser {
	@Override
	public String getTypeCode() {
		return "IF";
	}

	 

	@Override
	public Node<TreeMethodModel> getMethodModel(StatementTree statementTree, ClassModel classModel, MethodModel methodModel, Node<TreeMethodModel> parentNode) {
		
		JCIf jcif = (JCIf) statementTree;
		JCBlock thenStatement = (JCBlock) jcif.getThenStatement();
		JCBlock elseStatement = (JCBlock) jcif.getElseStatement();
		
		Tree<TreeMethodModel> ifTreeNode = new Tree<>();
		ifTreeNode.setParentNode(parentNode);
		
		if(thenStatement != null) {
			List<JCStatement> jCStatements4thenStatement = thenStatement.getStatements();
			Node<TreeMethodModel> thenNode = MethodInvocation.foreach(classModel, methodModel, jCStatements4thenStatement,ifTreeNode);
			if(thenNode != null) {
				ifTreeNode.addNode(thenNode);
			}
			
		}
		  
		if(elseStatement != null) {
			List<JCStatement> jCStatements4elseStatement = elseStatement.getStatements();
			Node<TreeMethodModel> elseNode = MethodInvocation.foreach(classModel, methodModel, jCStatements4elseStatement,ifTreeNode);
			if(elseNode != null) {
				ifTreeNode.addNode(elseNode);
			}
			
		}
		
		methodModel.getInvokeTree().addNode(parentNode, ifTreeNode);
		 
		return ifTreeNode;
	}



	
	
	
	 
	 
}

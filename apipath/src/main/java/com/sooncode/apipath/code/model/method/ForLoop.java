package com.sooncode.apipath.code.model.method;

import java.util.List;

import com.sooncode.apipath.code.model.ClassModel;
import com.sooncode.apipath.code.model.MethodModel;
import com.sooncode.apipath.code.model.TreeMethodModel;
import com.sooncode.apipath.code.newtree.Node;
import com.sun.source.tree.StatementTree;
import com.sun.tools.javac.tree.JCTree.JCBlock;
import com.sun.tools.javac.tree.JCTree.JCForLoop;
import com.sun.tools.javac.tree.JCTree.JCStatement;

public class ForLoop implements MethodParser {
	@Override
	public String getTypeCode() {
		return "FOR_LOOP";
	}

	@Override
	public Node<TreeMethodModel> getMethodModel(StatementTree statementTree, ClassModel classModel, MethodModel methodModel, Node<TreeMethodModel> parentNode) {
		JCForLoop jcForLoop = (JCForLoop) statementTree;
		
		JCBlock jcBlock = (JCBlock) jcForLoop.getStatement();
		
		List<JCStatement> list = jcBlock.getStatements();
		
		Node<TreeMethodModel> thisNode = MethodInvocation.foreach(classModel, methodModel, list, parentNode);
		
		return thisNode;
	}

	 
	 
}

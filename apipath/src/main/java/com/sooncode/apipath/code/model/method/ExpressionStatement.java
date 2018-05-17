package com.sooncode.apipath.code.model.method;

import com.sooncode.apipath.code.model.ClassModel;
import com.sooncode.apipath.code.model.MethodModel;
import com.sooncode.apipath.code.model.TreeMethodModel;
import com.sooncode.apipath.code.newtree.Node;
import com.sun.source.tree.StatementTree;
import com.sun.source.tree.Tree.Kind;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCExpressionStatement;

public class ExpressionStatement implements MethodParser {

	@Override
	public String getTypeCode() {
		return "EXPRESSION_STATEMENT";
	}

	 

	@Override
	public Node<TreeMethodModel> getMethodModel(StatementTree statementTree, ClassModel classModel, MethodModel methodModel, Node<TreeMethodModel> parentNode) {
		Node<TreeMethodModel> thisNode = null;
		JCExpressionStatement jCExpressionStatement = (JCExpressionStatement) statementTree;
		JCExpression jcExpression = jCExpressionStatement.getExpression();
		thisNode = MethodInvocation.addMethodTree(jcExpression, methodModel, classModel, parentNode);
		return thisNode;
	}

	 
}

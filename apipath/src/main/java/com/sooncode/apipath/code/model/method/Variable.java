package com.sooncode.apipath.code.model.method;

import java.util.HashMap;
import java.util.Map;

import com.sooncode.apipath.code.model.ClassModel;
import com.sooncode.apipath.code.model.LocalValueModel;
import com.sooncode.apipath.code.model.MethodModel;
import com.sooncode.apipath.code.model.TreeMethodModel;
import com.sooncode.apipath.code.newtree.Node;
import com.sun.source.tree.StatementTree;
import com.sun.source.tree.Tree.Kind;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;

public class Variable implements MethodParser {
	@Override
	public String getTypeCode() {
		return "VARIABLE";
	}

	@Override
	public Node<TreeMethodModel> getMethodModel(StatementTree statementTree, ClassModel classModel, MethodModel methodModel, Node<TreeMethodModel> parentNode) {

		Node<TreeMethodModel> thisNode = null;

		Map<String, LocalValueModel> localValues = methodModel.getLocalValues();
		if (localValues == null) {
			localValues = new HashMap<>();
		}
		
		 

		JCVariableDecl jCVariableDecl = (JCVariableDecl) statementTree;
		String name = jCVariableDecl.getName().toString();
		String type = jCVariableDecl.getType().toString();
		
		LocalValueModel localValueModel = new LocalValueModel();
		localValueModel.setValueName(name);
		localValueModel.setType(type);
		localValues.put(localValueModel.getValueName(), localValueModel);
		methodModel.setLocalValues(localValues);
		
		JCExpression jcExpression = jCVariableDecl.getInitializer();
		Kind kind = jcExpression.getKind();

		if (kind.toString().equals("METHOD_INVOCATION")) {

			thisNode = MethodInvocation.addMethodTree(jcExpression, methodModel, classModel, parentNode);

		}else {
			thisNode = parentNode;
		}

		

		return thisNode;

	}

}

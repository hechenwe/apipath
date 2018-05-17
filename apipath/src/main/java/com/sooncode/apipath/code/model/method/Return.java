package com.sooncode.apipath.code.model.method;

import com.sooncode.apipath.code.model.ClassModel;
import com.sooncode.apipath.code.model.MethodModel;
import com.sooncode.apipath.code.model.TreeMethodModel;
import com.sooncode.apipath.code.newtree.Node;
import com.sun.source.tree.StatementTree;

public class Return implements MethodParser {
	@Override
	public String getTypeCode() {
		return "RETURN";
	}

	@Override
	public Node<TreeMethodModel> getMethodModel(StatementTree statementTree, ClassModel classModel, MethodModel methodModel, Node<TreeMethodModel> parentNode) {
		// TODO Auto-generated method stub
		return parentNode;
	}

	 
}

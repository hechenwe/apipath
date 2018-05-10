package com.sooncode.apipath.code.model;

import com.sun.source.tree.StatementTree;

public interface MethodParser {

	public String getTypeCode();
	
	public void getMethodModel(StatementTree statementTree,MethodModel methodModel);
}

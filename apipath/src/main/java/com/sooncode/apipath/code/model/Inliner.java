package com.sooncode.apipath.code.model;

import com.sun.source.tree.MethodTree;
import com.sun.source.util.TreeScanner;
 

public class Inliner  extends TreeScanner <MethodTree,MethodModel >  {

	@Override
	public MethodTree visitMethod(MethodTree arg0, MethodModel methodModel) {
		 
	    arg0.getParameters();
	    System.out.println("-------------------------------Inliner.visitMethod()");
		return super.visitMethod(arg0, methodModel);
	}

	 
	
}

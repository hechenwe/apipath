package com.sooncode.apipath.code.model;

import java.util.List;

import com.sooncode.apipath.code.tree.Tree;

public class MethodModel {

	private String methodName ;
	
	private List<AnnotationModel> annotations ;
	
	private List<LocalValueModel> localValues ;
	
	
	private Tree<MethodModel> invokeTree;


	public String getMethodName() {
		return methodName;
	}


	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}


	public List<LocalValueModel> getLocalValues() {
		return localValues;
	}


	public void setLocalValues(List<LocalValueModel> localValues) {
		this.localValues = localValues;
	}


	public Tree<MethodModel> getInvokeTree() {
		return invokeTree;
	}


	public void setInvokeTree(Tree<MethodModel> invokeTree) {
		this.invokeTree = invokeTree;
	}


	public List<AnnotationModel> getAnnotations() {
		return annotations;
	}


	public void setAnnotations(List<AnnotationModel> annotations) {
		this.annotations = annotations;
	}

 
	
}

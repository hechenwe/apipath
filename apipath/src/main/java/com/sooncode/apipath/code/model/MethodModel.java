package com.sooncode.apipath.code.model;

import java.util.Map;

import com.sooncode.apipath.code.newtree.Tree;

public class MethodModel {

	private String methodName ;
	
	private Map<String,AnnotationModel> annotations ;
	
	private Map<String,LocalValueModel> localValues ;
	
	
	private Tree<TreeMethodModel> invokeTree;


	public String getMethodName() {
		return methodName;
	}


	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}


 


	public Tree<TreeMethodModel> getInvokeTree() {
		return invokeTree;
	}


	public void setInvokeTree(Tree<TreeMethodModel> invokeTree) {
		this.invokeTree = invokeTree;
	}


	public Map<String, AnnotationModel> getAnnotations() {
		return annotations;
	}


	public void setAnnotations(Map<String, AnnotationModel> annotations) {
		this.annotations = annotations;
	}


	public Map<String, LocalValueModel> getLocalValues() {
		return localValues;
	}


	public void setLocalValues(Map<String, LocalValueModel> localValues) {
		this.localValues = localValues;
	}


 
 
	
}

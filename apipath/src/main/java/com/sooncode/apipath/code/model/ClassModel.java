package com.sooncode.apipath.code.model;

import java.util.List;

public class ClassModel {
	
	private String packageString ;
	
	private List<String> imports ;
	private String className;
	private List<FieldModel> fields;
	private List<MethodModel> methods;
	 
	public List<FieldModel> getFields() {
		return fields;
	}
	public void setFields(List<FieldModel> fields) {
		this.fields = fields;
	}
	public List<MethodModel> getMethods() {
		return methods;
	}
	public void setMethods(List<MethodModel> methods) {
		this.methods = methods;
	}
	public String getPackageString() {
		return packageString;
	}
	public void setPackageString(String packageString) {
		this.packageString = packageString;
	}
	public List<String> getImports() {
		return imports;
	}
	public void setImports(List<String> imports) {
		this.imports = imports;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
	

}

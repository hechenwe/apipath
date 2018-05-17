package com.sooncode.apipath.code.model;

import java.util.List;
import java.util.Map;

public class ClassModel {

	private String packageString;

	private List<String> imports;
	private String className;
	private Map<String, FieldModel> fields;
	private Map<String, MethodModel> methods;

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

	public Map<String, FieldModel> getFields() {
		return fields;
	}

	public void setFields(Map<String, FieldModel> fields) {
		this.fields = fields;
	}

	public Map<String, MethodModel> getMethods() {
		return methods;
	}

	public void setMethods(Map<String, MethodModel> methods) {
		this.methods = methods;
	}

}

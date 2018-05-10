package com.sooncode.apipath.node;

public class NodeModel {
 
	
	private String name;
	private String type ;
	private String explain ;
	private String key ;
	private String nextNodeNames ;
	private String apiUrl;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getNextNodeNames() {
		return nextNodeNames;
	}
	public void setNextNodeNames(String nextNodeNames) {
		this.nextNodeNames = nextNodeNames;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	 
	
	
}

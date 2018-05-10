package com.sooncode.apipath.node;

import java.util.ArrayList;
import java.util.List;

public class NodeModelManager {
	private static  List<NodeModel>  nodeModels = new ArrayList<>();

	public static void add(NodeModel nodeModel) {
		nodeModels.add(nodeModel);
	}

	public static List<NodeModel> getNodeModels() {
		return nodeModels;
	}
}

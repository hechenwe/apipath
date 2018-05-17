package com.sooncode.apipath.code.model.method;

import java.util.List;
import java.util.Map;

import com.sooncode.apipath.code.model.ClassModel;
import com.sooncode.apipath.code.model.FieldModel;
import com.sooncode.apipath.code.model.LocalValueModel;
import com.sooncode.apipath.code.model.MethodModel;
import com.sooncode.apipath.code.model.TreeMethodModel;
import com.sooncode.apipath.code.newtree.Tree;
import com.sooncode.apipath.code.newtree.GeneralNode;
import com.sooncode.apipath.code.newtree.Node;
import com.sun.source.tree.StatementTree;
import com.sun.source.tree.Tree.Kind;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCFieldAccess;
import com.sun.tools.javac.tree.JCTree.JCMethodInvocation;
import com.sun.tools.javac.tree.JCTree.JCStatement;
import com.sun.tools.javac.util.Name;

public class MethodInvocation {

	public static Node<TreeMethodModel> addMethodTree(JCExpression jcExpression, MethodModel methodModel, ClassModel classModel, Node<TreeMethodModel> parentNode) {

		Node<TreeMethodModel> thisNode = null;

		JCTree jcTree = jcExpression.getTree();
		JCMethodInvocation jCMethodInvocation = (JCMethodInvocation) jcTree;
		JCFieldAccess jCFieldAccess = (JCFieldAccess) jCMethodInvocation.getMethodSelect();
		Name methodName = jCFieldAccess.getIdentifier();

		String classOrObjectName = jCFieldAccess.getExpression().toString();
		String methodClassName = getMethodClassName(classOrObjectName, methodModel.getLocalValues(), classModel);

		if (methodClassName.contains(MethodParserCollect.PACKAGE_PREFIX)) {
			Tree<TreeMethodModel> tree = methodModel.getInvokeTree();
			TreeMethodModel treeMethod = new TreeMethodModel();
			treeMethod.setClassName(methodClassName);
			treeMethod.setMethodName(methodName.toString());
			Node<TreeMethodModel> node = new GeneralNode<>();
			node.setNodeData(treeMethod);
			thisNode = tree.addNode(parentNode, node);
			methodModel.setInvokeTree(tree);
		}

		return thisNode;

	}

	public static String getMethodClassName(String methodName, Map<String, LocalValueModel> localValues, ClassModel classModel) {

		String methodClassName = null;
		LocalValueModel lvm = localValues.get(methodName);
		if (lvm == null) {

			FieldModel fm = classModel.getFields().get(methodName);

			if (fm != null) {
				methodClassName = fm.getType();
			}

		} else {
			methodClassName = lvm.getType();
		}
		return methodClassName;
	}

	public static Node<TreeMethodModel> foreach(ClassModel classModel, MethodModel methodModel, List<JCStatement> list, Node<TreeMethodModel> parentNode) {
		Node<TreeMethodModel> treeNode = parentNode;

		for (StatementTree st : list) {
			Kind k = st.getKind();
			MethodParser methodParser = MethodParserCollect.getMethodParser(k.name());
			treeNode = methodParser.getMethodModel(st, classModel, methodModel, treeNode);

		}

		return treeNode;

	}
}

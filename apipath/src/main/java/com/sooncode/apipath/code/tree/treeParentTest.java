package com.sooncode.apipath.code.tree;

import java.util.List;

/**
 * Created by ietree
 * 2017/4/30
 */
public class treeParentTest {

    public static void main(String[] args) {

    	Tree<String> tp = new Tree<String>("root");
        TreeNode<String> root = tp.getRootNode();
        
        tp.addNode(root,"节点1");
        System.out.println("此树的深度：" + tp.deep());
        tp.addNode(root,"节点2");
        // 获取根节点的所有子节点
        List<TreeNode<String>> nodes = tp.getChildrenNodes(root);
        System.out.println("根节点的第一个子节点：" + nodes.get(0));
        // 为根节点的第一个子节点新增一个子节点
        tp.addNode(nodes.get(0),"节点3");
        System.out.println("此树的深度：" + tp.deep());
        
        Tree<String> treeFirst = new Tree<String>("first");
        

    }
}
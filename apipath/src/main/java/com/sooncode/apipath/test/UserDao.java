package com.sooncode.apipath.test;

import com.sooncode.apipath.node.Node;
import com.sooncode.apipath.node.NodeType;

public class UserDao {

	
	@Node(type = NodeType.DAO, explain = "插入数据库 user_table", key = "UserDao.saveUser")
	public void saveUser(String userName) {
	  
		 
		System.out.println("UserService.saveUser()");
	}
	
	
	@Node(type = NodeType.DAO, explain = "更新数据库 user_table", key = "UserDao.updateUser" )
	public void updateUser(String userName) {
		
		
		System.out.println("UserService.saveUser()");
	}
}

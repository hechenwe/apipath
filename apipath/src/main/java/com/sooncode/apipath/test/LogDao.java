package com.sooncode.apipath.test;

import com.sooncode.apipath.node.Node;
import com.sooncode.apipath.node.NodeType;

public class LogDao {

	
	@Node(type = NodeType.DAO, explain = "插入数据库 log_table", key = "LogDao.saveLog")
	public void saveLog(String message) {
	  
		//@Node( apiId = "userController/addUser", id = "1", type = "controller", explain = "添加用户接口", key = "UserController.class",nextIds = "2")
		//ApiNode api = new  ApiNode();
		System.out.println("UserService.saveUser()");
	}
	
	
	 
}

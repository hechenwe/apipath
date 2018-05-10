package com.sooncode.apipath.test;

import com.sooncode.apipath.node.NextNode;
import com.sooncode.apipath.node.Node;
import com.sooncode.apipath.node.NodeType;

public class UserService {

	private UserDao userDao ;
	
	@Node(   type = NodeType.SERVICE, explain = "添加用户信息", key = "UserService.saveUser")
	@NextNode(names= {"com.sooncode.apipath.UserDao.saveUser(String userName)"})
	public void saveUser(String userName) {
	  
		//@Node( apiId = "userController/addUser", id = "1", type = "controller", explain = "添加用户接口", key = "UserController.class",nextIds = "2")
		//ApiNode api = new  ApiNode();
		System.out.println("UserService.saveUser()");
		userDao.saveUser(userName);
	}
	
	
	@Node(   type = NodeType.SERVICE, explain = "修改用户信息", key = "UserService.updateUser" )
	@NextNode(names= {"com.sooncode.apipath.UserDao.updateUser(String userName)"})
	public void updateUser(String userName) {
		
		userDao.updateUser(userName);
		System.out.println("UserService.saveUser()");
	}
}

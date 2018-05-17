package com.sooncode.apipath.test;

import org.springframework.web.bind.annotation.RequestMapping;

import com.sooncode.apipath.node.Node;
import com.sooncode.apipath.node.NodeType;

@RequestMapping(value= {"userController",""})
public class UserController {
	
	private UserService userService = new UserService();
	
	private LogService logService= new LogService();

	@Node(  type = NodeType.CONTROLLER, explain = "添加用户接口", key = "UserController.addUser" )
	@RequestMapping("addUser")
	public String addUser(String userName) {
		
		int size = logService.saveLog(0.090,new User());
		
		logService.saveLog("");
		logService.saveLog(123);
		
		 
		if (userName != null) {

			String oldUserName = new String("oldUserName");

			if (userName.equals("oldUserName")) {
				
				logService.saveLog("");
				userService.saveUser(oldUserName);
				userService.saveUser(1234);
				return "ADD_USER_SUCCES";

			}else {
				userService.updateUser(oldUserName);
				return "UPDATE_USER_SUCCES";
			}

		}
		
		
		
		for(int i = 0 ; i <10 ; i ++) {
			userService.saveUser("");
			
			for(int j = 0 ; j<12 ;j++){
				userService.saveUser(1234);
			}
		}
		

		return null;
	}
	
	 
}

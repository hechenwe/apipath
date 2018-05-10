package com.sooncode.apipath;

import org.springframework.web.bind.annotation.RequestMapping;

import com.sooncode.apipath.node.NextNode;
import com.sooncode.apipath.node.Node;
import com.sooncode.apipath.node.NodeType;
import com.sooncode.apipath.test.LogService;
import com.sooncode.apipath.test.UserService;

@RequestMapping(value= {"userController",""})
public class UserController {
	
	private UserService userService = new UserService();
	
	private LogService logService= new LogService();

	@Node(  type = NodeType.CONTROLLER, explain = "添加用户接口", key = "UserController.addUser" )
	@NextNode(names = {"com.sooncode.apipath.UserService.saveUser(String userName)",
			           "com.sooncode.apipath.UserService.updateUser(String userName)"})
 
	@RequestMapping("addUser")
	public String addUser(String userName) {
		
		
		logService.saveLog("");
		logService.saveLog("");
		 
		if (userName != null) {

			String oldUserName = new String("oldUserName");

			if (userName.equals("oldUserName")) {
				
				logService.saveLog("");
				userService.saveUser(oldUserName);
				return "ADD_USER_SUCCES";

			}else {
				userService.updateUser(oldUserName);
				return "UPDATE_USER_SUCCES";
			}

		}

		return null;
	}
	
	 
}

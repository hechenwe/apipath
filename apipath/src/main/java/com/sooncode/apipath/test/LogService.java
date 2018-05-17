package com.sooncode.apipath.test;

import com.sooncode.apipath.node.NextNode;
import com.sooncode.apipath.node.Node;
import com.sooncode.apipath.node.NodeType;

public class LogService {

	private LogDao logDao ;
	
	@Node(   type = NodeType.SERVICE, explain = "添加日志信息", key = "LogService.saveLog")
	@NextNode(names= {"com.sooncode.apipath.UserDao.saveUser(String userName)"})
	public void saveLog(String userName) {
		logDao.saveLog("");;
	}
	
	public void saveLog( Integer age) {
		logDao.saveLog("");;
	}
	 
	
	public int saveLog( Double age,User user) {
		logDao.saveLog("");
		return 0;
	}
	
}

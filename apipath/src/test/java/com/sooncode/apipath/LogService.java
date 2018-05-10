package com.sooncode.apipath;

import com.sooncode.apipath.LogDao;
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
	 
}

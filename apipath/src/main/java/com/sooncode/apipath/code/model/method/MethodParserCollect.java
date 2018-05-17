package com.sooncode.apipath.code.model.method;

import java.util.HashMap;
import java.util.Map;

public class MethodParserCollect {
 
	public static String PACKAGE_PREFIX = "com.sooncode";
 
	private static Map<String, MethodParser> map = new HashMap<>();

	static {
		MethodParser expressionStatement = new ExpressionStatement();
		MethodParser ifMethodParser = new If();
		MethodParser returnMethodParser = new Return();
		MethodParser variable = new Variable();
		MethodParser enhancedForLoop = new EnhancedForLoop();
		MethodParser forLoop = new ForLoop();
		map.put(expressionStatement.getTypeCode(), expressionStatement);
		map.put(ifMethodParser.getTypeCode(), ifMethodParser);
		map.put(returnMethodParser.getTypeCode(), returnMethodParser);
		map.put(variable.getTypeCode(), variable);
		map.put(enhancedForLoop.getTypeCode(), enhancedForLoop);
		map.put(forLoop.getTypeCode(), forLoop);
	}

	public static MethodParser getMethodParser(String typeCode) {
		return map.get(typeCode);
	}

}

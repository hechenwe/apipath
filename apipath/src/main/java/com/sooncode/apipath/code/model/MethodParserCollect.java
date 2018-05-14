package com.sooncode.apipath.code.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.source.tree.StatementTree;
import com.sun.source.tree.Tree.Kind;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCExpressionStatement;
import com.sun.tools.javac.tree.JCTree.JCIf;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;

public class MethodParserCollect {

	private static Log logger = LogFactory.getLog(MethodParserCollect.class);

	static MethodParser EXPRESSION_STATEMENT = new MethodParser() {

		@Override
		public String getTypeCode() {
			return "EXPRESSION_STATEMENT";
		}

		@Override
		public void getMethodModel(StatementTree statementTree, MethodModel methodModel) {

			JCExpressionStatement jCExpressionStatement = (JCExpressionStatement) statementTree;

			JCExpression jcex = jCExpressionStatement.getExpression();
			jcex.toString();

		}
	};

	static MethodParser IF = new MethodParser() {

		@Override
		public String getTypeCode() {
			return "IF";
		}

		@Override
		public void getMethodModel(StatementTree statementTree, MethodModel methodModel) {
			JCIf jcif = (JCIf) statementTree;

			JCExpression jcex = jcif.getCondition();

			String expr = jcex.toString();

		}
	};

	static MethodParser VARIABLE = new MethodParser() {

		@Override
		public String getTypeCode() {
			return "VARIABLE";
		}

		@Override
		public void getMethodModel(StatementTree statementTree, MethodModel methodModel) {

			logger.info("[" + getTypeCode() + "]:" + statementTree.toString());

			JCVariableDecl jCVariableDecl = (JCVariableDecl) statementTree;
			String name = jCVariableDecl.getName().toString();

			String type = jCVariableDecl.getType().toString();

			JCExpression jcExpression = jCVariableDecl.getInitializer();

			Kind kind = jcExpression.getKind(); // METHOD_INVOCATION ; NEW_CLASS
			String str = jcExpression.toString();

		}
	};

	static MethodParser RETURN = new MethodParser() {

		@Override
		public String getTypeCode() {
			return "RETURN";
		}

		@Override
		public void getMethodModel(StatementTree statementTree, MethodModel methodModel) {

		}
	};
	
	
	static MethodParser ENHANCED_FOR_LOOP = new MethodParser() {
		
		@Override
		public String getTypeCode() {
			return "ENHANCED_FOR_LOOP";
		}
		
		@Override
		public void getMethodModel(StatementTree statementTree, MethodModel methodModel) {
			
		}
	};

	private static Map<String, MethodParser> map = new HashMap<>();

	static {
		Class<?> clas = MethodParserCollect.class;
		Field[] fields = clas.getDeclaredFields();
		for (Field f : fields) {
			try {
				MethodParser methodParser = (MethodParser) f.get(MethodParser.class);
				map.put(methodParser.getTypeCode(), methodParser);
			} catch (Exception e) {
				continue;
			}
		}
	}

	public static MethodParser getMethodParser(String typeCode) {
		return map.get(typeCode);
	}

}

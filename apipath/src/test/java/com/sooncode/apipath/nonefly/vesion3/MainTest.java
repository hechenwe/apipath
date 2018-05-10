package com.sooncode.apipath.nonefly.vesion3;

public class MainTest {
	public static void main(String[] args) {
		/*创建词法分析类*/
		TestLexer testLexer = new TestLexer("D:\\workspaces3\\apipath\\src\\test\\java\\com\\sooncode\\apipath\\UserController.java");
		//FileUtil.clearFile();//清空文件
		testLexer.analyse();
	}
}

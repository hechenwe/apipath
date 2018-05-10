package com.sooncode.apipath.code;

import java.util.List;

import com.sooncode.apipath.util.JavaFile;

public class ClassCode {

	public Class<?> getClass(String javaCode) {

		return null;
	}

	public String getClassAllName(String javaCode) {

		return null;

	}

	public static void main(String[] args) {
		List<String> javaCodes = JavaFile.readLine("D:\\workspaces3\\apipath\\src\\main\\java\\com\\sooncode\\apipath\\test\\UserController.java");
		for (String string : javaCodes) {
			System.out.println(string);

		}
	}
}

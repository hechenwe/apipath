package com.sooncode.apipath.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作
 * 
 * @author hechenwe@gmail.com
 *
 */
public class JavaFile {

	public static List<String> readLine(String filePath) {
		File file = new File(filePath);
		return readLine(file);
	}

	public static List<String> readLine(File file) {

		List<String> list = new ArrayList<String>();
		try {
			InputStreamReader isr;
			isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String temp = null;
			temp = br.readLine();
			while (temp != null) {
				if(!temp.toString().trim().equals("")) {
					list.add(temp.toString());
				}
				temp = br.readLine();
			}
			br.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}

	}

	/**
	 * 获取文件夹下的所有文件
	 * 
	 * @param fileDir
	 *            文件夹目录
	 * @return 文件集合
	 */
	public static List<File> readAllFile(String fileDir) {
		List<File> fileList = new ArrayList<File>();
		File[] files = new File(fileDir).listFiles();// 获取目录下的所有文件或文件夹

		for (File file : files) {
			if (file.isFile()) {
				fileList.add(file);
			} else if (file.isDirectory()) {
				fileList.addAll(readAllFile(file.getAbsolutePath()));
			}
		}

		return fileList;

	}

}

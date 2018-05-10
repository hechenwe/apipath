package com.sooncode.apipath.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作
 * 
 * @author hechenwe@gmail.com
 *
 */
public class FileUtil {
	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param sPath
	 * @return
	 */
	public boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	public static String readFile(String filePath) {
		File file = new File(filePath);
		return readFile(file);
	}

	public static String readFile(File file) {

		try {
			InputStreamReader isr;
			isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String temp = null;
			StringBuffer sb = new StringBuffer();
			temp = br.readLine();
			while (temp != null) {
				sb.append(temp + "\n");
				temp = br.readLine();
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 写文件
	 * 
	 * @param fileName
	 *            文件的全名(包含文件所在的路径和文件名)
	 * @param content
	 *            要写入的内容
	 * @param encoding
	 *            文件的编码格式
	 * @throws IOException
	 */
	public boolean writeFile(String fileName, String content, String encoding) {
		File file = new File(fileName);
		if (!file.exists()) { // 文件不存在
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} else {// 文件存在
			file.delete();
		}

		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(file, true);
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(content);
			fileOutputStream.write(stringBuffer.toString().getBytes(encoding));

			fileOutputStream.close();
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	public List<String> getFileNames(String path) {
		File file = new File("D:/eclipse");
		String test[];
		test = file.list();
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}

		return null;
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

	public static void main(String[] args) throws IOException {
		// FileUtil fu = new FileUtil();
		// boolean b = fu.writeFile("D:/text.txt", "大家好!hahah", "utf-8");
		// String string = fu.readFile("D:/text.txt");
		// System.out.println("FileUtil.main()" + string);

		// File file = new File("D:/eclipse");
		// String test[];
		// test = file.list();
		// for (int i = 0; i < test.length; i++) {
		// System.out.println(test[i]);
		// }

		List<File> list = readAllFile("D:\\workspaces\\soon_subassembly\\soon_subassembly");
		for (File file : list) {
			System.out.println(file.getName());
		}

	}
}

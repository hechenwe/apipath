package com.sooncode.apipath;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sooncode.apipath.node.NextNode;
import com.sooncode.apipath.node.Node;
import com.sooncode.apipath.node.NodeModel;
import com.sooncode.apipath.node.NodeModelManager;
import com.sooncode.apipath.util.AddApiNodesModel;
import com.sooncode.apipath.util.ApiNode;
import com.sooncode.apipath.util.HttpRequest;

import net.sf.json.JSONObject;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class PackageClassUtil {
	// private static final Log log = LogFactory.getLog(PackageClassUtil.class);
	/**
	 * 从包package中获取所有的Class
	 *
	 * @param pack
	 * @return
	 */
	public static Set<Class<?>> getClasses(String pack) throws Exception {

		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageName = pack;
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx).replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class") && !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(packageName.length() + 1, name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class.forName(packageName + '.' + className));
										} catch (ClassNotFoundException e) {
											e.printStackTrace();
											// log
											// .error("添加用户自定义视图类错误 找不到此类的.class文件");
										}
									}
								}
							}
						}
					} catch (IOException e) {
						// log.error("在扫描用户定义视图时从jar包获取文件出错");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 *
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			// log.warn("用户定义包名 " + packageName + " 下没有任何文件");
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					// 添加到集合中去
					classes.add(Class.forName(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					// log.error("添加用户自定义视图类错误 找不到此类的.class文件");
					e.printStackTrace();
				}
			}
		}
	}

	
	@Test
	public  void run()   {
		Set<Class<?>> clas;
		try {
			clas = getClasses("com.sooncode");
			
			for (Class<?> c : clas) {
				
				String className = c.getName();
				 
				Method[] methods = c.getDeclaredMethods();
				for (Method m : methods) {
					
					System.out.println(m.toString());
					String methodName =className + "."+ m.getName() + "(" ;
				 
					Parameter[] parameters = m.getParameters();
					
					String tempName = new String ();
					for (int i = 0 ; i < parameters.length ; i++) {
						
						Class <?> typeClass  = parameters[i].getType();
						String pname = parameters[i].getName();
						
						if( i != 0 ) {
							tempName = tempName + " , ";
						} 
						tempName  = tempName +  typeClass.getSimpleName()+ " " + pname ;
						
					}
					
					methodName = methodName + tempName + ")";
					
					System.out.println(methodName);
					
					RequestMapping classRM= c.getAnnotation(RequestMapping.class);
					RequestMapping methodRM= m.getAnnotation(RequestMapping.class);
					
					
					
					
					Node node = m.getAnnotation(Node.class);
					if (node != null) {
						NodeModel nodeModel = new NodeModel();
						nodeModel.setExplain(node.explain());
						nodeModel.setKey(node.key());
						nodeModel.setType(node.type().name());
						nodeModel.setName(methodName);
						NodeModelManager.add(nodeModel);
						
						NextNode nextNode = m.getAnnotation(NextNode.class);
						if(nextNode !=null ) {
						String[] names = 	nextNode.names();
						String nameses = new String ();
						for (int i = 0 ;i< names.length ; i++) {
							nameses = nameses + ((i != 0) ? ";":"") + names[i];
						}
						nodeModel.setNextNodeNames(nameses);
						}
						 
						if(methodRM!=null && classRM != null) {
							String[] classUrl = classRM.value();
							String [] methodUrl = methodRM.value();
							
							nodeModel.setApiUrl(classUrl[0]+"/"+methodUrl[0]);
							
							
						}

					}
				}

			}
			List<NodeModel> list =  NodeModelManager.getNodeModels();
			List<ApiNode> apiNodes = new ArrayList<>();
			String projectId = "00001";
			for (NodeModel nodeModel : list) {
				System.out.println(""+nodeModel);
				ApiNode an = new ApiNode();
				 
				an.setExplain(nodeModel.getExplain());
				an.setKey(nodeModel.getKey());
				an.setName(nodeModel.getName().replaceAll("\\s+"," "));//str.replaceAll("\\s+"," ")
				an.setNextNodeNames( nodeModel.getNextNodeNames()==null ? null :  nodeModel.getNextNodeNames().replaceAll("\\s+"," "));
				an.setProjectId(projectId);
				an.setType(nodeModel.getType());
				an.setApiUrl(nodeModel.getApiUrl());
				apiNodes.add(an);
			}
			
			
			String url = "http://127.0.0.1:9003/apiNodeController/addApiNodes";
			AddApiNodesModel aanm = new AddApiNodesModel();
			aanm.setProjectId(projectId);
			aanm.setApiNodes(apiNodes);
			JSONObject jo = JSONObject.fromObject(aanm);
			
			HttpRequest.postRequest(url, jo.toString());
			
			
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		String url = "http://127.0.0.1:9003/apiNodeController/getApiNodes";
		String str = "{\"projectId\":\"00001\",\"apiUrl\":\"http://127.0.0.1:8080/userController/addUser\"}";
		String data = HttpRequest.postRequest(url,str );
		System.out.println(data);
	}
	 
	

}
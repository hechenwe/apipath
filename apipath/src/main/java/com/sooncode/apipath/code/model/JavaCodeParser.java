package com.sooncode.apipath.code.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.lang.model.element.Name;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sooncode.apipath.code.model.method.MethodParser;
import com.sooncode.apipath.code.model.method.MethodParserCollect;
import com.sooncode.apipath.code.newtree.Node;
import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.BlockTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.ImportTree;
import com.sun.source.tree.MemberReferenceTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.ModifiersTree;
import com.sun.source.tree.ParameterizedTypeTree;
import com.sun.source.tree.ParenthesizedTree;
import com.sun.source.tree.StatementTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.Tree.Kind;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.parser.JavacParser;
import com.sun.tools.javac.parser.ParserFactory;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.util.Context;

public class JavaCodeParser {

	private static Log logger = LogFactory.getLog(JavaCodeParser.class);
	private ParserFactory factory;

	public JavaCodeParser() {
		factory = getParserFactory();
	}

	public ClassModel parseMethodDefs(String file) throws IOException {
		JCCompilationUnit unit = this.parse(file);
		ClassScanner scanner = new ClassScanner();
		return scanner.visitCompilationUnit(unit, new ClassModel());

	}

	private JCCompilationUnit parse(String file) throws IOException {
		JavacParser parser = factory.newParser(readFile(file), true, false, true);
		JCCompilationUnit jCCompilationUnit = parser.parseCompilationUnit();
		// JCExpression jce = parser.parseType();
		// JCStatement jCStatement = parser.parseStatement();
		// JCExpression jCExpression2=parser.parseExpression();
		return jCCompilationUnit;
	}

	private ParserFactory getParserFactory() {
		Context context = new Context();
		JavacFileManager.preRegister(context);
		ParserFactory factory = ParserFactory.instance(context);
		return factory;
	}

	private CharSequence readFile(String file) throws IOException {
		FileInputStream fin = new FileInputStream(file);
		FileChannel ch = fin.getChannel();
		ByteBuffer buffer = ch.map(MapMode.READ_ONLY, 0, ch.size());
		CharSequence charSequence = Charset.defaultCharset().decode(buffer);
		fin.close();
		return charSequence;
	}

	static class ClassScanner extends TreeScanner<ClassModel, ClassModel> {

		@Override
		public ClassModel visitMethod(MethodTree methodTree, ClassModel classModel) {

			MethodModel mm = new MethodModel();
			String methodName = methodTree.getName().toString();
			mm.setMethodName(methodName);
			BlockTree bt = methodTree.getBody();
			ModifiersTree modifiersTree = methodTree.getModifiers();
			List<? extends AnnotationTree> annotationTrees = modifiersTree.getAnnotations();
			Map<String, AnnotationModel> annotationModels = new HashMap<>();

			for (AnnotationTree an : annotationTrees) {
				String anString = an.toString();
				AnnotationModel am = new AnnotationModel();
				am.setAnnotationClass(anString);
				annotationModels.put(am.getAnnotationClass(), am);

			}

			mm.setAnnotations(annotationModels);
			
			com.sooncode.apipath.code.newtree.Tree<TreeMethodModel> mainTree = new com.sooncode.apipath.code.newtree.Tree<>();
			 
			List<? extends StatementTree> list = bt.getStatements();

 
			mm.setInvokeTree(mainTree);

			Node<TreeMethodModel> treeNode = null;
			for (StatementTree st : list) {
				Kind k = st.getKind();

				logger.info(k.name() + "  Class :" + st.getClass());
				MethodParser methodParser = MethodParserCollect.getMethodParser(k.name());
				treeNode = methodParser.getMethodModel(st, classModel, mm, treeNode);
				 
			}
			

			Map<String, MethodModel> methods = classModel.getMethods();
			if (methods == null) {
				methods = new HashMap<>();
			}
			methods.put(mm.getMethodName(), mm);
			classModel.setMethods(methods);
			return classModel;
		}

		@Override
		public ClassModel visitAnnotation(AnnotationTree annotationTree, ClassModel classModel) {

			Kind kind = annotationTree.getKind();
			kind.name();

			return classModel;
		}

		@Override
		public ClassModel visitCompilationUnit(CompilationUnitTree compilationUnitTree, ClassModel classModel) {

			ExpressionTree packageName = compilationUnitTree.getPackageName();

			classModel.setPackageString(packageName.toString());

			List<? extends ImportTree> imports = compilationUnitTree.getImports();
			List<String> list = new ArrayList<>();
			for (ImportTree importTree : imports) {
				String str = importTree.toString().split(";")[0];
				int index = str.indexOf("import ");
				str = str.substring(index + "import ".length(), str.length());
				list.add(str);

				// logger.info("[import]: [pos]="+importTree);
			}
			classModel.setImports(list);
			return super.visitCompilationUnit(compilationUnitTree, classModel);
		}

		@Override
		public ClassModel visitParameterizedType(ParameterizedTypeTree arg0, ClassModel arg1) {
			System.out.println("[visitParameterizedType]  :   " + arg0);
			return super.visitParameterizedType(arg0, arg1);
		}

		@Override
		public ClassModel visitParenthesized(ParenthesizedTree arg0, ClassModel arg1) {
			System.out.println("[visitParenthesized]  :   " + arg0);
			return super.visitParenthesized(arg0, arg1);
		}

		@Override
		public ClassModel visitMemberReference(MemberReferenceTree arg0, ClassModel arg1) {
			System.out.println("[visitMemberReference]  :   " + arg0);
			return super.visitMemberReference(arg0, arg1);
		}

		@Override
		public ClassModel visitMemberSelect(MemberSelectTree arg0, ClassModel arg1) {
			System.out.println("MemberSelect    :   " + arg0.toString());
			return super.visitMemberSelect(arg0, arg1);
		}

		@Override
		public ClassModel visitClass(ClassTree classTree, ClassModel classModel) {

			List<? extends Tree> tree = classTree.getMembers();
			Map<String, FieldModel> fields = new HashMap<>();
			for (Tree tre : tree) {
				Kind kind = tre.getKind();
				if ("VARIABLE".equals(kind.name())) {
					JCVariableDecl jcvd = (JCVariableDecl) tre;
					Name name = jcvd.getName();
					JCTree jct = jcvd.getType();
					FieldModel fm = new FieldModel();
					fm.setFieldName(name.toString());
					fm.setType(getAllClassName(classModel, jct.toString()));
					fields.put(fm.getFieldName(), fm);
				}
			}
			classModel.setFields(fields);

			Name name = classTree.getSimpleName();
			Kind kind = classTree.getKind();
			kind.name();
			classModel.setClassName(name.toString());

			return super.visitClass(classTree, classModel);
		}

	}

	private static String getAllClassName(ClassModel classModel, String className) {
		String allClassName = null;
		for (String string : classModel.getImports()) {
			if (string.lastIndexOf(className) != -1) {
				allClassName = string;
				break;
			}
		}

		if (allClassName == null) {
			allClassName = classModel.getPackageString() + "." + className;
		}

		return allClassName;

	}

	public static void main(String[] args) throws IOException {
		JavaCodeParser parser = new JavaCodeParser();

		String javaFile = "C:\\Users\\Administrator\\git\\apipath\\apipath\\src\\main\\java\\com\\sooncode\\apipath\\test\\UserController.java";

		ClassModel classModel = parser.parseMethodDefs(javaFile);

		Map<String, MethodModel> map = classModel.getMethods();

		for (Entry<String, MethodModel> en : map.entrySet()) {
			String methodName = en.getKey();
			MethodModel mm = en.getValue();

			com.sooncode.apipath.code.newtree.Tree<TreeMethodModel> tree = mm.getInvokeTree();

			logger.info(methodName + "  : " + tree);
		}

	}
}
package com.sooncode.apipath.code.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Name;

import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.BlockTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.ImportTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.ModifiersTree;
import com.sun.source.tree.StatementTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.Tree.Kind;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.parser.Parser;
import com.sun.tools.javac.parser.ParserFactory;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCExpressionStatement;
import com.sun.tools.javac.tree.JCTree.JCIf;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.util.Context;

public class JavaCodeParser {
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
		Parser parser = factory.newParser(readFile(file), true, false, true);
		return parser.parseCompilationUnit();
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
			
			BlockTree bt = methodTree.getBody();
			ModifiersTree   modifiersTree  = methodTree.getModifiers();
			List<? extends AnnotationTree> annotationTrees =  modifiersTree.getAnnotations();
			List<AnnotationModel> annotationModels = new ArrayList<>();
			
			for (AnnotationTree an : annotationTrees) {
				String anString = an.toString();
				AnnotationModel am = new AnnotationModel();
				am.setAnnotationClass(anString);
				annotationModels.add(am);
				
			}
			
			mm.setAnnotations(annotationModels);
			 

			List<? extends StatementTree> list = bt.getStatements();

			for (StatementTree st : list) {
				Kind k = st.getKind();
				System.out.println(k.name() + "  Class :"+ st.getClass());
				MethodParser methodParser =	MethodParserCollect.getMethodParser(k.name());
			    methodParser.getMethodModel(st,mm);
				 
			}
			
			
			
			
			List <MethodModel> methods = classModel.getMethods();
			if(methods == null) {
				methods = new ArrayList<>();
			}
			methods.add(mm);
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
			}
			classModel.setImports(list);
			return super.visitCompilationUnit(compilationUnitTree, classModel);
		}

		@Override
		public ClassModel visitClass(ClassTree classTree, ClassModel classModel) {

			List<? extends Tree> tree = classTree.getMembers();
			List<FieldModel> fields = new ArrayList<>();
			for (Tree tre : tree) {
				Kind kind = tre.getKind();
				if ("VARIABLE".equals(kind.name())) {
					JCVariableDecl jcvd = (JCVariableDecl) tre;
					Name name = jcvd.getName();
					JCTree jct = jcvd.getType();
					FieldModel fm = new FieldModel();
					fm.setFieldName(name.toString());
					fm.setType(getAllClassName(classModel.getImports(), jct.toString()));
					fields.add(fm);
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

	private static String getAllClassName(List<String> imports, String className) {

		for (String string : imports) {
			if (string.lastIndexOf(className) != -1) {
				return string;
			}
		}

		return null;

	}

	public static void main(String[] args) throws IOException {
		JavaCodeParser parser = new JavaCodeParser();
		String javaFile = "D:\\\\workspaces3\\\\zbank-account-front-v3.0.0\\\\zbank-account-web\\\\src\\\\main\\\\java\\\\com\\\\zbank\\\\account\\\\transfer\\\\biz\\\\impl\\\\TransferBizImpl.java";
		ClassModel classModel = parser.parseMethodDefs(javaFile);

		System.out.println(classModel.getPackageString() + classModel.getClassName());

		for (FieldModel field : classModel.getFields()) {
			System.out.println(field.getFieldName());

		}

	}
}
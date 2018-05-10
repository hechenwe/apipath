package com.sooncode.apipath.code;

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

import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.BlockTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.StatementTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.Tree.Kind;
import com.sun.source.tree.TypeParameterTree;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.parser.Parser;
import com.sun.tools.javac.parser.ParserFactory;
import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCExpressionStatement;
import com.sun.tools.javac.tree.JCTree.JCIf;
import com.sun.tools.javac.util.Context;

public class JDKParser {
	private ParserFactory factory;

	public JDKParser() {
		factory = getParserFactory();
	}

	public Map<String, String> parseMethodDefs(String file) throws IOException {
		JCCompilationUnit unit = this.parse(file);
		MethodScanner scanner = new MethodScanner();
		return scanner.visitCompilationUnit(unit, new HashMap<String, String>());

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

	// 扫描方法时，把方法名加入到一个list中
	static class MethodScanner extends TreeScanner<Map<String, String>, Map<String, String>> {
		@Override
		public Map<String, String> visitMethod(MethodTree node, Map<String, String> map) {
			BlockTree bt = node.getBody();
			Kind kind = bt.getKind();

			StatementTree statementTree;

			List<? extends StatementTree> list = bt.getStatements();

			for (StatementTree st : list) {

				Kind k = st.getKind();
				System.out.println("--- " + k + " : " + st.getClass());

				if (k.name().equals("EXPRESSION_STATEMENT")) {

					JCExpressionStatement jCExpressionStatement = (JCExpressionStatement) st;

					JCExpression jcex = jCExpressionStatement.getExpression();
					jcex.toString();
				}

				if (k.name().equals("IF")) {

					JCIf jcif = (JCIf) st;

					JCExpression jcex = jcif.getCondition();

					String expr = jcex.toString();

				}

			}

			return map;
		}

		@Override
		public Map<String, String> visitAnnotation(AnnotationTree annotationTree, Map<String, String> map) {

			Kind kind = annotationTree.getKind();
			kind.name();

			return map;
		}

		@Override
		public Map<String, String> visitCompilationUnit(CompilationUnitTree compilationUnitTree, Map<String, String> map) {
			ExpressionTree packageName = compilationUnitTree.getPackageName();
			map.put("PACKAGE", packageName.toString());
			return super.visitCompilationUnit(compilationUnitTree, map);
		}

		@Override
		public Map<String, String> visitClass(ClassTree classTree, Map<String, String> map) {

			List<? extends Tree> tree = classTree.getMembers();

			for (Tree tre : tree) {

				Kind kind = tre.getKind();
				System.out.println("[visitClass] " + kind.name() + " : " + tre.toString());
				if ("VARIABLE".equals(kind.name())) {
					map.put(kind.name(), tre.toString());
				}

			}

			Name name = classTree.getSimpleName();
			Kind kind = classTree.getKind();
			kind.name();
			map.put("CLASS", name.toString());

			return super.visitClass(classTree, map);
		}

	}

	public static void main(String[] args) throws IOException {
		JDKParser parser = new JDKParser();
		String javaFile = "D:\\\\workspaces3\\\\zbank-account-front-v3.0.0\\\\zbank-account-web\\\\src\\\\main\\\\java\\\\com\\\\zbank\\\\account\\\\transfer\\\\biz\\\\impl\\\\TransferBizImpl.java";
		Map<String, String> map = parser.parseMethodDefs(javaFile);

		for (Entry<String, String> en : map.entrySet()) {

			String key = en.getKey();
			String val = en.getValue();
			System.out.println(key + ":" + val);

		}
	}
}
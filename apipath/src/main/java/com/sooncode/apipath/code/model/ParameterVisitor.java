package com.sooncode.apipath.code.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.source.tree.AnnotatedTypeTree;
import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.ArrayAccessTree;
import com.sun.source.tree.ArrayTypeTree;
import com.sun.source.tree.AssertTree;
import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.BlockTree;
import com.sun.source.tree.BreakTree;
import com.sun.source.tree.CaseTree;
import com.sun.source.tree.CatchTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.CompoundAssignmentTree;
import com.sun.source.tree.ConditionalExpressionTree;
import com.sun.source.tree.ContinueTree;
import com.sun.source.tree.DoWhileLoopTree;
import com.sun.source.tree.EmptyStatementTree;
import com.sun.source.tree.EnhancedForLoopTree;
import com.sun.source.tree.ErroneousTree;
import com.sun.source.tree.ExpressionStatementTree;
import com.sun.source.tree.ForLoopTree;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.IfTree;
import com.sun.source.tree.ImportTree;
import com.sun.source.tree.InstanceOfTree;
import com.sun.source.tree.IntersectionTypeTree;
import com.sun.source.tree.LabeledStatementTree;
import com.sun.source.tree.LambdaExpressionTree;
import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.MemberReferenceTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.ModifiersTree;
import com.sun.source.tree.NewArrayTree;
import com.sun.source.tree.NewClassTree;
import com.sun.source.tree.ParameterizedTypeTree;
import com.sun.source.tree.ParenthesizedTree;
import com.sun.source.tree.PrimitiveTypeTree;
import com.sun.source.tree.ReturnTree;
import com.sun.source.tree.SwitchTree;
import com.sun.source.tree.SynchronizedTree;
import com.sun.source.tree.ThrowTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.TryTree;
import com.sun.source.tree.TypeCastTree;
import com.sun.source.tree.TypeParameterTree;
import com.sun.source.tree.UnaryTree;
import com.sun.source.tree.UnionTypeTree;
import com.sun.source.tree.VariableTree;
import com.sun.source.tree.WhileLoopTree;
import com.sun.source.tree.WildcardTree;
import com.sun.source.util.TreeScanner;

public class ParameterVisitor extends TreeScanner {
	private static Log logger = LogFactory.getLog(ParameterVisitor.class);
	@Override
	public Object reduce(Object arg0, Object arg1) {
		logger.info( arg0 );
		return super.reduce(arg0, arg1);
	}

	@Override
	public Object scan(Iterable arg0, Object arg1) {
		logger.info( arg0 );
		return super.scan(arg0, arg1);
	}

	@Override
	public Object scan(Tree arg0, Object arg1) {
		logger.info( arg0 );
		return super.scan(arg0, arg1);
	}

	@Override
	public Object visitAnnotatedType(AnnotatedTypeTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitAnnotatedType(arg0, arg1);
	}

	@Override
	public Object visitAnnotation(AnnotationTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitAnnotation(arg0, arg1);
	}

	@Override
	public Object visitArrayAccess(ArrayAccessTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitArrayAccess(arg0, arg1);
	}

	@Override
	public Object visitArrayType(ArrayTypeTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitArrayType(arg0, arg1);
	}

	@Override
	public Object visitAssert(AssertTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitAssert(arg0, arg1);
	}

	@Override
	public Object visitAssignment(AssignmentTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitAssignment(arg0, arg1);
	}

	@Override
	public Object visitBinary(BinaryTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitBinary(arg0, arg1);
	}

	@Override
	public Object visitBlock(BlockTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitBlock(arg0, arg1);
	}

	@Override
	public Object visitBreak(BreakTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitBreak(arg0, arg1);
	}

	@Override
	public Object visitCase(CaseTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitCase(arg0, arg1);
	}

	@Override
	public Object visitCatch(CatchTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitCatch(arg0, arg1);
	}

	@Override
	public Object visitClass(ClassTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitClass(arg0, arg1);
	}

	@Override
	public Object visitCompilationUnit(CompilationUnitTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitCompilationUnit(arg0, arg1);
	}

	@Override
	public Object visitCompoundAssignment(CompoundAssignmentTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitCompoundAssignment(arg0, arg1);
	}

	@Override
	public Object visitConditionalExpression(ConditionalExpressionTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitConditionalExpression(arg0, arg1);
	}

	@Override
	public Object visitContinue(ContinueTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitContinue(arg0, arg1);
	}

	@Override
	public Object visitDoWhileLoop(DoWhileLoopTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitDoWhileLoop(arg0, arg1);
	}

	@Override
	public Object visitEmptyStatement(EmptyStatementTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitEmptyStatement(arg0, arg1);
	}

	@Override
	public Object visitEnhancedForLoop(EnhancedForLoopTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitEnhancedForLoop(arg0, arg1);
	}

	@Override
	public Object visitErroneous(ErroneousTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitErroneous(arg0, arg1);
	}

	@Override
	public Object visitExpressionStatement(ExpressionStatementTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitExpressionStatement(arg0, arg1);
	}

	@Override
	public Object visitForLoop(ForLoopTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitForLoop(arg0, arg1);
	}

	@Override
	public Object visitIdentifier(IdentifierTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitIdentifier(arg0, arg1);
	}

	@Override
	public Object visitIf(IfTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitIf(arg0, arg1);
	}

	@Override
	public Object visitImport(ImportTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitImport(arg0, arg1);
	}

	@Override
	public Object visitInstanceOf(InstanceOfTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitInstanceOf(arg0, arg1);
	}

	@Override
	public Object visitIntersectionType(IntersectionTypeTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitIntersectionType(arg0, arg1);
	}

	@Override
	public Object visitLabeledStatement(LabeledStatementTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitLabeledStatement(arg0, arg1);
	}

	@Override
	public Object visitLambdaExpression(LambdaExpressionTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitLambdaExpression(arg0, arg1);
	}

	@Override
	public Object visitLiteral(LiteralTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitLiteral(arg0, arg1);
	}

	@Override
	public Object visitMemberReference(MemberReferenceTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitMemberReference(arg0, arg1);
	}

	@Override
	public Object visitMemberSelect(MemberSelectTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitMemberSelect(arg0, arg1);
	}

	@Override
	public Object visitMethod(MethodTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitMethod(arg0, arg1);
	}

	@Override
	public Object visitMethodInvocation(MethodInvocationTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitMethodInvocation(arg0, arg1);
	}

	@Override
	public Object visitModifiers(ModifiersTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitModifiers(arg0, arg1);
	}

	@Override
	public Object visitNewArray(NewArrayTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitNewArray(arg0, arg1);
	}

	@Override
	public Object visitNewClass(NewClassTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitNewClass(arg0, arg1);
	}

	@Override
	public Object visitOther(Tree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitOther(arg0, arg1);
	}

	@Override
	public Object visitParenthesized(ParenthesizedTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitParenthesized(arg0, arg1);
	}

	@Override
	public Object visitPrimitiveType(PrimitiveTypeTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitPrimitiveType(arg0, arg1);
	}

	@Override
	public Object visitReturn(ReturnTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitReturn(arg0, arg1);
	}

	@Override
	public Object visitSwitch(SwitchTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitSwitch(arg0, arg1);
	}

	@Override
	public Object visitSynchronized(SynchronizedTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitSynchronized(arg0, arg1);
	}

	@Override
	public Object visitThrow(ThrowTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitThrow(arg0, arg1);
	}

	@Override
	public Object visitTry(TryTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitTry(arg0, arg1);
	}

	@Override
	public Object visitTypeCast(TypeCastTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitTypeCast(arg0, arg1);
	}

	@Override
	public Object visitTypeParameter(TypeParameterTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitTypeParameter(arg0, arg1);
	}

	@Override
	public Object visitUnary(UnaryTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitUnary(arg0, arg1);
	}

	@Override
	public Object visitUnionType(UnionTypeTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitUnionType(arg0, arg1);
	}

	@Override
	public Object visitVariable(VariableTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitVariable(arg0, arg1);
	}

	@Override
	public Object visitWhileLoop(WhileLoopTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitWhileLoop(arg0, arg1);
	}

	@Override
	public Object visitWildcard(WildcardTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitWildcard(arg0, arg1);
	}

	@Override
	public Object visitParameterizedType(ParameterizedTypeTree arg0, Object arg1) {
		logger.info( arg0 );
		return super.visitParameterizedType(arg0, arg1);
	}

}

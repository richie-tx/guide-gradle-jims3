/*
 * Created on Dec 14, 2005
 *
 */
package mojo.tools.code;

/**
 * @author eamundson
 *
 */
public interface IElementVisitor
{
	void visit(Attribute anAttribute);
	void visit(Block aBlock);
	void visit(BlockComment aComment);
	void visit(Comment aComment);
	void visit(CompilationUnit aUnit);
	void visit(Expression anExpression);
	void visit(IfStatement anIfStatement);
    void visit(ImportDeclaration anImport);
	void visit(Initializer anInit);
	void visit(Interface anInterface);
	void visit(JavaComment aJavaComment);
	void visit(LineComment aLineComment);
	void visit(Method aMethod);
	void visit(Class aClass);
	void visit(Parameter aParameter);
	void visit(Statement aStatement);
	void visit(ThrowStatement aThrowStatement);
	void visit(Type aType);
	void visit(VariableDeclarationStatement aVariableStatement);
}

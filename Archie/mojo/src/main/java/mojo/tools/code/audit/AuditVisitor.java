/*
 * Created on Jun 23, 2006
 *
 */
package mojo.tools.code.audit;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mojo.tools.code.AbstractStatement;
import mojo.tools.code.Attribute;
import mojo.tools.code.Block;
import mojo.tools.code.BlockComment;
import mojo.tools.code.Class;
import mojo.tools.code.CodeElement;
import mojo.tools.code.Comment;
import mojo.tools.code.CompilationUnit;
import mojo.tools.code.Expression;
import mojo.tools.code.IElementVisitor;
import mojo.tools.code.IfStatement;
import mojo.tools.code.ImportDeclaration;
import mojo.tools.code.Initializer;
import mojo.tools.code.Interface;
import mojo.tools.code.JavaComment;
import mojo.tools.code.LineComment;
import mojo.tools.code.Method;
import mojo.tools.code.Parameter;
import mojo.tools.code.Statement;
import mojo.tools.code.ThrowStatement;
import mojo.tools.code.Type;
import mojo.tools.code.VariableDeclarationStatement;

/**
 * @author Jim Fisher
 *
 */
public class AuditVisitor implements IElementVisitor
{
    private Map checkLookup;

    private CompilationUnit compUnit;

    private AuditResult result;

    public AuditVisitor(Map aCheckLookup)
    {
        this.result = new AuditResult();
        this.checkLookup = aCheckLookup;
    }

    public void addError(AuditError anError)
    {
        this.result.addError(anError);
    }

    /**
     * @return Returns the compUnit.
     */
    public CompilationUnit getCompUnit()
    {
        return compUnit;
    }

    public AuditResult getResult()
    {
        return result;
    }

    private void processChecks(CodeElement aCodeElement, String aCodeElementType)
    {
        if (this.checkLookup != null)
        {
            List myChecks = (List) this.checkLookup.get(aCodeElementType);
            if (myChecks != null)
            {
                Iterator i = myChecks.iterator();
                while (i.hasNext())
                {
                    Check check = (Check) i.next();
                    check.auditComponent(this, aCodeElement);
                }
            }
        }
    }

    public void visit(Attribute anAttribute)
    {
        this.processChecks(anAttribute, CodeElementTypes.ATTRIBUTE);
    }

    public void visit(Block aBlock)
    {
        this.processChecks(aBlock, CodeElementTypes.BLOCK);

        List statements = aBlock.getStatements();

        Iterator i = statements.iterator();
        while (i.hasNext())
        {
            AbstractStatement s = (AbstractStatement) i.next();
            s.accept(this);
        }
    }

    public void visit(BlockComment aComment)
    {
        throw new UnsupportedOperationException("BlockComment visit not supported");
    }

    public void visit(Class aClass)
    {
        this.processChecks(aClass, CodeElementTypes.CLASS);
        this.processChecks(aClass, CodeElementTypes.TYPE);        
        
        Iterator a = aClass.getAttributes();
        while (a.hasNext())
        {
            Attribute attribute = (Attribute) a.next();
            attribute.accept(this);
        }

        List methods = aClass.getMethods();
        int methodLen = methods.size();
        for(int i=0;i<methodLen;i++)
        {
            Method method = (Method) methods.get(i);
            method.accept(this);
        }
    }

    public void visit(Comment aComment)
    {
        throw new UnsupportedOperationException("Comment visit not supported");
    }

    public void visit(CompilationUnit aUnit)
    {
        this.compUnit = aUnit;

        this.result.setFileName(aUnit.getFile().getAbsolutePath());

        this.processChecks(aUnit, CodeElementTypes.COMPILATION_UNIT);
        
        Iterator i = aUnit.getImports();
        while(i.hasNext())
        {
            ImportDeclaration importDecl = (ImportDeclaration) i.next();
            this.processChecks(importDecl, CodeElementTypes.IMPORT_DECLARATION);
        }

        Type mainType = aUnit.getMainType();
        this.result.setPackageName(mainType.getPackage().getName());
        this.result.setComponentName(mainType.getName());
        mainType.accept(this);
    }

    public void visit(Expression anExpression)
    {
        this.processChecks(anExpression, CodeElementTypes.EXPRESSION_STATEMENT);
    }

    public void visit(IfStatement aStatement)
    {
        this.processChecks(aStatement, CodeElementTypes.IF_STATEMENT);

        Expression expression = aStatement.getExpression();
        if(expression != null)
        {
        	expression.accept(this);
        }
        
        AbstractStatement thenStatement = aStatement.getThenStatement();
        if (thenStatement != null)
        {
            thenStatement.accept(this);
        }

        AbstractStatement elseStatement = aStatement.getElseStatement();
        if (elseStatement != null)
        {
            elseStatement.accept(this);
        }
    }

    /* (non-Javadoc)
     * @see mojo.tools.code.IElementVisitor#visit(mojo.tools.code.ImportDeclaration)
     */
    public void visit(ImportDeclaration anImport)
    {
        this.processChecks(anImport, CodeElementTypes.IMPORT_DECLARATION);
    }

    public void visit(Initializer anInit)
    {
        throw new UnsupportedOperationException("Initializer visit not supported");
    }

    public void visit(Interface anInterface)
    {
        this.processChecks(anInterface, CodeElementTypes.INTERFACE);
        this.processChecks(anInterface, CodeElementTypes.TYPE);

        List methods = anInterface.getMethods();
        int methodLen = methods.size();
        for(int i=0;i<methodLen;i++)
        {
            Method method = (Method) methods.get(i);
            method.accept(this);
        }
    }

    public void visit(JavaComment aJavaComment)
    {
        throw new UnsupportedOperationException("JavaComment visit not supported");
    }

    public void visit(LineComment aLineComment)
    {
        throw new UnsupportedOperationException("LineComment visit not supported");
    }

    public void visit(Method aMethod)
    {
        this.processChecks(aMethod, CodeElementTypes.METHOD);

        Iterator i = aMethod.getParameters();
        while (i.hasNext())
        {
            Parameter param = (Parameter) i.next();
            param.accept(this);
        }

        Block block = aMethod.getBlock();
        if (block != null)
        {
            block.accept(this);
        }
    }

    public void visit(Parameter aParameter)
    {
        this.processChecks(aParameter, CodeElementTypes.PARAMETER);
    }

    public void visit(Statement aStatement)
    {
        this.processChecks(aStatement, CodeElementTypes.STATEMENT);
    }

    public void visit(ThrowStatement aThrowStatement)
    {
        this.processChecks(aThrowStatement, CodeElementTypes.THROW_STATEMENT);
    }

    public void visit(Type aType)
    {
        // TODO Will this ever execute, since Type is abstract?
    }

    public void visit(VariableDeclarationStatement aVariableStatement)
    {
        this.processChecks(aVariableStatement, CodeElementTypes.VARIABLE_DECLARATION_STATEMENT);
    }
}

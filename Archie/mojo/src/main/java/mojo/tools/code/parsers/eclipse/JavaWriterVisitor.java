/*
 * Created on Dec 14, 2005
 *
 */
package mojo.tools.code.parsers.eclipse;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import mojo.tools.code.Attribute;
import mojo.tools.code.Block;
import mojo.tools.code.BlockComment;
import mojo.tools.code.Class;
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
import mojo.tools.code.KeyWord;
import mojo.tools.code.CodeElement;
import mojo.tools.code.VariableDeclarationStatement;

/**
 * @author eamundson
 *  
 */
public class JavaWriterVisitor implements IElementVisitor
{
    private CompilationUnit compilationUnit;

    protected StringBuffer file = new StringBuffer("");

    /**
     * @return
     */
    public CompilationUnit getCompilationUnit()
    {
        return compilationUnit;
    }

    protected String indent(String aBlock)
    {
        StringBuffer file = new StringBuffer();
        StringTokenizer lLines = new StringTokenizer(aBlock, KeyWord.NEWLINE);
        while (lLines.hasMoreTokens())
        {
            String line = lLines.nextToken();
            if (line.trim().length() > 0)
            {
                file.append(KeyWord.INDENTION);
                file.append(line);
                file.append(KeyWord.NEWLINE);
            }
        }
        return file.toString();
    }

    public String toString()
    {
        return file.toString();
    }

    public void visit()
    {
    }

    public void visit(Attribute attribute)
    {
        if (attribute.getComment() != null)
        {
            attribute.getComment().accept(this);
        }

        if (attribute.isFinal())
        {
            file.append(KeyWord.FINAL);
        }

        if (attribute.isStatic())
        {
            file.append(KeyWord.STATIC);
        }

        if (attribute.getScope() != null)
        {
            file.append(attribute.getScope());
            file.append(KeyWord.SPACE);
        }

        file.append(attribute.getType());
        file.append(KeyWord.SPACE);

        file.append(attribute.getName());

        if (attribute.getInitialValue() != null)
        {
            file.append(KeyWord.SPACE);
            file.append(KeyWord.EQUALS);

            file.append(KeyWord.SPACE);
            file.append(attribute.getInitialValue());
        }

        file.append(KeyWord.SEMICOLON);
        file.append(KeyWord.NEWLINE);

    }

    public void visit(Block codeBlock)
    {

    }

    public void visit(BlockComment comment)
    {
        file.append(KeyWord.BLOCKCOMMENT_OPEN);
        file.append(KeyWord.NEWLINE);

        file.append(comment.getComment());
        file.append(KeyWord.NEWLINE);

        file.append(KeyWord.BLOCKCOMMENT_CLOSE);
        file.append(KeyWord.NEWLINE);
    }

    public void visit(Class aClass)
    {
        if (aClass.getComment() != null)
        {
            aClass.getComment().accept(this);
        }
        if (aClass.isAbstract())
        {
            file.append(KeyWord.ABSTRACT);
        }
        if (aClass.isFinal())
        {
            file.append(KeyWord.FINAL);
        }
        if (aClass.isStatic())
        {
            file.append(KeyWord.STATIC);
        }
        if (aClass.getScope() != null)
        {
            file.append(aClass.getScope()).append(KeyWord.SPACE);
        }

        file.append(KeyWord.CLASS);
        file.append(aClass.getName());

        if (aClass.getExtendsClass() != null)
        {
            file.append(KeyWord.EXTENDS);
            file.append(aClass.getExtendsClass());
        }
        if (aClass.getImplements().hasNext())
        {
            file.append(KeyWord.IMPLEMENTS);
            for (Iterator i = aClass.getImplements(); i.hasNext();)
            {
                file.append(i.next().toString());
                if (i.hasNext())
                {
                    file.append(KeyWord.COMMA);
                }
            }
        }
        file.append(KeyWord.BLOCK_OPEN);
        file.append(KeyWord.NEWLINE);

        for (Iterator i = aClass.getAttributes(); i.hasNext();)
        {
            Attribute lAttr = (Attribute) i.next();
            lAttr.accept(this);
        }

        List methods = aClass.getMethods();
        int methodLen = methods.size();
        for(int i=0;i<methodLen;i++)
        {
            Method method = (Method) methods.get(i);
            method.accept(this);
        }

        for (Iterator i = aClass.getInnerTypes(); i.hasNext();)
        {
            Type lType = (Type) i.next();
            lType.accept(this);
        }

        if (aClass.getInitializer() != null)
        {
            aClass.getInitializer().accept(this);
        }

        file.append(KeyWord.BLOCK_CLOSE);
        file.append(KeyWord.NEWLINE);

    }

    public void visit(Comment comment)
    {

    }

    public void visit(CompilationUnit unit)
    {
        if (unit.getPackageName() != null)
        {
            file.append(unit.getPackage());
        }
        file.append(KeyWord.NEWLINE);
        for (Iterator i = unit.getImports(); i.hasNext();)
        {
            file.append(KeyWord.IMPORT);
            file.append(i.next().toString());
            file.append(KeyWord.SEMICOLON);
            file.append(KeyWord.NEWLINE);
        }
        file.append(KeyWord.NEWLINE);

        compilationUnit = unit;
        for (Iterator i = unit.getTypes(); i.hasNext();)
        {
            Type aType = (Type) i.next();
            aType.accept(this);
        }

    }

    public void visit(Expression anExpression)
    {
    }

    public void visit(IfStatement anIfStatement)
    {
    }

    /* (non-Javadoc)
     * @see mojo.tools.code.IElementVisitor#visit(mojo.tools.code.ImportDeclaration)
     */
    public void visit(ImportDeclaration anImport)
    {
        // TODO Auto-generated method stub

    }

    public void visit(Initializer init)
    {
        if (init.getBody() != null)
        {
            file.append(KeyWord.NEWLINE).append(init.getBody()).append(KeyWord.NEWLINE);
        }

    }

    public void visit(Interface anInterface)
    {
        if (anInterface.isFinal())
        {
            file.append(KeyWord.FINAL);
        }

        if (anInterface.getScope() != null)
        {
            file.append(anInterface.getScope()).append(KeyWord.SPACE);
        }

        file.append(KeyWord.INTERFACE).append(anInterface.getName());

        if (anInterface.getExtends().hasNext())
        {
            file.append(KeyWord.EXTENDS);
            for (Iterator i = anInterface.getExtends(); i.hasNext();)
            {
                file.append(i.next().toString());
                if (i.hasNext())
                {
                    file.append(KeyWord.COMMA);
                }
            }
        }
        file.append(KeyWord.BLOCK_OPEN).append(KeyWord.NEWLINE);

        for (Iterator i = anInterface.getAttributes(); i.hasNext();)
        {
            Attribute lAttr = (Attribute) i.next();
            lAttr.accept(this);
        }

        List methods = anInterface.getMethods();
        int methodLen = methods.size();
        for(int i=0;i<methodLen;i++)
        {
            Method method = (Method) methods.get(i);
            method.accept(this);
        }

        for (Iterator i = anInterface.getInnerTypes(); i.hasNext();)
        {
            Class lClass = (Class) i.next();
            lClass.accept(this);
        }

        file.append(KeyWord.BLOCK_CLOSE);
        file.append(KeyWord.NEWLINE);

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.tools.code.IElementVisitor#visit(mojo.tools.code.JavaComment)
     */
    public void visit(JavaComment javaComment)
    {
        // TODO Auto-generated method stub
        file.append(KeyWord.JAVADOCCOMMENT_OPEN).append(KeyWord.NEWLINE);
        StringTokenizer lTokens = new StringTokenizer(javaComment.getComment(), KeyWord.NEWLINE);
        while (lTokens.hasMoreTokens())
        {
            String lLine = lTokens.nextToken();
            file.append(KeyWord.ASTERIX);
            file.append(KeyWord.SPACE);
            file.append(lLine);
            file.append(KeyWord.NEWLINE);
        }
        CodeElement lParent = javaComment.getParent();
        if (lParent != null)
        {
            for (Iterator i = lParent.getProperties(); i.hasNext();)
            {
                String lKey = (String) i.next();
                for (Iterator j = lParent.getProperties(lKey); j.hasNext();)
                {
                    file.append(KeyWord.ASTERIX);
                    file.append(KeyWord.SPACE);
                    file.append(KeyWord.AT);
                    file.append(lKey);
                    file.append(KeyWord.SPACE);
                    file.append((String) j.next());
                    file.append(KeyWord.NEWLINE);
                }
            }
        }
        file.append(KeyWord.JAVADOCCOMMENT_CLOSE);
        file.append(KeyWord.NEWLINE);

    }

    public void visit(LineComment lineComment)
    {
        file.append(KeyWord.LINECOMMENT_OPEN).append(lineComment.getComment()).append(KeyWord.NEWLINE);
    }

    public void visit(Method aMethod)
    {
        if (aMethod.getComment() != null)
        {
            aMethod.getComment().accept(this);
        }
        if (aMethod.isSynchronized())
        {
            file.append(KeyWord.SYNCHRONIZED);
        }
        if (aMethod.isFinal())
        {
            file.append(KeyWord.FINAL);
        }
        if (aMethod.isAbstract())
        {
            file.append(KeyWord.ABSTRACT);
        }
        if (aMethod.isStatic())
        {
            file.append(KeyWord.STATIC);
        }
        if (aMethod.isNative())
        {
            file.append(KeyWord.NATIVE);
        }
        if (aMethod.getScope() != null)
        {
            file.append(aMethod.getScope());
            file.append(KeyWord.SPACE);
        }

        file.append(aMethod.getReturnType());
        file.append(KeyWord.SPACE);
        file.append(aMethod.getName());
        file.append(KeyWord.OPEN_PAREN);

        for (Iterator i = aMethod.getParameters(); i.hasNext();)
        {
            Parameter lParam = (Parameter) i.next();
            lParam.accept(this);
            if (i.hasNext())
            {
                file.append(KeyWord.COMMA);
            }
        }
        file.append(KeyWord.CLOSE_PAREN);

        Iterator j = aMethod.getThrows();
        if (j.hasNext())
        {
            file.append(KeyWord.THROWS);
            while (j.hasNext())
            {
                String throwsClause = (String) j.next();
                file.append(throwsClause);
                if (j.hasNext())
                {
                    file.append(KeyWord.COMMA);
                }
            }
        }
        if (aMethod.getParent() instanceof Interface || aMethod.isAbstract() || aMethod.isNative())
        {
            file.append(KeyWord.SEMICOLON).append(KeyWord.NEWLINE);
        }
        else
        {
            file.append(KeyWord.BLOCK_OPEN).append(KeyWord.NEWLINE);
            file.append(indent(aMethod.getBody()));
            file.append(KeyWord.BLOCK_CLOSE).append(KeyWord.NEWLINE);
        }

    }

    public void visit(Parameter parameter)
    {
        if (parameter.isFinal())
        {
            file.append("final ");
        }
        file.append(parameter.getType()).append(KeyWord.SPACE).append(parameter.getName());

    }

    public void visit(Statement aStatement)
    {
    }

    public void visit(ThrowStatement aThrowStatement)
    {
    }

    public void visit(Type aType)
    {

    }    

    public void visit(VariableDeclarationStatement aVariableStatement)
    {
        
    }
}

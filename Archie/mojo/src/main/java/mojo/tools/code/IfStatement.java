/*
 * Created on May 24, 2006
 *
 */
package mojo.tools.code;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jim Fisher
 *  
 */
public class IfStatement extends AbstractStatement
{
    private List dispatchStatements;

    private AbstractStatement elseStatement;

    private Expression expression;

    private AbstractStatement thenStatement;

    public IfStatement()
    {
        this.dispatchStatements = new ArrayList();
    }

    public void accept(IElementVisitor visitor)
    {
        visitor.visit(this);
    }

    /**
     * @return Returns the elseStatement.
     */
    public AbstractStatement getElseStatement()
    {
        return elseStatement;
    }

    /**
     * @return Returns the expression.
     */
    public Expression getExpression()
    {
        return expression;
    }

    /**
     * @return Returns the thenStatement.
     */
    public AbstractStatement getThenStatement()
    {
        return thenStatement;
    }

    /**
     * @param elseStatement The elseStatement to set.
     */
    public void setElseStatement(AbstractStatement anElseStatement)
    {
        this.elseStatement = anElseStatement;
    }

    /**
     * @param expression The expression to set.
     */
    public void setExpression(Expression expression)
    {
        this.expression = expression;
    }

    /**
     * @param thenStatement The thenStatement to set.
     */
    public void setThenStatement(AbstractStatement thenStatement)
    {
        this.thenStatement = thenStatement;
    }   
}

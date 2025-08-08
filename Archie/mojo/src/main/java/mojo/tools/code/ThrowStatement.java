/*
 * Created on Jun 26, 2006
 *
 */
package mojo.tools.code;

/**
 * @author Jim Fisher
 *
 */
public class ThrowStatement extends AbstractStatement 
{
    private Expression expression;

    public void accept(IElementVisitor visitor)
    {
        visitor.visit(this);
    }
    
    public Expression getExpression()
    {
        return expression;
    }
    
    public void setExpression(Expression anExpression)
    {
        this.expression = anExpression;
    }
}

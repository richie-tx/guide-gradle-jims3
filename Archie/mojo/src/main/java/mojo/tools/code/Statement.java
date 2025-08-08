/*
 * Created on May 24, 2006
 *
 */
package mojo.tools.code;

/**
 * @author Jim Fisher
 *  
 */
public class Statement extends AbstractStatement
{
    public void accept(IElementVisitor visitor)
    {
        visitor.visit(this);
    }   
}

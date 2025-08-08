/*
 * Created on May 24, 2006
 *
 */
package mojo.tools.code;

import java.util.List;
import java.util.Vector;

/**
 * @author Jim Fisher
 *
 */
public class Block extends AbstractStatement
{
    private List<AbstractStatement> statements;
    
    public Block()
    {
        this.statements = new Vector<AbstractStatement>();
    }

    public void accept(IElementVisitor visitor)
    {
        visitor.visit(this);
    }
    
    /**
     * @return Returns the statements.
     */
    public List getStatements()
    {
        return statements;
    }
    
    /**
     * @param statements The statements to set.
     */
    public void setStatements(List<AbstractStatement> statements)
    {
        this.statements = statements;
    }
    
    public void addStatement(AbstractStatement statement)
    {
        statement.setParent(this);
        this.statements.add(statement);
    }

}

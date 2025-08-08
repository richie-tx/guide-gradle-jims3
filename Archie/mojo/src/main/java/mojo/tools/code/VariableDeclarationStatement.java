/*
 * Created on Jun 26, 2006
 *
 */
package mojo.tools.code;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jim Fisher
 *
 */
public class VariableDeclarationStatement extends AbstractStatement
{
    private String type;

    private List names;

    public VariableDeclarationStatement()
    {
        this.names = new ArrayList();
    }

    public void accept(IElementVisitor visitor)
    {
        visitor.visit(this);
    }

    public void addName(String name)
    {
        this.names.add(name);
    }
    
    public Iterator getNames()
    {
        return this.names.iterator();
    }
    
    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(type);
        buffer.append(" ");
        Iterator i = names.iterator();
        while(i.hasNext())
        {
            buffer.append(i.next());
            if(i.hasNext())
            {
                buffer.append(", ");
            }
        }        
        return buffer.toString();
    }
}

package mojo.tools.code;

import java.util.*;

import mojo.tools.code.KeyWord;

/**
 * This class represents a Java interface definition.
 * 
 * @author Saleem Shafi
 * @modelguid {F40C46B8-61E7-4ED1-899A-44D7366C50E4}
 */
public class Interface extends Type
{
    /** @modelguid {6D22D2CE-0177-48F6-AB59-AD3A9F2978D2} */
    private List extendsList = new Vector();

    /**
     * Returns the list of interface names that this interface extends.
     * 
     * @return Iterator to list of interface names
     * @modelguid {C26D8E6E-FE94-4F2D-97AF-F79905CBD3E8}
     */
    public Iterator getExtends()
    {
        return extendsList.iterator();
    }

    /**
     * Adds the interface with the given name to the list of extended interfaces
     * 
     * @param String
     *            an interface name
     * @modelguid {94E3E2A5-4C7D-4C27-BC3A-E2F159FF891F}
     */
    public void addExtends(String anExtends)
    {
        extendsList.add(anExtends);
    }

    /**
     * Removes the given interface name from the list of extended interfaces
     * 
     * @param String
     *            name of interface to remove
     * @modelguid {65218998-2443-4F75-8627-C81CAF41BFDC}
     */
    public void removeExtends(String anExtends)
    {
        extendsList.remove(anExtends);
    }

    /**
     * Creates an interface with the given name. If the name is fully-qualified, then the package name is set
     * accordinly, as well.
     * 
     * @param String
     *            interface name
     * @modelguid {9FFFBFC7-8D12-41A0-B193-4657005A4AE4}
     */
    public Interface(String aClassname)
    {
        super(aClassname);
    }

    /**
     * Returns the Java code representation of this interfaces' definition.
     * 
     * @return String represetation of this interface's definition
     * @modelguid {EB07AB6C-13B1-4D2D-AEF9-E4AFC6191223}
     */
    public String toString()
    {
        StringBuffer lFile = new StringBuffer();
        if (isFinal())
            lFile.append(KeyWord.FINAL);
        if (getScope() != null)
            lFile.append(getScope()).append(KeyWord.SPACE);
        lFile.append(KeyWord.INTERFACE).append(getName());
        if (getExtends().hasNext())
        {
            lFile.append(KeyWord.EXTENDS);
            for (Iterator i = getExtends(); i.hasNext();)
            {
                lFile.append(i.next().toString());
                if (i.hasNext())
                    lFile.append(KeyWord.COMMA);
            }
        }
        lFile.append(KeyWord.BLOCK_OPEN).append(KeyWord.NEWLINE);

        for (Iterator i = getAttributes(); i.hasNext();)
        {
            Attribute lAttr = (Attribute) i.next();
            lFile.append(indent(lAttr.toString()));
        }

        List methods = getMethods();
        int methodLen = methods.size();
        for (int i = 0; i < methodLen; i++)
        {
            Method lMethod = (Method) methods.get(i);
            lFile.append(indent(lMethod.toString()));
        }

        for (Iterator i = getInnerTypes(); i.hasNext();)
        {
            Class lClass = (Class) i.next();
            lFile.append(indent(lClass.toString()));
        }

        lFile.append(KeyWord.BLOCK_CLOSE).append(KeyWord.NEWLINE);

        return lFile.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.tools.code.CodeElement#accept(mojo.tools.code.IElementVisitor)
     */
    public void accept(IElementVisitor visitor)
    {
        // TODO Auto-generated method stub
        visitor.visit(this);
    }

}

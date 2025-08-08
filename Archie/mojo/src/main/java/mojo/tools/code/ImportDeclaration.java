/*
 * Created on Jun 30, 2006
 *
 */
package mojo.tools.code;

/**
 * @author Jim Fisher
 *
 */
public class ImportDeclaration extends CodeElement
{
    private String name;

    private boolean onDemand;

    private boolean staticImport;

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return Returns the onDemand.
     */
    public boolean isOnDemand()
    {
        return onDemand;
    }

    /**
     * @return Returns the staticImport.
     */
    public boolean isStaticImport()
    {
        return staticImport;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @param onDemand The onDemand to set.
     */
    public void setOnDemand(boolean onDemand)
    {
        this.onDemand = onDemand;
    }

    /**
     * @param staticImport The staticImport to set.
     */
    public void setStaticImport(boolean staticImport)
    {
        this.staticImport = staticImport;
    }

    public void accept(IElementVisitor visitor)
    {
        visitor.visit(this);
    }

    public String toString()
    {
        return this.name;
    }

    public boolean equals(Object anObject)
    {
        boolean equals = false;
        if (anObject instanceof ImportDeclaration && anObject != null && this.name != null)
        {
            ImportDeclaration anImp = (ImportDeclaration) anObject;
            equals = this.name.equals(anImp.getName());
            equals = this.onDemand == anImp.isOnDemand();
        }
        return equals;
    }
}

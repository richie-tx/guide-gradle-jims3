/*
 * Created on Jul 13, 2007
 *
 */
package mojo.km.config;

/**
 * @author Jim Fisher
 *  
 */
public class DependencyProperties extends GenericProperties
{
    private String className;

    private String dependencyType;

    public static final String CLASSNAME = "className";

    public static final String DEPENDENCYTYPE = "dependencyType";

    /**
     * @return Returns the className.
     */
    public String getClassName()
    {
        return className;
    }

    /**
     * @param className
     *            The className to set.
     */
    public void setClassName(String className)
    {
        this.className = className;
    }

    /**
     * @return Returns the dependencyType.
     */
    public String getDependencyType()
    {
        return dependencyType;
    }

    /**
     * @param dependencyType
     *            The dependencyType to set.
     */
    public void setDependencyType(String dependencyType)
    {
        this.dependencyType = dependencyType;
    }
}

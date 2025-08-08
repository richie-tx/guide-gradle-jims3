package mojo.tools.code;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mojo.tools.code.KeyWord;

/**
 * Represents the definition of a Java class.
 * 
 */
public class Class extends Type
{
    private List implementList = new ArrayList();

    private String extendsClass;

    /**
     * Returns the name of the class that this class extends.
     * 
     * @return name of the extended class
     */
    public String getExtendsClass()
    {
        return extendsClass;
    }

    /**
     * Sets super-class of this class to the class with the given name. The
     * classname does not need to be fully-qualified, but if it is not, the
     * class must be imported in the compilation-unit for the generated source
     * code to resolve properly.
     * 
     * @param String the name of the super-class.
     * @modelguid {C983AFB7-2631-487D-9DF1-2B0EB8EAF3B0}
     */
    public void setExtendsClass(String anExtendsClass)
    {
        extendsClass = anExtendsClass;
    }

    /**
     * Returns the list of interface names that this class implements.
     * 
     * @return Iterator to the list of implemented interfaces
     * @modelguid {12FB0414-6827-43FB-AEDD-821EFF514D36}
     */
    public Iterator getImplements()
    {
        return implementList.iterator();
    }

    /**
     * Adds the interface with the given name to the list of interfaces that
     * this class implements.
     * 
     * @param String name of the interface to implement
     * @modelguid {44F63186-F773-4EE2-976E-FD5A103F842C}
     */
    public void addImplement(String anImplement)
    {
        implementList.add(anImplement);
    }

    /**
     * Removes the interface with the given name from the list of interfaces
     * that this class implements. The interface will not be removed if the name
     * does not match exactly with the way the interface implementation was
     * originally defined.
     * 
     * @param String name of the interface to remove
     * @modelguid {5A0A3ABF-D763-43DD-B5CC-383E1DBF5B24}
     */
    public void removeImplement(String anImplement)
    {
        implementList.remove(anImplement);
    }

    /**
     * Creates a class with the given name.
     * 
     * @param String the name of the class
     * @modelguid {63C9BE18-B802-4437-9515-2551059FCCE2}
     */
    public Class(String aClassname)
    {
        super(aClassname);
    }

    /**
     * Returns the Java code that represents the definition of this class.
     * 
     * @return String representation of this class definition
     * @modelguid {707E6E7C-B4F0-4F10-AF7D-B5FE4A554363}
     */
    public String toString()
    {
        StringBuffer file = new StringBuffer();
        if (getComment() != null)
        {
            file.append(getComment().toString());
        }
        if (isAbstract())
        {
            file.append(KeyWord.ABSTRACT);
        }
        if (isFinal())
        {
            file.append(KeyWord.FINAL);
        }
        if (isStatic())
        {
            file.append(KeyWord.STATIC);
        }
        if (getScope() != null)
        {
            file.append(getScope()).append(KeyWord.SPACE);
        }
        
        file.append(KeyWord.CLASS).append(getName());
        
        if (getExtendsClass() != null)
        {
            file.append(KeyWord.EXTENDS).append(getExtendsClass());
        }
        
        if (getImplements().hasNext())
        {
            file.append(KeyWord.IMPLEMENTS);
            for (Iterator i = getImplements(); i.hasNext();)
            {
                file.append(i.next().toString());
                if (i.hasNext())
                {
                    file.append(KeyWord.COMMA);
                }
            }
        }
        file.append(KeyWord.BLOCK_OPEN).append(KeyWord.NEWLINE);

        for (Iterator i = getAttributes(); i.hasNext();)
        {
            Attribute lAttr = (Attribute) i.next();
            file.append(indent(lAttr.toString()));
        }

        List methods = this.getMethods();
        int methodLen = methods.size();
        for(int i=0;i<methodLen;i++)
        {
            Method method = (Method) methods.get(i);
            file.append(indent(method.toString()));
        }

        for (Iterator i = getInnerTypes(); i.hasNext();)
        {
            Type lType = (Type) i.next();
            file.append(indent(lType.toString()));
        }

        if (getInitializer().getBody() != null)
        {
            file.append(KeyWord.NEWLINE).append(getInitializer().getBody()).append(KeyWord.NEWLINE);
        }

        file.append(KeyWord.BLOCK_CLOSE).append(KeyWord.NEWLINE);

        return file.toString();
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

    /**
     * @param commandInterface
     * @return
     */
    /*
     * public boolean isInstanceOf(Type aType) { List myParents = new Vector();
     * boolean hasParent = true; while (hasParent == true) { String
     * myParentClass = this.getExtendsClass(); if (myParentClass == null ||
     * myParentClass.equals("")) { myParents.add("java.lang.Object"); hasParent =
     * false; } else { myParents.add(myParentClass); } }
     * 
     * String fullyQualifiedName = this.getFullyQualified(aType);
     * 
     * return false; }
     */

}

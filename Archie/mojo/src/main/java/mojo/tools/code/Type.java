package mojo.tools.code;

import java.util.Vector;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class represents the declaration of a Java type, either a Class or
 * Interface, as such it is the base class of Class and Interface.
 * 
 * @author Saleem Shafi
 * @modelguid {38EEEEF1-9815-4882-B2CC-03BBD0AAF60C}
 */
abstract public class Type extends CodeElement
{
    private Map attributes = new HashMap();

    private CompilationUnit compUnit;

    private Initializer initializer = new Initializer();

    private List innerTypeList = new Vector();

    private boolean isAbstract = false;

    private boolean isFinal = false;

    private boolean isStatic = false;

    private boolean isStrictfp = false;

    private List methodList = new Vector();

    private String name;

    private List orderedAttributes = new Vector();

    private Type parentType;

    private String scope;

    private Package thePackage;

    /**
     * Creates a new instance of a type based on the given type name. A
     * compilation unit is create automatically for this type.
     * 
     * @param String the name of the type
     * @modelguid {E4F53C03-4EE5-4149-90C6-D22363950DDA}
     */
    public Type(String aClassname)
    {
        new CompilationUnit().addType(this);
        setName(aClassname);
    }

    /**
     * Adds the given attribute to this type.
     * 
     * @param Attribute the attribute to add to this type
     * @modelguid {2E71BBD4-00B2-40ED-ACB2-E7FF5B4A6F2D}
     */
    public void addAttribute(Attribute anAttribute)
    {
        anAttribute.setParent(this);
        anAttribute.setParentType(this);
        if (!attributes.containsKey(anAttribute.getName()))
        {
            orderedAttributes.add(anAttribute);
        }
        attributes.put(anAttribute.getName(), anAttribute);
    }

    /**
     * Adds the given type as an inner type.
     * 
     * @param Type the type to add
     * @modelguid {C4F8A76F-02FA-4646-BC56-7860E3AC3B7B}
     */
    public void addInnerType(Type aType)
    {
        innerTypeList.add(aType);
    }

    /**
     * Adds the given method to this type definition.
     * 
     * @param Method the method to add
     * @modelguid {2B76D7AC-655F-49BD-9CC1-9236980B3E23}
     */
    public void addMethod(Method aMethod)
    {
        aMethod.setParent(this);
        methodList.add(aMethod);
    }

    /**
     * Returns whether or not a method with the given name and parameter list is
     * defined in this type.
     * 
     * @param String the name of the method
     * @param String[] the list of parameter types
     * @return true iff a method exists
     * @modelguid {B6F4C1F4-4E19-4DAC-B7C2-E74AF7D02C1D}
     */
    public boolean containsMethod(String aMethodName, String[] aParameters)
    {
        return getMethod(aMethodName, aParameters) != null;
    }

    /**
     * Returns the attribute of the given name defined in this type.
     * 
     * @return the named attribute
     * @modelguid {7BB76068-46DA-46BC-9AC5-CD854219EB93}
     */
    public Attribute getAttribute(String aName)
    {
        return (Attribute) attributes.get(aName);
    }

    /**
     * Returns a list of attributes defined in this type.
     * 
     * @return Iterator to the list of attributes in this type
     * @modelguid {2CAAC37E-4DED-485A-8B4E-BC8A225EE4F6}
     */
    public Iterator getAttributes()
    {
        return orderedAttributes.iterator();
    }
    
    public List getAttributesList()
    {
    	return this.orderedAttributes;
    }

    /**
     * Returns the compilation unit that this type is defined in.
     * 
     * @return the compilation unit
     * @modelguid {3E891ECC-6074-4CF8-A0B7-822F9ADE2501}
     */
    public CompilationUnit getCompilationUnit()
    {
        return compUnit;
    }

    /**
     * @return
     */
    public Initializer getInitializer()
    {
        return initializer;
    }

    /**
     * Returns the list of inner types defined in this type.
     * 
     * @return Iterator to this list of inner types
     * @modelguid {8534C0F3-9F03-4C06-A8FC-B1F9028A3451}
     */
    public Iterator getInnerTypes()
    {
        return innerTypeList.iterator();
    }

    /**
     * Returns the method with the given name and parameter list defined in this
     * type definition.
     * 
     * @param String the name of the method
     * @param String[] the list of parameter types
     * @modelguid {B9DB9AAF-4721-4357-8CD6-7AC22E125292}
     */
    public Method getMethod(String aMethodName, String[] aParameters)
    {
        List methods = this.getMethods(aMethodName);
        int methodLen = methods.size();
        methodList: for (int i=0;i<methodLen;i++)
        {
            Method lMethod = (Method) methods.get(i);
            List lParamTypes = new Vector();
            for (Iterator j = lMethod.getParameters(); j.hasNext();)
            {
                Parameter lParam = (Parameter) j.next();
                lParamTypes.add(lParam.getType());
            }
            Object[] lParameters = lParamTypes.toArray();
            if (lParameters.length == aParameters.length)
            {
                for (int k = 0; k < aParameters.length; k++)
                {
                    String myParm = "" + lParameters[k];
                    if (!(myParm).equals(aParameters[k]))
                    {
                        continue methodList;
                    }
                }
                return lMethod;
            }
        }
        return null;
    }   

    /**
     * Return the list of methods defined for this type.
     * 
     * @return Iterator to the list of methods
     */
    public List getMethods()
    {
        return methodList;
    }

    /**
     * Returns the list of methods with the given name defined for this type.
     * 
     * @return Iterator to the list of methods with the given name
     * @modelguid {9BFAB42F-C05F-4F90-9E89-A5B7291FF286}
     */
    public List getMethods(String aMethodName)
    {
        List lMethods = new Vector();
        int methodLen = methodList.size();
        for(int i=0;i<methodLen;i++)
        {
            Method lMethod = (Method) methodList.get(i);
            if (lMethod.getName().equals(aMethodName))
            {
                lMethods.add(lMethod);
            }
        }
        return lMethods;
    }

    /**
     * Returns the name of the type.
     * 
     * @return the name of the type
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the package that this type is defined in.
     * 
     * @return the package
     * @modelguid {3CBBB008-D17D-4384-AE55-2E11184E0679}
     */
    public Package getPackage()
    {
        return thePackage;
    }

    /**
     * Returns the parent type for this type. This will be null unless this type
     * is an inner class or inner interface.
     * 
     * @return parent type
     * @modelguid {F8164163-BA76-4599-9B61-FA6475BFB232}
     */
    public Type getParentType()
    {
        return parentType;
    }

    /**
     * Returns the fully-qualified name of the type.
     * 
     * @return the qualified name of the type
     */
    public String getQualifiedName()
    {
        String qName;
        if (getPackage() != null)
        {
            StringBuffer buffer = new StringBuffer(getPackage().getName());
            buffer.append(".");
            buffer.append(this.getName());
            qName = buffer.toString();
        }
        else
        {
            qName = this.name;
        }
        return qName;
    }

    /**
     * Returns the scope of this type.
     * 
     * @return the scope
     * @modelguid {2932582C-61FB-4E6B-A000-DA6CF27BEF86}
     */
    public String getScope()
    {
        return scope;
    }

    /**
     * Returns whether or not this type is defined as abstract.
     * 
     * @return true iff this type is defined as abstract
     */
    public boolean isAbstract()
    {
        return isAbstract;
    }

    /**
     * Returns whether or not this type is defined as final.
     * 
     * @return true iff this type is defined as final
     * @modelguid {C4278CFF-A215-4062-A45E-3ECCFA3E0777}
     */
    public boolean isFinal()
    {
        return isFinal;
    }

    /**
     * Returns whether or not this type is defined as static.
     * 
     * @return true iff this type is defined as static
     * @modelguid {3B7A573E-4F76-4E63-8E63-5536CD97557C}
     */
    public boolean isStatic()
    {
        return isStatic;
    }

    /**
     * Returns whether or not this type is defined as strictfp.
     * 
     * @return true iff this type is defined as strictfp
     * @modelguid {40E9EC79-C5F6-4C8D-AB58-191F36C29308}
     */
    public boolean isStrictfp()
    {
        return isStrictfp;
    }

    /**
     * Removes the given attribute from this type definition.
     * 
     * @param Attribute the attribute to remove
     * @modelguid {6E5868FB-D55E-4D80-B380-57DADA6A9803}
     */
    public void removeAttribute(Attribute anAttribute)
    {
        anAttribute.setParentType(null);
        attributes.remove(anAttribute.getName());
        orderedAttributes.remove(anAttribute);
    }

    /**
     * Removes the attribute with the given name from this type definition.
     * 
     * @param String the name of the attribute to remove
     * @modelguid {67E1F5B0-0571-4F1C-91E3-703A91D9AE15}
     */
    public void removeAttribute(String anAttributeName)
    {
        Attribute lAttribute = getAttribute(anAttributeName);
        removeAttribute(lAttribute);
    }

    /**
     * Removes the given inner type from this type definition.
     * 
     * @param Type the type to remove
     * @modelguid {149CFD59-EA8F-435B-B236-17C349B96F2A}
     */
    public void removeInnerType(Type aType)
    {
        innerTypeList.remove(aType);
    }

    /**
     * Removes the given method from this type definition.
     * 
     * @param Method the method to remove
     * @modelguid {515B08C9-6311-4ECE-8029-31EFE1B0B044}
     */
    public void removeMethod(Method aMethod)
    {
        aMethod.setParent(null);
        methodList.remove(aMethod);
    }

    /**
     * Sets whether or not this type should be declared as abstract.
     * 
     * @param boolean whether or not this type should be abstract
     * @modelguid {59510547-12F3-48F5-A956-37566559CF98}
     */
    public void setAbstract(boolean isAbstract)
    {
        this.isAbstract = isAbstract;
    }

    /**
     * Sets the compilation unit that this type is defined in.
     * 
     * @param CompilationUnit the compilation unit
     * @modelguid {51020E02-8F88-491F-B375-B6C203EF717D}
     */
    public void setCompilationUnit(CompilationUnit aUnit)
    {
        compUnit = aUnit;
    }

    /**
     * Sets whether or not this type should be declared as final.
     * 
     * @param boolean whether or not this type should be final
     * @modelguid {A09F6D22-79BA-4EC6-93F0-C6390C4FB1CA}
     */
    public void setFinal(boolean isFinal)
    {
        this.isFinal = isFinal;
    }

    /**
     * @param string
     */
    public void setInitializer(String string)
    {
        initializer.setBody(string);
    }

    /**
     * Sets the name of the type. The name is forced to start with a capital
     * letter.
     * 
     * @param the name of the type
     */
    public void setName(String aName)
    {
        name = aName;
        if (name != null)
        {
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
        }
    }

    /**
     * Sets the package that this type is defined in.
     * 
     * @param Package the package
     * @modelguid {A0BD4A0C-2D20-48D1-BD5A-0222B65B1508}
     */
    void setPackage(Package aParentType)
    {
        thePackage = aParentType;
    }

    /**
     * Sets the parent type for this type. This method should only be used if
     * this type is an inner class or inner interface.
     * 
     * @modelguid {7F7E3766-0A38-437A-A383-9BF275958A57}
     */
    public void setParentType(Type aParentType)
    {
        parentType = aParentType;
    }

    /**
     * Sets the scope for this type.
     * 
     * @param String the scope
     * @modelguid {57CA9D69-0A58-4E40-9FE9-215660F755C5}
     */
    public void setScope(String aScope)
    {
        scope = aScope;
    }

    /**
     * Sets whether or not this type should be declared as static.
     * 
     * @param boolean whether or not this type should be static
     * @modelguid {7CFCC9CF-48D3-487A-839B-1C2FA4088C22}
     */
    public void setStatic(boolean isStatic)
    {
        this.isStatic = isStatic;
    }

    /**
     * Sets whether or not this type should be declared as strictfp.
     * 
     * @param boolean whether or not this type should be strictfp
     * @modelguid {9704FEBE-FF4B-4FC6-A7AD-6128A830E61F}
     */
    public void setStrictfp(boolean isStrictfp)
    {
        this.isStrictfp = isStrictfp;
    }

}

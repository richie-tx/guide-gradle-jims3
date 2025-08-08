package mojo.tools.code;

import mojo.tools.code.KeyWord;

/**
 * This class represents a variable defined at the object or class level. a parameter on a method.
 * Basically, it is any named variable outside of the body of a method.
 * 
 * @author Saleem Shafi
 * @modelguid {0C7E3401-E208-4632-A7AC-20AE954A3C23}
 */
public class Attribute extends CodeElement
{
	/** @modelguid {B704E5C7-0261-4660-80EF-76B3D5EB530B} */
	private String name = null;

	/** @modelguid {0C80269D-494B-415E-8174-20B27EB9854F} */
	private String scope = null;

	/** @modelguid {C4F17A33-0546-49E0-86E2-DB503B0535F9} */
	private String initialValue = null;

	/** @modelguid {88585390-C997-4A77-91EE-2121830B8928} */
	private String type = null;

	/** @modelguid {2275470E-9D73-45DE-B569-F27CE406C832} */
	private boolean isFinal = false;

	/** @modelguid {67DF280E-CED5-4CB8-91CD-43025C412330} */
	private boolean isStatic = false;

	/** @modelguid {BC151825-9A6D-4981-A1F3-7C7E7F447271} */
	private boolean isTransient = false;

	/** @modelguid {D3E080B9-3D0B-4FAB-B02B-3107DE6AF87C} */
	private Type parentType = null;

	/**
	 * Creates an attribute of the given type with the given name.
	 * 
	 * @param String
	 *            the type of the attribute
	 * @param String
	 *            the name of the attribute
	 * @modelguid {9E64ACB6-9172-4FB7-8371-5AD89B5944E5}
	 */
	public Attribute(String aType, String aName)
	{
		setType(aType);
		setName(aName);
	}

	/**
	 * Returns the scope of this attribute. The scope will be either private, protected or public.
	 * If this value is null or emptyString, package scope is assumed.
	 * 
	 * @return the scope of the attribute
	 * @modelguid {72EC6D10-30D7-47D8-B669-4A83DD99ED00}
	 */
	public String getScope()
	{
		return scope;
	}

	/**
	 * Sets the scope of this attribute. The scope should be either private, protected or public. If
	 * package scope is desired, the scope should be set to emptyString. Although these are the only
	 * expected values, other values will not cause an exception to be thrown.
	 * 
	 * @param String
	 *            the scope of the attribute.
	 * @modelguid {E9B5A629-13EA-424E-81B2-17E233E3DBB2}
	 */
	public void setScope(String aScope)
	{
		scope = aScope;
	}

	/**
	 * Returns the name of this attribute.
	 * 
	 * @return attribute name
	 * @modelguid {B8D7414C-DDBC-407A-BDD6-ACFD0998982C}
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name of this attribute
	 * 
	 * @param String
	 *            the name of the attribute
	 * @modelguid {1E6A0D18-A0EA-4676-BD65-93C892B88D0C}
	 */
	public void setName(String aName)
	{
		name = aName;
	}

	/**
	 * Return the type of this attribute. This value will not necessarily be the fully-qualified
	 * type.
	 * 
	 * @return the type of the attribute
	 * @modelguid {0BF975BB-229D-46F2-A405-39CE2C542993}
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Sets the type of the attribute. Either the classname or the fully-qualified classname of the
	 * attribute type can be used. If only the basic classname is used, be sure that the class is
	 * imported in the compilation unit or the generated code will not resolve properly.
	 * 
	 * @param String
	 *            attribute type
	 * @modelguid {14B99678-FA8F-45F6-8B67-E54111BE8552}
	 */
	public void setType(String aType)
	{
		type = aType;
	}

	/**
	 * Returns the initial value of the attribute as a String. If the result is null, that means
	 * that the attribute is not initialized at all, not that the attribute is initialized to null;
	 * the String 'null' will be returned in that case.
	 * 
	 * @return the initial value of the attribute
	 * @modelguid {1D0032D0-53A2-4CA2-83B6-6E90FB3378A4}
	 */
	public String getInitialValue()
	{
		return initialValue;
	}

	/**
	 * Sets the initial value of the attribute. If the parameter is null, the attribute is not
	 * initialized. To initialize the attribute to null, the String 'null' should be used as the
	 * parameter.
	 * 
	 * @param the
	 *            String representation of the initial value of the attribute
	 * @modelguid {7F917279-02A6-455A-950F-93B79A8C272C}
	 */
	public void setInitialValue(String aValue)
	{
		initialValue = aValue;
	}

	/**
	 * Returns a reference to the encapsulating class or interface of this attribute.
	 * 
	 * @return the parent type of this attribute
	 * @modelguid {6A8AD5F6-01B4-4810-8346-183A7BB96957}
	 */
	public Type getParentType()
	{
		return parentType;
	}

	/**
	 * Sets the encapsulating type for this attribute.
	 * 
	 * @param Type
	 *            the parent class or interface of this attribute
	 * @modelguid {4F8232D5-0CDA-4593-9B3E-DD3EAB73D3B6}
	 */
	void setParentType(Type aType)
	{
		parentType = aType;
	}

	/**
	 * Returns whether or not this attribute is defined as final.
	 * 
	 * @return true iff this attribute is defined as final.
	 * @modelguid {48A1CE95-4BB3-4177-9358-353CF2023AFC}
	 */
	public boolean isFinal()
	{
		return isFinal;
	}

	/**
	 * Sets whether or not this attribute should be final.
	 * 
	 * @param boolean
	 *            should this attribute be final
	 * @modelguid {0D32EC6B-8B9A-43BA-BDEF-364C97A93503}
	 */
	public void setFinal(boolean isFinal)
	{
		this.isFinal = isFinal;
	}

	/**
	 * Returns whether or not this attribute is defined as static.
	 * 
	 * @return true iff this attribute is static
	 * @modelguid {6C6D5997-5AE7-4414-8BA3-71F9103A26E8}
	 */
	public boolean isStatic()
	{
		return isStatic;
	}

	/**
	 * Sets whether or not this attribute should be defined as static.
	 * 
	 * @param boolean
	 *            should this attribute be static
	 * @modelguid {91BA10FD-EFBD-4886-ADFE-881D25C9E364}
	 */
	public void setStatic(boolean isStatic)
	{
		this.isStatic = isStatic;
	}

	/**
	 * Returns whether or not this attribute is defined as transient.
	 * 
	 * @return true iff this attribute is defined as transient.
	 * @modelguid {C5F934FA-AB62-40F6-979D-96C2A5926426}
	 */
	public boolean isTransient()
	{
		return isTransient;
	}

	public boolean isPrimitive()
	{
		if (type == null)
		{
			return false;
		}
		if (type.equals("int"))
		{
			return true;
		}
		else if (type.toLowerCase().equals("integer"))
		{
			return true;
		}
		else if (type.toLowerCase().equals("long"))
		{
			return true;
		}
		else if (type.toLowerCase().equals("double"))
		{
			return true;
		}
		else if (type.toLowerCase().equals("short"))
		{
			return true;
		}
		else if (type.toLowerCase().equals("boolean"))
		{
			return true;
		}
		else if (type.equals("BigDecimal"))
		{
			return true;
		}
		else if (type.endsWith("Date"))
		{
			return true;
		}
		else if (type.equals("String"))
		{
			return true;
		}
		else if (type.equals("Number"))
		{
			return true;
		}
		else if (type.equals("StringBuffer"))
		{
			return true;
		}
		else if (type.equals("Timestamp"))
		{
			return true;
		}
		return false;
	}

	/**
	 * Sets whether or not this attribute should be transient.
	 * 
	 * @param boolean
	 *            should this attribute be transient
	 * @modelguid {F7E3622D-AE5B-48E4-9467-526560FDED70}
	 */
	public void setTransient(boolean isTransient)
	{
		this.isTransient = isTransient;
	}

	/**
	 * Returns the Java source code representing this attribute definition.
	 * 
	 * @return String representation of this attribute definition
	 * @modelguid {E81E6F10-CE19-4A8C-9335-0E380474A473}
	 */
	public String toString()
	{
		StringBuffer lText = new StringBuffer();
		if (getComment() != null)
			lText.append(getComment().toString());

		if (isFinal())
			lText.append(KeyWord.FINAL);
		if (isStatic())
			lText.append(KeyWord.STATIC);
		if (getScope() != null)
			lText.append(getScope()).append(KeyWord.SPACE);
		lText.append(getType()).append(KeyWord.SPACE).append(getName());
		if (getInitialValue() != null)
		{
			lText.append(KeyWord.SPACE).append(KeyWord.EQUALS).append(KeyWord.SPACE).append(getInitialValue());
		}
		lText.append(KeyWord.SEMICOLON).append(KeyWord.NEWLINE);

		return lText.toString();
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

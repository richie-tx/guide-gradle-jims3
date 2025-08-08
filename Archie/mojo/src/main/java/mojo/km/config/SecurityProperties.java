package mojo.km.config;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class SecurityProperties extends GenericProperties
{
	private List urlResources = new ArrayList();
	private Map constraintMappingsTypeToClass = new Hashtable();
	private Map constraintMappingsClassToType = new Hashtable();
	private List unprotectedResources = new ArrayList();
	private List unprotectedExtensions = new ArrayList();
	private List fullAccessUserTypes = new ArrayList();

	/**
	 * @roseuid 424957BF00C7
	 */
	public SecurityProperties()
	{

	}

	/**
	 * @return String
	 * @roseuid 424957140338
	 */
	public String getSecurityManagerImpl()
	{
		return this.getProperty("SecurityManagerImpl");
	}

	/**
	 * @return String
	 * @roseuid 4249572B0287
	 */
	public String getAuthenticatorImpl()
	{
		return this.getProperty("AuthenticatorImpl");
	}

	/**
	 * @return String
	 * @roseuid 4249573801B3
	 */
	public String getConstraintMapperImpl()
	{
		return this.getProperty("ConstraintMapperImpl");
	}

	/**
	 * @return mojo.km.config.SecurityProperties
	 * @roseuid 42495B0400AA
	 */
	public static SecurityProperties getInstance()
	{
		return MojoProperties.getInstance().getSecurityProperties();
	}

	/**
	 * @param url
	 * @param queryParameters
	 * @return String[]
	 * @roseuid 424961B701F6
	 */
	public String[] getRequiredFeatures(String url, Map queryParameters)
	{
		for (int i = 0; i < urlResources.size(); i++)
		{
			URLResource resource = (URLResource) urlResources.get(i);
			if (resource.matchesRequest(url, queryParameters))
			{
				return resource.getRequiredFeatures();
			}
		}
		return null;
	}

	/**
	 * @param url
	 * @param queryParameters
	 * @return String[]
	 * @roseuid 424961D403C8
	 */
	public String[] getRequiredUserTypes(String url, Map queryParameters)
	{
		for (int i = 0; i < urlResources.size(); i++)
		{
			URLResource resource = (URLResource) urlResources.get(i);
			if (resource.matchesRequest(url, queryParameters))
			{
				return resource.getRequiredUserTypes();
			}
		}
		return null;
	}

	/**
	 * @param urlResource
	 * @roseuid 4249A284015C
	 */
	public void addURLResource(URLResource urlResource)
	{
		urlResources.add(urlResource);
	}

	/**
	 * @param constraintType
	 * @return String
	 * @roseuid 424BFC100202
	 */
	public Class getConstraintClass(String constraintType)
	{
		return (Class) constraintMappingsTypeToClass.get(constraintType);
	}

	/**
	 * @param type
	 * @param className
	 * @roseuid 424BFD310320
	 */
	public void addConstraintMapping(String type, String className)
	{
		if (constraintMappingsTypeToClass.get(type) != null)
		{
			constraintMappingsTypeToClass.remove(type);
		}
		try
		{
			constraintMappingsTypeToClass.put(type, Class.forName(className));
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw new RuntimeException("The provided classname " + className + " could not be instantiated");
		}
	}

	public String getConstraintType(Class constraintType)
	{
		return (String) constraintMappingsClassToType.get(constraintType);
	}

	public void addClassToTypeConstraintMapping(String className, String type)
	{
		Class theClass = null;
		try
		{
			theClass = Class.forName(className);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw new RuntimeException("The provided classname " + className + " could not be instantiated");
		}
		if (constraintMappingsClassToType.get(theClass) != null)
		{
			constraintMappingsClassToType.remove(theClass);
		}
		constraintMappingsClassToType.put(theClass, type);
	}

	public void addUnprotectedResource(String unprotectedResource)
	{
		this.unprotectedResources.add(unprotectedResource);
	}
	public List getUnprotectedResources()
	{
		return this.unprotectedResources;
	}

	public boolean isUnprotected(String uri)
	{
		for (int i = 0; i < unprotectedResources.size(); i++)
		{
			if (unprotectedResources.get(i).equals(uri))
			{
				return true;
			}
		}
		for (int i = 0; i < unprotectedExtensions.size(); i++)
		{
			if (uri.toLowerCase().endsWith((String) unprotectedExtensions.get(i)))
			{
				return true;
			}
		}
		return false;
	}

	public void addUnprotectedExtension(String unprotectedExtension)
	{
		this.unprotectedExtensions.add(unprotectedExtension);
	}
	public List getUnprotectedExtensions()
	{
		return this.unprotectedExtensions;
	}

	/**
	 * Will add a user type that is granted full access
	 * @param userType
	 */
	public void addFullAccessUserType(String userType)
	{
		this.fullAccessUserTypes.add(userType);
	}

	/**
	 * Will return a collection of userTypes that have
	 * full access to the application
	 * @return
	 */
	public List getFullAccessUserTypes()
	{
		return this.fullAccessUserTypes;
	}
}

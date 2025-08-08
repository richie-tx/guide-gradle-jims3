package mojo.km.config;

import java.util.Hashtable;
import java.util.TimeZone;

import mojo.km.config.GenericProperties;
import mojo.km.config.MojoProperties;

public class CalendaringProperties extends GenericProperties
{
	public static String DEFAULT_PERSISTENT_TIMEZONE = "CST";
	public Hashtable contextClasses = new Hashtable();
	
	public CalendaringProperties() {}

	/**
	 * Returns the timezone that calendar events start/end date times
	 * will be persisted in.
	 * @return String
	 */
	public String getPersistenceTimeZone()
	{						
		String timezone = this.getProperty("PersistenceTimeZone");
		if (timezone == null) {
			timezone = DEFAULT_PERSISTENT_TIMEZONE; 	
		}
		return timezone; 
	}


	/**
	 * Returns the timezone that calendar events start/end date times
	 * will be presented in.
	 * @return String
	 */
	public String getPresentationTimeZone()
	{
		String timezone = this.getProperty("PresentationTimeZone");
		if (timezone == null) {
			timezone = TimeZone.getDefault().getID(); 	
		}
		return timezone;
	}

	/**
	 * @return mojo.km.config.SecurityProperties
	 * @roseuid 42495B0400AA
	 */
	public static CalendaringProperties getInstance()
	{
		return MojoProperties.getInstance().getCalendaringProperties();
	}
	
	public Class getContextClass(String contextType)
	{
		return (Class) contextClasses.get(contextType);
	}

	public void addContextClass(String contextType, String contextClass)
	{
		// Remove it if it already is in the hash
		if (contextClasses.get(contextType) != null)
		{
			contextClasses.remove(contextType);
		}

		try
		{
			contextClasses.put(contextType, Class.forName(contextClass));
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw new RuntimeException("[CalendaringProperties] The provided classname " + contextClass + " could not be instantiated. Verify it is defined correctly in the mojo.xml (Calendaring/Contexts/Context)");
		}
	}
}

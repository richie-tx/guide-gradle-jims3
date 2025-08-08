/*
 * Created on Oct 13, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.km.util;

/**
 * @author dapte
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Name {
	private String firstName;
	private String middleName;
	private String lastName;
	private String suffix;
	
	/**
	 * Default constructor
	 *
	 */
	public Name() {
		firstName = "";
		middleName = "";
		lastName = "";
		suffix = "";
	}
	/**
	 * 
	 * @param fName
	 * @param mName
	 * @param lName
	 */
	public Name(String fName, String mName, String lName) {
		firstName = fName;
		middleName = mName;
		lastName = lName;
		suffix = "";
	}
	/**
	 * 
	 * @param fName
	 * @param mName
	 * @param lName
	 */
	public Name(String fName, String mName, String lName, String sName) {
		firstName = fName;
		middleName = mName;
		lastName = lName;
		suffix = sName;
	}
	/**
	 * Provides a clear method
	 *
	 */
	public void clear() {
		firstName = "";
		middleName = "";
		lastName = "";
		suffix = "";
	}
	/**
	 * Provides a reset method
	 *
	 */	
	public void reset() {
		clear();
	}
/**
 * 
 * @return String
 */
	public String getFormattedName() {
		StringBuffer full = new StringBuffer();
	    if (lastName == null || lastName.equals(""))
		{
			full.append("NOT AVAILABLE");
		}
	    else
	    {
	        full.append(lastName);
			if (firstName != null && !firstName.equals(""))
			{
				full.append(", ");
				full.append(firstName);
			}
			if (middleName != null && !middleName.equals(""))
			{
				full.append(" ");
				full.append(middleName);
			}
			if (suffix != null && !suffix.equals(""))
			{
				full.append(" ");
				full.append(suffix);
			}
	    }
		return full.toString();
	}
	
	
	/**
	 * @return String firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return String lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return String middleName
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return String suffix
	 */
	public String getSuffix()
	{
		return suffix;
	}
	
	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	}
	
	/**
	 * @param string
	 */
	public void setSuffix(String string)
	{
		suffix = string;
	}
	
	/**
	 * @return String formattedName
	 */
	public String toString() {
		return getFormattedName();
	}
}

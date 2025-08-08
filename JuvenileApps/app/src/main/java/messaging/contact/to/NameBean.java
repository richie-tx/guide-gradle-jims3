/*
 * Created on Mar 1, 2006
 *
 */
package messaging.contact.to;

import messaging.contact.domintf.IName;

/**
 * @author Jim Fisher
 *
 */

public class NameBean implements IName
{
    static private final String NOT_AVAILABLE = ""; 
    
	public final static int PREFIX_NAME = 1;
	public final static int FIRST_NAME = 2;
	public final static int MIDDLE_NAME = 4;
	public final static int LAST_NAME = 8;
	public final static int SUFFIX_NAME = 16;

	private String firstName;
	private String middleName;
	private String lastName;
	private String suffix;
	private String prefix;

	/**
	 * 
	 */
	public NameBean()
	{
		this.clear();
	}

	/**
	 * @param fName
	 * @param mName
	 * @param lName
	 */
	public NameBean(String fName, String mName, String lName)
	{
		this.clear();
		firstName = fName;
		middleName = mName;
		lastName = lName;
	}

	/**
	 * @param fName
	 * @param mName
	 * @param lName
	 */
	public NameBean(String fName, String mName, String lName, String prefix)
	{
		this.clear();
		this.prefix = prefix;
		firstName = fName;
		middleName = mName;
		lastName = lName;
	}

	/**
	 * 
	 */
	public void clear()
	{
		this.firstName = "";
		this.middleName = "";
		this.lastName = "";
		this.prefix = "";
		this.suffix = "";
	}

	/**
	 * 
	 */
	public void reset()
	{
		this.clear();
	}

	/**
	 * @return String first Name
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return last Name
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return middle name
	 */
	public String getMiddleName()
	{
		return middleName;
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
	 * @see Object#toString()
	 * @return String
	 */
	public String toString()
	{
		return this.getFirstName() + " " + this.getLastName();
	}

	/**
	 * @return String
	 */
	public String getPrefix()
	{
		return prefix;
	}

	/**
	 * @return String
	 */
	public String getSuffix()
	{
		return suffix;
	}

	/**
	 * @param string
	 */
	public void setPrefix(String string)
	{
		prefix = string;
	}

	/**
	 * @param string
	 */
	public void setSuffix(String string)
	{
		suffix = string;
	}

	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IName#getCompleteFullNameFirst()
	 */
	public String getCompleteFullNameFirst()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IName#getCompleteFullNameLast()
	 */
	public String getCompleteFullNameLast()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @return biikeab
	 */
	private boolean isFirstAvailable()
	{
		boolean available = true;
		if (firstName == null || firstName.equals(""))
		{
			available = false;
		}
		return available;
	}
	/**
	 * @return boolean 
	 */
	private boolean isMiddleAvailable()
	{
		boolean available = true;
		if (middleName == null || middleName.equals(""))
		{
			available = false;
		}
		return available;
	}

	/**
	 * @return boolean
	 */
	private boolean isPrefixAvailable()
	{
		boolean available = true;
		if (prefix == null || prefix.equals(""))
		{
			available = false;
		}
		return available;
	}
	
	/**
	 * @return boolean
	 */
	private boolean isNameAvailable()
	{
		boolean available = true;
		//if (lastName == null || lastName.equals("") || firstName == null || firstName.equals(""))
		if ((lastName == null || lastName.trim().equals("")) && (firstName == null || firstName.trim().equals("")) && (middleName == null || middleName.trim().equals("")))
		{
			available = false;
		}
		return available;
	}
	
	/**
	 * @return boolean
	 */
	private boolean isLastAvailable()
	{
		boolean available = true;
		if (lastName == null || lastName.equals(""))
		{
			available = false;
		}
		return available;
	}

	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IName#getFormattedName()
	 */
	public String getFormattedName()
	{
	    String name = null;
		if (!this.isNameAvailable())
		{
			name = NameBean.NOT_AVAILABLE;
		}
		else
		{
			StringBuffer full = new StringBuffer();
			if (this.isPrefixAvailable()){
				full.append(prefix);				
				full.append(" ");
			}
			if (isLastAvailable()){
				full.append(lastName);
			}
			if (this.isFirstAvailable())
			{
				full.append(", ");
				full.append(firstName);
				if (this.isMiddleAvailable())
				{
					full.append(" " + middleName);
				}
			}
			name = full.toString();
		}
		return name;
	}

	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IName#getFullNameFirst()
	 */
	public String getFullNameFirst()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IName#getFullNameLast()
	 */
	public String getFullNameLast()
	{
		// TODO Auto-generated method stub
		return null;
	}

}

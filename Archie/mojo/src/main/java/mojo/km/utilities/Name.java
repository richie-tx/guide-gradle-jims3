/*
 * Created on Oct 13, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.utilities;

/**
 * @author dapte
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Name
{
/**	static private final String NOT_AVAILABLE = "Not Available"; */
	static private final String NOT_AVAILABLE = ""; 

	// Internally used for the format formula
	private final static int PREFIX_NAME = 1;
	private final static int FIRST_NAME = 2;
	private final static int MIDDLE_NAME = 4;
	private final static int LAST_NAME = 8;
	private final static int SUFFIX_NAME = 16;
	private final static int FIRST_NAME_FIRST = 1;
	private final static int LAST_NAME_FIRST = 2;

	private String firstName;
	private String middleName;
	private String lastName;
	private String suffix;
	private String prefix;

	/**
	 * 
	 */
	public Name()
	{
		this.clear();
	}

	/**
	 * @param fName
	 * @param mName
	 * @param lName
	 */
	public Name(String fName, String mName, String lName)
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
	public Name(String fName, String mName, String lName, String prefix)
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
		clear();
	}

	/**
	 * @return String fullnamefirst
	 */
	public String getFullNameFirst()
	{
		String name = null;
		if (!this.isNameAvailable())
		{
			name = Name.NOT_AVAILABLE;
		}
		else if (this.isFirstAvailable())
		{
			int fmt = FIRST_NAME | MIDDLE_NAME | LAST_NAME;
			name = this.format(fmt, FIRST_NAME_FIRST);
		}
		else
		{
			int fmt = LAST_NAME;
			name = this.format(fmt, LAST_NAME_FIRST);
		}

		return name;
	}

	/**
	 * @return String fullNameLast
	 */
	public String getFullNameLast()
	{
		String name = null;
		if (!this.isNameAvailable())
		{
			name = Name.NOT_AVAILABLE;
		}
		else
		{
			int fmt = FIRST_NAME | MIDDLE_NAME | LAST_NAME;
			name = this.format(fmt, LAST_NAME_FIRST);
		}

		return name;
	}

	/**
	 * @return String complete Full Name last
	 */
	public String getCompleteFullNameLast()
	{
		String name = null;
		if (!this.isNameAvailable())
		{
			name = Name.NOT_AVAILABLE;
		}
		else
		{
			int fmt =
				PREFIX_NAME
					| FIRST_NAME
					| MIDDLE_NAME
					| LAST_NAME
					| SUFFIX_NAME;
			name = this.format(fmt, LAST_NAME_FIRST);
		}

		return name;
	}

	/**
	 * @return String Complete Full Name First
	 */
	public String getCompleteFullNameFirst()
	{
		String name = null;
		if (!this.isNameAvailable())
		{
			name = Name.NOT_AVAILABLE;
		}
		else
		{
			int fmt =
				PREFIX_NAME
					| FIRST_NAME
					| MIDDLE_NAME
					| LAST_NAME
					| SUFFIX_NAME;
			name = this.format(fmt, FIRST_NAME_FIRST);
		}

		return name;
	}

	/**
	 * @return String formatted Name
	 */
	public String getFormattedName()
	{
		String name = null;
		if (!this.isNameAvailable())
		{
			name = Name.NOT_AVAILABLE;
		}
		else
		{
			StringBuffer full = new StringBuffer();
			if(isPrefixAvailable()){
				full.append(prefix);				
			}
			if(isLastAvailable()){
				full.append(" ");
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

	/**
	 * @param fields
	 * @param type
	 * @return String format
	 */
	private String format(int fields, int type)
	{
		String name = "";

		if ((fields | PREFIX_NAME) == fields)
		{
			name += this.prefix + " ";
		}

		if (type == FIRST_NAME_FIRST)
		{
			if (this.isFirstAvailable())
			{
				if ((fields | FIRST_NAME) == fields)
				{
					name += this.firstName;
				}
				if (this.isMiddleAvailable())
				{
					if ((fields | MIDDLE_NAME) == fields)
					{
						name += " " + this.middleName;
					}
				}
			}

			if ((fields | LAST_NAME) == fields)
			{
				if (this.isFirstAvailable())
				{
					name += " ";
				}
				name += this.lastName;
			}
		}
		else if (type == LAST_NAME_FIRST)
		{
			if ((fields | LAST_NAME) == fields)
			{
				name += this.lastName;
			}

			if (this.isFirstAvailable())
			{
				if ((fields | FIRST_NAME) == fields)
				{
					name += ", " + this.firstName;
					if (this.isMiddleAvailable())
					{
						if ((fields | MIDDLE_NAME) == fields)
						{
							name += " " + this.middleName;
						}
					}
				}
			}
		}

		if ((fields | SUFFIX_NAME) == fields)
		{
			name += " " + this.suffix;
		}

		return name;
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

	//	private boolean isLastAvailable()
	//	{
	//		boolean available = true;
	//		if (lastName == null || lastName.equals(""))
	//		{
	//			available = false;
	//		}
	//		return available;
	//	}
	//
	//	private boolean isPrefixAvailable()
	//	{
	//		boolean available = true;
	//		if (prefix == null || prefix.equals(""))
	//		{
	//			available = false;
	//		}
	//		return available;
	//	}
	//
	//	private boolean isSuffixAvailable()
	//	{
	//		boolean available = true;
	//		if (suffix == null || suffix.equals(""))
	//		{
	//			available = false;
	//		}
	//		return available;
	//	}

	/**
	 * @return boolean
	 */
	private boolean isNameAvailable()
	{
		boolean available = true;
		//if (lastName == null || lastName.equals("") || firstName == null || firstName.equals(""))
		if (lastName == null || lastName.equals(""))
		{
			available = false;
		}
		return available;
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
		return getFormattedName();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Name name = new Name();

		name.setPrefix("Mr.");
		name.setFirstName("Jay");
		name.setMiddleName("Clark");
		name.setLastName("Fanning");
		name.setSuffix("Sr.");

		System.out.println("First/Middle/Last");
		System.out.println("getFormattedName()-" + name.getFormattedName());
		System.out.println("getFullNameLast()-" + name.getFullNameLast());
		System.out.println("getFullNameFirst()-" + name.getFullNameFirst());
		System.out.println(
			"getCompleteFullNameFirst()-" + name.getCompleteFullNameFirst());
		System.out.println(
			"getCompleteFullNameLast()-" + name.getCompleteFullNameLast());

		name.setPrefix("Mr.");
		name.setFirstName("");
		name.setMiddleName("");
		name.setLastName("Fanning");
		name.setSuffix("Sr.");

		System.out.println("");
		System.out.println("Last only");
		System.out.println("getFormattedName()-" + name.getFormattedName());
		System.out.println("getFullNameLast()-" + name.getFullNameLast());
		System.out.println("getFullNameFirst()-" + name.getFullNameFirst());
		System.out.println(
			"getCompleteFullNameFirst()-" + name.getCompleteFullNameFirst());
		System.out.println(
			"getCompleteFullNameLast()-" + name.getCompleteFullNameLast());

		name.setPrefix("Mr.");
		name.setFirstName("Jay");
		name.setMiddleName("");
		name.setLastName("Fanning");
		name.setSuffix("Sr.");

		System.out.println("");
		System.out.println("First/Last");
		System.out.println("getFormattedName()-" + name.getFormattedName());
		System.out.println("getFullNameLast()-" + name.getFullNameLast());
		System.out.println("getFullNameFirst()-" + name.getFullNameFirst());
		System.out.println(
			"getCompleteFullNameFirst()-" + name.getCompleteFullNameFirst());
		System.out.println(
			"getCompleteFullNameLast()-" + name.getCompleteFullNameLast());

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

}

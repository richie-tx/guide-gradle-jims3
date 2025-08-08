/*
 * Created on Jan 24, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.km.util;

/**
 * @author dnikolis
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Phone
{
	private String areaCode;
	private String last4Digit;
	private String prefix;

	/**
	 * Default constructor
	 *
	 */
	public Phone()
	{
		areaCode = "";
		last4Digit = "";
		prefix = "";
	}
	/**
	 * @param phoneNumber
	 */
	public Phone(String phoneNumber)
	{
		this.setPhoneNumber(phoneNumber);
	}

	/**
	 * @return String
	 */
	public String get4Digit()
	{
		if (last4Digit != null)
		{
			return last4Digit;
		}
		return "";
	}

	/**
	 * @return String
	 */
	public String getAreaCode()
	{
		if (areaCode != null)
		{
			return areaCode;
		}
		return "";
	}

	/**
	 * @return String
	 */
	public String getFormattedPhoneNumber()
	{
		StringBuffer phNumber = new StringBuffer();
		phNumber = phNumber.append(areaCode).append("-").append(prefix).append("-").append(last4Digit);
		String ph = phNumber.toString();
		if (ph.equals("--") || ph.equalsIgnoreCase("null-null-null"))
		{
			return "";
		}
		return ph;
	}

	/**
	 * @return String
	 */
	public String getLast4Digit()
	{
		if (last4Digit != null)
		{
			return last4Digit;
		}
		return "";
	}

	/**
	 * @return String
	 */
	public String getPhoneNumber()
	{
		StringBuffer phNumber = new StringBuffer();
		phNumber = phNumber.append(areaCode).append(prefix).append(last4Digit);
		return phNumber.toString();
	}

	/**
	 * @return String
	 */
	public String getPrefix()
	{
		if (prefix != null)
		{
			return prefix;
		}
		return "";
	}

	/**
	 * @param string
	 */
	public void set4Digit(String string)
	{
		if (string == null)
		{
			string = "";
		}
		last4Digit = string;
	}

	/**
	 * @param string
	 */
	public void setAreaCode(String string)
	{
		if (string == null)
		{
			string = "";
		}
		areaCode = string;
	}

	/**
	 * @param string
	 */
	public void setLast4Digit(String string)
	{
		if (string == null)
		{
			string = "";
		}
		last4Digit = string;
	}

	/**
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		if ((phoneNumber != null) && phoneNumber.length() > 9)
		{
			String formatedValue = phoneNumber.substring(3, 8);
			if (formatedValue.startsWith("-") && formatedValue.endsWith("-"))
			{
				this.areaCode = phoneNumber.substring(0, 3);
				this.prefix = phoneNumber.substring(4, 7);
				this.last4Digit = phoneNumber.substring(8, 12);
			}
			else
			{
				this.areaCode = phoneNumber.substring(0, 3);
				this.prefix = phoneNumber.substring(3, 6);
				this.last4Digit = phoneNumber.substring(6, 10);
			}
		}
	}

	/**
	 * @param string
	 */
	public void setPrefix(String string)
	{
		if (string == null)
		{
			string = "";
		}
		prefix = string;
	}

}
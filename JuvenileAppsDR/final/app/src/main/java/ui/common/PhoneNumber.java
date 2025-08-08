/*
 * Created on Aug 26, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common;

import messaging.contact.domintf.IPhoneNumber;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PhoneNumber implements Cloneable, IPhoneNumber
{
	private String areaCode = "";
	private String prefix = "";
	private String last4Digit = "";
	private boolean valid = false;
	private String ext ="";
	private String phoneId="";
     
	/**
	 * @default Constructor added for Create Juvenile //Referrals Conversion
	 */
	public PhoneNumber()
	{
		
	}
	
	/**
	 * @param phoneNumber
	 */
	public PhoneNumber(String phoneNumber)
	{
		if(phoneNumber==null || phoneNumber.trim().equals(""))
			this.setPhoneNumber("");
		else
			this.setPhoneNumber(phoneNumber);
	}

	/**
	 * @param areaCode
	 * @param prefix
	 * @param fourDigit
	 */
	public PhoneNumber(String aAreaCode, String aPrefix, String fourDigit)
	{
		if (aAreaCode != null && aPrefix != null && fourDigit != null)
		{
			if (!aAreaCode.trim().equals("") && !aPrefix.trim().equals("") && !fourDigit.trim().equals(""))
			{

				setAreaCode(aAreaCode);
				setPrefix(aPrefix);
				set4Digit(fourDigit);
				valid = true;
			}
		}
	}

	/**
	 * 
	 */
	public void clear()
	{
		areaCode = "";
		prefix = "";
		last4Digit = "";
		ext="";
		valid = false;
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
/**			return "Not Available"; // change to "None" if desired */
/** CShimek 08/23/2005  revised to return blank to match latest UI guideline */ 
			return ""; 
		}
		return ph;
	}
	
	/**
	 * @return String
	 */
	public String formatPhoneNumberWithDashes(String phoneNumber)
	{
		if ((phoneNumber != null) && phoneNumber.length() == 10 && phoneNumber.indexOf("-") < 0){
			StringBuffer phone = new StringBuffer(phoneNumber.substring(0,3));
			phone.append("-");
			phone.append(phoneNumber.substring(3,6));
			phone.append("-");
			phone.append(phoneNumber.substring(6));
			return phone.toString(); 
		}
		return phoneNumber;
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
	public String getPrefix()
	{
		if (prefix != null)
		{
			return prefix;
		}
		return "";
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
	public String getLast4Digit()
	{
		if (last4Digit != null)
		{
			return last4Digit;
		}
		return "";
	}

	/**
	 * @param string
	 */
	public void setAreaCode(String string)
	{
		if (string == null)
		{
			string = "";
			valid = false;
		}
		areaCode = string;
	}

	/**
	 * @param string
	 */
	public void set4Digit(String string)
	{
		if (string == null)
		{
			string = "";
			valid = false;
		}
		last4Digit = string;
	}

	/**
	 * @param string
	 */
	public void setLast4Digit(String string)
	{
		if (string == null)
		{
			string = "";
			valid = false;
		}
		last4Digit = string;
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

	/**
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		if(phoneNumber == null || phoneNumber.equals(""))
		{
			phoneNumber = "";
			this.areaCode = "";
			this.prefix = "";
			this.last4Digit = "";
			this.valid = false;
			this.ext ="";
			this.phoneId="";
			
		}
		if ((phoneNumber != null) && phoneNumber.length() > 9)
		{
			// set the individual fields too
			// check if input is formated
			String formatedValue = phoneNumber.substring(3, 8);
			if (formatedValue.startsWith("-") && formatedValue.endsWith("-"))
			{
				this.areaCode = phoneNumber.substring(0, 3);
				this.prefix = phoneNumber.substring(4, 7);
				this.last4Digit = phoneNumber.substring(8, 12);
				this.valid = true;
			}
			else
			{
				this.areaCode = phoneNumber.substring(0, 3);
				this.prefix = phoneNumber.substring(3, 6);
				this.last4Digit = phoneNumber.substring(6, 10);
				this.valid = true;
			}

		}

	}

	/**
	 * @return String
	 */
	public String toString()
	{
		return getFormattedPhoneNumber();
	}

	/**
	 * @return boolean
	 */
	public boolean isValid()
	{
		return valid;
	}

	public Object clone()
	{
		PhoneNumber phone = new PhoneNumber("");
		phone.setAreaCode(this.areaCode);
		phone.setLast4Digit(this.last4Digit);
		phone.setPrefix(this.prefix);
		return phone;
	}

	/**
	 * @return
	 */
	public String getExt()
	{
		if (ext != null)
		{
			return ext;
		}
		return ext;
	}

	/**
	 * @param string
	 */
	public void setExt(String string)
	{
		if (string == null)
		{
			string = "";
		}
		ext = string;
	}

	/**
	 * @return
	 */
	public String getPhoneId()
	{
		return phoneId;
	}

	/**
	 * @param string
	 */
	public void setPhoneId(String string)
	{
		phoneId = string;
	}

	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IPhoneNumber#getExtension()
	 */
	public String getExtension()
	{
		return getExt();
	}

	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IPhoneNumber#getFourDigit()
	 */
	public String getFourDigit()
	{
		return getLast4Digit();
	}

	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IPhoneNumber#setExtension(java.lang.String)
	 */
	public void setExtension(String string)
	{
		ext = string;

	}

	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IPhoneNumber#setFourDigit(java.lang.String)
	 */
	public void setFourDigit(String string)
	{
		setLast4Digit(string);

	}

}
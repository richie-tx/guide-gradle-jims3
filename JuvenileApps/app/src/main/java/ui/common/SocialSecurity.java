/*
 * Created on Aug 26, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common;

import messaging.contact.domintf.ISocialSecurity;

/**
 * @author ldeen
 *
 * K Lancaster	11/24/2004	Revised due to problems with Manage Uer
 * 
 */
public class SocialSecurity implements ISocialSecurity
{
	private String ssn1 = "";
	private String ssn2 = "";
	private String ssn3 = "";
	//private boolean valid = true;

	/**
	 * @param ssn
	 */
	public SocialSecurity(String ssn)
	{
		parseSSN(ssn);
	}

	/**
	 * @param ssn
	 */
	public SocialSecurity(String ssn, boolean validFlag)
	{
		parseSSN(ssn);
	}

	/**
	 * @param ssn1
	 * @param ssn2
	 * @param ssn3
	 */
	public SocialSecurity(String aSsn1, String aSsn2, String aSsn3)
	{
		if (isValid(aSsn1, aSsn2, aSsn3))
		{
			this.ssn1 = aSsn1;
			this.ssn2 = aSsn2;
			this.ssn3 = aSsn3;
		}
	}

	public boolean isValid()
	{		
		return SocialSecurity.isValid(this.ssn1, this.ssn2, this.ssn3);
	}

	public static boolean isValid(String aSsn1, String aSsn2, String aSsn3)
	{
		boolean ret = false;
		if (aSsn1 != null && aSsn2 != null && aSsn3 != null)
		{
			if (aSsn1.length() == 3 && aSsn2.length() == 2 && aSsn3.length() == 4)
			{
				ret = true;
			}
		}
		return ret;
	}

	/**
	 * 
	 */
	public void clear()
	{
		ssn1 = "";
		ssn2 = "";
		ssn3 = "";
	}

	/**
	 * @return social
	 */
	public String getSSN()
	{
		if (isValid())
		{
			StringBuffer ssn = new StringBuffer();
			ssn = ssn.append(ssn1).append(ssn2).append(ssn3);
			return ssn.toString();
		}
		else
		{
			return "";
		}
	}

	/**
	 * @return formated social
	 */
	public String getFormattedSSN()
	{
		if (isValid())
		{
			StringBuffer ss = new StringBuffer();
			ss = ss.append(ssn1).append("-").append(ssn2).append("-").append(ssn3);
			return ss.toString();
		}
		else
		{
			return "";
		}
	}
	
	public String getMaskedSSN(){
	    String maskedSSN = "";
	    
	    if (isValid())
		{
		
		   if (!getSSN().equals("666666666") && !getSSN().equals("777777777") && !getSSN().equals("888888888") && !getSSN().equals("999999999"))//Individual has never had a social security number.
    		    {
    			maskedSSN = "XXX-XX-" + getSSN().substring(5);
    		    } 
		   else {
		   
		       maskedSSN = getSSN();
		   }
    			    		   
		}
	    
	    return maskedSSN;
	}

	/**
	 * @return ssn (length 3)
	 */
	public String getSSN1()
	{
		return ssn1;
	}

	/**
	 * @return ssn (length 2)
	 */
	public String getSSN2()
	{
		return ssn2;
	}

	/**
	 * @return ssn (length 4)
	 */
	public String getSSN3()
	{
		return ssn3;
	}

	/**
	 * @param string
	 */
	public void setSSN1(String string)
	{
		if ( string == null || string.trim().length() == 0 )
		{
			ssn1 = "";
		}
		else
		{
			ssn1 = string;
		}
	}

	/**
	 * @param string
	 */
	public void setSSN2(String string)
	{
		if ( string == null || string.trim().length() == 0 )
		{
			ssn2 = "";
		}
		else
		{
			ssn2 = string;
		}
	}

	/**
	 * @param string
	 */
	public void setSSN3(String string)
	{
		if ( string == null || string.trim().length() == 0 )
		{
			ssn3 = "";
		}
		else
		{
			ssn3 = string;
		}
	}

	/**
	 * @param string
	 */
	public void setSSN(String aSsn)
	{
		parseSSN(aSsn);
	}

	/**
	 * @param ssn
	 */
	private void parseSSN(String ssn)
	{
		if (ssn != null && ssn.length() > 8)
		{
			String formatedValue = ssn.substring(3, 7);
			if (ssn.length() == 9)
			{
				// set the individual fields too
				ssn1 = ssn.substring(0, 3);
				ssn2 = ssn.substring(3, 5);
				ssn3 = ssn.substring(5, 9);
			}
			else
				if (formatedValue.startsWith("-") && formatedValue.endsWith("-"))
				{
					ssn1 = ssn.substring(0, 3);
					ssn2 = ssn.substring(4, 6);
					ssn3 = ssn.substring(7, 11);

				}
		}
	}
	
	public String getSsn1(){
		return getSSN1();		
	}
	public String getSsn2(){
		return getSSN2();		
	}
	public String getSsn3(){
		return getSSN3();		
	}
	
	public void setSsn1(String string){
		setSSN1(string);
	}
	public void setSsn2(String string){
		setSSN2(string);
	}
	public void setSsn3(String string){
		setSSN3(string);
	}

	/**
	 * @see Object#toString()
	 * @return string formated ssn
	 */
	public String toString()
	{
		return getFormattedSSN();
	}
	//
	//	/**
	//	 * @return boolean
	//	 */
	//	public boolean isValid()
	//	{
	//		return valid;
	//	}

	//	/**
	//	 * @param b
	//	 */
	//	private void setValid(boolean b)
	//	{
	//		valid = b;
	//	}
	
	

}

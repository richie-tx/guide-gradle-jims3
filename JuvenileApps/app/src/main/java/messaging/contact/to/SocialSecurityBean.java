/*
 * Created on Mar 1, 2006
 *
 */
package messaging.contact.to;

import messaging.contact.domintf.ISocialSecurity;

/**
 * @author Jim Fisher
 *
 */
public class SocialSecurityBean implements ISocialSecurity
{
	private String ssn1;
	private String ssn2;
	private String ssn3;

	public SocialSecurityBean(String ssn)
		{
			parseSSN(ssn);
		}

	public SocialSecurityBean(String ssn1, String ssn2, String ssn3)
	{
		this.ssn1 = ssn1;
		this.ssn2 = ssn2;
		this.ssn3 = ssn3;
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
			else{
				ssn1="";
				ssn2="";
				ssn3="";
				
			}
		}

	/**
	 * @return
	 */
	public String getSsn1()
	{
		return ssn1;
	}

	/**
	 * @return
	 */
	public String getSsn2()
	{
		return ssn2;
	}

	/**
	 * @return
	 */
	public String getSsn3()
	{
		return ssn3;
	}

	/**
	 * @param string
	 */
	public void setSsn1(String string)
	{
		ssn1 = string;
	}

	/**
	 * @param string
	 */
	public void setSsn2(String string)
	{
		ssn2 = string;
	}

	/**
	 * @param string
	 */
	public void setSsn3(String string)
	{
		ssn3 = string;
	}

	public String toString()
	{
		return ssn1 + ssn2 + ssn3;
	}
	
	public String getFormattedSsn()
	{
		if(ssn1 == null || ssn1.trim().length() < 0 || 
				(ssn1.length() + ssn2.length() + ssn3.length()) < 8)
			return "";
		
		StringBuffer sb = new StringBuffer();
		sb.append(ssn1);
		sb.append("-");
		sb.append(ssn2);
		sb.append("-");
		sb.append(ssn3);
		
		return sb.toString();
	}
	

}

/*
 * Created on Mar 1, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common.format;

import java.text.ParseException;

import messaging.contact.domintf.ISocialSecurity;

/**
 * @author Jim Fisher
 *
 */
public class SocialSecurityFormatter implements IFormat
{
	public static final char SSN1 = 'A';
	public static final char SSN2 = 'B';
	public static final char SSN3 = 'C';
	
	private String format;
	
	public static final String SSN_NO_DASH_FORMAT = "ABC";
	public static final String SSN_DASH_FORMAT = "A-B-C";
	
	private boolean valid = true;
	
	public SocialSecurityFormatter(String format)
	{
		this.format = format;
	}

	/* (non-Javadoc)
	 * @see ui.common.format.IFormat#format(java.lang.Object)
	 */
	public String format(Object aSsn) throws IllegalArgumentException
	{
		if ((aSsn instanceof ISocialSecurity) == false)
		{
			throw new IllegalArgumentException("ssn parameter must implement " + aSsn.getClass().getName());
		}

		ISocialSecurity ssn = (ISocialSecurity) aSsn;

		String formattedString = null;

	if(ssn.getSsn1()!=null && !(ssn.getSsn1().equals("")) &&
		ssn.getSsn2()!=null && !(ssn.getSsn2().equals("")) &&
		ssn.getSsn3()!=null && !(ssn.getSsn3().equals(""))){
		if (format != null)
		{
			StringBuffer buffer = new StringBuffer();
			char[] chars = format.toCharArray();
			
				for (int i = 0; i < chars.length; i++)
				{
					char ch = chars[i];
					if (ch == SSN1)
					{
						buffer.append(ssn.getSsn1());
					}
					else if (ch == SSN2)
					{
						buffer.append(ssn.getSsn2());
					}
					else if (ch == SSN3)
					{
						buffer.append(ssn.getSsn3());
					}				
					else
					{
						buffer.append(ch);
					}
				}
			formattedString = buffer.toString();
		}
		}
		else
			formattedString="";
		
		return formattedString;
	}

	/* (non-Javadoc)
	 * @see ui.common.format.IFormat#parse(java.lang.String)
	 */
	public Object parse(String str)
	{
		throw new UnsupportedOperationException("SocialSecurityFormatter.parse is not supported.");
	}

	/* (non-Javadoc)
	 * @see ui.common.format.IFormat#isValid()
	 */
	public boolean isValid()
	{
		return valid;
	}

}

/*
 * Created on Mar 1, 2006
 *
 */
package ui.common.format;

import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.to.PhoneNumberBean;

/**
 * @author Jim Fisher
 *
 */
public class PhoneNumberFormatter implements IFormat
{
	public static final String PHONE_DASH_FORMAT = "A-B-C";
	public static final String PHONE_NO_DASH_FORMAT = "ABC";
	public static final String PHONE_PARENS_FORMAT = "(A) B-C";
	public static final String PHONE_PARENS_NO_SPACE_FORMAT = "(A)B-C";
	public static final String PHONE_DOT_FORMAT = "A.B.C";

	private static final char AREA_CODE_CHAR = 'A';
	private static final char PREFIX_CHAR = 'P';
	private static final char FOUR_DIGIT_CHAR = 'F';
	private static final char EXTENSION_CHAR = 'X';

	private String format;

	private boolean valid = true;

	public PhoneNumberFormatter(String format)
	{
		this.format = format;
	}

	/* (non-Javadoc)
	 * @see ui.common.format.IFormat#format(java.lang.Object)
	 */
	public String format(Object aPhoneNumber) throws IllegalArgumentException
	{
		if ((aPhoneNumber instanceof IPhoneNumber) == false)
		{
			throw new IllegalArgumentException("name parameter must implement " + aPhoneNumber.getClass().getName());
		}

		IPhoneNumber phoneNumber = (IPhoneNumber) aPhoneNumber;

		String formattedString = "";

		if (format != null)
		{
			boolean atLeastOneRealChar=false;
			StringBuffer buffer = new StringBuffer();
			char[] chars = format.toCharArray();
			for (int i = 0; i < chars.length; i++)
			{
				char ch = chars[i];
				int preBuf=buffer.length();
				if (ch == AREA_CODE_CHAR)
				{
					buffer.append(phoneNumber.getAreaCode());
					if(!atLeastOneRealChar && buffer.length()!=preBuf)
						atLeastOneRealChar=true;
				}
				else if (ch == PREFIX_CHAR)
				{
					buffer.append(phoneNumber.getPrefix());
				//	buffer.append(phoneNumber.getAreaCode());
					if(!atLeastOneRealChar && buffer.length()!=preBuf)
						atLeastOneRealChar=true;
				}
				else if (ch == FOUR_DIGIT_CHAR)
				{
					buffer.append(phoneNumber.getFourDigit());
			//		buffer.append(phoneNumber.getAreaCode());
					if(!atLeastOneRealChar && buffer.length()!=preBuf)
						atLeastOneRealChar=true;
				}
				else if (ch == EXTENSION_CHAR)
				{
					buffer.append(phoneNumber.getExtension());
			//		buffer.append(phoneNumber.getAreaCode());
					if(!atLeastOneRealChar && buffer.length()!=preBuf)
						atLeastOneRealChar=true;
				}
				else
				{
					buffer.append(ch);
				}
			}
			if(atLeastOneRealChar)
				formattedString = buffer.toString();
			else
				formattedString="";
		}

		return formattedString;
	}

	/* (non-Javadoc)
	 * @see ui.common.format.IFormat#parse(java.lang.String)
	 */
	public Object parse(String str)
	{
		IPhoneNumber phone = null;

		this.valid = false;
		String areaCode = null;
		String prefix = null;
		String fourDigit = null;

		if (str != null)
		{
			if (PHONE_DASH_FORMAT.equals(format) && str.length() == 12)
			{
				areaCode = str.substring(0, 3);
				prefix = str.substring(4, 7);
				fourDigit = str.substring(8, 12);
				this.valid = true;
			}
			else if (PHONE_NO_DASH_FORMAT.equals(format) && str.length() == 10)
			{
				areaCode = str.substring(0, 3);
				prefix = str.substring(3, 6);
				fourDigit = str.substring(6, 10);
				this.valid = true;
			}
			else
			{
				this.valid = false;
			}
		}
		else
		{
			this.valid = false;
		}

		if (this.valid == true)
		{
			phone = new PhoneNumberBean(areaCode, prefix, fourDigit);
		}
		else
		{
			phone = new PhoneNumberBean();
		}

		return phone;
	}

	/* (non-Javadoc)
	 * @see ui.common.format.IFormat#isValid()
	 */
	public boolean isValid()
	{
		return this.valid;
	}

}

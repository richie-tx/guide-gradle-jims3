/*
 * Created on Mar 1, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common.format;

import java.text.ParseException;

import messaging.contact.domintf.IName;

/**
 * @author Jim Fisher
 * 
 * The formatting logic was taken from the ui.common.Name bean.  It may be useful
 * to extend the java.text.Format class for enhanced capability in the future.
 *
 */
public class NameFormatter implements IFormat
{
	private String format;

	private static final char PREFIX_CHAR = 'P';
	private static final char LAST_CHAR = 'L';
	private static final char MIDDLE_CHAR = 'M';
	private static final char FIRST_CHAR = 'F';
	private static final char SUFFIX_CHAR = 'S';
	
	private boolean valid = true;

	public NameFormatter(String format)
	{
		this.format = format;
	}

	public String format(Object aName)
	{
		if ((aName instanceof IName) == false)
		{
			throw new IllegalArgumentException("name parameter must implement " + aName.getClass().getName());
		}

		IName name = (IName) aName;

		String formattedString = "";
		String separationChar="";
		String tempStr="";
		if (format != null)
		{
			boolean atLeastOneRealChar=false;
			StringBuffer buffer = new StringBuffer();
			char[] chars = format.toCharArray();
			for (int i = 0; i < chars.length; i++)
			{
				
				char ch = chars[i];
				int preBuf=buffer.length();
				if (ch == PREFIX_CHAR)
				{
					buffer.append(name.getPrefix());
					if(!atLeastOneRealChar && buffer.length()!=preBuf)
						atLeastOneRealChar=true;
				}
				else if (ch == FIRST_CHAR)
				{
					buffer.append(name.getFirstName());
					if(!atLeastOneRealChar && buffer.length()!=preBuf)
						atLeastOneRealChar=true;
				}
				else if (ch == MIDDLE_CHAR)
				{
					buffer.append(name.getMiddleName());
					if(!atLeastOneRealChar && buffer.length()!=preBuf)
						atLeastOneRealChar=true;
				}
				else if (ch == LAST_CHAR)
				{
					buffer.append(name.getLastName());
					if(!atLeastOneRealChar && buffer.length()!=preBuf)
						atLeastOneRealChar=true;
				}
				else if (ch == SUFFIX_CHAR)
				{
					buffer.append(name.getSuffix());
					if(!atLeastOneRealChar && buffer.length()!=preBuf)
						atLeastOneRealChar=true;
				}
				else
				{
					buffer.append(ch);
					if(ch!=' '){
						separationChar=String.valueOf(ch);
					}
				}
			}
			if(atLeastOneRealChar)
				formattedString = buffer.toString();
			else
				formattedString="";
		}
		if(formattedString!=null) {
			tempStr = formattedString.trim();
			if (tempStr.lastIndexOf(",") == tempStr.length() - 1){
				formattedString = tempStr.substring(0, tempStr.length() - 1);
			}			
			if (tempStr.equals(separationChar)){
				formattedString="";
			}
		}
		
		return formattedString;
	}

	/* (non-Javadoc)
	 * @see ui.common.format.Format#parse(java.lang.String)
	 */
	public Object parse(String str)
	{
		throw new UnsupportedOperationException("NameFormatter.parse is not supported.");
	}

	/* (non-Javadoc)
	 * @see ui.common.format.IFormat#isValid()
	 */
	public boolean isValid()
	{
		// TODO Auto-generated method stub
		return valid;
	}

}

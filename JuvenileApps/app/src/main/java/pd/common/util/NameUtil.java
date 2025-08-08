package pd.common.util;

import java.util.StringTokenizer;

import pd.km.util.Name;


/**
 * @author jmcnabb
 *
 */
public final class NameUtil
{
	/**
	 * 
	 */
	private NameUtil()
	{
		super();
	}

	/**
	 * Returns a name as pd.km.util.Name with the given full, formatted name.
	 * @param formattedName
	 * @return
	 */
	public static Name getNameFromString(String formattedName)
	{
		Name nameObj = new Name();

		if (formattedName != null && !formattedName.equals(""))
		{
			try
			{
				StringTokenizer strTok = new StringTokenizer(formattedName, " ");
				
				String lastName = strTok.nextToken();
				lastName = lastName.substring(0, lastName.length()-1);
				String firstName = strTok.nextToken();
				String middleName = "";
				if (strTok.hasMoreTokens())
				{
					middleName = strTok.nextToken();
				}
				nameObj.setLastName(lastName);
				nameObj.setFirstName(firstName);
				nameObj.setMiddleName(middleName);
			}
			catch (Exception e)
			{
				// do something
			}
		}
		return nameObj;
	}
}

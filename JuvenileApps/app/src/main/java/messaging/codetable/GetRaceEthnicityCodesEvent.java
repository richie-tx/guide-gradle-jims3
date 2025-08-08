/*
 * Created on Jul 15, 2005
 *
 */
package messaging.codetable;

import mojo.km.messaging.RequestEvent;

/**
 * @author ryoung
 *
 */
public class GetRaceEthnicityCodesEvent extends RequestEvent
{
	private String oidDerived;
	private String code;
	private String category;
	/**
	 * @return
	 */
	public String getCategory()
	{
		return category;
	}

	/**
	 * @param string
	 */
	public void setCategory(String string)
	{
		category = string;
	}

	/**
	 * @return
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @return
	 */
	public String getOIDDerived()
	{
		String categoryPadded = paddingString(this.category, 3, ' ');
		String codePadded = paddingString(this.code, 10, ' ');

		return categoryPadded + codePadded;
	}

	/**
	 * @param string
	 */
	public void setCode(String string)
	{
		code = string;
	}

	public String paddingString(String s, int n, char c)
	{
		if (s == null)
		{
			s = new String();
		}
		StringBuffer str = new StringBuffer(s);
		int strLength = str.length();
		if (n > 0 && n > strLength)
		{
			for (int i = 0; i <= n; i++)
			{
				if (i > strLength)
				{
					str.append(c);
				}

			}
		}
		return str.toString();
	}

}

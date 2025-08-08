/*
 * Created on Jan 25, 2005
 */
package pd.km.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

/**
 * @author glyons
 */
public final class StringUtility
{
	private StringUtility() {}
	
	/**
	 * This method takes a string that has a delimineter and returns
	 * a collection representing each string without the deliminater
	 * @param work
	 * @param delim
	 * @return Collection
	 */
	public static Collection getStringCollection(String work, String delim)
	{
		ArrayList list = new ArrayList();
		StringTokenizer token = new StringTokenizer(work, delim);
		if (!token.hasMoreElements())
		{
			list.add(work);
		} //else {
			while (token.hasMoreElements())
			{
				list.add(token.nextToken());
			}
		//} 
		
		return (Collection) list;
	}
}

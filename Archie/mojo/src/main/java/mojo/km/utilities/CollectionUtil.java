/*
 * Created on Feb 2, 2006
 *
 */
package mojo.km.utilities;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jim Fisher
 *
 */
public class CollectionUtil
{
	public static List iteratorToList(Iterator i)
	{
		List list = new ArrayList();
		if (i != null)
		{
			while (i.hasNext())
			{
				list.add(i.next());
			}
		}
		return list;
	}

	public static List enumerationToList(Enumeration e)
	{
		List list = new ArrayList();
		if (e != null)
		{
			while (e.hasMoreElements())
			{
				list.add(e.nextElement());
			}
		}
		return list;
	}
}

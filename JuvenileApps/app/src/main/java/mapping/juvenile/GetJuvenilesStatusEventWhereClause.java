/*
 * Created on Jul 03, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.juvenile;

import java.util.Iterator;

import messaging.juvenile.GetJuvenileStatusEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class GetJuvenilesStatusEventWhereClause
{

	/**
	 * @param event
	 * @return sql
	 */
	public String getJuvenilesStatusWhereClause(IEvent anEvent)
	{
		GetJuvenileStatusEvent event = (GetJuvenileStatusEvent) anEvent;
		StringBuilder sql = new StringBuilder(100);

		sql.append("JUVENILE_ID IN(");

		Iterator i = event.getJuvenileNums().iterator();

		while (i.hasNext())
		{
			sql.append("'");
			sql.append((String) i.next());
			if (i.hasNext())
			{
				sql.append("',");
			}
			else
			{
				sql.append("'");
			}

		}
		sql.append(")");
		return sql.toString();
	}
}

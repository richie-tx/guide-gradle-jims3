/*
 */
package mapping.administerlocation;

import messaging.administerlocation.GetAllJuvLocationUnitsByJuvLocIdEvent;
import mojo.km.messaging.IEvent;


public class GetJuvLocationUnitByIdsWhereClause {

	
	
	public String getJuvLocationUnitByIdsClause( IEvent anEvent )
	{
		GetAllJuvLocationUnitsByJuvLocIdEvent mev = (GetAllJuvLocationUnitsByJuvLocIdEvent)anEvent;
		String juvLocUnitId = mev.getJuvLocUnitId();
		if (juvLocUnitId!=null && !juvLocUnitId.equals("")){
			StringBuffer buf = new StringBuffer("JUVLOCUNIT_ID IN (");
			buf.append(juvLocUnitId);
			buf.append(")");
			return buf.toString();
		}
		return null;
	}	
	

}

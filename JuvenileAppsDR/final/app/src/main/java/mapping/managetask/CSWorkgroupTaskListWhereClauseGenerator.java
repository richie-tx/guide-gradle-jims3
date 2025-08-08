package mapping.managetask;

import messaging.managetask.GetCSTaskListEvent;
import mojo.km.messaging.IEvent;


public class CSWorkgroupTaskListWhereClauseGenerator
{

	Object[] arrayOfWorkgroupIds = null ; 
	
	public String getTaskListWhereClause(IEvent event)
	{
		GetCSTaskListEvent taskListEvent = (GetCSTaskListEvent) event ;
		StringBuffer whereClauseBuffer = new StringBuffer() ; 
		whereClauseBuffer.append("WORKGROUP_ID IN ( ") ;
		this.arrayOfWorkgroupIds = taskListEvent.getArrayOfWorkgroupIds() ;
		int arrayLength = this.arrayOfWorkgroupIds.length ; 
		int counter = 0 ; 
		for ( ; counter < arrayLength ; counter++  )
		{
			whereClauseBuffer.append( arrayOfWorkgroupIds[counter] );
			if (counter != ( arrayLength - 1) )
			{
				whereClauseBuffer.append( ",");  
			}
		}
		whereClauseBuffer.append(" )") ; 
		return whereClauseBuffer.toString() ; 
	}

}

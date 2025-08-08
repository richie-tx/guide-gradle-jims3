/*
 * Created on Dec 03, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.supervision;

import java.util.Iterator;
import java.util.Set;

import messaging.supervisionorder.GetOrderConditionAgencyPolicyEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetOrderConditionAgencyPolicyEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getOrderConditionAgencyPolicyEventWhereClause( IEvent event )
	{
    	GetOrderConditionAgencyPolicyEvent ocpEvent = (GetOrderConditionAgencyPolicyEvent) event;
		//Set orderConditionIds = ocpEvent.getOrderConditionIds();
    	Iterator iterator = null;
    	StringBuffer ids = new StringBuffer();
    	while(iterator.hasNext()){
    		String orderCondId = (String) iterator.next();
    		ids.append(orderCondId);
    		if(iterator.hasNext()){
    			ids.append(",");
    		}
    	}
    	
    	return this.buildSql(ids.toString());
	}
	
	/**
	 * @param string
	 * @return
	 */
	private String buildSql(String oderConditionIds) {
		StringBuffer sql = new StringBuffer();
		if(oderConditionIds != null && !oderConditionIds.trim().equals("")){
			sql.append("SPRVSNORDRCOND_ID IN ("); 
			sql.append(oderConditionIds); 
			sql.append( ") WITH UR" );
		}
		return sql.toString();		
	}
}

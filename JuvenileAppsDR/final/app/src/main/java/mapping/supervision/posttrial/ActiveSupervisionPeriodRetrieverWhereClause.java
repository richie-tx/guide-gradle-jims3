/*
 * Created on may 29, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.supervision.posttrial;

import messaging.posttrial.GetActiveSupervisionPeriodForSuperviseeEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActiveSupervisionPeriodRetrieverWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String buildWhereClause( IEvent event )
	{
    	GetActiveSupervisionPeriodForSuperviseeEvent aEvent = (GetActiveSupervisionPeriodForSuperviseeEvent) event;
		return this.buildSql(aEvent.getSpn());
	}
	
	/**
	 * @param contractName
	 * @param contractTypeId
	 * @param fundingProgranDescription
	 * @param fromDate
	 * @param toDate
	 * @param number
	 * @param agencyId
	 * @param contractId
	 * @param isExpired
	 * @return
	 */
	private String buildSql(String spn) {
		StringBuffer sql = new StringBuffer();
		if(spn != null && !spn.equals("")){
			sql.append(" DEFENDANT_ID IN("); 
			sql.append(spn); 
			sql.append( ") AND SPRVISIONENDDATE IS NULL" );
		}
		return sql.toString();		
	}
}

/*
 * Created on Dec 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mapping.supervision;

import java.util.Collection;
import java.util.Iterator;

import mapping.WhereClauseHelper;
import messaging.supervisionoptions.SearchSupervisionConditionsDetailsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SearchSupervisionConditionsWhereClause
{

	/**
	 * 
	 */
	public SearchSupervisionConditionsWhereClause()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public String getClause(IEvent anEvent)
	{
		SearchSupervisionConditionsDetailsEvent event = (SearchSupervisionConditionsDetailsEvent) anEvent;
		StringBuffer buf = new StringBuffer();

		// Group1
		String group1 = event.getGroup1(); 
		String subClause = WhereClauseHelper.buildBuildClause("int", false, false, group1, "GROUP1");
		buf = WhereClauseHelper.addToClause(buf, subClause);

		// Group2
		String group2 = event.getGroup2();
		subClause = WhereClauseHelper.buildBuildClause("int", false, false, group2, "GROUP2");
		buf = WhereClauseHelper.addToClause(buf, subClause);

		// Group3
		String group3 = event.getGroup3();
		subClause = WhereClauseHelper.buildBuildClause("int", false, false, group3, "GROUP3");
		buf = WhereClauseHelper.addToClause(buf, subClause);

		// supervision type
		String supervisionType = event.getSupervisionTypeId();
		subClause = WhereClauseHelper.buildBuildClause("String", true, false, supervisionType, "CODE");
		buf = WhereClauseHelper.addToClause(buf, subClause);

		Integer standard = event.isStandard() ? new Integer(1) : new Integer(0); 

		subClause = WhereClauseHelper.buildBuildClause("boolean", false, false,standard , "ISSTANDARD");
		buf = WhereClauseHelper.addToClause(buf, subClause);

		// agency
		String agencyId = event.getAgencyId();
		subClause = WhereClauseHelper.buildBuildClause("String", true, false, agencyId, "AGENCY_ID");
		buf = WhereClauseHelper.addToClause(buf, subClause);

		// effective date
		subClause = " GETDATE() >= effectivedate ";
		buf = WhereClauseHelper.addToClause(buf, subClause);
				
		// Courts
		Collection courts = event.getCourts(); 
		if ( courts.size() > 0 )
		{
			buf.append( " and court_id in('" ); 
			boolean hasFirst = false;
			Iterator iter = courts.iterator();
			while ( iter.hasNext() )
			{
				String courtId = (String)iter.next();
				if ( hasFirst )
				{
					buf.append( "','" ); 
				}
				buf.append( courtId ); 
				hasFirst = true;
			}
			buf.append( "')" );
		}

		return buf.toString();
	}


}

/*//
 * Created on Oct 5, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.supervision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.spnsplit.ProcessSpnSplitEvent;
import mojo.km.messaging.IEvent;

/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSupOrderCaseNumAgencyWhereClause {
    public final String COMMA = "', '";
    public final String CLOSEQUOTES = "')";
    public final String ANDSTR = " AND ";

	public String getByCaseNumIdsClause( IEvent anEvent )
    //public String getByCaseNumIdsClause(Map supOrderCaseNums )
	{
		ProcessSpnSplitEvent spnSplitEvent = (ProcessSpnSplitEvent)anEvent;
//		StringBuffer agencyBuf = new StringBuffer();
        StringBuffer caseNumBuf = new StringBuffer();

//        Map supOrderCaseNums = spnSplitEvent.getCaseNumsAgencyMap();
		StringBuffer buf = new StringBuffer();
        List caseNums = spnSplitEvent.getCases();
		if (caseNums.size() > 0)
		{
			buf.append( "CRIMINALCASE_ID IN(" ); 
			boolean hasFirst = false;
			Iterator iter = caseNums.iterator();
			while ( iter.hasNext() )
			{
				String caseId = (String)iter.next();
				if ( hasFirst )
				{
					buf.append( "," ); 
				}
				buf.append( "'" + caseId + "'"); 
				hasFirst = true;
			}
			buf.append( ")" );
		}

//        Iterator agency = supOrderCaseNums.keySet().iterator();
//        if(agency.hasNext())
//        {
//            agencyBuf.append(" AGENCYCD IN ('" );
//            caseNumBuf.append(" CRIMINALCASE_ID IN ('");
//            while(agency.hasNext())
//            {
//                String agencyCd = (String)agency.next();
//                agencyBuf.append(agencyCd);
//                Iterator caseNums = ((List)supOrderCaseNums.get(agencyCd)).iterator();
//                while(caseNums.hasNext())
//                {
//                    caseNumBuf.append((String)caseNums.next());
//                    if(caseNums.hasNext())
//                    {
//                        caseNumBuf.append(COMMA);
//                    }
//                }
//                if(agency.hasNext())
//                {
//                	agencyBuf.append(COMMA);
//                }
//            }
//            agencyBuf.append(CLOSEQUOTES + ANDSTR);
//            caseNumBuf.append(CLOSEQUOTES);
//        }
//        return agencyBuf.append(caseNumBuf.toString()).toString();
		return buf.toString();
	}
    
//    /**
//     * @param anEvent
//     * @return
//     */
//    public String getBySpnCaseIdsClause(IEvent anEvent)
//    {
//        StringBuffer  sb = new StringBuffer(getByCaseNumIdsClause(anEvent));
//        ProcessSpnSplitEvent spnSplitEvent = (ProcessSpnSplitEvent)anEvent;
//        sb.append(" AND DEFENDANT_ID = '").append(spnSplitEvent.getErroneousSpn()).append("' ");
//        
//        return sb.toString();
//        
//    }
    
    
//    public String getBySpnSupPeriodIdsClause(IEvent anEvent)
//    {
//        ProcessSpnSplitEvent spnSplitEvent = (ProcessSpnSplitEvent)anEvent;
//        StringBuffer sb  = new StringBuffer();
//        Iterator iter = spnSplitEvent.getPeriodIds().iterator();
//        sb.append(" SPRVSIONPERIOD_ID IN ('" );
//        while(iter.hasNext())
//        {
//        	sb.append((String)iter.next());
//            if(iter.hasNext())
//            {
//                sb.append(COMMA);
//            }
//        }
//        sb.append(CLOSEQUOTES).append(ANDSTR).append(" DEFENDANT_ID = '").append(spnSplitEvent.getErroneousSpn()).append("' ");
//        return sb.toString();
//    }
    
    
    public static void main(String []args)
    {
        //ProcessSpnSplitEvent spn = new ProcessSpnSplitEvent();
        List lt = new ArrayList();
        lt.add("123456");
        lt.add("4567788");
        List lt1 = new ArrayList();
        lt.add("09789875");
        lt.add("123389");
        Map map = new HashMap();
        map.put("CSC", lt);
        map.put("PTR", lt1);
        //spn.setSupOrderIds(map);
        
        GetSupOrderCaseNumAgencyWhereClause gt =  new GetSupOrderCaseNumAgencyWhereClause();
    }
	
}

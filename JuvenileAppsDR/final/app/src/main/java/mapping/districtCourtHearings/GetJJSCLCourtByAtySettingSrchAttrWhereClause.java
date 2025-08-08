package mapping.districtCourtHearings;

import messaging.districtCourtHearings.GetJJSCLCourtByAtySettingSrchAttrEvent;
import mojo.km.messaging.IEvent;
/**
 * 81390 changes
 * @author AnPillai
 *
 */
public class GetJJSCLCourtByAtySettingSrchAttrWhereClause
{

    public String getJJSCLCourtByAtySettingSrchAttrClause(IEvent anEvent)
    {
	StringBuffer buf = new StringBuffer(300);
	if(anEvent instanceof GetJJSCLCourtByAtySettingSrchAttrEvent)
    	{
        	GetJJSCLCourtByAtySettingSrchAttrEvent attEvent = (GetJJSCLCourtByAtySettingSrchAttrEvent) anEvent;        	
        	
        	String barNum = attEvent.getBarNumber();
        	String juvenileNum =attEvent.getJuvenileNumber();
	        String crtDate = attEvent.getCourtDate();
	        String todaysDate = attEvent.getTodaysDate();
	        String assignedAtty = attEvent.getAssignedAtty();
	        if ( todaysDate != null && !"".equals( todaysDate ) )
	        {
	            buf.append(" COURTDATE >= '");            
	            buf.append( todaysDate.trim() );
	            buf.append("'  AND HEARINGDISPOSITION IS NULL");
	        } 
	        if ( juvenileNum != null && !"".equals( juvenileNum ) )
	        {
	            buf.append(" AND JUVENILENUM = '");            
	            buf.append( juvenileNum.trim() );
	            buf.append("'");
	        } 
	        if ( crtDate != null && !"".equals( crtDate ) )
	        {
	            buf.append(" AND COURTDATE = '");            
	            buf.append( crtDate.trim() );
	            buf.append("'");
	        } 
	        
	        if ( assignedAtty != null && "1".equals( assignedAtty ) )
	        {
        	        if ( barNum != null && !"".equals( barNum ) )
        	        {
        	            buf.append(" AND BARNUM = '");            
        	            buf.append( barNum.trim());
        	            buf.append("'");
        	        } 
	        }
	        if ( assignedAtty != null && "2".equals( assignedAtty ) )
	        {
        	        if ( barNum != null && !"".equals( barNum ) )
        	        {
        	            buf.append(" AND SECONDATTYBAR = '");            
        	            buf.append( barNum.trim());
        	            buf.append("'");
        	        } 
	        }
	        if ( assignedAtty != null && "0".equals( assignedAtty ) )
	        {
        	        if ( barNum != null && !"".equals( barNum ) )
        	        {
        	            buf.append(" AND ( BARNUM = '");            
        	            buf.append( barNum.trim());
        	            buf.append("'");
        	            buf.append(" OR SECONDATTYBAR = '");            
        	            buf.append( barNum.trim());
        	            buf.append("' )");
        	        } 
	        }
    	}
	return buf.toString();
    }
}

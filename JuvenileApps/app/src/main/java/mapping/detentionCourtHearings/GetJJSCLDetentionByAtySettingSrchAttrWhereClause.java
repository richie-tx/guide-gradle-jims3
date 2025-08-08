package mapping.detentionCourtHearings;

import messaging.detentionCourtHearings.GetJJSCLDetentionByAtySettingSrchAttrEvent;
import mojo.km.messaging.IEvent;

/**
 * 
 * @author anpillai
 * added for user-story 81390
 *
 */
public class GetJJSCLDetentionByAtySettingSrchAttrWhereClause
{
    /**
     * 
     * Retrieve records within the date range.
     * 
     * @param anEvent
     * @return
     */
    public String getJJSCLDetentionByAtySettingSrchAttrClause(IEvent anEvent)
    {
	StringBuffer buf = new StringBuffer(300);
	if(anEvent instanceof GetJJSCLDetentionByAtySettingSrchAttrEvent)
    	{
	    GetJJSCLDetentionByAtySettingSrchAttrEvent attEvent = (GetJJSCLDetentionByAtySettingSrchAttrEvent) anEvent;        	
        	
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

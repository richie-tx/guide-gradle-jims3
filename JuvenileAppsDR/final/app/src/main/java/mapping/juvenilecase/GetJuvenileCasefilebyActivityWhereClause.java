/* 
 */
package mapping.juvenilecase;

import messaging.juvenilecase.GetJuvenileByCasefileActivityEvent;
import mojo.km.messaging.IEvent;

public class GetJuvenileCasefilebyActivityWhereClause
{

    public String getJuvenileCasefilebyActivityClause(IEvent anEvent)
    {
    	StringBuffer buf = new StringBuffer(300);
    	
    	if(anEvent instanceof GetJuvenileByCasefileActivityEvent)
    	{
    	    	GetJuvenileByCasefileActivityEvent juvInfo = (GetJuvenileByCasefileActivityEvent) anEvent;       
	        
	        String activityCategory = juvInfo.getActivityCategory();
	        String activityType = juvInfo.getActivityType();
	        String activityCode = juvInfo.getActivityCode();
	        String supervisionTypeId = juvInfo.getSupervisionTypeId();
	        String juvLocationId = juvInfo.getJuvLocationId();
	        String officerLastNameData = juvInfo.getOfficerLastNameData();
	        String officerFirstNameData = juvInfo.getOfficerFirstNameData();
	        String fromDate = juvInfo.getFromDate();
	        String toDate = juvInfo.getToDate()+" 23:59:59.0000000";
	        String systemActivities = juvInfo.getSystemActivities();
	        String closedCasefiles = juvInfo.getClosedCasefiles();
	        String juvNum = juvInfo.getJuvenileNumber();
	        
	        if ( activityCategory != null && !"".equals( activityCategory ) )
	        {
	            buf.append("CATEGORYCD = '");            
	            buf.append( activityCategory.trim());
	            buf.append("'");
	        } 
	        
	        if ( activityType != null && !"".equals( activityType ) )
	        {
	            buf.append(" AND TYPECD = '");            
	            buf.append( activityType.trim() );
	            buf.append("'");
	        } 
	        if ( activityCode != null && !"".equals( activityCode ) )
	        {
	            buf.append(" AND ACTIVITYCD = '");            
	            buf.append( activityCode.trim() );
	            buf.append("'");
	        } 
	        if ( supervisionTypeId != null && !"".equals( supervisionTypeId ) )
	        {
	            buf.append(" AND SPRVSIONTYPECD = '");            
	            buf.append( supervisionTypeId.trim() );
	            buf.append("'");
	        } 
	        if ( juvLocationId != null && !"".equals( juvLocationId ) )
	        {	           
	            buf.append(" AND LOCATION_ID = '");            
	            buf.append( juvLocationId.trim() );
	            buf.append("'");
	        } 
	        if ( officerLastNameData != null && !"".equals( officerLastNameData ) )
	        {
	            //task 161071 - allow officer search by activity category
	            if(activityCategory == null || "".equals(activityCategory)){
	        	
	        	buf.append(" OFFICERLNAME LIKE '");            
		        buf.append( officerLastNameData.trim() );
		        buf.append("%'");
		            
	            } else {
	        	
	        	 buf.append(" AND OFFICERLNAME LIKE '");            
		         buf.append( officerLastNameData.trim() );
		         buf.append("%'");
	            }
	           
	        } 
	        if ( officerFirstNameData != null && !"".equals( officerFirstNameData ) )
	        {
	            buf.append(" AND OFFICERFNAME LIKE '");            
		    buf.append( officerFirstNameData.trim() );
		    buf.append("%'");
	            
	        } 
	        if ( fromDate != null && !"".equals( fromDate )&& toDate != null && !"".equals( toDate ) )
	        {	
	            //chnage the between
	            buf.append(" AND ACTIVITYDATE >= '");            
	            buf.append( fromDate.trim() );
	            buf.append("' AND ACTIVITYDATE <= '");
	            buf.append( toDate.trim() );
	            buf.append("'");
	        } 
	        if ( juvNum != null && !"".equals( juvNum ) )
	        {	            
	            buf.append(" AND JUVENILE_ID = '");            
	            buf.append( juvNum.trim() );
	            buf.append("'");
	        } 
	        if ( systemActivities != null && !"".equals( systemActivities ) && "no".equals( systemActivities ))
	        {	            
	            buf.append(" AND (PERMISSIONCD is null or PERMISSIONCD <> 'S')");
	        } 
	        if ( closedCasefiles != null && !"".equals( closedCasefiles ) && "no".equals( closedCasefiles ))
	        {
	            buf.append(" AND CASESTATUSCD = 'A'");
	        }
	        else if ( closedCasefiles != null && !"".equals( closedCasefiles ) && "yes".equals( closedCasefiles ))
	        {
	            buf.append(" AND (CASESTATUSCD = 'A' OR CASESTATUSCD LIKE '%C%')");
	        }
	        
    	}
    	
	return buf.toString();
    } 
    
}

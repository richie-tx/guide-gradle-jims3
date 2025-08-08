/* 
 */
package mapping.juvenilecase;

import messaging.juvenilecase.GetCasefileWithReferralEvent;
import messaging.juvenilecase.GetCasefileWithReferralsEvent;
import messaging.juvenilecase.GetCourtActivityByYouthEvent;
import messaging.juvenilecase.GetFacilityAdmitReasonOffenseCdEvent;
import mojo.km.messaging.IEvent;

public class GetJuvenileCasefileReferralWhereClause
{

    public String getJuvenileReferralClause(IEvent anEvent)
    {
    	StringBuffer buf = new StringBuffer(200);
    	
    	if(anEvent instanceof GetCasefileWithReferralEvent){
    		GetCasefileWithReferralEvent juvInfo = (GetCasefileWithReferralEvent) anEvent;
	       
	        String juvNum = juvInfo.getJuvenileNum();
	        String refNum = juvInfo.getReferralNum();
	        String petNum = juvInfo.getPetitionNum();
	        
	        if ( juvNum != null && !"".equals( juvNum ) )
	        {
	            buf.append("JUVENILENUM = '");            
	            buf.append( juvNum.trim() );
	            buf.append( "'");
	        } 
	        
	        if ( refNum != null && !"".equals( refNum ) )
	        {
	        	buf.append(" AND");
	            buf.append(" REFERRALNUM = ");            
	            buf.append( refNum.trim() );
	        } 
	        
	        if ( petNum != null && !"".equals( petNum ) )
	        {
	            buf.append("PETITIONNUM = '");            
	            buf.append( petNum.trim() );
	            buf.append("'");
	        } 
    	}
    	
		return buf.toString();
    }
    
    public String getJuvenileReferralClauseX(IEvent anEvent)
    {
    	StringBuffer buf = new StringBuffer(200);
    	
    	if(anEvent instanceof GetCasefileWithReferralsEvent){
    		GetCasefileWithReferralsEvent juvInfo = (GetCasefileWithReferralsEvent) anEvent;
	       
	        String juvNum = juvInfo.getJuvenileNum();
	        String refNum = juvInfo.getReferralNum();
	        
	        if ( juvNum != null && !"".equals( juvNum ) )
	        {
	            buf.append("JUVENILENUM = '");            
	            buf.append( juvNum.trim() );
	            buf.append( "'");
	        } 
	        
	        if ( refNum != null && !"".equals( refNum ) )
	        {
	        	buf.append(" AND");
	            buf.append(" REFERRALNUM = ");            
	            buf.append( refNum.trim() );
	        } 
	        
    	}
    	
		return buf.toString();
    }
  

	
	public String getJuvenileOffenseClause(IEvent anEvent)
	{
		GetFacilityAdmitReasonOffenseCdEvent juvOffenseInfo = (GetFacilityAdmitReasonOffenseCdEvent) anEvent;
	    StringBuffer buf = new StringBuffer(200);
	   
	    String juvNum = juvOffenseInfo.getJuvenileNum();	   
	    String referralNum = juvOffenseInfo.getReferralNum();
        if ( juvNum != null && !"".equals( juvNum ) )
        {
            buf.append("JUVENILENUM = ");            
            buf.append( juvNum );
            
        }
        if ( referralNum != null && !"".equals( referralNum ) )
        {
            buf.append(" AND REFERRALNUM = ");            
            buf.append( referralNum );           
        }
        buf.append(" AND SEQNUM=1");
        return buf.toString();
	}
	
	public String getCourtActivityByYouthClause(IEvent anEvent)
	{
		GetCourtActivityByYouthEvent request = (GetCourtActivityByYouthEvent) anEvent;
	    StringBuffer buf = new StringBuffer(200);
	   
	    String juvNum = request.getJuvenileNum();	   
        if ( juvNum != null && !"".equals( juvNum ) )
        {
            buf.append("JUVENILENUM = ");            
            buf.append( juvNum );            
        }

        buf.append(" ORDER BY COURTDATE DESC");
        return buf.toString();
	}
	
}

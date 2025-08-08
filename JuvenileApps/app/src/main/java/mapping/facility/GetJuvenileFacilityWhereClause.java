package mapping.facility;

import messaging.facility.GetJuvenileFacilityDetailsEvent;
import mojo.km.messaging.IEvent;

public class GetJuvenileFacilityWhereClause
{
    public String getHeaderMaxTJPCSeqClause(IEvent anEvent)
    {
            
        StringBuffer whereClause = new StringBuffer(120);       
        whereClause.append("TJPCSEQNUM = (SELECT MAX (TJPCSEQNUM) FROM JIMS2.JJS_HEADER WHERE CREATEDATE > '2015-02-02')");
        return whereClause.toString();
    }
    public String getDetentionMaxTJPCSeqClause(IEvent anEvent)
    {
            
        StringBuffer whereClause = new StringBuffer(120);       
        whereClause.append("TJPCSEQNUM = (SELECT MAX (TJPCSEQNUM) FROM JIMS2.JJS_DETENTION WHERE CREATEDATE > '2015-02-02')");
        return whereClause.toString();
    }
    public String getCLDetMaxTJPCSeqClause(IEvent anEvent)
    {
            
        StringBuffer whereClause = new StringBuffer(120);       
        whereClause.append("TJPCSEQNUM = (SELECT MAX (TJPCSEQNUM) FROM JIMS2.JJSCLDETENTION)");
        return whereClause.toString();
    }
       
    public String getJuvenileFacilityClause(IEvent anEvent){

    	StringBuffer buf = new StringBuffer(200);
    	
    	if(anEvent instanceof GetJuvenileFacilityDetailsEvent){
	    	GetJuvenileFacilityDetailsEvent juvInfo = (GetJuvenileFacilityDetailsEvent) anEvent;
	    	String eventJuvnum=juvInfo.getJuvenileNum();
	        String juvNum=null;
	        if ( eventJuvnum != null && !"".equals( eventJuvnum ) )
	            juvNum = "\'"+juvInfo.getJuvenileNum()+"\'";
	        else
	            juvNum = juvInfo.getJuvenileNum();
	        String detentionId = juvInfo.getDetentionRecordId();
	        if ( juvNum != null && !"".equals( juvNum ) ){
	            buf.append("JUVENILENUM = ");
	            buf.append(juvNum);
	            if(juvInfo.getReferralNum() != null && !"".equals( juvInfo.getReferralNum() )){
	            	buf.append(" AND CURRENTREFERRAL = ");
	            	buf.append(juvInfo.getReferralNum());
	            }if(juvInfo.getFacilitySequenceNum()!= null && !"".equals( juvInfo.getFacilitySequenceNum() )){
	            	buf.append(" AND SEQNUM = ");
	            	buf.append(juvInfo.getFacilitySequenceNum());
	            	buf.append(" order by CREATEDATE desc");
	            }       
	        }else if ( detentionId != null && !"".equals( detentionId ) ){
	            buf.append("JJS_DETENTION_ID = ");
	            buf.append(detentionId); 
	        }       
    	}
    	return buf.toString();
    }
}

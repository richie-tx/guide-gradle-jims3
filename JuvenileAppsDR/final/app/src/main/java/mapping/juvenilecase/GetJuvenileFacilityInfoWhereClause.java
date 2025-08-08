/* 
 */
package mapping.juvenilecase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import messaging.juvenile.GetFacilityAdmitReasonPopEvent;
import messaging.juvenilecase.GetCourtActivityByYouthEvent;
import messaging.juvenilecase.GetCurrentFacilityCalCourtEvent;
import messaging.juvenilecase.GetCustomFacilityAdmitReasonPopEvent;
import messaging.juvenilecase.GetFacilityAdmitReasonOffenseCdEvent;
import messaging.juvenilecase.GetFacilityCurrentFacilityOfficerCdEvent;
import messaging.juvenilecase.GetFacilityCurrentPopEvent;
import messaging.juvenilecase.GetJJSJuvenileInfoEvent;
import messaging.juvenilecase.GetJuvenileDetentionFacilitiesByCodeEvent;
import messaging.juvenilecase.GetJuvenileFacilityHistoricalReceiptsEvent;
import messaging.juvenilecase.GetJuvenileInfoEvent;
import messaging.juvenilecase.GetPetitionOffenseCdEvent;
import mojo.km.messaging.IEvent;
import mojo.km.util.DateUtil;

public class GetJuvenileFacilityInfoWhereClause
{

    public String getJuvenileInfoClause(IEvent anEvent)
    {
	StringBuffer buf = new StringBuffer(200);
	if (anEvent instanceof GetFacilityAdmitReasonPopEvent)
	{
	    GetFacilityAdmitReasonPopEvent juvInfo = (GetFacilityAdmitReasonPopEvent) anEvent;

	    String juvNum = juvInfo.getJuvenileNum();
	    if (juvNum != null && !"".equals(juvNum))
	    {
		buf.append("JUVENILENUM = ");
		buf.append(juvNum);
	    }
	}
	else
	    if (anEvent instanceof GetJuvenileInfoEvent)
	    {
		GetJuvenileInfoEvent juvInfo = (GetJuvenileInfoEvent) anEvent;
		String juvNum = juvInfo.getJuvenileNum();
		if (juvNum != null && !"".equals(juvNum))
		{
		    buf.append("JUVENILENUM = ");

		    buf.append(juvNum);
		}
	    }
	    else
		if (anEvent instanceof GetJuvenileDetentionFacilitiesByCodeEvent)
		{

		    GetJuvenileDetentionFacilitiesByCodeEvent juvInfo = (GetJuvenileDetentionFacilitiesByCodeEvent) anEvent;
		    String juvNum = juvInfo.getJuvenileNum();
		    if (juvNum != null && !"".equals(juvNum))
		    {
			buf.append("JUVENILENUM = ");
			buf.append(juvNum);
		    }
		}
		else
		    if (anEvent instanceof GetJJSJuvenileInfoEvent)
		    {
			GetJJSJuvenileInfoEvent juvInfo = (GetJJSJuvenileInfoEvent) anEvent;
			String juvNum = juvInfo.getJuvenileNum();
			if (juvNum != null && !"".equals(juvNum))
			{
			    buf.append("JUVENILENUM = ");

			    buf.append(juvNum);
			}
		    }

	return buf.toString();
    }

    public String getJuvenileHeaderClause(IEvent anEvent)
    {
	GetFacilityAdmitReasonPopEvent juvInfo = (GetFacilityAdmitReasonPopEvent) anEvent;
	StringBuffer buf = new StringBuffer(200);

	String facilityCode = juvInfo.getFacilityId();
	if (facilityCode != null && !"".equals(facilityCode))
	{
	    buf.append("HEADERFACILITY = '");
	    buf.append(facilityCode);
	    buf.append("'");
	}
	buf.append(" AND (DETENTIONSTATUS='D' OR DETENTIONSTATUS='P' OR DETENTIONSTATUS='V' OR DETENTIONSTATUS='T')");
	return buf.toString();
    }

    public String getHeaderClause(IEvent anEvent)
    {
	GetJuvenileDetentionFacilitiesByCodeEvent juvInfo = (GetJuvenileDetentionFacilitiesByCodeEvent) anEvent;
	StringBuffer buf = new StringBuffer(200);
	String facilityCode = juvInfo.getFacilityCode();
	String searchType = juvInfo.getSearchType();
	if (facilityCode != null && !"".equals(facilityCode))
	{
	    buf.append("HEADERFACILITY = '");
	    buf.append(facilityCode);
	    buf.append("'");
	}
	if (searchType != null && searchType.equalsIgnoreCase("FP"))
	    buf.append(" AND (DETENTIONSTATUS='D' OR DETENTIONSTATUS='P' OR DETENTIONSTATUS='V' OR DETENTIONSTATUS='T')");
	else
	{
	    buf.append(" AND LCDATE>= DATEADD(YEAR, -1, CONVERT(DATE, GETDATE())) ");
	}
	return buf.toString();
    }

    public String getJuvenileDetentionClause(IEvent anEvent)
    {
	GetJuvenileDetentionFacilitiesByCodeEvent juvInfo = (GetJuvenileDetentionFacilitiesByCodeEvent) anEvent;
	StringBuffer buf = new StringBuffer(200);
	String facilitySeqNum = juvInfo.getFacilitySeqNum();
	String searchType = juvInfo.getSearchType();
	String facilityCode = juvInfo.getFacilityCode();
	String juvNum = juvInfo.getJuvenileNum();
	Calendar cal = Calendar.getInstance();
	cal.setTime(DateUtil.getCurrentDate());
	cal.add(Calendar.DAY_OF_YEAR, -2);
	Date dayBefore = cal.getTime();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	String s = sdf.format(dayBefore);
	int cutOffDate = Integer.valueOf(s);

	if (facilityCode != null && !"".equals(facilityCode))
	{
	    buf.append("HEADERFACILITY = '");
	    buf.append(facilityCode);
	    buf.append("'");
	}
	if (searchType != null && searchType.equalsIgnoreCase("FP"))
	    buf.append(" AND (HDETENTIONSTATUS='D' OR HDETENTIONSTATUS='P' OR HDETENTIONSTATUS='V' OR HDETENTIONSTATUS='T')");
	else
	{
	    buf.append(" AND HLCDATE>= DATEADD(YEAR, -1, CONVERT(DATE, GETDATE())) ");
	}
	/*   if ( facilitySeqNum != null && !"".equals( facilitySeqNum ) )
	   {
	       buf.append(" SEQNUM = ");            
	       buf.append( facilitySeqNum );           
	   }    
	   if ( juvNum != null && !"".equals( juvNum ) )
	   {
	       buf.append(" AND JUVENILENUM = '");            
	       buf.append( juvNum );      
	       buf.append( "'");
	   } 
	   if(searchType!=null && !searchType.equalsIgnoreCase("RS"))
	   	buf.append(" AND RELEASEDATE IS NULL ");          
	   */
	return buf.toString();
    }

    public String getJuvenileDetentionAdmitReasonClause(IEvent anEvent)
    {
	GetFacilityAdmitReasonPopEvent juvInfo = (GetFacilityAdmitReasonPopEvent) anEvent;
	StringBuffer buf = new StringBuffer(200);
	String facilitySeqNum = juvInfo.getFacilitySeqNum();
	String juvNum = juvInfo.getJuvenileNum();
	String facilityCode = juvInfo.getFacilityId();
	String securedFacility = juvInfo.getSecuredFacility();
	String admitReasonId = juvInfo.getAdmitReasonId();

	if (facilityCode != null && !"".equals(facilityCode))
	{
	    buf.append("HEADERFACILITY = '");
	    buf.append(facilityCode);
	    buf.append("'");
	}
	if (securedFacility != null && !"".equals(securedFacility))
	{
	    buf.append(" AND SECURESTATUS = '");
	    buf.append(securedFacility);
	    buf.append("'");
	}
	if (admitReasonId != null && !"".equals(admitReasonId))
	{
	    buf.append(" AND REASONCODE= '");
	    buf.append(admitReasonId);
	    buf.append("'");
	}

	buf.append(" AND (HDETENTIONSTATUS='D' OR HDETENTIONSTATUS='P' OR HDETENTIONSTATUS='V')");
	return buf.toString();
    }

    /**
     * 
     * @param anEvent
     * @return
     */
    public String getCustomFacilityAdmitReasonClause(IEvent anEvent)
    {
	GetCustomFacilityAdmitReasonPopEvent juvInfo = (GetCustomFacilityAdmitReasonPopEvent) anEvent;
	StringBuffer buf = new StringBuffer(200);
	String facilitySeqNum = juvInfo.getFacilitySeqNum();
	String juvNum = juvInfo.getJuvenileNum();
	String facilityCode = juvInfo.getFacilityId();
	String securedFacility = juvInfo.getSecuredFacility();
	String [] admitReasonIds = juvInfo.getAdmitReasonIds();

	if (facilityCode != null && !"".equals(facilityCode))
	{
	    buf.append("HEADERFACILITY = '");
	    buf.append(facilityCode);
	    buf.append("'");
	}
	if (securedFacility != null && !"".equals(securedFacility))
	{
	    buf.append(" AND SECURESTATUS = '");
	    buf.append(securedFacility);
	    buf.append("'");
	}
	if (admitReasonIds != null && admitReasonIds.length > 0 )
	{
	    buf.append(" AND REASONCODE in( '");
	    for (int i =0; i <  admitReasonIds.length; i++){
		if (i > 0 ) {
		    buf.append( "','" + admitReasonIds[i]);
		} else {
		    buf.append( admitReasonIds[i]);
		}
	    }
	    buf.append("')");
	}

	buf.append(" AND (HDETENTIONSTATUS='D' OR HDETENTIONSTATUS='P' OR HDETENTIONSTATUS='V')");
	return buf.toString();
    }

    public String getJuvenileOffenseClause(IEvent anEvent)
    {
	GetFacilityAdmitReasonOffenseCdEvent juvOffenseInfo = (GetFacilityAdmitReasonOffenseCdEvent) anEvent;
	StringBuffer buf = new StringBuffer(200);

	String juvNum = juvOffenseInfo.getJuvenileNum();
	String referralNum = juvOffenseInfo.getReferralNum();
	if (juvNum != null && !"".equals(juvNum))
	{
	    buf.append("JUVENILENUM = ");
	    buf.append(juvNum);

	}
	if (referralNum != null && !"".equals(referralNum))
	{
	    buf.append(" AND REFERRALNUM = ");
	    buf.append(referralNum);
	}
	buf.append(" AND SEQNUM=1");
	return buf.toString();
    }

    public String getCourtActivityByYouthClause(IEvent anEvent)
    {
	GetCourtActivityByYouthEvent request = (GetCourtActivityByYouthEvent) anEvent;
	StringBuffer buf = new StringBuffer(200);

	String juvNum = request.getJuvenileNum();
	if (juvNum != null && !"".equals(juvNum))
	{
	    buf.append("JUVENILENUM = ");
	    buf.append(juvNum);
	    // buf.append(" order BY CHAINNUM desc, SEQNUM desc");
	}
	return buf.toString();
    }

    public String getJuvenileInfoForCurrentPopClause(IEvent anEvent)
    {
	GetFacilityCurrentPopEvent juvInfo = (GetFacilityCurrentPopEvent) anEvent;
	StringBuffer buf = new StringBuffer(200);
	String juvNum = juvInfo.getJuvenileNum();
	if (juvNum != null && !"".equals(juvNum))
	{
	    buf.append("JUVENILENUM = ");
	    buf.append(juvNum);
	}
	return buf.toString();
    }

    public String getJuvenileHeaderForCurrentPopClause(IEvent anEvent)
    {
	GetFacilityCurrentPopEvent juvInfo = (GetFacilityCurrentPopEvent) anEvent;
	StringBuffer buf = new StringBuffer(200);

	String facilityCode = juvInfo.getFacilityCode();
	if (facilityCode != null && !"".equals(facilityCode))
	{
	    buf.append("HEADERFACILITY = '");
	    buf.append(facilityCode);
	    buf.append("'");
	}
	buf.append(" AND (HDETENTIONSTATUS='D' OR HDETENTIONSTATUS='P' OR HDETENTIONSTATUS='V' OR HDETENTIONSTATUS='T')");
	return buf.toString();
    }

    /*public String getJuvenileDetentionForCurrentPopClause(IEvent anEvent)
    {
    	GetFacilityCurrentPopEvent facilityInfo = (GetFacilityCurrentPopEvent) anEvent;
        StringBuffer buf = new StringBuffer(200);	    
        String facilitySeqNum = facilityInfo.getFacilitySeqNum();	   
        String facilityCode = facilityInfo.getFacilityCode();
        String juvNum = facilityInfo.getJuvenileNum();
        String referralNum = facilityInfo.getReferralNum();
    if ( facilitySeqNum != null && !"".equals( facilitySeqNum ) )
    {
        buf.append("SEQNUM = ");            
        buf.append( facilitySeqNum );           
    }     
    if ( referralNum != null && !"".equals( referralNum ) )
    {
        buf.append(" AND REFERRALNUM = ");            
        buf.append( referralNum );           
    }           
    if ( juvNum != null && !"".equals( juvNum ) )
    {
        buf.append(" AND JUVENILENUM = '");            
        buf.append( juvNum );      
        buf.append( "'");
    } 
    return buf.toString();
    }
    */
    public String getProbationOfficerIdClause(IEvent anEvent)
    {
	GetFacilityCurrentFacilityOfficerCdEvent juvOfficerInfo = (GetFacilityCurrentFacilityOfficerCdEvent) anEvent;
	StringBuffer buf = new StringBuffer(200);

	String juvNum = juvOfficerInfo.getJuvenileNum();
	String referralNum = juvOfficerInfo.getReferralNum();
	if (juvNum != null && !"".equals(juvNum))
	{
	    buf.append("JUVENILENUM = ");
	    buf.append(juvNum);
	}
	if (referralNum != null && !"".equals(referralNum))
	{
	    buf.append(" AND REFERRALNUM = ");
	    buf.append(referralNum);
	}
	return buf.toString();
    }

    public String getJuvenilePetitionOffenseClause(IEvent anEvent)
    {
	GetPetitionOffenseCdEvent juvOffenseInfo = (GetPetitionOffenseCdEvent) anEvent;
	StringBuffer buf = new StringBuffer(200);
	String juvNum = juvOffenseInfo.getJuvenileNum();
	String referralNum = juvOffenseInfo.getReferralNum();

	if (juvNum != null && !"".equals(juvNum))
	{
	    buf.append("JUVENILENUM = ");
	    buf.append(juvNum);

	}
	if (referralNum != null && !"".equals(referralNum))
	{
	    buf.append(" AND REFERRALNUM = ");
	    buf.append(referralNum);
	}
	return buf.toString();
    }

    public String getJuvenileCourtDateClause(IEvent anEvent)
    {
	GetCurrentFacilityCalCourtEvent juvClCourtInfo = (GetCurrentFacilityCalCourtEvent) anEvent;
	StringBuffer buf = new StringBuffer(200);
	String juvNum = juvClCourtInfo.getJuvenileNum();
	String referralNum = juvClCourtInfo.getReferralNum();
	String petitionNum = juvClCourtInfo.getPetitionNum();
	if (juvNum != null && !"".equals(juvNum))
	{
	    buf.append("JUVENILENUM = '");
	    buf.append(juvNum);
	    buf.append("'");
	}
	if (petitionNum != null && !"".equals(petitionNum))
	{
	    buf.append(" AND PETITIONNUM = '");
	    buf.append(petitionNum);
	    buf.append("'");
	}

	return buf.toString();
    }

    /**
     * Retrieve historical receipts based on detention facility and juvenile
     * number
     * 
     * @param anEvent
     * @return
     */
    public String getJuvenileFacilityHistoryReceiptsClause(IEvent anEvent)
    {
	GetJuvenileFacilityHistoricalReceiptsEvent historicalReceipts = (GetJuvenileFacilityHistoricalReceiptsEvent) anEvent;
	StringBuffer buf = new StringBuffer(200);
	String juvNum = historicalReceipts.getJuvenileNum();
	String facilityCode = historicalReceipts.getFacilityCode();
	if ((juvNum != null && !"".equals(juvNum)) && (facilityCode != null && !"".equals(facilityCode)))
	{
	    buf.append("JUVENILENUM = '");

	    buf.append(juvNum);

	    buf.append("' AND DETAINEDFACILITY = '");

	    buf.append(facilityCode);

	    buf.append("'");
	}
	return buf.toString();

    }

}

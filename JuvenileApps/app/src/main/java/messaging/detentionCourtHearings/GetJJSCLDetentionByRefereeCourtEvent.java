package messaging.detentionCourtHearings;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * U.S #11645 
 * @author sthyagarajan
 *
 */
public class GetJJSCLDetentionByRefereeCourtEvent extends RequestEvent{
	
	private Date courtDate; //hearingdate or courtDate
	private String courtId;
	private String facilityId;
	
	//US 73756
	private String recType;
	
	/**
	 * @return the courtDate
	 */
	public Date getCourtDate() {
		return courtDate;
	}
	/**
	 * @param courtDate the courtDate to set
	 */
	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}
	/**
	 * @return the courtId
	 */
	public String getCourtId() {
		return courtId;
	}
	/**
	 * @param courtId the courtId to set
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	public String getFacilityId()
	{
	    return facilityId;
	}
	public void setFacilityId(String facilityId)
	{
	    this.facilityId = facilityId;
	}
	/**
	 * @return the recType
	 */
	public String getRecType()
	{
	    return recType;
	}
	/**
	 * @param recType the recType to set
	 */
	public void setRecType(String recType)
	{
	    this.recType = recType;
	}
	
}

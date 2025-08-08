package messaging.cscdcalendar.reply;

import java.util.Comparator;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;


/**
 * 
 * @author cc_bjangay
 *
 */
public class CSEventsReportReponseEvent extends ResponseEvent
{
	private String csEventId;
	private String defendantId;
	private String defendantName;
	private String csEventTypeId;
	private String csEventTypeDesc;
	private Date csEventDate;
	private String fvPurpose;
	private String fvType;
	private String outcomeCd;
	private String resultUserId;
	private String resultPositionName;
	private String sexOffenderType;
	private String status;
	private String contactMethod;
	private Date endTime;
	private Date startTime;
		
	
	/**
	 * @return the csEventDate
	 */
	public Date getCsEventDate() {
		return csEventDate;
	}
	/**
	 * @param csEventDate the csEventDate to set
	 */
	public void setCsEventDate(Date csEventDate) {
		this.csEventDate = csEventDate;
	}
	/**
	 * @return the csEventId
	 */
	public String getCsEventId() {
		return csEventId;
	}
	/**
	 * @param csEventId the csEventId to set
	 */
	public void setCsEventId(String csEventId) {
		this.csEventId = csEventId;
	}
	/**
	 * @return the csEventTypeDesc
	 */
	public String getCsEventTypeDesc() {
		return csEventTypeDesc;
	}
	/**
	 * @param csEventTypeDesc the csEventTypeDesc to set
	 */
	public void setCsEventTypeDesc(String csEventTypeDesc) {
		this.csEventTypeDesc = csEventTypeDesc;
	}
	/**
	 * @return the defendantId
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId the defendantId to set
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @return the outcomeCd
	 */
	public String getOutcomeCd() {
		return outcomeCd;
	}
	/**
	 * @param outcomeCd the outcomeCd to set
	 */
	public void setOutcomeCd(String outcomeCd) {
		this.outcomeCd = outcomeCd;
	}
	/**
	 * @return the resultPositionName
	 */
	public String getResultPositionName() {
		return resultPositionName;
	}
	/**
	 * @param resultPositionName the resultPositionName to set
	 */
	public void setResultPositionName(String resultPositionName) {
		this.resultPositionName = resultPositionName;
	}
	/**
	 * @return the resultUserId
	 */
	public String getResultUserId() {
		return resultUserId;
	}
	/**
	 * @param resultUserId the resultUserId to set
	 */
	public void setResultUserId(String resultUserId) {
		this.resultUserId = resultUserId;
	}
	/**
	 * @return the defendantName
	 */
	public String getDefendantName() {
		return defendantName;
	}
	/**
	 * @param defendantName the defendantName to set
	 */
	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}
	/**
	 * @return the csEventTypeId
	 */
	public String getCsEventTypeId() {
		return csEventTypeId;
	}
	/**
	 * @param csEventTypeId the csEventTypeId to set
	 */
	public void setCsEventTypeId(String csEventTypeId) {
		this.csEventTypeId = csEventTypeId;
	}
	/**
	 * @return the fvPurpose
	 */
	public String getFvPurpose() {
		return fvPurpose;
	}
	/**
	 * @param fvPurpose the fvPurpose to set
	 */
	public void setFvPurpose(String fvPurpose) {
		this.fvPurpose = fvPurpose;
	}
	/**
	 * @return the fvType
	 */
	public String getFvType() {
		return fvType;
	}
	/**
	 * @param fvType the fvType to set
	 */
	public void setFvType(String fvType) {
		this.fvType = fvType;
	}
	/**
	 * @return the sexOffenderType
	 */
	public String getSexOffenderType() {
		return sexOffenderType;
	}
	/**
	 * @param sexOffenderType the sexOffenderType to set
	 */
	public void setSexOffenderType(String sexOffenderType) {
		this.sexOffenderType = sexOffenderType;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the contactMethod
	 */
	public String getContactMethod() {
		return contactMethod;
	}
	/**
	 * @param contactMethod the contactMethod to set
	 */
	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public static Comparator CSEventsReportComparator = new Comparator() {
		public int compare(Object startDate, Object otherStartDate) {
			
		  int result = 0;
		  Date bStartDate = ((CSEventsReportReponseEvent)startDate).getCsEventDate();
		  Date bOtherStartDate = ((CSEventsReportReponseEvent)otherStartDate).getCsEventDate();
		  
		  if(bStartDate == null)
		  {
			  result = -1;
		  }
		  else if(bOtherStartDate == null)
		  {
			  result = -1;
		  }
		  else 
		  {
			  result = bStartDate.compareTo(bOtherStartDate);
		  }
		  return result;
		}	
	};
}

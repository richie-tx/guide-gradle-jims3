// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\ResponseEvents\\CSFieldVisitResponseEvent.java

package messaging.cscdcalendar.reply;

import java.util.Date;
import java.util.List;

import messaging.contact.to.Address;
import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.DateUtil;

public class CSFieldVisitResponseEvent extends ResponseEvent {

	private String purposeCd;

	private String fvTypeCd;

	private String sexOffendarTypeCd;

	private String sexOffendarType;

	private String measureTypeCd;

	private String contactMethodCd;

	private String outcomeCd;

	private String eventStatusCd;

	private String fvIteneraryId;

	private String superviseeId;

	private String fvEventId;// OID

	private String superviseeSSN;

	private String superviseeSpn;

	private String superviseePhone;

	private String eventName;

	private String purpose;

	private String fvType;

	private String measureType;

	private String comments;

	private String conditions;

	private Date startTime;

	private Date endTime;

	private String altPhone;

	private String addrDesc;

	private String caution;

	private String narrative;

	private String contactMethod;

	private String superviseeName;

	private String keyMap;

	private String outcome;

	private String eventStatus;

	private String csEventId;

	private Date markedForDeleteOn;

	private String eventType;

	private Date eventDate;

	private String sequenceNum;

	private String otherPurpose;

	private String[] associateId;

	private Address superviseeAddress = new Address();

	private Address alternateAddress = new Address();

	private List offenses;

	private String startTime1;

	private String endTime1;

	private String AMPMId1;

	private String AMPMId2;
	
	private String levelOfSupervision;

	private String probationOfficeInd;
	
	private String formattedAlternatePhone;
	
	private String formattedSuperviseePhone;

	

	public void clear() {

	}

	/**
	 * @roseuid 47A2366F00D7
	 */
	public CSFieldVisitResponseEvent() {

	}
	
	public String getFormattedAlternatePhone(){
		String ph ="";
       if(altPhone!=null && altPhone.length() > 5){
    	   StringBuffer phNumber = new StringBuffer();
    	   phNumber = phNumber.append("(").append(altPhone.substring(0,3)).append(")").append(altPhone.substring(3,6)).append("-").append(altPhone.substring(6,10));
         ph = phNumber.toString();       
       }
       return ph;
    }

	public String getFormattedSuperviseePhone(){
		String ph ="";
		if(superviseePhone!= null && superviseePhone.length() > 9){
	    	   StringBuffer phNumber = new StringBuffer();
	    	   phNumber = phNumber.append("(").append(superviseePhone.substring(0,3)).append(") ").append(superviseePhone.substring(3,6)).append("-").append(superviseePhone.substring(6,superviseePhone.length()));
	         ph = phNumber.toString();       
	    }else if(superviseePhone!= null && superviseePhone.length() > 6){
    	   StringBuffer phNumber = new StringBuffer();
    	   phNumber = phNumber.append(superviseePhone.substring(0,3)).append("-").append(superviseePhone.substring(3,superviseePhone.length()));
         ph = phNumber.toString();       
       }
       return ph;
    }

	public String getStartTime1() {
		if (startTime1 == null) {
			if (startTime != null) {
				String sTime = DateUtil.dateToString(startTime, "hh:mm a");
				if ((sTime != null) && (!sTime.trim().equals(""))
						&& (sTime.length() > 5)) {
					startTime1 = sTime.substring(0, 5);
				}
			}
		}
		return startTime1;
	}

	public void setStartTime1(String startTime1) {
		this.startTime1 = startTime1;
	}

	public String getEndTime1() {
		if (endTime1 == null) {
			if (endTime != null) {
				String eTime = DateUtil.dateToString(endTime, "hh:mm a");
				if ((eTime != null) && (!eTime.trim().equals(""))
						&& (eTime.length() > 5)) {
					endTime1 = eTime.substring(0, 5);
				}
			}
		}
		return endTime1;
	}

	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}

	public String getAMPMId1() {
		if (AMPMId1 == null) {
			if (startTime != null) {
				String sTime = DateUtil.dateToString(startTime, "hh:mm a");
				if ((sTime != null) && (!sTime.trim().equals(""))
						&& (sTime.length() > 5)) {
					AMPMId1 = sTime.substring(5).trim();
				}
			}
		}
		return AMPMId1;
	}

	public void setAMPMId1(String id1) {
		AMPMId1 = id1;
	}

	public String getAMPMId2() {
		if (AMPMId2 == null) {
			if (endTime != null) {
				String eTime = DateUtil.dateToString(endTime, "hh:mm a");
				if ((eTime != null) && (!eTime.trim().equals(""))
						&& (eTime.length() > 5)) {
					AMPMId2 = eTime.substring(5).trim();
				}
			}
		}
		return AMPMId2;
	}

	public void setAMPMId2(String id2) {
		AMPMId2 = id2;
	}

	/**
	 * @return Returns the addrDesc.
	 */
	public String getAddrDesc() {
		return addrDesc;
	}

	/**
	 * @param addrDesc
	 *            The addrDesc to set.
	 */
	public void setAddrDesc(String addrDesc) {
		this.addrDesc = addrDesc;
	}

	/**
	 * @return Returns the altPhone.
	 */
	public String getAltPhone() {
		return altPhone;
	}

	/**
	 * @param altPhone
	 *            The altPhone to set.
	 */
	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}

	/**
	 * @return Returns the caution.
	 */
	public String getCaution() {
		return caution;
	}

	/**
	 * @param caution
	 *            The caution to set.
	 */
	public void setCaution(String caution) {
		this.caution = caution;
	}

	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return Returns the conditions.
	 */
	public String getConditions() {
		return conditions;
	}

	/**
	 * @param conditions
	 *            The conditions to set.
	 */
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	/**
	 * @return Returns the contactMethod.
	 */
	public String getContactMethod() {
		return contactMethod;
	}

	/**
	 * @param contactMethod
	 *            The contactMethod to set.
	 */
	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}

	/**
	 * @return Returns the endTime.
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            The endTime to set.
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return Returns the eventName.
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName
	 *            The eventName to set.
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return Returns the fvType.
	 */
	public String getFvType() {
		return fvType;
	}

	/**
	 * @param fvType
	 *            The fvType to set.
	 */
	public void setFvType(String fvType) {
		this.fvType = fvType;
	}

	/**
	 * @return Returns the fvIteneraryId.
	 */
	public String getFvIteneraryId() {
		return fvIteneraryId;
	}

	/**
	 * @param fvIteneraryId
	 *            The fvIteneraryId to set.
	 */
	public void setFvIteneraryId(String fvIteneraryId) {
		this.fvIteneraryId = fvIteneraryId;
	}

	/**
	 * @return Returns the measureType.
	 */
	public String getMeasureType() {
		return measureType;
	}

	/**
	 * @param measureType
	 *            The measureType to set.
	 */
	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}

	/**
	 * @return Returns the narrative.
	 */
	public String getNarrative() {
		return narrative;
	}

	/**
	 * @param narrative
	 *            The narrative to set.
	 */
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	/**
	 * @return Returns the purpose.
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @param purpose
	 *            The purpose to set.
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * @return Returns the sexOffendarType.
	 */
	public String getSexOffendarType() {
		return sexOffendarType;
	}

	/**
	 * @param sexOffendarType
	 *            The sexOffendarType to set.
	 */
	public void setSexOffendarType(String sexOffendarType) {
		this.sexOffendarType = sexOffendarType;
	}

	/**
	 * @return Returns the startTime.
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            The startTime to set.
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return Returns the superviseePhone.
	 */
	public String getSuperviseePhone() {
		return superviseePhone;
	}

	/**
	 * @param superviseePhone
	 *            The superviseePhone to set.
	 */
	public void setSuperviseePhone(String superviseePhone) {
		this.superviseePhone = superviseePhone;
	}

	/**
	 * @return Returns the superviseeSpn.
	 */
	public String getSuperviseeSpn() {
		return superviseeSpn;
	}

	/**
	 * @param superviseeSpn
	 *            The superviseeSpn to set.
	 */
	public void setSuperviseeSpn(String superviseeSpn) {
		this.superviseeSpn = superviseeSpn;
	}

	/**
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}

	/**
	 * @param superviseeId
	 *            The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}

	/**
	 * @return Returns the keyMap.
	 */
	public String getKeyMap() {
		return keyMap;
	}

	/**
	 * @param keyMap
	 *            The keyMap to set.
	 */
	public void setKeyMap(String keyMap) {
		this.keyMap = keyMap;
	}

	/**
	 * @return Returns the superviseeName.
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}

	/**
	 * @param superviseeName
	 *            The superviseeName to set.
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}

	/**
	 * @return Returns the superviseeSSN.
	 */
	public String getSuperviseeSSN() {
		return superviseeSSN;
	}

	/**
	 * @param superviseeSSN
	 *            The superviseeSSN to set.
	 */
	public void setSuperviseeSSN(String superviseeSSN) {
		this.superviseeSSN = superviseeSSN;
	}

	/**
	 * @return Returns the outcome.
	 */
	public String getOutcome() {
		return outcome;
	}

	/**
	 * @param outcome
	 *            The outcome to set.
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	/**
	 * @return Returns the fvEventId.
	 */
	public String getFvEventId() {
		return fvEventId;
	}

	/**
	 * @param fvEventId
	 *            The fvEventId to set.
	 */
	public void setFvEventId(String fvEventId) {
		this.fvEventId = fvEventId;
	}

	/**
	 * @return Returns the csEventId.
	 */
	public String getCsEventId() {
		return csEventId;
	}

	/**
	 * @param csEventId
	 *            The csEventId to set.
	 */
	public void setCsEventId(String csEventId) {
		this.csEventId = csEventId;
	}

	/**
	 * @return Returns the alternateAddress.
	 */
	public Address getAlternateAddress() {
		return alternateAddress;
	}

	/**
	 * @param alternateAddress
	 *            The alternateAddress to set.
	 */
	public void setAlternateAddress(Address alternateAddress) {
		this.alternateAddress = alternateAddress;
	}

	/**
	 * @return Returns the superviseeAddress.
	 */
	public Address getSuperviseeAddress() {
		return superviseeAddress;
	}

	/**
	 * @param superviseeAddress
	 *            The superviseeAddress to set.
	 */
	public void setSuperviseeAddress(Address superviseeAddress) {
		this.superviseeAddress = superviseeAddress;
	}

	/**
	 * @return Returns the contactMethodCd.
	 */
	public String getContactMethodCd() {
		return contactMethodCd;
	}

	/**
	 * @param contactMethodCd
	 *            The contactMethodCd to set.
	 */
	public void setContactMethodCd(String contactMethodCd) {
		this.contactMethodCd = contactMethodCd;
	}

	/**
	 * @return Returns the fvTypeCd.
	 */
	public String getFvTypeCd() {
		return fvTypeCd;
	}

	/**
	 * @param fvTypeCd
	 *            The fvTypeCd to set.
	 */
	public void setFvTypeCd(String fvTypeCd) {
		this.fvTypeCd = fvTypeCd;
	}

	/**
	 * @return Returns the outcomeCd.
	 */
	public String getOutcomeCd() {
		return outcomeCd;
	}

	/**
	 * @param outcomeCd
	 *            The outcomeCd to set.
	 * 
	 */
	public void setOutcomeCd(String outcomeCd) {
		this.outcomeCd = outcomeCd;
	}

	/**
	 * @return Returns the purposeCd.
	 */
	public String getPurposeCd() {
		return purposeCd;
	}

	/**
	 * @param purposeCd
	 *            the purposeCd to set.
	 * 
	 */
	public void setPurposeCd(String purposeCd) {
		this.purposeCd = purposeCd;
	}

	/**
	 * @return Returns the measureTypeCd.
	 */
	public String getMeasureTypeCd() {
		return measureTypeCd;
	}

	/**
	 * @param measureTypeCd
	 *            the measureTypeCd to set.
	 * 
	 */
	public void setMeasureTypeCd(String measureTypeCd) {
		this.measureTypeCd = measureTypeCd;
	}

	/**
	 * @return Returns the eventStatus.
	 */
	public String getEventStatus() {
		return eventStatus;
	}

	/**
	 * @param eventStatus
	 *            the eventStatus to set.
	 */
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	/**
	 * @return Returns the eventStatusCd.
	 */
	public String getEventStatusCd() {
		return eventStatusCd;
	}

	/**
	 * @param eventStatusCd
	 *            The eventStatusCd to set.
	 */
	public void setEventStatusCd(String eventStatusCd) {
		this.eventStatusCd = eventStatusCd;
	}

	/**
	 * @return Returns the sexOffendarTypeCd.
	 */
	public String getSexOffendarTypeCd() {
		return sexOffendarTypeCd;
	}

	/**
	 * @param sexOffendarTypeCd
	 *            The sexOffendarTypeCd to set.
	 */
	public void setSexOffendarTypeCd(String sexOffendarTypeCd) {
		this.sexOffendarTypeCd = sexOffendarTypeCd;
	}

	public Date getMarkedForDeleteOn() {
		return markedForDeleteOn;
	}

	public void setMarkedForDeleteOn(Date markedForDeleteOn) {
		this.markedForDeleteOn = markedForDeleteOn;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return Returns the otherPurpose.
	 */
	public String getOtherPurpose() {
		return otherPurpose;
	}

	/**
	 * @param otherPurpose
	 *            The otherPurpose to set.
	 */
	public void setOtherPurpose(String otherPurpose) {
		this.otherPurpose = otherPurpose;
	}

	/**
	 * @return Returns the associateId.
	 */
	public String[] getAssociateId() {
		return associateId;
	}

	/**
	 * @param associateId
	 *            The associateId to set.
	 */
	public void setAssociateId(String[] associateId) {
		this.associateId = associateId;
	}

	/**
	 * @return Returns the offenses.
	 */
	public List getOffenses() {
		return offenses;
	}

	/**
	 * @param offenses
	 *            The offenses to set.
	 */
	public void setOffenses(List offenses) {
		this.offenses = offenses;
	}
	
	public String getLevelOfSupervision() {
		return levelOfSupervision;
	}

	public void setLevelOfSupervision(String levelOfSupervision) {
		this.levelOfSupervision = levelOfSupervision;
	}

	public String getProbationOfficeInd() {
		return probationOfficeInd;
	}

	public void setProbationOfficeInd(String probationOfficeInd) {
		this.probationOfficeInd = probationOfficeInd;
	}
}

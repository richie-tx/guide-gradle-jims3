/*
 * Created on Aug 3, 2005
 *
 */
package messaging.juvenilewarrant.reply;

import java.util.Collection;
import java.util.Date;

/**
 * @author Jim Fisher
 *
 */
public class PetitionResponseEvent extends ChargeResponseEvent implements Comparable
{

	// TODO Change name of PetitionResponseEvent to IncidentResponseEvent

	private String juvenileNum;
	private String severity;
	private String amend;
	private Date petitionDate;
	private String petition_Date;
	private String daLogNum;
	private String CJISNum;
	private String CJISSuffixNum;
	private String lawEnforcementAgency;
	private Double totalPropertyLossAmount;
	private String ncicOffenseCode;
	private String petitionStatus;
	private String petitionType;
	private String recType;
	private String tjpcSeqNum;
	private String updateFlag;
	private String deleteFlag;
	private String OID;
	private String referralNum;
	private Date lastChangeDate;
	private Date lastChangeTime;
	private String last_Change_Date;
	private String last_Change_Time;
	private String lastChangeUser;
	private String petitionAllegation;
	private String petitionNum;
	private String sequenceNum;
	private String sequenceNumber;
	private String courtDate;
	private String selectedReferralNum;
	private Date referralDate;
	private String courtId;
	private String petitionStatusDescription;
	private String petitionAmendment;
	private String severitySubType;
	private Date terminationDate;
	private String termination_Date;
	private Date terminationCreateDate;
	private String termination_CreateDate;
	private String petCJISNum;
	
	private String courtResult;
	private String courtResultDesc;
	private String finalDisposition;
	private String finalDispositionDescription;
	private Collection offenses;
	private String DPSCode;

	

	/**
	 * @return
	 */
	public String getAmend()
	{
		return amend;
	}

	/**
	 * @return
	 */
	public String getCJISNum()
	{
		return CJISNum;
	}

	/**
	 * @return
	 */
	public String getCJISSuffixNum()
	{
		return CJISSuffixNum;
	}

	/**
	 * @return
	 */
	public String getDaLogNum()
	{
		return daLogNum;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return
	 */
	public String getLawEnforcementAgency()
	{
		return lawEnforcementAgency;
	}

	/**
	 * @return
	 */
	public Date getPetitionDate()
	{
		return petitionDate;
	}

	/**
	 * @return
	 */
	public String getSeverity()
	{
		return severity;
	}

	/**
	 * @return
	 */
	public Double getTotalPropertyLossAmount()
	{
		return totalPropertyLossAmount;
	}

	/**
	 * @param string
	 */
	public void setAmend(String string)
	{
		amend = string;
	}

	/**
	 * @param string
	 */
	public void setCJISNum(String string)
	{
		CJISNum = string;
	}

	/**
	 * @param string
	 */
	public void setCJISSuffixNum(String string)
	{
		CJISSuffixNum = string;
	}

	/**
	 * @param string
	 */
	public void setDaLogNum(String string)
	{
		daLogNum = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param string
	 */
	public void setLawEnforcementAgency(String string)
	{
		lawEnforcementAgency = string;
	}

	/**
	 * @param date
	 */
	public void setPetitionDate(Date date)
	{
		petitionDate = date;
	}

	/**
	 * @param string
	 */
	public void setSeverity(String string)
	{
		severity = string;
	}

	/**
	 * @param double1
	 */
	public void setTotalPropertyLossAmount(Double double1)
	{
		totalPropertyLossAmount = double1;
	}


	/**
	 * @return
	 */
	public String getNcicOffenseCode()
	{
		return ncicOffenseCode;
	}

	/**
	 * @param string
	 */
	public void setNcicOffenseCode(String string)
	{
		ncicOffenseCode = string;
	}

	/**
	 * @return the petitionStatus
	 */
	public String getPetitionStatus() {
		return petitionStatus;
	}

	/**
	 * @param petitionStatus the petitionStatus to set
	 */
	public void setPetitionStatus(String petitionStatus) {
		this.petitionStatus = petitionStatus;
	}

	/**
	 * @return the petitionType
	 */
	public String getPetitionType() {
		return petitionType;
	}

	/**
	 * @param petitionType the petitionType to set
	 */
	public void setPetitionType(String petitionType) {
		this.petitionType = petitionType;
	}

	public String getRecType()
	{
	    return recType;
	}

	public void setRecType(String recType)
	{
	    this.recType = recType;
	}

	public String getTjpcSeqNum()
	{
	    return tjpcSeqNum;
	}

	public void setTjpcSeqNum(String tjpcSeqNum)
	{
	    this.tjpcSeqNum = tjpcSeqNum;
	}
	
	public String getReferralNum()
	{
	    return referralNum;
	}

	public void setReferralNum(String referralNum)
	{
	    this.referralNum = referralNum;
	}

	public Date getLastChangeDate()
	{
	    return lastChangeDate;
	}

	public void setLastChangeDate(Date lastChangeDate)
	{
	    this.lastChangeDate = lastChangeDate;
	}

	public String getLastChangeUser()
	{
	    return lastChangeUser;
	}

	public void setLastChangeUser(String lastChangeUser)
	{
	    this.lastChangeUser = lastChangeUser;
	}
	
	/** Sort in order of descending severity
	 * @see Comparable#compareTo(Object)
	 */
	public int compareTo(Object petitionObj)
	{
	    int result =-1;
		String severityStr = ((PetitionResponseEvent) petitionObj).getSeverity();
		if( severityStr != null ){
		    
		    result = severity.compareTo(severityStr);
		}
		
		return -result;
	}

	public String getUpdateFlag()
	{
	    return updateFlag;
	}

	public void setUpdateFlag(String updateFlag)
	{
	    this.updateFlag = updateFlag;
	}

	public String getDeleteFlag()
	{
	    return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag)
	{
	    this.deleteFlag = deleteFlag;
	}
	public String getOID()
	{
	    return OID;
	}

	public void setOID(String oID)
	{
	    OID = oID;
	}

	public String getPetition_Date()
	{
	    return petition_Date;
	}

	public void setPetition_Date(String petition_Date)
	{
	    this.petition_Date = petition_Date;
	}

	public String getLast_Change_Date()
	{
	    return last_Change_Date;
	}

	public void setLast_Change_Date(String last_Change_Date)
	{
	    this.last_Change_Date = last_Change_Date;
	}

	public String getPetitionAllegation()
	{
	    return petitionAllegation;
	}

	public void setPetitionAllegation(String petitionAllegation)
	{
	    this.petitionAllegation = petitionAllegation;
	}
	

	public String getPetitionNum()
	{
	    return petitionNum;
	}

	public void setPetitionNum(String petitionNum)
	{
	    this.petitionNum = petitionNum;
	}
	

	public String getSequenceNum()
	{
	    return sequenceNum;
	}

	public void setSequenceNum(String sequenceNum)
	{
	    this.sequenceNum = sequenceNum;
	}

	public Date getLastChangeTime()
	{
	    return lastChangeTime;
	}

	public void setLastChangeTime(Date lastChangeTime)
	{
	    this.lastChangeTime = lastChangeTime;
	}

	public String getLast_Change_Time()
	{
	    return last_Change_Time;
	}

	public void setLast_Change_Time(String last_Change_Time)
	{
	    this.last_Change_Time = last_Change_Time;
	}

	public String getSequenceNumber()
	{
	    return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber)
	{
	    this.sequenceNumber = sequenceNumber;
	}
	
	public String getCourtDate()
	{
	    return courtDate;
	}

	public void setCourtDate(String courtDate)
	{
	    this.courtDate = courtDate;
	}
	
	public String getSelectedReferralNum()
	{
	    return selectedReferralNum;
	}

	public void setSelectedReferralNum(String selectedReferralNum)
	{
	    this.selectedReferralNum = selectedReferralNum;
	}

	public Date getReferralDate()
	{
	    return referralDate;
	}

	public void setReferralDate(Date referralDate)
	{
	    this.referralDate = referralDate;
	}

	public String getCourtId()
	{
	    return courtId;
	}

	public void setCourtId(String courtId)
	{
	    this.courtId = courtId;
	}

	public String getPetitionStatusDescription()
	{
	    return petitionStatusDescription;
	}

	public void setPetitionStatusDescription(String petitionStatusDescription)
	{
	    this.petitionStatusDescription = petitionStatusDescription;
	}

	public String getPetitionAmendment()
	{
	    return petitionAmendment;
	}

	public void setPetitionAmendment(String petitionAmendment)
	{
	    this.petitionAmendment = petitionAmendment;
	}

	public String getSeveritySubType()
	{
	    return severitySubType;
	}

	public void setSeveritySubType(String severitySubType)
	{
	    this.severitySubType = severitySubType;
	}

	public Date getTerminationDate()
	{
	    return terminationDate;
	}

	public void setTerminationDate(Date terminationDate)
	{
	    this.terminationDate = terminationDate;
	}

	public String getTermination_Date()
	{
	    return termination_Date;
	}

	public void setTermination_Date(String termination_Date)
	{
	    this.termination_Date = termination_Date;
	}
	
	public Date getTerminationCreateDate()
	{
	    return this.terminationCreateDate;
	}

	public void setTerminationCreateDate(Date terminationCreateDate)
	{
	    this.terminationCreateDate = terminationCreateDate;
	}
	
	public String getTermination_CreateDate()
	{
	    return this.termination_CreateDate;
	}

	public void setTermination_CreateDate(String termination_CreateDate)
	{
	    this.termination_CreateDate = termination_CreateDate;
	}
	
	//
	public String getPetCJISNum()
	{
	    return petCJISNum;
	}

	public void setPetCJISNum(String petCJISNum)
	{
	    this.petCJISNum = petCJISNum;
	}
	public String getCourtResult()
	{
	    return courtResult;
	}
	public void setCourtResult(String courtResult)
	{
	    this.courtResult = courtResult;
	}
	public String getCourtResultDesc()
	{
	    return courtResultDesc;
	}
	public void setCourtResultDesc(String courtResultDesc)
	{
	    this.courtResultDesc = courtResultDesc;
	}
	public String getFinalDisposition()
	{
	    return finalDisposition;
	}
	public void setFinalDisposition(String finalDisposition)
	{
	    this.finalDisposition = finalDisposition;
	}
	public String getFinalDispositionDescription()
	{
	    return finalDispositionDescription;
	}

	public void setFinalDispositionDescription(String finalDispositionDescription)
	{
	    this.finalDispositionDescription = finalDispositionDescription;
	}
	public Collection getOffenses()
	{
	    return offenses;
	}

	public void setOffenses(Collection offenses)
	{
	    this.offenses = offenses;
	}
	public String getDPSCode()
	{
	    return DPSCode;
	}

	public void setDPSCode(String dPSCode)
	{
	    DPSCode = dPSCode;
	}
}

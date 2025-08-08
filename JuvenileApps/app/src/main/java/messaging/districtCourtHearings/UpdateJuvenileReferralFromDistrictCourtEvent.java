package messaging.districtCourtHearings;

import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import mojo.km.messaging.RequestEvent;

/**
 * 
 * @author sthyagarajan
 *
 */
public class UpdateJuvenileReferralFromDistrictCourtEvent extends RequestEvent
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String courtId;
    private String courtDate;
    private String deleteFlag;
    private String courtResult;
    private String courtDisposition;
    private String originalCourtDisposition;
    private String originalCourtResult;
    private String juvenileNumber;
    private String referralNumber;
    private String transferTo;
    private String actionDate;
    private String isLastSetting;
    private String isOnlySetting;
    private String decisionType;
    private List<DocketEventResponseEvent> courtSettings;
    private String PDADate; 
    private String transferFlag;
    private String petitionNum;
    private String petitionStatus;
    
    
    
    /**
     * @return the courtResult
     */
    public String getCourtResult()
    {
        return courtResult;
    }
    /**
     * @param courtResult the courtResult to set
     */
    public void setCourtResult(String courtResult)
    {
        this.courtResult = courtResult;
    }
    /**
     * @return the courtDisposition
     */
    public String getCourtDisposition()
    {
        return courtDisposition;
    }
    /**
     * @param courtDisposition the courtDisposition to set
     */
    public void setCourtDisposition(String courtDisposition)
    {
        this.courtDisposition = courtDisposition;
    }
    /**
     * @return the originalCourtDisposition
     */
    public String getOriginalCourtDisposition()
    {
        return originalCourtDisposition;
    }
    /**
     * @param originalCourtDisposition the originalCourtDisposition to set
     */
    public void setOriginalCourtDisposition(String originalCourtDisposition)
    {
        this.originalCourtDisposition = originalCourtDisposition;
    }
    /**
     * @return the originalCourtResult
     */
    public String getOriginalCourtResult()
    {
        return originalCourtResult;
    }
    /**
     * @param originalCourtResult the originalCourtResult to set
     */
    public void setOriginalCourtResult(String originalCourtResult)
    {
        this.originalCourtResult = originalCourtResult;
    }
    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
  
    /**
     * @return the courtDate
     */
    public String getCourtDate()
    {
	return courtDate;
    }
    /**
     * @param courtDate the courtDate to set
     */
    public void setCourtDate(String courtDate)
    {
	this.courtDate = courtDate;
    }
    /**
     * @return the courtId
     */
    public String getCourtId()
    {
	return courtId;
    }
    /**
     * @param courtId the courtId to set
     */
    public void setCourtId(String courtId)
    {
	this.courtId = courtId;
    }
    /**
     * @return the deleteFlag
     */
    public String getDeleteFlag()
    {
	return deleteFlag;
    }
    /**
     * @param deleteFlag the deleteFlag to set
     */
    public void setDeleteFlag(String deleteFlag)
    {
	this.deleteFlag = deleteFlag;
    }
    /**
     * @return the juvenileNumber
     */
    public String getJuvenileNumber()
    {
	return juvenileNumber;
    }
    /**
     * @param juvenileNumber the juvenileNumber to set
     */
    public void setJuvenileNumber(String juvenileNumber)
    {
	this.juvenileNumber = juvenileNumber;
    }
    /**
     * @return the referralNumber
     */
    public String getReferralNumber()
    {
	return referralNumber;
    }
    /**
     * @param referralNumber the referralNumber to set
     */
    public void setReferralNumber(String referralNumber)
    {
	this.referralNumber = referralNumber;
    }
    /**
     * @return the transferTo
     */
    public String getTransferTo()
    {
	return transferTo;
    }
    /**
     * @param transferTo the transferTo to set
     */
    public void setTransferTo(String transferTo)
    {
	this.transferTo = transferTo;
    }
    /**
     * @return the actionDate
     */
    public String getActionDate()
    {
	return actionDate;
    }
    /**
     * @param actionDate the actionDate to set
     */
    public void setActionDate(String actionDate)
    {
	this.actionDate = actionDate;
    }
    /**
     * @return the isLastSetting
     */
    public String getIsLastSetting()
    {
	return isLastSetting;
    }
    /**
     * @param isLastSetting the isLastSetting to set
     */
    public void setIsLastSetting(String isLastSetting)
    {
	this.isLastSetting = isLastSetting;
    }
    /**
     * @return the decisionType
     */
    public String getDecisionType()
    {
	return decisionType;
    }
    /**
     * @param decisionType the decisionType to set
     */
    public void setDecisionType(String decisionType)
    {
	this.decisionType = decisionType;
    }
    public String getIsOnlySetting()
    {
	return isOnlySetting;
    }
    public void setIsOnlySetting(String isOnlySetting)
    {
	this.isOnlySetting = isOnlySetting;
    }
    public List<DocketEventResponseEvent> getCourtSettings()
    {
	return courtSettings;
    }
    public void setCourtSettings(List<DocketEventResponseEvent> courtSettings)
    {
	this.courtSettings = courtSettings;
    }
    public String getPDADate()
    {
        return PDADate;
    }
    public void setPDADate(String pDADate)
    {
        PDADate = pDADate;
    }
    public String getTransferFlag()
    {
        return transferFlag;
    }
    public void setTransferFlag(String transferFlag)
    {
        this.transferFlag = transferFlag;
    }
    public String getPetitionNum()
    {
        return petitionNum;
    }
    public void setPetitionNum(String petitionNum)
    {
        this.petitionNum = petitionNum;
    }
    public String getPetitionStatus()
    {
        return petitionStatus;
    }
    public void setPetitionStatus(String petitionStatus)
    {
        this.petitionStatus = petitionStatus;
    }
    
}

package messaging.administercaseload;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateLevelOfSupervisionEvent extends RequestEvent
{
    private String caseNum;

	private String comments; //Comments

	private String defendantId; //SPN

    private boolean isCreate;
    
    private boolean isDelete;
    
    private boolean isUpdate;
    
    private String levelOfSupervisionId; //Supervision Level 
    
    private Date losEffectiveDate; //Effective Date 
    
    private String superviseeHistoryId; //OID - unique ID
     
    /**
     * @roseuid 46435FC302AE
     */
    public UpdateLevelOfSupervisionEvent()
    {

    }    
    
    public String getCaseNum() {
		return caseNum;
	}
    
    public String getComments() {
		return comments;
	}

    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId()
    {
        return defendantId;
    }

    public String getLevelOfSupervisionId() {
		return levelOfSupervisionId;
	}

	public Date getLosEffectiveDate() {
		return losEffectiveDate;
	}

	public String getSuperviseeHistoryId() {
		return superviseeHistoryId;
	}

	public boolean isCreate() {
		return isCreate;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	/**
     * @param defendantId
     *        The defendantId to set.
     */
    public void setDefendantId(String defendantId)
    {
        this.defendantId = defendantId;
    }

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public void setLevelOfSupervisionId(String levelOfSupervisionId) {
		this.levelOfSupervisionId = levelOfSupervisionId;
	}

	public void setLosEffectiveDate(Date losEffectiveDate) {
		this.losEffectiveDate = losEffectiveDate;
	}

	public void setSuperviseeHistoryId(String superviseeHistoryId) {
		this.superviseeHistoryId = superviseeHistoryId;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
}

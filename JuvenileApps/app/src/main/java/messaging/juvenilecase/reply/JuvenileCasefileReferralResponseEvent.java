package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;
import pd.juvenilecase.JuvenileCasefile;

public class JuvenileCasefileReferralResponseEvent extends ResponseEvent implements Comparable<JuvenileCasefileReferralResponseEvent>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String juvenileNum;
	private JuvenileCasefile caseFile = null;
	private String caseFileId;
	private String referralNumber;
	private String caseStatusCd;
	private String supervisionTypeCd;
	private String supervisionCat;
	private String supervisionType;
	private boolean updateTjjdRefDate;
	private Date assignmentAddDate;
	
	//US 71173
	String offenseCodes;
    
	public int compareTo(JuvenileCasefileReferralResponseEvent resp)
	{
		if(resp==null)
			return -1;
		return referralNumber.compareToIgnoreCase(resp.getReferralNumber());		
	}

	/**
	 * @return the caseFile
	 */
	public JuvenileCasefile getCaseFile() {
		return caseFile;
	}
	/**
	 * @param caseFile the caseFile to set
	 */
	public void setCaseFile(JuvenileCasefile caseFile) {
		this.caseFile = caseFile;
	}
	/**
	 * @return the offenseCodes
	 */
	public String getOffenseCodes()
	{
	    return offenseCodes;
	}

	/**
	 * @param offenseCodes the offenseCodes to set
	 */
	public void setOffenseCodes(String offenseCodes)
	{
	    this.offenseCodes = offenseCodes;
	}

	/**
	 * @return the caseFileId
	 */
	public String getCaseFileId() {
		return caseFileId;
	}
	/**
	 * @param caseFileId the caseFileId to set
	 */
	public void setCaseFileId(String caseFileId) {
		this.caseFileId = caseFileId;
	}
	/**
	 * @return the referralNumber
	 */
	public String getReferralNumber() {
		return referralNumber;
	}
	/**
	 * @param referralNumber the referralNumber to set
	 */
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	/**
	 * @return the caseStatusCd
	 */
	public String getCaseStatusCd() {
		return caseStatusCd;
	}
	/**
	 * @param caseStatusCd the caseStatusCd to set
	 */
	public void setCaseStatusCd(String caseStatusCd) {
		this.caseStatusCd = caseStatusCd;
	}
	/**
	 * @return the supervisionTypeCd
	 */
	public String getSupervisionTypeCd() {
		return supervisionTypeCd;
	}
	/**
	 * @param supervisionTypeCd the supervisionTypeCd to set
	 */
	public void setSupervisionTypeCd(String supervisionTypeCd) {
		this.supervisionTypeCd = supervisionTypeCd;
	}
	/**
	 * @return the supervisionCat
	 */
	public String getSupervisionCat() {
		return supervisionCat;
	}
	/**
	 * @param supervisionCat the supervisionCat to set
	 */
	public void setSupervisionCat(String supervisionCat) {
		this.supervisionCat = supervisionCat;
	}
	
	/**

	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return the supervisionType
	 */
	public String getSupervisionType() {
		return supervisionType;
	}

	/**
	 * @param supervisionType the supervisionType to set
	 */
	public void setSupervisionType(String supervisionType) {
		this.supervisionType = supervisionType;
	}

	public boolean isUpdateTjjdRefDate()
	{
	    return updateTjjdRefDate;
	}

	public void setUpdateTjjdRefDate(boolean updateTjjdRefDate)
	{
	    this.updateTjjdRefDate = updateTjjdRefDate;
	}

	public Date getAssignmentAddDate()
	{
	    return assignmentAddDate;
	}

	public void setAssignmentAddDate(Date assignmentAddDate)
	{
	    this.assignmentAddDate = assignmentAddDate;
	}
	
	
	
	

}

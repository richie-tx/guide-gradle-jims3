package messaging.calendar;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetProgramReferralWithEventsEvent extends RequestEvent {

    private String referralId;
    private String programReferralId;
	private String casefileId;
	private String juvenileNum;
	private boolean courtOrdered ;
	private String providerProgramId ;
	private Date beginDate ;
	private String assignedHours ;
	private Integer calendarEventId;
	private Date assignmentDate; //bug #37849
	
	//add rest of the stuff for the juvprogref table.
	private String programOutomeCd;
	private String programOutcomeCdDesc;
	private String substatusCd;
	private Date endDate; //enddate
	private String controllingReferralNum;
	private String programOutcomeSubcategoryCd;
	private String referralStatusCd;
	private Date acknowlegmentDate;
	
		
	/**
	 * @return the programOutomeCd
	 */
	public String getProgramOutomeCd() {
		return programOutomeCd;
	}

	/**
	 * @param programOutomeCd the programOutomeCd to set
	 */
	public void setProgramOutomeCd(String programOutomeCd) {
		this.programOutomeCd = programOutomeCd;
	}

	/**
	 * @return the programOutcomeCdDesc
	 */
	public String getProgramOutcomeCdDesc() {
		return programOutcomeCdDesc;
	}

	/**
	 * @param programOutcomeCdDesc the programOutcomeCdDesc to set
	 */
	public void setProgramOutcomeCdDesc(String programOutcomeCdDesc) {
		this.programOutcomeCdDesc = programOutcomeCdDesc;
	}

	/**
	 * @return the substatusCd
	 */
	public String getSubstatusCd() {
		return substatusCd;
	}

	/**
	 * @param substatusCd the substatusCd to set
	 */
	public void setSubstatusCd(String substatusCd) {
		this.substatusCd = substatusCd;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the controllingReferralNum
	 */
	public String getControllingReferralNum() {
		return controllingReferralNum;
	}

	/**
	 * @param controllingReferralNum the controllingReferralNum to set
	 */
	public void setControllingReferralNum(String controllingReferralNum) {
		this.controllingReferralNum = controllingReferralNum;
	}

	/**
	 * @return the programOutcomeSubcategoryCd
	 */
	public String getProgramOutcomeSubcategoryCd() {
		return programOutcomeSubcategoryCd;
	}

	/**
	 * @param programOutcomeSubcategoryCd the programOutcomeSubcategoryCd to set
	 */
	public void setProgramOutcomeSubcategoryCd(String programOutcomeSubcategoryCd) {
		this.programOutcomeSubcategoryCd = programOutcomeSubcategoryCd;
	}

	/**
	 * @return the referralStatusCd
	 */
	public String getReferralStatusCd() {
		return referralStatusCd;
	}

	/**
	 * @param referralStatusCd the referralStatusCd to set
	 */
	public void setReferralStatusCd(String referralStatusCd) {
		this.referralStatusCd = referralStatusCd;
	}

	/**
	 * @return Returns the programReferralId.
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}
	
	/**
	 * @param programReferralId
	 *            The programReferralId to set.
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
	
	/**
	 * @return Returns the referralId.
	 */
	public String getReferralId() {
		return referralId;
	}
	/**
	 * @param referralId The referralId to set.
	 */
	public void setReferralId(String referralId) {
		this.referralId = referralId;
	}
	
	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId The casefileId to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	
	/**
	 * @return Returns the providerProgramId.
	 */
	public String getProviderProgramId( )
	{
		return providerProgramId ;
	}

	/**
	 * @param providerProgramId
	 * The providerProgramId to set.
	 */
	public void setProviderProgramId( String providerProgramId )
	{
		this.providerProgramId = providerProgramId ;
	}

	/**
	 * @return Returns the courtOrdered.
	 */
	public boolean isCourtOrdered( )
	{
		return courtOrdered ;
	}

	/**
	 * @param courtOrdered
	 * The courtOrdered to set.
	 */
	public void setCourtOrdered( boolean courtOrdered )
	{
		this.courtOrdered = courtOrdered ;
	}

	/**
	 * @return Returns the beginDateStr.
	 */
	public Date getBeginDate( )
	{
		return beginDate ;
	}

	/**
	 * @param beginDateStr
	 * The beginDateStr to set.
	 */
	public void setBeginDate( Date beginDate )
	{
		this.beginDate = beginDate ;
	}
	/**
	 * @return Returns the assignedHours.
	 */
	public String getAssignedHours( )
	{
		return assignedHours ;
	}

	/**
	 * @param assignedHours
	 * The assignedHours to set.
	 */
	public void setAssignedHours( String assignedHours )
	{
		this.assignedHours = assignedHours ;
	}
	

    /**
     * @return Returns the calendarEventId.
     */
    public Integer getCalendarEventId()
    {
      
        return calendarEventId;
    }

    /**
     * @param calendarEventId
     *            The calendarEventId to set.
     */
    public void setCalendarEventId(Integer calendarEventId)
    {
       this.calendarEventId = calendarEventId;
    }

	/**
	 * @return the assignmentDate
	 */
	public Date getAssignmentDate() {
		return assignmentDate;
	}

	/**
	 * @param assignmentDate the assignmentDate to set
	 */
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	/**
	 * @return the acknowlegmentDate
	 */
	public Date getAcknowlegmentDate() {
		return acknowlegmentDate;
	}

	/**
	 * @param acknowlegmentDate the acknowlegmentDate to set
	 */
	public void setAcknowlegmentDate(Date acknowlegmentDate) {
		this.acknowlegmentDate = acknowlegmentDate;
	}

}

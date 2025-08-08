package messaging.districtCourtHearings;
import java.util.Date;

import messaging.calendar.reply.DocketEventResponseEvent;
import mojo.km.messaging.RequestEvent;

/**
 * @author nemathew
 *
 */
public class UpdateJJSCLAncillarySettingEvent extends RequestEvent {
	
	private static final long serialVersionUID = 1L;
	private Date courtDate;
	private String courtId;
	private String courtTime;
	private String petitionNum;
	private String petitionAmendment;
	private String issueFlag;
	private String typeCase;
	private String respondentName;
	private String settingReason;
	private String barNum;
	private String attorneyName;
	private String galBarNum;
	private String galName;
	private String attorneyConnection;
	private Date filingDate;
	private String chainNum;
	private String seqNumber;
	private Date	lcDate;
	private Date	lcTime;
	private String	lcUser;
	private String recType;
	private String hearingResult;//added for court action
	private String hearingDisposition;// added for court action
	private String docketEventId;
	private String juryFlag;
	private String resetHearingType;
	private String tjpcSeqNumber;
	private String prevNotes;
	private String transferTo;
	private boolean isNewRecordCreated;
	private String comments;
	private String updateFlag;
	//  task 168662
	private String interpreter;  
	
	
	private DocketEventResponseEvent dockerResp;
	/**
	 * @return the courtDate
	 */
	public Date getCourtDate() {
		return courtDate;
	}
	/**
	 * @param string the courtDate to set
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
	/**
	 * @return
	 */
	public String getCourtTime() {
		return courtTime;
	}
	public void setCourtTime(String courtTime) {
		this.courtTime = courtTime;
	}
	public String getPetitionNum() {
		return petitionNum;
	}
	public void setPetitionNum(String petitionNum) {
		this.petitionNum = petitionNum;
	}
	public String getIssueFlag() {
		return issueFlag;
	}
	public void setIssueFlag(String issueFlag) {
		this.issueFlag = issueFlag;
	}
	public String getTypeCase() {
		return typeCase;
	}
	public void setTypeCase(String typeCase) {
		this.typeCase = typeCase;
	}
	public String getRespondentName() {
		return respondentName;
	}
	public void setRespondentName(String respondentName) {
		this.respondentName = respondentName;
	}
	public String getSettingReason() {
		return settingReason;
	}
	public void setSettingReason(String settingReason) {
		this.settingReason = settingReason;
	}
	public String getBarNum() {
		return barNum;
	}
	public void setBarNum(String barNum) {
		this.barNum = barNum;
	}
	public String getAttorneyName() {
		return attorneyName;
	}
	public void setAttorneyName(String attorneyName) {
		this.attorneyName = attorneyName;
	}
	public String getAttorneyConnection() {
		return attorneyConnection;
	}
	public void setAttorneyConnection(String attorneyConnection) {
		this.attorneyConnection = attorneyConnection;
	}
	public Date getFilingDate() {
		return filingDate;
	}
	public void setFilingDate(Date string) {
		this.filingDate = string;
	}
	public String getChainNum() {
		return chainNum;
	}
	public void setChainNum(String chainNum) {
		this.chainNum = chainNum;
	}
	public String getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(String seqNumber) {
		this.seqNumber = seqNumber;
	}
	public Date getLcDate() {
		return lcDate;
	}
	public void setLcDate(Date lcDate) {
		this.lcDate = lcDate;
	}
	public Date getLcTime() {
		return lcTime;
	}
	public void setLcTime(Date lcTime) {
		this.lcTime = lcTime;
	}
	public String getLcUser() {
		return lcUser;
	}
	public void setLcUser(String lcUser) {
		this.lcUser = lcUser;
	}
	public String getRecType() {
		return recType;
	}
	public void setRecType(String recType) {
		this.recType = recType;
	}
	public DocketEventResponseEvent getDockerResp() {
		return dockerResp;
	}
	public void setDockerResp(DocketEventResponseEvent dockerResp) {
		this.dockerResp = dockerResp;
	}
	/**
	 * @return the docketEventId
	 */
	public String getDocketEventId() {
		return docketEventId;
	}
	/**
	 * @param docketEventId the docketEventId to set
	 */
	public void setDocketEventId(String docketEventId) {
		this.docketEventId = docketEventId;
	}
	/**
	 * @return the hearingResult
	 */
	public String getHearingResult()
	{
	    return hearingResult;
	}
	/**
	 * @param hearingResult the hearingResult to set
	 */
	public void setHearingResult(String hearingResult)
	{
	    this.hearingResult = hearingResult;
	}
	/**
	 * @return the hearingDisposition
	 */
	public String getHearingDisposition()
	{
	    return hearingDisposition;
	}
	/**
	 * @param hearingDisposition the hearingDisposition to set
	 */
	public void setHearingDisposition(String hearingDisposition)
	{
	    this.hearingDisposition = hearingDisposition;
	}
	
	/**
	 * @return the juryFlag
	 */
	public String getJuryFlag()
	{
	    return juryFlag;
	}
	/**
	 * @param juryFlag the juryFlag to set
	 */
	public void setJuryFlag(String juryFlag)
	{
	    this.juryFlag = juryFlag;
	}
	/**
	 * @return the resetHearingType
	 */
	public String getResetHearingType()
	{
	    return resetHearingType;
	}
	/**
	 * @param resetHearingType the resetHearingType to set
	 */
	public void setResetHearingType(String resetHearingType)
	{
	    this.resetHearingType = resetHearingType;
	}
	/**
	 * @return the tjpcSeqNumber
	 */
	public String getTjpcSeqNumber()
	{
	    return tjpcSeqNumber;
	}
	/**
	 * @param tjpcSeqNumber the tjpcSeqNumber to set
	 */
	public void setTjpcSeqNumber(String tjpcSeqNumber)
	{
	    this.tjpcSeqNumber = tjpcSeqNumber;
	}
	/**
	 * @return the petitionAmendment
	 */
	public String getPetitionAmendment()
	{
	    return petitionAmendment;
	}
	/**
	 * @param petitionAmendment the petitionAmendment to set
	 */
	public void setPetitionAmendment(String petitionAmendment)
	{
	    this.petitionAmendment = petitionAmendment;
	}
	/**
	 * @return the prevNotes
	 */
	public String getPrevNotes()
	{
	    return prevNotes;
	}
	/**
	 * @param prevNotes the prevNotes to set
	 */
	public void setPrevNotes(String prevNotes)
	{
	    this.prevNotes = prevNotes;
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
	 * @return the isNewRecordCreated
	 */
	public boolean isNewRecordCreated()
	{
	    return isNewRecordCreated;
	}
	/**
	 * @param isNewRecordCreated the isNewRecordCreated to set
	 */
	public void setNewRecordCreated(boolean isNewRecordCreated)
	{
	    this.isNewRecordCreated = isNewRecordCreated;
	}
	/**
	 * @return the comments
	 */
	public String getComments()
	{
	    return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments)
	{
	    this.comments = comments;
	}
	/**
	 * @return the updateFlag
	 */
	public String getUpdateFlag()
	{
	    return updateFlag;
	}
	/**
	 * @param updateFlag the updateFlag to set
	 */
	public void setUpdateFlag(String updateFlag)
	{
	    this.updateFlag = updateFlag;
	}
	public String getGalBarNum()
	{
	    return galBarNum;
	}
	public void setGalBarNum(String galBarNum)
	{
	    this.galBarNum = galBarNum;
	}
	public String getGalName()
	{
	    return galName;
	}
	public void setGalName(String galName)
	{
	    this.galName = galName;
	}
	public String getInterpreter()
	{
	    return interpreter;
	}
	public void setInterpreter(String interpreter)
	{
	    this.interpreter = interpreter;
	}

}

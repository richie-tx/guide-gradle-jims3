//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\CreateProviderProgramEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class CreateProviderProgramEvent extends RequestEvent
{
	public Date endDate;
	public String programName;
	public String programCode;
	public Date startDate;
	public String stateProgramCode;
	public String subType;
	public String targetIntervention;
	public String programScheduleType; //added for U.S #11099
	public String type;
	public String description;
	private String juvenileServiceProviderId;
	private String providerProgramId;
	
	private boolean isCreate;
	private boolean inactivate;
	private String statusId;
	
	//added for US #11376
	private String programLocation;
	private String programCategory;
	private String sourceFund;
	private Date fundStartDate;
	private Date fundEndDate;
	private boolean newSourceFund;
	private String oldSourceFundId;
	private String transferredProgRef;
	private String programID;
	private Date discontinueDate;
	private String supervisionCategory;
	public String programSubTypeId;
	public String tjjdEdiCode;
	public int maxYouth; //added for US 190589

	

	/**
	 * @roseuid 4473534903D3
	 */
	public CreateProviderProgramEvent()
	{

	}

	/**
	 * Access method for the endDate property.
	 * 
	 * @return   the current value of the endDate property
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Access method for the programName property.
	 * 
	 * @return   the current value of the programName property
	 */
	public String getProgramName()
	{
		return programName;
	}

	/**
	 * Access method for the programCode property.
	 * 
	 * @return   the current value of the programCode property
	 */
	public String getProgramCode()
	{
		return programCode;
	}

	/**
	 * Access method for the juvenileServiceProviderId property.
	 * 
	 * @return   the current value of the juvenileServiceProviderId property
	 */
	public String getJuvenileServiceProviderId()
	{
		return juvenileServiceProviderId;
	}

	/**
	 * Access method for the startDate property.
	 * 
	 * @return   the current value of the startDate property
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * Access method for the stateProgramCode property.
	 * 
	 * @return   the current value of the stateProgramCode property
	 */
	public String getStateProgramCode()
	{
		return stateProgramCode;
	}

	/**
	 * Access method for the subType property.
	 * 
	 * @return   the current value of the subType property
	 */
	public String getSubType()
	{
		return subType;
	}

	/**
	 * Access method for the targetIntervention property.
	 * 
	 * @return   the current value of the targetIntervention property
	 */
	public String getTargetIntervention()
	{
		return targetIntervention;
	}

	/**
	 * Access method for the type property.
	 * 
	 * @return   the current value of the type property
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Sets the value of the endDate property.
	 * 
	 * @param aEndDate the new value of the endDate property
	 */
	public void setEndDate(Date aEndDate)
	{
		endDate = aEndDate;
	}

	/**
	 * Sets the value of the programName property.
	 * 
	 * @param aprogramName the new value of the programName property
	 */
	public void setProgramName(String aProgramName)
	{
		programName = aProgramName;
	}

	/**
	 * Sets the value of the programCode property.
	 * 
	 * @param aProgramCode the new value of the programCode property
	 */
	public void setProgramCode(String aProgramCode)
	{
		programCode = aProgramCode;
	}

	/**
	 * Sets the value of the juvenileServiceProviderId property.
	 * 
	 * @param aProgramCode the new value of the juvenileServiceProviderId property
	 */
	public void setJuvenileServiceProviderId(String aJuvenileServiceProviderId)
	{
		juvenileServiceProviderId = aJuvenileServiceProviderId;
	}

	/**
	 * Sets the value of the startDate property.
	 * 
	 * @param aStartDate the new value of the startDate property
	 */
	public void setStartDate(Date aStartDate)
	{
		startDate = aStartDate;
	}

	/**
	 * Sets the value of the stateProgramCode property.
	 * 
	 * @param aStateProgramCode the new value of the stateProgramCode property
	 */
	public void setStateProgramCode(String aStateProgramCode)
	{
		stateProgramCode = aStateProgramCode;
	}

	/**
	 * Sets the value of the targetIntervention property.
	 * 
	 * @param aTargetIntervention the new value of the targetIntervention property
	 */
	public void setTargetIntervention(String aTargetIntervention)
	{
		targetIntervention = aTargetIntervention;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param aType the new value of the type property
	 */
	public void setType(String aType)
	{
		type = aType;
	}
	/**
	 * Sets the value of the subType property.
	 * @param aSubType the new value of the subType property
	 */
	public void setSubType(String aSubType)
	{
		subType = aSubType;
	}	
	/**
	 * @return
	 */
	public String getProviderProgramId()
	{
		return providerProgramId;
	}

	/**
	 * @param string
	 */
	public void setProviderProgramId(String string)
	{
		providerProgramId = string;
	}

	/**
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}

	/**
	 * @return Returns the inactivate.
	 */
	public boolean isInactivate() {
		return inactivate;
	}
	/**
	 * @param inactivate The inactivate to set.
	 */
	public void setInactivate(boolean inactivate) {
		this.inactivate = inactivate;
	}
	/**
	 * @return the programLocation
	 */
	public String getProgramLocation() {
		return programLocation;
	}

	/**
	 * @param programLocation the programLocation to set
	 */
	public void setProgramLocation(String programLocation) {
		this.programLocation = programLocation;
	}

	/**
	 * @return the programCategory
	 */
	public String getProgramCategory() {
		return programCategory;
	}

	/**
	 * @param programCategory the programCategory to set
	 */
	public void setProgramCategory(String programCategory) {
		this.programCategory = programCategory;
	}

	/**
	 * @return the sourceFund
	 */
	public String getSourceFund() {
		return sourceFund;
	}

	/**
	 * @param sourceFund the sourceFund to set
	 */
	public void setSourceFund(String sourceFund) {
		this.sourceFund = sourceFund;
	}

	/**
	 * @return the fundStartDate
	 */
	public Date getFundStartDate() {
		return fundStartDate;
	}

	/**
	 * @param fundStartDate the fundStartDate to set
	 */
	public void setFundStartDate(Date fundStartDate) {
		this.fundStartDate = fundStartDate;
	}

	/**
	 * @return the fundEndDate
	 */
	public Date getFundEndDate() {
		return fundEndDate;
	}

	/**
	 * @param fundEndDate the fundEndDate to set
	 */
	public void setFundEndDate(Date fundEndDate) {
		this.fundEndDate = fundEndDate;
	}

	/**
	 * @return Returns the isCreate.
	 */
	public boolean isCreate() {
		return isCreate;
	}
	/**
	 * @param isCreate The isCreate to set.
	 */
	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}
	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return the programScheduleType
	 */
	public String getProgramScheduleType() {
		return programScheduleType;
	}

	/**
	 * @param programScheduleType the programScheduleType to set
	 */
	public void setProgramScheduleType(String programScheduleType) {
		this.programScheduleType = programScheduleType;
	}

	public boolean isNewSourceFund() {
		return newSourceFund;
	}

	public void setNewSourceFund(boolean newSourceFund) {
		this.newSourceFund = newSourceFund;
	}

	public String getOldSourceFundId() {
		return oldSourceFundId;
	}

	public void setOldSourceFundId(String oldSourceFundId) {
		this.oldSourceFundId = oldSourceFundId;
	}

	public String getTransferredProgRef()
	{
	    return transferredProgRef;
	}

	public void setTransferredProgRef(String transferredProgRef)
	{
	    this.transferredProgRef = transferredProgRef;
	}
	public String getProgramID()
	{
	    return programID;
	}

	public void setProgramID(String programID)
	{
	    this.programID = programID;
	}
	
	public Date getDiscontinueDate()
	{
	    return this.discontinueDate;
	}

	public void setDiscontinueDate(Date discontinueDate)
	{
	    this.discontinueDate = discontinueDate;
	}
	
	public String getSupervisionCategory()
	{
	    return this.supervisionCategory;
	}

	public void setSupervisionCategory(String supervisionCategory)
	{
	    this.supervisionCategory = supervisionCategory;
	}
	public String getProgramSubTypeId()
	{
	    return programSubTypeId;
	}

	public void setProgramSubTypeId(String programSubTypeId)
	{
	    this.programSubTypeId = programSubTypeId;
	}

	public String getTjjdEdiCode()
	{
	    return tjjdEdiCode;
	}

	public void setTjjdEdiCode(String tjjdEdiCode)
	{
	    this.tjjdEdiCode = tjjdEdiCode;
	}

	public int getMaxYouth()
	{
	    return maxYouth;
	}

	public void setMaxYouth(int maxYouth)
	{
	    this.maxYouth = maxYouth;
	}
	
	
	
}
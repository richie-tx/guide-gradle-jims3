/*
 * Created on Sep 25, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider;

import java.util.Date;
import java.util.Iterator;

import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileEventTypeCode;
import mojo.km.persistence.PersistentObject;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
 * @author C_NAggarwal
 *
 */
public class SearchServiceProvider extends PersistentObject {

	/**
	 * 
	 */
	public SearchServiceProvider() {
	}
	
	/**
	* Properties for targetIntervention
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey PROGRAM_GROUP
	*/
	private Code targetIntervention = null;
	private String stateProgramCodeId;
	private String typeProgramCodeId;
	

	private String targetInterventionId;
	private String juvenileServiceProviderId;
	private String programStatusId;
	private String maxEnrollment;
	private String serviceCode;
	private String serviceCost;
	private String serviceId;
	private JuvenileEventTypeCode serviceType = null;
	private Code programScheduleType = null; //added for U.S #11099
	private String programScheduleTypeId;//added for U.S #11099
	
	/**
	* Properties for programStatus
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey PROVIDERPROGRAM_STATUS
	*/
	private Code programStatus = null;
	/**
	* Properties for stateProgramCode
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey STATE_PROGRAM_CODE
	*/
	private Code stateProgramCode = null;
	private String programName;
	private String programCodeId;
	private Code programCode = null;
	private Date endDate;
	private Date startDate;	

	private Date endDateFrom;
	private Date endDateTo;
	
	private String serviceProviderName;
	private boolean inHouse;
	private String serviceProviderStatusId;
	private Code serviceProviderStatus = null;

	private String programId;
	private String serviceName;
	private String serviceTypeId;
	
	private String programDescription;	
	
	private String serviceStatusId;
	/**
	* Properties for programStatus
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey SERVICE_STATUS
	*/
	private Code serviceStatus = null;
	
	//added for US #11376
	private String programLocation;
	
	private String programCategory;
	private String transferredProgRef;
	private String contractID;// added for contract DB OID task 171957
	private Date discontinueDate;
	
	
	/**
	* Access method for the programName property.
	* @return the current value of the programName property
	*/
	public String getProgramName() {
		fetch();
		return programName;
	}
	/**
	* Sets the value of the programName property.
	* @param aProgramName the new value of the programName property
	*/
	public void setProgramName(String aProgramName) {
		this.programName = aProgramName;
	}
	/**
	* Access method for the endDate property.
	* @return the current value of the endDate property
	*/
	public Date getEndDate() {
		fetch();
		return endDate;
	}
	/**
	* Sets the value of the endDate property.
	* @param aEndDate the new value of the endDate property
	*/
	public void setEndDate(Date aEndDate) {
		this.endDate = aEndDate;
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setTargetInterventionId(String targetInterventionId) {
		this.targetInterventionId = targetInterventionId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getTargetInterventionId() {
		fetch();
		return targetInterventionId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initTargetIntervention() {
		if (targetIntervention == null) {
			targetIntervention = (Code) new mojo.km.persistence.Reference(targetInterventionId, Code.class, PDCodeTableConstants.JUV_PROGRAM_GROUP).getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getTargetIntervention() {
		fetch();
		initTargetIntervention();
		return targetIntervention;
	}
	/**
	* set the type reference for class member targetIntervention
	*/
	public void setTargetIntervention(Code targetIntervention) {
		setTargetInterventionId("" + targetIntervention.getOID());
		targetIntervention.setContext(PDCodeTableConstants.JUV_PROGRAM_GROUP);
		this.targetIntervention = (Code) new mojo.km.persistence.Reference(targetIntervention).getObject();
	}
	
	/**
	 * @param programScheduleTypeId the programScheduleTypeId to set
	 */
	public void setProgramScheduleTypeId(String programScheduleTypeId) {
		this.programScheduleTypeId = programScheduleTypeId;
	}
	/**
	 * @return the programScheduleTypeId
	 */
	public String getProgramScheduleTypeId() {
		fetch();
		return programScheduleTypeId;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initProgramScheduleType() {
		if (programScheduleType == null) {
			programScheduleType = (Code) new mojo.km.persistence.Reference(programScheduleTypeId, Code.class, PDCodeTableConstants.PROGRAM_SCHEDULE_TYPE).getObject();
		}
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getProgramScheduleType() {
		fetch();
		initProgramScheduleType();
		return programScheduleType;
	}
	

	/**
	 * @param programScheduleTypeId the programScheduleTypeId to set
	 */
	public void setProgramScheduleType(Code programScheduleType) {
		setProgramScheduleTypeId("" + programScheduleType.getOID());
		programScheduleType.setContext(PDCodeTableConstants.PROGRAM_SCHEDULE_TYPE);
		this.programScheduleType = (Code) new mojo.km.persistence.Reference(programScheduleType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setProgramStatusId(String programStatusId) {
		this.programStatusId = programStatusId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getProgramStatusId() {
		fetch();
		return programStatusId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initProgramStatus() {
		if (programStatus == null) {
			programStatus = (Code) new mojo.km.persistence.Reference(programStatusId, Code.class, "PROVIDERPROGRAM_STATUS").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getProgramStatus() {
		fetch();
		initProgramStatus();
		return programStatus;
	}

	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setStateProgramCodeId(String stateProgramCodeId) {
		this.stateProgramCodeId = stateProgramCodeId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getStateProgramCodeId() {
		fetch();
		return stateProgramCodeId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initStateProgramCode() {
		if (stateProgramCode == null) {
			stateProgramCode = (Code) new mojo.km.persistence.Reference(stateProgramCodeId, Code.class, "STATE_PROGRAM_CODE").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getStateProgramCode() {
		fetch();
		initStateProgramCode();
		return stateProgramCode;
	}
	/**
	* set the type reference for class member stateProgramCode
	*/
	public void setStateProgramCode(Code stateProgramCode) {
		setStateProgramCodeId("" + stateProgramCode.getOID());
		stateProgramCode.setContext("STATE_PROGRAM_CODE");
		this.stateProgramCode = (Code) new mojo.km.persistence.Reference(stateProgramCode).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.administerserviceprovider.JuvenileServiceProvider
	*/
	public void setJuvenileServiceProviderId(String juvenileServiceProviderId) {
		this.juvenileServiceProviderId = juvenileServiceProviderId;
	}	
	/**
	* Get the reference value to class :: pd.supervision.administerserviceprovider.JuvenileServiceProvider
	*/
	public String getJuvenileServiceProviderId() {
		fetch();
		return juvenileServiceProviderId;
	}
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4177C29D03A9
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return (Iterator) home.findAll(event, SearchServiceProvider.class);
	}

	public void createOID() {
		new Home().bind(this);
	}
	/**
	* Access method for the serviceProviderName property.
	* @return the current value of the serviceProviderName property
	*/
	public String getServiceProviderName() {
		fetch();
		return serviceProviderName;
	}
	/**
	* Sets the value of the serviceProviderName property.
	* @param aServiceProviderName the new value of the serviceProviderName property
	*/
	public void setServiceProviderName(String aServiceProviderName) {
		this.serviceProviderName = aServiceProviderName;
	}	
	/**
	* Determines if the inHouse property is true.
	* @return <code>true<code> if the inHouse property is true
	*/
	public boolean getInHouse() {
		fetch();
		return inHouse;
	}
	/**
	* Sets the value of the inHouse property.
	* @param aInHouse the new value of the inHouse property
	*/
	public void setInHouse(boolean aInHouse) {
		this.inHouse = aInHouse;
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setServiceProviderStatusId(String serviceProviderStatusId) {
		this.serviceProviderStatusId = serviceProviderStatusId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getServiceProviderStatusId() {
		fetch();
		return serviceProviderStatusId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initServiceProviderStatus() {
		if (serviceProviderStatus == null) {
			serviceProviderStatus = (Code) new mojo.km.persistence.Reference(serviceProviderStatusId, Code.class, "SERVICEPROVIDER_STATUS").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getServiceProviderStatus() {
		fetch();
		initServiceProviderStatus();
		return serviceProviderStatus;
	}
	/**
	* set the type reference for class member serviceProviderStatus
	*/
	public void setServiceProviderStatus(Code serviceProviderStatus) {
		setServiceProviderStatusId("" + serviceProviderStatus.getOID());
		serviceProviderStatus.setContext("SERVICEPROVIDER_STATUS");
		this.serviceProviderStatus = (Code) new mojo.km.persistence.Reference(serviceProviderStatus).getObject();
	}	
	/**
	* Access method for the endDateFrom property.
	* @return the current value of the endDateFrom property
	*/
	public Date getEndDateFrom() {
		fetch();
		return endDateFrom;
	}
	/**
	* Sets the value of the endDateFrom property.
	* @param aEndDateFrom the new value of the endDateFrom property
	*/
	public void setEndDateFrom(Date aEndDateFrom) {
		this.endDateFrom = aEndDateFrom;
	}
	/**
	* Access method for the endDateTo property.
	* @return the current value of the endDateTo property
	*/
	public Date getEndDateTo() {
		fetch();
		return endDateTo;
	}
	/**
	* Sets the value of the endDateTo property.
	* @param aEndDateTo the new value of the endDateTo property
	*/
	public void setEndDateTo(Date aEndDateTo) {
		this.endDateTo = aEndDateTo;
	}
	/**
	* Set the reference value to class :: pd.supervision.administerserviceprovider.JuvenileServiceProvider
	*/
	public void setProgramId(String programId) {
		this.programId = programId;
	}	
	/**
	* Get the reference value to class :: pd.supervision.administerserviceprovider.JuvenileServiceProvider
	*/
	public String getProgramId() {
		fetch();
		return programId;
	}
	/**
	* Access method for the serviceProviderName property.
	* @return the current value of the serviceProviderName property
	*/
	public String getServiceName() {
		fetch();
		return serviceName;
	}
	/**
	* Sets the value of the serviceProviderName property.
	* @param aServiceProviderName the new value of the serviceProviderName property
	*/
	public void setServiceName(String aServiceName) {
		this.serviceName = aServiceName;
	}
	/**
	* Access method for the serviceProviderName property.
	* @return the current value of the serviceProviderName property
	*/
	public String getServiceTypeId() {
		fetch();
		return serviceTypeId;
	}
	/**
	* Sets the value of the serviceProviderName property.
	* @param aServiceProviderName the new value of the serviceProviderName property
	*/
	public void setServiceTypeId(String aServiceTypeId) {
		this.serviceTypeId = aServiceTypeId;
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setServiceStatusId(String serviceStatusId) {
		this.serviceStatusId = serviceStatusId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getServiceStatusId() {
		fetch();
		return serviceStatusId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initServiceStatus() {
		if (serviceStatus == null) {
			serviceStatus = (Code) new mojo.km.persistence.Reference(serviceStatusId, Code.class, "SERVICE_STATUS").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getServiceStatus() {
		fetch();
		initServiceStatus();
		return serviceStatus;
	}
	/**
	* set the type reference for class member serviceStatus
	*/
	public void setServiceStatus(Code serviceStatus) {
		setServiceStatusId("" + serviceStatus.getOID());
		serviceStatus.setContext("SERVICE_STATUS");
		this.serviceStatus = (Code) new mojo.km.persistence.Reference(serviceStatus).getObject();
	}
	
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		return home.findAll(attrName, attrValue, SearchServiceProvider.class);
	}
	
	static public Iterator findAllByNumeric(String attrName, String attrValue)
	{
		IHome home = new Home();
		return home.findAll(attrName, new Integer(attrValue), SearchServiceProvider.class);
	}
	
	public void setProgramStatus(Code programStatus) {
		setProgramStatusId("" + programStatus.getOID());
		programStatus.setContext("PROVIDERPROGRAM_STATUS");
		this.programStatus = (Code) new mojo.km.persistence.Reference(programStatus).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setProgramCodeId(String programCodeId) {
		this.programCodeId = programCodeId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getProgramCodeId() {
		fetch();
		return programCodeId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initProgramCode() {
		if (programCode == null) {
			programCode = (Code) new mojo.km.persistence.Reference(programCodeId, Code.class, "PROGRAM_CODE").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getProgramCode() {
		fetch();
		initProgramCode();
		return programCode;
	}
	/**
	* set the type reference for class member programCode
	*/
	public void setProgramCode(Code programCode) {
		setProgramCodeId("" + programCode.getOID());
		programCode.setContext("PROGRAM_CODE");
		this.programCode = (Code) new mojo.km.persistence.Reference(programCode).getObject();
	}
	/**
	* Access method for the endDate property.
	* @return the current value of the endDate property
	*/
	public Date getStartDate() {
		fetch();
		return startDate;
	}
	/**
	* Sets the value of the endDate property.
	* @param aEndDate the new value of the endDate property
	*/
	public void setStartDate(Date aStartDate) {
		this.startDate = aStartDate;
	}
	public String getProgramDescription() {
		fetch();
		return programDescription;
	}
	/**
	* Sets the value of the serviceProviderName property.
	* @param aServiceProviderName the new value of the serviceProviderName property
	*/
	public void setProgramDescription(String aProgramDescription) {
		this.programDescription = aProgramDescription;
	}

	/**
	 * @return Returns the maxEnrollment.
	 */
	public String getMaxEnrollment() {
		fetch();
		return maxEnrollment;
	}
	/**
	 * @param maxEnrollment The maxEnrollment to set.
	 */
	public void setMaxEnrollment(String maxEnrollment) {
		this.maxEnrollment = maxEnrollment;
	}
	/**
	 * @return Returns the serviceCode.
	 */
	public String getServiceCode() {
		fetch();
		return serviceCode;
	}
	/**
	 * @param serviceCode The serviceCode to set.
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public JuvenileEventTypeCode getServiceType() {
		fetch();
		initServiceType();
		return serviceType;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initServiceType() {
		if (serviceType == null) {
			serviceType = (JuvenileEventTypeCode) new mojo.km.persistence.Reference(serviceTypeId, JuvenileEventTypeCode.class).getObject();
		}
	}

	/**
	 * @return Returns the serviceCost.
	 */
	public String getServiceCost() {
		return serviceCost;
	}
	/**
	 * @param serviceCost The serviceCost to set.
	 */
	public void setServiceCost(String serviceCost) {
		this.serviceCost = serviceCost;
	}
	/**
	 * @return Returns the serviceId.
	 */
	public String getServiceId() {
		fetch();
		return serviceId;
	}
	/**
	 * @param serviceId The serviceId to set.
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getProgramLocation() {
		fetch();
		return programLocation;
	}
	public void setProgramLocation(String programLocation) {
		this.programLocation = programLocation;
	}
	public String getProgramCategory() {
		fetch();
		return programCategory;
	}
	public void setProgramCategory(String programCategory) {
		this.programCategory = programCategory;
	}
	
	public String getTransferredProgRef() {
		fetch();
		return this.transferredProgRef;
	}
	public void setTransferredProgRef(String transferredProgRef) {
		this.transferredProgRef = transferredProgRef;
	}
	public String getContractID()
	{
	    fetch();
	    return contractID;
	}
	public void setContractID(String contractID)
	{
	    this.contractID = contractID;
	}
	
	public Date getDiscontinueDate()
	{
	    fetch();
	    return this.discontinueDate;
	}

	public void setDiscontinueDate(Date discontinueDate)
	{
	    this.discontinueDate = discontinueDate;
	}
	public String getTypeProgramCodeId()
	{
	    return typeProgramCodeId;
	}
	public void setTypeProgramCodeId(String typeProgramCodeId)
	{
	    this.typeProgramCodeId = typeProgramCodeId;
	}
}

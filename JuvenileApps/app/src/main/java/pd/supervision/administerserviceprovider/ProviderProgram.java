package pd.supervision.administerserviceprovider;

import messaging.administerserviceprovider.CreateProviderProgramEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import mojo.km.utilities.DateUtil;
/**
* Properties for targetIntervention
* @detailerDoNotGenerate false
* @referencedType pd.codetable.Code
* @contextKey TARGET_INTERVENTION
*/
public class ProviderProgram extends PersistentObject {
	/**
	* Properties for targetIntervention
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey TARGET_INTERVENTION
	*/
	private Code targetIntervention = null;
	private Date startDate;
	/**
	* Properties for programSubType
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey PROGRAM_SUBTYPE
	*/
	private Code programSubType = null;
	private String stateProgramCodeId;
	private String targetInterventionId;
	private String programSubTypeId;
	private String juvenileServiceProviderId;
	private String providerProgramId;
	private String statusId;
	private String programScheduleTypeId; //added for U.S #11099
	/**
	* Properties for status
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey PROVIDERPROGRAM_STATUS
	*/
	private Code status = null;
	/**
	* Properties for stateProgramCode
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey STATE_PROGRAM_CODE
	*/
	private Code stateProgramCode = null;
	private String programName;
	private String programCode;
	private String description;
	/**
	* Properties for programType
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey PROGRAM_TYPE
	*/
	private Code programType = null;
	private Date endDate;
	private String programTypeId;
	private Date statusChangeDate;	

	/**
	* Properties for services
	* @referencedType pd.supervision.administerserviceprovider.Service
	*/
	private Collection services = null;
	
	//added for US #11376
	private Collection <ProgramSourceFund> programSourceFundList;
	private String programLocation;
	private String programCategory;
	private String transferredProgRef;
	private String programId;
	private Date discontinueDate;
	private String supervisionCategory;
	private String tjjdEdiCode;
	private String maxYouth;
	
	/**
	* @roseuid 447357EA002A
	*/
	public ProviderProgram() {
	}
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
		if (this.programName == null || !this.programName.equals(aProgramName)) {
			markModified();
		}
		programName = aProgramName;
	}
	/**
	* Access method for the programCode property.
	* @return the current value of the programCode property
	*/
	public String getProgramCode() {
		fetch();
		return programCode;
	}
	/**
	* Sets the value of the programCode property.
	* @param aProgramCode the new value of the programCode property
	*/
	public void setProgramCode(String aProgramCode) {
		if (this.programCode == null || !this.programCode.equals(aProgramCode)) {
			markModified();
		}
		programCode = aProgramCode;
	}
	/**
	* Access method for the startDate property.
	* @return the current value of the startDate property
	*/
	public Date getStartDate() {
		fetch();
		return startDate;
	}
	/**
	* Sets the value of the startDate property.
	* @param aStartDate the new value of the startDate property
	*/
	public void setStartDate(Date aStartDate) {
		if (this.startDate == null || !this.startDate.equals(aStartDate)) {
			markModified();
		}
		startDate = aStartDate;
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
		if (this.endDate == null || !this.endDate.equals(aEndDate)) {
			markModified();
		}
		endDate = aEndDate;
	}
	/**
	* Access method for the description property.
	* @return the current value of the description property
	*/
	public String getDescription() {
		fetch();
		return description;
	}
	/**
	* Sets the value of the description property.
	* @param aDescription the new value of the description property
	*/
	public void setDescription(String aDescription) {
		if (this.description == null || !this.description.equals(aDescription)) {
			markModified();
		}
		description = aDescription;
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setTargetInterventionId(String targetInterventionId) {
		if (this.targetInterventionId == null || !this.targetInterventionId.equals(targetInterventionId)) {
			markModified();
		}
		targetIntervention = null;
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
			targetIntervention = (Code) new mojo.km.persistence.Reference(targetInterventionId, Code.class, "TARGET_INTERVENTION").getObject();
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
		if (this.targetIntervention == null || !this.targetIntervention.equals(targetIntervention)) {
			markModified();
		}
		if (targetIntervention.getOID() == null) {
			new mojo.km.persistence.Home().bind(targetIntervention);
		}
		setTargetInterventionId("" + targetIntervention.getOID());
		targetIntervention.setContext("TARGET_INTERVENTION");
		this.targetIntervention = (Code) new mojo.km.persistence.Reference(targetIntervention).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setProgramSubTypeId(String programSubTypeId) {
		if (this.programSubTypeId == null || !this.programSubTypeId.equals(programSubTypeId)) {
			markModified();
		}
		programSubType = null;
		this.programSubTypeId = programSubTypeId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getProgramSubTypeId() {
		fetch();
		return programSubTypeId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initProgramSubType() {
		if (programSubType == null) {
			programSubType = (Code) new mojo.km.persistence.Reference(programSubTypeId, Code.class, "PROGRAM_SUBTYPE").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getProgramSubType() {
		fetch();
		initProgramSubType();
		return programSubType;
	}
	/**
	* set the type reference for class member programSubType
	*/
	public void setProgramSubType(Code programSubType) {
		if (this.programSubType == null || !this.programSubType.equals(programSubType)) {
			markModified();
		}
		if (programSubType.getOID() == null) {
			new mojo.km.persistence.Home().bind(programSubType);
		}
		setProgramSubTypeId("" + programSubType.getOID());
		programSubType.setContext("PROGRAM_SUBTYPE");
		this.programSubType = (Code) new mojo.km.persistence.Reference(programSubType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setStatusId(String statusId) {
		if (this.statusId == null || !this.statusId.equals(statusId)) {
			markModified();
		}
		status = null;
		this.statusId = statusId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getStatusId() {
		fetch();
		return statusId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initStatus() {
		if (status == null) {
			status = (Code) new mojo.km.persistence.Reference(statusId, Code.class, "PROVIDERPROGRAM_STATUS").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getStatus() {
		fetch();
		initStatus();
		return status;
	}
	/**
	* set the type reference for class member status
	*/
	public void setStatus(Code status) {
		if (this.status == null || !this.status.equals(status)) {
			markModified();
		}
		if (status.getOID() == null) {
			new mojo.km.persistence.Home().bind(status);
		}
		setStatusId("" + status.getOID());
		status.setContext("PROVIDERPROGRAM_STATUS");
		this.status = (Code) new mojo.km.persistence.Reference(status).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setStateProgramCodeId(String stateProgramCodeId) {
		if (this.stateProgramCodeId == null || !this.stateProgramCodeId.equals(stateProgramCodeId)) {
			markModified();
		}
		stateProgramCode = null;
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
		if (this.stateProgramCode == null || !this.stateProgramCode.equals(stateProgramCode)) {
			markModified();
		}
		if (stateProgramCode.getOID() == null) {
			new mojo.km.persistence.Home().bind(stateProgramCode);
		}
		setStateProgramCodeId("" + stateProgramCode.getOID());
		stateProgramCode.setContext("STATE_PROGRAM_CODE");
		this.stateProgramCode = (Code) new mojo.km.persistence.Reference(stateProgramCode).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setProgramTypeId(String programTypeId) {
		if (this.programTypeId == null || !this.programTypeId.equals(programTypeId)) {
			markModified();
		}
		programType = null;
		this.programTypeId = programTypeId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getProgramTypeId() {
		fetch();
		return programTypeId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initProgramType() {
		if (programType == null) {
			programType = (Code) new mojo.km.persistence.Reference(programTypeId, Code.class, "PROGRAM_TYPE").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getProgramType() {
		fetch();
		initProgramType();
		return programType;
	}
	/**
	* set the type reference for class member programType
	*/
	public void setProgramType(Code programType) {
		if (this.programType == null || !this.programType.equals(programType)) {
			markModified();
		}
		if (programType.getOID() == null) {
			new mojo.km.persistence.Home().bind(programType);
		}
		setProgramTypeId("" + programType.getOID());
		programType.setContext("PROGRAM_TYPE");
		this.programType = (Code) new mojo.km.persistence.Reference(programType).getObject();
	}

	/**
	* Initialize class relationship to class pd.supervision.administerserviceprovider.Service
	*/
	private void initServices() {
		if (services == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			services = new mojo.km.persistence.ArrayList(Service.class, "providerProgramId", "" + getOID());
		}
	}
	/**
	* Gets referenced type pd.supervision.administerserviceprovider.Service
	*/
	public Collection getServices() {
		fetch();
		initServices();
		return services;
	}
	/**
	* set the type reference for class member services
	*/
	public void setServices(Collection collection) {
		if (this.services == null || !this.services.equals(collection)) {
			markModified();
		}
		services = collection;
	}
	/**
	* Set the reference value to class :: pd.supervision.administerserviceprovider.JuvenileServiceProvider
	*/
	public void setJuvenileServiceProviderId(String juvenileServiceProviderId) {
		if (this.juvenileServiceProviderId == null || !this.juvenileServiceProviderId.equals(juvenileServiceProviderId)) {
			markModified();
		}
		this.juvenileServiceProviderId = juvenileServiceProviderId;
	}	
	/**
	* Set the reference value to providerProgramId
	*/
	public void setProviderProgramId(String providerProgramId) {
		if (this.providerProgramId == null || !this.providerProgramId.equals(providerProgramId)) {
			markModified();
		}
		this.providerProgramId = providerProgramId;
	}
	
	public Date getDiscontinueDate() {
		fetch();
		return this.discontinueDate;
	}
	/**
	* Sets the value of the startDate property.
	* @param aStartDate the new value of the startDate property
	*/
	public void setDiscontinueDate(Date discontinueDate) {
		if (this.discontinueDate == null || !this.discontinueDate.equals(discontinueDate)) {
			markModified();
		}
		this.discontinueDate = discontinueDate;
	}
	
	
	/**
	* Get the reference value to class :: pd.supervision.administerserviceprovider.JuvenileServiceProvider
	*/
	public String getJuvenileServiceProviderId() {
		fetch();
		return juvenileServiceProviderId;
	}
	/**
	* Get the reference value to providerProgramId
	*/
	public String getProviderProgramId() {
		fetch();
		return providerProgramId;
	}	
	
	/**
	 * @return the programScheduleTypeId
	 */
	public String getProgramScheduleTypeId() {
		fetch();
		return programScheduleTypeId;
	}
	/**
	 * @param programScheduleTypeId the programScheduleTypeId to set
	 */
	public void setProgramScheduleTypeId(String programScheduleTypeId) {
		if (this.programScheduleTypeId == null || !this.programScheduleTypeId.equals(programScheduleTypeId)) {
			markModified();
		}
		this.programScheduleTypeId = programScheduleTypeId;
	}	
	/**
	* insert a pd.supervision.administerserviceprovider.Service into class relationship collection.
	*/
	public void insertServices(Service anObject) {
		initServices();
		services.add(anObject);
	}
	/**
	* Removes a pd.supervision.administerserviceprovider.Service from class relationship collection.
	*/
	public void removeServices(Service anObject) {
		initServices();
		services.remove(anObject);
	}
	/**
	* Clears all pd.supervision.administerserviceprovider.Service from class relationship collection.
	*/
	public void clearServices() {
		initServices();
		services.clear();
	}
	/**
	* @return pd.supervision.administerserviceprovider.ProviderProgram
	* @param programCode
	* @roseuid 4107B06D01B5
	*/
	static public ProviderProgram find(String providerProgramId) {
		ProviderProgram providerProgram = null;
		IHome home = new Home();
		providerProgram = (ProviderProgram) home.find(providerProgramId, ProviderProgram.class);
		return providerProgram;
	}
	
	/**
		* @return pd.supervision.administerserviceprovider.ProviderProgram
		* @param programCode
		* @roseuid 4107B06D01B5
		*/
		static public Iterator findAll(String attrName, String attrValue) {
			Iterator providerProgram = null;
			IHome home = new Home();
			providerProgram = home.findAll(attrName, attrValue, ProviderProgram.class);
			return providerProgram;
		}
	public void setProviderProgram(CreateProviderProgramEvent createEvent) {
		this.setProgramName(createEvent.getProgramName());
		this.setTjjdEdiCode(createEvent.getTjjdEdiCode());
		this.setProgramCode(createEvent.getProgramCode());
		this.setStateProgramCodeId(createEvent.getStateProgramCode());
		this.setTargetInterventionId(createEvent.getTargetIntervention());
		this.setProgramScheduleTypeId(createEvent.getProgramScheduleType());
		this.setStartDate(createEvent.getStartDate());
		this.setEndDate(createEvent.getEndDate());
		this.setProgramTypeId(createEvent.getType());
		//this.setProgramSubTypeId(createEvent.getSubType());//check this
		this.setProgramSubTypeId(createEvent.getProgramSubTypeId());
		this.setJuvenileServiceProviderId(createEvent.getJuvenileServiceProviderId());
		this.setDescription(createEvent.getDescription());
		this.setStatusId(createEvent.getStatusId());
		this.setProgramCategory(createEvent.getProgramCategory());
		this.setProgramLocation(createEvent.getProgramLocation());
		this.setTransferredProgRef(createEvent.getTransferredProgRef());
		this.setProgramId(createEvent.getProgramID());
		this.setDiscontinueDate(createEvent.getDiscontinueDate());
		this.setSupervisionCategory(createEvent.getSupervisionCategory());
		this.setMaxYouth(Integer.toString(createEvent.getMaxYouth()));  //added for US 190589
		//this.setCreateUserID(createEvent.getUserID());
	}	
	public void createOID() {
		new Home().bind(this);
	}		
	public void updateProgramStatus(CreateProviderProgramEvent createEvent) {
		this.setStatusId(createEvent.getStatusId());
		this.setStatusChangeDate(DateUtil.getCurrentDate());
//		this.setEndDate(DateUtil.getCurrentDate());
	}	
	public Date getStatusChangeDate() {
		fetch();
		return statusChangeDate;
	}		
	public void setStatusChangeDate(Date aStatusChangeDate) {
		if (this.statusChangeDate == null || !this.statusChangeDate.equals(aStatusChangeDate)) {
			markModified();
		}
		statusChangeDate = aStatusChangeDate;
	}
	
	/**
	* Initialize class relationship implementation for pd.supervision.administerserviceprovider.ProgramSourceFund
	*/
	private void initProgramSourceFunds() {
		if (programSourceFundList == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			programSourceFundList =
				new mojo.km.persistence.ArrayList(ProgramSourceFund.class, "providerProgramId", "" + getOID());
		}
	}
	/**
	* returns a collection of pd.supervision.administerserviceprovider.ProgramSourceFund
	*/
	public Collection getProgramSourceFundList() {
		fetch();
		initProgramSourceFunds();
		return programSourceFundList;
	}
	/**
	* insert a pd.supervision.administerserviceprovider.ProgramSourceFund into class relationship collection.
	*/
	public void insertProgramSourceFunds(ProgramSourceFund anObject) {
		initProgramSourceFunds();
		programSourceFundList.add(anObject);
	}
	/**
	* Removes a pd.supervision.administerserviceprovider.ProgramSourceFund from class relationship collection.
	*/
	public void removeProgramSourceFunds(ProgramSourceFund anObject) {
		initProgramSourceFunds();
		programSourceFundList.remove(anObject);
	}
	/**
	* Clears all pd.supervision.administerserviceprovider.ProgramSourceFund from class relationship collection.
	*/
	public void clearProgramSourceFunds() {
		initProgramSourceFunds();
		programSourceFundList.clear();
	}
	public String getProgramLocation() {
		fetch();
		return programLocation;
	}
	public void setProgramLocation(String aProgramLocation) {
		if (this.programLocation == null || !this.programLocation.equals(aProgramLocation)) {
			markModified();
		}
		this.programLocation = aProgramLocation;
	}
	public String getProgramCategory() {
		fetch();
		return programCategory;
	}
	public void setProgramCategory(String aProgramCategory) {
		if (this.programCategory == null || !this.programCategory.equals(aProgramCategory)) {
			markModified();
		}
		this.programCategory = aProgramCategory;
	}
	
	
	public String getTransferredProgRef()
	{
	    fetch();
	    return transferredProgRef;
	}


	public void setTransferredProgRef(String transferredProgRef)
	{
	    if (this.transferredProgRef == null || !this.transferredProgRef.equals(transferredProgRef)) {
		markModified();
	}
	    this.transferredProgRef = transferredProgRef;
	}
	public String getProgramId()
	{
	    fetch();
	    return programId;
	}
	public void setProgramId(String programId)
	{
	    if (this.programId == null || !this.programId.equals(programId)) {
		markModified();
	    }
	    this.programId = programId;
	}
	
	public String getSupervisionCategory() {
		fetch();
		return this.supervisionCategory;
	}
	
	public void setSupervisionCategory(String supervisionCategory) {
		if (this.supervisionCategory == null || !this.supervisionCategory.equals(supervisionCategory)) {
			markModified();
		}
		this.supervisionCategory = supervisionCategory;
	}
	public String getTjjdEdiCode()
	{
	    fetch();
	    return tjjdEdiCode;
	}
	public void setTjjdEdiCode(String tjjdEdiCode)
	{
	    if ( this.tjjdEdiCode == null || !this.tjjdEdiCode.equals( tjjdEdiCode ) ) {
		markModified();
	    }
	    this.tjjdEdiCode = tjjdEdiCode;
	}
	public String getMaxYouth()
	{
	    return maxYouth;
	}
	public void setMaxYouth(String maxYouth)
	{
	    this.maxYouth = maxYouth;
	}
	
	

}

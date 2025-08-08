/*
 * Created on Jun 20, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.administerserviceprovider.reply;
import java.util.Collection ;
import java.util.Comparator;
import java.util.Date;

import pd.supervision.administerserviceprovider.ProgramSourceFund;

import mojo.km.messaging.ResponseEvent;

/**
 * @author C_NAggarwal
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProviderProgramResponseEvent extends ResponseEvent implements Comparable
{
	public String providerProgramId;
	
	private String serviceProviderId;
	private String serviceProviderName;
	private boolean inHouse;
	private String serviceProviderStatusId;
	private String serviceType;
	private String programName;
	private String programCodeId;
	private String programTypeId;
	private String stateProgramCodeId;
	private String typeProgramCodeId;
	private String targetInterventionId;	
	private String programScheduleTypeId; //added for U.S #11099
	private String programStatusId;
	
	private Date startDate;
	private Date endDate;
	private String programDescription;

	// each Program will have one or more Services
	private Collection services ;
	
	//US #11376
	private String programCategory;
	private String programLocation;
	private Collection<ProgramSourceFundResponseEvent> programSourceFundList;
	private String transferredProgRef;
	public String ProgramID;
	private Date discontinueDate;
	private boolean isProgramDiscontinued = false;
	private String supervisionCategory;
	private String tjjdEdiCode;
	public String maxYouth;  //added for US 190589

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
	 * @return Returns the inHouse.
	 */
	public boolean isInHouse() {
		return inHouse;
	}
	/**
	 * @return Returns the programCode.
	 */
	public String getProgramCodeId() {
		return programCodeId;
	}
	/**
	 * @return Returns the programName.
	 */
	public String getProgramName() {
		return programName;
	}
	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}
	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @return Returns the serviceProviderStatusId.
	 */
	public String getServiceProviderStatusId() {
		return serviceProviderStatusId;
	}
	/**
	 * @return Returns the serviceType
	 */
	public String getServiceType() {
		return serviceType;
	}
	/**
	 * @return Returns the targetInterventionId.
	 */
	public String getTargetInterventionId() {
		return targetInterventionId;
	}
	/**
	 * @param inHouse The inHouse to set.
	 */
	public void setInHouse(boolean inHouse) {
		this.inHouse = inHouse;
	}
	/**
	 * @param programCode The programCode to set.
	 */
	public void setProgramCodeId(String programCodeId) {
		this.programCodeId = programCodeId;
	}
	/**
	 * @param programName The programName to set.
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	/**
	 * @param serviceProviderId The serviceProviderId to set.
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	/**
	 * @param serviceProviderName The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	/**
	 * @param serviceProviderStatusId The serviceProviderStatusId to set.
	 */
	public void setServiceProviderStatusId(String serviceProviderStatusId) {
		this.serviceProviderStatusId = serviceProviderStatusId;
	}
	/**
	 * @param targetInterventionId The targetInterventionId to set.
	 */
	public void setTargetInterventionId(String targetInterventionId) {
		this.targetInterventionId = targetInterventionId;
	}
	
	/**
	 * @return the programScheduleTypeId
	 */
	public String getProgramScheduleTypeId() {
		return programScheduleTypeId;
	}

	/**
	 * @param programScheduleTypeId the programScheduleTypeId to set
	 */
	public void setProgramScheduleTypeId(String programScheduleTypeId) {
		this.programScheduleTypeId = programScheduleTypeId;
	}
	/**
	 * @return Returns the programStatusId.
	 */
	public String getProgramStatusId() {
		return programStatusId;
	}
	/**
	 * @param programStatusId The programStatusId to set.
	 */
	public void setProgramStatusId(String programStatusId) {
		this.programStatusId = programStatusId;
	}
	/**
	 * @param serviceType The serviceType to set
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	/**
	 * @return Returns the stateProgramCodeId.
	 */
	public String getStateProgramCodeId() {
		return stateProgramCodeId;
	}
	/**
	 * @param stateProgramCodeId The stateProgramCodeId to set.
	 */
	public void setStateProgramCodeId(String stateProgramCodeId) {
		this.stateProgramCodeId = stateProgramCodeId;
	}
	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the programDescription.
	 */
	public String getProgramDescription() {
		return programDescription;
	}
	/**
	 * @param programDescription The programDescription to set.
	 */
	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}
	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public int compareTo(Object obj)
	{
		if(obj==null)
			return -1;
		ProviderProgramResponseEvent evt = (ProviderProgramResponseEvent) obj;
		return programName.compareToIgnoreCase(evt.getProgramName());		
	}	
	
	public static Comparator providerNameComparator = new Comparator() {
		public int compare(Object providerNameCom, Object otherProviderNameCom) {
		  String sName = ((ProviderProgramResponseEvent)providerNameCom).getServiceProviderName();
		  String otherSName = ((ProviderProgramResponseEvent)otherProviderNameCom).getServiceProviderName();	
		  return sName.compareTo(otherSName);
		}	
	};


	public String getProgramTypeId() {
		return programTypeId;
	}

	public void setProgramTypeId(String programTypeId) {
		this.programTypeId = programTypeId;
	}

	public Collection getServices( )
	{
		return services ;
	}

	public void setServices( Collection services )
	{
		this.services = services ;
	}

	public Collection<ProgramSourceFundResponseEvent> getProgramSourceFundList() {
		return programSourceFundList;
	}

	public void setProgramSourceFundList(Collection<ProgramSourceFundResponseEvent> programSourceFundList) {
		this.programSourceFundList = programSourceFundList;
	}

	public String getProgramCategory() {
		return programCategory;
	}

	public void setProgramCategory(String programCategory) {
		this.programCategory = programCategory;
	}

	public String getProgramLocation() {
		return programLocation;
	}

	public void setProgramLocation(String programLocation) {
		this.programLocation = programLocation;
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
	    return ProgramID;
	}

	public void setProgramID(String programID)
	{
	    ProgramID = programID;
	}
	
	public Date getDiscontinueDate()
	{
	    return this.discontinueDate;
	}

	public void setDiscontinueDate(Date discontinueDate)
	{
	    this.discontinueDate = discontinueDate;
	}
	
	public boolean getIsProgramDiscontinued()
	{
	    Date today = new Date();
	    
	    if(today != null && this.discontinueDate != null && !this.discontinueDate.equals("")){
		
		if(today.after(this.discontinueDate)){
		    this.isProgramDiscontinued = true;
		}
	    }
	    
	    return this.isProgramDiscontinued;
	}
	
	public String getSupervisionCategory()
	{
	    return this.supervisionCategory;
	}

	public void setSupervisionCategory(String supervisionCategory)
	{
	    this.supervisionCategory = supervisionCategory;
	}
	public String getTypeProgramCodeId()
	{
	    return typeProgramCodeId;
	}

	public void setTypeProgramCodeId(String typeProgramCodeId)
	{
	    this.typeProgramCodeId = typeProgramCodeId;
	}

	public String getTjjdEdiCode()
	{
	    return tjjdEdiCode;
	}

	public void setTjjdEdiCode(String tjjdEdiCode)
	{
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

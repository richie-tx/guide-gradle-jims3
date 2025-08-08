/*
 * Created on Apr 30, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.medical.form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.to.NameBean;
import mojo.km.utilities.DateUtil;

import org.apache.struts.action.ActionForm;

import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;

/**
 * @author ugopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MedicalForm extends ActionForm{

	private String juvenileNum="";
	private String selectedValue="";
	private String actionType="";
	private String confirmMessage="";
	private Date entryDate = new Date();
	private String selectedMed = "";
	//	Collections
	private Collection healthIssuesList = new ArrayList();
	private Collection medicationList = new ArrayList();
	private Collection hospitalizationList = new ArrayList();
	private Collection traitsList = new ArrayList();
	private Collection admissionTypes = new ArrayList();
	private Collection medications = new ArrayList();
	private Collection healthIssues = new ArrayList();
	private List<String> admitYears = new ArrayList();
	private List<CodeResponseEvent> hospitalLengthOfStays = new ArrayList();
	
	private HealthIssue hsRec = new HealthIssue();
	private Medication medicRec = new Medication();
	private Medication searchMedication = new Medication();
	private Hospitalization hospRec = new Hospitalization();
	private String hospitalLengthOfStay;
	private String hospitalLengthOfStayDesc;
	private String admitYear;
	
	public void clear()
	{
		selectedMed = "";
		admitYear = "";
		hospitalLengthOfStay = "";
		hospitalLengthOfStays = new ArrayList();
		
	}
	/**
	 * @return the action
	 *//*
	public String getAction() {
		return action;
	}
	*//**
	 * @param action the action to set
	 *//*
	public void setAction(String action) {
		this.action = action;
	}*/
	/**
	 * @return Returns the actionType.
	 */
	public String getActionType() {
		return actionType;
	}
	/**
	 * @param actionType The actionType to set.
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	/**
	 * @return Returns the admissionTypes.
	 */
	public Collection getAdmissionTypes() {
		return admissionTypes;
	}
	/**
	 * @param admissionTypes The admissionTypes to set.
	 */
	public void setAdmissionTypes(Collection admissionTypes) {
		this.admissionTypes = admissionTypes;
	}
	/**
	 * @return Returns the confirmMessage.
	 */
	public String getConfirmMessage() {
		return confirmMessage;
	}
	/**
	 * @param confirmMessage The confirmMessage to set.
	 */
	public void setConfirmMessage(String confirmMessage) {
		this.confirmMessage = confirmMessage;
	}
	
	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate() {
		return entryDate;
	}
	/**
	 * @param entryDate The entryDate to set.
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	/**
	 * @return Returns the healthIssuesList.
	 */
	public Collection getHealthIssuesList() {
		return healthIssuesList;
	}
	/**
	 * @param healthIssuesList The healthIssuesList to set.
	 */
	public void setHealthIssuesList(Collection healthIssuesList) {
		this.healthIssuesList = healthIssuesList;
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
	 * @return Returns the medicationList.
	 */
	public Collection getMedicationList() {
		return medicationList;
	}
	
	/**
	 * @return Returns the medications.
	 */
	public Collection getMedications() {
		return medications;
	}
	/**
	 * @param medications The medications to set.
	 */
	public void setMedications(Collection medications) {
		this.medications = medications;
	}
	/**
	 * @param medicationList The medicationList to set.
	 */
	public void setMedicationList(Collection medicationList) {
		this.medicationList = medicationList;
	}
	/**
	 * @return Returns the medicRec.
	 */
	public Medication getMedicRec() {
		return medicRec;
	}
	/**
	 * @param medicRec The medicRec to set.
	 */
	public void setMedicRec(Medication medicRec) {
		this.medicRec = medicRec;
	}
	/**
	 * @return Returns the hospitalizationList.
	 */
	public Collection getHospitalizationList() {
		return hospitalizationList;
	}
	/**
	 * @param hospitalizationList The hospitalizationList to set.
	 */
	public void setHospitalizationList(Collection hospitalizationList) {
		this.hospitalizationList = hospitalizationList;
	}
	/**
	 * @return Returns the traitsList.
	 */
	public Collection getTraitsList() {
		return traitsList;
	}
	/**
	 * @param traitsList The traitsList to set.
	 */
	public void setTraitsList(Collection traitsList) {
		this.traitsList = traitsList;
	}
	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	
	/**
	 * @return Returns the hsRec.
	 */
	public HealthIssue getHsRec() {
		return hsRec;
	}
	/**
	 * @param hsRec The hsRec to set.
	 */
	public void setHsRec(HealthIssue hsRec) {
		this.hsRec = hsRec;
	}
	/**
	 * @return Returns the hospRec.
	 */
	public Hospitalization getHospRec() {
		return hospRec;
	}
	/**
	 * @param hospRec The hospRec to set.
	 */
	public void setHospRec(Hospitalization hospRec) {
		this.hospRec = hospRec;
	}
	public static class HealthIssue{
		private String issueId="";
		private String issue="";
		private String issueStatusId="";
		private String issueStatus="";
		private String healthStatus=""; //added a new dropdown
		private String healthStatusId=""; //added a new dropdown ERJIMS200076774
		private String action=""; 
		private String conditionSeverityId="";
		private String conditionSeverity="";
		private String conditionLevelId="";
		private String conditionLevel="";
		private String modificationReason=""; //added new
		private String healthStatusFull="";
		public void clear()
		{
			issueId="";
			issueStatusId="";
			conditionSeverityId="";
			conditionLevelId="";
			healthStatus="";
			healthStatusId="";
			modificationReason="";
			healthStatusFull="";
		}
		/**
		 * @return Returns the conditionLevel.
		 */
		public String getConditionLevel() {
			return conditionLevel;
		}
		/**
		 * @param conditionLevel The conditionLevel to set.
		 */
		public void setConditionLevel(String conditionLevel) {
			this.conditionLevel = conditionLevel;
		}
		/**
		 * @return the modificationReason
		 */
		public String getModificationReason() {
			return modificationReason;
		}
		/**
		 * @param modificationReason the modificationReason to set
		 */
		public void setModificationReason(String modificationReason) {
			this.modificationReason = modificationReason;
		}
		/**
		 * @return Returns the conditionLevelId.
		 */
		public String getConditionLevelId() {
			return conditionLevelId;
		}
		/**
		 * @param conditionLevelId The conditionLevelId to set.
		 */
		public void setConditionLevelId(String conditionLevelId) {
			if(conditionLevelId != null || !conditionLevelId.equals(""))
			{
				this.conditionLevel = SimpleCodeTableHelper.getDescrByCode("HEALTH_CONDITION_LEVEL", conditionLevelId);
			}
			this.conditionLevelId = conditionLevelId;
		}
		/**
		 * @return Returns the conditionSeverity.
		 */
		public String getConditionSeverity() {
			return conditionSeverity;
		}
		/**
		 * @param conditionSeverity The conditionSeverity to set.
		 */
		public void setConditionSeverity(String conditionSeverity) {
			this.conditionSeverity = conditionSeverity;
		}
		/**
		 * @return the healthStatusFull
		 */
		public String getHealthStatusFull() {
			return healthStatusFull;
		}
		/**
		 * @param healthStatusFull the healthStatusFull to set
		 */
		public void setHealthStatusFull(String healthStatusFull) {
			this.healthStatusFull = healthStatusFull;
		}
		/**
		 * @return Returns the conditionSeverityId.
		 */
		public String getConditionSeverityId() {
			return conditionSeverityId;
		}
		/**
		 * @param conditionSeverityId The conditionSeverityId to set.
		 */
		public void setConditionSeverityId(String conditionSeverityId) {
			if(conditionSeverityId != null || !conditionSeverityId.equals(""))
			{
				this.conditionSeverity = SimpleCodeTableHelper.getDescrByCode("HEALTH_CONDITION_SEVERITY", conditionSeverityId);
			}
			this.conditionSeverityId = conditionSeverityId;
		}
		/**
		 * @return Returns the issue.
		 */
		public String getIssue() {
			return issue;
		}
		/**
		 * @param issue The issue to set.
		 */
		public void setIssue(String issue) {
			this.issue = issue;
		}
		/**
		 * @return Returns the issueId.
		 */
		public String getIssueId() {
			return issueId;
		}
		/**
		 * @param issueId The issueId to set.
		 */
		public void setIssueId(String issueId) {
			if(issueId != null || !issueId.equals(""))
			{
				this.issue = SimpleCodeTableHelper.getDescrByCode("HEALTH_ISSUE", issueId);
			}
			this.issueId = issueId;
		}
		/**
		 * @return Returns the issueStatus.
		 */
		public String getIssueStatus() {
			return issueStatus;
		}
		/**
		 * @param issueStatus The issueStatus to set.
		 */
		public void setIssueStatus(String issueStatus) {
			this.issueStatus = issueStatus;
		}
		/**
		 * @return the healthStatus
		 */
		public String getHealthStatus() {
			return healthStatus;
		}
		/**
		 * @param healthStatus the healthStatus to set
		 */
		public void setHealthStatus(String healthStatus) {
			this.healthStatus = healthStatus;
		}
		/**
		 * @return Returns the healthStatusId.
		 */
		public String getHealthStatusId() {
			return healthStatusId;
		}
		/**
		 * @param healthStatusId The healthStatusId to set.
		 */
		public void setHealthStatusId(String healthStatusId) {
			if(healthStatusId != null || !healthStatusId.equals(""))
			{
				this.healthStatus = SimpleCodeTableHelper.getDescrByCode("HEALTH_STATUS", healthStatusId);
			}
			this.healthStatusId = healthStatusId;
		}
		/**
		 * @return the action
		 */
		public String getAction() {
			return action;
		}
		/**
		 * @param action the action to set
		 */
		public void setAction(String action) {
			this.action = action;
		}
		/**
		 * @return Returns the issueStatusId.
		 */
		public String getIssueStatusId() {
			return issueStatusId;
		}
		/**
		 * @param issueStatusId The issueStatusId to set.
		 */
		public void setIssueStatusId(String issueStatusId) {
			if(issueStatusId != null || !issueStatusId.equals(""))
			{
				this.issueStatus = SimpleCodeTableHelper.getDescrByCode("HEALTH_ISSUE_STATUS", issueStatusId);
			}
			this.issueStatusId = issueStatusId;
		}
	}
	
	public static class Medication{
		
		//private String medication="";
		private String dosage="";
		private boolean currentlyTaking = true;
		private String frequencyId="";
		private String frequency="";
		private String currentlyTakingMedId=""; //added new for the dropdown
		private String currentlyTakingMed=""; //added new for the dropdown
		private String action; 
		private IName physicianName= new Name();
		private PhoneNumber physicianPhone = new PhoneNumber("");
		private String medicationReason="";
		private String medicationTypeId="";
		private String modificationReason=""; 
		private String currentlyTakingMedicationFull="";
		
		// Search fields
		private String medication="";
		private String tradeName ="";
		private String delivery = "";
		private String strength="";
		private String medCode="";
		private String usage="";
		
		
		public void clear()
		{			
			medication="";
			medCode="";
			currentlyTaking = true;
			frequencyId="";
			frequency="";
			currentlyTakingMedId="";
			currentlyTakingMed="";
			physicianName= new NameBean();
			physicianPhone = new PhoneNumber("");
			medicationReason="";
			modificationReason="";
			dosage="";
		}
		
		public void clearSearchCriteria()
		{				
			//tradeName="";
			medication="";
			delivery="";		
			strength="";
			medCode="";
			usage="";
		}
		/**
		 * @return Returns the currentlyTaking.
		 */
		public boolean isCurrentlyTaking() {
			return currentlyTaking;
		}
		/**
		 * @param currentlyTaking The currentlyTaking to set.
		 */
		public void setCurrentlyTaking(boolean currentlyTaking) {
			this.currentlyTaking = currentlyTaking;
		}
		/**
		 * @return Returns the dosage.
		 */
		public String getDosage() {
			return dosage;
		}
		/**
		 * @param dosage The dosage to set.
		 */
		public void setDosage(String dosage) {
			this.dosage = dosage;
		}
		/**
		 * @return Returns the frequency.
		 */
		public String getFrequency() {
			return frequency;
		}
		/**
		 * @param frequency The frequency to set.
		 */
		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}
		/**
		 * @return Returns the frequencyId.
		 */
		public String getFrequencyId() {
			return frequencyId;
		}
		/**
		 * @param frequencyId The frequencyId to set.
		 */
		public void setFrequencyId(String frequencyId) {
			if(frequencyId != null || !frequencyId.equals(""))
			{
				this.frequency = SimpleCodeTableHelper.getDescrByCode("MEDICATION_FREQUENCY", frequencyId);
			}
			this.frequencyId = frequencyId;
		}
		/**
		 * @return Returns the currentlyTakingMed.
		 */
		public String getCurrentlyTakingMed() {
			return currentlyTakingMed;
		}
		/**
		 * @param currentlyTakingMed The currentlyTakingMed to set.
		 */
		public void setCurrentlyTakingMed(String currentlyTakingMed) {
			this.currentlyTakingMed = currentlyTakingMed;
		}
		/**
		 * @return the action
		 */
		public String getAction() {
			return action;
		}

		/**
		 * @param action the action to set
		 */
		public void setAction(String action) {
			this.action = action;
		}

		/**
		 * @return Returns the currentlyTakingMedId.
		 */
		public String getCurrentlyTakingMedId() {
			return currentlyTakingMedId;
		}
		/**
		 * @param currentlyTakingMedId The currentlyTakingMedId to set.
		 */
		public void setCurrentlyTakingMedId(String currentlyTakingMedId) {
			if(currentlyTakingMedId != null || !currentlyTakingMedId.equals(""))
			{
				this.currentlyTakingMed = SimpleCodeTableHelper.getDescrByCode("MEDICATION_CURRENT", currentlyTakingMedId);
			}
			this.currentlyTakingMedId = currentlyTakingMedId;
		}
	
		/**
		 * @return the currentlyTakingMedicationFull
		 */
		public String getCurrentlyTakingMedicationFull() {
			return currentlyTakingMedicationFull;
		}

		/**
		 * @param currentlyTakingMedicationFull the currentlyTakingMedicationFull to set
		 */
		public void setCurrentlyTakingMedicationFull(
				String currentlyTakingMedicationFull) {
			this.currentlyTakingMedicationFull = currentlyTakingMedicationFull;
		}

		/**
		 * @return Returns the medication.
		 */
		public String getMedication() {
			return medication;
		}
		/**
		 * @param medication The medication to set.
		 */
		public void setMedication(String medication) {
			this.medication = medication;
		}
	
		/**
		 * @return Returns the medicationReason.
		 */
		public String getMedicationReason() {
			return medicationReason;
		}
		/**
		 * @param medicationReason The medicationReason to set.
		 */
		public void setMedicationReason(String medicationReason) {
			this.medicationReason = medicationReason;
		}
		/**
		 * @return Returns the modificationReason.
		 */
		public String getModificationReason() {
			return modificationReason;
		}
		/**
		 * @param modificationReason The modificationReason to set.
		 */
		public void setModificationReason(String modificationReason) {
			this.modificationReason = modificationReason;
		}
		/**
		 * @return Returns the physicianName.
		 */
		public IName getPhysicianName() {
			return physicianName;
		}
		/**
		 * @param physicianName The physicianName to set.
		 */
		public void setPhysicianName(IName physicianName) {
			this.physicianName = physicianName;
		}
		/**
		 * @return Returns the physicianPhone.
		 */
		public PhoneNumber getPhysicianPhone() {
			return physicianPhone;
		}
		/**
		 * @param physicianPhone The physicianPhone to set.
		 */
		public void setPhysicianPhone(PhoneNumber physicianPhone) {
			this.physicianPhone = physicianPhone;
		}
		/**
		 * @return Returns the delivery.
		 */
		public String getDelivery() {
			return delivery;
		}
		/**
		 * @param delivery The delivery to set.
		 */
		public void setDelivery(String delivery) {
			this.delivery = delivery;
		}
		/**
		 * @return Returns the medCode.
		 */
		public String getMedCode() {
			return medCode;
		}
		/**
		 * @param medCode The medCode to set.
		 */
		public void setMedCode(String medCode) {
			this.medCode = medCode;
		}
		/**
		 * @return Returns the strength.
		 */
		public String getStrength() {
			return strength;
		}
		/**
		 * @param strength The strength to set.
		 */
		public void setStrength(String strength) {
			this.strength = strength;
		}
		/**
		 * @return Returns the tradeName.
		 */
		public String getTradeName() {
			return tradeName;
		}
		/**
		 * @param tradeName The tradeName to set.
		 */
		public void setTradeName(String tradeName) {
			this.tradeName = tradeName;
		}
		/**
		 * @return Returns the usage.
		 */
		public String getUsage() {
			return usage;
		}
		/**
		 * @param usage The usage to set.
		 */
		public void setUsage(String usage) {
			this.usage = usage;
		}
		/**
		 * @return Returns the medicationTypeId.
		 */
		public String getMedicationTypeId() {
			return medicationTypeId;
		}
		/**
		 * @param medicationTypeId The medicationTypeId to set.
		 */
		public void setMedicationTypeId(String medicationTypeId) {
			this.medicationTypeId = medicationTypeId;
		}
	}
	
	public static class Hospitalization{
		private Date admissionDate = new Date();
		private String admissionType="";
		private String admissionTypeId="";
		private String facilityName="";
		private String hospitalizationReason="";
		private Date releaseDate = new Date();
		private IName admittingPhysicianName = new Name();
		private IPhoneNumber physicianPhone = new PhoneNumber("");
		
		
		public void clear()
		{
			admissionType="";
			admissionTypeId="";
			facilityName="";
			hospitalizationReason="";
			releaseDate = new Date();
			admissionDate = new Date();
			//releaseDate.
			admittingPhysicianName = new Name();
			physicianPhone = new PhoneNumber("");
		}
		
		private String formatDate(Date newDate)
		{
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			return formatter.format(newDate);
		}
		/**
		 * @return Returns the admissionDate.
		 */
		public String getAdmissionDate() {
			
			return DateUtil.dateToString(admissionDate, DateUtil.DATE_FMT_1);
		}
		/**
		 * @param admissionDate The admissionDate to set.
		 */
		public void setAdmissionDate(String admissionDate) {
			
			Date admDate = DateUtil.stringToDate(admissionDate, DateUtil.DATE_FMT_1);
			
			this.admissionDate = admDate;
		}
		
		public void setAdmissionDate(Date admissionDate) {
			this.admissionDate = admissionDate;
		}
		/**
		 * @return Returns the admissionType.
		 */
		public String getAdmissionType() {
			return admissionType;
		}
		/**
		 * @param admissionType The admissionType to set.
		 */
		public void setAdmissionType(String admissionType) {
			this.admissionType = admissionType;
		}
		/**
		 * @return Returns the admissionTypeId.
		 */
		public String getAdmissionTypeId() {
			return admissionTypeId;
		}
		/**
		 * @param admissionTypeId The admissionTypeId to set.
		 */
		public void setAdmissionTypeId(String admissionTypeId) {
			this.admissionTypeId = admissionTypeId;
		}
		/**
		 * @return Returns the admittingPhysicianName.
		 */
		public IName getAdmittingPhysicianName() {
			return admittingPhysicianName;
		}
		/**
		 * @param admittingPhysicianName The admittingPhysicianName to set.
		 */
		public void setAdmittingPhysicianName(IName admittingPhysicianName) {
			this.admittingPhysicianName = admittingPhysicianName;
		}
		/**
		 * @return Returns the facilityName.
		 */
		public String getFacilityName() {
			return facilityName;
		}
		/**
		 * @param facilityName The facilityName to set.
		 */
		public void setFacilityName(String facilityName) {
			this.facilityName = facilityName;
		}
		/**
		 * @return Returns the hospitalizationReason.
		 */
		public String getHospitalizationReason() {
			return hospitalizationReason;
		}
		/**
		 * @param hospitalizationReason The hospitalizationReason to set.
		 */
		public void setHospitalizationReason(String hospitalizationReason) {
			this.hospitalizationReason = hospitalizationReason;
		}
		/**
		 * @return Returns the physicianPhone.
		 */
		public IPhoneNumber getPhysicianPhone() {
			return physicianPhone;
		}
		/**
		 * @param physicianPhone The physicianPhone to set.
		 */
		public void setPhysicianPhone(IPhoneNumber physicianPhone) {
			this.physicianPhone = physicianPhone;
		}
		/**
		 * @return Returns the releaseDate.
		 */
		public String getReleaseDate() {
			return DateUtil.dateToString(releaseDate, DateUtil.DATE_FMT_1);
		}
		/**
		 * @param releaseDate The releaseDate to set.
		 */
		public void setReleaseDate(String releaseDate) {
			Date relDate = DateUtil.stringToDate(releaseDate, DateUtil.DATE_FMT_1);
			this.releaseDate = relDate;
		}
		/**
		 * @param releaseDate The releaseDate to set.
		 */
		public void setReleaseDate(Date releaseDate) {
			this.releaseDate = releaseDate;
		}
	}
	/**
	 * @return Returns the selectedMed.
	 */
	public String getSelectedMed() {
		return selectedMed;
	}
	/**
	 * @param selectedMed The selectedMed to set.
	 */
	public void setSelectedMed(String selectedMed) {
		this.selectedMed = selectedMed;
	}
	
	/**
	 * @return Returns the searchMedication.
	 */
	public Medication getSearchMedication() {
		return searchMedication;
	}
	/**
	 * @param searchMedication The searchMedication to set.
	 */
	public void setSearchMedication(Medication searchMedication) {
		this.searchMedication = searchMedication;
	}
	/**
	 * @return the healthIssues
	 */
	public Collection getHealthIssues() {
		return healthIssues;
	}
	/**
	 * @param healthIssues the healthIssues to set
	 */
	public void setHealthIssues(Collection healthIssues) {
		this.healthIssues = healthIssues;
	}
	public String getHospitalLengthOfStay()
	{
	    return hospitalLengthOfStay;
	}
	public void setHospitalLengthOfStay(String hospitalLengthOfStay)
	{
	    this.hospitalLengthOfStay = hospitalLengthOfStay;
	}
	public String getAdmitYear()
	{
	    return admitYear;
	}
	public void setAdmitYear(String admitYear)
	{
	    this.admitYear = admitYear;
	}
	public List<CodeResponseEvent> getHospitalLengthOfStays()
	{
	    return hospitalLengthOfStays;
	}
	public void setHospitalLengthOfStays(List<CodeResponseEvent> hospitalLengthOfStays)
	{
	    this.hospitalLengthOfStays = hospitalLengthOfStays;
	}
	public List<String> getAdmitYears()
	{
	    return admitYears;
	}
	public void setAdmitYears(List<String> admitYears)
	{
	    this.admitYears = admitYears;
	}
	public String getHospitalLengthOfStayDesc()
	{
	    return hospitalLengthOfStayDesc;
	}
	public void setHospitalLengthOfStayDesc(String hospitalLengthOfStayDesc)
	{
	    this.hospitalLengthOfStayDesc = hospitalLengthOfStayDesc;
	}
	
	
	
	
	
	
	
	
	
	
	
}

package ui.supervision.programReferral.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionForm;

import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;

public class CSCProgRefCaseloadForm extends ActionForm
{
	private String action;
	
	private String searchBy;
	
	private String officerNamePosition;
	private List defendantIdsList = new ArrayList();
	
	private String serviceProviderName;
	private String inHouse;
	private String regionCd;
	private List inHouseList = new ArrayList();
	private List regionsList = new ArrayList();
	
	private String programName;
	private String cstsCode;
	
	private String programGroupId;
	private String programTypeId;
	private String programSubTypeId;
	
	private Collection cstsCodesList;
	private Collection programHeirarchyList; 
	
	private String serviceProvidersSize;
	private Collection serviceProvidersList = new ArrayList();
	private String programNamesSize;
	private Collection programNamesList = new ArrayList();
	private Collection programsList = new ArrayList();
	private Collection locationsList = new ArrayList();
	private Collection referralsList = new ArrayList();
	private Collection allReferralsList = new ArrayList();
	private String activeReferrals;
	private String exitedReferrals;
	private String superviseeSize;
	
	private Map serProvIdBeanMap = new HashMap();
	private Map programNameBeanMap = new HashMap();
	private Map programIdBeanMap = new HashMap();
	private Map locIdBeanMap = new HashMap();
	
	private String officerName;
	private String officerPosition;
	private String spName;
	private String progName;
	private String progIdentifier;
	private String streetNumber;
	private String streetName;
	private String streetTypeCd;
	private String aptNum;
	private String city;
	private String state;
	private String zipCode;
	
	private String selectedId;
	private String selectedProgramName;

	private String selectedSpId;
	private String selectedProgramId;
	private String selectedCriminalCaseId;
	
	public void clearAll()
	{
		action = "";
			
		searchBy="";
		
		officerNamePosition = "";
		defendantIdsList = new ArrayList();
		
		serviceProviderName="";
		inHouse="";
		regionCd="";
		inHouseList = new ArrayList();
		regionsList = new ArrayList();
		
		programName = "";
		cstsCode = "";
		
		programGroupId = "";
		programTypeId = "";
		programSubTypeId = "";
		
		cstsCodesList = new ArrayList();
		programHeirarchyList = new ArrayList(); 
		
		serviceProvidersSize= "";
		serviceProvidersList = new ArrayList();
		programNamesSize = "";
		programNamesList = new ArrayList();
		programsList = new ArrayList();
		locationsList = new ArrayList();
		referralsList = new ArrayList();
		allReferralsList = new ArrayList();
		activeReferrals = "";
		exitedReferrals = "";
		superviseeSize = "";
		
		serProvIdBeanMap = new HashMap();
		programNameBeanMap = new HashMap();
		programIdBeanMap = new HashMap();
		locIdBeanMap = new HashMap();
		
		officerName = "";
		officerPosition = "";
		spName = "";
		progName = "";
		progIdentifier = "";
		streetNumber = "";
		streetName = "";
		streetTypeCd = "";
		aptNum = "";
		city = "";
		state = "";
		zipCode = "";
		
		selectedId = "";
		selectedProgramName = "";
		
		selectedSpId = "";
		selectedProgramId = "";
		selectedCriminalCaseId = "";
	}

	public void clearSpCurrentCaseloadHeader()
	{
		action = "";
		
		selectedId = "";
		selectedProgramName = "";
		
		spName = "";
		progName = "";
		progIdentifier= "";
		streetNumber = "";
		streetName = "";
		streetTypeCd = "";
		aptNum = "";
		city = "";
		state = "";
		zipCode = "";
	}
	
	public void clearProgramCurrentCaseloadHeader()
	{
		action = "";
		
		selectedId = "";
		selectedProgramName = "";
		
		progName = "";
		progIdentifier= "";
		streetNumber = "";
		streetName = "";
		streetTypeCd = "";
		aptNum = "";
		city = "";
		state = "";
		zipCode = "";
	}
	
	
	public void clearLocCurrentCaseloadHeader()
	{
		action = "";
		
		selectedId = "";
		selectedProgramName = "";
		
		streetNumber = "";
		streetName = "";
		streetTypeCd = "";
		aptNum = "";
		city = "";
		state = "";
		zipCode = "";
	}
	
	
	/**
	 * @return the searchBy
	 */
	public String getSearchBy() {
		return searchBy;
	}


	/**
	 * @param searchBy the searchBy to set
	 */
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}


	/**
	 * @return the serviceProviderName
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}


	/**
	 * @param serviceProviderName the serviceProviderName to set
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}


	/**
	 * @return the inHouse
	 */
	public String getInHouse() {
		return inHouse;
	}


	/**
	 * @param inHouse the inHouse to set
	 */
	public void setInHouse(String inHouse) {
		this.inHouse = inHouse;
	}


	/**
	 * @return the regionCd
	 */
	public String getRegionCd() {
		return regionCd;
	}


	/**
	 * @param regionCd the regionCd to set
	 */
	public void setRegionCd(String regionCd) {
		this.regionCd = regionCd;
	}


	/**
	 * @return the inHouseList
	 */
	public List getInHouseList() {
		return inHouseList;
	}


	/**
	 * @param inHouseList the inHouseList to set
	 */
	public void setInHouseList(List inHouseList) {
		this.inHouseList = inHouseList;
	}


	/**
	 * @return the regionsList
	 */
	public List getRegionsList() {
		return regionsList;
	}


	/**
	 * @param regionsList the regionsList to set
	 */
	public void setRegionsList(List regionsList) {
		this.regionsList = regionsList;
	}


	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}


	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}


	/**
	 * @return the cstsCode
	 */
	public String getCstsCode() {
		return cstsCode;
	}


	/**
	 * @param cstsCode the cstsCode to set
	 */
	public void setCstsCode(String cstsCode) {
		this.cstsCode = cstsCode;
	}


	/**
	 * @return the programGroupId
	 */
	public String getProgramGroupId() {
		return programGroupId;
	}


	/**
	 * @param programGroupId the programGroupId to set
	 */
	public void setProgramGroupId(String programGroupId) {
		this.programGroupId = programGroupId;
	}


	/**
	 * @return the programTypeId
	 */
	public String getProgramTypeId() {
		return programTypeId;
	}


	/**
	 * @param programTypeId the programTypeId to set
	 */
	public void setProgramTypeId(String programTypeId) {
		this.programTypeId = programTypeId;
	}


	/**
	 * @return the programSubTypeId
	 */
	public String getProgramSubTypeId() {
		return programSubTypeId;
	}


	/**
	 * @param programSubTypeId the programSubTypeId to set
	 */
	public void setProgramSubTypeId(String programSubTypeId) {
		this.programSubTypeId = programSubTypeId;
	}


	/**
	 * @return the programGroups
	 */
	public Collection getProgramGroups() {
		return SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.ASP_CS_PROGRAM_GROUP);
	}

	/**
	 * @return the programTypes
	 */
	public Collection getProgramTypes() {
		return SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.ASP_CS_PROGRAM_TYPE);
	}
	/**
	 * @return the programSubTypes
	 */
	public Collection getProgramSubTypes() {
		return SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.ASP_CS_PROGRAM_SUB_TYPE);
	}

	/**
	 * @return the programHeirarchyList
	 */
	public Collection getProgramHeirarchyList() {
		if( this.programHeirarchyList == null || programHeirarchyList.size() < 1 ){
			
			this.setProgramHeirarchyList(ComplexCodeTableHelper.getCSCServiceProviderProgramHeirarchy());
		}
		return programHeirarchyList;
	}


	/**
	 * @param programHeirarchyList the programHeirarchyList to set
	 */
	public void setProgramHeirarchyList(Collection programHeirarchyList) {
		this.programHeirarchyList = programHeirarchyList;
	}


	/**
	 * @return the defendantIdsList
	 */
	public List getDefendantIdsList() {
		return defendantIdsList;
	}


	/**
	 * @param defendantIdsList the defendantIdsList to set
	 */
	public void setDefendantIdsList(List defendantIdsList) {
		this.defendantIdsList = defendantIdsList;
	}
	/**
	 * @return the serviceProvidersList
	 */
	public Collection getServiceProvidersList() {
		return serviceProvidersList;
	}


	/**
	 * @param serviceProvidersList the serviceProvidersList to set
	 */
	public void setServiceProvidersList(Collection serviceProvidersList) {
		this.serviceProvidersList = serviceProvidersList;
	}


	/**
	 * @return the serProvIdBeanMap
	 */
	public Map getSerProvIdBeanMap() {
		return serProvIdBeanMap;
	}


	/**
	 * @param serProvIdBeanMap the serProvIdBeanMap to set
	 */
	public void setSerProvIdBeanMap(Map serProvIdBeanMap) {
		this.serProvIdBeanMap = serProvIdBeanMap;
	}


	/**
	 * @return the programIdBeanMap
	 */
	public Map getProgramIdBeanMap() {
		return programIdBeanMap;
	}


	/**
	 * @param programIdBeanMap the programIdBeanMap to set
	 */
	public void setProgramIdBeanMap(Map programIdBeanMap) {
		this.programIdBeanMap = programIdBeanMap;
	}


	/**
	 * @return the locIdBeanMap
	 */
	public Map getLocIdBeanMap() {
		return locIdBeanMap;
	}


	/**
	 * @param locIdBeanMap the locIdBeanMap to set
	 */
	public void setLocIdBeanMap(Map locIdBeanMap) {
		this.locIdBeanMap = locIdBeanMap;
	}


	/**
	 * @return the serviceProvidersSize
	 */
	public String getServiceProvidersSize() {
		return serviceProvidersSize;
	}
	/**
	 * @param serviceProvidersSize the serviceProvidersSize to set
	 */
	public void setServiceProvidersSize(String serviceProvidersSize) {
		this.serviceProvidersSize = serviceProvidersSize;
	}
	/**
	 * @return the programsList
	 */
	public Collection getProgramsList() {
		return programsList;
	}


	/**
	 * @param programsList the programsList to set
	 */
	public void setProgramsList(Collection programsList) {
		this.programsList = programsList;
	}
	/**
	 * @return the locationsList
	 */
	public Collection getLocationsList() {
		return locationsList;
	}
	/**
	 * @param locationsList the locationsList to set
	 */
	public void setLocationsList(Collection locationsList) {
		this.locationsList = locationsList;
	}

	/**
	 * @return the selectedId
	 */
	public String getSelectedId() {
		return selectedId;
	}
	/**
	 * @param selectedId the selectedId to set
	 */
	public void setSelectedId(String selectedId) {
		this.selectedId = selectedId;
	}
	/**
	 * @return the referralsList
	 */
	public Collection getReferralsList() {
		return referralsList;
	}
	/**
	 * @param referralsList the referralsList to set
	 */
	public void setReferralsList(Collection referralsList) {
		this.referralsList = referralsList;
	}
	/**
	 * @return the programNameBeanMap
	 */
	public Map getProgramNameBeanMap() {
		return programNameBeanMap;
	}
	/**
	 * @param programNameBeanMap the programNameBeanMap to set
	 */
	public void setProgramNameBeanMap(Map programNameBeanMap) {
		this.programNameBeanMap = programNameBeanMap;
	}
	/**
	 * @return the programNamesList
	 */
	public Collection getProgramNamesList() {
		return programNamesList;
	}
	/**
	 * @param programNamesList the programNamesList to set
	 */
	public void setProgramNamesList(Collection programNamesList) {
		this.programNamesList = programNamesList;
	}
	/**
	 * @return the programNamesSize
	 */
	public String getProgramNamesSize() {
		return programNamesSize;
	}
	/**
	 * @param programNamesSize the programNamesSize to set
	 */
	public void setProgramNamesSize(String programNamesSize) {
		this.programNamesSize = programNamesSize;
	}
	/**
	 * @return the selectedProgramName
	 */
	public String getSelectedProgramName() {
		return selectedProgramName;
	}
	/**
	 * @param selectedProgramName the selectedProgramName to set
	 */
	public void setSelectedProgramName(String selectedProgramName) {
		this.selectedProgramName = selectedProgramName;
	}
	/**
	 * @return the cstsCodesList
	 */
	public Collection getCstsCodesList() {
		return cstsCodesList;
	}


	/**
	 * @param cstsCodesList the cstsCodesList to set
	 */
	public void setCstsCodesList(Collection cstsCodesList) {
		this.cstsCodesList = cstsCodesList;
	}
	/**
	 * @return the allReferralsList
	 */
	public Collection getAllReferralsList() {
		return allReferralsList;
	}


	/**
	 * @param allReferralsList the allReferralsList to set
	 */
	public void setAllReferralsList(Collection allReferralsList) {
		this.allReferralsList = allReferralsList;
	}
	/**
	 * @return the officerName
	 */
	public String getOfficerName() {
		return officerName;
	}


	/**
	 * @param officerName the officerName to set
	 */
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}


	/**
	 * @return the officerPosition
	 */
	public String getOfficerPosition() {
		return officerPosition;
	}


	/**
	 * @param officerPosition the officerPosition to set
	 */
	public void setOfficerPosition(String officerPosition) {
		this.officerPosition = officerPosition;
	}


	/**
	 * @return the spName
	 */
	public String getSpName() {
		return spName;
	}


	/**
	 * @param spName the spName to set
	 */
	public void setSpName(String spName) {
		this.spName = spName;
	}


	/**
	 * @return the progName
	 */
	public String getProgName() {
		return progName;
	}


	/**
	 * @param progName the progName to set
	 */
	public void setProgName(String progName) {
		this.progName = progName;
	}


	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}


	/**
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}


	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}


	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}


	/**
	 * @return the streetTypeCd
	 */
	public String getStreetTypeCd() {
		return streetTypeCd;
	}


	/**
	 * @param streetTypeCd the streetTypeCd to set
	 */
	public void setStreetTypeCd(String streetTypeCd) {
		this.streetTypeCd = streetTypeCd;
	}


	/**
	 * @return the aptNum
	 */
	public String getAptNum() {
		return aptNum;
	}


	/**
	 * @param aptNum the aptNum to set
	 */
	public void setAptNum(String aptNum) {
		this.aptNum = aptNum;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}


	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the progIdentifier
	 */
	public String getProgIdentifier() {
		return progIdentifier;
	}

	/**
	 * @param progIdentifier the progIdentifier to set
	 */
	public void setProgIdentifier(String progIdentifier) {
		this.progIdentifier = progIdentifier;
	}
	/**
	 * @return the selectedSpId
	 */
	public String getSelectedSpId() {
		return selectedSpId;
	}

	/**
	 * @param selectedSpId the selectedSpId to set
	 */
	public void setSelectedSpId(String selectedSpId) {
		this.selectedSpId = selectedSpId;
	}

	/**
	 * @return the selectedProgramId
	 */
	public String getSelectedProgramId() {
		return selectedProgramId;
	}

	/**
	 * @param selectedProgramId the selectedProgramId to set
	 */
	public void setSelectedProgramId(String selectedProgramId) {
		this.selectedProgramId = selectedProgramId;
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
	 * @return the activeReferrals
	 */
	public String getActiveReferrals() {
		return activeReferrals;
	}

	/**
	 * @param activeReferrals the activeReferrals to set
	 */
	public void setActiveReferrals(String activeReferrals) {
		this.activeReferrals = activeReferrals;
	}

	/**
	 * @return the exitedReferrals
	 */
	public String getExitedReferrals() {
		return exitedReferrals;
	}
	
	/**
	 * @param exitedReferrals the exitedReferrals to set
	 */
	public void setExitedReferrals(String exitedReferrals) {
		this.exitedReferrals = exitedReferrals;
	}

	/**
	 * @return the superviseeSize
	 */
	public String getSuperviseeSize() {
		return superviseeSize;
	}

	/**
	 * @param superviseeSize the superviseeSize to set
	 */
	public void setSuperviseeSize(String superviseeSize) {
		this.superviseeSize = superviseeSize;
	}
	/**
	 * @return the officerNamePosition
	 */
	public String getOfficerNamePosition() {
		return officerNamePosition;
	}

	/**
	 * @param officerNamePosition the officerNamePosition to set
	 */
	public void setOfficerNamePosition(String officerNamePosition) {
		this.officerNamePosition = officerNamePosition;
	}
	/**
	 * @return the selectedCriminalCaseId
	 */
	public String getSelectedCriminalCaseId() {
		return selectedCriminalCaseId;
	}

	/**
	 * @param selectedCriminalCaseId the selectedCriminalCaseId to set
	 */
	public void setSelectedCriminalCaseId(String selectedCriminalCaseId) {
		this.selectedCriminalCaseId = selectedCriminalCaseId;
	}





	public class CstsCodeBean
	{
		private String code;
		private String description;
		
		public CstsCodeBean(String cstsCode)
		{
			code = cstsCode;
			description = cstsCode;
		}
		
		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}
		/**
		 * @param code the code to set
		 */
		public void setCode(String code) {
			this.code = code;
		}
		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}
	}
	
	
	public void populateCSTSCodeBeans(Set cstsCodesSet)
	{
		Set myset = new TreeSet( cstsCodesSet );
		
		this.cstsCodesList = new ArrayList();
		Iterator iter = myset.iterator();
		while(iter.hasNext())
		{
			String cstsCode = (String)iter.next();
			CstsCodeBean cstsCodeBean = new CstsCodeBean(cstsCode);
			this.cstsCodesList.add(cstsCodeBean);
		}
	}
}

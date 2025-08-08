/*
 * Created on May 3, 2005
 *
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.validator.ValidatorForm;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;

/**
 * @author dapte
 * 
 *  
 */
public class CasefileSearchForm extends ValidatorForm
{
    // The codeID for the selected search type
    private String searchTypeId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String officerFirstName;

    private String officerMiddleName;

    private String officerLastName;

    private String juvenileNum;
    
    private String juvenileNameType;

	private String supervisionNum;

    private String locationId;
    
    private String zipCode; //#32659 changes
    
    private String dispositionDate; //#35786 changes.
    


    // The codeID for the selected supervision type searching by Case Status
    private String supervisionTypeId;

    // The codeID for the selected case status type searching by Case Status
    private String caseStatusTypeId;

    // The codeID for the selected supervision type searching by Supervision
    // Type
    private String supervisionTypeId2;

    // The codeID for the selected case status type searching by Supervision
    // Type
    private String caseStatusTypeId2;

    // The collection of casefiles that matched the search;
    private Collection casefileSearchResults;

    // Total Search Results Count
    private int searchResultSize;
    
    private String caseLoadMgr;
    
    private List caseLoadMgrs;
    
    private List officers;
    
    private String officer;
    
    private List caseloads;
    
    private String caseStatusCd;
    
    private int juvenilesCount;
    
    private int casefilesCount;
    
    //<KISHORE>JIMS200060469 : MJCW CF: Add Caseload Search date range(UI)-KK
    private String casefileActivationStDate;
    private String casefileActivationEndDate;
    
    private String caseloadExpectedEndDateFrom;
    private String caseloadExpectedEndDateTo;
    private String caseStatusExpectedEndDateFrom;
    private String caseStatusExpectedEndDateTo;
    private String caseStatusDispDateFrom; // Task 50044 changes
    private String caseStatusDispDateTo; // Task 50044 changes
 
	//#32659 changes starts
    /**
     * Juvnile count on zipcode,officers,supervisions
     */
	Collection<JuvenileCasefileSearchResponseEvent> zipCodes_count;
	Collection<JuvenileCasefileSearchResponseEvent> officers_count;
	Collection<JuvenileCasefileSearchResponseEvent> supervisions_count;
	
	
	/**
	 * Total juvenile counts for zipcode,officers,supervision type
	 */
	String totZipJuvCount;
	String totOffJuvCount;
	String totSupvJuvCount;
	//#32659 changes ends
	
	//Added for US 32107
	private String restrictedAccessFeature;
	
    /**
     * Clears the form fields
     */
    public void clear()
    {
        this.searchTypeId = null;
        this.firstName = null;
        this.middleName = null;
        this.lastName = null;
        this.zipCode = null;
        this.officerFirstName = null;
        this.officerMiddleName = null;
        this.officerLastName = null;
        this.supervisionNum = null;
        this.supervisionTypeId = null;
        this.supervisionTypeId2 = null;
        this.juvenileNum = null;
        this.juvenileNameType = null;
        this.caseStatusTypeId = null;
        this.caseStatusTypeId2 = null;
        this.casefileSearchResults = new ArrayList();
        this.searchResultSize = 0;
        this.caseLoadMgr=null;
        this.caseLoadMgrs= new ArrayList();
        this.officer= null;
        this.officers = new ArrayList();
        this.casefilesCount = 0;
        this.juvenilesCount = 0;
        this.caseloads = new ArrayList();;
        this.caseStatusCd = null;
        this.casefileActivationStDate = null;
        this.casefileActivationEndDate = null;
        this.caseloadExpectedEndDateFrom = null;
        this.caseloadExpectedEndDateTo = null;
        this.caseStatusExpectedEndDateFrom = null;
        this.caseStatusExpectedEndDateTo = null;
    }
    
    /**
     * Clears the form fields
     */
    public void reset()
    {
        this.casefilesCount = 0;
        this.juvenilesCount = 0;
        this.caseloads = new ArrayList();;
    }
	
	
	/**
	 * @return the zipCodes_count
	 */
	public Collection<JuvenileCasefileSearchResponseEvent> getZipCodes_count() {
		return zipCodes_count;
	}

	/**
	 * @param zipCodes_count the zipCodes_count to set
	 */
	public void setZipCodes_count(Collection<JuvenileCasefileSearchResponseEvent> zipCodes_count) {
		this.zipCodes_count = zipCodes_count;
	}

	/**
	 * @return the officers_count
	 */
	public Collection<JuvenileCasefileSearchResponseEvent> getOfficers_count() {
		return officers_count;
	}

	/**
	 * @param officers_count the officers_count to set
	 */
	public void setOfficers_count(Collection<JuvenileCasefileSearchResponseEvent> officers_count) {
		this.officers_count = officers_count;
	}

	/**
	 * @return the supervisions_count
	 */
	public Collection<JuvenileCasefileSearchResponseEvent> getSupervisions_count() {
		return supervisions_count;
	}

	/**
	 * @param supervisions_count the supervisions_count to set
	 */
	public void setSupervisions_count(Collection<JuvenileCasefileSearchResponseEvent> supervisions_count) {
		this.supervisions_count = supervisions_count;
	}

	/**
	 * @return the totZipJuvCount
	 */
	public String getTotZipJuvCount() {
		return totZipJuvCount;
	}

	/**
	 * @param totZipJuvCount the totZipJuvCount to set
	 */
	public void setTotZipJuvCount(String totZipJuvCount) {
		this.totZipJuvCount = totZipJuvCount;
	}

	/**
	 * @return the totOffJuvCount
	 */
	public String getTotOffJuvCount() {
		return totOffJuvCount;
	}

	/**
	 * @param totOffJuvCount the totOffJuvCount to set
	 */
	public void setTotOffJuvCount(String totOffJuvCount) {
		this.totOffJuvCount = totOffJuvCount;
	}

	/**
	 * @return the totSupvJuvCount
	 */
	public String getTotSupvJuvCount() {
		return totSupvJuvCount;
	}

	/**
	 * @param totSupvJuvCount the totSupvJuvCount to set
	 */
	public void setTotSupvJuvCount(String totSupvJuvCount) {
		this.totSupvJuvCount = totSupvJuvCount;
	}

    
    /**
     * @return searchTypeId
     */
    public String getSearchTypeId()
    {
        return this.searchTypeId;
    }

    /**
     * @param aSearchTypeId
     */
    public void setSearchTypeId(String aSearchTypeId)
    {
        this.searchTypeId = aSearchTypeId;
    }

    /**
     * @return firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param aFirstName
     */
    public void setFirstName(String aFirstName)
    {

        if (aFirstName != null)
        {
            aFirstName = aFirstName.trim();
        }
        this.firstName = aFirstName;
    }

    /**
     * @return middleName
     */
    public String getMiddleName()
    {
        return this.middleName;
    }

    /**
     * @param aMiddleName
     */
    public void setMiddleName(String aMiddleName)
    {
        if (aMiddleName != null)
        {
            aMiddleName = aMiddleName.trim();
        }
        this.middleName = aMiddleName;
    }

    /**
     * @return lastName
     */
    public String getLastName()
    {
        return this.lastName;
    }

    /**
     * @param aLastName
     */
    public void setLastName(String aLastName)
    {
        if (aLastName != null)
        {
            aLastName = aLastName.trim();
        }
        this.lastName = aLastName;
    }

    public String getJuvenileNameType() {
		return juvenileNameType;
	}

	public void setJuvenileNameType(String juvenileNameType) {
		this.juvenileNameType = juvenileNameType;
	}

    /**
     * @return String
     */
    public String getOfficerFirstName()
    {
        return officerFirstName;
    }

    /**
     * @param aOfficerFirstName
     */
    public void setOfficerFirstName(String aOfficerFirstName)
    {
        if (aOfficerFirstName != null)
        {
            aOfficerFirstName = aOfficerFirstName.trim();
        }
        this.officerFirstName = aOfficerFirstName;
    }

    /**
     * @return aOfficerMiddleName
     */
    public String getOfficerMiddleName()
    {
        return this.officerMiddleName;
    }

    /**
     * @param aOfficerMiddleName
     */
    public void setOfficerMiddleName(String aOfficerMiddleName)
    {
        if (aOfficerMiddleName != null)
        {
            aOfficerMiddleName = aOfficerMiddleName.trim();
        }
        this.officerMiddleName = aOfficerMiddleName;
    }

    /**
     * @return aOfficerLastName
     */
    public String getOfficerLastName()
    {
        return this.officerLastName;
    }

    /**
     * @param aOfficerLastName
     */
    public void setOfficerLastName(String aOfficerLastName)
    {
        if (aOfficerLastName != null)
        {
            aOfficerLastName = aOfficerLastName.trim();
        }
        this.officerLastName = aOfficerLastName;
    }

    /**
     * @return juvenileNum
     */
    public String getJuvenileNum()
    {
        return this.juvenileNum;
    }

    /**
     * @param aJuvenileNum
     */
    public void setJuvenileNum(String aJuvenileNum)
    {
        if (aJuvenileNum != null)
        {
            aJuvenileNum = aJuvenileNum.trim();
        }
        this.juvenileNum = aJuvenileNum;
    }

    /**
     * @return supervisionNum
     */
    public String getSupervisionNum()
    {
        return this.supervisionNum;
    }

    /**
     * @param aSupervisionNum
     */
    public void setSupervisionNum(String aSupervisionNum)
    {
        if (aSupervisionNum != null)
        {
            aSupervisionNum = aSupervisionNum.trim();
        }
        this.supervisionNum = aSupervisionNum;
    }

    /**
     * @return supervisionTypeId
     */
    public String getSupervisionTypeId()
    {
        return this.supervisionTypeId;
    }

    /**
     * @return supervisionTypeId
     */
    public String getSupervisionTypeId2()
    {
        return this.supervisionTypeId2;
    }

    /**
     * @param aSupervisionTypeId
     */
    public void setSupervisionTypeId(String aSupervisionTypeId)
    {
        this.supervisionTypeId = aSupervisionTypeId;
    }

    /**
     * @param aSupervisionTypeId
     */
    public void setSupervisionTypeId2(String aSupervisionTypeId2)
    {
        this.supervisionTypeId2 = aSupervisionTypeId2;
    }

    /**
     * @return caseStatusTypeId
     */
    public String getCaseStatusTypeId()
    {
        return this.caseStatusTypeId;
    }

    /**
     * @return caseStatusTypeId
     */
    public String getCaseStatusTypeId2()
    {
        return this.caseStatusTypeId2;
    }

    /**
     * 
     * @param aCaseStatusTypeId
     */
    public void setCaseStatusTypeId(String aCaseStatusTypeId)
    {
        this.caseStatusTypeId = aCaseStatusTypeId;
    }

    /**
     * @param aCaseStatusTypeId
     */
    public void setCaseStatusTypeId2(String aCaseStatusTypeId2)
    {
        this.caseStatusTypeId2 = aCaseStatusTypeId2;
    }

    /**
     * @return casefileSearchResults
     */
    public Collection getCasefileSearchResults()
    {
        return this.casefileSearchResults;
    }

    /**
     * @param aCasefileSearchResults
     */
    public void setCasefileSearchResults(Collection aCasefileSearchResults)
    {
        this.casefileSearchResults = aCasefileSearchResults;
    }

    /**
     * Returns the collection code response events for the search types
     * 
     * @return
     */
    public Collection getJuvenileCaseSearchTypes()
    {
        return CodeHelper.getJuvenileCaseSearchTypes();
    }

    /**
     * Returns the collection code response events for the juvenile case statues
     * 
     * @return
     */
    public Collection getJuvenileCaseStatuses()
    {
        return CodeHelper.getJuvenileCaseStatuses();
    }

    /**
     * Returns the collection code response events for the supervision types
     * 
     * @return
     */
    public Collection getSupervisionTypes()
    {
	//Collection allSupervisionTypes = CodeHelper.getSupervisionTypes();
	Collection activeSupervisionTypes = CodeHelper.getActiveSupervisionTypes();  //US 180034 
        return activeSupervisionTypes;
    }

    /**
     * @param aSearchResultSize
     */
    public void setSearchResultSize(int aSearchResultSize)
    {
        this.searchResultSize = aSearchResultSize;
    }

    /**
     * @return searchResultSize
     */
    public int getSearchResultSize()
    {
        return this.searchResultSize;
    }

    public void setLocationId(String alocationId)
    {
        this.locationId = alocationId;
    }

    /**
     * @return
     */
    public String getLocationId()
    {
        return this.locationId;
    }

    /**
     * Returns the collection code response events for the locations
     * 
     * @return
     */
    public Collection getLocation()
    {
        //return CodeHelper.getLocationCodes(); //commented for  US 181050 and 181049
        return (Collection)ComplexCodeTableHelper.getActiveJuvenileLocationUnits(); //addedd for  US 181050 and 181049
    }



	/**
	 * @return the caseLoadMgr
	 */
	public String getCaseLoadMgr() {
		return caseLoadMgr;
	}

	/**
	 * @param caseLoadMgr the caseLoadMgr to set
	 */
	public void setCaseLoadMgr(String caseLoadMgr) {
		this.caseLoadMgr = caseLoadMgr;
	}

	/**
	 * @return the officers
	 */
	public List getOfficers() {
		return officers;
	}

	/**
	 * @param officers the officers to set
	 */
	public void setOfficers(List officers) {
		this.officers = officers;
	}

	/**
	 * @return the caseLoadMgrs
	 */
	public List getCaseLoadMgrs() {
		return caseLoadMgrs;
	}

	/**
	 * @param caseLoadMgrs the caseLoadMgrs to set
	 */
	public void setCaseLoadMgrs(List caseLoadMgrs) {
		this.caseLoadMgrs = caseLoadMgrs;
	}

	/**
	 * @return the officer
	 */
	public String getOfficer() {
		return officer;
	}

	/**
	 * @param officer the officer to set
	 */
	public void setOfficer(String officer) {
		this.officer = officer;
	}

	/**
	 * @return the caseloads
	 */
	public List getCaseloads() {
		return caseloads;
	}

	/**
	 * @param caseloads the caseloads to set
	 */
	public void setCaseloads(List caseloads) {
		this.caseloads = caseloads;
	}

	/**
	 * @return the juvenilesCount
	 */
	public int getJuvenilesCount() {
		return juvenilesCount;
	}

	/**
	 * @param juvenilesCount the juvenilesCount to set
	 */
	public void setJuvenilesCount(int juvenilesCount) {
		this.juvenilesCount = juvenilesCount;
	}

	/**
	 * @return the casefilesCount
	 */
	public int getCasefilesCount() {
		return casefilesCount;
	}

	/**
	 * @param casefilesCount the casefilesCount to set
	 */
	public void setCasefilesCount(int casefilesCount) {
		this.casefilesCount = casefilesCount;
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
	 * @return the officerName
	 */
	public String getOfficerName() {
		String name = null;
		StringBuffer full = new StringBuffer();
		if (StringUtils.isNotEmpty(officerLastName)) {
			full.append(officerLastName);
		}
		if (StringUtils.isNotEmpty(officerFirstName)) {
			full.append(", ");
			full.append(officerFirstName);
			if (StringUtils.isNotEmpty(officerMiddleName)) {
				full.append(" " + officerMiddleName);
			}
		}
		name = full.toString();

		return name;
	}

	/**
	 * @return the casefileActivationStDate
	 */
	public String getCasefileActivationStDate() {
		return casefileActivationStDate;
	}

	/**
	 * @param casefileActivationStDate the casefileActivationStDate to set
	 */
	public void setCasefileActivationStDate(String casefileActivationStDate) {
		this.casefileActivationStDate = casefileActivationStDate;
	}

	/**
	 * @return the casefileActivationEndDate
	 */
	public String getCasefileActivationEndDate() {
		return casefileActivationEndDate;
	}

	/**
	 * @param casefileActivationEndDate the casefileActivationEndDate to set
	 */
	public void setCasefileActivationEndDate(String casefileActivationEndDate) {
		this.casefileActivationEndDate = casefileActivationEndDate;
	}

	/**
	 * @return the caseloadExpectedEndDateFrom
	 */
	public String getCaseloadExpectedEndDateFrom() {
		return caseloadExpectedEndDateFrom;
	}

	/**
	 * @param caseloadExpectedEndDateFrom the caseloadExpectedEndDateFrom to set
	 */
	public void setCaseloadExpectedEndDateFrom(String caseloadExpectedEndDateFrom) {
		this.caseloadExpectedEndDateFrom = caseloadExpectedEndDateFrom;
	}

	/**
	 * @return the caseloadExpectedEndDateTo
	 */
	public String getCaseloadExpectedEndDateTo() {
		return caseloadExpectedEndDateTo;
	}

	/**
	 * @param caseloadExpectedEndDateTo the caseloadExpectedEndDateTo to set
	 */
	public void setCaseloadExpectedEndDateTo(String caseloadExpectedEndDateTo) {
		this.caseloadExpectedEndDateTo = caseloadExpectedEndDateTo;
	}

	/**
	 * @return the caseStatusExpectedEndDateFrom
	 */
	public String getCaseStatusExpectedEndDateFrom() {
		return caseStatusExpectedEndDateFrom;
	}

	/**
	 * @param caseStatusExpectedEndDateFrom the caseStatusExpectedEndDateFrom to set
	 */
	public void setCaseStatusExpectedEndDateFrom(
			String caseStatusExpectedEndDateFrom) {
		this.caseStatusExpectedEndDateFrom = caseStatusExpectedEndDateFrom;
	}

	/**
	 * @return the caseStatusExpectedEndDateTo
	 */
	public String getCaseStatusExpectedEndDateTo() {
		return caseStatusExpectedEndDateTo;
	}

	/**
	 * @param caseStatusExpectedEndDateTo the caseStatusExpectedEndDateTo to set
	 */
	public void setCaseStatusExpectedEndDateTo(String caseStatusExpectedEndDateTo) {
		this.caseStatusExpectedEndDateTo = caseStatusExpectedEndDateTo;
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

	public String getRestrictedAccessFeature() {
		return restrictedAccessFeature;
	}

	public void setRestrictedAccessFeature(String restrictedAccessFeature) {
		this.restrictedAccessFeature = restrictedAccessFeature;
	}

	/**
	 * @return the dispositionDate
	 */
	public String getDispositionDate() {
		return dispositionDate;
	}

	/**
	 * @param dispositionDate the dispositionDate to set
	 */
	public void setDispositionDate(String dispositionDate) {
		this.dispositionDate = dispositionDate;
	}

	public String getCaseStatusDispDateFrom() {
	    return caseStatusDispDateFrom;
	}

	public void setCaseStatusDispDateFrom(String caseStatusDispDateFrom) {
	    this.caseStatusDispDateFrom = caseStatusDispDateFrom;
	}

	public String getCaseStatusDispDateTo() {
	    return caseStatusDispDateTo;
	}

	public void setCaseStatusDispDateTo(String caseStatusDispDateTo) {
	    this.caseStatusDispDateTo = caseStatusDispDateTo;
	}
}

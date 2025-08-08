/*
 * Created on Jan 29, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.csserviceprovider.reply;

import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSProgramResponseEvent extends ResponseEvent 
{
    /************ CSProgramResponseEvent Member Variables ***********************/
    private String programId;
    private String serviceProviderId;
    private String programIdentifier;
    private String programName;
    private boolean isContractProgram;
    private String referralTypeCode;
    private String programHierarchyCode;
    private String programGroupCode;
    private String programTypeCode;
    private String programSubtypeCode;
    private Date programStartDate;
    private Date programEndDate;
    private String sexSpecificCode;
    private Object[] oldProgLocations;
    private Collection programLanguages;
    private Collection programLocations;
    private Collection programLocationIds;
    private Collection locationIds;
    private Collection selectedProglocationIds;
    private Collection programLanguageCodes;
    private String officeHours;
    private String programDescription;
    private String statusCode;
    private String statusChangeComments;
    private Date statusChangeDate;
    
    private String divisionId;
    private String divisionName;
    private String programUnitId;
    private String programUnitName;
    private String incarcerationConditionId;
    private String incarcerationConditionName;
    private String price;
    
    
    /**
     * @return Returns the isContractProgram.
     */
    public boolean isContractProgram() {
        return isContractProgram;
    }
    /**
     * @param isContractProgram The isContractProgram to set.
     */
    public void setContractProgram(boolean isContractProgram) {
        this.isContractProgram = isContractProgram;
    }
    /**
     * @return Returns the officeHours.
     */
    public String getOfficeHours() {
        return officeHours;
    }
    /**
     * @param officeHours The officeHours to set.
     */
    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
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
     * @return Returns the programEndDate.
     */
    public Date getProgramEndDate() {
        return programEndDate;
    }
    /**
     * @param programEndDate The programEndDate to set.
     */
    public void setProgramEndDate(Date programEndDate) {
        this.programEndDate = programEndDate;
    }
    /**
     * @return Returns the programHierarchyCode.
     */
    public String getProgramHierarchyCode() {
        return programHierarchyCode;
    }
    /**
     * @param programHierarchyCode The programHierarchyCode to set.
     */
    public void setProgramHierarchyCode(String programHierarchyCode) {
        this.programHierarchyCode = programHierarchyCode;
    }
    /**
     * @return Returns the programId.
     */
    public String getProgramId() {
        return programId;
    }
    /**
     * @param programId The programId to set.
     */
    public void setProgramId(String programId) {
        this.programId = programId;
    }
    /**
     * @return Returns the programIdentifier.
     */
    public String getProgramIdentifier() {
        return programIdentifier;
    }
    /**
     * @param programIdentifier The programIdentifier to set.
     */
    public void setProgramIdentifier(String programIdentifier) {
        this.programIdentifier = programIdentifier;
    }
    /**
     * @return Returns the programLanguageCodes.
     */
    public Collection getProgramLanguageCodes() {
        return programLanguageCodes;
    }
    /**
     * @param programLanguageCodes The programLanguageCodes to set.
     */
    public void setProgramLanguageCodes(Collection programLanguageCodes) {
        this.programLanguageCodes = programLanguageCodes;
    }
    /**
	 * @return the oldProgLocations
	 */
	public Object[] getOldProgLocations() {
		return oldProgLocations;
	}
	/**
	 * @param oldProgLocations the oldProgLocations to set
	 */
	public void setOldProgLocations(Object[] oldProgLocations) {
		this.oldProgLocations = oldProgLocations;
	}
	/**
     * @return Returns the programLanguages.
     */
    public Collection getProgramLanguages() {
        return programLanguages;
    }
    /**
     * @param programLanguages The programLanguages to set.
     */
    public void setProgramLanguages(Collection programLanguages) {
        this.programLanguages = programLanguages;
    }
    /**
     * @return Returns the programLocationIds.
     */
    public Collection getProgramLocationIds() {
        return programLocationIds;
    }
    /**
     * @param programLocationIds The programLocationIds to set.
     */
    public void setProgramLocationIds(Collection programLocationIds) {
        this.programLocationIds = programLocationIds;
    }
    /**
     * @return Returns the programLocations.
     */
    public Collection getProgramLocations() {
        return programLocations;
    }
    /**
     * @param programLocations The programLocations to set.
     */
    public void setProgramLocations(Collection programLocations) {
        this.programLocations = programLocations;
    }
    /**
     * @return Returns the programName.
     */
    public String getProgramName() {
        return programName;
    }
    /**
     * @param programName The programName to set.
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }
    /**
     * @return Returns the programStartDate.
     */
    public Date getProgramStartDate() {
        return programStartDate;
    }
    /**
     * @param programStartDate The programStartDate to set.
     */
    public void setProgramStartDate(Date programStartDate) {
        this.programStartDate = programStartDate;
    }
    /**
     * @return Returns the referralTypeCode.
     */
    public String getReferralTypeCode() {
        return referralTypeCode;
    }
    /**
     * @param referralTypeCode The referralTypeCode to set.
     */
    public void setReferralTypeCode(String referralTypeCode) {
        this.referralTypeCode = referralTypeCode;
    }
    /**
     * @return Returns the serviceProviderId.
     */
    public String getServiceProviderId() {
        return serviceProviderId;
    }
    /**
     * @param serviceProviderId The serviceProviderId to set.
     */
    public void setServiceProviderId(String serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }
    /**
     * @return Returns the sexSpecificCode.
     */
    public String getSexSpecificCode() {
        return sexSpecificCode;
    }
    /**
     * @param sexSpecificCode The sexSpecificCode to set.
     */
    public void setSexSpecificCode(String sexSpecificCode) {
        this.sexSpecificCode = sexSpecificCode;
    }
    /**
     * @return Returns the statusChangeComments.
     */
    public String getStatusChangeComments() {
        return statusChangeComments;
    }
    /**
     * @param statusChangeComments The statusChangeComments to set.
     */
    public void setStatusChangeComments(String statusChangeComments) {
        this.statusChangeComments = statusChangeComments;
    }
    /**
     * @return Returns the statusChangeDate.
     */
    public Date getStatusChangeDate() {
        return statusChangeDate;
    }
    /**
     * @param statusChangeDate The statusChangeDate to set.
     */
    public void setStatusChangeDate(Date statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }
    /**
     * @return Returns the statusCode.
     */
    public String getStatusCode() {
        return statusCode;
    }
    /**
     * @param statusCode The statusCode to set.
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    /**
     * @return Returns the programGroupCode.
     */
    public String getProgramGroupCode() {
        return programGroupCode;
    }
    /**
     * @param programGroupCode The programGroupCode to set.
     */
    public void setProgramGroupCode(String programGroupCode) {
        this.programGroupCode = programGroupCode;
    }
    /**
     * @return Returns the programSubtypeCode.
     */
    public String getProgramSubtypeCode() {
        return programSubtypeCode;
    }
    /**
     * @param programSubtypeCode The programSubtypeCode to set.
     */
    public void setProgramSubtypeCode(String programSubtypeCode) {
        this.programSubtypeCode = programSubtypeCode;
    }
    /**
     * @return Returns the programTypeCode.
     */
    public String getProgramTypeCode() {
        return programTypeCode;
    }
    /**
     * @param programTypeCode The programTypeCode to set.
     */
    public void setProgramTypeCode(String programTypeCode) {
        this.programTypeCode = programTypeCode;
    }
    /**
     * @return Returns the locationIds.
     */
    public Collection getLocationIds() {
        return locationIds;
    }
    /**
     * @param locationIds The locationIds to set.
     */
    public void setLocationIds(Collection locationIds) {
        this.locationIds = locationIds;
    }
	/**
	 * @return the selectedProglocationIds
	 */
	public Collection getSelectedProglocationIds() {
		return selectedProglocationIds;
	}
	/**
	 * @param selectedProglocationIds the selectedProglocationIds to set
	 */
	public void setSelectedProglocationIds(Collection selectedProglocationIds) {
		this.selectedProglocationIds = selectedProglocationIds;
	}
	public String getProgramUnitId() {
		return programUnitId;
	}
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
	}
	public String getIncarcerationConditionId() {
		return incarcerationConditionId;
	}
	public void setIncarcerationConditionId(String incarcerationConditionId) {
		this.incarcerationConditionId = incarcerationConditionId;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getProgramUnitName() {
		return programUnitName;
	}
	public void setProgramUnitName(String programUnitName) {
		this.programUnitName = programUnitName;
	}
	public String getIncarcerationConditionName() {
		return incarcerationConditionName;
	}
	public void setIncarcerationConditionName(String incarcerationConditionName) {
		this.incarcerationConditionName = incarcerationConditionName;
	}
}//end of CSProgramResponseEvent

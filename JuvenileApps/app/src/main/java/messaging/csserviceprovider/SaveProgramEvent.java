/*
 * Created on Jan 15, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.csserviceprovider;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.Composite.CompositeRequest;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveProgramEvent extends CompositeRequest 
{
    /************ SaveProgramEvent Member Variables ***********************/
    private String serviceProviderId;
    private String programId;
    private String programIdentifier;
    private String programName;
    private boolean isContractProgram;
    private String programGroupCode;
    private String programTypeCode;
    private String programSubTypeCode;
    private Date programStartDate;
    private Date programEndDate;
    private String sexSpecificCode;
    private Object[] oldProgLocations;
    private List programLanguages;
    private List programLocations;
    private List progLocation_Ids;
    private List selectedProgLocationIds;
    private String officeHours;
    private String programDescription;
    private String programStatus;
    private String statusChangeComments;
    private Date statusChangeDate;
    
    private String programUnitId;
    private String incarcerationConditionId;
    private String price;
    

    /************ Member Variable Getters & Setters ***********************/

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
    public List getProgramLanguages() {
        return programLanguages;
    }
    /**
     * @param programLanguages The programLanguages to set.
     */
    public void setProgramLanguages(List programLanguages) {
        this.programLanguages = programLanguages;
    }
    /**
     * @return Returns the programLocations.
     */
    public List getProgramLocations() {
        return programLocations;
    }
    /**
     * @param programLocations The programLocations to set.
     */
    public void setProgramLocations(List programLocations) {
        this.programLocations = programLocations;
    }
    /**
	 * @return the progLocation_Ids
	 */
	public List getProgLocation_Ids() {
		return progLocation_Ids;
	}
	/**
	 * @param progLocation_Ids the progLocation_Ids to set
	 */
	public void setProgLocation_Ids(List progLocation_Ids) {
		this.progLocation_Ids = progLocation_Ids;
	}
	/**
	 * @return the selectedProgLocationIds
	 */
	public List getSelectedProgLocationIds() {
		return selectedProgLocationIds;
	}
	/**
	 * @param selectedProgLocationIds the selectedProgLocationIds to set
	 */
	public void setSelectedProgLocationIds(List selectedProgLocationIds) {
		this.selectedProgLocationIds = selectedProgLocationIds;
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
     * @return Returns the programSubTypeCode.
     */
    public String getProgramSubTypeCode() {
        return programSubTypeCode;
    }
    /**
     * @param programSubTypeCode The programSubTypeCode to set.
     */
    public void setProgramSubTypeCode(String programSubTypeCode) {
        this.programSubTypeCode = programSubTypeCode;
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
     * @return Returns the programStatus.
     */
    public String getProgramStatus() {
        return programStatus;
    }
    /**
     * @param programStatus The programStatus to set.
     */
    public void setProgramStatus(String programStatus) {
        this.programStatus = programStatus;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}//end of SaveProgramEvent

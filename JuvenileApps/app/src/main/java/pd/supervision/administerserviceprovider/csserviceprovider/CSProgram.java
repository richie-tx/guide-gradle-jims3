/*
 * Created on Jan 15, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.csserviceprovider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;


/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSProgram extends PersistentObject 
{

    /************ CSProgram Member Variables ***********************/
    private String programId;
    private String serviceProviderId;
    private String programIdentifier;
    private String programName;
    private boolean isContractProgram;
    private String referralTypeCode;
    private String programHierarchyCode;
    private Date programStartDate;
    private Date programEndDate;
    private String sexSpecificCode;
    private Collection programLanguages;
    private Collection programLocations;
    private Collection programLocationIds;
    private Collection programLanguageCodes;
    private String officeHours;
    private String programDescription;
    private String statusCode;
    private String statusChangeComments;
    private Date statusChangeDate;
    private Collection validLocStatusCds;
    
    private String programUnitId = null;
    private String incarcerationConditionId = null;
    private float price;
    /************ Member Variable Getters & Setters ***********************/
    
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
     * @return Returns the isContractProgram.
     */
    public boolean getIsContractProgram() {
        return isContractProgram;
    }
    
    /**
     * @param isContractProgram The isContractProgram to set.
     */
    public void setIsContractProgram(boolean isContractProgram) {
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
     * @return Returns the programLanguages.
     */
    public Collection getProgramLanguages() 
    {
        	//retrieve program from DB
		fetch();
		
			//initialize then return list of program location objects
		initProgramLanguages();
		return programLanguages;
        
    }
    
    /**
     * @param programLanguages The programLanguages to set.
     */
    public void setProgramLanguages(List programLanguages) 
    {
        this.programLanguages = programLanguages;
    }//end of setProgramLanguages()
    
    /**
     * @return Returns the programLanguageIds.
     */
    public Collection getProgramLanguageCodes() 
    {
    		//retrieve program language objects
        if (programLanguageCodes == null)
        {
            Object[] prog_languages = getProgramLanguages().toArray();
            int num_languages = prog_languages.length;
            
            	//iterate thru program language objects and populate list of program language codes
            ArrayList prog_language_codes = new ArrayList(num_languages);
            for (int i=0;i<num_languages;i++)
            {
                prog_language_codes.add(
                        	((CSProgramLanguage)prog_languages[i]).getLanguageCode());                 
            }
            	//set program language codes
            setProgramLanguageCodes(prog_language_codes);            
        }
        	//return language codes
        return programLanguageCodes;
    }//end of programLanguageCodes()
    
    /**
     * @param programLanguageCodes The programLanguageIds to set.
     */
    public void setProgramLanguageCodes(Collection programLanguageCodes) {
        this.programLanguageCodes = programLanguageCodes;
    }
    
    /**
     * Clear list of program languages 
     *
     */
    private void clearProgramLanguages() 
	{
	    	//allocate then clear list of program languages
		initProgramLanguages();
		programLanguages.clear();
	}//end of clearProgramLanguages()

    
    /**
	* Initialize list of program language objects
	*/
	private void initProgramLanguages() 
	{
		if (programLanguages == null) 
		{
			if (this.getOID() == null) 
			{
			    	//save program to DB if not yet saved
				bind();
			}

				//allocate array of program language objects
			programLanguages = (mojo.km.persistence.ArrayList)
			    new mojo.km.persistence.ArrayList(CSProgramLanguage.class, 
			            								"programId", getOID());
		}
	}//end of initProgramLanguages()
    
	/**
	* insert a program language object into class relationship collection.
	*/
	public void insertProgramLanguage(CSProgramLanguage programLanguage) 
	{
	    initProgramLanguages();
		programLanguages.add(programLanguage);
	}//end of insertProgramLanguage()

	
	/**
	* Removes a program language relationship 
	*/
	public void removeProgramLanguage(CSProgramLanguage programLanguage) 
	{
	    	//initialize then remove a given program language object
	    initProgramLanguages();
	    programLanguages.remove(programLanguage);
	}//end of removeProgramLanguage()
	
	/**
	* returns a collection of program location objects
	*/
	public Collection getProgramLocations() 
	{
	    	//retrieve program from DB
		fetch();
		
			//initialize then return list of program location objects
		initProgramLocations();
		return programLocations;
	}   
	
	/**
     * @return Returns the programLocationIds.
     */
    public Collection getProgramLocationIds() 
    {
        if (programLocationIds == null)
        {
			//retrieve program location objects
            Object[] prog_locations = getProgramLocations().toArray();
            int num_locations = prog_locations.length;
            	//iterate thru program location objects and populate list of program language codes
            ArrayList prog_location_ids = new ArrayList(num_locations);
            for (int i=0;i<num_locations;i++)
            {
                prog_location_ids.add(
                        	((CSProgramLocation)prog_locations[i]).getProgramLocationId());                 
            }
            	//set value of program location ids
            setProgramLocationIds(prog_location_ids);
        }
        	//return location IDs
        return programLocationIds;
    }//end of getProgramLocationIDs()

    /**
     * @return Returns the programLocationIds.
     */
    public Collection getLocationIds() 
    {
		//retrieve program location objects
        Object[] prog_locations = getProgramLocations().toArray();
        int num_locations = prog_locations.length;
        	//iterate thru program location objects and populate list of location ids
        ArrayList location_ids = new ArrayList(num_locations);
        for (int i=0;i<num_locations;i++)
        {
            location_ids.add(
                    	((CSProgramLocation)prog_locations[i]).getLocationId());                 
        }
            //return location IDs
        return location_ids;
    }//end of getLocationIDs()
    
    /**
     * @param programLocationIds The programLocationIds to set.
     */
    public void setProgramLocationIds(Collection programLocationIds) {
        this.programLocationIds = programLocationIds;
    }
    
    /**
     * @param programLanguages The programLanguages to set.
     */
    public void setProgramLanguages(Collection programLanguages) {
        this.programLanguages = programLanguages;
    }
    /**
     * @param programLocations The programLocations to set.
     */
    public void setProgramLocations(Collection programLocations) {
        this.programLocations = programLocations;
    }
    /**
     * @param programLocations The programLocations to set.
     */
    public void setProgramLocations(List programLocations) 
    {
 		if (this.programLocations == null || !this.programLocations.equals(programLocations)) 
		{
			markModified();
		}
		this.programLocations = programLocations;        
    }//end of setProgramLocations()
  
    /**
     * Clear list of program locations 
     *
     */
    private void clearProgramLocations() 
	{
	    	//allocate then clear list of program locations
		initProgramLocations();
		programLocations.clear();
	}//end of clearProgramLocations()

    /**
	* Initialize list of program location objects
	*/
	private void initProgramLocations() 
	{
		if (programLocations == null) 
		{
			if (this.getOID() == null) 
			{
			    	//save program to DB if not yet saved
				bind();
			}

				//allocate array of program location objects
			programLocations = (mojo.km.persistence.ArrayList)
			    new mojo.km.persistence.ArrayList(CSProgramLocation.class, 
			            								"programId", getOID());
		}
	}//end of initProgramLocations()
    
	/**
	* insert a Location object into class relationship collection.
	*/
	public void insertProgramLocation(CSProgramLocation programLocation) 
	{
		initProgramLocations();
		programLocations.add(programLocation);
	}//end of insertProgramLocation()

	/**
	* Removes a program location relationship 
	*/
	public void removeProgramLocation(CSProgramLocation programLocation) 
	{
	    	//initialize then remove a given program location object
		initProgramLocations();
		programLocations.remove(programLocation);
	}//end of removeProgramLocation()
	
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
	 * @return the validLocStatusCds
	 */
	public Collection getValidLocStatus() {
		//retrieve program location objects
        Object[] prog_locations = getProgramLocations().toArray();
        int num_locations = prog_locations.length;
        	//iterate thru program location objects and populate list of valid location status codes
        ArrayList validLocStatusCds = new ArrayList(num_locations);
        for (int i=0;i<num_locations;i++)
        {
        	validLocStatusCds.add(
                    	((CSProgramLocation)prog_locations[i]).getValidLocStatus());                 
        }
            //return valid location status codes
		
		return validLocStatusCds;
	}
	/**
	 * @param validLocStatusCds the validLocStatusCds to set
	 */
	public void setValidLocStatusCds(Collection validLocStatusCds) {
		this.validLocStatusCds = validLocStatusCds;
	}
	/************ CSProgram Lookup Methods ***********************/
	/**
     * Find CSProgram by OID
     */
	static public CSProgram find(String programId)
	{
	    	//initialize lookup objects
	    CSProgram spProgram = null;
		IHome home = new Home();

			//use delegate to locate given cs service provider entity
		spProgram = (CSProgram) home.find(programId, CSProgram.class);
		return spProgram;
	}//end of find()

    /**
     * Find all CSProgram objects
     */
	static public Iterator findAll()
	{
	    	//initialize lookup objects
	    IHome home = new Home();
	    
	    	//use delegate to locate all service provider program objects
		Iterator iter = home.findAll(CSProgram.class);
		return iter;
	}//end of findAll()
	
    /**
     * Find all CSProgram objects matching the given event attributes
     */
	static public Iterator findAll(IEvent event)
	{
	    	//initialize lookup objects
		IHome home = new Home();
		
			//use delegate to lookup service provider programs matching the given event values
		return home.findAll(event, CSProgram.class);
	}//end of findAll()

    /**
     * Find all CSProgram objects matching the given event attributes
     */	
	static public Iterator findAll(String attrName, String attrValue) {
    		
	    	//initialize lookup objects
	    IHome home = new Home();
		Iterator spPrograms = null;
		
			//use delegate to lookup service provider programs with the given attribute/value matches
		spPrograms = home.findAll(attrName, attrValue, CSProgram.class);
		return spPrograms;
	}
	
	/**
	 * Bind entity to database thus creating an OID
	 *
	 */
    public void bind()
    {
        IHome home = new Home();
        home.bind(this);
    }//end of bind()
	public String getProgramUnitId() {
		if("".equals(programUnitId)) {
			return null;
		}
		return programUnitId;
	}
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
	}
	public String getIncarcerationConditionId() {
		if("".equals(incarcerationConditionId)){
			return null;
		}
		return incarcerationConditionId;
	}
	public void setIncarcerationConditionId(String incarcerationConditionId) {
		this.incarcerationConditionId = incarcerationConditionId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
    
}//end of CSProgram

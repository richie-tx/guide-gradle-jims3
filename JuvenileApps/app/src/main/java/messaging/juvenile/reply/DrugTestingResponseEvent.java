package messaging.juvenile.reply;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import naming.PDCodeTableConstants;

import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
import pd.transferobjects.helper.UserProfileHelper;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.contact.UIContactHelper;
import ui.contact.user.helper.UIUserFormHelper;
import messaging.administerlocation.reply.LocationResponseEvent;

import messaging.codetable.drug.reply.DrugTestResultCodeResponseEvent;
import messaging.codetable.drug.reply.DrugTypeCodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.security.UserEntityBean;

public class DrugTestingResponseEvent  extends ResponseEvent
{
    	private String drugTestingId;
    	private String associateCasefile;
	private Date testDate;
	private Date testTime;
	private String formarttedTestDate;
	private String formattedTestTime;
	private Date createDate;
	private Date updateDate;
	private String testAdministered;
	private String testAdministeredDesc;
	private String substanceTested;
	private String substanceTestedDesc;
	private String drugTestResults;
	private String drugTestResultsDesc;
	private String drugTestResultsResult;
	private String testLocation;
	private String testLocationDesc;
	private String administeredBy;
	private String administeredByName;
	private String comments;
	private String juvenileId;
	private String activityId;
	private String createUser;
	private String updateUser;
	private static List<LocationResponseEvent>testLocations = UIContactHelper.getLocationUnits();
	private static List<DrugTestResultCodeResponseEvent>drugTestResultCodes = ComplexCodeTableHelper.getDrugTestResultCodes() ;
	private static List<DrugTypeCodeResponseEvent>drugTypeCodes =  ComplexCodeTableHelper.getDrugTypeCodes();
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	public DrugTestingResponseEvent(){ }
	
	
	
	public String getDrugTestingId()
	{
	    return drugTestingId;
	}

	public void setDrugTestingId(String drugTestingId)
	{
	    this.drugTestingId = drugTestingId;
	}

	public String getAssociateCasefile()
	{
	    return associateCasefile;
	}
	public void setAssociateCasefile(String associateCasefile)
	{
	    this.associateCasefile = associateCasefile;
	}
	public Date getTestDate()
	{
	    return testDate;
	}
	public void setTestDate(Date testDate)
	{
	    this.testDate = testDate;
	}
	public Date getTestTime()
	{
	    return testTime;
	}
	public void setTestTime(Date testTime)
	{
	    this.testTime = testTime;
	}
	
	
	public String getFormarttedTestDate()
	{
	    return formarttedTestDate;
	}

	public void setFormarttedTestDate(String formarttedTestDate)
	{
	    this.formarttedTestDate = formarttedTestDate;
	}

	public String getFormattedTestTime()
	{
	    return formattedTestTime;
	}
	public void setFormattedTestTime(String formattedTestTime)
	{
	    this.formattedTestTime = formattedTestTime;
	}
	public String getTestAdministered()
	{
	    return testAdministered;
	}
	public void setTestAdministered(String testAdministered)
	{
	    this.testAdministered = testAdministered;
	}
	public String getSubstanceTested()
	{
	    return substanceTested;
	}
	public void setSubstanceTested(String substanceTested)
	{
	    this.substanceTested = substanceTested;
	}
	public String getDrugTestResults()
	{
	    return drugTestResults;
	}
	public void setDrugTestResults(String drugTestResults)
	{
	    this.drugTestResults = drugTestResults;
	}
	public String getTestLocation()
	{
	    return testLocation;
	}
	public void setTestLocation(String testLocation)
	{
	    this.testLocation = testLocation;
	}
	public String getAdministeredBy()
	{
	    return administeredBy;
	}
	public void setAdministeredBy(String administeredBy)
	{
	    this.administeredBy = administeredBy;
	}
	public String getComments()
	{
	    return comments;
	}
	public void setComments(String comments)
	{
	    this.comments = comments;
	}
	public String getAdministeredByName()
	{
	    if ( this.administeredBy != null
		    && this.administeredBy.length() > 0 ) {
		UserEntityBean userEBean = PDSecurityHelper.getSecurityUserProfileByJUCode(this.administeredBy);
		OfficerProfileResponseEvent officerProfile = UIUserFormHelper.getUserOfficerProfile(this.administeredBy);
		//UserProfile userProfile = UserProfileHelper.getUserProfileFromJUCode(this.administeredBy);
		if ( officerProfile != null ) {  
		    this.administeredByName = officerProfile.getFirstName() + " " + officerProfile.getLastName();
		} else if ( userEBean != null ){
		    this.administeredByName = userEBean.getFirstname() + " " + userEBean.getLastname();
		} else {
		    this.administeredByName = "";
		}
	    }
	    return administeredByName;
	}
	public void setAdministeredByName(String getAdministeredByName)
	{
	    this.administeredByName = getAdministeredByName;
	}
	public String getTestAdministeredDesc()
	{
	    if ( this.testAdministered != null
		    && this.testAdministered.length() > 0 ){
		this.testAdministeredDesc = CodeHelper.getCodeDescription( PDCodeTableConstants.DRUG_TEST_ADMINISTERED , this.testAdministered );
	    }
	    return testAdministeredDesc;
	}
	public void setTestAdministeredDesc(String testAdministeredDesc)
	{
	    this.testAdministeredDesc = testAdministeredDesc;
	}
	public String getDrugTestResultsDesc()
	{
	    if ( this.drugTestResults != null
		    && this.drugTestResults.length() > 0
		    && this.drugTestResultCodes != null
		    && this.drugTestResultCodes.size() > 0 ){
		this.drugTestResultsDesc = ComplexCodeTableHelper.getDrugTestResultDescription( drugTestResultCodes, this.drugTestResults );
	    }
	    return drugTestResultsDesc;
	}
	public void setDrugTestResultsDesc(String drugTestResultsDesc)
	{
	    this.drugTestResultsDesc = drugTestResultsDesc;
	}
	
	public String getDrugTestResultsResult()
	{
	    if ( this.drugTestResults != null
		    && this.drugTestResults.length() > 0
		    && this.drugTestResultCodes != null
		    && this.drugTestResultCodes.size() > 0 ){
		this.drugTestResultsResult = ComplexCodeTableHelper.getDrugTestResultsResult( drugTestResultCodes, this.drugTestResults );
	    }
	    return drugTestResultsResult;
	}
	public void setDrugTestResultsResult(String drugTestResultsResult)
	{
	    this.drugTestResultsResult = drugTestResultsResult;
	}
	
	
	public String getTestLocationDesc()
	{
	    if ( this.testLocation != null 
		    && this.testLocation.length() > 0
		    && this.testLocations != null
		    && this.testLocations.size() > 0) {
		
		for ( LocationResponseEvent location : this.testLocations ){
			if ( this.testLocation.equals( location.getJuvLocationUnitId() ) ){
			    this.testLocationDesc = location.getLocationUnitName();
			}
		}
		
	    }
	    return testLocationDesc;
	}
	public void setTestLocationDesc(String testLocationDesc)
	{
	    this.testLocationDesc = testLocationDesc;
	}

	public String getSubstanceTestedDesc()
	{
	    if ( this.substanceTested != null
		    && this.substanceTested.length() > 0
		    && this.drugTypeCodes != null
		    && this.drugTypeCodes.size() > 0 ){
		List<String> substancesTested = Arrays.asList( this.substanceTested.split(",") );
		StringBuffer substancesTestedDescr = new StringBuffer();
		for (int i = 0; i <  substancesTested.size(); i++ ) {
		    if ( i == 0 ) {
			 substancesTestedDescr.append( ComplexCodeTableHelper.getDrugTypeCodeDescription( drugTypeCodes, substancesTested.get(i).trim() ) ) ;
		    } else {
			substancesTestedDescr.append(", " +  ComplexCodeTableHelper.getDrugTypeCodeDescription( drugTypeCodes, substancesTested.get(i).trim() ) ) ;
		    }
		}
		this.substanceTestedDesc = substancesTestedDescr.toString();
		//this.substanceTestedDesc =  ComplexCodeTableHelper.getDrugTypeCodeDescription(drugTypeCodes, this.substanceTested );
	    }
	    return substanceTestedDesc;
	}

	public void setSubstanceTestedDesc(String substanceTestedDesc)
	{
	    this.substanceTestedDesc = substanceTestedDesc;
	}



	public Date getCreateDate()
	{
	    return createDate;
	}



	public void setCreateDate(Date createDate)
	{
	    this.createDate = createDate;
	}
	
	public Date getUpdateDate()
	{
	    return updateDate;
	}



	public void setUpdateDate(Date updateDate)
	{
	    this.updateDate = updateDate;
	}



	public String getJuvenileId()
	{
	    return juvenileId;
	}



	public void setJuvenileId(String juvenileId)
	{
	    this.juvenileId = juvenileId;
	}



	public String getActivityId()
	{
	    return activityId;
	}



	public void setActivityId(String activityId)
	{
	    this.activityId = activityId;
	}



	public String getCreateUser()
	{
	    return createUser;
	}



	public void setCreateUser(String createUser)
	{
	    this.createUser = createUser;
	}
	
	public String getUpdateUser()
	{
	    return updateUser;
	}



	public void setUpdateUser(String updateUser)
	{
	    this.updateUser = updateUser;
	}
	
	/**
	 * @return the createJIMS2UserID
	 */
	public String getCreateJIMS2UserID() {
		return createJIMS2UserID;
	}
	/**
	 * @param createJIMS2UserID the createJIMS2UserID to set
	 */
	public void setCreateJIMS2UserID(String createJIMS2UserID) {
		this.createJIMS2UserID = createJIMS2UserID;
	}
	/**
	 * @return the updateJIMS2UserID
	 */
	public String getUpdateJIMS2UserID() {
		return updateJIMS2UserID;
	}
	
	/**
	 * @param updateJIMS2UserID the updateJIMS2UserID to set
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
	}
	
	
	
	
	
	
	
	
	
	
	

}

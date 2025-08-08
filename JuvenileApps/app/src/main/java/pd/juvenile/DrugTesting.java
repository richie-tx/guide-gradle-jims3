package pd.juvenile;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;


import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class DrugTesting extends PersistentObject
{
    
    	//User story 162443
  	private String associateCasefile;
  	private String juvenileId;
  	private Date testDate;
  	private Date testTime;
  	private String testAdministered;
  	private String substanceTested;
  	private String drugTestResults;
  	private String testLocation;
  	private String administeredBy;
  	private String comments;
  	private String activityId;
  	
  	
	public String getAssociateCasefile()
	{
	    fetch();
	    return associateCasefile;
	}
	public void setAssociateCasefile(String associateCasefile)
	{
	     if ( this.associateCasefile == null 
		    || !this.associateCasefile.equals(associateCasefile) ){
		markModified();
	    }
	    this.associateCasefile = associateCasefile;
	}
	
	
	
	public String getJuvenileId()
	{
	    fetch();
	    return juvenileId;
	}
	public void setJuvenileId(String juvenileId)
	{
	    if ( this.juvenileId == null 
		    || !this.juvenileId.equals(juvenileId) ){
		markModified();
	    }
	    this.juvenileId = juvenileId;
	}
	public Date getTestDate()
	{
	    fetch();
	    return testDate;
	}
	public void setTestDate(Date testDate)
	{
	    if ( this.testDate == null ){
		markModified();
	    }
	    
	    this.testDate = testDate;
	}
	
	public Date getTestTime()
	{
	    fetch();
	    return testTime;
	}
	public void setTestTime(Date testTime)
	{
	    if ( this.testTime == null ){
		markModified();
	    }
	    this.testTime = testTime;
	}
	
	public String getTestAdministered()
	{
	    fetch();
	    return testAdministered;
	}
	public void setTestAdministered(String testAdministered)
	{
	    if ( this.testAdministered == null 
		    || !this.testAdministered.equals(testAdministered)){
		markModified();
	    }
	    this.testAdministered = testAdministered;
	}
	
	public String getSubstanceTested()
	{
	    fetch();
	    return substanceTested;
	}
	public void setSubstanceTested(String substanceTested)
	{
	    if ( this.substanceTested == null 
		    || !this.substanceTested.equals(substanceTested)){
		markModified();
	    }
	    this.substanceTested = substanceTested;
	}
	
	public String getDrugTestResults()
	{
	    fetch();
	    return drugTestResults;
	}
	public void setDrugTestResults(String drugTestResults)
	{
	    if ( this.drugTestResults  == null 
		    || !this.drugTestResults.equals(drugTestResults)){
		markModified();
	    }
	    this.drugTestResults = drugTestResults;
	}
	
	public String getTestLocation()
	{
	    fetch();
	    return testLocation;
	}
	public void setTestLocation(String testLocation)
	{
	    if ( this.testLocation == null
		    || !this.testLocation.equals(testLocation)){
		markModified();
	    }
	    this.testLocation = testLocation;
	}
	
	public String getAdministeredBy()
	{
	    fetch();
	    return administeredBy;
	}
	public void setAdministeredBy(String administeredBy)
	{
	    if ( this.administeredBy == null
		    || !this.administeredBy.equals(administeredBy)){
		markModified();
	    }
	    this.administeredBy = administeredBy;
	}
	
	public String getComments()
	{
	    fetch();
	    return comments;
	}
	public void setComments(String comments)
	{
	    if ( this.comments == null
		    || !this.comments.equals(comments)){
		markModified();
	    }
	    this.comments = comments;
	}
	
	
	public String getActivityId()
	{
	    fetch();
	    return activityId;
	}
	public void setActivityId(String activityId)
	{
	    if ( this.activityId == null
		    || !this.activityId.equals(activityId)){
		markModified();
	    }
	    this.activityId = activityId;
	}
	
	
	static public DrugTesting find(String drugTestingId)
	{
		IHome home = new Home();
		DrugTesting drugTesting = (DrugTesting) home.find(drugTestingId, DrugTesting.class);
		return drugTesting;
	}
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator drugTestings = home.findAll(event, DrugTesting.class);
		return drugTestings;
	}
	
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator drugTestings = home.findAll(attrName, attrValue, DrugTesting.class);
		return drugTestings;
	}
  	
  	

}

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class CreateDrugTestingInfoEvent extends RequestEvent
{
    	private String associateCasefile;
    	private String juvenileNumber;
	private String testDate;
	private String testTime;
	private String testAdministered;
	private String substanceTested;
	private String drugTestResult;
	private String testLocation;
	private String administeredBy;
	private String comments;
	
	public String getAssociateCasefile()
	{
	    return associateCasefile;
	}
	public void setAssociateCasefile(String associateCasefile)
	{
	    this.associateCasefile = associateCasefile;
	}
	
	public String getJuvenileNumber()
	{
	    return juvenileNumber;
	}
	public void setJuvenileNumber(String juvenileNumber)
	{
	    this.juvenileNumber = juvenileNumber;
	}
	public String getTestDate()
	{
	    return testDate;
	}
	public void setTestDate(String testDate)
	{
	    this.testDate = testDate;
	}
	public String getTestTime()
	{
	    return testTime;
	}
	public void setTestTime(String testTime)
	{
	    this.testTime = testTime;
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
	public String getDrugTestResult()
	{
	    return drugTestResult;
	}
	public void setDrugTestResult(String drugTestResult)
	{
	    this.drugTestResult = drugTestResult;
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
	
	

}

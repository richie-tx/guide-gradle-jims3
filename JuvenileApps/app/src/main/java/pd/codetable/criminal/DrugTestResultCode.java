package pd.codetable.criminal;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import pd.codetable.ICodetable;

public class DrugTestResultCode extends PersistentObject 
{
    public DrugTestResultCode(){}
    
    private String testResultId;
    private String code;
    private String description;
    private String results;
    private String status;
    private String activityCd;
    
    
    
    
    
    public String getTestResultId()
    {
	fetch();
        return testResultId;
    }

    
    public String getCode()
    {
	fetch();
        return code;
    }

    public void setCode(String code)
    {
	if ( this.code == null 
		|| !this.code.equals( code ) ) {
	    markModified();
	}
        this.code = code;
    }

    public String getDescription()
    {
	fetch();
        return description;
    }

    public void setDescription(String description)
    {
	if ( this.description == null 
		|| !this.description.equals( description ) ) {
	    markModified();
	}
        this.description = description;
    }

    public String getResults()
    {
	fetch();
        return results;
    }

    public void setResults(String results)
    {
	if ( this.results == null 
		|| !this.results.equals( results  ) ) {
	    markModified();
	}
        this.results = results;
    }

    public String getStatus()
    {
	fetch();
        return status;
    }

    public void setStatus(String status)
    {
	if ( this.status == null 
		|| !this.status.equals( status  ) ) {
	    markModified();
	}
        this.status = status;
    }

    
    
    public String getActivityCd()
    {
	fetch();
        return activityCd;
    }


    public void setActivityCd(String activityCd)
    {
	if ( this.activityCd == null 
		|| !this.activityCd.equals( activityCd  ) ) {
	    markModified();
	}
        this.activityCd = activityCd;
    }


    public static Iterator findAll() {
	return new Home().findAll(DrugTestResultCode.class);
    }
    
    public static Iterator findAll(String attributeName, String attributeValue)
    {
	return new Home().findAll(attributeName, attributeValue, DrugTestResultCode.class);
    }
    
    public static Iterator findAll(IEvent event)
    {
		return new Home().findAll(event, DrugTestResultCode.class);
    }
    
    public static DrugTestResultCode find(String testResultId){
	return (DrugTestResultCode) new Home().find(testResultId, DrugTestResultCode.class);
    }
    
    
    
    
   

}

package pd.codetable.criminal;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

public class DrugTypeCode  extends PersistentObject 
{
    public DrugTypeCode(){}
    
    private String drugTypeCodeId;
    private String code;
    private String description;
    private String drugTest;
    private String status;
    
    public String getDrugTypeCodeId()
    {
	fetch();
        return drugTypeCodeId;
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
    public String getDrugTest()
    {
	fetch();
        return drugTest;
    }
    public void setDrugTest(String drugTest)
    {

	if ( this.drugTest == null 
		|| !this.drugTest.equals( drugTest ) ) {
	    markModified();
	}
        this.drugTest = drugTest;
    }
    public String getStatus()
    {
	fetch();
        return status;
    }
    public void setStatus(String status)
    {
	if ( this.status == null 
		|| !this.status.equals( status ) ) {
	    markModified();
	}
        this.status = status;
    }
    
    public static Iterator findAll() {
   	return new Home().findAll(DrugTypeCode.class);
    }
       
    public static Iterator findAll(String attributeName, String attributeValue)
    {
   	return new Home().findAll(attributeName, attributeValue, DrugTypeCode.class);
    }
       
    public static Iterator findAll(IEvent event)
    {
	return new Home().findAll(event, DrugTypeCode.class);
    }
       
    public static DrugTypeCode find(String drugTypeCodeId){
   	return (DrugTypeCode) new Home().find(drugTypeCodeId, DrugTypeCode.class);
    }
    
    
}

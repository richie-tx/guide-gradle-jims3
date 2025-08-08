package pd.codetable.criminal;

import java.util.Iterator;

import messaging.codetable.reply.ICode;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

public class JuvenileMasterStatus  extends PersistentObject implements ICode
{
    private String code;
    private String description;
    private String activeSupervision;
    private String status;
    private String codeDesc;
    
    public String getCode()
    {
	fetch();
	return code;
    }
    
    public void setCode(String code)
    {
	if ( this.code == null 
		|| !this.code.equals( code ) ){
	    
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
    
    public String getActiveSupervision()
    {
	fetch();
	return activeSupervision;
    }
    public void setActiveSupervision(String activeSupervision)
    {
	if ( this.activeSupervision == null
		|| !this.activeSupervision.equals( activeSupervision )) {
	    markModified();
	}
	
	this.activeSupervision = activeSupervision;
    }
    
    public String getStatus()
    {
	fetch();
	return status;
    }
    
    public void setStatus(String status)
    {
	if ( this.status == null 
		|| !this.status.equals( status )) {
	    markModified();
	}
	this.status = status;
    }
    
    public static JuvenileMasterStatus find(String OID)
    {
	return (JuvenileMasterStatus) new Home().find(OID, JuvenileMasterStatus.class);
    }
    
  
    
    static public JuvenileMasterStatus find(String attributeName, String attributeValue) {
   	return (JuvenileMasterStatus ) new Home().find(attributeName, attributeValue, JuvenileMasterStatus.class);
       }
    
    public static Iterator findAll()
    {
	return new Home().findAll(JuvenileMasterStatus.class);
    }
    
    static public Iterator findAll(String attributeName, String attributeValue) {
	return new Home().findAll(attributeName, attributeValue, JuvenileMasterStatus.class);
    }

    public String getCodeDesc()
    {
	return this.code+" - "+this.description;
    }

    public void setCodeDesc(String codeDesc)
    {
	this.codeDesc = codeDesc;
    }

}

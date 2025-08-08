package pd.supervision.administercompliance;

import java.util.Iterator;

import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * 
 * @roseuid 47DA99E301D1
 */
public class ReasonForTransferCode extends PersistentObject
{
	private String code;
	private String description;
	private String type;

    	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	public static Iterator findAll(IEvent anEvent){
        return new Home().findAll(anEvent, ReasonForTransferCode.class);
    }
    public static Iterator findAll(String attrName, String attrValue){
        return new Home().findAll(attrName, attrValue, ReasonForTransferCode.class);
    }
    
    public static Iterator findAllByNumericParam(String attrName, String attrValue){
        return new Home().findAll(attrName, new Integer(attrValue), ReasonForTransferCode.class);
    }
    
    public static ReasonForTransferCode find(String codeId){
        return (ReasonForTransferCode) new Home().find(codeId, ReasonForTransferCode.class);
    }
	public CodeResponseEvent getResponse() {
		CodeResponseEvent resp = new CodeResponseEvent();
		resp.setCode(this.getCode());
		resp.setDescription(this.getDescription());
		resp.setCodeId(this.getOID());
		return resp;
	}
}

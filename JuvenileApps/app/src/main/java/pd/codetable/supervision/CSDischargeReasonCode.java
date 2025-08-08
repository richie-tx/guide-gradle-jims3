package pd.codetable.supervision;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;



public class CSDischargeReasonCode extends PersistentObject
{
    private String dischargeReasonId;
    private String conversionType;
    private String code;
    private String description;
    private String relativeCode;
    
    
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the conversionType
	 */
	public String getConversionType() {
		return conversionType;
	}
	/**
	 * @param conversionType the conversionType to set
	 */
	public void setConversionType(String conversionType) {
		this.conversionType = conversionType;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the dischargeReasonId
	 */
	public String getDischargeReasonId() {
		return dischargeReasonId;
	}
	/**
	 * @param dischargeReasonId the dischargeReasonId to set
	 */
	public void setDischargeReasonId(String dischargeReasonId) {
		this.dischargeReasonId = dischargeReasonId;
	}
	/**
	 * @return the relativeCode
	 */
	public String getRelativeCode() {
		return relativeCode;
	}
	/**
	 * @param relativeCode the relativeCode to set
	 */
	public void setRelativeCode(String relativeCode) {
		this.relativeCode = relativeCode;
	}
    
    
	 /************ CSProgramReferralType Lookup Methods ***********************/

    
	static public CSDischargeReasonCode find(String dischargeReasonId)
	{
	    	//initialize lookup objects
		CSDischargeReasonCode dischargeReason = null;
		IHome home = new Home();

		dischargeReason = (CSDischargeReasonCode) home.find(dischargeReasonId, CSDischargeReasonCode.class);
		return dischargeReason;
	}//end of find()

    
	static public Iterator findAll()
	{
	    	//initialize lookup objects
	    IHome home = new Home();
	    
		Iterator iter = home.findAll(CSDischargeReasonCode.class);
		return iter;
	}//end of findAll()
	
    
	static public Iterator findAll(IEvent event)
	{
	    	//initialize lookup objects
		IHome home = new Home();
		return home.findAll(event, CSDischargeReasonCode.class);
	}//end of findAll()

    
	static public Iterator findAll(String attrName, String attrValue) {
    		
	    	//initialize lookup objects
	    IHome home = new Home();
		Iterator dischargeReasons = null;
		
		dischargeReasons = home.findAll(attrName, attrValue, CSDischargeReasonCode.class);
		return dischargeReasons;
	}
    
    
}

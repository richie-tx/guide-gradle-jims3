package pd.juvenilecase;

import java.util.Iterator;
import messaging.juvenilecase.reply.OverrideOptionsResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;


/**
* @referencedType pd.juvenilecase.OverrideOptions
* @detailerDoNotGenerate false
*/
public class OverrideOptions extends PersistentObject {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	private String code;
	private String overrideOption;
	private String exceptionReason;
	private String status;
	
	static public Iterator findAll(IEvent event)
	{
	   IHome home = new Home();
	   return home.findAll(event, OverrideOptions.class);
	}
	
	public OverrideOptions() {
	}
	
	static public OverrideOptions find(String oid) {
		IHome home = new Home();
		OverrideOptions overrideOptions = (OverrideOptions) home.find(oid, OverrideOptions.class);
		return overrideOptions;
	}
	
	static public Iterator findAll() {
		return new Home().findAll(OverrideOptions.class);
	}

	/**
	* @roseuid 427676D702F0
	*/
	public void find() {
		fetch();
	}

	/**
	* Finds all overrideOptions by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue, OverrideOptions.class);
	}
	

	public String getCode()
	{
	    fetch();
	    return code;
	}

        public void setCode(String aCode)
        {
        	if (this.code == null || !this.code.equals(aCode))
        	{
        	    markModified();
        	}
        	this.code = aCode;
        }

	public String getOverrideOption()
	{
	    fetch();
	    return overrideOption;
	}

	public void setOverrideOption(String aOverrideOption)
	{
	    if (this.overrideOption == null || !this.overrideOption.equals(aOverrideOption)) {
		markModified();
	}
	    this.overrideOption = aOverrideOption;
	}

	public String getExceptionReason()
	{
	    fetch();
	    return exceptionReason;
	}

	public void setExceptionReason(String aExceptionReason)
	{
	    if (this.exceptionReason == null || !this.exceptionReason.equals(aExceptionReason)) {
		markModified();
	}
	    this.exceptionReason = aExceptionReason;
	}

	public String getStatus()
	{
	    fetch();
	    return status;
	}

	public void setStatus(String aStatus)
	{
	    if (this.status == null || !this.status.equals(aStatus)) {
		markModified();
	}
	    this.status = aStatus;
	}
	
	public OverrideOptionsResponseEvent getResponseEvent(){
	    OverrideOptionsResponseEvent myRespEvt=new OverrideOptionsResponseEvent();
	    myRespEvt.setCode(this.getCode());
	    myRespEvt.setExceptionReason(this.getExceptionReason());
	    myRespEvt.setOverrideOption(this.getOverrideOption());
	    myRespEvt.setStatus(this.getStatus());
	    return myRespEvt;
	}
}

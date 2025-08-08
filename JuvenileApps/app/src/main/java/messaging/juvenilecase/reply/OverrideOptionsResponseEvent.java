/*
 * Created on Aug 15, 2024
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenilecase.reply;

import pd.juvenilecase.OverrideOptions;
import mojo.km.messaging.ResponseEvent;

/**
 * @author NM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OverrideOptionsResponseEvent extends ResponseEvent implements Comparable{

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	
    	private String code;
	private String overrideOption;
	private String exceptionReason;
	private String status;
		

        public String getCode()
	{
	    return code;
	}


	public void setCode(String code)
	{
	    this.code = code;
	}


	public String getOverrideOption()
	{
	    return overrideOption;
	}


	public void setOverrideOption(String overrideOption)
	{
	    this.overrideOption = overrideOption;
	}


	public String getExceptionReason()
	{
	    return exceptionReason;
	}


	public void setExceptionReason(String exceptionReason)
	{
	    this.exceptionReason = exceptionReason;
	}


	public String getStatus()
	{
	    return status;
	}


	public void setStatus(String status)
	{
	    this.status = status;
	}


	public int compareTo(Object o)
        {
    		return 0;
        }
	
	
	public static OverrideOptionsResponseEvent getResponse( OverrideOptions overrideOptionCode){
	    OverrideOptionsResponseEvent myRespEvt=new OverrideOptionsResponseEvent();
	    myRespEvt.setCode(overrideOptionCode.getCode());
	    myRespEvt.setExceptionReason(overrideOptionCode.getExceptionReason());
	    myRespEvt.setOverrideOption(overrideOptionCode.getOverrideOption());
	    myRespEvt.setStatus(overrideOptionCode.getStatus());
	    return myRespEvt;
	}
	
}

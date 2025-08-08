package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetRelativeCodeEvent extends RequestEvent
{
	
	private String conversionType;
	private String code;
	

	public GetRelativeCodeEvent() 
   {
    
   }


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
	
}

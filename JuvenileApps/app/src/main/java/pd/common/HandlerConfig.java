/*
 * Created on Sep 18, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.common;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandlerConfig {
     private String handlerName;
     private String programName;
     private String handlerLocatorKey;
     
	/**
	 * @return the handlerLocatorKey
	 */
	public String getHandlerLocatorKey() {
		return handlerLocatorKey;
	}
	/**
	 * @return the handlerName
	 */
	public String getHandlerName() {
		return handlerName;
	}
	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}
	/**
	 * @param handlerLocatorKey the handlerLocatorKey to set
	 */
	public void setHandlerLocatorKey(String handlerLocatorKey) {
		this.handlerLocatorKey = handlerLocatorKey;
	}
	/**
	 * @param handlerName the handlerName to set
	 */
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}
}

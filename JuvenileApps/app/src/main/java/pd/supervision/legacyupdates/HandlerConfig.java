/*
 * Created on Sep 18, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.legacyupdates;

import java.util.Iterator;

import pd.supervision.cscdcalendar.CSEvent;

import mojo.km.persistence.AllQueryEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandlerConfig extends PersistentObject{
     private String handlerName;
     private String programName;
     private String handlerLocatorKey;
     
	
     public static Iterator findAll(){
	     return new Home().findAll(HandlerConfig.class); 
     }
     
     /**
      * @return HandlerConfig
      * @param handlerConfigId
      * @roseuid 
      */
     static public HandlerConfig find(String handlerConfigId) {
         IHome home = new Home();
         return (HandlerConfig) home.find(handlerConfigId, HandlerConfig.class);
     }
     
     /**
	 * @return the handlerLocatorKey
	 */
	public String getHandlerLocatorKey() {
		fetch();
		return handlerLocatorKey;
	}
	/**
	 * @return the handlerName
	 */
	public String getHandlerName() {
		fetch();
		return handlerName;
	}
	/**
	 * @return the programName
	 */
	public String getProgramName() {
		fetch();
		return programName;
	}
	/**
	 * @param handlerLocatorKey the handlerLocatorKey to set
	 */
	public void setHandlerLocatorKey(String handlerLocatorKey) {
		if (this.handlerLocatorKey == null || !this.handlerLocatorKey.equals(handlerLocatorKey))
		{
			markModified();
		}
		this.handlerLocatorKey = handlerLocatorKey;
	}
	/**
	 * @param handlerName the handlerName to set
	 */
	public void setHandlerName(String handlerName) {
		if (this.handlerName == null || !this.handlerName.equals(handlerName))
		{
			markModified();
		}
		this.handlerName = handlerName;
	}
	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		if (this.programName == null || !this.programName.equals(programName))
		{
			markModified();
		}
		this.programName = programName;
	}
}

/*
 * Created on Nov 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.codetable;

import mojo.km.messaging.RequestEvent;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCodetableRegistrationAttributesAndTypesEvent extends RequestEvent {

	   private String codetableRegId;
	   private String type;
	   
	   /**
	    * @roseuid 
	    */
	   public GetCodetableRegistrationAttributesAndTypesEvent() {
	    
	   }
		/**
		 * @return Returns the codetableRegId.
		 */
		public String getCodetableRegId() {
			return codetableRegId;
		}
		/**
		 * @param codetableRegId The codetableRegId to set.
		 */
		public void setCodetableRegId(String codetableRegId) {
			this.codetableRegId = codetableRegId;
		}
		/**
		 * @return Returns the type.
		 */
		public String getType() {
			return type;
		}
		/**
		 * @param type The type to set.
		 */
		public void setType(String type) {
			this.type = type;
		}
	}


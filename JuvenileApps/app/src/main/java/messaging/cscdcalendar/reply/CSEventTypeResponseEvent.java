//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\ResponseEvents\\CSEventTypesResponseEvent.java

package messaging.cscdcalendar.reply;

import java.util.Comparator;

import mojo.km.messaging.ResponseEvent;


public class CSEventTypeResponseEvent  extends ResponseEvent implements Comparable {
	
	private String eventType;
	private String categoryCode;
	private String description;
	private String accessContext;
	private String displayContext;
//	private SupervisionCode category = null;
	private String eventTypeId;
	
   
   /**
    * @roseuid 47A2366F000C
    */
   	public CSEventTypeResponseEvent() {
    
    }   
	/**
	 * @return Returns the categoryCode.
	 */
	public String getCategoryCode() {
		return categoryCode;
	}
	/**
	 * @param categoryCode The categoryCode to set.
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	/**
	 * @return Returns the eventType.
	 */
	public String getEventType() {
		return eventType;
	}
	/**
	 * @param eventType The eventType to set.
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	/**
	 * @return Returns the accessContext.
	 */
	public String getAccessContext() {
		return accessContext;
	}
	/**
	 * @param accessContext The accessContext to set.
	 */
	public void setAccessContext(String accessContext) {
		this.accessContext = accessContext;
	}
	/**
	 * @return Returns the eventTypeId.
	 */
	public String getEventTypeId() {
		return eventTypeId;
	}
	/**
	 * @param eventTypeId The eventTypeId to set.
	 */
	public void setEventTypeId(String eventTypeId) {
		this.eventTypeId = eventTypeId;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the displayContext.
	 */
	public String getDisplayContext() {
		return displayContext;
	}
	/**
	 * @param displayContext The displayContext to set.
	 */
	public void setDisplayContext(String displayContext) {
		this.displayContext = displayContext;
	}
	
	public int compareTo(Object obj) {
		if(obj==null)
			return -1;
		CSEventTypeResponseEvent evt = (CSEventTypeResponseEvent) obj;
		
		if (evt.getEventType() == null){
			return -1;
		}		
		if (this.getEventType() == null){
			return 1;
		}
        return this.getEventType().trim().compareToIgnoreCase(evt.getEventType().trim());
	}
	
	public static Comparator DescriptionComparator = new Comparator() {
        public int compare(Object description, Object otherDescription) {
          int result = 0;
          String bDescription = ((CSEventTypeResponseEvent)description).getDescription();
          String bOtherDescription = ((CSEventTypeResponseEvent)otherDescription).getDescription();
          
          if(bDescription == null)
          {
                result = -1;
          }
          else if(bOtherDescription == null)
          {
                result = -1;
          }
          else 
          {
                result = bDescription.compareTo(bOtherDescription);
          }
          return result;
        }     
  };
}

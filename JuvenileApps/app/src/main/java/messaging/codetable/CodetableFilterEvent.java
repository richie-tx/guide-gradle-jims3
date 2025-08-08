//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\codetable\\GetCodetableRecordEvent.java

package messaging.codetable;

/*
 * @author mchowdhury
 */
import mojo.km.messaging.RequestEvent;

public class CodetableFilterEvent extends RequestEvent 
{
   private String attributeDisplayOrder;
   private String attributeValue;
   private String filterType;
   
   /**
    * @roseuid 45B956990233
    */
   public CodetableFilterEvent() 
   {
    
   }
   
  /**
	 * @return Returns the attributeValue.
	 */
	public String getAttributeValue() {
		return attributeValue;
	}
	
	/**
	 * @param attributeValue The attributeValue to set.
	 */
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	
	/**
	 * @return Returns the filterType.
	 */
	public String getFilterType() {
		return filterType;
	}
	
	/**
	 * @param filterType The filterType to set.
	 */
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	/**
	 * @return Returns the attributeDisplayOrder.
	 */
	public String getAttributeDisplayOrder() {
		return attributeDisplayOrder;
	}
	/**
	 * @param attributeDisplayOrder The attributeDisplayOrder to set.
	 */
	public void setAttributeDisplayOrder(String attributeDisplayOrder) {
		this.attributeDisplayOrder = attributeDisplayOrder;
	}
}

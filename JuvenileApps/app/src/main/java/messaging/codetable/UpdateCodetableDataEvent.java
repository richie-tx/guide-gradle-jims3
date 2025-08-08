//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\codetable\\UpdateCodetableDataEvent.java

package messaging.codetable;

import java.util.Map;

import mojo.km.messaging.RequestEvent;

public class UpdateCodetableDataEvent extends RequestEvent 
{
   
	private String entityName;
	private String codetableDataId;
	private String contextKey;
	private String codetableType;
	private Map valueMap;

	/**
    * @roseuid 45B956A902A1
    */
   public UpdateCodetableDataEvent() 
   {
    
   }
	/**
	 * @return Returns the codetableDataId.
	 */
	public String getCodetableDataId() {
		return codetableDataId;
	}
	/**
	 * @param codetableDataId The codetableDataId to set.
	 */
	public void setCodetableDataId(String codetableDataId) {
		this.codetableDataId = codetableDataId;
	}
	/**
	 * @return Returns the entityName.
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName The entityName to set.
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	/**
	 * @return Returns the valueMap.
	 */
	public Map getValueMap() {
		return valueMap;
	}
	/**
	 * @param valueMap The valueMap to set.
	 */
	public void setValueMap(Map valueMap) {
		this.valueMap = valueMap;
	}
	/**
	 * @return Returns the codetableType.
	 */
	public String getCodetableType() {
		return codetableType;
	}
	/**
	 * @param codetableType The codetableType to set.
	 */
	public void setCodetableType(String codetableType) {
		this.codetableType = codetableType;
	}
	/**
	 * @return Returns the contextKey.
	 */
	public String getContextKey() {
		return contextKey;
	}
	/**
	 * @param contextKey The contextKey to set.
	 */
	public void setContextKey(String contextKey) {
		this.contextKey = contextKey;
	}
}

/*
 * Created on Jan 30, 2007
 *
 */
package messaging.codetable.reply;

import java.util.Map;
import java.util.TreeMap;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 */
public class CodetableDataResponseEvent extends ResponseEvent implements Cloneable, Comparable
{
	private String codetableDataId;
	private Map valueMap;
	private String entityName;
	private Object sortedValue;
	
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
	
	public Object clone()
	{
		CodetableDataResponseEvent clone = new CodetableDataResponseEvent();
		clone.setCodetableDataId(this.getCodetableDataId());
		clone.setEntityName(this.getEntityName());
		Map valueMap = new TreeMap();
		valueMap.putAll(this.getValueMap());
		clone.setValueMap(valueMap);
		return clone;
	}
	/**
	 * @return Returns the sortedValue.
	 */
	public Object getSortedValue() {
		return sortedValue;
	}
	/**
	 * @param sortedValue The sortedValue to set.
	 */
	public void setSortedValue(Object sortedValue) {
		this.sortedValue = sortedValue;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj){
		if(obj==null)
			return -1;
		CodetableDataResponseEvent evt = (CodetableDataResponseEvent) obj;
		if(this.sortedValue == null || this.sortedValue.toString().equals("") || evt.getSortedValue() == null || evt.getSortedValue().toString().equals("")){
			return 0;	
		}
		
		try{
			int i = Integer.parseInt(evt.getSortedValue().toString());
			int j = Integer.parseInt(this.sortedValue.toString());
			if (i > j){
				return -1;
			}else if (i < j){
				return 1;
			}else{
				return 0;
			}
		}
		catch(NumberFormatException e){
			return this.sortedValue.toString().compareToIgnoreCase(evt.getSortedValue().toString());
		}
	}
}

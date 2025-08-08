/*
 * Created on Apr 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.common;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SorterObj implements Comparable{
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	
	private boolean sortAsc=true;
	private String primarySortType;
	private Object primaryPropertyValue;
	private String secondarySortType;
	private Object secondaryPropertyValue;
	private String finalSortType;
	private Object finalPropertyValue;
	private Object actualObject;
	private boolean useSecSort=false;
	private boolean useFinalSort=false;
	

	public int compareTo(Object o) {
		if(o==null)
			return 1;
		SorterObj incomingObj=(SorterObj)o;
		int value=0;
		if(primaryPropertyValue==null && incomingObj.getPrimaryPropertyValue()==null){
			value=0;
		}
		else if(primaryPropertyValue==null && incomingObj.getPrimaryPropertyValue()!=null){
			value=-1;
		}
		else if(primaryPropertyValue!=null && incomingObj.getPrimaryPropertyValue()==null){
			value=1;
		}
		else{  // neither is null
			try{
				if(primaryPropertyValue instanceof String){
					String primProp=(String)primaryPropertyValue;
					String incomingPrimProp=(String)incomingObj.getPrimaryPropertyValue();
					value=primProp.compareTo(incomingPrimProp);
				}
				else if(primaryPropertyValue instanceof Integer){
					Integer primProp=(Integer)primaryPropertyValue;
					Integer incomingPrimProp=(Integer)incomingObj.getPrimaryPropertyValue();
					value=primProp.compareTo(incomingPrimProp);
				}
				else if(primaryPropertyValue instanceof Long){
					Long primProp=(Long)primaryPropertyValue;
					Long incomingPrimProp=(Long)incomingObj.getPrimaryPropertyValue();
					value=primProp.compareTo(incomingPrimProp);
				}
				else if(primaryPropertyValue instanceof Double){
					Double primProp=(Double)primaryPropertyValue;
					Double incomingPrimProp=(Double)incomingObj.getPrimaryPropertyValue();
					value=primProp.compareTo(incomingPrimProp);
				}
				else if(primaryPropertyValue instanceof Timestamp){
					Timestamp primProp=(Timestamp)primaryPropertyValue;
					Timestamp incomingPrimProp=(Timestamp)incomingObj.getPrimaryPropertyValue();
					value=primProp.compareTo(incomingPrimProp);
				}
				else if(primaryPropertyValue instanceof Date){
					Date primProp=(Date)primaryPropertyValue;
					Date incomingPrimProp=(Date)incomingObj.getPrimaryPropertyValue();
					value=primProp.compareTo(incomingPrimProp);
				}
				
				if(value==0 && isUseSecSort()){
					if(secondaryPropertyValue==null && incomingObj.getSecondaryPropertyValue()==null){
						value=0;
					}
					else if(secondaryPropertyValue==null && incomingObj.getSecondaryPropertyValue()!=null){
						value=-1;
					}
					else if(secondaryPropertyValue!=null && incomingObj.getSecondaryPropertyValue()==null){
						value=1;
					}
					else{  // neither is null
						if(secondaryPropertyValue instanceof String){
							String secProp=(String)secondaryPropertyValue;
							String incomingSecProp=(String)incomingObj.getSecondaryPropertyValue();
							value=secProp.compareTo(incomingSecProp);
						}
						else if(secondaryPropertyValue instanceof Integer){
							Integer secProp=(Integer)secondaryPropertyValue;
							Integer incomingSecProp=(Integer)incomingObj.getSecondaryPropertyValue();
							value=secProp.compareTo(incomingSecProp);
						}
						else if(secondaryPropertyValue instanceof Long){
							Long secProp=(Long)secondaryPropertyValue;
							Long incomingSecProp=(Long)incomingObj.getSecondaryPropertyValue();
							value=secProp.compareTo(incomingSecProp);
						}
						else if(secondaryPropertyValue instanceof Double){
							Double secProp=(Double)secondaryPropertyValue;
							Double incomingSecProp=(Double)incomingObj.getSecondaryPropertyValue();
							value=secProp.compareTo(incomingSecProp);
						}
						else if(secondaryPropertyValue instanceof Timestamp){
							Timestamp secProp=(Timestamp)secondaryPropertyValue;
							Timestamp incomingSecProp=(Timestamp)incomingObj.getSecondaryPropertyValue();
							value=secProp.compareTo(incomingSecProp);
						}
						else if(secondaryPropertyValue instanceof Date){
							Date secProp=(Date)secondaryPropertyValue;
							Date incomingSecProp=(Date)incomingObj.getSecondaryPropertyValue();
							value=secProp.compareTo(incomingSecProp);
						}
						
					}
				}
				
				if(value==0 && isUseFinalSort()){
					if(finalPropertyValue==null && incomingObj.getFinalPropertyValue()==null){
						value=0;
					}
					else if(finalPropertyValue==null && incomingObj.getFinalPropertyValue()!=null){
						value=-1;
					}
					else if(finalPropertyValue!=null && incomingObj.getFinalPropertyValue()==null){
						value=1;
					}
					else{  // neither is null
						if(finalPropertyValue instanceof String){
							String finalProp=(String)finalPropertyValue;
							String incomingFinalProp=(String)incomingObj.getFinalPropertyValue();
							value=finalProp.compareTo(incomingFinalProp);
						}
						else if(finalPropertyValue instanceof Integer){
							Integer finalProp=(Integer)finalPropertyValue;
							Integer incomingFinalProp=(Integer)incomingObj.getFinalPropertyValue();
							value=finalProp.compareTo(incomingFinalProp);
						}
						else if(finalPropertyValue instanceof Long){
							Long finalProp=(Long)finalPropertyValue;
							Long incomingFinalProp=(Long)incomingObj.getFinalPropertyValue();
							value=finalProp.compareTo(incomingFinalProp);
						}
						else if(finalPropertyValue instanceof Double){
							Double finalProp=(Double)finalPropertyValue;
							Double incomingFinalProp=(Double)incomingObj.getFinalPropertyValue();
							value=finalProp.compareTo(incomingFinalProp);
						}
						else if(finalPropertyValue instanceof Timestamp){
							Timestamp finalProp=(Timestamp)finalPropertyValue;
							Timestamp incomingFinalProp=(Timestamp)incomingObj.getFinalPropertyValue();
							value=finalProp.compareTo(incomingFinalProp);
						}
						else if(finalPropertyValue instanceof Date){
							Date finalProp=(Date)finalPropertyValue;
							Date incomingFinalProp=(Date)incomingObj.getFinalPropertyValue();
							value=finalProp.compareTo(incomingFinalProp);
						}
						
					}
				}
			}
			catch(Exception e){
				value=1;
			}
		}
		if(!sortAsc)
			return -1*value;
		else
			return value;
	}
	
	/**
	 * @return Returns the actualObject.
	 */
	public Object getActualObject() {
		return actualObject;
	}
	/**
	 * @param actualObject The actualObject to set.
	 */
	public void setActualObject(Object actualObject) {
		this.actualObject = actualObject;
	}
	/**
	 * @return Returns the primaryPropertyValue.
	 */
	public Object getPrimaryPropertyValue() {
		return primaryPropertyValue;
	}
	/**
	 * @param primaryPropertyValue The primaryPropertyValue to set.
	 */
	public void setPrimaryPropertyValue(Object primaryPropertyValue) {
		this.primaryPropertyValue = primaryPropertyValue;
	}
	/**
	 * @return Returns the primarySortType.
	 */
	public String getPrimarySortType() {
		return primarySortType;
	}
	/**
	 * @param primarySortType The primarySortType to set.
	 */
	public void setPrimarySortType(String primarySortType) {
		this.primarySortType = primarySortType;
	}
	/**
	 * @return Returns the secondaryPropertyValue.
	 */
	public Object getSecondaryPropertyValue() {
		return secondaryPropertyValue;
	}
	/**
	 * @param secondaryPropertyValue The secondaryPropertyValue to set.
	 */
	public void setSecondaryPropertyValue(Object secondaryPropertyValue) {
		this.secondaryPropertyValue = secondaryPropertyValue;
	}
	/**
	 * @return Returns the secondarySortType.
	 */
	public String getSecondarySortType() {
		return secondarySortType;
	}
	/**
	 * @param secondarySortType The secondarySortType to set.
	 */
	public void setSecondarySortType(String secondarySortType) {
		this.secondarySortType = secondarySortType;
	}
	
	/**
	 * @return Returns the finalPropertyValue.
	 */
	public Object getFinalPropertyValue() {
		return finalPropertyValue;
	}
	/**
	 * @param finalPropertyValue The finalPropertyValue to set.
	 */
	public void setFinalPropertyValue(Object finalPropertyValue) {
		this.finalPropertyValue = finalPropertyValue;
	}
	/**
	 * @return Returns the finalSortType.
	 */
	public String getFinalSortType() {
		return finalSortType;
	}
	/**
	 * @param finalSortType The finalSortType to set.
	 */
	public void setFinalSortType(String finalSortType) {
		this.finalSortType = finalSortType;
	}
	/**
	 * @return Returns the sortAsc.
	 */
	public boolean isSortAsc() {
		return sortAsc;
	}
	/**
	 * @param sortAsc The sortAsc to set.
	 */
	public void setSortAsc(boolean sortAsc) {
		this.sortAsc = sortAsc;
	}
	/**
	 * @return Returns the useSecSort.
	 */
	public boolean isUseSecSort() {
		return useSecSort;
	}
	/**
	 * @param useSecSort The useSecSort to set.
	 */
	public void setUseSecSort(boolean useSecSort) {
		this.useSecSort = useSecSort;
	}
	
	/**
	 * @return Returns the useFinalSort.
	 */
	public boolean isUseFinalSort() {
		return useFinalSort;
	}
	/**
	 * @param useFinalSort The useFinalSort to set.
	 */
	public void setUseFinalSort(boolean useFinalSort) {
		this.useFinalSort = useFinalSort;
	}
}

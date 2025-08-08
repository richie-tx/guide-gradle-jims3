/*
 * Created on July 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.config; 

/**
 * @author mchowdhury
 * Class holding data for each individual jsp.
 */
 
public class SortingEntity{
	private String id;
	private String primarySortBy;
	private String secondarySortBy;
	private String returnPath;
	private String responseEvent;
	private String responseEventId;
	private String actionForm;
	private String actionFormIdentifier;
	private String collectionData;
	private String sortingDataType;
	
	/**
	 * Consturctor
	 */
	public SortingEntity(){
	}
	/**
	 * @return actionForm String
	 */
	public String getActionForm() throws EmptyMappingPropertyException {
		return this.checkEmptyParameters(ISortingConstants.ACTION_FORM, actionForm, id);
	}

	/**
	 * @return collectionData String
	 */
	public String getCollectionData() throws EmptyMappingPropertyException {
		return this.checkEmptyParameters(ISortingConstants.COLLECTION_DATA, collectionData, id);
	}

	/**
	 * @return  String
	 */
	public String getId() throws EmptyMappingPropertyException{
		if(id != null && !(id.equals(""))){
		  return id;	
	   }else{
		  throw new EmptyMappingPropertyException(id + " in mojo.km.Sort.xml file is Empty");
	   }
	}

	/**
	 * @return primarySortBy String
	 */
	public String getPrimarySortBy() throws EmptyMappingPropertyException {
		return this.checkEmptyParameters(ISortingConstants.PRIMARY_SORT_BY, primarySortBy, id);
	}

	/**
	 * @return responseEvent String
	 */
	public String getResponseEvent() throws EmptyMappingPropertyException {
		return this.checkEmptyParameters(ISortingConstants.RESPONSE_EVENT, responseEvent, id);
	}

	/**
	 * @return responseEventId String
	 */
	public String getResponseEventId() throws EmptyMappingPropertyException {
		return this.checkEmptyParameters(ISortingConstants.RESPONSE_EVENT_ID, responseEventId, id);
	}
	
	/**
	 * @return returnPath String
	 */
	public String getReturnPath() throws EmptyMappingPropertyException {
		return this.checkEmptyParameters(ISortingConstants.RETURN_PATH, returnPath, id);
	}
	
	/**
	 * @return
	 */
	public String getActionFormIdentifier() throws EmptyMappingPropertyException{
		return this.checkEmptyParameters(ISortingConstants.ACTION_FORM_IDENTIFIER, actionFormIdentifier , id);
	}


	/**
	 * @return secondarySortBy String
	 */
	public String getSecondarySortBy() {
		return secondarySortBy;
	}

	/**
	 * @param string
	 */
	public void setActionForm(String string) {
		actionForm = string;
	}

	/**
	 * @param string
	 */
	public void setCollectionData(String string) {
		collectionData = this.getGetterMethodName(string);
	}

	/**
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

	/**
	 * @param string
	 */
	public void setPrimarySortBy(String string) {
		primarySortBy = this.getGetterMethodName(string);
	}

	/**
	 * @param string
	 */
	public void setResponseEvent(String string) {
		responseEvent = string;
	}

	/**
	 * @param string
	 */
	public void setResponseEventId(String string) {
		responseEventId = this.getGetterMethodName(string);
	}

	/**
	 * @param string
	 */
	public void setReturnPath(String string) {
		returnPath = string;
	}

	/**
	 * @param string
	 */
	public void setSecondarySortBy(String string) {
		secondarySortBy = this.getGetterMethodName(string);
	}
	
	/**
	 * @param string
	 */
	public void setActionFormIdentifier(String string)
	{
		actionFormIdentifier = string;
	}

	
	/**
	* @param name String
	* @return String
	*/
	private String getGetterMethodName(String name){
	   if(name != null && !(name.equals(""))){
	      StringBuffer methodName = new StringBuffer("get");  
	      methodName.append(name.toUpperCase().charAt(0));
	      methodName.append(name.substring(1));
	      return methodName.toString();	
	   }else{
	   	  return null;
	   }
	}
	
	/**
	* @param param String
	* @param paramValue String
	* @param id String
	* @return String
	*/
	private String checkEmptyParameters(String param, String paramValue, String id) throws EmptyMappingPropertyException{
	   if(paramValue != null && !(paramValue.equals(""))){
		  return paramValue;	
	   }else{
		  throw new EmptyMappingPropertyException(param + " name in " + id + " is Empty");
	   }
	}

	/**
	 * @return
	 */
	public String getSortingDataType() throws EmptyMappingPropertyException
	{
		return this.checkEmptyParameters(ISortingConstants.SORTING_DATA_TYPE, sortingDataType, id);
	}

	/**
	 * @param string
	 */
	public void setSortingDataType(String string)
	{
		sortingDataType = string;
	}

}

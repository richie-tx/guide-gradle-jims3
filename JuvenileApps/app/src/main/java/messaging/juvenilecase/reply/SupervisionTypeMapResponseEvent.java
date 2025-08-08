/*
 * Created on Jul 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SupervisionTypeMapResponseEvent extends ResponseEvent implements Comparable{

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	
	private String assignmentLevelId;
	private String serviceUnitId;
	private String supervisionCatId;
	private String supervisionTypeId;
	private String supervisionCat;
	private String supervisionType;
	private String supervisionTypeMapId;
	private String splCategoryId;
	//added for bug 50021
	private String supervisionTypeStatus;
	//added for bug 50021
	private String inactiveInd;
	private String supTJJDTypeId;
	private String tjjdIndicator;
	
	

	public int compareTo(Object o) {
		int retVal=1;
		if(o!=null && o instanceof SupervisionTypeMapResponseEvent){
			SupervisionTypeMapResponseEvent myEvt=(SupervisionTypeMapResponseEvent)o;
			if((myEvt.getSupervisionType()!=null && !myEvt.getSupervisionType().equals("")) && ((this.getSupervisionType()!=null && !this.getSupervisionType().equals("")))){
					retVal=this.getSupervisionType().compareTo(myEvt.getSupervisionType());
			}
			else{
				if(myEvt.getSupervisionTypeId()==null){
					retVal=1;
				}
				else if(this.getSupervisionTypeId()==null){
					retVal=-1;
				}
				else{
					retVal=this.getSupervisionTypeId().compareTo(myEvt.getSupervisionTypeId());
				}
			}
		}
		return retVal;
	}
	
	public String getTjjdIndicator()
	{
	    return tjjdIndicator;
	}

	public void setTjjdIndicator(String tjjdIndicator)
	{
	    this.tjjdIndicator = tjjdIndicator;
	}
	
	/**
	 * @return Returns the assignmentLevelId.
	 */
	public String getAssignmentLevelId() {
		return assignmentLevelId;
	}
	/**
	 * @param assignmentLevelId The assignmentLevelId to set.
	 */
	public void setAssignmentLevelId(String assignmentLevelId) {
		this.assignmentLevelId = assignmentLevelId;
	}
	/**
	 * @return Returns the serviceUnitId.
	 */
	public String getServiceUnitId() {
		return serviceUnitId;
	}
	/**
	 * @param serviceUnitId The serviceUnitId to set.
	 */
	public void setServiceUnitId(String serviceUnitId) {
		this.serviceUnitId = serviceUnitId;
	}
	/**
	 * @return Returns the supervisionCat.
	 */
	public String getSupervisionCat() {
		return supervisionCat;
	}
	/**
	 * @param supervisionCat The supervisionCat to set.
	 */
	public void setSupervisionCat(String supervisionCat) {
		this.supervisionCat = supervisionCat;
	}
	/**
	 * @return Returns the supervisionCatId.
	 */
	public String getSupervisionCatId() {
		return supervisionCatId;
	}
	/**
	 * @param supervisionCatId The supervisionCatId to set.
	 */
	public void setSupervisionCatId(String supervisionCatId) {
		this.supervisionCatId = supervisionCatId;
	}
	/**
	 * @return Returns the supervisionType.
	 */
	public String getSupervisionType() {
		return supervisionType;
	}
	/**
	 * @param supervisionType The supervisionType to set.
	 */
	public void setSupervisionType(String supervisionType) {
		this.supervisionType = supervisionType;
	}
	/**
	 * @return Returns the supervisionTypeId.
	 */
	public String getSupervisionTypeId() {
		return supervisionTypeId;
	}
	/**
	 * @param supervisionTypeId The supervisionTypeId to set.
	 */
	public void setSupervisionTypeId(String supervisionTypeId) {
		this.supervisionTypeId = supervisionTypeId;
	}
	/**
	 * @return Returns the supervisionTypeMapId.
	 */
	public String getSupervisionTypeMapId() {
		return supervisionTypeMapId;
	}
	/**
	 * @param supervisionTypeMapId The supervisionTypeMapId to set.
	 */
	public void setSupervisionTypeMapId(String supervisionTypeMapId) {
		this.supervisionTypeMapId = supervisionTypeMapId;
	}
	/**
	 * @return the supervisionTypeStatus
	 */
	public String getSupervisionTypeStatus() {
		return supervisionTypeStatus;
	}

	/**
	 * @param supervisionTypeStatus the supervisionTypeStatus to set
	 */
	public void setSupervisionTypeStatus(String supervisionTypeStatus) {
		this.supervisionTypeStatus = supervisionTypeStatus;
	}

	public String getInactiveInd()
	{
	    return inactiveInd;
	}

	public void setInactiveInd(String inactiveInd)
	{
	    this.inactiveInd = inactiveInd;
	}

	public String getSplCategoryId()
	{
	    return splCategoryId;
	}

	public void setSplCategoryId(String splCategoryId)
	{
	    this.splCategoryId = splCategoryId;
	}
	public String getSupTJJDTypeId()
	{
	    return supTJJDTypeId;
	}

	public void setSupTJJDTypeId(String supTJJDTypeId)
	{
	    this.supTJJDTypeId = supTJJDTypeId;
	}
	
}

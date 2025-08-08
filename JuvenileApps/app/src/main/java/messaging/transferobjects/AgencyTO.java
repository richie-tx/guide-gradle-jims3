/*
 * Created on Aug 20, 2007
 *
 */
package messaging.transferobjects;

import java.util.Collection;

/**
 * @author cc_mdsouza
 *  
 */
public class AgencyTO 
extends PersistentObjectTO
{
	

	private CodeTO agencyType = null;
	private String agencyTypeId;
	private String agencyId;
	private String agencyName;
	private Collection departments = null;
	private String projectAnalystInd;

	public AgencyTO() {
	}

	
	
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	/**
	 * @return Returns the agencyName.
	 */
	public String getAgencyName() {
		return agencyName;
	}
	/**
	 * @param agencyName The agencyName to set.
	 */
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	/**
	 * @return Returns the agencyType.
	 */
	public CodeTO getAgencyType() {
		return agencyType;
	}
	/**
	 * @param agencyType The agencyType to set.
	 */
	public void setAgencyType(CodeTO agencyType) {
		this.agencyType = agencyType;
	}
	/**
	 * @return Returns the agencyTypeId.
	 */
	public String getAgencyTypeId() {
		return agencyTypeId;
	}
	/**
	 * @param agencyTypeId The agencyTypeId to set.
	 */
	public void setAgencyTypeId(String agencyTypeId) {
		this.agencyTypeId = agencyTypeId;
	}
	/**
	 * @return Returns the departments.
	 */
	public Collection getDepartments() {
		return departments;
	}
	/**
	 * @param departments The departments to set.
	 */
	public void setDepartments(Collection departments) {
		this.departments = departments;
	}
	/**
	 * @return Returns the projectAnalystInd.
	 */
	public String getProjectAnalystInd() {
		return projectAnalystInd;
	}
	/**
	 * @param projectAnalystInd The projectAnalystInd to set.
	 */
	public void setProjectAnalystInd(String projectAnalystInd) {
		this.projectAnalystInd = projectAnalystInd;
	}
}

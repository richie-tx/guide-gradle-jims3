/*
 * Created on Januray 31, 2008
 */
package ui.supervision.administercompliance.administerconditioncompliance.form;

import org.apache.struts.action.ActionForm;

/**
 * @author cshimek
 *  
 */
public class ComplianceSuperviseeInfoForm extends ActionForm 
{
	public String levelOfSupervision;
	public String officerName;
	public String programUnit;	
	public boolean superviseeCompliant;	
	public String superviseeName;
	private String superviseeSPN;
	
    public void clear()
    {
    	this.levelOfSupervision = "";
    	this.officerName = "";
    	this.programUnit = "";
    	this.superviseeCompliant = false;
    	this.superviseeName = "";
    	this.superviseeSPN = "";
    }

	/**
	 * @return Returns the levelOfSupervision.
	 */
	public String getLevelOfSupervision() {
		return levelOfSupervision;
	}
	/**
	 * @param levelOfSupervision The levelOfSupervision to set.
	 */
	public void setLevelOfSupervision(String levelOfSupervision) {
		this.levelOfSupervision = levelOfSupervision;
	}
	/**
	 * @return Returns the officerName.
	 */
	public String getOfficerName() {
		return officerName;
	}
	/**
	 * @param officerName The officerName to set.
	 */
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	/**
	 * @return Returns the programUnit.
	 */
	public String getProgramUnit() {
		return programUnit;
	}
	/**
	 * @param programUnit The programUnit to set.
	 */
	public void setProgramUnit(String programUnit) {
		this.programUnit = programUnit;
	}
	/**
	 * @return Returns the superviseeCompliant.
	 */
	public boolean isSuperviseeCompliant() {
		return superviseeCompliant;
	}
	/**
	 * @param superviseeCompliant The superviseeCompliant to set.
	 */
	public void setSuperviseeCompliant(boolean superviseeCompliant) {
		this.superviseeCompliant = superviseeCompliant;
	}
	/**
	 * @return Returns the superviseeName.
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}
	/**
	 * @param superviseeName The superviseeName to set.
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}
	/**
	 * @return Returns the superviseeSPN.
	 */
	public String getSuperviseeSPN() {
		return superviseeSPN;
	}
	/**
	 * @param superviseeSPN The superviseeSPN to set.
	 */
	public void setSuperviseeSPN(String superviseeSPN) {
		this.superviseeSPN = superviseeSPN;
	}
}
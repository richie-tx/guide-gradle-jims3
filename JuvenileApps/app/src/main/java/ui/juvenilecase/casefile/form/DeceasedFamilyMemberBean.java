/*
 * Created on Sep 26, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.casefile.form;

import ui.common.Name;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeceasedFamilyMemberBean {
	private Name deceasedMemberName;
	private String relationshipToJuvenile;
	private String juvenileAgeAtDeath;
	
	public DeceasedFamilyMemberBean() {
		deceasedMemberName = new Name();
		relationshipToJuvenile = "";		
	}
	
	/**
	 * @return Returns the deceasedMemberName.
	 */
	public Name getDeceasedMemberName() {
		return deceasedMemberName;
	}
	/**
	 * @param deceasedMemberName The deceasedMemberName to set.
	 */
	public void setDeceasedMemberName(Name deceasedMemberName) {
		this.deceasedMemberName = deceasedMemberName;
	}
	/**
	 * @return Returns the relationshipToJuvenile.
	 */
	public String getRelationshipToJuvenile() {
		return relationshipToJuvenile;
	}
	/**
	 * @param relationshipToJuvenile The relationshipToJuvenile to set.
	 */
	public void setRelationshipToJuvenile(String relationshipToJuvenile) {
		this.relationshipToJuvenile = relationshipToJuvenile;
	}
	/**
	 * @return Returns the juvenileAgeAtDeath.
	 */
	public String getJuvenileAgeAtDeath() {
		return juvenileAgeAtDeath;
	}
	/**
	 * @param juvenileAgeAtDeath The juvenileAgeAtDeath to set.
	 */
	public void setJuvenileAgeAtDeath(String juvenileAgeAtDeath) {
		this.juvenileAgeAtDeath = juvenileAgeAtDeath;
	}
}

/*
 * Created on Sep 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.casefile.form;

import java.util.ArrayList;
import java.util.List;

import ui.common.Name;

/**
 * @author awidjaja
 *
 */
public class FamilyMemberTraitBean {
	Name familyMemberName;
	String relationship; //Er changes 11119
	List traits;
	
	public FamilyMemberTraitBean() {
		familyMemberName = new Name();
		traits = new ArrayList();
	}
	
	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	/**
	 * @return Returns the familyMemberName.
	 */
	public Name getFamilyMemberName() {
		return familyMemberName;
	}
	/**
	 * @param familyMemberName The familyMemberName to set.
	 */
	public void setFamilyMemberName(Name familyMemberName) {
		this.familyMemberName = familyMemberName;
	}
	/**
	 * @return Returns the traits.
	 */
	public List getTraits() {
		return traits;
	}
	/**
	 * @param traits The traits to set.
	 */
	public void setTraits(List traits) {
		this.traits = traits;
	}
}

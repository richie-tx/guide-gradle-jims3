/*
 * Created on Jan 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.interviewinfo.form;

import ui.juvenilecase.form.JuvenileFamilyForm;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//The reason why it doesn't extend JuvenileFamilyForm.Guardian is
//that this class doesn't need any of guardian's financial info.
public class InterviewGuardian extends JuvenileFamilyForm.MemberList 
{
	private boolean isSelected = false;
	private String explanationMethod = "";
	/**
	 * @return Returns the explainationMethod.
	 */
	public String getExplanationMethod() {
		return explanationMethod;
	}
	/**
	 * @param explainationMethod The explainationMethod to set.
	 */
	public void setExplanationMethod(String explanationMethod) {
		
		this.explanationMethod = explanationMethod;
	}
	/**
	 * @return Returns the isSelected.
	 */
	public boolean isSelected() {
		return isSelected;
	}
	/**
	 * @param isSelected The isSelected to set.
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public InterviewGuardian()
	{
		isSelected = false;
		explanationMethod = "";
	}
	
	//This method will only set info required for Parental Rights use-case
	public void setGuardianInformation(JuvenileFamilyForm.MemberList member)
	{
		this.setMemberNumber(member.getMemberNumber());
		this.setMemberName(member.getMemberName());
		this.setRelationshipToJuvId(member.getRelationshipToJuvId());
		this.setDeceased(member.isDeceased());
		this.setGuardian(member.isGuardian());
		this.setFamilyConstellationMemberNum(member.getFamilyConstellationMemberNum());
	}
}
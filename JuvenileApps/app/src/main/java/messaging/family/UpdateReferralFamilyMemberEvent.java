/*
 * Created on Oct 22, 2018 by NM
 *
 */
package messaging.family;

import java.util.List;

import ui.juvenilecase.referral.JuvenileReferralMemberDetailsBean;

import mojo.km.messaging.RequestEvent;

/**
 * @author NM
 *
  */

public class UpdateReferralFamilyMemberEvent extends RequestEvent 
{
	
	private String  memberId;
	private JuvenileReferralMemberDetailsBean selectedMemberBean;
	private List<JuvenileReferralMemberDetailsBean> memberDetailsBeanList;
	private String	firstName;
	private String	lastName;
	private String	middleName;
	private String	ssn;
	private String relationship;
		
	private boolean	deceasedInd;
	private boolean	incarcerated;

	private List suspiciousMatches;
	
	
	/**
	 * @return
	 */
	public boolean isDeceasedInd()
	{
		return deceasedInd;
	}
	
	/**
	 * @return
	 */
	public boolean isIncarcerated()
	{
		return incarcerated;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMemberId()
	{
		return memberId;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return
	 */
	public String getSsn()
	{
		return ssn;
	}

	/**
	 * @param b
	 */
	public void setDeceasedInd(boolean b)
	{
		deceasedInd = b;
	}

	/**
	 * @param b
	 */
	public void setIncarcerated(boolean b)
	{
		incarcerated = b;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setMemberId(String string)
	{
		memberId = string;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	}
	/**
	 * @param string
	 */
	public void setSsn(String string)
	{
		ssn = string;
	}
	public List getSuspiciousMatches() {
		return suspiciousMatches;
	}

	public void setSuspiciousMatches(List suspiciousMatches) {
		this.suspiciousMatches = suspiciousMatches;
	}

	public List<JuvenileReferralMemberDetailsBean> getMemberDetailsBeanList()
	{
	    return memberDetailsBeanList;
	}

	public void setMemberDetailsBeanList(List<JuvenileReferralMemberDetailsBean> memberDetailsBeanList)
	{
	    this.memberDetailsBeanList = memberDetailsBeanList;
	}

	public JuvenileReferralMemberDetailsBean getSelectedMemberBean()
	{
	    return selectedMemberBean;
	}

	public void setSelectedMemberBean(JuvenileReferralMemberDetailsBean selectedMemberBean)
	{
	    this.selectedMemberBean = selectedMemberBean;
	}

	public String getRelationship()
	{
	    return relationship;
	}

	public void setRelationship(String relationship)
	{
	    this.relationship = relationship;
	}
}

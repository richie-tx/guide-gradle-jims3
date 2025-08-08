/*
 * Created on Oct 6, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReferralPrefillResponseEvent extends ResponseEvent
{

	private String probationStatus = "";
	private String courtStatus = "";
	private String petitionAllegation = "";
	private int numofCharges;
	private String referrals;
	private String school;
	private String grade;

	/**
	 * @return
	 */
	public String getCourtStatus()
	{
		return courtStatus;
	}

	/**
	 * @return
	 */
	public String getPetitionAllegation()
	{
		return petitionAllegation;
	}

	/**
	 * @return
	 */
	public String getProbationStatus()
	{
		return probationStatus;
	}

	/**
	 * @param string
	 */
	public void setCourtStatus(final String string)
	{
		courtStatus = string;
	}

	/**
	 * @param string
	 */
	public void setPetitionAllegation(final String string)
	{
		petitionAllegation = string;
	}

	/**
	 * @param string
	 */
	public void setProbationStatus(final String string)
	{
		probationStatus = string;
	}

	/**
	 * @return
	 */
	public int getNumofCharges()
	{
		return numofCharges;
	}

	/**
	 * @param i
	 */
	public void setNumofCharges(final int i)
	{
		numofCharges = i;
	}

	public void setReferrals(String referrals) {
		this.referrals = referrals;
	}

	public String getReferrals() {
		return referrals;
	}

	public String getSchool()
	{
	    return school;
	}

	public void setSchool(String school)
	{
	    this.school = school;
	}

	public String getGrade()
	{
	    return grade;
	}

	public void setGrade(String grade)
	{
	    this.grade = grade;
	}

}

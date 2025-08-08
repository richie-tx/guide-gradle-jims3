/*
 * Created on May 9, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateFamilyMemberMatchesEvent extends RequestEvent
{

	private String memberMatchId;
	private String memberA;
	private String memberB;
	private String status;
	private String notes;
	private String createUser;
	private String matchType;


	/**
	 * @return
	 */
	public String getMemberA()
	{
		return memberA;
	}

	/**
	 * @return
	 */
	public String getMemberB()
	{
		return memberB;
	}

	/**
	 * @return
	 */
	public String getNotes()
	{
		return notes;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @param string
	 */
	public void setMemberA(String string)
	{
		memberA = string;
	}

	/**
	 * @param string
	 */
	public void setMemberB(String string)
	{
		memberB = string;
	}

	/**
	 * @param string
	 */
	public void setNotes(String string)
	{
		notes = string;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string)
	{
		status = string;
	}

	/**
	 * @return
	 */
	public String getCreateUser()
	{
		return createUser;
	}

	/**
	 * @param string
	 */
	public void setCreateUser(String string)
	{
		createUser = string;
	}

	/**
	 * @return
	 */
	public String getMemberMatchId()
	{
		return memberMatchId;
	}

	/**
	 * @param string
	 */
	public void setMemberMatchId(String string)
	{
		memberMatchId = string;
	}

	public String getMatchType()
	{
	    return matchType;
	}

	public void setMatchType(String matchType)
	{
	    this.matchType = matchType;
	}

}

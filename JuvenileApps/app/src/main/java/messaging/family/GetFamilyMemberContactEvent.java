/*
 * Created on Sep 30, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetFamilyMemberContactEvent extends RequestEvent
{

	private String memberId;
	private String memberContactId;
	
	
	/**
	 * 
	 */
	public GetFamilyMemberContactEvent()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 */
	public String getMemberId()
	{
		return memberId;
	}

	/**
	 * @param string
	 */
	public void setMemberId(String string)
	{
		memberId = string;
	}


	/**
	 * @return
	 */
	public String getMemberContactId()
	{
		return memberContactId;
	}

	/**
	 * @param string
	 */
	public void setMemberContactId(String string)
	{
		memberContactId = string;
	}

}

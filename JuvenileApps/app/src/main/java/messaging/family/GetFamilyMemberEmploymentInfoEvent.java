/*
 * Created on Sep 29, 2005
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
public class GetFamilyMemberEmploymentInfoEvent extends RequestEvent
{
	private String memberNum;
	private String memberEmploymentNum;


	/**
	 * @return
	 */
	public String getMemberEmploymentNum()
	{
		return memberEmploymentNum;
	}

	/**
	 * @return
	 */
	public String getMemberNum()
	{
		return memberNum;
	}

	/**
	 * @param string
	 */
	public void setMemberEmploymentNum(String string)
	{
		memberEmploymentNum = string;
	}

	/**
	 * @param string
	 */
	public void setMemberNum(String string)
	{
		memberNum = string;
	}

}

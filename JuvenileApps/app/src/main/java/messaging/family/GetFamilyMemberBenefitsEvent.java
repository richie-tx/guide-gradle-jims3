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
public class GetFamilyMemberBenefitsEvent extends RequestEvent
{

	private String memberId;
	private String memberBenefitId;

	/**
	 * 
	 */
	public GetFamilyMemberBenefitsEvent()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 */
	public String getMemberBenefitId()
	{
		return memberBenefitId;
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
	public void setMemberBenefitId(String string)
	{
		memberBenefitId = string;
	}

	/**
	 * @param string
	 */
	public void setMemberId(String string)
	{
		memberId = string;
	}

}

/*
 * Created on May 6, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJuvenileCasefileByCompositeKeyEvent extends RequestEvent
{
	private String juvenileNum;
	private String jpoUserId;		// removed for defect 32703.
	private String supervisionTypeId;
	private String caseStatusId;

	/**
	 * @return Returns the jpoUserId.
	 */
	public String getJpoUserId() {
		return jpoUserId;
	}
	/**
	 * @param jpoUserId The jpoUserId to set.
	 */
	public void setJpoUserId(String jpoUserId) {
		this.jpoUserId = jpoUserId;
	}
	/**
	 * @return
	 */
	public String getCaseStatusId()
	{
		return caseStatusId;
	}

	/**
	 * @return
	 */
//	public String getJpoUserId()
//	{
//		return jpoUserId;
//	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return
	 */
	public String getSupervisionTypeId()
	{
		return supervisionTypeId;
	}

	/**
	 * @param string
	 */
	public void setCaseStatusId(String string)
	{
		caseStatusId = string;
	}

	/**
	 * @param string
	 */
//	public void setJpoUserId(String string)
//	{
//		jpoUserId = string;
//	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisionTypeId(String string)
	{
		supervisionTypeId = string;
	}

}

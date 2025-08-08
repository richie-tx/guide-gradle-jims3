/*
 * Created on Dec 2, 2004
 */
package messaging.codetable.criminal.reply;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;


public class JuvenileCourtResponseEvent extends ResponseEvent implements Comparable, ICode
{
	private String tycCourtCode;
	private String judgeName;
	private String code;
	private String description;
	private String inactiveInd;
	private String refereesCourtInd;
	
	/**
	 * @return
	 */
	public String getCode()
	{
		return this.code;
	}

	/**
	 * @return
	 */
	public String getTYCCourtCode()
	{
		return this.tycCourtCode;
	}

	/**
	 * @return
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * @return
	 */
	public String getJudgeName()
	{
		if(judgeName == null || judgeName.equals("")) {
			judgeName = "Not Available";
		}
		return this.judgeName;
	}
	
	/**
	 * @return
	 */
	public String getInactiveInd() 
	{
		return this.inactiveInd;
	}

	/**
	 * @param code
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * @param courtCode
	 */
	public void setTYCCourtCode(String tycCourtCode)
	{
		this.tycCourtCode = tycCourtCode;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @param judgeName
	 */
	public void setJudgeName(String judgeName)
	{
		this.judgeName = judgeName;
	}
	
	/**
	 * @param inactive
	 */
	public void setInactiveInd(String inactiveInd)
	{
		this.inactiveInd = inactiveInd;
	}

	/**
	 * @return
	 */
	public String getRefereesCourtInd()
	{
		return refereesCourtInd;
	}

	/**
	 * @return
	 */
	public String getTycCourtCode()
	{
		return tycCourtCode;
	}

	/**
	 * @param string
	 */
	public void setRefereesCourtInd(String refereesCourtInd )
	{
		this.refereesCourtInd = refereesCourtInd;
	}

	/**
	 * @param string
	 */
	public void setTycCourtCode(String string)
	{
		tycCourtCode = string;
	}
	
	public int compareTo(Object obj) throws ClassCastException {
		JuvenileCourtResponseEvent evt = (JuvenileCourtResponseEvent)obj;
		return description.compareTo(evt.getDescription());
	}

}

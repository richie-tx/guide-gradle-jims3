/*
 * Created on Feb 16, 2006
 *
 */
package messaging.supervisionorder;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class PrepareToFileSupervisionOrderEvent extends RequestEvent
{
	private String presentorFirstName;
	private String presentorLastName;
	private Date signedDate;
	private String signorFirstName;
	private String signorLastName;
	private String signorType;
	private String supervisionOrderId;
	private Date judgeSignedDate;

	/**
	 * @return
	 */
	public String getPresentorFirstName()
	{
		return presentorFirstName;
	}

	/**
	 * @return
	 */
	public String getPresentorLastName()
	{
		return presentorLastName;
	}

	/**
	 * @return
	 */
	public Date getSignedDate()
	{
		return signedDate;
	}

	/**
	 * @return
	 */
	public String getSignorFirstName()
	{
		return signorFirstName;
	}

	/**
	 * @return
	 */
	public String getSignorLastName()
	{
		return signorLastName;
	}

	/**
	 * @return
	 */
	public String getSignorType()
	{
		return signorType;
	}

	/**
	 * @return
	 */
	public String getSupervisionOrderId()
	{
		return supervisionOrderId;
	}

	/**
	 * @param aPresentorFirstName
	 */
	public void setPresentorFirstName(String aPresentorFirstName)
	{
		presentorFirstName = aPresentorFirstName;
	}

	/**
	 * @param aPresentorLastName
	 */
	public void setPresentorLastName(String aPresentorLastName)
	{
		presentorLastName = aPresentorLastName;
	}

	/**
	 * @param aDate
	 */
	public void setSignedDate(Date aDate)
	{
		signedDate = aDate;
	}

	/**
	 * @param aSignorFirstName
	 */
	public void setSignorFirstName(String aSignorFirstName)
	{
		signorFirstName = aSignorFirstName;
	}

	/**
	 * @param aSignorLastName
	 */
	public void setSignorLastName(String aSignorLastName)
	{
		signorLastName = aSignorLastName;
	}

	/**
	 * @param aSignorType
	 */
	public void setSignorType(String aSignorType)
	{
		signorType = aSignorType;
	}

	/**
	 * @param aSupervisionOrderId
	 */
	public void setSupervisionOrderId(String aSupervisionOrderId)
	{
		supervisionOrderId = aSupervisionOrderId;
	}

	public void setJudgeSignedDate(Date judgeSignedDate) {
		this.judgeSignedDate = judgeSignedDate;
	}

	public Date getJudgeSignedDate() {
		return judgeSignedDate;
	}

}

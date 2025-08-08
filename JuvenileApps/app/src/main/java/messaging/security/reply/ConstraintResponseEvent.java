/*
 * Created on Apr 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.security.reply;

import mojo.km.messaging.ResponseEvent;


/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConstraintResponseEvent extends ResponseEvent
{
	private String constraintId;
	private String constrainsId;
	private String constrainsType;
	private String constrainerId;
	private String constrainerType;


	/**
	 * @return ConstrainerId
	 */
	public String getConstrainerId()
	{
		return constrainerId;
	}

	/**
	 * @return ConstrainerType
	 */
	public String getConstrainerType()
	{
		return constrainerType;
	}

	/**
	 * @return ConstrainsId
	 */
	public String getConstrainsId()
	{
		return constrainsId;
	}

	/**
	 * @return ConstrainsType
	 */
	public String getConstrainsType()
	{
		return constrainsType;
	}

	/**
	 * @return ConstraintId
	 */
	public String getConstraintId()
	{
		return constraintId;
	}

	/**
	 * @param string ConstrainerId
	 */
	public void setConstrainerId(String string)
	{
		constrainerId = string;
	}

	/**
	 * @param string ConstrainerType
	 */
	public void setConstrainerType(String string)
	{
		constrainerType = string;
	}

	/**
	 * @param string ConstrainsId
	 */
	public void setConstrainsId(String string)
	{
		constrainsId = string;
	}

	/**
	 * @param string ConstrainsType
	 */
	public void setConstrainsType(String string)
	{
		constrainsType = string;
	}

	/**
	 * @param string ConstraintId
	 */
	public void setConstraintId(String string)
	{
		constraintId = string;
	}

}

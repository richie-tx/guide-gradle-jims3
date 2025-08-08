/*
 * Created on Oct 11, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import java.util.Date;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface IFamilyMemberMaritalStatus
{
	/**
	 * @return
	 */
	public Date getDivorceDate();

	/**
	 * @return
	 */
	public Date getEntryDate();

	/**
	 * @return
	 */
	public String getMaritalStatusId();

	/**
	 * @return
	 */
	public Date getMarriageDate();

	/**
	 * @return
	 */
	public String getNoOfChildren();

	/**
	 * @param date
	 */
	public void setDivorceDate(Date date);

	/**
	 * @param date
	 */
	public void setEntryDate(Date date);

	/**
	 * @param string
	 */
	public void setMaritalStatusId(String string);

	/**
	 * @param date
	 */
	public void setMarriageDate(Date date);

	/**
	 * @param String
	 */
	public void setNoOfChildren(String string);

}

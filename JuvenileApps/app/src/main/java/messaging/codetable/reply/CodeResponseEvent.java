/*
 * Created on Aug 5, 2004
 *
 */
package messaging.codetable.reply;

import java.util.Date;
 

import mojo.km.messaging.ResponseEvent;

/**
 * @author mlawles
 *
 */
public class CodeResponseEvent extends ResponseEvent implements Comparable, ICode {
	private Date activeDate;
	private boolean canDelete;
	private String code;
	private String codeId;
	private String codeTableName;
	private String description;
	private boolean hasHistory;
	private Date inactiveDate;
	private Date inactiveEffectiveDate;
	private String pendingStatus;
	private String status;
	private String supervisionCode;
	private String transaction;
	private boolean visible;
	private String descriptionWithCode;
	
	public int compareTo(Object obj) throws ClassCastException {
		ICode evt = (ICode)obj;
		return description.compareTo(evt.getDescription());
	}

	/**
	 * @return
	 */
	public Date getActiveDate()
	{
		return activeDate;
	}

	/**
	 * @return
	 */
	public boolean getCanDelete()
	{
		return canDelete;
	}
	
	/**
	 * @return
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @return
	 */
	public String getCodeId()
	{
		return codeId;
	}

	/**
	 * @return
	 */
	public String getCodeTableName()
	{
		return codeTableName;
	}

	/**
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return
	 */
	public Date getInactiveDate()
	{
		return inactiveDate;
	}

	/**
	 * @return
	 */
	public Date getInactiveEffectiveDate()
	{
		return inactiveEffectiveDate;
	}

	/**
	 * @return
	 */
	public String getPendingStatus()
	{
		return pendingStatus;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
		return status;
	}

	
    /**
     * @return Returns the supervisionCode.
     */
    public String getSupervisionCode() {
        return supervisionCode;
    }

	/**
	 * @return
	 */
	public String getTransaction()
	{
		return transaction;
	}

	/**
	 * @return
	 */
	public boolean isHasHistory()
	{
		return hasHistory;
	}

	/**
	 * @return
	 */
	public boolean isVisible()
	{
		return visible;
	}

	/**
	 * @param date
	 */
	public void setActiveDate(Date date)
	{
		activeDate = date;
	}

	/**
	 * @param b
	 */
	public void setCanDelete(boolean b)
	{
		canDelete = b;
	}

	/**
	 * @param string
	 */
	public void setCode(String string)
	{
		code = string;
	}

	/**
	 * @param string
	 */
	public void setCodeId(String string)
	{
		codeId = string;
	}

	/**
	 * @param string
	 */
	public void setCodeTableName(String string)
	{
		codeTableName = string;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}

	/**
	 * @param b
	 */
	public void setHasHistory(boolean b)
	{
		hasHistory = b;
	}

	/**
	 * @param date
	 */
	public void setInactiveDate(Date date)
	{
		inactiveDate = date;
	}

	/**
	 * @param date
	 */
	public void setInactiveEffectiveDate(Date date)
	{
		inactiveEffectiveDate = date;
	}

	/**
	 * @param string
	 */
	public void setPendingStatus(String string)
	{
		pendingStatus = string;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string)
	{
		status = string;
	}
    /**
     * @param supervisionCode The supervisionCode to set.
     */
    public void setSupervisionCode(String supervisionCode) {
        this.supervisionCode = supervisionCode;
    }

	/**
	 * @param string
	 */
	public void setTransaction(String string)
	{
		transaction = string;
	}

	/**
	 * @param b
	 */
	public void setVisible(boolean b)
	{
		visible = b;
	}
	
	public String toString() {
		return description;
	}

	/**
	 * @return the descriptionWithCode
	 */
	public String getDescriptionWithCode() {
		return descriptionWithCode;
	}

	/**
	 * @param descriptionWithCode the descriptionWithCode to set
	 */
	public void setDescriptionWithCode(String descriptionWithCode) {
		this.descriptionWithCode = descriptionWithCode;
	}
}

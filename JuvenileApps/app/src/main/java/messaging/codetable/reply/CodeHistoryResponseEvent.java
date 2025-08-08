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
public class CodeHistoryResponseEvent extends ResponseEvent
{
	private Date activeDate;
	private String activeTime;
	private String auditId;
	private String code;
	private String codeHistoryId;
	private String codeId;
	private String codeTableSubTypeId;
	private String codeTableTypeId;
	private String description;
	private Date inactiveDate;
	private Date inactiveEffectiveDate;
	private String inactiveEffectiveTime;
	private String inactiveTime;
	private String status;
	private String transaction;
	private Date transactionDate;
	private String transactionLogonId;
	private String transactionTime;
	private String logonId;

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
	public String getActiveTime()
	{
		return activeTime;
	}
	/**
	 * @return
	 */
	public String getAuditId()
	{
		return auditId;
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
	public String getCodeHistoryId()
	{
		return codeHistoryId;
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
	public String getCodeTableSubTypeId()
	{
		return codeTableSubTypeId;
	}

	/**
	 * @return
	 */
	public String getCodeTableTypeId()
	{
		return codeTableTypeId;
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
	public String getInactiveEffectiveTime()
	{
		return inactiveEffectiveTime;
	}

	/**
	 * @return
	 */
	public String getInactiveTime()
	{
		return inactiveTime;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
		return status;
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
	public Date getTransactionDate()
	{
		return transactionDate;
	}

	/**
	 * @return
	 */
	public String getTransactionLogonId()
	{
		return transactionLogonId;
	}

	/**
	 * @return
	 */
	public String getTransactionTime()
	{
		return transactionTime;
	}

	/**
	 * @param activeDate
	 */
	public void setActiveDate(Date activeDate)
	{
		this.activeDate = activeDate;
	}

	/**
	 * @param activeTime
	 */
	public void setActiveTime(String activeTime)
	{
		this.activeTime = activeTime;
	}

	/**
	 * @param auditId
	 */
	public void setAuditId(String auditId)
	{
		this.auditId = auditId;
	}

	/**
	 * @param code
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * @param codeHistoryId
	 */
	public void setCodeHistoryId(String codeHistoryId)
	{
		this.codeHistoryId = codeHistoryId;
	}

	/**
	 * @param codeId
	 */
	public void setCodeId(String codeId)
	{
		this.codeId = codeId;
	}

	/**
	 * @param codeTableSubTypeId
	 */
	public void setCodeTableSubTypeId(String codeTableSubTypeId)
	{
		this.codeTableSubTypeId = codeTableSubTypeId;
	}

	/**
	 * @param codeTableTypeId
	 */
	public void setCodeTableTypeId(String codeTableTypeId)
	{
		this.codeTableTypeId = codeTableTypeId;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @param inactiveDate
	 */
	public void setInactiveDate(Date inactiveDate)
	{
		this.inactiveDate = inactiveDate;
	}

	/**
	 * @param inactiveEffectiveDate
	 */
	public void setInactiveEffectiveDate(Date inactiveEffectiveDate)
	{
		this.inactiveEffectiveDate = inactiveEffectiveDate;
	}

	/**
	 * @param inactiveEffectiveTime
	 */
	public void setInactiveEffectiveTime(String inactiveEffectiveTime)
	{
		this.inactiveEffectiveTime = inactiveEffectiveTime;
	}

	/**
	 * @param inactiveTime
	 */
	public void setInactiveTime(String inactiveTime)
	{
		this.inactiveTime = inactiveTime;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * @param transaction
	 */
	public void setTransaction(String transaction)
	{
		this.transaction = transaction;
	}

	/**
	 * @param transactionDate
	 */
	public void setTransactionDate(Date transactionDate)
	{
		this.transactionDate = transactionDate;
	}

	/**
	 * @param transactionLogonId
	 */
	public void setTransactionId(String transactionLogonId)
	{
		this.transactionLogonId = transactionLogonId;
	}

	/**
	 * @param transactionLogonId
	 */
	public void setTransactionLogonId(String transactionLogonId)
	{
		this.transactionLogonId = transactionLogonId;
	}

	/**
	 * @param transactionTime
	 */
	public void setTransactionTime(String transactionTime)
	{
		this.transactionTime = transactionTime;
	}
	/**
	 * @return
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * @param string
	 */
	public void setLogonId(String string)
	{
		logonId = string;
	}

}
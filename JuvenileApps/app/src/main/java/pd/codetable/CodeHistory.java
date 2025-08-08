//Source file: C:\\views\\dev\\app\\src\\pd\\codetable\\CodeHistory.java

package pd.codetable;

import java.util.Date;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
@referenceProperty auditId
 */
public class CodeHistory extends PersistentObject
{
	private String codeId;
	private String code;
	private String description;
	private String status;
	private Date inactiveDate;
	private Date inactiveEffectiveDate;
	private String auditId;
	private Date activeDate;
	private String transaction;
	private Date transactionDate;
	private String transactionLogonId;

	/**
	@roseuid 40F7EE3E02C8
	 */
	public CodeHistory()
	{

	}

	/**
	Access method for the codeId property.
	
	@return   the current value of the codeId property
	 */
	public String getCodeId()
	{
		fetch();
		return codeId;
	}

	/**
	Sets the value of the codeId property.
	
	@param aCodeId the new value of the codeId property
	 */
	public void setCodeId(String aCodeId)
	{
		if (this.codeId == null || !this.codeId.equals(aCodeId))
		{
			markModified();
		}
		codeId = aCodeId;
	}

	/**
	Access method for the code property.
	
	@return   the current value of the code property
	 */
	public String getCode()
	{
		fetch();
		return code;
	}

	/**
	Sets the value of the code property.
	
	@param aCode the new value of the code property
	 */
	public void setCode(String aCode)
	{
		if (this.code == null || !this.code.equals(aCode))
		{
			markModified();
		}
		code = aCode;
	}

	/**
	Access method for the description property.
	
	@return   the current value of the description property
	 */
	public String getDescription()
	{
		fetch();
		return description;
	}

	/**
	Sets the value of the description property.
	
	@param aDescription the new value of the description property
	 */
	public void setDescription(String aDescription)
	{
		if (this.description == null || !this.description.equals(aDescription))
		{
			markModified();
		}
		description = aDescription;
	}

	/**
	Access method for the status property.
	
	@return   the current value of the status property
	 */
	public String getStatus()
	{
		fetch();
		return status;
	}

	/**
	Sets the value of the status property.
	
	@param aStatus the new value of the status property
	 */
	public void setStatus(String aStatus)
	{
		if (!this.status.equals(aStatus))
		{
			markModified();
		}
		status = aStatus;
	}

	/**
	Access method for the inactiveDate property.
	
	@return   the current value of the inactiveDate property
	 */
	public Date getInactiveDate()
	{
		fetch();
		return inactiveDate;
	}

	/**
	Sets the value of the inactiveDate property.
	
	@param aInactiveDate the new value of the inactiveDate property
	 */
	public void setInactiveDate(Date aInactiveDate)
	{
		if (!this.inactiveDate.equals(aInactiveDate))
		{
			markModified();
		}
		inactiveDate = aInactiveDate;
	}

	/**
	Access method for the inactiveEffectiveDate property.
	
	@return   the current value of the inactiveEffectiveDate property
	 */
	public Date getInactiveEffectiveDate()
	{
		fetch();
		return inactiveEffectiveDate;
	}

	/**
	Sets the value of the inactiveEffectiveDate property.
	
	@param aInactiveEffectiveDate the new value of the inactiveEffectiveDate property
	 */
	public void setInactiveEffectiveDate(Date aInactiveEffectiveDate)
	{
		if (!this.inactiveEffectiveDate.equals(aInactiveEffectiveDate))
		{
			markModified();
		}
		inactiveEffectiveDate = aInactiveEffectiveDate;
	}

	/**
	Access method for the auditId property.
	
	@return   the current value of the auditId property
	 */
	public String getAuditId()
	{
		fetch();
		return auditId;
	}

	/**
	Sets the value of the auditId property.
	
	@param aAuditId the new value of the auditId property
	 */
	public void setAuditId(String aAuditId)
	{
		if ((this.auditId == null && aAuditId != this.auditId) || !this.auditId.equals(aAuditId))
		{
			markModified();
		}
		auditId = aAuditId;
	}

	/**
	Access method for the activeDate property.
	
	@return   the current value of the activeDate property
	 */
	public Date getActiveDate()
	{
		return activeDate;
	}

	/**
	Sets the value of the activeDate property.
	
	@param aActiveDate the new value of the activeDate property
	 */
	public void setActiveDate(Date aActiveDate)
	{
		activeDate = aActiveDate;
	}

	/**
	Access method for the transaction property.
	
	@return   the current value of the transaction property
	 */
	public String getTransaction()
	{
		fetch();
		return transaction;
	}

	/**
	Sets the value of the transaction property.
	
	@param aTransaction the new value of the transaction property
	 */
	public void setTransaction(String aTransaction)
	{
		if ((this.transaction == null && aTransaction != this.transaction) || !this.transaction.equals(aTransaction))
		{
			markModified();
		}
		transaction = aTransaction;
	}

	/**
	Access method for the transactionDate property.
	
	@return   the current value of the transactionDate property
	 */
	public Date getTransactionDate()
	{
		fetch();
		return transactionDate;
	}

	/**
	Sets the value of the transactionDate property.
	
	@param aTransactionDate the new value of the transactionDate property
	 */
	public void setTransactionDate(Date aTransactionDate)
	{
		if ((this.transactionDate == null && aTransactionDate != this.transactionDate)
			|| !this.transactionDate.equals(aTransactionDate))
		{
			markModified();
		}
		transactionDate = aTransactionDate;
	}

	/**
	Access method for the transactionLogonId property.
	
	@return   the current value of the transactionLogonId property
	 */
	public String getTransactionLogonId()
	{
		fetch();
		return transactionLogonId;
	}

	/**
	Sets the value of the transactionLogonId property.
	
	@param aTransactionLogonId the new value of the transactionLogonId property
	 */
	public void setTransactionLogonId(String aTransactionLogonId)
	{
		if ((this.transactionLogonId == null && aTransactionLogonId != this.transactionLogonId)
			|| !this.transactionLogonId.equals(aTransactionLogonId))
		{
			markModified();
		}
		transactionLogonId = aTransactionLogonId;
	}

	/**
	@param auditId
	
	@return pd.codetable.CodeHistory
	@roseuid 40F7E6B50256
	 */
	public static CodeHistory find(String auditId)
	{
		IHome home = new Home();
		CodeHistory history = null;
		history = (CodeHistory) home.find(auditId, CodeHistory.class);
		return history;
	}
}

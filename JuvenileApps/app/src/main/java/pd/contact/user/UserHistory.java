// no longer in use. Migrated to SM. Refer US #87188.
package pd.contact.user;



import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

import java.util.Date;
import java.util.Iterator;
import pd.codetable.Code;

/**
 * 
 * @author dgibler
 To change the template for this generated type comment go to
 Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserHistory extends PersistentObject
{
	/*static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		return home.findAll(attrName, attrValue, UserHistory.class);
	}
	
	private String actionInd;
	private String changeTime;
	private String fieldName;
	private String logonId;
	private String newValue;
	private String oldValue;
	private String transaction;
	private Date transactionDate;
	private String transactionLogonId;
	*//**
	 * Properties for transactionUser
	 * @detailerDoNotGenerate true
	 * @referencedType pd.contact.user.UserProfile
	 *//*
	private UserProfile transactionUser = null;
	private Date transferDate;
	*//**
	 * Properties for inactivationTime
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey WORK_DAY
	 *//*
	private Code transferTime = null;
	private String transferTimeId;
	private String userHistoryId;

	*//**
	 * 
	 * @roseuid 4107BF850224
	 *//*
	public UserHistory()
	{
	}

	*//**
	 * Access method for the actionInd property.
	 * @return the current value of the actionInd property
	 *//*
	public java.lang.String getActionInd()
	{
		fetch();
		return actionInd;
	}

	*//**
	 * 
	 * @return Returns the changeTime.
	 *//*
	public String getChangeTime()
	{
		fetch();
		return changeTime;
	}

	*//**
	 * Access method for the fieldName property.
	 * @return the current value of the fieldName property
	 *//*
	public java.lang.String getFieldName()
	{
		fetch();
		return fieldName;
	}

	*//**
	 * Access method for the logonId property.
	 * @return the current value of the logonId property
	 *//*
	public java.lang.String getLogonId()
	{
		fetch();
		return logonId;
	}

	*//**
	 * Access method for the newValue property.
	 * @return the current value of the newValue property
	 *//*
	public java.lang.String getNewValue()
	{
		fetch();
		return newValue;
	}

	*//**
	 * Access method for the oldValue property.
	 * @return the current value of the oldValue property
	 *//*
	public java.lang.String getOldValue()
	{
		fetch();
		return oldValue;
	}

	*//**
	 * Access method for the transaction property.
	 * @return the current value of the transaction property
	 *//*
	public java.lang.String getTransaction()
	{
		fetch();
		return transaction;
	}

	*//**
	 * Access method for the transactionDate property.
	 * @return the current value of the transactionDate property
	 *//*
	public Date getTransactionDate()
	{
		fetch();
		return transactionDate;
	}

	*//**
	 * Access method for the transactionLogonId property.
	 * @return the current value of the transactionLogonId property
	 *//*
	public java.lang.String getTransactionLogonId()
	{
		fetch();
		return transactionLogonId;
	}

	*//**
	 * Gets referenced type pd.contact.UserProfile
	 * @return transactionUser
	 *//*
	public pd.contact.user.UserProfile getTransactionUser()
	{
		fetch();
		initTransactionUser();
		return transactionUser;
	}

	*//**
	 * 
	 * @return java.util.Date
	 *//*
	public Date getTransferDate()
	{
		fetch();
		return transferDate;
	}

	*//**
	 * Gets referenced type pd.codetable.Code
	 * @return Code transferTime
	 *//*
	public pd.codetable.Code getTransferTime()
	{
		fetch();
		initTransferTime();
		return transferTime;
	}

	*//**
	 * 
	 * @return String
	 *//*
	public String getTransferTimeId()
	{
		fetch();
		return transferTimeId;
	}

	*//**
	 * Get the reference value to class :: pd.contact.UserProfile
	 * @return String OID
	 *//*
	public String getUserHistoryId()
	{
		return "" + getOID();
	}

	*//**
	 * Initialize class relationship to class pd.contact.UserProfile
	 *//*
	private void initTransactionUser()
	{
		if (transactionUser == null)
		{
			try
			{
				transactionUser = (pd.contact.user.UserProfile) new mojo.km.persistence.Reference(transactionLogonId,
						pd.contact.user.UserProfile.class).getObject();
			}
			catch (Throwable t)
			{
				t.printStackTrace();
			}
		}
	}

	*//**
	 * Initialize class relationship to class pd.codetable.Code
	 *//*
	private void initTransferTime()
	{
		if (transferTime == null)
		{
			try
			{
				transferTime = (pd.codetable.Code) new mojo.km.persistence.Reference(transferTimeId,
						pd.codetable.Code.class, "WORK_DAY").getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}

	*//**
	 * Sets the value of the actionInd property.
	 * @param aActionInd the new value of the actionInd property
	 *//*
	public void setActionInd(java.lang.String aActionInd)
	{
		if (this.actionInd == null || !this.actionInd.equals(aActionInd))
		{
			markModified();
		}
		actionInd = aActionInd;
	}

	*//**
	 * 
	 * @param changeTime The changeTime to set.
	 *//*
	public void setChangeTime(String changeTime)
	{
		if (this.changeTime == null || !this.changeTime.equals(changeTime))
		{
			markModified();
		}
		this.changeTime = changeTime;
	}

	*//**
	 * Sets the value of the fieldName property.
	 * @param aFieldName the new value of the fieldName property
	 *//*
	public void setFieldName(java.lang.String aFieldName)
	{
		if (this.fieldName == null || !this.fieldName.equals(aFieldName))
		{
			markModified();
		}
		fieldName = aFieldName;
	}

	*//**
	 * Sets the value of the logonId property.
	 * @param aLogonId the new value of the logonId property
	 *//*
	public void setLogonId(java.lang.String aLogonId)
	{
		if (this.logonId == null || !this.logonId.equals(aLogonId))
		{
			markModified();
		}
		logonId = aLogonId;
	}

	*//**
	 * Sets the value of the newValue property.
	 * @param aNewValue the new value of the newValue property
	 *//*
	public void setNewValue(java.lang.String aNewValue)
	{
		if (this.newValue == null || !this.newValue.equals(aNewValue))
		{
			markModified();
		}
		newValue = aNewValue;
	}

	*//**
	 * Sets the value of the oldValue property.
	 * @param aOldValue the new value of the oldValue property
	 *//*
	public void setOldValue(java.lang.String aOldValue)
	{
		if (this.oldValue == null || !this.oldValue.equals(aOldValue))
		{
			markModified();
		}
		oldValue = aOldValue;
	}

	*//**
	 * Sets the value of the transaction property.
	 * @param aTransaction the new value of the transaction property
	 *//*
	public void setTransaction(java.lang.String aTransaction)
	{
		if (this.transaction == null || !this.transaction.equals(aTransaction))
		{
			markModified();
		}
		transaction = aTransaction;
	}

	*//**
	 * Sets the value of the transactionDate property.
	 * @param aTransactionDate the new value of the transactionDate property
	 *//*
	public void setTransactionDate(Date aTransactionDate)
	{
		if (this.transactionDate == null || !this.transactionDate.equals(aTransactionDate))
		{
			markModified();
		}
		transactionDate = aTransactionDate;
	}

	*//**
	 * Sets the value of the transactionLogonId property.
	 * @param aTransactionLogonId the new value of the transactionLogonId property
	 *//*
	public void setTransactionLogonId(java.lang.String aTransactionLogonId)
	{
		if (this.transactionLogonId == null || !this.transactionLogonId.equals(aTransactionLogonId))
		{
			markModified();
		}
		transactionLogonId = aTransactionLogonId;
	}

	*//**
	 * set the type reference for class member transactionUser
	 *//*
	public void setTransactionUser(pd.contact.user.UserProfile atransactionUser)
	{
		if (this.transactionUser == null || !this.transactionUser.equals(atransactionUser))
		{
			markModified();
		}
		setTransactionLogonId("" + atransactionUser.getOID());
		this.transactionUser = (pd.contact.user.UserProfile) new mojo.km.persistence.Reference(
				(PersistentObject) atransactionUser).getObject();
	}

	*//**
	 * 
	 * @param transferDate
	 *//*
	public void setTransferDate(Date transferDate)
	{
		if (this.transferDate == null || !this.transferDate.equals(transferDate))
		{
			markModified();
		}
		this.transferDate = transferDate;
	}

	*//**
	 * set the type reference for class member transferTime
	 * @param aTransferTime
	 *//*
	public void setTransferTime(pd.codetable.Code aTransferTime)
	{
		if (this.transferTime == null || !this.transferTime.equals(aTransferTime))
		{
			markModified();
		}
		if (aTransferTime.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aTransferTime);
		}
		setTransferTimeId("" + aTransferTime.getOID());
		this.transferTime = (pd.codetable.Code) new mojo.km.persistence.Reference(aTransferTime).getObject();
	}

	*//**
	 * 
	 * @param transferTimeId
	 *//*
	public void setTransferTimeId(String transferTimeId)
	{
		if (this.transferTimeId == null || !this.transferTimeId.equals(transferTimeId))
		{
			markModified();
		}
		transferTime = null;
		this.transferTimeId = transferTimeId;
	}

	*//**
	 * Set the reference value to class :: pd.contact.UserProfile
	 * @param userHistoryId
	 *//*
	public void setUserHistoryId(String auserHistoryId)
	{
		if (this.userHistoryId == null || !this.userHistoryId.equals(auserHistoryId))
		{
			markModified();
		}
		this.userHistoryId = auserHistoryId;
	}*/
}

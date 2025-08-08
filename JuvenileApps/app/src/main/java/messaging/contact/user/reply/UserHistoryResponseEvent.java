package messaging.contact.user.reply;// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.

/*
 * Created on Mar 29, 2004
 
package messaging.contact.user.reply;

import java.util.Date;
import mojo.km.messaging.ResponseEvent;

*//**
 * @author dgibler
 *//*
public class UserHistoryResponseEvent extends ResponseEvent implements Comparable
{
	private String action;
	private String changedByLogonId;
	private String changedByUserFirstName;
	private String changedByUserLastName;
	private String changeTime;
	private String fieldName;
	private String logonId;
	private String newValue;
	private String oldValue;
	private String transaction;
	private Date transactionDate;
	private Date transferDate;
	private String transferTime;
	
	public int compareTo(Object obj) throws ClassCastException
		{
			if(obj==null)
				return -1;
			UserHistoryResponseEvent evt = (UserHistoryResponseEvent) obj;
			int result = 0;
				try{
					if(this.transactionDate!=null || evt.getTransactionDate()!=null){
						if(evt.getTransactionDate()==null)
							return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
						if(this.transactionDate==null)
							return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
						Date currDate=this.transactionDate;
						Date incomingDate=evt.getTransactionDate();
						if(currDate==null)
							return -1;
						if(incomingDate==null)
							return 1;
						result= incomingDate.compareTo(currDate); // backwards in order to get list to show up most recent first
					}
			
				}
				catch(NumberFormatException e){
					result = 0;
				}
	
			return result;
		}		

	*//**
	 * @return
	 *//*
	public String getAction()
	{
		return action;
	}

	*//**
	 * @return String
	 *//*
	public String getChangedByLogonId()
	{
		return changedByLogonId;
	}

	*//**
	 * @return String
	 *//*
	public String getChangedByUserFirstName()
	{
		return changedByUserFirstName;
	}

	*//**
	 * @return String
	 *//*
	public String getChangedByUserLastName()
	{
		return changedByUserLastName;
	}

    *//**
     * @return Returns the changeTime.
     *//*
    public String getChangeTime()
    {
        return changeTime;
    }

    *//**
	 * @return
	 *//*
	public String getFieldName()
	{
		return fieldName;
	}

	*//**
	 * @return
	 *//*
	public String getLogonId()
	{
		return logonId;
	}

	*//**
	 * @return String
	 *//*
	public String getNewValue()
	{
		return newValue;
	}

	*//**
	 * @return String
	 *//*
	public String getOldValue()
	{
		return oldValue;
	}

	*//**
	 * @return String
	 *//*
	public String getTransaction()
	{
		return transaction;
	}

	*//**
	 * @return java.util.Date
	 *//*
	public Date getTransactionDate()
	{
		return transactionDate;
	}

	*//**
	 * @return java.util.Date
	 *//*
	public Date getTransferDate()
	{
		return transferDate;
	}

	*//**
	 * @return String
	 *//*
	public String getTransferTime()
	{
		return transferTime;
	}

	*//**
	 * @param action
	 *//*
	public void setAction(String action)
	{
		this.action = action;
	}

	*//**
	 * @param transactionLogonId
	 *//*
	public void setChangedByLogonId(String changedByLogonId)
	{
		this.changedByLogonId = changedByLogonId;
	}

	*//**
	 * @param changedByUserFirstName
	 *//*
	public void setChangedByUserFirstName(String changedByUserFirstName)
	{
		this.changedByUserFirstName = changedByUserFirstName;
	}

	*//**
	 * @param changedByUserLastName
	 *//*
	public void setChangedByUserLastName(String changedByUserLastName)
	{
		this.changedByUserLastName = changedByUserLastName;
	}
    *//**
     * @param changeTime The changeTime to set.
     *//*
    public void setChangeTime(String changeTime)
    {
        this.changeTime = changeTime;
    }

	*//**
	 * @param fieldName
	 *//*
	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	*//**
	 * @param logonId
	 *//*
	public void setLogonId(String logonId)
	{
		this.logonId = logonId;
	}

	*//**
	 * @param newValue
	 *//*
	public void setNewValue(String newValue)
	{
		this.newValue = newValue;
	}

	*//**
	 * @param oldValue
	 *//*
	public void setOldValue(String oldValue)
	{
		this.oldValue = oldValue;
	}

	*//**
	 * @param transaction
	 *//*
	public void setTransaction(String transaction)
	{
		this.transaction = transaction;
	}

	*//**
	 * @param transactionDate
	 *//*
	public void setTransactionDate(Date transactionDate)
	{
		this.transactionDate = transactionDate;
	}

	*//**
	 * @param transferDate
	 *//*
	public void setTransferDate(Date transferDate)
	{
		this.transferDate = transferDate;
	}

	*//**
	 * @param transferTime
	 *//*
	public void setTransferTime(String transferTime)
	{
		this.transferTime = transferTime;
	}
}*/
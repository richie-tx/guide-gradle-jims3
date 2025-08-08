package pd.juvenilecase.referral;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;
import mojo.km.messaging.IEvent;

/**
 * 
 * @roseuid 467FB9C3018F
 */
public class JUVFeeReceipt extends PersistentObject
{
	private String transactionNum;
	private String petitionNum;
	private String receiptNum;
	private Date paymentDate;

	/**
	 * 
	 * @roseuid 467FB9C3018F
	 */
	public JUVFeeReceipt()
	{
	}

	/**
	 * @return Returns the petitionNum.
	 */
	public String getPetitionNum()
	{
		fetch();
		return petitionNum;
	}

	/**
	 * @param petitionNum The petitionNum to set.
	 */
	public void setPetitionNum(String petitionNum)
	{
		this.petitionNum = petitionNum;
	}

	/**
	 * @return Returns the receiptNum.
	 */
	public String getReceiptNum()
	{
		fetch();
		return receiptNum;
	}

	/**
	 * @param receiptNum The receiptNum to set.
	 */
	public void setReceiptNum(String receiptNum)
	{
		this.receiptNum = receiptNum;
	}

	/**
	 * @return Returns the transactionNum.
	 */
	public String getTransactionNum()
	{
		fetch();
		return transactionNum;
	}

	/**
	 * @param transactionNum The transactionNum to set.
	 */
	public void setTransactionNum(String transactionNum)
	{
		this.transactionNum = transactionNum;
	}

	/**
	 * 
	 * @return iterator
	 * @roseuid 42A99B980107
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JUVFeeReceipt.class);
	}

	/**
	 * 
	 * @roseuid 4177C29D03A9
	 * @param departmentId
	 * @return java.util.Iterator
	 */
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator receipts = null;
		receipts = home.findAll(attrName, attrValue, JUVFeeReceipt.class);
		return receipts;
	}

	/**
	 * 
	 * @return Returns the paymentDate.
	 */
	public Date getPaymentDate()
	{
		fetch();
		return paymentDate;
	}

	/**
	 * 
	 * @param paymentDate The paymentDate to set.
	 */
	public void setPaymentDate(Date paymentDate)
	{
		this.paymentDate = paymentDate;
	}
}

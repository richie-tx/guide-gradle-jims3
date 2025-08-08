package pd.juvenilecase.referral;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.criminal.JuvenileFeeCode;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;
import pd.codetable.Code;
import mojo.km.messaging.IEvent;

/**
 * @roseuid 467FB98A03C0
 */
public class JUVFeePayment extends PersistentObject
{
	private String amountDue;
	private String amountPaid;
	private Date receivedDate;
	private Date dueDate;
	private Date paymentDate;
	private String payorId;
	private String transactionNum;
	private String petitionNum;
	private String revisionCode;
	/**
	 * Properties for juvBillingAddress
	 */
	private JUVBillingAddress juvBillingAddress = null;
	/**
	 * Properties for attorneyAddress
	 */
	private PIDAddress attorneyAddress = null;
	/**
	 * Properties for feeType
	 */
	private JuvenileFeeCode feeType = null;
	/**
	 * Properties for feeCode
	 */
	private JuvenileFeeCode feeCode = null;
	/**
	 * Properties for juvFeeReceipt
	 */
	private JUVFeeReceipt juvFeeReceipt = null;
	/**
	 * Properties for feeStatus
	 */
	private Code feeStatus = null;
	/**
	 * Properties for payorType
	 */
	private Code payorType = null;
	private String juvBillingAddressId;
	private String attorneyAddressId;
	private String feeTypeId;
	private String juvFeeReceiptId;
	private String feeStatusId;
	private String payorTypeId;
	private String feeCodeId;
	private String caseNum;

	/**
	 * @roseuid 467FB98A03C0
	 */
	public JUVFeePayment()
	{
	}

	/**
	 * @return Iterator JUVFeePayment
	 * @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JUVFeePayment.class);
	}

	/**
	 * 
	 * @return Returns the amountDue.
	 */
	public String getAmountDue()
	{
		fetch();
		return amountDue;
	}

	/**
	 * 
	 * @param amountDue The amountDue to set.
	 */
	public void setAmountDue(String amountDue)
	{
		this.amountDue = amountDue;
	}

	/**
	 * 
	 * @return Returns the amountPaid.
	 */
	public String getAmountPaid()
	{
		fetch();
		return amountPaid;
	}

	/**
	 * 
	 * @param amountPaid The amountPaid to set.
	 */
	public void setAmountPaid(String amountPaid)
	{
		this.amountPaid = amountPaid;
	}

	/**
	 * 
	 * @return Returns the attorneyAddress.
	 */
	public PIDAddress getAttorneyAddress()
	{
		initAttorneyAddress();
		return attorneyAddress;
	}


	/**
	 * 
	 * @return Returns the attorneyAddressId.
	 */
	public String getAttorneyAddressId()
	{
		fetch();
		return attorneyAddressId;
	}

	/**
	 * 
	 * @param attorneyAddressId The attorneyAddressId to set.
	 */
	public void setAttorneyAddressId(String attorneyAddressId)
	{
		this.attorneyAddressId = attorneyAddressId;
	}

	/**
	 * 
	 * @return Returns the caseNum.
	 */
	public String getCaseNum()
	{
		fetch();
		return caseNum;
	}

	/**
	 * 
	 * @param caseNum The caseNum to set.
	 */
	public void setCaseNum(String caseNum)
	{
		this.caseNum = caseNum;
	}

	/**
	 * 
	 * @return Returns the dueDate.
	 */
	public Date getDueDate()
	{
		fetch();
		return dueDate;
	}

	/**
	 * 
	 * @param dueDate The dueDate to set.
	 */
	public void setDueDate(Date dueDate)
	{
		this.dueDate = dueDate;
	}

	/**
	 * 
	 * @return Returns the feeCode.
	 */
	public JuvenileFeeCode getFeeCode()
	{
		initFeeCode();
		return feeCode;
	}

	

	/**
	 * 
	 * @return Returns the feeCodeId.
	 */
	public String getFeeCodeId()
	{
		fetch();
		return feeCodeId;
	}

	/**
	 * 
	 * @param feeCodeId The feeCodeId to set.
	 */
	public void setFeeCodeId(String feeCodeId)
	{
		this.feeCodeId = feeCodeId;
	}

	/**
	 * 
	 * @return Returns the feeStatus.
	 */
	public Code getFeeStatus()
	{
		initFeeStatus();
		return feeStatus;
	}

	
	/**
	 * 
	 * @return Returns the feeStatusId.
	 */
	public String getFeeStatusId()
	{
		fetch();
		return feeStatusId;
	}

	/**
	 * 
	 * @param feeStatusId The feeStatusId to set.
	 */
	public void setFeeStatusId(String feeStatusId)
	{
		this.feeStatusId = feeStatusId;
	}

	/**
	 * 
	 * @return Returns the feeType.
	 */
	public JuvenileFeeCode getFeeType()
	{
		initFeeType();
		return feeType;
	}

	

	/**
	 * 
	 * @return Returns the feeTypeId.
	 */
	public String getFeeTypeId()
	{
		fetch();
		return feeTypeId;
	}

	/**
	 * 
	 * @param feeTypeId The feeTypeId to set.
	 */
	public void setFeeTypeId(String feeTypeId)
	{
		this.feeTypeId = feeTypeId;
	}

	/**
	 * 
	 * @return Returns the juvBillingAddress.
	 */
	public JUVBillingAddress getJuvBillingAddress()
	{
		initJuvBillingAddress();
		return juvBillingAddress;
	}

	
	/**
	 * 
	 * @return Returns the juvBillingAddressId.
	 */
	public String getJuvBillingAddressId()
	{
		fetch();
		return juvBillingAddressId;
	}

	/**
	 * 
	 * @param juvBillingAddressId The juvBillingAddressId to set.
	 */
	public void setJuvBillingAddressId(String juvBillingAddressId)
	{
		this.juvBillingAddressId = juvBillingAddressId;
	}

	/**
	 * 
	 * @return Returns the juvFeeReceipt.
	 */
	public JUVFeeReceipt getJuvFeeReceipt()
	{
		initJuvFeeReceipt();
		return juvFeeReceipt;
	}

	/**
	 * 
	 * @return Returns the juvFeeReceiptId.
	 */
	public String getJuvFeeReceiptId()
	{
		fetch();
		return juvFeeReceiptId;
	}

	/**
	 * 
	 * @param juvFeeReceiptId The juvFeeReceiptId to set.
	 */
	public void setJuvFeeReceiptId(String juvFeeReceiptId)
	{
		this.juvFeeReceiptId = juvFeeReceiptId;
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

	/**
	 * 
	 * @return Returns the payorId.
	 */
	public String getPayorId()
	{
		fetch();
		return payorId;
	}

	/**
	 * 
	 * @param payorId The payorId to set.
	 */
	public void setPayorId(String payorId)
	{
		this.payorId = payorId;
	}

	/**
	 * 
	 * @return Returns the payorType.
	 */
	public Code getPayorType()
	{
		initPayorType();
		return payorType;
	}



	/**
	 * 
	 * @return Returns the payorTypeId.
	 */
	public String getPayorTypeId()
	{
		fetch();
		return payorTypeId;
	}

	/**
	 * 
	 * @param payorTypeId The payorTypeId to set.
	 */
	public void setPayorTypeId(String payorTypeId)
	{
		this.payorTypeId = payorTypeId;
	}

	/**
	 * 
	 * @return Returns the petitionNum.
	 */
	public String getPetitionNum()
	{
		fetch();
		return petitionNum;
	}

	/**
	 * 
	 * @param petitionNum The petitionNum to set.
	 */
	public void setPetitionNum(String petitionNum)
	{
		this.petitionNum = petitionNum;
	}

	/**
	 * 
	 * @return Returns the receivedDate.
	 */
	public Date getReceivedDate()
	{
		fetch();
		return receivedDate;
	}

	/**
	 * 
	 * @param receivedDate The receivedDate to set.
	 */
	public void setReceivedDate(Date receivedDate)
	{
		this.receivedDate = receivedDate;
	}

	/**
	 * 
	 * @return Returns the revisionCode.
	 */
	public String getRevisionCode()
	{
		fetch();
		return revisionCode;
	}

	/**
	 * 
	 * @param revisionCode The revisionCode to set.
	 */
	public void setRevisionCode(String revisionCode)
	{
		this.revisionCode = revisionCode;
	}

	/**
	 * 
	 * @return Returns the transactionNum.
	 */
	public String getTransactionNum()
	{
		fetch();
		return transactionNum;
	}

	/**
	 * 
	 * @param transactionNum The transactionNum to set.
	 */
	public void setTransactionNum(String transactionNum)
	{
		this.transactionNum = transactionNum;
	}

	/**
	 * Initialize class relationship to class pd.juvenilecase.referral.JUVBillingAddress
	 */
	private void initJuvBillingAddress()
	{
		if (juvBillingAddress == null)
		{
			juvBillingAddress = (JUVBillingAddress) new mojo.km.persistence.Reference(
					juvBillingAddressId, JUVBillingAddress.class).getObject();
		}
	}

	/**
	 * set the type reference for class member juvBillingAddress
	 */
	public void setJuvBillingAddress(JUVBillingAddress juvBillingAddress)
	{
		setJuvBillingAddressId("" + juvBillingAddress.getOID());
		this.juvBillingAddress = (JUVBillingAddress) new mojo.km.persistence.Reference(
				juvBillingAddress).getObject();
	}

	/**
	 * Initialize class relationship to class pd.juvenilecase.referral.PIDAddress
	 */
	private void initAttorneyAddress()
	{
		if (attorneyAddress == null)
		{
			attorneyAddress = (PIDAddress) new mojo.km.persistence.Reference(
					attorneyAddressId, PIDAddress.class).getObject();
		}
	}

	/**
	 * set the type reference for class member attorneyAddress
	 */
	public void setAttorneyAddress(PIDAddress attorneyAddress)
	{
		setAttorneyAddressId("" + attorneyAddress.getOID());
		this.attorneyAddress = (PIDAddress) new mojo.km.persistence.Reference(attorneyAddress)
				.getObject();
	}

	/**
	 * Initialize class relationship to class pd.codetable.criminal.JuvenileFeeCode
	 */
	private void initFeeType()
	{
		if (feeType == null)
		{
			feeType = (JuvenileFeeCode) new mojo.km.persistence.Reference(feeTypeId,
					JuvenileFeeCode.class).getObject();
		}
	}

	/**
	 * set the type reference for class member feeType
	 */
	public void setFeeType(JuvenileFeeCode feeType)
	{
		setFeeTypeId("" + feeType.getOID());
		this.feeType = (JuvenileFeeCode) new mojo.km.persistence.Reference(feeType).getObject();
	}

	/**
	 * Initialize class relationship to class pd.codetable.criminal.JuvenileFeeCode
	 */
	private void initFeeCode()
	{
		if (feeCode == null)
		{
			feeCode = (JuvenileFeeCode) new mojo.km.persistence.Reference(feeCodeId,
					JuvenileFeeCode.class).getObject();
		}
	}

	/**
	 * set the type reference for class member feeCode
	 */
	public void setFeeCode(JuvenileFeeCode feeCode)
	{
		setFeeCodeId("" + feeCode.getOID());
		this.feeCode = (JuvenileFeeCode) new mojo.km.persistence.Reference(feeCode).getObject();
	}

	/**
	 * Initialize class relationship to class pd.juvenilecase.referral.JUVFeeReceipt
	 */
	private void initJuvFeeReceipt()
	{
		if (juvFeeReceipt == null)
		{
			juvFeeReceipt = (JUVFeeReceipt) new mojo.km.persistence.Reference(juvFeeReceiptId,
					JUVFeeReceipt.class).getObject();
		}
	}

	/**
	 * set the type reference for class member juvFeeReceipt
	 */
	public void setJuvFeeReceipt(JUVFeeReceipt juvFeeReceipt)
	{
		setJuvFeeReceiptId("" + juvFeeReceipt.getOID());
		this.juvFeeReceipt = (JUVFeeReceipt) new mojo.km.persistence.Reference(juvFeeReceipt)
				.getObject();
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initFeeStatus()
	{
		if (feeStatus == null)
		{
			feeStatus = (Code) new mojo.km.persistence.Reference(feeStatusId, Code.class)
					.getObject();
		}
	}

	/**
	 * set the type reference for class member feeStatus
	 */
	public void setFeeStatus(Code feeStatus)
	{
		setFeeStatusId("" + feeStatus.getOID());
		this.feeStatus = (Code) new mojo.km.persistence.Reference(feeStatus).getObject();
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initPayorType()
	{
		if (payorType == null)
		{
			payorType = (Code) new mojo.km.persistence.Reference(payorTypeId, Code.class)
					.getObject();
		}
	}

	/**
	 * set the type reference for class member payorType
	 */
	public void setPayorType(Code payorType)
	{
		setPayorTypeId("" + payorType.getOID());
		this.payorType = (Code) new mojo.km.persistence.Reference(payorType).getObject();
	}
}

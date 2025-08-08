package pd.juvenile;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
 * 
 * @author bschwartz
 */
public class JuvenileBenefit extends PersistentObject
{
	private String juvenileNum;

	private String eligibilityTypeId;

	/**
	 * @detailerDoNotGenerate false
	 * @referencedType pd.codetable.Code
	 * @contextKey MEMBER_BENEFIT_ELIGIBILITY
	 */
	private Code eligibilityType = null;

	private boolean receivingBenefits;

	private boolean eligibleForBenefits;

	//added for US 27022
	private String receivedByFirstName;
	
	private String receivedByMiddleName;
	
	private String receivedByLastName;
		
	private int receivedAmt;
	
	private String idNumber;
	
	private String benefitStatus;
	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setEligibilityTypeId(String eligibilityTypeId)
	{
		if (this.eligibilityTypeId == null || !this.eligibilityTypeId.equals(eligibilityTypeId))
		{
			markModified();
		}
		eligibilityType = null;
		this.eligibilityTypeId = eligibilityTypeId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getEligibilityTypeId()
	{
		fetch();
		return eligibilityTypeId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initEligibilityType()
	{
		if (eligibilityType == null)
		{
			eligibilityType = (Code) new mojo.km.persistence.Reference(eligibilityTypeId,
					Code.class, "MEMBER_BENEFIT_ELIGIBILITY").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getEligibilityType()
	{
		fetch();
		initEligibilityType();
		return eligibilityType;
	}

	/**
	 * set the type reference for class member eligibilityType
	 */
	public void setEligibilityType(Code eligibilityType)
	{
		if (this.eligibilityType == null || !this.eligibilityType.equals(eligibilityType))
		{
			markModified();
		}
		if (eligibilityType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(eligibilityType);
		}
		setEligibilityTypeId("" + eligibilityType.getOID());
		eligibilityType.setContext("MEMBER_BENEFIT_ELIGIBILITY");
		this.eligibilityType = (Code) new mojo.km.persistence.Reference(eligibilityType).getObject();
	}

	/**
	 * Set the reference value to class :: pd.juvenilecase.family.FamilyMember
	 */
	public void setJuvenileNum(String aJuvenileNum)
	{
		if (this.juvenileNum == null || !this.juvenileNum.equals(aJuvenileNum))
		{
			markModified();
		}
		this.juvenileNum = aJuvenileNum;
	}

	/**
	 * Get the reference value to class :: pd.juvenilecase.family.FamilyMember
	 */
	public String getJuvenileNum()
	{
		fetch();
		return juvenileNum;
	}

	/**
	 * @return
	 */
	public boolean isEligibleForBenefits()
	{
		fetch();
		return eligibleForBenefits;
	}

	/**
	 * @return
	 */
	public boolean isReceivingBenefits()
	{
		fetch();
		return receivingBenefits;
	}

	/**
	 * @param b
	 */
	public void setEligibleForBenefits(boolean b)
	{
		if (this.eligibleForBenefits != b)
		{
			markModified();
		}
		eligibleForBenefits = b;
	}

	/**
	 * @param b
	 */
	public void setReceivingBenefits(boolean b)
	{
		if (this.receivingBenefits != b)
		{
			markModified();
		}
		receivingBenefits = b;
	}

	/**
	 * Finds all benefits by an attribute value
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator benefits = home.findAll(attributeName, attributeValue, JuvenileBenefit.class);
		return benefits;
	}

	/**
	 * @return the receivedByFirstName
	 */
	public String getReceivedByFirstName() {
		return receivedByFirstName;
	}

	/**
	 * @param receivedByFirstName the receivedByFirstName to set
	 */
	public void setReceivedByFirstName(String receivedByFirstName) {
	    if (this.receivedByFirstName == null || !this.receivedByFirstName.equals(receivedByFirstName))
		{
			markModified();
		}
		this.receivedByFirstName = receivedByFirstName;
	}

	/**
	 * @return the receivedByMiddleName
	 */
	public String getReceivedByMiddleName() {
		return receivedByMiddleName;
	}

	/**
	 * @param receivedByMiddleName the receivedByMiddleName to set
	 */
	public void setReceivedByMiddleName(String receivedByMiddleName) {
	    if (this.receivedByMiddleName == null || !this.receivedByMiddleName.equals(receivedByMiddleName))
		{
			markModified();
		}
		this.receivedByMiddleName = receivedByMiddleName;
	}

	/**
	 * @return the receivedByLastName
	 */
	public String getReceivedByLastName() {
		return receivedByLastName;
	}

	/**
	 * @param receivedByLastName the receivedByLastName to set
	 */
	public void setReceivedByLastName(String receivedByLastName) {
	    if (this.receivedByLastName == null || !this.receivedByLastName.equals(receivedByLastName))
		{
			markModified();
		}
		this.receivedByLastName = receivedByLastName;
	}

	/**
	 * @return the receivedAmt
	 */
	public int getReceivedAmt() {
		return receivedAmt;
	}

	/**
	 * @param receivedAmt the receivedAmt to set
	 */
	public void setReceivedAmt(int receivedAmt) {
	    if (this.receivedAmt != receivedAmt)
		{
			markModified();
		}
		this.receivedAmt = receivedAmt;
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
	    if (this.idNumber == null || !this.idNumber.equals(idNumber))
		{
			markModified();
		}
		this.idNumber = idNumber;
	}

	public String getBenefitStatus() {
		return benefitStatus;
	}

	public void setBenefitStatus(String aBenefitStatus) {
		if (this.benefitStatus == null || !this.benefitStatus.equals(aBenefitStatus))
		{
			markModified();
		}
		this.benefitStatus = aBenefitStatus;
	}
	
	/**
	 * @return JuvenileBenefit
	 * @param benefitId
	 */
	static public JuvenileBenefit find(String benefitId)
	{
		IHome home = new Home();
		JuvenileBenefit benefit = (JuvenileBenefit) home.find(benefitId, JuvenileBenefit.class);
		return benefit;
	}
}

package pd.juvenilecase.family;

import java.util.Iterator;


import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.Code;
import pd.juvenilecase.family.*;

/**
* @author athorat
To change the template for this generated type comment go to
Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
public class FamilyMemberBenefit extends mojo.km.persistence.PersistentObject {
	private String eligibilityTypeId;
	/**
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey MEMBER_BENEFIT_ELIGIBILITY
	*/
	private Code eligibilityType = null;
	private boolean receivingBenefits;
	private String familyMemberId;
	/**
	* Properties for familyMember
	* @detailerDoNotGenerate false
	* @referencedType pd.juvenilecase.family.FamilyMember
	*/
	private FamilyMember familyMember = null;
	private boolean eligibleForBenefits;
	
	//added for US 27022
	private String receivedByFirstName;
	
	private String receivedByMiddleName;
	
	private String receivedByLastName;
		
	private int receivedAmt;
	
	private String idNumber;
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setEligibilityTypeId(String eligibilityTypeId) {
		if (this.eligibilityTypeId == null || !this.eligibilityTypeId.equals(eligibilityTypeId)) {
			markModified();
		}
		eligibilityType = null;
		this.eligibilityTypeId = eligibilityTypeId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getEligibilityTypeId() {
		fetch();
		return eligibilityTypeId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initEligibilityType() {
		if (eligibilityType == null) {
			eligibilityType =
				(Code) new mojo.km.persistence.Reference(eligibilityTypeId, Code.class, "MEMBER_BENEFIT_ELIGIBILITY").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getEligibilityType() {
		fetch();
		initEligibilityType();
		return eligibilityType;
	}
	/**
	* set the type reference for class member eligibilityType
	*/
	public void setEligibilityType(Code eligibilityType) {
		if (this.eligibilityType == null || !this.eligibilityType.equals(eligibilityType)) {
			markModified();
		}
		if (eligibilityType.getOID() == null) {
			new mojo.km.persistence.Home().bind(eligibilityType);
		}
		setEligibilityTypeId("" + eligibilityType.getOID());
		eligibilityType.setContext("MEMBER_BENEFIT_ELIGIBILITY");
		this.eligibilityType = (Code) new mojo.km.persistence.Reference(eligibilityType).getObject();
	}
	/**
	* Set the reference value to class :: pd.juvenilecase.family.FamilyMember
	*/
	public void setFamilyMemberId(String familyMemberId) {
		if (this.familyMemberId == null || !this.familyMemberId.equals(familyMemberId)) {
			markModified();
		}
		familyMember = null;
		this.familyMemberId = familyMemberId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.family.FamilyMember
	*/
	public String getFamilyMemberId() {
		fetch();
		return familyMemberId;
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.family.FamilyMember
	*/
	private void initFamilyMember() {
		if (familyMember == null) {
			familyMember = (FamilyMember) new mojo.km.persistence.Reference(familyMemberId, FamilyMember.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.juvenilecase.family.FamilyMember
	*/
	public FamilyMember getFamilyMember() {
		fetch();
		initFamilyMember();
		return familyMember;
	}
	/**
	* set the type reference for class member familyMember
	*/
	public void setFamilyMember(FamilyMember familyMember) {
		if (this.familyMember == null || !this.familyMember.equals(familyMember)) {
			markModified();
		}
		if (familyMember.getOID() == null) {
			new mojo.km.persistence.Home().bind(familyMember);
		}
		setFamilyMemberId("" + familyMember.getOID());
		this.familyMember = (FamilyMember) new mojo.km.persistence.Reference(familyMember).getObject();
	}
	/**
	* @return 
	*/
	public boolean isEligibleForBenefits() {
		fetch();
		return eligibleForBenefits;
	}
	/**
	* @return 
	*/
	public boolean isReceivingBenefits() {
		fetch();
		return receivingBenefits;
	}
	/**
	* @param b
	*/
	public void setEligibleForBenefits(boolean b) {
		if (this.eligibleForBenefits != b) {
			markModified();
		}
		eligibleForBenefits = b;
	}
	/**
	* @param b
	*/
	public void setReceivingBenefits(boolean b) {
		if (this.receivingBenefits != b) {
			markModified();
		}
		receivingBenefits = b;
	}

	/**
	* Finds all benefits by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator benefits = home.findAll(attributeName, attributeValue, FamilyMemberBenefit.class);
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
		this.idNumber = idNumber;
	}
}

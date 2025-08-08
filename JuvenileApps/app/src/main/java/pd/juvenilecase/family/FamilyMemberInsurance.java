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
public class FamilyMemberInsurance extends mojo.km.persistence.PersistentObject {
	/**
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate false
	* @contextKey INSURANCE_TYPE
	*/
	private Code insuranceType = null;
	private String familyMemberId;
	/**
	* Properties for familyMember
	* @detailerDoNotGenerate false
	* @referencedType pd.juvenilecase.family.FamilyMember
	*/
	private FamilyMember familyMember = null;
	private String insuranceTypeId;
	private String carrier;
	private String policyNum;
	/**
	* @return 
	*/
	public String getCarrier() {
		fetch();
		return carrier;
	}
	/**
	* @return 
	*/
	public String getPolicyNum() {
		fetch();
		return policyNum;
	}
	/**
	* @param string
	*/
	public void setCarrier(String string) {
		if (this.carrier == null || !this.carrier.equals(string)) {
			markModified();
		}
		carrier = string;
	}
	/**
	* @param string
	*/
	public void setPolicyNum(String string) {
		if (this.policyNum == null || !this.policyNum.equals(string)) {
			markModified();
		}
		policyNum = string;
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setInsuranceTypeId(String insuranceTypeId) {
		if (this.insuranceTypeId == null || !this.insuranceTypeId.equals(insuranceTypeId)) {
			markModified();
		}
		insuranceType = null;
		this.insuranceTypeId = insuranceTypeId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getInsuranceTypeId() {
		fetch();
		return insuranceTypeId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initInsuranceType() {
		if (insuranceType == null) {
			insuranceType = (Code) new mojo.km.persistence.Reference(insuranceTypeId, Code.class, "INSURANCE_TYPE").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getInsuranceType() {
		initInsuranceType();
		return insuranceType;
	}
	/**
	* set the type reference for class member insuranceType
	*/
	public void setInsuranceType(Code insuranceType) {
		if (this.insuranceType == null || !this.insuranceType.equals(insuranceType)) {
			markModified();
		}
		if (insuranceType.getOID() == null) {
			new mojo.km.persistence.Home().bind(insuranceType);
		}
		setInsuranceTypeId("" + insuranceType.getOID());
		insuranceType.setContext("INSURANCE_TYPE");
		this.insuranceType = (Code) new mojo.km.persistence.Reference(insuranceType).getObject();
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
	* Finds all insurances by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator insurances = home.findAll(attributeName, attributeValue, FamilyMemberInsurance.class);
		return insurances;
	}

}

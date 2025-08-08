package pd.juvenilecase;

import naming.PDCodeTableConstants;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.address.Address;
import pd.codetable.Code;
import pd.codetable.person.SocialElementCode;
import pd.contact.Employer;

/**
* @author dgibler
*/
public class JJSFamily extends PersistentObject
{
	/**
	* Properties for specialAssignment
	* @detailerDoNotGenerate true
	* @referencedType pd.codetable.Code
	* @contextKey SPECIAL_ASSIGNMENT
	*/
	private Code specialAssignment = null;
	private String parentMaritalStatusId;
	private String socialCodeId;
	private String livesWithId;
	private String schoolProgramId;
	/**
	* Properties for motherAddress
	* @useParent true
	* @referencedType pd.address.Address
	*/
	private Address motherAddress = null;
	private String supplementalIncome;
	private String juvenileNum;
	private String schoolStatusId;
	private String motherFirstName;
	/**
	* Properties for parentEmployer
	*/
	private Employer parentEmployer = null;
	private String fatherPhone;
	private String fatherSsn;
	private String schoolGrade;
	private String fatherFirstName;
	private String specialAssignmentId;
	/**
	* Properties for livesWith
	*/
	private Code livesWith = null;
	private String juvenileEmployerId;
	private String motherMiddleName;
	/**
	* Properties for schoolProgram
	*/
	private Code schoolProgram = null;
	private String otherLastName;
	private String otherMiddleName;
	/**
	* Properties for schoolStatus
	*/
	private Code schoolStatus = null;
	/**
	* Properties for fatherAddress
	* @useParent true
	* @referencedType pd.address.Address
	*/
	private Address fatherAddress = null;
	private String numberInHome;
	/**
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey MARITAL_STATUS
	*/
	private Code parentMaritalStatus = null;
	private String relationshipId;
	/**
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.person.SocialElementCode
	* @contextKey pd.codetable.person.SocialElementCode
	*/
	private SocialElementCode religion = null;
	/**
	* Properties for otherAddress
	* @useParent true
	* @referencedType pd.address.Address
	*/
	private Address otherAddress = null;
	private String religionId;
	private String fatherAddressId;
	private String motherLastName;
	private String otherFirstName;
	private String otherAddressId;
	/**
	* Properties for relationship
	* @detailerDoNotGenerate true
	* @referencedType pd.codetable.person.SocialElementCode
	*/
	private Code relationship = null;
	private String parentEmployerId;
	private String fatherMiddleName;
	private String motherAddressId;
	private String otherPhone;
	private String otherSsn;
	/**
	* Properties for juvenileEmployer
	*/
	private Employer juvenileEmployer = null;
	private String motherPhone;
	private String motherSsn;
	/**
	* Properties for socialCode
	*/
	private Code socialCode = null;
	private String fatherLastName;
	/**
	* @roseuid 42777D6F017D
	*/
	public JJSFamily()
	{
	}

	/**
	* @return Juvenile.
	* @return pd.juvenilecase.Juvenile
	* @param juvNum. juvenile number is the unique primary key of this table.
	* @param juvNum
	* @roseuid 42A882800158
	*/
	static public JJSFamily find(String juvNum)
	{
		IHome home = new Home();
		JJSFamily juvenileFamily = (JJSFamily) home.find(juvNum, JJSFamily.class);
		return juvenileFamily;
	}

	/**
	* Set the reference value to class :: pd.address.Address
	*/
	public void setMotherAddressId(String motherAddressId)
	{
		this.motherAddressId = motherAddressId;
	}
	/**
	* Get the reference value to class :: pd.address.Address
	*/
	public String getMotherAddressId()
	{
		fetch();
		return motherAddressId;
	}
	/**
	* Initialize class relationship to class pd.address.Address
	*/
	private void initMotherAddress()
	{
		if (motherAddress == null)
		{
			motherAddress =
				(Address) new mojo
					.km
					.persistence
					.Reference(
						motherAddressId,
						Address.class,
						(mojo.km.persistence.PersistentObject) this,
						"motherAddress")
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.address.Address
	*/
	public Address getMotherAddress()
	{
		initMotherAddress();
		return motherAddress;
	}
	/**
	* set the type reference for class member motherAddress
	*/
	public void setMotherAddress(Address motherAddress)
	{
		setMotherAddressId("" + motherAddress.getOID());
		motherAddress.setContext(this, "motherAddress");
		this.motherAddress = (Address) new mojo.km.persistence.Reference(motherAddress).getObject();
	}
	/**
	* Set the reference value to class :: pd.contact.Employer
	*/
	public void setParentEmployerId(String parentEmployerId)
	{
		this.parentEmployerId = parentEmployerId;
	}
	/**
	* Get the reference value to class :: pd.contact.Employer
	*/
	public String getParentEmployerId()
	{
		fetch();
		return parentEmployerId;
	}
	/**
	* Initialize class relationship to class pd.contact.Employer
	*/
	private void initParentEmployer()
	{
		if (parentEmployer == null)
		{
			parentEmployer =
				(Employer) new mojo
					.km
					.persistence
					.Reference(parentEmployerId, Employer.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.contact.Employer
	*/
	public Employer getParentEmployer()
	{
		initParentEmployer();
		return parentEmployer;
	}
	/**
	* set the type reference for class member parentEmployer
	*/
	public void setParentEmployer(Employer parentEmployer)
	{
		setParentEmployerId("" + parentEmployer.getOID());
		this.parentEmployer = (Employer) new mojo.km.persistence.Reference(parentEmployer).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setLivesWithId(String livesWithId)
	{
		this.livesWithId = livesWithId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getLivesWithId()
	{
		fetch();
		return livesWithId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initLivesWith()
	{
		if (livesWith == null)
		{
			livesWith =
				(Code) new mojo.km.persistence.Reference(livesWithId, Code.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getLivesWith()
	{
		initLivesWith();
		return livesWith;
	}
	/**
	* set the type reference for class member livesWith
	*/
	public void setLivesWith(Code livesWith)
	{
		setLivesWithId("" + livesWith.getOID());
		this.livesWith = (Code) new mojo.km.persistence.Reference(livesWith).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setSchoolProgramId(String schoolProgramId)
	{
		this.schoolProgramId = schoolProgramId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getSchoolProgramId()
	{
		fetch();
		return schoolProgramId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initSchoolProgram()
	{
		if (schoolProgram == null)
		{
			schoolProgram =
				(Code) new mojo
					.km
					.persistence
					.Reference(schoolProgramId, Code.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getSchoolProgram()
	{
		initSchoolProgram();
		return schoolProgram;
	}
	/**
	* set the type reference for class member schoolProgram
	*/
	public void setSchoolProgram(Code schoolProgram)
	{
		setSchoolProgramId("" + schoolProgram.getOID());
		this.schoolProgram = (Code) new mojo.km.persistence.Reference(schoolProgram).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setSchoolStatusId(String schoolStatusId)
	{
		this.schoolStatusId = schoolStatusId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getSchoolStatusId()
	{
		fetch();
		return schoolStatusId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initSchoolStatus()
	{
		if (schoolStatus == null)
		{
			schoolStatus =
				(Code) new mojo
					.km
					.persistence
					.Reference(schoolStatusId, Code.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getSchoolStatus()
	{
		initSchoolStatus();
		return schoolStatus;
	}
	/**
	* set the type reference for class member schoolStatus
	*/
	public void setSchoolStatus(Code schoolStatus)
	{
		setSchoolStatusId("" + schoolStatus.getOID());
		this.schoolStatus = (Code) new mojo.km.persistence.Reference(schoolStatus).getObject();
	}
	/**
	* Set the reference value to class :: pd.address.Address
	*/
	public void setFatherAddressId(String fatherAddressId)
	{
		this.fatherAddressId = fatherAddressId;
	}
	/**
	* Get the reference value to class :: pd.address.Address
	*/
	public String getFatherAddressId()
	{
		fetch();
		return fatherAddressId;
	}
	/**
	* Initialize class relationship to class pd.address.Address
	*/
	private void initFatherAddress()
	{
		if (fatherAddress == null)
		{
			fatherAddress =
				(Address) new mojo
					.km
					.persistence
					.Reference(
						fatherAddressId,
						Address.class,
						(mojo.km.persistence.PersistentObject) this,
						"fatherAddress")
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.address.Address
	*/
	public Address getFatherAddress()
	{
		initFatherAddress();
		return fatherAddress;
	}
	/**
	* set the type reference for class member fatherAddress
	*/
	public void setFatherAddress(Address fatherAddress)
	{
		setFatherAddressId("" + fatherAddress.getOID());
		fatherAddress.setContext(this, "fatherAddress");
		this.fatherAddress = (Address) new mojo.km.persistence.Reference(fatherAddress).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setParentMaritalStatusId(String parentMaritalStatusId)
	{
		this.parentMaritalStatusId = parentMaritalStatusId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getParentMaritalStatusId()
	{
		fetch();
		return parentMaritalStatusId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initParentMaritalStatus()
	{
		if (parentMaritalStatus == null)
		{
			parentMaritalStatus =
				(Code) new mojo
					.km
					.persistence
					.Reference(parentMaritalStatusId, Code.class, PDCodeTableConstants.MARITAL_STATUS)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getParentMaritalStatus()
	{
		initParentMaritalStatus();
		return parentMaritalStatus;
	}
	/**
	* set the type reference for class member parentMaritalStatus
	*/
	public void setParentMaritalStatus(Code parentMaritalStatus)
	{
		setParentMaritalStatusId("" + parentMaritalStatus.getOID());
		parentMaritalStatus.setContext(PDCodeTableConstants.MARITAL_STATUS);
		this.parentMaritalStatus =
			(Code) new mojo.km.persistence.Reference(parentMaritalStatus).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.person.SocialElementCode
	*/
	public void setReligionId(String religionId)
	{
		this.religionId = religionId;
	}
	/**
	* Get the reference value to class :: pd.codetable.person.SocialElementCode
	*/
	public String getReligionId()
	{
		fetch();
		return religionId;
	}
	/**
	* Initialize class relationship to class pd.codetable.person.SocialElementCode
	*/
	private void initReligion()
	{
		if (religion == null)
		{
			religion =
				(SocialElementCode) new mojo
					.km
					.persistence
					.Reference(
						religionId,
						SocialElementCode.class,
						"pd.codetable.person.SocialElementCode")
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.person.SocialElementCode
	*/
	public SocialElementCode getReligion()
	{
		initReligion();
		return religion;
	}
	/**
	* set the type reference for class member religion
	*/
	public void setReligion(SocialElementCode religion)
	{
		setReligionId("" + religion.getOID());
		religion.setContext("pd.codetable.person.SocialElementCode");
		this.religion = (SocialElementCode) new mojo.km.persistence.Reference(religion).getObject();
	}
	/**
	* Set the reference value to class :: pd.address.Address
	*/
	public void setOtherAddressId(String otherAddressId)
	{
		this.otherAddressId = otherAddressId;
	}
	/**
	* Get the reference value to class :: pd.address.Address
	*/
	public String getOtherAddressId()
	{
		fetch();
		return otherAddressId;
	}
	/**
	* Initialize class relationship to class pd.address.Address
	*/
	private void initOtherAddress()
	{
		if (otherAddress == null)
		{
			otherAddress =
				(Address) new mojo
					.km
					.persistence
					.Reference(
						otherAddressId,
						Address.class,
						(mojo.km.persistence.PersistentObject) this,
						"otherAddress")
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.address.Address
	*/
	public Address getOtherAddress()
	{
		initOtherAddress();
		return otherAddress;
	}
	/**
	* set the type reference for class member otherAddress
	*/
	public void setOtherAddress(Address otherAddress)
	{
		setOtherAddressId("" + otherAddress.getOID());
		otherAddress.setContext(this, "otherAddress");
		this.otherAddress = (Address) new mojo.km.persistence.Reference(otherAddress).getObject();
	}
	/**
	* Set the reference value to class :: pd.contact.Employer
	*/
	public void setJuvenileEmployerId(String juvenileEmployerId)
	{
		this.juvenileEmployerId = juvenileEmployerId;
	}
	/**
	* Get the reference value to class :: pd.contact.Employer
	*/
	public String getJuvenileEmployerId()
	{
		fetch();
		return juvenileEmployerId;
	}
	/**
	* Initialize class relationship to class pd.contact.Employer
	*/
	private void initJuvenileEmployer()
	{
		if (juvenileEmployer == null)
		{
			juvenileEmployer =
				(Employer) new mojo
					.km
					.persistence
					.Reference(juvenileEmployerId, Employer.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.contact.Employer
	*/
	public Employer getJuvenileEmployer()
	{
		initJuvenileEmployer();
		return juvenileEmployer;
	}
	/**
	* set the type reference for class member juvenileEmployer
	*/
	public void setJuvenileEmployer(Employer juvenileEmployer)
	{
		setJuvenileEmployerId("" + juvenileEmployer.getOID());
		this.juvenileEmployer = (Employer) new mojo.km.persistence.Reference(juvenileEmployer).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setSocialCodeId(String socialCodeId)
	{
		this.socialCodeId = socialCodeId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getSocialCodeId()
	{
		fetch();
		return socialCodeId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initSocialCode()
	{
		if (socialCode == null)
		{
			socialCode =
				(Code) new mojo
					.km
					.persistence
					.Reference(socialCodeId, Code.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getSocialCode()
	{
		initSocialCode();
		return socialCode;
	}
	/**
	* set the type reference for class member socialCode
	*/
	public void setSocialCode(Code socialCode)
	{
		setSocialCodeId("" + socialCode.getOID());
		this.socialCode = (Code) new mojo.km.persistence.Reference(socialCode).getObject();
	}
	/**
	 * @return
	 */
	public String getFatherFirstName()
	{
		return fatherFirstName;
	}

	/**
	 * @return
	 */
	public String getFatherLastName()
	{
		return fatherLastName;
	}

	/**
	 * @return
	 */
	public String getFatherMiddleName()
	{
		return fatherMiddleName;
	}

	/**
	 * @return
	 */
	public String getFatherPhone()
	{
		return fatherPhone;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return
	 */
	public String getMotherFirstName()
	{
		return motherFirstName;
	}

	/**
	 * @return
	 */
	public String getMotherLastName()
	{
		return motherLastName;
	}

	/**
	 * @return
	 */
	public String getMotherMiddleName()
	{
		return motherMiddleName;
	}

	/**
	 * @return
	 */
	public String getMotherPhone()
	{
		return motherPhone;
	}

	/**
	 * @return
	 */
	public String getNumberInHome()
	{
		return numberInHome;
	}

	/**
	 * @return
	 */
	public String getOtherFirstName()
	{
		return otherFirstName;
	}

	/**
	 * @return
	 */
	public String getOtherLastName()
	{
		return otherLastName;
	}

	/**
	 * @return
	 */
	public String getOtherMiddleName()
	{
		return otherMiddleName;
	}

	/**
	 * @return
	 */
	public String getOtherPhone()
	{
		return otherPhone;
	}

	/**
	 * @return
	 */
	public Code getRelationship()
	{
		return relationship;
	}

	/**
	 * @return
	 */
	public String getRelationshipId()
	{
		return relationshipId;
	}

	/**
	 * @return
	 */
	public String getSchoolGrade()
	{
		return schoolGrade;
	}

	/**
	 * @return
	 */
	public Code getSpecialAssignment()
	{
		return specialAssignment;
	}

	/**
	 * @return
	 */
	public String getSpecialAssignmentId()
	{
		return specialAssignmentId;
	}

	/**
	 * @return
	 */
	public String getSupplementalIncome()
	{
		return supplementalIncome;
	}

	/**
	 * @param string
	 */
	public void setFatherFirstName(String string)
	{
		fatherFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setFatherLastName(String string)
	{
		fatherLastName = string;
	}

	/**
	 * @param string
	 */
	public void setFatherMiddleName(String string)
	{
		fatherMiddleName = string;
	}

	/**
	 * @param string
	 */
	public void setFatherPhone(String string)
	{
		fatherPhone = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param string
	 */
	public void setMotherFirstName(String string)
	{
		motherFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setMotherLastName(String string)
	{
		motherLastName = string;
	}

	/**
	 * @param string
	 */
	public void setMotherMiddleName(String string)
	{
		motherMiddleName = string;
	}

	/**
	 * @param string
	 */
	public void setMotherPhone(String string)
	{
		motherPhone = string;
	}

	/**
	 * @param string
	 */
	public void setNumberInHome(String string)
	{
		numberInHome = string;
	}

	/**
	 * @param string
	 */
	public void setOtherFirstName(String string)
	{
		otherFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setOtherLastName(String string)
	{
		otherLastName = string;
	}

	/**
	 * @param string
	 */
	public void setOtherMiddleName(String string)
	{
		otherMiddleName = string;
	}

	/**
	 * @param string
	 */
	public void setOtherPhone(String string)
	{
		otherPhone = string;
	}

	/**
	 * @param code
	 */
	public void setRelationship(Code code)
	{
		relationship = code;
	}

	/**
	 * @param string
	 */
	public void setRelationshipId(String string)
	{
		relationshipId = string;
	}

	/**
	 * @param string
	 */
	public void setSchoolGrade(String string)
	{
		schoolGrade = string;
	}

	/**
	 * @param code
	 */
	public void setSpecialAssignment(Code code)
	{
		specialAssignment = code;
	}

	/**
	 * @param string
	 */
	public void setSpecialAssignmentId(String string)
	{
		specialAssignmentId = string;
	}

	/**
	 * @param string
	 */
	public void setSupplementalIncome(String string)
	{
		supplementalIncome = string;
	}

	/**
	 * @return fatherSsn
	 */
	public String getFatherSsn()
	{
		return fatherSsn;
	}

	/**
	 * @return motherSsn
	 */
	public String getMotherSsn()
	{
		return motherSsn;
	}

	/**
	 * @return otherSsn
	 */
	public String getOtherSsn()
	{
		return otherSsn;
	}

	/**
	 * @param string
	 */
	public void setFatherSsn(String fatherSsn)
	{
		this.fatherSsn = fatherSsn;
	}

	/**
	 * @param string
	 */
	public void setMotherSsn(String motherSsn)
	{
		this.motherSsn = motherSsn;
	}

	/**
	 * @param string
	 */
	public void setOtherSsn(String otherSsn)
	{
		this.otherSsn = otherSsn;
	}

}

package pd.juvenilecase.family;

import java.util.Iterator;

import naming.PDCodeTableConstants;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.Code;

/**
* @author athorat
To change the template for this generated type comment go to
Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
public class FamilyMemberEmployment extends mojo.km.persistence.PersistentObject {
	private String currentEmployer;
	private String salaryRateId;
	private double  annualNetIncome;
	
	/**
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate false
	* @contextKey EMPLOYMENT_STATUS
	*/
	private Code employmentStatus = null;
	private String employmentStatusId;
	/**
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate false
	* @contextKey SALARY_RATE
	*/
	private Code salaryRate = null;
	private double workHours;
	private String jobTitle;
	private String familyMemberId;
	/**
	* Properties for familyMember
	* @detailerDoNotGenerate false
	* @referencedType pd.juvenilecase.family.FamilyMember
	*/
	private FamilyMember familyMember = null;
	private String lenghtOfEmployment;
	private double salary;
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setEmploymentStatusId(String employmentStatusId) {
		if (this.employmentStatusId == null || !this.employmentStatusId.equals(employmentStatusId)) {
			markModified();
		}
		employmentStatus = null;
		this.employmentStatusId = employmentStatusId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getEmploymentStatusId() {
		fetch();
		return employmentStatusId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initEmploymentStatus() {
		if (employmentStatus == null) {
			employmentStatus = (Code) new mojo.km.persistence.Reference(employmentStatusId, Code.class, PDCodeTableConstants.EMPLOYMENT_STATUS).getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getEmploymentStatus() {
		fetch();
		initEmploymentStatus();
		return employmentStatus;
	}
	/**
	* set the type reference for class member employmentStatus
	*/
	public void setEmploymentStatus(Code employmentStatus) {
		if (this.employmentStatus == null || !this.employmentStatus.equals(employmentStatus)) {
			markModified();
		}
		if (employmentStatus.getOID() == null) {
			new mojo.km.persistence.Home().bind(employmentStatus);
		}
		setEmploymentStatusId("" + employmentStatus.getOID());
		employmentStatus.setContext(PDCodeTableConstants.EMPLOYMENT_STATUS);
		this.employmentStatus = (Code) new mojo.km.persistence.Reference(employmentStatus).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setSalaryRateId(String salaryRateId) {
		if (this.salaryRateId == null || !this.salaryRateId.equals(salaryRateId)) {
			markModified();
		}
		salaryRate = null;
		this.salaryRateId = salaryRateId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getSalaryRateId() {
		fetch();
		return salaryRateId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initSalaryRate() {
		if (salaryRate == null) {
			salaryRate = (Code) new mojo.km.persistence.Reference(salaryRateId, Code.class, "SALARY_RATE").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getSalaryRate() {
		fetch();
		initSalaryRate();
		return salaryRate;
	}
	/**
	* set the type reference for class member salaryRate
	*/
	public void setSalaryRate(Code salaryRate) {
		if (this.salaryRate == null || !this.salaryRate.equals(salaryRate)) {
			markModified();
		}
		if (salaryRate.getOID() == null) {
			new mojo.km.persistence.Home().bind(salaryRate);
		}
		setSalaryRateId("" + salaryRate.getOID());
		salaryRate.setContext("SALARY_RATE");
		this.salaryRate = (Code) new mojo.km.persistence.Reference(salaryRate).getObject();
	}
	/**
	* @return 
	*/
	public String getCurrentEmployer() {
		fetch();
		return currentEmployer;
	}
	/**
	* @return 
	*/
	public String getJobTitle() {
		fetch();
		return jobTitle;
	}
	/**
	* @return 
	*/
	public String getLenghtOfEmployment() {
		fetch();
		return lenghtOfEmployment;
	}
	/**
	* @return 
	*/
	public double getSalary() {
		fetch();
		return salary;
	}
	/**
	* @return 
	*/
	public double getWorkHours() {
		fetch();
		return workHours;
	}
	/**
	* @param string
	*/
	public void setCurrentEmployer(String string) {
		if (this.currentEmployer == null || !this.currentEmployer.equals(string)) {
			markModified();
		}
		currentEmployer = string;
	}
	/**
	* @param string
	*/
	public void setJobTitle(String string) {
		if (this.jobTitle == null || !this.jobTitle.equals(string)) {
			markModified();
		}
		jobTitle = string;
	}
	/**
	* @param string
	*/
	public void setLenghtOfEmployment(String string) {
		if (this.lenghtOfEmployment == null || !this.lenghtOfEmployment.equals(string)) {
			markModified();
		}
		lenghtOfEmployment = string;
	}
	/**
	* @param string
	*/
	public void setSalary(double amount) {
		if ( this.salary != amount ) {
			markModified();
		}
		salary = amount;
	}
	/**
	* @param string
	*/
	public void setWorkHours(double hours) {
		if ( this.workHours != hours ) {
			markModified();
		}
		workHours = hours;
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
	* Finds all employments by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public FamilyMemberEmployment find( String oid ) 
	{
		IHome home = new Home();
		return (FamilyMemberEmployment)home.find( oid, FamilyMemberEmployment.class );
	}
	
	/**
	* Finds all employments by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator employments = home.findAll(attributeName, attributeValue, FamilyMemberEmployment.class);
		return employments;
	}
	
	/**
	 * @return
	 */
	public double getAnnualNetIncome()
	{
		fetch();
		return annualNetIncome;
	}

	/**
	 * @param d
	 */
	public void setAnnualNetIncome(double d)
	{
		annualNetIncome = d;
		if (this.annualNetIncome != d)
		{
			markModified();
			annualNetIncome = d;
		}
	}

}

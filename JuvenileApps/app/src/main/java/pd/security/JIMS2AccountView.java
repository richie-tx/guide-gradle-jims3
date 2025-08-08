package pd.security;

import java.util.Date;
import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.contact.agency.Department;
import pd.codetable.Code;
import pd.transferobjects.helper.DepartmentHelper;

/**
* @roseuid 4399CD6C0032
*/
public class JIMS2AccountView extends PersistentObject
{
	private String middleName;
	private String JIMS2LogonId;
	private String logonId;
	
	/**
	* Properties for department
	*/
	private Department department = null;
	/**
	* Properties for JIMS2AccountType
	* @referencedType pd.security.JIMS2AccountType
	*/
	private java.util.Collection JIMS2AccountType = null;
	/**
	* Properties for passwordQuestion
	* @referencedType pd.codetable.Code
	* @contextKey PASSWORD_QUESTION
	* @detailerDoNotGenerate true
	*/	
	private Code passwordQuestion = null;
	private String password;
	private String firstName;
	private String lastName;
	private String passwordQuestionId;
	private String departmentId;
	private String answer;
	private String status;
	private String userAccountOID;
	
	//added to cover service providers
	private Date activatedDate;

	private Date inactiveDate;

	private String activatedBy;

	private String inactivatedBy;
	/**
	* @roseuid 4399CD6C0032
	*/
	public JIMS2AccountView()
	{
	}
	/**
	* Clears all pd.security.JIMS2AccountType from class relationship collection.
	*/
	public void clearJIMS2AccountType()
	{
		initJIMS2AccountType();
		JIMS2AccountType.clear();
	}
	/**
	* @return 
	*/
	public String getAnswer()
	{
		fetch();
		return answer;
	}
	/**
	* Gets referenced type pd.contact.agency.Department
	*/
	public Department getDepartment()
	{
		fetch();
		initDepartment();
		return department;
	}
	/**
	* Get the reference value to class :: pd.contact.agency.Department
	*/
	public String getDepartmentId()
	{
		fetch();
		return departmentId;
	}
	/**
	* @return 
	*/
	public String getFirstName()
	{
		fetch();
		return firstName;
	}
	/**
	* returns a collection of pd.security.JIMS2AccountType
	*/
	public java.util.Collection getJIMS2AccountType()
	{
		fetch();
		initJIMS2AccountType();
		return JIMS2AccountType;
	}

	/**
	* @return 
	*/
	public String getJIMS2LogonId()
	{
		fetch();
		return JIMS2LogonId;
	}
	/**
	* @return 
	*/
	public String getLastName()
	{
		fetch();
		return lastName;
	}
	/**
	* @return 
	*/
	public String getMiddleName()
	{
		fetch();
		return middleName;
	}
	/**
	* @return 
	*/
	public String getPassword()
	{
		fetch();
		return password;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getPasswordQuestion()
	{
		fetch();
		initPasswordQuestion();
		return passwordQuestion;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getPasswordQuestionId()
	{
		fetch();
		return passwordQuestionId;
	}
	/**
	* @return 
	*/
	public String getStatus()
	{
		fetch();
		return status;
	}
	/**
	* Initialize class relationship to class pd.contact.agency.Department
	*/
	private void initDepartment()
	{
		if (department == null)
		{
			department = Department.find(departmentId);//87191
				/*(pd.contact.agency.Department) new mojo
					.km
					.persistence
					.Reference(departmentId, pd.contact.agency.Department.class)
					.getObject();*/
		}
	}
	/**
	* Initialize class relationship implementation for pd.security.JIMS2AccountType
	*/
	private void initJIMS2AccountType()
	{
		if (JIMS2AccountType == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			JIMS2AccountType =
				new mojo.km.persistence.ArrayList(JIMS2AccountType.class, "JIMS2AccountId", "" + getOID());
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initPasswordQuestion()
	{
		if (passwordQuestion == null)
		{
			passwordQuestion =
				(Code) new mojo
					.km
					.persistence
					.Reference(passwordQuestionId, Code.class, "PASSWORD_QUESTION")
					.getObject();
		}
	}
	/**
	* insert a pd.security.JIMS2AccountType into class relationship collection.
	*/
	public void insertJIMS2AccountType(JIMS2AccountType anObject)
	{
		initJIMS2AccountType();
		JIMS2AccountType.add(anObject);
	}
	/**
	* Removes a pd.security.JIMS2AccountType from class relationship collection.
	*/
	public void removeJIMS2AccountType(JIMS2AccountType anObject)
	{
		initJIMS2AccountType();
		JIMS2AccountType.remove(anObject);
	}
	/**
	* @param string
	*/
	public void setAnswer(String string)
	{
		if (this.answer == null || !this.answer.equals(string))
		{
			markModified();
		}
		answer = string;
	}
	/**
	* set the type reference for class member department
	*/
	public void setDepartment(Department department)
	{
		/*if (this.department == null || !this.department.equals(department))
		{
			markModified();
		}
		if (department.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(department);
		}*/
		setDepartmentId("" + department.getDepartmentId());
		this.department = department; //87191(pd.contact.agency.Department) new mojo.km.persistence.Reference(department).getObject(); #87191
	}
	/**
	* Set the reference value to class :: pd.contact.agency.Department
	*/
	public void setDepartmentId(String departmentId)
	{
		if (this.departmentId == null || !this.departmentId.equals(departmentId))
		{
			markModified();
		}
		department = null;
		this.departmentId = departmentId;
	}
	/**
	* @param string
	*/
	public void setFirstName(String string)
	{
		if (this.firstName == null || !this.firstName.equals(string))
		{
			markModified();
		}
		firstName = string;
	}
	/**
	* @param collection
	*/
	public void setJIMS2AccountType(java.util.Collection collection)
	{
		if (this.JIMS2AccountType == null || !this.JIMS2AccountType.equals(collection))
		{
			markModified();
		}
		JIMS2AccountType = collection;
	}

	/**
	* @param string
	*/
	public void setJIMS2LogonId(String string)
	{
		if (this.JIMS2LogonId == null || !this.JIMS2LogonId.equals(string))
		{
			markModified();
		}
		JIMS2LogonId = string;
	}
	/**
	* @param string
	*/
	public void setLastName(String string)
	{
		if (this.lastName == null || !this.lastName.equals(string))
		{
			markModified();
		}
		lastName = string;
	}
	/**
	* @param string
	*/
	public void setMiddleName(String string)
	{
		if (this.middleName == null || !this.middleName.equals(string))
		{
			markModified();
		}
		middleName = string;
	}
	/**
	* @param string
	*/
	public void setPassword(String string)
	{
		if (this.password == null || !this.password.equals(string))
		{
			markModified();
		}
		password = string;
	}
	/**
	* set the type reference for class member passwordQuestion
	*/
	public void setPasswordQuestion(Code passwordQuestion)
	{
		if (this.passwordQuestion == null || !this.passwordQuestion.equals(passwordQuestion))
		{
			markModified();
		}
		if (passwordQuestion.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(passwordQuestion);
		}
		setPasswordQuestionId("" + passwordQuestion.getOID());
		this.passwordQuestion = (Code) new mojo.km.persistence.Reference(passwordQuestion).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setPasswordQuestionId(String passwordQuestionId)
	{
		if (this.passwordQuestionId == null || !this.passwordQuestionId.equals(passwordQuestionId))
		{
			markModified();
		}
		passwordQuestion = null;
		this.passwordQuestionId = passwordQuestionId;
	}
	
	/**
	* @param string
	*/
	public void setStatus(String string)
	{
		if (this.status == null || !this.status.equals(string))
		{
			markModified();
		}
		status = string;
	}
	/**
	* @return 
	* @param string
	*/
	static public JIMS2AccountView find(String JIMS2AccountId)
	{
		JIMS2AccountView jims2Account = null;
		IHome home = new Home();
		jims2Account = (JIMS2AccountView) home.find(JIMS2AccountId, JIMS2AccountView.class);
		return jims2Account;
	}
	/**
	* @roseuid 42E65EA6010F
	*/
	static public Iterator findAll()
	{
		IHome home = new Home();
		Iterator iter = home.findAll(JIMS2AccountView.class);
		return iter;
	}
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4107B06D01BB
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(event, JIMS2AccountView.class);
		return iter;
	}
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @roseuid 42E65EA6010F
	*/
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue, JIMS2AccountView.class);
	}

	/**
	 * @param deptEvent
	 * @param class1
	 * @return
	 */
	public static MetaDataResponseEvent findMeta(IEvent aEvent ) {
		IHome home = new Home();
		MetaDataResponseEvent iter = home.findMeta(aEvent, JIMS2AccountView.class);
		return iter;
	}	
	
	/**
	* @return 
	* @param string
	*/
	static public JIMS2AccountView findByLogonId(String logonId)
	{
		JIMS2AccountView jims2Account = null;
		IHome home = new Home();
		Iterator iter = (Iterator) home.findAll("logonId", logonId, JIMS2AccountView.class);
		while (iter.hasNext())
		{
			jims2Account = (JIMS2AccountView) iter.next();
		}
		return jims2Account;
	}
	/**
	 * @return Returns the logonId.
	 */
	public String getLogonId() {
		return logonId;
	}
	/**
	 * @param logonId The logonId to set.
	 */
	public void setLogonId(String logonId) {
		if (this.logonId == null || !this.status.equals(logonId))
		{
			markModified();
		}
		this.logonId = logonId;
	}
	/**
	 * @return Returns the userAccountOID.
	 */
	public String getUserAccountOID() {
		fetch();
		return userAccountOID;
	}
	/**
	 * @param userAccountOID The userAccountOID to set.
	 */
	public void setUserAccountOID(String userAccountOID) {
		if (this.userAccountOID == null || !this.userAccountOID.equals(userAccountOID))
		{
			markModified();
		}
		this.userAccountOID = userAccountOID;
	}
	/**
	 * @return the activatedDate
	 */
	public Date getActivatedDate()
	{
	    return activatedDate;
	}
	/**
	 * @param activatedDate the activatedDate to set
	 */
	public void setActivatedDate(Date activatedDate)
	{
	    if (this.activatedDate == null || !this.activatedDate.equals(activatedDate))
		{
			markModified();
		}
	    this.activatedDate = activatedDate;
	}
	/**
	 * @return the inactiveDate
	 */
	public Date getInactiveDate()
	{
	    return inactiveDate;
	}
	/**
	 * @param inactiveDate the inactiveDate to set
	 */
	public void setInactiveDate(Date inactiveDate)
	{
	    if (this.inactiveDate == null || !this.inactiveDate.equals(inactiveDate))
		{
			markModified();
		}
	    this.inactiveDate = inactiveDate;
	}
	/**
	 * @return the activatedBy
	 */
	public String getActivatedBy()
	{
	    return activatedBy;
	}
	/**
	 * @param activatedBy the activatedBy to set
	 */
	public void setActivatedBy(String activatedBy)
	{
	    if (this.activatedBy == null || !this.activatedBy.equals(activatedBy))
		{
			markModified();
		}
	    this.activatedBy = activatedBy;
	}
	/**
	 * @return the inactivatedBy
	 */
	public String getInactivatedBy()
	{
	    return inactivatedBy;
	}
	/**
	 * @param inactivatedBy the inactivatedBy to set
	 */
	public void setInactivatedBy(String inactivatedBy)
	{
	    if (this.inactivatedBy == null || !this.inactivatedBy.equals(inactivatedBy))
		{
			markModified();
		}
		this.inactivatedBy = inactivatedBy;
	}
}

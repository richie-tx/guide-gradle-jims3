package pd.juvenilecase.interviewinfo;

import messaging.interviewinfo.domintf.IInterviewPerson;
import mojo.km.persistence.PersistentObject;

/**
* Name of the person interviewed
*/
public class InterviewPerson extends PersistentObject 
{
	/**
	* Name of the person interviewed
	*/
	private String lastName;
	private String middleName;
	private String firstName;
	
	private String interviewId;
	
	/**
	* @roseuid 448EC36203CF
	*/
	public InterviewPerson() 
	{
	}

	/**
	* 
	*/
	public void fill( IInterviewPerson person ) 
	{
		person.setInterviewId( getInterviewId() );
		person.setFirstName( getFirstName() );
		person.setMiddleName( getMiddleName() );
		person.setLastName( getLastName() );
	}

	
	/**
	* Set the reference value to class :: pd.juvenilecase.interviewinfo.Interview
	*/
	public void setInterviewId(String interviewId) 
	{
		if (this.interviewId == null || !this.interviewId.equals(interviewId)) {
			markModified();
		}
		this.interviewId = interviewId;
	}
	
	/**
	* Get the reference value to class :: pd.juvenilecase.interviewinfo.Interview
	*/
	public String getInterviewId() 
	{
		fetch();
		return interviewId;
	}
	
	/**
	* 
	*/
	public void setFirstName(String aFirstName) 
	{
		if (this.firstName == null || !this.firstName.equals(aFirstName)) {
			markModified();
		}
		this.firstName = aFirstName;
	}
	
	/**
	* 
	*/
	public String getFirstName() 
	{
		fetch();
		return firstName;
	}
	
	/**
	* 
	*/
	public void setMiddleName(String aMiddleName) 
	{
		if (this.middleName == null || !this.firstName.equals(aMiddleName)) {
			markModified();
		}
		this.middleName = aMiddleName;
	}
	
	/**
	* 
	*/
	public String getMiddleName() 
	{
		fetch();
		return middleName;
	}
	
	/**
	* 
	*/
	public void setLastName(String aLastName) 
	{
		if (this.lastName == null || !this.firstName.equals(aLastName)) {
			markModified();
		}
		this.lastName = aLastName;
	}
	
	/**
	* 
	*/
	public String getLastName() 
	{
		fetch();
		return lastName;
	}
	
	
}

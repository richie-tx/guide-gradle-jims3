package messaging.interviewinfo.reply;

import messaging.interviewinfo.domintf.IInterviewPerson;
import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.Name;


/**
 *
 */
public class InterviewPersonResponseEvent extends ResponseEvent implements IInterviewPerson, Comparable
{
	private String interviewId;
	private String lastName;
	private String middleName;
	private String firstName;
	private String relationshipId;
	private String relationship;
	private String typeOfPerson="";
	
	public static String PERSON_CONTACT="C";
	public static String PERSON_SELF="S";
	public static String PERSON_FAMILY="F";
	
	// May add relationship in the future
	// to handle persons with same names.

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getInterviewId()
	{
		return interviewId;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setInterviewId(String string)
	{
		interviewId = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	}

	public String getFormattedName()
	{
		Name name = new Name(firstName, middleName, lastName);
		return name.getFormattedName().trim();	
	}
	/**
	 * @return Returns the relationshipId.
	 */
	public String getRelationshipId() {
		return relationshipId;
	}
	/**
	 * @param relationshipId The relationshipId to set.
	 */
	public void setRelationshipId(String relationshipId) {
		this.relationshipId = relationshipId;
	}
	/**
	 * @return Returns the relationship.
	 */
	public String getRelationship() {
		return relationship;
	}
	/**
	 * @param relationship The relationship to set.
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		InterviewPersonResponseEvent input = (InterviewPersonResponseEvent) o;
		if(this.lastName==null){
			return -1;
		}
		else if (input.lastName==null){
			return 1;
		}
		int compare = this.lastName.compareTo(input.lastName);
		if(compare == 0) {
			if(this.firstName==null){
				return -1;
			}
			else if (input.firstName==null){
				return 1;
			}
			compare = this.firstName.compareTo(input.firstName);
			
			if(compare == 0) {
				if(this.middleName==null){
					return -1;
				}
				else if (input.middleName==null){
					return 1;
				}
				compare = this.middleName.compareTo(input.middleName);
			}
		}
		
		
			
		return compare;
		
	}
	/**
	 * @return Returns the typeOfPerson.
	 */
	public String getTypeOfPerson() {
		return typeOfPerson;
	}
	/**
	 * @param typeOfPerson The typeOfPerson to set.
	 */
	public void setTypeOfPerson(String typeOfPerson) {
		this.typeOfPerson = typeOfPerson;
	}
}

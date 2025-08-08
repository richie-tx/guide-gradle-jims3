package messaging.juvenilecase.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;


/**
 * Returned during a request for a list of outstanding requested assessments.
 */
public class RequestedBenefitsAssessmentResponseEvent extends ResponseEvent implements Comparable
{
   public String assessmentId;
   public String juvenileNumber;
   public String casefileId;
   public String firstName;
   public String lastName;
   public String jpoID;
   public String jpoName;
   public Date requestDate;   
   public String requesterName;
	/**
	 * @return Returns the requesterName.
	 */
	public String getRequesterName() {
		return requesterName;
	}
	/**
	 * @param requesterName The requesterName to set.
	 */
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}
	/**
	 * @return Returns the jpoName.
	 */
	public String getJpoName() {
		return jpoName;
	}
	/**
	 * @return Returns the requestDate.
	 */
	public Date getRequestDate() {
		return requestDate;
	}
	/**
	 * @param jpoName The jpoName to set.
	 */
	public void setJpoName(String jpoName) {
		this.jpoName = jpoName;
	}
	/**
	 * @param requestDate The requestDate to set.
	 */
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	   
	   public Collection guardians = new ArrayList();	// of BenefitsAssessmentGuardianResponseEvent
	   
		/**
		 * @return
		 */
		public String getAssessmentId()
		{
			return assessmentId;
		}
		
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
		public Collection getGuardians()
		{
			return guardians;
		}
		
		/**
		 * @return
		 */
		public String getJuvenileNumber()
		{
			return juvenileNumber;
		}
		
		/**
		 * @return
		 */
		public String getLastName()
		{
			return lastName;
		}
		
		/**
		 * @param string
		 */
		public void setAssessmentId(String string)
		{
			assessmentId = string;
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
		public void setJuvenileNumber(String string)
		{
			juvenileNumber = string;
		}
		
		/**
		 * @param string
		 */
		public void setLastName(String string)
		{
			lastName = string;
		}
		
	/**
	 * @return
	 */
	public String getCasefileId()
	{
		return casefileId;
	}
	
	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
	}
	
	/**
	 * @return Returns the jpoID.
	 */
	public String getJpoID() {
		return jpoID;
	}
	/**
	 * @param jpoID The jpoID to set.
	 */
	public void setJpoID(String jpoID) {
		this.jpoID = jpoID;
	}
	
	public int compareTo(Object arg0)
	{
		int result = 0;
	
		RequestedBenefitsAssessmentResponseEvent obj2 = (RequestedBenefitsAssessmentResponseEvent)arg0;
		
		if(obj2 == null || obj2.getRequestDate() == null) {
			return -1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
		}
		if(this.getRequestDate() == null) {
			return 1;// this makes any null objects go to the bottom change this to 1 if you want the top of the list
		}
		
		return this.getRequestDate().compareTo(obj2.getRequestDate());
	}

}

//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\SendNewJuvenileCasefileNotificationEvent.java

package messaging.juvenilecase;

import java.util.ArrayList;
import java.util.Collection;

import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import mojo.km.messaging.PersistentEvent;

public class SendNewJuvenileCasefileNotificationEvent extends PersistentEvent 
{
   private Collection assignments = new ArrayList();
   private String juvenileFirstName;
   private String juvenileLastName;
   private String juvenileNumber;
   private String supervisionNumber;
   private String jpoFirstName;
   private String jpoLastName;
   private String caseLoadManagerId;
   private boolean isMAYSINeeded;
   private boolean isBenefitAssessmentNeeded;
   private boolean isRiskAssessmentNeeded;
   private String emailFrom;
   private String emailTo;
   
   
	/**
	 * @return
	 */
	public String getJuvenileFirstName()
	{
		return juvenileFirstName;
	}
	
	/**
	 * @return
	 */
	public String getJuvenileLastName()
	{
		return juvenileLastName;
	}
	
	/**
	 * @return
	 */
	public String getJuvenileNumber()
	{
		return juvenileNumber;
	}
	
	/**
	 * @param string
	 */
	public void setJuvenileFirstName(String string)
	{
		juvenileFirstName = string;
	}
	
	/**
	 * @param string
	 */
	public void setJuvenileLastName(String string)
	{
		juvenileLastName = string;
	}
	
	/**
	 * @param string
	 */
	public void setJuvenileNumber(String string)
	{
		juvenileNumber = string;
	}
	
	/**
	 * @return
	 */
	public String getSupervisionNumber()
	{
		return supervisionNumber;
	}
	
	/**
	 * @param string
	 */
	public void setSupervisionNumber(String string)
	{
		supervisionNumber = string;
	}

	/**
	 * @return
	 */
	public String getCaseLoadManagerId()
	{
		return caseLoadManagerId;
	}
	
	/**
	 * @return
	 */
	public boolean isBenefitAssessmentNeeded()
	{
		return isBenefitAssessmentNeeded;
	}
	
	/**
	 * @return
	 */
	public boolean isMAYSINeeded()
	{
		return isMAYSINeeded;
	}
	
	/**
	 * @return
	 */
	public boolean isRiskAssessmentNeeded()
	{
		return isRiskAssessmentNeeded;
	}
	
	/**
	 * @param string
	 */
	public void setCaseLoadManagerId(String string)
	{
		caseLoadManagerId = string;
	}
	
	/**
	 * @param b
	 */
	public void setBenefitAssessmentNeeded(boolean b)
	{
		isBenefitAssessmentNeeded = b;
	}
	
	/**
	 * @param b
	 */
	public void setMAYSINeeded(boolean b)
	{
		isMAYSINeeded = b;
	}
	
	/**
	 * @param b
	 */
	public void setRiskAssessmentNeeded(boolean b)
	{
		isRiskAssessmentNeeded = b;
	}
	
	/**
	 * @return
	 */
	public String getJpoFirstName()
	{
		return jpoFirstName;
	}
	
	/**
	 * @return
	 */
	public String getJpoLastName()
	{
		return jpoLastName;
	}
	
	/**
	 * @param string
	 */
	public void setJpoFirstName(String string)
	{
		jpoFirstName = string;
	}
	
	/**
	 * @param string
	 */
	public void setJpoLastName(String string)
	{
		jpoLastName = string;
	}

	/**
	 * @return
	 */
	public Collection getAssignments()
	{
		return assignments;
	}
	
	/**
	 * @param collection
	 */
	public void setAssignments(Collection collection)
	{
		assignments = collection;
	}

	/**
	 * @param collection
	 */
	public void insertAssignment(JuvenileCasefileReferralsResponseEvent referralResponseEvent)
	{
		assignments.add(referralResponseEvent);
	}

	/**
	 * @return
	 */
	public String getEmailFrom()
	{
		return emailFrom;
	}
	
	/**
	 * @return
	 */
	public String getEmailTo()
	{
		return emailTo;
	}
	
	/**
	 * @param string
	 */
	public void setEmailFrom(String string)
	{
		emailFrom = string;
	}
	
	/**
	 * @param string
	 */
	public void setEmailTo(String string)
	{
		emailTo = string;
	}

}

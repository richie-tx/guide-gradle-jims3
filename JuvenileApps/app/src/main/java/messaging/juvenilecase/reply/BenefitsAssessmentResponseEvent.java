package messaging.juvenilecase.reply;

import java.util.Date;




import mojo.km.messaging.ResponseEvent;

public class BenefitsAssessmentResponseEvent extends ResponseEvent implements Comparable
{
	public String assessmentId;
	
	public String juvenileNumber;
	public String firstName;
	public String lastName;
   
	public String guardianId;
	public String guardianFirstName;
	public String guardianLastName;
   
	private Date entryDate;
	
	private boolean isEligibleForMedicaid;
	private boolean isReceivingMedicaid;
	private boolean isEligibleForTitleIVe;
	private boolean isReceivingTitleIVe;
	
	/**
	 * @return
	 */
	public String getGuardianName()
	{
		return guardianLastName + ", " + guardianFirstName;
	}
		
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
	public Date getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @return
	 */
	public String getGuardianFirstName()
	{
		return guardianFirstName;
	}

	/**
	 * @return
	 */
	public String getGuardianId()
	{
		return guardianId;
	}

	/**
	 * @return
	 */
	public String getGuardianLastName()
	{
		return guardianLastName;
	}

	/**
	 * @return
	 */
	public boolean isEligibleForMedicaid()
	{
		return isEligibleForMedicaid;
	}

	/**
	 * @return
	 */
	public boolean isEligibleForTitleIVe()
	{
		return isEligibleForTitleIVe;
	}

	/**
	 * @return
	 */
	public boolean isReceivingMedicaid()
	{
		return isReceivingMedicaid;
	}

	/**
	 * @return
	 */
	public boolean isReceivingTitleIVe()
	{
		return isReceivingTitleIVe;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/**
	 * @param string
	 */
	public void setGuardianFirstName(String string)
	{
		guardianFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setGuardianId(String string)
	{
		guardianId = string;
	}

	/**
	 * @param string
	 */
	public void setGuardianLastName(String string)
	{
		guardianLastName = string;
	}

	/**
	 * @param b
	 */
	public void setEligibleForMedicaid(boolean b)
	{
		isEligibleForMedicaid = b;
	}

	/**
	 * @param b
	 */
	public void setEligibleForTitleIVe(boolean b)
	{
		isEligibleForTitleIVe = b;
	}

	/**
	 * @param b
	 */
	public void setReceivingMedicaid(boolean b)
	{
		isReceivingMedicaid = b;
	}

	/**
	 * @param b
	 */
	public void setReceivingTitleIVe(boolean b)
	{
		isReceivingTitleIVe = b;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */

	
	public int compareTo(Object obj) throws ClassCastException
	{
		if(obj==null)
			return -1;
		BenefitsAssessmentResponseEvent evt = (BenefitsAssessmentResponseEvent) obj;
		int result = 0;
		if(getEntryDate() != null && evt.getEntryDate() != null )
		{
			try{
				if(this.entryDate!=null || evt.getEntryDate()!=null){
					if(evt.getEntryDate()==null)
						return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
					if(this.entryDate==null)
						return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
					
					if(this.entryDate==null)
						return -1;
					if(evt.getEntryDate()==null)
						return 1;
					result= evt.getEntryDate().compareTo(this.entryDate); // backwards in order to get list to show up most recent first
				}
			}
			catch(NumberFormatException e){
				result = 0;
			}
		}
		return result;
	}

}

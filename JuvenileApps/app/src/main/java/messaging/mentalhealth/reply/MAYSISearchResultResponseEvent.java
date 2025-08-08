package messaging.mentalhealth.reply;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import messaging.casefile.reply.ActivityResponseEvent;
import mojo.km.messaging.ResponseEvent;
import naming.PDJuvenileCaseConstants;

/**
 *  
 * @author  athorat
 * @version  1.0.0
 * Date:    2005-05-10
 */
public class MAYSISearchResultResponseEvent extends ResponseEvent implements Comparable
{
    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------

    private String maysiFullAssessId;
    
    private String assessmentId;
    
    private String subAssessId;
    
    private String maysiDetailId;

    private Date assessDate;

    private String referralNumber;

    private String juvenileNumber;

    private String testAge;

    private String locationUnit;
    
    private String locationUnitId;

    private String facilityType;
    
    private String facilityTypeId;

    private String assessmentOption;
    
    private String assessmentOptionId;
    
    private boolean detailsAvailable=false;
    
    private String reasonNotDone;
    

	/**
	 * 
	 */
	public MAYSISearchResultResponseEvent()
	{
		super();
		this.setTopic(PDJuvenileCaseConstants.MAYSI_RESULTS_LIST_TOPIC);
		// TODO Auto-generated constructor stub
	}

    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------
	
    /**
     *  
     * @return  The assessment option.
     */
    public String getAssessmentOption()
    {
        return assessmentOption;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.getAssessmentOption

    /**
     *  
     * @return  The facility type.
     */
    public String getFacilityType()
    {
        return facilityType;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.getFacilityType

    /**
     *  
     * @return  The juvinile number.
     */
    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.getJuvinileNumber

    /**
     *  
     * @return  The location.
     */
    public String getLocationUnit()
    {
        return locationUnit;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.getLocation

    /**
     *  
     * @return  The referral number.
     */
    public String getReferralNumber()
    {
        return referralNumber;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.getReferralNumber

    /**
     *  
     * @return  The screen date.
     */
    public Date getAssessDate()
    {
        return assessDate;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.getScreenDate

	/**
	 *  
	 * @return  The screen date as string.
	 */
	public String getScreenDateAsString()
	{
		String dateFormat = "MM/dd/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String mmddyyyyDate = sdf.format(assessDate);
		return mmddyyyyDate;
	}//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.getScreenDate

    /**
     *  
     * @return  The sequence number.
     */
    public String getMaysiFullAssessId()
    {
        return maysiFullAssessId;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.getSequenceNumber

    /**
     * @return assessmentId
     */
    public String getAssessmentId() {
		return assessmentId;
	}

	/**
	 * @return maysiDetailId
	 */
	public String getMaysiDetailId() {
		return maysiDetailId;
	}

	/**
	 * @return subAssessId
	 */
	public String getSubAssessId() {
		return subAssessId;
	}
    
    /**
     *  
     * @return  The test age.
     */
    public String getTestAge()
    {
        return testAge;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.getTestAge

    /**
     *  
     * @param string The assessment option.
     */
    public void setAssessmentOption(String string)
    {
        assessmentOption = string;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.setAssessmentOption

    /**
     *  
     * @param string The facility type.
     */
    public void setFacilityType(String string)
    {
        facilityType = string;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.setFacilityType

    /**
     *  
     * @param string The juvinile number.
     */
    public void setJuvenileNumber(String string)
    {
        juvenileNumber = string;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.setJuvinileNumber

    /**
     *  
     * @param string The location.
     */
    public void setLocationUnit(String string)
    {
        locationUnit = string;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.setLocation

    /**
     *  
     * @param string The referral number.
     */
    public void setReferralNumber(String string)
    {
        referralNumber = string;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.setReferralNumber

    /**
     *  
     * @param date The screen date.
     */
    public void setAssessDate(Date date)
    {
        assessDate = date;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.setScreenDate

    /**
     *  
     * @param string The sequence number.
     */
    public void setMaysiFullAssessId(String string)
    {
        maysiFullAssessId = string;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.setSequenceNumber
    
    /**
     * @param assessmentId
     */
    public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}
    
    /**
     * @param maysiDetailId
     */
    public void setMaysiDetailId(String maysiDetailId) {
		this.maysiDetailId = maysiDetailId;
	}
    
    /**
     * @param subAssessId
     */
    public void setSubAssessId(String subAssessId) {
		this.subAssessId = subAssessId;
	}

    /**
     *  
     * @param i The test age.
     */
    public void setTestAge(String i)
    {
        testAge = i;
    }//end of messaging.juvenilecase.reply.MAYSISearchResultResponseEvent.setTestAge
    
	public int compareTo(Object obj) throws ClassCastException
	{
		if(obj==null)
			return -1;
		MAYSISearchResultResponseEvent evt = (MAYSISearchResultResponseEvent) obj;
		int result = 0;
			try{
				if(this.assessDate!=null || evt.getAssessDate()!=null){
					if(evt.getAssessDate()==null)
						return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
					if(this.assessDate==null)
						return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
					Date currDate=this.assessDate;
					Date incomingDate=evt.getAssessDate();
					if(currDate==null)
						return -1;
					if(incomingDate==null)
						return 1;
					result= incomingDate.compareTo(currDate); // backwards in order to get list to show up most recent first
				}
			
			}
			catch(NumberFormatException e){
				result = 0;
			}
	
		return result;
	}		

	/**
	 * @return Returns the assessmentOptionId.
	 */
	public String getAssessmentOptionId() {
		return assessmentOptionId;
	}
	/**
	 * @param assessmentOptionId The assessmentOptionId to set.
	 */
	public void setAssessmentOptionId(String assessmentOptionId) {
		this.assessmentOptionId = assessmentOptionId;
	}
	/**
	 * @return Returns the detailsAvailable.
	 */
	public boolean isDetailsAvailable() {
		return detailsAvailable;
	}
	/**
	 * @param detailsAvailable The detailsAvailable to set.
	 */
	public void setDetailsAvailable(boolean detailsAvailable) {
		this.detailsAvailable = detailsAvailable;
	}
	/**
	 * @return Returns the facilityTypeId.
	 */
	public String getFacilityTypeId() {
		return facilityTypeId;
	}
	/**
	 * @param facilityTypeId The facilityTypeId to set.
	 */
	public void setFacilityTypeId(String facilityTypeId) {
		this.facilityTypeId = facilityTypeId;
	}
	/**
	 * @return Returns the locationId.
	 */
	public String getLocationUnitId() {
		return locationUnitId;
	}
	/**
	 * @param locationId The locationId to set.
	 */
	public void setLocationUnitId(String locationId) {
		this.locationUnitId = locationId;
	}
	
	
	
	public String getReasonNotDone()
	{
	    return reasonNotDone;
	}

	public void setReasonNotDone(String reasonNotDone)
	{
	    this.reasonNotDone = reasonNotDone;
	}



	public static Comparator CaseReviewJournalSummaryMaysiComparator = new Comparator() {
		public int compare(Object maysiView, Object otherMaysiView) {
		
		int result = 0;
		if(maysiView != null && otherMaysiView != null){ 			
		  String referralNumber = ((MAYSISearchResultResponseEvent)maysiView).getReferralNumber();
		  String otherReferralNumber = ((MAYSISearchResultResponseEvent)otherMaysiView).getReferralNumber();
		  Date maysiDate = ((MAYSISearchResultResponseEvent)maysiView).getAssessDate();
		  Date otherMaysiDate = ((MAYSISearchResultResponseEvent)otherMaysiView).getAssessDate();
		  
		  if(referralNumber == null || referralNumber.equals("")){
			  result = -1;
		  } else if(otherReferralNumber == null || otherReferralNumber.equals("")){
			  result = 1;
		  }else{
			  result = referralNumber.compareTo(otherReferralNumber);
		  }
		  if(result == 0){
			  if(maysiDate == null)
			  {
				  result = -1;
			  }else if(otherMaysiDate == null)
			  {
				  result = 1;
			  }
			  else 
			  {
				  result = otherMaysiDate.compareTo(maysiDate);
			  }
		  }
		}
		  
		  return result;
		}	
	};
	
} // end MAYSISearchResultResponseEvent

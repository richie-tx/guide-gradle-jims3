package pd.juvenilecase.interviewinfo;

import java.util.Date;
import java.util.List;

import messaging.contact.domintf.IAddress;


/**
 * @stereotype control
 */
public class JuvenileInterviewInfoController 
{
   
   /**
    * @roseuid 43F379F4013E
    */
   public JuvenileInterviewInfoController() 
   {
    
   }
   
   /**
    * @stereotype design
    * @param juvenileNum
    * @roseuid 43F371BF038B
    */
   public void getJuvenileBenefits(String juvenileNum) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param juvenileNum
    * @roseuid 43F371C0002D
    */
   public void getJuvenileInsurance(String juvenileNum) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param juvenileNum
    * @param receivingBenefits
    * @param eligibleForBenefits
    * @param eligibilityTypeId
    * @roseuid 43F371C301EB
    */
   public void UpdateJuvenileBenefits(String juvenileNum, boolean receivingBenefits, boolean eligibleForBenefits, String eligibilityTypeId) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param juvenileNum
    * @param typeId
    * @param policyNum
    * @param carrier
    * @roseuid 43F371C70004
    */
   public void updateJuvenileInsurance(String juvenileNum, String typeId, String policyNum, String carrier) 
   {
    
   }
   
   
   /**
    * Returns all the interview summary data (InterviewResponseEvent) for a casefile.
    * 
	* @stereotype design
	*/
   public void getInterviews( String casefileId ) 
   {
   }
   
   /**
    * Returns data required to create a new interview. Currently only 
    * InterviewPersonResponseEvent's are returned.
    * 
	* @stereotype design
	*/
   public void getInterviewCreationData( String casefileId ) 
   {
   }
   
   /**
    * Returns the interview detail data (InterviewDetailResponseEvent) for an interview.
    * 
	* @stereotype design
	*/
   public void getInterviewDetail( String interviewId ) 
   {
   }
   
   /**
	* Creates a new interview. Returns the new interview (InterviewDetailResponseEvent). 
	* 
	* @stereotype design
	*/
   public void createInterview( 
					   String casefileId,
					   Date interviewDate,
					   List personsInterviewed,
					   List recordInventoryIds,
					   String otherInventoryRecords,
					   String interviewTypeId,
					   String locationId,
					   IAddress address,
					   String summaryNotes ) 
   {
   }
   
   /**
	* Returns InterviewDetailResponseEvent.
	* 
	* @stereotype design
	*/
	public void saveInterview()
	{
	}
  
   /**
	* Returns InterviewCategoryResponseEvents.
	* 
	* @stereotype design
	*/
	public void getInterviewCategories()
	{
	}
   
   /**
	* Returns InterviewQuestionResponseEvents.
	* 
	* @stereotype design
	*/
	public void getInterviewQuestions( List categoryIds )
	{
	}
   
   /**
	* Returns InterviewTaskResponseEvents.
	* 
	* @stereotype design
	*/
	public void createInterviewTaskList( String interviewId, List questionIds )
	{
	}
   
   /**
	* Returns InterviewTaskResponseEvents.
	* 
	* @stereotype design
	*/
	public void getInterviewTaskList( String interviewId )
	{
	}
   
   /**
	* Returns InterviewDetailResponseEvent.
	* 
	* @stereotype design
	*/
	public void completeInterviewTask( String interviewId, List taskIds )
	{
	}
		
	/**
	* Returns InterviewDetailResponseEvent.
	* 
	* @stereotype design
	*/
	public void completeInterview( String interviewId )
	{
	}
	
	/**
	* Returns InterviewDetailResponseEvent.
	* 
	* @stereotype design
	*/
	public void addInterviewSummaryNotes(String summaryNotes, String interviewId )
	{
	}	
		
	/**
	* Returns SocialHistoryReportDataResponseEvent.
	* 
	* @stereotype design
	*/
	public void getSocialHistoryReportData( String casefileId )
	{
	}	

	/**
	* Returns SocialHistoryReportHeaderResponseEvent.
	* 
	* @stereotype design
	*/
	public void getInterviewReports( String casefileId )
	{
	}	

	/**
	* Returns SocialHistoryReportHeaderResponseEvent.
	* 
	* @stereotype design
	*/
	public void getInterviewReport( String reportId )
	{
	}	

	/**
	* @stereotype design
	*/
	public void emailInterviewReport( String reportId, String toAddress, String subject )
	{
	}	

	/**
	* Returns ??
	* 
	* @stereotype design
	*/
	public void getAttorneyOptionsData( String casefileId )
	{
	}	

	/**
	* Returns ??
	* 
	* @stereotype design
	*/
	public void getAttorneyAppointmentData(  )
	{
	}	

	
	
	/**
	* Returns ??
	* 
	* @stereotype design
	*/
	public void createSocialHistoryReport(  )
	{
	}	

	/**
	* Returns ??
	* 
	* @stereotype design
	*/
	public void createParentalStatementReport(  )
	{
	}	

	/**
	* Returns ??
	* 
	* @stereotype design
	*/
	public void createHireAttorneyReport(  )
	{
	}	

	/**
	* Returns ??
	* 
	* @stereotype design
	*/
	public void createRequestAttorneyReport(  )
	{
	}	

	/**
	* Returns ??
	* 
	* @stereotype design
	*/
	public void createRightsOfParentsWorksheet(  )
	{
	}	

	/**
	* Returns messaging.interviewinfo.reply.JuvenileCourtDateEvent
	* 
	* @stereotype design
	*/
	public void getNextJuvenileCourtDate(  )
	{
	}	

	/**
	* Returns 
	* 
	* @stereotype design
	*/
	public void saveInterviewInventory(  )
	{
	}
	
	/**
	* Returns 
	* 
	* @stereotype design
	*/
	public void getInterviewInventory(  )
	{
	}
	
	/**
	* Returns 
	* 
	* @stereotype design
	*/
	public void saveInterviewDocument(){}
	
	/**
	* Returns 
	* 
	* @stereotype design
	*/
	public void getServiceAndAttendance(){}
	
	
}

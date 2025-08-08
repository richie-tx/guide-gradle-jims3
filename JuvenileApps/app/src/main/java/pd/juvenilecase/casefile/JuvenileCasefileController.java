package pd.juvenilecase.casefile;

import java.util.Date;


/**
 * @stereotype control
 */
public class JuvenileCasefileController 
{
   
   /**
    * @roseuid 439602DB0247
    */
   public JuvenileCasefileController() 
   {
    
   }
   
   /**
    * @stereotype design
    * @param supervisionNumber
    * @roseuid 4395BC26033A
    */
   public void getCasefileClosingExceptions(String supervisionNumber) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param supervisionNumber
    * @roseuid 4395C2350376
    */
   public void getCasefileClosingDetails(String supervisionNumber) 
   {
    
   }
   /**
    * @stereotype design
    * @param supervisionNumber
    * @roseuid 4395C2350376
    */
   public void saveCasefileClosingProbDatesFlag(String supervisionNumber) 
   {
    
   }
   
  /**
   * @stereotype design
   * @param referenceId
   * @param responseType
   * @roseuid 4395C2350376
   */
  public void getResponse(String referenceId, String responseType) 
  {

  }
   
   /**
    * @stereotype design
    * @param status
    * @roseuid 4395C237018E
    */
   public void updateCasefileClosing(int status) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param supervisionEndDate
    * @param supervisionOutcomeId
    * @param controllingReferrelId
    * @param closingEvaluation
    * @param closingComments
    * @param type
    * @roseuid 4395C23902A0
    */
   public void updateResponse(String supervisionEndDate, String supervisionOutcomeId, String controllingReferrelId, String closingEvaluation, String closingComments, String type) 
   {
    
   }
   
  /**
   * @stereotype design
   * @param expectedReleaseDate
   * @param facilityId
   * @param facilityReleaseReasonId
   * @param levelofcareId
   * @param permanencyPlanid
   * @param special notes
   * @roseuid 4395C23902A0
   */
  public void updateResidentialExitPlan(String expectedReleaseDate, String facilityId, String facilityReleaseReasonId, String levelofcareId, String permanencyPlanid, String specialNotes) 
  {

  }
  
  /**
	* @stereotype design
	* @param petitionNumber
	* @param closingStatus
	* @roseuid 4395C23902A0
	*/
	public void updateCourtExitPlan(String petitionNumber, String closingStatus) 
	{
	
	}
	
	/**
	  * @stereotype design
	  */
	public void getCasefileForActivation( String supervisionNum )
	{
			
	}
	
	/**
	  * @stereotype design
	  */
	public void submitCasefileForActivation(String supervisionNum )
	{
			
	}


	/**
	  * @stereotype design
	  */
	public void updateJuvenileCasefile(String supervisionNum, Date supervisionEndDate)
	{
			
	}
	
	/**
	  * @stereotype design
	  */
	public void createActivity() {
		
	}
	
	/**
	  * @stereotype design
	  */
	public void updateActivity() {
		
	}
	
	/**
	  * @stereotype design
	  */
	public void getActivities() {
		
	}
	
	/**
	  * @stereotype design
	  */
	public void getActivityDetails() {
		
	}	
	
	/**
	  * @stereotype design
	  */
	public void getCommonAppFinancialInfo(String juvenileNum)
	{
			
	}
	
	/**
	  * @stereotype design
	  */
	public void getCommonAppDocs()
	{
			
	}
	
	/**
	  * @stereotype design
	  */
	public void saveCommonAppDoc()
	{
	
	}
	
	/**
	  * @stereotype design
	  */
	public void saveJournalSummaryDoc()
	{
	
	}
	
	/**
	  * @stereotype design
	  */
	public void getJournalDocs()
	{
	
	}
	
	/**
	  * @stereotype design
	  */
	public void getCommonAppDocByOID(String oid)
	{
		
	}
	
	/**
	  * @stereotype design
	  */
	public void updateExitReportDSMDiagnosis(String dsmDiagnosisId)
	{
			
	}
	
	/**
	  * @stereotype design
	  */
	public void getDSMDiagnosisByClosingInfoId(String closingInfoId)
	{
			
	}
	
	/**
	  * @stereotype design
	  */
	public void updateJuvenileCasefileCorrection( String supervisionNumber,boolean changeCasefileStatus,String casfileStatusId,String changeCasefileType,String prevSupTypeId,String supTypeId)
	{
		
	}
	 /**
	    * @stereotype design
	    * @param supervisionNumber
	    * @roseuid 4395C2350376
	    */
     public void getJournalEntries(String supervisionNumber) 
     {
    
     }
	 
	   /**
	    * @stereotype design
	    * @param juvenileNum
	    * 
	    */     
     public void getJuvenileAlias(String juvenileNum) 
     {
    
     }


	   /**
	    * @stereotype design
	    * @param juvenileNum
	    * 
	    */     
     public void getJuvenileAliasesByJuvNum(String juvenileNum) 
     {
    
     }
     
 	/**
	    * @stereotype design
	    * @param juvenileNum
	    * 
	    */ 
   public void CreateJournalReport() 
   {
  
   }
   
   /**
    * @stereotype design
    * @param juvenileId
    * @param casefileId
    */
	 public void getCasefileDocuments(String juvenileId,String casefileId) 
	 {
	
	 }
	 
	 /**
	   * @stereotype design
	   * @param juvenileNum
	   * @roseuid 43821A0E037B
	   */
	 public void saveAssessmentReferral() 
	 {
	    
	 }
	 
	 /**
	   * @stereotype design
	   * @param juvenileNum
	   * @roseuid 43821A0E037B
	   */
	 public void getAssessmentReferral(String juvenileId) 
	 {
	    
	 }
	 
	 /**
	  * @stereotype design
	  */
		public void saveFacilityDoc()
		{
		
		}
		//US 103958
		/**
		   * @stereotype design
		   * @param detentionId
		   * @roseuid 43821A0E037B
		   *//*
		public void getActivitiesByDetentionIDforFacility(String detentionId) 
		     {
		    
		     }*/
	
		 /**
		   * @stereotype design
		   * @param juvenileNum
		   * @roseuid 43821A0E037B
		   */
		 public void getGangAssessmentReferral(String juvenileId) 
		 {
		    
		 }
}

package pd.juvenilecase.mentalhealth;

import java.util.Date;

/**
 * @stereotype control
 **/
public class JuvenileMentalHealthController
{

	public JuvenileMentalHealthController()
	{
		
	}
	/**
	 * @stereotype design
	 * @param juvenileNumber
	 * @roseuid 43821A0E02EF
	 */
	public void getAllMAYSIAssessments(int juvenileNumber) 
	{
    
	}
	
	/**
	 * @stereotype design
	 * @param sequenceNumber
	 * @roseuid 43821A0E02FE
	 */
	public void getMAYSIDetails(int sequenceNumber) 
	{
    
	}
   
	/**
	 * @stereotype design
	 * @param juvenileNumber
	 * @roseuid 43821A0E031C
	 */
	public void getMAYSITextFileData(String juvenileNumber) 
	{
    
	}
	
	/**
	* @stereotype design
	* @param hasPreviousMAYSI
	* @param facilityType
	* @param location
	* @param lengthOfStay
	* @param referralNumber
	* @roseuid 43821A0E032C
	*/
   public void saveMAYSIMetadata(String assessmentOption,String requestingOfficerId,Date requestDate,String referralNumber,boolean hasPreviousMAYSI,boolean administered,String locationId,String lengthOfStay,String facilityType,String juvenileNumber,String reasonNotDone) 
   {

   }
   
   /**
	* @stereotype design
	* @param sequenceNumber
	* @param assessmentResults
	* @param assessmentReviewDate
	* @param assessmentOfficer
	* @param asessmentOption
	* @roseuid 43821A0E034B
	*/
   public void saveSubsequentMAYSIData(String reviewUserId,Date reviewDate,String reviewComments,String juvenileMAYSIAssessId,String providerTypeReferredId,boolean subReferral,boolean assessmentComplete) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param juvenileNum
	* @roseuid 43821A0E034B
	*/
  public void getMentalHealthHospitalizationList(String juvenileNum) 
  {
   
  }
  
  /**
	* @stereotype design
	* @param hospitalizationId
	* @roseuid 43821A0E034B
	*/
	public void getMentalHealthHospitalizationData(String hospitalizationId) 
	{
	 
	}
	/**
	* @stereotype design
	* @roseuid 43821A0E034C
	*/
	public void createHospitalizationHistory(){
	}

	/**
	* @stereotype design
	* @roseuid 43821A0E034F
	*/

	public void createMentalHealthTestSession()
	{}
	
	/**
	* @stereotype design
	* @param juvenileNum
	* @roseuid 43821A0E039B
	*/
 	public void getMentalHealthTestingResults(String juvenileNum){
     }
 	
	/**
	* @stereotype design
	* @param juvenileNum
	* @roseuid 43821A0E039B
	*/
 	public void getMentalHealthTestingSession(String juvenileNum, String serviceEventId){
     }
 	
 	/**
	* @stereotype design
	* @param testSessID
	* @roseuid 43821A0E039B
	*/
 	public void getMentalHealthTestData(String testSessID)
 	{}
 	
 	/**
	* @stereotype design 
	* @roseuid 43821A0E040B
	*/
 	public void createMentalHealthDSMIV()
 	{}
 	
 	/**
	* @stereotype design 
	* @roseuid 43821A0E040B
	*/
 	public void  createMentalHealthIQTest()
 	{}
 	
 	/**
	* @stereotype design 
	* @roseuid 43821A0E040B
	*/
 	public void  createMentalHealthAchievement()
 	{}
 	
 	/**
	* @stereotype design 
	* @roseuid 43821A0E040B
	*/
 	public void  createMentalHealthAdaptiveFunctioning()
 	{}
 	
 	/**
	* @stereotype design 
	* @roseuid 43821A0E040B
	*/
 	public void  getDSMIVResults()
 	{}
 	/**
	* @stereotype design 
	* @roseuid 43821A0E040B
	*/
 	public void getMentalHealthDSMTestData()
 	{}
 	
 	/**
	* @stereotype design 
	* @roseuid 43821A0E040B
	*/
 	public void getMentalHealthIQResults()
 	{}
 	
 	/**
	* @stereotype design 
	* @roseuid 43821A0E040B
	*/
 	public void getMentalHealthIQTestData()
 	{}
 	
 	/**
	* @stereotype design 
	* @roseuid 43821A0E040B
	*/
 	public void getMentalHealthAchievementResults()
 	{}
 	
 	/**
	* @stereotype design 
	* @roseuid 43821A0E040B
	*/
 	public void getMentalHealthATTestData()
 	{}
 	
 	/**
	* @stereotype design 
	* @roseuid 43821A0E040B
	*/
 	public void  getMentalHealthAFResults()
 	{}
 	/**
	* @stereotype design 
	* @roseuid 43821A0E040B
	*/
 	public void  getMentalHealthAFTestData()
 	{}
 	/**
	* @stereotype design 
	* @roseuid 43821A0E040B
	*/
 	public void  getDSMIVRecord()
 	{}
 	
 	/**
	* @stereotype design 
	* @roseuid 43821A0E040B
	*/
 	public void getMaysiRequests(Date aFindDate, String juvenileNum, String referralNum){}
 	
	/** 
	 * @stereotype design
	 */
	public void getMentalHealthABResults(String juvenileNum) {
	}

	/** 
	 * @stereotype design
	 */
	public void getMentalHealthABTestData(String testId) {
	}

	/** 
	 * @stereotype design
	 */
	public void createMentalHealthAdaptiveBehavior() {
	}
	

	/** 
	 * @stereotype design
	 */
	public void updateSubsequentMAYSIComment(String maysiSubAssessmentId,
							String maysiAssessmentId,
							String existingComments,
							String newComments){
	    
	}
}

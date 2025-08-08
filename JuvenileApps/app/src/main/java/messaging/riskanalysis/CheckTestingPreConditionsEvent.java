//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\CheckInterviewPreconditionsEvent.java

package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class CheckTestingPreConditionsEvent extends RequestEvent 
{
   private String caseFileId;
   private String juvenileNumber;
   private String assessmentType;
   private String traitTypeId;
   private String traitName;
   private String riskpoint;
   
   /**
    * @roseuid 4342C3B102E1
    */
   public CheckTestingPreConditionsEvent() 
   {
    
   }
   
   /**
    * @param caseFileId
    * @roseuid 433C3D3C0383
    */
   public void setCaseFileId(final String string) 
   {
		caseFileId = string;
   }
   
   /**
    * @roseuid 433C3D3C0385
    */
   public String getCaseFileId() 
   {
    	return caseFileId;
   }
   
   /**
    * @param juvenileNumber
    * @roseuid 433C3D3C0387
    */
   public void setJuvenileNumber(final String juvNum) 
   {
		juvenileNumber = juvNum;
   }
   
   /**
    * @roseuid 433C3D3C0389
    */
   public String getJuvenileNumber() 
   {
   		return juvenileNumber;    
   }
	/**
	 * @return
	 */
	public String getAssessmentType()
	{
		return assessmentType;
	}
	
	/**
	 * @param string
	 */
	public void setAssessmentType(String string)
	{
		assessmentType = string;
	}

	/**
	 * @return
	 */
	public String getRiskpoint()
	{
		return riskpoint;
	}
	
	/**
	 * @return
	 */
	public String getTraitName()
	{
		return traitName;
	}
	
	/**
	 * @return
	 */
	public String getTraitTypeId()
	{
		return traitTypeId;
	}
	
	/**
	 * @param i
	 */
	public void setRiskpoint(String i)
	{
		riskpoint = i;
	}
	
	/**
	 * @param string
	 */
	public void setTraitName(String string)
	{
		traitName = string;
	}
	
	/**
	 * @param string
	 */
	public void setTraitTypeId(String string)
	{
		traitTypeId = string;
	}

}

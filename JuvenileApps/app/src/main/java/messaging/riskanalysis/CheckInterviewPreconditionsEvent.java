//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\CheckInterviewPreconditionsEvent.java

package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class CheckInterviewPreconditionsEvent extends RequestEvent 
{
   private String caseFileId;
   private String juvenileNumber;
   
   /**
    * @roseuid 4342C3B102E1
    */
   public CheckInterviewPreconditionsEvent() 
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
}

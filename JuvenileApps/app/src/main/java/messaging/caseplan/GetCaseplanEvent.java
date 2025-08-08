//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\caseplan\\GetCaseplanEvent.java

package messaging.caseplan;

import mojo.km.messaging.RequestEvent;

public class GetCaseplanEvent extends RequestEvent 
{
  private String caseplanId;;
   
   /**
    * @roseuid 4533BCF102F8
    */
   public GetCaseplanEvent() 
   {
    
   }  
  
   /**
    * @param caseplanId
    * @roseuid 45119A640183
    */
   public void setCaseplanId(String caseplanId) 
   {
   		this.caseplanId = caseplanId;
   }
   
   /**
    * @return String
    * @roseuid 45119A64018E
    */
   public String getCaseplanId() 
   {
   		return caseplanId;
   }
	
}

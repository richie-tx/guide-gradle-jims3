//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefileEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;
/**
 * 
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJuvenileCasefileByCasefileIdEvent extends RequestEvent 
{
   private String supervisionNum;
  
   
   /**
    * @roseuid 4278C82101F1
    */
   public GetJuvenileCasefileByCasefileIdEvent() 
   {
    
   }
   
   /**
    * @param supervisionNum
    * @roseuid 4278C7B8038B
    */
   public void setSupervisionNumber(String aSupervisionNum) 
   {
        this.supervisionNum = aSupervisionNum;
   }
   
   /**
    * @return String
    * @roseuid 4278C7B8038D
    */
   public String getSupervisionNum() 
   {
    return this.supervisionNum;
   }

}

/* Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\casefile\\GetCasefileClosingDetailsEvent.java
 *
 *  Created on Deecember 06, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package messaging.casefile;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 * description getter and setter for casefileclosingdetails
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class GetCasefileClosingExceptionsEvent extends RequestEvent 
{
   private String supervisionNumber;
   
   /**
    * @roseuid 439601DF027F
    */
   public GetCasefileClosingExceptionsEvent() 
   {
    
   }
   
   /**
    * @param supervisionNumber
    * @roseuid 4395BC260181
    */
   public void setSupervisionNumber(String supervisionNumber) 
   {
       this.supervisionNumber = supervisionNumber;
   }
   
   /**
    * @return String
    * @roseuid 4395BC26022C
    */
   public String getSupervisionNumber() 
   {
        return supervisionNumber;
   }
}

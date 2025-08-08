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
public class GetJuvenileCasefileEvent extends RequestEvent 
{
   private String supervisionNum;
   private boolean retrieveJuvenile;
   
   /**
    * @roseuid 4278C82101F1
    */
   public GetJuvenileCasefileEvent() 
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
/**
 * @return
 */
public boolean isRetrieveJuvenile()
{
	return retrieveJuvenile;
}

/**
 * @param b
 */
public void setRetrieveJuvenile(boolean retrieveJuvenile)
{
	this.retrieveJuvenile = retrieveJuvenile;
}

}

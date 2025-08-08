//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefilePetitionsEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefileDispositionsEvent extends RequestEvent 
{
   public String petitionNum;
   
   /**
    * @roseuid 42A9A16E02BE
    */
   public GetJuvenileCasefileDispositionsEvent() {}
   
   /**
    * Access method for the petitionNum property.
    * 
    * @return   the current value of the petitionNum property
    */
   public String getPetitionNum()
   {
      return petitionNum;
   }
   
   /**
    * Sets the value of the petitionNum property.
    * 
    * @param aPetitionNum the new value of the petitionNum property
    */
   public void setPetitionNum(String aPetitionNum)
   {
      petitionNum = aPetitionNum;
   }
}

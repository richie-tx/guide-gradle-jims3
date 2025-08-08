//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\referral\\GetJuvenileCasefileVictimWitnessDetailsEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefileVictimWitnessDetailsEvent extends RequestEvent 
{
   public String daLogNum;
   public String sequenceNum;
   
   /**
    * @roseuid 467FB1CD0252
    */
   public GetJuvenileCasefileVictimWitnessDetailsEvent() 
   {
    
   }
   
  
/**
 * @return Returns the daLogNum.
 */
public String getDaLogNum() {
	return daLogNum;
}
/**
 * @param daLogNum The daLogNum to set.
 */
public void setDaLogNum(String daLogNum) {
	this.daLogNum = daLogNum;
}
/**
 * @return Returns the sequenceNum.
 */
public String getSequenceNum() {
	return sequenceNum;
}
/**
 * @param sequenceNum The sequenceNum to set.
 */
public void setSequenceNum(String sequenceNum) {
	this.sequenceNum = sequenceNum;
}
}

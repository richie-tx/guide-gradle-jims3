//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerviolationreport\\PositiveUAHistory.java

package pd.supervision.administercompliance;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class PositiveUAHistory extends PositiveUA
{
   
   /**
    * @roseuid 47DA99E101E1
    */
   public PositiveUAHistory() 
   {
    
   }
   
   public void setPositiveUAHistory(){
	   this.setNcResponseId(super.getNcResponseId());
	   this.setOccurenceDate(super.getOccurenceDate());
	   this.setSubstance(super.getSubstance());
   }
}

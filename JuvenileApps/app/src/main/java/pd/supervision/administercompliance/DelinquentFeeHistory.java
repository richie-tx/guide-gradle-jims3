//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerviolationreport\\DelinquentFeeHistory.java

package pd.supervision.administercompliance;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class DelinquentFeeHistory extends DelinquentFee
{
   /**
    * @roseuid 47DA99DC03A6
    */
   public DelinquentFeeHistory() 
   {
     
   }
   
   public void setDelinquentFeeHistory(){
	   this.setAmountDelinquent(super.getAmountDelinquent());
	   this.setAmountOrdered(super.getAmountOrdered());
	   this.setNcResponseId(super.getNcResponseId());
	   this.setPaidToDate(super.getPaidToDate());
	   this.setPayType(super.getPayType());
   }
}

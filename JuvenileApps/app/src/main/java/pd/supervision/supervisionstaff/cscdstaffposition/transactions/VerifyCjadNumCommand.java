//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionstaff\\cscdstaffposition\\transactions\\VerifyCjadNumCommand.java

package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import messaging.cscdstaffposition.VerifyCjadNumEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class VerifyCjadNumCommand implements ICommand 
{
   
   /**
    * @roseuid 460BFAA30378
    */
   public VerifyCjadNumCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 460BD54902FE
    */
   public void execute(IEvent event) 
   {
       VerifyCjadNumEvent reqEvent = (VerifyCjadNumEvent) event;
       CSCDStaffPositionHelper.verifyCjadNum(reqEvent);
   }
   
   /**
    * @param event
    * @roseuid 460BD549030D
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 460BD549030F
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    * @roseuid 460BFAA30397
    */
   public void update(Object updateObject) 
   {
    
   }
}

//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\transactions\\UpdateNCResponseCommand.java

package pd.supervision.administercompliance.transactions;

import java.sql.Date;

import messaging.administercompliance.UpdateUrinalysisEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.DateUtil;
import pd.common.CommandUtil;
import pd.supervision.administercompliance.Urinalysis;

/*
 * 
 * @author ryoung
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateUrinalysisCommand implements ICommand 
{
   
   /**
    * @roseuid 47DA96A80002
    */
   public UpdateUrinalysisCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D5AF780011
    */
   public void execute(IEvent event) 
   {
   	   UpdateUrinalysisEvent ncEvent = ( UpdateUrinalysisEvent ) event;
   	   
 	   Urinalysis urinalysis = new Urinalysis();
 	  
   	   java.util.Calendar cal = java.util.Calendar.getInstance();
	   java.util.Date utilDate = cal.getTime();
	   Date sqlDate = new Date( utilDate.getTime());
	    
 	   urinalysis.setPushDate( DateUtil.dateToString(sqlDate, "yyyy-MM-dd HH:mm:ss"));
 	   urinalysis.setSpn( Integer.parseInt( ncEvent.getDefendantId() ));
 	   urinalysis.setfName( ncEvent.getfName() );
 	   urinalysis.setmName( ncEvent.getmName() );
 	   urinalysis.setlName( ncEvent.getlName() );
 	   urinalysis.setPoi( ncEvent.getPoi() );
 	   urinalysis.setDob( ncEvent.getDob() );
 	   urinalysis.setSex( ncEvent.getSex() );
 	   urinalysis.setRace( ncEvent.getRace() );
 	   urinalysis.setCrt( ncEvent.getCrt() );
   }
   
   /**
    * @param event
    * @roseuid 47D5AF780013
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D5AF78001F
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 47D5AF780021
    */
   public void update(Object anObject) 
   {
    
   }  
}

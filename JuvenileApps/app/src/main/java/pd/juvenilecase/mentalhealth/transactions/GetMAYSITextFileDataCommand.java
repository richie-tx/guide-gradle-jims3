//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetMAYSITextFileDataCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import java.util.Iterator;

import pd.juvenilecase.mentalhealth.JuvenileMAYSIComplete;
import pd.juvenilecase.mentalhealth.JuvenileMaysiDoc;
import messaging.mentalhealth.GetMAYSIDetailsEvent;
import messaging.mentalhealth.GetMAYSITextFileDataEvent;
import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.mentalhealth.reply.MAYSIDocResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * 
 * @author dapte
 *
 * This command retrieves the contents of the MAYSI roll-up text file
 * for the Juvenile. It uses the juvenile number to locate the MAYSI 
 * file. This file resides on the "juvenile probation server" at:
 *              juv_probation/hcjuvapp/Project2/MAYSI
 * and is named as s<JuvenileNumber/>Data.txt.
 * 
 * This comamnd talks to the architecture "File Reader Component" to 
 * retrieve the contents of the requested file.
 * 
 * The structure of the ResponseEvent has not been decided yet. It would
 * depend upon the services of the architecture component.
 * 
 */
public class GetMAYSITextFileDataCommand implements ICommand 
{
   
   /**
    * @roseuid 42791F8F036B
    */
   public GetMAYSITextFileDataCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42791D570247
    */
   public void execute(IEvent event) 
   {
   	GetMAYSITextFileDataEvent mEvent = (GetMAYSITextFileDataEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	JuvenileMaysiDoc myDoc;
	try{
		 myDoc = JuvenileMaysiDoc.find(mEvent.getJuvenileNumber());
	}
	catch (Exception e){
		return;
	}
	if (myDoc!=null)
	{
		MAYSIDocResponseEvent myRespEvent=new MAYSIDocResponseEvent();
		myRespEvent.setJuvenileNum(myDoc.getJuvenileNum());
		myRespEvent.setMaysiDoc(myDoc.getMaysiDoc());
		dispatch.postEvent(myRespEvent);
	}

   }
   
   /**
    * @param event
    * @roseuid 42791D570251
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42791D570253
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42791D570255
    */
   public void update(Object anObject) 
   {
    
   }
   

}

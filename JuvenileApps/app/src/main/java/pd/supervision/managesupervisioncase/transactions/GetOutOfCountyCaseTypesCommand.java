//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managesupervisioncase\\transactions\\GetOutOfCountyCaseTypesCommand.java

package pd.supervision.managesupervisioncase.transactions;

import java.util.Iterator;

import messaging.supervisionoptions.reply.CourtResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.SupervisionConstants;
import pd.supervision.Court;

public class GetOutOfCountyCaseTypesCommand implements ICommand 
{
   
   /**
    */
   public GetOutOfCountyCaseTypesCommand() 
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	Iterator iter = Court.findAll();
	while (iter.hasNext())
	{
		Court court = (Court) iter.next();
		CourtResponseEvent evt = new CourtResponseEvent();
		// TODO This if condition will go away when m204 program is modified to filter out courts for Common Supervision
		// TODO Update 05/09/2008: This condition has been updated to omit Justice of Peace Courts
		if (SupervisionConstants.OUT_OF_COUNTY_COURT.equals(court.getCourtCategory()) ) // ||
			//SupervisionConstants.JUSTICE_OF_PEACE_COURT.equals(court.getCourtCategory()))
		{
			evt.setCourtNumber(court.getCourtNumber());
			evt.setCourtCategory(court.getCourtCategory());
			evt.setCourtId(court.getOID().toString());
			evt.setDescription(court.getDescription());
			dispatch.postEvent(evt);
		}
	}
   }
   
   /**
    * @param event
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    */
   public void update(Object updateObject) 
   {
    
   }
   
}

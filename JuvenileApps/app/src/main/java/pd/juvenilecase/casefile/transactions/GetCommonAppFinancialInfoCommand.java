package pd.juvenilecase.casefile.transactions;

import java.util.Collection;

import messaging.casefile.GetCommonAppFinancialInfoEvent;
import messaging.casefile.reply.CommonAppFinancialResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.Juvenile;
import pd.juvenilecase.family.JuvenileFamilyHelper;

public class GetCommonAppFinancialInfoCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC54501B8
    */
   public GetCommonAppFinancialInfoCommand() 
   {
   }
   
	/**
	 * @param event
	 * @roseuid 448D7EEE03B2
	 */
	public void execute(IEvent event) 
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		GetCommonAppFinancialInfoEvent request = (GetCommonAppFinancialInfoEvent)event;
		String juvNum = request.getJuvenileNum();
		// Profile stripping fix
		//Juvenile juvenile = Juvenile.find(juvNum);
		Juvenile juvenile = Juvenile.findJCJuvenile(juvNum);
		//
		CommonAppFinancialResponseEvent resp = new CommonAppFinancialResponseEvent();
		Collection financialInfo = JuvenileFamilyHelper.buildFinancialHistory(juvenile);
		resp.setFinacialInfo(financialInfo);
		dispatch.postEvent(resp);		
	}

	
   /**
    * @param event
    * @roseuid 448D7EEE03B4
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 448D7EEE03BB
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 448D7EEE03BD
    */
   public void update(Object anObject) 
   {
    
   }
   
}

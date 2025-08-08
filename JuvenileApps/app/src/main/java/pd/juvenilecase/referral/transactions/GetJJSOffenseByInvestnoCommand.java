//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileOffensesCommand.java

package pd.juvenilecase.referral.transactions;

import java.util.Iterator;

import naming.PDJuvenileCaseConstants;

import pd.juvenilecase.referral.JJSOffense;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.referral.GetJJSOffenseByInvestnoEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * 
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJJSOffenseByInvestnoCommand implements ICommand 
{
   
   /**
    * @roseuid 42A9A3020387
    */
   public GetJJSOffenseByInvestnoCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42A99B980159
    */
   public void execute(IEvent event) 
   {
	   GetJJSOffenseByInvestnoEvent request = (GetJJSOffenseByInvestnoEvent) event;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
    	
		Iterator<JJSOffense> offenseIter = JJSOffense.findAll( request );	
    	while (offenseIter.hasNext())
    	{
    		JJSOffense o = (JJSOffense) offenseIter.next();
    		JJSOffenseResponseEvent resp = new JJSOffenseResponseEvent();
    		resp.setTopic(PDJuvenileCaseConstants.JUVENILE_OFFENSES_TOPIC);
    		resp.setJuvenileNum(o.getJuvenileNum());
    		resp.setOffenseDate(o.getOffenseDate());
    		resp.setOffenseDescription(o.getOffenseDescription());
    		resp.setReferralNum(o.getReferralNum());
    		resp.setSequenceNum(o.getSequenceNum());
    		resp.setCatagory(o.getCatagory());
    		resp.setCitationCode(o.getCitationCode());
    		resp.setCitationSource(o.getCitationSource());
    		resp.setSequenceNum(o.getSequenceNum());
    		resp.setInvestigationNum(o.getInvestigationNumber());
    		resp.setOffenseReportGroup(o.getOffenseReportGroup());
    		resp.setOffenseCodeId(o.getOffenseCodeId());
    		resp.setOffenseCode(o.getOffenseCode().getOffenseCode());
    		resp.setSeveritySubtype(o.getOffenseCode().getSeveritySubtype()); //added for user-story #32226
    		
    		dispatch.postEvent(resp);
    	}
   }
   
   /**
    * @param event
    * @roseuid 42A99B980162
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42A99B98016B
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42A99B98016D
    */
   public void update(Object anObject) 
   {
    
   }
}

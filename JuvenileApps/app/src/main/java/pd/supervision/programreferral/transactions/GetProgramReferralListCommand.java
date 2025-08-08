// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\supervision\\calendar\\transactions\\GetProgramReferralListCommand.java

package pd.supervision.programreferral.transactions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.error.reply.ErrorResponseEvent;
import messaging.programreferral.GetProgramReferralListEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.supervision.programreferral.JuvenileProgramReferral;

public class GetProgramReferralListCommand implements ICommand {

	/**
	 * @roseuid 463BA5250088
	 */
	public GetProgramReferralListCommand() {

	}

	/**
	 * @param event
	 * @roseuid 463A437A013B
	 */
	public void execute(IEvent event) {
		GetProgramReferralListEvent prevent = (GetProgramReferralListEvent)event;
		
		IHome home = new Home();
		MetaDataResponseEvent programReferralMetaData = (MetaDataResponseEvent)home.findMeta(prevent, JuvenileProgramReferral.class);
		

		if( programReferralMetaData != null && programReferralMetaData.getCount() < 2000 )
		{
		
			Iterator progRefIter = JuvenileProgramReferral.findAll(event);
			Map juvMap = new HashMap();
			while (progRefIter.hasNext()){
				JuvenileProgramReferral programReferral = (JuvenileProgramReferral)progRefIter.next();
				// Profile stripping fix   97547
				//Juvenile juv = null;
				JuvenileCore juv = null;
				if(juvMap.containsKey(programReferral.getJuvenileId())){
					//juv = (Juvenile) juvMap.get(programReferral.getJuvenileId());
				    juv = (JuvenileCore) juvMap.get(programReferral.getJuvenileId());
				}
				juv = programReferral.postValueObject(prevent.isDetailsNeeded(), juv);
				if (juv != null) {
					juvMap.put(juv.getJuvenileNum(), juv);				
				}
			}
			
		}
		else
		{
			ErrorResponseEvent errorResp = new ErrorResponseEvent();
			errorResp.setMessage("error.max.limit.exceeded");
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errorResp);
		}
		
	}
	
	/**
	 * @param event
	 * @roseuid 463A437A013D
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 463A437A014A
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 463A437A014C
	 */
	public void update(Object anObject) {

	}

}

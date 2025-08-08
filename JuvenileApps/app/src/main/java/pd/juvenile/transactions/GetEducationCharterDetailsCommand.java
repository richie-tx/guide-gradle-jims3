package pd.juvenile.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import pd.juvenile.JuvenileCharterGED;
import pd.juvenile.JuvenileCharterPostReleaseTracking;
import pd.juvenile.JuvenileCharterVEP;
import messaging.juvenile.GetEducationCharterDetailsEvent;
import messaging.juvenile.reply.CharterDetailsResponseEvent;
import messaging.juvenile.reply.CharterGEDResponseEvent;
import messaging.juvenile.reply.CharterPostReleaseResponseEvent;
import messaging.juvenile.reply.CharterVEPResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;

public class GetEducationCharterDetailsCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		GetEducationCharterDetailsEvent requestEvent = (GetEducationCharterDetailsEvent)anEvent;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		CharterDetailsResponseEvent resp = new CharterDetailsResponseEvent();
		List juvenileCharterGEDs = new ArrayList();
		List juvenileCharterVEPs = new ArrayList();
		List juvenileCharterPostReleases = new ArrayList();
		
		Iterator charterGEDs = JuvenileCharterGED.findAll("juvenileNum", requestEvent.getJuvenileNum());
		while (charterGEDs.hasNext())
		{
			JuvenileCharterGED charterGED = (JuvenileCharterGED) charterGEDs.next();
			CharterGEDResponseEvent responseGED = charterGED.getResponseEvent();
			if (responseGED != null)
			{
				juvenileCharterGEDs.add(responseGED);
			}
		} 
		
		Iterator charterVEPs = JuvenileCharterVEP.findAll("juvenileNum", requestEvent.getJuvenileNum());
		while (charterVEPs.hasNext())
		{
			JuvenileCharterVEP charterVEP = (JuvenileCharterVEP) charterVEPs.next();
			CharterVEPResponseEvent responseVEP = charterVEP.getResponseEvent();
			if (responseVEP != null)
			{
				juvenileCharterVEPs.add(responseVEP);
			}
		} 
		
		Iterator charterPRs = JuvenileCharterPostReleaseTracking.findAll("juvenileNum", requestEvent.getJuvenileNum());
		while (charterPRs.hasNext())
		{
			JuvenileCharterPostReleaseTracking charterPR = (JuvenileCharterPostReleaseTracking) charterPRs.next();
			CharterPostReleaseResponseEvent responsePR = charterPR.getResponseEvent();
			if (responsePR != null)
			{
				juvenileCharterPostReleases.add(responsePR);
			}
		} 
		
		if ( juvenileCharterGEDs.size() > 0) {
			resp.setJuvenileCharterGEDs(juvenileCharterGEDs);
		}
		if ( juvenileCharterVEPs.size() > 0) {
			resp.setJuvenileCharterVEPs(juvenileCharterVEPs);
		}
		if ( juvenileCharterPostReleases.size() > 0) {
			resp.setJuvenileCharterPostReleases(juvenileCharterPostReleases);
		}
		dispatch.postEvent(resp);
	}

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}

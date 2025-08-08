//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileDispositionsCommand.java

package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.GetJuvenileCasefilePetitionDispositionsEvent;
import messaging.juvenilecase.reply.JuvenileCourtDispositionResponseEvent;
import messaging.juvenilecase.reply.JuvenileDispositionResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.referral.GetJuvenileCasefileDispositionsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.AttributeEvent;
import naming.PDJuvenileCaseConstants;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.referral.Disposition;
import pd.juvenilewarrant.JJSPetition;


public class GetJuvenileCasefilePetitionDispositionsCommand implements ICommand
{

	/**
	 * @roseuid 42A9A301026D
	 */
	public GetJuvenileCasefilePetitionDispositionsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42A99B990240
	 */
	public void execute(IEvent event)
	{
		GetJuvenileCasefilePetitionDispositionsEvent pet = (GetJuvenileCasefilePetitionDispositionsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetJJSCourtEvent jjsevent = new GetJJSCourtEvent();
		jjsevent.setJuvenileNumber(pet.getJuvenileNum());
		jjsevent.setReferralNumber(pet.getReferralNum());
		Iterator i = JJSCourt.findAll(jjsevent);
		AttributeEvent disEvent = new AttributeEvent();
		disEvent.setAttributeName("petitionNum");
		while (i.hasNext())
		{
			JJSCourt jjsCourt = (JJSCourt) i.next();
			disEvent.setAttributeValue(jjsCourt.getPetitionNumber());
			Iterator iter = Disposition.findAll(disEvent);
			while (iter.hasNext())
			{
				Disposition dis = (Disposition) iter.next();
				JuvenileCourtDispositionResponseEvent resp = new JuvenileCourtDispositionResponseEvent();
				resp.setTopic(PDJuvenileCaseConstants.JUVENILE_COURT_DISPOSITION_TOPIC);
				resp.setDispositionDate(dis.getDispositionDate());
				resp.setJudgementDate(dis.getJudgementDate());
				resp.setDispositionNumber(dis.getOID().toString());
				resp.setPetitionNum(jjsCourt.getPetitionNumber());
				resp.setCourtDate(jjsCourt.getCourtDate());
				resp.setCourtId(jjsCourt.getCourtId());
				resp.setCourtTime(jjsCourt.getCourtTime());
				resp.setHearingTypeId(jjsCourt.getHearingType());
				dispatch.postEvent(resp);
			}
		}
	}
	
   /**
	 * @param event
	 * @roseuid 42A99B990242
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42A99B99024A
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42A99B99024C
	 */
	public void update(Object anObject)
	{

	}
}

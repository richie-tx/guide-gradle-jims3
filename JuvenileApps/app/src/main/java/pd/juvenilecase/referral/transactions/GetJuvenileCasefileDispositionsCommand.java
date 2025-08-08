//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileDispositionsCommand.java

package pd.juvenilecase.referral.transactions;

import java.util.Iterator;

import naming.PDJuvenileCaseConstants;

import pd.juvenilecase.referral.Disposition;
import messaging.juvenilecase.reply.JuvenileDispositionResponseEvent;
import messaging.referral.GetJuvenileCasefileDispositionsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.AttributeEvent;

public class GetJuvenileCasefileDispositionsCommand implements ICommand
{

	/**
	 * @roseuid 42A9A301026D
	 */
	public GetJuvenileCasefileDispositionsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42A99B990240
	 */
	public void execute(IEvent event)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetJuvenileCasefileDispositionsEvent disp = (GetJuvenileCasefileDispositionsEvent) event;
////		AttributeEvent att = new AttributeEvent();
////		att.setAttributeName("petitionNum");
//		att.setAttributeValue(disp.getPetitionNum());

		// RRY I just used the disp event instead
		Iterator i = Disposition.findAll(disp);
		while (i.hasNext())
		{
			Disposition d = (Disposition) i.next();
			JuvenileDispositionResponseEvent resp = new JuvenileDispositionResponseEvent();
			resp.setTopic(PDJuvenileCaseConstants.JUVENILE_DISPOSITION_TOPIC);
			resp.setDispositionDate(d.getDispositionDate());
			resp.setJudgement(d.getJudgement());
			resp.setJudgementDate(d.getJudgementDate());
			resp.setProbationTime(getTime(d.getProbationTimeInd(), d.getProbationTime()));
			resp.setTYCTime(getTime(d.getTYCTimeInd(), d.getTYCTime()));
			dispatch.postEvent(resp);
		}
	}

	/**
	 * Determines if the time is in months or years.
	 */
	private String getTime(String indicator, String time)
	{
		if (indicator != null && !indicator.trim().equals(""))
		{
			if (indicator.trim().equalsIgnoreCase("Y"))
			{
				return time + " years";
			}
			else
			{
				return time + " months";
			}
		}
		return time;
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

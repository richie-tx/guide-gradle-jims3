//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetAllCourtsCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import messaging.supervisionoptions.GetFilteredCourtsEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.SupervisionOptionsConstants;
import pd.supervision.Court;

public class GetFilteredCourtsCommand implements ICommand
{

	/**
	 * @roseuid 42F7C438037A
	 */
	public GetFilteredCourtsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42F7997B031E
	 */
	public void execute(IEvent event)
	{
		GetFilteredCourtsEvent filteredCourtsEvent = (GetFilteredCourtsEvent) event;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		Iterator iter = Court.findAll(filteredCourtsEvent);
		while (iter.hasNext()){
			Court court = (Court) iter.next();
			CourtResponseEvent evt = new CourtResponseEvent();
			// TODO This if condition will go away when m204 program is modified to filter out courts for Common Supervision
			if (	("CC".equals(court.getCourtCategory())
					|| "CR".equals(court.getCourtCategory())
					|| "OC".equals(court.getCourtCategory())
					|| "CV".equals(court.getCourtCategory())
					|| "FAM".equals(court.getCourtCategory())
					|| "JP".equals(court.getCourtCategory())
					|| "JUV".equals(court.getCourtCategory()))
					&& ((court.getJudgeFirstName()!= null 
							&& !court.getJudgeFirstName().equals(""))
						|| (court.getJudgeLastName()!= null 
							&& !court.getJudgeLastName().equals("")) ))
			{
				evt.setCourtNumber(court.getCourtNumber());
				evt.setCourtCategory(court.getCourtCategory());
				evt.setCourtId(court.getOID().toString().trim());
				evt.setJudgeFirstName(court.getJudgeFirstName());
				evt.setJudgeLastName(court.getJudgeLastName());
				evt.setDescription(court.getDescription());
				evt.setTopic(SupervisionOptionsConstants.COURT);
				dispatch.postEvent(evt);
			}
		}
	}

	/**
	 * @param event
	 * @roseuid 42F7997B0320
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42F7997B0322
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42F7997B032D
	 */
	public void update(Object anObject)
	{

	}

}

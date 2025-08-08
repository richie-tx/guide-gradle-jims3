package pd.juvenilecase.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import naming.PDCodeTableConstants;

import messaging.juvenilecase.GetJCCConvictionEvent;
import messaging.juvenilecase.GetJCCDefendantEvent;
import messaging.juvenilecase.GetJPCourtReferralsEvent;
import messaging.juvenilecase.reply.JPCourtReferralResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.DateUtil;
import pd.codetable.Code;
import pd.codetable.PDCodeHelper;
import pd.codetable.PDCodeTableHelper;
import pd.juvenilecase.JCCConviction;
import pd.juvenilecase.JCCDefendant;
import ui.common.CodeHelper;

/**
 * 
 */
public class GetJPCourtReferralsCommand implements ICommand
{

	/**
	 * 
	 */
	public GetJPCourtReferralsCommand()
	{

	}

	/**
	 * @param event
	 * 
	 */
	public void execute(IEvent event)
	{
		GetJPCourtReferralsEvent request = (GetJPCourtReferralsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		List convictions = new ArrayList();
		GetJCCConvictionEvent queryEvt = new GetJCCConvictionEvent(); 
		queryEvt.setM204JuvNumber( request.getM204JuvNumber() );
		Iterator iter = JCCConviction.findAll( queryEvt );
		while ( iter.hasNext() )
		{
			JCCConviction conviction = (JCCConviction)iter.next();
			JPCourtReferralResponseEvent response = new JPCourtReferralResponseEvent();
			//response.set( defendant.getM204JuvNumber() );
			response.setOffenseId( conviction.getOffenseId() );
			
			//TODO Add Offense Description.
			response.setOffenseDescription( CodeHelper.getOffenseCodeDescription(conviction.getOffenseId()));
			
			///TODO Set Court name (not ID).
			Code court = conviction.getCourt();
			if(court != null) {
				response.setCourtName(court.getDescription());
			}
			
			
			response.setConvictionDate( DateUtil.IntToDate( conviction.getConvictionDate(),DateUtil.DATE_FMT_2));
			response.setFileDate( DateUtil.IntToDate(conviction.getFilingDate(),DateUtil.DATE_FMT_2));
			response.setDisposition( conviction.getDisposition() );
			response.setCaseNumber(conviction.getCaseNumber());
			
			dispatch.postEvent(response);			
		}
		
	}

	/**
	 * @param event
	 * 
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * 
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * 
	 */
	public void update(Object anObject)
	{

	}
}

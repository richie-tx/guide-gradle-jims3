package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetCasefileWithReferralsEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefileReferrals;

/**
 * 
 * 
 * 
 */
public class GetCasefileWithReferralsCommand implements ICommand
{

	/**
	 * @roseuid 42791F9003A9
	 */
	public GetCasefileWithReferralsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42791D5702FF
	 */
	public void execute(IEvent event)
	{
		
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		
			
			GetCasefileWithReferralsEvent searchEvent = (GetCasefileWithReferralsEvent)event;	
			
			Iterator<JuvenileCasefileReferrals> iter = JuvenileCasefileReferrals.findAll(searchEvent);

			while (iter.hasNext())
			{
				JuvenileCasefileReferrals caseRef = (JuvenileCasefileReferrals)iter.next();
	
				JuvenileCasefileReferralDetailResponseEvent resp = new JuvenileCasefileReferralDetailResponseEvent();
				resp.setReferralNumber(caseRef.getReferralNum());
				resp.setCaseFileId(caseRef.getCasefileId());
				resp.setOfficerFName(caseRef.getOfficerFName());
				resp.setOfficerMName(caseRef.getOfficerMName());
				resp.setOfficerLName(caseRef.getOfficerLName());
				resp.setAssignOfficerId(caseRef.getAssignOfficerId());
				resp.setJuvenileId(caseRef.getJuvenileId());
				resp.setAssignmentDate(caseRef.getAssignmentDate());
				resp.setSupervisionTypeCd(caseRef.getSupervisionTypeCd());
				resp.setSupervisionTypeDesc(caseRef.getSupervisionTypeDesc());
				
				dispatch.postEvent(resp);
			}	

	}

	/**
	 * @param event
	 * @roseuid 42791D570301
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42791D570303
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42791D57030E
	 */
	public void update(Object anObject)
	{

	}

}

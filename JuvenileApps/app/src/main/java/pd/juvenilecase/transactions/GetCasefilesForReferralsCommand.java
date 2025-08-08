package pd.juvenilecase.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import naming.PDCodeTableConstants;
import messaging.juvenilecase.GetCasefilesForReferralsEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.Code;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.SupervisionTypeMap;

/**
 * 
 * 
 * 
 */
public class GetCasefilesForReferralsCommand implements ICommand
{

    /**
     * @roseuid 42791F9003A9
     */
    public GetCasefilesForReferralsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42791D5702FF
     */
    public void execute(IEvent event)
    {

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	GetCasefilesForReferralsEvent searchEvent = (GetCasefilesForReferralsEvent) event;
	JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
	resp.setReferralNumber(searchEvent.getReferralNum());
	Collection<JuvenileCasefileReferral> coll = new ArrayList<JuvenileCasefileReferral>();
	SupervisionTypeMap sMap = null;
	String supervisionCategory = null;
	
	Iterator<JuvenileCasefileReferral> iter = JuvenileCasefileReferral.findAll(searchEvent);
	while (iter.hasNext())
	{
	    JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) iter.next();
	    if (caseRef != null)
	    {
		Iterator<SupervisionTypeMap> supervisioncategoryIter = SupervisionTypeMap.findAll("supervisionTypeId", caseRef.getSupervisionTypeCd());
		Code code = Code.find(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, caseRef.getSupervisionTypeCd());
		if (code != null)
		{
		    caseRef.setSupervisionType(code.getDescription()); //set Supervision type desc
		}
		if (supervisioncategoryIter.hasNext())
		{
		    sMap = supervisioncategoryIter.next();
		    if (sMap != null)
		    {
			supervisionCategory = sMap.getSupervisionCatId();
			caseRef.setSupervisionCat(supervisionCategory); // category.
		    }
		}
		resp.setCasefileId( caseRef.getCaseFileId());
	    }
	    coll.add(caseRef);
	}
	
	resp.setCasefileReferrals(coll);
	dispatch.postEvent(resp);
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

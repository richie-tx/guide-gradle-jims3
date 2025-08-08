package pd.juvenilecase.caseplan.transactions;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.FastArrayList;

import naming.ActivityConstants;
import naming.JuvenileCasefileControllerServiceNames;

import pd.juvenilecase.caseplan.CasePlan;
import messaging.casefile.GetActivitiesEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.caseplan.GetCaseplansByJuvenileNumberEvent;
import messaging.caseplan.reply.CaseplanListResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class GetCaseplansByJuvenileNumberCommand implements ICommand
{

    /**
     * @roseuid 4533B81001A8
     */
    public GetCaseplansByJuvenileNumberCommand()
    {

    }

    /**
     * @param event
     * @roseuid 45119A64015D
     */
    public void execute(IEvent event)
    {
        GetCaseplansByJuvenileNumberEvent request = (GetCaseplansByJuvenileNumberEvent) event;
        String juvenileNum = request.getJuvenileNum();

        //CaseplanDetailsResponseEvent cpResponse = new CaseplanDetailsResponseEvent();
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        Iterator cps = CasePlan.findByJuvenileNum(request);
        List caseplanList = new FastArrayList();
        while (cps.hasNext())
        {
            CasePlan cp = (CasePlan) cps.next();
            caseplanList.add(cp); // save it in the list for later iterator
            CaseplanListResponseEvent cpResponse = new CaseplanListResponseEvent();
            cpResponse.setCaseplanID(cp.getCaseplanID());
            cpResponse.setSupervisionNumber(cp.getSupervisionNumber());
            cpResponse.setCreateDate(cp.getCreateTimestamp());
            cpResponse.setReviewDate(cp.getReviewDate());
            cpResponse.setStatusId(cp.getStatusId());
            cpResponse.setStatus(cp.getStatus().getDescription());
            dispatch.postEvent(cpResponse);
        }

        GetActivitiesEvent activityEvt = 
			(GetActivitiesEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETACTIVITIES);
        activityEvt.setActivityTypeId(ActivityConstants.ACTIVITY_TYPE_CASEPLAN);
        activityEvt.setActivityCodeId("");
        activityEvt.setCasefileID("");
        activityEvt.setCategoryId(ActivityConstants.ACTIVITY_CATEGORY_REPORTING);
        activityEvt.setJuvenileNum(juvenileNum);
        
        List responses = MessageUtil.postRequestListFilter(activityEvt, ActivityResponseEvent.class);
        
        int len = responses.size();
        for(int i=0;i<len;i++)
        {
            ActivityResponseEvent act = (ActivityResponseEvent) responses.get(i);
            String codeID = act.getActivityId();
            String title = act.getTitle() == null ? "" : act.getTitle();
            if (codeID.equalsIgnoreCase(ActivityConstants.CASE_PLAN_ACCEPTED_BY_CLM)
                    || (codeID.equalsIgnoreCase(ActivityConstants.CASE_PLAN_MODIFIED)))
            {
                if (checkIfMatchesCaseplan(act.getCasefileId(), caseplanList))
                {                    
                    /*
                    ActivityResponseEvent response = new ActivityResponseEvent();
                    response.setActivityDate(act.getActivityDate());
                    response.setActivityId(act.getOID().toString());
                    response.setCategoryId(act.getActivityCategoryId());
                    response.setTypeId(act.getActivityTypeId());
                    response.setCodeId(act.getActivityCodeId());
                    response.setCasefileId(act.getSupervisionNumber());
                    response.setComments(act.getTitle());
                    */
                    dispatch.postEvent(act);
                }
            }
        }

    }

    private boolean checkIfMatchesCaseplan(String casefileID, List caseplanList)
    {
        boolean matches = false;
        int len = caseplanList.size();
        for(int i=0;i<len;i++)
        {
            CasePlan cp = (CasePlan) caseplanList.get(i);
            if (cp.getSupervisionNumber().equals(casefileID))
            {
                matches = true;
                break;
            }
        }
        return matches;
    }

    /**
     * @param event
     * @roseuid 45119A64015F
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 45119A640166
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 45119A64016F
     */
    public void update(Object anObject)
    {

    }

}

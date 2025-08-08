package pd.juvenilecase.caseplan.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.casefile.GetActivitiesEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.caseplan.GetJPOReviewDataEvent;
import messaging.caseplan.reply.JPOReviewRiskAnalysisResponseEvent;
import messaging.juvenilecase.reply.RuleDetailResponseEvent;
import messaging.programreferral.GetProgramReferralListEvent;
import messaging.programreferral.ProgramReferralRetrieverAttribute;
import messaging.programreferral.ProgramReferralRetrieverAttributeImpl;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import naming.ActivityConstants;
import naming.RiskAnalysisConstants;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.casefile.Activity;
import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.caseplan.CaseplanHelper;
import pd.juvenilecase.caseplan.Goal;
import pd.juvenilecase.factory.IJuvenileCaseworkResponseFactory;
import pd.juvenilecase.factory.JuvenileCaseworkResponseFactory;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.juvenilecase.riskanalysis.RiskAnalysisProgress;
import pd.juvenilecase.riskanalysis.RiskAnalysisRecommendation;
import pd.juvenilecase.riskanalysis.RiskRecommendation;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRule;
import pd.juvenilecase.rules.RuleGroupConditionView;
import pd.supervision.programreferral.JuvenileProgramReferral;

public class GetJPOReviewDataCommand implements ICommand
{

    /**
     * @roseuid 4533B83100E9
     */
    public GetJPOReviewDataCommand()
    {

    }

    /**
     * @param event
     * @roseuid 45119A6303A9
     */
    public void execute(IEvent event)
    {
        GetJPOReviewDataEvent reqEvent = (GetJPOReviewDataEvent) event;
        String casefileId = reqEvent.getCasefileId();
        String juvenileNum = reqEvent.getJuvenileNum();
        CaseplanHelper.retrieveJuvenileInfo(juvenileNum);

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        Iterator iter = PDRiskAnalysisHelper.getRiskAnalysisByCasefileIdAndAssessmentType(casefileId,
                RiskAnalysisConstants.RISK_TYPE_PROGRESS);

        while (iter.hasNext())
        {
            RiskAnalysis risk = (RiskAnalysis) iter.next();
            RiskAnalysisProgress prog = RiskAnalysisProgress.getRiskAnalysisProgressByRiskAnalysisId(risk.getOID());
            JPOReviewRiskAnalysisResponseEvent re = null;
            if (prog != null)
            {
                re = new JPOReviewRiskAnalysisResponseEvent();
                re.setEntryDate(prog.getEnteredDate());
                re.setComments(prog.getComments());
               
                
                //re.setRecommendation(risk.getRecommendation());
                List recomList = CollectionUtil.iteratorToList(risk.getRecommendations().iterator());
                if (recomList.size() > 0){
                	RiskAnalysisRecommendation raRecomm = (RiskAnalysisRecommendation) recomList.get(0);
                	RiskRecommendation riskRecomm = raRecomm.getRecommendation();
                	re.setRecommendation(riskRecomm.getRecommendation());
                }
                
                dispatch.postEvent(re);
            }

        }

         GetActivitiesEvent getActivities = new GetActivitiesEvent();
         getActivities.setCasefileID(casefileId);
         getActivities.setJuvenileNum(juvenileNum);
         
        Activity act = null; 
        ActivityResponseEvent actResp = null ;
 		Iterator<Activity> actIter = Activity.findAll( getActivities );
 		
 		while(actIter.hasNext())
 		{
 			act = actIter.next();
 			if( act != null )
 			{
 				actResp = act.valueObject();
 				if (actResp.getCodeId().equals(ActivityConstants.MODIFY_SUPERVISION_RULES)
 	                    || actResp.getCodeId().equals(ActivityConstants.CASE_PLAN_REVIEWED))
 	            {                
 	                dispatch.postEvent( actResp );
 	            }
 			}
 		}

        JuvenileCasefile cf = JuvenileCasefile.find( casefileId) ;
        CasePlan cp = null;
        if (cf == null || cf.getCaseplanId() == null || cf.getCaseplanId().equals(""))
        {

        }
        else
        {
            cp = CasePlan.find(cf.getCaseplanId());
        }
        // get goals and create goallistresponsevent
        Iterator ite = cp.getTheGoal().iterator();
        while (ite.hasNext())
        {
            Goal goal = (Goal) ite.next();
            // get the rules
            Iterator ruleIte = goal.getRules().iterator();
            IJuvenileCaseworkResponseFactory responseFactory = new JuvenileCaseworkResponseFactory();
            while (ruleIte.hasNext())
            {
                JuvenileCaseSupervisionRule supRule = (JuvenileCaseSupervisionRule) ruleIte.next();
                RuleGroupConditionView ruleView = RuleGroupConditionView.find(supRule.getOID().toString());
                // added if condition as ruleView is null in few cases. tested for oid 64.
                // Alerts Impl.
                if (ruleView != null)
                {
                    RuleDetailResponseEvent response = responseFactory.getRuleDetailResponseEvent(ruleView);
                    dispatch.postEvent(response);
                }
            }
        }

        GetProgramReferralListEvent gpre = new GetProgramReferralListEvent();

        List attributeList = new ArrayList();
        ProgramReferralRetrieverAttribute casefileAttr = new ProgramReferralRetrieverAttributeImpl(
                ProgramReferralRetrieverAttribute.CASEFILE);

        casefileAttr.setAttributeValue(casefileId);

        attributeList.add(casefileAttr);

        gpre.setReferralAttributes(attributeList);
        gpre.setDetailsNeeded(true);

        Iterator progRefIter = JuvenileProgramReferral.findAll(gpre);
        while (progRefIter.hasNext())
        {
            JuvenileProgramReferral programReferral = (JuvenileProgramReferral) progRefIter.next();
            //details is needed since comments is part of the details
            ProgramReferralResponseEvent prre = programReferral.getValueObject(true);
            dispatch.postEvent(prre);
        }

    }

    /**
     * @param event
     * @roseuid 45119A6303B2
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 45119A6303B4
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 45119A6303BC
     */
    public void update(Object anObject)
    {

    }

}

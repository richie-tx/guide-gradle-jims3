//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\casefile\\transactions\\GetCasefileClosingExceptionsCommand.java

package pd.juvenilecase.casefile.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.casefile.GetCasefileClosingExceptionsEvent;
import messaging.casefile.reply.CasefileExceptionResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileRiskNeedLevelByStatusEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.GetVOPDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.JuvenileReferralControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.PACTRiskNeedLevel;
import pd.juvenilecase.casefile.PDCasefileClosingHelper;
import pd.juvenilecase.caseplan.Goal;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIMetadata;
import pd.juvenilecase.referral.JCVOP;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.rules.RuleGroupConditionView;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.casefile.JuvenileReferralHelper;

public class GetCasefileClosingExceptionsCommand implements ICommand 
{
   
   /**
    * @roseuid 439602E5015D
    */
   public GetCasefileClosingExceptionsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4395BC250298
    */
   public void execute(IEvent event) 
   {
		GetCasefileClosingExceptionsEvent casefileEvent= (GetCasefileClosingExceptionsEvent) event;
		// process all the exceptions
		JuvenileCasefile casefile = JuvenileCasefile.find(casefileEvent.getSupervisionNumber());
		 
		//remove risk assesment requirement for supervision category type AR(Post Adjudicated Residential) - US 106778
		if(casefile.getSupervisionCategoryId() != null && "AR".equalsIgnoreCase(casefile.getSupervisionCategoryId())){		    
		    casefile.setIsProgressRiskNeeded(false);
		    casefile.setIsResProgressRiskNeeded(false);
		    casefile.setIsResidentialRiskNeeded(false);
		    casefile.setIsCommunityRiskNeeded(false);
		    casefile.setIsReferralRiskNeeded(false);
		}
		
		Iterator<Assignment>assignmentIter = Assignment.findAll("caseFileId", casefileEvent.getSupervisionNumber());
	    	Iterator<JuvenileMAYSIMetadata>maysiIter = JuvenileMAYSIMetadata.findAll("juvenileNumber", casefile.getJuvenileId());
	    	List<JuvenileMAYSIMetadata>maysiList = new ArrayList<JuvenileMAYSIMetadata>();
	    	List<String>assignmentReferrals = new ArrayList<>();
	    	List<String>casefileReferrals = new ArrayList<>(); //added for US 161877 Aug 2023
	    	while( assignmentIter.hasNext() ) {
	    	    Assignment assignment = assignmentIter.next();
	    	    if ( !assignmentReferrals.contains(assignment.getReferralNumber()) ){
	    		assignmentReferrals.add( assignment.getReferralNumber() );
	    		casefileReferrals.add( assignment.getReferralNumber() ); //added for US 161877 Aug 2023
	    	    }
	    	}
	    	String juvNum = casefile.getJuvenileNum();
	/*
	 * //commented the below code for US 186994 April 2025
	 * //added for US 161877 Aug 2023 STARTS 
	for (String caseFileRefNum : casefileReferrals)
	{
	    
	    //check if VOP exists for the refNum
	    Collection juvNumRefNumVOPrecord = new ArrayList<>();
	    juvNumRefNumVOPrecord = JuvenileReferralHelper.getVOPRecordsForJuvNumRefNum(juvNum, caseFileRefNum); //commented for BUG 164392
	    GetVOPDetailsEvent vopEvt = (GetVOPDetailsEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETVOPDETAILS);
	    vopEvt.setJuvenileNumber(juvNum);
	    vopEvt.setReferralNumber(caseFileRefNum);
	    JCVOP vopReferral  = JCVOP.find(vopEvt);
	    //if no VOP, check if refNum is VOP eligible
	    //if (juvNumRefNumVOPrecord != null && juvNumRefNumVOPrecord.isEmpty())  //commented for BUG 164392
	    if (vopReferral == null)
	    {
		//getOffenses for juvNum RefNum
		JuvenileProfileReferralListResponseEvent referral = new JuvenileProfileReferralListResponseEvent();
		GetJuvenileCasefileOffensesEvent offEvent = new GetJuvenileCasefileOffensesEvent();
		offEvent.setJuvenileNum(juvNum);
		offEvent.setReferralNum(caseFileRefNum);

		Iterator<JJSOffense> iter = JJSOffense.findAll(offEvent);
		while (iter.hasNext())
		{
		    JJSOffense offense = iter.next();
		    //check subsevType of offenseCode ID if 23 - it is VOP eligible
		    JuvenileOffenseCodeResponseEvent offenseCode = JuvenileReferralHelper.getOffenseCd(offense.getOffenseCodeId());
		    String numericCode = offenseCode.getNumericCode();
		    //if subSevType = 23 add a caseFile exception to add VOP
		    if (numericCode != null && numericCode.equalsIgnoreCase("23"))
		    {
			CasefileExceptionResponseEvent casefileExceptionResponseEvent = new CasefileExceptionResponseEvent();
			casefileExceptionResponseEvent.setExceptionType(PDJuvenileCaseConstants.VOP_DETAILS_REQUIRED);
			casefileExceptionResponseEvent.setExceptionId(caseFileRefNum);
			casefileExceptionResponseEvent.setTopic(PDJuvenileCaseConstants.NO_CASEFILE_EXCEPTION_TOPIC);
			PDCasefileClosingHelper.sendResponseEvent(casefileExceptionResponseEvent);
			break;
		    }
		}
	    }
	}//added for US 161877 Aug 2023 ENDS
*/	//commented the above code for US 186994 April 2025
	    	while(maysiIter.hasNext()) {
	    	    JuvenileMAYSIMetadata maysi = maysiIter.next();
	    	    maysiList.add(maysi);
	    	}
	    	for ( String assignmentReferral : assignmentReferrals ) {
	    	    for ( JuvenileMAYSIMetadata maysi : maysiList ){
	    		if ( assignmentReferral.equals(maysi.getReferralNumber())
	    			&& "B".equals(maysi.getAssessmentOptionId())
	    			  && !casefile.getActivationDate().after(maysi.getRequestDate()) ){
	    		CasefileExceptionResponseEvent casefileExceptionResponseEvent = new CasefileExceptionResponseEvent();
			casefileExceptionResponseEvent.setExceptionType(PDJuvenileCaseConstants.MAYSI_DATE_PRIOR_ACTIVATION_DATE);			
			casefileExceptionResponseEvent.setTopic(PDJuvenileCaseConstants.NO_CASEFILE_EXCEPTION_TOPIC);
			PDCasefileClosingHelper.sendResponseEvent(casefileExceptionResponseEvent);
	    		}
	    	    }
	    	}
				
		if (casefile.getIsMAYSINeeded()){
		    	
		    	
			CasefileExceptionResponseEvent casefileExceptionResponseEvent = new CasefileExceptionResponseEvent();
			casefileExceptionResponseEvent.setExceptionType(PDJuvenileCaseConstants.MAYSI_NOT_DONE);			
			casefileExceptionResponseEvent.setTopic(PDJuvenileCaseConstants.NO_CASEFILE_EXCEPTION_TOPIC);
			PDCasefileClosingHelper.sendResponseEvent(casefileExceptionResponseEvent);			
		}
		
		if (casefile.getIsRiskAssessmentNeeded()){
			CasefileExceptionResponseEvent casefileExceptionResponseEvent = new CasefileExceptionResponseEvent();
			casefileExceptionResponseEvent.setExceptionType(PDJuvenileCaseConstants.RISK_NOT_DONE);
			casefileExceptionResponseEvent.setTopic(PDJuvenileCaseConstants.NO_CASEFILE_EXCEPTION_TOPIC);
			PDCasefileClosingHelper.sendResponseEvent(casefileExceptionResponseEvent);			   							
		}
		
		//Added for User Story 41483
		//2/21/2017 added check for create date on or after 03/06/2017 - Bug #45845
		boolean result = UIJuvenileCaseworkHelper.isSupTypeInCat(casefile.getSupervisionTypeId(), UIConstants.CASEFILE_SUPERVISION_CAT_PRE_ADJ);
		Date casefileCreateDate = new Date(casefile.getCreateTimestamp().getTime());
		Date pactLiveDate = DateUtil.stringToDate("03/06/2017",DateUtil.DATE_FMT_1);
		if (casefile.getSupervisionTypeId()!=null && result && !casefileCreateDate.before(pactLiveDate))
		{
			//check if PACT has been completed
			GetJuvenileCasefileRiskNeedLevelByStatusEvent riskNeedLvlEvt = new GetJuvenileCasefileRiskNeedLevelByStatusEvent();
			riskNeedLvlEvt.setCaseFileId(casefile.getCasefileId());
			riskNeedLvlEvt.setJuvenileNum(casefile.getJuvenileId());
			Iterator<PACTRiskNeedLevel> riskNeedIter = PACTRiskNeedLevel.findAll(riskNeedLvlEvt);
			if(!riskNeedIter.hasNext())
			{
				CasefileExceptionResponseEvent casefileExceptionResponseEvent = new CasefileExceptionResponseEvent();
				casefileExceptionResponseEvent.setExceptionType(PDJuvenileCaseConstants.PACT_NOT_DONE);
				casefileExceptionResponseEvent.setTopic(PDJuvenileCaseConstants.NO_CASEFILE_EXCEPTION_TOPIC);
				PDCasefileClosingHelper.sendResponseEvent(casefileExceptionResponseEvent);	
			}
		}
		findAndPostRuleExceptions(casefileEvent.getSupervisionNumber());				
		findAndPostGoalExceptions(casefile.getCaseplanId()); 
   }
   
   /**
	* @param supervisionNumber
	*/
   
   public void findAndPostRuleExceptions(String supervisionNumber){
					
		Iterator<RuleGroupConditionView> rulesIterator = RuleGroupConditionView.findAll("casefileId", supervisionNumber);
		if (rulesIterator != null){
			while(rulesIterator.hasNext()){
				RuleGroupConditionView ruleView = (RuleGroupConditionView) rulesIterator.next();
				String completionStatusId = ruleView.getCompletionStatusId();
				if (!completionStatusId.equals(PDJuvenileCaseConstants.RULE_STATUS_NON_COMPLIANT) && !completionStatusId.equals(PDJuvenileCaseConstants.RULE_STATUS_INACTIVE) && !completionStatusId.equals(PDJuvenileCaseConstants.RULE_STATUS_COMPLETE) && !completionStatusId.equals(PDJuvenileCaseConstants.RULE_STATUS_COMPLIANT)){
					CasefileExceptionResponseEvent casefileExceptionResponseEvent = new CasefileExceptionResponseEvent();
					casefileExceptionResponseEvent.setExceptionType(PDJuvenileCaseConstants.RULES_NOT_DONE);
					casefileExceptionResponseEvent.setExceptionId(ruleView.getRuleId());
					casefileExceptionResponseEvent.setTopic(PDJuvenileCaseConstants.NO_CASEFILE_EXCEPTION_TOPIC);
					PDCasefileClosingHelper.sendResponseEvent(casefileExceptionResponseEvent);			   							
				}
			}
		}								
   }
   
   /**
	* @param casePlanId
	*/
  
  public void findAndPostGoalExceptions(String casePlanId){
					
		Iterator<Goal> goalsIterator = Goal.findAll("casePlanId", casePlanId);
		if (goalsIterator != null){
			while(goalsIterator.hasNext()){
				Goal goal = (Goal) goalsIterator.next();
				String endRecommendations = goal.getEndRecommendations();
				Date endRecoDate =goal.getEndRecommendationDate();
				if (endRecoDate==null && (endRecommendations==null || endRecommendations.equals(""))){
					CasefileExceptionResponseEvent casefileExceptionResponseEvent = new CasefileExceptionResponseEvent();
					casefileExceptionResponseEvent.setExceptionType(PDJuvenileCaseConstants.GOALS_NOT_DONE);
					casefileExceptionResponseEvent.setExceptionId(goal.getGoalId());
					casefileExceptionResponseEvent.setTopic(PDJuvenileCaseConstants.NO_CASEFILE_EXCEPTION_TOPIC);
					PDCasefileClosingHelper.sendResponseEvent(casefileExceptionResponseEvent);			   							
				}
			}
		}								
  }
  
 
   
   /**
    * @param event
    * @roseuid 4395BC250310
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4395BC250324
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 4395BC250375
    */
   public void update(Object anObject) 
   {
    
   }

}

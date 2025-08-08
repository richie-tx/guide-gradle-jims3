//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\SubmitExitPlanAction.java

package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.SaveCommonAppDocEvent;
import messaging.casefile.UpdateResidentialExitPlanEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.casefile.reply.CommonAppDocResponseEvent;
import messaging.caseplan.GetCaseplansByJuvenileNumberEvent;
import messaging.caseplan.GetGoalRuleDetailsByCaseplanIdEvent;
import messaging.caseplan.reply.CaseplanListResponseEvent;
import messaging.caseplan.reply.GoalRuleDetailsResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.JuvenileCasePlanControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.Question;
import ui.common.QuestionGroup;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.Rule;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.ResidentialExitPlanPrintBean;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.casefile.form.ResidentialExitPlanForm;
import ui.juvenilecase.caseplan.form.CaseplanForm.GoalInfo;
import ui.juvenilecase.form.JuvenileCasefileForm;

public class SubmitResidentialExitPlanAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 439604820030
    */
   public SubmitResidentialExitPlanAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 4395C2380355
    */
   public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
		  {
			  return aMapping.findForward(UIConstants.CANCEL);
		  }
	  public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
			 {
				 return aMapping.findForward(UIConstants.BACK);
			 }
   
	 public ActionForward saveAndContinue(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
		{
			ResidentialExitPlanForm myResidenitalForm=(ResidentialExitPlanForm)aForm;
			JuvenileCasefileForm casefileForm=(JuvenileCasefileForm)UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
			CasefileClosingForm myClosingForm=(CasefileClosingForm)UIJuvenileHelper.getJuvenileCasefileClosingForm(aRequest,casefileForm.getSupervisionNum()); //#bug fix 35750 
			
			myResidenitalForm.setAction(UIConstants.CONFIRM_UPDATE);
			myResidenitalForm.setSecondaryAction("");
			myResidenitalForm.setSelectedValue("");
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

			UpdateResidentialExitPlanEvent myEvent=UIJuvenileCasefileClosingHelper.getResidentialEventFROMResidentialForm(myResidenitalForm);
			myEvent.setCasefileClosingStatus(casefileForm.getCaseStatusId());
			myEvent.setExitPlanTemplateLocation(myClosingForm.getReportFileLocOnServer());
			myEvent.setResponses(UIUtil.getUpdateResponseEvtFromUIQuestionGroups(myResidenitalForm.getExitPlanQuestions(),myClosingForm.getClosingInfoId(), myResidenitalForm.getReportType()));
			dispatch.postEvent(myEvent);
			CompositeResponse compositeResponse = (CompositeResponse) 
			dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			CasefileClosingResponseEvent event = (CasefileClosingResponseEvent) MessageUtil.filterComposite(compositeResponse, CasefileClosingResponseEvent.class);
			if(event!=null){
				UIJuvenileCasefileClosingHelper.setResidentialExitPlanInfoFROMClosingRespEvt(myResidenitalForm,event);
				myClosingForm.setReportFileLocOnServer(event.getExitPlanTemplateLocation());
			}
			return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
		}
   
	public ActionForward returnToCasefile(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
				 {
					ResidentialExitPlanForm myResidenitalForm=(ResidentialExitPlanForm)aForm;
					myResidenitalForm.setAction("");
					 return aMapping.findForward(UIConstants.SUCCESS_HOME);
				 }
				 
	public ActionForward print(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse response) {
		ResidentialExitPlanForm myResidentialForm=(ResidentialExitPlanForm)aForm;
		JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		ResidentialExitPlanPrintBean printDataBean = new ResidentialExitPlanPrintBean();
		printDataBean.setJuvenileNumber(casefileForm.getJuvenileNum());
		printDataBean = this.prepareBean(myResidentialForm, printDataBean, casefileForm.getJuvenileNum(), casefileForm.getSupervisionNum());
		
		// record activity
		UIJuvenileHelper.createActivity(casefileForm.getSupervisionNum(), ActivityConstants.EXIT_PLAN_GENERATED, "");

		CompositeResponse compResp = sendPrintRequest("REPORTING::RESIDENTIAL_EXIT_PLAN", printDataBean, null);
		
		ReportResponseEvent aReportRespEvt = (ReportResponseEvent) MessageUtil.filterComposite(compResp, ReportResponseEvent.class);
		if(aReportRespEvt.getContent()==null || aReportRespEvt.getContent().length<1){
			sendToErrorPage(aRequest,"error.generic","Problems generating report");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		SaveCommonAppDocEvent saveEvt=new SaveCommonAppDocEvent();
		saveEvt.setCasefileId(casefileForm.getSupervisionNum());
		saveEvt.setDocument(aReportRespEvt.getContent());
		saveEvt.setDocTypeCd(CommonAppDocResponseEvent.RESIDENTIAL_EXIT_PLAN);
		
		CompositeResponse response1=postRequestEvent(saveEvt);
		CommonAppDocResponseEvent respEvt = (CommonAppDocResponseEvent)MessageUtil.filterComposite(response1, CommonAppDocResponseEvent.class);
		if(respEvt==null){
			sendToErrorPage(aRequest,"error.generic","Problems generating and saving report");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
		try {
			setPrintContentResp(response, compResp, "RESIDENTIAL_EXIT_PLAN", UIConstants.PRINT_AS_PDF_DOC);
		}
		catch(GeneralFeedbackMessageException e) {
			sendToErrorPage(aRequest, "");
		}
		
		return null;
	}
  
	 protected Map getKeyMethodMap()
	  {
		  Map buttonMap = new HashMap();
		  buttonMap.put("button.cancel", "cancel");
		  buttonMap.put("button.back", "back");
		  buttonMap.put("button.saveAndContinue", "saveAndContinue");
		  buttonMap.put("button.returnToCasefileClosingDetails", "returnToCasefile");
			buttonMap.put("button.generate", "print");
		  return buttonMap;
	  }
	  
	private ResidentialExitPlanPrintBean prepareBean(ResidentialExitPlanForm form,
			ResidentialExitPlanPrintBean printDataBean, String juvNum, String casefileId) {
			Collection questionsAnswers = form.getExitPlanQuestions();
			
			int noOfQuestions = questionsAnswers.size();
			
			Iterator iGroup = questionsAnswers.iterator();
			
			QuestionGroup questionGroup = new QuestionGroup();
			Map questionMap =  new HashMap();
			Map answerMap = new HashMap();
			
			Iterator questionsList = null;
			Question question = null;
			String questionID = null;
			String answerValue = null;
			
			while (iGroup.hasNext()) {
				questionGroup = (QuestionGroup) iGroup.next();

				questionsList = questionGroup.getQuestions().iterator();
				while(questionsList.hasNext()) {
					
					question = (Question)questionsList.next();
					questionID = question.getQuestionId();

					questionMap.put(questionID, question.getQuestionText());
					answerValue = question.getResponse();
					if(answerValue == null) {
						answerValue = "";
					}
					answerMap.put(questionID,answerValue);
					printDataBean.addAnswer(questionID,answerValue);
					
				}				
			}
			
			//TODO: Need to get caseplan only for specific juvenile and casefileId
		 	GetCaseplansByJuvenileNumberEvent evt = new GetCaseplansByJuvenileNumberEvent();
			evt.setJuvenileNum(juvNum);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(evt);
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			
			Collection caseplans = MessageUtil.compositeToCollection(compositeResponse, CaseplanListResponseEvent.class);
		
			if (caseplans != null && caseplans.size() > 0) {
				Iterator iterCaseplan = caseplans.iterator();
				
				CaseplanListResponseEvent curRespCaseplan = null;
				CaseplanListResponseEvent aRespCaseplan = null;
				
				while (iterCaseplan.hasNext()) {
					//Filter out other casefiles' caseplans
					aRespCaseplan = (CaseplanListResponseEvent) iterCaseplan.next();
					if(aRespCaseplan.getSupervisionNumber().equalsIgnoreCase(casefileId) &&
						(curRespCaseplan == null || 
							curRespCaseplan.getCreateDate().before(aRespCaseplan.getCreateDate())) ) {
						curRespCaseplan = aRespCaseplan;
					}
				}
				
				if(curRespCaseplan != null) {
					//Getting all the goals, as well as rules associated to this Caseplan 
					GetGoalRuleDetailsByCaseplanIdEvent nevt =
						(GetGoalRuleDetailsByCaseplanIdEvent)EventFactory.getInstance(
							JuvenileCasePlanControllerServiceNames.GETGOALRULEDETAILSBYCASEPLANID);
					nevt.setCaseplanId(curRespCaseplan.getCaseplanID());
					CompositeResponse compResp = postRequestEvent(nevt);
					
					Collection goalRuleDetails = 
						(Collection)MessageUtil.compositeToCollection(
								compResp, GoalRuleDetailsResponseEvent.class);
					
					//use a map to filter out duplicated data, due to flattened raw data
					Map goalInfoMap = new HashMap();
					
					//First iteration is to sort relationship between goal-rule
					for(Iterator goalsIter = goalRuleDetails.iterator(); goalsIter.hasNext();) {
						GoalRuleDetailsResponseEvent rawData = (GoalRuleDetailsResponseEvent)goalsIter.next();
						GoalInfo goal = (GoalInfo)goalInfoMap.get(rawData.getCaseGoalId());
						
						if(goal == null) {
							goal = new GoalInfo();
							goal.setGoalId(rawData.getCaseGoalId());
							goal.setGoal(rawData.getGoalDesc());
							goal.setTimeFrameCd(rawData.getGoalTimeFrameCd());
							goal.setIntervention(rawData.getIntervention()); //added for ER JIMS200075816 
							List personResponsibleList = new ArrayList();
							personResponsibleList.add(rawData.getResponsibleName());
							goal.setPersonsResponsibleDisplay(personResponsibleList);
							goal.setDomainTypeCd(rawData.getGoalDomainTypeCd());
							goal.setStatusCd(rawData.getGoalStatusCd());
							goalInfoMap.put( goal.getGoalId(), goal );				
						}
						
						if(!goal.getPersonsResponsibleDisplay().contains(rawData.getResponsibleName())) {
							goal.getPersonsResponsibleDisplay().add(rawData.getResponsibleName());
						}
						
						if(rawData.getSupRuleId() != null)
						{
							List rules = (List)goal.getAssociatedRules();
							if(rules == null) {
								rules = new ArrayList();
								goal.setAssociatedRules(rules);
							}
							
							Rule rule = null;
							for(Iterator rulesIter = rules.iterator(); rulesIter.hasNext();) {
								Rule eachRule = (Rule)rulesIter.next();
								if(eachRule.getRuleId().equals(rawData.getSupRuleId())) {
									rule = eachRule;						
								}
							}
							if(rule == null) {
								rule = new Rule();
								rule.setRuleId(rawData.getSupRuleId());
								rule.setUnformattedDesc(rawData.getUnformattedDesc());
								rule.setResolvedDesc(rawData.getResolveDesc());
								rules.add(rule);
							}	
						}
					}
					
					Collection goals = goalInfoMap.values();
					for(Iterator goalsIter = goals.iterator(); goalsIter.hasNext();) {
						GoalInfo goal = (GoalInfo)goalsIter.next();
						Collection associatedRules = goal.getAssociatedRules();
						for(Iterator rulesIter = associatedRules.iterator();rulesIter.hasNext();) {
							Rule eachRule = (Rule)rulesIter.next();
							goal.getSelectedRulesList().add(eachRule);
						}
					}
					Collection tmp = goalInfoMap.values();
					ArrayList tmp2 = new ArrayList();
					tmp2.addAll(tmp);
					printDataBean.setGoalDetailsList(tmp2);
				}
			}
			
			printDataBean.setJuvenileFacilityStaySpecialNotes(form.getSpecialNotes());
			return printDataBean;
	}

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		// TODO Auto-generated method stub
		
	}
	  
}// END CLASS


/*public ActionForward print(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse response)
{
	ResidentialExitPlanForm myResidenitalForm=(ResidentialExitPlanForm)aForm;
	myResidenitalForm.setAction("");
	
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	
	ReportRequestEvent residentialExitPlanFormEvent = new ReportRequestEvent();

//	   warrantResponse is the Java Bean object which contains the
//	   values to be applied to field values in the Report Template.

	residentialExitPlanFormEvent.addDataObject(prepareBean(myResidenitalForm));
	residentialExitPlanFormEvent.setReportName("REPORTING::pd.juvenile.reporting.wordxml.MSWordReporting::RESIDENTIAL_EXIT_PLAN");

	dispatch.postEvent(residentialExitPlanFormEvent);

	CompositeResponse compResponse = (CompositeResponse) 
	dispatch.getReply();

	ReportResponseEvent aRespEvent = (ReportResponseEvent) 
	MessageUtil.filterComposite(compResponse, ReportResponseEvent.class);
	try{
	
		if (aRespEvent == null)
		{
			 ReturnExceptions from persistence layer 
			ReturnException returnException =
				(ReturnException) MessageUtil.filterComposite(compResponse, ReturnException.class);
			if (returnException != null)
			{
				throw returnException;
			}
		}
		else
		{
			response.setContentType("application/x-file-download");
			response.setHeader("Content-disposition", "attachment; filename=" + aRespEvent.getFileName() + ".jims");
			response.setHeader("Cache-Control", "max-age=" + 1200);
			response.setContentLength(aRespEvent.getContent().length);
			response.resetBuffer();
			OutputStream os = response.getOutputStream();
			os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
			os.flush();
			os.close();
		}
	
	 return aMapping.findForward(UIConstants.PRINT_SUCCESS);
}
*/
/*
private ResidentialExitPlanPrintBean prepareBean(ResidentialExitPlanForm form, printDataBean)
{
	ResidentialExitPlanPrintBean printDataBean = new ResidentialExitPlanPrintBean();
		
		Collection questionsAnswers = form.getExitPlanQuestions();
		int noOfQuestions = questionsAnswers.size();
		
		Iterator iGroup = questionsAnswers.iterator();
		
		QuestionGroup questionGroup = new QuestionGroup();
		Map questionMap =  new HashMap();
		Map answerMap = new HashMap();
		
		Iterator questionsList = null;
		Question question = null;
		String questionID = null;
		String answerValue = null;
		
		while (iGroup.hasNext()) {
			questionGroup = (QuestionGroup) iGroup.next();

			questionsList = questionGroup.getQuestions().iterator();
			
			while(questionsList.hasNext()) {
				question = (Question)questionsList.next();
				questionID = question.getQuestionId();

				questionMap.put(questionID, question.getQuestionText());
				answerValue = question.getResponse();
				if(answerValue == null) {
					answerValue = "";
				}
				answerMap.put(questionID,answerValue);
				printDataBean.addAnswer(questionID,answerValue);
					
				if(question.getDependsOnQuestionId() != null && !question.getDependsOnQuestionId().equals("")) {
					answerMap.put();		
				}
				else if(question.getDependsOnResponseId() != null && !question.getDependsOnResponseId().equals("")){
//					printDataBean.addAnswer("a" + counter, question.getResponse());						
				}
					
			}				
		}
		
		return printDataBean;
}*/
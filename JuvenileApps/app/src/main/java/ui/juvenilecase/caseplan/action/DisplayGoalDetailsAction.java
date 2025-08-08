package ui.juvenilecase.caseplan.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.caseplan.GetGoalDetailsEvent;
import messaging.caseplan.reply.GoalDetailsResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasePlanControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileCasefileStatusHelper;
import ui.juvenilecase.UIJuvenileCaseplanHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.caseplan.form.CaseplanForm;
import ui.juvenilecase.caseplan.form.CaseplanForm.PersonResponsible;
import ui.juvenilecase.form.JuvenileCasefileForm;

/**
 * 
 * @author awidjaja
 * 
 */
public class DisplayGoalDetailsAction extends JIMSBaseAction
{
  /**
   * @param aMapping
   * @param aForm
   * @param aRequest
   * @param aResponse
   * @return ActionForward
   * @roseuid 42F79A090282
   */
  public ActionForward displayGoalDetails(ActionMapping aMapping, ActionForm aForm, 
  		HttpServletRequest aRequest, HttpServletResponse aResponse)
  {
    CaseplanForm form = (CaseplanForm) aForm;

    GetGoalDetailsEvent det = (GetGoalDetailsEvent) 
    		EventFactory.getInstance(JuvenileCasePlanControllerServiceNames.GETGOALDETAILS);

    det.setGoalID(form.getSelectedValue());
    CompositeResponse response = MessageUtil.postRequest(det);
    GoalDetailsResponseEvent goalEvt = (GoalDetailsResponseEvent) 
    		MessageUtil.filterComposite(response, GoalDetailsResponseEvent.class);

    if (goalEvt != null)
    {
      CaseplanForm.GoalInfo currGoal = form.getCurrentGoalInfo();
      UIJuvenileCaseplanHelper.fillGoalDetails(currGoal, goalEvt);
    }

    List<RuleResponseEvent> ruleEvts = MessageUtil.compositeToList(response, RuleResponseEvent.class);

    form.getCurrentGoalInfo().setOldSelectedRules(new String[0]);
    if( ruleEvts != null && ruleEvts.size() > 0 )
    {
      String[] oldSelectedRules = new String[ruleEvts.size()];
      int i = 0;
    	for( RuleResponseEvent ruleEvt : ruleEvts )
    	{
        String status = CodeHelper.getCodeDescriptionByCode(
        		CodeHelper.getCodes(PDCodeTableConstants.COMPLETION_STATUS), ruleEvt.getRuleCompletionStatusId());
        ruleEvt.setRuleCompletionStatus(status);

        String ruleMonitorFreqDesc = CodeHelper.getCodeDescription(
        		PDCodeTableConstants.MONITOR_FREQUENCY, ruleEvt.getRuleMonitorFreqId());
        ruleEvt.setRuleMonitorFreqDesc(ruleMonitorFreqDesc);

        String ruleTypeDesc = CodeHelper.getCodeDescription(
        		PDCodeTableConstants.SUPERVISION_RULES_TYPE, ruleEvt.getRuleTypeId());

        ruleEvt.setRuleTypeDesc(ruleTypeDesc);
        ruleEvt.setRuleType(ruleTypeDesc);
        oldSelectedRules[ i++ ] = ruleEvt.getRuleId();
      }
      form.getCurrentGoalInfo().setOldSelectedRules(oldSelectedRules);

    }

    Collections.sort((List) UIJuvenileCaseplanHelper.getRuleDetails(ruleEvts));
    form.getCurrentGoalInfo().setAssociatedRules(ruleEvts);
    
	form.setAllowUpdates(UIJuvenileCasefileStatusHelper.casefileStatusClosed(aRequest));
	
    aRequest.setAttribute("status", "view");

    if( form.getAction() != null && form.getAction().equalsIgnoreCase("JUVPROFILEVIEW") )
    {
      return aMapping.findForward("juvProfile");
    }

    return aMapping.findForward(UIConstants.SUCCESS);
  }

  /*
   * @param aMapping
   * @param aForm
   * @param aRequest
   * @param aResponse
   * @return
   * @throws GeneralFeedbackMessageException
   */
  public ActionForward updateGoal(ActionMapping aMapping, ActionForm aForm, 
  		HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
  {
    CaseplanForm form = (CaseplanForm) aForm;
    form.setAction(UIConstants.UPDATE);
    
    String statusCD = form.getCurrentGoalInfo().getStatusCd() ;
    
    if( statusCD != null &&
    		(statusCD.equalsIgnoreCase(PDCodeTableConstants.GOAL_STATUS_PENDING) ||
    		 statusCD.equalsIgnoreCase(PDCodeTableConstants.GOAL_STATUS_APPROVED) )  )
    {
    	String status = form.getCurrentCaseplan().getStatus() ;
    	
			if( statusCD.equalsIgnoreCase(PDCodeTableConstants.GOAL_STATUS_APPROVED)  &&
					status != null && status.equalsIgnoreCase("IN REVIEW"))
			{
		    form.setGoalInfoEditable(false);
			}
			else
			{
		    form.setGoalInfoEditable(true);
			}
    }
    else
    {
      form.setGoalInfoEditable(false);
    }
    
    //set the values from the code table
    { Collection coll = CodeHelper.getCodes("GOAL_DOMAIN_TYPE", true);
    
 // added to get residential caseplan and show domain type accordingly ERJIMS200076784
	String supervisionTypeCurrentCaseplan = form.getSupervisionType();
	String supervisionCatCurrentCaseplan = UIJuvenileCaseworkHelper.getSupCatFromType(supervisionTypeCurrentCaseplan);
	
	//ADDED for JIMS200077469 -- removing the some of the domain type codes
	Iterator domainTypeCodeItr = coll.iterator();
	while(domainTypeCodeItr.hasNext()){
		CodeResponseEvent domainTypeCdRespEvt = (CodeResponseEvent)domainTypeCodeItr.next();
		//String currentDomainTypeCd = domainTypeCdRespEvt.getCode();
		//if(currentDomainTypeCd.equals(UIConstants.GOAL_DOMAIN_TYPE_CONTACT_FREQUENCY) || currentDomainTypeCd.equals(UIConstants.GOAL_DOMAIN_TYPE_FAMILY_PARTICIPATION) || currentDomainTypeCd.equals(UIConstants.GOAL_DOMAIN_TYPE_SOCIALIZATION) || currentDomainTypeCd.equals(UIConstants.GOAL_DOMAIN_TYPE_SUPPORT_SERVICES))
		//added to remove all inactive goal domain types ER 11214
		String currentDomainTypeStatus = domainTypeCdRespEvt.getStatus();
		if(currentDomainTypeStatus != null && currentDomainTypeStatus.equalsIgnoreCase("Inactive"))			
		{
			domainTypeCodeItr.remove();
		}
	}
	
	// removing recreational domain for residential caseplan ERJIMS200076784
	if(supervisionCatCurrentCaseplan !=null && !supervisionCatCurrentCaseplan.equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES)){
		
		Iterator codeIter = coll.iterator();        
        while(codeIter.hasNext()){
        	CodeResponseEvent respEvent = (CodeResponseEvent)codeIter.next();
            String currentCode = respEvent.getCode();
            if(currentCode.equals(UIConstants.GOAL_DOMAIN_TYPE_RECREATIONAL)){                    	
            	codeIter.remove();
            	break;
            }
        }			
	}
	//-----ended
      form.setDormainTypeList(coll);
      Collection timeFrame = CodeHelper.getCodes("GOAL_TIMEFRAME", true);
      form.setTimeFrameList(timeFrame);
    }

    //get the family members
    Collection members = UIJuvenileCaseplanHelper.getFamilyMembers(form);
    Collection<PersonResponsible> contactNames =
    		UIJuvenileCaseplanHelper.getContactNames(form);
    //add the contactNames to the members
    if( contactNames != null )
    {
    	for( PersonResponsible personResp : contactNames )
    	{
        members.add(personResp);
      }
    }
    
    JuvenileCasefileForm myCasefileForm = (JuvenileCasefileForm) this.getSessionForm(aMapping, aRequest,
            UIJuvenileHelper.JUVENILE_CASEFILE_FORM, true);
  //add probation officer also in the responsible person list ER JIMS200075816 - start 
	Name probOffcerName = myCasefileForm.getProbationOfficerName();
	
		if(probOffcerName != null){
		PersonResponsible probOfficer = new PersonResponsible();
		String str1 = probOffcerName.getCompleteFullNameLast() + "(JPO)";
		probOfficer.setName(str1.trim());
		members.add(probOfficer) ;
	}
	//add probation officer also in the responsible person list ER JIMS200075816 - end 

    StringBuffer str = new StringBuffer();
    if (myCasefileForm.getJuvenileName().getLastName() != null)
    {
      str.append(myCasefileForm.getJuvenileName().getLastName());
    }

    if (myCasefileForm.getJuvenileName().getFirstName() != null)
    {
      str.append(", " + myCasefileForm.getJuvenileName().getFirstName());
    }

    if (myCasefileForm.getJuvenileName().getMiddleName() != null)
    {
      str.append(" " + myCasefileForm.getJuvenileName().getMiddleName());
    }
    str.append(" (SELF)");

    PersonResponsible selfPR = new PersonResponsible();
    selfPR.setName(str.toString());
    selfPR.setType("S");

    members.add(selfPR);
    if( members.size( ) > 1 )
    {
      Collections.sort((ArrayList) members);
    }
    form.setPersonsResponsibleList(members);
    
    return aMapping.findForward(UIConstants.UPDATE);
  }

  /*
   * @param aMapping
   * @param aForm
   * @param aRequest
   * @param aResponse
   * @return
   * @throws GeneralFeedbackMessageException
   */
  public ActionForward edit(ActionMapping aMapping, ActionForm aForm, 
  		HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
  {
		CaseplanForm form = (CaseplanForm) aForm;

    GetGoalDetailsEvent det = (GetGoalDetailsEvent) 
    		EventFactory.getInstance(JuvenileCasePlanControllerServiceNames.GETGOALDETAILS);
    det.setGoalID(form.getSelectedValue());

    GoalDetailsResponseEvent goalEvt = (GoalDetailsResponseEvent) 
    		MessageUtil.postRequest(det, GoalDetailsResponseEvent.class);
    if( goalEvt != null )
    {
      CaseplanForm.GoalInfo currGoal = form.getCurrentGoalInfo();
      UIJuvenileCaseplanHelper.fillGoalDetails(currGoal, goalEvt);
      
      JuvenileCasefileForm myCasefileForm = (JuvenileCasefileForm) 
      		this.getSessionForm(aMapping, aRequest, UIJuvenileHelper.JUVENILE_CASEFILE_FORM, true);

      getPersonsResponsible(form, myCasefileForm);
      form.setCasefileId(myCasefileForm.getSupervisionNum());
    }

    form.setAction("update");

    return aMapping.findForward(UIConstants.UPDATE);
  }

  /*
   * @param form
   * @param myCasefileForm
   */
  private void getPersonsResponsible(CaseplanForm form, JuvenileCasefileForm myCasefileForm)
  {
    //		get the family members
    Collection members = UIJuvenileCaseplanHelper.getFamilyMembers(form);
    Collection<PersonResponsible> contactNames = UIJuvenileCaseplanHelper.getContactNames(form);

    //add the contactNames to the members
    if( contactNames != null )
    {
    	for( PersonResponsible personResp : contactNames )
    	{
         members.add(personResp);
      }
    }

    PersonResponsible selfPR = new PersonResponsible();
    StringBuffer str = new StringBuffer();
    if (myCasefileForm.getJuvenileName().getLastName() != null)
    {
      str.append(myCasefileForm.getJuvenileName().getLastName());
    }

    if (myCasefileForm.getJuvenileName().getFirstName() != null)
    {
      str.append(", " + myCasefileForm.getJuvenileName().getFirstName());
    }

    if (myCasefileForm.getJuvenileName().getMiddleName() != null)
    {
      str.append(" " + myCasefileForm.getJuvenileName().getMiddleName());
    }
    str.append(" (SELF)");

    selfPR.setName(str.toString());
    selfPR.setType("S");
    members.add(selfPR);
    if(members.size() > 1 )
    {
      Collections.sort((ArrayList) members);
    }
    form.setPersonsResponsibleList(members);
  }

  /*
   * (non-Javadoc)
   * @see ui.action.JIMSBaseAction#cancel(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, 
  		HttpServletRequest aRequest, HttpServletResponse aResponse)
  {
    ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
    return forward;
  }

  /*
   * (non-Javadoc)
   * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
  		HttpServletRequest aRequest, HttpServletResponse aResponse)
  {
    return aMapping.findForward(UIConstants.BACK);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
   */
  protected void addButtonMapping(Map keyMap)
  {
    keyMap.put("button.link", "displayGoalDetails");
    keyMap.put("button.cancel", "cancel");
    keyMap.put("button.back", "back");
    keyMap.put("button.update", "updateGoal");
    keyMap.put("button.edit", "edit");
  }
}
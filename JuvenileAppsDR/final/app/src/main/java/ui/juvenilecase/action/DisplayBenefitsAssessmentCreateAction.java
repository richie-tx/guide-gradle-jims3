package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.appshell.UserEvent;
import messaging.family.GetBenefitsAssessmentsEvent;
import messaging.family.RequestBenefitsAssessmentEvent;
import messaging.juvenilecase.reply.BenefitsAssessmentResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberFinancialResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;
import naming.ActivityConstants;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileFamilyForm;

public class DisplayBenefitsAssessmentCreateAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("title.benefitsAssessments", "displayRequestNewAssessment");
		keyMap.put("button.requestNewAssessment", "requestNewAssessment");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}
		
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
	HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

		
	public ActionForward requestNewAssessment(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
	{
		JuvenileCasefileForm form = (JuvenileCasefileForm)aForm;
		
		RequestBenefitsAssessmentEvent event = new RequestBenefitsAssessmentEvent(); 
		HttpSession session = aRequest.getSession(false);
//		UserEvent userEvent= (UserEvent) session.getAttribute("userInfo");
		event.setJuvenileNum( form.getJuvenileNum() );
		event.setCasefileId( form.getSupervisionNum() );
		event.setRequesterName(UIUtil.getCurrentUserName()); 
		
		// Adding record in activity table
		UIJuvenileHelper.createActivity(form.getSupervisionNum(), ActivityConstants.BENEFITS_ASSESSMENT_REQUESTED, "");		
		
		MessageUtil.postRequest(event);		
		
		ActionMessages messageHolder = new ActionMessages();
		messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.notificationForNewBenefitsAssessmentSent"));
		aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
		
		return aMapping.findForward("requestNewAssessmentSuccess");
	}
	
	public ActionForward displayRequestNewAssessment(
					ActionMapping aMapping,
					ActionForm aForm,
					HttpServletRequest aRequest,
					HttpServletResponse aResponse)
	{
		JuvenileCasefileForm form = (JuvenileCasefileForm)aForm;
		
		String juvNumber = form.getJuvenileNum();
		if(juvNumber != null)
		{
			GetBenefitsAssessmentsEvent event = new GetBenefitsAssessmentsEvent(); 
			event.setJuvenileNum( juvNumber );
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(event);
			Collection benes =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), BenefitsAssessmentResponseEvent.class );
			
			if(benes != null && benes.size() > 0)
			{
				ArrayList sortedList = (ArrayList)benes;
				Collections.sort(sortedList);
				form.setPreviousBenefitsAssessments(sortedList);
			}	
			else
			{
				JuvenileFamilyForm familyForm = UIJuvenileHelper.getFamilyForm(aRequest);
				if(familyForm == null)
					familyForm = new JuvenileFamilyForm();
				Map dataMap = UIJuvenileFamilyHelper.getActiveFamilyConstellation(juvNumber);
				if(dataMap!=null)
				{
					Collection guardians=new ArrayList();
					Collection families = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
				    if (families != null && families.size() > 0)
				    {
				       Iterator myIter = families.iterator();
				        while (myIter.hasNext())
				        {
				            FamilyConstellationListResponseEvent myFamily = (FamilyConstellationListResponseEvent) myIter.next();
				            if (myFamily.isActive())
				            {
				            	JuvenileFamilyForm.Constellation newFamily=new JuvenileFamilyForm.Constellation();
				            	Collection familiesMembers  = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
				            	newFamily.setFamilyNumber(myFamily.getFamilyNum());			            	
				            	familyForm.setCurrentConstellation(newFamily);
				            	UIJuvenileHelper.setJuvFamilyFormFROMMemberListRespEvt(newFamily,familiesMembers);
				            	Iterator iter =  newFamily.getMemberList().iterator();
					  			  while(iter.hasNext())
					  			  {
					  			  	JuvenileFamilyForm.MemberList myMember = (JuvenileFamilyForm.MemberList)iter.next();
					  			  	if(myMember.isGuardian())
					  			  	{
										String famConstellationMemberNum = myMember.getFamilyConstellationMemberNum();
										FamilyConstellationMemberFinancialResponseEvent financial = UIJuvenileFamilyHelper.getFamilyConstellationFinancial(famConstellationMemberNum);			
										JuvenileFamilyForm.Guardian myGuardian=UIJuvenileHelper.getFamilyFormGuardianFROMFamilyConstellationFinancialRespEvt(financial);
										myGuardian.setMemberNumber(myMember.getMemberNumber());
										myGuardian.setConstellationMemberId(myMember.getFamilyConstellationMemberNum());
										myGuardian.setName(myMember.getMemberName());
										myGuardian.setRelationshipToJuv(myMember.getRelationshipToJuv());
										myGuardian.setDeceased(myMember.getDeceasedYesNo());
										myGuardian.setInHomeStatus(myMember.isInHomeStatus());
										guardians.add(myGuardian);	
					  			  	}
					  			  }
					  			familyForm.getCurrentConstellation().setGuardiansList(UIJuvenileHelper.sortGuardianList((ArrayList)guardians));
				            }
				        }
				    }
				}
				HttpSession session = aRequest.getSession();
				session.setAttribute("juvenileFamilyForm", familyForm);
				
			}
				
		}
			  
		return aMapping.findForward(UIConstants.SUCCESS);
	}
}
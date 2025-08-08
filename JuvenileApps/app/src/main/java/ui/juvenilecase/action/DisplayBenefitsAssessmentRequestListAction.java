package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetRequestedBenefitsAssessmentsEvent;
import messaging.juvenilecase.reply.BenefitsAssessmentGuardianResponseEvent;
import messaging.juvenilecase.reply.RequestedBenefitsAssessmentResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.Name;
import ui.juvenilecase.form.ProcessBenefitsAssessmentForm;
import ui.security.SecurityUIHelper;

public class DisplayBenefitsAssessmentRequestListAction extends Action
{
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		ProcessBenefitsAssessmentForm form = (ProcessBenefitsAssessmentForm)aForm;
		form.clear();
		
		GetRequestedBenefitsAssessmentsEvent event = new GetRequestedBenefitsAssessmentsEvent(); 
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		Collection benes =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), RequestedBenefitsAssessmentResponseEvent.class );
		
		//sort with oldest dates to be the top of the list.
		Collections.sort((List)benes);			
				
		if(benes != null && benes.size() > 0)
		{
			Collection juvInfoList = new ArrayList();
			
			for(Iterator beneIter = benes.iterator(); beneIter.hasNext();)
			{
				RequestedBenefitsAssessmentResponseEvent bene = 
					(RequestedBenefitsAssessmentResponseEvent) beneIter.next();
				
				ProcessBenefitsAssessmentForm.BenefitAssessment assessmentInfo = 
					new ProcessBenefitsAssessmentForm.BenefitAssessment();
				Name juvName=new Name();
				if(bene.getFirstName()!=null){
					juvName.setFirstName(bene.getFirstName());
				}
				if(bene.getLastName()!=null){
					juvName.setLastName(bene.getLastName());
				}
				assessmentInfo.setJuvName(juvName);
				assessmentInfo.setJuvNumber(bene.getJuvenileNumber());
				assessmentInfo.setAssessmentId(bene.getAssessmentId());
				assessmentInfo.setCasefileId(bene.getCasefileId());
				assessmentInfo.setJpoName(bene.getJpoName());
				assessmentInfo.setRequesterName(bene.getRequesterName());
				assessmentInfo.setRequestDate(bene.getRequestDate());
				
				Collection guardiansInfo = new ArrayList();				
			
				if(bene.getGuardians() != null && bene.getGuardians().size() > 0)
				{
					for(Iterator guardianIter = bene.getGuardians().iterator(); guardianIter.hasNext();)
					{
						BenefitsAssessmentGuardianResponseEvent guardian = 
							(BenefitsAssessmentGuardianResponseEvent) guardianIter.next();
						
						ProcessBenefitsAssessmentForm.Guardian guardianInfo = 
							new ProcessBenefitsAssessmentForm.Guardian();
						Name guardName=new Name();
						if(guardian.getFirstName()!=null){
							guardName.setFirstName(guardian.getFirstName());
						}
						if(guardian.getLastName()!=null){
							guardName.setLastName(guardian.getLastName());
						}
						guardianInfo.setName(guardName);
						guardianInfo.setPhoneNumber(guardian.getPhoneNumber());
						guardianInfo.setPhoneType(guardian.getPhoneType());
						guardianInfo.setMemberNumber(guardian.getMemberId());
						guardianInfo.setConstellationMemberId(guardian.getConstellationMemberId());
						guardianInfo.setFamilyNumber(guardian.getFamilyId());
						guardianInfo.setRelationship(guardian.getRelationshipToJuvenile());
						guardiansInfo.add(guardianInfo); //add to list
					}
					
					assessmentInfo.setListOfGuardians(guardiansInfo);
				}
				
				juvInfoList.add(assessmentInfo); //add to list
			}
			form.setRequestList(juvInfoList);
		}
		
		//set the current log on user as titleIVeOfficer
		form.setTitleIVeOfficerId(SecurityUIHelper.getLogonId());
		IUserInfo user = SecurityUIHelper.getUser();
		form.setTitleIVeOfficerName(new Name(user.getFirstName(), user.getMiddleName(), user.getLastName()));
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
		
	
}
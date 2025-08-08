package ui.juvenilecase.action;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.ProcessBenefitsAssessmentForm;


public class DisplayBenefitsAssessmentMemberListAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
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
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
	
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
	HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
		
	public ActionForward next(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
	{
		String forwardPath = UIConstants.NEXT;
		
		ProcessBenefitsAssessmentForm form = (ProcessBenefitsAssessmentForm)aForm;
		ProcessBenefitsAssessmentForm.BenefitAssessment currentAssessment = form.getCurrentAssessment();
		currentAssessment.setGuardian(new ProcessBenefitsAssessmentForm.Guardian());
		
		if(currentAssessment.getAssessmentId() != null && currentAssessment.getAssessmentId().length() > 0)
		{
			//get current assessment, for header display
			String selBenefitAssessmentId = currentAssessment.getAssessmentId();
			if(selBenefitAssessmentId != null && selBenefitAssessmentId.length() > 0)
			{
				//create header information
				for(Iterator iter=form.getRequestList().iterator();iter.hasNext();)
				{
					ProcessBenefitsAssessmentForm.BenefitAssessment assessmentInfo = 
						(ProcessBenefitsAssessmentForm.BenefitAssessment)iter.next();
					if(assessmentInfo.getAssessmentId().equals(selBenefitAssessmentId))
					{
						currentAssessment.setListOfGuardians(assessmentInfo.getListOfGuardians());
						currentAssessment.setJuvNumber(assessmentInfo.getJuvNumber());
						currentAssessment.setJuvName(assessmentInfo.getJuvName());
						currentAssessment.setCasefileId(assessmentInfo.getCasefileId());
						
						//Save casefileId into session
						JuvenileCasefileForm casefileForm = new JuvenileCasefileForm();
						casefileForm.populateJuvenileCasefileForm(assessmentInfo.getCasefileId());
						aRequest.getSession().setAttribute("juvenileCasefileForm", casefileForm);
						break;
					}
					//bug fix for 142503
					HttpSession session = aRequest.getSession();
					JuvenileBriefingDetailsForm juvenileBriefingForm = null;

					juvenileBriefingForm =  UIJuvenileHelper.getJuvBriefingDetailsForm(aRequest);

					if( juvenileBriefingForm == null ){
						juvenileBriefingForm = new JuvenileBriefingDetailsForm();
						setProfileDetail(assessmentInfo.getJuvNumber(), juvenileBriefingForm);
						session.setAttribute("juvenileBriefingDetailsForm", juvenileBriefingForm);
					}
					//
				}
			}
			
			//clears out the Request List?
			//form.setRequestList(new ArrayList());
			
			//if there's only 1 guardian, then default to that guardian and skip
			//guardian selection page
			Collection juvGuardians = form.getCurrentAssessment().getListOfGuardians(); 
			if(juvGuardians != null && !juvGuardians.isEmpty())
			{
				
				Iterator iter = juvGuardians.iterator();
				
			
				if(juvGuardians.size() == 1)
				{					
					if(iter.hasNext())
					{
						ProcessBenefitsAssessmentForm.Guardian theGuardian = (ProcessBenefitsAssessmentForm.Guardian)iter.next();
						form.getCurrentAssessment().getGuardian().setMemberNumber(theGuardian.getMemberNumber());
						form.getCurrentAssessment().getGuardian().setFamilyNumber(theGuardian.getFamilyNumber());
						//form.setGuardianFinancialInfo(theGuardian);
						forwardPath = "defaultGuardian";
					}
				}
				else
				{
					//?
				}
			}
			//else... what happen if the juvenile doesn't have a guardian? Display Error?
			else
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noGuardianAssigned", "No Guardian available."));
				saveErrors(aRequest, errors);
				forwardPath = UIConstants.FAILURE;
			}
			
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.pleaseSelectOneToProceed", "Please select one"));
			saveErrors(aRequest, errors);
			forwardPath = UIConstants.FAILURE;
		}
		
		return aMapping.findForward(forwardPath);
	}
	private void setProfileDetail(String juvenileNum, JuvenileBriefingDetailsForm form)
	    {
		GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

		reqProfileMain.setJuvenileNum(juvenileNum);
		reqProfileMain.setFromProfile(form.getFromProfile());
		CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
		JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);

		form.setJisInfo(new JuvenileJISResponseEvent());
		if (juvProfileRE != null)
		{
		    form.setProfileDetail(juvProfileRE);
		    form.setProfileDescriptions();

		    if (juvProfileRE.getDateOfBirth() != null)
		    { // Get age based on
		      // year
			int age = UIUtil.getAgeInYears(juvProfileRE.getDateOfBirth());
			if (age > 0)
			{
			    form.setAge(String.valueOf(age));
			}
			else
			{
			    form.setAge(UIConstants.EMPTY_STRING);
			}
		    }
		    Collection jisResps = juvProfileRE.getJisInfo();
		    if (jisResps != null)
		    {
			Collections.sort((List) jisResps);
			Iterator jisIter = jisResps.iterator();
			if (jisIter.hasNext())
			{
			    JuvenileJISResponseEvent jis = (JuvenileJISResponseEvent) jisIter.next();
			    form.setJisInfo(jis);
			}
		    }

		    form.setInMentalHealthServices(juvProfileRE.isMentalHealthServices());
		  //U.S 88526
	    	if ( juvProfileRE.getHispanic() != null )
	    	{
	    	    if ( juvProfileRE.getHispanic().equalsIgnoreCase("Y"))
	    	    {
	    		form.setHispanic(UIConstants.YES_FULL_TEXT);
	    	    }
	    	    else
	    	    {
	    		form.setHispanic(UIConstants.NO_FULL_TEXT);
	    	    }
	    	}
	    	else
	    	{
	    	    form.setHispanic(UIConstants.EMPTY_STRING);
	    	}
		}
		

		
		
	    }
}
package ui.juvenilecase.populationReport.action;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.casefile.GetAssessmentReferralEvent;
import messaging.casefile.GetGangAssessmentReferralEvent;
import messaging.casefile.reply.AssessmentReferralResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.officer.GetOfficerProfilesEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import pd.contact.officer.OfficerProfile;
import pd.juvenile.Juvenile;
import pd.km.util.Name;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.GangAssessmentReferralPrintReportBean;
import ui.juvenilecase.casefile.form.AssessmentReferralForm;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class DisplayGangAssessmentReferralsAction extends JIMSBaseAction
{
	/**
	 * @roseuid 42AF409F01B5
	 */
	public DisplayGangAssessmentReferralsAction()
	{
	}

	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected void addButtonMapping(Map buttonMap)
	{
		buttonMap.put("button.submit", "submitSearch");
		buttonMap.put("button.print", "printReport");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.link", "gangAssessmentsTab");
		return;
	}
	/*
	 * 
	 */
	public ActionForward submitSearch(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
            {
        	AssessmentReferralForm assessmentForm = (AssessmentReferralForm) aForm;
        
        	GetGangAssessmentReferralEvent gangAssessmentRefEvent = (GetGangAssessmentReferralEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETGANGASSESSMENTREFERRAL); //getGangAssessmentReferral
        	gangAssessmentRefEvent.setAssessmentStatus(assessmentForm.getStatus());
        	gangAssessmentRefEvent.setFromDateStr(assessmentForm.getStartDateAsStr());
        	gangAssessmentRefEvent.setToDateStr(assessmentForm.getEndDateAsStr());
        
        	CompositeResponse response = MessageUtil.postRequest(gangAssessmentRefEvent);
        	List<AssessmentReferralResponseEvent> gangAssessmentRespEvtList = MessageUtil.compositeToList(response, AssessmentReferralResponseEvent.class);
        	
        	ActionErrors errors = new ActionErrors();
		if(gangAssessmentRespEvtList != null && gangAssessmentRespEvtList.size() == 0){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage( UIConstants.NO_RECORDS_ERROR ));
			saveErrors(aRequest, errors);		
		}		
		if(errors.size() > 0){
		    assessmentForm.clearAll();		    	
			return( aMapping.findForward(UIConstants.CANCEL) );
		}
		//add JPO ID
		for (int i=0; i<gangAssessmentRespEvtList.size(); i++){
		    AssessmentReferralResponseEvent assessmentRespEvt = gangAssessmentRespEvtList.get(i);
		    Juvenile juv = Juvenile.findJCJuvenile(assessmentRespEvt.getJuvenileNum());
		    assessmentRespEvt.setJpoID(juv.getJpoOfRecId());
		  //get the officer name for the JPO ID
			if(juv.getJpoOfRecId()!=null && !juv.getJpoOfRecId().isEmpty())
			{
				GetOfficerProfilesEvent event = new GetOfficerProfilesEvent();
				event.setUserID(juv.getJpoOfRecId());
				event.setStatus("A");
				Iterator iter = OfficerProfile.findAll(event);
				if (iter.hasNext())
				{
					OfficerProfile officerProfile = (OfficerProfile) iter.next();
					Name officerName = new Name(officerProfile.getFirstName(), officerProfile.getMiddleName(), officerProfile.getLastName());
					assessmentRespEvt.setJpoOfRecord(officerName.getFormattedName());
				}
			}
			else
			{
			    assessmentRespEvt.setJpoOfRecord(null);
			    
			}
		}
		
        	Collections.sort(gangAssessmentRespEvtList);
        	//assessmentForm.setGangAssessmentRefList(gangAssessmentRespEvtList);//can't use this 
        	assessmentForm.setGangAssessmentRefSearchList(gangAssessmentRespEvtList);//need a new collection as the list is being overwritten when clicking on the juvNum link
        	if(gangAssessmentRespEvtList!= null){
        	    assessmentForm.setListTotal(gangAssessmentRespEvtList.size());
        	    assessmentForm.setSelectedStatusId(gangAssessmentRefEvent.getAssessmentStatus());
        	}else {
        	    assessmentForm.setListTotal(0);
        	}
        	return aMapping.findForward(UIConstants.SUCCESS);
            }	
	
	 public ActionForward gangAssessmentsTab(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse)throws GeneralFeedbackMessageException 
                    {
                	AssessmentReferralForm gangAssessmentRefForm = (AssessmentReferralForm) aForm;
                	String selectedStatus = gangAssessmentRefForm.getSelectedStatusId();
                	gangAssessmentRefForm.clear();
                	String juvenileNum = (String) aRequest.getParameter("juvenileNum");
                	HttpSession session = aRequest.getSession();
                
                	JuvenileProfileForm profileForm = (JuvenileProfileForm) session.getAttribute("juvenileProfileForm");
                	if (profileForm == null)
                	{
                	    UIJuvenileHelper.populateJuvenileProfileHeaderForm(aRequest, juvenileNum);
                	}
                	JuvenileBriefingDetailsForm juvenileBriefingForm = null;
                
                	juvenileBriefingForm = UIJuvenileHelper.getJuvBriefingDetailsForm(aRequest);
                
                	if (juvenileBriefingForm == null)
                	{
                	    juvenileBriefingForm = new JuvenileBriefingDetailsForm();
                	    setProfileDetail(juvenileNum, juvenileBriefingForm);
                	    session.setAttribute("juvenileBriefingDetailsForm", juvenileBriefingForm);
                	}
                	GetAssessmentReferralEvent event = (GetAssessmentReferralEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETASSESSMENTREFERRAL);
                	event.setJuvenileId(juvenileNum);
                	CompositeResponse composite = MessageUtil.postRequest(event);
                	List<AssessmentReferralResponseEvent> gangAssessmentsResp = MessageUtil.compositeToList(composite, AssessmentReferralResponseEvent.class);
                	if (gangAssessmentsResp != null && gangAssessmentsResp.iterator().hasNext())
                	{
                	    Collections.sort(gangAssessmentsResp);
                	    gangAssessmentRefForm.setGangAssessmentRefList(gangAssessmentsResp);
                	    gangAssessmentRefForm.setStatus(selectedStatus);
                	    gangAssessmentRefForm.setCreateUserId(gangAssessmentsResp.get(0).getUserId());
                	}
                	ActionForward forward = aMapping.findForward("assessmentsTab");
                	return forward;
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
        	    { // Get age based on year
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
        	    if (juvProfileRE.getHispanic() != null)
        	    {
        		if (juvProfileRE.getHispanic().equalsIgnoreCase("Y"))
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
	
            public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
            {
        	AssessmentReferralForm assessmentForm = (AssessmentReferralForm) aForm;
        	assessmentForm.clearAll();
        	return aMapping.findForward(UIConstants.CANCEL);
            }
            
            /*
             * for US 180013 
             */
            public ActionForward printReport(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
            {
        	AssessmentReferralForm form = (AssessmentReferralForm) aForm;
        	
        	GangAssessmentReferralPrintReportBean reportBean = new GangAssessmentReferralPrintReportBean();
        	reportBean.setGangAssessmentRefList(form.getGangAssessmentRefSearchList());
        	reportBean.setStatus(form.getStatus());
        	reportBean.setTotal(form.getListTotal());
        	reportBean.setTodaysDate(DateUtil.dateToString(new Date(), UIConstants.DATE_FMT_1));
        	reportBean.setFromDate(form.getStartDateAsStr());
        	reportBean.setToDate(form.getEndDateAsStr());
		aRequest.getSession().setAttribute("reportInfo", reportBean);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		//let the pdfManager know that the report should be saved in the request during report creation
		aRequest.setAttribute("isPdfSaveNeeded", "true");
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.GANG_ASSESSMENT_REPORT);
		
        	return null;
            }

}

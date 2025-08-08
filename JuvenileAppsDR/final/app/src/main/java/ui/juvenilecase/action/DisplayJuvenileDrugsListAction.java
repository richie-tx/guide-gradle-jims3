package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetDrugTestingInfoEvent;
import messaging.juvenile.GetJuvenileDrugsEvent;
import messaging.juvenile.GetJuvenileJobsEvent;
import messaging.juvenile.GetSubstanceAbuseInfoEvent;
import messaging.juvenile.reply.DrugTestingResponseEvent;
import messaging.juvenile.reply.JuvenileDrugsResponseEvent;
import messaging.juvenile.reply.JuvenileJobResponseEvent;
import messaging.juvenile.reply.SubstanceAbuseResponseEvent;
import messaging.juvenilecase.GetActiveCasefileReferralsEvent;
import messaging.juvenilecase.GetAllJuvenileCasefilesEvent;
import messaging.juvenilecase.GetJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTraitsHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.JuvenileDrugForm;
import ui.juvenilecase.form.JuvenileMainForm;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.TraitsForm;

public class DisplayJuvenileDrugsListAction extends LookupDispatchAction
{
    /**
     * @roseuid 42A5E1CD00EA
     */
    public DisplayJuvenileDrugsListAction()
    {

    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    private List fetchJuvenileDrugs(String juvenileNum)
    {
        GetJuvenileDrugsEvent event = (GetJuvenileDrugsEvent) EventFactory
                .getInstance(JuvenileControllerServiceNames.GETJUVENILEDRUGS);

        event.setJuvenileNum(juvenileNum);

        CompositeResponse composite = MessageUtil.postRequest(event);

        List drugs = MessageUtil.compositeToList(composite, JuvenileDrugsResponseEvent.class);

        Collections.sort(drugs);

        return drugs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.link", "link");
        keyMap.put("button.back", "back");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.tab", "tab");
        return keyMap;
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42B03B350171
     */
    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        TraitsForm traitsForm = UIJuvenileHelper.getTraitsForm(aRequest, true);
        traitsForm.setCategoryName(PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_DRUGS);
        traitsForm.clear();
        String juvenileNum = aRequest.getParameter("juvnum");
        String casefileNum = aRequest.getParameter("casenum");
        if ( juvenileNum == null
        	|| juvenileNum.length() == 0 ) {
            juvenileNum =UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
        } else {
            UIJuvenileHelper.populateJuvenileProfileHeaderForm( aRequest, juvenileNum );
        } 
        
        
        traitsForm.setJuvenileNumber(juvenileNum);
        traitsForm.setUICasefile(false); // mark the fact that we are coming in from profile not casefile
        traitsForm.initializeTraitForm(true);
        traitsForm.setActiveCasefileFound(UIJuvenileTraitsHelper.findActiveCasefile(juvenileNum));

        //		DRUG SPECIFIC STUFF
        JuvenileDrugForm drugForm = (JuvenileDrugForm) aForm;
        drugForm.clear();
        drugForm.setJuvenileNum(juvenileNum);
        drugForm.setAssociateCasefile(casefileNum);

        String action = drugForm.getAction();
        if (action != null)
        {
            drugForm.setAction(action);
        }
        else
        {
            drugForm.setAction(UIConstants.UPDATE);
        }

        Collection drugsResponse = this.fetchJuvenileDrugs(juvenileNum);

        drugForm.setDrugsList(drugsResponse);
        
        //User story 162036
        GetAllJuvenileCasefilesEvent getEvent = (GetAllJuvenileCasefilesEvent)  EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETALLJUVENILECASEFILES);
	getEvent.setJuvenileNum(drugForm.getJuvenileNum());
	

	List<JuvenileCasefileResponseEvent> juvenileCasfileResps = MessageUtil.postRequestListFilter(getEvent, JuvenileCasefileResponseEvent.class);
	List<JuvenileCasefileResponseEvent> filteredCasefiles = new ArrayList<>();
	
	if ( juvenileCasfileResps != null
		|| juvenileCasfileResps.size() > 0 ){
	    for (JuvenileCasefileResponseEvent casefileResp : juvenileCasfileResps ) {
		 if ( "A".equals( casefileResp.getCaseStatusId() ) ){
		     String supervisionNumWithSupervisionType = casefileResp.getSupervisionNum() + "-" + casefileResp.getSupervisionType();
		     casefileResp.setSupervisionNumWithSupervisionType(supervisionNumWithSupervisionType);	
		    filteredCasefiles.add(casefileResp);
		}
		
		  GetDrugTestingInfoEvent getDrugTestingInfoEvent = (GetDrugTestingInfoEvent)
				EventFactory.getInstance(JuvenileControllerServiceNames.GETDRUGTESTINGINFO);
		  getDrugTestingInfoEvent.setCasefileId(casefileResp.getSupervisionNum());
		  CompositeResponse replyEvent = MessageUtil.postRequest(getDrugTestingInfoEvent);
		  Collection<DrugTestingResponseEvent> drugTestingInfos = MessageUtil.compositeToCollection(replyEvent, DrugTestingResponseEvent.class);
		 
		  if ( drugTestingInfos != null ) {
		      drugForm.getDrugTestingInfoList().addAll(drugTestingInfos);
		  }
		  
		  GetSubstanceAbuseInfoEvent getSubstanceAbuseInfoEvent = (GetSubstanceAbuseInfoEvent)
			  EventFactory.getInstance(JuvenileControllerServiceNames.GETSUBSTANCEABUSEINFO);
		  getSubstanceAbuseInfoEvent.setCasefileId(casefileResp.getSupervisionNum());
		  CompositeResponse substanceAbuseInfoResp = MessageUtil.postRequest(getSubstanceAbuseInfoEvent);
		  Collection<SubstanceAbuseResponseEvent>substanceAbuseInfos = MessageUtil.compositeToCollection(substanceAbuseInfoResp, SubstanceAbuseResponseEvent.class);
		  if (substanceAbuseInfos != null
			  && substanceAbuseInfos.size() > 0 ){
		      drugForm.getSubstanceAbuseInfoList().addAll(substanceAbuseInfos);
		  }
	    }
	    
	    if ( drugForm.getDrugTestingInfoList() != null
		    && drugForm.getDrugTestingInfoList().size() > 0 ) {
        	    Collections.sort(drugForm.getDrugTestingInfoList(), new Comparator<DrugTestingResponseEvent>(){
        		      @Override
        		      public int compare(DrugTestingResponseEvent d1, DrugTestingResponseEvent d2 ){
        			  return d2.getTestDate().compareTo(d1.getTestDate());
        		      }
        		      
        	    });
    	    }
	    drugForm.setJuvenileCasfileResps(filteredCasefiles);
	}
	
	
        
        // code added for defect #72492 to display all profile tabs
		JuvenileMainForm jmForm = (JuvenileMainForm)aRequest.getSession().getAttribute("juvenileMainForm");
		if(jmForm == null )
		{
			jmForm = new JuvenileMainForm();
		}
        if (jmForm.isHasCasefile() == false){
        	jmForm.setHasCasefile(true);  // must be true to display tabs
        }
        aRequest.getSession().setAttribute("juvenileProfileMainForm", jmForm);
        // end defect code
        
        JuvenileBriefingDetailsForm juvenileBriefingForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("juvenileBriefingDetailsForm");
        if ( juvenileBriefingForm != null ) {
            drugForm.setJuvenileMasterStatus( juvenileBriefingForm.getMasterStatus() );
        }
        
        return forward;
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42B03B350171
     */
    public ActionForward tab(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        TraitsForm traitsForm = UIJuvenileHelper.getTraitsForm(aRequest, true);
        traitsForm.setAction(UIConstants.VIEW);
        return link(aMapping, aForm, aRequest, aResponse);
    }
  
}
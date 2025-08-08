package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
import messaging.juvenilecase.GetJuvenileTraitsByJuvenileIdEvent;
import messaging.juvenilecase.GetJuvenileTraitsByParentTypeEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
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

import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTraitsHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.TraitsForm;

public class DisplayJuvenileTraitsSearchAction extends LookupDispatchAction
{

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
        traitsForm.setInformationSrcCd("");

        String fromDashboard = aRequest.getParameter("fromDashboard");
        if (fromDashboard != null && !"".equals(fromDashboard) && fromDashboard.equals("viewTraits")) {
        	String juvenileNum = aRequest.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM);

	        if (juvenileNum != null) {
		        GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory
		                .getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
		
		        requestEvent.setJuvenileNum(juvenileNum);
		
		        CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);
		
		        JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent,
		                JuvenileProfileDetailResponseEvent.class);        
	        
	        	UIJuvenileHelper.putHeaderForm(aRequest, detail);
	        }
        }
        return link(aMapping, aForm, aRequest, aResponse);
    }
    public ActionForward detentionVisit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	 TraitsForm traitsForm = UIJuvenileHelper.getTraitsForm(aRequest, true);
         traitsForm.setAction(UIConstants.VIEW);
         traitsForm.setCategoryName(UIConstants.TRAIT_TYPE_ADMINISTRATIVE);
         traitsForm.setTraitTypeId("25");
         traitsForm.setTraitTypeDescriptionId("IBV");
         traitsForm.initializeTraitForm(true);
         String juvenileNum = aRequest.getParameter("juvenileNum");
         Collection juvenileTraits = UIJuvenileCaseworkHelper.fetchJuvTraits(juvenileNum);
         Iterator<JuvenileTraitResponseEvent> traitItr = juvenileTraits.iterator();
         ArrayList<JuvenileTraitResponseEvent> detVisitBannedTraits = new ArrayList<JuvenileTraitResponseEvent>();
 		while(traitItr.hasNext()){
 			JuvenileTraitResponseEvent traitRespEvent = traitItr.next();
 			
			if(traitRespEvent.getTraitTypeName().equals(UIConstants.TRAIT_TYPE_ADMINISTRATIVE) && 
					traitRespEvent.getTraitTypeDescription().equals(UIConstants.TRAIT_TYPE_DESCRIPTION_BANNED_VISITATION)){
				detVisitBannedTraits.add(traitRespEvent);
			}
 		}
 		traitsForm.setTraitsSearchResult(detVisitBannedTraits);
    	return aMapping.findForward("detentionSuccess");
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
        keyMap.put("button.detention", "detentionVisit");
        return keyMap;
    }

    /**
     * Constructor
     */
    public DisplayJuvenileTraitsSearchAction()
    {

    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }

    /**
     * Get data to populate search drop-down
     */
    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TraitsForm traitsForm = (TraitsForm) aForm;
        traitsForm.setCategoryName("");
        traitsForm.clear();
        String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
        traitsForm.setJuvenileNumber(juvenileNum);
        traitsForm.setActiveCasefileFound(UIJuvenileTraitsHelper.findActiveCasefile(juvenileNum));
        traitsForm.setUICasefile(false);
        traitsForm.initializeTraitForm(true);

        
        //Refresh briefing form with new status
        HttpSession session = aRequest.getSession();
	JuvenileBriefingDetailsForm juvenileBriefingForm = null;

	juvenileBriefingForm = UIJuvenileHelper.getJuvBriefingDetailsForm(aRequest);

	if (juvenileBriefingForm == null)
	{

	    juvenileBriefingForm = new JuvenileBriefingDetailsForm();
	    setProfileDetail(juvenileNum, juvenileBriefingForm);
	    session.setAttribute("juvenileBriefingDetailsForm", juvenileBriefingForm);
	}
	
	GetJuvenileTraitsByJuvenileIdEvent allTraitsEvent = (GetJuvenileTraitsByJuvenileIdEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITSBYJUVENILEID);
	allTraitsEvent.setJuvenileNum( juvenileNum );

	Collection<JuvenileTraitResponseEvent> allTraits = MessageUtil.postRequestListFilter(allTraitsEvent, JuvenileTraitResponseEvent.class);

	Collections.sort((List<JuvenileTraitResponseEvent>) allTraits, new Comparator<JuvenileTraitResponseEvent>() {
	    @Override
	    public int compare(JuvenileTraitResponseEvent evt1, JuvenileTraitResponseEvent evt2)
	    {
		if (evt1.getEntryDate() != null && evt2.getEntryDate()  != null)
		    return evt2.getEntryDate().compareTo(evt1.getEntryDate() );
		else
		    return -1;
	    }
	});
	
	if( allTraits.size() > 0 ){
	    
	    traitsForm.setTraitsSearchResult( allTraits );
	}

	
	
	// Filter for former dual trait status
	GetJuvenileTraitsByParentTypeEvent traitByParentEvent = (GetJuvenileTraitsByParentTypeEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITSBYPARENTTYPE);
	traitByParentEvent.setJuvenileNum( juvenileNum );
	traitByParentEvent.setTraitType("25");

	Collection<JuvenileTraitResponseEvent> juvTraits = MessageUtil.postRequestListFilter(traitByParentEvent, JuvenileTraitResponseEvent.class);

	Collections.sort((List<JuvenileTraitResponseEvent>) juvTraits, new Comparator<JuvenileTraitResponseEvent>() {
	    @Override
	    public int compare(JuvenileTraitResponseEvent evt1, JuvenileTraitResponseEvent evt2)
	    {
		if (evt1.getJuvenileTraitId() != null && evt2.getJuvenileTraitId() != null)
		    return evt2.getJuvenileTraitId().compareTo(evt1.getJuvenileTraitId());
		else
		    return -1;
	    }
	});

	juvenileBriefingForm.setDualStatus("");
	if (juvTraits != null && juvTraits.size() > 0)
	{
	    for (JuvenileTraitResponseEvent juvenileTrait : juvTraits)
	    {
		//filter for current vs former
		if ("DST".equalsIgnoreCase(juvenileTrait.getTraitTypeId()) && "CU".equalsIgnoreCase(juvenileTrait.getStatusId()))
		{
		    juvenileBriefingForm.setDualStatus("DS");
		    break;
		}
		else
		{
		    if ("DST".equalsIgnoreCase(juvenileTrait.getTraitTypeId()) && !"CU".equalsIgnoreCase(juvenileTrait.getStatusId()))
		    {
			juvenileBriefingForm.setDualStatus("FDS");
			break;

		    }
		}
	    }
	}// end of if
	
        return aMapping.findForward(UIConstants.SUCCESS);

    }
    
    /**
     * @param juvenileNum
     * @param form
     */
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

}

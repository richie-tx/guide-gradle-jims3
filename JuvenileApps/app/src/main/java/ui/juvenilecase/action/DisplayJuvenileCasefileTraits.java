package ui.juvenilecase.action;

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
import messaging.juvenilecase.GetJuvenileTraitsByParentTypeEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCasefileStatusHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTraitsHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.TraitsForm;

public class DisplayJuvenileCasefileTraits extends LookupDispatchAction
{

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.link", "link");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.tab", "tab");
		keyMap.put("button.find", "find");
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
	public ActionForward tab(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		TraitsForm traitsForm=UIJuvenileHelper.getTraitsForm(aRequest,true);
		traitsForm.setAction(UIConstants.VIEW);
		JuvenileProfileForm jpForm = UIJuvenileHelper.getHeaderForm(aRequest,true);
		String juvNum = jpForm.getJuvenileNum();
		// if statement true, jpForm probably empty
		if (juvNum == null || "".equalsIgnoreCase(juvNum) ){
			juvNum = UIJuvenileHelper.getJuvenileNumber(aRequest,false,true);
			// just in case juvenile number not found
			if (juvNum != null && !"".equalsIgnoreCase(juvNum) ){
				GetJuvenileProfileMainEvent getJuvProfileEvent = new GetJuvenileProfileMainEvent();
				getJuvProfileEvent.setJuvenileNum(juvNum);
				CompositeResponse response = MessageUtil.postRequest(getJuvProfileEvent);
				JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent)
						MessageUtil.filterComposite( response, JuvenileProfileDetailResponseEvent.class);
				if (juvProfileRE != null){
					UIJuvenileHelper.populateHeaderFormEvent( jpForm, juvProfileRE);
				}
			} else {
				jpForm.clear();
			}	
		}

		return link(aMapping,aForm,aRequest,aResponse);	
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward find(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		TraitsForm traitsForm = UIJuvenileHelper.getTraitsForm(aRequest,true);
		
		traitsForm.setAction( UIConstants.FIND );

		return link(aMapping,aForm,aRequest,aResponse);	
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward link(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		TraitsForm traitsForm = (TraitsForm) aForm;
		traitsForm.setCategoryName("");
		traitsForm.clear();
		String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest,false,true);
		traitsForm.setJuvenileNumber(juvenileNum);
		traitsForm.setUICasefile(true);  // mark the fact that we are coming in from casefile not profile
		traitsForm.initializeTraitForm(true);
		traitsForm.setAllowUpdates(UIJuvenileCasefileStatusHelper.casefileStatusClosed(aRequest));
		traitsForm.setActiveCasefileFound(UIJuvenileTraitsHelper.findActiveCasefile(juvenileNum));
		
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
	
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
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

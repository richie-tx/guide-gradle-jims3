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

import messaging.juvenile.GetJuvenilePhysicalAttributesEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.GetTattoosScarsPhysicalAttributesEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenilePhysicalAttributesResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.TattoosScarsPhysicalAttributesResponseEvent;
import messaging.juvenilecase.GetJuvenileTraitsByParentTypeEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenilePhotoHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.JuvenileMainForm;
import ui.juvenilecase.form.JuvenilePhotoForm;
import ui.juvenilecase.form.JuvenilePhysicalCharacteristicsForm;
import ui.juvenilecase.form.JuvenileProfileForm;

public class DisplayJuvenileMasterInformationAction extends Action
{
    public static final String PHYSICALCHARACTERISTICS_FORM = "juvenilePhysicalCharacteristicsForm";

    /**
     * @roseuid 42A898BE0251
     */
    public DisplayJuvenileMasterInformationAction()
    {
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42A46D8F0244
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileMainForm juvMainForm = (JuvenileMainForm) aForm;
	juvMainForm.clear();

	String juvenileNum = aRequest.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM);
	if (juvenileNum == null)
	{
	    JuvenileProfileForm form = UIJuvenileHelper.getHeaderForm(aRequest);
	    if (form != null)
	    {
		juvenileNum = form.getJuvenileNum();
	    }
	    else
	    {
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}

	JuvenilePhotoForm myPhotoForm = UIJuvenileHelper.getJuvPhotoForm(aRequest, true);
	String actionStr = aRequest.getParameter("action");
	if (actionStr != null && actionStr.equalsIgnoreCase("viewTattoo"))
	{
	    myPhotoForm.setIsTattoo(true);
	    if (myPhotoForm.getAllTattoos() == null || myPhotoForm.getAllTattoos().isEmpty())
		UIJuvenilePhotoHelper.getMostRecentTattooInit(myPhotoForm);
	}
	else
	    myPhotoForm.setIsTattoo(false);
	myPhotoForm.setJuvenileNumber(juvenileNum);

	String fromProfile = aRequest.getParameter("fromProfile");

	GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

	requestEvent.setJuvenileNum(juvenileNum);
	requestEvent.setFromProfile((fromProfile == null) ? "" : fromProfile);

	CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);

	JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent, JuvenileProfileDetailResponseEvent.class);

	if (detail == null)
	{ // ERROR OCCURRED
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	// populate the form with the response
	juvMainForm.setAction("");

	// populate the header form from the event and put in session
	UIJuvenileHelper.putHeaderForm(aRequest, detail);

	HttpSession session = aRequest.getSession();
	JuvenileBriefingDetailsForm juvenileBriefingForm = null;

	juvenileBriefingForm = UIJuvenileHelper.getJuvBriefingDetailsForm(aRequest);

	if (juvenileBriefingForm == null)
	{

	    juvenileBriefingForm = new JuvenileBriefingDetailsForm();
	    setProfileDetail(juvenileNum, juvenileBriefingForm);
	    session.setAttribute("juvenileBriefingDetailsForm", juvenileBriefingForm);
	}

	// fetch physical characteristics for the juvenile 
	// and put the form in session
	handlePhysicalCharacteristics(aRequest, juvenileNum);
	UIJuvenileHelper.setJuvenileMainForm(juvMainForm, detail);
	UIJuvenileHelper.setJuvenileAliasInfo(aRequest, detail);

	/** Added for 39094 **/
	JuvenileFacilityHeaderResponseEvent facilityHeaderResp = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvenileNum);
	if (facilityHeaderResp != null)
	{
	    juvMainForm.setJuvHeaderDetails(facilityHeaderResp);
	    juvMainForm.setFacilityCodeDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, facilityHeaderResp.getFacilityCode()));
	    juvMainForm.setFacilityStatusDesc(SimpleCodeTableHelper.getDescrByCode("FACILITY_STATUS", facilityHeaderResp.getFacilityStatus())); //set facility status desc.
	}
	/** Added for 39094 **/
	
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

	String fromDashboard = aRequest.getParameter("fromDashboard");

	if (fromDashboard != null && fromDashboard.equals("update"))
	{
	    return aMapping.findForward(UIConstants.UPDATE);
	}
	else
	{
	    return aMapping.findForward(UIConstants.SUCCESS);
	}
    }

    /*
     * 
     */
    private void handlePhysicalCharacteristics(HttpServletRequest aRequest, String juvenileNum)
    {
	// fetch responses
	GetJuvenilePhysicalAttributesEvent requestEvent = new GetJuvenilePhysicalAttributesEvent();
	requestEvent.setJuvenileNum(juvenileNum);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(requestEvent);
	CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
	ReturnException returnException = (ReturnException) MessageUtil.filterComposite(replyEvent, ReturnException.class);
	if (returnException != null)
	{
	    return;
	}
	// get the from
	JuvenilePhysicalCharacteristicsForm physicalForm = getPhysicalCharacteristicsForm(aRequest);
	physicalForm.setJuvenileNum(juvenileNum);

	List physicalChars = MessageUtil.compositeToList(replyEvent, JuvenilePhysicalAttributesResponseEvent.class);
	if (physicalChars != null)
	{
	    // sort this list based on entryDate
	    Collections.sort(physicalChars);

	    // put this collection in a map to find it fast when entrydate
	    // selection is changed
	    Map map = new HashMap();
	    String description = null;
	    String otherTattooComments = null;
	    String allScars = null;

	    Iterator<JuvenilePhysicalAttributesResponseEvent> it = physicalChars.iterator();
	    while (it.hasNext())
	    {
		JuvenilePhysicalAttributesResponseEvent phyAttRespEvent = it.next();
		map.put(phyAttRespEvent.getEntryDate(), phyAttRespEvent);
	    }

	    // DPA: Need to do it here because we cannot import UIUtil and
	    // CodeHelper inside the event.
	    GetTattoosScarsPhysicalAttributesEvent request = new GetTattoosScarsPhysicalAttributesEvent();
	    request.setJuvenileNum(juvenileNum);
	    TattoosScarsPhysicalAttributesResponseEvent responseEvent = (TattoosScarsPhysicalAttributesResponseEvent) MessageUtil.postRequest(request, TattoosScarsPhysicalAttributesResponseEvent.class);

	    Collection tattoos = responseEvent.getTattoos();
	    if (tattoos != null)
	    {
		List tattooDescriptions = CodeHelper.getCodeDescriptions(tattoos);
		description = UIUtil.separateByDelim(tattooDescriptions, "; ");
	    }

	    Collection comments = responseEvent.getOtherTattooComments();
	    if (comments != null)
	    {
		Iterator i = comments.iterator();
		if (i.hasNext())
		{
		    otherTattooComments = (String) i.next();
		}
	    }

	    Collection scars = responseEvent.getScars();
	    if (scars != null)
	    {
		List scarsDescriptions = CodeHelper.getCodeDescriptions(scars);
		allScars = UIUtil.separateByDelim(scarsDescriptions, "; ");
	    }

	    physicalForm.setAllTatoosDescWithCode(tattoos);
	    physicalForm.setAllOtherTattooCommentsWithCode(comments);
	    physicalForm.setAllScarsWithCode(scars);
	    physicalForm.setPhysicalCharacteristics(physicalChars);
	    physicalForm.setPhysicalCharacteristicsMap(map);
	    physicalForm.setAllTatoosDesc(description);
	    physicalForm.setAllOtherTattooComments(otherTattooComments);
	    physicalForm.setAllScars(allScars);
	    String updatePhysicalFromFacility = aRequest.getParameter("updatePhysical"); //added for US 38802
	    physicalForm.setFromFacility(updatePhysicalFromFacility);
	}
    }

    /**
     * put the physical characteristics form in session
     * 
     * @return HttpServletRequest
     */
    private JuvenilePhysicalCharacteristicsForm getPhysicalCharacteristicsForm(HttpServletRequest aRequest)
    {
	JuvenilePhysicalCharacteristicsForm physicalForm = null;
	HttpSession session = aRequest.getSession();

	Object obj = session.getAttribute(PHYSICALCHARACTERISTICS_FORM);
	if (obj != null || obj instanceof JuvenilePhysicalCharacteristicsForm)
	{
	    physicalForm = (JuvenilePhysicalCharacteristicsForm) obj;
	}
	else
	{
	    physicalForm = new JuvenilePhysicalCharacteristicsForm();
	    session.setAttribute(PHYSICALCHARACTERISTICS_FORM, physicalForm);
	}

	return physicalForm;
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

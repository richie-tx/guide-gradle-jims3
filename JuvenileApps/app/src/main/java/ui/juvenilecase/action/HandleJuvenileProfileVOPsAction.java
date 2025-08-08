//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\referral\\action\\HandleJuvenileProfileTransferredOffensesSelectionAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.codetable.GetAllJuvenileOffenseCodesEvent;
import messaging.codetable.GetJuvenileOffenseCodeEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.juvenilecase.GetCasefileByJuvNumRefNum;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilecase.reply.JuvenileReferralVOPResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.GetJuvenileProfileReferralDetailsEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileConstants;
import naming.UIConstants;

import net.minidev.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.form.AssignedReferralsForm;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.VOPOffenseForm;

/**
 * This action is used in profile and casefile transfer offense referral create
 * pages as both pages perform the same functionality but different tabs
 */
public class HandleJuvenileProfileVOPsAction extends JIMSBaseAction
{
    /* (non-Javadoc)
    * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
    */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.vop","vop");
	keyMap.put("button.addVOPCasefileClosing","addVOPDetailsFromCaseFileClosing");//backup code not used now 08 30 2023
	keyMap.put("button.next", "next");
	keyMap.put("button.addVOPOffenseDetails", "addChargeDetails");
	keyMap.put("button.validateOffenseCode", "validateOffenseCode");
	keyMap.put("button.searchForOffenseCode", "goToOffenseSearch");
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.finish", "finish");
	keyMap.put("button.vopDetailsAdd","addVOPDetails");
    }
    
    /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * VOP //US 119194 new sub-tab VOP in casefile-> referrals tab
	 */
	public ActionForward vop(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String juvenileNum =  aRequest.getParameter("juvnum");
	AssignedReferralsForm assignedRefForm = null;
	HttpSession session = aRequest.getSession();
	
	if ( juvenileNum != null 
		&& juvenileNum.length() > 0 ){
	    UIJuvenileHelper.populateJuvenileProfileHeaderForm(aRequest, juvenileNum);
	} else {
	    JuvenileProfileForm myJuvForm = (JuvenileProfileForm) UIJuvenileHelper.getHeaderForm(aRequest);
	    juvenileNum = myJuvForm.getJuvenileNum();
	    assignedRefForm = (AssignedReferralsForm) session.getAttribute("assignedReferralsForm");
	}
	
		
	aRequest.getSession().setAttribute(
		PDJuvenileCaseConstants.JUVENILE_HISTORY_PAGE,
		PDJuvenileCaseConstants.JUVENILE_PROFILE);

	aRequest.getSession().setAttribute(
		PDJuvenileCaseConstants.JUVENILE_HISTORY,
		PDJuvenileCaseConstants.JUVENILE_REFERRAL);
	
	VOPOffenseForm vopOffForm = (VOPOffenseForm) aForm;
	vopOffForm.setJuvenileNum(juvenileNum);
	
	Collection referralList = null;
	if ( assignedRefForm != null ){
	    referralList = assignedRefForm.getReferralList();
	} else {
	    referralList = getReferralList(juvenileNum);
	}
	
	//Collection existingVOPs = JuvenileReferralHelper.getexisitngVOPRecordsForJuvNum(juvenileNum);
	Collection<JuvenileReferralVOPResponseEvent> existingVOPs = JuvenileReferralHelper.getexisitngVOPRecordsForJuvNum(juvenileNum);
	Collection<PetitionResponseEvent> openRefsPetitionStatF = new ArrayList<PetitionResponseEvent>();
	Collection referralListVOPs = new ArrayList();
	Date dateToday = new Date();
	Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referralList.iterator();
	while (referralIterator.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
	    //add the list offenses from JJS_MS_OFFENSE table in the existing JCVOP US 163617 starts
	    Iterator<JuvenileReferralVOPResponseEvent> vopIterator = existingVOPs.iterator();
	    while (vopIterator.hasNext()){
		JuvenileReferralVOPResponseEvent voprespEvt = vopIterator.next();
		if (voprespEvt.getReferralNum().equalsIgnoreCase(referral.getReferralNumber()))
		{
		    voprespEvt.setVopOffensePetitionNum(referral.getPetitionNumber())  ; //Added for US 170026, 170161
		  //Added for BUG 167120 begins
		    Collection<JuvenileOffenseCodeResponseEvent> offenseCodeRespEvtColl = referral.getOffenseCodes();
		    if (offenseCodeRespEvtColl != null)
		    {
			Collection offenseListVOPs = new ArrayList();
			Iterator<JuvenileOffenseCodeResponseEvent> offenseItr = offenseCodeRespEvtColl.iterator();

			while (offenseItr.hasNext())
			{
			    JuvenileOffenseCodeResponseEvent offenseCode = offenseItr.next();
			    // JuvenileOffenseCodeResponseEvent offenseCode = JuvenileReferralHelper.getOffenseCd(offense.getOffenseCodeId());
			    String numericCode = offenseCode.getNumericCode();
			    if (numericCode != null && numericCode.equalsIgnoreCase("23"))
			    {
				offenseListVOPs.add(offenseCode);
				if (offenseCode.getSeveritySubtype() != null && offenseCode.getSeveritySubtype().equalsIgnoreCase("E"))
			    	    {
			    		referral.setSeveritySubtype("E");
			    		//break;
			    	    }
			    }
			}
			if (offenseListVOPs != null && offenseListVOPs.size() > 0)
			{
			    voprespEvt.setOffenses(offenseListVOPs);
			}
			if (voprespEvt.getOffenses() != null && voprespEvt.getOffenses().size() > 0)
			{
			    voprespEvt.setOffensesAvailableJCVOP(true);
			}

		    }
		    //Added for BUG 167120 ends
		    
		    //Code changes for US 170161 and US 170026 starts
		    List<PetitionResponseEvent> petitions = InterviewHelper.getPetitionsRespForReferral(juvenileNum, String.valueOf(voprespEvt.getInCCountyOrigPetitionedRefNum()));
		    PetitionResponseEvent petition = new PetitionResponseEvent();
		    if (petitions.size() > 0)
		    {
			Collections.sort(petitions, new Comparator<PetitionResponseEvent>() {
			    @Override
			    public int compare(PetitionResponseEvent p1, PetitionResponseEvent p2)
			    {
				return (p1.getOID().compareTo(p2.getOID()));
			    }
			});
			Collections.reverse(petitions);
			petition = petitions.get(0);

			if (petition.getPetitionStatus().equalsIgnoreCase("F"))
			{
			    voprespEvt.setPetitionNum(petition.getPetitionNum())  ;
			    //deleted the code for court date is greater than today
			    //if needed refer it down below LN#215
			}
		    }   //Code changes for US 170161 and US 170026 ENDS
		}
	    } // US 163617 changes ends
	
	    if (referral.getCloseDate() == null)
	    {
		Collection<JJSOffense> offenses = referral.getOffenses();
		if (offenses != null)
		{
		    Collection offenseListVOPs = new ArrayList();
		    Iterator<JJSOffense> offenseItr = offenses.iterator();

		    while (offenseItr.hasNext())
		    {
			JJSOffense offense = offenseItr.next();
			JuvenileOffenseCodeResponseEvent offenseCode = JuvenileReferralHelper.getOffenseCd(offense.getOffenseCodeId());
			String numericCode = offenseCode.getNumericCode();
			if (numericCode != null && numericCode.equalsIgnoreCase("23"))
			{
			    offenseListVOPs.add(offense);
			    if (offenseCode.getSeveritySubtype() != null && offenseCode.getSeveritySubtype().equalsIgnoreCase("E"))
			    {
				referral.setSeveritySubtype("E");
				//break;
			    }
			}
		    }
		    if (offenseListVOPs != null && offenseListVOPs.size() > 0)
		    {
			referral.setOffenses(offenseListVOPs);
			//referralListVOPs.add(referral);
			//Check for no prior VOP for this RefNum 
			Collection<JuvenileReferralVOPResponseEvent> juvNumRefNumVOP = JuvenileReferralHelper.getVOPRecordsForJuvNumRefNum(juvenileNum, referral.getReferralNumber());
			if (juvNumRefNumVOP != null && juvNumRefNumVOP.isEmpty())
			{
			    referralListVOPs.add(referral);
			}
		    }
		}
		List<PetitionResponseEvent> petitions = InterviewHelper.getPetitionsRespForReferral(juvenileNum, referral.getReferralNumber());
		PetitionResponseEvent petition = new PetitionResponseEvent();
		if (petitions.size() > 0)
		{
		    Collections.sort(petitions, new Comparator<PetitionResponseEvent>() {
			@Override
			public int compare(PetitionResponseEvent p1, PetitionResponseEvent p2)
			{
			    return (p1.getOID().compareTo(p2.getOID()));
			}
		    });
		    Collections.reverse(petitions);
		    petition = petitions.get(0);

		    if (petition.getPetitionStatus().equalsIgnoreCase("F"))
		    {
			if (referral.getCourtDate() != null)
			{
			    //if JJS_MS_REFERRAL.courtdate is greater than current date, do not allow petition to be listed for in-county
			    /* (value less than 0) if getStartDatetime is before today's date
			    if( form.getCurrentEvent().getStartDatetime().compareTo(dateToday) < 1)*/ //US 170161
			    if (referral.getCourtDate().compareTo(dateToday) < 1) 
			    {
				referral.setPetitionAllegation(petition.getPetitionAllegation());
				openRefsPetitionStatF.add(petition);
			    }
			}
			else
			{
			    referral.setPetitionAllegation(petition.getPetitionAllegation());
			    openRefsPetitionStatF.add(petition);
			}
		    }
		}
	    }
	}
	vopOffForm.setExistingVOPs(existingVOPs);
	vopOffForm.setReferralsVOP(referralListVOPs);
	vopOffForm.setPetitionsForRef(openRefsPetitionStatF);//TO Do check if needed
	vopOffForm.setInCountyPetitions(openRefsPetitionStatF);
	aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY, PDJuvenileCaseConstants.VIOLATION_OF_PROBATION);
	return aMapping.findForward(UIConstants.VOP);
    }
	
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward addVOPDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	HttpSession session = aRequest.getSession();
	VOPOffenseForm vopForm = (VOPOffenseForm)aForm;
	String selectedRef4VOP = vopForm.getSelectedRef4VOP();
	Collection<JuvenileProfileReferralListResponseEvent> referralListVOPs = vopForm.getReferralsVOP();
	Collection<PetitionResponseEvent> petitions = (List<PetitionResponseEvent>) vopForm.getPetitionsForRef();
	//vopOffForm.setVopOffenseCode(offenseCode.getOffenseCode());
	vopForm.setReferralsVOP(referralListVOPs);
	vopForm.setInCountyPetitions(petitions);
	vopForm.setConfirmMsg(UIConstants.EMPTY_STRING);
	vopForm.setAdultIndicatorStr(UIConstants.EMPTY_STRING);
	Iterator<JuvenileProfileReferralListResponseEvent>  refItr = referralListVOPs.iterator();
	while (refItr.hasNext()){
	    JuvenileProfileReferralListResponseEvent refRespEvent = refItr.next();
	    if (selectedRef4VOP!= null && selectedRef4VOP.equalsIgnoreCase(refRespEvent.getReferralNumber()))
	    {
		vopForm.setRefForVop(refRespEvent);
		vopForm.setReferralNum(refRespEvent.getReferralNumber());
		vopForm.setReferralDate(refRespEvent.getReferralDate());
		vopForm.setReferralDateStr(DateUtil.dateToString(refRespEvent.getReferralDate(), "MM/dd/yyyy"));
		vopForm.setOffensesVOPs(refRespEvent.getOffenses());
		vopForm.setSelectedSubSevType4VOP(refRespEvent.getSeveritySubtype());
		vopForm.setPetitionNumVOP(refRespEvent.getPetitionNumber()); //Added for US 170026, 170161
		//List<offenses> offenses = new ArrayList<>(); 
		List<JJSOffense> offenses = (List<JJSOffense>) refRespEvent.getOffenses();
		//The UI for the other screens (which show only one offense), always show the first seqnum MJCW 09/12/2023
		Collections.sort((List<JJSOffense>) offenses, new Comparator<JJSOffense>() {
		    @Override
		    public int compare(JJSOffense evt1, JJSOffense evt2)
		    {
			return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
		    }
		});
		JJSOffense  offense = offenses.get(0);
		vopForm.setVopOffenseCode(offense.getOffenseCodeId());
		if(refRespEvent.getOffenses()!=null && refRespEvent.getOffenses().size()>0){
		    vopForm.setOffensesAvailable(true);
		}
		
		
		break;
	    }
	}
	//session.setAttribute( "VOPOffenseForm", vopForm );
	vopForm.setOffenseCharge(UIConstants.EMPTY_STRING);
	vopForm.setOffenseChargeDate(UIConstants.EMPTY_STRING);
	vopForm.setSelectedPetition(UIConstants.EMPTY_STRING);
	vopForm.setAdultIndicatorStr(UIConstants.EMPTY_STRING); //adultIndicatorStr
	vopForm.setLocationIndicator(null);
	
	if ( vopForm.getReferralNum() != null
		&& vopForm.getReferralNum().length() > 0  ) {
	    GetCasefileByJuvNumRefNum searchEvent = new GetCasefileByJuvNumRefNum();
		searchEvent.setJuvenileNum( vopForm.getJuvenileNum() );
		searchEvent.setReferralNum( vopForm.getReferralNum() );
		
		Iterator casefileIter = JuvenileCasefileReferral.findAll(searchEvent);
		List<String> casefileIds = new ArrayList<>();
		while( casefileIter.hasNext() ) {
		    JuvenileCasefileReferral casefile = (JuvenileCasefileReferral) casefileIter.next();
		    casefileIds.add( casefile.getCaseFileId() );
		}
		
		if ( casefileIds.size() > 0 ){
		    vopForm.setCasefileIds(casefileIds);
		}
	}
	
	
	aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY, PDJuvenileCaseConstants.VIOLATION_OF_PROBATION);
	return aMapping.findForward(UIConstants.VOP_CREATE);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse)
    {
	VOPOffenseForm vopForm = (VOPOffenseForm) aForm;
	if (vopForm.getOffenseCharge() != null && StringUtils.isNotEmpty(vopForm.getOffenseCharge()))
	{
	    JuvenileOffenseCodeResponseEvent jocEvent = JuvenileReferralHelper.validateOffenseCd(vopForm.getOffenseCharge());
	    if (jocEvent == null)
	    {
		ActionErrors errors = new ActionErrors();
		errors.clear();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Offense Code. Please Correct and Retry"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    else
		if (jocEvent.getNumericCode() != null && "23".equalsIgnoreCase(jocEvent.getNumericCode())) //added for US 183029
		{
		  /*  JSONObject message = new JSONObject();
			message.put("message", "A violation of probation is not a valid charge code. Please enter a criminal charge.");
			vopForm.setOffenseCharge("");
			aResponse.getWriter().println(message);
			return null;*/
		    ActionErrors errors = new ActionErrors();
		    errors.clear();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "A violation of probation is not a valid charge code. Please enter a criminal charge"));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
	}
	vopForm.setInCCountyOrigPetitionedRefNum(Integer.parseInt(vopForm.getSelectedPetition().trim())); //convert string to int
	Collection<PetitionResponseEvent> inCountyPetList = vopForm.getInCountyPetitions();
	Iterator<PetitionResponseEvent> petitionsListItr = inCountyPetList.iterator();
	while (petitionsListItr.hasNext())
	{
	    PetitionResponseEvent petRespEvt = petitionsListItr.next();
	    if (petRespEvt.getReferralNum().equalsIgnoreCase(vopForm.getSelectedPetition()))
	    {
		vopForm.setPetitionAllegation(petRespEvt.getOffenseCodeId());
		vopForm.setPetitionAllegationDesc(petRespEvt.getOffense());
		vopForm.setPetitionNum(petRespEvt.getPetitionNum());
	    }
	}

	return aMapping.findForward(UIConstants.VOP_ADD_DETAILS);
	
    }
    

    /** this is now done using jQuery/JavaScript
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
   /* public ActionForward addChargeDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	HttpSession session = aRequest.getSession();
	VOPOffenseForm vopForm = (VOPOffenseForm)session.getAttribute("VOPOffenseForm");
	AssignedReferralsForm rForm  = (AssignedReferralsForm)session.getAttribute("assignedReferralsForm");
	String selectedPet = aRequest.getParameter("selectedPetition");
	Collection<PetitionResponseEvent> inCountyPetitions = vopForm.getInCountyPetitions();
	Iterator<PetitionResponseEvent> inCountyPetitionItr = inCountyPetitions.iterator();
	while (inCountyPetitionItr.hasNext()){
	    PetitionResponseEvent petitionResp = inCountyPetitionItr.next();
	    if(petitionResp.getReferralNum().equalsIgnoreCase(selectedPet)){
		String selectedSubSeverity = petitionResp.getSeveritySubType();
		if(selectedSubSeverity.equalsIgnoreCase("E")){
		    vopForm.setSelectedPetSubseverity("E");
		}
		break;
	    }
	}
	vopForm.setInCCountyOrigPetitionedRefNum(Integer.parseInt(selectedPet));
	return aMapping.findForward(UIConstants.VOP_ADD_DETAILS);
    }*/
    
/*
    public ActionForward saveInfo(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	HttpSession session = aRequest.getSession();
	VOPOffenseForm vopForm = (VOPOffenseForm)session.getAttribute("VOPOffenseForm");
	String forwardStr = "finishSuccess" ;
	return aMapping.findForward(forwardStr);
    }*/

    public ActionForward validateOffenseCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
	VOPOffenseForm vopForm = (VOPOffenseForm) aForm;
	JuvenileOffenseCodeResponseEvent jocEvent = validateOffenseCd(vopForm.getOffenseCharge());
	if (jocEvent != null)
	{
	    //String selectedPetition = aRequest.getParameter("selectedPetition");
	    //String lit = jocEvent.getShortDescription() + " (" + jocEvent.getCategory() + ")";
	    /*ActionMessages messageHolder = new ActionMessages();
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.offenseCodeValid"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);*/
	    if (jocEvent.getNumericCode() != null && "23".equalsIgnoreCase(jocEvent.getNumericCode())) // added for US 183029
	    {
		JSONObject message = new JSONObject();
		message.put("message", "A violation of probation is not a valid charge code. Please enter a criminal charge.");
		vopForm.setOffenseCharge("");
		aResponse.getWriter().println(message);
		return null;
	    }
	    else
	    {
		JSONObject message = new JSONObject();
		message.put("message", "Offense Code Entered Is Valid.");
		aResponse.getWriter().println(message);
		return null;
	    }
	}else
	{
	    JSONObject message = new JSONObject();
	    message.put("message","Invalid Offense Code. Please Correct and Retry.");
	    vopForm.setOffenseCharge("");
	    aResponse.getWriter().println(message);
	    return null;
	}
	
	//return aMapping.findForward(UIConstants.OFFENSE_SUCCESS);
  
	}
    

    /**
     * @param offenseCd
     * @return
     */
    private JuvenileOffenseCodeResponseEvent validateOffenseCd(String offenseCd)
    {
	JuvenileOffenseCodeResponseEvent jpEvent = null;
	GetJuvenileOffenseCodeEvent jocEvent = new GetJuvenileOffenseCodeEvent();
	jocEvent.setAlphaCode(offenseCd);
	List events = MessageUtil.postRequestListFilter(jocEvent, JuvenileOffenseCodeResponseEvent.class);
	if (events != null & !events.isEmpty())
	{
	    for (int x = 0; x < events.size(); x++)
	    {
		JuvenileOffenseCodeResponseEvent respEvent = (JuvenileOffenseCodeResponseEvent) events.get(x);
		// Added DPS code blank for bug 37346 
		if (!"Y".equals(respEvent.getInactiveInd()) && offenseCd.equalsIgnoreCase(respEvent.getOffenseCode()) && !(respEvent.getDpsOffenseCode().isEmpty()))
		{

		    jpEvent = new JuvenileOffenseCodeResponseEvent();
		    jpEvent.setCategory(respEvent.getCategory());
		    jpEvent.setDpsOffenseCode(respEvent.getDpsOffenseCode());
		    jpEvent.setShortDescription(respEvent.getShortDescription());
		    jpEvent.setNumericCode(respEvent.getNumericCode());
		    break;
		}
	    }
	}
	return jpEvent;
    }

    public ActionForward goToOffenseSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	VOPOffenseForm form = (VOPOffenseForm) aForm;
	form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAlphaCodeId(UIConstants.EMPTY_STRING);
	form.setShortDesc(UIConstants.EMPTY_STRING);
	form.setDpsCodeId(UIConstants.EMPTY_STRING);
	form.setCategoryId(UIConstants.EMPTY_STRING);
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setOffenseResultList(new ArrayList());
	
	GetAllJuvenileOffenseCodesEvent requestEvent = (GetAllJuvenileOffenseCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETALLJUVENILEOFFENSECODES);
	List codes = MessageUtil.postRequestListFilter(requestEvent, JuvenileCasefileOffenseCodeResponseEvent.class);

	Collections.sort(codes);

	form.setCodetableDataList(codes);

	return aMapping.findForward(UIConstants.OFFENSE_SUCCESS);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	VOPOffenseForm form = (VOPOffenseForm) aForm;
	//form.clear();
	return aMapping.findForward(UIConstants.BACK);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	VOPOffenseForm form = (VOPOffenseForm) aForm;
	form.clear();
	return aMapping.findForward(UIConstants.CANCEL);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward addVOPDetailsFromCaseFileClosing(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	HttpSession session = aRequest.getSession();
	VOPOffenseForm vopForm = (VOPOffenseForm) aForm;
	String referralNumForVOP = aRequest.getParameter("referralNum");
	String juvenileNum = aRequest.getParameter("juvenileNum");
	
	JuvenileProfileReferralListResponseEvent referralRespEvt = new JuvenileProfileReferralListResponseEvent();
	Collection<PetitionResponseEvent> openRefsPetitionStatF = new ArrayList<PetitionResponseEvent>();
	referralRespEvt.setReferralNumber(referralNumForVOP);
	//need offense list
	//getOffenses for juvNum RefNum
	GetJuvenileCasefileOffensesEvent offEvent = new GetJuvenileCasefileOffensesEvent();
	offEvent.setJuvenileNum(juvenileNum);
	offEvent.setReferralNum(referralNumForVOP);
	Collection offenseListVOPs = new ArrayList();
	Iterator<JJSOffense> iter = JJSOffense.findAll(offEvent);
	while (iter.hasNext())
	{
	    JJSOffense offense = iter.next();
	    
	    //check subsevType of offenseCode ID if 23 - it is VOP eligible
	    JuvenileOffenseCodeResponseEvent offenseCode = JuvenileReferralHelper.getOffenseCd(offense.getOffenseCodeId());
	    String numericCode = offenseCode.getNumericCode();
	    //if subSevType = 23 
	    if (numericCode != null && numericCode.equalsIgnoreCase("23"))
	    {
		offense.setOffenseDescription(offenseCode.getShortDescription());
		offenseListVOPs.add(offense);
		if (offenseCode.getSeveritySubtype() != null && offenseCode.getSeveritySubtype().equalsIgnoreCase("E"))
		{
		    vopForm.setSelectedSubSevType4VOP("E");
		}
	    }
	
	}
	if(referralRespEvt.getOffenses()!=null && referralRespEvt.getOffenses().size()>0){
	    vopForm.setOffensesAvailable(true);
	}
	referralRespEvt.setOffenses(offenseListVOPs);
	vopForm.getOffenseCollectionSize();
	vopForm.setOffensesVOPs(referralRespEvt.getOffenses());
	List<PetitionResponseEvent> petitions = InterviewHelper.getPetitionsForJuv(juvenileNum);//get ALL petitions for juv
	Iterator<PetitionResponseEvent> petition = petitions.iterator();
	
	while (petition.hasNext())
	{
	    PetitionResponseEvent petRespEvt = petition.next();
	    if (petRespEvt.getPetitionStatus().equalsIgnoreCase("F"))
	    {
		referralRespEvt.setPetitionAllegation(petRespEvt.getPetitionAllegation());
		openRefsPetitionStatF.add(petRespEvt);
	    }
	}

	//build VOP form
	vopForm.setSelectedRef4VOP(referralNumForVOP);
	vopForm.setJuvenileNum(juvenileNum);
	vopForm.setInCountyPetitions(openRefsPetitionStatF);
	vopForm.setRefForVop(referralRespEvt);

	/*        Collection<JuvenileProfileReferralListResponseEvent> referralListVOPs = vopForm.getReferralsVOP();
	        Collection<PetitionResponseEvent> petitions = (List<PetitionResponseEvent>) vopForm.getPetitionsForRef();
	        //vopOffForm.setVopOffenseCode(offenseCode.getOffenseCode());
	        vopForm.setReferralsVOP(referralListVOPs);
	        vopForm.setInCountyPetitions(petitions);
	        vopForm.setConfirmMsg(UIConstants.EMPTY_STRING);
	        vopForm.setAdultIndicatorStr(UIConstants.EMPTY_STRING);
	        Iterator<JuvenileProfileReferralListResponseEvent>  refItr = referralListVOPs.iterator();
	        while (refItr.hasNext()){
	            JuvenileProfileReferralListResponseEvent refRespEvent = refItr.next();
	            if (selectedRef4VOP!= null && selectedRef4VOP.equalsIgnoreCase(refRespEvent.getReferralNumber()))
	            {
	        	vopForm.setRefForVop(refRespEvent);
	        	vopForm.setReferralNum(refRespEvent.getReferralNumber());
	        	vopForm.setOffensesVOPs(refRespEvent.getOffenses());
	        	vopForm.setSelectedSubSevType4VOP(refRespEvent.getSeveritySubtype());
	        	//List<offenses> offenses = new ArrayList<>(); 
	        	List<JJSOffense> offenses = (List<JJSOffense>) refRespEvent.getOffenses();
	        	JJSOffense  offense = offenses.get(0);
	        	vopForm.setVopOffenseCode(offense.getOffenseCodeId());
	        	if(refRespEvent.getOffenses()!=null && refRespEvent.getOffenses().size()>0){
	        	    vopForm.setOffensesAvailable(true);
	        	}
	        	break;
	            }
	        }*/
	vopForm.setOffenseCharge(UIConstants.EMPTY_STRING);
	vopForm.setOffenseChargeDate(UIConstants.EMPTY_STRING);
	vopForm.setSelectedPetition(UIConstants.EMPTY_STRING);
	vopForm.setAdultIndicatorStr(UIConstants.EMPTY_STRING); //adultIndicatorStr
	vopForm.setLocationIndicator(null);

	aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY, PDJuvenileCaseConstants.VIOLATION_OF_PROBATION);
	return aMapping.findForward(UIConstants.VOP_CREATE);
    }
    
    private Collection getReferralList(String juvenileNumber){
	Collection referrals = null;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetJuvenileProfileReferralDetailsEvent event = (GetJuvenileProfileReferralDetailsEvent)
		EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALDETAILS);
	event.setJuvenileNum(juvenileNumber);
	dispatch.postEvent(event);
            
	
	CompositeResponse response = (CompositeResponse)dispatch.getReply();
	MessageUtil.processReturnException(response);
            
	Map dataMap = MessageUtil.groupByTopic(response);
	
	if( dataMap != null ) {
	    referrals = (Collection)dataMap.get(PDJuvenileConstants.JUVENILE_PROFILE_REFERRAL_LIST_TOPIC);
	    
	    if( referrals != null  &&  referrals.size() > 0 ){
		if(referrals.size() > 1 ){
		    Collections.sort((List<JuvenileProfileReferralListResponseEvent>) referrals, 
			    				Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
			@Override
			public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
			{
			    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
				return evt1.getReferralNumber().compareTo(evt2.getReferralNumber());
			    else
				return -1;
			}
		    }));
		}
	    }
	}
            
            return referrals;
    }
}
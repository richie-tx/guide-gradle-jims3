/*
 * Project: JIMS
 * Class:   ui.juvenilecase.action.DisplayJuvenileReferralSealingSearchResultsAction
 *
 * Date:    2019-09-27
 *
 * Author:  Anju Pillai
 
 */

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByJuvNumRefNumEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.GetJuvenileFacilityDetailsEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.GetJuvenileStatusEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetCasefileWithReferralsEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.GetProductionSupportJuvenileReferralsEvent;
import messaging.productionsupport.ListProductionSupportJuvenileReferralsEvent;
import messaging.productionsupport.UpdateCourtRecordSealOnReferralSealEvent;
import messaging.productionsupport.UpdateDetentionCourtRecordSealOnReferralSealEvent;
import messaging.productionsupport.UpdatePetitionRecordSealOnReferralSealEvent;
import messaging.productionsupport.UpdateProductionSupportReferralSealEvent;
import messaging.productionsupport.UpdateReleaseRecordOnReferralSealEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.JuvenileFacilityControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProductionSupportControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import pd.codetable.Code;
import pd.juvenile.JuvenileBuilder;
import pd.juvenile.JuvenileStatus;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JuvenileCasefile;
import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

public class DisplayJuvenileReferralSealingSearchResultsAction extends JIMSBaseAction
{
    protected void addButtonMapping(Map keyMap)
    {
	
	keyMap.put("button.submit", "submitSearch");
	keyMap.put("button.back", "back");
	keyMap.put("button.confirmReferralSeal", "submitSeal");
	

    }

    public DisplayJuvenileReferralSealingSearchResultsAction()
    {

    }

    /**
     * @param aMapping
     *            The a mapping.
     * @param aForm
     *            The a form.
     * @param aRequest
     *            The a request.
     * @param aResponse
     *            The a response.
     * @return ActionForward
     * @roseuid 42A46D8E0234
     */

    public ActionForward submitSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
	ProdSupportForm regform = (ProdSupportForm) aForm;
	String forward = "success";
	String juvenileNum = "";
	juvenileNum = regform.getJuvenileId().trim();
	/*if (juvenileNum == null || juvenileNum.equals(""))
	{
	    juvenileNum = aRequest.getParameter("juvNum");
	    regform.setJuvenileId(juvenileNum);
	}*/
	//JJSJuvenileResponseEvent juvenile = retrieveJuvenile(juvenileNum);
	GetJJSJuvenileEvent request = (GetJJSJuvenileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILE);
	request.setJuvenileNum(juvenileNum);

	JJSJuvenileResponseEvent juvenile = (JJSJuvenileResponseEvent) MessageUtil.postRequest(request, JJSJuvenileResponseEvent.class);
	if (juvenile == null || juvenile.getJuvenileNum() == null || juvenile.getJuvenileNum().equals(""))
	{
	    regform.setToJuvenileId("");
	    //regform.setMsg("Juvenile not found. You must enter a valid JuvenileID.");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile record not found.  Please retry search"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward("error");
	}
	Name name = new Name(juvenile.getFirstName(), juvenile.getMiddleName(), juvenile.getLastName());

	regform.setJuvenileName(name.getFormattedName());
	regform.setJuvenileSsn(juvenile.getJuvenileSsn());
	regform.setBirthDate(juvenile.getBirthDate());
	regform.setSex(juvenile.getSexId());
	regform.setRace(juvenile.getRaceId());
	//JuvenileProfileDetailResponseEvent aRespEvnt = regform.getProfileDetail( ) ;
	GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

	requestEvent.setJuvenileNum(juvenileNum);
	CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);
	JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent, JuvenileProfileDetailResponseEvent.class);

	if (detail.getHispanic() != null)
	{
	    if (detail.getHispanic().equalsIgnoreCase("Y"))
	    {
		regform.setHispanic(UIConstants.YES_FULL_TEXT);
	    }
	    else
	    {
		regform.setHispanic(UIConstants.NO_FULL_TEXT);
	    }
	}

	// master status from jjs_ms_juvenile
	String statusDesc = JuvenileBuilder.getMasterStatusDesc(juvenile.getStatusId());
	regform.setStatusId(statusDesc);
	//

	regform.setRectype(juvenile.getRectype());
	if (juvenile.getRectype().equals("S.JUVENILE"))
	{
	    regform.setToJuvenileId("");
	    //regform.setMsg("Juvenile not found. You must enter a valid JuvenileID.");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile Master Record is Sealed.  This record does not qualify for Juvenile Referral Seal"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward("error");
	}
	ArrayList juvprogrefs = null;
	//find all active referrals;
	/*int cnt=0;
	Collection<JuvenileProfileReferralListResponseEvent> referralList = JuvenileFacilityHelper.getJuvReferralDetails(juvenileNum);
	Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referralList.iterator();
	while (referralIterator.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
	    if (referral != null && referral.getRecType().equalsIgnoreCase("REFERRAL") && referral.getCloseDate()==null)
	    {
		cnt+=1;
	    }
	}
	regform.setHdnCount(cnt);
	*/
	    //
	// Get and set Associated JuvProgRefs
	ListProductionSupportJuvenileReferralsEvent getJuvenileRerralsEvent = (ListProductionSupportJuvenileReferralsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.LISTPRODUCTIONSUPPORTJUVENILEREFERRALS);
	getJuvenileRerralsEvent.setJuvenileId(juvenileNum);
	CompositeResponse juvenileReferralsResp = MessageUtil.postRequest(getJuvenileRerralsEvent);
	juvprogrefs = (ArrayList) MessageUtil.compositeToCollection(juvenileReferralsResp, ProductionSupportJuvenileReferralResponseEvent.class);

	if (juvprogrefs != null)
	{
	    regform.setJuvprogrefCount(juvprogrefs.size());
	    //regform.setHdnCount(juvprogrefs.size());
	    //sorts in descending order by seq num.
	    Collections.sort((List<ProductionSupportJuvenileReferralResponseEvent>) juvprogrefs, Collections.reverseOrder(new Comparator<ProductionSupportJuvenileReferralResponseEvent>() {
		@Override
		public int compare(ProductionSupportJuvenileReferralResponseEvent evt1, ProductionSupportJuvenileReferralResponseEvent evt2)
		{
		    return Integer.valueOf(evt2.getReferralNum()).compareTo(Integer.valueOf(evt1.getReferralNum()));
		}
	    }));
	    regform.setJuvprogrefs(juvprogrefs);
	    forward = "listSuccess";
	    
	}
	else
	{
	    //regform.setMsg();
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile referral record not found.  Please retry search."));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward("error");
	}
	return aMapping.findForward(forward);
    }

    public ActionForward submitSeal(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
	ProdSupportForm regform = (ProdSupportForm) aForm;
	String forward = "success";
	String reffNum = "";
	String juvenileNum = "";
	reffNum = regform.getReferralNum();
	
	

	//reffNum = aRequest.getParameter("reffNum");
	if (reffNum != null && !reffNum.equals(""))
	{
	    juvenileNum = regform.getJuvenileId().trim();
	    ArrayList juvReferrals = null;

	    int cnt=0;
	    boolean isSelected=false;
	    Collection<JuvenileProfileReferralListResponseEvent> referralList = JuvenileFacilityHelper.getJuvReferralDetails(juvenileNum);
		Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referralList.iterator();
		while (referralIterator.hasNext())
		{
		    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
		    if (referral != null && (referral.getRecType().equalsIgnoreCase("REFERRAL") || referral.getRecType().equalsIgnoreCase("I.REFERRAL")))
		    {
			cnt+=1;
			if(referral.getReferralNumber().equalsIgnoreCase(reffNum))
			    isSelected = true;
		    }
		}
	    if(cnt==1 && isSelected){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The referral number is the only referral assigned and has an active record type.  Juvenile Master Seal is required"));
		saveErrors(aRequest, errors);
		return aMapping.findForward("listError");
	    }
	    
	    // if active facility booking
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    //check if Juvenile in Facility		       
	    JJSHeader head = JuvenileFacilityHelper.getJJSHeader(juvenileNum);
	    if (head != null)
	    {
		GetJuvenileFacilityDetailsEvent event = (GetJuvenileFacilityDetailsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYDETAILS);
		event.setJuvenileNum(juvenileNum);
		//event.setReferralNum(reffNum);
		//event.setFacilitySequenceNum(head.getLastSequenceNumber());

		dispatch.postEvent(event);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse responses = (CompositeResponse) replyEvent;

		Collection<JuvenileDetentionFacilitiesResponseEvent> juvFacilityDetails = MessageUtil.compositeToCollection(responses, JuvenileDetentionFacilitiesResponseEvent.class);
		Iterator facIter = juvFacilityDetails.iterator();
		while (facIter.hasNext())
		{
		    JuvenileDetentionFacilitiesResponseEvent detFac = new JuvenileDetentionFacilitiesResponseEvent();
		    if (facIter.hasNext())
		    {
			detFac = (JuvenileDetentionFacilitiesResponseEvent) facIter.next();
		    }
		    if (detFac.getCurrentReferral() != null && detFac.getReferralNumber() != null)
		    {
			if (head.getFacilityStatus() != null && (detFac.getCurrentReferral().equalsIgnoreCase(reffNum) || detFac.getReferralNumber().equalsIgnoreCase(reffNum)))//|| detFac.
			{
			    ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile is currently booked in a facility. Referral cannot be Sealed"));
			    saveErrors(aRequest, errors);
			    return aMapping.findForward("listError");
			}
		    }
		}
	    }
	    //check if its closed
	    GetProductionSupportJuvenileReferralsEvent getJuvenileRerralsEvent = (GetProductionSupportJuvenileReferralsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEREFERRALS);
	    getJuvenileRerralsEvent.setJuvenileId(juvenileNum);
	    getJuvenileRerralsEvent.setReferralNum(reffNum);
	    CompositeResponse juvenileReferralsResp = MessageUtil.postRequest(getJuvenileRerralsEvent);
	    //juvReferrals = (ArrayList) MessageUtil.compositeToCollection(juvenileReferralsResp, ProductionSupportJuvenileReferralResponseEvent.class);
	    Collection<ProductionSupportJuvenileReferralResponseEvent> juvReffDetails = MessageUtil.compositeToCollection(juvenileReferralsResp, ProductionSupportJuvenileReferralResponseEvent.class);
	    Iterator refIter = juvReffDetails.iterator();
	    if (juvReffDetails != null)
	    {
		ProductionSupportJuvenileReferralResponseEvent reff = new ProductionSupportJuvenileReferralResponseEvent();
		if (refIter.hasNext())
		{
		    reff = (ProductionSupportJuvenileReferralResponseEvent) refIter.next();
		}
		if (reff.getCloseDate() == null || reff.getCloseDate() == "")
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile referral is currently active.  Referral must be CLOSED to allow sealing"));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward("listError");
		}

	    }
	    // if associated to casefile
	    GetCasefileWithReferralsEvent req = (GetCasefileWithReferralsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILEWITHREFERRALS);
	    req.setJuvenileNum(juvenileNum);
	    //req.setReferralNum(reffNum);
	    List casefileList = MessageUtil.postRequestListFilter(req, JuvenileCasefileReferralDetailResponseEvent.class);

	    Collections.sort((List) casefileList, JuvenileCasefileReferralDetailResponseEvent.DateRefComparator);

	    if (casefileList.size() == 1)
	    {
		for (int i = 0; i < casefileList.size(); i++)
		{
		    String caseFileId, caseReffNum;
		    JuvenileCasefile casefile = null;
		    JuvenileCasefileReferralDetailResponseEvent response = (JuvenileCasefileReferralDetailResponseEvent) casefileList.get(i);
		    caseFileId = response.getCaseFileId();
		    caseReffNum = response.getReferralNumber();
		    if (caseReffNum.equalsIgnoreCase(reffNum))
		    {
			if (caseFileId != null && !caseFileId.equals(""))
			{

			    casefile = JuvenileCasefile.find(caseFileId);
			    Code status = casefile.getCaseStatus();
			    if (!status.getCode().equalsIgnoreCase("C"))
			    {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Referral assignment is associated to a supervision number and is the only assignment.  Supervision must be closed to allow seal"));
				saveErrors(aRequest, errors);
				return aMapping.findForward("listError");

			    }
			}
		    }
		}
	    }

	    ProductionSupportJuvenileReferralResponseEvent record = retrieveRecord(regform, reffNum);
	    UpdateProductionSupportReferralSealEvent updateReferralSeal = (UpdateProductionSupportReferralSealEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTREFERRALSEAL);

	    String sealComments = regform.getSealComments();
	    String sealDate = regform.getSealedDate();
	    updateReferralSeal.setJuvenileId(juvenileNum);
	    updateReferralSeal.setReferralNum(reffNum);
	    updateReferralSeal.setSealedComments(sealComments);
	    updateReferralSeal.setSealedDate(sealDate);
	    updateReferralSeal.setOID(record.getReferralOID());
	    if(updateReferralSeal.getAction() == null){
		updateReferralSeal.setAction("seal");
	    }
	    CompositeResponse compResp = MessageUtil.postRequest(updateReferralSeal);
	    Object errResp = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
	    if (errResp != null)
	    {

		log.info("UPDATE JUVENILE REFERRAL MASTER: " + SecurityUIHelper.getLogonId());
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Referral failed to seal."));
		saveErrors(aRequest, errors);
		return aMapping.findForward("listError");
	    }
	    else
	    {
		//court records sealing
		IDispatch disp = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJJSCourtEvent courtEvt = (GetJJSCourtEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURT);
		courtEvt.setJuvenileNumber(juvenileNum);
		courtEvt.setReferralNumber(reffNum);
		disp.postEvent(courtEvt);
		CompositeResponse response = (CompositeResponse) disp.getReply();
		//Court Response.
		List<DocketEventResponseEvent> crtdktRespEvts = MessageUtil.compositeToList(response, DocketEventResponseEvent.class);

		if (crtdktRespEvts != null && !crtdktRespEvts.isEmpty())
		{
		    Iterator<DocketEventResponseEvent> crtdktRespEvtsItr = crtdktRespEvts.iterator();
		    while (crtdktRespEvtsItr.hasNext())
		    {
			DocketEventResponseEvent crtDocResp = (DocketEventResponseEvent) crtdktRespEvtsItr.next();
			if (crtDocResp != null)
			{
			    String docketId = crtDocResp.getDocketEventId();
			    UpdateCourtRecordSealOnReferralSealEvent updateCourtSeal = (UpdateCourtRecordSealOnReferralSealEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATECOURTRECORDSEALONREFERRALSEAL);
			    updateCourtSeal.setDocketId(docketId);
			    updateCourtSeal.setAction("seal");
			    CompositeResponse compRes = MessageUtil.postRequest(updateCourtSeal);
			    Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
			    if (errRes != null)
			    {

				log.info("UPDATE COURT RECORD: " + SecurityUIHelper.getLogonId());

				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Court Records failed to Seal."));
				saveErrors(aRequest, errors);
				return aMapping.findForward("listError");
			    }

			}
		    }
		}
		//detention records sealing
		
		GetJJSCLDetentionByJuvNumRefNumEvent detentionEvt = (GetJJSCLDetentionByJuvNumRefNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYJUVNUMREFNUM);
		detentionEvt.setJuvenileNumber(juvenileNum);
		detentionEvt.setReferralNumber(reffNum);
		dispatch.postEvent(detentionEvt);
		CompositeResponse detentionResp = (CompositeResponse) dispatch.getReply();

		List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detentionResp, DocketEventResponseEvent.class);
		//Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
		
		if (docketResponses != null && !docketResponses.isEmpty())
		{
		    Iterator<DocketEventResponseEvent> crtdetdktRespEvtsItr = docketResponses.iterator();
		    while (crtdetdktRespEvtsItr.hasNext())
		    {
			DocketEventResponseEvent crtDetDocResp = (DocketEventResponseEvent) crtdetdktRespEvtsItr.next();
			if (crtDetDocResp != null)
			{
			    String docketId=crtDetDocResp.getDocketEventId();
			    UpdateDetentionCourtRecordSealOnReferralSealEvent updateDetCourtSeal = (UpdateDetentionCourtRecordSealOnReferralSealEvent) 
				EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEDETENTIONCOURTRECORDSEALONREFERRALSEAL) ;
			    updateDetCourtSeal.setDocketId(docketId);
			    updateDetCourtSeal.setAction("seal");
			    CompositeResponse compRes = MessageUtil.postRequest( updateDetCourtSeal );
				Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
				if (errRes != null){
				    
					log.info("UPDATE DETENTION COURT RECORD: " + SecurityUIHelper.getLogonId());
					
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Detention Records failed to Seal."));
					saveErrors(aRequest, errors);
					return aMapping.findForward("listError");
				}					
			   
			}
		    }
		}
		//petition records sealing
		GetJJSPetitionsEvent petitionEvent = (GetJJSPetitionsEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJJSPETITIONS);
		petitionEvent.setJuvenileNum(juvenileNum);
		petitionEvent.setReferralNum(reffNum);
		dispatch.postEvent(petitionEvent);
		CompositeResponse compositeResp = MessageUtil.postRequest(petitionEvent);
		List<PetitionResponseEvent> petResponses = MessageUtil.compositeToList(compositeResp, PetitionResponseEvent.class);
		//PetitionResponseEvent petitionResponseEvent = (PetitionResponseEvent) MessageUtil.filterComposite(compositeResp, PetitionResponseEvent.class);
		if (petResponses != null && !petResponses.isEmpty())
		{
		    Iterator<PetitionResponseEvent> petRespEvtsItr = petResponses.iterator();
		    while (petRespEvtsItr.hasNext())
		    {
			PetitionResponseEvent petResp = (PetitionResponseEvent) petRespEvtsItr.next();
			if (petResp != null)
			{
			    String oid=petResp.getOID();
			    UpdatePetitionRecordSealOnReferralSealEvent updatePetSeal = (UpdatePetitionRecordSealOnReferralSealEvent) 
				EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPETITIONRECORDSEALONREFERRALSEAL) ;
			    updatePetSeal.setOID(oid);
			    updatePetSeal.setAction("seal");
			    CompositeResponse compRes = MessageUtil.postRequest( updatePetSeal );
				Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
				if (errRes != null){
				    
					log.info("UPDATE PETITION RECORD: " + SecurityUIHelper.getLogonId());
					
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Petition Records failed to Seal."));
					saveErrors(aRequest, errors);
					return aMapping.findForward("listError");
				}					
			   
			}
		    }
		}
		//header record sealing
		//detention records by juvenile
		/*dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJuvenileDetentionFacilitiesEvent event = (GetJuvenileDetentionFacilitiesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILEDETENTIONFACILITIES);
		event.setJuvenileNum(juvenileNum);
		CompositeResponse resp = MessageUtil.postRequest(event);
		List<JuvenileDetentionFacilitiesResponseEvent> facResponses = MessageUtil.compositeToList(resp, JuvenileDetentionFacilitiesResponseEvent.class);
		//detention records by juvenile and referral
		dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJuvenileDetentionFacilitiesEvent event2 = (GetJuvenileDetentionFacilitiesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILEDETENTIONFACILITIES);
		event2.setJuvenileNum(juvenileNum);
		event2.setReferralNum(reffNum);
		CompositeResponse res = MessageUtil.postRequest(event);
		List<JuvenileDetentionFacilitiesResponseEvent> facResponsesWithRef = MessageUtil.compositeToList(resp, JuvenileDetentionFacilitiesResponseEvent.class);
		if(facResponses.size()==facResponsesWithRef.size())
		{
		
		}*/
		//release details update
		
			    UpdateReleaseRecordOnReferralSealEvent updateRelease = (UpdateReleaseRecordOnReferralSealEvent) 
				EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATERELEASERECORDONREFERRALSEAL) ;
			    updateRelease.setJuvenileNum(juvenileNum);
			    updateRelease.setReferralNum(reffNum);
			    CompositeResponse compRes = MessageUtil.postRequest( updateRelease );
				Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
				if (errRes != null){
				    
					log.info("UPDATE PETITION RECORD: " + SecurityUIHelper.getLogonId());
					
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Petition Records failed to Seal."));
					saveErrors(aRequest, errors);
					return aMapping.findForward("listError");
				}					
			   
		
		ActionMessages messageHolder = new ActionMessages();
		//messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.referralNumberSealed"));//("Referral# "+reffNum+" has been sealed for "+juvenileNum+""));
		messageHolder.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("prompt.referralNumberSealed",reffNum, juvenileNum));
		aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
		saveMessages(aRequest, messageHolder);
		/*regform.setMsg("Referral# "+reffNum+" has been sealed for "+juvenileNum+"");*/
		//return aMapping.findForward("listSuccess");
		//search again bug 125599
		return submitSearch(aMapping, aForm, aRequest, aResponse);
	    }

	}
	return aMapping.findForward(forward);
    }

    private ProductionSupportJuvenileReferralResponseEvent retrieveRecord(ProdSupportForm regform, String reffNum)
    {

	ArrayList juvprogrefs = regform.getJuvprogrefs();
	ProductionSupportJuvenileReferralResponseEvent record = null;
	/*
	Iterator iter = juvprogrefs.iterator();
	if (iter.hasNext())
	{
	    //juvprogrefs.
		record = (ProductionSupportJuvenileReferralResponseEvent)iter.next();
	}*/
	for (int i = 0; i < juvprogrefs.size(); i++)
	{
	    ProductionSupportJuvenileReferralResponseEvent resp = (ProductionSupportJuvenileReferralResponseEvent) juvprogrefs.get(i);
	    if (reffNum.equals(resp.getReferralNum()))
	    {
		record = (ProductionSupportJuvenileReferralResponseEvent) juvprogrefs.get(i);
	    }

	}
	return record;

    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	form.clearSpecialProcessingResults();
	return aMapping.findForward("back");
    }
    

}

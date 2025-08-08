package ui.juvenilecase.prodsupport.action.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetAllJuvenileOffenseCodesEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByJuvNumEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByJuvNumEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.GetProductionSupportCourtRecordsEvent;
import messaging.productionsupport.GetProductionSupportJuvenileReferralsEvent;
import messaging.productionsupport.GetProductionSupportPetitionDetailsEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileReferralResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProductionSupportControllerServiceNames;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.codetable.SimpleCodeTableHelper;
import pd.juvenile.JuvenileBuilder;
import pd.juvenilecase.referral.JJSOffense;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.ProdSupportForm;

//import mojo.km.utilities.DateUtil;

/**
 * @author aPillai
 */

public class DeletePetitionRecordQueryAction extends Action
{

    /* protected void addButtonMapping(Map keyMap)
     {	
    keyMap.put("button.submit", "submit");
    keyMap.put("button.cancel", "cancel");
    keyMap.put("button.refresh", "refresh");
    keyMap.put("button.back", "back");
      }*/
    private Logger log = Logger.getLogger("DeletePetitionRecordQueryAction");

    /* public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
     {*/
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

	ProdSupportForm regform = (ProdSupportForm) form;

	/** Check for initial load of this page **/
	String clrChk = request.getParameter("clr");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y"))
	{
	    regform.setJuvenileId("");
	    regform.setReferralId("");
	    regform.clearAllResults();
	    regform.setMsg("");
	    return mapping.findForward("error");
	}
	ArrayList<PetitionResponseEvent> petitionDetails = null;
	JJSJuvenileResponseEvent juvenileDetail = null;
	ArrayList<JJSOffense> offenses = null;
	ArrayList<ProductionSupportJuvenileReferralResponseEvent> referralDetails = null;
	ArrayList<JuvenileOffenseCodeResponseEvent> offenseCodes = null;
	regform.setMsg("");
	regform.setPetitionDetails(null);

	String juvenileNum = regform.getJuvenileId();
	String referralNum = regform.getReferralId();

	offenseCodes = retrieveOffenseCodes();
	regform.setOffenseCodes(offenseCodes);

	if (isNumeric(juvenileNum) && isNumeric(referralNum))
	{
	    juvenileDetail = retrieveJuvenile(juvenileNum);
	    if (juvenileDetail != null)
	    {
		juvenileDetail.setDob(formatDate(juvenileDetail.getBirthDate()));
		if (juvenileDetail.getStatusId() != null)
		{
		    //juvenileDetail.setStatusId(CodeHelper.getCodeDescription(PDCodeTableConstants.JUVENILE_PROFILE_STATUS, juvenileDetail.getStatusId()));
		    String masterStatus = JuvenileBuilder.getMasterStatusDesc(juvenileDetail.getStatusId());
		    juvenileDetail.setMasterStatus(masterStatus);
		}
		regform.setJuvenileDetail(juvenileDetail);
		referralDetails = retrieveReferrral(juvenileNum, referralNum);
		if (referralDetails.size() > 0)
		{
		    offenses = retrieveOffenseForReferral(juvenileNum, referralNum);
		    if (offenses != null && offenses.size() > 0)
		    {
			if (offenses.size() > 1)
			{
			    Collections.sort(offenses, compareBySequence);
			    Collections.reverse(offenses);
			}
			regform.setCurrentOffense(offenses.get(0).getOffenseCode().getOffenseCode());
		    }

		    for (int i = 0; i < referralDetails.size(); i++)
		    {
			if (referralDetails.get(i).getCloseDate() != null)
			{
			    referralDetails.get(i).setCloseDate("Closed");
			}
			else
			{
			    referralDetails.get(i).setCloseDate("Active");
			}
		    }
		    regform.setReferralDetails(referralDetails);
		    petitionDetails = retrievePetitionDetails(juvenileNum, referralNum);
		}
		else
		{
		    regform.setMsg("No Petition detail records found for Juvenile Number " + juvenileNum + " and Referral Number " + referralNum);
		    return mapping.findForward("error");
		}
	    }
	    else
	    {
		regform.setMsg("No Juvenile record found for Juvenile Number " + juvenileNum);
		return mapping.findForward("error");
	    }
	}
	else
	{
	    regform.setMsg("You must enter a valid Juvenile Number and a valid Referral Number.");
	    return mapping.findForward("error");
	}

	if (petitionDetails != null && petitionDetails.size() > 0)
	{
	    regform.setPetitionDetails(petitionDetails);
	}
	else
	{
	    regform.setMsg("No Petition detail records found for Juvenile Number " + juvenileNum + " and Referral Number " + referralNum);
	    return mapping.findForward("error");
	}

	return mapping.findForward("success");

    }
    @SuppressWarnings("unchecked")
    private ArrayList<PetitionResponseEvent> retrievePetitionDetails(String juvenileNum,
	    								String referralNum
	    								) throws Exception {
	
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetProductionSupportPetitionDetailsEvent getPetitionEvent = (GetProductionSupportPetitionDetailsEvent)
									EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTPETITIONDETAILS );
	getPetitionEvent.setJuvenileNum(juvenileNum);
	getPetitionEvent.setReferralNum(referralNum);
	dispatch.postEvent(getPetitionEvent);
	
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	Collection<PetitionResponseEvent> petitionEvents =
		MessageUtil.compositeToCollection(compositeResponse,PetitionResponseEvent.class);

        ArrayList<PetitionResponseEvent> petitionEventsList =  new ArrayList<PetitionResponseEvent>();
        if (petitionEvents!=null && petitionEvents.size() > 0){
        	petitionEventsList.addAll(petitionEvents);
        }
        return petitionEventsList;	
	
	
    }
    
    private JJSJuvenileResponseEvent retrieveJuvenile (String juvenileNum) throws Exception
    {

	GetJJSJuvenileEvent request = (GetJJSJuvenileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILE);
	request.setJuvenileNum(juvenileNum);

	JJSJuvenileResponseEvent juvenileResp = (JJSJuvenileResponseEvent) MessageUtil.postRequest(request, JJSJuvenileResponseEvent.class);

	return juvenileResp;
    }
    
    
    @SuppressWarnings("unchecked")
    private ArrayList<ProductionSupportJuvenileReferralResponseEvent> retrieveReferrral(String juvenileNum,
	    											String referralNum
	    											) throws Exception
    {
	ArrayList<ProductionSupportJuvenileReferralResponseEvent> juvReferrals = null;

	// Get and set Associated JuvProgRefs
	GetProductionSupportJuvenileReferralsEvent getJuvenileRerralsEvent = (GetProductionSupportJuvenileReferralsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEREFERRALS);
	getJuvenileRerralsEvent.setJuvenileId(juvenileNum);
	getJuvenileRerralsEvent.setReferralNum(referralNum);
	CompositeResponse juvenileReferralsResp = MessageUtil.postRequest(getJuvenileRerralsEvent);
	juvReferrals = (ArrayList<ProductionSupportJuvenileReferralResponseEvent>)
				MessageUtil.compositeToCollection(juvenileReferralsResp, ProductionSupportJuvenileReferralResponseEvent.class);

	return juvReferrals;
    }
    
    private ArrayList<JJSOffense>retrieveOffenseForReferral(String juvenileNum,
	    							String referralNum
	    							) throws Exception {

	ArrayList<JJSOffense> offenses = new ArrayList<JJSOffense>();
	GetJuvenileCasefileOffensesEvent event = new GetJuvenileCasefileOffensesEvent();
	event.setJuvenileNum(juvenileNum);
	event.setReferralNum(referralNum);

	Iterator<JJSOffense> offensesEvent = JJSOffense.findAll(event);
	while (offensesEvent.hasNext())
	{
		JJSOffense offense = offensesEvent.next();
		offenses.add(offense);
	}
	
	return offenses;
    }
    
    /**
     * 
     * @return
     */
    private ArrayList<JuvenileOffenseCodeResponseEvent>retrieveOffenseCodes(){
	
	GetAllJuvenileOffenseCodesEvent requestEvent = (GetAllJuvenileOffenseCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETALLJUVENILEOFFENSECODES);
	ArrayList codes = (ArrayList) MessageUtil.postRequestListFilter(requestEvent, JuvenileCasefileOffenseCodeResponseEvent.class);
	
	Collections.sort((List<JuvenileCasefileOffenseCodeResponseEvent>) codes, Collections.reverseOrder(new Comparator<JuvenileCasefileOffenseCodeResponseEvent>() {
	    public int compare(JuvenileCasefileOffenseCodeResponseEvent evt1, JuvenileCasefileOffenseCodeResponseEvent evt2)
	    {
		if (evt1.getAlphaCode() != null && evt2.getAlphaCode() != null)
		    return evt2.getAlphaCode().compareTo(evt1.getAlphaCode());
		else
		    return -1;
	    }
	}));
	
	return codes;
	
    }
    
    
    private boolean isNumeric ( String value ){
	return value.matches("\\d+");
    }
    
    private String formatDate(Date date){
	if( date!= null){
	    
		return new SimpleDateFormat("MM/dd/yyyy").format(date);

	}else{
	    return null;
	}
    }
    
    private Comparator<JJSOffense>compareBySequence = new Comparator<JJSOffense>(){
	@Override
	public int compare(JJSOffense o1, JJSOffense o2){
	    return o1.getSequenceNum().compareTo(o1.getSequenceNum());
	}
    };


}

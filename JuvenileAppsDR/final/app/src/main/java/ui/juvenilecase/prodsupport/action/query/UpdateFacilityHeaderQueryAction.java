package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.facility.GetJuvenileFacilityDetailsEvent;
import messaging.juvenile.SearchJuvenileProfileCasefileListEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.productionsupport.GetProductionSupportFacilityHeaderEvent;
import messaging.productionsupport.reply.ProductionSupportFacilityHeaderResponseEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFacilityControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter Query to Update Facility Header Fields
 */

public class UpdateFacilityHeaderQueryAction extends Action
{

    private Logger log = Logger.getLogger("UpdateFacilityHeaderQueryAction");

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

	ProdSupportForm regform = (ProdSupportForm) form;

	/** Check for initial load of this page **/
	String clrChk = request.getParameter("clr");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y"))
	{
	    regform.clearAllResults();
	    regform.setMsg("");
	    return mapping.findForward("error");
	}

	String juvenileId = regform.getJuvenileId();
	
	if (juvenileId == null || juvenileId.equals(""))
	{
	    regform.setMsg("Please enter a Valid Juvenile Id.");
	    return (mapping.findForward("error"));
	}
	
	try{
	    int a = Integer.parseInt(juvenileId);
	    
	}catch( NumberFormatException nfe ){
	    
	    regform.setMsg("Please enter a Valid Juvenile Id.");
	    return (mapping.findForward("error"));
	}

	//Log the query attempt
	log.info("[" + new Date() + "] ProdSupport (" + SecurityUIHelper.getLogonId().toUpperCase() + ") - Juvenile Query ID: " + juvenileId);

	ArrayList<ProductionSupportFacilityHeaderResponseEvent> facilityHeaderList = getFacilityHeader(juvenileId);

	if (facilityHeaderList != null && facilityHeaderList.size() > 0)
	{
	    regform.setFacilityHeaderCount(facilityHeaderList.size());
	    regform.setFacilityHeaderList(facilityHeaderList);
	    loadResponseToForm(facilityHeaderList, regform);
	    createStatusList(regform);
	    loadDropDownTables(regform);
	    loadBookingReferrals(regform);
	    loadSprvisionNumbers(regform);
	    loadCodeDescriptions(facilityHeaderList);
	    setFacilitySeqNum(regform);	    
	}
	else
	{
	    regform.setMsg("No Header Record Found.");
	    return mapping.findForward("error");
	}

	regform.setMsg("");
	return mapping.findForward("success");

    }

    /**
     * load the facility header record
     * 
     * @param referralNum
     * @return
     */
    public static ArrayList<ProductionSupportFacilityHeaderResponseEvent> getFacilityHeader(String juvenileId)
    {
	ArrayList<ProductionSupportFacilityHeaderResponseEvent> facilityHeaderList = null;

	// Get and set Associated JuvProgRefs
	GetProductionSupportFacilityHeaderEvent getFacilityHeaderEvent = (GetProductionSupportFacilityHeaderEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTFACILITYHEADER);
	getFacilityHeaderEvent.setJuvenileId(juvenileId);
	CompositeResponse facilityHeaderResp = MessageUtil.postRequest(getFacilityHeaderEvent);
	facilityHeaderList = (ArrayList<ProductionSupportFacilityHeaderResponseEvent>) MessageUtil.compositeToCollection(facilityHeaderResp, ProductionSupportFacilityHeaderResponseEvent.class);

	return facilityHeaderList;
    }

    /**
     * find and load all the dropdown lists on the form
     * 
     * @param regform
     */
    private void loadDropDownTables(ProdSupportForm regform)
    {
	/** Populate Drop-downs **/
	ArrayList<JuvenileFacilityResponseEvent> activeFacilitiesList = (ArrayList<JuvenileFacilityResponseEvent>) JuvenileFacilityHelper.loadActiveFacilities();
	if (activeFacilitiesList != null)
	{
	    regform.setActiveFacilitiesList(activeFacilitiesList);
	}
    }

    /**
     * find and load descriptions for each code shown on the form
     * 
     * @param headerResponseEventList
     */
    private void loadCodeDescriptions(ArrayList<ProductionSupportFacilityHeaderResponseEvent> headerResponseEventList)
    {
	for (ProductionSupportFacilityHeaderResponseEvent responseEvent : headerResponseEventList)
	{
	    // activeFacility
	    JuvenileFacilityResponseEvent activeFacilityResponseEvent = JuvenileFacilityHelper.getFacilityByCode(responseEvent.getFacilityCode());
	    if (activeFacilityResponseEvent != null)
	    {
		responseEvent.setFacilityName(activeFacilityResponseEvent.getDescription());
	    }
	    else
	    {
		responseEvent.setFacilityName("");
	    }
	}
    }

    /**
     * @param regform
     * @return
     */
    private void loadBookingReferrals(ProdSupportForm regform)
    {

	ArrayList referralList = new ArrayList();

	GetJuvenileProfileReferralListEvent event = (GetJuvenileProfileReferralListEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLIST);
	event.setJuvenileNum(regform.getJuvenileId());

	List referralResp = MessageUtil.postRequestListFilter(event, JuvenileProfileReferralListResponseEvent.class);

	if( referralResp.size() > 0 ){
	    
	    Collections.sort((List<JuvenileProfileReferralListResponseEvent>) referralResp, new Comparator<JuvenileProfileReferralListResponseEvent>() {
		    @Override
		    public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
		    {
			if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
			    return evt2.getReferralNumber().compareTo(evt1.getReferralNumber());
			else
			    return -1;
		    }
		});
		Map refMap = new TreeMap();
		Iterator refIter = referralResp.iterator();
		while (refIter.hasNext())
		{

		    JuvenileProfileReferralListResponseEvent refResp = (JuvenileProfileReferralListResponseEvent) refIter.next();
		    refMap.put(refResp.getReferralNumber(), refResp);

		}

		Iterator iter = refMap.entrySet().iterator();
		while (iter.hasNext())
		{
		    Map.Entry pair = (Map.Entry) iter.next();
		    CodeResponseEvent obj = new CodeResponseEvent();
		    obj.setDescription(pair.getKey().toString());
		    referralList.add(obj);
		    iter.remove();
		}
	}	

	regform.setBookingReferrals(referralList);

    }

    /**
     * @param regForm
     */
    private void loadSprvisionNumbers(ProdSupportForm regForm)
    {

	ArrayList supervisionList = new ArrayList();

	SearchJuvenileProfileCasefileListEvent search = (SearchJuvenileProfileCasefileListEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILECASEFILELIST);
	search.setJuvenileId(regForm.getJuvenileId());

	List casefiles = MessageUtil.postRequestListFilter(search, JuvenileProfileCasefileListResponseEvent.class);

	Collections.sort((List<JuvenileProfileCasefileListResponseEvent>) casefiles, new Comparator<JuvenileProfileCasefileListResponseEvent>() {
	    @Override
	    public int compare(JuvenileProfileCasefileListResponseEvent evt1, JuvenileProfileCasefileListResponseEvent evt2)
	    {
		if (evt1.getSupervisionNum() != null && evt2.getSupervisionNum() != null)
		    return evt2.getSupervisionNum().compareTo(evt1.getSupervisionNum());
		else
		    return -1;
	    }
	});
	Map supervisionMap = new TreeMap();

	Iterator sprvIter = casefiles.iterator();
	while (sprvIter.hasNext())
	{

	    JuvenileProfileCasefileListResponseEvent response = (JuvenileProfileCasefileListResponseEvent) sprvIter.next();
	    if ("PENDING".equalsIgnoreCase(response.getCaseStatus()))
	    {

	    }
	    else
	    {

		supervisionMap.put(response.getSupervisionNum(), response);
	    }
	}

	Iterator iter = supervisionMap.entrySet().iterator();
	while (iter.hasNext())
	{
	    Map.Entry pair = (Map.Entry) iter.next();
	    CodeResponseEvent obj = new CodeResponseEvent();
	    obj.setDescription(pair.getKey().toString());
	    supervisionList.add(obj);
	    iter.remove();
	}
	regForm.setBookingSprvisionNumbers(supervisionList);
    }

    private void createStatusList(ProdSupportForm regForm)
    {

	ArrayList<CodeResponseEvent> list = new ArrayList<CodeResponseEvent>();
	String[][] obj = { { "D", "Detained in) Detention Facility" }, 
		{ "E", "Escape (a quasi-release)" }, { "N", "In Transfer" }, 
		{ "P", "Placement" }, { "R", "Returned to custody following Escape" }, 
		{ "T", "Temporary Release" }, { "V", "Diversion Program" } };

	    CodeResponseEvent codeObj = new CodeResponseEvent();
	    codeObj.setCode(obj[0][0]);
	    codeObj.setDescription((obj[0][1]));
	    list.add(codeObj);
	    
	    CodeResponseEvent codeObj2 = new CodeResponseEvent();
	    codeObj2.setCode(obj[1][0]);
	    codeObj2.setDescription((obj[1][1]));
	    list.add(codeObj2);
	    
	    CodeResponseEvent codeObj3 = new CodeResponseEvent();
	    codeObj3.setCode(obj[2][0]);
	    codeObj3.setDescription((obj[2][1]));
	    list.add(codeObj3);
	    
	    CodeResponseEvent codeObj4 = new CodeResponseEvent();
	    codeObj4.setCode(obj[3][0]);
	    codeObj4.setDescription((obj[3][1]));
	    list.add(codeObj4);
	    
	    CodeResponseEvent codeObj5 = new CodeResponseEvent();
	    codeObj5.setCode(obj[4][0]);
	    codeObj5.setDescription((obj[4][1]));
	    list.add(codeObj5);
	    
	    CodeResponseEvent codeObj6 = new CodeResponseEvent();
	    codeObj6.setCode(obj[5][0]);
	    codeObj6.setDescription((obj[5][1]));
	    list.add(codeObj6);
	    
	    CodeResponseEvent codeObj7 = new CodeResponseEvent();
	    codeObj7.setCode(obj[6][0]);
	    codeObj7.setDescription((obj[6][1]));
	    list.add(codeObj7);
	    
	    regForm.setStatusCodes(list);

    }

    /**
     * @param juvenileNum
     * @return
     */
    private void setFacilitySeqNum(ProdSupportForm regForm)
    {

	GetJuvenileFacilityDetailsEvent event = (GetJuvenileFacilityDetailsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYDETAILS);
	event.setJuvenileNum(regForm.getJuvenileId());

	List<JuvenileDetentionFacilitiesResponseEvent> juvFacilityDetails = MessageUtil.postRequestListFilter(event, JuvenileDetentionFacilitiesResponseEvent.class);

	if (juvFacilityDetails.size() > 0)
	{

	    Collections.sort((List<JuvenileDetentionFacilitiesResponseEvent>) juvFacilityDetails, new Comparator<JuvenileDetentionFacilitiesResponseEvent>() {
		@Override
		public int compare(JuvenileDetentionFacilitiesResponseEvent evt1, JuvenileDetentionFacilitiesResponseEvent evt2)
		{
		    if (evt1.getFacilitySequenceNumber() != null && evt2.getFacilitySequenceNumber() != null)
			 return Integer.valueOf(evt2.getFacilitySequenceNumber()).compareTo(Integer.valueOf(evt1.getFacilitySequenceNumber()));
		    else
			return -1;
		}
	    });

	    JuvenileDetentionFacilitiesResponseEvent detentionRec = juvFacilityDetails.get(0);

	    regForm.setSequenceNum(detentionRec.getFacilitySequenceNumber());
	    regForm.setBookingSupervisionNum(detentionRec.getBookingSupervisionNum());

	}
    }

    private void loadResponseToForm(ArrayList<ProductionSupportFacilityHeaderResponseEvent> respList, ProdSupportForm psform)
    {

	if (respList.size() > 0)
	{

	    ProductionSupportFacilityHeaderResponseEvent resp = respList.get(0);
	    psform.setNewReferralNum(resp.getBookingReferralNum());
	    psform.setNewcasefileId(resp.getBookingSupervisionNum());
	    psform.setNewJuvseqnum(resp.getLastSequenceNum());
	    psform.setNewHeaderFacilityCd(resp.getFacilityCode());
	    psform.setFacilityStatus(resp.getFacilityStatusCode());
	    psform.setNewBeginDate(resp.getNextHearingDate());
	    psform.setNewEndDate(resp.getProbableCauseHearingDate());
	}
    }

}

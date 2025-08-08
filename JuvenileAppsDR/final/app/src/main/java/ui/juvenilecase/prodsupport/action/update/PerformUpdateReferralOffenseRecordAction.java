package ui.juvenilecase.prodsupport.action.update;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetAllJuvenileOffenseCodesEvent;
import messaging.codetable.GetAllOffenseCodesEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.productionsupport.GetProductionSupportJuvenileReferralsEvent;
import messaging.productionsupport.UpdateReferralOffenseEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileReferralResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import pd.juvenilecase.referral.JJSReferral;
import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author apillai
 */

public class PerformUpdateReferralOffenseRecordAction extends JIMSBaseAction
{
    @Override
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.validateOffenseCode", "validateOffenseCode");
	keyMap.put("button.searchForOffenseCode", "goToOffenseSearch");
	keyMap.put("button.findOffenses", "findOffenses");
	keyMap.put("button.refresh", "refresh");
	keyMap.put("button.back", "back");
	keyMap.put("button.select", "returnSelect");
	keyMap.put("button.updateOffense","submitOffenseRecordUpdate");
    }

    private Logger log = Logger.getLogger("PerformUpdateDistrictCourtCalendarRecordAction");
    public ActionForward goToOffenseSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;	
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAlphaCodeId(UIConstants.EMPTY_STRING);
	form.setShortDesc(UIConstants.EMPTY_STRING);
	form.setDpsCodeId(UIConstants.EMPTY_STRING);
	form.setCategoryId(UIConstants.EMPTY_STRING);
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setOffenseResultList(new ArrayList());
	
	GetAllOffenseCodesEvent requestEvent = (GetAllOffenseCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETALLOFFENSECODES);
	List codes = MessageUtil.postRequestListFilter(requestEvent, JuvenileCasefileOffenseCodeResponseEvent.class);

	Collections.sort(codes);

	form.setCodetableDataList(codes);	
	return aMapping.findForward(UIConstants.COURT_OFFENSE_SUCCESS);
    }
    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
    {
	ProdSupportForm form = (ProdSupportForm)aForm;
 	   form.setAlphaCodeId(UIConstants.EMPTY_STRING);
 	   form.setShortDesc(UIConstants.EMPTY_STRING);
 	   form.setDpsCodeId(UIConstants.EMPTY_STRING);
 	   form.setCategoryId(UIConstants.EMPTY_STRING);
 	   form.setErrMessage(UIConstants.EMPTY_STRING);
 	   return aMapping.findForward("courtOffenseSuccess"); 
    }
    public ActionForward findOffenses(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
    {
	ProdSupportForm form = (ProdSupportForm)aForm;
 	   form.setSelectedValue(UIConstants.EMPTY_STRING);
 	   form.setErrMessage(UIConstants.EMPTY_STRING);
 	   List temp1 = new ArrayList();
 	   List temp2 = new ArrayList();
 	   List temp3 = new ArrayList();
 	   List temp4 = new ArrayList();
 	   List codeTableList = (List) form.getCodetableDataList(); 	  
 	  String alphaCode = aRequest.getParameter("acId");
	   String shortDesc = aRequest.getParameter("descId");
	   String dpsCode = aRequest.getParameter("dpsId");
	   String category = aRequest.getParameter("catId");
 	   if ("".equalsIgnoreCase(alphaCode) ){
 		   temp1 = codeTableList;
 	   } else {
 		   for (int x=0; x<codeTableList.size(); x++){
 			   JuvenileCasefileOffenseCodeResponseEvent ocEvent  = (JuvenileCasefileOffenseCodeResponseEvent) codeTableList.get(x);
 			 //(DPS code fix for bug 37346)
 			   if (ocEvent.getAlphaCode().indexOf(alphaCode) == 0 && ((ocEvent.getDpsCode().length() > 1))){
 				   temp1.add(ocEvent);
 			   }
 		   }
 	   }
 	   alphaCode = null;
 	   // narrow results by short description
 	   if ("".equalsIgnoreCase(shortDesc) ){
 		   temp2 = temp1;
 	   } else {	   
 		   for (int x=0; x<temp1.size(); x++){
 			   JuvenileCasefileOffenseCodeResponseEvent ocEvent  = (JuvenileCasefileOffenseCodeResponseEvent) temp1.get(x);
 			   //(DPS code fix for bug 37346)
 			   if (ocEvent.getShortDescription().indexOf(shortDesc) > -1 && ((ocEvent.getDpsCode().length() > 1))){
 				   temp2.add(ocEvent);
 			   }
 		   }
 	   }
 	   temp1 = null;
 	   shortDesc = null;
 	// narrow results by DPS Code
 	   if ("".equalsIgnoreCase(dpsCode) ){
 		   temp3 = temp2;
 	   } else {	   
 		   for (int x=0; x<temp2.size(); x++){
 			   JuvenileCasefileOffenseCodeResponseEvent ocEvent  = (JuvenileCasefileOffenseCodeResponseEvent) temp2.get(x);
 			   if (ocEvent.getDpsCode().indexOf(dpsCode) == 0){
 				   temp3.add(ocEvent);
 			   }
 		   }
 	   }
 	   temp2 = null;
 	   dpsCode = null;
 	// narrow results by Severity Level (category)
 	   if ("".equalsIgnoreCase(category) ){
 		   temp4 = temp3;
 	   } else {  
 		   for (int x=0; x<temp3.size(); x++){
 			   JuvenileCasefileOffenseCodeResponseEvent ocEvent  = (JuvenileCasefileOffenseCodeResponseEvent) temp3.get(x);
 	// Task 36326 && ((ocEvent.getDpsCode().length() > 1) addition to existing condition
 			   if ((ocEvent.getCategory().indexOf(category) == 0) && ((ocEvent.getDpsCode().length() > 1))){
 				      temp4.add(ocEvent);
 				  
 			   }
 		   }
 	   }
 	   
 	   form.setOffenseResultList(temp4);
 	   if (temp4.size() == 0){
 		   form.setErrMessage("No records found for supplied search criteria");
 	   }
 	   temp3 = null;
 	   temp4 = null;
 	   category = null;

 	   return aMapping.findForward("courtOffenseSuccess"); 
    }
    public ActionForward submitOffenseRecordUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	ProdSupportForm regform = (ProdSupportForm) form;
	String logonid = SecurityUIHelper.getLogonId();
	String actionFwd = "";
	String referralNumberOriginal = regform.getReferralId();

	JJSOffenseResponseEvent record = regform.getReferralOffense();
	
	
	

	if (record == null)
	{
	    regform.setMsg("PerformUpdateReferralOffenseRecordAction.java (73) - Could not retrieve record.");
	    return (mapping.findForward("error"));
	}
	//validate offense
	if (record.getOffenseCode() != null && !record.getOffenseCode().equals(""))
	{
	    String severityLevel = "";
	    JuvenileOffenseCodeResponseEvent jocEvent = JuvenileDistrictCourtHelper.validateWithAllOffenseCd(record.getOffenseCode());
	    if (jocEvent != null)
	    {
		severityLevel = jocEvent.getSeverity();
		record.setOffenseSeverity(severityLevel);
	    }
	    else
	    {
		regform.setCursorPosition("Allegation");
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Offense Code. Please Correct and Retry"));
		saveErrors(request, errors);		
		return (mapping.findForward("error"));
	    }
	}
	// end validate offense	
	
	
	//get referral date of new referral entered
	String referralDateStringNew = this.getRefferalDateByJuvAndRefNo(record.getJuvenileNum(), record.getReferralNum());
	
	if(referralDateStringNew == null || "".equals(referralDateStringNew)){
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The referral date for the referral number(" + record.getReferralNum() + ") is null. Confirm that the referral number exists. Original referral number: " + referralNumberOriginal));
	    saveErrors(request, errors);
	    //reset referral number to original one
	    //record.setReferralNum(referralNumberOriginal);
		
	    return (mapping.findForward("error"));	    
	}	
	
	if((referralDateStringNew != null && !"".equals(referralDateStringNew)) && (record.getOffDate() != null || !"".equals(record.getOffDate()))){
	    
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    Date referralDateNew = formatter.parse(referralDateStringNew);
	    Date offenseDateNew = formatter.parse(record.getOffDate());
	    //ensure that the offense date is not greater than the referral date
	    if(offenseDateNew.after(referralDateNew)){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The offense date (" + formatter.format(offenseDateNew) + ") cannot be greater than the referral date (" + formatter.format(referralDateNew) + "). Please review"));
		saveErrors(request, errors);
		//reset referral number to original one
		//record.setReferralNum(referralNumberOriginal);
		
		return (mapping.findForward("error"));    	    
	    }
	}
	
	
	
	//if (record.getJuvenileNumber().equals(regform.getJuvenileId()))
	if (record.getJuvenileNum() != null && !record.getJuvenileNum().isEmpty()&&record.getReferralNum() != null && !record.getReferralNum().isEmpty())
	{
	    UpdateReferralOffenseEvent updateOffense = (UpdateReferralOffenseEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEREFERRALOFFENSE);
	    // set values to update
	    updateOffense.setOID(record.getOID());
	    updateOffense.setJuvenileNum(record.getJuvenileNum());
	    updateOffense.setReferralNum(record.getReferralNum());
	    updateOffense.setOffenseCode(record.getOffenseCode());
	    updateOffense.setOffenseSeverity(record.getOffenseSeverity());	    
	    updateOffense.setOffDate(record.getOffDate());
	    updateOffense.setKeyMapLocation(record.getKeyMapLocation());	   
	    updateOffense.setInvestigationNum(record.getInvestigationNum());	    
	    updateOffense.setOffenseStreetNum(record.getOffenseStreetNum());
	    updateOffense.setOffenseStreetName(record.getOffenseStreetName());
	    updateOffense.setOffenseAptNum(record.getOffenseAptNum());
	    updateOffense.setOffenseCity(record.getOffenseCity());
	    updateOffense.setOffenseState(record.getOffenseState());
	    updateOffense.setOffenseZip(record.getOffenseZip());
	    updateOffense.setWeaponType(record.getWeaponType());
	    updateOffense.setCjisNum(record.getCjisNum());
	    updateOffense.setArrestDate(record.getArrestDate());
	    updateOffense.setArrestTime(record.getArrestTime());
	    updateOffense.setSequenceNum(record.getSequenceNum());	    
	    if (StringUtil.isEmpty(record.getChargeSequenceNum()))
		updateOffense.setChargeSequenceNum(null); 
	    else
		updateOffense.setChargeSequenceNum(record.getChargeSequenceNum());
	    updateOffense.setLcUser(record.getLcUser());
	    updateOffense.setLcDate(record.getLcDate());
	    updateOffense.setLcTime(record.getLcTime());
	    
	    updateOffense.setOnCampOffense(record.getOnCampOffense());
	    updateOffense.setOnCampDistrict(record.getOnCampDistrict());
	    updateOffense.setOnCampSchool(record.getOnCampSchool());

	    CompositeResponse compResp = MessageUtil.postRequest(updateOffense);
	    Object errResp = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
	    ////////

	    if (errResp == null)
	    {
		   log.info("UPDATE JUVENILE OFFENSE  REFERRAL: " + SecurityUIHelper.getLogonId());
		   regform.setMsg("");
		   actionFwd = "success";
	    }

	    else
	    {

		regform.setMsg("");
		actionFwd = "error";
	    }
	}

	return mapping.findForward(actionFwd);

    }

    /*private static JJSReferral getJuvenileReferralDetails(GetJJSReferralEvent refEvent)
    {
	JJSReferral referralResp = null;
	//List<JJSReferralResponseEvent> referralResps = MessageUtil.postRequestListFilter(refEvent, JJSReferralResponseEvent.class);
	Iterator<JJSReferral> referralRespItr = JJSReferral.findAll(refEvent);
	if (referralRespItr.hasNext())
	{
	    referralResp = referralRespItr.next();
	}
	return referralResp;
    }*/

    public ActionForward validateOffenseCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	//JJSOffenseResponseEvent record = retrieveRecord(form,form.getSequenceNum());
	//JJSOffenseResponseEvent record = retrieveRecord(form);
	JJSOffenseResponseEvent record = form.getReferralOffense();
	String severityLevel = "";

	JuvenileOffenseCodeResponseEvent jocEvent = JuvenileDistrictCourtHelper.validateOffenseCd(record.getOffenseCode());
	if (jocEvent != null)
	{
	    severityLevel = jocEvent.getSeverity();
	    record.setOffenseSeverity(severityLevel);
	    String lit = jocEvent.getShortDescription() + " (" + jocEvent.getCategory() + ")";
	    //record.setAllegationDesc(lit);
	    ActionMessages messageHolder = new ActionMessages();
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.offenseEnteredValid"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	}
	else
	{
	    form.setCursorPosition("Allegation");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Offense Code. Please Correct and Retry"));
	    saveErrors(aRequest, errors);
	}
	return aMapping.findForward("error");
    }

    
    
    public ActionForward returnSelect(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	ProdSupportForm form = (ProdSupportForm) aForm;
	//JJSOffenseResponseEvent record = retrieveRecord(form);
	JJSOffenseResponseEvent record = form.getReferralOffense();
	String offCode = "";
	String severityLevel = "";
	

	form.setValidateMsg("");
	List<JuvenileCasefileOffenseCodeResponseEvent> wrkList = new ArrayList<JuvenileCasefileOffenseCodeResponseEvent>(form.getOffenseResultList());
	String selVal = aRequest.getParameter("OffCode");
	for (int x = 0; x < wrkList.size(); x++)
	{
	    JuvenileCasefileOffenseCodeResponseEvent jcoEvent = (JuvenileCasefileOffenseCodeResponseEvent) wrkList.get(x);
	    if (selVal.equals(jcoEvent.getAlphaCode()))
	    {
		offCode = jcoEvent.getAlphaCode();
		severityLevel = jcoEvent.getSeverity();		
		break;
	    }
	}

	record.setOffenseCode(offCode);
	record.setOffenseSeverity(severityLevel);
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	selVal = null;
	wrkList = null;
	//form.setAttorneyDataList(null);
	return aMapping.findForward("error");
    }

    
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	//ProdSupportForm form = (ProdSupportForm) aForm;
	return aMapping.findForward("error");
    }
    
    private JJSOffenseResponseEvent retrieveRecord(ProdSupportForm regform)
    {

	ArrayList referralOffenses = regform.getReferralOffenses();
	JJSOffenseResponseEvent record = null;

	Iterator iter = referralOffenses.iterator();
	if (iter.hasNext())
	{
	    record = (JJSOffenseResponseEvent) iter.next();
	}
	
	return record;
    }
    
    
    private String getRefferalDateByJuvAndRefNo(String JuvNumber, String ReferralNumber){
	
	String refDate = null;
	
	GetProductionSupportJuvenileReferralsEvent getJuvenileRerralsEvent = (GetProductionSupportJuvenileReferralsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEREFERRALS);
	getJuvenileRerralsEvent.setJuvenileId(JuvNumber);
	getJuvenileRerralsEvent.setReferralNum(ReferralNumber);
	CompositeResponse juvenileReferralsResp = MessageUtil.postRequest(getJuvenileRerralsEvent);
	Collection<ProductionSupportJuvenileReferralResponseEvent> juvReffDetails = MessageUtil.compositeToCollection(juvenileReferralsResp, ProductionSupportJuvenileReferralResponseEvent.class);
	Iterator refIter = juvReffDetails.iterator();
	    if (juvReffDetails != null)
	    {
		ProductionSupportJuvenileReferralResponseEvent reff = new ProductionSupportJuvenileReferralResponseEvent();
		if (refIter.hasNext())
		{
		    reff = (ProductionSupportJuvenileReferralResponseEvent) refIter.next();
		    refDate = reff.getReferralDate();
		}
	    }
	    
	return refDate;
	
    }

}

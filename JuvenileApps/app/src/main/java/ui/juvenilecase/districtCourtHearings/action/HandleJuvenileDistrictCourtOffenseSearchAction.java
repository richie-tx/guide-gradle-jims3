//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\referral\\action\\HandleJuvenileCasefileFeeSelectionAction.java

package ui.juvenilecase.districtCourtHearings.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;

/**
 * 
 * @author sthyagarajan
 *
 */
public class HandleJuvenileDistrictCourtOffenseSearchAction extends JIMSBaseAction
{
	 /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "addToList");
		keyMap.put("button.findOffenses", "findOffenses");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.select", "returnSelect");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}	
   
   /**
    * @roseuid 467FB5C80014
    */
   public HandleJuvenileDistrictCourtOffenseSearchAction() 
   {
    
   }
   
   /**
    * findOffenses
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return
    */
   public ActionForward findOffenses(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   CourtHearingForm form = (CourtHearingForm)aForm;
	   form.setSelectedValue(UIConstants.EMPTY_STRING);
	   form.setErrMessage(UIConstants.EMPTY_STRING);
	   List temp1 = new ArrayList();
	   List temp2 = new ArrayList();
	   List temp3 = new ArrayList();
	   List temp4 = new ArrayList();
	   List temp5 = new ArrayList();
	   List finaltemp = new ArrayList();
	   List codeTableList = (List) form.getCodetableDataList();
	   String alphaCode = form.getAlphaCodeId();
	   String shortDesc = form.getShortDesc();
	   String dpsCode = form.getDpsCodeId();
	   String category = form.getCategoryId();  // Severity Level input value
	   String ncicCode = form.getNcicCode();
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
	// narrow results by DPS Code
	   if ("".equalsIgnoreCase(ncicCode) ){
		   temp5 = temp4;
	   } else {	   
		   for (int x=0; x<temp4.size(); x++){
			   JuvenileCasefileOffenseCodeResponseEvent ocEvent  = (JuvenileCasefileOffenseCodeResponseEvent) temp4.get(x);
			   if (ocEvent.getNcicCode()!=null)
			   {
        			   if (ocEvent.getNcicCode().indexOf(ncicCode) == 0){
        				   temp5.add(ocEvent);
        			   }
			   }
		   }
	   }
	   
	   
	   if (temp5.size() == 0)
	   {
		   form.setErrMessage("No records found for supplied search criteria");
	   }
	   //task 174995
	   else
	   {
	       for (int x=0; x<temp5.size(); x++)
		   {
        		   JuvenileCasefileOffenseCodeResponseEvent ocEvent  = (JuvenileCasefileOffenseCodeResponseEvent) temp5.get(x);
        		      if (!"Y".equalsIgnoreCase( ocEvent.getDiscontCode()) && !"Y".equalsIgnoreCase( ocEvent.getInactiveInd() ) )
                		   {
                		   	finaltemp.add(ocEvent);
                		   } 
                		  
		   }
	   }
	   if ( finaltemp.size() > 0 ) {
	       form.setOffenseResultList(finaltemp);
	   } else {
	       form.setOffenseResultList(null);
	       form.setErrMessage("Discontinued or Invalid Offense Code, Please Correct and Retry.");
	   }
	   temp3 = null;
	   temp4 = null;
	   temp5 = null;
	   category = null;

	   return aMapping.findForward("success"); 
   }
   
   /**
    * 
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return
    */
   public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   CourtHearingForm form = (CourtHearingForm)aForm;
	   form.setAlphaCodeId(UIConstants.EMPTY_STRING);
	   form.setShortDesc(UIConstants.EMPTY_STRING);
	   form.setDpsCodeId(UIConstants.EMPTY_STRING);
	   form.setCategoryId(UIConstants.EMPTY_STRING);
	   form.setOffenseResultList(new ArrayList());
	   form.setErrMessage(UIConstants.EMPTY_STRING);
	   form.setPetitionAllegationSev(UIConstants.EMPTY_STRING);
	   form.setNcicCode(UIConstants.EMPTY_STRING);
	   return aMapping.findForward("success"); 
   }
   
   /**
    * 
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return
    */
    public ActionForward returnSelect(ActionMapping aMapping, ActionForm aForm,HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setValidateMsg("");
	
	List<JuvenileCasefileOffenseCodeResponseEvent> wrkList = null;
	
	if (form.getOffenseResultList() != null)
	    wrkList = new ArrayList<JuvenileCasefileOffenseCodeResponseEvent>(form.getOffenseResultList());

	String selVal = form.getSelectedValue();
	for (int x = 0; x < wrkList.size(); x++)
	{
	    JuvenileCasefileOffenseCodeResponseEvent jcoEvent = wrkList.get(x);
	    if (selVal.equals(jcoEvent.getAlphaCode()))
	    {
		form.setPetitionAllegation(jcoEvent.getAlphaCode());
		form.setPetitionAllegationDesc(jcoEvent.getShortDescription());
		form.setPetitionAllegationSev(jcoEvent.getSeverity());
		break;
	    }
	}
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	selVal = null;
	wrkList = null;
	form.setOffenseResultList(null);
	String forwardStr = "successInitialSetting";
	if (form.getAction().equals("petitionUpdate"))
	{
	    forwardStr = "successPetitionUpdate";
	} else
	{
	    form.setAction("validateOffenseCd");
	}
	return aMapping.findForward(forwardStr);

    }
   
   /**
    * back
    */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm,HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	String forwardStr = UIConstants.BACK;
	if (form.getAction().equals("petitionUpdate"))
	{
	    forwardStr = "backPetitionUpdate";
	}
	return aMapping.findForward(forwardStr);
    }
   
   /**
    * cancel
    */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	String forwardStr = UIConstants.CANCEL;
	if (form.getAction().equals("petitionUpdate"))
	{
	    forwardStr = "cancelPetitionUpdate";
	}
	return aMapping.findForward(forwardStr);
    }
}

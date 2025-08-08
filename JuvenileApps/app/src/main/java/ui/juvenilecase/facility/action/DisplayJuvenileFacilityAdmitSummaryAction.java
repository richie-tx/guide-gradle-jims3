package ui.juvenilecase.facility.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.codetable.criminal.reply.JuvenileAdmitReasonsResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilecase.reply.TraitTypeResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.codetable.SimpleCodeTableHelper;
import pd.contact.officer.PDOfficerProfileHelper;

import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.facility.form.AdmitReleaseForm;
import ui.juvenilecase.form.TraitsForm;


public class DisplayJuvenileFacilityAdmitSummaryAction extends LookupDispatchAction{
	
	
	
	 // ------------------------------------------------------------------------
    // --- constructor ---
    // ------------------------------------------------------------------------

    /**
     * @roseuid 42A898CE034B
     */
    public DisplayJuvenileFacilityAdmitSummaryAction()
    {

    }
    
    /**
     * 
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
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	AdmitReleaseForm arForm = (AdmitReleaseForm) aForm;  	
    	
    	//check if Referral Source is valid
    	if(!JuvenileFacilityHelper.validReferralSource(arForm.getReferralSource()))
    	{
    		//String facility = JuvenileFacilityHelper.getFacilityCodeWithJuvTJPCPlacementType(arForm.getFacilities(), arForm.getDetainedFacility());
    		//String[] facilityStr = facility.split("[|]");
    		//arForm.setDetainedFacility(arForm.getDetainedFacility()+"|"+arForm.getPlacementType()+ "|"+selectedFacility[2]);
    		   ActionErrors errors = new ActionErrors();
    	        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Referral Source is invalid"));
    	        saveErrors(aRequest, errors);
    	        return aMapping.findForward(UIConstants.FAILURE);
    	}

	if (arForm.getAdmitAuthority() != null && !arForm.getAdmitAuthority().equals("JUDGE") && 
	    arForm.getAdmitAuthority() != "")	
	{
	    OfficerProfileResponseEvent officerProfile = PDOfficerProfileHelper.getOfficerProfileByLogonId(arForm.getAdmitAuthority());
	    if (officerProfile == null)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Authorized By Must Be JUDGE OR Valid OFFICER USER ID"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);

	    }
	
	}
    	  //SA reason
        String[] selectedSAReasons=arForm.getSelectedSAReasons();
       /* if(selectedSAReasons!=null && selectedSAReasons.length!=0)
        {
        	arForm.setSaReasonStr(SimpleCodeTableHelper.getDescsFromSelCodes(PDCodeTableConstants.SPECIAL_ATTENTION_REASON,selectedSAReasons));
        }*/
        if(selectedSAReasons!=null){
	        ArrayList selectedSAReasonsList = new ArrayList();
	        for(int i=0;i<selectedSAReasons.length;i++)
	        	selectedSAReasonsList.add(selectedSAReasons[i]);
	        arForm.setSaReasonStr(selectedSAReasonsList);
        }
      //set Detention Facility description
    	String[] selectedFacility = arForm.getDetainedFacility().split("[|]");
    	Iterator dfIter = arForm.getFacilities().iterator();
    	while(dfIter.hasNext())
    	{
    		JuvenileFacilityResponseEvent facResp = (JuvenileFacilityResponseEvent)dfIter.next();
    		if(facResp.getCode().equalsIgnoreCase(selectedFacility[0]))
    		{
    			arForm.setDetainedFacilityStr(facResp.getDescription());
    			arForm.setDetainedFacility(selectedFacility[0]);
    			arForm.setLocationOneLabel(facResp.getLocationOneLabel());
    			arForm.setLocationTwoLabel(facResp.getLocationTwoLabel());
    			arForm.setLocationThreeLabel(facResp.getLocationThreeLabel());
    			arForm.setPlacementType(facResp.getJuvTJPCPlacementType());
    		}
    	}
    	//set secure status
    	//Removed for Bug #43866
    	/*if(selectedFacility[2] !=null && !selectedFacility[2].equalsIgnoreCase(""))
    	{
    		if(selectedFacility[2].equalsIgnoreCase("S") || selectedFacility[2].equalsIgnoreCase("B"))
    			arForm.setSecureStatus("S");
    		else
    			arForm.setSecureStatus("N");
    	}*/
      
    	
    	//set the entry date for the trait
    	arForm.getDetentionTrait().setEntryDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
    	//set the description for Admit Reason Code
    	Iterator arIter = arForm.getAdmitReasons().iterator();    	
    	while(arIter.hasNext())
    	{
    		JuvenileAdmitReasonsResponseEvent resp = (JuvenileAdmitReasonsResponseEvent)arIter.next();
    		if(resp.getCodeWithDetType().equalsIgnoreCase(arForm.getReasonCode()))
    		{
    			arForm.setAdmitReasonStr(resp.getDescription());
    			arForm.setAdmitReasonDetentionType(resp.getDetentionType());
    			arForm.setReasonCode(resp.getCode());
    		}
    	}
    	arForm.setAdmitDate(DateUtil.stringToDate(arForm.getAdmitDateStr(), DateUtil.DATE_FMT_1));
    	
    	
    	
    //arForm.setSaReasonStr(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SPECIAL_ATTENTION_REASON, arForm.getSaReason()));
    	arForm.setAdmitTime(JuvenileFacilityHelper.getDateWithColon(arForm.getAdmitTime()));
    	arForm.setHideFacilityTraitsLink(true);
    	
    	//US 49902 - get vendor info from JJS_COD_Facility and store in JJS_Detention - VendorLocation
    	if(arForm.getDetainedFacility() != null && !"".equals(arForm.getDetainedFacility())){
    	    
    	    JuvenileFacilityResponseEvent fac = JuvenileFacilityHelper.getFacilityByCode(arForm.getDetainedFacility());
    	    
    	    if(fac.getVendor() != null && !"".equals(fac.getVendor())){
    		 arForm.setVendorLocation(fac.getVendor());
    	    }
    	   
    	}
    	
    	arForm.setAction(UIConstants.SUMMARY);
    	return( aMapping.findForward(UIConstants.SUCCESS) );
    }

    
    
    
  

	  public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse)
	    {
	        return aMapping.findForward(UIConstants.CANCEL);
	    }

	    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse)
	    {
	    	AdmitReleaseForm arForm = (AdmitReleaseForm)aForm;
	    	arForm.setCurrentReferral(arForm.getCurrentReferral()+"|"+arForm.getCurrentSupervisionNum()+"|"+arForm.getCurrentSeveritySubType());
	        return aMapping.findForward(UIConstants.BACK);
	    }
        
    /**
     * 
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();        
        keyMap.put("button.back", "back");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.next", "next");      
     
        return keyMap;
    }
   
}

package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenileStatusEvent;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.productionsupport.GetProductionSupportJuvenileReferralsEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileReferralResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenile.JuvenileBuilder;
import pd.juvenile.JuvenileMasterStatus;
import pd.juvenile.JuvenileStatus;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSSVIntakeHistory;

import ui.common.CodeHelper;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.form.ProdSupportForm;

public class UpdateIntakeHistoryRecordQueryAction extends Action
{
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
	ProdSupportForm regform = (ProdSupportForm) aForm;
	String forward = "success";
	
        String deleteIntakHistory= aRequest.getParameter("deleteIntakHistory");
	
	if (deleteIntakHistory != null && deleteIntakHistory.equalsIgnoreCase("true"))
	{
	    regform.setIsIntakeHistoryDelete(true);   
	}
	else
	{
	    regform.setIsIntakeHistoryDelete(false);
	}
	
	
	String clrChk = aRequest.getParameter("clr");
	
	if (clrChk != null && clrChk.equalsIgnoreCase("Y"))
	{
	    regform.clearAllResults();
	    regform.setMsg("");
	    return aMapping.findForward("error");
	}
	
	if (clrChk != null && clrChk.equalsIgnoreCase("R"))
	{
	    regform.clearAllResults();
	    regform.setMsg("");
	    String juvenileNumber  = regform.getOriginalJuvenileNumber();
	    if ( juvenileNumber != null
		    && juvenileNumber.length() > 0){
		regform.setJuvenileId( juvenileNumber );
	    }
	    String referralNumber  = regform.getOriginalReferralNumber();
	    if ( referralNumber != null
		    && referralNumber.length() > 0){
		regform.setReferralId(referralNumber);
	    }
	    return aMapping.findForward("error");
	}
	
	JJSJuvenileResponseEvent					juvenileDetail		= null;
	ArrayList<ProductionSupportJuvenileReferralResponseEvent> 	referralDetails 	= null;
	ArrayList<JJSOffense>		 				offenses	 	= null;
	
	String juvenileNumber = regform.getJuvenileId();
	regform.setOriginalJuvenileNumber(juvenileNumber);
	String referralNumber = regform.getReferralId();
	regform.setOriginalReferralNumber(referralNumber);
	String masterStatus   = "";
	List<JJSSVIntakeHistory> intakeHistoryRecords = new ArrayList<JJSSVIntakeHistory>();
	
	if ( (juvenileNumber!= null && juvenileNumber.length() > 0)
		|| (referralNumber != null && referralNumber.length() > 0 )){
	   
	    juvenileDetail 	 = retrieveJuvenile( juvenileNumber );
	    if (juvenileDetail != null ){
		//masterStatus	 = retrieveMasterStatus(juvenileNumber);
		masterStatus=JuvenileBuilder.getMasterStatusDesc(juvenileDetail.getStatusId());
		juvenileDetail.setMasterStatus(masterStatus);
		regform.setJuvenileDetail(juvenileDetail);
		referralDetails = retrieveReferrral(juvenileNumber, referralNumber);
		if ( referralDetails != null && referralDetails.size() > 0 ){
		    offenses = retrieveOffenseForReferral(juvenileNumber, referralNumber);
		    regform.setReferralDetail(referralDetails.get(0));
		    System.out.println("intake date" + referralDetails.get(0).getIntakeDate() );
		    System.out.println("intake decision" + referralDetails.get(0).getIntakeDecision() );
		    if ( offenses != null 
			    && offenses.size() > 0 ){
			 if (offenses.size() > 1 ){
				Collections.sort(offenses, compareBySequence);
				Collections.reverse(offenses);
			    }
			    regform.setCurrentOffense(  offenses.get(0).getOffenseCode().getOffenseCode());
		    } 
		    
		    for (int i = 0; i < referralDetails.size(); i++) {
			if (referralDetails.get(i).getCloseDate() != null ){
			    referralDetails.get(i).setCloseDate("CLOSED");
			} else {
			    referralDetails.get(i).setCloseDate("ACTIVE");
			}
		    }
		    regform.setReferralDetails(referralDetails);
		    intakeHistoryRecords = JuvenileReferralHelper
    				.getIntakeHisoryForJuvNumRefNum(juvenileNumber, referralNumber);
		    if (intakeHistoryRecords != null 
			    && intakeHistoryRecords.size() > 0 ){			
			regform.setIntakeHistoryRecords(intakeHistoryRecords);
		    } else {
			regform.setMsg("No Intake History records found for Juvenile Number " 
					+ juvenileNumber 
					+ " and Referral Number " 
					+ referralNumber + ". Please verify entry.");
			return aMapping.findForward("error");
		    }
		} else {
		    regform.setMsg("No Referral detail records found for Juvenile Number " 
				+ juvenileNumber 
				+ " and Referral Number " 
				+ referralNumber + ". Please verify entry." );
		    return aMapping.findForward("error");
		}
    	    	
	    } else {
		regform.setMsg("No Juvenile record found for Juvenile Number " + juvenileNumber);
		return aMapping.findForward("error");
	    }
	} else {
	    regform.setMsg("Please enter a valid Juvenile Number and a valid Referral Number.");
	    return aMapping.findForward("error");
	}
	
	
	
	return aMapping.findForward(forward);
	
    }

    private JJSJuvenileResponseEvent retrieveJuvenile (String juvenileNum) throws Exception {
	
	GetJJSJuvenileEvent getJJSJuvenileEvent = (GetJJSJuvenileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILE);
	getJJSJuvenileEvent .setJuvenileNum(juvenileNum);

	JJSJuvenileResponseEvent juvenileResp = (JJSJuvenileResponseEvent) MessageUtil.postRequest(getJJSJuvenileEvent, JJSJuvenileResponseEvent.class);
	
	return juvenileResp;
    }

    private ArrayList<ProductionSupportJuvenileReferralResponseEvent> retrieveReferrral(String juvenileNum,
											String referralNum
											) throws Exception {
	ArrayList<ProductionSupportJuvenileReferralResponseEvent> juvReferrals = null;
        
	// Get and set Associated JuvProgRefs
        GetProductionSupportJuvenileReferralsEvent getJuvenileRerralsEvent = (GetProductionSupportJuvenileReferralsEvent) 
        	    									EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEREFERRALS);
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
    
    private Comparator<JJSOffense>compareBySequence = new Comparator<JJSOffense>(){
  	@Override
  	public int compare(JJSOffense o1, JJSOffense o2){
  	    return o1.getSequenceNum().compareTo(o1.getSequenceNum());
  	}
      };
      
     //master status is finding in the command of juvenile itself
    /* private String retrieveMasterStatus(String juvenileNum) throws Exception{
	 String masterStatus = "";
	 JuvenileStatus status = JuvenileStatus.find(juvenileNum );
	 
	 if ( status != null ) {
	     
	     masterStatus = CodeHelper.getCodeDescription(PDCodeTableConstants.JUVENILE_PROFILE_STATUS, status.getStatusId());
	 }
	 return masterStatus;
	 String masterStatus = "";
		JuvenileMasterStatus status = JuvenileMasterStatus.find(juvenileNum);
		if (status != null)
		{
		    masterStatus = JuvenileBuilder.getMasterStatusDesc(status.getStatusId()); 
		}

		return masterStatus;
     }*/
     
    

}

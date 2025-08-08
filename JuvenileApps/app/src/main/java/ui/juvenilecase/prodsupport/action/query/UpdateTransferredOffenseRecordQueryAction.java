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
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
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

public class UpdateTransferredOffenseRecordQueryAction extends Action
{
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
	ProdSupportForm regform = (ProdSupportForm) aForm;
	String forward = "success";
	
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
	    
		return aMapping.findForward("error");
	    
	}
	
	JJSJuvenileResponseEvent					juvenileDetail		= null;
	
	
	String juvenileNumber = regform.getJuvenileId();
	regform.setOriginalJuvenileNumber(juvenileNumber);
	
	String masterStatus   = "";
	List<JuvenileCasefileTransferredOffenseResponseEvent> tranOffRecords = new ArrayList<JuvenileCasefileTransferredOffenseResponseEvent>();
	
	if ( (juvenileNumber!= null && juvenileNumber.length() > 0)){
	   
	    juvenileDetail 	= retrieveJuvenile( juvenileNumber );
	    if (juvenileDetail != null ){
		//masterStatus	 = retrieveMasterStatus(juvenileNumber);
		masterStatus=JuvenileBuilder.getMasterStatusDesc(juvenileDetail.getStatusId());
		juvenileDetail.setMasterStatus(masterStatus);
		regform.setJuvenileDetail(juvenileDetail);
		
		    tranOffRecords = JuvenileReferralHelper.getTransOffenseReferralForJuvNum(juvenileNumber);
		    if (tranOffRecords != null 
			    && tranOffRecords.size() > 0 ){			
			regform.setTransOffenseReferrals((ArrayList)tranOffRecords);
		    } else {
			regform.setMsg("No Transferred Offenses records found for Juvenile Number " 
					+ juvenileNumber + ". Please verify entry.");
			return aMapping.findForward("error");
		    }
		} else {
		    regform.setMsg("No Transferred Offenses records found for Juvenile Number " 
				+ juvenileNumber 
				+ ". Please verify entry." );
		    return aMapping.findForward("error");
		}
    	    	
	    } else {
		regform.setMsg("No Juvenile record found for Juvenile Number " + juvenileNumber);
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

    
     
    

}

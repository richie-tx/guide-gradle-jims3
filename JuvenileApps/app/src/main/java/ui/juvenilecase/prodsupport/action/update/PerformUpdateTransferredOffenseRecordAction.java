package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.productionsupport.UpdateProductionSupportIntakeHistoryEvent;
import messaging.referral.SaveTransferredOffenseReferralsEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProductionSupportControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.referral.JJSSVIntakeHistory;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.contact.user.helper.UIUserFormHelper;
import ui.juvenilecase.form.ProdSupportForm;

public class PerformUpdateTransferredOffenseRecordAction extends Action
{
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
	ProdSupportForm regform = (ProdSupportForm) aForm;
	String forward = "success";
	
	String clrChk = aRequest.getParameter("clr");
	List<JuvenileCasefileTransferredOffenseResponseEvent> tranOffRecords = regform.getTransOffenseReferrals();
	String transOffenseId	  = regform.getTransOffenseId();
	JuvenileCasefileTransferredOffenseResponseEvent updatedTransferredOffense =  regform.getTransferredOffenseRecord();
	
	
	if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
	    regform.setMsg("");
	    for (JuvenileCasefileTransferredOffenseResponseEvent tranOffRecord : tranOffRecords){
		if (tranOffRecord.getTransOffenseReferralId().equals(transOffenseId)){
		    regform.setTransferredOffenseRecord(tranOffRecord);
		    regform.setOriginalJuvenileNumber(tranOffRecord.getJuvenileNum());
		    regform.setOriginalReferralNumber(tranOffRecord.getReferralNum());
		    regform.setOriginalCountyId(tranOffRecord.getCountyId());
		    regform.setOriginalOffenseCode(tranOffRecord.getOffenseCode());
		    regform.setOriginalCategory(tranOffRecord.getCategory());
		    regform.setOriginalDpsCode(tranOffRecord.getDpsCode());
		    regform.setOriginalOffenseDate(tranOffRecord.getOffenseDate());
		    regform.setOriginalAdjudicationDate(tranOffRecord.getAdjudicationDate());
		    regform.setOriginalPersonId(tranOffRecord.getPersonId());
		}
	    }
	    return aMapping.findForward("error");
	}
	
	JJSJuvenile juvenile = JJSJuvenile.find(updatedTransferredOffense.getJuvenileNum());
	if ( juvenile != null ){
	   
	    updatedTransferredOffense.setOffenseDate(DateUtil.stringToDate(updatedTransferredOffense.getOffenseDateStr(), DateUtil.DATE_FMT_1 ));
	    updatedTransferredOffense.setAdjudicationDate(DateUtil.stringToDate(updatedTransferredOffense.getAdjudicationDateStr(), DateUtil.DATE_FMT_1 ));
    	
	    
	    SaveTransferredOffenseReferralsEvent stoEvent = new SaveTransferredOffenseReferralsEvent();	   
	    stoEvent.setTransOffenseReferralId(updatedTransferredOffense.getTransOffenseReferralId());
	    stoEvent.setJuvenileNum(updatedTransferredOffense.getJuvenileNum());
	    stoEvent.setReferralNum(updatedTransferredOffense.getReferralNum());
	    stoEvent.setCountyId(updatedTransferredOffense.getCountyId());	    
	    stoEvent.setPersonId(updatedTransferredOffense.getPersonId()); // added for user story 11081.
	    stoEvent.setOffenseId(updatedTransferredOffense.getOffenseCode());	    
	    stoEvent.setOffenseCategory(updatedTransferredOffense.getCategory());
	    stoEvent.setDpsCode(updatedTransferredOffense.getDpsCode());
	    stoEvent.setOffenseDate(updatedTransferredOffense.getOffenseDate());
	    stoEvent.setAdjudicationDate(updatedTransferredOffense.getAdjudicationDate());
	    MessageUtil.postRequest(stoEvent);
	       	
	    regform.setMsg("Transferred Offense Referral Table values have been updated");
	    
	    return aMapping.findForward(forward);
	} else {
	    System.out.println("Juvenile is not valid");
	    regform.setMsg("Juvenile number is not valid. Please input a valid juvenile number.");
	    return aMapping.findForward("error");
	}
    }
    
    
   
}

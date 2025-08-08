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
import messaging.productionsupport.UpdateProductionSupportIntakeHistoryEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.referral.JJSSVIntakeHistory;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.contact.user.helper.UIUserFormHelper;
import ui.juvenilecase.form.ProdSupportForm;

public class PerformUpdateIntakeHistoryRecordAction extends Action
{
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
	ProdSupportForm regform = (ProdSupportForm) aForm;
	String forward = "success";
	
	String clrChk = aRequest.getParameter("clr");
	List<JJSSVIntakeHistory> intakeHistoryRecords = regform.getIntakeHistoryRecords();
	String intakeHistoryId	  = regform.getIntakeHistoryId();
	JJSSVIntakeHistory updatedIntakeHistory =  regform.getIntakeHistoryRecord();
	
	
	if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
	    regform.setMsg("");
	    for (JJSSVIntakeHistory intakeHistory : intakeHistoryRecords){
		if (intakeHistory.getOID().equals(intakeHistoryId)){
		    regform.setIntakeHistoryRecord(intakeHistory);
		    regform.setOriginalAssmntType(intakeHistory.getAssignmentType());
		    regform.setOriginalSupervisionType(intakeHistory.getSupervisionTypeId());
		    regform.setOriginalAssignmenDate(intakeHistory.getAssignmentDate());
		    regform.setOriginalIntakeDate(intakeHistory.getIntakeDate());
		    regform.setOriginalIntakeDecision(intakeHistory.getIntakeDecisionId());
		    regform.setOriginalIntakeJPO(intakeHistory.getJpoId());
		}
	    }
	    
	    regform.setReferralAssignmentCodes(ComplexCodeTableHelper.getRefAssmntTypeCodes());
	    regform.setSupervisionTypeCodes(CodeHelper.getSupervisionTypes());
	    regform.setOutcomeCodes((ArrayList)JuvenileCaseHelper.getAllReferralDecisions());
	    return aMapping.findForward("error");
	}
	
	JJSJuvenile juvenile = JJSJuvenile.find(updatedIntakeHistory.getJuvenileNum());
	if ( juvenile != null ){
	    updatedIntakeHistory.setAssignmentDate(DateUtil.stringToDate(regform.getAssignmentDate(), DateUtil.DATE_FMT_1 ));
	    updatedIntakeHistory.setIntakeDate(DateUtil.stringToDate(regform.getIntakeDate(), DateUtil.DATE_FMT_1 ));
    	
	    UpdateProductionSupportIntakeHistoryEvent updateIntakeHistoryEvent  = (UpdateProductionSupportIntakeHistoryEvent
    											)EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTINTAKEHISTORY);
	    updateIntakeHistoryEvent.setIntakeHistoryId(updatedIntakeHistory.getOID());
	    updateIntakeHistoryEvent.setAssignmentType(updatedIntakeHistory.getAssignmentType());
	    updateIntakeHistoryEvent.setSupervisionTypeCode(updatedIntakeHistory.getSupervisionTypeId());
	    updateIntakeHistoryEvent.setAssignmentDate(updatedIntakeHistory.getAssignmentDate());
	    updateIntakeHistoryEvent.setIntakeDate(updatedIntakeHistory.getIntakeDate());
	    updateIntakeHistoryEvent.setIntakeDecision(updatedIntakeHistory.getIntakeDecisionId());
	    updateIntakeHistoryEvent.setIntakeJPO(updatedIntakeHistory.getJpoId());
	    if ( updatedIntakeHistory.getJuvenileNum() != null
		    && updatedIntakeHistory.getJuvenileNum().length() > 0 ) {
		updateIntakeHistoryEvent.setJuvenileNumber(updatedIntakeHistory.getJuvenileNum());
	    }
	    if ( updatedIntakeHistory.getReferralNumber() != null
		    && updatedIntakeHistory.getReferralNumber().length() > 0 ) {
		updateIntakeHistoryEvent.setReferralNumber(updatedIntakeHistory.getReferralNumber());
	    }
	    	
	    MessageUtil.postRequest(updateIntakeHistoryEvent);
	    regform.setMsg("Intake History Table values have been updated");
	    JJSSVIntakeHistory intakeHistory = JJSSVIntakeHistory.find(intakeHistoryId);
	    if ( intakeHistory != null) {
		regform.setIntakeHistoryRecord(intakeHistory);
	    }
    	
	    return aMapping.findForward(forward);
	} else {
	    System.out.println("Juvenile is not valid");
	    regform.setMsg("Juvenile number is not valid. Please input a valid juvenile number.");
	    return aMapping.findForward("error");
	}
    }
    
    
   
}

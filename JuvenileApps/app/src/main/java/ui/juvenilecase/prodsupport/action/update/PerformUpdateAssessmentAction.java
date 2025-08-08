package ui.juvenilecase.prodsupport.action.update;

import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.productionsupport.UpdateProductionSupportRiskAnalysisEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.util.DateUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.prodsupport.helpers.Constants;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 */


public class PerformUpdateAssessmentAction extends Action {
	private Logger log = Logger.getLogger("PerformUpdateAssessmentAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;

		String logonid = SecurityUIHelper.getLogonId();  //For readability in the logs.

		boolean status = false;
		
		String riskAnalysisId = regform.getRiskanalysisId();

		if (riskAnalysisId == null || riskAnalysisId.equals("")) {
			regform.setMsg("PerformUpdateAssessmentAction.java - Risk ID was null.");
			return (mapping.findForward("error"));
		}
		
		String newActDate = regform.getNewActDate();
		//String actday = regform.getActday();
		//String actmonth = regform.getActmonth();
		//String actyear = regform.getActyear();
		String supervisionNum = null;
		supervisionNum = regform.getNewcasefileId();
		
		
		
		if ( (newActDate != null  && StringUtils.isNotEmpty(newActDate)) || (supervisionNum != null && !supervisionNum.equals("") )){

		    	UpdateProductionSupportRiskAnalysisEvent updateEvent = new UpdateProductionSupportRiskAnalysisEvent();
		    	if(newActDate != null){
		    	   
				//regform.setNewActDate( newActDate );
				Date newEnteredDate=DateUtil.stringToDate( newActDate, DateUtil.DATE_FMT_1);
				log.info("Updating RiskAnalysisId: " + riskAnalysisId + " To new Date: " + newActDate + " For LogonId: " + logonid);
				// send the update request
				updateEvent.setRiskAnalysisId(riskAnalysisId);				
				updateEvent.setDateEntered(newEnteredDate);
		    	}

			if( !StringUtils.isEmpty( supervisionNum )){
			    updateEvent.setRiskAnalysisId(riskAnalysisId);
			    updateEvent.setMergeToCasefileId( supervisionNum );
			    log.info("Updating RiskAnalysisId: " + riskAnalysisId + " To new Supervision: " + supervisionNum + " For LogonId: " + logonid);
			}
			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
			dispatch.postEvent(updateEvent);

			Constants.writeToLog("Performed a RISK ANALYSIS DATEENTERED change for RISKANALYSIS_ID="
				+ riskAnalysisId+" New Value:"+newActDate, logonid);
			Constants.writeToLog("Performed a RISK ANALYSIS CASEFILE change for RISKANALYSIS_ID="
				+ riskAnalysisId+" New Value:"+supervisionNum, logonid);
			return mapping.findForward("success");
		}else{
			//Don't clobber other error messages
			if (regform.getMsg().equals(""))
				regform.setMsg("Error - The assessment was not updated. PerformUpdateAssessmentAction.java");
			return mapping.findForward("error");
		}
	}
}

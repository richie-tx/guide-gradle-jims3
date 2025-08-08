package ui.juvenilecase.populationReport.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.populationReport.FacilityAdmitReasonPopulationReportBean;
import ui.juvenilecase.populationReport.UIFacilityPopulationHelper;
import ui.juvenilecase.populationReport.form.JuvenilePopulationForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class DisplayAdmittanceReasonPopulationAction extends JIMSBaseAction
{
	/**
	 * @roseuid 42AF409F01B5
	 */
	public DisplayAdmittanceReasonPopulationAction()
	{
	}

	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected void addButtonMapping(Map buttonMap)
	{
		buttonMap.put("button.submit", "submitSearch");
		buttonMap.put("button.print", "print");
		buttonMap.put("button.cancel", "cancel");
		return;
	}
	/*
	 * 
	 */
	public ActionForward submitSearch(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenilePopulationForm jpForm = (JuvenilePopulationForm)aForm;
		UIFacilityPopulationHelper.getCustomFacilityPopulationReport(jpForm); 
		aRequest.getSession().setAttribute("reset", "yes");
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/*
	 * 
	 */
	public ActionForward print(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenilePopulationForm jpForm = (JuvenilePopulationForm)aForm;
		// add code to load data needed for BCO print
		
		FacilityAdmitReasonPopulationReportBean reportingInfo = prepareBean(jpForm, true);
		reportingInfo.setReportName(PDFReport.ADMIT_REASON_REPORT_NAME.getReportName());
		aRequest.getSession().setAttribute("reportInfo", reportingInfo);

		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.ADMIT_REASON_REPORT_NAME);
		return null;
	}
	
	private FacilityAdmitReasonPopulationReportBean prepareBean(JuvenilePopulationForm form, boolean viewReport)
	{
		FacilityAdmitReasonPopulationReportBean bean = new FacilityAdmitReasonPopulationReportBean();
		 StringBuilder selectedReasonCodes = new StringBuilder();
		
		bean.setFacilityName(UIFacilityPopulationHelper.getCodeDescription(form.getFacilities(), form.getFacilityId()));
		//bean.setReasonCode(form.getAdmitReasonId());
		if ( form.getAdmitReasonIds() != null 
			&& form.getAdmitReasonIds().length > 0 ){
		    for (int i =0 ; i < form.getAdmitReasonIds().length; i++){
			if ( i > 0 ){
			    selectedReasonCodes.append(",");
			}
			selectedReasonCodes.append( form.getAdmitReasonIds()[i] );
			
		    }
		    bean.setSelectedReasonCodes(selectedReasonCodes.toString());
		}
		
		bean.setSecureStatus(form.getSecuredFacility());
		bean.setJuvenileAdmits(form.getJuvenileAdmits());
		return bean;
	}
	/*
	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenilePopulationForm jpForm = (JuvenilePopulationForm)aForm;		
		jpForm.setSearchTypeId("");
		jpForm.setFacilityId("");
		jpForm.setSecuredFacility("");
		jpForm.setAdmitReasonId("");
		return aMapping.findForward("cancel");
	}

}

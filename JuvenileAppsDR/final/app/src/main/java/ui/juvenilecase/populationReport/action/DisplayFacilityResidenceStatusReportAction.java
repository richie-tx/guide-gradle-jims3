package ui.juvenilecase.populationReport.action;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.populationReport.FacilityResidentStatusReportBean;
import ui.juvenilecase.populationReport.UIFacilityPopulationHelper;
import ui.juvenilecase.populationReport.form.JuvenilePopulationForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class DisplayFacilityResidenceStatusReportAction extends JIMSBaseAction
{
	/**
	 * @roseuid 42AF409F01B5
	 */
	public DisplayFacilityResidenceStatusReportAction()
	{
	}

	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected void addButtonMapping(Map buttonMap)
	{
		buttonMap.put("button.submit", "findFacilityInfo");
		buttonMap.put("button.print", "print");
		buttonMap.put("button.cancel", "cancel");
		return;
	}
	/*
	 * 
	 */
	public ActionForward findFacilityInfo(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenilePopulationForm jpForm = (JuvenilePopulationForm)aForm;
		UIFacilityPopulationHelper.getFacilityStatusInfo(jpForm);		
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
		FacilityResidentStatusReportBean reportingInfo = prepareBean(jpForm);
		reportingInfo.setReportName(PDFReport.RESIDENT_STATUS_REPORT_NAME.getReportName());
		aRequest.getSession().setAttribute("reportInfo", reportingInfo);

		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.RESIDENT_STATUS_REPORT_NAME);
		
		return null;
	}
	
	private FacilityResidentStatusReportBean prepareBean(JuvenilePopulationForm form)
	{
		FacilityResidentStatusReportBean bean = new FacilityResidentStatusReportBean();
		bean.setResidentsList(form.getResidentsStatusList());
		bean.setResidentsWithOtherChanges(form.getResidentWithOtherStatusChanges());
		bean.setFacilityTotal(form.getListTotal());
		bean.setFacilityName(UIFacilityPopulationHelper.getCodeDescription(form.getFacilities(), form.getFacilityId()));
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
		return aMapping.findForward("cancel");
	}


}

package ui.juvenilecase.populationReport.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.populationReport.FacilityPopulationTotalsReportBean;
import ui.juvenilecase.populationReport.UIFacilityPopulationHelper;
import ui.juvenilecase.populationReport.form.JuvenilePopulationForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class DisplayFacilityPopulationReportTotalsAction extends JIMSBaseAction
{
	/**
	 * @roseuid 42AF409F01B5
	 */
	public DisplayFacilityPopulationReportTotalsAction()
	{
	}

	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected void addButtonMapping(Map buttonMap)
	{
		buttonMap.put("button.submit", "findTotals");
		buttonMap.put("button.print", "print");
		buttonMap.put("button.cancel", "cancel");
		return;
	}
	/*
	 * 
	 */
	public ActionForward findTotals(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenilePopulationForm jpForm = (JuvenilePopulationForm)aForm;
		UIFacilityPopulationHelper.getFacilityPopulationTotalsInfo(jpForm);
		// add code to retrieve data based on facility code - jpForm.getFacilityId()
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
		
		FacilityPopulationTotalsReportBean reportingInfo = prepareBean(jpForm);
		reportingInfo.setReportName(PDFReport.POPULATION_TOTALS_REPORT_NAME.getReportName());
		aRequest.getSession().setAttribute("reportInfo", reportingInfo);

		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.POPULATION_TOTALS_REPORT_NAME);
		return null;
	}
	
	private FacilityPopulationTotalsReportBean prepareBean(JuvenilePopulationForm form)
	{
		FacilityPopulationTotalsReportBean bean = new FacilityPopulationTotalsReportBean();
		bean.setTotalSecureMalecount(form.getTotalSecureMalecount());
		bean.setTotalFemaleSecureCount(form.getTotalFemaleSecureCount());
		bean.setTotalNonSecMaleCount(form.getTotalNonSecMaleCount());
		bean.setTotalNonSecFemaleCount(form.getTotalNonSecFemaleCount());
		bean.setTotalDivMaleCount(form.getTotalDivMaleCount());
		bean.setTotalDivFemaleCount(form.getTotalDivFemaleCount());
		bean.setTotalTempReleaseMaleCount(form.getTotalTempReleaseMaleCount());
		bean.setTotalTempReleaseFemaleCount(form.getTotalTempReleaseFemaleCount());
		bean.setTotalSecureInmates(form.getTotalSecureInmates());
		bean.setTotalNonSecInmates(form.getTotalNonSecInmates());
		bean.setTotalDivInmates(form.getTotalDivInmates());
		bean.setTotalTempReleaseInmates(form.getTotalTempReleaseInmates());
		bean.setFacilityPopTots(form.getFacilityPopTots());		
		bean.setFacilityName(UIFacilityPopulationHelper.getCodeDescription(form.getFacilities(), form.getFacilityId()));
		
		bean.setHighHighPercent( form.getHighHighPercent() );
		bean.setHighModeratePercent( form.getHighModeratePercent() );
		bean.setHighLowPercent( form.getHighLowPercent() );
		
		bean.setModerateHighPercent( form.getModerateHighPercent() );
		bean.setModerateModeratePercent( form.getModerateModeratePercent() );
		bean.setModerateLowPercent( form.getModerateLowPercent() );
		
		bean.setLowHighPercent( form.getLowHighPercent() );
		bean.setLowModeratePercent( form.getLowModeratePercent() );
		bean.setLowLowPercent( form.getLowLowPercent() );
		
		bean.setMissingScorePercent( form.getMissingScorePercent() );
		
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

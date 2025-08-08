package ui.juvenilecase.populationReport.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.utilities.DateUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.populationReport.UIFacilityPopulationHelper;
import ui.juvenilecase.populationReport.form.JuvenilePopulationForm;

public class HandlePopulationReportSearchAction extends JIMSBaseAction
{
	/**
	 * @roseuid 42AF409F01B5
	 */
	public HandlePopulationReportSearchAction()
	{
	}

	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected void addButtonMapping(Map buttonMap)
	{
		buttonMap.put("button.submit", "submitSearch");
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
		String forwardStr = "errorSuccess";
		if ("FP".equals(jpForm.getSearchTypeId() ) )
		{
			jpForm.setCurrentTime(DateUtil.getCurrentDate());
			UIFacilityPopulationHelper.getFacilityPopulationTotalsInfo(jpForm);
			forwardStr = "successPopTotals";
		}
		if ("RS".equals(jpForm.getSearchTypeId() ) )
		{
			UIFacilityPopulationHelper.getFacilityStatusInfo(jpForm);
			forwardStr = "successResStatus";
		}
		if ("FC".equals(jpForm.getSearchTypeId() ) )
		{
			jpForm.setCurrentTime(DateUtil.getCurrentDate());
			//jpForm.setCurrentPopulations(UIFacilityPopulationHelper.getFacilityCurrentPopulationInfo(jpForm.getFacilityId() ) );
			UIFacilityPopulationHelper.getFacilityCurrentPopulationReport(jpForm);			
			forwardStr = "successCurrentPop";
		}
		if ("AR".equals(jpForm.getSearchTypeId() ) )
		{
			jpForm.setCurrentTime(DateUtil.getCurrentDate());
			//jpForm.setJuvenileAdmits(UIFacilityPopulationHelper.getFacilityReasonPopulationInfo(jpForm.getFacilityId(), jpForm.isSecuredFacility(), jpForm.getAdmitReasonId()) ); 	
			//UIFacilityPopulationHelper.getFacilityPopulationTotalReport(jpForm);
			
			UIFacilityPopulationHelper.getCustomFacilityPopulationReport(jpForm);
			forwardStr = "successAdmitReasons";
		}
		if ("FO".equals(jpForm.getSearchTypeId() ) )
		{
			jpForm.setCurrentTime(DateUtil.getCurrentDate());
			UIFacilityPopulationHelper.getFacilityCurrentObservation(jpForm);			
			forwardStr = "successCurrentObservation";
		}
		return aMapping.findForward(forwardStr);
	}

	/*
	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenilePopulationForm jpForm = (JuvenilePopulationForm)aForm;
		//jpForm.clear();
		jpForm.setFacilityId("");
		jpForm.setSearchTypeId("");
		return aMapping.findForward("cancel");
	}

}

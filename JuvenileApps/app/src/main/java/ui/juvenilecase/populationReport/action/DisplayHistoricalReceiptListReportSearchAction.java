package ui.juvenilecase.populationReport.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.populationReport.UIFacilityPopulationHelper;
import ui.juvenilecase.populationReport.form.JuvenileFacilityReceiptForm;

/**
 * 
 * @author rcarter
 *
 * 
 */
public class DisplayHistoricalReceiptListReportSearchAction extends Action
{

	/**
	 * @roseuid 4278CA1C002D
	 */
	public DisplayHistoricalReceiptListReportSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4278C7B803C9
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileFacilityReceiptForm receiptsForm = (JuvenileFacilityReceiptForm) aForm;
		receiptsForm.clear();
		
		receiptsForm.setFacilities((List)UIFacilityPopulationHelper.loadFacilites());
		return aMapping.findForward("success"); 
		
//		ActionErrors errors = new ActionErrors();
//		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.noRecords"));
//		saveErrors(aRequest, errors);
//		forward = aMapping.findForward("customFailure");
	}
	
	
}

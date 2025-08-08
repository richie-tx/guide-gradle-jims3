package ui.juvenilecase.populationReport.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetJuvenileActivityTypeCodesEvent;
import messaging.codetable.criminal.reply.JuvenileActivityTypeCodeResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.activities.form.ActivitiesForm;
import ui.juvenilecase.populationReport.UIFacilityPopulationHelper;
import ui.juvenilecase.populationReport.form.JuvenileFacilityReceiptForm;

/**
 * 
 * @author anpillai
 *
 * 
 */
public class DisplayActivityCategorySearchAction extends Action
{

	/**
	 * @roseuid 4278CA1C002D
	 */
	public DisplayActivityCategorySearchAction()
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
	    	ActivitiesForm activitiesForm = (ActivitiesForm) aForm;
	    	//activitiesForm.clear();	 
	    	//receiptsForm.setFacilities((List)UIFacilityPopulationHelper.loadFacilites());// set active locations
	    	
	    	GetJuvenileActivityTypeCodesEvent reqEvent = (GetJuvenileActivityTypeCodesEvent) EventFactory
	                .getInstance(CodeTableControllerServiceNames.GETJUVENILEACTIVITYTYPECODES);

	        JuvenileActivityTypeCodeResponseEvent response = (JuvenileActivityTypeCodeResponseEvent) MessageUtil
	                .postRequest(reqEvent, JuvenileActivityTypeCodeResponseEvent.class);

	        activitiesForm.setActivityCodes(response.getReturnValues());
		return aMapping.findForward("success"); 
		
	}
	
	
}

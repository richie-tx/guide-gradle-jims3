//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenileCasefileSearchAction.java

package ui.juvenilecase.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import naming.PDCodeTableConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.juvenilecase.populationReport.UIFacilityPopulationHelper;
import ui.juvenilecase.populationReport.form.JuvenilePopulationForm;

/**
 * 
 * @author cshimek
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayPopulationReportSearchAction extends Action
{

	/**
	 * @roseuid 4278CA1C002D
	 */
	public DisplayPopulationReportSearchAction()
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
 		JuvenilePopulationForm jpForm = (JuvenilePopulationForm) aForm;
		jpForm.clear();
		
		List<CodeResponseEvent> dropDown = CodeHelper.getCodes(PDCodeTableConstants.FACILITY_POPULATION_REPORT,true);
		jpForm.setSearchTypes( dropDown );
		jpForm.setFacilities((List)UIFacilityPopulationHelper.loadFacilites());		
		jpForm.setAdmitReasons((List)UIFacilityPopulationHelper.loadAdmitReasons());
		return aMapping.findForward("success"); 
	}
	
	/*private static Collection loadAdmitReasons()
	{
		List admitReasonValues = CodeHelper.getCodes(PDCodeTableConstants.DETENTION_REASON, true);
		if(admitReasonValues != null){
			Iterator admitReasonItr = admitReasonValues.iterator();
		    while(admitReasonItr.hasNext())
		    {
		    	CodeResponseEvent codeRespEvent = (CodeResponseEvent)admitReasonItr.next();		    	
		    	String codeDescVal = codeRespEvent.getDescription()+" ("+codeRespEvent.getCode()+")";
		    	codeRespEvent.setDescription(codeDescVal);
		    }			
		}
		return admitReasonValues;
	}*/
	
	


}

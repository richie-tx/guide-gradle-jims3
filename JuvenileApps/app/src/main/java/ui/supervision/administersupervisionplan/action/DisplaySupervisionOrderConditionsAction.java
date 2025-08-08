//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administersupervisionplan\\action\\DisplaySupervisionOrderConditionsAction.java

package ui.supervision.administersupervisionplan.action;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdcalendar.GetCSSpnViewConditionsEvent;
import messaging.cscdcalendar.reply.CSSpnViewConditionsResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administersupervisionplan.form.SupervisionPlanForm;



public class DisplaySupervisionOrderConditionsAction extends JIMSBaseAction
{
   
   
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "link");
	}
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm)aForm;
		supervisionPlanForm.setConditionsMap(new Hashtable());
		
		String defendantId = supervisionPlanForm.getDefendantId();
		
		GetCSSpnViewConditionsEvent conditionsReqEvt = (GetCSSpnViewConditionsEvent)EventFactory.getInstance(CSEventControllerServiceNames.GETCSSPNVIEWCONDITIONS);
		conditionsReqEvt.setSpnId(defendantId);
		
		CompositeResponse compResponse = this.postRequestEvent(conditionsReqEvt);
		CSSpnViewConditionsResponseEvent respEvt = (CSSpnViewConditionsResponseEvent)MessageUtil.filterComposite(compResponse, CSSpnViewConditionsResponseEvent.class);
		
		if(respEvt == null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to retrieve the Conditions");
			return aMapping.findForward(UIConstants.SUCCESS);
		}
				
		supervisionPlanForm.setConditionsMap(respEvt.getCaseConditions());
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}//end of link()
}

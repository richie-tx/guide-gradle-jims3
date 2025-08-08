/*
 * Created on Feb 28, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.cscdcalendar.fieldVisit.action;

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
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayComplianceConditionsAction extends JIMSBaseAction{
	public ActionForward link(ActionMapping aMapping, 
			ActionForm aForm, 
			HttpServletRequest aRequest, 
			HttpServletResponse aResponse) {
		
		CSCalendarDisplayForm form = (CSCalendarDisplayForm)aForm;
		
		GetCSSpnViewConditionsEvent spnEvent = 
			(GetCSSpnViewConditionsEvent)EventFactory.getInstance(
					CSEventControllerServiceNames.GETCSSPNVIEWCONDITIONS);
		spnEvent.setSpnId(form.getSuperviseeId());
		CompositeResponse response = postRequestEvent(spnEvent);
		
		CSSpnViewConditionsResponseEvent respEvent = 
			(CSSpnViewConditionsResponseEvent) MessageUtil.filterComposite(
                response, CSSpnViewConditionsResponseEvent.class);
		
		form.setConditions(respEvent);
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link","link");
	}
}

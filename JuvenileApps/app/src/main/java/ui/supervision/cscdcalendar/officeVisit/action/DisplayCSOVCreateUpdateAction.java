/*
 * Created on Mar 10, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.cscdcalendar.officeVisit.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.party.reply.PartyResponseEvent;
import messaging.contact.to.PhoneNumberBean;
import messaging.party.GetPartyInfoEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PartyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administersupervisee.UIAdministerSuperviseeHelper;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;
import ui.supervision.cscdcalendar.form.CSCalendarOVForm;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCSOVCreateUpdateAction extends JIMSBaseAction {

	public ActionForward createOfficeVisit(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		form.getCurrentOfficeVisit().clear();
		
		String forwardStr = UIConstants.SUCCESS;
		if (form.getActivityFlow() != null && !form.getActivityFlow().equalsIgnoreCase("enterResults")){
			form.setConfirmMsg("");
		}
		
		String superviseeId = form.getSuperviseeId();
		if (StringUtil.isEmpty(superviseeId))
		{
				//retrieve parameter based on caseload search
			superviseeId = aRequest.getParameter("caseSuperviseeId");
		}
		
		
		if(superviseeId != null && superviseeId.length() > 0) {
			form.getCurrentOfficeVisit().setSuperviseeId(superviseeId);
			//Load Supervisee Form Info for JSP display
			SuperviseeForm superviseeForm = (SuperviseeForm)getSessionForm(aMapping, aRequest,"superviseeForm",true);						
			superviseeForm.setSelectedValue(superviseeId);
			superviseeForm.setSuperviseeId(superviseeId);
			UIAdministerSuperviseeHelper.populateSuperviseeForm(superviseeForm);
			//Load Supervisee Header Info for JSP display
			SuperviseeHeaderForm myHeaderForm = 
				(SuperviseeHeaderForm)getSessionForm(aMapping, aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
			myHeaderForm.setSuperviseeId(superviseeId);
			UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
			
			//Load date from CSCalendarDisplayForm
			CSCalendarDisplayForm csCalendarDisplayForm = 
				(CSCalendarDisplayForm)getSessionForm(aMapping, aRequest,"csCalendarDisplayForm",true);
			form.getCurrentOfficeVisit().setEventDate(form.getEventDate());
//					csCalendarDisplayForm.getEventDate());
			form.getCurrentOfficeVisit().setEventTypeCd(csCalendarDisplayForm.getSelectedEventTypeCd());
			form.getCurrentOfficeVisit().setPositionId(csCalendarDisplayForm.getPositionId());
			
			//Get Supervisee Phone
			GetPartyInfoEvent getPartyInfo = 
				(GetPartyInfoEvent)EventFactory.getInstance(PartyControllerServiceNames.GETPARTYINFO);
			getPartyInfo.setSpn(superviseeId);
			CompositeResponse response = postRequestEvent(getPartyInfo);
			
			PartyResponseEvent party = 
				(PartyResponseEvent)MessageUtil.filterComposite(response, PartyResponseEvent.class);
			
			if ( party != null ){
				
				if(party.getHomePhoneNum() != null && party.getHomePhoneNum().length() > 0) {
					form.getCurrentOfficeVisit().setSuperviseePhone(new PhoneNumberBean(party.getHomePhoneNum()));
				}
			}else{
				
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "M204 NAME record is missing for this SPN. Contact ITC Helpdesk");
				forwardStr = UIConstants.FAILURE; 
			}
		} else {
			throw new GeneralFeedbackMessageException("Supervisee Id is missing."); 
		}
		
		form.setActivityFlow(UIConstants.CREATE);
		aRequest.setAttribute("state","summary");
		
		return aMapping.findForward( forwardStr );
	}
	
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.createOfficeVisit","createOfficeVisit");

	}
}
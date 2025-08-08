/*
 * Created on Feb 6, 2008
 *
 */
package ui.supervision.cscdcalendar.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.party.reply.PartyResponseEvent;
import messaging.contact.to.Address;
import messaging.contact.to.PhoneNumberBean;
import messaging.cscdcalendar.GetCSFVSuperviseeDetailsEvent;
import messaging.party.GetPartyInfoEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
import naming.PartyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author awidjaja
 * 
 */
public class HandleCaseloadSelectionAction extends JIMSBaseAction {

	public ActionForward createFieldVisit(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		CSCalendarFVForm form = (CSCalendarFVForm) aForm;
		form.setActivityFlow(UIConstants.CREATE);
		form.getCurrentFieldVisit().clear();
		form.getCurrentFieldVisit().setFieldVisitDate(form.getEventDate());
		form.getCurrentFieldVisit().setNarrative("");
		form.getCurrentFieldVisit().setMethodOfContactCd("");

		// Set superviseeId along, also populate supervisee header info form
		String superviseeId = form.getSuperviseeId();
		if (StringUtil.isEmpty(superviseeId)) {
			// retrieve parameter based on caseload search
			superviseeId = aRequest.getParameter("caseSuperviseeId");
		}
		SuperviseeForm superviseeForm = (SuperviseeForm)getSessionForm(aMapping, aRequest,"superviseeForm",true);						
		superviseeForm.setSelectedValue(superviseeId);		
		SuperviseeHeaderForm myHeaderForm = 
			(SuperviseeHeaderForm) getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		myHeaderForm.setSuperviseeId(superviseeId);
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);

		if (superviseeId != null) {
			form.getCurrentFieldVisit().setSuperviseeId(superviseeId);
			GetCSFVSuperviseeDetailsEvent request = (GetCSFVSuperviseeDetailsEvent) EventFactory.getInstance(CSEventControllerServiceNames.GETCSFVSUPERVISEEDETAILS);
			request.setSuperviseeId(superviseeId);
/**			Commented out for Defect# JIMS200063178			
 			CompositeResponse compositeResponse = MessageUtil.postRequest(request);

			List superviseeDetails = (List) MessageUtil.compositeToCollection(compositeResponse, CSFVSuperviseeDetailsResponseEvent.class);

			if (superviseeDetails != null && superviseeDetails.size() > 0) {
				Iterator i = superviseeDetails.iterator();

				while (i.hasNext()) {
					CSFVSuperviseeDetailsResponseEvent resp = (CSFVSuperviseeDetailsResponseEvent) i.next();

					//form.getCurrentFieldVisit().setComments(resp.getComments());
					//form.getCurrentFieldVisit().setAddressDescription(resp.getAddressDesc());
					//form.getCurrentFieldVisit().setCaution(resp.getCaution());
					break;
				}
			}
**/
		}

		// Field visit date is selected from previous page, thus will be preset
		// here.
		//if(form.getCurrentFieldVisit().setFieldVisitDate(form.getEventDate());

		// Get Supervisee Address and Phone
		GetPartyInfoEvent getPartyInfo = (GetPartyInfoEvent) EventFactory.getInstance(PartyControllerServiceNames.GETPARTYINFO);
		getPartyInfo.setSpn(superviseeId);
		CompositeResponse response = postRequestEvent(getPartyInfo);

		PartyResponseEvent party = (PartyResponseEvent) MessageUtil.filterComposite(response, PartyResponseEvent.class);

		Address superviseeAddress = new Address();

		if (party != null) {
			superviseeAddress.setStreetNum(party.getCurrentAddressStreetNum());
			superviseeAddress.setStreetName(party.getCurrentAddressStreetName());
			superviseeAddress.setStreetType(party.getCurrentAddressStreetName2());
			superviseeAddress.setAptNum(party.getCurrentAddressAptNum());
			superviseeAddress.setCity(party.getCurrentAddressCity());
			superviseeAddress.setStateCode(party.getCurrentAddressStateId());
			superviseeAddress.setZipCode(party.getCurrentAddressZipCode());

			form.getCurrentFieldVisit().setSuperviseeAddress(superviseeAddress);

			PhoneNumberBean superviseePhone = new PhoneNumberBean();
			if (party.getHomePhoneNum() != null && party.getHomePhoneNum().length() > 0) {
				superviseePhone = new PhoneNumberBean(party.getHomePhoneNum());
				form.getCurrentFieldVisit().setSuperviseePhone(superviseePhone);
			}else{
				form.getCurrentFieldVisit().setSuperviseePhone(superviseePhone);
			}
		}

		return aMapping.findForward("createFieldVisitSuccess");
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.createFieldVisit", "createFieldVisit");
	}

}

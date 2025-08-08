/*
 * Created on April 3, 2008
 *
 */
package ui.supervision.cscdcalendar.fieldVisit.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdcalendar.SaveCSFVItineraryEvent;
import messaging.cscdcalendar.reply.CSFVItineraryResponseEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.supervision.cscdcalendar.CSFVBean;
import ui.supervision.cscdcalendar.CSFVItineraryBean;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

/**
 * @author awidjaja
 * 
 * 
 */
public class SubmitCSFVItineraryUpdateAction extends JIMSBaseAction {

	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CSCalendarFVForm form = (CSCalendarFVForm) aForm;

		CSFVItineraryBean currentItinerary = form.getCurrentItinerary();
		CSFVBean currentFieldVisit = form.getCurrentFieldVisit();

		SaveCSFVItineraryEvent saveItinerary = (SaveCSFVItineraryEvent) EventFactory
				.getInstance(CSEventControllerServiceNames.SAVECSFVITINERARY);
		saveItinerary.setUpdate(true);
		saveItinerary.setUpdateItineraryId(currentItinerary.getItineraryId());
		saveItinerary.setItineraryDate(currentItinerary.getEventDate());
		saveItinerary.setQuadrantCd(currentItinerary.getQuadrantCd());

		String dateStr = DateUtil.dateToString(currentItinerary.getEventDate(),
				DateUtil.DATE_FMT_1);

		String inOfficeFromTime = null;
		String inOfficeToTime = null;
		String inFieldFromTime = null;
		String inFieldToTime = null;

		if (!StringUtil.isEmpty(currentItinerary.getInOfficeFrom())) {
			inOfficeFromTime = currentItinerary.getInOfficeFrom() + " "
					+ currentItinerary.getInOfficeAMPMId1();
		} else {
			inOfficeFromTime = currentItinerary.getInOfficeFrom();
		}
		if (inOfficeFromTime != null && inOfficeFromTime.length() > 0) {
			Date inOfficeFrom = DateUtil.stringToDate(dateStr + " "
					+ inOfficeFromTime, "MM/dd/yyyy hh:mm a");
			saveItinerary.setInOfficeFrom(inOfficeFrom);
		}

		if (!StringUtil.isEmpty(currentItinerary.getInOfficeTo())) {
			inOfficeToTime = currentItinerary.getInOfficeTo() + " "
					+ currentItinerary.getInOfficeAMPMId2();
		} else {
			inOfficeToTime = currentItinerary.getInOfficeTo();
		}

		if (inOfficeToTime != null && inOfficeToTime.length() > 0) {
			Date inOfficeTo = DateUtil.stringToDate(dateStr + " "
					+ inOfficeToTime, "MM/dd/yyyy hh:mm a");
			saveItinerary.setInOfficeTo(inOfficeTo);
		}

		if (!StringUtil.isEmpty(currentItinerary.getInFieldFrom())) {
			inFieldFromTime = currentItinerary.getInFieldFrom() + " "
					+ currentItinerary.getInFieldAMPMId1();
		} else {
			inFieldFromTime = currentItinerary.getInFieldFrom();
		}

		if (inFieldFromTime != null && inFieldFromTime.length() > 0) {
			Date inFieldFrom = DateUtil.stringToDate(dateStr + " "
					+ inFieldFromTime, "MM/dd/yyyy hh:mm a");
			saveItinerary.setInFieldFrom(inFieldFrom);
		}

		if (!StringUtil.isEmpty(currentItinerary.getInFieldTo())) {
			inFieldToTime = currentItinerary.getInFieldTo() + " "
					+ currentItinerary.getInFieldAMPMId2();
		} else {
			inFieldToTime = currentItinerary.getInFieldTo();
		}

		if (inFieldToTime != null && inFieldToTime.length() > 0) {
			Date inFieldTo = DateUtil.stringToDate(dateStr + " "
					+ inFieldToTime, "MM/dd/yyyy hh:mm a");
			saveItinerary.setInFieldTo(inFieldTo);
		}

		saveItinerary.setMobile(currentItinerary.getMobilePager().getPhoneNumber());
		saveItinerary.setRadioCallNum(currentItinerary.getRadioCallNum());
		saveItinerary.setP1FirstName(currentItinerary.getPassenger1().getFirstName());
		saveItinerary.setP1MiddleName(currentItinerary.getPassenger1().getMiddleName());
		saveItinerary.setP1LastName(currentItinerary.getPassenger1().getLastName());

		saveItinerary.setP2FirstName(currentItinerary.getPassenger2().getFirstName());
		saveItinerary.setP2MiddleName(currentItinerary.getPassenger2().getMiddleName());
		saveItinerary.setP2LastName(currentItinerary.getPassenger2().getLastName());

		saveItinerary.setP3FirstName(currentItinerary.getPassenger3().getFirstName());
		saveItinerary.setP3MiddleName(currentItinerary.getPassenger3().getMiddleName());
		saveItinerary.setP3LastName(currentItinerary.getPassenger3().getLastName());

		saveItinerary.setCarTypeCd(currentItinerary.getCarTypeCd());
		saveItinerary.setMileageIn(currentItinerary.getMileageIn());
		saveItinerary.setMileageOut(currentItinerary.getMileageOut());
		saveItinerary.setAutoLicense(currentItinerary.getAutoLicense());
		saveItinerary.setAutoYear(currentItinerary.getAutoYear());
		saveItinerary.setAutoMake(currentItinerary.getAutoMake());
		saveItinerary.setAutoModel(currentItinerary.getAutoModel());
		saveItinerary.setAutoColor(currentItinerary.getAutoColor());

		AssignSuperviseeService helper = AssignSuperviseeService.getInstance();
		CSCDSupervisionStaffResponseEvent staff = helper.getCSCDStaff();

		saveItinerary.setPositionId(currentItinerary.getPositionId());

		CompositeResponse response = postRequestEvent(saveItinerary);

		CSFVItineraryResponseEvent returnedObj = (CSFVItineraryResponseEvent) MessageUtil
				.filterComposite(response, CSFVItineraryResponseEvent.class);
		if (returnedObj != null) {
			form.getCurrentItinerary().setItineraryId(returnedObj.getFvItineraryId());
		} else {
			// TODO: Failure
		}

		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");

	}

}

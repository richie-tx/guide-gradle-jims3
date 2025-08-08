//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\action\\SubmitCSFVEventUpdateAction.java

package ui.supervision.cscdcalendar.fieldVisit.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.to.Address;
import messaging.cscdcalendar.DeleteCSFVEvent;
import messaging.cscdcalendar.SaveCSFVEventEvent;
import messaging.cscdcalendar.SaveCSFVItineraryEvent;
import messaging.cscdcalendar.SaveCSFVReorderItineraryEvent;
import messaging.cscdcalendar.reply.CSFVItineraryResponseEvent;
import messaging.cscdcalendar.reply.CSFieldVisitResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.cscdcalendar.CSFVBean;
import ui.supervision.cscdcalendar.CSFVItineraryBean;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class SubmitCSFVEventUpdateAction extends JIMSBaseAction {

	/**
	 * @roseuid 479A0F0B025E
	 */
	public SubmitCSFVEventUpdateAction() {

	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {

		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {

		return aMapping.findForward(UIConstants.CANCEL);
	}

	public ActionForward reorderItinerary(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CSCalendarFVForm form = (CSCalendarFVForm) aForm;

		HashMap orderMap = new HashMap();
		HashMap startEndTimeMap = new HashMap();
	
		List eventsList = form.getEventsList();
		String[] origSequenceNum = form.getOrigSequenceNum();
		
		String dateStr = DateUtil.dateToString(form.getEventDate(), DateUtil.DATE_FMT_1);		
		
		String sTime = null;
		String eTime = null;
		Date startTime = null;
		Date endTime = null;

		// Create a HashMap from out-of-sequence list...
		for (int index = 0; index < eventsList.size(); index++) {
			CSFieldVisitResponseEvent event = (CSFieldVisitResponseEvent) eventsList.get(index);
			List startEndTimesList = new ArrayList();
			if (!StringUtil.isEmpty(event.getStartTime1())) {
				sTime = event.getStartTime1() + " " + event.getAMPMId1();
				startTime = DateUtil.stringToDate(dateStr + " " + sTime, "MM/dd/yyyy hh:mm a");
				startEndTimesList.add(startTime);

			} else {
				startEndTimesList.add(null);			
				}
		
			
			if (!StringUtil.isEmpty(event.getEndTime1())) {
				eTime = event.getEndTime1() + " " + event.getAMPMId2();
				endTime = DateUtil.stringToDate(dateStr+ " " + eTime, "MM/dd/yyyy hh:mm a");
				startEndTimesList.add(endTime);
				
			} else {
				startEndTimesList.add(null);
			}
			startEndTimeMap.put(event.getCsEventId(), startEndTimesList); 
			if (!event.getSequenceNum().equals(origSequenceNum[index])) {
				orderMap.put(event.getFvEventId(), event.getSequenceNum());
			}
		}
		
			SaveCSFVReorderItineraryEvent reorderItineraryEvent = (SaveCSFVReorderItineraryEvent) EventFactory
					.getInstance(CSEventControllerServiceNames.SAVECSFVREORDERITINERARY);
			reorderItineraryEvent.setFvIdToOrderMap(orderMap);
			reorderItineraryEvent.setFvIteneraryId(form.getCurrentItinerary()
					.getItineraryId());			
			reorderItineraryEvent.setFvStartTimeMap(startEndTimeMap);			
			postRequestEvent(reorderItineraryEvent);
		
		aRequest.setAttribute("status", UIConstants.CONFIRM_SUCCESS);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.saveContinue", "reorderItinerary");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.link", "save");
		keyMap.put("button.finishAndScheduleNext", "scheduleNext");
	}

	public ActionForward finishDelete(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {

		CSCalendarFVForm form = (CSCalendarFVForm) aForm;
		DeleteCSFVEvent deleteEvent = (DeleteCSFVEvent) EventFactory.getInstance(CSEventControllerServiceNames.DELETECSFV);
		deleteEvent.setFvEventid(form.getCurrentFieldVisit().getFvEventId());
		if(form.getCurrentFieldVisit().getStatusCd()!= null && (form.getCurrentFieldVisit().getStatusCd()).equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE)){
			aRequest.setAttribute("status", UIConstants.CONFIRM_DELETE);	
		}
		else{
			aRequest.setAttribute("status", UIConstants.CONFIRM);	
		}
		EventManager.getSharedInstance(EventManager.REQUEST).postEvent(deleteEvent);
		return aMapping.findForward(UIConstants.SUCCESS);

	}

	/**
	 * @throws GeneralFeedbackMessageException
	 * @roseuid 47A22E980125
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		CSCalendarFVForm form = (CSCalendarFVForm) aForm;

		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);

		if (form.getActivityFlow() != null && form.getActivityFlow().equals("delete")) {
			return finishDelete(aMapping, aForm, aRequest, aResponse);
		}

		CSFVItineraryBean currentItinerary = form.getCurrentItinerary();

		CSFVBean currentFieldVisit = form.getCurrentFieldVisit();
		if ("createItinerary".equals(form.getSecondaryActivityFlow())) {
			SaveCSFVItineraryEvent saveItinerary = (SaveCSFVItineraryEvent) EventFactory
					.getInstance(CSEventControllerServiceNames.SAVECSFVITINERARY);
			saveItinerary.setCreate(true);
			saveItinerary.setItineraryDate(currentItinerary.getEventDate());
			if (StringUtils.isNotEmpty(currentItinerary.getRadioCallNum())) {
				saveItinerary.setRadioCallNum(currentItinerary.getRadioCallNum());
			}
			String dateStr = DateUtil.dateToString(currentItinerary
					.getEventDate(), DateUtil.DATE_FMT_1);

			String inOfficeFromTime = null;
			String inOfficeToTime = null;
			String inFieldFromTime = null;
			String inFieldToTime = null;

			if (!StringUtil.isEmpty(currentItinerary.getInOfficeFrom())) {
				inOfficeFromTime = currentItinerary.getInOfficeFrom() + " " + currentItinerary.getInOfficeAMPMId1();
			} else {
				inOfficeFromTime = currentItinerary.getInOfficeFrom();
			}

			if (inOfficeFromTime != null && inOfficeFromTime.length() > 0) {
				Date inOfficeFrom = DateUtil.stringToDate(dateStr + " " + inOfficeFromTime, "MM/dd/yyyy hh:mm a");
				saveItinerary.setInOfficeFrom(inOfficeFrom);
			}

			if (!StringUtil.isEmpty(currentItinerary.getInOfficeTo())) {
				inOfficeToTime = currentItinerary.getInOfficeTo() + " " + currentItinerary.getInOfficeAMPMId2();
			} else {
				inOfficeToTime = currentItinerary.getInOfficeTo();
			}

			if (inOfficeToTime != null && inOfficeToTime.length() > 0) {
				Date inOfficeTo = DateUtil.stringToDate(dateStr + " " + inOfficeToTime, "MM/dd/yyyy hh:mm a");
				saveItinerary.setInOfficeTo(inOfficeTo);
			}

			if (!StringUtil.isEmpty(currentItinerary.getInFieldFrom())) {
				inFieldFromTime = currentItinerary.getInFieldFrom() + " " + currentItinerary.getInFieldAMPMId1();
			} else {
				inFieldFromTime = currentItinerary.getInFieldFrom();
			}

			if (inFieldFromTime != null && inFieldFromTime.length() > 0) {
				Date inFieldFrom = DateUtil.stringToDate(dateStr + " " + inFieldFromTime, "MM/dd/yyyy hh:mm a");
				saveItinerary.setInFieldFrom(inFieldFrom);
			}

			if (!StringUtil.isEmpty(currentItinerary.getInFieldTo())) {
				inFieldToTime = currentItinerary.getInFieldTo() + " " + currentItinerary.getInFieldAMPMId2();
			} else {
				inFieldToTime = currentItinerary.getInFieldTo();
			}

			if (inFieldToTime != null && inFieldToTime.length() > 0) {
				Date inFieldTo = DateUtil.stringToDate(dateStr + " " + inFieldToTime, "MM/dd/yyyy hh:mm a");
				saveItinerary.setInFieldTo(inFieldTo);
			}

			saveItinerary.setMobile(currentItinerary.getMobilePager().getPhoneNumber());
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
			saveItinerary.setQuadrantCd( currentItinerary.getQuadrantCd() );

			if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form
					.getContext())) {
				if (myHeaderForm.getOfficerPositionId() != null) {
					saveItinerary.setPositionId(myHeaderForm.getOfficerPositionId());
				}
			} else {
				saveItinerary.setPositionId(currentItinerary.getPositionId());
			}
			// saveItinerary.setPositionId(currentItinerary.getPositionId());

			CompositeResponse response = postRequestEvent(saveItinerary);

			CSFVItineraryResponseEvent returnedObj = (CSFVItineraryResponseEvent) MessageUtil
					.filterComposite(response, CSFVItineraryResponseEvent.class);
			if (returnedObj != null) {
				form.getCurrentItinerary().setItineraryId(returnedObj.getFvItineraryId());
			} else {
				// TODO: Failure
			}
		}

		SaveCSFVEventEvent saveEvent = (SaveCSFVEventEvent) EventFactory
				.getInstance(CSEventControllerServiceNames.SAVECSFVEVENT);

		saveEvent.setEventTypeId(CSFVBean.EVENT_TYPE);

		if ("create".equals(form.getActivityFlow())) {
			saveEvent.setCreate(true);
		} else if ("update".equals(form.getActivityFlow())
				|| "reorderItinerary".equals(form.getActivityFlow())) {
			saveEvent.setUpdate(true);
			saveEvent.setFvEventid(form.getCurrentFieldVisit().getFvEventId());
			saveEvent.setSequenceNum(currentFieldVisit.getSequenceNum());
			saveEvent.setResultPositionId(form.getPositionId()); //Set the staff position
		} else if ("enterResults".equals(form.getActivityFlow())) {
			saveEvent.setResults(true);
			saveEvent.setFvEventid(form.getCurrentFieldVisit().getFvEventId());
			saveEvent.setSequenceNum(currentFieldVisit.getSequenceNum());
			saveEvent.setResultPositionId(form.getPositionId()); //Set the staff position
		} else if ("reschedule".equals(form.getActivityFlow())) {
			saveEvent.setReschedule(true);
			// saveEvent.setSequenceNum(currentFieldVisit.getSequenceNum());
			saveEvent.setRescheduleFVEventId(form.getCurrentFieldVisit().getRescheduleFVEventId());

		}

		// check if itineraryId is missing. Display an error message otherwise
		saveEvent.setFvIteneraryId(form.getCurrentItinerary().getItineraryId());

		saveEvent.setEventDate(currentFieldVisit.getFieldVisitDate());

		String dateStr = DateUtil.dateToString(currentFieldVisit.getFieldVisitDate(), DateUtil.DATE_FMT_1);

		String startTimeStr = null;
		String endTimeStr = null;

		if (!StringUtil.isEmpty(currentFieldVisit.getStartTime())) {
			startTimeStr = currentFieldVisit.getStartTime() + " " + currentFieldVisit.getAMPMId1();
		} else {
			startTimeStr = currentFieldVisit.getStartTime();
		}

		if (!StringUtil.isEmpty(currentFieldVisit.getEndTime())) {
			endTimeStr = currentFieldVisit.getEndTime() + " " + currentFieldVisit.getAMPMId2();
		} else {
			endTimeStr = currentFieldVisit.getEndTime();
		}

		if (startTimeStr != null && startTimeStr.length() > 0) {
			Date startTime = DateUtil.stringToDate(dateStr + " " + startTimeStr, "MM/dd/yyyy hh:mm a");
			saveEvent.setStartTime(startTime);
		}

		if (endTimeStr != null && endTimeStr.length() > 0) {
			Date endTime = DateUtil.stringToDate(dateStr + " " + endTimeStr, "MM/dd/yyyy hh:mm a");
			saveEvent.setEndTime(endTime);
		}

		saveEvent.setPurposeCd(currentFieldVisit.getPurposeCd());
		if ("OT".equals(currentFieldVisit.getPurposeCd())) {
			saveEvent.setOtherPurpose(currentFieldVisit.getOtherPurpose());
		}
		saveEvent.setFvTypeCd(currentFieldVisit.getFieldVisitTypeCd());
		saveEvent.setSexOffendarTypeCd(currentFieldVisit.getSexOffenderCategoryCd());

		saveEvent.setComments(currentFieldVisit.getComments());
		saveEvent.setConditions(currentFieldVisit.getNoteworthyConditions());

		saveEvent.setKeyMap(currentFieldVisit.getKeyMap());

		Address alternateAddress = currentFieldVisit.getAlternateAddress();
		saveEvent.setStreetNum(alternateAddress.getStreetNum());
		saveEvent.setStreetName(alternateAddress.getStreetName());
		saveEvent.setStreetType(alternateAddress.getStreetTypeCode());
		saveEvent.setAptNum(alternateAddress.getAptNum());
		saveEvent.setCity(alternateAddress.getCity());
		saveEvent.setState(alternateAddress.getStateCode());
		saveEvent.setZipcode(alternateAddress.getZipCode() + alternateAddress.getAdditionalZipCode());
		saveEvent.setAddressTypeCd(alternateAddress.getAddressTypeCode());
		saveEvent.setCounty(alternateAddress.getCountyCode());

		saveEvent.setAltPhone(currentFieldVisit.getAlternatePhone()
				.getPhoneNumber());

		saveEvent.setAddrDesc(currentFieldVisit.getAddressDescription());
		saveEvent.setCaution(currentFieldVisit.getCaution());

		saveEvent.setPartyId(currentFieldVisit.getSuperviseeId());
		saveEvent.setAssignStaffPos_Id(myHeaderForm.getOfficerPositionId());
		if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form
				.getContext())) {
			if (form.getCurrentItinerary().getPositionId() != null) {
				saveEvent.setPositionId(form.getCurrentItinerary().getPositionId());
			}
		} else {
			saveEvent.setPositionId(form.getPositionId());
		}
		// saveEvent.setPositionId(form.getPositionId());

		saveEvent.setFvOutcomeCd(currentFieldVisit.getOutcomeCd());
		saveEvent.setContactMethodCd(currentFieldVisit.getMethodOfContactCd());
		saveEvent.setNarrative(currentFieldVisit.getNarrative());
		saveEvent.setMeasureTypeCd(currentFieldVisit.getMeasurementResultCd());

		saveEvent.setAssociateId(currentFieldVisit.getAssociateIds());
		saveEvent.setCurrentContext(form.getContext());

		MessageUtil.postRequest(saveEvent);
		aRequest.setAttribute("status", UIConstants.CONFIRM);
	
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward scheduleNext(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		
		String returnString = "";
		CSCalendarFVForm form = (CSCalendarFVForm) aForm;

		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);

		if (form.getActivityFlow() != null && form.getActivityFlow().equals("delete")) {
			return finishDelete(aMapping, aForm, aRequest, aResponse);
		}
		if (myHeaderForm.getOfficerPositionId() != null) {
			CSFVItineraryBean currentItinerary = form.getCurrentItinerary();
	
			CSFVBean currentFieldVisit = form.getCurrentFieldVisit();
			
			if ("createItinerary".equals(form.getSecondaryActivityFlow())) {
				SaveCSFVItineraryEvent saveItinerary = (SaveCSFVItineraryEvent) EventFactory
						.getInstance(CSEventControllerServiceNames.SAVECSFVITINERARY);
				saveItinerary.setCreate(true);
				saveItinerary.setItineraryDate(currentItinerary.getEventDate());
	
				String dateStr = DateUtil.dateToString(currentItinerary.getEventDate(), DateUtil.DATE_FMT_1);
	
				String inOfficeFromTime = null;
				String inOfficeToTime = null;
				String inFieldFromTime = null;
				String inFieldToTime = null;
	
				if (!StringUtil.isEmpty(currentItinerary.getInOfficeFrom())) {
					inOfficeFromTime = currentItinerary.getInOfficeFrom() + " " + currentItinerary.getInOfficeAMPMId1();
				} else {
					inOfficeFromTime = currentItinerary.getInOfficeFrom();
				}
	
				if (inOfficeFromTime != null && inOfficeFromTime.length() > 0) {
					Date inOfficeFrom = DateUtil.stringToDate(dateStr + " " + inOfficeFromTime, "MM/dd/yyyy hh:mm a");
					saveItinerary.setInOfficeFrom(inOfficeFrom);
				}
	
				if (!StringUtil.isEmpty(currentItinerary.getInOfficeTo())) {
					inOfficeToTime = currentItinerary.getInOfficeTo() + " " + currentItinerary.getInOfficeAMPMId2();
				} else {
					inOfficeToTime = currentItinerary.getInOfficeTo();
				}
	
				if (inOfficeToTime != null && inOfficeToTime.length() > 0) {
					Date inOfficeTo = DateUtil.stringToDate(dateStr + " " + inOfficeToTime, "MM/dd/yyyy hh:mm a");
					saveItinerary.setInOfficeTo(inOfficeTo);
				}
	
				if (!StringUtil.isEmpty(currentItinerary.getInFieldFrom())) {
					inFieldFromTime = currentItinerary.getInFieldFrom() + " " + currentItinerary.getInFieldAMPMId1();
				} else {
					inFieldFromTime = currentItinerary.getInFieldFrom();
				}
	
				if (inFieldFromTime != null && inFieldFromTime.length() > 0) {
					Date inFieldFrom = DateUtil.stringToDate(dateStr + " " + inFieldFromTime, "MM/dd/yyyy hh:mm a");
					saveItinerary.setInFieldFrom(inFieldFrom);
				}
	
				if (!StringUtil.isEmpty(currentItinerary.getInFieldTo())) {
					inFieldToTime = currentItinerary.getInFieldTo() + " " + currentItinerary.getInFieldAMPMId2();
				} else {
					inFieldToTime = currentItinerary.getInFieldTo();
				}
	
				if (inFieldToTime != null && inFieldToTime.length() > 0) {
					Date inFieldTo = DateUtil.stringToDate(dateStr + " " + inFieldToTime, "MM/dd/yyyy hh:mm a");
					saveItinerary.setInFieldTo(inFieldTo);
				}
	
				saveItinerary.setMobile(currentItinerary.getMobilePager().getPhoneNumber());
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
	
				if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form.getContext())) {
					if (myHeaderForm.getOfficerPositionId() != null) {
						saveItinerary.setPositionId(myHeaderForm.getOfficerPositionId());
					}
				} else {
					saveItinerary.setPositionId(currentItinerary.getPositionId());
				}
				CompositeResponse response = postRequestEvent(saveItinerary);	
				CSFVItineraryResponseEvent returnedObj = (CSFVItineraryResponseEvent) MessageUtil
						.filterComposite(response, CSFVItineraryResponseEvent.class);
				if (returnedObj != null) {
					form.getCurrentItinerary().setItineraryId(
							returnedObj.getFvItineraryId());
				} else {
					// TODO: Failure
				}
			}
	
			SaveCSFVEventEvent saveEvent = (SaveCSFVEventEvent) EventFactory
					.getInstance(CSEventControllerServiceNames.SAVECSFVEVENT);
			
			saveEvent.setEventTypeId(CSFVBean.EVENT_TYPE);
			//added for defect 74840
			if ("create".equals(form.getActivityFlow())) {
				saveEvent.setCreate(true);
			} else if ("update".equals(form.getActivityFlow())
					|| "reorderItinerary".equals(form.getActivityFlow())) {
				saveEvent.setUpdate(true);
				saveEvent.setFvEventid(form.getCurrentFieldVisit().getFvEventId());
				saveEvent.setSequenceNum(currentFieldVisit.getSequenceNum());
				saveEvent.setResultPositionId(form.getPositionId()); //Set the staff position
			} else if ("enterResults".equals(form.getActivityFlow())) {
				saveEvent.setResults(true);
				saveEvent.setFvEventid(form.getCurrentFieldVisit().getFvEventId());
				saveEvent.setSequenceNum(currentFieldVisit.getSequenceNum());
				saveEvent.setResultPositionId(form.getPositionId()); //Set the staff position
			} else if ("reschedule".equals(form.getActivityFlow())) {
				saveEvent.setReschedule(true);
				saveEvent.setRescheduleFVEventId(form.getCurrentFieldVisit().getRescheduleFVEventId());

			}
			//end of defect JIMS200074840
			
			
			
			// check if itineraryId is missing. Display an error message otherwise
			saveEvent.setFvIteneraryId(form.getCurrentItinerary().getItineraryId());
	
			saveEvent.setEventDate(currentFieldVisit.getFieldVisitDate());
	
			String dateStr = DateUtil.dateToString(currentFieldVisit.getFieldVisitDate(), DateUtil.DATE_FMT_1);
	
			String startTimeStr = null;
			String endTimeStr = null;
	
			if (!StringUtil.isEmpty(currentFieldVisit.getStartTime())) {
				startTimeStr = currentFieldVisit.getStartTime() + " " + currentFieldVisit.getAMPMId1();
			} else {
				startTimeStr = currentFieldVisit.getStartTime();
			}
	
			if (!StringUtil.isEmpty(currentFieldVisit.getEndTime())) {
				endTimeStr = currentFieldVisit.getEndTime() + " " + currentFieldVisit.getAMPMId2();
			} else {
				endTimeStr = currentFieldVisit.getEndTime();
			}
	
			if (startTimeStr != null && startTimeStr.length() > 0) {
				Date startTime = DateUtil.stringToDate(dateStr + " " + startTimeStr, "MM/dd/yyyy hh:mm a");
				saveEvent.setStartTime(startTime);
			}
	
			if (endTimeStr != null && endTimeStr.length() > 0) {
				Date endTime = DateUtil.stringToDate(dateStr + " " + endTimeStr, "MM/dd/yyyy hh:mm a");
				saveEvent.setEndTime(endTime);
			}
	
			saveEvent.setPurposeCd(currentFieldVisit.getPurposeCd());
			if ("OT".equals(currentFieldVisit.getPurposeCd())) {
				saveEvent.setOtherPurpose(currentFieldVisit.getOtherPurpose());
			}
			saveEvent.setFvTypeCd(currentFieldVisit.getFieldVisitTypeCd());
			saveEvent.setSexOffendarTypeCd(currentFieldVisit.getSexOffenderCategoryCd());
	
			saveEvent.setComments(currentFieldVisit.getComments());
			saveEvent.setConditions(currentFieldVisit.getNoteworthyConditions());
	
			saveEvent.setKeyMap(currentFieldVisit.getKeyMap());
	
			Address alternateAddress = currentFieldVisit.getAlternateAddress();
			saveEvent.setStreetNum(alternateAddress.getStreetNum());
			saveEvent.setStreetName(alternateAddress.getStreetName());
			saveEvent.setStreetType(alternateAddress.getStreetTypeCode());
			saveEvent.setAptNum(alternateAddress.getAptNum());
			saveEvent.setCity(alternateAddress.getCity());
			saveEvent.setState(alternateAddress.getStateCode());
			saveEvent.setZipcode(alternateAddress.getZipCode() + alternateAddress.getAdditionalZipCode());
			saveEvent.setAddressTypeCd(alternateAddress.getAddressTypeCode());
			saveEvent.setCounty(alternateAddress.getCountyCode());
	
			saveEvent.setAltPhone(currentFieldVisit.getAlternatePhone().getPhoneNumber());
	
			saveEvent.setAddrDesc(currentFieldVisit.getAddressDescription());
			saveEvent.setCaution(currentFieldVisit.getCaution());
	
			saveEvent.setPartyId(currentFieldVisit.getSuperviseeId());
			if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form.getContext())) {
				if (myHeaderForm.getOfficerPositionId() != null) {
					saveEvent.setPositionId(form.getCurrentItinerary().getPositionId());
				}
			} else {
				saveEvent.setPositionId(form.getPositionId());
			}
			
	
			saveEvent.setFvOutcomeCd(currentFieldVisit.getOutcomeCd());
			saveEvent.setContactMethodCd(currentFieldVisit.getMethodOfContactCd());
			saveEvent.setNarrative(currentFieldVisit.getNarrative());
			saveEvent.setMeasureTypeCd(currentFieldVisit.getMeasurementResultCd());
	
			saveEvent.setAssociateId(currentFieldVisit.getAssociateIds());
			saveEvent.setCurrentContext(form.getContext());
	
			form.setEventDateStr("");
			MessageUtil.postRequest(saveEvent); //added for defect JIMS200074840
			aRequest.setAttribute("status", UIConstants.CONFIRM);
	
			returnString = UIConstants.NEXT;
		}else{	
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "No active Supervision order exists for this SPN");
			returnString = UIConstants.ERROR;
		}
		return aMapping.findForward(returnString);
	}

	public ActionForward save(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		CSCalendarFVForm form = (CSCalendarFVForm) aForm;

		SuperviseeHeaderForm myHeaderForm = 
			(SuperviseeHeaderForm) getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);

		CSFVItineraryBean currentItinerary = form.getCurrentItinerary();

		// CSFVBean currentFieldVisit = form.getCurrentFieldVisit();
		if ("createItinerary".equals(form.getSecondaryActivityFlow())) {
			SaveCSFVItineraryEvent saveItinerary = (SaveCSFVItineraryEvent) EventFactory
					.getInstance(CSEventControllerServiceNames.SAVECSFVITINERARY);
			saveItinerary.setCreate(true);
			saveItinerary.setItineraryDate(currentItinerary.getEventDate());

			String dateStr = DateUtil.dateToString(currentItinerary.getEventDate(), DateUtil.DATE_FMT_1);

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
				Date inOfficeFrom = DateUtil.stringToDate(dateStr + " " + inOfficeFromTime, "MM/dd/yyyy hh:mm a");
				saveItinerary.setInOfficeFrom(inOfficeFrom);
			}

			if (!StringUtil.isEmpty(currentItinerary.getInOfficeTo())) {
				inOfficeToTime = currentItinerary.getInOfficeTo() + " " + currentItinerary.getInOfficeAMPMId2();
			} else {
				inOfficeToTime = currentItinerary.getInOfficeTo();
			}

			if (inOfficeToTime != null && inOfficeToTime.length() > 0) {
				Date inOfficeTo = DateUtil.stringToDate(dateStr + " " + inOfficeToTime, "MM/dd/yyyy hh:mm a");
				saveItinerary.setInOfficeTo(inOfficeTo);
			}

			if (!StringUtil.isEmpty(currentItinerary.getInFieldFrom())) {
				inFieldFromTime = currentItinerary.getInFieldFrom() + " " + currentItinerary.getInFieldAMPMId1();
			} else {
				inFieldFromTime = currentItinerary.getInFieldFrom();
			}

			if (inFieldFromTime != null && inFieldFromTime.length() > 0) {
				Date inFieldFrom = DateUtil.stringToDate(dateStr + " " + inFieldFromTime, "MM/dd/yyyy hh:mm a");
				saveItinerary.setInFieldFrom(inFieldFrom);
			}

			if (!StringUtil.isEmpty(currentItinerary.getInFieldTo())) {
				inFieldToTime = currentItinerary.getInFieldTo() + " "
						+ currentItinerary.getInFieldAMPMId2();
			} else {
				inFieldToTime = currentItinerary.getInFieldTo();
			}

			if (inFieldToTime != null && inFieldToTime.length() > 0) {
				Date inFieldTo = DateUtil.stringToDate(dateStr + " " + inFieldToTime, "MM/dd/yyyy hh:mm a");
				saveItinerary.setInFieldTo(inFieldTo);
			}

			saveItinerary.setMobile(currentItinerary.getMobilePager().getPhoneNumber());
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

			if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form.getContext())) {
				if (myHeaderForm.getOfficerPositionId() != null) {
					saveItinerary.setPositionId(myHeaderForm.getOfficerPositionId());
				}
			} else {
				saveItinerary.setPositionId(currentItinerary.getPositionId());
			}
			// saveItinerary.setPositionId(currentItinerary.getPositionId());

			CompositeResponse response = postRequestEvent(saveItinerary);

			CSFVItineraryResponseEvent returnedObj = (CSFVItineraryResponseEvent) MessageUtil
					.filterComposite(response, CSFVItineraryResponseEvent.class);
			if (returnedObj != null) {
				form.getCurrentItinerary().setItineraryId(returnedObj.getFvItineraryId());
			} else {
				// TODO: Failure
			}
		}

		for (Iterator iter = form.getFilteredList().iterator(); iter.hasNext();) {
			CSFieldVisitResponseEvent event = (CSFieldVisitResponseEvent) iter.next();

			CSFVBean fv = new CSFVBean(form.getAgencyId());
			fv.load(event);
			fv.setRescheduleFVEventId(fv.getFvEventId());
			form.setCurrentFieldVisit(fv);

			CSFVBean currentFieldVisit = form.getCurrentFieldVisit();

			if (currentFieldVisit != null) {

				SaveCSFVEventEvent saveEvent = (SaveCSFVEventEvent) EventFactory
						.getInstance(CSEventControllerServiceNames.SAVECSFVEVENT);

				saveEvent.setEventTypeId(CSFVBean.EVENT_TYPE);

				saveEvent.setReschedule(true);
				// saveEvent.setSequenceNum(currentFieldVisit.getSequenceNum());
				saveEvent.setRescheduleFVEventId(form.getCurrentFieldVisit().getRescheduleFVEventId());

				// check if itineraryId is missing. Display an error message
				// otherwise
				saveEvent.setFvIteneraryId(form.getCurrentItinerary().getItineraryId());

				saveEvent.setEventDate(currentItinerary.getEventDate());

				String dateStr = DateUtil.dateToString(currentItinerary.getEventDate(), DateUtil.DATE_FMT_1);

				String startTimeStr = null;
				String endTimeStr = null;

				if (!StringUtil.isEmpty(currentFieldVisit.getStartTime())) {
					startTimeStr = currentFieldVisit.getStartTime() + " " + currentFieldVisit.getAMPMId1();
				} else {
					startTimeStr = currentFieldVisit.getStartTime();
				}

				if (!StringUtil.isEmpty(currentFieldVisit.getEndTime())) {
					endTimeStr = currentFieldVisit.getEndTime() + " " + currentFieldVisit.getAMPMId2();
				} else {
					endTimeStr = currentFieldVisit.getEndTime();
				}

				if (startTimeStr != null && startTimeStr.length() > 0) {
					Date startTime = DateUtil.stringToDate(dateStr + " " + startTimeStr, "MM/dd/yyyy hh:mm a");
					saveEvent.setStartTime(startTime);
				}

				if (endTimeStr != null && endTimeStr.length() > 0) {
					Date endTime = DateUtil.stringToDate(dateStr + " " + endTimeStr, "MM/dd/yyyy hh:mm a");
					saveEvent.setEndTime(endTime);
				}

				saveEvent.setPurposeCd(currentFieldVisit.getPurposeCd());
				if ("OT".equals(currentFieldVisit.getPurposeCd())) {
					saveEvent.setOtherPurpose(currentFieldVisit.getOtherPurpose());
				}
				saveEvent.setFvTypeCd(currentFieldVisit.getFieldVisitTypeCd());
				saveEvent.setSexOffendarTypeCd(currentFieldVisit.getSexOffenderCategoryCd());

				saveEvent.setComments(currentFieldVisit.getComments());
				saveEvent.setConditions(currentFieldVisit.getNoteworthyConditions());

				saveEvent.setKeyMap(currentFieldVisit.getKeyMap());

				Address alternateAddress = currentFieldVisit.getAlternateAddress();
				saveEvent.setStreetNum(alternateAddress.getStreetNum());
				saveEvent.setStreetName(alternateAddress.getStreetName());
				saveEvent.setStreetType(alternateAddress.getStreetTypeCode());
				saveEvent.setAptNum(alternateAddress.getAptNum());
				saveEvent.setCity(alternateAddress.getCity());
				saveEvent.setState(alternateAddress.getStateCode());
				saveEvent.setZipcode(alternateAddress.getZipCode()
						+ alternateAddress.getAdditionalZipCode());
				saveEvent.setAddressTypeCd(alternateAddress.getAddressTypeCode());
				saveEvent.setCounty(alternateAddress.getCountyCode());
				if (currentFieldVisit.getAlternatePhone() != null
						&& currentFieldVisit.getAlternatePhone().getAreaCode() != null) {

					saveEvent.setAltPhone(currentFieldVisit.getAlternatePhone().getPhoneNumber());
				}

				saveEvent.setAddrDesc(currentFieldVisit.getAddressDescription());
				saveEvent.setCaution(currentFieldVisit.getCaution());

				saveEvent.setPartyId(currentFieldVisit.getSuperviseeId());
				if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form.getContext())) {
					if (myHeaderForm.getOfficerPositionId() != null) {
						saveEvent.setPositionId(form.getCurrentItinerary().getPositionId());
					}
				} else {
					saveEvent.setPositionId(form.getPositionId());
				}
				// saveEvent.setPositionId(form.getPositionId());

				saveEvent.setFvOutcomeCd(currentFieldVisit.getOutcomeCd());
				saveEvent.setContactMethodCd(currentFieldVisit.getMethodOfContactCd());
				saveEvent.setNarrative(currentFieldVisit.getNarrative());
				saveEvent.setMeasureTypeCd(currentFieldVisit.getMeasurementResultCd());

				saveEvent.setAssociateId(currentFieldVisit.getAssociateIds());
				saveEvent.setCurrentContext(form.getContext());
				MessageUtil.postRequest( saveEvent );
			}
		}
		aRequest.setAttribute("status", UIConstants.SUCCESS);

		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @roseuid 47A22EA200F6
	 */
	public ActionForward finishResults(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(null);
	}

	/**
	 * @roseuid 47A22EAB029C
	 */
	public ActionForward finishReschedule(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(null);
	}
}
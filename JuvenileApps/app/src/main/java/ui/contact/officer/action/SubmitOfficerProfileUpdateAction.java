//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\officer\\action\\SubmitOfficerProfileUpdateAction.java

package ui.contact.officer.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.authentication.CreateJIMS2AccountEvent;
import messaging.authentication.GetJIMS2AccountByOfficerIdEvent;
import messaging.contact.officer.reply.OfficerJuvWarrantAssociationResponseEvent;
import messaging.officer.DeleteOfficerProfileEvent;
import messaging.officer.UpdateOfficerProfileEvent;
import messaging.officer.WorkDayRequestEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.LogonControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.contact.UIContactHelper;
import ui.contact.officer.form.OfficerForm;

public class SubmitOfficerProfileUpdateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42E6766E0120
	 */
	public SubmitOfficerProfileUpdateAction()
	{

	}

	/**
		* @return Map
		*/
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.cancelCreate", "cancelCreate");
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.inactivate", "finish");
		buttonMap.put("button.back", "back");
		return buttonMap;
	}

	/**
		* @param aMapping
		* @param aForm
		* @param aRequest
		* @param aResponse
		* @return ActionForward
		*/
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.FAILURE;
		OfficerForm officerForm = (OfficerForm) aForm;
		String action = officerForm.getAction();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		if (action.equals(UIConstants.CREATE) || action.equals(UIConstants.UPDATE))
		{
			forward = this.update(officerForm, aRequest, dispatch);
		}
		else
			if (action.equals(UIConstants.INACTIVATE))
			{
				forward = this.delete(officerForm, aRequest, dispatch);
				officerForm.setStatusId("I");
			}
			else
			{
				return aMapping.findForward(forward);
			}

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		//	set the values from dropdownlist
		UIContactHelper.setManageOfficerValuesFromDropDownList(officerForm);
		return aMapping.findForward(forward);
	}

	/**
		* @param officerForm
		* @param aRequest HttpServletRequest
		* @param dispatch IDispatch 
		* @return String forward
		*/
	private String delete(OfficerForm officerForm, HttpServletRequest aRequest, IDispatch dispatch)
	{
		DeleteOfficerProfileEvent requestEvent =
			(DeleteOfficerProfileEvent) EventFactory.getInstance(
				OfficerProfileControllerServiceNames.DELETEOFFICERPROFILE);
		requestEvent.setOfficerProfileId(officerForm.getOfficerProfileId());
		//	set the values from dropdownlist
		UIContactHelper.setManageOfficerValuesFromDropDownList(officerForm);
		dispatch.postEvent(requestEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Object obj = MessageUtil.filterComposite(compositeResponse, OfficerJuvWarrantAssociationResponseEvent.class);
		if (obj != null){
			OfficerJuvWarrantAssociationResponseEvent event = (OfficerJuvWarrantAssociationResponseEvent) obj;
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.officer.officerwarrantassociation",event.getWarrantId(),event.getJuvNumber(),event.getJuvLastName(),event.getJuvFirstName()));
			saveErrors(aRequest, errors);
			return UIConstants.FAILURE;
		}
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		return UIConstants.CONFIRM_DELETE_SUCCESS;
	}

	/**
		* @param officerForm
		* @param aRequest HttpServletRequest
		* @param dispatch IDispatch 
		* @return String forward
		*/
	private String update(OfficerForm officerForm, HttpServletRequest aRequest, IDispatch dispatch)
	{
		UpdateOfficerProfileEvent updateEvent =
			(UpdateOfficerProfileEvent) EventFactory.getInstance(
				OfficerProfileControllerServiceNames.UPDATEOFFICERPROFILE);		
		// set the officer profile attributes
		updateEvent = this.setOfficerProfile(updateEvent, officerForm);
		// set the work days attribute
		updateEvent = this.setWorkDays(updateEvent, officerForm);
		updateEvent.setOfficerProfileId(officerForm.getOfficerProfileId());
		dispatch.postEvent(updateEvent);
		//on update check if the officer has a jims2Account
		if(officerForm.getAction().equals(UIConstants.UPDATE))
		{
			GetJIMS2AccountByOfficerIdEvent accountEvent =
				(GetJIMS2AccountByOfficerIdEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNTBYOFFICERID);
			accountEvent.setOfficerId(officerForm.getOfficerProfileId());
			dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(accountEvent);
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			JIMS2AccountResponseEvent jims2User =
				(JIMS2AccountResponseEvent) MessageUtil.filterComposite(
					compositeResponse,
					JIMS2AccountResponseEvent.class);
			if (jims2User != null)
			{
				//update the JIMS2 account//#79250
				CreateJIMS2AccountEvent createJIMS2 =
					(CreateJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.CREATEJIMS2ACCOUNT);
				//createJIMS2.setCreate(false);#U.S #79250
				createJIMS2.setFirstName(officerForm.getFirstName());
				createJIMS2.setMiddleName(officerForm.getMiddleName());
				createJIMS2.setLastName(officerForm.getLastName());
				createJIMS2.setJIMS2LogonId(jims2User.getJIMS2LogonId());
				createJIMS2.setLogonId(jims2User.getJimsLogonId());
				createJIMS2.setUserID(jims2User.getJimsLogonId());
				IDispatch disp =  EventManager.getSharedInstance(EventManager.REQUEST);
				disp.postEvent(createJIMS2);
			}
		}
		if (officerForm.getAction().equals(UIConstants.UPDATE))
		{
			return UIConstants.CONFIRM_UPDATE_SUCCESS;
		}
		else
		{
			return UIConstants.CONFIRM_SUCCESS;
		}
	}

	/**
		* @param aMapping
		* @param aForm
		* @param aRequest
		* @param aResponse
		* @return ActionForward
		*/
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/**
		  * @param aMapping
		  * @param aForm
		  * @param aRequest
		  * @param aResponse
		  * @return ActionForward
		  */
	public ActionForward cancelCreate(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL_CREATE);
	}

	/**
		* @param aMapping
		* @param aForm
		* @param aRequest
		* @param aResponse
		* @return ActionForward
		*/
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	/**
	 * @param updateEvent
	 * @param officerForm
	 * @return UpdateOfficerProfileEvent
	 */
	private UpdateOfficerProfileEvent setWorkDays(UpdateOfficerProfileEvent updateEvent, OfficerForm officerForm)
	{
		Collection workSchedules = new ArrayList();
		WorkDayRequestEvent request = null;
		if ((officerForm.getDayOff0() != null && officerForm.getDayOff0().equals("Y"))
			|| (officerForm.getStartTime0() != null && !officerForm.getStartTime0().equals("")))
		{
			request = new WorkDayRequestEvent();
			request.setDayId(UIConstants.SUNDAY);
			request.setDayOff(officerForm.getDayOff0());
			request.setOfficerProfileId(officerForm.getOfficerProfileId());
			request.setWorkScheduleId(officerForm.getWorkScheduleId0());
			if (officerForm.getDayOff0() != null && officerForm.getDayOff0().equals("Y"))
			{
				request.setEndTimeId("");
				request.setStartTimeId("");
			}
			else
			{
				request.setEndTimeId(officerForm.getEndTimeId0());
				request.setStartTimeId(officerForm.getStartTimeId0());
			}
			workSchedules.add(request);
		}

		if ((officerForm.getDayOff1() != null && officerForm.getDayOff1().equals("Y"))
			|| (officerForm.getStartTime1() != null && !officerForm.getStartTime1().equals("")))
		{
			request = new WorkDayRequestEvent();
			request.setDayId(UIConstants.MONDAY);
			request.setDayOff(officerForm.getDayOff1());
			request.setOfficerProfileId(officerForm.getOfficerProfileId());
			request.setWorkScheduleId(officerForm.getWorkScheduleId1());
			if (officerForm.getDayOff1() != null && officerForm.getDayOff1().equals("Y"))
			{
				request.setEndTimeId("");
				request.setStartTimeId("");
			}
			else
			{
				request.setStartTimeId(officerForm.getStartTimeId1());
				request.setEndTimeId(officerForm.getEndTimeId1());
			}
			workSchedules.add(request);
		}

		if ((officerForm.getDayOff2() != null && officerForm.getDayOff2().equals("Y"))
			|| (officerForm.getStartTime2() != null && !officerForm.getStartTime2().equals("")))
		{
			request = new WorkDayRequestEvent();
			request.setDayId(UIConstants.TUESDAY);
			request.setDayOff(officerForm.getDayOff2());
			request.setOfficerProfileId(officerForm.getOfficerProfileId());
			request.setWorkScheduleId(officerForm.getWorkScheduleId2());
			if (officerForm.getDayOff2() != null && officerForm.getDayOff2().equals("Y"))
			{
				request.setEndTimeId("");
				request.setStartTimeId("");
			}
			else
			{
				request.setEndTimeId(officerForm.getEndTimeId2());
				request.setStartTimeId(officerForm.getStartTimeId2());
			}
			workSchedules.add(request);
		}

		if ((officerForm.getDayOff3() != null && officerForm.getDayOff3().equals("Y"))
			|| (officerForm.getStartTime3() != null && !officerForm.getStartTime3().equals("")))
		{
			request = new WorkDayRequestEvent();
			request.setDayId(UIConstants.WEDNESDAY);
			request.setDayOff(officerForm.getDayOff3());
			request.setOfficerProfileId(officerForm.getOfficerProfileId());
			request.setWorkScheduleId(officerForm.getWorkScheduleId3());
			if (officerForm.getDayOff3() != null && officerForm.getDayOff3().equals("Y"))
			{
				request.setEndTimeId("");
				request.setStartTimeId("");
			}
			else
			{
				request.setEndTimeId(officerForm.getEndTimeId3());
				request.setStartTimeId(officerForm.getStartTimeId3());
			}
			workSchedules.add(request);
		}

		if ((officerForm.getDayOff4() != null && officerForm.getDayOff4().equals("Y"))
			|| (officerForm.getStartTime4() != null && !officerForm.getStartTime4().equals("")))
		{
			request = new WorkDayRequestEvent();
			request.setDayId(UIConstants.THURSDAY);
			request.setDayOff(officerForm.getDayOff4());
			request.setOfficerProfileId(officerForm.getOfficerProfileId());
			request.setWorkScheduleId(officerForm.getWorkScheduleId4());
			if (officerForm.getDayOff4() != null && officerForm.getDayOff4().equals("Y"))
			{
				request.setEndTimeId("");
				request.setStartTimeId("");
			}
			else
			{
				request.setEndTimeId(officerForm.getEndTimeId4());
				request.setStartTimeId(officerForm.getStartTimeId4());
			}
			workSchedules.add(request);
		}

		if ((officerForm.getDayOff5() != null && officerForm.getDayOff5().equals("Y"))
			|| (officerForm.getStartTime5() != null && !officerForm.getStartTime5().equals("")))
		{
			request = new WorkDayRequestEvent();
			request.setDayId(UIConstants.FRIDAY);
			request.setDayOff(officerForm.getDayOff5());
			request.setOfficerProfileId(officerForm.getOfficerProfileId());
			request.setWorkScheduleId(officerForm.getWorkScheduleId5());
			if (officerForm.getDayOff5() != null && officerForm.getDayOff5().equals("Y"))
			{
				request.setEndTimeId("");
				request.setStartTimeId("");
			}
			else
			{
				request.setEndTimeId(officerForm.getEndTimeId5());
				request.setStartTimeId(officerForm.getStartTimeId5());
			}
			workSchedules.add(request);
		}

		if ((officerForm.getDayOff6() != null && officerForm.getDayOff6().equals("Y"))
			|| (officerForm.getStartTime6() != null && !officerForm.getStartTime6().equals("")))
		{
			request = new WorkDayRequestEvent();
			request.setDayId(UIConstants.SATURDAY);
			request.setDayOff(officerForm.getDayOff6());
			request.setOfficerProfileId(officerForm.getOfficerProfileId());
			request.setWorkScheduleId(officerForm.getWorkScheduleId6());
			if (officerForm.getDayOff6() != null && officerForm.getDayOff6().equals("Y"))
			{
				request.setEndTimeId("");
				request.setStartTimeId("");
			}
			else
			{
				request.setEndTimeId(officerForm.getEndTimeId6());
				request.setStartTimeId(officerForm.getStartTimeId6());
			}
			workSchedules.add(request);
		}

		updateEvent.setWorkDayRequestEvents(workSchedules);
		return updateEvent;
	}

	/**
	 * @param officerForm OfficerForm
	 * @param updateEvent UpdateOfficerProfileEvent 
		* @return UpdateOfficerProfileEvent
		*/
	private UpdateOfficerProfileEvent setOfficerProfile(UpdateOfficerProfileEvent updateEvent, OfficerForm officerForm)
	{
		updateEvent.setLastName(officerForm.getLastName());
		updateEvent.setFirstName(officerForm.getFirstName());
		updateEvent.setDepartmentId(officerForm.getDepartmentId());
		updateEvent.setOtherIdNum(officerForm.getOtherIdNum());
		updateEvent.setBadgeNum(officerForm.getBadgeNum());
		updateEvent.setLogonId(officerForm.getUserId());
		updateEvent.setMiddleName(officerForm.getMiddleName());
		updateEvent.setOfficerTypeId(officerForm.getOfficerTypeId());
		updateEvent.setSsn(this.getSsn(officerForm));
		
		if("".equals(officerForm.getJuvLocationId()) == false)
		{
			updateEvent.setJuvLocationId(officerForm.getJuvLocationId());
		}
		if("".equals(officerForm.getJuvUnitId()) == false)
		{
			updateEvent.setJuvUnitId(officerForm.getJuvUnitId());
		}

		updateEvent.setWorkPhone(this.getWorkPhone(officerForm));
		updateEvent.setExtn(officerForm.getExtn());
		updateEvent.setHomePhone(this.getHomePhone(officerForm));
		updateEvent.setCellPhone(this.getCellPhone(officerForm));
		updateEvent.setPager(this.getPager(officerForm));
		updateEvent.setFax(this.getFax(officerForm));
		updateEvent.setFaxLocation(officerForm.getFaxLocation());
		updateEvent.setEmail(officerForm.getEmail());
		updateEvent.setRankId(officerForm.getRankId());
		updateEvent.setDivisionName(officerForm.getDivisionName());
		updateEvent.setAssignedArea(officerForm.getAssignedArea());
		updateEvent.setRadioNum(officerForm.getRadioNumber());
		updateEvent.setWorkShift(officerForm.getWorkShift());
		updateEvent.setStreetNum(officerForm.getStreetNumber());
		updateEvent.setStreetName(officerForm.getStreetName());
		updateEvent.setStreetTypeId(officerForm.getStreetTypeId());
		updateEvent.setApartmentNum(officerForm.getApartmentNumber());
		updateEvent.setCity(officerForm.getCity());
		updateEvent.setStateId(officerForm.getStateId());
		updateEvent.setZipCode(officerForm.getZipCode());
		updateEvent.setAdditionalZipCode(officerForm.getAdditionalZipCode());
		updateEvent.setManagerId(officerForm.getManagerId());
		updateEvent.setManagerFirstName(officerForm.getManagerFirstName());
		updateEvent.setManagerLastName(officerForm.getManagerLastName());
		updateEvent.setManagerMiddleName(officerForm.getManagerMiddleName());
		updateEvent.setStatusId(officerForm.getStatusId());
		updateEvent.setOfficerSubTypeId(officerForm.getOfficerSubTypeId());
		updateEvent.setSurvey(officerForm.getSurvey());
		updateEvent.setSupervisor(officerForm.getSupervisor());
		UIContactHelper.setManageOfficerValuesFromDropDownList(officerForm);
		return updateEvent;
	}

	/**
	 * @param officerForm
	 * @return
	 */
	private String getWorkPhone(OfficerForm officerForm)
	{
		StringBuffer workPhone = new StringBuffer(officerForm.getWorkPhoneAreaCode());
		workPhone.append(officerForm.getWorkPhonePrefix());
		workPhone.append(officerForm.getWorkPhoneMain());
		return workPhone.toString();
	}

	/**
	 * @param officerForm
	 * @return
	 */
	private String getCellPhone(OfficerForm officerForm)
	{
		StringBuffer cellPhone = new StringBuffer(officerForm.getCellPhoneAreaCode());
		cellPhone.append(officerForm.getCellPhonePrefix());
		cellPhone.append(officerForm.getCellPhoneMain());
		return cellPhone.toString();
	}

	/**
	 * @param officerForm
	 * @return
	 */
	private String getPager(OfficerForm officerForm)
	{
		StringBuffer pager = new StringBuffer(officerForm.getPagerAreaCode());
		pager.append(officerForm.getPagerPrefix());
		pager.append(officerForm.getPagerMain());
		return pager.toString();
	}

	/**
	 * @param officerForm
	 * @return
	 */
	private String getFax(OfficerForm officerForm)
	{
		StringBuffer fax = new StringBuffer(officerForm.getFaxAreaCode());
		fax.append(officerForm.getFaxPrefix());
		fax.append(officerForm.getFaxMain());
		return fax.toString();
	}

	/**
	 * @param officerForm
	 * @return
	 */
	private String getHomePhone(OfficerForm officerForm)
	{
		StringBuffer homePhone = new StringBuffer(officerForm.getHomePhoneAreaCode());
		homePhone.append(officerForm.getHomePhonePrefix());
		homePhone.append(officerForm.getHomePhoneMain());
		return homePhone.toString();
	}

	/**
	 * @param officerForm
	 * @return
	 */
	private String getSsn(OfficerForm officerForm)
	{
		StringBuffer ssn = new StringBuffer(officerForm.getSsn1());
		ssn.append(officerForm.getSsn2());
		ssn.append(officerForm.getSsn3());
		return ssn.toString();
	}

	/**
		 * @param aRequest
		 */
	private void sendToErrorPage(HttpServletRequest aRequest)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.common"));
		saveErrors(aRequest, errors);
	}
}
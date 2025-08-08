/*
 * Created on Mar 31, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.cscdcalendar.groupOfficeVisit.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administersupervisee.GetSuperviseeDataEvent;
import messaging.administersupervisee.reply.SuperviseeDetailResponseEvent;
import messaging.contact.domintf.IName;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.cscdcalendar.SuperviseeBean;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;
import ui.supervision.cscdcalendar.form.CSCalendarOVForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCSGVCreateUpdateAction extends JIMSBaseAction {

	public ActionForward createGroupOfficeVisit(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		form.setAgencyId(SecurityUIHelper.getUserAgencyId());  //needed for spellcheck in jsp, added 01/28/2011
		if (form.getAgencyId() == null){
			form.setAgencyId("");
		}
		String superviseeId = aRequest.getParameter("superviseeId");
		if (StringUtil.isEmpty(superviseeId))
		{
				//retrieve parameter based on caseload search
			superviseeId = aRequest.getParameter("caseSuperviseeId");
		}
		
		/*
		if(superviseeIds == null || superviseeIds.length < 1) {
			throw new GeneralFeedbackMessageException("Supervisee Id is missing.");
		}*/
		
		//Load date from CSCalendarDisplayForm
		CSCalendarDisplayForm csCalendarDisplayForm = 
			(CSCalendarDisplayForm)getSessionForm(
					aMapping, aRequest,
					"csCalendarDisplayForm",true);
		form.getCurrentOfficeVisit().setEventDate(
				form.getEventDate());
		form.getCurrentOfficeVisit().setEventTypeCd(
				form.getEventTypeCd());
		
		if(PDCodeTableConstants.CS_OFFICE_VISIT_TYPE.equals(
				form.getEventTypeCd())) {
			form.getCurrentOfficeVisit().setSuperviseeId(superviseeId);
			
			//Load Supervisee Header Info for JSP display
			SuperviseeHeaderForm myHeaderForm = 
				(SuperviseeHeaderForm)getSessionForm(
						aMapping, aRequest,
						UIConstants.SUPERVISEE_HEADER_FORM,true);
			myHeaderForm.setSuperviseeId(superviseeId);
			UICommonSupervisionHelper.populateSuperviseeHeaderForm(
					myHeaderForm);
		}
		else if(PDCodeTableConstants.CS_GROUP_OFFICE_VISIT_TYPE.equals(
				form.getEventTypeCd())){
			String tempSuperviseeId = aRequest.getParameter("superviseeId");
			if (StringUtil.isEmpty(superviseeId))
			{
				String[] superviseeIds = aRequest.getParameterValues("caseSuperviseeIds");

			
				
					List superviseeList = form.getSuperviseeList();
					SuperviseeBean supervisee = null;

					if (superviseeList == null) {
						superviseeList = new ArrayList();
					} else {
						superviseeList.clear();
					}

					if (superviseeIds != null && superviseeIds.length > 0) {
						for (String superviseId : superviseeIds) {
							
							for(int i=0;i<csCalendarDisplayForm.getDefendantsSupervised().size();i++){
								CaseAssignmentResponseEvent resp = (CaseAssignmentResponseEvent) csCalendarDisplayForm.getDefendantsSupervised().get(i);
								if(resp.getDefendantId().equalsIgnoreCase(superviseId)){
									supervisee = new SuperviseeBean();
									supervisee.setSpn(superviseId);
									//supervisee.setName(resp.getDefendantName());
									supervisee.setDefendantName(resp.getDefendantFullName());
									superviseeList.add(supervisee);
									break;
								}
							}
						}
					}
					form.setSuperviseeList(superviseeList);

			}
			
			else{
				IName defendant_name = new Name();
				if (csCalendarDisplayForm.getSearchBySPNResult() != null)
				{
					defendant_name = 
						csCalendarDisplayForm.getSearchBySPNResult().getDefendantName();
				}
				else
				{
					GetSuperviseeDataEvent supervisee_request_event = new GetSuperviseeDataEvent();
					supervisee_request_event.setSuperviseeId(tempSuperviseeId);

					SuperviseeDetailResponseEvent 
						supervisee_data	= (SuperviseeDetailResponseEvent)
								MessageUtil.postRequest(supervisee_request_event, 
											SuperviseeDetailResponseEvent.class);
					if (supervisee_data != null)
					{
						defendant_name = 
							new Name(supervisee_data.getFirstName(), 
										supervisee_data.getMiddleName(), supervisee_data.getLastName());
					}
					
				}
				
				List superviseeList = form.getSuperviseeList();
				SuperviseeBean supervisee = new SuperviseeBean();
				supervisee.setSpn(tempSuperviseeId);
				supervisee.setName(defendant_name);
				if(superviseeList == null) {
					superviseeList = new ArrayList();
				} else {
					superviseeList.clear();
				}
				superviseeList.add(supervisee);
				form.setSuperviseeList(superviseeList);
			
			}
		}
		
		form.setActivityFlow(UIConstants.CREATE);
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.createGroupOfficeVisit","createGroupOfficeVisit");

	}

}

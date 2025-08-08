/*
 * Created on Apr 28, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.cscdcalendar.fieldVisit.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.GetSuperviseeHeaderInfoEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administercaseload.reply.SuperviseeInfoResponseEvent;
import messaging.cscdcalendar.reply.CSFieldVisitResponseEvent;
import messaging.report.ChronologicalFieldVisitsPrintRequestEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.messaging.reporting.ReportRequestEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadControllerServiceNames;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.supervision.cscdcalendar.fieldVisit.FieldVisitReportBean;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;

/**
 * @author cc_vnarsingoju
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitCSFVPrintFieldReportAction extends JIMSBaseAction {

	public static final String FILEEXT = ".pdf";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.print", "printFVReport");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward printFVReport(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws Exception {
		CSCalendarFVForm fvForm = (CSCalendarFVForm) aForm;
		
		FieldVisitReportBean fvBean = new FieldVisitReportBean();

		List resultsList = new ArrayList();
		if (fvForm.getEventsList() == null) {
			if (fvForm.getCurrentEventsList() != null && fvForm.getCurrentEventsList().size() > 0) {
				fvForm.setEventDate(fvForm.getCurrentEventDate());
				fvForm.setEventsList(fvForm.getCurrentEventsList());
			}
		}
		if (fvForm.getEventsList() != null && fvForm.getEventsList().size() > 0) {
			for (int i = 0; i < fvForm.getEventsList().size(); i++) {
				CSFieldVisitResponseEvent resp = (CSFieldVisitResponseEvent) fvForm.getEventsList().get(i);
				if (!StringUtil.isEmpty(resp.getNarrative())) {
					resp.setNarrative( resp.getNarrative() );
				}
				if (!StringUtil.isEmpty(resp.getSuperviseeId())) {
					String supId = resp.getSuperviseeId();
					GetSuperviseeHeaderInfoEvent getEvent = (GetSuperviseeHeaderInfoEvent) EventFactory
							.getInstance(CaseloadControllerServiceNames.GETSUPERVISEEHEADERINFO);
					getEvent.setDefendantId(supId);
			   SuperviseeInfoResponseEvent sprResponse = (SuperviseeInfoResponseEvent) MessageUtil.postRequest(getEvent,SuperviseeInfoResponseEvent.class);
					if (sprResponse != null) {
						resp.setLevelOfSupervision(sprResponse.getSupervisionLevel());
						if( StringUtils.isNotEmpty( sprResponse.getPositionId()))
						{
							GetLightCSCDStaffForUserEvent reqEvt = new GetLightCSCDStaffForUserEvent();
							reqEvt.setStaffPositionId(sprResponse.getPositionId());
							LightCSCDStaffResponseEvent staffPosRespEvt = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(reqEvt, LightCSCDStaffResponseEvent.class);
							resp.setProbationOfficeInd(staffPosRespEvt.getStaffPositionName());
						}
						
					}

				}
				resultsList.add(resp);
			}
		}

		Collection events = (Collection) resultsList;

		fvBean.setEvents(events);
		if (fvForm.getCurrentItinerary() != null) {
			if (fvForm.getCurrentItinerary().getMileageIn() != null) {
				fvBean.setStartMileage(fvForm.getCurrentItinerary().getMileageIn());
			}
			if (fvForm.getCurrentItinerary().getMileageOut() != null) {
				fvBean.setEndMileage(fvForm.getCurrentItinerary().getMileageOut());
			}
			if (fvForm.getCurrentItinerary().getEventDate() != null) {
				fvBean.setFieldVisitDate(DateUtil.dateToString(fvForm.getCurrentItinerary().getEventDate(), "MM/dd/yyyy"));
			}
			if (fvForm.getCurrentItinerary().getMobilePager() != null) {
				fvBean.setCellPhone(fvForm.getCurrentItinerary().getMobilePager().getPhoneNumberFormat());
			}
		}

		ReportResponseEvent aRespEvent = null;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		ReportRequestEvent fvReportEvent = new ChronologicalFieldVisitsPrintRequestEvent();
		fvReportEvent.setReportName("REPORTING::CHRONOLOGICAL FIELDVISIT REPORT");
		fvReportEvent.addDataObject(fvBean);

		dispatch.postEvent(fvReportEvent);
		CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();
		aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(compResponse, ReportResponseEvent.class);

		if (aRespEvent == null) {
			ReturnException returnException = (ReturnException) MessageUtil.filterComposite(compResponse, ReturnException.class);
			throw returnException;
		} else {
			aResponse.setContentType("application/x-file-download");
			aResponse.setHeader("Content-disposition", "attachment; filename=" + aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/") + 1) + FILEEXT);
			aResponse.setHeader("Cache-Control", "must-revalidate");
			aResponse.setContentLength(aRespEvent.getContent().length);
			aResponse.resetBuffer();
			OutputStream os = aResponse.getOutputStream();
			os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
			os.flush();
			os.close();
		}
		return (null);

	}
	
}

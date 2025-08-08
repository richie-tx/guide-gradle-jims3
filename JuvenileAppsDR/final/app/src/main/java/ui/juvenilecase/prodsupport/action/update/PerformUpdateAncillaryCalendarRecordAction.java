package ui.juvenilecase.prodsupport.action.update;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.districtCourtHearings.UpdateJJSCLAncillarySettingEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import naming.JuvenileCourtHearingControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.ProdSupportForm;

public class PerformUpdateAncillaryCalendarRecordAction extends JIMSBaseAction
{

    @Override
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.updateRecord", "submitAncillaryCalendarUpdate");
	
	
    }
    
    public ActionForward submitAncillaryCalendarUpdate(ActionMapping mapping, ActionForm aForm, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	UpdateJJSCLAncillarySettingEvent updateEvent = (UpdateJJSCLAncillarySettingEvent)
		EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLANCILLARYSETTING);
	DocketEventResponseEvent ancillaryRecord = form.getAncillaryCalendarRecord();
	updateEvent.setDocketEventId(ancillaryRecord.getDocketEventId());
	updateEvent.setCourtId(ancillaryRecord.getCourt());
	updateEvent.setCourtDate(DateUtil.stringToDate(ancillaryRecord.getCourtDate(), DateUtil.DATE_FMT_1));
	updateEvent.setCourtTime(ancillaryRecord.getCourtTime());
	updateEvent.setHearingResult(ancillaryRecord.getCourtResult());
	updateEvent.setHearingDisposition(ancillaryRecord.getDisposition());
	updateEvent.setResetHearingType(ancillaryRecord.getResetHearingType());
	updateEvent.setPetitionNum(ancillaryRecord.getPetitionNumber());
	updateEvent.setSettingReason(ancillaryRecord.getSettingReason());
	updateEvent.setTypeCase(ancillaryRecord.getTypeCase());
	updateEvent.setFilingDate(DateUtil.stringToDate(ancillaryRecord.getFilingDate(), DateUtil.DATE_FMT_1));
	updateEvent.setBarNum(ancillaryRecord.getBarNum());
	updateEvent.setAttorneyConnection(ancillaryRecord.getAttorneyConnection());
	updateEvent.setAttorneyName(ancillaryRecord.getAttorneyName());
	updateEvent.setRespondentName(ancillaryRecord.getRespondantName());
	dispatch.postEvent(updateEvent);
	return  mapping.findForward("success");
    }
}

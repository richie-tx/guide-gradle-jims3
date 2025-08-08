package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetAttorneyNameAndBarNumEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.districtCourtHearings.DeleteJJSCLCourtSettingEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import messaging.productionsupport.GetProductionSupportCourtRecordsEvent;
import messaging.productionsupport.UpdateJJSCLCourtEvent;
import messaging.productionsupport.UpdateProductionSupportDistrictCourtReferralEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileReferralEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.commons.lang.StringUtils;

import pd.juvenilecase.referral.JJSReferral;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;


/**
 * @author apillai
 */

public class PerformDeleteDistrictCourtCalendarRecordAction extends JIMSBaseAction
{
    @Override
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.deleteRecord", "submitCourtRecordDelete");
	keyMap.put("button.refresh", "refresh");
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
    }

    private Logger log = Logger.getLogger("PerformUpdateDistrictCourtCalendarRecordAction");

    public ActionForward submitCourtRecordDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	//chnage the code to delete 
	ProdSupportForm regform = (ProdSupportForm) form;
	String logonid = SecurityUIHelper.getLogonId();
	String actionFwd = "";
	DocketEventResponseEvent record = retrieveRecord(regform);

	if (record == null)
	{
	    regform.setMsg("PerformUpdateDistrictCourtCalendarRecordAction.java (73) - Could not retrieve record.");
	    return (mapping.findForward("error"));
	}
	if (record.getJuvenileNumber() != null && !record.getJuvenileNumber().isEmpty())
	{
	    DeleteJJSCLCourtSettingEvent deleteCourtSettingEvt = (DeleteJJSCLCourtSettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.DELETEJJSCLCOURTSETTING);
	    deleteCourtSettingEvt.setDocketEventId(record.getDocketEventId());	    

	    CompositeResponse compositeResponse = MessageUtil.postRequest(deleteCourtSettingEvt);
	    DocketEventResponseEvent resp = (DocketEventResponseEvent) MessageUtil.filterComposite(compositeResponse, DocketEventResponseEvent.class);
	    Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	    if (errorResponse != null)
	    {
		actionFwd = "error"; 
		ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
		try
		{
		    handleFatalUnexpectedException(myEvt.getMessage());
		}
		catch (GeneralFeedbackMessageException e)
		{
		    e.printStackTrace();
		}
	    }
	    else
		actionFwd = "success";	    	
	}
	
	//actionFwd = "success";
	return mapping.findForward(actionFwd);

    }

    private static JJSReferral getJuvenileReferralDetails(GetJJSReferralEvent refEvent)
    {
	JJSReferral referralResp = null;
	//List<JJSReferralResponseEvent> referralResps = MessageUtil.postRequestListFilter(refEvent, JJSReferralResponseEvent.class);
	Iterator<JJSReferral> referralRespItr = JJSReferral.findAll(refEvent);
	if (referralRespItr.hasNext())
	{
	    referralResp = referralRespItr.next();
	}
	return referralResp;
    }

    

   
    

    
    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	form.setAttorneyName(UIConstants.EMPTY_STRING);
	form.setAttorneyDataList(new ArrayList<AttorneyNameAndAddressResponseEvent>());
	return aMapping.findForward("attorneySearchSuccess");
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	String from = nvl( aRequest.getParameter("from") );
	String forward ="";

	
	
	if ( from.equals("updateAncillaryCalendar")) {
	    forward = UIConstants.ANCILLARYCALENDAR_UPDATE_DISPLAY;
	} else {
	    forward = UIConstants.DISTRICTCOURT_UPDATE_DISPLAY;
	}
	return aMapping.findForward(forward);
    }

    private String padBarNumber(String barNumber)
    {

	String barNum = "";
	if (barNumber != null)
	{

	    barNum = barNumber;
	    if (barNum.length() < 8)
	    {
		StringBuffer sb = new StringBuffer(barNumber);
		for (int i = 0; i < 8 - barNumber.length(); i++)
		{
		    sb.insert(0, "0");
		}
		barNum = sb.toString();
	    }
	}
	return barNum;
    }

    private DocketEventResponseEvent retrieveRecord(ProdSupportForm regform)
    {

	ArrayList juvDistCourtRecords = regform.getJuvDistCourtRecords();
	DocketEventResponseEvent record = null;

	Iterator iter = juvDistCourtRecords.iterator();
	if (iter.hasNext())
	{
	    record = (DocketEventResponseEvent) iter.next();
	}

	return record;
    }
   

    private String nvl(String value){
	return (value != null && value.length() > 0) ? value : "";
    }

    
}

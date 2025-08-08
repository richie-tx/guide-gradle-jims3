package ui.juvenilecase.prodsupport.action.delete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import mojo.km.persistence.Home;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

public class PerformDeleteDetentionCourtRecordAction extends JIMSBaseAction
{
    @Override
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.deleteRecord", "submitCourtRecordDelete");	
	keyMap.put("button.refresh", "refresh");
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
    }

    private Logger log = Logger.getLogger("PerformDeleteDetentionCourtRecordAction");

    public ActionForward submitCourtRecordDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	ProdSupportForm regform = (ProdSupportForm) form;
	String logonid = SecurityUIHelper.getLogonId();
	String actionFwd = "";

	DocketEventResponseEvent record = retrieveRecord(regform);

	if (record == null)
	{
	    regform.setMsg("PerformDeleteDetentionCourtRecordAction.java - Could not retrieve record. Docket record is null.");
	    return (mapping.findForward("error"));
	}
	
	if (record.getDocketEventId() == null || record.getDocketEventId().equals(""))
	{
	    regform.setMsg("PerformDeleteDetentionCourtRecordAction.java - Could not delete record. docketEventId is null");
	    return (mapping.findForward("error"));
	}
	
	JJSCLDetention clDetention = JJSCLDetention.find(record.getDocketEventId());
	if (clDetention!= null){
	    clDetention.delete();
	    new Home().bind(clDetention);
	    actionFwd = "success";
	    regform.setDetentionId("");
	}
	
	
	return mapping.findForward(actionFwd);

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
	return aMapping.findForward(UIConstants.DISTRICTCOURT_UPDATE_DISPLAY);
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

	ArrayList juvDistCourtRecords = regform.getJuvDetCourtRecords();
	DocketEventResponseEvent record = null;

	Iterator iter = juvDistCourtRecords.iterator();
	if (iter.hasNext())
	{
	    record = (DocketEventResponseEvent) iter.next();
	}

	return record;
    }

}

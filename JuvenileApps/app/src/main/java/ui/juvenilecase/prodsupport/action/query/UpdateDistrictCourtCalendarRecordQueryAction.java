package ui.juvenilecase.prodsupport.action.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByJuvNumEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByJuvNumEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.productionsupport.GetProductionSupportCourtRecordsEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProductionSupportControllerServiceNames;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.codetable.SimpleCodeTableHelper;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.ProdSupportForm;

//import mojo.km.utilities.DateUtil;

/**
 * @author aPillai
 */

public class UpdateDistrictCourtCalendarRecordQueryAction extends Action
{

    /* protected void addButtonMapping(Map keyMap)
     {	
    keyMap.put("button.submit", "submit");
    keyMap.put("button.cancel", "cancel");
    keyMap.put("button.refresh", "refresh");
    keyMap.put("button.back", "back");
      }*/
    private Logger log = Logger.getLogger("UpdateDistrictCourtCalendarRecordQueryAction");

    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {

	ProdSupportForm regform = (ProdSupportForm) aForm;
	String forward = "success";
	String juvenileNum, courtDt, reffNum;//courtId;
	ArrayList<DocketEventResponseEvent> juvDistCourtRecords = regform.getJuvDistCourtRecords();

	/** Check for initial load of this page **/
	String clrChk = aRequest.getParameter("clr");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y"))
	{
	    saveToken(aRequest);
	    regform.clearAllResults();
	    regform.setMsg("");
	    return aMapping.findForward("error");
	}
	
	
	juvenileNum = regform.getJuvenileId();
	courtDt = regform.getCourtDate();
	//courtId = regform.getCourtId();
	reffNum=regform.getReferralNum();

	String docketId = regform.getDetentionId();

	if (juvenileNum == null || juvenileNum.equals(""))
	{
	    /*regform.setMsg("Please enter a Juvenile ID.");
	    return (aMapping.findForward("error"));*/
	    juvenileNum = aRequest.getParameter("juvenileNum");
	    courtDt = aRequest.getParameter("courtDt");
	    //courtId = aRequest.getParameter("courtId");
	    reffNum = aRequest.getParameter("referralNum");

	}
	if (docketId == null || docketId.equals("")) {
	    docketId = aRequest.getParameter("docketId");
	}

	if (regform.getJuvcourtDocketsCount() > 1
		&& ( docketId == null 
			|| docketId.equals("") ) )
	{
	    regform.setJuvDistCourtRecords( regform.getOriginalJuvDistCourtRecords() );
	    forward = "listsuccess";
	    return aMapping.findForward(forward);
	}
	
	if (isTokenValid(aRequest)||aRequest.getParameter("docketId")!=null)
	{

	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    if (docketId == null || docketId.equals(""))
	    {
		if (juvenileNum != null && !juvenileNum.isEmpty())
		{
		    //GET Juvenile Name from Juvenile Number.
		    GetJuvenileProfileMainEvent getJuvProfileEvent = new GetJuvenileProfileMainEvent();

		    getJuvProfileEvent.setJuvenileNum(juvenileNum);
		    CompositeResponse response = MessageUtil.postRequest(getJuvProfileEvent);
		    JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
		    if (juvProfileRE != null)
		    {
			Name myName = new Name();
			myName.setFirstName(juvProfileRE.getFirstName());
			myName.setLastName(juvProfileRE.getLastName());
			myName.setMiddleName(juvProfileRE.getMiddleName());
			regform.setJuvenileName(myName.getFormattedName().trim());
			regform.setRectype(juvProfileRE.getRecType());
		    }

		    //***************************************** NO ANCILLARY RECORDS BY Juvenile Number*********************************************/	

		    //*****************************************COURT RECORDS *********************************************/	

		    GetProductionSupportCourtRecordsEvent jjsCLCrtEvent = (GetProductionSupportCourtRecordsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCOURTRECORDS);
		    //jjsCLCrtEvent.setCourtId(courtId);
		    jjsCLCrtEvent.setReferralNum(reffNum);

		    if (courtDt != null && !courtDt.isEmpty())
		    {
			Date cortDate = DateUtil.stringToDate(courtDt, DateUtil.DATE_FMT_1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String formattedCourtDate = sdf.format(cortDate);
			jjsCLCrtEvent.setCourtDate(formattedCourtDate);
		    }

		    jjsCLCrtEvent.setJuvenileNumber(juvenileNum);

		    dispatch.postEvent(jjsCLCrtEvent);
		    CompositeResponse resp = (CompositeResponse) dispatch.getReply();
		    Map<String, DocketEventResponseEvent> docketsMap = new HashMap<String, DocketEventResponseEvent>();
		    List<DocketEventResponseEvent> dktRespEvts = MessageUtil.compositeToList(resp, DocketEventResponseEvent.class);
		    juvDistCourtRecords = null;
		    juvDistCourtRecords = (ArrayList) MessageUtil.compositeToCollection(resp, DocketEventResponseEvent.class);

		    if (juvDistCourtRecords != null && juvDistCourtRecords.size() > 1)
		    {
			Collections.sort((List<DocketEventResponseEvent>) juvDistCourtRecords, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
			    @Override
			    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
			    {
				return Integer.valueOf(evt2.getReferralNum()).compareTo(Integer.valueOf(evt1.getReferralNum()));
			    }
			}));

		    }
		    //setting the searchresultmap for accessing it later
		    if (dktRespEvts != null && !dktRespEvts.isEmpty())
		    {
			Iterator<DocketEventResponseEvent> docketIter = dktRespEvts.iterator();

			while (docketIter.hasNext())
			{

			    DocketEventResponseEvent docRespEvent = docketIter.next();
			    docketsMap.put(docRespEvent.getDocketEventId(), docRespEvent);
			}
			regform.setDktSearchResultsMap(docketsMap);
			regform.setUpdateDocketMap(docketsMap);
		    }
		    regform.setJuvcourtDocketsCount(juvDistCourtRecords.size());
		    regform.setJuvDistCourtRecords(juvDistCourtRecords);

		    if (regform.getJuvcourtDocketsCount() == 0)
		    {
			regform.setJuvenileName("");
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "NO 'DISTRICT COURT RECORD' FOUND"));
			saveErrors(aRequest, errors);
			//return aMapping.findForward("error");
			//forward = "listsuccess";
			forward = "error";
		    }
		    else
		    {
			if (regform.getJuvcourtDocketsCount() > 1)
			{
			    regform.setMsg(regform.getJuvcourtDocketsCount() + " RECORD(S) FOUND");
			    forward = "listsuccess";
			}
			else
			    forward = "success";
			/** Populate Drop-downs **/

			List<JuvenileDispositionCodeResponseEvent> juvenileDispositions = JuvenileDistrictCourtHelper.getSortedCourtDecisions();
			regform.setCourtDecisionsResponses(juvenileDispositions);
			regform.setDetentionHearingResults(juvenileDispositions);
			regform.setCourtHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes("COURT"));			
			//regform.setCourtTypes(CodeHelper.getCourtCodes(true));
			resetToken(aRequest);
		    }

		}
	    }
	    else
	    {
		if (juvDistCourtRecords != null && juvDistCourtRecords.size() > 1)
		{
		    regform.setOriginalJuvDistCourtRecords(juvDistCourtRecords);
		    ArrayList tempjuvDistCourtRecords = new ArrayList();
		    DocketEventResponseEvent dktResp = null;
		    for (int i = 0; i < juvDistCourtRecords.size(); i++)
		    {
			DocketEventResponseEvent resp = juvDistCourtRecords.get(i);
			if (docketId.equals(resp.getDocketEventId()))
			{
			    dktResp = juvDistCourtRecords.get(i);
			    tempjuvDistCourtRecords.add(dktResp);
			    break;
			}
		    }
		    regform.setJuvDistCourtRecords(tempjuvDistCourtRecords);
		    forward = "success";
		   /* if(aRequest.getParameter("docketId")!=null)
			aRequest.setAttribute("docketId", null);*/
			
		    /** Populate Drop-downs **/

		    List<JuvenileDispositionCodeResponseEvent> juvenileDispositions = JuvenileDistrictCourtHelper.getSortedCourtDecisions();
		    regform.setCourtDecisionsResponses(juvenileDispositions);
		    regform.setDetentionHearingResults(juvenileDispositions);
		    regform.setCourtHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes("COURT"));
		    //regform.setCourtTypes(CodeHelper.getCourtCodes(true));
		    resetToken(aRequest);
		}

	    }
	    //resetToken(aRequest);
	    regform.setHiddenForward(forward);
	}
	else
	{
	    forward = regform.getHiddenForward();
	    //	    String dktId = regform.getDetentionId();
	    //	    if(!(dktId.equals(""))&& dktId!=null)
	    //		saveToken(aRequest);	    
	}
	return aMapping.findForward(forward);//(UIConstants.JUVENILE_SETTING_SUCCESS)
    }

}

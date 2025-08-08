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
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCourtDecisionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByJuvNumEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByJuvNumEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.GetJuvenileStatusEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.productionsupport.GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent;
import messaging.productionsupport.GetProductionSupportCourtRecordsEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
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
import pd.juvenile.JuvenileBuilder;
import pd.juvenile.JuvenileStatus;
import pd.juvenilecase.JuvenileCaseHelper;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;


public class DeleteDetentionCourtRecordQueryAction extends Action
{

    private Logger log = Logger.getLogger("DeleteDetentionCourtRecordQueryAction");

    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {

	ProdSupportForm regform = (ProdSupportForm) aForm;
	String forward = "success";
	ArrayList<DocketEventResponseEvent> juvDetCourtRecords = regform.getJuvDetCourtRecords();

	/** Check for initial load of this page **/
	String clrChk = aRequest.getParameter("clr");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y"))
	{
	    regform.clearAllResults();
	    regform.setMsg("");
	    //regform.setReferralId("");
	    return aMapping.findForward("error");
	}

	String juvenileNum = regform.getJuvenileId().trim();
	String reffNum = regform.getReferralId();
	String chainNum = regform.getChainNum();
	String courtDt = regform.getCourtDate();
	String docketId = regform.getDetentionId();
	
	if (docketId == null || docketId.equals("")){
	    docketId = aRequest.getParameter("docketId");
	}
		
	 if ( regform.getOriginalJuvDetCourtRecords() != null
		    && regform.getOriginalJuvDetCourtRecords().size()  > 1
		    && ( docketId == null || docketId.equals("") ) ) {
		
	     regform.setJuvcourtDocketsCount( regform.getOriginalJuvDetCourtRecords().size() );
	     regform.setJuvDetCourtRecords(regform.getOriginalJuvDetCourtRecords());
	     regform.setMsg(regform.getJuvcourtDocketsCount() + " RECORD(S) FOUND");
	     forward = "listsuccess";
	     return aMapping.findForward(forward);
	 }

	//Log the query attempt
	log.info("[" + new Date() + "] ProdSupport (" + SecurityUIHelper.getLogonId().toUpperCase() + ") - Juvenile ID: " + juvenileNum + "");
	//Verify valid JuvenileID		
	JJSJuvenileResponseEvent juvenile = retrieveJuvenile(juvenileNum);
	if (juvenile == null || juvenile.getJuvenileNum() == null || juvenile.getJuvenileNum().equals(""))
	{
	    regform.setToJuvenileId("");
	    regform.setMsg("Juvenile Number is invalid.  Please retry search.");
	    return aMapping.findForward("error");
	}

	regform.clear();
	// Set juvenile 
	Name name = new Name(juvenile.getFirstName(), juvenile.getMiddleName(), juvenile.getLastName());

	regform.setJuvenileName(name.getFormattedName());
	regform.setJuvenileSsn(juvenile.getJuvenileSsn());
	regform.setBirthDate(juvenile.getBirthDate());

	// master status from jjs_ms_juvenile
	String statusDesc = JuvenileBuilder.getMasterStatusDesc(juvenile.getStatusId());
	regform.setStatusId(statusDesc);
	//
	
	regform.setRectype(juvenile.getRectype());
	
	
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	if (docketId == null || docketId.equals(""))
	{
	   
	    
	    if (juvenileNum != null && !juvenileNum.isEmpty())
	    {

		//*****************************************COURT RECORDS *********************************************/	

		GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent jjsCLCrtEvent = (GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETJJSCLDETENTIONBYJUVNUMREFNUMCHAINNUMCOURTDATE);
		jjsCLCrtEvent.setJuvenileNumber(juvenileNum);
		jjsCLCrtEvent.setReferralNumber(reffNum);
		jjsCLCrtEvent.setChainNumber(chainNum);
		if (courtDt != null && !courtDt.isEmpty())
		{
		    Date cortDate = DateUtil.stringToDate(courtDt, DateUtil.DATE_FMT_1);
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String formattedCourtDate = sdf.format(cortDate);
		    jjsCLCrtEvent.setCourtDate(formattedCourtDate);
		}

		dispatch.postEvent(jjsCLCrtEvent);
		CompositeResponse resp = (CompositeResponse) dispatch.getReply();
		Map<String, DocketEventResponseEvent> docketsMap = new HashMap<String, DocketEventResponseEvent>();
		List<DocketEventResponseEvent> dktRespEvts = MessageUtil.compositeToList(resp, DocketEventResponseEvent.class);
		juvDetCourtRecords = null;
		juvDetCourtRecords = (ArrayList) MessageUtil.compositeToCollection(resp, DocketEventResponseEvent.class);

		if (juvDetCourtRecords != null && juvDetCourtRecords.size() > 1)
		{
		    Collections.sort((List<DocketEventResponseEvent>) juvDetCourtRecords, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
			@Override
			public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
			{
			    if (evt1.getReferralNum() != null && evt2.getReferralNum() != null)
				    return evt2.getReferralNum().compareTo(evt1.getReferralNum());
				else
				    return -1;
			}
		    }));

		}
		
		regform.setJuvcourtDocketsCount(juvDetCourtRecords.size());
		regform.setJuvDetCourtRecords(juvDetCourtRecords);

		if (regform.getJuvcourtDocketsCount() == 0)
		{
		    regform.setJuvenileName("");
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "NO 'DETENTION COURT RECORD' FOUND"));
		    saveErrors(aRequest, errors);		    
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

		    List<JuvenileCourtDecisionCodeResponseEvent> hearingDecisionResults = JuvenileCaseHelper.getAllCourtDecisions();		   
		    regform.setHearingDecisionResults(hearingDecisionResults);
		    regform.setCourtHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes("COURT"));
		    loadBookingReferrals(regform);
		}

	    }
	}
	else
	{
	    if (juvDetCourtRecords != null && juvDetCourtRecords.size() > 1)
	    {
		ArrayList tempjuvDistCourtRecords = new ArrayList();
		DocketEventResponseEvent dktResp = null;
		for (int i = 0; i < juvDetCourtRecords.size(); i++)
		{
		    DocketEventResponseEvent resp = juvDetCourtRecords.get(i);
		    if (docketId.equals(resp.getDocketEventId()))
		    {
			dktResp = juvDetCourtRecords.get(i);
			tempjuvDistCourtRecords.add(dktResp);
			juvDetCourtRecords.remove(i);
			break;
		    }
		}
		regform.setJuvDetCourtRecords(tempjuvDistCourtRecords);
		regform.setOriginalJuvDetCourtRecords( juvDetCourtRecords );
		forward = "success";
		/** Populate Drop-downs **/

		List<JuvenileCourtDecisionCodeResponseEvent> hearingDecisionResults= JuvenileCaseHelper.getAllCourtDecisions();
		regform.setHearingDecisionResults(hearingDecisionResults);
		regform.setCourtHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes("COURT"));
		loadBookingReferrals(regform);
	    }

	}
	return aMapping.findForward(forward);

    }
    private void loadBookingReferrals(ProdSupportForm regform)
    {

	ArrayList referralList = new ArrayList();

	GetJuvenileProfileReferralListEvent event = (GetJuvenileProfileReferralListEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLIST);
	event.setJuvenileNum(regform.getJuvenileId());

	List referralResp = MessageUtil.postRequestListFilter(event, JuvenileProfileReferralListResponseEvent.class);

	if( referralResp.size() > 0 ){
	    
	    Collections.sort((List<JuvenileProfileReferralListResponseEvent>) referralResp, new Comparator<JuvenileProfileReferralListResponseEvent>() {
		    @Override
		    public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
		    {
			if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
			    return evt2.getReferralNumber().compareTo(evt1.getReferralNumber());
			else
			    return -1;
		    }
		});
		Map refMap = new TreeMap();
		Iterator refIter = referralResp.iterator();
		while (refIter.hasNext())
		{

		    JuvenileProfileReferralListResponseEvent refResp = (JuvenileProfileReferralListResponseEvent) refIter.next();
		    refMap.put(refResp.getReferralNumber(), refResp);

		}

		Iterator iter = refMap.entrySet().iterator();
		while (iter.hasNext())
		{
		    Map.Entry pair = (Map.Entry) iter.next();
		    CodeResponseEvent obj = new CodeResponseEvent();
		    obj.setDescription(pair.getKey().toString());
		    referralList.add(obj);
		    iter.remove();
		}
	}	

	regform.setBookingReferrals(referralList);

    }
    /**
     * returns juvenile number if it finds it
     * 
     * @param juvenileNumber
     * @return
     */
    private JJSJuvenileResponseEvent retrieveJuvenile(String juvenileNumber)
    {

	/**
	 * Search for jcjuvenile.
	 */
	GetJJSJuvenileEvent request = (GetJJSJuvenileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILE);
	request.setJuvenileNum(juvenileNumber);

	JJSJuvenileResponseEvent juvenileResp = (JJSJuvenileResponseEvent) MessageUtil.postRequest(request, JJSJuvenileResponseEvent.class);

	return juvenileResp;
    }

    /**
     * @param referralNum
     * @return
     */
    
    
    private String nvl(String value, String alt) {
	return ( value != null && ( value.length()  > 0 ) ) ? value : alt;
    }

}

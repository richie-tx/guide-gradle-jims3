package ui.juvenilecase.districtCourtHearings.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByJuvNumEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByJuvNumEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenilecase.JJSLastAttorney;

import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

/**
 * 
 * @author sthyagarajan
 *
 */
public class SubmitJuvenileDistrictCourtJuvenileSettingSearch extends JIMSBaseAction
{

    
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.submit", "submit");
    }

    /**
     * Submit
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.setDktSearchResults(Collections.EMPTY_LIST); //empty the list
	
	String juvenileNum = courtHearingForm.getJuvenileNumber();
	
	List<DocketEventResponseEvent> dockets = new ArrayList<DocketEventResponseEvent>();

	if (juvenileNum!=null && !juvenileNum.isEmpty())
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
		courtHearingForm.setJuvenileName(myName.getFormattedName().trim());
	    }
	    //***************************************** NO ANCILLARY RECORDS BY Juvenile Number*********************************************//	
	
	    //*****************************************COURT RECORDS *********************************************//	
	    // JJSCOURT : only Juvenile Number
	  
	    if (juvenileNum!=null && !juvenileNum.isEmpty()&& courtHearingForm.getBarNumber() != null && courtHearingForm.getBarNumber().isEmpty() && courtHearingForm.getCourtDate()!=null && courtHearingForm.getCourtDate().isEmpty() )
	    {		
		GetJJSCLCourtByJuvNumEvent jjsCLCrtEvent = (GetJJSCLCourtByJuvNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYJUVNUM);
		jjsCLCrtEvent.setJuvenileNumber(courtHearingForm.getJuvenileNumber());
		dispatch.postEvent(jjsCLCrtEvent);
		CompositeResponse resp = (CompositeResponse) dispatch.getReply();		
		List<DocketEventResponseEvent> crtDktRespEvts = MessageUtil.compositeToList(resp, DocketEventResponseEvent.class);
		////task 146725
		//sort order by oid and chainnumber
		Date fromDate=DateUtil.stringToDate(DateUtil.dateToString( DateUtil.getCurrentDate(), DateUtil.DATE_FMT_2),DateUtil.DATE_FMT_2);
		Collections.sort((List<DocketEventResponseEvent>) crtDktRespEvts, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
			@Override
			public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
			{
			    return new CompareToBuilder().append(evt1.getChainNumber(), evt2.getChainNumber())
					.append(evt1.getDocketEventId(), evt2.getDocketEventId()).toComparison();
			 }
		    }));
		
		String prevChain="";
		//
		if (crtDktRespEvts != null && !crtDktRespEvts.isEmpty())
		{
		    Iterator<DocketEventResponseEvent> dktRespEvtsItr = crtDktRespEvts.iterator();

		    while (dktRespEvtsItr.hasNext())
		    {
			DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) dktRespEvtsItr.next();
			if (docRespEvent != null)
			{
			    ////task 146725
			    if(docRespEvent.getChainNumber().equalsIgnoreCase(prevChain))
				continue;
			    else if(fromDate.compareTo(DateUtil.stringToDate(docRespEvent.getCourtDate(), DateUtil.DATE_FMT_1))<=0)// || fromDate.equals(DateUtil.stringToDate(docRespEvent.getCourtDate(), DateUtil.DATE_FMT_1)))
				{
				    docRespEvent.setCourtTime(JuvenileFacilityHelper.stripColonInDate(docRespEvent.getCourtTime()));
				    dockets.add(docRespEvent);
				}			    
			}
			//to set the previous chain
			prevChain=docRespEvent.getChainNumber();
		    }
		}
	    }
	    //*****************************************DETENTION RECORDS *********************************************//
	    //GET JJSCLDETENTION RECORDS
	   // only juv num
	    if (juvenileNum != null && !juvenileNum.isEmpty() && courtHearingForm.getBarNumber() != null && courtHearingForm.getBarNumber().isEmpty() && courtHearingForm.getCourtDate().isEmpty())
	    {
		GetJJSCLDetentionByJuvNumEvent jjsdetnCrtEvent = (GetJJSCLDetentionByJuvNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYJUVNUM);
		 jjsdetnCrtEvent.setJuvenileNumber(courtHearingForm.getJuvenileNumber());
		dispatch.postEvent(jjsdetnCrtEvent);
		CompositeResponse clDetnResp = (CompositeResponse) dispatch.getReply();
		List<DocketEventResponseEvent> clDetnDktRespEvts = MessageUtil.compositeToList(clDetnResp, DocketEventResponseEvent.class);
		////task 146725
				//sort order by oid and chainnumber
		Date fromDate=DateUtil.stringToDate(DateUtil.dateToString( DateUtil.getCurrentDate(), DateUtil.DATE_FMT_2),DateUtil.DATE_FMT_2);
		Collections.sort((List<DocketEventResponseEvent>) clDetnDktRespEvts, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
			@Override
			public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
			{
			    return new CompareToBuilder().append(evt1.getChainNumber(), evt2.getChainNumber())
					.append(evt1.getDocketEventId(), evt2.getDocketEventId()).toComparison();
					 }
			}));
				
		String prevChain="";
				//
		if (clDetnDktRespEvts != null && !clDetnDktRespEvts.isEmpty())
		{
		    Iterator<DocketEventResponseEvent> clDetnDktRespItr = clDetnDktRespEvts.iterator();
		    while (clDetnDktRespItr.hasNext())
		    {
			DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) clDetnDktRespItr.next();
			if (docRespEvent != null)
			{
			////task 146725
			    if(docRespEvent.getChainNumber().equalsIgnoreCase(prevChain))
				continue;
			    else if(fromDate.compareTo(DateUtil.stringToDate(docRespEvent.getCourtDate(), DateUtil.DATE_FMT_1))<=0&&(docRespEvent.getDisposition()==null||docRespEvent.getDisposition().isEmpty()))// || fromDate.equals(DateUtil.stringToDate(docRespEvent.getCourtDate(), DateUtil.DATE_FMT_1)))
				{
				    docRespEvent.setCourtTime(JuvenileFacilityHelper.stripColonInDate(docRespEvent.getCourtTime()));
				  //set Court Date and time
				    docRespEvent.setCourtTime(JuvenileFacilityHelper.stripColonInDate(docRespEvent.getCourtTime()));
				    //bug #69379
				    if (docRespEvent.getPetitionNumber()==null || docRespEvent.getPetitionNumber().equals(""))
				    {
					docRespEvent.setPetitionNumber("J0" + docRespEvent.getJuvenileNumber());
				    }			
				    dockets.add(docRespEvent);
				 }			    
			}
			//to set the previous chain
			prevChain=docRespEvent.getChainNumber();
		    }
		}
	    }
	    Collections.sort((List<DocketEventResponseEvent>) dockets, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1,
			DocketEventResponseEvent evt2)
		{
			//Bug #69311 - string comparison of date was not working
			 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			 Date date1 = new Date();
				Date date2 = new Date();
			 try {
				 date1 = sdf.parse(evt1.getCourtDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				 date2 = sdf.parse(evt2.getCourtDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return new CompareToBuilder().append(date1, date2).append(evt1.getCourtTime(), evt2.getCourtTime()).append(evt1.getPetitionNumber(), evt2.getPetitionNumber()).toComparison();

		}
	    }));
	    courtHearingForm.setDktSearchResults(dockets);
	    courtHearingForm.setSearchResultSize(dockets.size());
	    courtHearingForm.setShowmessage(true);
	  //add last attorney 	 
	    JJSLastAttorney lastAttorney = JuvenileDistrictCourtHelper.getLastAttorneyInfo(courtHearingForm.getJuvenileNumber());
	    courtHearingForm.setJjslastAttorney(lastAttorney);
	    String createdate;
	    if(lastAttorney!=null)
	    {
        	    if(lastAttorney.getCreateTimestamp()==null)
        		createdate="";
        	    else
        		createdate=lastAttorney.getCreateTimestamp().toString();
	    }
	    else
		createdate="";
	    //if (courtHearingForm.getDktSearchResults() == null || (courtHearingForm.getDktSearchResults() != null && courtHearingForm.getDktSearchResults().isEmpty()))
	    if (dockets.size() == 0 && (lastAttorney==null||createdate.isEmpty()))//||lastAttorney.getAtyBarNum()==null
	    {
	    	courtHearingForm.setJuvenileName("");
	    	courtHearingForm.setShowmessage(false);
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Record Found For Supplied Inquiry Fields"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    
	}
	return aMapping.findForward(UIConstants.JUVENILE_SETTING_SUCCESS);
    }
}

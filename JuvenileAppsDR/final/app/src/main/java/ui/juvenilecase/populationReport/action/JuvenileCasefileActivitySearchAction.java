package ui.juvenilecase.populationReport.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.casefile.GetCasefileClosingDetailsEvent;
import messaging.codetable.GetJuvenileActivityTypeCodesEvent;
import messaging.codetable.criminal.reply.JuvenileActivityTypeCodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenile.reply.JuvenileProfilesResponseEvent;
import messaging.juvenilecase.GetJuvenileByCasefileActivityEvent;
import messaging.juvenilecase.GetJuvenileFacilityHistoricalReceiptsEvent;
import messaging.juvenilecase.reply.JuvenileCasefileActivityResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileActivityStatisticsResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilitiesCurrPopResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHistoricalReceiptsResponseEvent;
import messaging.juvenilecase.reply.SearchResultsCountResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.AttorneysettingsSearchResultsReportBean;
import ui.juvenilecase.JuvenileCasefileActivitySearchResultsReportBean;
import ui.juvenilecase.activities.form.ActivitiesForm;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.populationReport.form.JuvenileFacilityReceiptForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class JuvenileCasefileActivitySearchAction extends JIMSBaseAction
{
	/**
	 * @roseuid 42AF409F01B5
	 */
	public JuvenileCasefileActivitySearchAction()
	{
	}

	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected void addButtonMapping(Map buttonMap)
	{
		buttonMap.put("button.submit", "submitSearch");
		buttonMap.put("button.print", "printReport");
		buttonMap.put("button.cancel", "cancel");
		return;
	}
	/*
	 * 
	 */
	public ActionForward submitSearch(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
	    	ActivitiesForm form = (ActivitiesForm)aForm;	
	    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    	GetJuvenileByCasefileActivityEvent event = (GetJuvenileByCasefileActivityEvent) EventFactory
	                .getInstance(JuvenileCaseControllerServiceNames.GETJUVENILEBYCASEFILEACTIVITY);
	    
	    	event.setActivityCategory(form.getSelectedCategoryId());
	    	event.setActivityType(form.getSelectedTypeId());
	    	event.setFromDate(form.getStartDateAsStr());
	    	event.setToDate(form.getEndDateAsStr());
	    	event.setActivityCode(form.getSelectedDescriptionId());
	    	event.setSupervisionTypeId(form.getSupervisionTypeId2());
	    	event.setJuvLocationId(form.getLocationId());
	    	event.setOfficerLastNameData(form.getOfficerLastName());
	    	event.setOfficerFirstNameData(form.getOfficerFirstName());
	    	event.setJuvenileNumber(form.getJuvenileNumber());
	    	if(form.getSelectSystemActivities().equalsIgnoreCase("0"))
	    	    event.setSystemActivities("no");
	    	else
	    	    event.setSystemActivities("yes");
	    	if(form.getSelectclosedCasefiles().equalsIgnoreCase("0"))
	    	    event.setClosedCasefiles("no");
	    	else
	    	    event.setClosedCasefiles("yes");
	    	dispatch.postEvent(event);
		CompositeResponse resp = (CompositeResponse) dispatch.getReply();
		List<JuvenileCasefileActivityResponseEvent> filteredJuveniles = new ArrayList<JuvenileCasefileActivityResponseEvent>();
		List<JuvenileCasefileActivityResponseEvent> juveniles = MessageUtil.compositeToList(resp, JuvenileCasefileActivityResponseEvent.class);
		//statictics
		Map<String, String> unique = new HashMap<String, String>();
		//Map<String, Integer> groupings = new HashMap<String, Integer>();
		
		Iterator<JuvenileCasefileActivityResponseEvent> iter = juveniles.iterator();
		while(iter.hasNext()){
		    
		    JuvenileCasefileActivityResponseEvent evt = iter.next();
		    unique.put( evt.getActivityCodeId(), evt.getActivitycodeDesc());
		}
		int cntr = 0;
		int actCounter=0;
		int total=0;
		List<JuvenileCasefileActivityStatisticsResponseEvent> statisticsCollection = new ArrayList<JuvenileCasefileActivityStatisticsResponseEvent>();
		for (String key : unique.keySet()) {
			for( JuvenileCasefileActivityResponseEvent s : juveniles ) {
			    if( key.equals(s.getActivityCodeId()) ) {
			        cntr ++;
			    }
			   
			}			 
			JuvenileCasefileActivityStatisticsResponseEvent evt= new JuvenileCasefileActivityStatisticsResponseEvent();
			evt.setActivityName(unique.get(key));
			evt.setActivityCount(cntr);
			 actCounter ++;
			 total=total+cntr;
			 cntr =0;
			 statisticsCollection.add(evt);			 
		}
		
		form.setStatistics(statisticsCollection);
		form.setTotal(total);
		form.setActCounter(actCounter);
		//
		form.setJuvenilebycasefileActivity(juveniles);
		form.setListTotal(juveniles.size());
		ActionErrors errors = new ActionErrors();
		if(form.getJuvenilebycasefileActivity() != null && form.getJuvenilebycasefileActivity().size() == 0){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage( UIConstants.NO_JUVENILE_CASEFILE_ACTIVITY_FOUND_ERROR ));
			saveErrors(aRequest, errors);		
		}		
		if(errors.size() > 0){
		    	form.clear();		    	
			return( aMapping.findForward(UIConstants.SEARCH_FAILURE) );
		}
						
		return aMapping.findForward(UIConstants.SUCCESS);
	}	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward printReport(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
	    	ActivitiesForm form = (ActivitiesForm) aForm;
	    	StringBuilder title = new StringBuilder();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		JuvenileCasefileActivitySearchResultsReportBean reportBean = new JuvenileCasefileActivitySearchResultsReportBean();
		if (form.getJuvenilebycasefileActivity() != null)
		    reportBean.setNumberOfResults(form.getJuvenilebycasefileActivity().size());
		reportBean.setTodaysDate(DateUtil.dateToString(new Date(), UIConstants.DATE_FMT_1));
		reportBean.setSearchResults(form.getJuvenilebycasefileActivity());
		reportBean.setStatistics(form.getStatistics());
		reportBean.setTotal(form.getTotal());
		reportBean.setActCounter(form.getActCounter());
		
		if ( (form.getOfficerFirstName()!= null
			&& form.getOfficerFirstName().length() > 0 )
			|| (form.getOfficerLastName() != null && form.getOfficerLastName().length() > 0 ) ) {
		    title.append(form.getOfficerLastName() + ", " + form.getOfficerFirstName() + " - " + form.getStartDateAsStr() + " To " + form.getEndDateAsStr()   );
		}
		
		if ( (form.getOfficerFirstName()== null
			|| form.getOfficerFirstName().length() == 0 )
			&& ( form.getOfficerLastName() == null || form.getOfficerLastName().length() == 0 )
			&& (form.getSupervisionTypeId2() != null && form.getSupervisionTypeId2().length() > 0 )) {
		    title.append(form.getSupervisionTypeDesc() + " - " + form.getStartDateAsStr() + " To " + form.getEndDateAsStr()   );
		}
		
		if ( (form.getOfficerFirstName()== null
			|| form.getOfficerFirstName().length() == 0 )
			&& ( form.getOfficerLastName() == null || form.getOfficerLastName().length() == 0 )
			&& (form.getSupervisionTypeId2() == null || form.getSupervisionTypeId2().length() == 0 )) {
		    if (form.getLocationId() != null && form.getLocationId().length() > 0 ){
			title.append( form.getLocationDesc() + " - ");
		    }
		    title.append( form.getStartDateAsStr() + " To " + form.getEndDateAsStr()   );
		}
		
		reportBean.setTitle(title.toString());
		aRequest.getSession().setAttribute("reportInfo", reportBean);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		//let the pdfManager know that the report should be saved in the request during report creation
		aRequest.setAttribute("isPdfSaveNeeded", "false");
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.JUVENILEBYACTIVITYCATEGORY_SEARCH_RESULTS_REPORT);
		return null;
	}
	

	/**
	 * populate the form with results data
	 * @param receiptsForm
	 * @param response
	 */
	private void populateHistoricalReceiptForm(JuvenileFacilityReceiptForm receiptsForm, CompositeResponse response){	
		List receipts = MessageUtil.compositeToList(response, JuvenileFacilityHistoricalReceiptsResponseEvent.class);
		Collections.sort( receipts, JuvenileFacilityHistoricalReceiptsResponseEvent.ReleaseDateComparator );
		receiptsForm.setHistoricalReceipts(receipts);
		receiptsForm.setNumReceipts(receipts.size());
		// set values for the juvenile information
		JuvenileProfilesResponseEvent juvenileProfileResponse = (JuvenileProfilesResponseEvent)MessageUtil.filterComposite(response, JuvenileProfilesResponseEvent.class);
		// populate juvenile number with original search value, since nothing was found 
		if(juvenileProfileResponse.getJuvenileNum() != null ){	
			receiptsForm.setJuvenileNumber(juvenileProfileResponse.getJuvenileNum());
			receiptsForm.setJuvenileName(formatJuvenileFullName(juvenileProfileResponse));
			receiptsForm.setJuvenileRace(juvenileProfileResponse.getRace());
			receiptsForm.setJuvenileSex(juvenileProfileResponse.getSex());
			receiptsForm.setJuvenileDateOfBirth(juvenileProfileResponse.getDateOfBirth());
		}else{
			receiptsForm.setJuvenileName("");
			receiptsForm.setJuvenileRace("");
			receiptsForm.setJuvenileSex("");
			receiptsForm.setJuvenileDateOfBirth(null);
		}
	}
	
	/**
	 * format full name by "lastname, firstname middleName"
	 * @param juvenileProfileResponse
	 * @return
	 */
	private static String formatJuvenileFullName(JuvenileProfilesResponseEvent juvenileProfileResponse){
		StringBuffer fullName = new StringBuffer();
		if(juvenileProfileResponse.getLastName() != null &&  juvenileProfileResponse.getLastName().length() > 0){
			fullName.append(juvenileProfileResponse.getLastName() + ", ");
		}
		if(juvenileProfileResponse.getFirstName() != null && juvenileProfileResponse.getFirstName().length() > 0){
			fullName.append(juvenileProfileResponse.getFirstName() + " ");
		}
		if(juvenileProfileResponse.getMiddleName() != null && juvenileProfileResponse.getMiddleName().length() > 0){
			fullName.append(juvenileProfileResponse.getMiddleName());
		}	

		return fullName.toString();
	}
	
	/*
	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
	    ActivitiesForm form = (ActivitiesForm)aForm;
	    form.clear();
	    GetJuvenileActivityTypeCodesEvent reqEvent = (GetJuvenileActivityTypeCodesEvent) EventFactory
	                .getInstance(CodeTableControllerServiceNames.GETJUVENILEACTIVITYTYPECODES);

	        JuvenileActivityTypeCodeResponseEvent response = (JuvenileActivityTypeCodeResponseEvent) MessageUtil
	                .postRequest(reqEvent, JuvenileActivityTypeCodeResponseEvent.class);

	        form.setActivityCodes(response.getReturnValues());
	    return aMapping.findForward("cancel");
	}

}

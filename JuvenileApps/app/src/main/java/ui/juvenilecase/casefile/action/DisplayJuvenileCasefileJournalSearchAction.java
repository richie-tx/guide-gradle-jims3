//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\casefile\\action\\DisplayJuvenileCasefileJournalSearchAction.java

package ui.juvenilecase.casefile.action;

import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.casefile.CreateJournalReportEvent;
import messaging.casefile.SaveJournalSummaryDocEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.casefile.reply.CasefileJournalResponseEvent;
import messaging.casefile.reply.FacilityActivityResponseEvent;
import messaging.casefile.reply.GoalJournalResponseEvent;
import messaging.casefile.reply.JournalDocResponseEvent;
import messaging.casefile.reply.TraitJournalResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.journal.to.JournalSummaryTO;
import messaging.journal.to.JournalTO;
import messaging.juvenilecase.GetCasefileAssignmentHistoryEvent;
import messaging.juvenilecase.reply.JPOAssignmentHistoryViewResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilecase.reply.RiskAnswerResponseEvent;
import messaging.juvenilecase.reply.RiskRecommendationResponseEvent;
import messaging.mentalhealth.reply.MAYSISearchResultResponseEvent;
import messaging.officer.SearchJuvenileOfficerEvent;
import messaging.programreferral.reply.ProgramReferralCommentResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import messaging.riskanalysis.reply.RiskAssessmentResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCalendarConstants;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ujac.util.BeanComparator;

import pd.codetable.Code;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.JournalHelper;
import ui.juvenilecase.casefile.form.JournalForm;
import ui.juvenilecase.casefile.form.JournalForm.RiskAnalysisJournal;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.security.SecurityUIHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class DisplayJuvenileCasefileJournalSearchAction extends JIMSBaseAction
{
	/**
	 * @roseuid 47E25BDE0247
	 */
	public DisplayJuvenileCasefileJournalSearchAction( )
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 47E258D30320
	 */
	public ActionForward link( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JournalForm jf = (JournalForm)aForm ;
		jf.clearAll( ) ;
		return aMapping.findForward( UIConstants.SUCCESS ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward submit( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse ) throws Exception
	{
		JournalForm jf = (JournalForm)aForm ;
		JuvenileCasefileForm casefile = UIJuvenileCaseworkHelper.getHeaderForm( aRequest ) ;
		if (casefile == null) {
			throw new GeneralFeedbackMessageException( "Problem encountered while retrieving the casefile." ) ;
		}
		
		jf.setJuvenileId( casefile.getJuvenileNum() ) ;
		jf.setCasefileId(casefile.getSupervisionNum());
		//get the supervision type - User Story 26444     
		String supervisionCategory="";
		//supervisionCategory=casefile.getSupervisionCategoryId();
		Code typeCD = Code.find(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, casefile.getSupervisionTypeId());
		supervisionCategory= typeCD.getDescription();
		CompositeResponse journalResp = JournalHelper.getJournalEntries( jf, casefile.getSupervisionNum(),supervisionCategory ) ;
		CompositeResponse journalSummaryResp = JournalHelper.getCaseReviewJournalSummaryEntries( jf, casefile.getSupervisionNum(), supervisionCategory ) ;
		// populate search results for search result page and Generate Journal Report
		if( journalResp != null  && 
				journalResp.getResponses().size() > 0 )
		{
			/* access the [big] collection via an array for faster access with
			 * subsequent calls to compositeToCollection - this specialized, 
			 * "compositeToCollection" function exists in the JuvenileHelper 
			 * class to operate on the array, versus a collection.
			 * 
			 * it's not that accessing an array primitive is faster than
			 * accessing elements in an ArrayList, but we're setting the
			 * objects in the array to null to signify we've visited it.
			 */
			Object[] eventsBag = journalResp.getResponses().toArray() ;
			
			Collection coll = JournalHelper.compositeToCollection( eventsBag, ActivityResponseEvent.class ) ;
			if( coll != null )
			{
				Collections.sort((List)coll ) ;
				jf.setActivityEntries( coll ) ;
				
				//U.S. #27342
				coll = JournalHelper.compositeToCollection( eventsBag, FacilityActivityResponseEvent.class ) ;
				if( coll != null )
				{
					Collections.sort((List)coll) ;
					jf.setFacEventEntries(coll) ;
					
					/* the stacked if's are on purpose (for performance 
					 * reasons). there is no need to move on to the next 
					 * event type if there aren't any left [in the array], 
					 * so why make the function call (even if that function 
					 * call has a short-circuit check to return early).
					 */
				coll = JournalHelper.compositeToCollection( eventsBag, CasefileClosingResponseEvent.class ) ;
				if( coll != null )
				{
					jf.setClosingEntries( coll ) ;
				
					coll = JournalHelper.compositeToCollection( eventsBag, GoalJournalResponseEvent.class ) ;
					if( coll != null )
					{
						Collections.sort( (List)coll ) ;
						jf.setGoalEntries( coll ) ;

						coll = JournalHelper.compositeToCollection( eventsBag, ProgramReferralResponseEvent.class ) ;
						if( coll != null )
						{
							Collections.sort( (ArrayList)coll, ProgramReferralResponseEvent.refDateComparator ) ;
							List <ProgramReferralResponseEvent> referralList = CollectionUtil.iteratorToList(coll.iterator());
							ProgramReferralResponseEvent prre = null;
							for (int i = 0; i < referralList.size(); i++) {
								prre = referralList.get(i);
								StringBuffer pText = new StringBuffer(prre.getProviderProgramName());
								if (prre.getBeginDate() != null){
									pText.append(" Begin Date: ");
									pText.append(DateUtil.dateToString(prre.getBeginDate(), DateUtil.DATE_FMT_1));
									if (prre.getEndDate() != null){
										pText.append(" End Date: ");
										pText.append(DateUtil.dateToString(prre.getEndDate(), DateUtil.DATE_FMT_1));
									}
									prre.setProviderProgramName(pText.toString());
								} 
							}

							
							jf.setProgReferralEntries( coll ) ;
						
							coll = JournalHelper.compositeToCollection( eventsBag, CalendarServiceEventResponseEvent.class ) ;
							if( coll != null )
							{
								List theList = new ArrayList(coll);
			                    ArrayList sortFields = new ArrayList();
			                    sortFields.add(new ReverseComparator(new BeanComparator("eventDate")));
			                    sortFields.add(new ReverseComparator(new BeanComparator("eventTime")));
			                    ComparatorChain multiSort = new ComparatorChain(sortFields);
			                    Collections.sort(theList, multiSort);
			                    coll = new ArrayList(theList);
			                    Iterator iter = coll.iterator();
			                    Collection spEvents = new ArrayList();
			                    Collection juvEvents = new ArrayList();
			                    while(iter.hasNext())
			                    {
			                    	
			                    	CalendarServiceEventResponseEvent resp = (CalendarServiceEventResponseEvent)iter.next();
			                  
			                    	if(resp.getEventTypeCategory().equals("P"))
			                    		spEvents.add(resp);
			                    	else
			                    		juvEvents.add(resp);
			                    }
			                    jf.setCalEventEntries( coll ) ;
			                    jf.setJuvEventEntries(juvEvents);
			                    jf.setSpEventEntries(spEvents);
								
			                    coll = JournalHelper.compositeToCollection( eventsBag, TraitJournalResponseEvent.class ) ;
								if( coll != null )
								{
									Collections.sort( (List)coll ) ;
									jf.setTraitEntries( coll ) ;

									coll = JournalHelper.getRiskEntriesFromEventBag( eventsBag ) ;
									//<KISHORE>JIMS200058908 : MJCW - Casefile Journal Search Always Displaying Risk Section
									if( coll != null )
									{
				                        List newList = new ArrayList(coll);
				                        ArrayList srtFields = new ArrayList();
				                        srtFields.add(new ReverseComparator(new BeanComparator("entryDate")));
				                        ComparatorChain srt = new ComparatorChain(srtFields);
				                        Collections.sort(newList, srt);
				                        coll = new ArrayList(newList);
				                        jf.setRiskEntries( coll ) ;
									}
								}
							}
						}
					}
				}
			}
		}
		}
		
		// retrieve and populate the search for Generate Journal Summary Report, which has slightly different rules and filters
		if(journalSummaryResp != null){
			populateJournalSummaryResults(journalSummaryResp, jf, aRequest);
		}

		return aMapping.findForward( UIConstants.SUBMIT ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward find( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JournalForm jf = (JournalForm)aForm ;
		String searchType = jf.getSearchType( ) ;
		
		if( PDCalendarConstants.JPO_NAME_SEARCH.equals( searchType ) )
		{
			SearchJuvenileOfficerEvent searchEvent = (SearchJuvenileOfficerEvent)
					EventFactory.getInstance( OfficerProfileControllerServiceNames.SEARCHJUVENILEOFFICER ) ;

			searchEvent.setJpo( true ) ;
			searchEvent.setFirstName( jf.getFirstName( ) ) ;
			searchEvent.setLastName( jf.getLastName( ) ) ;
			searchEvent.setMiddleName( jf.getMiddleName( ) ) ;

			List results = super.postRequestListFilter( searchEvent, OfficerProfileResponseEvent.class ) ;
			jf.setNameSearchResults( results ) ;
			jf.setListCount( "" + results.size( ) ) ;
		}
		else if( PDCalendarConstants.CLM_NAME_SEARCH.equals( searchType ) )
		{
			SearchJuvenileOfficerEvent searchEvent = (SearchJuvenileOfficerEvent)
					EventFactory.getInstance( OfficerProfileControllerServiceNames.SEARCHJUVENILEOFFICER ) ;

			searchEvent.setCaseloadManager( true ) ;
			searchEvent.setFirstName( jf.getFirstName( ) ) ;
			searchEvent.setLastName( jf.getLastName( ) ) ;
			searchEvent.setMiddleName( jf.getMiddleName( ) ) ;

			List results = super.postRequestListFilter( searchEvent, OfficerProfileResponseEvent.class ) ;
			jf.setNameSearchResults( results ) ;
			jf.setListCount( "" + results.size( ) ) ;
		}
		
		return aMapping.findForward( UIConstants.SUCCESS ) ;
	}


	

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping( Map keyMap )
	{
		keyMap.put( "button.link", "link" ) ;
		keyMap.put( "button.submit", "submit" ) ;
		keyMap.put( "button.find", "find" ) ;
		keyMap.put( "button.back", "back" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.print", "generateReport" ) ;
		keyMap.put( "button.printJournalSummary", "bfoGenerateJournalSummaryReport" ) ;
		
	}

	/**
	 * Escapes the special XML characters like &,  ',",< and >  in the string passed.
     * Replaces all the <p> and &nbsp; tags in the string passed.
     * @param string The list in which the tags needs to be replaced
     * @return The list with replaced tags.
     */
	private List escapeXMLCharsActivities(List list)
    {
		Object obj = new Object();	
		List formattedList = new ArrayList();
		
		for ( int i = 0; i <list.size(); i++ )
		{
			obj = list.get(i);
			
			if ( obj instanceof ActivityResponseEvent ){
				
				ActivityResponseEvent acr = (ActivityResponseEvent)obj;
				String desc = acr.getActivityDesc();
				String comments = acr.getComments();
				desc = replaceTags(desc);
				comments = replaceTags(comments);
				acr.setActivityDesc(desc);
				acr.setComments(comments);
				formattedList.add(acr);
			}
	    }
        return formattedList;
    }
	
	/** Below method Added for US 35963, TASK 43840
	 * Escapes the special XML characters like &,  ',",< and >  in the string passed.
     * Replaces all the <p> and &nbsp; tags in the string passed.
     * @param string The list in which the tags needs to be replaced
     * @return The list with replaced tags.
     */
	private List escapeXMLCharsFacilityActivities(List list)
    {
		Object obj = new Object();	
		List formattedList = new ArrayList();
		
		for ( int i = 0; i <list.size(); i++ )
		{
			obj = list.get(i);
			
			if ( obj instanceof FacilityActivityResponseEvent ){
				
				FacilityActivityResponseEvent facr = (FacilityActivityResponseEvent)obj;
				String desc = facr.getActivityDesc();
				String comments = facr.getComments();
				desc = replaceTags(desc);
				comments = replaceTags(comments);
				facr.setActivityDesc(desc);
				facr.setComments(comments);
				formattedList.add(facr);
			}
	    }
        return formattedList;
    }
	
	/**
	 * Escapes the special XML characters like &,  ',",< and >  in the string passed.
     * Replaces all the <p> and &nbsp; tags in the string passed.
     * @param string The list in which the tags needs to be replaced
     * @return The list with replaced tags.
     */
	private List escapeXMLCharsCalEvent(List list)
    {
		Object obj = new Object();	
		List formattedList = new ArrayList();
		
		for ( int i = 0; i <list.size(); i++ )
		{
			obj = list.get(i);
			
			if ( obj instanceof CalendarServiceEventResponseEvent ){
				
				CalendarServiceEventResponseEvent acr = (CalendarServiceEventResponseEvent)obj;
				String type = acr.getCalendarEventType();
				String comments = acr.getEventComments();
				type = replaceTags(type);
				comments = replaceTags(comments);
				acr.setCalendarEventType(type);
				acr.setEventComments(comments);
				formattedList.add(acr);
				
			}
	    }
        return formattedList;
    }
	
	/**
	 * Escapes the special XML characters like &,  ',",< and >  in the string passed.
     * Replaces all the <p> and &nbsp; tags in the string passed.
     * @param string The list in which the tags needs to be replaced
     * @return The list with replaced tags.
     */
	private List escapeXMLCharsGoalEntries(List list)
    {
		Object obj = new Object();	
		List formattedList = new ArrayList();
		
		for ( int i = 0; i <list.size(); i++ )
		{
			obj = list.get(i);
			
			if ( obj instanceof GoalJournalResponseEvent ){
				
				GoalJournalResponseEvent acr = (GoalJournalResponseEvent)obj;
				String endRecom = acr.getEndRecommendations();
				String goalDesc = acr.getGoalDescription();
				String progressNote = acr.getProgressNotes();
				endRecom = replaceTags(endRecom);
				goalDesc = replaceTags(goalDesc);
				progressNote = replaceTags(progressNote);
				acr.setEndRecommendations(endRecom);
				acr.setGoalDescription(goalDesc);
				acr.setProgressNotes(progressNote);
				formattedList.add(acr);
			}
	    }
        return formattedList;
    }
	
	/**
	 * Escapes the special XML characters like &,  ',",< and >  in the string passed.
     * Replaces all the <p> and &nbsp; tags in the string passed.
     * @param string The list in which the tags needs to be replaced
     * @return The list with replaced tags.
     */
	private List escapeXMLCharsRiskEntries(List list)
    {
		Object obj = new Object();	
		List formattedList = new ArrayList();
		
		for ( int i = 0; i <list.size(); i++ )
		{
			obj = list.get(i);
			
			if ( obj instanceof RiskAnalysisJournal ){
				
				RiskAnalysisJournal acr = (RiskAnalysisJournal)obj;
				String comments = acr.getComments();
				String title = acr.getTitle();
				comments = replaceTags(comments);
				title = replaceTags(title);
				acr.setComments(comments);
				acr.setTitle(title);
				formattedList.add(acr);
			}
	    }
        return formattedList;
    }
	
	/**
	 * Escapes the special XML characters like &,  ',",< and >  in the string passed.
     * Replaces all the <p> and &nbsp; tags in the string passed.
     * @param string The list in which the tags needs to be replaced
     * @return The list with replaced tags.
     */
	private List escapeXMLCharsTraitEntries(List list)
    {
		Object obj = new Object();	
		List formattedList = new ArrayList();
		
		for ( int i = 0; i <list.size(); i++ )
		{
			obj = list.get(i);
			
			if ( obj instanceof TraitJournalResponseEvent ){
				
				TraitJournalResponseEvent acr = (TraitJournalResponseEvent)obj;
				String comment = acr.getComments();
				String traitName = acr.getTraitName();
				String traitStatus = acr.getTraitStatus();
				comment = replaceTags(comment);
				traitName = replaceTags(traitName);
				traitStatus = replaceTags(traitStatus);
				acr.setComments(comment);
				acr.setTraitName(traitName);
				acr.setTraitStatus(traitStatus);
				formattedList.add(acr);
			}
	    }
        return formattedList;
    }
	
	/**
	 * Escapes the special XML characters like &,  ',",< and >  in the string passed.
     * Replaces all the <p> and &nbsp; tags in the string passed.
     * @param string The list in which the tags needs to be replaced
     * @return The list with replaced tags.
     */
	private List escapeXMLCharsProgReferral(List list)
    {
		Object obj = new Object();	
		List formattedList = new ArrayList();
		
		for ( int i = 0; i <list.size(); i++ )
		{
			obj = list.get(i);
			
			if ( obj instanceof ProgramReferralResponseEvent ){
				
				ProgramReferralResponseEvent acr = (ProgramReferralResponseEvent)obj;
				String providerProgramName = acr.getProviderProgramName();
				providerProgramName = replaceTags(providerProgramName);
				acr.setProviderProgramName(providerProgramName);
				Object comments = new Object();
				List referralComments = acr.getReferralComments();
				for ( int j = 0; j < referralComments.size(); j++ )
				{
					comments = referralComments.get(j);
					if ( comments instanceof ProgramReferralCommentResponseEvent )
					{
						ProgramReferralCommentResponseEvent referral = (ProgramReferralCommentResponseEvent)comments;
						String commentText = referral.getCommentText();
						commentText = replaceTags(commentText);
						referral.setCommentText(commentText);
					}
				
				}
				formattedList.add(acr);
			}
	    }
        return formattedList;
    }
	
	/**
	 * Escapes the special XML characters like &,  ',",< and >  in the string passed.
     * Replaces all the <p> and &nbsp; tags in the string passed.
     * @param string The list in which the tags needs to be replaced
     * @return The list with replaced tags.
     */
	private List escapeXMLCharsClosingEntries(List list)
    {
		Object obj = new Object();	
		List formattedList = new ArrayList();
		
		for ( int i = 0; i <list.size(); i++ )
		{
			obj = list.get(i);
			
			if ( obj instanceof CasefileClosingResponseEvent ){
				
				CasefileClosingResponseEvent acr = (CasefileClosingResponseEvent)obj;
				String closingComments = acr.getClosingComments();
				String closingEvaluation = acr.getClosingEvaluation();
				closingComments = replaceTags(closingComments);
				closingEvaluation = replaceTags(closingEvaluation);
				acr.setClosingComments(closingComments);
				acr.setClosingEvaluation(closingEvaluation);
				formattedList.add(acr);
			}
	    }
        return formattedList;
    }
	
	/**
     * Replaces all the <p> and &nbsp; tags in the string passed.
     * @param string The string in which the tags needs to be replaced
     * @return The string with replaced tags.
     */
    private String replaceTags(String string) {
        String resolvedString = string;
        if (string != null)
            resolvedString = string.replaceAll("> <", "><space/><").replaceAll("<p>", "").replaceAll("</p>", "").replaceAll("&nbsp;", "<space/>").replaceAll("<b>", "").replaceAll("</b>", "").replaceAll("<br />", "").replaceAll("<br", "").replaceAll("/>", "")
            .replaceAll("<BR>", "<br/>").replaceAll("<br>", "<br/>").replaceAll("<u>", "").replaceAll("</u>", "").replaceAll("</br>", "<br/>").replaceAll("&",  "&#38;").replaceAll("\"","&quot;").replaceAll("<", "&lt;")
            .replaceAll(">","&gt;").replaceAll("\\\\+", "/");
        return resolvedString;
    }

	/**
	 * populate the summary journal data for the journal summary report into the journal Form
	 * @param journalSummaryResp
	 * @param summaryJournalForm
	 * @throws Exception
	 */
	public void populateJournalSummaryResults(CompositeResponse journalSummaryResp, JournalForm journalForm, HttpServletRequest aRequest) throws Exception
	{
		// populate search results for search result page and Generate Journal Summary Report
		if( journalSummaryResp != null  && 
				journalSummaryResp.getResponses().size() > 0 )
		{
			Object[] summaryEventsBag = journalSummaryResp.getResponses().toArray() ;
			
			// activities
			Collection coll = JournalHelper.compositeToCollection( summaryEventsBag, ActivityResponseEvent.class ) ;
			if( coll != null )
			{
				Collections.sort( (List)coll ) ;
				journalForm.setActivitySummaryEntries( coll ) ;
			}
			
			// facility activities //US 35963, TASK 43840
			coll = JournalHelper.compositeToCollection( summaryEventsBag, FacilityActivityResponseEvent.class ) ;
			if( coll != null )
			{
				Collections.sort( (List)coll ) ;
				journalForm.setFacilityActivitySummaryEntries(coll);
			}
			
			// casefile closing
			coll = JournalHelper.compositeToCollection( summaryEventsBag, CasefileClosingResponseEvent.class ) ;
			if( coll != null )
			{
				journalForm.setClosingSummaryEntries( coll ) ;
			}
			// program referrals
			coll = JournalHelper.compositeToCollection( summaryEventsBag, ProgramReferralResponseEvent.class ) ;
			if( coll != null )
			{	
				journalForm.setProgReferralSummaryEntries( coll ) ;
			}
			// calendar events		
			coll = JournalHelper.compositeToCollection( summaryEventsBag, CalendarServiceEventResponseEvent.class ) ;
			if( coll != null ){
				List theList = new ArrayList(coll);
                ArrayList sortFields = new ArrayList();
                sortFields.add(new ReverseComparator(new BeanComparator("eventDate")));
                sortFields.add(new ReverseComparator(new BeanComparator("eventTime")));
                ComparatorChain multiSort = new ComparatorChain(sortFields);
                Collections.sort(theList, multiSort);
                coll = new ArrayList(theList);
                Iterator iter = coll.iterator();
                Collection spEvents = new ArrayList();
                Collection juvEvents = new ArrayList();
                while(iter.hasNext())
                {
                	
                	CalendarServiceEventResponseEvent resp = (CalendarServiceEventResponseEvent)iter.next();
              
                	if(resp.getEventTypeCategory().equalsIgnoreCase("P")){
                		spEvents.add(resp);
                	// Only add eventTypeCode N for JUV Events
                	}else if(resp.getEventTypeCategory().equalsIgnoreCase("N") || resp.getEventTypeCategory().equalsIgnoreCase("I")){
                		juvEvents.add(resp);
                	}
                }
                journalForm.setCalEventSummaryEntries( coll ) ;
                journalForm.setJuvEventSummaryEntries(juvEvents);
                journalForm.setSpEventSummaryEntries(spEvents);
			}
				
			// risk analysis
			List<RiskAssessmentResponseEvent> riskAssessmentResponseList = (ArrayList)JournalHelper.compositeToCollection( summaryEventsBag, RiskAssessmentResponseEvent.class ) ;
			List<RiskRecommendationResponseEvent> riskRecommendationResponseList = (ArrayList)JournalHelper.compositeToCollection( summaryEventsBag, RiskRecommendationResponseEvent.class ) ;
			List<RiskAnswerResponseEvent> riskAnswerResponseList = (ArrayList)JournalHelper.compositeToCollection( summaryEventsBag, RiskAnswerResponseEvent.class ) ;
			List<JournalForm.CaseReviewRiskAnalysisBean> summaryRiskAssessmentsList = new ArrayList();
			for(RiskAssessmentResponseEvent event:riskAssessmentResponseList){
				JournalForm.CaseReviewRiskAnalysisBean riskJournal = new JournalForm.CaseReviewRiskAnalysisBean();
				if(event.getEnteredDate() != null && !event.getEnteredDate().equals("")){
					riskJournal.setEntryDate(DateUtil.dateToString(event.getEnteredDate(), DateUtil.DATE_FMT_1));
				}
				if(event.getAssessmentType() != null && !event.getAssessmentType().equals("")){
					riskJournal.setRiskAnalysis(event.getAssessmentType().toUpperCase());
				}
				if(event.getRecommendations() !=null && event.getRecommendations().size() > 0){
					RiskRecommendationResponseEvent recommendationEvent = (RiskRecommendationResponseEvent) event.getRecommendations().get(0);
					if(recommendationEvent.getRiskAnalysisRecommendation() != null){
						riskJournal.setRecommendation(recommendationEvent.getRiskAnalysisRecommendation().toUpperCase() );
					}
				}
				String supervisionLevel = "";
				if(riskAnswerResponseList != null && riskAnswerResponseList.size() > 0){
					for(RiskAnswerResponseEvent questionAnswerEvent :riskAnswerResponseList){
						if(questionAnswerEvent != null && questionAnswerEvent.getRiskAnalysisId().equalsIgnoreCase(event.getRiskAnalysisId()) && 
								questionAnswerEvent.getRiskQuestionText().contains("Supervision Level")){
							if(questionAnswerEvent.getAnswerText() != null){
								supervisionLevel = questionAnswerEvent.getAnswerText().toUpperCase();
							}
						}
					}
				}
				riskJournal.setSupervisionLevel(supervisionLevel);
				
				summaryRiskAssessmentsList.add(riskJournal);
				supervisionLevel = "";
			}
				
			if(summaryRiskAssessmentsList != null){
				Collections.sort(summaryRiskAssessmentsList, JournalForm.CaseReviewRiskAnalysisBean.CaseReviewJournalRiskAnalysisSummaryComparator);
				journalForm.setRiskSummaryEntries( summaryRiskAssessmentsList ) ;
			}
				
			// maysi assessments
			coll = JournalHelper.compositeToCollection( summaryEventsBag, MAYSISearchResultResponseEvent.class ) ;
			 Collection maysiAssessments = new ArrayList();
             if(coll != null){
	             Iterator iter = coll.iterator();
	             while(iter.hasNext())
	             {         	
	            	 MAYSISearchResultResponseEvent resp = (MAYSISearchResultResponseEvent)iter.next();
	            	 maysiAssessments.add(resp);	
	             }
	             journalForm.setMaysiAssessmentSummaryEntries( maysiAssessments );
             }
             
	 		Collection assignmentHistory = new ArrayList();
			GetCasefileAssignmentHistoryEvent jpoAssignmentHistoriesEvent = 
			(GetCasefileAssignmentHistoryEvent)EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILEASSIGNMENTHISTORY);
			jpoAssignmentHistoriesEvent.setCasefileId(journalForm.getCasefileId());				
			CompositeResponse response = UIJuvenileHelper.getCompositeResponse(jpoAssignmentHistoriesEvent);
			assignmentHistory = (Collection)UIJuvenileHelper.fetchCollection(response, PDJuvenileCaseConstants.JUVENILE_CASEFILE_TOPIC);
			 journalForm.setHistoricalAssignmentSummaryEntries( assignmentHistory );
			 // TODO
			 populateValidMaysiAndHistoricalAssignments(journalForm);
             
             // supervision level
             journalForm.setOverviewSummarySupervisionLevel( getOverviewSupervisionLevel( (ArrayList) riskAnswerResponseList, (ArrayList)riskAssessmentResponseList));
             
             // overview activities
             ArrayList overviewActivities = getOverviewActivies((ArrayList)journalForm.getActivitySummaryEntries());
             journalForm.setOverviewActivitySummaryEntries(overviewActivities);
             
             // overview Facility activities US 35963, TASK 43840
             ArrayList overviewFacilityActivities = getOverviewFacilitiesActivies((ArrayList)journalForm.getFacilityActivitySummaryEntries());
             journalForm.setOverviewFacilityActivitySummaryEntries(overviewFacilityActivities);
             
             // overview calendarEvents
             ArrayList overviewCalendarEvents = getOverviewCalendarEvents((ArrayList)journalForm.getJuvEventSummaryEntries());
             journalForm.setOverviewCalendarEventSummaryEntries(overviewCalendarEvents);
             
             //get the supervision type - User Story 26444
             HttpSession session= aRequest.getSession();
     		JuvenileCasefileForm caseFileForm = (JuvenileCasefileForm)session.getAttribute("juvenileCasefileForm");
     		String supervisionCategory="";
     		if(caseFileForm!=null)
     			supervisionCategory=caseFileForm.getSupervisionCategoryId();
     				
             // overview progress risks
             ArrayList overviewProgressRisks =  getOverviewProgressRisk((ArrayList) riskAnswerResponseList,(ArrayList)riskAssessmentResponseList,supervisionCategory);
             if(overviewProgressRisks != null){
	             Collections.sort(overviewProgressRisks, JournalForm.OverviewProgressRiskBean.CaseReviewOverviewProgressRiskComparator);
	             journalForm.setOverviewProgressRiskSummaryEntries(overviewProgressRisks);
             }
		}
	}
	
   /**
    * Generate the Chronological Dictation Report Using UJAC
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return
    */
	public ActionForward generateReport(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JournalForm journalForm = (JournalForm)aForm;

		JuvenileCasefileForm casefile = UIJuvenileCaseworkHelper.getHeaderForm( aRequest ) ;
		journalForm.setJuvenileId( casefile.getJuvenileNum() ) ;
		
		JournalTO journalTO = new JournalTO();
		
		journalTO.setJuvenileName(casefile.getJuvenileFullName());
		journalTO.setProbationOfficer(casefile.getProbationOfficerFullName());
		journalTO.setSupervisionType(casefile.getSupervisionType());
		journalTO.setJuvenileId(casefile.getJuvenileNum());
		journalTO.setCaseFileId(casefile.getSupervisionNum());
		
		List journalEntries = new ArrayList();
		Object obj = new Object();	
		List tempEntries = (List) journalForm.getActivityEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
		{
			tempEntries = this.escapeXMLCharsActivities(tempEntries);
			//ActivityResponseEvent
			for ( int i = 0; i <tempEntries.size(); i++ )
			{
				obj = tempEntries.get(i);
				if ( obj instanceof ActivityResponseEvent ){
					ActivityResponseEvent acr = (ActivityResponseEvent)obj;
					CasefileJournalResponseEvent cjrEvent = new CasefileJournalResponseEvent ();
					cjrEvent.setDate(acr.getActivityDate());
					if (acr.getActivityTime() != null
						&& acr.getActivityTime().length() > 0) {
					    
					    cjrEvent.setTime( acr.getActivityTime() );
					}
					
					cjrEvent.setSubject(acr.getActivityDesc());
					cjrEvent.setText(acr.getComments());
					cjrEvent.setUpdateComments(acr.getUpdateComments());
					cjrEvent.setCreatedBy(acr.getCreatedBy());
					journalEntries.add(cjrEvent);
				}
		    }
		}
		 //  Facility activities US 35963, Task: 43840
		tempEntries = (List) journalForm.getFacEventEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
				{
					tempEntries = this.escapeXMLCharsFacilityActivities(tempEntries);
					//FacilityActivityResponseEvent
					for ( int i = 0; i <tempEntries.size(); i++ )
					{
						obj = tempEntries.get(i);
						if ( obj instanceof FacilityActivityResponseEvent ){
							FacilityActivityResponseEvent facr = (FacilityActivityResponseEvent)obj;
							CasefileJournalResponseEvent facilityEvent = new CasefileJournalResponseEvent ();
							facilityEvent.setDate(facr.getActivityDate());
							if (facr.getActivityTime() != null
								&& facr.getActivityTime().length() > 0) {
							    
							    facilityEvent.setTime( facr.getActivityTime() );
							}
							
							facilityEvent.setSubject(facr.getActivityDesc());
							facilityEvent.setText(facr.getComments());
							facilityEvent.setCreatedBy(facr.getCreatedBy());
							journalEntries.add(facilityEvent);
						}
				    }
				}
		tempEntries = (List) journalForm.getCalEventEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
		{
			tempEntries = this.escapeXMLCharsCalEvent(tempEntries);
			//CalendarServiceEventResponseEvent
			for ( int i = 0; i <tempEntries.size(); i++ )
			{
				obj = tempEntries.get(i);
				if ( obj instanceof CalendarServiceEventResponseEvent ){
					CalendarServiceEventResponseEvent cser = (CalendarServiceEventResponseEvent)obj;
					CasefileJournalResponseEvent cfjrEvent = new CasefileJournalResponseEvent ();
					cfjrEvent.setDate(cser.getEventDate());
					cfjrEvent.setSubject(cser.getEventType());
					if (cser.getEventTypeCategory().equals("P")) {
						cfjrEvent.setSubject(cser.getServiceName());						
					}
					if (cser.getEventTypeCategory().equals("I")) {
						cfjrEvent.setSubject(cser.getInterviewType());						
					}
					StringBuffer text = new StringBuffer();
					text.append("Event Comments: ");
					text.append(cser.getEventComments());
					if (cser.getProgressNotes() != null) {
						text.append(" <br/> Progress Notes: ");
						text.append(cser.getProgressNotes());
					}
					if (cser.getJuvenileAttendanceStatus()!= null 
							&& !cser.getJuvenileAttendanceStatus().equalsIgnoreCase("confirmed")
							&& !cser.getJuvenileAttendanceStatus().equalsIgnoreCase("unconfirmed")) {
						text.append("<br/> Attendance Status: ");
						text.append(cser.getJuvenileAttendanceStatus());
					}
					if (cser.getInterviewSummaryNotes() != null) {
						text.append(" <br/> Summary Notes: ");
						text.append(cser.getInterviewSummaryNotes());
					}
					cfjrEvent.setText(text.toString());
					cfjrEvent.setCreatedBy(cser.getCreatedBy());
					journalEntries.add(cfjrEvent);
				}
		    }
		}
		tempEntries = (List) journalForm.getGoalEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
		{
			tempEntries = this.escapeXMLCharsGoalEntries(tempEntries);
			//GoalJournalResponseEvent
			for ( int i = 0; i <tempEntries.size(); i++ )
			{
				obj = tempEntries.get(i);
				if ( obj instanceof GoalJournalResponseEvent ){
					GoalJournalResponseEvent gjer = (GoalJournalResponseEvent)obj;
					CasefileJournalResponseEvent jrEvent = new CasefileJournalResponseEvent ();
					jrEvent.setDate(gjer.getEntryDate());
					jrEvent.setSubject(gjer.getGoalDescription());
					StringBuffer goalText = new StringBuffer();
					goalText.append("Progress Notes: ");
					goalText.append(gjer.getProgressNotes());
					if (gjer.getEndRecommendations() != null) {
						goalText.append(" <br/> End Recommendations: ");
						goalText.append(gjer.getEndRecommendations());
					}
					jrEvent.setText(goalText.toString());
					jrEvent.setCreatedBy(gjer.getCreatedBy());
					journalEntries.add(jrEvent);
				}
		    }
		}
		tempEntries = (List) journalForm.getRiskEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
		{
			tempEntries = this.escapeXMLCharsRiskEntries(tempEntries);
			//RiskAnalysisJournal
			for ( int i = 0; i <tempEntries.size(); i++ )
			{
				obj = tempEntries.get(i);
				if ( obj instanceof RiskAnalysisJournal ){
					RiskAnalysisJournal grjer = (RiskAnalysisJournal)obj;
					CasefileJournalResponseEvent raEvent = new CasefileJournalResponseEvent ();
					raEvent.setDate(grjer.getEntryDate());
					raEvent.setSubject(grjer.getTitle());
					raEvent.setText(grjer.getComments());
					raEvent.setCreatedBy(grjer.getCreatedBy());
					journalEntries.add(raEvent);
				}
		    }
		}
		tempEntries = (List) journalForm.getTraitEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
		{
			tempEntries = this.escapeXMLCharsTraitEntries(tempEntries);
			//TraitJournalResponseEvent
			for ( int i = 0; i <tempEntries.size(); i++ )
			{
				obj = tempEntries.get(i);
				if ( obj instanceof TraitJournalResponseEvent ){
					TraitJournalResponseEvent tjre = (TraitJournalResponseEvent)obj;
					CasefileJournalResponseEvent tcjrEvent = new CasefileJournalResponseEvent ();
					tcjrEvent.setDate(tjre.getTraitDate());
					tcjrEvent.setSubject(tjre.getTraitName());
					tcjrEvent.setText(tjre.getComments());
					tcjrEvent.setCreatedBy(tjre.getCreatedBy());
					journalEntries.add(tcjrEvent);
				}
		    }
		}
		tempEntries = (List) journalForm.getProgReferralEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
		{
			tempEntries = this.escapeXMLCharsProgReferral(tempEntries);
			//ProgramReferralResponseEvent
			for ( int i = 0; i <tempEntries.size(); i++ )
			{
				obj = tempEntries.get(i);
				if ( obj instanceof ProgramReferralResponseEvent ){
					ProgramReferralResponseEvent prre = (ProgramReferralResponseEvent)obj;
					CasefileJournalResponseEvent prjrEvent = new CasefileJournalResponseEvent ();
					prjrEvent.setDate(prre.getBeginDate());
					prjrEvent.setSubject(prre.getProviderProgramName());
					StringBuffer pText = new StringBuffer();
					Object comments = new Object();
					List referralComments = prre.getReferralComments();
					for ( int j = 0; j < referralComments.size(); j++ )
					{
						comments = referralComments.get(j);
						if ( comments instanceof ProgramReferralCommentResponseEvent )
						{
							ProgramReferralCommentResponseEvent referral = (ProgramReferralCommentResponseEvent)comments;
							String commentText = referral.getCommentText();
							String commentDate = UIUtil.parseDate(referral.getCommentsDate());
							String commentTime = UIUtil.parseTime(referral.getCommentsDate());
							pText = pText.append(commentText);
							pText.append(" ["); 
							pText.append(commentDate);
							pText.append(" ");
							pText.append(commentTime);
							pText.append(" - ");
							pText.append(referral.getUserName());
							pText.append("] <br/>");
						}
					}
					prjrEvent.setText(pText.toString());
					prjrEvent.setCreatedBy(prre.getCreatedBy());
					journalEntries.add(prjrEvent);
				}
		    }
		}
		tempEntries = (List) journalForm.getClosingEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
		{
			tempEntries = this.escapeXMLCharsClosingEntries(tempEntries);
			//CasefileClosingResponseEvent
			for ( int i = 0; i <tempEntries.size(); i++ )
			{
				obj = tempEntries.get(i);
				if ( obj instanceof CasefileClosingResponseEvent ){
					CasefileClosingResponseEvent ccre = (CasefileClosingResponseEvent)obj;
					CasefileJournalResponseEvent ccrEvent = new CasefileJournalResponseEvent ();
					ccrEvent.setDate(ccre.getCreateDate());
					ccrEvent.setSubject("Closing Information");
					StringBuffer cText = new StringBuffer();
					cText.append("Closing Comments: ");
					cText.append(ccre.getClosingComments());
					if (ccre.getClosingEvaluation() != null) {
						cText.append(" <br/> Closing Evaluation: ");
						cText.append(ccre.getClosingEvaluation());
					}
					ccrEvent.setText(cText.toString());
					ccrEvent.setCreatedBy(ccre.getCreatedBy());
					journalEntries.add(ccrEvent);
				}
		    }
		}
		if( journalEntries != null )
		{
			for (int i = 0; i < journalEntries.size(); i++) {
				CasefileJournalResponseEvent ccrEvent = (CasefileJournalResponseEvent) journalEntries.get(i);
				if (ccrEvent.getDate() instanceof Timestamp){
					Date aDate = new Date(ccrEvent.getDate().getTime());
					ccrEvent.setDate(aDate);
				}
			}
			List theList = new ArrayList(journalEntries);
            ArrayList sortFields = new ArrayList();
            sortFields.add(new ReverseComparator(new BeanComparator("date")));
            ComparatorChain multiSort = new ComparatorChain(sortFields);
            Collections.sort(theList, multiSort);
            journalEntries = new ArrayList(theList);
		}   
		journalTO.setJournalEntries(journalEntries);
		CreateJournalReportEvent reqEvent = new CreateJournalReportEvent();
		reqEvent.setJournalDataTO(journalTO);
		reqEvent.setCasefileId(journalForm.getCasefileId());
		
		ReportResponseEvent aRespEvent = (ReportResponseEvent)MessageUtil.postRequest(reqEvent, ReportResponseEvent.class);
		System.out.println("Generate general report...");
		System.out.println("Path: " + aRespEvent.getFileName());
		
		try
		{
			aResponse.setContentType("application/x-file-download");
			aResponse.setHeader("Content-disposition", "attachment; filename=" + aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/") + 1) + ".pdf");
			aResponse.setHeader("Cache-Control", "max-age=" + 10);
			aResponse.setContentLength(aRespEvent.getContent().length);
			aResponse.resetBuffer();
			OutputStream os = aResponse.getOutputStream();
			os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
			os.flush();
			os.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Generate the Chronological Dictation Report Using BFO Framework
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward bfoGenerateReport(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JournalForm journalForm = (JournalForm)aForm;
	
		JuvenileCasefileForm casefile = UIJuvenileCaseworkHelper.getHeaderForm( aRequest ) ;
		journalForm.setJuvenileId( casefile.getJuvenileNum() ) ;
		
		JournalTO journalTO = new JournalTO();
		
		journalTO.setJuvenileName(casefile.getJuvenileFullName());
		journalTO.setProbationOfficer(casefile.getProbationOfficerFullName());
		journalTO.setSupervisionType(casefile.getSupervisionType());
		journalTO.setJuvenileId(casefile.getJuvenileNum());
		journalTO.setCaseFileId(casefile.getSupervisionNum());
		
		List journalEntries = new ArrayList();
		Object obj = new Object();	
		List tempEntries = (List) journalForm.getActivityEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
		{
			tempEntries = this.escapeXMLCharsActivities(tempEntries);
			//ActivityResponseEvent
			for ( int i = 0; i <tempEntries.size(); i++ )
			{
				obj = tempEntries.get(i);
				if ( obj instanceof ActivityResponseEvent ){
					ActivityResponseEvent acr = (ActivityResponseEvent)obj;
					CasefileJournalResponseEvent cjrEvent = new CasefileJournalResponseEvent ();
					cjrEvent.setDate(acr.getActivityDate());
					cjrEvent.setSubject(acr.getActivityDesc());
					cjrEvent.setText(acr.getComments());
					cjrEvent.setUpdateComments(acr.getUpdateComments());
					cjrEvent.setCreatedBy(acr.getCreatedBy());
					journalEntries.add(cjrEvent);
				}
		    }
		}
		tempEntries = (List) journalForm.getCalEventEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
		{
			tempEntries = this.escapeXMLCharsCalEvent(tempEntries);
			//CalendarServiceEventResponseEvent
			for ( int i = 0; i <tempEntries.size(); i++ )
			{
				obj = tempEntries.get(i);
				if ( obj instanceof CalendarServiceEventResponseEvent ){
					CalendarServiceEventResponseEvent cser = (CalendarServiceEventResponseEvent)obj;
					CasefileJournalResponseEvent cfjrEvent = new CasefileJournalResponseEvent ();
					cfjrEvent.setDate(cser.getEventDate());
					cfjrEvent.setSubject(cser.getEventType());
					if (cser.getEventTypeCategory().equals("P")) {
						cfjrEvent.setSubject(cser.getServiceName());						
					}
					if (cser.getEventTypeCategory().equals("I")) {
						cfjrEvent.setSubject(cser.getInterviewType());						
					}
					StringBuffer text = new StringBuffer();
					text.append("Event Comments: ");
					text.append(cser.getEventComments());
					if (cser.getProgressNotes() != null) {
						text.append(" <br/> Progress Notes: ");
						text.append(cser.getProgressNotes());
					}
					if (cser.getJuvenileAttendanceStatus()!= null && cser.getEventTypeCategory().equals("P")
							&& !cser.getJuvenileAttendanceStatus().equalsIgnoreCase("confirmed")
							&& !cser.getJuvenileAttendanceStatus().equalsIgnoreCase("unconfirmed")) {
						text.append("<br/> Attendance Status: ");
						text.append(cser.getJuvenileAttendanceStatus());
					}
					if (cser.getInterviewSummaryNotes() != null) {
						text.append(" <br/> Summary Notes: ");
						text.append(cser.getInterviewSummaryNotes());
					}
					cfjrEvent.setText(text.toString());
					cfjrEvent.setCreatedBy(cser.getCreatedBy());
					journalEntries.add(cfjrEvent);
				}
		    }
		}
		tempEntries = (List) journalForm.getGoalEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
		{
			tempEntries = this.escapeXMLCharsGoalEntries(tempEntries);
			//GoalJournalResponseEvent
			for ( int i = 0; i <tempEntries.size(); i++ )
			{
				obj = tempEntries.get(i);
				if ( obj instanceof GoalJournalResponseEvent ){
					GoalJournalResponseEvent gjer = (GoalJournalResponseEvent)obj;
					CasefileJournalResponseEvent jrEvent = new CasefileJournalResponseEvent ();
					jrEvent.setDate(gjer.getEntryDate());
					jrEvent.setSubject(gjer.getGoalDescription());
					StringBuffer goalText = new StringBuffer();
					goalText.append("Progress Notes: ");
					goalText.append(gjer.getProgressNotes());
					if (gjer.getEndRecommendations() != null) {
						goalText.append(" <br/> End Recommendations: ");
						goalText.append(gjer.getEndRecommendations());
					}
					jrEvent.setText(goalText.toString());
					jrEvent.setCreatedBy(gjer.getCreatedBy());
					journalEntries.add(jrEvent);
				}
		    }
		}
		tempEntries = (List) journalForm.getRiskEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
		{
			tempEntries = this.escapeXMLCharsRiskEntries(tempEntries);
			//RiskAnalysisJournal
			for ( int i = 0; i <tempEntries.size(); i++ )
			{
				obj = tempEntries.get(i);
				if ( obj instanceof RiskAnalysisJournal ){
					RiskAnalysisJournal grjer = (RiskAnalysisJournal)obj;
					CasefileJournalResponseEvent raEvent = new CasefileJournalResponseEvent ();
					raEvent.setDate(grjer.getEntryDate());
					raEvent.setSubject(grjer.getTitle());
					raEvent.setText(grjer.getComments());
					raEvent.setCreatedBy(grjer.getCreatedBy());
					journalEntries.add(raEvent);
				}
		    }
		}
		tempEntries = (List) journalForm.getTraitEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
		{
			tempEntries = this.escapeXMLCharsTraitEntries(tempEntries);
			//TraitJournalResponseEvent
			for ( int i = 0; i <tempEntries.size(); i++ )
			{
				obj = tempEntries.get(i);
				if ( obj instanceof TraitJournalResponseEvent ){
					TraitJournalResponseEvent tjre = (TraitJournalResponseEvent)obj;
					CasefileJournalResponseEvent tcjrEvent = new CasefileJournalResponseEvent ();
					tcjrEvent.setDate(tjre.getTraitDate());
					tcjrEvent.setSubject(tjre.getTraitName());
					tcjrEvent.setText(tjre.getComments());
					tcjrEvent.setCreatedBy(tjre.getCreatedBy());
					journalEntries.add(tcjrEvent);
				}
		    }
		}
		tempEntries = (List) journalForm.getProgReferralEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
		{
			tempEntries = this.escapeXMLCharsProgReferral(tempEntries);
			//ProgramReferralResponseEvent
			for ( int i = 0; i <tempEntries.size(); i++ )
			{
				obj = tempEntries.get(i);
				if ( obj instanceof ProgramReferralResponseEvent ){
					ProgramReferralResponseEvent prre = (ProgramReferralResponseEvent)obj;
					CasefileJournalResponseEvent prjrEvent = new CasefileJournalResponseEvent ();
					prjrEvent.setDate(prre.getBeginDate());
					prjrEvent.setSubject(prre.getProviderProgramName());
					StringBuffer pText = new StringBuffer();
					Object comments = new Object();
					List referralComments = prre.getReferralComments();
					for ( int j = 0; j < referralComments.size(); j++ )
					{
						comments = referralComments.get(j);
						if ( comments instanceof ProgramReferralCommentResponseEvent )
						{
							ProgramReferralCommentResponseEvent referral = (ProgramReferralCommentResponseEvent)comments;
							String commentText = referral.getCommentText();
							String commentDate = UIUtil.parseDate(referral.getCommentsDate());
							String commentTime = UIUtil.parseTime(referral.getCommentsDate());
							pText = pText.append(commentText);
							pText.append(" ["); 
							pText.append(commentDate);
							pText.append(" ");
							pText.append(commentTime);
							pText.append(" - ");
							pText.append(referral.getUserName());
							pText.append("] <br/>");
						}
					}
					prjrEvent.setText(pText.toString());
					prjrEvent.setCreatedBy(prre.getCreatedBy());
					journalEntries.add(prjrEvent);
				}
		    }
		}
		tempEntries = (List) journalForm.getClosingEntries();
		if (tempEntries != null && !tempEntries.isEmpty())
		{
			tempEntries = this.escapeXMLCharsClosingEntries(tempEntries);
			//CasefileClosingResponseEvent
			for ( int i = 0; i <tempEntries.size(); i++ )
			{
				obj = tempEntries.get(i);
				if ( obj instanceof CasefileClosingResponseEvent ){
					CasefileClosingResponseEvent ccre = (CasefileClosingResponseEvent)obj;
					CasefileJournalResponseEvent ccrEvent = new CasefileJournalResponseEvent ();
					ccrEvent.setDate(ccre.getCreateDate());
					ccrEvent.setSubject("Closing Information");
					StringBuffer cText = new StringBuffer();
					cText.append("Closing Comments: ");
					cText.append(ccre.getClosingComments());
					if (ccre.getClosingEvaluation() != null) {
						cText.append(" <br/> Closing Evaluation: ");
						cText.append(ccre.getClosingEvaluation());
					}
					ccrEvent.setText(cText.toString());
					ccrEvent.setCreatedBy(ccre.getCreatedBy());
					journalEntries.add(ccrEvent);
				}
		    }
		}
		if( journalEntries != null )
		{
			for (int i = 0; i < journalEntries.size(); i++) {
				CasefileJournalResponseEvent ccrEvent = (CasefileJournalResponseEvent) journalEntries.get(i);
				if (ccrEvent.getDate() instanceof Timestamp){
					Date aDate = new Date(ccrEvent.getDate().getTime());
					ccrEvent.setDate(aDate);
				}
			}
			List theList = new ArrayList(journalEntries);
	        ArrayList sortFields = new ArrayList();
	        sortFields.add(new ReverseComparator(new BeanComparator("date")));
	        ComparatorChain multiSort = new ComparatorChain(sortFields);
	        Collections.sort(theList, multiSort);
	        journalEntries = new ArrayList(theList);
		}   
		journalTO.setJournalEntries(journalEntries);

		aRequest.getSession().setAttribute("journalTO", journalTO);

		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.CHRONOLOGICAL_DICTATION_JOURNAL);

	    return null;
	}
	
	/**
	 * Generate the Journal Summary Report Using BFO Framework
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward bfoGenerateJournalSummaryReport(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JournalForm journalForm = (JournalForm)aForm;
	
		String isDraft = aRequest.getParameter("Draft");
		JuvenileCasefileForm casefile = UIJuvenileCaseworkHelper.getHeaderForm( aRequest ) ;
		journalForm.setJuvenileId( casefile.getJuvenileNum() ) ;
		
		JournalSummaryTO journalSummaryTO = new JournalSummaryTO();
		
		journalSummaryTO.setJuvenileName(casefile.getJuvenileFullName());
		journalSummaryTO.setProbationOfficer(casefile.getProbationOfficerFullName());
		journalSummaryTO.setSupervisionType(casefile.getSupervisionType());
		journalSummaryTO.setJuvenileId(casefile.getJuvenileNum());
		journalSummaryTO.setCaseFileId(casefile.getSupervisionNum());
		journalSummaryTO.setFromDateRange(journalForm.getFromDate());
		journalSummaryTO.setToDateRange(journalForm.getEndDate()); 
		journalSummaryTO.setDraft(isDraft);
		
		// referrals
		if(journalForm.getCasefileReferralsSummaryEntries() != null && journalForm.getCasefileReferralsSummaryEntries().size() > 0){
			String assignedReferrals = "";
			String scheduledCourtDates = "";
			List<JuvenileCasefileReferralsResponseEvent> referallResponseList = (ArrayList)journalForm.getCasefileReferralsSummaryEntries();
			int lastloop = referallResponseList.size();
			int counter = 1;
			for(JuvenileCasefileReferralsResponseEvent event: referallResponseList){
				if(event.getReferralNumber() != null && !event.getReferralNumber().equals("")){
					if(counter != lastloop){
						assignedReferrals = assignedReferrals +  event.getReferralNumber() + ", ";
					}else{
						assignedReferrals = assignedReferrals +  event.getReferralNumber();
					}
				}
				if(validateIfFutureDate(event.getCourtDate())){
					if(event.getReferralNumber() != null && !event.getReferralNumber().equals("")){
						if(counter != lastloop){
							scheduledCourtDates = scheduledCourtDates + event.getReferralNumber() + " - " +  DateUtil.dateToString(event.getCourtDate(), DateUtil.DATE_FMT_1) + ", ";
						}else{
							scheduledCourtDates = scheduledCourtDates + event.getReferralNumber() + " - " +   DateUtil.dateToString(event.getCourtDate(), DateUtil.DATE_FMT_1);
						}
					}
				}
				counter++;
			}
			journalSummaryTO.setSummaryAssignedReferrals(assignedReferrals);
			journalSummaryTO.setSummaryScheduledCourtDates(scheduledCourtDates);
			
		}
		// activities
		if(journalForm.getActivitySummaryEntries() != null && journalForm.getActivitySummaryEntries().size() > 0){	
			
			List<ActivityResponseEvent> activityResponseList = (ArrayList)journalForm.getActivitySummaryEntries();
			// sort activities by activity description and then by activity date
			Collections.sort(activityResponseList, ActivityResponseEvent.CaseReviewJournalSummaryActivityComparator);
			journalSummaryTO.setSummaryActivityEntries(activityResponseList);
			journalSummaryTO.setSummaryActivityEntriesCount(activityResponseList.size());
		}
		
		// Facility Admit/Release Activities //added for Task 43840,  US 35963 
		if(journalForm.getFacilityActivitySummaryEntries() != null && journalForm.getFacilityActivitySummaryEntries().size() > 0){	
					
			List<FacilityActivityResponseEvent> facActivityResponseList = (ArrayList)journalForm.getFacilityActivitySummaryEntries();
			// sort facility activities by activity description and then by activity date
			Collections.sort(facActivityResponseList, FacilityActivityResponseEvent.CaseReviewJournalSummaryFacilityActivityComparator);
			journalSummaryTO.setSummaryFacilityActivityEntries(facActivityResponseList);
			journalSummaryTO.setSummaryFacilityActivityEntriesCount(facActivityResponseList.size());
		}
		
		// calendar events
		if(journalForm.getJuvEventSummaryEntries() != null && journalForm.getJuvEventSummaryEntries().size() > 0){	
			
			List<CalendarServiceEventResponseEvent> calendarResponseList = (ArrayList)journalForm.getJuvEventSummaryEntries();
			// sort activities by activity description and then by activity date
			Collections.sort(calendarResponseList, CalendarServiceEventResponseEvent.CaseReviewJournalSummaryCalendarEventComparator);
			journalSummaryTO.setSummaryCalendarEntries(calendarResponseList);
			journalSummaryTO.setSummaryCalendarEntriesCount(calendarResponseList.size());
		}
		// maysi
		if(journalForm.getMaysiAssessmentSummaryEntries() != null && journalForm.getMaysiAssessmentSummaryEntries().size() > 0){	
			String maysiSubsequentNeeded = "";
			String maysiSubsequentDone = "";
			String maysiSubsequentNotNeeded = "";
			String maysiNotDone = "";
			List<MAYSISearchResultResponseEvent> maysiSubsequentNeededList = new ArrayList();
			List<MAYSISearchResultResponseEvent> maysiSubsequentDoneList = new ArrayList();
			List<MAYSISearchResultResponseEvent> maysiSubsequentNotNeededList = new ArrayList();
			TreeSet<String> maysiNotDoneSet = new TreeSet<String>();
			
			List<MAYSISearchResultResponseEvent> maysiResponseList = (ArrayList)journalForm.getNoMaysiSectionMaysiAndAssignmentSummaryEntries();
			if(maysiResponseList != null){
				Collections.sort(maysiResponseList, MAYSISearchResultResponseEvent.CaseReviewJournalSummaryMaysiComparator);
			}
			
			Set<String> totalMaysiSubsequentReferralNumberSet = new TreeSet<String>();	// list to keep track of referral numbers collected that are not NON-MAYSI
			// populate the 4 different collections based on the maysi assessment option
			for(MAYSISearchResultResponseEvent event: maysiResponseList){
				if(event != null){
					if(event.getAssessmentOptionId().equals("D")){	
						maysiSubsequentDoneList.add(event);
						totalMaysiSubsequentReferralNumberSet.add(event.getReferralNumber());
					}else if (event.getAssessmentOptionId().equals("B")){
						maysiSubsequentNeededList.add(event);
						totalMaysiSubsequentReferralNumberSet.add(event.getReferralNumber());
					}else if (event.getAssessmentOptionId().equals("C")){
						maysiSubsequentNotNeededList.add(event);
						totalMaysiSubsequentReferralNumberSet.add(event.getReferralNumber());
					}
				}
			}	
			// go through the maysiSubsequentDoneList
			int lastloop = maysiSubsequentDoneList.size();
			int counter = 1;
			for(MAYSISearchResultResponseEvent event: maysiSubsequentDoneList){
				if(event.getReferralNumber() != null && !event.getReferralNumber().equals("")){
					if(counter != lastloop){						
						maysiSubsequentDone = maysiSubsequentDone +  event.getReferralNumber()  + validateDate(event.getAssessDate()) + ", ";
					}else{
						maysiSubsequentDone = maysiSubsequentDone +  event.getReferralNumber() + validateDate(event.getAssessDate()) ;
					}
				}
				counter++;
			}
			
			// go through the maysiSubsequentNeededList
			lastloop = maysiSubsequentNeededList.size();
			counter = 1;
			for(MAYSISearchResultResponseEvent event: maysiSubsequentNeededList){
				if(event.getReferralNumber() != null && !event.getReferralNumber().equals("")){
					if(counter != lastloop){						
						maysiSubsequentNeeded = maysiSubsequentNeeded +  event.getReferralNumber() + validateDate(event.getAssessDate()) + ", ";
					}else{
						maysiSubsequentNeeded = maysiSubsequentNeeded +  event.getReferralNumber() + validateDate(event.getAssessDate());
					}
				}
				counter++;
			}
			
			// go through the maysiSubsequentNotNeededList
			lastloop = maysiSubsequentNotNeededList.size();
			counter = 1;
			for(MAYSISearchResultResponseEvent event: maysiSubsequentNotNeededList){
				if(event.getReferralNumber() != null && !event.getReferralNumber().equals("")){
					if(counter != lastloop){						
						maysiSubsequentNotNeeded = maysiSubsequentNotNeeded +  event.getReferralNumber() + validateDate(event.getAssessDate()) + ", ";	
					}else{
						maysiSubsequentNotNeeded = maysiSubsequentNotNeeded +  event.getReferralNumber() + validateDate(event.getAssessDate());
					}
				}
				counter++;
			}
			// go through the maysiNotDoneList		
			ArrayList<MAYSISearchResultResponseEvent> noMaysiSectionMaysiAndAssignmentResults = (ArrayList)journalForm.getNoMaysiSectionMaysiAndAssignmentSummaryEntries();
			ArrayList<JPOAssignmentHistoryViewResponseEvent> noMaysiSectionNoMaysiJustAssignmentResults = (ArrayList)journalForm.getNoMaysiSectionAssignmentWithNoMaysiSummaryEntries();
			for(MAYSISearchResultResponseEvent event: noMaysiSectionMaysiAndAssignmentResults){
				// check that referral not in one of the other subsequent lists already
				if(event != null && !totalMaysiSubsequentReferralNumberSet.contains(event.getReferralNumber())){
					if (event.getAssessmentOptionId().equals("T")){
						maysiNotDoneSet.add(event.getReferralNumber() + validateDate(event.getAssessDate()));
					}else if(event.getAssessmentOptionId().equals("A")){
						maysiNotDoneSet.add(event.getReferralNumber() + validateDate(event.getAssessDate()));
					}
				}
			}
			for(JPOAssignmentHistoryViewResponseEvent assignmentEvent: noMaysiSectionNoMaysiJustAssignmentResults){
				if(assignmentEvent != null){
					// check that referral not in one of the other subsequent lists already
					if(!totalMaysiSubsequentReferralNumberSet.contains(assignmentEvent.getReferralNumber())){
						maysiNotDoneSet.add(assignmentEvent.getReferralNumber());
					}
				}
			}
			counter = 1;
			lastloop = maysiNotDoneSet.size();	
			for(String referralNumber:maysiNotDoneSet){
				if(counter != lastloop){						
					maysiNotDone = maysiNotDone +  referralNumber +  ", ";	
				}else{
					maysiNotDone = maysiNotDone +  referralNumber;
				}
				counter++;
			}
			
			journalSummaryTO.setSummaryMaysiSubsequentDone(maysiSubsequentDone);
			journalSummaryTO.setSummaryMaysiSubsequentNeeded(maysiSubsequentNeeded);
			journalSummaryTO.setSummaryMaysiSubsequentNotNeeded(maysiSubsequentNotNeeded);
			journalSummaryTO.setSummaryMaysiNone(maysiNotDone);
		}				
		// logged in user name
		String userName = SecurityUIHelper.getUser().getFirstName() + " " + SecurityUIHelper.getUser().getLastName();
		journalSummaryTO.setUserName(userName);
		
		// current date
		journalSummaryTO.setCurrentDate(Calendar.getInstance().getTime());
		
		// program referrals
		List summaryAcceptedProgramReferralsList = new ArrayList();
		List summaryClosedProgramReferralsList = new ArrayList();
		List summaryTentativeReferredProgramReferralsList = new ArrayList();
		List<ProgramReferralResponseEvent> programReferralResponseList = (ArrayList)journalForm.getProgReferralSummaryEntries();
		if(programReferralResponseList != null){
			// sort all list based on the referralStatudCd in alphabetical, and then by ServiceProvider Name alphabetical
			Collections.sort(programReferralResponseList, ProgramReferralResponseEvent.CaseReviewJournalSummaryProgramReferralComparator);
			journalSummaryTO.setSummaryAllReferredProgramReferrals(programReferralResponseList);
		}
		if(journalForm.getProgReferralSummaryEntries() != null && journalForm.getProgReferralSummaryEntries().size() > 0){			
			for(ProgramReferralResponseEvent event: programReferralResponseList){
				if(event.getReferralStatusCd() != null){
					if(event.getReferralStatusCd().equals("AC")){
						summaryAcceptedProgramReferralsList.add(event);
					}else if(event.getReferralStatusCd().equals("CL")){
						summaryClosedProgramReferralsList.add(event);
					}else if(event.getReferralStatusCd().equals("TN")) {
						summaryTentativeReferredProgramReferralsList.add(event);
					}
				}
				// filter the program name to get rid of the begin string
				if(event.getProviderProgramName() != null && !event.getProviderProgramName().equals("")){
					String programName = event.getProviderProgramName();
					if(programName.contains("Begin")){						
						int substringIndicator = programName.indexOf("Begin");
						programName = programName.substring(0, substringIndicator);
						event.setProviderProgramName(programName);
					}
				}
			}
		}
		journalSummaryTO.setSummaryAcceptedProgramReferrals(summaryAcceptedProgramReferralsList);
		journalSummaryTO.setSummaryClosedProgramReferrals(summaryClosedProgramReferralsList);
		journalSummaryTO.setSummaryTentativeReferredProgramReferrals(summaryTentativeReferredProgramReferralsList);
		journalSummaryTO.setSummaryAcceptedProgramReferralsCount(summaryAcceptedProgramReferralsList.size());
		journalSummaryTO.setSummaryClosedProgramReferralsCount(summaryClosedProgramReferralsList.size());
		journalSummaryTO.setSummaryTentativeReferredProgramReferralsCount(summaryTentativeReferredProgramReferralsList.size());
		journalSummaryTO.setSummaryProgramReferralsTotalCount(summaryAcceptedProgramReferralsList.size() + summaryClosedProgramReferralsList.size() 
				+ summaryTentativeReferredProgramReferralsList.size());
	
		// risk analysis
		List riskAnalysisSummaryList = new ArrayList(journalForm.getRiskSummaryEntries());
		journalSummaryTO.setSummaryRiskAnalysis(riskAnalysisSummaryList);
		journalSummaryTO.setSummaryRiskAnalysisCount(riskAnalysisSummaryList.size());
		// casefile requirements overview
		journalSummaryTO.setSummaryOverviewSupervisionLevel(journalForm.getOverviewSummarySupervisionLevel());
		journalSummaryTO.setOverviewActivitySummaryEntries(new ArrayList(journalForm.getOverviewActivitySummaryEntries()));
		journalSummaryTO.setOverviewActivitySummaryEntriesCount(new ArrayList(journalForm.getOverviewActivitySummaryEntries()).size());
		journalSummaryTO.setOverviewCalendarEventSummaryEntries(new ArrayList(journalForm.getOverviewCalendarEventSummaryEntries()));
		journalSummaryTO.setOverviewCalendarEventSummaryEntriesCount(new ArrayList(journalForm.getOverviewCalendarEventSummaryEntries()).size());
		
		journalSummaryTO.setOverviewFacilityActivitySummaryEntries(new ArrayList (journalForm.getOverviewFacilityActivitySummaryEntries()));
		journalSummaryTO.setOverviewFacilityActivitySummaryEntriesCount(new ArrayList(journalForm.getOverviewFacilityActivitySummaryEntries()).size());
		//added for Bug #37399
		int numProgressRisks=0;
		int numResProgRisks=0;
		Iterator progRiskIter=journalForm.getOverviewProgressRiskSummaryEntries().iterator();
		while(progRiskIter.hasNext())
		{
			JournalForm.OverviewProgressRiskBean riskBean = (JournalForm.OverviewProgressRiskBean)progRiskIter.next();
			if(riskBean.getAssessmentType()!=null && riskBean.getAssessmentType().equalsIgnoreCase("PROGRESS"))
				numProgressRisks++;
			else if (riskBean.getAssessmentType()!=null && riskBean.getAssessmentType().equalsIgnoreCase("RESIDENTIAL PROGRESS"))
				numResProgRisks++;
		}
		journalSummaryTO.setNumProgressRisks(numProgressRisks);
		journalSummaryTO.setNumResProgRisks(numResProgRisks);
		journalSummaryTO.setOverviewProgressRiskSummaryEntries(new ArrayList(journalForm.getOverviewProgressRiskSummaryEntries()));
		journalSummaryTO.setOverviewProgressRiskSummaryEntriesCount(new ArrayList(journalForm.getOverviewProgressRiskSummaryEntries()).size());
		
		
		aRequest.getSession().setAttribute("journalSummaryTO", journalSummaryTO);

		// tell BFO to save report if not a draft
		if(journalSummaryTO.getDraft() != null && journalSummaryTO.getDraft().equalsIgnoreCase("N")){
			aRequest.setAttribute("isPdfSaveNeeded", "true");
		}
		// generate report
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.CASEFILE_REVIEW_SUMMARY);
	
		// save the report if it is not a DRAFT Report - has to be done after report generation in BFO
		if(journalSummaryTO.getDraft() != null && journalSummaryTO.getDraft().equalsIgnoreCase("N")){
			byte[] reportByte = (byte[]) aRequest.getAttribute("pdfSavedReport");
			if(reportByte != null && reportByte.length > 0){
				SaveJournalSummaryDocEvent saveEvt = new SaveJournalSummaryDocEvent();	
				saveEvt.setCasefileId(journalSummaryTO.getCaseFileId());		
				saveEvt.setDocument(reportByte);
				saveEvt.setDocTypeCd(JournalDocResponseEvent.JOURNAL_CASE_REVIEW);
		
				CompositeResponse response1 = postRequestEvent(saveEvt);
				JournalDocResponseEvent respEvt = (JournalDocResponseEvent) // change when switched
						MessageUtil.filterComposite(response1, JournalDocResponseEvent.class);
				if( respEvt == null ){
					sendToErrorPage(aRequest, "error.generic", "Problems generating and saving report"); // Add to error collection for page
				}
			}
		
		}
	    return null;
	    
	    
	}
	
	/**
	 * validate if a date is a future date
	 * @param possibleFutureDate
	 * @return
	 */
	public boolean validateIfFutureDate(Date possibleFutureDate){
		
		boolean isFutureDate = false;	
		if(possibleFutureDate != null && !possibleFutureDate.equals("")){
			Calendar currentTimecal = Calendar.getInstance();
			Date currentDate = currentTimecal.getTime();
			if(currentDate.before(possibleFutureDate)){
				isFutureDate = true;
			}
		}
		
		return isFutureDate;
	}
	
	/**
	 * validate if a date is valid and not null and then return formatted string if valid.
	 * @param possibleDate
	 * @return
	 */
	public String validateDate(Date possibleDate){
		
		String formatedDateString = "";	
		
		if(possibleDate != null && !possibleDate.equals("")){
			formatedDateString = " - " + DateUtil.dateToString(possibleDate, DateUtil.DATE_FMT_1);
		}
		
		return formatedDateString;
	}
	
	/**
	 * calculate the supervision level from risk analysis records
	 * @param riskAnalysisAssessmentList
	 * @return
	 */
	public String getOverviewSupervisionLevel(ArrayList<RiskAnswerResponseEvent> riskAnswerResponseList, ArrayList<RiskAssessmentResponseEvent> riskAnalysisAssessmentList){
		String supervisionLevel = "";	
		if(riskAnalysisAssessmentList != null){
			Collections.sort(riskAnalysisAssessmentList, RiskAssessmentResponseEvent.CaseReviewJournalLatestRiskAssessmentComparator);
		
			outerLoop:
			for(RiskAnswerResponseEvent riskQuestionAnswer: riskAnswerResponseList){
				for(RiskAssessmentResponseEvent riskAssessment: riskAnalysisAssessmentList){
					if(riskAssessment != null && riskQuestionAnswer != null && riskAssessment.getAssessmentType() != null && riskQuestionAnswer.getRiskQuestionText() != null && 
							riskQuestionAnswer.getRiskQuestionText().contains("Supervision Level") && riskAssessment.getAssessmentType().equals("PROGRESS") && 
							riskAssessment.getRiskAnalysisId().equalsIgnoreCase(riskQuestionAnswer.getRiskAnalysisId())){
						String level = riskQuestionAnswer.getAnswerText().toUpperCase();
						if(level != null){
							supervisionLevel = level;
						}
						break outerLoop;
					}
				}
			}	
		}
		return supervisionLevel;
	}
	
	/**
	 * sort and calculate the overview activities collection for the activities overview part of case review report
	 * @param activityResponseList
	 * @return
	 */
	public ArrayList<JournalForm.OverviewActivityBean> getOverviewActivies(ArrayList<ActivityResponseEvent> activityResponseList){
		
		Map<String,Long> activityMap = new TreeMap<String,Long>();
		ArrayList<JournalForm.OverviewActivityBean> overviewActivitiesList = new ArrayList<JournalForm.OverviewActivityBean>();
		for(ActivityResponseEvent activityEvent: activityResponseList){
			String activityDescription = activityEvent.getActivityDesc();
			if(activityMap.containsKey(activityDescription)){
				Long occurrenceCounter = activityMap.get(activityDescription);
				occurrenceCounter++;
				activityMap.put(activityDescription, occurrenceCounter);
			}else{
				activityMap.put(activityDescription, new Long(1));
			}	
		}
		for(String key: activityMap.keySet()){
			JournalForm.OverviewActivityBean overviewActivityBean = new JournalForm.OverviewActivityBean();
			overviewActivityBean.setActivityDescription(key);
			overviewActivityBean.setQuantity(activityMap.get(key).toString());
			overviewActivitiesList.add(overviewActivityBean);
		}
		return overviewActivitiesList;
	}

	/**
	 * sort and calculate the overview facility activities collection for the facility activities overview part of case review report
	 * @param activityResponseList
	 * @return
	 */	 // overview Facility activities US 35963, TASK 43840
	
	public ArrayList<JournalForm.OverviewFacilityActivityBean> getOverviewFacilitiesActivies(ArrayList<FacilityActivityResponseEvent> activityResponseList){
		
		Map<String,Long> facilityActivityMap = new TreeMap<String,Long>();
		ArrayList<JournalForm.OverviewFacilityActivityBean> overviewFacilityActivitiesList = new ArrayList<JournalForm.OverviewFacilityActivityBean>();
		for(FacilityActivityResponseEvent activityEvent: activityResponseList){
			String facilityActivityDescription = activityEvent.getActivityDesc();
			if(facilityActivityMap.containsKey(facilityActivityDescription)){
				Long occurrenceCounter = facilityActivityMap.get(facilityActivityDescription);
				occurrenceCounter++;
				facilityActivityMap.put(facilityActivityDescription, occurrenceCounter);
			}else{
			    facilityActivityMap.put(facilityActivityDescription, new Long(1));
			}	
		}
		for(String key: facilityActivityMap.keySet()){
			JournalForm.OverviewFacilityActivityBean overviewFacilityActivityBean = new JournalForm.OverviewFacilityActivityBean();
			overviewFacilityActivityBean.setFacilityActivityDescription(key);
			overviewFacilityActivityBean.setQuantity(facilityActivityMap.get(key).toString());
			overviewFacilityActivitiesList.add(overviewFacilityActivityBean);
		}
		return overviewFacilityActivitiesList;
	}	
	
	/**
	 * sort and calculate the overview calendar event collection for the events overview part of case review report
	 * @param calendarEventsResponseList
	 * @return
	 */
	public ArrayList getOverviewCalendarEvents(ArrayList<CalendarServiceEventResponseEvent> calendarEventsResponseList){		
		Map<String,JournalForm.OverviewCalendarEventBean> calendarEventMap = new TreeMap<String,JournalForm.OverviewCalendarEventBean>();
		ArrayList<JournalForm.OverviewCalendarEventBean> overviewCalendarEventsList = new ArrayList<JournalForm.OverviewCalendarEventBean>();
		for(CalendarServiceEventResponseEvent calendarEvent: calendarEventsResponseList){
			String calendarEventType = calendarEvent.getEventType();
			if(calendarEventType != null){
				if(calendarEventMap.containsKey(calendarEventType)){
					JournalForm.OverviewCalendarEventBean calendarBean = calendarEventMap.get(calendarEventType);
					calendarBean.addCalendarEvent(calendarEvent);
				}else{
					JournalForm.OverviewCalendarEventBean newBean = new JournalForm.OverviewCalendarEventBean();
					newBean.addCalendarEvent(calendarEvent);
					calendarEventMap.put(calendarEventType,newBean);
				}
			}
		}
		for(String key: calendarEventMap.keySet()){
			JournalForm.OverviewCalendarEventBean calendarBean = calendarEventMap.get(key);
			ArrayList<CalendarServiceEventResponseEvent> calendarEventList = calendarBean.getCalendarEvents();
			long scheduled = 0;
			long attended = 0;
			long absent = 0;
			long excused = 0;
			long sumDenominator = 0;
			double percentAttendedDouble = 0.0;
			int percentAttendedInt = 0;
			for(CalendarServiceEventResponseEvent event:calendarEventList){
				if(event.getJuvenileAttendanceStatus() != null){
					calendarBean.setEventDescription(event.getEventType());				
					if(event.getJuvenileAttendanceStatus().equalsIgnoreCase("ATTENDED")){
						attended++;
					}else if(event.getJuvenileAttendanceStatus().equalsIgnoreCase("ABSENT")){
						absent++;
					}else if(event.getJuvenileAttendanceStatus().equalsIgnoreCase("EXCUSED")){
						excused++;
					}else {
						scheduled ++;
					}
					sumDenominator = attended + absent + excused;
					if(sumDenominator != 0){
						percentAttendedDouble = 100.00 * ((float)attended/sumDenominator);
						DecimalFormat percentFormat = new DecimalFormat("###");
						percentAttendedDouble = new Double(percentFormat.format(percentAttendedDouble));
						percentAttendedInt = (int)percentAttendedDouble;				
					}				
					calendarBean.setScheduled(scheduled);
					calendarBean.setExcused(excused);
					calendarBean.setAbsent(absent);
					calendarBean.setAttended(attended);
					calendarBean.setPercentAttended(percentAttendedInt);
				}
			}		
			overviewCalendarEventsList.add(calendarBean);
		}
		
		return overviewCalendarEventsList;
	}
	
	/**
	 * sort MAYSI and Historical Assignment records and determine which ones should be in the final collection of both maysiAssessmentSummaryEntries 
	 * and historicalAssignmentSummaryEntries collections based on business rules
	 * @param journalForm
	 */
	private void populateValidMaysiAndHistoricalAssignments(JournalForm journalForm){
		
		ArrayList<MAYSISearchResultResponseEvent> maysiAssessmentSummaryEntries = (ArrayList)journalForm.getMaysiAssessmentSummaryEntries();
		ArrayList<JPOAssignmentHistoryViewResponseEvent> historicalAssignmentSummaryEntries = (ArrayList)journalForm.getHistoricalAssignmentSummaryEntries();
		ArrayList<MAYSISearchResultResponseEvent> filteredMaysiAssessmentSummaryEntries =  new ArrayList();
		// only keep Maysi records that also are in the historical assigned records (have been assigned)
		for(MAYSISearchResultResponseEvent maysiAssessment:maysiAssessmentSummaryEntries){
			for(JPOAssignmentHistoryViewResponseEvent historicalAssignment:historicalAssignmentSummaryEntries){				
				if(maysiAssessment != null && historicalAssignment != null){
					if(maysiAssessment.getReferralNumber().equals(historicalAssignment.getReferralNumber()) && !maysiAssessment.getAssessmentOptionId().equals("T")){
						filteredMaysiAssessmentSummaryEntries.add(maysiAssessment);
						break;
					}
				}
			}			
		}
		
		// determine subsequent done list
		// there are two cases: 1 - where there is Assignment and only maysi record with "Test Not Administered" and 2- Assignment records with NO Maysi at all - both go in NO MAYSI
		boolean isNonDoneRecord = true; // are there filtered maysi records for a given referral that are NOT "Test Not Administered"? If yes, then do do not include.		
		MAYSISearchResultResponseEvent nonDoneMaysi = null;
		// 1- loop to determine maysi that has assignment, and is not done in maysi without a "Test Not Administered" status
		for(JPOAssignmentHistoryViewResponseEvent historicalAssignment:historicalAssignmentSummaryEntries){	
			for(MAYSISearchResultResponseEvent maysiAssessment:maysiAssessmentSummaryEntries){
				if(maysiAssessment.getReferralNumber().equals(historicalAssignment.getReferralNumber())){
					if (!maysiAssessment.getAssessmentOptionId().equals("T")){
						isNonDoneRecord = false;	
					}else{
						nonDoneMaysi = maysiAssessment;
					}
				}
			}
			if(isNonDoneRecord){
				filteredMaysiAssessmentSummaryEntries.add(nonDoneMaysi);
			}
		}
		journalForm.setNoMaysiSectionMaysiAndAssignmentSummaryEntries(filteredMaysiAssessmentSummaryEntries);
		// loop to determine records where there is an assignment record, and absolutely no matching maysi record
		boolean isAssignmentWithNoMatchingMaysi = true; 
		ArrayList<JPOAssignmentHistoryViewResponseEvent> noMaysiSectionAssignmentWithNoMaysiSummaryEntries = new ArrayList();
		for(JPOAssignmentHistoryViewResponseEvent historicalAssignment:historicalAssignmentSummaryEntries){
			isAssignmentWithNoMatchingMaysi = true;
			if(maysiAssessmentSummaryEntries.size() == 0){
				noMaysiSectionAssignmentWithNoMaysiSummaryEntries.add(historicalAssignment);
			}
			for(MAYSISearchResultResponseEvent maysiAssessment:maysiAssessmentSummaryEntries){
				if(maysiAssessment.getReferralNumber().equals(historicalAssignment.getReferralNumber())){
					isAssignmentWithNoMatchingMaysi= false;
				}
			}
			if(isAssignmentWithNoMatchingMaysi){
				noMaysiSectionAssignmentWithNoMaysiSummaryEntries.add(historicalAssignment);
			}
		}
		journalForm.setNoMaysiSectionAssignmentWithNoMaysiSummaryEntries(noMaysiSectionAssignmentWithNoMaysiSummaryEntries);	
	}
	
	
	/**
	 * sort and calculate the overview risk progress risk collection for the events overview part of case review report
	 * @param riskAnswerResponseList
	 * @param riskAnalysisAssessmentList
	 * @return
	 */
	public ArrayList getOverviewProgressRisk(ArrayList<RiskAnswerResponseEvent> riskAnswerResponseList, ArrayList<RiskAssessmentResponseEvent> riskAnalysisAssessmentList, String supervisionCat ){
		
		// get each unique question and create a bean, and collect the associated events on corresponding event collection
		Map<String,JournalForm.OverviewProgressRiskBean> progressRiskMap = new TreeMap<String,JournalForm.OverviewProgressRiskBean>();
		ArrayList<JournalForm.OverviewProgressRiskBean> overviewProgressRiskList = new ArrayList<JournalForm.OverviewProgressRiskBean>();
		for(RiskAnswerResponseEvent riskQuestionEvent:riskAnswerResponseList){
			String riskQuestion = riskQuestionEvent.getRiskQuestionText();
			if(riskQuestion != null && !riskQuestion.equalsIgnoreCase("") ){
				if(progressRiskMap.containsKey(riskQuestion)){
					JournalForm.OverviewProgressRiskBean riskBean = progressRiskMap.get(riskQuestion);
					riskBean.addRiskQuestionEvent(riskQuestionEvent);
				}else{
					JournalForm.OverviewProgressRiskBean newBean = new JournalForm.OverviewProgressRiskBean();
					newBean.addRiskQuestionEvent(riskQuestionEvent);
					progressRiskMap.put(riskQuestion,newBean);
				}
			}
		}
		// take each bean and use event collection on it to calculate scores and dates
		for(String key: progressRiskMap.keySet()){
			JournalForm.OverviewProgressRiskBean riskBean = progressRiskMap.get(key);
			ArrayList<RiskAnswerResponseEvent> beanEventList = riskBean.getRiskAnswerEvents();
		
			if(beanEventList.size() > 1){
				int counter = 0;
				for(RiskAnswerResponseEvent event:beanEventList){
					// 12168 - Exclude any risk questions that are not weighted - which includes RADIO, DATE, TEXTAREA, TEXTBOX, T
					if(event.getRiskQuestionText() != null && event.getUiControlType() != null && !event.getUiControlType().equalsIgnoreCase("RADIO") && !event.getUiControlType().equalsIgnoreCase("DATE")
							&& !event.getUiControlType().equalsIgnoreCase("TEXTAREA") && !event.getUiControlType().equalsIgnoreCase("TEXTBOX") && !event.getUiControlType().equalsIgnoreCase("QUESTIONHEADER")
							&& ( (isProgressRisk(event, riskAnalysisAssessmentList) && !supervisionCat.equalsIgnoreCase("AR")) || (isResProgressRisk(event, riskAnalysisAssessmentList) && supervisionCat.equalsIgnoreCase("AR")))){
						String assmntType=getRiskAssessmentType(event, riskAnalysisAssessmentList);
						riskBean.setAssessmentType(getRiskAssessmentType(event, riskAnalysisAssessmentList));
						riskBean.setProgressRiskQuestionText(key);
						if(event.getQuestionNumber() != null){
							riskBean.setQuestionNumber(event.getQuestionNumber());
						}
						if(counter == 0){
							riskBean.setRecentEntryDate(event.getEntryDate());
							riskBean.setOldestEntryDate(event.getEntryDate());
							riskBean.setRecentWeightScore(event.getWeight());
							riskBean.setOldestWeightScore(event.getWeight());
							counter ++;
						}else{
							if(event.getEntryDate().after(riskBean.getRecentEntryDate()) ){
								riskBean.setRecentEntryDate(event.getEntryDate());
								riskBean.setRecentWeightScore(event.getWeight());
								counter++;
							}else if(event.getEntryDate().before(riskBean.getOldestEntryDate()) ){
								riskBean.setOldestEntryDate(event.getEntryDate());
								riskBean.setOldestWeightScore(event.getWeight());
								counter++;
							}else{
								counter++;
							}
								
						}
						
					}
				}
				// only add the bean if there were at least two events not of type radio.
				if(counter > 1){
					overviewProgressRiskList.add(riskBean);
				}
			}
		}
				
		// go through the risk beans, calculate the risk, and set the indicators to true depending on the result
		for(JournalForm.OverviewProgressRiskBean bean:overviewProgressRiskList){
			if(bean.getRecentWeightScore() >  bean.getOldestWeightScore()){
				bean.setRegression(true);									
			}else if(bean.getRecentWeightScore() <  bean.getOldestWeightScore()){
				bean.setImprovement(true);
			}else{
				bean.setNoChange(true);
			}
		}
		return overviewProgressRiskList;
	}
	
	/**
	 * 
	 * @param event
	 * @param riskAnalysisAssessmentList
	 * @return
	 */
	public boolean isProgressRisk(RiskAnswerResponseEvent event, ArrayList<RiskAssessmentResponseEvent> riskAnalysisAssessmentList ){
			
		boolean isProgressRisk = false;
		for( RiskAssessmentResponseEvent assessment:riskAnalysisAssessmentList ){
			if(assessment.getRiskAnalysisId().equalsIgnoreCase(event.getRiskAnalysisId())){
				if(assessment.getAssessmentType().equals("PROGRESS")){
					isProgressRisk = true;
				}
			}
		}
		
		return isProgressRisk;
		
	}
	
	/**
	 * 
	 * @param event
	 * @param riskAnalysisAssessmentList
	 * @return
	 */
	public boolean isResProgressRisk(RiskAnswerResponseEvent event, ArrayList<RiskAssessmentResponseEvent> riskAnalysisAssessmentList ){
			
		boolean isResProgressRisk = false;
		for( RiskAssessmentResponseEvent assessment:riskAnalysisAssessmentList ){
			if(assessment.getRiskAnalysisId().equalsIgnoreCase(event.getRiskAnalysisId())){
				if(assessment.getAssessmentType().equals("RESIDENTIAL PROGRESS")){
					isResProgressRisk = true;
				}
			}
		}
		
		return isResProgressRisk;
		
	}
	/**
	 * 
	 * @param event
	 * @param riskAnalysisAssessmentList
	 * @return
	 */
	public String getRiskAssessmentType(RiskAnswerResponseEvent event, ArrayList<RiskAssessmentResponseEvent> riskAnalysisAssessmentList ){
			
		
		for( RiskAssessmentResponseEvent assessment:riskAnalysisAssessmentList ){
			if(assessment.getRiskAnalysisId().equalsIgnoreCase(event.getRiskAnalysisId())){
				return assessment.getAssessmentType();
				}
		}	
		return null;		
	}
}

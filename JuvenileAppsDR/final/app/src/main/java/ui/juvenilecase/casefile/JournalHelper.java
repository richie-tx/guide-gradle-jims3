/*
 * Created on May 20th 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.casefile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import messaging.casefile.GetJournalEntriesEvent;
import messaging.juvenilecase.reply.RiskAnswerResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.RiskAnalysisConstants;
import naming.UIConstants;
import ui.contact.UIContactHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.casefile.form.JournalForm;

/**
 * @author ugopinath TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class JournalHelper
{
	public static final Object	AMPERCASEFILEID		= "&CASEFILE_ID=";
	public static final Object	AMPERJUVENILENAME	= "&juvenilename=";
	private static final String COMMUNITY_TITLE_STR = "Community Screening/Forms" ;
	private static final String RESIDENTIAL_TITLE_STR = "Residential Screening/Forms Impressions/Needs" ;
	private static final String GANG_TITLE_STR = "Gang Screening/Forms" ;
	private static final String PROGRESS_TITLE_STR = "Progress Screening/Forms" ;
	private static final String RESIDENTIAL_PRO_TITLE_STR = "Residential Progress Screening/Forms";
	

	/**
	 * retrieve the main Journal Entries based on the search criteria. Result used for both search result 
	 * page and for the main Journal Report created through the button on search results page.
	 * @param jf
	 * @param casefileId
	 * @return
	 */
	public static CompositeResponse getJournalEntries( JournalForm jf, String casefileId, String supervisionCategory )
	{
		GetJournalEntriesEvent entries = (GetJournalEntriesEvent)
				EventFactory.getInstance( JuvenileCasefileControllerServiceNames.GETJOURNALENTRIES );

		String aDate = jf.getFromDate();
		
		// see if the user specified a start date
		if( aDate != null && aDate.length() > 0 )
		{
			entries.setFromDate( DateUtil.stringToDate( aDate, DateUtil.DATE_FMT_1 ) );
		}

		// see if the user specified an end date
		aDate = jf.getEndDate();
		if( aDate != null && aDate.length() > 0 )
		{
			entries.setEndDate( DateUtil.stringToDate( aDate, DateUtil.DATE_FMT_1 ) );
		}
		else
		{
			entries.setEndDate( DateUtil.getCurrentDate() );
		}
		
		entries.setCasefileId( casefileId );
		entries.setJournalCategoryCd( jf.getJournalCategoryCd() );
		entries.setJuvenileId( jf.getJuvenileId() );
		entries.setUserId( jf.getSelectedOfficerId() );
		entries.setSupervisionCategory(supervisionCategory);
		CompositeResponse resp = MessageUtil.postRequest( entries );

		return resp;
	}
	
	/**
	 * retrieve the Journal Summary Entries based on the search criteria. Result used for the 
	 * Journal Summary Report created through the button Journal Summary report on search results page.
	 * @param jf
	 * @param casefileId
	 * @return
	 */
	public static CompositeResponse getCaseReviewJournalSummaryEntries( JournalForm jf, String casefileId, String supervisionCategory )
	{
		GetJournalEntriesEvent entries = (GetJournalEntriesEvent)
				EventFactory.getInstance( JuvenileCasefileControllerServiceNames.GETJOURNALENTRIES );

		String fromDate = jf.getFromDate();
		String toDate = jf.getEndDate();
		
		// Rule 1: If fromDate is given, but toDate is not, then go out 30 Days from the given toDate.
		if(fromDate != null && !fromDate.equals("") && (toDate == null || toDate.equals(""))){
			Date fromDateValue = DateUtil.stringToDate( fromDate, DateUtil.DATE_FMT_1 );
			Calendar cal = Calendar.getInstance();
			cal.setTime(fromDateValue);
			entries.setFromDate(fromDateValue);
			cal.add(Calendar.DAY_OF_MONTH, +30);
			Date endDate = cal.getTime();
			
			entries.setEndDate( endDate );
		}
		// Rule 2: If fromDate and toDate are both given, then use these for date range.
		else if(fromDate != null && !fromDate.equals("") && toDate != null && !toDate.equals("")){
			entries.setFromDate( DateUtil.stringToDate( fromDate, DateUtil.DATE_FMT_1 ) );
			entries.setEndDate( DateUtil.stringToDate( toDate, DateUtil.DATE_FMT_1 ) );
		}
		// Rule 3: If neither FromDate OR ToDate is  given, then go back 30 DAYS from current date for date range.  
		else {
			Calendar cal = Calendar.getInstance();
			Date endDate = cal.getTime();
			cal.add(Calendar.DAY_OF_MONTH, -30);
			Date beginDate = cal.getTime();
			
			entries.setFromDate(beginDate );
			entries.setEndDate(endDate);
		}
		
		
		entries.setCasefileId( casefileId );
		entries.setJournalCategoryCd( PDJuvenileCaseConstants.JOURNAL_CASE_REVIEW_SUMMARY );
		entries.setJuvenileId( jf.getJuvenileId() );
		entries.setSupervisionCategory(supervisionCategory);
		entries.setUserId( "" );
		CompositeResponse resp = MessageUtil.postRequest( entries );
		
		// set dates on form for report
		jf.setFromDate(DateUtil.dateToString(entries.getFromDate(), DateUtil.DATE_FMT_1));
		jf.setEndDate(DateUtil.dateToString(entries.getEndDate(), DateUtil.DATE_FMT_1));
		
		// retrieve the referrals/assignment records - not part of normal call to get entries
		Collection JuvenileCasefileReferallsList = UIJuvenileCaseworkHelper.fetchJuvenileCasefileReferralsList(
				casefileId, jf.getJuvenileId());
		jf.setCasefileReferralsSummaryEntries(JuvenileCasefileReferallsList);

		return resp;
	}

	/* given an array of events and an event type, check each
	 * event type and add it to it's own container. when we do
	 * add an object to the container, set it to null, so we 
	 * don't have to reflect on its type in future invocations. 
	 * 
	 * this is a specialization of the function found in 
	 * MessageUtil, but this one operates on an array, 
	 * versus a collection
	 */
	public static Collection compositeToCollection( Object[] eventsIN, Class eventClass )
	{
		if( eventsIN.length == 0 )
		{ // ensure we still have events in the array
			return null ;
		}

		int len = eventsIN.length  ;
		Collection eventCollection = new ArrayList( );
		
		((ArrayList)eventCollection).ensureCapacity( len ) ;
		/* use a reference to reduce the amount of array accesses, 
		 * since java checks for array-out-of-bounds 
		 * (i.e. - think, performance :) 
		 */
		Object currObj ; 
		
		if( len %2 == 0 )
		{
			/*
			 * if the array size is evenly divisible by 2,
			 * then operate on two objects for each iteration
			 */
			Object secObj ;
			for( int i = (len -1); i > 0 ; i -= 2 )
			{ // always faster to compare to zero, rather
				// than iterating up to some 'count' variable
				currObj = eventsIN[i] ;
				secObj = eventsIN[i -1] ;
				if( currObj != null  &&  eventClass.isInstance(currObj) )
				{
					eventCollection.add(currObj);
					/* set it to null to signify we've already 
					 * visited this object, since we'll be traversing
					 * the array on future calls to this function. 
					 */
					eventsIN[i] = null ; 
				}
				if( secObj != null  &&  eventClass.isInstance(secObj) )
				{
					eventCollection.add(secObj);
					/* set it to null to signify we've already 
					 * visited this object, since we'll be traversing
					 * the array on future calls to this function. 
					 */
					eventsIN[i -1] = null ; 
				}
			}
		}
		else
		{ // array has odd number of objects
			
			for( int i = len; --i >= 0 ; /*empty*/ )
			{
				currObj = eventsIN[i] ;
				if( currObj != null  &&  eventClass.isInstance(currObj) )
				{
					eventCollection.add(currObj);
					/* set it to null to signify we've already 
					 * visited this object, since we'll be traversing
					 * the array on future calls to this function. 
					 */
					eventsIN[i] = null ; 
				}
			}
		}

		return eventCollection;
	}

	/* given an array of events, check for one of several
	 * question types - if the object matches, create a new
	 * riskJournal object, set a unique identifying string
	 * and add it to the risk container
	 */
	public static Collection getRiskEntriesFromEventBag( Object[] eventsIN )
	{
		String IbtlevelC="" ;
		String IbtprogressC="";
		String planofjuvC="";
		String planofjpoC="";
		String planofparentC="";
		String planofotherC=""; 
		String planofAdd="";
		
		Collection riskEvents = compositeToCollection( eventsIN, RiskAnswerResponseEvent.class );
		int eventCount = riskEvents.size() ;
		
		Map progressRiskSupervisionMonthsMap = new HashMap();
		Map progressRiskSupervisionLevelMap = new HashMap();
		Map progressRiskCommentsMap = new HashMap();
		Map progressRiskJournalMap = new HashMap();
		JournalForm.RiskAnalysisJournal progressRiskJournal = null;
				
		if( riskEvents != null && eventCount > 0 )
		{	
			Collection journalEntries = new ArrayList();
			((ArrayList)journalEntries).ensureCapacity( eventCount ) ;

			if( eventCount > 1)
			{
				Collections.sort( (List)riskEvents );
			}
			
			Object[] rare = riskEvents.toArray() ;

			String questionText = UIConstants.EMPTY_STRING ;
			JournalForm.RiskAnalysisJournal riskJournal = null;
			RiskAnswerResponseEvent riskResp = null ;
			for( int i = eventCount; --i >= 0; /*empty*/ )
			{ // always faster to compare to zero, rather
				// than iterating up to some 'count' variable
				riskResp = (RiskAnswerResponseEvent)rare[ i ] ;
				questionText = riskResp.getRiskQuestionText() ;
				
				if( questionText.equalsIgnoreCase( UIConstants.COMMUNITY_ANALYSIS_COMMENTS ) )
				{
					riskJournal = new JournalForm.RiskAnalysisJournal();
					riskJournal.setTitle( COMMUNITY_TITLE_STR );
					riskJournal.setComments( riskResp.getAnswerText() );
					riskJournal.setEntryDate( riskResp.getEntryDate() );
					String userLogonId = riskResp.getCreateUserID();
					if (userLogonId != null) {
						String fullName = UIContactHelper.getUserProfileName(userLogonId);
						riskJournal.setCreatedBy(fullName);
					}
					journalEntries.add( riskJournal );
				}
				else if( questionText.equalsIgnoreCase( UIConstants.RESIDENTIAL_ANALYSIS_COMMENTS ) )
				{
					riskJournal = new JournalForm.RiskAnalysisJournal();
					riskJournal.setTitle( RESIDENTIAL_TITLE_STR );
					riskJournal.setComments( riskResp.getAnswerText() );
					riskJournal.setEntryDate( riskResp.getEntryDate() );
					String userLogonId = riskResp.getCreateUserID();
					if (userLogonId != null) {
						String fullName = UIContactHelper.getUserProfileName(userLogonId);
						riskJournal.setCreatedBy(fullName);
					}
					journalEntries.add( riskJournal );
				}else if( questionText.equalsIgnoreCase( UIConstants.GANG_RISK_ANALYSIS_COMMENTS)){
					riskJournal = new JournalForm.RiskAnalysisJournal();
					riskJournal.setTitle( GANG_TITLE_STR );
					riskJournal.setComments( riskResp.getAnswerText() );
					riskJournal.setEntryDate( riskResp.getEntryDate() );
					String userLogonId = riskResp.getCreateUserID();
					if (userLogonId != null) {
						String fullName = UIContactHelper.getUserProfileName(userLogonId);
						riskJournal.setCreatedBy(fullName);
					}
					journalEntries.add( riskJournal );
				}
		//36907 Residential risk progress in Journal Entries
				else if ( riskResp.getAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS) &&(questionText.equalsIgnoreCase(UIConstants.RESIDENTIAL_PROG_IBT_LEVEL)||questionText.equalsIgnoreCase(UIConstants.RESIDENTIAL_PROG_IBT_PROGRESS)|| questionText.equalsIgnoreCase(UIConstants.RESIDENTIAL_PROG_ADDITIONAL) || questionText.equalsIgnoreCase( UIConstants.RESIDENTIAL_PROGRESS_COMMENTS)|| questionText.equalsIgnoreCase(UIConstants.RESIDENTIAL_PROG_JUVENILE)
						||questionText.equalsIgnoreCase( UIConstants.RESIDENTIAL_PROG_PARENT)||questionText.equalsIgnoreCase( UIConstants.RESIDENTIAL_PROG_OTHER))){
					
					if (questionText.equalsIgnoreCase( UIConstants.RESIDENTIAL_PROG_IBT_LEVEL)){
						IbtlevelC = riskResp.getAnswerText();
					}
								
					if (questionText.equalsIgnoreCase(UIConstants.RESIDENTIAL_PROG_IBT_PROGRESS)) {
						IbtprogressC = riskResp.getAnswerText();
					}
					if (questionText.equalsIgnoreCase(UIConstants.RESIDENTIAL_PROG_ADDITIONAL)){
						planofAdd= riskResp.getAnswerText();
					}
					if (questionText.equalsIgnoreCase( UIConstants.RESIDENTIAL_PROGRESS_COMMENTS)){
						planofjpoC = riskResp.getAnswerText();
					}
					if (questionText.equalsIgnoreCase( UIConstants.RESIDENTIAL_PROG_JUVENILE)){
						planofjuvC = riskResp.getAnswerText();
						}
					if (questionText.equalsIgnoreCase( UIConstants.RESIDENTIAL_PROG_PARENT)){
						planofparentC = riskResp.getAnswerText();
					}
									
					if (questionText.equalsIgnoreCase( UIConstants.RESIDENTIAL_PROG_OTHER)){
						planofotherC = riskResp.getAnswerText();
						
						riskJournal = new JournalForm.RiskAnalysisJournal();
						riskJournal.setTitle(RESIDENTIAL_PRO_TITLE_STR);
						
						StringBuilder residentialProgressRisk = new StringBuilder();
						
						residentialProgressRisk.append("<b>"+UIConstants.RESIDENTIAL_PROG_IBT_LEVEL+"</b>");
						residentialProgressRisk.append(":");
						residentialProgressRisk.append(IbtlevelC);											
						residentialProgressRisk.append(";");
						residentialProgressRisk.append("<br />");
						residentialProgressRisk.append("<b>"+"IBT Progress"+"</b>");
						residentialProgressRisk.append(":");
						residentialProgressRisk.append(IbtprogressC);
						residentialProgressRisk.append(";");
						residentialProgressRisk.append("<br />");
						residentialProgressRisk.append("<b>"+UIConstants.RESIDENTIAL_PROG_JUVENILE+"</b>");
						residentialProgressRisk.append(":");
						residentialProgressRisk.append(planofjuvC);
						residentialProgressRisk.append(";");
						residentialProgressRisk.append("<br />");
						residentialProgressRisk.append("<b>"+UIConstants.RESIDENTIAL_PROGRESS_COMMENTS+"</b>");
						residentialProgressRisk.append(":");
						residentialProgressRisk.append(planofjpoC);
						residentialProgressRisk.append(";");
						residentialProgressRisk.append("<br />");
						residentialProgressRisk.append("<b>"+UIConstants.RESIDENTIAL_PROG_PARENT+"</b>");
						residentialProgressRisk.append(":");
						residentialProgressRisk.append(planofparentC);
						residentialProgressRisk.append(";");
						residentialProgressRisk.append("<br />");
						residentialProgressRisk.append("<b>"+"Plan of Action for Other"+"</b>");
						residentialProgressRisk.append(":");
						residentialProgressRisk.append(planofotherC);
						residentialProgressRisk.append(";");
						residentialProgressRisk.append("<br />");
						residentialProgressRisk.append("<b>"+"Additional Comments during evaluation period"+"</b>");
						residentialProgressRisk.append(":");
						residentialProgressRisk.append(planofAdd);
						residentialProgressRisk.append(";");
						residentialProgressRisk.append("<br />");
			
						riskJournal.setComments(residentialProgressRisk.toString());	
						riskJournal.setEntryDate(riskResp.getEntryDate());
						String userLogonId = riskResp.getCreateUserID();
						if (userLogonId != null) {
							String fullName = UIContactHelper.getUserProfileName(userLogonId);
							riskJournal.setCreatedBy(fullName);
						}
						journalEntries.add(riskJournal);
						IbtlevelC="" ;
						IbtprogressC="";
						planofjuvC="";
						planofjpoC="";
						planofparentC="";
						planofotherC=""; 
						planofAdd="";
						
						
					} 
					
					
					
				
				}
				
				else if( riskResp.getAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_PROGRESS) && (questionText.equalsIgnoreCase( UIConstants.PROGRESS_ANALYSIS_COMMENTS ) 
						|| questionText.equalsIgnoreCase( UIConstants.PROGRESS_ANALYSIS_SUPERVISION_MONTHS ) 
						|| questionText.equalsIgnoreCase( UIConstants.PROGRESS_ANALYSIS_SUPERVISION_LEVEL )))
				{
					//Group Progress answers by riskAnalysisId because a Progress assessment can be done more than once.
					if (progressRiskJournalMap.get(riskResp.getRiskAnalysisId()) == null){
						progressRiskJournal= new JournalForm.RiskAnalysisJournal();
						progressRiskJournal.setTitle( PROGRESS_TITLE_STR );
						progressRiskJournal.setEntryDate( riskResp.getEntryDate() );
						String userLogonId = riskResp.getCreateUserID();
						if (userLogonId != null) {
							String fullName = UIContactHelper.getUserProfileName(userLogonId);
							progressRiskJournal.setCreatedBy(fullName);
						}
						progressRiskJournalMap.put(riskResp.getRiskAnalysisId(), progressRiskJournal);
					}
					if( questionText.equalsIgnoreCase( UIConstants.PROGRESS_ANALYSIS_COMMENTS )){
						progressRiskCommentsMap.put(riskResp.getRiskAnalysisId(), riskResp.getAnswerText());
					} else if( questionText.equalsIgnoreCase( UIConstants.PROGRESS_ANALYSIS_SUPERVISION_MONTHS ) ){
						progressRiskSupervisionMonthsMap.put(riskResp.getRiskAnalysisId(), UIConstants.PROGRESS_ANALYSIS_SUPERVISION_MONTHS  + ":" + riskResp.getAnswerText());
					} else if( questionText.equalsIgnoreCase( UIConstants.PROGRESS_ANALYSIS_SUPERVISION_LEVEL ) ){
						progressRiskSupervisionLevelMap.put(riskResp.getRiskAnalysisId(), UIConstants.PROGRESS_ANALYSIS_SUPERVISION_LEVEL  + ":" + riskResp.getAnswerText());
					} 
				}
			} // for
				
			if (progressRiskJournalMap.size() > 0){
				Set riskIdSet = progressRiskJournalMap.keySet();
				List <String> riskIdList = CollectionUtil.iteratorToList(riskIdSet.iterator());
				String riskId = null;
				String progressRiskSupervisionMonths = null;
				String progressRiskSupervisionLevel = null;
				String progressRiskComments = null;

				for (int i = 0; i < riskIdList.size(); i++) {
					riskId = riskIdList.get(i);
					progressRiskJournal = (JournalForm.RiskAnalysisJournal) progressRiskJournalMap.get(riskId);
					progressRiskSupervisionMonths = (String) progressRiskSupervisionMonthsMap.get(riskId);
					progressRiskSupervisionLevel = (String) progressRiskSupervisionLevelMap.get(riskId);
					progressRiskComments = (String) progressRiskCommentsMap.get(riskId);
					StringBuffer sb = new StringBuffer();
					if (progressRiskSupervisionMonths != null){
						sb.append(progressRiskSupervisionMonths);
						sb.append(" ");
					}
					if (progressRiskSupervisionLevel != null){
						sb.append(progressRiskSupervisionLevel);
						sb.append("; ");
					}
					if (progressRiskComments != null){
						sb.append(progressRiskComments);
					}
					if (sb.length() > 0){
						progressRiskJournal.setComments(sb.toString());
						journalEntries.add(progressRiskJournal);
					}
					sb = null;
					progressRiskJournal = null;
				}
				riskId = null;
				riskIdSet = null;
				progressRiskSupervisionMonths = null;
				progressRiskSupervisionLevel = null;
				progressRiskComments = null;
				riskIdList = null;
			}
			
			progressRiskJournalMap.clear();
			progressRiskJournalMap = null;
			progressRiskSupervisionMonthsMap.clear();
			progressRiskSupervisionMonthsMap = null;
			progressRiskSupervisionLevelMap.clear();
			progressRiskSupervisionLevelMap = null;
			progressRiskJournal = null;
			
			return( journalEntries );
		}
		
		return( null );
	}
}

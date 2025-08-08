//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\casefile\\action\\DisplayJuvenileCasefileJournalSearchAction.java

package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ujac.util.BeanComparator;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.casefile.reply.FacilityActivityResponseEvent;
import messaging.casefile.reply.GoalJournalResponseEvent;
import messaging.casefile.reply.TraitJournalResponseEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.RiskAnswerResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.DateUtil;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.JournalHelper;
import ui.juvenilecase.casefile.form.JournalForm;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.JuvenileProfileCasefileListForm;
import ui.juvenilecase.form.JuvenileProfileForm;

public class DisplayJuvenileJournalListAction extends JIMSBaseAction
{
    private static final String BRIEFING_FORM_STR = "juvenileBriefingDetailsForm";

    /**
     * @roseuid 47E25BDE0247
     */
    public DisplayJuvenileJournalListAction()
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
    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	HttpSession session = aRequest.getSession();
	JuvenileBriefingDetailsForm form = (JuvenileBriefingDetailsForm) session.getAttribute(BRIEFING_FORM_STR);

	JuvenileProfileForm f = UIJuvenileHelper.getHeaderForm(aRequest);

	JournalForm journalForm = (JournalForm) aForm;

	// Collection of selected casefileIds
	String selectedValues[] = journalForm.getSelectedCasefiles();
	List<String> casefileIds = Arrays.asList(selectedValues[0].split(","));
	journalForm.setSelectedValues(casefileIds.toString());

	HashMap<String, String> map = new HashMap<String, String>();
	Collection casefiles = form.getCasefiles();
	if (casefiles != null)
	{
	    Iterator iter = casefiles.iterator();
	    while (iter.hasNext())
	    {
		JuvenileCasefileSearchResponseEvent respEvt = (JuvenileCasefileSearchResponseEvent) iter.next();
		if (casefileIds.contains(respEvt.getSupervisionNum()))
		{
		    // Adding to map for ease of access
		    map.put(respEvt.getSupervisionNum(), respEvt.getSupervisionType());
		}
	    }
	}
	
	// If Dates are given look through all casefiles rry
	if( StringUtils.isNotBlank( journalForm.getFromDate() )) {
	    
	    casefileIds = new ArrayList<String>();
	    for (Iterator i = casefiles.iterator(); i.hasNext(); ) {  // No ForUpdate
		JuvenileCasefileSearchResponseEvent obj  =  (JuvenileCasefileSearchResponseEvent) i.next();
		if(!PDJuvenileCaseConstants.CASESTATUS_PENDING_DESCRIPTION.equalsIgnoreCase(obj.getCaseStatus())) {
		    
			casefileIds.add (obj.getSupervisionNum() );
		}	        
	    }	    
	}

	if (casefileIds != null)
	{
	    //journalForm.clearAll();
	    journalForm.setJuvenileId(form.getJuvenileNum());
	    String supervisionNum = UIConstants.EMPTY_STRING;
	    /* while we have a Casefile, get all the event types 
	     * as required for Journal entries. move through the
	     * entire collection of event types, creating individual
	     * collections for each type 
	     */

	    CompositeResponse journalResp = null;
	    Collection<Object> journalEntries = new ArrayList();
	    int arrayCapacity = 0;
	    //Iterator<JuvenileCasefileSearchResponseEvent> iter = casefiles.iterator();
	    
	    List<ActivityResponseEvent> activities = new ArrayList<ActivityResponseEvent>();
	    List<FacilityActivityResponseEvent> facilityActs = new ArrayList<FacilityActivityResponseEvent>();
	    List<CasefileClosingResponseEvent> closings = new ArrayList<CasefileClosingResponseEvent>();
	    List<ProgramReferralResponseEvent> programReferrals = new ArrayList<ProgramReferralResponseEvent>();
	    List<CalendarServiceEventResponseEvent> calendarActs = new ArrayList<CalendarServiceEventResponseEvent>();
	    List<TraitJournalResponseEvent> traitActs = new ArrayList<TraitJournalResponseEvent>();
	    List<RiskAnswerResponseEvent> risks = new ArrayList<RiskAnswerResponseEvent>();
	    JournalForm.JuvenileJournal juvJournal = new JournalForm.JuvenileJournal();
	    
	    for (int x = 0; x < casefileIds.size(); x++)
	    {
		supervisionNum = casefileIds.get(x);

		//supervisionCategory = currentCasefile.getSupervisionCategory();

		journalResp = JournalHelper.getJournalEntries(journalForm, supervisionNum, journalForm.getJournalCategoryCd());
		if (journalResp != null && journalResp.getResponses().size() > 0)
		{
		    /* access the [big] collection via an array for faster access.
		     * a specialized, local "compositeToCollection" exists in this
		     * class to operate on the array, versus a collection.
		     * 
		     * it's not that accessing an array primitive is faster than
		     * accessing elements in an ArrayList, but we're setting the
		     * objects in the array to null to signify we've visited it.
		     */
		    Object[] eventsBag = journalResp.getResponses().toArray();
		    arrayCapacity += eventsBag.length;
		    ((ArrayList) journalEntries).ensureCapacity(arrayCapacity);

		    StringBuffer sb = new StringBuffer();
		    sb.append(supervisionNum).append("		").append(map.get(supervisionNum));
		    juvJournal.setCasefileId(sb.toString());
		    
		    /* the stacked if's are on purpose (for performance 
		     * reasons). there is no need to move on to the next 
		     * event type if there aren't any left [in the array], 
		     * so why make the function call (even if that function 
		     * call has a short-circuit check to return early).
		     */
		    Collection coll = JournalHelper.compositeToCollection(eventsBag, ActivityResponseEvent.class);
		    if (coll != null)
		    {
			//Collections.sort((List) coll);
			activities.addAll( coll );
			//juvJournal.setActivityEntries(coll);

			coll = JournalHelper.compositeToCollection(eventsBag, FacilityActivityResponseEvent.class);
			if (coll != null)
			{
			    //Collections.sort((List) coll);
			    facilityActs.addAll(coll);
			    //juvJournal.setFacEventEntries(coll);

			    coll = JournalHelper.compositeToCollection(eventsBag, CasefileClosingResponseEvent.class);
			    if (coll != null)
			    {
				closings.addAll(coll);
				//juvJournal.setClosingEntries(coll);

				coll = JournalHelper.compositeToCollection(eventsBag, GoalJournalResponseEvent.class);
				if (coll != null)
				{
				    //Collections.sort((List) coll);
				   // juvJournal.setGoalEntries(coll);

				    coll = JournalHelper.compositeToCollection(eventsBag, ProgramReferralResponseEvent.class);
				    if (coll != null)
				    {
					//Collections.sort((ArrayList) coll, ProgramReferralResponseEvent.refDateComparator);

					List<ProgramReferralResponseEvent> referralList = CollectionUtil.iteratorToList(coll.iterator());
					ProgramReferralResponseEvent prre = null;
					for (int i = 0; i < referralList.size(); i++)
					{
					    prre = referralList.get(i);
					    StringBuffer pText = new StringBuffer(prre.getProviderProgramName());
					    if (prre.getBeginDate() != null)
					    {
						pText.append(" Begin Date: ");
						pText.append(DateUtil.dateToString(prre.getBeginDate(), DateUtil.DATE_FMT_1));
						if (prre.getEndDate() != null)
						{
						    pText.append(" End Date: ");
						    pText.append(DateUtil.dateToString(prre.getEndDate(), DateUtil.DATE_FMT_1));
						}
						prre.setProviderProgramName(pText.toString());
					    }
					}

					//juvJournal.setProgRefEntries(coll);

					coll = JournalHelper.compositeToCollection(eventsBag, CalendarServiceEventResponseEvent.class);
					if (coll != null)
					{
					    /*List theList = new ArrayList(coll);
					    ArrayList sortFields = new ArrayList();
					    sortFields.add(new ReverseComparator(new BeanComparator("eventDate")));
					    sortFields.add(new ReverseComparator(new BeanComparator("eventTime")));
					    ComparatorChain multiSort = new ComparatorChain(sortFields);
					    Collections.sort(theList, multiSort);
					    coll = new ArrayList(theList);*/
					    calendarActs.addAll(coll);
					    //juvJournal.setCalEventEntries(coll);

					    coll = JournalHelper.compositeToCollection(eventsBag, TraitJournalResponseEvent.class);
					    if (coll != null)
					    {
						//Collections.sort((List) coll);
						traitActs.addAll(coll);
						//juvJournal.setTraitEntries(coll);

						coll = JournalHelper.getRiskEntriesFromEventBag(eventsBag);
						if (coll != null)
						{
						    /*List newList = new ArrayList(coll);
						    ArrayList srtFields = new ArrayList();
						    srtFields.add(new ReverseComparator(new BeanComparator("entryDate")));
						    ComparatorChain srt = new ComparatorChain(srtFields);
						    Collections.sort(newList, srt);
						    coll = new ArrayList(newList);*/
						    risks.addAll(coll);
						    //juvJournal.setRiskAnalEntries(coll);
						}
					    }
					}
				    }
				}
			    }
			}
		    }
		    // add the current Casefile's collection of events
		   // journalEntries.add(juvJournal);
		    
		}
		// }
	    } // while another Casefile iterator
	    // Loop through Journal Entries and set to separate bags then set to form
	    
	    if( activities.size() > 0) {
		
		    ArrayList sortFields = new ArrayList();
		    sortFields.add(new ReverseComparator(new BeanComparator("activityDate")));
		    ComparatorChain srt = new ComparatorChain(sortFields);
		    Collections.sort(activities, srt);
		    juvJournal.setActivityEntries(activities);
	    }

	    if( closings.size() > 0) {
		
		juvJournal.setClosingEntries(closings);
	    }
	    
	    if( facilityActs.size() >0) {
		
		Collections.sort(facilityActs, FacilityActivityResponseEvent.CaseReviewJournalSummaryFacilityActivityComparator);
		juvJournal.setFacEventEntries(facilityActs);
	    }
	    
	    if( calendarActs.size() >0) {
		
		 ArrayList sortFields = new ArrayList();
		 sortFields.add(new ReverseComparator(new BeanComparator("eventDate")));
		 sortFields.add(new ReverseComparator(new BeanComparator("eventTime")));
		 ComparatorChain multiSort = new ComparatorChain(sortFields);
		 Collections.sort(calendarActs, multiSort);
		 juvJournal.setCalEventEntries(calendarActs);
	    }
	   
	    if( programReferrals.size() >0) {
		
		Collections.sort((ArrayList) programReferrals, ProgramReferralResponseEvent.refDateComparator); 
		juvJournal.setProgRefEntries(programReferrals);
	    }
	    
	    if( risks.size() >0) {
		
		  ArrayList srtFields = new ArrayList();
		  srtFields.add(new ReverseComparator(new BeanComparator("entryDate")));
		  ComparatorChain srt = new ComparatorChain(srtFields);
		  Collections.sort(risks, srt);
		  juvJournal.setRiskAnalEntries(risks);
	    }
	   
	    if( traitActs.size() >0 ) {
		
		    Collections.sort((List) traitActs);
		    juvJournal.setTraitEntries(traitActs);
	    } 

	    if( juvJournal.getActivityEntries() != null || juvJournal.getClosingEntries()!= null 
		    || juvJournal.getFacEventEntries()!= null  || juvJournal.getCalEventEntries()!= null
		    || juvJournal.getProgRefEntries()!= null  || juvJournal.getRiskAnalEntries()!= null
		    || juvJournal.getTraitEntries()!= null) {
		
		 journalEntries.add(juvJournal);
	    }
	   
	    
	    if( journalEntries != null  ) {
		
		journalForm.setJuvJournalEntries(journalEntries);
	    }	    
	}
	/*}
	
	JuvenileProfileForm profileForm = (JuvenileProfileForm) session.getAttribute(PROFILE_FORM_STR);
	if (profileForm == null)
	{
	    if (form != null)
	    {
		UIJuvenileHelper.populateJuvenileProfileHeaderForm(aRequest, juvenileNum);
	    }
	}*/

	return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 47E258D30320
     */
    public ActionForward generate(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward journalSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	HttpSession session = aRequest.getSession();
	JuvenileBriefingDetailsForm form = (JuvenileBriefingDetailsForm) session.getAttribute(BRIEFING_FORM_STR);
	String juvenileNum = aRequest.getParameter("juvenileNum");
	//bug fix for 131898
	JuvenileProfileForm f = UIJuvenileHelper.getHeaderForm(aRequest);
	JournalForm jf = (JournalForm) aForm;
	jf.clearAll();

	Calendar today = Calendar.getInstance();
	today.setTime(new Date());
	//today.add(Calendar.YEAR, -1);
	today.add(Calendar.DAY_OF_YEAR, -364);
	Date minusOneYear = today.getTime();

	List<JuvenileProfileCasefileListResponseEvent> temp = new ArrayList();

	JuvenileProfileCasefileListForm casefileListForm = (JuvenileProfileCasefileListForm) session.getAttribute("juvenileProfileCasefileListForm");

	Collection<JuvenileProfileCasefileListResponseEvent> casefiles = casefileListForm.getJuvenileProfileCasefileList();
	Iterator iter = casefiles.iterator();

	while (iter.hasNext())
	{

	    JuvenileProfileCasefileListResponseEvent casefile = (JuvenileProfileCasefileListResponseEvent) iter.next();

	    if (!"PENDING".equalsIgnoreCase(casefile.getCaseStatus()))//found the issue while debugging for US:187988, field is coming back as PENDING and not the code 'P'
	    {

		if ("ACTIVE".equalsIgnoreCase(casefile.getCaseStatus())) //changes made for US 187988
		{

		    temp.add(casefile);
		}
		else
		    if (casefile.getActivationDateDt().compareTo(minusOneYear) > 0)
		    {

			temp.add(casefile);
		    }
	    }

	}

	jf.setJuvenileProfileCasefileList(temp);

	return aMapping.findForward(UIConstants.SEARCH);

    }

    /*
     * (non-Javadoc)
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.link", "link");
	keyMap.put("button.tab", "journalSearch");
	keyMap.put("button.generateJournalReport", "generate");
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.print", "printJournal");
    }
}

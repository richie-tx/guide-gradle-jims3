package pd.juvenile.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.juvenile.SearchJuvenileProfileCasefileListEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.ujac.util.BeanComparator;

import pd.codetable.Code;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.casefile.CasefileClosingInfo;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilewarrant.JJSPetition;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.casefile.JuvenileReferralHelper;

/**
 * Class SearchJuvenileProfileCasefileListCommand.
 * 
 * @author Anand Thorat
 */
public class SearchJuvenileProfileCasefileListCommand implements mojo.km.context.ICommand
{
    /**
     * 
     * @param event
     * @roseuid 42B888920234
     */
    public void execute(IEvent event)
    {
        SearchJuvenileProfileCasefileListEvent searchEvent = (SearchJuvenileProfileCasefileListEvent) event;
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        String juvenileId = searchEvent.getJuvenileId();
        Iterator iter = JuvenileCasefile.findAll("juvenileId", juvenileId);
        while (iter.hasNext())
        {
            JuvenileCasefile juvenileCasefile = (JuvenileCasefile) iter.next();
            JuvenileProfileCasefileListResponseEvent response = getResponseEvent(juvenileCasefile);
            dispatch.postEvent(response);
        }

    } //end of pd.juvenile.transactions.SearchJuvenileProfileCasefileListCommand.execute

    /**
     * 
     * @param event
     * @roseuid 42B888920242
     */
    public void onRegister(IEvent event)
    {

    } //end of pd.juvenile.transactions.SearchJuvenileProfileCasefileListCommand.onRegister

    /**
     * 
     * @param event
     * @roseuid 42B888920244
     */
    public void onUnregister(IEvent event)
    {

    } //end of pd.juvenile.transactions.SearchJuvenileProfileCasefileListCommand.onUnregister

    /**
     * 
     * @param updateObject
     * @roseuid 42B88AD40261
     */
    public void update(Object updateObject)
    {

    } //end of pd.juvenile.transactions.SearchJuvenileProfileCasefileListCommand.update

    /**
     * 
     * @param juvenileCasefile
     *            The juvenile casefile.
     * @return The response event.
     */
    private JuvenileProfileCasefileListResponseEvent getResponseEvent(JuvenileCasefile juvenileCasefile)
    {
        JuvenileProfileCasefileListResponseEvent event = new JuvenileProfileCasefileListResponseEvent();
        event.setSupervisionNum(juvenileCasefile.getOID());
        event.setSequenceNum(juvenileCasefile.getSequenceNumber());
        event.setActivationDate(getFormatedDate(juvenileCasefile.getActivationDate()));
        event.setActivationDateDt(juvenileCasefile.getActivationDate());
        //event.setSupervisionEndDate(getFormatedDate(juvenileCasefile.getSupervisionEndDate()));
        event.setProbationOfficer(juvenileCasefile.getProbationOfficerFullName());
        Code supervisionType = juvenileCasefile.getSupervisionType();
        event.setSupervisionType(supervisionType.getDescription());
        event.setSupervisionCategory(juvenileCasefile.getSupervisionCategoryId());
        event.setSupervisionTypeId(juvenileCasefile.getSupervisionTypeId()); //Added for Task 37996; User story 11077
        Code status = juvenileCasefile.getCaseStatus();
        String controllingRef = juvenileCasefile.getControllingReferral();
        if (controllingRef != null)
        {
        	event.setControllingReferralId(juvenileCasefile.getControllingReferral());
        }	
        String controllingRefId = juvenileCasefile.getCasefileControllingReferralId();
        if (controllingRefId != null)
        {
        	event.setControllingReferralId(juvenileCasefile.getCasefileControllingReferralId());
        }	
        event.setSupervisionOutcomeDescriptionId(juvenileCasefile.getSupervisionOutcomeDescriptionId());
        event.setSupervisionOutcome(juvenileCasefile.getSupervisionOutcome());
        if (status != null)
        {
            event.setCaseStatus(status.getDescription());
        }
        if (juvenileCasefile.getAssignmentAddDate() != null)
        {
        	String assignmentDate = getFormatedDate(juvenileCasefile.getAssignmentAddDate());
        	event.setAssignmentAddDate(assignmentDate);
        } else {
			Iterator assignments = Assignment.findAll("caseFileId", juvenileCasefile.getOID());
			List sortedList = new ArrayList();
			while (assignments.hasNext()) {
				Assignment cfAssignment = (Assignment) assignments.next();
				sortedList.add(cfAssignment);
			}
        	ArrayList sortFields = new ArrayList();
    		sortFields.add(new BeanComparator("assignmentAddDate"));
    		ComparatorChain multiSort = new ComparatorChain(sortFields);
    		Collections.sort(sortedList, multiSort);
    		Assignment casefileAssignment = null;
			for (int i = 0; i < sortedList.size(); i++) {
    			casefileAssignment = (Assignment) sortedList.get(i);
    			String assignmentAddDate = getFormatedDate(casefileAssignment.getAssignmentAddDate());
    			event.setAssignmentAddDate(assignmentAddDate);
    			break;
    		}
        }
        Iterator<CasefileClosingInfo> casefileClosings = CasefileClosingInfo.findAll("supervisionNumber", juvenileCasefile.getOID());
		while(casefileClosings.hasNext())
		{
			CasefileClosingInfo casefileClosingInfo = casefileClosings.next();
			event.setControllingReferralId(casefileClosingInfo.getControllingReferralId());
			event.setSupervisionEndDate(getFormatedDate(casefileClosingInfo.getSupervisionEndDate()));
			event.setSupervisionEndDateDt(casefileClosingInfo.getSupervisionEndDate());
	        event.setSupervisionOutcome(casefileClosingInfo.getSupervisionOutcomeId());
	        Code sprOutcomeCd = Code.find( PDCodeTableConstants.SUPERVISIONOUTCOME, casefileClosingInfo.getSupervisionOutcomeId());
	        if( sprOutcomeCd != null ){
	        	
		        event.setSupervisionOutcomeDescriptionId( sprOutcomeCd.getDescription() );
	        }
		}//added for User Story 14257: mouse hover the severe offense for controlling ref and court decision 
		String cntrlRef = event.getControllingReferralId();
		
		if (cntrlRef != null && !"".equals(cntrlRef)) {
			String refNumberWithOffense = ( JuvenileReferralHelper.getMaxSeverityOffenseCode(cntrlRef, juvenileCasefile.getJuvenileNum()));	
			String shortDesc = ( JuvenileReferralHelper.getFinalDisposition(cntrlRef,  juvenileCasefile.getJuvenileNum(), juvenileCasefile.getOID()));
	    	if (notNullNotEmptyString( refNumberWithOffense ) ) {
	    		event.setRefNumWithOffense(refNumberWithOffense);
	    		
	    		if( notNullNotEmptyString( shortDesc) )  {
	    			event.setRefNumWithOffense(refNumberWithOffense + " - " +  shortDesc);
					
			    }
	    	} else 	{
	    			event.setRefNumWithOffense( cntrlRef );
	    			
	    	}
		}
		//ended
		
		
		
		     
//      String assignmentDate = getFormatedDate(juvenileCasefile.getAssignmentAddDate());
//      if (assignmentDate != null)
//  	{
//       	event.setAssignmentAddDate(assignmentDate);
//	    }
//      GetCasefileAssignmentHistoryEvent casefileAssignmentHistoryEvent = (GetCasefileAssignmentHistoryEvent)event;
//		Iterator iter = JPOAssignmentHistoryView.findAll(casefileAssignmentHistoryEvent, juvenileId);
//		JPOAssignmentHistoryView jpoAssignmentHistory = null;

//		while (iter.hasNext()) {
//			jpoAssignmentHistory = (JPOAssignmentHistoryView) iter.next();

//			if (jpoAssignmentHistory != null) {
//               JPOAssignmentHistoryViewResponseEvent responseEvent = JuvenileCaseHelper.getJPOAssignmentHistoryViewResponseEvent(jpoAssignmentHistory);
//              event.setAssignmentAddDate(getFormatedDate(responseEvent.getAssignmentAddDate()));
//			}
//		}	
        return event;
    }

    /**
     * 
     * @param date
     *            The date.
     * @return The formated date.
     */
    private String getFormatedDate(Date date)
    {
        String strDate = "";
        if (date != null)
        {
            strDate = DateUtil.dateToString(date, "MM/dd/yyyy");
        }
        return strDate;
    } //end of pd.juvenile.transactions.SearchJuvenileProfileCasefileListCommand.getFormatedDate	
    
    /*
	 * given a string, return true it it's not null and not empty
	 * @param str
	 * @return
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  (str.trim().length() > 0) ) ;
	}
    
    

} // end SearchJuvenileProfileCasefileListCommand

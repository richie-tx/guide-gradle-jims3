//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefilesCommand.java

package pd.juvenilecase.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileDetailsEvent;
import messaging.juvenilecase.reply.NoJuvenileCasefilesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang.StringUtils;
import org.ujac.util.BeanComparator;

import pd.codetable.Code;
import pd.contact.officer.OfficerProfile;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.casefile.CasefileClosingInfo;
import ui.juvenilecase.casefile.JuvenileReferralHelper;

public class GetJuvenileCasefileDetailsCommand implements ICommand
{

    /**
     * @roseuid 42B85504004C
     */
    public GetJuvenileCasefileDetailsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42A70E2E001F performace fix
     */
    public void execute(IEvent event)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	GetJuvenileCasefileDetailsEvent getEvent = (GetJuvenileCasefileDetailsEvent) event;
	Iterator i = JuvenileCasefile.findAll("juvenileId", getEvent.getJuvenileNum());
	JuvenileProfileCasefileListResponseEvent casefileResponse = null;
	if (i.hasNext())
	{
	    HashMap<String, String> map = new HashMap<String, String>();
	    
	    while (i.hasNext())
	    {
		casefileResponse = new JuvenileProfileCasefileListResponseEvent();
		JuvenileCasefile casefile = (JuvenileCasefile) i.next();

		Code status = casefile.getCaseStatus();
		casefileResponse.setCaseStatus(status.getDescription());
		casefileResponse.setSupervisionNum(casefile.getOID());
		casefileResponse.setJuvenileId(casefile.getJuvenileId());
		Code supervisionType = casefile.getSupervisionType();
		casefileResponse.setSupervisionType(supervisionType.getDescription());
		casefileResponse.setSequenceNum(casefile.getSequenceNumber());
		casefileResponse.setSupervisionEndDateDt(casefile.getSupervisionEndDate());
		casefileResponse.setControllingReferralId(casefile.getCasefileControllingReferralId());
		casefileResponse.setActivationDateDt(casefile.getCreateTimestamp());

		// Find assignments,sort by top 1 by refseqnum

		if (casefile.getAssignmentAddDate() != null)
		{
		    String assignmentDate = DateUtil.dateToString(casefile.getAssignmentAddDate(), "MM/dd/yyyy");
		    casefileResponse.setAssignmentAddDate(assignmentDate);
		}
		else
		{
		    Iterator assignments = Assignment.findAll("caseFileId", casefile.getOID());
		    List sortedList = new ArrayList();
		    while (assignments.hasNext())
		    {
			Assignment cfAssignment = (Assignment) assignments.next();
			sortedList.add(cfAssignment);
		    }
		    ArrayList sortFields = new ArrayList();
		    sortFields.add(new BeanComparator("assignmentAddDate"));
		    ComparatorChain multiSort = new ComparatorChain(sortFields);
		    Collections.sort(sortedList, multiSort);
		    Assignment casefileAssignment = null;
		    for (int y = 0; y < sortedList.size(); y++)
		    {
			casefileAssignment = (Assignment) sortedList.get(y);
			String assignmentAddDate = DateUtil.dateToString(casefileAssignment.getAssignmentAddDate(), "MM/dd/yyyy");
			casefileResponse.setAssignmentAddDate(assignmentAddDate);
			break;
		    }
		}

		// Find casefile Closing

		Iterator closIter = CasefileClosingInfo.findAll("supervisionNumber", casefile.getOID());
		while (closIter.hasNext())
		{

		    CasefileClosingInfo closing = (CasefileClosingInfo) closIter.next();
		    if (closing.getControllingReferralId() != null)
		    {
			casefileResponse.setControllingReferralId(closing.getControllingReferralId());

		    }
		    casefileResponse.setSupervisionOutcome(closing.getSupervisionOutcomeId());
		    Code sprOutcomeCd = Code.find(PDCodeTableConstants.SUPERVISIONOUTCOME, closing.getSupervisionOutcomeId());
		    if (sprOutcomeCd != null)
		    {

			casefileResponse.setSupervisionOutcomeDescriptionId(sprOutcomeCd.getDescription());
		    }
		    casefileResponse.setSupervisionEndDate(DateUtil.dateToString(closing.getSupervisionEndDate(), "MM/dd/yyyy"));
		}

		OfficerProfile officer = OfficerProfile.find(casefile.getProbationOfficerId());
		if (officer != null)
		{

		    casefileResponse.setProbationOfficer(officer.getFirstName() + " " + officer.getLastName());
		}
		//added for User Story 14257: mouse hover the severe offense for controlling ref and court decision 
		String cntrlRef = casefileResponse.getControllingReferralId();

		if (cntrlRef != null && !"".equals(cntrlRef))
		{
		    if(!map.containsKey(cntrlRef)){
			
			String refNumberWithOffense = (JuvenileReferralHelper.getMaxSeverityOffenseCode(cntrlRef, casefile.getJuvenileNum()));
			String shortDesc = (JuvenileReferralHelper.getFinalDisposition(cntrlRef, casefile.getJuvenileNum(), casefile.getOID()));
			    if (StringUtils.isNotEmpty(refNumberWithOffense))
			    {
				casefileResponse.setRefNumWithOffense(refNumberWithOffense);

				if (StringUtils.isNotEmpty(shortDesc))
				{
				    casefileResponse.setRefNumWithOffense(refNumberWithOffense + " - " + shortDesc);

				}
			    }
			    map.put(cntrlRef, refNumberWithOffense);
		    } else{
			casefileResponse.setRefNumWithOffense( map.get(cntrlRef));
		    }
		}else {
			casefileResponse.setRefNumWithOffense(cntrlRef);
		    }
		//ended
		dispatch.postEvent(casefileResponse);
	    }
	}
	else
	{
	    NoJuvenileCasefilesResponseEvent none = new NoJuvenileCasefilesResponseEvent();
	    none.setMessage("No casefiles found for Juvenile Number: " + getEvent.getJuvenileNum());
	    dispatch.postEvent(none);
	}
    }

    /**
     * @param event
     * @roseuid 42A70E2E0021
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 42A70E2E0023
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 42B85504005B
     */
    public void update(Object updateObject)
    {

    }
}

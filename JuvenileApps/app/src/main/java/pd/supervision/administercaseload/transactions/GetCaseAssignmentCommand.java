package pd.supervision.administercaseload.transactions;

import java.util.Iterator;

import pd.supervision.administercaseload.CaseAssignment;
import pd.supervision.administercaseload.CaseAssignmentBuilder;
import pd.supervision.supervisionorder.SupervisionOrder;
import messaging.administercaseload.GetCaseAssignmentEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class GetCaseAssignmentCommand implements ICommand {

	/**
	 * @roseuid 4643602E010A
	 */
	public GetCaseAssignmentCommand() {

	}

	/**
	 * @param event
	 * @roseuid 464342310396
	 */
	public void execute(IEvent anEvent) {
		GetCaseAssignmentEvent event = (GetCaseAssignmentEvent) anEvent;
		CaseAssignmentResponseEvent response = new CaseAssignmentResponseEvent();

		String[] caseAssignments = null;
		if (event.getCaseAssignmentId() != null) {
			caseAssignments = new String[1];
			caseAssignments[0] = event.getCaseAssignmentId();
		} else if (event.getCaseAssignments() != null) {
			caseAssignments = event.getCaseAssignments();
		}

		if (caseAssignments != null && caseAssignments.length > 0) {
			CaseAssignmentBuilder builder = null;
			int len = caseAssignments.length;
			for (int i = 0; i < len; i++) {
				CaseAssignment assignment = CaseAssignment.find(caseAssignments[i]);
				builder = new CaseAssignmentBuilder(assignment);
				builder.build();
				ICaseAssignment currentAssignment = (ICaseAssignment) builder.getResult();
				response.addCaseAssignment(currentAssignment);
			}
		} else if (event.getCriminalCaseId() != null) { 
			CaseAssignment caseAssignment = null;
			boolean caseAssignmentAvailable = false;
			Iterator iter = CaseAssignment.findAll("criminalCaseId", event.getCriminalCaseId());
			if (iter != null) { 
				for (;iter.hasNext();) {//there will be only one record.
					caseAssignment = (CaseAssignment) iter.next();
					if (caseAssignment != null) { 
						CaseAssignmentBuilder builder = new CaseAssignmentBuilder(caseAssignment);
						builder.build();
						ICaseAssignment currentAssignment = (ICaseAssignment) builder.getResult();
						response.addCaseAssignment(currentAssignment);		
						caseAssignmentAvailable = true;
					} 		
					break;
				}
			}			
			if (!caseAssignmentAvailable && event.getSupervisionOrderId() != null) { //case assignment record does not exit.
				SupervisionOrder order = SupervisionOrder.find(event.getSupervisionOrderId());
				CaseAssignmentBuilder builder = new CaseAssignmentBuilder(order);
				builder.build();
				ICaseAssignment currentAssignment = (ICaseAssignment) builder.getResult();
				response.addCaseAssignment(currentAssignment);												
			}
		}
		MessageUtil.postReply(response);
	}

	/**
	 * @param event
	 * @roseuid 4643423103C3
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4643423103D2
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4643423103D4
	 */
	public void update(Object anObject) {

	}
}

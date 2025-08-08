package pd.supervision.administercaseload.transactions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import messaging.administercaseload.GetActiveSuperviseeCasesEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import pd.common.util.StringUtil;
import pd.criminalcase.CriminalCase;
import pd.supervision.administercaseload.CaseAssignmentOrder;

public class GetActiveSuperviseeCasesCommand implements ICommand
{

    /**
     * @roseuid 464360260252
     */
    public GetActiveSuperviseeCasesCommand()
    {

    }

    /**
     * @param event
     * @roseuid 46433E1C016C
     */
    public void execute(IEvent anEvent)
    {
        GetActiveSuperviseeCasesEvent event = (GetActiveSuperviseeCasesEvent) anEvent;
        event.setDefendantId(StringUtil.padString(event.getDefendantId(), CaseloadConstants.DEFENDANT_ID_PAD_CHAR, CaseloadConstants.DEFENDANT_ID_LEN));

        // search for active cases based on defendantId (i.e. defendantId)
        List caseAssignments = CaseAssignmentOrder.findAllBySupervisee(event.getDefendantId());
        CaseAssignmentResponseEvent response = new CaseAssignmentResponseEvent();
        
        Map criminalCaseMap = new HashMap();
        for (int i = 0; i < caseAssignments.size(); i++) {
            CaseAssignmentOrder caseAssignment = (CaseAssignmentOrder) caseAssignments.get(i);
        	if( CaseloadConstants.OFFICER_ACKNOWLEDGED.equals(caseAssignment.getCaseState())||
        		CaseloadConstants.OFFICER_ASSIGNED.equals(caseAssignment.getCaseState()) ||
        		CaseloadConstants.PROGRAM_UNIT_ASSIGNED.equals(caseAssignment.getCaseState())||
        		CaseloadConstants.SUPERVISOR_ALLOCATED.equals(caseAssignment.getCaseState()) ) {
                
        		ICaseAssignment assignmentBean = caseAssignment.valueObject();

                if(event.isQueryName()){
                    String defendantName = this.getDefendantName(caseAssignment,criminalCaseMap);
                    assignmentBean.setSuperviseeName(defendantName);
                    criminalCaseMap.put(caseAssignment.getCriminalCaseId(), defendantName);
                }
                response.addCaseAssignment(assignmentBean);
                assignmentBean = null;
            }
        	caseAssignment = null;
        }        
        response.setDefendantId(event.getDefendantId());
        MessageUtil.postReply(response);
        event = null;
        caseAssignments.clear();
        response = null;
    }

    private String getDefendantName(CaseAssignmentOrder caseAssignment, Map criminalCaseMap) {
    	String defendantName = (String) criminalCaseMap.get(caseAssignment.getCriminalCaseId());
        
        if (defendantName == null) {
        	CriminalCase cCase = caseAssignment.getCriminalCase();
            if(cCase != null){
            	defendantName = cCase.getDefendantName();
            }
        }
        //Clear Hashmap
        criminalCaseMap.clear();
    	return defendantName;
	}

    /**
     * @param event
     * @roseuid 46433E1C016E
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 46433E1C0170
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 46433E1C017C
     */
    public void update(Object anObject)
    {

    }
}

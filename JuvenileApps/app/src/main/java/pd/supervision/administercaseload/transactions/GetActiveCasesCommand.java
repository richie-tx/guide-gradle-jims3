package pd.supervision.administercaseload.transactions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.administercaseload.GetActiveCasesEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import pd.common.util.StringUtil;
import pd.criminalcase.CriminalCase;
import pd.supervision.administercaseload.CaseAssignmentOrder;

public class GetActiveCasesCommand implements ICommand
{

    /**
     * @roseuid 464360260252
     */
    public GetActiveCasesCommand()
    {

    }

    /**
     * @param event
     * @roseuid 46433E1C016C
     */
    public void execute(IEvent anEvent)
    {
        GetActiveCasesEvent event = (GetActiveCasesEvent) anEvent;
        event.setDefendantId(StringUtil.padString(event.getDefendantId(), CaseloadConstants.DEFENDANT_ID_PAD_CHAR, CaseloadConstants.DEFENDANT_ID_LEN));

        // search for active cases based on defendantId (i.e. defendantId)
        Iterator iter = CaseAssignmentOrder.findAllByEvent(event);
        CaseAssignmentResponseEvent response = new CaseAssignmentResponseEvent();
        
        Map criminalCaseMap = new HashMap();
        while(iter.hasNext()) {
            CaseAssignmentOrder caseAssignment = (CaseAssignmentOrder) iter.next();
            ICaseAssignment assignmentBean = caseAssignment.detailsValueObject();
            assignmentBean.setSupervisorAllocationDate(caseAssignment.getSupervisorAllocatedDate());
            String defendantName = this.getDefendantName(caseAssignment,criminalCaseMap);
            assignmentBean.setSuperviseeName(defendantName);
            //CSCASEASSIGNACTORD view returns active supervision orders that may not be assigned.  
            //OID mapping was changed to SPRVISIONORDER_ID 
            assignmentBean.setSupervisionOrderId(caseAssignment.getOID());
            assignmentBean.setCaseAssignmentId(caseAssignment.getCaseAssignId());
            criminalCaseMap.put(caseAssignment.getCriminalCaseId(), defendantName);
            response.addCaseAssignment(assignmentBean);   
        }
        response.setDefendantId(event.getDefendantId());
        MessageUtil.postReply(response);
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

package pd.supervision.administercaseload.transactions;

import pd.supervision.administercaseload.CaseAssignmentHelper;
import messaging.administercaseload.UpdateCaseAssignmentEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class UpdateCaseAssignmentCommand implements ICommand
{

    /**
     * @roseuid 4643603B006E
     */
    public UpdateCaseAssignmentCommand()
    {

    }

    /**
     * @param event
     * @roseuid 46433E1A0055
     */
    public void execute(IEvent anEvent)
    {
        UpdateCaseAssignmentEvent event = (UpdateCaseAssignmentEvent) anEvent;

        ICaseAssignment assignmentBean = CaseAssignmentHelper.updateCaseAssignment(event);
        CaseAssignmentResponseEvent response = new CaseAssignmentResponseEvent();
        response.addCaseAssignment(assignmentBean);
        MessageUtil.postReply(response);
    }

    /**
     * @param event
     * @roseuid 46433E1A0064
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 46433E1A0066
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 46433E1A0072
     */
    public void update(Object anObject)
    {

    }

}

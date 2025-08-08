package pd.juvenilewarrant.transactions;

import java.util.Calendar;

import naming.PDJuvenileWarrantConstants;
import naming.PDNotificationConstants;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import pd.juvenilewarrant.helper.JuvenileWarrantWorker;
import messaging.juvenilewarrant.AcknowledgeDirectiveToApprehendEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author ryoung
 * 
 */
public class AcknowledgeDirectiveToApprehendCommand implements ICommand
{

    /**
     * @roseuid 4187CBF80087
     */
    public AcknowledgeDirectiveToApprehendCommand()
    {
    }

    /**
     * @param event
     * @roseuid 4187BDDD0039
     */
    public void execute(IEvent event)
    {
        AcknowledgeDirectiveToApprehendEvent updateDTAEvent = (AcknowledgeDirectiveToApprehendEvent) event;
        JuvenileWarrant warrant = JuvenileWarrant.find(updateDTAEvent.getWarrantNum());

        if (warrant != null)
        {
            if (isPreConditionsMatched(warrant))
            {
                warrant.setWarrantAcknowledgementDate(Calendar.getInstance().getTime());
                warrant.setWarrantAcknowledgeStatusId(PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_PRINTED);

                //Register DTA activation message
                JuvenileWarrantWorker worker = new JuvenileWarrantWorker();
                worker.sendNotification(warrant, PDNotificationConstants.DTA_FILED);

                IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);                

                //create response events
                JuvenileWarrantResponseEvent jwResponse = warrant.valueObject();                
                EventManager.getSharedInstance(EventManager.REPLY).postEvent(jwResponse);

                /* Changes for Printing/Reporting functionality */
                PDJuvenileWarrantHelper.postResponses(warrant.getChargeResponses());
                /* End Changes for Printing/Reporting functionality */

            }

        }
    }

    /**
     * @param event
     * @roseuid 4187BDDD003B
     */
    public void onRegister(IEvent event)
    {
    }

    /**
     * @param event
     * @roseuid 4187BDDD003D
     */
    public void onUnregister(IEvent event)
    {
    }

    /**
     * @param anObject
     * @roseuid 4187BDDD003F
     */
    public void update(Object anObject)
    {
    }

    /**
     * @param warrantEvent
     * @return
     */
    private boolean isPreConditionsMatched(JuvenileWarrant warrantEvent)
    {
        // TODO Auto-generated method stub
        String warrantType = warrantEvent.getWarrantTypeId();
        String warrantStatus = warrantEvent.getWarrantStatusId();
        String warrantActivationStatus = warrantEvent.getWarrantActivationStatusId();
        String warrantAcknowledgementStatus = warrantEvent.getWarrantAcknowledgeStatusId();
        String warrantSignedStatus = warrantEvent.getWarrantSignedStatusId();
        String wannantNotSignedReason = warrantEvent.getUnsendNotSignedReason();

        if ((warrantType.equalsIgnoreCase(PDJuvenileWarrantConstants.WARRANT_TYPE_DTA))
                && (warrantStatus.equalsIgnoreCase(PDJuvenileWarrantConstants.WARRANT_STATUS_PENDING))
                && (warrantActivationStatus.equalsIgnoreCase(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE))
                && (warrantAcknowledgementStatus.equalsIgnoreCase(PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_NOT_PRINTED)))
        {
            return true;
        }
        return false;
    }

}
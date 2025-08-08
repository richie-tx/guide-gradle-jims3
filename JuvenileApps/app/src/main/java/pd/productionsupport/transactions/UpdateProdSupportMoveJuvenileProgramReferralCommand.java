package pd.productionsupport.transactions;

import messaging.productionsupport.UpdateProdSupportMoveJuvenileProgramReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.programreferral.JuvenileEventReferral;

public class UpdateProdSupportMoveJuvenileProgramReferralCommand implements ICommand
{
    /**
     * @roseuid 4278CAAA00AA
     */
    public UpdateProdSupportMoveJuvenileProgramReferralCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4278C7B80346
     */
    public void execute(IEvent event)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	UpdateProdSupportMoveJuvenileProgramReferralEvent moveJuvenileProgramReferralEvent = (UpdateProdSupportMoveJuvenileProgramReferralEvent) event;

	JuvenileEventReferral juvEvtRef = JuvenileEventReferral.find(moveJuvenileProgramReferralEvent.getOID());
	if(juvEvtRef != null){
	juvEvtRef.setProgramReferralId(moveJuvenileProgramReferralEvent.getJuvProgRefId());
	}
    }

    /**
     * @param event
     * @roseuid 4278C7B8034F
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4278C7B80359
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4278C7B80364
     */
    public void update(Object anObject)
    {

    }
}

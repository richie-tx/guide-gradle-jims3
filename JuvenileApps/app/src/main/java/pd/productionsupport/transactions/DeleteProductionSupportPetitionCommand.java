package pd.productionsupport.transactions;

import messaging.productionsupport.DeleteProductionSupportPetitionEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilewarrant.JJSPetition;

public class DeleteProductionSupportPetitionCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public DeleteProductionSupportPetitionCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
    	System.out.println("DeleteProductionSupportPetitionCommand");
    	DeleteProductionSupportPetitionEvent deletepetEvent = (DeleteProductionSupportPetitionEvent) event;   	
    	JJSPetition petition = JJSPetition.findById(deletepetEvent.getPetitionId());
    	if(petition != null)
    	{
    	    petition.delete();
	}    	
    }

    /**
     * @param event
     * @roseuid 4526B083011E
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4526B083012B
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4526B083012D
     */
    public void update(Object anObject)
    {

    }
    
}

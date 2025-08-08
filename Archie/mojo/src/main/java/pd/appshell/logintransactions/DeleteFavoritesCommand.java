package pd.appshell.logintransactions;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

/**
 * Responsible for implementing behavior of analysis method deleteFavorites of control class
 * mojo.pd.appshell.logintransactions.LoginController
 *@author Design detail addin
 *@version 1.0
 * @modelguid {60193799-0B46-40D8-B428-5CCB059F7D4B}
 */
public class DeleteFavoritesCommand implements mojo.km.context.ICommand, mojo.km.transaction.Transactional {
    /** Default constructor 
     * @modelguid {6060888D-7947-41EB-95D4-7F073B75A244}
     */
    public DeleteFavoritesCommand() { }

    /**
     *Provides behavior for application requirements. It is executed when event is posted from requesting context.
     *@param event - houses data for command operation.
     *@exception thrown if error in application behavior
     * @modelguid {06864167-B0CA-453A-ACD6-6A5E4605C1F7}
     */
    public void execute(IEvent event) throws Exception { }

    /**
     *Upon command registration with context this method will get executed
     *@param event - sample event data.
     * @modelguid {91672495-DC62-4E9B-90DF-52A36BE512B5}
     */
    public void onRegister(IEvent event) { }

    /**
     *Upon command unregistration from context this method will get executed
     *@param event - sample event
     * @modelguid {A54D6709-46E4-4369-9B7C-E94DA88FC477}
     */
    public void onUnregister(IEvent event) { }

    /**
     *If command requires data before execute is called context will place the in command
     *@param object - housing input data
     * @modelguid {C96DD68B-EFB5-46D6-AC55-B87C5EC56D81}
     */
    public void update(Object object) { }
}

package pd.appshell.logintransactions;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

/**
 * Responsible for implementing behavior of analysis method addFavorites of control class
 * mojo.pd.appshell.logintransactions.LoginController
 *@author Design detail addin
 *@version 1.0
 * @modelguid {756B92BE-000B-489C-AC4F-A23D70710F29}
 */
public class AddFavoritesCommand implements mojo.km.context.ICommand, mojo.km.transaction.Transactional {
    /** Default constructor 
     * @modelguid {BB4296DD-3BB6-4F5E-B684-05924657A696}
     */
    public AddFavoritesCommand() { }

    /**
     *Provides behavior for application requirements. It is executed when event is posted from requesting context.
     *@param event - houses data for command operation.
     *@exception thrown if error in application behavior
     * @modelguid {EED3B91F-A3C9-4E7D-82FF-6A0EEC921232}
     */
    public void execute(IEvent event) throws Exception { }

    /**
     *Upon command registration with context this method will get executed
     *@param event - sample event data.
     * @modelguid {D88AE25B-9EBD-4401-B435-0368F9E613EB}
     */
    public void onRegister(IEvent event) { }

    /**
     *Upon command unregistration from context this method will get executed
     *@param event - sample event
     * @modelguid {3F1039DC-9FC5-45AA-A18B-861E5E7212C4}
     */
    public void onUnregister(IEvent event) { }

    /**
     *If command requires data before execute is called context will place the in command
     *@param object - housing input data
     * @modelguid {C2288F03-2B77-4B41-A5C4-3EDCB9E9F1CC}
     */
    public void update(Object object) { }
}

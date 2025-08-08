package pd.appshell.logintransactions;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

/**
 * Responsible for implementing behavior of analysis method renameFavorites of control class
 * mojo.pd.appshell.logintransactions.LoginController
 *@author Design detail addin
 *@version 1.0
 * @modelguid {D6DEE2C7-9304-450B-8FFE-597C739E270C}
 */
public class RenameFavoritesCommand implements mojo.km.context.ICommand, mojo.km.transaction.Transactional {
    /** Default constructor 
     * @modelguid {3A71A1DB-1080-457C-84A0-EC7BF3A1E1A6}
     */
    public RenameFavoritesCommand() { }

    /**
     *Provides behavior for application requirements. It is executed when event is posted from requesting context.
     *@param event - houses data for command operation.
     *@exception thrown if error in application behavior
     * @modelguid {04C13021-D246-47CC-B35A-618C3AA8F873}
     */
    public void execute(IEvent event) throws Exception { }

    /**
     *Upon command registration with context this method will get executed
     *@param event - sample event data.
     * @modelguid {8DD353A8-D680-4410-B3C8-4C8E290D1B9B}
     */
    public void onRegister(IEvent event) { }

    /**
     *Upon command unregistration from context this method will get executed
     *@param event - sample event
     * @modelguid {FB54FA04-FAE7-4F8D-AEEB-CAF57B321B80}
     */
    public void onUnregister(IEvent event) { }

    /**
     *If command requires data before execute is called context will place the in command
     *@param object - housing input data
     * @modelguid {0B4E6EA4-BBE9-4F4C-AABB-7013FF101D1D}
     */
    public void update(Object object) { }
}

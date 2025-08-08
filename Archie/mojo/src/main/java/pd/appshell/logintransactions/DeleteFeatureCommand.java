package pd.appshell.logintransactions;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

/**
 * Responsible for implementing behavior of analysis method deleteFeature of control class
 * mojo.pd.appshell.logintransactions.LoginController
 *@author Design detail addin
 *@version 1.0
 * @modelguid {498C0106-74C3-481A-B37C-411AEC94CD1C}
 */
public class DeleteFeatureCommand implements mojo.km.context.ICommand, mojo.km.transaction.Transactional {
    /** Default constructor 
     * @modelguid {816A0181-479C-4B5F-9B5F-52B108582DB0}
     */
    public DeleteFeatureCommand() { }

    /**
     *Provides behavior for application requirements. It is executed when event is posted from requesting context.
     *@param event - houses data for command operation.
     *@exception thrown if error in application behavior
     * @modelguid {FAB53576-E12A-4ED4-9042-B6984EBD3401}
     */
    public void execute(IEvent event) throws Exception { }

    /**
     *Upon command registration with context this method will get executed
     *@param event - sample event data.
     * @modelguid {887E1568-6C2D-4E44-B35D-F5E85D43FDD5}
     */
    public void onRegister(IEvent event) { }

    /**
     *Upon command unregistration from context this method will get executed
     *@param event - sample event
     * @modelguid {0AA13C15-0A34-4465-B052-1C9E96005217}
     */
    public void onUnregister(IEvent event) { }

    /**
     *If command requires data before execute is called context will place the in command
     *@param object - housing input data
     * @modelguid {605E4D78-2D30-4390-B4C6-57DA74D07254}
     */
    public void update(Object object) { }
}

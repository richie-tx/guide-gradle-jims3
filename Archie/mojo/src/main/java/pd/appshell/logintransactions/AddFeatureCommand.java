package pd.appshell.logintransactions;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

/**
 * Responsible for implementing behavior of analysis method addFeature of control class
 * mojo.pd.appshell.logintransactions.LoginController
 *@author Design detail addin
 *@version 1.0
 * @modelguid {25CE478E-A81B-4C51-A96A-BC4EF119D829}
 */
public class AddFeatureCommand implements mojo.km.context.ICommand, mojo.km.transaction.Transactional {
    /** Default constructor 
     * @modelguid {DB6736A7-E353-4415-9E65-2FA90D7A42B3}
     */
    public AddFeatureCommand() { }

    /**
     *Provides behavior for application requirements. It is executed when event is posted from requesting context.
     *@param event - houses data for command operation.
     *@exception thrown if error in application behavior
     * @modelguid {EFE2415E-7BD0-462D-92F6-1E95C59D5277}
     */
    public void execute(IEvent event) throws Exception { }

    /**
     *Upon command registration with context this method will get executed
     *@param event - sample event data.
     * @modelguid {47367812-B1E5-4C11-9143-8311E437E069}
     */
    public void onRegister(IEvent event) { }

    /**
     *Upon command unregistration from context this method will get executed
     *@param event - sample event
     * @modelguid {977D21BC-B445-4D4C-BB14-EEF8CC48458D}
     */
    public void onUnregister(IEvent event) { }

    /**
     *If command requires data before execute is called context will place the in command
     *@param object - housing input data
     * @modelguid {503FB86D-E302-4B7D-8B36-289BE2B41DBC}
     */
    public void update(Object object) { }
}

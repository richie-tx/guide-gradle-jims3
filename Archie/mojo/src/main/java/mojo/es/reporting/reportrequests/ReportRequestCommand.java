package mojo.es.reporting.reportrequests;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

/**
 * Responsible for implementing behavior of analysis method reportRequest of control class
 * mojo.es.reporting.reportrequests.ReportRequestController
 *@author Design detail addin
 *@version 1.0
 * @modelguid {565070C0-2F4E-497A-B4E5-F426E3B07C09}
 */
public class ReportRequestCommand implements mojo.km.context.ICommand, mojo.km.transaction.Transactional {
    /** Default constructor 
     * @modelguid {01ABFAA2-1BF1-4EB7-BDCF-C900CD6C02ED}
     */
    public ReportRequestCommand() { }

    /**
     *Provides behavior for application requirements. It is executed when event is posted from requesting context.
     *@param event - houses data for command operation.
     *@exception thrown if error in application behavior
     * @modelguid {CE19F819-11C5-4D30-BCBC-E9C62971D13E}
     */
    public void execute(IEvent event) throws Exception { }

    /**
     *Upon command registration with context this method will get executed
     *@param event - sample event data.
     * @modelguid {FA5CF4F3-9EC2-4A18-A3DE-90937D652632}
     */
    public void onRegister(IEvent event) { }

    /**
     *Upon command unregistration from context this method will get executed
     *@param event - sample event
     * @modelguid {448FF035-E3F4-47B0-B41B-66213C1628DF}
     */
    public void onUnregister(IEvent event) { }

    /**
     *If command requires data before execute is called context will place the in command
     *@param object - housing input data
     * @modelguid {CBB06D56-2EAC-4B52-A95E-659297523BE6}
     */
    public void update(Object object) { }
}

package mojo.messaging;

import mojo.km.messaging.RequestEvent;

/**
 * Responsible for housing data that will be sent to control command GetKeyValuesCommand
 *@author Design detail addin
 *@version 1.0
 * @contextManagerName mojo.km.context.Default.DefaultContextManager
 * @requestDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @replyDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @asyncDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @queueDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @pubSubDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @debugMode false
 * @connectionPool false
 * @workflowEnabled false
 */
public class KeyValueEvent extends RequestEvent {
    public KeyValueEvent() { }

    public String getOID(){ return OID; }

    public void setOID(String OID){ this.OID = OID; }

    public boolean getSelected(){ return isSelected; }

    public void setSelected(boolean isSelected){ this.isSelected = isSelected; }

    public String getValue(){ return value; }

    public void setValue(String value){ this.value = value; }

    private String OID;
    private boolean isSelected;
    private String value;
}

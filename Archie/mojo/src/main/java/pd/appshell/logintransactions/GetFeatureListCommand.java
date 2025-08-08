package pd.appshell.logintransactions;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;

import messaging.appshell.DisplayFeatureEvent;
import messaging.appshell.DisplayFeatureListEvent;
import messaging.appshell.NodeEvent;
import messaging.appshell.NodeListEvent;
import messaging.logintransactionsevents.GetFeatureListEvent;
import mojo.km.config.PropertyBundleProperties;
import mojo.km.context.ContextManager;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.security.*;
import mojo.km.transaction.ReadOnlyTransactional;
import pd.appshell.ACL;
import pd.appshell.Feature;

/**
 * @author Egan Royal
 * @modelguid {F081122A-A7BB-4C53-920B-AA9D30D6A952}
 */
public class GetFeatureListCommand implements ICommand, ReadOnlyTransactional {

    /**
     *Provides behavior for application requirements. It is executed when event is posted from requesting context.
     *@param event - houses data for command operation.
     *@exception thrown if error in application behavior
     * @modelguid {85DF892B-0FF0-4011-8905-7955B5AC7A43}
     */
    
    
    public void execute(IEvent event) throws Exception {
    	System.out.println("~~~~~~~~~~~~~~~~~~In getfeatureslist command ");
		NodeListEvent features = new NodeListEvent();
		ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		/*
		 * SecurityManager has ACL which has ACEs which each have a Feature
		 */
		 Iterator aces = mgr.getACL().getACEs().iterator();
		 while (aces.hasNext()) {
		 	ACE ace = (ACE) aces.next();
		 	mojo.km.security.FeatureBean feature = ace.getFeature();
			String type = NodeEvent.FOLDER;
			if(feature.getParentId() != null && feature.getParentId().trim().length() > 0){
				type = NodeEvent.LEAF;
			}
			NodeEvent parentNodeEvent = new NodeEvent(feature.getOID().toString(),
												feature.getParentId(),
												feature.getName(),
												feature.getDescription(),
												type
												);

			features.insertNode(parentNodeEvent);
		 }
		
        EventManager.getSharedInstance(EventManager.REPLY).postEvent(features);
    }
    
    /**
     *Upon command registration with context this method will get executed
     *@param event - sample event data.
     * @modelguid {781F2A06-8190-4BB1-8026-57B3501FDC11}
     */
    public void onRegister(IEvent event) { }

    /**
     *Upon command unregistration from context this method will get executed
     *@param event - sample event
     * @modelguid {93208EED-AC71-465A-AADF-2A9DC338CF59}
     */
    public void onUnregister(IEvent event) { }

    /**
     *If command requires data before execute is called context will place the in command
     *@param object - housing input data
     * @modelguid {714D2C01-5CDF-41C8-8294-BBD4A0847719}
     */
    public void update(Object uObject) { }
}

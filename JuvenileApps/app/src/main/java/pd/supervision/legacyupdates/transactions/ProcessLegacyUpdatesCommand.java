/*
 * Created on Dec 17, 2007
 *
 */
package pd.supervision.legacyupdates.transactions;

import java.util.List;

import naming.LegacyUpdateConstants;

import messaging.legacyupdates.ProcessLegacyUpdatesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.legacyupdates.HandlerContextFactory;
import pd.supervision.legacyupdates.ILegacyUpdateHandler;
import pd.supervision.legacyupdates.LegacyUpdateLog;

/**
 * @author dgibler
 *
 */
public class ProcessLegacyUpdatesCommand implements ICommand {

	private static final long SLEEP_TIME = 5000; //5 seconds
    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) {
    	ProcessLegacyUpdatesEvent pEvent = (ProcessLegacyUpdatesEvent) event;
        List legacyAssignments = LegacyUpdateLog.findAll(pEvent);
        ILegacyUpdateHandler handler = null;
        HandlerContextFactory handlerFac = HandlerContextFactory.getInstance();		 
        
        for (int i = 0; i < legacyAssignments.size(); i++) {
            LegacyUpdateLog legacyUpdateLog = (LegacyUpdateLog) legacyAssignments.get(i);
            Object obj = null;
            try {
            	Thread.sleep( SLEEP_TIME );
   	   	        obj = handlerFac.lookup(legacyUpdateLog.getRecordTypeId());
            	handler = (ILegacyUpdateHandler) obj;
   		    } catch (Exception e) {   
   		    	legacyUpdateLog.setStatusId(LegacyUpdateConstants.STATUS_ERROR);  	
   		    	legacyUpdateLog.setError("Record Type not configured");	
   		        continue;
   		    }
   		    if("A".equalsIgnoreCase(legacyUpdateLog.getStatusId())){
   		    	handler.createLegacy(legacyUpdateLog);
   		    }else if("D".equalsIgnoreCase(legacyUpdateLog.getStatusId())){
   		    	handler.deleteLegacy(legacyUpdateLog);
   		    }else if("C".equalsIgnoreCase(legacyUpdateLog.getStatusId())){
   		    	handler.updateLegacy(legacyUpdateLog);
   		    }   		    
        } 
    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event) {
 
    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event) {
    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject) {
    }
}

/*
 * Created on Aug 21, 2008
 *
  */
package pd.supervision.legacyupdates;

import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 */
public class LegacyUpdatesImpl implements ILegacyUpdates {

	private static HandlerContextFactory _HANDLERFAC = HandlerContextFactory.getInstance();
	
	/**
	 * The <code> getHandlerImp() </code> helper method allows a caller 
	 * to obtain a reference to the handler implementation for a given
	 * record type.
	 * 
	 * @param recType
	 * @return A reference to a handler implementation of type ILegacyUpdateHandler
	 */
	private ILegacyUpdateHandler getHandlerImpl(String recType){
		ILegacyUpdateHandler handler = null;
		try {
        	handler = (ILegacyUpdateHandler) _HANDLERFAC.lookup(recType);
        } catch (Exception e) {
	        e.printStackTrace();
		}
        return handler;
	}
	
	/**
	 * The <code> create() </code> method allows a caller to create a
	 * a record to be processed for an update in the legacy system.
	 * 
	 * @param reqEvent - A entity representation of data that needs to be inserted
	 *                   or updated in the T_LEGACY_SUPERVISION_UPDATE_LOG table.
	 * @param recType  - A String representation of Record Types
	 * 
	 * @return String - The result
	 */
	public String create(IEvent reqEvent, String recType) throws Exception {
		System.out.println("LegacyUpdatesImpl.create (IEvent, String) - In method... ");
		String result = null;
		ILegacyUpdateHandler handler = null;
	
		if((handler = getHandlerImpl(recType)) != null){
			result = handler.createLog(reqEvent);
		} else {
			throw new Exception("Could not CREATE record for - "+
									"recType ["+recType+"] "+
									"and event type - ["+
									reqEvent.getClass().getName()+"]");
		}
		// Dereference
		handler = null;
		// Return
		System.out.println("LegacyUpdatesImpl.create (IEvent, String) - Leaving method... ");
		return result;
	}
	
	/**
	 * The <code> delete() </code> method allows a caller to delete
	 * a record for a given Source OID, SPN and Record Type
	 * 
	 * @param sourceOid A String representation of the Source OID
	 * @param recType A String representation of Record Type
	 * @param spn A String representation of a SPN.
	 */
	public void delete(String sourceOid, String recType, String spn) throws Exception {
		System.out.println("LegacyUpdatesImpl.delete (Str, Str, Str) - In method... ");
		ILegacyUpdateHandler handler = null;
		if((handler = getHandlerImpl(recType)) != null){
			handler.deleteLog(sourceOid, recType, spn);
		} else {
			throw new Exception("Could not DELETE log record for - " +
									"Source OID["+sourceOid+"] "+
									"recType ["+recType+"] "+
									"SPN ["+spn+"] ");
		}
		//Dereference
		handler = null;
		System.out.println("LegacyUpdatesImpl.delete (Str, Str, Str) - Leaving method... ");
	}	
}

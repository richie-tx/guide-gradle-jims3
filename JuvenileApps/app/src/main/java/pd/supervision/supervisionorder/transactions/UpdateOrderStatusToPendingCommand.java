//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionorder\\transactions\\ActivateSupervisionOrderCommand.java

package pd.supervision.supervisionorder.transactions;

import java.io.IOException;
import java.util.Iterator;
import messaging.managetask.GetCSTasksEvent;
import messaging.supervisionorder.UpdateOrderStatusToPendingEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.supervision.administercaseload.CaseAssignment;
import pd.supervision.managetask.CSTask;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionPeriod;
import pd.supervision.supervisionorder.SupervisionPeriodRedirect;


/**
 * @author ryoung
 *  
 */
public class UpdateOrderStatusToPendingCommand implements ICommand {

	
	/**
	 * @roseuid 43B2E757005D
	 */
	public UpdateOrderStatusToPendingCommand() {

	}

	/**
	 * @param event
	 * @roseuid 43B2B6F1007F
	 */
	public void execute(IEvent event) throws IOException {
		
		UpdateOrderStatusToPendingEvent updateEvent = (UpdateOrderStatusToPendingEvent) event;
		
		SupervisionOrder order = SupervisionOrder.find(updateEvent.getSupervisionOrderId());
		
		if ( order != null ) {
			
			char status = new Character( order.getVersionTypeId().charAt(0) );
			
			switch( status ){
			
			case 'A': 
				//Handle amended orders
				handleAmendedOrderVersions( order );
				break;
				
			case 'O':
				
				// close task for order
				closeOutDatedCSTasks( order );
				deleteSupervisionPeriod( order.getSupervisionPeriodId() );
				
				// set order status to pending
				// null order activation date
				order.setActivationDate( null );
				order.setOrderStatusId("P");
				order.setSupervisionPeriodId( null );
				break;
			}
			
		}
	}

 	
	/**
	 * 
	 * @param spn
	 * @param crimCase
	 */
	private void closeOutDatedCSTasks ( SupervisionOrder order ){
		
		CSTask cstask = null;
		GetCSTasksEvent request = new GetCSTasksEvent();
		
		request.setDefendantId( order.getDefendantId() );
		request.setCriminalCaseId( order.getCriminalCaseId() );
		
		Iterator i = new Home().findAll( request, CSTask.class );
		while (i.hasNext()) {
			cstask = (CSTask) i.next();			
			// Close tasks here...
			if( cstask != null ){
				cstask.setStatusId( "C" );
			}
		}
		cstask = null;
		request = null;
	}
	
	/**
	 * 
	 * @param order
	 */
	private void handleAmendedOrderVersions( SupervisionOrder order ){
		
		Iterator iterator = SupervisionOrder.findAll("criminalCaseId", order.getCriminalCaseId() );
        SupervisionOrder so = null;

        while(iterator.hasNext())
        {
        	so = ( SupervisionOrder ) iterator.next();
			
        	if ( "O".equals( so.getVersionTypeId() )){
        		
        		// Reset Original Order Version to Active and null InActivation date
        		so.setInactivationDate( null );
        		so.setOrderStatusId("A");
        		//Change order Id on Case assignment
        		updateCaseAssignment( so.getCriminalCaseId(), so.getOID() );
        		break;
        	}
		 }
        
        // Reset current order to Pending
        // null order inactivation date and supervision period id
        order.setActivationDate( null );
		order.setOrderStatusId("P");
		order.setSupervisionPeriodId( null );
		new mojo.km.persistence.Home().bind( order );

		// Close tasks for case
		closeOutDatedCSTasks( order );
	}
	
	/**
	 * @param event
	 * @roseuid 43B2B6F10081
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 43B2B6F10083
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * 
	 * @param criminalCaseId
	 * @param newOrderId
	 */
	private void updateCaseAssignment( String criminalCaseId, String newOrderId ) {
		
		Iterator iter = CaseAssignment.findAll("criminalCaseId", criminalCaseId);
		if (iter != null && iter.hasNext()){
			CaseAssignment caseAssignment = (CaseAssignment) iter.next();
			caseAssignment.setSupervisionOrderId(newOrderId);
			//caseAssignment.setTerminationDate(null);
		}
	}

	/**
	 * @param order
	 */
	private void deleteSupervisionPeriod( String spvPeriodId) {
		
		SupervisionPeriod supervisionPeriod = SupervisionPeriod.find( spvPeriodId );

		if ( supervisionPeriod != null ) {
			
			 SupervisionPeriodRedirect supervisionPeriodRedirect = null;
		        Iterator iter = SupervisionPeriodRedirect.findAll("sourceSupervisinPeriodId", spvPeriodId );
				// there will be only one 
				if(iter.hasNext()){
					supervisionPeriodRedirect = (SupervisionPeriodRedirect)iter.next();
					supervisionPeriodRedirect.delete();
				}
			supervisionPeriod.delete();
		} 
	}

	/**
	 * @param updateObject
	 * @roseuid 43B2E757007D
	 */
	public void update(Object updateObject) {

	}

}

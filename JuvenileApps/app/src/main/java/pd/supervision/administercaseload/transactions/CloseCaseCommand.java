package pd.supervision.administercaseload.transactions;

import java.util.Iterator;
import java.util.List;


import pd.supervision.administercaseload.CloseCaseHelper;
import pd.supervision.managetask.CSTask;

import messaging.administercaseload.CloseCaseEvent;
import messaging.administercaseload.CloseCaseResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.managetask.GetCSTasksEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;

public class CloseCaseCommand implements ICommand
{
	public static final int ZERO = 0;
	
	
	 public CloseCaseCommand()
	 {
	 }
	 
	 
	 /**
	  * 
	  */
	public void execute(IEvent event) 
	{
		CloseCaseEvent closeCaseEvent = (CloseCaseEvent)event;
		
		if(closeCaseEvent != null)
		{
			String defendantId = null;
			
			List closeCasesList = (List)closeCaseEvent.getCloseCasesList();
			
			if((closeCasesList != null) && (closeCasesList.size() > 0))
			{
				Iterator iterator = closeCasesList.iterator();
				
				while(iterator.hasNext())
				{
					CaseAssignmentTO closeCaseAssignment = (CaseAssignmentTO)iterator.next();
					
//					to set the defendant ID to 8 characters long by appending ZEROs in the prefix
					StringBuffer defendantIdBuffer = new StringBuffer(closeCaseAssignment.getDefendantId());
					while(defendantIdBuffer.length() < 8)
					{
						defendantIdBuffer.insert(ZERO, ZERO);
					}
					defendantId = defendantIdBuffer.toString();
					String criminalCaseId = closeCaseAssignment.getCriminalCaseId();
					
					closeAllCSTasks( defendantId, criminalCaseId );
					closeCaseAssignment.setDefendantId(defendantId);
					
//					close the case 
					CloseCaseResponseEvent responseEvent = CloseCaseHelper.closeCase(closeCaseAssignment);
					
					MessageUtil.postReply(responseEvent);
				}
				
//				check if it is the last case of the defendant 
//				i.e. there are no more ACTIVE Supervision Orders for that Defendant
				if(CloseCaseHelper.isLastCase(defendantId))
				{
					CloseCaseResponseEvent responseEvent = CloseCaseHelper.processLastCase(defendantId);
					closeAllCSTasksForSPN( defendantId );
					
					MessageUtil.postReply(responseEvent);
				}
			}
		}
	}//end of execute()

	
	/**
	 * 
	 * @param spn
	 * @param crimCase
	 */
	private void closeAllCSTasks ( String spn, String crimCase ){
		
		CSTask cstask = null;
		GetCSTasksEvent request = new GetCSTasksEvent();
		
		request.setDefendantId( spn );
		request.setCriminalCaseId( crimCase );
		
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
     * @param toSpn
     * @param fromSpn
     */
    private void closeAllCSTasksForSPN( String defendantId ) {
       
    	CSTask task = null;
    	 CSTask csTask = new CSTask();
    	 
        List< CSTask > tasks = 
        	CollectionUtil.iteratorToList(
        			csTask.findAll( "defendantId" , defendantId ));
        
	     for ( int x =0; x < tasks.size(); x ++ ){
	    	 
	    	 task = (CSTask) tasks.get( x );
	    	 // Close tasks here...
			 if( task != null && !task.getStatusId().equals("C")){
				 task.setStatusId( "C" );
			 }
	     }
	     tasks.clear();
	     tasks = null;
	     task = null;
	     csTask = null;
	     
    }
	
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void update(Object updateObject) {
		// TODO Auto-generated method stub
		
	}

}

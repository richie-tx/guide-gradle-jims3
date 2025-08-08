//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\casefile\\transactions\\UpdateCasefileClosingCommand.java

package pd.juvenilecase.casefile.transactions;

import java.io.IOException;
import java.util.Iterator;
import messaging.casefile.UpdateCasefileClosingEvent;
import messaging.task.CreateTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.TaskControllerServiceNames;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.SupervisionTypeMap;
import pd.juvenilecase.casefile.CasefileClosingInfo;
import pd.juvenilecase.casefile.PDCasefileClosingHelper;
import pd.task.helper.TaskHelper;

/**
 * @author mchowdhury
 */

public class UpdateCasefileClosingCommand implements ICommand
{
	/**
	 * @roseuid 439602E60046
	 */
	public UpdateCasefileClosingCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 4395C2370063
	 */
	public void execute( IEvent event ) throws IOException 
	{
		UpdateCasefileClosingEvent updateEvent = (UpdateCasefileClosingEvent)event ;		
		if( updateEvent == null )
		{
			return ;
		}

		CasefileClosingInfo casefile = null ;
		{ String closingInfoID = updateEvent.getCasefileClosingInfoId() ;
			if( closingInfoID != null &&  closingInfoID.length() > 0 )
			{
				casefile = CasefileClosingInfo.find( closingInfoID ) ;
			} else {
				Iterator<CasefileClosingInfo> i = CasefileClosingInfo.findAll("supervisionNumber", updateEvent.getSupervisionNumber());
				
				if( i != null && i.hasNext() )
				{
					while(i.hasNext())
					{
						casefile = i.next();
					}
				}
				
			}
		}

		if( casefile == null )
		{
			casefile = new CasefileClosingInfo() ;
		}
		casefile.setCasefileClosingDetails( updateEvent ) ;
		casefile.createOID() ;

		// Set the casefile status
		this.setCasefileStatus( updateEvent ) ;

		// send the notification while sending for approval
		if( updateEvent.isSendForApproval() )
		{
			CreateTaskEvent createTask = (CreateTaskEvent)
					EventFactory.getInstance( TaskControllerServiceNames.CREATETASK ) ;

			createTask.setCreatorId( updateEvent.getUserID() ) ;
			createTask.setOwnerId( updateEvent.getClmLogonID() ) ;
			createTask.addTaskStateItem( "submitAction", "Link" ) ;
			createTask.addTaskStateItem( "casefileId", updateEvent.getCasefileID() ) ;
			createTask.setTaskTopic( "JC.CASEFILECLOSING_FOR_REVIEW" ) ;
			
			StringBuffer subject = new StringBuffer( "CLM needs to review Casefile for closing: Juvenile # " ) ;
			subject = subject.append( updateEvent.getJuvenileNum() ) ;
			createTask.setTaskSubject( subject.toString() ) ;
			//<KISHORE>JIMS200057619 : MJCW - Get Rid of Command Chaining for Tasks
			//IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
			//dispatch.postEvent( createTask ) ;
            try {
                @SuppressWarnings("unused")
				String taskId = TaskHelper.createTask(createTask);
            } catch (IOException e) {
                throw e;
            }
		}
		
		Juvenile juvenile = Juvenile.findJCJuvenile(updateEvent.getJuvenileNum());
		if( ! updateEvent.isSendForApproval()
			&& !updateEvent.isApprovalForRequest()
			&& !updateEvent.isApprovalRejected() ){
		    JuvenileCore jCore = JuvenileCore.findCore(updateEvent.getJuvenileNum());
		    if ( jCore != null ) {
			jCore.setYouthDeathReason(updateEvent.getYouthDeathReason());
			jCore.setYouthDeathVerification(updateEvent.getYouthDeathVerification());
			jCore.setDeathDate(updateEvent.getDeathDate());
			jCore.setDeathAge(updateEvent.getDeathAge());
		    }
		}
		JuvenileCasefile juvcase = JuvenileCasefile.find(updateEvent.getCasefileID());
		
		String type = juvcase.getSupervisionTypeId();
		
		Iterator stp = SupervisionTypeMap.findAll("supervisionTypeId", type);
		
		while (stp.hasNext())
		{
		  SupervisionTypeMap stm = (SupervisionTypeMap)stp.next();	
		if (stm.getSupervisionCatId().equals(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM)||
				stm.getSupervisionCatId().equals(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ) ||
				stm.getSupervisionCatId().equals(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES))
		{
			String status = updateEvent.getCasefileClosingStatus();
			if (status.equals(PDJuvenileCaseConstants.CASE_STATUS_CLOSE)) 
			{
				juvenile.setMentalHealthServices(false);
			}
			break;	
		}
		}
		// return the response event
		PDCasefileClosingHelper.postCasefileClosingDetails( casefile ) ;
	}

	/**
	 * @param updateEvent
	 */
	private void setCasefileStatus( UpdateCasefileClosingEvent updateEvent )
	{
		JuvenileCasefile juvenileCasefile = JuvenileCasefile.find( updateEvent.getSupervisionNumber() ) ;
		if( juvenileCasefile != null )
		{
			juvenileCasefile.setCaseStatusId( updateEvent.getCasefileClosingStatus() ) ;
		}
	}

	/**
	 * @param event
	 * @roseuid 4395C237006C
	 */
	public void onRegister( IEvent event )
	{
	}

	/**
	 * @param event
	 * @roseuid 4395C23700B2
	 */
	public void onUnregister( IEvent event )
	{
	}

	/**
	 * @param anObject
	 * @roseuid 4395C23700BD
	 */
	public void update( Object anObject )
	{
	}

}

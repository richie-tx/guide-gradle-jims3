/*
 * Created on May 15, 2007
 *
 */
package ui.juvenilecase.programreferral;

import java.util.Date ;
import java.util.Iterator ;
import java.util.List ;
import java.util.Map;
import messaging.calendar.SaveJuvenileAttendanceEvent ;
import messaging.calendar.reply.CalendarServiceEventResponseEvent ;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory ;
import mojo.km.messaging.Composite.CompositeResponse ;
import mojo.km.utilities.DateUtil ;
import mojo.km.utilities.MessageUtil ;
import naming.PDCalendarConstants ;
import naming.ProgramReferralConstants ;
import naming.ServiceEventControllerServiceNames ;
import naming.UIConstants ;

/**
 */
public class ProgramReferralCloseAction implements ProgramReferralAction
{
	private String subAction ;

	/**
	 * 
	 */
	public ProgramReferralCloseAction( String subAction )
	{
		this.subAction = subAction ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see messaging.programreferral.ProgramReferralAction#getActionName()
	 */
	public String getActionName( )
	{
		return subAction ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see messaging.programreferral.ProgramReferralAction#execute(ui.juvenilecase.programreferral.UIProgramReferralBean)
	 */
	public void execute( UIProgramReferralBean programReferral )
	{
		String currentUserType = programReferral.getCurrentUserType( ) ;

		if( subAction.equals( ProgramReferralConstants.ACTION_REJECT ) )
		{
			programReferral.setReferralState( ProgramReferralStateManager.getReferralState( 
					ProgramReferralConstants.CLOSED, ProgramReferralConstants.REJECTED ) ) ;
			
			if( currentUserType.equals( ProgramReferralConstants.SP_USER ) )
			{
				if( programReferral.getAcknowledgementDate( ) == null )
				{
					programReferral.setAcknowledgementDate( new Date( ) ) ;
				}
				programReferral.setEndDateStr( DateUtil.dateToString( new Date( ), DateUtil.DATE_FMT_1 ) ) ;

				UIProgramReferralBean.ProgramReferralNoticeInfo noticeInfo = 
						new UIProgramReferralBean.ProgramReferralNoticeInfo( ) ;

				noticeInfo.setNotificationTopic( UIConstants.PROGRAM_REFERRAL_NOTIFICATION ) ;
				noticeInfo.setIdentity( UIProgramReferralHelper.getOfficerUserId( programReferral.getOfficerId( ) ) ) ;
				noticeInfo.setSubject( "Program Referral Rejected" ) ;

				StringBuffer message = new StringBuffer( ) ;
				message.append( programReferral.getJuvServiceProviderName( ) ) ;
				message.append( " has rejected the " ) ;
				message.append( programReferral.getProviderProgramName( ) ) ;
				message.append( " referral for " ) ;
				message.append( programReferral.getJuvenileName( ) ) ;

				noticeInfo.setMessage( message.toString( ) ) ;
				programReferral.setReferralNoticeInfo( noticeInfo ) ;
			}
		}
		else if( subAction.equals( ProgramReferralConstants.ACTION_CANCEL ) )
		{
			programReferral.setReferralState( ProgramReferralStateManager.getReferralState( 
					ProgramReferralConstants.CLOSED, ProgramReferralConstants.CANCELLED ) ) ;
			
			if( currentUserType.equals( ProgramReferralConstants.JPO_USER ) )
			{
				String [] msgs = { " Referral Cancelled", " has cancelled the ", " referral for juvenile " } ;
				setAttendanceAndGenerateNotice( programReferral, true, msgs ) ;
			}
		} 
		else if( subAction.equals( ProgramReferralConstants.ACTION_WITHDRAW ) )
		{
			programReferral.setReferralState( ProgramReferralStateManager.getReferralState(
					ProgramReferralConstants.CLOSED, ProgramReferralConstants.WITHDRAWN ) ) ;
			
			if( currentUserType.equals( ProgramReferralConstants.JPO_USER ) )
			{
				String [] msgs = { " Referral Withdrawn", " has withdrawn the ", " referral for " } ;
				setAttendanceAndGenerateNotice( programReferral, true, msgs ) ;
			}
		}
		else if( subAction.equals( ProgramReferralConstants.ACTION_COMPLETE ) )
		{
			if( programReferral.getReferralState( ).getStatus( ).equals( ProgramReferralConstants.CLOSED ) && 
					programReferral.getReferralState( ).getSubStatus( ).equals( ProgramReferralConstants.REJECTED ) )
			{
				programReferral.setReferralState( ProgramReferralStateManager.getReferralState( 
						ProgramReferralConstants.CLOSED, ProgramReferralConstants.REJECTED ) ) ;
				
				if( currentUserType.equals( ProgramReferralConstants.JPO_USER ) )
				{
					String [] msgs = { " Referral Rejection Completed", " has completed the ", " rejected referral for " } ;
					setAttendanceAndGenerateNotice( programReferral, false, msgs ) ;
				}
			}
			else
			{
				programReferral.setReferralState( ProgramReferralStateManager.getReferralState( 
						ProgramReferralConstants.CLOSED, ProgramReferralConstants.COMPLETED ) ) ;
				
				if( currentUserType.equals( ProgramReferralConstants.JPO_USER ) )
				{ 
					String [] msgs = { " Referral Completed", " has completed the ", " referral for juvenile " } ;
					setAttendanceAndGenerateNotice( programReferral, false, msgs ) ;					
				}
			}
		}
		//added for US 52635
		else if( subAction.equals( ProgramReferralConstants.ACTION_ACCEPTANDCLOSE ) )
		{
			programReferral.setReferralState( ProgramReferralStateManager.getReferralState(
					ProgramReferralConstants.CLOSED, ProgramReferralConstants.ACCEPTEDANDCLOSED ) ) ;
			
			if( currentUserType.equals( ProgramReferralConstants.SP_USER ) )
				{
					if( programReferral.getAcknowledgementDate( ) == null )
					{
						programReferral.setAcknowledgementDate( new Date( ) ) ;
					}
					programReferral.setEndDateStr( DateUtil.dateToString( new Date( ), DateUtil.DATE_FMT_1 ) ) ;

					UIProgramReferralBean.ProgramReferralNoticeInfo noticeInfo = 
							new UIProgramReferralBean.ProgramReferralNoticeInfo( ) ;

					noticeInfo.setNotificationTopic( UIConstants.PROGRAM_REFERRAL_NOTIFICATION ) ;
					noticeInfo.setIdentity( UIProgramReferralHelper.getOfficerUserId( programReferral.getOfficerId( ) ) ) ;
					noticeInfo.setSubject( "Program Referral Accepted" ) ;

					StringBuffer message = new StringBuffer( ) ;
					message.append( programReferral.getJuvServiceProviderName( ) ) ;
					message.append( " has accepted the " ) ;
					message.append( programReferral.getProviderProgramName( ) ) ;
					message.append( " referral for " ) ;
					message.append( programReferral.getJuvenileName( ) ) ;

					noticeInfo.setMessage( message.toString( ) ) ;
					programReferral.setReferralNoticeInfo( noticeInfo ) ;
				}
		}
	}

	/*
	 * @param programReferral
	 * @param setEndDate
	 * @return
	 */
	private boolean setAttendanceAndGenerateNotice( 
			UIProgramReferralBean programReferral, boolean setEndDate, String [] messages )
	{
		boolean hasFutureEvents = false ;

		String isEmptyEventDetails = ". The following events have been excused for " ;
		StringBuffer eventDetails = new StringBuffer( ) ;
		
		List juvenileEvents = programReferral.getJuvenileEvents( ) ;
		if( juvenileEvents.size( ) > 0 )
		{
			eventDetails.append( isEmptyEventDetails ) ;
			eventDetails.append( programReferral.getJuvenileName( ) ) ;
			eventDetails.append( "<br />" ) ;

			for( Iterator iterJuvenileEvents = juvenileEvents.iterator( );
					iterJuvenileEvents.hasNext( ); /*empty*/ )
			{
				CalendarServiceEventResponseEvent resp = (CalendarServiceEventResponseEvent)iterJuvenileEvents.next( ) ;
				if( resp.getEventDate( ).after( DateUtil.getCurrentDate( ) ) )
				{
					hasFutureEvents = true ;
					eventDetails.append( " " + resp.getServiceName( ) ) ;
					eventDetails.append( " " + DateUtil.dateToString( resp.getEventDate( ), DateUtil.DATETIME24_FMT_1 ) ) ;
					eventDetails.append( "<br />" ) ;
					
					if (resp.getProgramReferralId().equals(programReferral.getReferralId())) {
						updateAttendance( programReferral, resp ) ;
					}
					
				}
			} // for
		}

		UIProgramReferralBean.ProgramReferralNoticeInfo noticeInfo = new UIProgramReferralBean.ProgramReferralNoticeInfo( ) ;
		noticeInfo.setNotificationTopic( UIConstants.PROGRAM_REFERRAL_NOTIFICATION ) ;

		{ String adminContactId = UIProgramReferralHelper.getSPAdminContactId( programReferral.getJuvServiceProviderId( ) ) ;
			noticeInfo.setIdentity( adminContactId ) ;

			StringBuffer title = new StringBuffer( ) ;
			title.append( programReferral.getProviderProgramName( ) ) ;
			title.append( messages[ 0 ] ) ;
			noticeInfo.setSubject( title.toString( ) ) ;
		}

		StringBuffer message = new StringBuffer( ) ;
		message.append( programReferral.getOfficerName( ) ) ;
		message.append( messages[ 1 ] ) ;
		message.append( programReferral.getProviderProgramName( ) ) ;
		message.append( messages[ 2 ] ) ;
		message.append( programReferral.getJuvenileName( ) ) ;

		if( hasFutureEvents )
		{
			message.append( eventDetails.toString( ) ) ;
		}
		noticeInfo.setMessage( message.toString( ) ) ;
		programReferral.setReferralNoticeInfo( noticeInfo ) ;
		
		return( true ) ;
	}

	/*
	 * @param programReferral
	 * @param resp
	 */
	private void updateAttendance( UIProgramReferralBean programReferral, 
			CalendarServiceEventResponseEvent calEvent )
	{
		SaveJuvenileAttendanceEvent saveAttendanceEvent = (SaveJuvenileAttendanceEvent)
				EventFactory.getInstance(ServiceEventControllerServiceNames.SAVEJUVENILEATTENDANCE);

		saveAttendanceEvent.setJuvenileId( programReferral.getJuvenileId() );
		saveAttendanceEvent.setServiceEventId( calEvent.getEventId() );
		saveAttendanceEvent.setEventStartDate( calEvent.getEventDate( ) );
		saveAttendanceEvent.setAttendanceStatusCd( PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED );
		saveAttendanceEvent.setEventCategory( UIConstants.PRESCHEDULED_SERVICE_TYPE );

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(saveAttendanceEvent);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
	}
}

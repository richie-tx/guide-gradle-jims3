package pd.juvenilecase;// no longer in use. Migrated to SQL-PART OF REFERRAL CONVERSION. Refer US #87188. No references in the mapping file.

/*
 * Created on Jun 06, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 
package pd.juvenilecase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.calendar.GetViewCalendarEventsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.mentalhealth.GetAllMAYSIAssessmentsEvent;
import messaging.notification.SendCasefileClosingNotificationEvent;
import messaging.notification.SendOverdueInterviewNotificationEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.riskanalysis.GetRecentRiskAnalysisForJuvenileEvent;
import messaging.riskanalysis.GetRecentRiskAnalysisForJuvenileUpTo10DaysEvent;
import messaging.scheduling.RegisterTaskEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.PersistentEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.scheduling.JobReportBean;
import mojo.km.scheduling.Log;
import mojo.km.transaction.multidatasource.SaveException;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.CalendarConstants;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasefileNotificationControllerServiceNames;
import naming.PDCalendarConstants;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.PDTaskConstants;
import naming.RiskAnalysisConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;

import pd.codetable.Code;
import pd.common.calendar.CalendarEventContext;
import pd.contact.officer.OfficerProfile;
import pd.exception.InvalidProbationOfficerException;
import pd.juvenile.Juvenile;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIComplete;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.riskanalysis.RiskAnalysis;

*//**
 * @author mchowdhury 
 * To change the template for this generated type comment go
 * to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code 
 * and Comments
 *//*
public class ProcessCasefileExtraction extends CasefileExtractionUtility
{
	*//**
	 *
	 *//*
	public ProcessCasefileExtraction()
	{
	}

	*//**
	 * This method is used to associate assignment records to casefiles. 
	 * A new casefile is created if one already doesn't exist for a 
	 * combination of SupervisionNum, JuvenileNum and jpoUserId and 
	 * Case Status not closed.
	 * 
	 * @param jjsServices
	 *          a collection of assignment records fetched from m204.
	 * @param jobId
	 * @throws RuntimeException
	 * @throws Exception
	 *//*
	public JobReportBean associateAssignmentsToCaseFile( 
			Iterator<JJSService> jjsServices, int jobId ) throws RuntimeException, Exception
	{
		JobReportBean rBean = null;
		String DELIMITER = "^";
		Map supTypeMap = new HashMap();
		Map juvenileMap = new HashMap();
		Map errors = new HashMap();

		int skipCount = 0;
		int successfulCount = 0;
		int errorCount = 0;

		JJSService jjsService = null;
		while( jjsServices.hasNext() )
		{
			jjsService = jjsServices.next();
			String caseManagerId = jjsService.getCaseLoadManagerId();
			String errorMessage = ValidateJJSService.validate( jjsService );

			if( ! StringUtils.equals( errorMessage, UIConstants.EMPTY_STRING ) )
			{
				if( ! StringUtils.equals( caseManagerId, UIConstants.EMPTY_STRING ) )
				{
					errors = this.recoredErrorsForNotification( jjsService, errorMessage, errors );
				}
				
				{ String str = logMessage( jjsService, errorMessage ) ;
					Log.createLog( jobId, getCurrentTime(), str, "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
				}
				
				jjsService.setJims2ExtractInd( PDJuvenileCaseConstants.CASEFILE_EXTRACTION_ERROR_STATUS );
				++errorCount ;
				jjsService = null;
				continue;
			}

			String assignmentLevelId = jjsService.getAssignmentLevelId();
			String serviceUnitId = jjsService.getUnitId();
			String keySupType = new StringBuffer( assignmentLevelId )
					.append( DELIMITER ).append( serviceUnitId).toString() ;

			Code supervisionType = (Code)supTypeMap.get( keySupType );
			if( supervisionType == null )
			{
				supervisionType = SupervisionTypeMap.determineSupervisionType( serviceUnitId, assignmentLevelId );
				if( supervisionType == null )
				{
					errors = this.recoredErrorsForNotification( jjsService, "Combination of Level and Unit is invalid.", errors );
					
					String str = logMessage( jjsService, "Combination of Level and Unit is invalid." ) ;
					Log.createLog( jobId, getCurrentTime(), str, "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
					jjsService.setJims2ExtractInd( PDJuvenileCaseConstants.CASEFILE_EXTRACTION_SKIPPED_STATUS );
					++skipCount ;
					str = null ;
					jjsService = null;
					supervisionType = null;
					continue;
				}
				supTypeMap.put( keySupType, supervisionType );
			}

			OfficerProfile officer = null;
			try
			{
				officer = getOfficer( jjsService );
			}
			catch(InvalidProbationOfficerException e)
			{
				e.printStackTrace();
				errors = this.recoredErrorsForNotification( jjsService, "JPO Id is invalid.", errors );
				
				String str = logMessage( jjsService, "JPO Id is invalid." ) ;
				Log.createLog( jobId, getCurrentTime(), str, "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
				jjsService.setJims2ExtractInd( PDJuvenileCaseConstants.CASEFILE_EXTRACTION_ERROR_STATUS );

				++errorCount ;
				str = null ;
				jjsService = null;
				supervisionType = null;
				officer = null;
				continue;
			}

			String jpoId = officer.getOID().toString();
			String juvNum = jjsService.getJuvenileNum();
			String supTypeId = supervisionType.getCode();
			String referralNum = jjsService.getReferralNum();

			JuvenileCasefile casefile = JuvenileCasefile.find( jpoId, juvNum, supTypeId );
			if( casefile == null )
			{
				casefile = JuvenileCasefile.find( juvNum, supTypeId );
				if( casefile != null && 
						! PDCodeTableConstants.STATUS_CLOSED.equalsIgnoreCase( casefile.getCaseStatusId() ) )
				{
					if( this.doesReferralExists( casefile, referralNum ) )
					{
						String oldJPOName = casefile.getProbationOfficerFullName();

						{ String str = logMessage( jjsService, "Casefile already exists with a status not equal to Closed with another JPO for this referral. Updating JPO in JIMS" ) ;
							Log.createLog( jobId, getCurrentTime(), str, "L", PDJuvenileCaseConstants.CASEFILE_CREATOR );
						}
						
						jjsService.setJims2ExtractInd( PDJuvenileCaseConstants.CASEFILE_EXTRACTION_SUCCESS_STATUS );

						// <KISHORE>JIMS200052303 : MJCW CF Transfer Future Events not transferred. 
						// All the future events of old JPO should be assigned to new JPO
						IHome home = new Home();
						Collection<CalendarServiceEventResponseEvent> events = 
								findCalendarEvents( casefile.getProbationOfficerId(), casefile.getJuvenileNum(), casefile.getOID() );
						if( events != null )
						{
							Iterator<CalendarServiceEventResponseEvent> eventsIterator = events.iterator();
							while(eventsIterator.hasNext())
							{
								CalendarServiceEventResponseEvent event = eventsIterator.next();
								Iterator<CalendarEventContext> cont = CalendarEventContext.findByCalendarEventId( event.getCalendarEventId() );
								while(cont.hasNext())
								{
									CalendarEventContext context = cont.next();
									if( context != null && casefile.getOID().equalsIgnoreCase( context.getCaseFileId() ) )
									{
										context.setProbationOfficerId( jpoId );
										try
										{
											home.bind( context );
										}
										catch(SaveException e)
										{
											e.printStackTrace();
											break;
										}
									}
								} // while
							} // while
						}
						
						//Anytime the JPO is updated on a casefile a record is written by a DB trigger 
						//to JIMS2.JCJPOASSNMNTHIST.  This table is keeping a history of JPO assignments for a casefile.
						casefile.setProbationOfficerId( jpoId );
						Iterator iter = Assignment.findAll("caseFileId", casefile.getOID());
						while (iter.hasNext())
						{
							Assignment assignment = (Assignment) iter.next();
							if (assignment.getReferralNumber().equals(referralNum)) {
								assignment.setAssignmentLevelId(assignmentLevelId);
								assignment.setServiceUnitId(serviceUnitId);
							}
						} 
						
						++successfulCount ;

						try
						{
							JuvenileNotificationGenerator.sendJPOUpdateNotification( casefile, referralNum, oldJPOName );
						}
						catch(RuntimeException e)
						{
							e.printStackTrace();
							errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
							Log.createLog( jobId, getCurrentTime(), e.getMessage(), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
						}
						catch(Exception e)
						{
							String str = logMessage( jjsService, e.getMessage() ) ;
							Log.createLog( jobId, getCurrentTime(), str, "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
							e.printStackTrace();
							errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
						}
					}
					else
					{
						StringBuffer message = new StringBuffer( "Casefile already exists with a status not equal to Closed for this JPO " );
						message.append( casefile.getFormattedProbationOfficer());
						message.append( " with a mismatch in Referral Number." );
						errors = this.recoredErrorsForNotification( jjsService, message.toString(), errors );
						
						String str = logMessage( jjsService, message.toString() ) ;
						Log.createLog( jobId, getCurrentTime(), str, "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
						jjsService.setJims2ExtractInd( PDJuvenileCaseConstants.CASEFILE_EXTRACTION_ERROR_STATUS );
						++errorCount ;
					}
					
					jjsService = null;
					supervisionType = null;
					officer = null;
					casefile = null;
					continue;
				}
				else
				{
					try
					{
						//Anytime a casefile is created a record is written by a DB trigger to JIMS2.JCJPOASSNMNTHIST. 
						//This table is keeping a history of JPO assignments for a casefile.
						casefile = JuvenileCasefileGenerator.generate( casefile, jjsService, supervisionType, officer, juvenileMap );
						//retrieve the casefile again to get the seq num generated by the trigger.
						casefile = JuvenileCasefile.find( jpoId, juvNum, supTypeId );  //bug fix 22374 
					}
					catch(RuntimeException e)
					{
						e.printStackTrace();
						Log.createLog( jobId, getCurrentTime(), 
								logMessage( jjsService, e.getMessage() ), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
						jjsService.setJims2ExtractInd( PDJuvenileCaseConstants.CASEFILE_EXTRACTION_ERROR_STATUS );
						errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );

						++errorCount ;
						supervisionType = null;
						officer = null;
						casefile = null;
						jjsService = null;
						continue;
					}
					catch(Exception e)
					{
						e.printStackTrace();
						Log.createLog( jobId, getCurrentTime(), 
								logMessage( jjsService, e.getMessage() ), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
						jjsService.setJims2ExtractInd( PDJuvenileCaseConstants.CASEFILE_EXTRACTION_ERROR_STATUS );
						errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );

						++errorCount ;
						supervisionType = null;
						officer = null;
						casefile = null;
						jjsService = null;
						continue;
					}
					try
					{
						JuvenileNotificationGenerator.createTask( casefile, jjsService.getProbationOfficerId() );
					}
					catch(RuntimeException e)
					{
						e.printStackTrace();
						errors = this.recoredErrorsForNotification( jjsService, "Error occurred during the creation of Task after the successful generation of casefile.", errors );
						Log.createLog( jobId, getCurrentTime(), 
								logMessage( jjsService, "Error occurred during the creation of Task after the successful generation of casefile." ), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
					}
					catch(Exception e)
					{
						Log.createLog( jobId, getCurrentTime(), 
								logMessage( jjsService, e.getMessage() ), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
						e.printStackTrace();
						errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
					}
				}
			}
			else
			{
// casefile not equal null
				if( ! PDCodeTableConstants.STATUS_CLOSED.equalsIgnoreCase( casefile.getCaseStatusId() ) )
				{
					if( this.doesReferralExists( casefile, referralNum ) )
					{
						errors = this.recoredErrorsForNotification( jjsService, "An unclosed casefile for Juvenile/JPO/SupervisionType already exists with a matching referral.", errors );
						Log.createLog( jobId, getCurrentTime(), 
								logMessage( jjsService, "An unclosed casefile for Juvenile/JPO/SupervisionType already exists with a matching referral." ), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
						jjsService.setJims2ExtractInd( PDJuvenileCaseConstants.CASEFILE_EXTRACTION_ERROR_STATUS );
						++errorCount ;
					}
					else
					{
						JJSJuvenile jjsJuvenile = JJSJuvenile.find( juvNum );
						Date birthDate = jjsJuvenile.getBirthDate();
						//  06/08/2012 - revised 7max age from 18 to 19 per ER 71590
						if( getAgeInYears( birthDate ) >= 17 && getAgeInYears( birthDate ) < 19)
						{
							GetJuvenileCasefileOffensesEvent jEvent = new GetJuvenileCasefileOffensesEvent();
							jEvent.setJuvenileNum( juvNum );
							jEvent.setReferralNum( referralNum );
							Iterator iter = JJSOffense.findAll( jEvent );
							boolean skipFlag = true;

							StringBuffer referrals = new StringBuffer();
							while(iter.hasNext())
							{
								JJSOffense jOff = (JJSOffense)iter.next();
								if( jOff != null && 
										(getAgeInYears( jOff.getOffenseDate() ) < 17 || 
										 "17".equals( jOff.getOffenseReportGroup() )) )
								{
									Assignment assignment = this.createAssignment( jjsService, casefile );
									// Create Task for supervisionType = Deferred Adjudication, Prosecution
									if( casefile.getSupervisionTypeId().equalsIgnoreCase( 
													UIConstants.CASEFILE_SUPERVISION_TYPE_DEFERRED_ADJUDICATION_SUPERVISION ) || 
											casefile.getSupervisionTypeId().equalsIgnoreCase( 
													UIConstants.CASEFILE_SUPERVISION_TYPE_DEFERRED_PROSECUTION_SUPERVISION ) )
									{
										String subject = "Schedule Overdue Deferred adjudication/prosecution for Juvenile # " + juvNum;
										this.scheduleOverdueInterviewTask( 
												jjsService.getProbationOfficerId(), 
												PDTaskConstants.MJCW_JPO_OVERDUE_DEFERREDADJUDICATION, 
												subject, casefile, 240, jjsService.getAddDate(), 
												officer.getOfficerProfileId() );
									}
									// End Task

									// Send Casefile closing Notice to JPO
									if( casefile.getSupervisionTypeId().equalsIgnoreCase( 
													UIConstants.CASEFILE_SUPERVISION_TYPE_STATUS_OFFENDER_SUPERVISION ) || 
											casefile.getSupervisionTypeId().equalsIgnoreCase( 
													UIConstants.CASEFILE_SUPERVISION_TYPE_PRE_ADJUDICATION_SUPERVISION ) || 
											casefile.getSupervisionTypeId().equalsIgnoreCase( 
															UIConstants.CASEFILE_SUPERVISION_TYPE_INTAKE_SCREENING_SUPERVISION ) )
									{
										this.sendPOStatusOffenderSupervisionNotification( jjsService, casefile, jjsJuvenile );
									}

									Log.createLog( jobId, getCurrentTime(), 
											logMessage( jjsService, 
													"Referral has been added to the already existing casefile for Juvenile/JPO/SupervisionType." ), 
													"L", PDJuvenileCaseConstants.CASEFILE_CREATOR );
									jjsService.setJims2ExtractInd( PDJuvenileCaseConstants.CASEFILE_EXTRACTION_SUCCESS_STATUS );
									++successfulCount ;
									skipFlag = false;

									referrals.append( assignment.getReferralNumber() );
									referrals.append( " on " );
									referrals.append( assignment.getAssignmentAddDate().toString() );
									if( iter.hasNext() )
									{
										referrals.append( "," );
									}
									assignment = null;
								}
								jOff = null;
							}
							
							if( skipFlag )
							{
								errors = this.recoredErrorsForNotification( 
										jjsService, "Juvenile is between 17 and 19 and active casefile already exists and date of offense did not happen before his/her 17th birthday.", errors );
								Log.createLog( jobId, getCurrentTime(), 
										logMessage( jjsService, "Juvenile is between 17 and 19 and active casefile already exists and date of offense did not happen before his/her 17th birthday." ), 
											"E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
								jjsService.setJims2ExtractInd( PDJuvenileCaseConstants.CASEFILE_EXTRACTION_SKIPPED_STATUS );
								++skipCount ;
							}
							else
							{
								try
								{
									JuvenileNotificationGenerator.sendNewAssignmentUpdateJuvenileFor1718Notification( casefile, referrals.toString() );
								}
								catch(RuntimeException e)
								{
									e.printStackTrace();
									errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
									Log.createLog( jobId, getCurrentTime(), e.getMessage(), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
								}
								catch(Exception e)
								{
									Log.createLog( jobId, getCurrentTime(), logMessage( jjsService, e.getMessage() ), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
									e.printStackTrace();
									errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
								}
							}
							jEvent = null;
							iter = null;
						}
						else
						{
							Assignment assignment = this.createAssignment( jjsService, casefile );
							// find all active cases for juvenile where current jpo is NOT same as the one assigned
							// to the subsequent referral and create notifications for jpos and clms, if any
							String level = jjsService.getAssignmentLevelId();
							if ("B".equalsIgnoreCase(level) || "N".equalsIgnoreCase(level) || "J".equalsIgnoreCase(level)){
								sendJPOSubsequentNotifications(jjsService, officer, casefile, jobId, errors);
							}
							jjsService.setJims2ExtractInd( PDJuvenileCaseConstants.CASEFILE_EXTRACTION_SUCCESS_STATUS );
							Log.createLog( jobId, getCurrentTime(), logMessage( jjsService, "Referral has been added to the already existing casefile for Juvenile/JPO/SupervisionType." ), "L", PDJuvenileCaseConstants.CASEFILE_CREATOR );
							++successfulCount ;

							try
							{
								JuvenileNotificationGenerator.sendNewAssignmentUpdateNotification( casefile, assignment.getReferralNumber() );
							}
							catch(RuntimeException e)
							{
								e.printStackTrace();
								errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
								Log.createLog( jobId, getCurrentTime(), e.getMessage(), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
							}
							catch(Exception e)
							{
								Log.createLog( jobId, getCurrentTime(), logMessage( jjsService, e.getMessage() ), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
								e.printStackTrace();
								errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
							}
							assignment = null;
						}
						jjsJuvenile = null;
						birthDate = null;
					}
					
					supervisionType = null;
					officer = null;
					casefile = null;
					jjsService = null;
					continue;
				}
			}  // end casefile edits
// begin assignment edits
			Assignment assignment = new Assignment();
			populateAssignmentFromService( jjsService, assignment );
			casefile.insertAssignments( assignment );
			assignment.setCaseFileId( casefile.getOID().toString() );

			try
			{ // check MAYSI flag
				setMAYSIFlag( juvNum, assignment.getReferralNumber(), jpoId, assignment, casefile );
			}
			catch(RuntimeException e)
			{
				errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
				Log.createLog( jobId, getCurrentTime(), 
						logMessage( jjsService, e.getMessage() ), 
								"E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
				Log.createLog( jobId, getCurrentTime(), 
						logMessage( jjsService, e.getMessage() ), 
								"E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
			}

			try
			{ // check benefit flag in casefile
				// setBenefitAssessmentFlag(juvNum,
				// assignment.getAssignmentAddDate(),casefile);
				// check and set the risk analysis flag
				setRiskAnalysisFlag( juvNum, supervisionType, assignment.getAssignmentAddDate(), casefile, assignment.getReferralNumber() );
			}
			catch(RuntimeException e)
			{
				errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
				Log.createLog( jobId, getCurrentTime(), 
						logMessage( jjsService, e.getMessage() ), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
				Log.createLog( jobId, getCurrentTime(), 
						logMessage( jjsService, e.getMessage() ), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
			}

			try
			{
				// send notification
				JuvenileNotificationGenerator.sendNewJuvenileCasefileNotification( casefile, officer );
			}
			catch(RuntimeException e)
			{
				errors = this.recoredErrorsForNotification( jjsService, "Error occurred during the new Juvenile casefile notification", errors );
				Log.createLog( jobId, getCurrentTime(), 
						logMessage( jjsService, "Error occurred during the new Juvenile casefile notification" ), 
								"E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
				Log.createLog( jobId, getCurrentTime(), 
						logMessage( jjsService, e.getMessage() ), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
			}
// end assignment edits
			
// check for subsequent referral with level codes = B, N or J and send notice to JPO and CLM			
			String level = jjsService.getAssignmentLevelId();
			if ("B".equalsIgnoreCase(level) || "N".equalsIgnoreCase(level) || "J".equalsIgnoreCase(level)){
				String jpoID = officer.getOID().toString();
				Juvenile juvenile = casefile.getJuvenile();
				String juvenileName = juvenile.getFirstName() + " " + juvenile.getLastName();
				String juvenileNum = juvenile.getJuvenileNum();
				String officerJIMSLogonId = casefile.getProbationOfficer().getLogonId();
				String referralDate = DateUtil.dateToString(jjsService.getServiceDate(), DateUtil.DATE_FMT_1);
				JuvenileNotificationGenerator.sendSubsequentReferralNotification(juvenileName, juvenileNum, referralNum, referralDate, officerJIMSLogonId);
				
				OfficerProfile officerProfile = OfficerProfile.find(jpoID); 
				officerJIMSLogonId = officerProfile.getManagerId();
				//create CLM notification 
				if( officerJIMSLogonId != null && !"".equals(officerJIMSLogonId) )
				{
					JuvenileNotificationGenerator.sendSubsequentReferralNotification(juvenileName, juvenileNum, referralNum, referralDate, officerJIMSLogonId);
				}
				juvenile = null;
				juvenileName = null;
				juvenileNum = null;
				referralDate = null;
				officerJIMSLogonId = null; 
				jpoID = null;
			}	
			
			jjsService.setJims2ExtractInd( PDJuvenileCaseConstants.CASEFILE_EXTRACTION_SUCCESS_STATUS );
			++successfulCount ;
			Log.createLog( jobId, getCurrentTime(), 
					logMessage( jjsService, "Successfully Processed." ), "L", PDJuvenileCaseConstants.CASEFILE_CREATOR );

			jjsService = null;
			supervisionType = null;
			officer = null;
			casefile = null;
			assignment = null;
		} // end of while

		rBean = new JobReportBean( errorCount, skipCount, successfulCount );
		if( errorCount != 0 || skipCount != 0 )
		{
			// send notification for the error to individual CaseLoadManager
			try
			{
				JuvenileNotificationGenerator.sendCaseFileGenerationFailureNotifications( 
						errors, PDJuvenileCaseConstants.FAILURE_CASEFILE_NOTIFICATION );
			}
			catch(RuntimeException e)
			{
				Log.createLog( jobId, getCurrentTime(), 
						"Error occurred during the generation of Casefile Failure Notification.", 
						"E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
				e.printStackTrace();
			}
			catch(Exception e)
			{
				Log.createLog( jobId, getCurrentTime(), e.getMessage(), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
				e.printStackTrace();
			}
		}
		
		supTypeMap = null;
		juvenileMap = null;
		errors = null;
		
		return rBean;
	}

	*//**
	 * @param date
	 * @return
	 *//*
	public static String dateToString( Date date )
	{
		SimpleDateFormat dfmt = new SimpleDateFormat( "MM/dd/yyyy h:mm a" );
		String dateStr = UIConstants.EMPTY_STRING ;
		
		try
		{
			if( date != null )
			{
				dateStr = dfmt.format( date );
			}
		}
		catch(RuntimeException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
		
		return dateStr;
	}

	*//**
	 * Method for creating the task to JPO for overdue Deferred
	 * Adjudication/Prosecution Interview
	 * 
	 * @param probationOfficerId
	 * @param topic
	 * @param subject
	 * @param casefile
	 * @param hours
	 * @param juvenileNum
	 * @param addDate
	 *//*

	private void scheduleOverdueInterviewTask( 
			String ownerId, String topic, String subject, 
			JuvenileCasefile casefile, int hours, Date addDate, 
			String officerId ) throws RuntimeException, Exception
	{
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		SendOverdueInterviewNotificationEvent interviewTask = (SendOverdueInterviewNotificationEvent)
				EventFactory.getInstance( JuvenileCasefileNotificationControllerServiceNames.SENDOVERDUEINTERVIEWNOTIFICATION );

		Calendar cal = Calendar.getInstance();
		Date testDate = new Date();
		
		cal.setTime( testDate );
		cal.add( Calendar.HOUR, hours );
		
		Date dueDate = cal.getTime();
		
		interviewTask.setDueDate( dueDate );
		interviewTask.setOwnerId( ownerId );
		interviewTask.setCasefileId( casefile.getOID().toString() );
		interviewTask.setJuvenileNum( casefile.getJuvenileNum() );
		interviewTask.setOfficerId( officerId );
		interviewTask.setSubmitAction( "Link Create" );
		interviewTask.setTaskTopic( topic );
		interviewTask.setTaskSubject( subject );
		interviewTask.setAlertType( "Task" );
		interviewTask.setIdentityType( "jpo" );

		// Register the task with the scheduler
		RegisterTaskEvent rtEvent = new RegisterTaskEvent();
		rtEvent.setScheduleClassName( mojo.naming.CalendarConstants.ONCE_SCHEDULE_CLASS );
		rtEvent.setFirstNotificationDate( dueDate );
		rtEvent.setNextNotificationDate( dueDate );
		String taskName = interviewTask.getClass().getName() + "-" + Math.random();
		rtEvent.setTaskName( taskName );
		rtEvent.setNotificationEvent( interviewTask );
		dispatch.postEvent( rtEvent );

		dispatch = null;
		interviewTask = null;
		cal = null;
		testDate = null;
		dueDate = null;
		rtEvent = null;
	}

	*//**
	 * @param updateEvent
	 * @param casefileForm
	 *          To JPO CasefileClosingNotification.
	 *//*
	private void sendPOStatusOffenderSupervisionNotification( 
			JJSService jjsService, JuvenileCasefile casefile, 
			JJSJuvenile juvenile ) throws RuntimeException, Exception
	{
		SendCasefileClosingNotificationEvent casefileNotifEvent = (SendCasefileClosingNotificationEvent)
				EventFactory.getInstance( JuvenileCasefileNotificationControllerServiceNames.SENDCASEFILECLOSINGNOTIFICATION );
		
		String subject = "Status Offender Supervision has been open 30 days.";
		casefileNotifEvent.setSubject( subject );
		
		StringBuffer sb = new StringBuffer( juvenile.getLastName() );
		sb.append( " " ).append( juvenile.getFirstName() )
				.append( " Supervision # " ).append( casefile.getOID().toString() )
				.append( " was assigned to you on " )
				.append( dateToString( jjsService.getAddDate()) )
				.append( " and the case remains active. It has been 30 days since assignment." )
				.append( "\n" );
		
		casefileNotifEvent.setNotificationMessage( sb.toString() );
		String taskName = casefileNotifEvent.getClass().getName();
		// To JPO
		casefileNotifEvent.setIdentityType( "jpo" );
		casefileNotifEvent.setIdentity( jjsService.getProbationOfficerId() );
		casefileNotifEvent.setNoticeTopic( "MJCW.M204.CASEFILE.ASSIGNMENT.NOTIFICATION" );
		// Added as the command is used for both DB2 and M204 notices.
		casefileNotifEvent.setSupervisionNumber( casefile.getOID().toString() );
		this.scheduleNotification( casefileNotifEvent, taskName, jjsService.getAddDate(), 720 );
		casefileNotifEvent = null;
	}

	*//**
	 * @param event
	 * @param taskName
	 * @param date
	 * @param hours
	 *//*
	public void scheduleNotification( 
			PersistentEvent event, String taskName, Date date, int hours ) throws RuntimeException, Exception
	{
		// create RegisterTaskEvent and post it
		RegisterTaskEvent rtEvent = new RegisterTaskEvent();
		rtEvent.setScheduleClassName( CalendarConstants.ONCE_SCHEDULE_CLASS );

		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		cal.add( Calendar.HOUR, hours );
		Date futureDate = cal.getTime();
		taskName += "_" + Math.random();

		rtEvent.setFirstNotificationDate( futureDate );
		rtEvent.setNextNotificationDate( futureDate );

		rtEvent.setTaskName( taskName );
		rtEvent.setNotificationEvent( event );
		// EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent );
		MessageUtil.postRequest( rtEvent );
		rtEvent = null;
		cal = null;
		futureDate = null;
	}

	*//**
	 * @param juvNum
	 * @param referralNumber
	 * @param casefile
	 * @throws Exception
	 * @throws RuntimeException
	 *//*
	private void setMAYSIFlag( String juvNum, String referralNumber, 
			String jpoId, Assignment assignment, 
			JuvenileCasefile casefile ) throws RuntimeException, Exception
	{
		//for Deferred Adjudication Casefiles, the maysi flag is set to false in the setRiskAnalysisFlag method no matter what this method returns
		if(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ.equalsIgnoreCase(getSupCatFromType(casefile.getSupervisionTypeId())))
		{
			casefile.setIsMAYSINeeded( false );
			return;
		}
		casefile.setIsMAYSINeeded( false );
		if( ! maysiExists( juvNum, referralNumber ) )
		{
			casefile.setIsMAYSINeeded( true );
			try
			{
				OfficerProfile officer = casefile.getProbationOfficer();
				String officerJIMSLogonId = officer.getLogonId();
				casefile.getSequenceNumber();
				
				JuvenileNotificationGenerator.sendNotificationToConductMAYSI( juvNum, officerJIMSLogonId, assignment );
			}
			catch(RuntimeException e)
			{
				e.printStackTrace();
				throw new Exception( "Error Occurred during the MAYSI Notification" );
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	*//**
	 * Get supervision category for given sup type. Returns "" if not found;
	 * 
	 * @param aSupTypeId
	 * @returns the supTypeCatId
	 * @return
	 *//*
	private static String getSupCatFromType(String aSupTypeId)throws RuntimeException, Exception
	{

		SupervisionTypeMap sMap = null;
		Iterator supervisioncategoryIter = null;
		try
		{
			supervisioncategoryIter = SupervisionTypeMap.findAll( "supervisionTypeId", aSupTypeId );
			while(supervisioncategoryIter.hasNext())
			{
				sMap = (SupervisionTypeMap)supervisioncategoryIter.next();
			}

		} catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception( "Error Occurred during the setting of the risk flags" );
		}
		String supervisionCategory = sMap.getSupervisionCatId();
		if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ.equalsIgnoreCase( supervisionCategory ) )
		{
			return supervisionCategory;
		}
		return (UIConstants.EMPTY_STRING);
	}
		
	*//**
	 * @param juvenileNum
	 * @param supervisionType
	 * @param assignmentAddDate
	 * @param casefile
	 *//*
	private void setRiskAnalysisFlag( String juvenileNum, 
			Code supervisionType, Date assignmentAddDate, 
			JuvenileCasefile casefile, String referralNumber ) throws RuntimeException, Exception
	{
		String supervisionTypeId = supervisionType.getCode();
		SupervisionTypeMap sMap = null;
		Iterator supervisioncategoryIter = null;
		try
		{
			supervisioncategoryIter = SupervisionTypeMap.findAll( "supervisionTypeId", supervisionTypeId );
			while(supervisioncategoryIter.hasNext())
			{
				sMap = (SupervisionTypeMap)supervisioncategoryIter.next();
			}

		} catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception( "Error Occurred during the setting of the risk flags" );
		}
		if( sMap == null )
		{
			throw new Exception( "Error Occurred during the setting of the risk flags" );
		}
		// added this edit for activity 74021  supervisiontype DP90 aka DPS
		if (PDCodeTableConstants.CASEFILE_SUPERVISION_TYPE_DEFERRED_PROSECUTION_SUPERVISION.equalsIgnoreCase(supervisionTypeId) )
		{
			casefile.setIsMAYSINeeded( false );
		}
		String supervisionCategory = sMap.getSupervisionCatId();
		if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_PRE_ADJ.equalsIgnoreCase( supervisionCategory ) )
		{
			//casefile.setIsTestingRiskNeeded( false );   //taken out for US 14459
																									
			 taken out for US 14459
			 * if( !isRiskAnalysisAssessmentCompleted( casefile, juvenileNum, RiskAnalysisConstants.RISK_TYPE_INTERVIEW ) )
			{
				//ER 15119 changes
				casefile.setIsInterviewRiskNeeded( false );
			}
			
			
			if( (!isRiskAnalysisAssessmentCompleted( casefile, juvenileNum, RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE )) && 
					(!isRiskAnalysisAssessmentCompleted( casefile, juvenileNum, RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE )) && 
					(!isRiskAnalysisAssessmentCompleted( casefile, juvenileNum, RiskAnalysisConstants.RISK_TYPE_DETENTION )) )
			{
				casefile.setIsReferralRiskNeeded( true );
			}
		}
		else if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_PRE_PETITION.equalsIgnoreCase( supervisionCategory ) )
		{
			// change interview to false for defect 58185
			//taken out for US 14459
			//casefile.setIsInterviewRiskNeeded( false );

			if( (!isRiskAnalysisAssessmentCompletedWithin10Days( casefile, juvenileNum, RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE )) && 
					(!isRiskAnalysisAssessmentCompleted( casefile, juvenileNum, RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE )) &&
					(!isRiskAnalysisAssessmentCompletedWithin10Days( casefile, juvenileNum, RiskAnalysisConstants.RISK_TYPE_DETENTION )) )
			{
				casefile.setIsReferralRiskNeeded( true );
			}
		} //removed for u.s #41376
		if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ.equalsIgnoreCase( supervisionCategory ) )
		{
			casefile.setIsProgressRiskNeeded( false );
			casefile.setIsResProgressRiskNeeded( false );
			casefile.setIsResidentialRiskNeeded( false );
			casefile.setIsCommunityRiskNeeded( false );
			//casefile.setIsMAYSINeeded( true ); // changed to true for activity 74021			
			
			//added this edit 8/30/13 - activity 76050
			if ( maysiExists( juvenileNum, referralNumber ) ){
				casefile.setIsMAYSINeeded( false );
			}
			
			//changed the above code for defect 76891
			//users changed their mind, now if the case is a deferred adjudication the MAYSI should always = No
			casefile.setIsMAYSINeeded( false );
		}
		else if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM.equalsIgnoreCase( supervisionCategory ) )
		{
			//casefile.setIsProgressRiskNeeded( true ); removed for U.S 41377
			// added for defect #52979
			casefile.setIsResidentialRiskNeeded( false );
			 User Story 41375 - PACT
			if( !isRiskAnalysisAssessmentCompleted( casefile, juvenileNum, RiskAnalysisConstants.RISK_TYPE_COMMUNITY ) )
			{
				casefile.setIsCommunityRiskNeeded( true );
			}
		}
		else if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES.equalsIgnoreCase( supervisionCategory ) )
		{
			casefile.setIsResProgressRiskNeeded( true );
			if( !isRiskAnalysisAssessmentCompleted( casefile, juvenileNum, RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL ) )
			{
				casefile.setIsResidentialRiskNeeded( true );
			}
		}
		else
		{
			sMap = null;
			supervisioncategoryIter = null;
			return;
		}

		sMap = null;
		supervisioncategoryIter = null;
	}

	*//**
	 * RiskAnalysisForJuvenile within 30 days
	 * @param casefile
	 * @param juvenileNum
	 * @param assessmentType
	 * @return
	 *//*
	private boolean isRiskAnalysisAssessmentCompleted( 
			JuvenileCasefile casefile, String juvenileNum, 
			String assessmentType ) throws RuntimeException, Exception
	{
		GetRecentRiskAnalysisForJuvenileEvent event = new GetRecentRiskAnalysisForJuvenileEvent();
		event.setJuvenileNumber( juvenileNum );
		event.setAssessmentType( assessmentType );
		Iterator riskAnalysis = RiskAnalysis.findAll( event );
		if( riskAnalysis.hasNext() )
		{
			return true;
		}

		return false;
	}
	
	*//**
	 * RiskAnalysisForJuvenile within 10 days
	 * @param casefile
	 * @param juvenileNum
	 * @param assessmentType
	 * @return
	 *//*
	private boolean isRiskAnalysisAssessmentCompletedWithin10Days( 
			JuvenileCasefile casefile, String juvenileNum, 
			String assessmentType ) throws RuntimeException, Exception
	{
		GetRecentRiskAnalysisForJuvenileUpTo10DaysEvent event = new GetRecentRiskAnalysisForJuvenileUpTo10DaysEvent();
		event.setJuvenileNumber( juvenileNum );
		event.setAssessmentType( assessmentType );
		Iterator riskAnalysis = RiskAnalysis.findAll( event );
		if( riskAnalysis.hasNext() )
		{
			return true;
		}

		return false;
	}
	
	

	*//**
	 * @param juvNum
	 * @param assignmentAddDate
	 * @param casefile

**************************************************************
*	13july2009 - mjt - the call to this function has been commented out, 
* so there is no use in leaving this function lying around. obviously,
*	if the call to this function is "re-enabled", then undo the comment-out ...
* 
	private void setBenefitAssessmentFlag( 
			String juvNum, Date assignmentAddDate, 
			JuvenileCasefile casefile ) throws RuntimeException, Exception
	{
		casefile.setIsBenefitsAssessmentNeeded( false );
		/*
		 * GetLatestBenefitsAssessmentForJuvenileEvent request = new
		 * GetLatestBenefitsAssessmentForJuvenileEvent();
		 * request.setJuvenileId(juvNum); Iterator assessments =
		 * BenefitsAssessment.findAll(request); while(assessments.hasNext()){
		 * BenefitsAssessment ba = (BenefitsAssessment) assessments.next(); if(ba !=
		 * null && assignmentAddDate != null){ Date entryDate = ba.getEntryDate();
		 * if(entryDate != null){ double days = assignmentAddDate.getTime() -
		 * entryDate.getTime(); days = days / (2460601000); if(days < 10.0){
		 * casefile.setIsBenefitsAssessmentNeeded(false); } } entryDate = null; ba =
		 * null; } } request = null;

	}
  ********************    end commented-out function 
  **//* 

	
	*//**
	 * @param casefile
	 * @param referralNum
	 * @return
	 *//*
	private boolean doesReferralExists( 
			JuvenileCasefile casefile, String referralNum ) throws RuntimeException, Exception
	{
		Iterator<Assignment> refIter = casefile.getAssignments().iterator();
		while(refIter.hasNext())
		{
			Assignment assignment = refIter.next();
			if( referralNum.equalsIgnoreCase( assignment.getReferralNumber() ) )
			{
				assignment = null;
				return true;
			}
			assignment = null;
		}
		refIter = null;
		
		return false;
	}

	*//**
	 * @param jjsService
	 * @param casefile
	 *//*
	private Assignment createAssignment( JJSService jjsService, JuvenileCasefile casefile )
	{
		Assignment assignment = new Assignment();
		
		populateAssignmentFromService( jjsService, assignment );
		casefile.insertAssignments( assignment );
		assignment.setCaseFileId( casefile.getOID().toString() );
		assignment.setCreateUserID( PDJuvenileCaseConstants.CASEFILE_CREATOR );
		
		return assignment;
	}

	*//**
	 * @param jjsService
	 * @param errorMessage
	 * @param errors
	 *//*
	private Map recoredErrorsForNotification( 
			JJSService jjsService, String errorMessage, Map errors )
	{
		String caseLoadManagerId = jjsService.getCaseLoadManagerId();
		JuvenileCasefileExtractionErrorBean bean = createErrorBean( jjsService, errorMessage );
		List list = (List)errors.get( caseLoadManagerId );
		
		if ( list != null ) {
			errors.remove( caseLoadManagerId );
			list.add( bean );
			errors.put( caseLoadManagerId, list );
		} 
		else
		{
			list = new ArrayList();
			list.add( bean );
			errors.put( caseLoadManagerId, list );
		}
		
		list = null;
		bean = null;
		
		return errors;
	}

	*//**
	 * @param jjsService
	 * @param assignment
	 *//*
	private void populateAssignmentFromService( JJSService jjsService, Assignment assignment )
	{
		assignment.setAssignmentAddDate( jjsService.getAddDate() );
		assignment.setAssignmentLevelId( jjsService.getAssignmentLevelId() );
		assignment.setReferralNumber( jjsService.getReferralNum() );
		assignment.setServiceUnitId( jjsService.getUnitId() );
		assignment.setAssignByUserId(jjsService.getAssignByUserId());
	}

	*//**
	 * @param juvNum	
	 * @return
	 *//*
	private boolean maysiExists( String juvNum) throws RuntimeException, Exception
	{
		GetAllMAYSIAssessmentsEvent queryEvent = new GetAllMAYSIAssessmentsEvent();
		queryEvent.setJuvenileNumber( juvNum );
//		queryEvent.setReferralNumber( referralNumber );
		Iterator iter = JuvenileMAYSIComplete.findAll( queryEvent );
		SortedMap map = new TreeMap();
		// 08/30/2013
		// Revised to find first referral and check for existing MAYSI 
//		if( iter.hasNext() )
//		{
//			return true;
//		}		

		while(iter.hasNext()){
			JuvenileMAYSIComplete  jmc = (JuvenileMAYSIComplete) iter.next();
			map.put(jmc.getReferralNumber(), jmc);
		}
		List temp = new ArrayList(map.values());
		if (temp.size() > 0) {
			JuvenileMAYSIComplete  jmc = (JuvenileMAYSIComplete) temp.get(0);
			if ("1010".equals(jmc.getReferralNumber())) {
				return true;
			}
		}
		temp = null;
		
		return false;
	}
	
	*//**
	 * @param juvNum
	 * @param referralNumber
	 * @return
	 *//*
	private boolean maysiExists( String juvNum, String referralNumber) throws RuntimeException, Exception
	{
		GetAllMAYSIAssessmentsEvent queryEvent = new GetAllMAYSIAssessmentsEvent();
		queryEvent.setJuvenileNumber( juvNum );
		queryEvent.setReferralNumber( referralNumber );
		Iterator iter = JuvenileMAYSIComplete.findAll( queryEvent );
		SortedMap map = new TreeMap();
		// 08/30/2013
		// Revised to find first referral and check for existing MAYSI 
//		if( iter.hasNext() )
//		{
//			return true;
//		}		

		while(iter.hasNext()){
			JuvenileMAYSIComplete  jmc = (JuvenileMAYSIComplete) iter.next();
			map.put(jmc.getReferralNumber(), jmc);
		}
		List temp = new ArrayList(map.values());
		if (temp.size() > 0) {
			JuvenileMAYSIComplete  jmc = (JuvenileMAYSIComplete) temp.get(0);
			if (referralNumber.equals(jmc.getReferralNumber())) {
				return true;
			}
		}
		temp = null;
		
		return false;
	}

	*//**
	 * @param jjsService
	 * @return
	 *//*
	private static OfficerProfile getOfficer( JJSService jjsService ) 
			throws InvalidProbationOfficerException, RuntimeException, Exception
	{
		OfficerProfile officer = null;
		String jpoUserId = jjsService.getProbationOfficerId();

		if( jpoUserId == null || jpoUserId.equals( UIConstants.EMPTY_STRING ) )
		{
			throw new InvalidProbationOfficerException( "Invalid Probation Officer Exception" );
		}
		else
		{
			Iterator<OfficerProfile> officers = OfficerProfile.findAll( "logonId", jpoUserId );
			if( officers.hasNext() )
			{
				while(officers.hasNext())
				{
					officer = officers.next();
					String status = officer.getStatusId();
					if( status != null && status.equalsIgnoreCase( "A" ) )
					{
						return officer;
					}
				}
			}
			else
			{
				throw new InvalidProbationOfficerException( "Invalid Probation Officer Exception" );
			}
		}
		return officer;
	}

	// <KISHORE>JIMS200052303 : MJCW CF Transfer Future Events not transferred
	// Gets all the future non cancelled events for a given casefile
	private Collection<CalendarServiceEventResponseEvent> 
			findCalendarEvents( String jpoUserId, String juvNum, String casefileId )
	{
		Collection<CalendarServiceEventResponseEvent> eventsList = new ArrayList();
		Collection<CalendarServiceEventResponseEvent> futureNonCancelledEventsList = new ArrayList();

		GetViewCalendarEventsEvent vce = (GetViewCalendarEventsEvent)
				EventFactory.getInstance( ServiceEventControllerServiceNames.GETVIEWCALENDAREVENTS );

		vce.setOfficerId( jpoUserId );
		vce.setJuvenileNum( juvNum );

		Calendar rightNow = Calendar.getInstance();
		vce.setEventStartDate( rightNow.getTime() );

		CompositeResponse response = MessageUtil.postRequest( vce );
		ErrorResponseEvent errResp = (ErrorResponseEvent)MessageUtil.filterComposite( response, ErrorResponseEvent.class );
		if( errResp == null )
		{
			if( !response.hasResponses() )
			{
				return eventsList;
			}
			eventsList = MessageUtil.compositeToList( response, CalendarServiceEventResponseEvent.class );
		}

		if( eventsList != null && !eventsList.isEmpty() )
		{
			for(CalendarServiceEventResponseEvent tEvent : eventsList)
			{
				if( (casefileId.equalsIgnoreCase( tEvent.getCasefileId() )) && 
						(!tEvent.getEventStatusCode().equalsIgnoreCase( "CC" )) && 
						(!tEvent.getJuvenileAttendanceStatus().equals( PDCalendarConstants.JUV_ATTEND_STATUS_CANCELLED )) )
				{
					futureNonCancelledEventsList.add( tEvent );
				}
			}
		}
		
		return futureNonCancelledEventsList;
	}
	
	private void sendJPOSubsequentNotifications(JJSService jjsService, OfficerProfile officer, JuvenileCasefile casefile, int jobId, Map errors) throws RuntimeException, Exception
	{
//		String currentJPOID = officer.getOID().toString();
		Juvenile juvenile = casefile.getJuvenile();
		String juvenileName = juvenile.getFirstName() + " " + juvenile.getLastName();
		String juvenileNum = juvenile.getJuvenileNum();
//		String officerJIMSLogonId = casefile.getProbationOfficer().getLogonId();
		String referralDate = DateUtil.dateToString(jjsService.getServiceDate(), DateUtil.DATE_FMT_1);
		String referralNum = jjsService.getReferralNum();
		SearchJuvenileCasefilesEvent search = 
			(SearchJuvenileCasefilesEvent) EventFactory.getInstance( JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES );
		search.setSearchType( "JNUM" );
		search.setJuvenileNum( juvenileNum);
		CompositeResponse responses = MessageUtil.postRequest( search );
		List casefiles = MessageUtil.compositeToList(responses,  JuvenileCasefileSearchResponseEvent.class);
		List jpoList = new ArrayList(); 
		SortedMap jpoMap = new TreeMap();

		if (casefiles != null){
			for (int x = 0; x<casefiles.size(); x++){
				JuvenileCasefileSearchResponseEvent event = (JuvenileCasefileSearchResponseEvent) casefiles.get(x);
				if ("ACTIVE".equals(event.getCaseStatus()) ) {
					jpoMap.put(event.getJpoId(), event);
				}	
			}
			jpoList = new ArrayList(jpoMap.values()); 
			String activeCaseCLMLogonId = "";
			for (int y = 0; y<jpoList.size(); y++){
				JuvenileCasefileSearchResponseEvent event = (JuvenileCasefileSearchResponseEvent) jpoList.get(y);
				//create JPO notification
				try
				{
					JuvenileNotificationGenerator.sendSubsequentReferralNotification(juvenileName, juvenileNum, referralNum, referralDate, event.getOfficerLoginId());
				}
				catch(RuntimeException e)
				{
					e.printStackTrace();
					errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
					Log.createLog( jobId, getCurrentTime(), e.getMessage(), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
				}
				catch(Exception e)
				{
					String str = logMessage( jjsService, e.getMessage() ) ;
					Log.createLog( jobId, getCurrentTime(), str, "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
					e.printStackTrace();
					errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
				}	
				//create CLM notification
				OfficerProfile officerProfile = OfficerProfile.find(event.getJpoId() ); 
				activeCaseCLMLogonId = officerProfile.getManagerId();
				if (activeCaseCLMLogonId != null && !"".equals(activeCaseCLMLogonId)) {
					try
					{
						JuvenileNotificationGenerator.sendSubsequentReferralNotification(juvenileName, juvenileNum, referralNum, referralDate, activeCaseCLMLogonId);
					}
					catch(RuntimeException e)
					{
						e.printStackTrace();
						errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
						Log.createLog( jobId, getCurrentTime(), e.getMessage(), "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
					}
					catch(Exception e)
					{
						String str = logMessage( jjsService, e.getMessage() ) ;
						Log.createLog( jobId, getCurrentTime(), str, "E", PDJuvenileCaseConstants.CASEFILE_CREATOR );
						e.printStackTrace();
						errors = this.recoredErrorsForNotification( jjsService, e.getMessage(), errors );
					}				
				}
			}
			activeCaseCLMLogonId = null;
		}
		jpoList = null;
		jpoMap = null;
		
	}
}
*/
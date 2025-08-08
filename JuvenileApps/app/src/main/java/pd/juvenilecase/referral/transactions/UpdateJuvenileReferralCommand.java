package pd.juvenilecase.referral.transactions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import messaging.calendar.GetViewCalendarEventsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.casefile.CreateActivityEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.family.SaveFamilyMemberFinancialEvent;
import messaging.juvenilecase.GetCasefilesForReferralsEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvReferralFamilyResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.mentalhealth.GetAllMAYSIAssessmentsEvent;
import messaging.notification.SendCasefileClosingNotificationEvent;
import messaging.notification.SendOverdueInterviewNotificationEvent;
import messaging.productionsupport.GetTransOffenseReferralsQueryEvent;
import messaging.referral.GetJJSDecisionHistoryEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.SaveJJSReferralEvent;
import messaging.referral.UpdateJuvenileReferralEvent;
import messaging.riskanalysis.GetRecentRiskAnalysisForJuvenileEvent;
import messaging.scheduling.RegisterTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.PersistentEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.transaction.multidatasource.SaveException;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import mojo.naming.CalendarConstants;
import naming.ActivityConstants;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileCasefileNotificationControllerServiceNames;
import naming.PDCalendarConstants;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.PDTaskConstants;
import naming.RiskAnalysisConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;
import pd.address.Address;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.codetable.person.ReasonNotDone;
import pd.common.calendar.CalendarEventContext;
import pd.common.util.NameUtil;
import pd.contact.officer.OfficerProfile;
import pd.exception.InvalidProbationOfficerException;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.JJSFamily;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.JuvenileNotificationGenerator;
import pd.juvenilecase.casefile.ActivityHelper;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.FamilyMemberPhone;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIComplete;
import pd.juvenilecase.referral.JJSDecisionHistory;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSSVIntakeHistory;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.km.util.Name;
import pd.security.PDSecurityHelper;
import ui.common.PhoneNumber;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.referral.JuvenileReferralMemberDetailsBean;
import ui.juvenilecase.referral.JuvenileReferralOffenseBean;
import ui.security.SecurityUIHelper;


/**
 * 
 * @author ugopinath
 *
 */
public class UpdateJuvenileReferralCommand /*extends CasefileExtractionUtility no longer in use refer 87188  */implements ICommand {

    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateJuvenileReferralEvent evt = (UpdateJuvenileReferralEvent)event;
	  IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	  IHome home = new Home();
	  String fromEmail = "jims2notification@itc.hctx.net";
	  StringBuffer offenseCodes = new StringBuffer(100);
	 String oldRefStat="";
    	  GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
    	    jjsEvt.setJuvenileNum(evt.getJuvenileNum());
    	    jjsEvt.setReferralNum(evt.getReferralNum());
    	    Iterator<JJSReferral> refIter = JJSReferral.findAll(jjsEvt);
    
    	    if (refIter.hasNext())
    	    {
    		JJSReferral ref = refIter.next();
    		if(ref.getCloseDate()!=null)
    		    oldRefStat="CLOSED";
    		else
    		    oldRefStat="ACTIVE";
    		
    		 ref.setReferralNum(evt.getReferralNum());
    		 ref.setReferralDate(evt.getReferralDate());
    		 ref.setIntakeDate(evt.getIntakeDate());
    		 ref.setIntakeDecisionId(evt.getIntakeDecisionId());
    		 ref.setTJPCDisp(evt.getTJPCDisp());
    		 ref.setJuvenileNum(evt.getJuvenileNum());
    		 ref.setReferralSource(evt.getReferralSource());
    		 ref.setOffenseTotal(evt.getOffenseTotal());				
    		 ref.setJpoId(evt.getJpoID());
    		 ref.setProbJPOId(evt.getProbJPOId());
    		 ref.setCtAssignJPOId(evt.getCtAssignJPOId());
    		 ref.setProbationStartDate(evt.getProbationStartDate());
    		 ref.setProbationEndDate(evt.getProbationEndDate());
    		 ref.setCloseDate(evt.getClosedDate());
    		 ref.setLcUser(UIUtil.getCurrentUserID());
    		 ref.setUpdateUserID(SecurityUIHelper.getJIMSLogonId());
    		 ref.setUpdateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
    		 ref.setLcDate(DateUtil.getCurrentDate());    		
    		 ref.setLcTime(Calendar.getInstance().getTime());
    		 //Bug #85898
    		 if(ref.getReferralTypeInd()==null || ref.getReferralTypeInd().equals(""))//Hot Fix Bug #92017
    		 {
    		     ref.setReferralTypeInd("PA");
        	     //task 171521
        	     ref.setCountyREFD("101");
    		 }
    		//task 171521
    		if ( ref.getReferralTypeInd() != null&& !ref.getReferralTypeInd().equals(""))
    	    	{
    	    	    if(ref.getReferralTypeInd().equalsIgnoreCase("IC"))
    	    		ref.setCountyREFD("755");
    	    	    else if(ref.getReferralTypeInd().equalsIgnoreCase("CD"))
    	    		ref.setCountyREFD("756");
    	    	    else if(ref.getReferralTypeInd().equalsIgnoreCase("TR"))
    	    	    {
    	    		String countyId="";
    	    		GetTransOffenseReferralsQueryEvent getEvent = new GetTransOffenseReferralsQueryEvent();
    	    		getEvent.setJuvenileNumber(evt.getJuvenileNum());
    	    		getEvent.setReferralNumber(evt.getReferralNum());
                	Iterator<JJSTransferredOffenseReferral> transOffenseReferralsIter = JJSTransferredOffenseReferral.findAll(getEvent);                	    
                	while(transOffenseReferralsIter.hasNext()) 
                	{
                	    JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();
                	    countyId=transOffenseReferral.getFromCountyCode();
                	    ref.setCountyREFD(countyId);
                	}
                	   
    	    	    }
    	    	    else
    	    		ref.setCountyREFD("101");
    	    	}
    		 home.bind(ref);
    		 
    		if( evt.getTJPCDisp() != null && StringUtils.isNotBlank(evt.getTJPCDisp())) {
    	    	    
    	    	this.updateJJSDecisionHistory(evt);
	    	}
    	    }
	
    	    
	    //create or update offense
	    Iterator offenseIter = evt.getOffenses().iterator();
	    while (offenseIter.hasNext())
	    {
		//ref.setIntakeDecision(new Code())
		JuvenileReferralOffenseBean offBean = (JuvenileReferralOffenseBean) offenseIter.next();
		if(offBean.getOffenseID()==null)   //new offense
		{
        		JJSOffense offense = new JJSOffense();
        		offense.setOffenseDate(offBean.getOffenseDate());
        		offense.setOffenseCodeId(offBean.getOffenseCode());
        		offense.setReferralNum(evt.getReferralNum());
        		offense.setInvestigationNumber(offBean.getInvestigationNum());
        		offense.setKeyMapLocation(offBean.getKeyMapLocation());
        		offense.setSequenceNum(offBean.getSequenceNum());
        		offense.setJuvenileNum(evt.getJuvenileNum());
        		offense.setCreateUserID(UIUtil.getCurrentUserID());
        		offense.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
        		offense.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
        		//Bug #81685 - get offense severity
        		JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode",offBean.getOffenseCode());
        		if (offenseCode != null)
        		{
        		    offense.setSeverity(offenseCode.getSeverity());
        		}
        		home = new Home();
        		home.bind(offense);
        		offenseCodes.append(offBean.getOffenseCode());
        		if (offenseIter.hasNext())
        		    offenseCodes.append(", ");
		}
		else
		{
		    JJSOffense offense = JJSOffense.find(offBean.getOffenseID());
		    offense.setOffenseCodeId(offBean.getOffenseCode());    		   
    		    offense.setInvestigationNumber(offBean.getInvestigationNum());
    		    offense.setKeyMapLocation(offBean.getKeyMapLocation()); 
    		    offense.setOffenseDate(offBean.getOffenseDate());
    		    home = new Home();
    		    home.bind(offense);
		    
		}
	    }
	

	
	//Create Intake History
	
        	JJSSVIntakeHistory intakeHistory = new JJSSVIntakeHistory();
        	intakeHistory.setAssignmentType(evt.getAssignmentType());
        	intakeHistory.setAssignmentDate(evt.getAssignmentDate());
        	intakeHistory.setIntakeDate(evt.getIntakeDate());
        	intakeHistory.setIntakeDecisionId(evt.getIntakeDecisionId());
        	//intakeHistory.setJpoId(evt.getJpoID()); //commented for BUG 164605
        	//added for BUG 164605 STARTS
        	if (evt.getJpoID() != null)
        	{ 
        	    intakeHistory.setJpoId(evt.getJpoID());
        	}
        	else
        	    if (evt.getCtAssignJPOId() != null)
        	    {
        		intakeHistory.setJpoId(evt.getCtAssignJPOId());
        	    }
        	    else
        		if (evt.getProbJPOId() != null)
        		{
        		    intakeHistory.setJpoId(evt.getProbJPOId());
        		}
        	//added for BUG 164605 ENDS
        	intakeHistory.setJuvenileNum(evt.getJuvenileNum());
        	intakeHistory.setReferralNumber(evt.getReferralNum());
        	intakeHistory.setCreateUserID(UIUtil.getCurrentUserID());
        	intakeHistory.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
        	intakeHistory.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
        	intakeHistory.setSupervisionTypeId(evt.getSupervisionType());
        	
        	home = new Home();
        	home.bind(intakeHistory);
	String casefileId= evt.getCasefileId();
	if(evt.getUpdateAsmntTypeFlag()!=null && evt.getUpdateAsmntTypeFlag().equalsIgnoreCase("Y"))
	{
                String jpoIdFromUI= "";
        	if(evt.getJpoID()!=null){
        	    jpoIdFromUI = evt.getJpoID();
        	}else if(evt.getCtAssignJPOId()!=null){
        	    jpoIdFromUI = evt.getCtAssignJPOId();
        	}else if(evt.getProbJPOId()!=null){
        	    jpoIdFromUI = evt.getProbJPOId();
        	}
	    	if(evt.getCasefileGenerate()!=null && evt.getCasefileGenerate().equalsIgnoreCase("Y") && !(jpoIdFromUI.equalsIgnoreCase("UVANC") || jpoIdFromUI.equalsIgnoreCase("ANC")))
                {
        	      OfficerProfile officer = null;
        		try
        		{
        			officer = getOfficer( jpoIdFromUI );
        		}
        		catch(InvalidProbationOfficerException e)
        		{
        			
        		}
        
        	      String jpoId = officer.getOID().toString();
        	      String juvNum = evt.getJuvenileNum();
        	      String supTypeId = evt.getSupervisionType();
        	      String referralNum = evt.getReferralNum();
        	      Assignment assignment=null;
        	      boolean assignmentCreated = false;
        	      JuvenileCasefile casefile = JuvenileCasefile.find( jpoId, juvNum, supTypeId );
        	      if( casefile == null )
        		{		  	
        			casefile = JuvenileCasefile.find( juvNum, supTypeId );
        			//same casefile with a different jpo
        			if( casefile != null && 
        				! PDCodeTableConstants.STATUS_CLOSED.equalsIgnoreCase( casefile.getCaseStatusId() ) )
        			{
        			    if( this.doesReferralExists( casefile, referralNum ) )
        			    {
        				String oldJPOName = casefile.getProbationOfficerFullName();

					
					// All the future events of old JPO should be assigned to new JPO
					home = new Home();
					Collection<CalendarServiceEventResponseEvent> events = 
							findCalendarEvents( casefile.getProbationOfficerId(), casefile.getJuvenileNum(), casefile.getOID() );
					if( events != null )
					{
						Iterator<CalendarServiceEventResponseEvent> eventsIterator = events.iterator();
						while(eventsIterator.hasNext())
						{
							CalendarServiceEventResponseEvent calEvent = eventsIterator.next();
							Iterator<CalendarEventContext> cont = CalendarEventContext.findByCalendarEventId( calEvent.getCalendarEventId() );
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
					
					GetCasefilesForReferralsEvent searchEvent = new GetCasefilesForReferralsEvent();
					searchEvent.setReferralNum(referralNum);
					searchEvent.setJuvenileNum(evt.getJuvenileNum());

					JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
					resp.setReferralNumber(searchEvent.getReferralNum());

					Collection<JuvenileCasefileReferral> coll = new ArrayList<JuvenileCasefileReferral>();

					Iterator<JuvenileCasefileReferral> casefileRefItr = JuvenileCasefileReferral.findAll(searchEvent);
					while (casefileRefItr.hasNext())
					{
					    JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) casefileRefItr.next();
					    coll.add(caseRef);
					}
					resp.setCasefileReferrals(coll);
					Collection<JuvenileCasefileReferral> casefilesResp = resp.getCasefileReferrals();
					    Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
						@Override
						public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
						{
						    if (evt1.getRefSeqNum() != null && evt2.getRefSeqNum() != null)
							return evt1.getRefSeqNum().compareTo(evt2.getRefSeqNum());
						    else
							return -1;
						}
					    }));

					Iterator<JuvenileCasefileReferral> casefilesRespItr = casefilesResp.iterator();
					Iterator<Assignment> assignmentIter = Assignment.findAll("caseFileId", casefile.getOID());
					   if (assignmentIter.hasNext())
					    {
						assignment = (Assignment) assignmentIter.next();
						if (assignment.getReferralNumber().equals(referralNum))
						{
						    Assignment newAssignment = new Assignment();
						    newAssignment.setAssignmentAddDate( evt.getAssignmentDate());		
						    newAssignment.setReferralNumber( assignment.getReferralNumber() );
						    newAssignment.setAssignmentType(evt.getAssignmentType());
						    // assgnmt.setAssignmentAddDate(evt.getAssignmentDate());
						    if(casefilesRespItr.hasNext()){
							JuvenileCasefileReferral casefileRef = casefilesRespItr.next();
							if(casefileRef!=null){
							    newAssignment.setSeqNum(String.valueOf(Integer.valueOf(casefileRef.getRefSeqNum())+1));
							}
						    }
						    casefile.insertAssignments(newAssignment);
						}
					    }
					

					try
					{
						JuvenileNotificationGenerator.sendJPOUpdateNotification( casefile, referralNum, oldJPOName );
					}
					catch(RuntimeException e)
					{
					    ErrorResponseEvent errorResp = new ErrorResponseEvent();
					    errorResp.setMessage("Error sending JPO Update notification. " + e.getMessage());
					    dispatch.postEvent(errorResp);
					}
					catch(Exception e)
					{
					    ErrorResponseEvent errorResp = new ErrorResponseEvent();
					    errorResp.setMessage("Error sending JPO Update notification. " + e.getMessage());
					    dispatch.postEvent(errorResp);
					}
        			    }
        			    else
        			    {
        				//update the casefile with new jpo - Bug #83137
        				casefile.setProbationOfficerId(jpoId);
        				casefile.setOfficerLastNameData(officer.getLastName());
        				casefile.setOfficerFirstNameData(officer.getFirstName());
        				casefile.setOfficerMiddleNameData(officer.getMiddleName());
                			    ErrorResponseEvent errorResp = new ErrorResponseEvent();
                			    errorResp.setMessage("Casefile already exists with this supervision type and a status not equal to Closed.  Assignment details added to Casefile# " + casefile.getOID());			
                			    //IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REPLY);
                			    dispatch.postEvent(errorResp);
        			    }
        			}//end if same casefile same supervision details different jpo
        			 else
        			{
        				try
        				{
        					//Anytime a casefile is created a record is written by a DB trigger to JIMS2.JCJPOASSNMNTHIST. 
        					//This table is keeping a history of JPO assignments for a casefile.
        					casefile = generate( juvNum, jpoIdFromUI, supTypeId );
        					setRISKNEEDFlag(juvNum, evt.getSupervisionType(), evt.getSupervisionCat(), casefile);
        					   home = new Home();
        					    home.bind(casefile);
        					//retrieve the casefile again to get the seq num generated by the trigger.
        					casefile = JuvenileCasefile.find( jpoId, juvNum, supTypeId );  //bug fix 22374 
        				}
        				catch(RuntimeException e)
        				{
        				    ErrorResponseEvent errorResp = new ErrorResponseEvent();
        				    errorResp.setMessage("Error creating casefile." + e.getMessage());				   
        				    dispatch.postEvent(errorResp);
        					    
        				}
        				catch(Exception e)
        				{
        				   ErrorResponseEvent errorResp = new ErrorResponseEvent();
        				   errorResp.setMessage("Error creating casefile. " + e.getMessage());				
        				   dispatch.postEvent(errorResp);			  
        				}
        				try
        				{
        					JuvenileNotificationGenerator.createTask( casefile, jpoId );
        				}
        				catch(RuntimeException e)
        				{
        				    ErrorResponseEvent errorResp = new ErrorResponseEvent();
        				    errorResp.setMessage("Error occurred during the creation of Task after the successful generation of casefile. " + e.getMessage());				   
        				    dispatch.postEvent(errorResp);
        					
        				}
        				catch(Exception e)
        				{
        				    	ErrorResponseEvent errorResp = new ErrorResponseEvent();
        					errorResp.setMessage("Error occurred during the creation of Task after the successful generation of casefile. " + e.getMessage());				   
        					dispatch.postEvent(errorResp);
        				}
        			}//end else same casefile same supervision details different jpo
        		}//end if casefile not found with same jpo
        	      else
        		{
        //casefile not equal null
        			if( ! PDCodeTableConstants.STATUS_CLOSED.equalsIgnoreCase( casefile.getCaseStatusId() ) )
        			{
        			    if( this.doesReferralExists( casefile, referralNum ) )
        			    {
        				  ErrorResponseEvent errorResp = new ErrorResponseEvent();
					    errorResp.setMessage("An unclosed casefile for Juvenile/JPO/SupervisionType already exists with a matching referral.");
					    dispatch.postEvent(errorResp);
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
        								(getAgeAtOffense(birthDate, jOff.getOffenseDate()) < 17 || 
        								 "17".equals( jOff.getOffenseReportGroup() )) )
        						{
        						    	//assignment = this.createAssignment( evt, casefile );
        							// Create Task for supervisionType = Deferred Adjudication, Prosecution
        							if( casefile.getSupervisionTypeId().equalsIgnoreCase( 
        											UIConstants.CASEFILE_SUPERVISION_TYPE_DEFERRED_ADJUDICATION_SUPERVISION ) || 
        									casefile.getSupervisionTypeId().equalsIgnoreCase( 
        											UIConstants.CASEFILE_SUPERVISION_TYPE_DEFERRED_PROSECUTION_SUPERVISION ) )
        							{
        								String subject = "Schedule Overdue Deferred adjudication/prosecution for Juvenile # " + juvNum;
        								this.scheduleOverdueInterviewTask( 
        										jpoIdFromUI, 
        										PDTaskConstants.MJCW_JPO_OVERDUE_DEFERREDADJUDICATION, 
        										subject, casefile, 240, evt.getAssignmentDate(), 
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
        								//this.sendPOStatusOffenderSupervisionNotification( evt, casefile, jjsJuvenile );
        							}
        							skipFlag = false;
        
        							referrals.append( assignment.getReferralNumber() );
        							referrals.append( " on " );
        							referrals.append( assignment.getAssignmentAddDate().toString() );
        							if( iter.hasNext() )
        							{
        								referrals.append( "," );
        							}
        							assignmentCreated = true;
        							assignment = null;
        						}
        						jOff = null;
        					}
        					if( skipFlag )
        					{
        					    assignmentCreated = true;
        					    ErrorResponseEvent errorResp = new ErrorResponseEvent();
        						errorResp.setMessage("Juvenile is between 17 and 19 and active casefile already exists and date of offense did not happen before his/her 17th birthday. No new casefile generated");				   
        						dispatch.postEvent(errorResp);
        						
        					}
        					else
        					{
        						try
        						{
        							JuvenileNotificationGenerator.sendNewAssignmentUpdateJuvenileFor1718Notification( casefile, referrals.toString() );
        						}
        						catch(RuntimeException e)
        						{
        						    ErrorResponseEvent errorResp = new ErrorResponseEvent();
        						    errorResp.setMessage("Error sending notification for new assignemnt. " + e.getMessage());	
        						    //notificationError
        						    errorResp.setErrorLogId("notificationError"); //fixing nekahs bug as part of89877
        						    dispatch.postEvent(errorResp);
        						}
        						catch(Exception e)
        						{
        						    ErrorResponseEvent errorResp = new ErrorResponseEvent();
        						    errorResp.setMessage("Error sending notification for new assignemnt. " + e.getMessage());	
        						    errorResp.setErrorLogId("notificationError");
        						    dispatch.postEvent(errorResp);
        						}
        					}
        					jEvent = null;
        					iter = null;
        
        				}//end if age >17 & <19
        				
        				else
        				{
        					// assignment = this.createAssignment( evt, casefile);
        					// find all active cases for juvenile where current jpo is NOT same as the one assigned
        					// to the subsequent referral and create notifications for jpos and clms, if any
        					
        					if (evt.getAssignmentType()!= null && evt.getAssignmentType().equalsIgnoreCase("SBQ")){
        						//sendJPOSubsequentNotifications(evt, officer, casefile);
        					}
        				
        
        					/*try
        					{
        						JuvenileNotificationGenerator.sendNewAssignmentUpdateNotification( casefile, assignment.getReferralNumber() );
        					}
        					catch(RuntimeException e)
        					{
        					    ErrorResponseEvent errorResp = new ErrorResponseEvent();
        					    errorResp.setMessage("Error sending notification for new assignemnt. " + e.getMessage());				   
        					    dispatch.postEvent(errorResp);
        					}
        					catch(Exception e)
        					{
        					    ErrorResponseEvent errorResp = new ErrorResponseEvent();
        					    errorResp.setMessage("Error sending notification for new assignemnt. " + e.getMessage());				   
        					    dispatch.postEvent(errorResp);
        					}				*/	
        					assignmentCreated = false;
        				}
        				jjsJuvenile = null;
        				birthDate = null;
        			    }//end of else !age >17 & <19
        			}
        		}//end else casefile found
        	      if(!assignmentCreated && casefile!=null && casefile.getOID()!=null)
        	      {
        	       //89766 User-story
        			GetCasefilesForReferralsEvent searchEvent = new GetCasefilesForReferralsEvent();
        			searchEvent.setReferralNum(referralNum);
        			searchEvent.setJuvenileNum(evt.getJuvenileNum());

        			JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
        			resp.setReferralNumber(searchEvent.getReferralNum());

        			Collection<JuvenileCasefileReferral> coll = new ArrayList<JuvenileCasefileReferral>();

        			Iterator<JuvenileCasefileReferral> casefileRefItr = JuvenileCasefileReferral.findAll(searchEvent);
        			while (casefileRefItr.hasNext())
        			{
        			    JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) casefileRefItr.next();
        			    coll.add(caseRef);
        			}
        			resp.setCasefileReferrals(coll);
        			Collection<JuvenileCasefileReferral> casefilesResp = resp.getCasefileReferrals();
        			    Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
        				@Override
        				public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
        				{
        				    if (evt1.getRefSeqNum() != null && evt2.getRefSeqNum() != null)
        					return evt1.getRefSeqNum().compareTo(evt2.getRefSeqNum());
        				    else
        					return -1;
        				}
        			    }));

        			Iterator<JuvenileCasefileReferral> casefilesRespItr = casefilesResp.iterator();
        		 //89766 User-story	
        		  Iterator<Assignment> assignmentIter = Assignment.findAll("caseFileId", casefile.getOID());
			    while (assignmentIter.hasNext())
			    {
				assignment = (Assignment) assignmentIter.next();
				if (assignment.getReferralNumber().equals(referralNum))
				{
				    Assignment newAssignment = new Assignment();
				    newAssignment.setAssignmentAddDate( evt.getAssignmentDate());		
				    newAssignment.setReferralNumber( assignment.getReferralNumber() );
				    newAssignment.setAssignmentType(evt.getAssignmentType());
				    // assgnmt.setAssignmentAddDate(evt.getAssignmentDate());
				    newAssignment.setOverrideReason(assignment.getOverrideReason()); //US 71181
				    newAssignment.setOverrideOtherComments(assignment.getOverrideOtherComments()); //US 71181
				    if(casefilesRespItr.hasNext()){
					JuvenileCasefileReferral casefileReferral = casefilesRespItr.next();
					if(casefileReferral!=null){
					newAssignment.setSeqNum(String.valueOf((Integer.valueOf(casefileReferral.getRefSeqNum())+1)));
					}
				    }
				    casefile.insertAssignments(newAssignment);
				}
			    }
        		    if (!assignmentIter.hasNext())
        		    {
        			if(casefilesResp!=null){
        			    casefilesRespItr = casefilesResp.iterator();
        			    if (casefilesRespItr.hasNext())
        			    {
        				JuvenileCasefileReferral casefileRef = casefilesRespItr.next();
        				if (casefileRef.getReferralNumber().equals(referralNum))
        				{
                				assignment = new Assignment();
                				populateAssignment(evt, assignment);
                				assignment.setSeqNum(String.valueOf(Integer.valueOf(casefileRef.getRefSeqNum()) + 1));
                				casefile.insertAssignments(assignment);
                				assignment.setCaseFileId(casefile.getOID().toString());
        				}
        			    }
        			    else
        			    {
        				assignment = new Assignment();
        				populateAssignment(evt, assignment);
        				assignment.setSeqNum("1");
        				casefile.insertAssignments(assignment);
        				assignment.setCaseFileId(casefile.getOID().toString());
        			    }
        			}
        		    }
        	      }
        	      if(assignment!=null && casefile!=null && casefile.getOID()!=null)
        	      {
                	     
        			setMAYSIFlag( juvNum, assignment.getReferralNumber(), jpoId, assignment, casefile );         	
        			setRiskAnalysisFlag( juvNum, evt.getSupervisionType(), evt.getSupervisionCat(), assignment.getAssignmentAddDate(), casefile );
        			setRISKNEEDFlag(juvNum, evt.getSupervisionType(), evt.getSupervisionCat(), casefile);
        			// send notification
        			JuvenileNotificationGenerator.sendNewJuvenileCasefileNotification( casefile, officer );
                		
        	      }
        //end assignment edits		
        	      
        		if(casefile!=null && casefile.getOID()!=null)
        		    casefileId = casefile.getOID();
        		
        }//end if
	  
	//check for subsequent referral with level codes = B, N or J and send notice to JPO and CLM			
		
	if (evt.getAssignmentType()!=null && evt.getAssignmentType().equalsIgnoreCase("SBQ")){
	  
	    OfficerProfile officer = null;
		try
		{
			officer = getOfficer( jpoIdFromUI );
		}
		catch(InvalidProbationOfficerException e)
		{
			
		}	
		  String jpoId = officer.getOID().toString();
		  String juvNum = evt.getJuvenileNum();
		  String supTypeId = evt.getSupervisionType();
		  String referralNum = evt.getReferralNum();
		  Assignment assignment=null;
		  JuvenileCasefile casefile=null;
		  if(evt.getSubsequentCasefileId()!=null)
		      casefile = JuvenileCasefile.find(evt.getSubsequentCasefileId());
		  else
		      casefile = JuvenileCasefile.find( jpoId, juvNum, supTypeId );
		
		  /*  assignment = new Assignment();
		    populateAssignment( evt, assignment );
		    casefile.insertAssignments( assignment );
		    assignment.setCaseFileId( casefile.getOID().toString() );*/
		   //89766 User-story
			GetCasefilesForReferralsEvent searchEvent = new GetCasefilesForReferralsEvent();
			searchEvent.setReferralNum(referralNum);
			searchEvent.setJuvenileNum(evt.getJuvenileNum());

			JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
			resp.setReferralNumber(searchEvent.getReferralNum());

			Collection<JuvenileCasefileReferral> coll = new ArrayList<JuvenileCasefileReferral>();

			Iterator<JuvenileCasefileReferral> caseRefItr = JuvenileCasefileReferral.findAll(searchEvent);
			while (caseRefItr.hasNext())
			{
			    JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) caseRefItr.next();
			    coll.add(caseRef);
			}
			resp.setCasefileReferrals(coll);
        		Collection<JuvenileCasefileReferral> casefilesResp = resp.getCasefileReferrals();
        		Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
        		    @Override
        		    public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
        		    {
        			if (evt1.getRefSeqNum() != null && evt2.getRefSeqNum() != null)
        			    return evt1.getRefSeqNum().compareTo(evt2.getRefSeqNum());
        			else
        			    return -1;
        		    }
        		}));

			Iterator<JuvenileCasefileReferral> casefilesRespItr = casefilesResp.iterator();
			 //89766 User-story
			Iterator<Assignment> assignmentIter = Assignment.findAll("caseFileId", casefile.getOID());
        		    if (assignmentIter.hasNext())
        		    {
        			assignment = (Assignment) assignmentIter.next();
        			if (assignment.getReferralNumber().equals(referralNum))
        			{
        			    Assignment newAssignment = new Assignment();
        			    newAssignment.setAssignmentAddDate( evt.getAssignmentDate());		
        			    newAssignment.setReferralNumber( assignment.getReferralNumber() );
        			    newAssignment.setAssignmentType(evt.getAssignmentType());
        			    // assgnmt.setAssignmentAddDate(evt.getAssignmentDate());
        			    newAssignment.setOverrideReason(assignment.getOverrideReason()); //US 71181
        			    newAssignment.setOverrideOtherComments(assignment.getOverrideOtherComments()); //US 71181
        			    if(casefilesRespItr.hasNext()){
        				JuvenileCasefileReferral casefileRef = casefilesRespItr.next();
        				if(casefileRef!=null)
        				newAssignment.setSeqNum(String.valueOf((Integer.valueOf(casefileRef.getRefSeqNum())+1)));
        			    }
        			    casefile.insertAssignments(newAssignment);
        			}
        		    }
        		if (!assignmentIter.hasNext())
        		{
        		    if (casefilesResp != null)
        		    {
        			if (casefilesRespItr.hasNext())
        			{
        			    JuvenileCasefileReferral casefileRef = casefilesRespItr.next();
        			    if(casefileRef.getReferralNumber().equalsIgnoreCase(referralNum)){
        				Assignment newAssignment = new Assignment();
        				populateAssignment(evt, newAssignment);
        				newAssignment.setSeqNum(String.valueOf(Integer.valueOf(casefileRef.getRefSeqNum()) + 1));
        				casefile.insertAssignments(assignment);
        				newAssignment.setCaseFileId(casefile.getOID().toString());
        			    }
        			}
        			else
        			{
        			    assignment = new Assignment();
        			    populateAssignment(evt, assignment);
        			    assignment.setSeqNum("1");
        			    casefile.insertAssignments(assignment);
        			    assignment.setCaseFileId(casefile.getOID().toString());
        			}
        		    }
        		}
        	// Profile stripping fix task 97536     
		//Juvenile juvenile = casefile.getJuvenile();
        	JuvenileCore juvenile = casefile.getJuvenile();
		//
		String juvenileName = juvenile.getFirstName() + " " + juvenile.getLastName();
		String juvenileNum = juvenile.getJuvenileNum();
		String officerJIMSLogonId = casefile.getProbationOfficer().getLogonId();
		String referralDate = DateUtil.dateToString(evt.getAssignmentDate(), DateUtil.DATE_FMT_1);
		JuvenileNotificationGenerator.sendSubsequentReferralNotification(juvenileName, juvenileNum, referralNum, referralDate, officerJIMSLogonId);
				
		OfficerProfile officerProfile = OfficerProfile.find(jpoId); 
		officerJIMSLogonId = officerProfile.getManagerId();
		//create CLM notification 
		if( officerJIMSLogonId != null && !"".equals(officerJIMSLogonId) )
		{
			JuvenileNotificationGenerator.sendSubsequentReferralNotification(juvenileName, juvenileNum, referralNum, referralDate, officerJIMSLogonId);
					
			//send email notification as well
			StringBuffer message = new StringBuffer(100);
			SendEmailEvent sendEmailEvent  = new SendEmailEvent();
			sendEmailEvent.setSubject("Subsequent Referral Assignment for Juvenile: " +juvenileName + " " + juvenileNum);
			sendEmailEvent.setMessage( message.toString() ); 
			sendEmailEvent.setFromAddress( fromEmail);
			sendEmailEvent.addToAddress( officer.getEmail() );
			
			sendEmailEvent.setMessage(juvenileName + " " + juvenileNum + " received subsequent Referral #: " + evt.getReferralNum() + " under Supervision# "
			        	  + casefile.getOID() + ", for the following offense(s): " + offenseCodes); 
			
				          
			IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch1.postEvent( sendEmailEvent );  
		}
		if(casefile!=null && casefile.getOID()!=null)
		    casefileId = casefile.getOID();
		}	
	
	
	}//end if updateAssignmentflag
	//check if status has changed and create activity
	    if(!oldRefStat.equalsIgnoreCase(evt.getRefStatus()) && casefileId!=null)
	    {
		ActivityHelper helper = new ActivityHelper();
		String comments="";
		if(oldRefStat.equalsIgnoreCase("CLOSED"))
		{
		    	CreateActivityEvent reqEvent = (CreateActivityEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.CREATEACTIVITY);
			reqEvent.setSupervisionNumber(casefileId);
			reqEvent.setActivityDate(DateUtil.getCurrentDate());
			reqEvent.setActivityCategoryId(ActivityConstants.ACTIVITY_SYSTEM_GENERATED);
			reqEvent.setActivityTypeId(ActivityConstants.ACTIVITY_TYPE_REF_REC_CREATED);
			reqEvent.setActivityCodeId(ActivityConstants.REFERRAL_RECORD_REOPENED);
			comments="Referral " + evt.getReferralNum() + " has been re-opened by "  + UIUtil.getCurrentUserID() ;
			reqEvent.setComments(comments);
			helper.createActivity(reqEvent);
		}
		else
		{
		    	CreateActivityEvent reqEvent = (CreateActivityEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.CREATEACTIVITY);
			reqEvent.setSupervisionNumber(casefileId);
			reqEvent.setActivityDate(DateUtil.getCurrentDate());
			reqEvent.setActivityCategoryId(ActivityConstants.ACTIVITY_SYSTEM_GENERATED);
			reqEvent.setActivityTypeId(ActivityConstants.ACTIVITY_TYPE_REF_REC_CREATED);
			reqEvent.setActivityCodeId(ActivityConstants.REFERRAL_RECORD_CLOSED);
			comments= "Referral " + evt.getReferralNum() + " has been changed to a status of CLOSED, with Close Date: " + DateUtil.dateToString(evt.getClosedDate(), DateUtil.DATE_FMT_1) + " by "
				+ UIUtil.getCurrentUserID() + ". Supervision Category = " + evt.getSupervisionCat()+ " associated to the referral number at time of referral closure." ;
			reqEvent.setComments(comments);
			helper.createActivity(reqEvent);
		}
		helper = null;
	    }
	    
	    if(casefileId!=null)
	    {
		ActivityHelper helper = new ActivityHelper();
		String comments="";
		CreateActivityEvent reqEvent = (CreateActivityEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.CREATEACTIVITY);
		reqEvent.setSupervisionNumber(casefileId);
		reqEvent.setActivityDate(DateUtil.getCurrentDate());
		reqEvent.setActivityCategoryId(ActivityConstants.ACTIVITY_SYSTEM_GENERATED);
		reqEvent.setActivityTypeId(ActivityConstants.ACTIVITY_TYPE_REF_REC_CREATED);
		reqEvent.setActivityCodeId(ActivityConstants.REFERRAL_RECORD_MODIFIED);		
		comments="Referral " + evt.getReferralNum() + " details have been modified by "  + UIUtil.getCurrentUserID() ;		
		reqEvent.setComments(comments);
		helper.createActivity(reqEvent);
		helper = null;
	    }
	    
	    //check if all Referrals for the Juvenile are closed - if yes, then change Juvenile status
	    Iterator<JJSReferral> allReferralsIter = JJSReferral.findAll("juvenileNum", evt.getJuvenileNum());
	    boolean hasOpenRefs=false;
	    while (allReferralsIter.hasNext())
	    {
		JJSReferral ref = (JJSReferral) allReferralsIter.next();
		 if (ref.getCloseDate() == null)
		 {
		     hasOpenRefs=true;
		     break;
		 }
		     
	    }
	    if(!hasOpenRefs)
	    {
		JuvenileCore juvCore = JuvenileCore.findCore(evt.getJuvenileNum());
		if (juvCore != null)
		{
		    juvCore.setStatusId("C");
		    home = new Home();
    		    home.bind(juvCore);
		}
	    }
        
    }
    /**
	 * @param juvNum	
	 * @return
	 */
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
	
	/**
	 * @param juvNum
	 * @param referralNumber
	 * @return
	 */
	private boolean maysiExists( String juvNum, String referralNumber) throws RuntimeException, Exception
	{
		GetAllMAYSIAssessmentsEvent queryEvent = new GetAllMAYSIAssessmentsEvent();
		queryEvent.setJuvenileNumber( juvNum );
		queryEvent.setReferralNumber( referralNumber );
		Iterator iter = JuvenileMAYSIComplete.findAll( queryEvent );
		ArrayList<JuvenileMAYSIComplete> maysiList = new ArrayList<JuvenileMAYSIComplete>();
		while (iter.hasNext())
		{
		    JuvenileMAYSIComplete maysi =(JuvenileMAYSIComplete) iter.next();
		    if (maysi != null)
		    {
			maysiList.add(maysi);
		    }
		}
		//SortedMap map = new TreeMap();
		// 08/30/2013
		// Revised to find first referral and check for existing MAYSI 
//		if( iter.hasNext() )
//		{
//			return true;
//		}		

		/*while(iter.hasNext()){
			JuvenileMAYSIComplete  jmc = (JuvenileMAYSIComplete) iter.next();
			map.put(jmc.getReferralNumber(), jmc);
		}
		List temp = new ArrayList(map.values());*/
		/*if (temp.size() > 0)
		{
		    JuvenileMAYSIComplete jmc = (JuvenileMAYSIComplete) temp.get(0);
		    if (referralNumber.equals(jmc.getReferralNumber()))
		    {
			return true;
		    }
		}*/
		///task 172536
		//if (temp.size() > 0)
		//sort iterator desc order of assessmentid
		if(maysiList.size()>1)
		{
	        	Collections.sort((List<JuvenileMAYSIComplete>) maysiList, Collections.reverseOrder(new Comparator<JuvenileMAYSIComplete>() {
	        	@Override
	                	public int compare(JuvenileMAYSIComplete evt1, JuvenileMAYSIComplete evt2)
	                	{
	                	    if (evt1.getJuvenileMAYSIAssessId() != null && evt2.getJuvenileMAYSIAssessId() != null)
	                		return Integer.valueOf(evt1.getJuvenileMAYSIAssessId()).compareTo(Integer.valueOf(evt2.getJuvenileMAYSIAssessId()));
	                	    else
	                		return -1;
	                	}
	        	}));
		}
		Iterator<JuvenileMAYSIComplete> maysiItr = maysiList.iterator();
		if (maysiItr.hasNext())				    
		{
		    //JuvenileMAYSIComplete jmc = (JuvenileMAYSIComplete) temp.get(0);	
		    JuvenileMAYSIComplete jmc =(JuvenileMAYSIComplete)maysiItr.next();
		    if (jmc.getAssessmentOptionId() != null && jmc.getAssessmentOptionId().equals("T"))
		    {
        		    ReasonNotDone rnd =null;
        		    if(jmc.getReasonNotDoneId()!=null &&!jmc.getReasonNotDoneId().isEmpty())
        		    {
        			Iterator<ReasonNotDone> it = ReasonNotDone.findAll("description", jmc.getReasonNotDoneId());
        			while (it.hasNext()) 
        			{
        			    rnd = (ReasonNotDone) it.next();
        			    break;
        			}
        			if(rnd.getFinalReason()!=null)
        			{
        	        	    if (referralNumber.equals(jmc.getReferralNumber())&& rnd.getFinalReason().equalsIgnoreCase("YES"))//and finalreason yes and loop through all 
        	        	    {
        	        		return true;
        	        	    }
        			}
        		    }
		    }
		    /*else if(jmc.getAssessmentOption() != null && jmc.getAssessmentOption().equals("A"))
	    		return false;*/
	    	    else
	    		return true;
		}		
		//temp = null;
		return false;
	}
	/**
	 * @param jjsService
	 * @param casefile
	 */
	private Assignment createAssignment( UpdateJuvenileReferralEvent evt, JuvenileCasefile casefile )
	{
/*		Assignment assignment = new Assignment();
		
		populateAssignment( evt, assignment );
		casefile.insertAssignments( assignment );
		assignment.setCaseFileId( casefile.getOID().toString() );
		//assignment.setCreateUserID( PDJuvenileCaseConstants.CASEFILE_CREATOR );
		assignment.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
		return assignment;*/
	     //89766 User-story
		Assignment assignment = new Assignment();
		populateAssignment( evt, assignment );
		
		//JuvenileProfileReferralListResponseEvent profileResp = JuvenileFacilityHelper.getCasefilesFromReferral(evt.getJuvenileNum(), evt.getReferralNum());
		GetCasefilesForReferralsEvent searchEvent =  new GetCasefilesForReferralsEvent();
		searchEvent.setReferralNum(evt.getReferralNum());
		searchEvent.setJuvenileNum(evt.getJuvenileNum());
		
		JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
		resp.setReferralNumber(searchEvent.getReferralNum());
		
		Collection<JuvenileCasefileReferral> coll = new ArrayList<JuvenileCasefileReferral>();
		
		Iterator<JuvenileCasefileReferral> juvCaseRefIter = JuvenileCasefileReferral.findAll(searchEvent);
		while (juvCaseRefIter.hasNext())
		{
		    JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) juvCaseRefIter.next();
		    coll.add(caseRef);
		}
		resp.setCasefileReferrals(coll);
		Collection<JuvenileCasefileReferral> casefilesResps = resp.getCasefileReferrals();
        	if (casefilesResps != null)
        	{
        	    Iterator<JuvenileCasefileReferral> casefilesRespsItr = casefilesResps.iterator();
        	    Collections.sort((List<JuvenileCasefileReferral>) casefilesResps, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
        		@Override
        		public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
        		{
        		    if (evt1.getRefSeqNum() != null && evt2.getRefSeqNum() != null)
        			return evt1.getRefSeqNum().compareTo(evt2.getRefSeqNum());
        		    else
        			return -1;
        		}
        	    }));
        	     //89766 User-story
        	    if (casefilesRespsItr.hasNext())
        	    {
        		JuvenileCasefileReferral casefileRef = casefilesRespsItr.next();
        		assignment.setSeqNum(String.valueOf(Integer.valueOf(casefileRef.getRefSeqNum()) + 1));
        	    }
        	    else
        	    {
        		 assignment.setSeqNum("1");
        	    }
        	    casefile.insertAssignments(assignment);
        	    assignment.setCaseFileId(casefile.getOID().toString());
        	    assignment.setCreateUserID(UIUtil.getCurrentUserID());//assignment.setCreateUserID( PDJuvenileCaseConstants.CASEFILE_CREATOR ); 
        	}
		return assignment;
	}
	
	/**
	 * @param jjsService
	 * @param assignment
	 */
	private void populateAssignment( UpdateJuvenileReferralEvent evt, Assignment assignment )
	{
		assignment.setAssignmentAddDate( evt.getAssignmentDate() );		
		assignment.setReferralNumber( evt.getReferralNum() );
		assignment.setAssignmentType(evt.getAssignmentType());
		
	}
    
    /**
	 * @param casefile
	 * @param referralNum
	 * @return
	 */
	private JuvenileCasefile generate( 
			String juvNum, String officer, String supTypeId ) throws RuntimeException, Exception
	{
	    Juvenile juv = Juvenile.findJCJuvenile(juvNum);
	  
	    if (juv == null)
	    {
		// create juvenile master record
		juv = createJuvenile(juvNum);
		if(juv != null){
		// import family member info
									
		    Collection<JuvReferralFamilyResponseEvent> juvRefFamily =  UIJuvenileHelper.getFamilyInfoForMigratedRecordsWithoutCasefile(juvNum);
		    Iterator familyIter = juvRefFamily.iterator();
		    if (juvRefFamily.size()!=0)
		    {
			JuvReferralFamilyResponseEvent familyResp = (JuvReferralFamilyResponseEvent) familyIter.next();
			if(familyResp.getJuvRefFamId()!=null)
			{
        			JJSFamily juvfamily = populateJJSFamilyFromResp(familyResp, juvNum);
        			FamilyConstellation familyConstellation = createFamilyConstellation(juvfamily, juv.getJuvenileNum());
        			// add constellation to juvenile
        			if (familyConstellation != null)
        			{
        			    juv.insertFamilyConstellationList(familyConstellation);
        			} else {
        			    //JuvenileNotificationGenerator.sendMissingConstellationNotification(juvNum, officer, jjsService.getAddDate());
        			}
			}
		    }					
		}
	    }
			
	   
	    // new Casefile needs to be initiated
	    JuvenileCasefile casefile = new JuvenileCasefile();
	    casefile.setJuvenileId(juvNum);
	    
	    OfficerProfile officerProf = getOfficer(officer);
	    casefile.setProbationOfficer(officerProf);		
            casefile.setSupervisionTypeId(supTypeId);
            casefile.setSequenceNumber("0"); //bug fix 22374 
            casefile.setRectype("CASEFILE");
            casefile.setCaseStatusId(PDJuvenileCaseConstants.CASESTATUS_PENDING);
            casefile.setActivationDate(new Date());
          //  casefile.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
            casefile.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
            casefile.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
		
		return casefile;
	}
	
	/**
	 * @param juvenileNum
	 */
	private static Juvenile createJuvenile(String juvenileNum) throws RuntimeException, Exception 
	{
		Juvenile juvenile = null;
		JJSJuvenile jjsJuvenile = JJSJuvenile.find(juvenileNum);
		if (jjsJuvenile != null){
			Date birthDate = jjsJuvenile.getBirthDate();
			//  06/08/2012 - revised max age from 17 to 19 per ER 71590
			if(getAgeInYears(birthDate) < 10 || getAgeInYears(birthDate) > 19){
				throw new Exception("Juvenile can not be created as Juvenile age is not in between 10-19.");
			}
			
			juvenile = new Juvenile();
			populateJuvenileFromJJSJuvenile(jjsJuvenile, juvenile);
			IHome home = new Home();
			home.bind(juvenile);
		}
		return juvenile;
	}
	/**
	 * @param jjsJuvenile
	 * @param juvenile
	 * @throws Exception
	 * @throws 
	 */
	private static void populateJuvenileFromJJSJuvenile(JJSJuvenile jjsJuvenile, Juvenile juvenile)
	{
		juvenile.setJuvenileNum(jjsJuvenile.getJuvenileNum());
		//ER Fix:JIMS200077276 defaulting student id to the juvenileNum.
		juvenile.setStudentId(jjsJuvenile.getJuvenileNum());
		//juvenile.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
		juvenile.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
		juvenile.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
		juvenile.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));		
	}
	/**
	 * @param jpoUserId
	 * @return
	 */
	private static OfficerProfile getOfficer( String jpoUserId ) 
			throws InvalidProbationOfficerException, RuntimeException, Exception
	{
		OfficerProfile officer = null;
		
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
		
		return officer;
	}
	/**
	 * @param casefile
	 * @param referralNum
	 * @return
	 */
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

	/**
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
	 */

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

	/**
	 * @param updateEvent
	 * @param casefileForm
	 *          To JPO CasefileClosingNotification.
	 */
	private void sendPOStatusOffenderSupervisionNotification( 
		SaveJJSReferralEvent evt, JuvenileCasefile casefile, 
			JJSJuvenile juvenile ) throws RuntimeException, Exception
	{
    	    String jpoIdFromUI= "";
        	if(evt.getJpoID()!=null){
        	    jpoIdFromUI = evt.getJpoID();
        	}else if(evt.getCtAssignJPOId()!=null){
        	    jpoIdFromUI = evt.getCtAssignJPOId();
        	}else if(evt.getProbJPOId()!=null){
        	    jpoIdFromUI = evt.getProbJPOId();
        	}
		SendCasefileClosingNotificationEvent casefileNotifEvent = (SendCasefileClosingNotificationEvent)
				EventFactory.getInstance( JuvenileCasefileNotificationControllerServiceNames.SENDCASEFILECLOSINGNOTIFICATION );
		
		String subject = "Status Offender Supervision has been open 30 days.";
		casefileNotifEvent.setSubject( subject );
		
		StringBuffer sb = new StringBuffer( juvenile.getLastName() );
		sb.append( " " ).append( juvenile.getFirstName() )
				.append( " Supervision # " ).append( casefile.getOID().toString() )
				.append( " was assigned to you on " )
				.append( DateUtil.dateToString( evt.getAssignmentDate(), DateUtil.DATE_FMT_1) )
				.append( " and the case remains active. It has been 30 days since assignment." )
				.append( "\n" );
		
		casefileNotifEvent.setNotificationMessage( sb.toString() );
		String taskName = casefileNotifEvent.getClass().getName();
		// To JPO
		casefileNotifEvent.setIdentityType( "jpo" );
		casefileNotifEvent.setIdentity( jpoIdFromUI );
		casefileNotifEvent.setNoticeTopic( "MJCW.M204.CASEFILE.ASSIGNMENT.NOTIFICATION" );
		// Added as the command is used for both DB2 and M204 notices.
		casefileNotifEvent.setSupervisionNumber( casefile.getOID().toString() );
		this.scheduleNotification( casefileNotifEvent, taskName, evt.getAssignmentDate(), 720 );
		casefileNotifEvent = null;
	}

	/**
	 * @param event
	 * @param taskName
	 * @param date
	 * @param hours
	 */
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
	private void sendJPOSubsequentNotifications(SaveJJSReferralEvent evt, OfficerProfile officer, JuvenileCasefile casefile) throws RuntimeException, Exception
	{
//		String currentJPOID = officer.getOID().toString();
	    	//Profile stripping fix task 97536
		//Juvenile juvenile = casefile.getJuvenile();
	    	JuvenileCore juvenile = casefile.getJuvenile();
		//
		String juvenileName = juvenile.getFirstName() + " " + juvenile.getLastName();
		String juvenileNum = juvenile.getJuvenileNum();
//		String officerJIMSLogonId = casefile.getProbationOfficer().getLogonId();
		String referralDate = DateUtil.dateToString(evt.getReferralDate(), DateUtil.DATE_FMT_1);
		String referralNum = evt.getReferralNum();
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
					
				}
				catch(Exception e)
				{
					
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
						
					}
					catch(Exception e)
					{
						
					}				
				}
			}
			activeCaseCLMLogonId = null;
		}
		jpoList = null;
		jpoMap = null;
		
	}
	/**
	 * @param juvNum
	 * @param referralNumber
	 * @param casefile
	 * @throws Exception
	 * @throws RuntimeException
	 */
	private void setMAYSIFlag( String juvNum, String referralNumber, 
			String supervisionTypeId, Assignment assignment, 
			JuvenileCasefile casefile ) throws RuntimeException, Exception
	{
		//for Deferred Adjudication Casefiles, the maysi flag is set to false in the setRiskAnalysisFlag method no matter what this method returns
		if(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ.equalsIgnoreCase(supervisionTypeId))
		{
			casefile.setIsMAYSINeeded( false );
			return;
		}
		//casefile.setIsMAYSINeeded( false );
		//add a check to see if current casefile maysi flag bug 104412
		if(casefile.getIsMAYSINeeded())
		    casefile.setIsMAYSINeeded(true);
		else
		    casefile.setIsMAYSINeeded(false);
		//
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
	/**
	 * @param juvenileNum
	 * @param supervisionType
	 * @param assignmentAddDate
	 * @param casefile
	 */
	private void setRiskAnalysisFlag( String juvenileNum, 
		String supervisionTypeId, String supervisionCategory, Date assignmentAddDate, 
			JuvenileCasefile casefile ) throws RuntimeException, Exception
	{
		
		// added this edit for activity 74021  supervisiontype DP90 aka DPS
		if (PDCodeTableConstants.CASEFILE_SUPERVISION_TYPE_DEFERRED_PROSECUTION_SUPERVISION.equalsIgnoreCase(supervisionTypeId) )
		{
			casefile.setIsMAYSINeeded( false );
		}
		
		
		if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ.equalsIgnoreCase( supervisionCategory ) )
		{
			casefile.setIsProgressRiskNeeded( false );
			casefile.setIsResProgressRiskNeeded( false );
			casefile.setIsResidentialRiskNeeded( false );
			casefile.setIsCommunityRiskNeeded( false );	
			casefile.setIsMAYSINeeded( false );
		}
		else if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM.equalsIgnoreCase( supervisionCategory ) )
		{
			casefile.setIsResidentialRiskNeeded( false );			
		}
		else if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES.equalsIgnoreCase( supervisionCategory ) )
		{
			casefile.setIsResProgressRiskNeeded( true );
			if( !isRiskAnalysisAssessmentCompleted( casefile, juvenileNum, RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL ) )
			{
				casefile.setIsResidentialRiskNeeded( false );
			}
		}
	return;

		
	}
	
	private void setRISKNEEDFlag(String juvenileNum, String supervisionTypeId, String supervisionCategory, JuvenileCasefile casefile) throws RuntimeException, Exception
	{

		if (PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_PRE_ADJ.equalsIgnoreCase(supervisionCategory))
		{
		    casefile.setRiskNeed(true);
		}
		else
		{
		    casefile.setRiskNeed(false);
		}

		return;
	 }

	/**
	 * RiskAnalysisForJuvenile within 30 days
	 * @param casefile
	 * @param juvenileNum
	 * @param assessmentType
	 * @return
	 */
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
	
	/**
	 * Calculates the age in years given a date.
	 * @param ageDate
	 * @return age in years, 0 if age parameter is null
	 */
	private static int getAgeAtOffense(Date dob, Date offenseDate)
	{
		if (dob == null)
		{
			return 0;
		}
		Calendar birthdate = Calendar.getInstance();
		birthdate.setTime(dob);
		Calendar dateOfOffense = Calendar.getInstance();
		dateOfOffense.setTime(offenseDate);
		int age = 0;
		age = dateOfOffense.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
		birthdate.add(Calendar.YEAR, age);
		if (dateOfOffense.before(birthdate))
		{
			age--;
		}
		return age;
	}
	private static JJSFamily populateJJSFamilyFromResp(JuvReferralFamilyResponseEvent resp, String juvNum)
	{	    
	    JJSFamily fam = new JJSFamily();
	    fam.setFatherPhone(resp.getFathersPhone());
	    fam.setMotherPhone(resp.getMothersPhone());
	    fam.setOtherPhone(resp.getOtherPhone());
	    if(resp.getFathersName()!=null)
	    {
        	    Name fatherName = NameUtil.getNameFromString(resp.getFathersName());        	    
        	    fam.setFatherFirstName(fatherName.getFirstName());
        	    fam.setFatherLastName(fatherName.getLastName());
        	    fam.setFatherMiddleName(fatherName.getMiddleName());
	    }
	    JJSJuvenile jjsJuvenile = JJSJuvenile.find( juvNum );
	    if(jjsJuvenile.getSsnRelation1Id()!=null)
	    {
		if(jjsJuvenile.getSsnRelation1Id().equalsIgnoreCase("BF"))		
		    fam.setFatherSsn(jjsJuvenile.getSsn1());
		else if(jjsJuvenile.getSsnRelation1Id().equalsIgnoreCase("BM"))
		    fam.setMotherSsn(jjsJuvenile.getSsn1());
		else
		{
		    fam.setOtherSsn(jjsJuvenile.getSsn1());
		    fam.setRelationshipId(jjsJuvenile.getSsnRelation1Id());
		}
	    }
	    if(jjsJuvenile.getSsnRelation2Id()!=null)
	    {
		if(jjsJuvenile.getSsnRelation2Id().equalsIgnoreCase("BF"))		
		    fam.setFatherSsn(jjsJuvenile.getSsn2());
		else if(jjsJuvenile.getSsnRelation2Id().equalsIgnoreCase("BM"))
		    fam.setMotherSsn(jjsJuvenile.getSsn2());
		else
		{
		    fam.setOtherSsn(jjsJuvenile.getSsn2());
		    fam.setRelationshipId(jjsJuvenile.getSsnRelation2Id());
		}
	    }
	    if(resp.getMothersName()!=null)
	    {
		 Name motherName = NameUtil.getNameFromString(resp.getMothersName());  
		 fam.setMotherFirstName(motherName.getFirstName());
		 fam.setMotherLastName(motherName.getLastName());
		 fam.setMotherMiddleName(motherName.getMiddleName());
	    }
	    if(resp.getOtherName()!=null)
	    {
		Name otherName = NameUtil.getNameFromString(resp.getOtherName());  
		fam.setOtherFirstName(otherName.getFirstName());
		fam.setOtherLastName(otherName.getLastName());
		fam.setOtherMiddleName(otherName.getMiddleName());
	    }
	    
	    fam.setFatherAddressId(resp.getFathersAddress());
	    fam.setMotherAddressId(resp.getMothersAddress());
	    fam.setOtherAddressId(resp.getOtherAddress());
	    
	    return fam;	    
	}
	
	/**
	 * @param juvfamily
	 * @param juvNum
	 */
	
	private static FamilyConstellation createFamilyConstellation(JJSFamily juvfamily, String juvNum) throws RuntimeException, Exception
	{
	    FamilyConstellation familyConstellation = null;
	    ArrayList<JuvenileReferralMemberDetailsBean> juvFamilyList = new ArrayList<JuvenileReferralMemberDetailsBean>();
	    JuvenileReferralMemberDetailsBean memberBean1 = populateFamilyMemBeanFromJJSFamily(juvfamily, "BM"); 
	    juvFamilyList.add(memberBean1);
	    JuvenileReferralMemberDetailsBean memberBean2 = populateFamilyMemBeanFromJJSFamily(juvfamily, "BF"); 
	    juvFamilyList.add(memberBean2);
	    JuvenileReferralMemberDetailsBean memberBean3 = populateFamilyMemBeanFromJJSFamily(juvfamily, "OR"); 
	    juvFamilyList.add(memberBean3);
	    Iterator<JuvenileReferralMemberDetailsBean> familyMemberList = juvFamilyList.iterator();
	    while (familyMemberList.hasNext())
	    {
		    FamilyMember member = null;
		    JuvenileReferralMemberDetailsBean memberBean = (JuvenileReferralMemberDetailsBean) familyMemberList.next();
		// check if member exists
		FamilyMember mother =null;			
		ArrayList suspMemberIds=new ArrayList();
		 if ((memberBean.getFirstName() != null && memberBean.getFirstName().trim().length() > 0) || (memberBean.getMiddleName() != null && memberBean.getMiddleName().trim().length() > 0) || (memberBean.getLastName() != null && memberBean.getLastName().trim().length() > 0))
		    {
			if (memberBean.getSSN() != null && !memberBean.getSSN().getSSN().equals("") && !memberBean.getSSN().getSSN().equals("666666666") && !memberBean.getSSN().getSSN().equals("777777777") && !memberBean.getSSN().equals("888888888") && !memberBean.getSSN().getSSN().equals("999999999"))
			{
			    //check if any other family member has the same ssn
			    Iterator<FamilyMember> familyMembersSSN = JuvenileCaseHelper.checkFamilyMemberSSN(memberBean.getSSN().getSSN());
			    while (familyMembersSSN.hasNext())
			    {
				FamilyMember memberSSN = (FamilyMember) familyMembersSSN.next();
				//check if they have the same name
				if (memberSSN != null && memberSSN.getFirstName() != null && !memberSSN.getFirstName().equalsIgnoreCase(memberBean.getFirstName()) && memberSSN.getLastName() != null && !memberSSN.getLastName().equalsIgnoreCase(memberBean.getLastName()))
				{
				    suspMemberIds.add(memberSSN.getFamilyMemberId());
				}
			    }
			}
		    }
		boolean isGuardianAvailable = false;
		 Address address = new Address();
		 PhoneNumber phoneNumber = null;		   
		 address.setStreetName(memberBean.getMemberAddress().getStreetName());		
		 address.setCreateUserID(SecurityUIHelper.getLogonId());
		 address.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
		 address.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
		 address.setValidated("N");		
		 phoneNumber = memberBean.getContactPhoneNumber();		
		 try
			{
			    member = getFamilyMember(memberBean, address, phoneNumber);
			}
			catch (RuntimeException e)
			{
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
			catch (Exception e)
			{
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		 
		//suspicious
		    if (suspMemberIds != null && !suspMemberIds.isEmpty())
		    {
			Iterator<String> iter = suspMemberIds.iterator();
			while (iter.hasNext())
			{
			    String memberId = (String) iter.next();
			    JuvenileCaseHelper.markMembersSuspicious(memberId, member.getFamilyMemberId());
			}
		    }
		    if (memberBean.getRelationshipId().equalsIgnoreCase("BM"))
		    {
			if (familyConstellation == null)
			    familyConstellation = createFamilyConstellation(juvNum);
			FamilyConstellationMember familyConstellationMember = createCostellationMember(member, "BM");
			if(familyConstellationMember!=null)
			{
        			familyConstellationMember.setGuardian(true);
        			isGuardianAvailable = true;
        			familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
        			SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
        			aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
        			aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
        			JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
			}
		    }
		    // add member to constellation
		    if (memberBean.getRelationshipId().equalsIgnoreCase("BF"))
		    {
			if (familyConstellation == null)
			    familyConstellation = createFamilyConstellation(juvNum);
			FamilyConstellationMember familyConstellationMember = createCostellationMember(member, "BF");
			if(familyConstellationMember!=null)
			{
        			familyConstellationMember.setGuardian(true);
        			isGuardianAvailable = true;
        			familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
        			SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
        			aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
        			aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
        			JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
			}
		    }
		    // add member to constellation
		    if (memberBean.getRelationshipId().equalsIgnoreCase("OR"))
		    {
			FamilyConstellationMember familyConstellationMember = createCostellationMember(member, "OR");
			if (isGuardianAvailable == false)
			{
			    if (familyConstellation == null)
				familyConstellation = createFamilyConstellation(juvNum);
			    if(familyConstellationMember!=null)
			    {
        			    familyConstellationMember.setGuardian(true);
        			    familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
        			    SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
        			    aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
        			    aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
        			    JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
			    }
			}
		    }
		
	    }
	    
	    return familyConstellation;
	}
	 /**
	     * @param firstName
	     * @param middleName
	     * @param lastName
	     * @param memberAddress
	     * @param memberPhoneNum
	     * @param ssn
	     * @return
	     */
   private static JuvenileReferralMemberDetailsBean populateFamilyMemBeanFromJJSFamily(JJSFamily resp, String relationshipType)
	{	
	    JuvenileReferralMemberDetailsBean memberBean = new JuvenileReferralMemberDetailsBean();
	    memberBean.setRelationshipId(relationshipType);
	    if(relationshipType!= null && relationshipType.equalsIgnoreCase("BM"))
	    {		    
		memberBean.setFirstName(resp.getMotherFirstName());
		memberBean.setLastName(resp.getMotherLastName());
		memberBean.setMiddleName(resp.getMotherMiddleName());
		memberBean.setCompleteSSN(resp.getMotherSsn());
		memberBean.setContactPhoneNumber(new PhoneNumber(resp.getMotherPhone()));
		memberBean.setMemberAddress(new ui.common.Address());
		memberBean.getMemberAddress().setStreetName(resp.getMotherAddressId());
		
	    }
	    else if(relationshipType!= null && relationshipType.equalsIgnoreCase("BF"))
	    {		
		memberBean.setFirstName(resp.getFatherFirstName());
		memberBean.setLastName(resp.getFatherLastName());
		memberBean.setMiddleName(resp.getFatherMiddleName());
		memberBean.setContactPhoneNumber(new PhoneNumber(resp.getFatherPhone()));
		memberBean.setCompleteSSN(resp.getFatherSsn());
		memberBean.setMemberAddress(new ui.common.Address());
		memberBean.getMemberAddress().setStreetName(resp.getFatherAddressId());
	    }
	    else
	    {
		memberBean.setFirstName(resp.getOtherFirstName());
		memberBean.setLastName(resp.getOtherLastName());
		memberBean.setMiddleName(resp.getOtherMiddleName());
		memberBean.setContactPhoneNumber(new PhoneNumber(resp.getOtherPhone()));
		memberBean.setCompleteSSN(resp.getOtherSsn());
		memberBean.setMemberAddress(new ui.common.Address());
		memberBean.getMemberAddress().setStreetName(resp.getOtherAddressId());
	    }
	    
	    return memberBean;
	}

	  /**
	     * @param firstName
	     * @param middleName
	     * @param lastName
	     * @param memberAddress
	     * @param memberPhoneNum
	     * @param ssn
	     * @return
	     */
	    private static FamilyMember getFamilyMember(JuvenileReferralMemberDetailsBean memberBean, Address memberAddress, PhoneNumber contactPhoneNumber) throws RuntimeException, Exception
	    {
		if (memberBean != null)
		{
		    String firstName = memberBean.getFirstName();
		    String middleName = memberBean.getMiddleName();
		    String lastName = memberBean.getLastName();
		    String ssn = memberBean.getSSN().getSSN();

		    FamilyMember member = null;
		    if ((firstName != null && firstName.trim().length() > 0) || (middleName != null && middleName.trim().length() > 0) || (lastName != null && lastName.trim().length() > 0))
		    {
			//TODO need to check if member exists
			member = new FamilyMember();
			member.setFirstName(firstName);
			member.setLastName(lastName);
			member.setMiddleName(middleName);
			if (ssn == null || ssn.trim().equals(""))
			{
			    ssn = "666666666";
			}
			member.setSsn(ssn);
			//member.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
			member.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
			// create address
			if (memberAddress != null && !memberAddress.getStreetName().equals(""))
			{
			    Address address = null;			
			    address = memberAddress;			
			    member.insertAddresses(address);
			}

			FamilyMemberPhone memberPhone = null;
			if (contactPhoneNumber != null && contactPhoneNumber.getPhoneNumber() != null && contactPhoneNumber.getPhoneNumber().trim().length() > 0)
			{
			    memberPhone = JuvenileFamilyHelper.createMemberPhone(contactPhoneNumber.getPhoneNumber(), contactPhoneNumber.getExt(), memberBean.getPhoneType(), (String) member.getOID());
			}

			//add phonenum to member
			if (memberPhone != null)
			{
			    member.insertPhoneNumbers(memberPhone);
			}

			if (member.getOID() == null)
			{			    
			    IHome home = new Home();
			    home.bind(member);
			}
		    }
		    return member;
		}
		return null;
	    }
	    
	    /*
	     * @param juvNum
	     */
	    private static FamilyConstellation createFamilyConstellation(String juvNum)
	    {
		FamilyConstellation familyConstellation = new FamilyConstellation();
		familyConstellation.setEntryDate(new Date());
		familyConstellation.setJuvenileId(juvNum);
		familyConstellation.setActive(true);
		familyConstellation.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
		//familyConstellation.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
		familyConstellation.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
		return familyConstellation;
	    }

	    /**
	     * @param mother
	     * @param relationshipId
	     * @return FamilyConstellationMember
	     */
	    private static FamilyConstellationMember createCostellationMember(FamilyMember member, String relationshipId)
	    {
		FamilyConstellationMember familyConstellationMember = null;
		if (member != null)
		{
		    familyConstellationMember = new FamilyConstellationMember();
		    familyConstellationMember.setTheFamilyMemberId((String) member.getOID());
		    familyConstellationMember.setRelationshipToJuvenileId(relationshipId);
		    familyConstellationMember.setInHomeStatus(false); // default to No as per requrement
		    familyConstellationMember.setInvolvementLevelId(PDJuvenileCaseConstants.FAMILY_MEMBER_INVOLVEMENT_LVL_LOW); // default to LOW as per requirements
		    familyConstellationMember.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
		   // familyConstellationMember.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
		    familyConstellationMember.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
		}
		return familyConstellationMember;
	    }
	    
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
		
		/**
		 * Moved from casefile extraction.
		 * @param ageDate
		 * @return
		 */
		 private static int getAgeInYears(Date ageDate)
		    {
			if (ageDate == null)
			{
			    return 0;
			}
			Calendar birthdate = Calendar.getInstance();
			birthdate.setTime(ageDate);
			Calendar now = Calendar.getInstance();

			int age = 0;
			age = now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
			birthdate.add(Calendar.YEAR, age);
			if (now.before(birthdate))
			{
			    age--;
			}
			return age;
		    }
		 
		    /**
		     *
		     * @param saveEvent
		     */    
		    private void updateJJSDecisionHistory(UpdateJuvenileReferralEvent saveEvent) {
			
			    JJSDecisionHistory history = new JJSDecisionHistory();
			    
			    history.setJuvenileNum( saveEvent.getJuvenileNum() );
			    history.setReferralNum(saveEvent.getReferralNum());
			    history.setInDecisionId(saveEvent.getIntakeDecisionId());
			    history.setCourtDecisionId(null);
			    history.setDecisionDate(saveEvent.getIntakeDate());
			    history.setTjpcDisp( saveEvent.getTJPCDisp());
			    
			    GetJJSDecisionHistoryEvent req = new GetJJSDecisionHistoryEvent();
			    req.setJuvenileNum( saveEvent.getJuvenileNum() );
			    req.setReferralNum(saveEvent.getReferralNum());
			    
			    Iterator<JJSDecisionHistory> historyIter = JJSDecisionHistory.findAll(req);
			    
			    if(!historyIter.hasNext()) {
				
				 history.setTjpcDispSeqNum("1");
			    }else {
				
				int ctr =0;
				while( historyIter.hasNext()) {
				    
				    historyIter.next();				    
				    ctr++;
				}				
				history.setTjpcDispSeqNum(String.valueOf( ctr+ 1 ));				
			    }	
			
		    }
		
}

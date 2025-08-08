/*
 * Created on Mar 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.administerprogramreferrals.ChangeReferralStatusEvent;
import messaging.administerprogramreferrals.DeleteProgramReferralEvent;
import messaging.administerprogramreferrals.ExitProgramReferralEvent;
import messaging.administerprogramreferrals.FilterProgramReferralsEvent;
import messaging.administerprogramreferrals.GetCurrentReferralFormsEvent;
import messaging.administerprogramreferrals.GetInitiateAndOpenReferralsEvent;
import messaging.administerprogramreferrals.GetProgramReferralEvent;
import messaging.administerprogramreferrals.InitNOpenRefsForRefTypesEvent;
import messaging.administerprogramreferrals.ReferProgramReferralEvent;
import messaging.administerprogramreferrals.ReferralFormFieldValue;
import messaging.administerprogramreferrals.SaveProgRefCasenoteEvent;
import messaging.administerprogramreferrals.SaveProgramReferralsEvent;
import messaging.administerprogramreferrals.SaveReferralFormEvent;
import messaging.administerprogramreferrals.SubmitProgramReferralEvent;
import messaging.administerprogramreferrals.UpdateProgramReferralEvent;
import messaging.administerprogramreferrals.reply.AppointmentDetailsResponseEvent;
import messaging.administerprogramreferrals.reply.CSProgramReferralResponseEvent;
import messaging.administerprogramreferrals.reply.ProgrRefByCaseloadResponseEvent;
import messaging.administerprogramreferrals.reply.ReferralFormFieldResponseEvent;
import messaging.administerprogramreferrals.reply.ReferralFormOptionResponseEvent;
import messaging.administerprogramreferrals.reply.ReferralFormResponseEvent;
import messaging.administerprogramreferrals.reply.ReferralTypeResponseEvent;
import messaging.administerprogramreferrals.reply.ReferralWithRefTypeResponseEvent;
import messaging.administerprogramreferrals.reply.SubmitExitProgRefResponseEvent;
import messaging.codetable.GetRelativeCodeEvent;
import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.criminalcase.GetCaseEvent;
import messaging.csserviceprovider.GetProgramLocationEvent;
import messaging.csserviceprovider.GetSPAdminContactsEvent;
import messaging.csserviceprovider.GetSPProgramLocationEvent;
import messaging.managetask.CreateCSTaskEvent;
import messaging.supervisionoptions.GetAllVariableElementDataValueEvent;
import messaging.supervisionoptions.GetSupervisionConditionsByGroup1Event;
import messaging.supervisionorder.GetCaseSupervisionPeriodEvent;
import messaging.supervisionorder.GetMostRecentInactiveOrderForCaseEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import naming.CSAdministerProgramReferralsConstants;
import naming.CSCAssessmentConstants;
import naming.CasenoteConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import pd.codetable.SimpleCodeTableHelper;
import pd.codetable.supervision.CSDischargeReasonCode;
import pd.codetable.supervision.CSProgramReferralType;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.common.util.StringUtil;
import pd.contact.party.Party;
import pd.criminalcase.CriminalCase;
import pd.km.util.Name;
import pd.security.PDSecurityHelper;
import pd.supervision.Group;
import pd.supervision.administercaseload.CaseAssignment;
import pd.supervision.administercaseload.CloseCaseHelper;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administerserviceprovider.csserviceprovider.CSProgram;
import pd.supervision.administerserviceprovider.csserviceprovider.CSProgramHelper;
import pd.supervision.administerserviceprovider.csserviceprovider.CSProgramLocation;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProvider;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProviderContact;
import pd.supervision.csts.CSTSHelper;
import pd.supervision.legacyupdates.ILegacyUpdates;
import pd.supervision.legacyupdates.LegacyUpdatesImpl;
import pd.supervision.managetask.PDTaskHelper;
import pd.supervision.manageworkgroup.WorkGroup;
import pd.supervision.manageworkgroup.WorkGroupHelper;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.IncarcerationReferral;
import pd.supervision.supervisionorder.SupervisionOrder;

/**
 * @author cc_cwalters
 *
 */
public class CSProgramReferralHelper 
{

	
	private static final String CSTS_WORKGROUP_NAME = "CSTS WORKGROUP";

	private static final String FOR = " for ";

	private static final String PROGRAM_BEG_SUBJ = "Program Referral Begin Data Corrected";
	
	private static final String PROGRAM_END_SUBJ = "Program Referral End Data Corrected";

	private static final String SA = "SA";

	private static final String SPACE = " ";
	
	private static final String STATUS_O = "O";

	
	/**
	 * 
	 * @param initiatedPrgmRefList
	 */
	public static void autoExitInitiateProgramRef(ArrayList initiatedPrgmRefList)
	{
		if(initiatedPrgmRefList.size() > 0)
		{
			Iterator iterator = initiatedPrgmRefList.iterator();
			
			while(iterator.hasNext())
			{
				CSProgramReferral prgmRef = (CSProgramReferral) iterator.next();
				
				/*set the Exit Fields for "Initiated" Program Referrals
				PROGENDDATE = termination date of the associated case
				DISCHARGEREASON = "Inappropriate";*/
				String defendantId = prgmRef.getDefendantId();
				String caseId = prgmRef.getCriminalCaseId();			
				
				Date terminationDate = getCaseAssignmentTerminationDate(defendantId, caseId);
				if(terminationDate != null)
				{
					prgmRef.setProgramEndDate(terminationDate);
				}
//				if CaseAssignment record and SupervisionOrder record is not present(this situation can occur in case of OOC Case close only)
				else
				{
					Date terminationDat = new Date();
					String terminationDatStr = DateUtil.dateToString(terminationDat, DateUtil.DATE_FMT_1);
					terminationDat = DateUtil.stringToDate(terminationDatStr, DateUtil.DATE_FMT_1);
					
					prgmRef.setProgramEndDate(terminationDat);
				}
				//prgmRef.setDischargeReasonCd(PDCodeTableConstants.JIMS2_DISCHRG_REAS_INAPPROPRIATE);
				prgmRef.setDischargeReasonCd(CloseCaseHelper.getDischargeReasonForCase(defendantId, caseId));
				prgmRef.setReferralStatusCode( CSAdministerProgramReferralsConstants.EXITED_REFERRAL_STATUS );

				
//				record casenotes
				recordCasenotesOnAutoExit(prgmRef);
			}
		}
	}//end of autoExitInitiateProgramRef()
	
	/**
	 * 
	 * @param initiatedPrgmRefList
	 */
	public static void autoExitOpenProgramRef(ArrayList openPrgmRefList)
	{
		if(openPrgmRefList.size() > 0)
		{
			Iterator iterator = openPrgmRefList.iterator();
			
			while(iterator.hasNext())
			{
				CSProgramReferral prgmRef = (CSProgramReferral) iterator.next();
				
				/*set the Exit Fields for "Open" Program Referrals
				PROGENDDATE = termination date of the associated case
				DISCHARGEREASON = Read T35/T36 resords, convert TST code to JIMS2 Reason For Discharge (For Harris County Cases)
				 				= Obtain the Closure Reason from the OOC case record and convert to JIMS2 Reason For Discharge (For OOC Cases) */
				String defendantId = prgmRef.getDefendantId();
				String caseId = prgmRef.getCriminalCaseId();
				
				Date terminationDate = getCaseAssignmentTerminationDate(defendantId, caseId);
				if(terminationDate != null)
				{
					prgmRef.setProgramEndDate(terminationDate);
				}
//				if CaseAssignment record and SupervisionOrder record is not present(this situation can occur in case of OOC Case close only)
				else
				{
					Date terminationDat = new Date();
					String terminationDatStr = DateUtil.dateToString(terminationDat, DateUtil.DATE_FMT_1);
					terminationDat = DateUtil.stringToDate(terminationDatStr, DateUtil.DATE_FMT_1);
					
					prgmRef.setProgramEndDate(terminationDat);
				}
				prgmRef.setReferralStatusCode( CSAdministerProgramReferralsConstants.EXITED_REFERRAL_STATUS );
				
				String jims2DischargeReasCd = null;				
//				OOC Case
				if(caseId.startsWith(PDCodeTableConstants.CSCD) || caseId.startsWith(PDCodeTableConstants.PTS))
				{
//					Only CSCD OOC Cases(with CDI=010) can be closed
					if(caseId.startsWith(PDCodeTableConstants.CSCD))
					{
						/* Replaced with method in CloseCaseHelper.
						 * OutOfCountyCase oocCase = (OutOfCountyCase)OutOfCountyCaseFactory.find(caseId, PDCodeTableConstants.CSCD);
						String closureReasonCode = oocCase.getDispositionTypeId();
						if(closureReasonCode != null)
						{
							//jims2DischargeReasCd = convertOocClosureReasToJims2DischargeReasCd(closureReasonCode);
							jims2DischargeReasCd = convertOocClosureReasToJims2DischargeReasCd(closureReasonCode);
						}*/
						jims2DischargeReasCd = CloseCaseHelper.getDischargeReasonForCase(defendantId, caseId);
					}
				}
//				Harris County Case
				else
				{
					//jims2DischargeReasCd = CSTSHelper.getJims2ReasonForDischargeCd(prgmRef);
					jims2DischargeReasCd = CloseCaseHelper.getDischargeReasonForCase(defendantId, caseId);
				}
				prgmRef.setDischargeReasonCd(jims2DischargeReasCd);
				
				prgmRef.bind();
				
//				create T34 record, on exit of open state reporting program referrals
				if(prgmRef.getProgramBeginDate()!=null)
				{
					String referralTypeCode = prgmRef.getReferralTypeCode();
					Iterator refTypeIter = CSProgramReferralType.findAll("referralTypeCode", referralTypeCode);
					if(refTypeIter!=null && refTypeIter.hasNext())
					{
						CSProgramReferralType programRefType = (CSProgramReferralType)refTypeIter.next();
	//					If CTSCode on Referral Type is not "LOC"(Local), then it is state reporting
						if(!programRefType.getCtsCode().equalsIgnoreCase(CSAdministerProgramReferralsConstants.NOT_STATE_REPORTING_CODE))
						{
							String cstsDischargeReasCd = convertJims2DisReasCdToCstsDisReasCd(prgmRef.getDischargeReasonCd());
							if(!StringUtil.isEmpty(cstsDischargeReasCd))
							{
								CSTSHelper.createCSTSRecordOnExit(prgmRef, programRefType, cstsDischargeReasCd);
							}
						}
					}
				}
				
//				record casenotes
				recordCasenotesOnAutoExit(prgmRef);	
			}
		}
	}//end of autoExitOpenProgramRef()
	
	
	/**
	 * 
	 * @param defendantId
	 * @param terminationDate
	 */
	public static void autoExitReferrals(String defendantId)
	{
		GetInitiateAndOpenReferralsEvent event = new GetInitiateAndOpenReferralsEvent();
		event.setSpn(defendantId);
		
		Iterator iterator = CSProgramReferral.findAll(event);
		
		ArrayList initiatedPrgmRefList = new ArrayList();
		ArrayList openPrgmRefList = new ArrayList();
		
//		separate initiated referrals and open referrals
		if(iterator != null)
		{
			while(iterator.hasNext())
			{
				CSProgramReferral prgmRef = (CSProgramReferral)iterator.next();
				
				if(prgmRef.getReferralDate()!=null)
				{
			 		if(prgmRef.getProgramBeginDate()!=null)
			 		{
			 			openPrgmRefList.add(prgmRef);
			 		}
			 		else
			 		{
			 			initiatedPrgmRefList.add(prgmRef);
			 		}
			 	}
			}
		}
		
		autoExitInitiateProgramRef(initiatedPrgmRefList);
		
		autoExitOpenProgramRef(openPrgmRefList);
		
	}//end of autoExitReferrals()
	
	public static void changeProgramReferralStatus(ChangeReferralStatusEvent changeRefStatusEvt)
	{
		String programReferralId = changeRefStatusEvt.getProgramReferralId();
		if(!StringUtil.isEmpty(programReferralId))
		{
			CSProgramReferral programReferral = CSProgramReferral.find(programReferralId);
			
			if(changeRefStatusEvt.isRemoveEntry())
			 {
				programReferral.setReferralStatusCode(CSAdministerProgramReferralsConstants.INITIATED_REFERRAL_STATUS);
				
				programReferral.setProgramBeginDate(null);
				programReferral.setTracerNumber(null);
				programReferral.setPlacementReasonCd(null);
				
				programReferral.setConfinementYears(0);
				programReferral.setConfinementMonths(0);
				programReferral.setConfinementDays(0);
				
				programReferral.setSubmitComments(null);
				try {
					ILegacyUpdates referralHandler = new LegacyUpdatesImpl();  
					referralHandler.delete(programReferral.getOID(), "T33", programReferral.getDefendantId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			 }
			 else
			 if(changeRefStatusEvt.isRemoveExit())
			 {
				programReferral.setProgramEndDate(null);
				programReferral.setDischargeReasonCd(null); 
				
				programReferral.setExitComments(null);
				
				if(programReferral.getProgramBeginDate() == null)
				{
					programReferral.setReferralStatusCode(CSAdministerProgramReferralsConstants.INITIATED_REFERRAL_STATUS);
				}
				else
				{
					programReferral.setReferralStatusCode(CSAdministerProgramReferralsConstants.OPEN_REFERRAL_STATUS);
				}
				try {
					ILegacyUpdates referralHandler = new LegacyUpdatesImpl();  
					referralHandler.delete(programReferral.getOID(), "T34", programReferral.getDefendantId());
				} catch (Exception e) {
					e.printStackTrace();
				}

			 }
		}
	}
	
	
	/**
	 * 
	 * @param OocClosureReasonCode
	 * @return
	 */
	public static String convertJims2DisReasCdToCstsDisReasCd(String jims2DischargeReasonCode)
	{
		String cstsDischargeReasonCd = null;
		
		GetRelativeCodeEvent event = new GetRelativeCodeEvent();
		event.setConversionType(PDCodeTableConstants.CONVERSION_TYPE_JIMS2_CSTS);
		event.setCode(jims2DischargeReasonCode);
		
		Iterator iterator = CSDischargeReasonCode.findAll(event);
		if(iterator != null && iterator.hasNext())
		{
			CSDischargeReasonCode dischargeReasonRecord = (CSDischargeReasonCode)iterator.next();
			cstsDischargeReasonCd = dischargeReasonRecord.getRelativeCode();
		}
		
		return cstsDischargeReasonCd;
	}//end of convertJims2DisReasCdToCstsDisReasCd()
	
	
	/**
	 * 
	 * @param OocClosureReasonCode
	 * @return
	 */
	/* Not using anymore.  Replaced with method in CloseCaseHelper.
	 * public static String convertOocClosureReasToJims2DischargeReasCd(String OocClosureReasonCode)
	{
		String jims2DischargeReasonCd = PDCodeTableConstants.JIMS2_DISCHRG_REAS_UNKNOWN;
		
		GetRelativeCodeEvent event = new GetRelativeCodeEvent();
		event.setConversionType(PDCodeTableConstants.CONVERSION_TYPE_OOC_JIMS2);
		event.setCode(OocClosureReasonCode);
		
		Iterator iterator = CSDischargeReasonCode.findAll(event);
		if(iterator != null && iterator.hasNext())
		{
			CSDischargeReasonCode dischargeReasonRecord = (CSDischargeReasonCode)iterator.next();
			jims2DischargeReasonCd = dischargeReasonRecord.getRelativeCode();
		}
		
		return jims2DischargeReasonCd;
	}//end of convertOocClosureReasToJims2DischargeReas()
	*/
	
	public static ReferralFormResponseEvent convertToReferralResponseEvent(CSReferralForm referralForm)
	{
		ReferralFormResponseEvent responseEvt = new ReferralFormResponseEvent();
		responseEvt.setReferralFormId(referralForm.getReferralFormId());
		responseEvt.setReferralFormTitle(referralForm.getFormTitle());
		
		return responseEvt;
	}
	
	
	/**
	 * Richard Young re-wrote the logic to make it generic
	 * @param order
	 * @param orderConditionIdsList
	 */
	public static void createIncarcerationReferrals(SupervisionOrder order, List orderConditionIdsList)
	{
		String shockGroup1Id = null;
		
//		retrieve the OID for GROUP1='SHOCK'
		Iterator groupIterator = Group.findAll("groupName", CSAdministerProgramReferralsConstants.GROUPNAME_SHOCK);
		if(groupIterator!=null && groupIterator.hasNext())
		{
			Group csGroup = (Group)groupIterator.next();
			shockGroup1Id = csGroup.getOID();
		}
		
		if(!StringUtil.isEmpty(shockGroup1Id))
		{
//			filter the incarceration conditions if present 
			GetSupervisionConditionsByGroup1Event queryEvent = new GetSupervisionConditionsByGroup1Event();
			queryEvent.setGroup1ID(shockGroup1Id);
			queryEvent.setConditionIdsList(orderConditionIdsList);
			
			List referralIncList = new ArrayList();
						
			Iterator condIter = Condition.findAll(queryEvent);
			
			while(condIter.hasNext())
			{
				Condition incarcerationCondition = (Condition)condIter.next();
				String group2Id = incarcerationCondition.getGroup2Id();
				
//				retrieve the active program for the incarceration condition
				CSProgram csActiveProgram = CSProgramHelper.getIncarcerationProgram(group2Id);
				if( csActiveProgram != null )
				{
					String programId = csActiveProgram.getProgramId();
					
					Iterator csProgLocIter = CSProgramLocation.findAll("programId", programId);
					if(csProgLocIter!=null && csProgLocIter.hasNext())
					{
						CSProgramLocation progLocation = (CSProgramLocation)csProgLocIter.next();
						
						String programLocationId = progLocation.getProgramLocationId();
						
						CSProgramReferral programReferral = new CSProgramReferral();
						
						programReferral.setDefendantId(order.getDefendantId());
						programReferral.setCriminalCaseId(order.getCriminalCaseId());
						
						programReferral.setReferralStatusCode(
								CSAdministerProgramReferralsConstants.INITIATED_REFERRAL_STATUS);
						programReferral.setReferralTypeCode(csActiveProgram.getReferralTypeCode());
						programReferral.setReferralDate(new Date());
						programReferral.setProgramLocationId(programLocationId);
						programReferral.setContractProgram(csActiveProgram.getIsContractProgram());
						
						programReferral.setAutoReferral(true);
						programReferral.setIncarcerationReferral(true);
													
						String hcjNumberStr = "";
						String dataName = "";
						// Need to modify when they change condition literals
						String searchValue = "Credit";
												
						GetAllVariableElementDataValueEvent varDataQueryEvent = new GetAllVariableElementDataValueEvent();
						varDataQueryEvent.setSupervsionOrderId(order.getOID());
						varDataQueryEvent.setCondtionId(incarcerationCondition.getOID());
						
//						obtain the HCJNumber data value for the incarceration condition
						Iterator varIter = IncarcerationReferral.findAll(varDataQueryEvent);
						while ( varIter.hasNext() )
						{
							IncarcerationReferral incarcRef = ( IncarcerationReferral )varIter.next();
							referralIncList.add( incarcRef );
							
						}
						
						Collections.sort( referralIncList );
	
						int confinementDays = 0;
						int confinementMonths = 0;
						int confinementYears = 0;
						int confinementLength = 0;
						
						for ( int i =0; i < referralIncList.size(); i ++ ){
							
							IncarcerationReferral tempRef = ( IncarcerationReferral ) referralIncList.get( i );
							
							hcjNumberStr = tempRef.getDataValue();
							
							dataName = tempRef.getVarElementTypeName();
							int index1 = dataName.indexOf( searchValue );
							
							if ( index1 == -1 ){

								try {
	
									int x = Integer.parseInt( hcjNumberStr );
									confinementLength = x;
									
								} catch ( NumberFormatException e ) { 
									
									if(( CSAdministerProgramReferralsConstants.HCJTIMEPERIOD_DAY ).equalsIgnoreCase( hcjNumberStr ) ||
											( CSAdministerProgramReferralsConstants.HCJTIMEPERIOD_DAYS.equalsIgnoreCase( hcjNumberStr )) )
										{
											confinementDays = confinementLength;
											
											if( confinementLength > 31 )
											{
												confinementMonths = confinementLength / 31;
												confinementDays = confinementLength % 31;
												
												if( confinementMonths > 12 )
												{
													confinementYears = confinementMonths / 12;
													confinementMonths = confinementMonths % 12;
													
													if( confinementYears >= 10 )
													{
														confinementYears = 10;
														confinementMonths = 0;
														confinementDays = 0;
													}
												}
											}
										}
										else
										if((CSAdministerProgramReferralsConstants.HCJTIMEPERIOD_MONTH.equalsIgnoreCase( hcjNumberStr )) ||
												(CSAdministerProgramReferralsConstants.HCJTIMEPERIOD_MONTHS.equalsIgnoreCase( hcjNumberStr )))
										{
											confinementMonths = confinementLength ;
											
											if( confinementMonths > 12 )
											{
												confinementYears = confinementMonths / 12;
												confinementMonths = confinementMonths % 12;
												
												if( confinementYears >= 10 )
												{
													confinementYears = 10;
													confinementMonths = 0;
													confinementDays = 0;
												}
											}
										}
										else
										if((CSAdministerProgramReferralsConstants.HCJTIMEPERIOD_YEAR.equalsIgnoreCase( hcjNumberStr )) ||
												(CSAdministerProgramReferralsConstants.HCJTIMEPERIOD_YEARS.equalsIgnoreCase( hcjNumberStr )))
										{
											
											confinementDays = 0;
											confinementMonths = 0;
											confinementYears = confinementLength ;
											
											if( confinementYears >= 10 )
											{
												confinementYears = 10;
											}
										}
								}
							}// end if index
						}
							
						programReferral.setConfinementDays( confinementDays );
						programReferral.setConfinementMonths( confinementMonths );
						programReferral.setConfinementYears( confinementYears );
						
						programReferral.bind();
						
						break;
					}
				}
			}
		}
	}//end of method createIncarcerationReferrals()
	
	/**
	 *  Changed to only create cstask
	 * @param defendantId
	 * @param isSubmit
	 * @param referralTypeCode
	 * @param changeMsg
	 */
	public static void createTaskToCSTSWorkgroup(
	    		String defendantId, 
	    		boolean isSubmit, 
	    		String referralTypeCode,
	    		String changeMsg){
	    	
			WorkGroup wg = WorkGroupHelper.fetchWorkgroupByName(PDSecurityHelper.getUserAgencyId(), SA, CSTS_WORKGROUP_NAME);
			
		    if( wg != null ){
		    	
		    	String taskOwnerId = wg.getOID();
			    CreateCSTaskEvent createTask = new CreateCSTaskEvent();
			    
			    //Set due date to 5 days from current date.
			    Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, 5);
				createTask.setDueDate(cal.getTime());
				createTask.setStatusId( STATUS_O );
				StringBuffer subject = new StringBuffer();
				
				if ( isSubmit ){
					subject.append(PROGRAM_BEG_SUBJ);
				} else {
					subject.append(PROGRAM_END_SUBJ);
				}
				createTask.setTaskSubject(subject.toString());
				StringBuffer text = new StringBuffer(changeMsg);
				text.append(FOR);
				text.append(defendantId);
				text.append(SPACE);
				String desc = CSProgramReferralHelper.getReferralTypeDesc(referralTypeCode);
				text.append(desc);
				
				createTask.setTastText( text.toString() );
				createTask.setDefendantId( defendantId );
				createTask.setTopic( CSCAssessmentConstants.CSTASK_TOPIC_NOTIFY_CSTS_CHANGE );
				createTask.setStaffPositionId( taskOwnerId );
				
				 Party defendant = Party.find( defendantId );
			        Name aName = new Name();
			        if (defendant != null){
			        	if (defendant.getFirstName() != null && !defendant.getFirstName().equals(PDConstants.BLANK)){
			        		aName.setFirstName(defendant.getFirstName());
			        	} else {
			        		aName.setFirstName(PDConstants.BLANK);
			        	}
			        	if (defendant.getLastName() != null && !defendant.getLastName().equals(PDConstants.BLANK)){
			        		aName.setLastName(defendant.getLastName());
			        	} else {
			        		aName.setLastName(PDConstants.BLANK);
			        	}
			        } else {
			        	aName.setFirstName(PDConstants.BLANK);
			        	aName.setLastName(PDConstants.BLANK);
			        }
			        
			        createTask.setSuperviseeName( aName.getFormattedName() );
			        
			        PDTaskHelper.createCSTask( createTask );

		    }
	    }
	/**
	 * Delete specified program referral
	 * @param defendantId
	 */
	public static void deleteProgramReferral(DeleteProgramReferralEvent deleteReferralEvent)
	{
			//retrieve program referral to delete
		CSProgramReferral delete_referral = 
			CSProgramReferral.find(deleteReferralEvent.getProgramReferralId());
		
			//delete referral
		delete_referral.delete();
 		
	}//end of deleteProgramReferral()
	public static SubmitExitProgRefResponseEvent exitProgramReferral(ExitProgramReferralEvent exitProgRefEvent)
	{
		SubmitExitProgRefResponseEvent responseEvent = new SubmitExitProgRefResponseEvent();
		
		String programReferralId = exitProgRefEvent.getProgramReferralId();
		if(!StringUtil.isEmpty(programReferralId))
		{
			CSProgramReferral programReferral = CSProgramReferral.find(programReferralId);
			
			programReferral.setReferralStatusCode(CSAdministerProgramReferralsConstants.EXITED_REFERRAL_STATUS);
			
			programReferral.setProgramEndDate(exitProgRefEvent.getProgramEndDate());
			programReferral.setDischargeReasonCd(exitProgRefEvent.getDischargeReasonCd());
			
			programReferral.setExitComments(exitProgRefEvent.getExitComments());
			
			programReferral.bind();
			
			responseEvent.setTRecordGenerated(false);
			
//			crate T34 record, on exit of open state reporting program referrals
			if(programReferral.getProgramBeginDate()!=null)
			{
				String referralTypeCode = programReferral.getReferralTypeCode();
				Iterator refTypeIter = CSProgramReferralType.findAll("referralTypeCode", referralTypeCode);
				if(refTypeIter!=null && refTypeIter.hasNext())
				{
					CSProgramReferralType programRefType = (CSProgramReferralType)refTypeIter.next();
	//				If CTSCode on Referral Type is not "LOC"(Local), then it is state reporting
					if( !CSAdministerProgramReferralsConstants.NOT_STATE_REPORTING_CODE.equals( programRefType.getCtsCode()))
					{
						String cstsDischargeReasCd = convertJims2DisReasCdToCstsDisReasCd(programReferral.getDischargeReasonCd());
						if(!StringUtil.isEmpty(cstsDischargeReasCd))
						{
							CSTSHelper.createCSTSRecordOnExit(programReferral, programRefType, cstsDischargeReasCd);
							
							responseEvent.setTRecordGenerated(true);
						}
					}
				}
			}
		}
		return responseEvent;
	}
	/**
	 * Filter list of referrals for a supervisee
	 * @param defendantId
	 */
	public static List filterProgramReferrals(FilterProgramReferralsEvent filterReferrralsEvent)
	{
			//retrieve list of referrals
		Iterator list_iter = CSProgramReferral.findAll(filterReferrralsEvent);
		
			//convert to more manageable List
		List referral_list = CollectionUtil.iteratorToList(list_iter);
		
			//return list of referrals
		return referral_list;
	}//end of filterProgramReferrals()


	public static AppointmentDetailsResponseEvent getAppointmentDetailsResponseEvent(String programReferralId)
	{
		AppointmentDetailsResponseEvent responseEvt = new AppointmentDetailsResponseEvent();
		
		CSProgramReferral programReferral = CSProgramReferral.find(programReferralId);
		if(programReferral != null)
		{
			responseEvt.setScheduleDateTime(programReferral.getScheduleDate());
			
			String progLocationId = programReferral.getProgramLocationId();
			if(progLocationId != null)
			{
				//get the service provider location
				CSServiceProvider csServiceProvider = null;
				GetSPProgramLocationEvent spProgLocEvent = new GetSPProgramLocationEvent();
				spProgLocEvent.setProgLocationId(progLocationId);
				Iterator iter = CSServiceProvider.findAll(spProgLocEvent);
				if(iter.hasNext())
				{
					csServiceProvider = (CSServiceProvider)iter.next();
				}
				
				if(csServiceProvider!=null)
				{
					responseEvt.setServiceProviderName(csServiceProvider.getServiceProviderName());
					responseEvt.setServiceProviderPhone(csServiceProvider.getPhoneNumber());
					responseEvt.setServiceProviderURL(csServiceProvider.getWebsite());
					
					responseEvt.setProgramName(csServiceProvider.getProgramName());
						
					String streetNum = (csServiceProvider.getStreetNumber() == null)?" ":csServiceProvider.getStreetNumber();
					responseEvt.setStreetNumber(streetNum);
					
					String streetName = (csServiceProvider.getStreetName() == null)?" ":csServiceProvider.getStreetName();
					responseEvt.setStreetName(streetName);
					
					String streetType = (csServiceProvider.getStreetTypeCd() == null)?" ":csServiceProvider.getStreetTypeCd();
					responseEvt.setStreetType(streetType);
					
					String aptNum = (csServiceProvider.getAptNum() == null)?" ":csServiceProvider.getAptNum();
					responseEvt.setAptNum(aptNum);
					
					String city = (csServiceProvider.getCity() == null)?" ":csServiceProvider.getCity();
					responseEvt.setCity(city);
					
					String state = (csServiceProvider.getState() == null)?" ":csServiceProvider.getState();
					responseEvt.setState(state);
					
					String zipCode = (csServiceProvider.getZipCode() == null)?" ":csServiceProvider.getZipCode();
					responseEvt.setZipCode(zipCode);
					
					String locationPhone = (csServiceProvider.getLocationPhone() == null)?" ":csServiceProvider.getLocationPhone();
					responseEvt.setLocationPhone(locationPhone);
					
					String locationFax = (csServiceProvider.getFaxNumber() == null)?" ":csServiceProvider.getFaxNumber();
					responseEvt.setLocationFax(locationFax);
				}
				
				CSProgram program = CSProgram.find(csServiceProvider.getProgramId());
				responseEvt.setOfficeHours(program.getOfficeHours());
				
				List adminContactsList = new ArrayList();
				responseEvt.setContactsList(adminContactsList);
				
				GetSPAdminContactsEvent spContactsEvent = new GetSPAdminContactsEvent();
				spContactsEvent.setServiceProvdrId(program.getServiceProviderId());
				Iterator contactsIter = CSServiceProviderContact.findAll(spContactsEvent);
				while(contactsIter.hasNext())
				{
					CSServiceProviderContact spContact = (CSServiceProviderContact)contactsIter.next();
					IName contactName = new NameBean(spContact.getFirstName(), spContact.getMiddleName(), spContact.getLastName());
					adminContactsList.add(contactName.getFormattedName());
				}
			}
		}
		return responseEvt;
	}
	
	
	
	/**
	 * 
	 * @param criminalCaseId
	 * @return
	 */
	public static Date getCaseAssignmentTerminationDate(String defendantId, String criminalCaseId)
	{
		Iterator iter = CaseAssignment.findAll("criminalCaseId", criminalCaseId);
		if(iter != null && iter.hasNext())
		{
			CaseAssignment caseAssignment = (CaseAssignment)iter.next();
			if(caseAssignment != null)
			{
				return caseAssignment.getTerminationDate();
			}
		}
//		If Case Assignment record is not present, then search for most recent Inactive Supervision order for that case
		GetMostRecentInactiveOrderForCaseEvent event = new GetMostRecentInactiveOrderForCaseEvent();
		event.setAgencyId(PDSecurityHelper.getUserAgencyId());
		event.setSpn(defendantId);
		event.setCaseId(criminalCaseId);
		event.setOrderStatusId(PDCodeTableConstants.STATUS_INACTIVE_ID);
		
		iter = SupervisionOrder.findAll(event);
		if(iter != null && iter.hasNext())
		{
			SupervisionOrder supervisionOrder = (SupervisionOrder)iter.next();
			if(supervisionOrder != null)
			{
				return supervisionOrder.getInactivationDate();
			}
		}
		
		return null;
	}//end of getCaseAssignmentTerminationDate()
	
	
	
	
	/**
	 * 
	 * @param programReferral
	 * @return
	 */
	private static String getNotes(CSProgramReferral programReferral)
	{
		String criminalCaseId = programReferral.getCriminalCaseId();
    	String referralType = getReferralTypeDesc(programReferral.getReferralTypeCode());
    	String serviceProviderName = getServiceProviderName(programReferral);
    	String programName = getProgramName(programReferral);
    	String beginDate = DateUtil.dateToString(programReferral.getProgramBeginDate(), DateUtil.DATE_FMT_1);
    	
    	String reasonForDischargeCd = programReferral.getDischargeReasonCd();
    	String reasonForDischarge = "";
    	GetRelativeCodeEvent event = new GetRelativeCodeEvent();
		event.setConversionType(PDCodeTableConstants.CONVERSION_TYPE_JIMS2_CSTS);
		event.setCode(reasonForDischargeCd);
		
		Iterator iterator = CSDischargeReasonCode.findAll(event);
		if(iterator != null && iterator.hasNext())
		{
			CSDischargeReasonCode dischargeReasonRecord = (CSDischargeReasonCode)iterator.next();
			reasonForDischarge = dischargeReasonRecord.getDescription();
		}
    	
    	String endDate = DateUtil.dateToString(programReferral.getProgramEndDate(), DateUtil.DATE_FMT_1);
    	
    	StringBuffer notes = new StringBuffer("Program Referral automatically exited due to case closure for Case#: ");
    	notes.append(criminalCaseId);
    	notes.append(", Referral Type: ");
    	notes.append(referralType);
    	notes.append(", Service Provider Name: ");
    	notes.append(serviceProviderName);
    	notes.append(", Program Name: ");
    	notes.append(programName);
    	notes.append(", Begin Date: ");
    	if(beginDate != null)
    	{
    		notes.append(beginDate );
    		notes.append(", Reason For Discharge: ");
    	}
    	else
    	{
    		notes.append("- ");
    		notes.append(", Reason For Discharge: ");
    	}
    	notes.append(reasonForDischarge);
    	notes.append(", End Date: ");
    	notes.append(endDate);
    	
    	return notes.toString();
	}//end of getNotes()
	
	
	/**
	 * 
	 * @param programId
	 * @param locationId
	 * @return
	 */
	public static String getProgramLocationId(String programId, String locationId)
	{
		GetProgramLocationEvent progLocEvent = new GetProgramLocationEvent();
		progLocEvent.setProgId(programId);
		progLocEvent.setLocationID(locationId);
		
		Iterator iter = CSProgramLocation.findAll(progLocEvent);
		if(iter!=null && iter.hasNext())
		{
			CSProgramLocation progLocation = (CSProgramLocation)iter.next();
			return progLocation.getProgramLocationId();
		}
		return null;
	}
	
	
	
	/**
	 * 
	 * @param programReferral
	 * @return
	 */
	public static String getProgramName(CSProgramReferral programReferral)
	{
		String programName = "";
		
		String programLocationId = programReferral.getProgramLocationId();
		
		if(!StringUtil.isEmpty(programLocationId))
		{
			GetSPProgramLocationEvent spProgLocEvt = new GetSPProgramLocationEvent();
			spProgLocEvt.setProgLocationId(programLocationId);
			Iterator spIter = CSServiceProvider.findAll(spProgLocEvt);
			if(spIter!=null && spIter.hasNext())
			{
				CSServiceProvider spProgLoc = (CSServiceProvider)spIter.next();
				programName = spProgLoc.getProgramName();
			}
		}
		return programName;
	}//end of getProgramName()
	
	
	/**
	 * Retrieve specified program referral
	 * @param defendantId
	 */
	public static CSProgramReferral getProgramReferral(GetProgramReferralEvent getReferralEvent)
	{
			//retrieve program referral
		CSProgramReferral get_referral = 
			CSProgramReferral.find(getReferralEvent.getProgramReferralId());
		
			//return referral
		return get_referral;
		
	}//end of getProgramReferral()
	
	/**
	 * Create a referral response event given a referral entity
	 * @param referral
	 * @return
	 */
	public static CSProgramReferralResponseEvent 
							getProgramReferralResponse(CSProgramReferral referral)
	{
			//populate response event using referral data
		CSProgramReferralResponseEvent referral_response = 
								new CSProgramReferralResponseEvent();		
		
		referral_response.setAutoReferral(referral.isAutoReferral());
		referral_response.setIncarcerationReferral(referral.isIncarcerationReferral());
		
		referral_response.setSubmitComments(referral.getSubmitComments());
		referral_response.setExitComments(referral.getExitComments());
		
		referral_response.setConfinementDays(referral.getConfinementDays());
		referral_response.setConfinementMonths(referral.getConfinementMonths());
		referral_response.setConfinementYears(referral.getConfinementYears());
		referral_response.setContractProgram(referral.isContractProgram());
		referral_response.setCriminalCaseId(referral.getCriminalCaseId());

		referral_response.setDischargeReasonCd(referral.getDischargeReasonCd());
		// get back the description for the defendantId from the CodeTables table
		referral_response.setDischargeReason(SimpleCodeTableHelper.getDescrByCode("JIMS2_DISCHARGE_REASON",referral.getDischargeReasonCd()));

		referral_response.setDefendantId(referral.getDefendantId());
		
		referral_response.setNewServiceProviderFax(referral.getNewServiceProviderFax());
		referral_response.setNewServiceProviderName(referral.getNewServiceProviderName());
		referral_response.setNewServiceProviderPhone(referral.getNewServiceProviderPhone());

		referral_response.setPlacementReasonCd(referral.getPlacementReasonCd());
		referral_response.setProgramBeginDate(referral.getProgramBeginDate());

		referral_response.setProgramEndDate(referral.getProgramEndDate());
		referral_response.setProgramLocationId(referral.getProgramLocationId());
		referral_response.setProgramReferralId(referral.getOID());

		referral_response.setReferralDate(referral.getReferralDate());
		referral_response.setReferralStatusCode(referral.getReferralStatusCode());
		referral_response.setReferralTypeCode(referral.getReferralTypeCode());
		referral_response.setReferralTypeDesc(getReferralTypeDesc(referral.getReferralTypeCode()));
		referral_response.setScheduleDate(referral.getScheduleDate());
		referral_response.setTracerNumber(referral.getTracerNumber());	
		
			//return response event
		return referral_response;
	}//end of getProgramReferralResponse()
	
	
	/**
	 * Create a referral response event given a referral entity
	 * @param referral
	 * @return
	 */
	public static CSProgramReferralResponseEvent 
							getProgramReferralResponse(CSProgramReferral referral, boolean isViewDetails)
	{
			//populate response event using referral data
		CSProgramReferralResponseEvent referral_response = 
								new CSProgramReferralResponseEvent();		
		
		referral_response.setProgramReferralId(referral.getOID());
		
		referral_response.setDefendantId(referral.getDefendantId());
		referral_response.setCriminalCaseId(referral.getCriminalCaseId());
		
		referral_response.setReferralStatusCode(referral.getReferralStatusCode());
		
		referral_response.setReferralTypeCode(referral.getReferralTypeCode());
		referral_response.setReferralTypeDesc(getReferralTypeDesc(referral.getReferralTypeCode()));
		
		referral_response.setReferralDate(referral.getReferralDate());
		
		referral_response.setNewServiceProviderName(referral.getNewServiceProviderName());
		referral_response.setNewServiceProviderPhone(referral.getNewServiceProviderPhone());
		referral_response.setNewServiceProviderFax(referral.getNewServiceProviderFax());
		
		referral_response.setScheduleDate(referral.getScheduleDate());
		
		referral_response.setProgramBeginDate(referral.getProgramBeginDate());
		referral_response.setContractProgram(referral.isContractProgram());
		referral_response.setTracerNumber(referral.getTracerNumber());
		referral_response.setPlacementReasonCd(referral.getPlacementReasonCd());
		referral_response.setConfinementDays(referral.getConfinementDays());
		referral_response.setConfinementMonths(referral.getConfinementMonths());
		referral_response.setConfinementYears(referral.getConfinementYears());
		referral_response.setSubmitComments(referral.getSubmitComments());
		referral_response.setExitComments(referral.getExitComments());
		
		referral_response.setProgramEndDate(referral.getProgramEndDate());
		referral_response.setDischargeReasonCd(referral.getDischargeReasonCd());
		
		referral_response.setAutoReferral(referral.isAutoReferral());
		referral_response.setProgramUnitRef( referral.isProgramUnitReferral() );
		referral_response.setIncarcerationReferral(referral.isIncarcerationReferral());
		
//		check if the T-record of submit/exit referrals is sent to state
		if(referral.getProgramBeginDate()!=null || referral.getProgramEndDate()!=null)
		{
			boolean isSubmitRecord = false;
			if(referral.getProgramEndDate()==null && referral.getProgramBeginDate()!=null)
			{
				isSubmitRecord = true;
			}
			referral_response.setSentToState(CSTSHelper.isCSTSRecordBeenSentToState(referral, isSubmitRecord));
		}
		
		List programLanguagesList = new ArrayList();
		boolean isProgLocExist = false;
		String progLocationId = referral.getProgramLocationId();
		if(progLocationId != null)
		{
			isProgLocExist = true;
			
//				get the service provider Id
			CSServiceProvider csServiceProvider = null;
			GetSPProgramLocationEvent spProgLocEvent = new GetSPProgramLocationEvent();
			spProgLocEvent.setProgLocationId(progLocationId);
			Iterator iter = CSServiceProvider.findAll(spProgLocEvent);
			while(iter.hasNext())
			{
				csServiceProvider = (CSServiceProvider)iter.next();
				
				if( csServiceProvider.getProgramLanguage() != null )
				{
					programLanguagesList.add(csServiceProvider.getProgramLanguage());
				}
			}
			
			if(csServiceProvider!=null)
			{
				referral_response.setServiceProviderId(String.valueOf(csServiceProvider.getServiceProviderId()));
				
				referral_response.setServiceProviderName(csServiceProvider.getServiceProviderName());
				
				referral_response.setProgramId(csServiceProvider.getProgramId());
				referral_response.setLocationId(csServiceProvider.getLocationId());
				
				referral_response.setProgramIdentifier(csServiceProvider.getProgramIdentifier());
				referral_response.setProgramName(csServiceProvider.getProgramName());
				
				if(isViewDetails)
				{
					String csts_code = (csServiceProvider.getCstsCode() == null)?" ":csServiceProvider.getCstsCode();
					referral_response.setCstsCode(csts_code);
					
					String sexSpecificCd = (csServiceProvider.getSexSpecificCode() == null)?" ":csServiceProvider.getSexSpecificCode();
					referral_response.setSexSpecificCode(sexSpecificCd);
					
					referral_response.setSpContractProgram(csServiceProvider.getIsContractProgram());
					
					referral_response.setProgramLanguagesList(programLanguagesList);
					
					String streetNum = (csServiceProvider.getStreetNumber() == null)?" ":csServiceProvider.getStreetNumber();
					referral_response.setStreetNumber(streetNum);
					
					String streetName = (csServiceProvider.getStreetName() == null)?" ":csServiceProvider.getStreetName();
					referral_response.setStreetName(streetName);
					
					String streetType = (csServiceProvider.getStreetTypeCd() == null)?" ":csServiceProvider.getStreetTypeCd();
					referral_response.setStreetType(streetType);
					
					String aptNum = (csServiceProvider.getAptNum() == null)?" ":csServiceProvider.getAptNum();
					referral_response.setAptNum(aptNum);
					
					String city = (csServiceProvider.getCity() == null)?" ":csServiceProvider.getCity();
					referral_response.setCity(city);
					
					String state = (csServiceProvider.getState() == null)?" ":csServiceProvider.getState();
					referral_response.setState(state);
					
					String zipCode = (csServiceProvider.getZipCode() == null)?" ":csServiceProvider.getZipCode();
					referral_response.setZipCode(zipCode);
					
					String locationPhone = (csServiceProvider.getLocationPhone() == null)?" ":csServiceProvider.getLocationPhone();
					referral_response.setLocationPhone(locationPhone);
					
					String locationFax = (csServiceProvider.getLocationFax() == null)?" ":csServiceProvider.getLocationFax();
					referral_response.setLocationFax(locationFax);
				}
			}
		}
		
		if(!isProgLocExist && isViewDetails)
		{
			referral_response.setServiceProviderName("");
			referral_response.setProgramIdentifier("");
			referral_response.setProgramName("");
			
			referral_response.setCstsCode("");
			referral_response.setSexSpecificCode("");
			
			referral_response.setSpContractProgram(false);
			
			referral_response.setProgramLanguagesList(programLanguagesList);
			
			referral_response.setStreetNumber("");
			referral_response.setStreetName("");
			referral_response.setStreetType("");
			referral_response.setAptNum("");
			referral_response.setCity("");
			referral_response.setState("");
			referral_response.setZipCode("");
			referral_response.setLocationPhone("");
			referral_response.setLocationFax("");
		}
		
		//return response event
		return referral_response;
	}//end of getProgramReferralResponse()
	
	
	
	public static List getProgRefByCaseloadRespEvts(Iterator iterator)
	{
		ArrayList responseEvtList = new ArrayList();
		
		while(iterator.hasNext())
		{
			CSProgramReferralByCaseload progRefByCaseload = (CSProgramReferralByCaseload)iterator.next();
			
			ProgrRefByCaseloadResponseEvent responseEvt = new ProgrRefByCaseloadResponseEvent();
			
			responseEvt.setProgramReferralId(progRefByCaseload.getProgramReferralId());
			responseEvt.setCriminalCaseId(progRefByCaseload.getCriminalCaseId());
			responseEvt.setDefendantId(progRefByCaseload.getDefendantId());
			// get supervisee name from active case
			if ( StringUtils.isNotEmpty(progRefByCaseload.getCriminalCaseId())) {
				
				CriminalCase cCase = null;
				String criminalCaseId = progRefByCaseload.getCriminalCaseId();
				String caseNum = criminalCaseId.substring(3,criminalCaseId.length());
				String courtDivision = criminalCaseId.substring(0,3);
				GetCaseEvent caseEvent = new GetCaseEvent();
				caseEvent.setCaseNum(caseNum);
				caseEvent.setCourtDivisionId(courtDivision);
				caseEvent.setAgencyId( PDSecurityHelper.getUserAgencyId() );
				Iterator caseIter = CriminalCase.findAll(caseEvent);
				
				while ( caseIter.hasNext() ){
					
					cCase = (CriminalCase) caseIter.next();
					responseEvt.setSuperviseeName( cCase.getDefendantName() );
				}
				cCase = null;
				criminalCaseId = null;
				caseNum = null;
				courtDivision = null;
			}
			responseEvt.setReferralStatusCd(progRefByCaseload.getReferralStatusCode());
			responseEvt.setReferralTypeCd(progRefByCaseload.getReferralTypeCode());
			responseEvt.setReferralTypeDesc(getReferralTypeDesc(progRefByCaseload.getReferralTypeCode()));
			responseEvt.setProgramGroupCd(progRefByCaseload.getProgramGroupCd());
			responseEvt.setProgramTypeCd(progRefByCaseload.getProgramTypeCd());
			responseEvt.setProgramHierarchyCd(progRefByCaseload.getProgramHierarchyCd());
			responseEvt.setReferralDate(progRefByCaseload.getReferralDate());
			responseEvt.setProgLocationId(progRefByCaseload.getProgramLocationId());
			responseEvt.setReferralBeginDate(progRefByCaseload.getReferralBeginDate());
			responseEvt.setReferralEndDate(progRefByCaseload.getReferralEndDate());
			responseEvt.setServiceProviderId(progRefByCaseload.getServiceProviderId());
			responseEvt.setServiceProviderName(progRefByCaseload.getServiceProviderName());
			responseEvt.setProgramId(progRefByCaseload.getProgramId());
			responseEvt.setProgramIdentifier(progRefByCaseload.getProgramIdentifier());
			responseEvt.setProgramName(progRefByCaseload.getProgramName());
			responseEvt.setInHouse(progRefByCaseload.getIsInHouse());
			responseEvt.setCtsCode(progRefByCaseload.getCstsCode());
			responseEvt.setLocationId(progRefByCaseload.getLocationId());
			responseEvt.setRegionCd(progRefByCaseload.getRegionCode());
			responseEvt.setLocationName(progRefByCaseload.getLocationName());
			responseEvt.setLocationPhone(progRefByCaseload.getLocationPhone());
			responseEvt.setStreetNum(progRefByCaseload.getStreetNumber());
			responseEvt.setStreetName(progRefByCaseload.getStreetName());
			responseEvt.setStreetTypeCd(progRefByCaseload.getStreetTypeCd());
			responseEvt.setAptNum(progRefByCaseload.getAptNum());
			responseEvt.setCity(progRefByCaseload.getCity());
			responseEvt.setState(progRefByCaseload.getState());
			responseEvt.setZipCode(progRefByCaseload.getZipCode());
			
			responseEvtList.add(responseEvt);
			progRefByCaseload = null;
			responseEvt = null;
		}
		
		return responseEvtList;
	}

	
	
	
	public static Iterator getReferralForms(String referralTypeCd)
	{
		GetCurrentReferralFormsEvent referralFormsEvt = new GetCurrentReferralFormsEvent();
		referralFormsEvt.setReferralTypeCd(referralTypeCd);
		
		Iterator referralFormIter = CSReferralForm.findAll(referralFormsEvt);
		return referralFormIter;
	}
	
	
	public static List getReferralsForRefTypes(InitNOpenRefsForRefTypesEvent queryEvent)
	{
		return CollectionUtil.iteratorToList(CSProgramReferral.findAll(queryEvent));
	}
	
	
	public static List getReferralsWithRefTypesResponseEvent(List progRefList)
	{
		ArrayList responseList = new ArrayList();
		
		Iterator iter = progRefList.iterator();
		while(iter.hasNext())
		{
			CSProgramReferral programReferral = (CSProgramReferral)iter.next();
			
			ReferralWithRefTypeResponseEvent responseEvent = new ReferralWithRefTypeResponseEvent();
			responseEvent.setProgramReferralId(programReferral.getOID());
			responseEvent.setReferralTypeCd(programReferral.getReferralTypeCode());
			
			responseList.add(responseEvent);
		}
		return responseList;
	}
	
	
	
	private static CSProgramReferralType getReferralType(String referralTypeCode){
		CSProgramReferralType programRefType = null;
		Iterator refTypeIter = CSProgramReferralType.findAll("referralTypeCode", referralTypeCode);
		if(refTypeIter!=null && refTypeIter.hasNext())
		{
			programRefType = (CSProgramReferralType)refTypeIter.next();
		}
		return programRefType;
	}
	
	/**
	 * Convert the given referral type code to a meaningful description
	 * @param refTypeCode
	 * @return
	 */
	public static String getReferralTypeDesc(String refTypeCode)
	{
		int space_indx = refTypeCode.indexOf(' ');
		String program_group = refTypeCode.substring(0, space_indx);
		String program_type = refTypeCode.substring(space_indx + 1);
		
			//get description for the given program group and type codes
		String program_group_desc =
			SimpleCodeTableHelper.getDescrByCode(
				CSAdministerProgramReferralsConstants.PROGRAM_GROUP_CODE_TABLE, program_group);
		String program_type_desc =
			SimpleCodeTableHelper.getDescrByCode(
					CSAdministerProgramReferralsConstants.PROGRAM_TYPE_CODE_TABLE, program_type);
		
		return program_group_desc + " / " + program_type_desc;
	}//end of getReferralTypeResponse()
	
	
	
	/**
	 * Create a referral type response event using given referral type
	 * @param refType
	 * @return
	 */
	public static ReferralTypeResponseEvent getReferralTypeResponse(CSProgramReferralType refType)
	{
			//create and populate response event for referral types
		ReferralTypeResponseEvent ref_type_response = new ReferralTypeResponseEvent();
		
		ref_type_response.setProgramGroupCode(refType.getProgramGroupCode());
		ref_type_response.setProgramTypeCode(refType.getProgramTypeCode());
		ref_type_response.setReferralTypeCode(refType.getReferralTypeCode());
		
		ref_type_response.setReferralTypeId(refType.getOID());		

		ref_type_response.setProgramGroupDesc(
			SimpleCodeTableHelper.getDescrByCode(
					CSAdministerProgramReferralsConstants.PROGRAM_GROUP_CODE_TABLE,
								refType.getProgramGroupCode()));
		ref_type_response.setProgramTypeDesc(
			SimpleCodeTableHelper.getDescrByCode(
					CSAdministerProgramReferralsConstants.PROGRAM_TYPE_CODE_TABLE, 
								refType.getProgramTypeCode()));
		
		return ref_type_response;
	}//end of getReferralTypeResponse()
	

	
	
	
	/**
	 * Return a list referral type response events
	 * @return
	 */
	public static List getReferralTypeResponses()
	{
		List ref_types = getReferralTypes();
		int ref_type_cnt = ref_types.size();
		List ref_type_responses = new ArrayList(ref_type_cnt);
		
		for (int i=0;i<ref_type_cnt;i++)
		{	
				//convert referral type to referral type response event
			ref_type_responses.add(
					getReferralTypeResponse((CSProgramReferralType)ref_types.get(i)));
		}
		
			//return referral type response events
		return ref_type_responses;
	}//end of getReferralTypeResponses()
	
	
	/**
	 * Retrieve all referral types
	 * @return
	 */
	public static List getReferralTypes()
	{
			//return list of all referral types
		CSProgramReferralType programReferralType = new CSProgramReferralType();
		return CollectionUtil.iteratorToList(programReferralType.findAll());
	}//end of getReferralTypes()
	
	
	public static ReferralFormFieldResponseEvent getRefFormFieldRespEvent(CSReferralFormField refFormField, String programRefId)
	{
		//This code generates the form and the jsp tile from CSREFERFORMFIELD table 
		ReferralFormFieldResponseEvent responseEvt = new ReferralFormFieldResponseEvent();
		
		if(refFormField instanceof CSReferralFormFieldData)
		{
			responseEvt.setFieldId(((CSReferralFormFieldData)refFormField).getRefFormFieldId());
		}
		else
		{
			responseEvt.setFieldId(refFormField.getOID());
		}
		
		responseEvt.setFieldLabel(refFormField.getFieldLabel());
		
		responseEvt.setColumnWidth(refFormField.getColWidth());
		responseEvt.setSummaryColumnWidth(refFormField.getSummaryColWidth());
		
		responseEvt.setRowSequence(refFormField.getRowSequence());
		responseEvt.setColumnSequence(refFormField.getColSequence());
		
		responseEvt.setSummaryRowSeq(refFormField.getRowSequence());
		responseEvt.setSummaryColSeq(refFormField.getColSequence());
		
		responseEvt.setRequired(refFormField.getIsRequired());
		
		String uiControlType = refFormField.getResponseUIControlType();
		responseEvt.setResponseUIControlType(refFormField.getResponseUIControlType());
		responseEvt.setResponseDataType(refFormField.getResponseDataType());
		
		responseEvt.setUiControlSize(refFormField.getUiControlSize());
		responseEvt.setTextLength(refFormField.getTextLength());
		responseEvt.setMinValue(refFormField.getMinValue());
		responseEvt.setMaxValue(refFormField.getMaxValue());
		
		responseEvt.setResponseNewLine(refFormField.getIsResponseNewLine());
		responseEvt.setEachResponseNewLine(refFormField.getIsEachResponseNewLine());
		responseEvt.setNoOfRespInARow(refFormField.getNoOfRespInRow());
		responseEvt.setFormatKey(refFormField.getFormat());
		responseEvt.setChildFieldId(refFormField.getChildFieldId());
		
		if(uiControlType.equalsIgnoreCase(CSAdministerProgramReferralsConstants.UI_CNTRL_TYPE_SELECT) ||
				(uiControlType.equalsIgnoreCase(CSAdministerProgramReferralsConstants.UI_CNTRL_TYPE_RADIO)) ||
				(uiControlType.equalsIgnoreCase(CSAdministerProgramReferralsConstants.UI_CNTRL_TYPE_CHECKBOX)))
		{
			Iterator iter = CSReferralFormFieldOption.findAll("referralFormFieldId", responseEvt.getFieldId()) ;
			ArrayList optionsList = new ArrayList();
			responseEvt.setPossibleResponseEvents(optionsList);
			
			while(iter.hasNext())
			{
				CSReferralFormFieldOption fieldOption = (CSReferralFormFieldOption)iter.next();
				
				ReferralFormOptionResponseEvent optionRespEvt = new ReferralFormOptionResponseEvent();
				optionsList.add(optionRespEvt);
				
				optionRespEvt.setOptionId(fieldOption.getOID());
				optionRespEvt.setOptionLabel(fieldOption.getOptionLabel());
				optionRespEvt.setOptionValue(fieldOption.getOptionValue());
				
				optionRespEvt.setDefault(fieldOption.getIsDefault());
				optionRespEvt.setOptionEdit(fieldOption.getIsOptionEditable());
				optionRespEvt.setDisplaySequence(new Integer(fieldOption.getDisplaySequence()).intValue());
			}
		}
		
		
		if(refFormField instanceof CSReferralFormFieldData)
		{
			CSReferralFormFieldData refFormFieldData = (CSReferralFormFieldData)refFormField;
			
			if((refFormFieldData.getResponseUIControlType().equalsIgnoreCase(CSAdministerProgramReferralsConstants.UI_CNTRL_TYPE_TEXT)) ||
 					(refFormFieldData.getResponseUIControlType().equalsIgnoreCase(CSAdministerProgramReferralsConstants.UI_CNTRL_TYPE_TEXTBOX)) ||
 					(refFormFieldData.getResponseUIControlType().equalsIgnoreCase(CSAdministerProgramReferralsConstants.UI_CNTRL_TYPE_MCE_EDIT_TEXTBOX)))
 			{
				responseEvt.setSelectedResponseText(refFormFieldData.getRefFormFieldValue());
 			}
//			for RADIO, SELECT, SINGLECHECKBOX UIControlType ( For SINGLECHECKBOX responseId=1, if selected) 
			else
			{
				responseEvt.setSelectedResponseId(refFormFieldData.getRefFormFieldValue());
				
				if(uiControlType.equalsIgnoreCase(CSAdministerProgramReferralsConstants.UI_CNTRL_TYPE_SELECT))
				{
					String selResponseId = refFormFieldData.getRefFormFieldValue();
					if(!StringUtil.isEmpty(selResponseId))
					{
						Collection possibleOptEvtList = responseEvt.getPossibleResponseEvents();
						Iterator optIter = possibleOptEvtList.iterator();
						while(optIter.hasNext())
						{
							ReferralFormOptionResponseEvent optEvt = (ReferralFormOptionResponseEvent)optIter.next();
							if(optEvt.getOptionId().equalsIgnoreCase(selResponseId) && optEvt.isOptionEdit())
							{
								optEvt.setUserEnteredOptionName(refFormFieldData.getUserEnteredOptionName());
							}
						}
					}
				}
			}
		}
		return responseEvt;
	}
	
	
	public static ReferralFormFieldResponseEvent getRefFormFieldRespEvent(List refFormFieldDataList, String programRefId)
	{
		ReferralFormFieldResponseEvent responseEvt = new ReferralFormFieldResponseEvent();

		CSReferralFormFieldData  refFormFieldData = (CSReferralFormFieldData)refFormFieldDataList.get(0);
		
		responseEvt.setFieldId(refFormFieldData.getRefFormFieldId());
		responseEvt.setFieldLabel(refFormFieldData.getFieldLabel());
		
		responseEvt.setColumnWidth(refFormFieldData.getColWidth());
		responseEvt.setSummaryColumnWidth(refFormFieldData.getSummaryColWidth());
		
		responseEvt.setRowSequence(refFormFieldData.getRowSequence());
		responseEvt.setColumnSequence(refFormFieldData.getColSequence());
		
		responseEvt.setSummaryRowSeq(refFormFieldData.getRowSequence());
		responseEvt.setSummaryColSeq(refFormFieldData.getColSequence());
		
		responseEvt.setRequired(refFormFieldData.getIsRequired());
		
		responseEvt.setResponseUIControlType(refFormFieldData.getResponseUIControlType());
		responseEvt.setResponseDataType(refFormFieldData.getResponseDataType());
		
		responseEvt.setUiControlSize(refFormFieldData.getUiControlSize());
		responseEvt.setTextLength(refFormFieldData.getTextLength());
		responseEvt.setMinValue(refFormFieldData.getMinValue());
		responseEvt.setMaxValue(refFormFieldData.getMaxValue());
		
		responseEvt.setResponseNewLine(refFormFieldData.getIsResponseNewLine());
		responseEvt.setEachResponseNewLine(refFormFieldData.getIsEachResponseNewLine());
		responseEvt.setNoOfRespInARow(refFormFieldData.getNoOfRespInRow());
		responseEvt.setFormatKey(refFormFieldData.getFormat());
		responseEvt.setChildFieldId(refFormFieldData.getChildFieldId());
		
//			get field options
		Iterator iter = CSReferralFormFieldOption.findAll("referralFormFieldId", responseEvt.getFieldId()) ;
		ArrayList optionsList = new ArrayList();
		responseEvt.setPossibleResponseEvents(optionsList);
		Map optionIdRespEvtMap = new HashMap();
		
		while(iter.hasNext())
		{
			CSReferralFormFieldOption fieldOption = (CSReferralFormFieldOption)iter.next();
			
			ReferralFormOptionResponseEvent optionRespEvt = new ReferralFormOptionResponseEvent();
			optionsList.add(optionRespEvt);
			optionIdRespEvtMap.put(fieldOption.getOID(), optionRespEvt);
			
			optionRespEvt.setOptionId(fieldOption.getOID());
			optionRespEvt.setOptionLabel(fieldOption.getOptionLabel());
			optionRespEvt.setOptionValue(fieldOption.getOptionValue());
			optionRespEvt.setDefault(fieldOption.getIsDefault());
			optionRespEvt.setOptionEdit(fieldOption.getIsOptionEditable());
			optionRespEvt.setDisplaySequence(new Integer(fieldOption.getDisplaySequence()).intValue());
		}
			
		ArrayList selRespIdsList = new ArrayList();
		responseEvt.setSelectedResponseIdsList(selRespIdsList);
		
		Iterator fieldDataIter = refFormFieldDataList.iterator();
		while(fieldDataIter.hasNext())
		{
			CSReferralFormFieldData  refFormFldData = (CSReferralFormFieldData)fieldDataIter.next();
			String optionId = refFormFldData.getRefFormFieldValue();
			if(optionId!=null && refFormFldData.getIsOptionSelected())
			{
				selRespIdsList.add(optionId);
				
				ReferralFormOptionResponseEvent optionRespEvt = (ReferralFormOptionResponseEvent)optionIdRespEvtMap.get(optionId);
				if(optionRespEvt.isOptionEdit())
				{
					optionRespEvt.setUserEnteredOptionName(refFormFldData.getUserEnteredOptionName());
				}
			}
		}
	
		return responseEvt;
	}
	
	/**
	 * 
	 * @param programReferral
	 * @return
	 */
	public static String getServiceProviderName(CSProgramReferral programReferral)
	{
		String serviceProviderName = "";
		
		String programLocationId = programReferral.getProgramLocationId();
		
		if(!StringUtil.isEmpty(programLocationId))
		{
			GetSPProgramLocationEvent spProgLocEvt = new GetSPProgramLocationEvent();
			spProgLocEvt.setProgLocationId(programLocationId);
			Iterator spIter = CSServiceProvider.findAll(spProgLocEvt);
			if(spIter!=null && spIter.hasNext())
			{
				CSServiceProvider spProgLoc = (CSServiceProvider)spIter.next();
				serviceProviderName = spProgLoc.getServiceProviderName();
			}
		}
		return serviceProviderName;
	}//end of getServiceProviderName()
	
	
	/**
	 * Retrieve all referrals for a given supervisee
	 * @param defendantId
	 */
	public static List getSuperviseeReferrals(String defendantId)
	{
			//retrieve list of referrals
		Iterator list_iter = CSProgramReferral.findAll("defendantId", defendantId);
		
			//convert to more manageable List
		List referral_list = CollectionUtil.iteratorToList(list_iter);
		
			//return list of referrals
		return referral_list;
	}//end of getSuperviseeReferrals()
	private static String getSupervisionPeriodForCase(String defendantId, String criminalCaseId)
	{
		GetCaseSupervisionPeriodEvent event = new GetCaseSupervisionPeriodEvent();
		event.setAgencyId(PDSecurityHelper.getUserAgencyId());
		event.setCriminalCaseId(criminalCaseId);
		
		Iterator iter = SupervisionOrder.findAll(event);
		if(iter != null && iter.hasNext())
		{
			SupervisionOrder supervisionOrder = (SupervisionOrder)iter.next();
			if(supervisionOrder != null)
			{
				return supervisionOrder.getSupervisionPeriodId();
			}
		}
		return null; 
	}
	/**
	 * Save program referral using info specified in event
	 * @param save_referrals_event
	 */
	public static CSProgramReferral initiateProgramReferral(SaveProgramReferralsEvent saveReferralsEvent)
	{
		CSProgramReferral initiate_referral = null;
        
        	//check if programReferralId is set
		if (StringUtil.isEmpty(saveReferralsEvent.getProgramReferralId()))
		{
				// create a new referral and initialize status to 'INITIATED'
			initiate_referral = new CSProgramReferral();
			initiate_referral.setReferralStatusCode(
					CSAdministerProgramReferralsConstants.INITIATED_REFERRAL_STATUS);
			
			if ( saveReferralsEvent.getReferralDate() == null ) {
				
				initiate_referral.setReferralDate(new Date());
			}else{
				
				initiate_referral.setReferralDate( saveReferralsEvent.getReferralDate() );
			}

			initiate_referral.setDefendantId(saveReferralsEvent.getDefendantId());
			initiate_referral.setCriminalCaseId(saveReferralsEvent.getCriminalCaseId());
			initiate_referral.setReferralTypeCode(saveReferralsEvent.getReferralTypeCode());
			initiate_referral.setIncarcerationReferral( saveReferralsEvent.isIncarcerationReferral() );
			initiate_referral.setProgramUnitReferral( saveReferralsEvent.isProgramUnitReferral() );
		}
		else
		{
				// update existing referral if referralId is set
			initiate_referral = 
			    CSProgramReferral.find(saveReferralsEvent.getProgramReferralId());
		
				//set referral properties and update
			initiate_referral.setCriminalCaseId(saveReferralsEvent.getCriminalCaseId());
			String newReferralCd = saveReferralsEvent.getReferralTypeCode();
			String oldReferralCd = initiate_referral.getReferralTypeCode();
			if(!oldReferralCd.equalsIgnoreCase(newReferralCd))
			{
				initiate_referral.setReferralTypeCode(saveReferralsEvent.getReferralTypeCode());
				
				initiate_referral.setProgramLocationId(null);		
				
				initiate_referral.setContractProgram(false);
				
				initiate_referral.setNewServiceProviderName(null);
				initiate_referral.setNewServiceProviderPhone(null);
				initiate_referral.setNewServiceProviderFax(null);
				
				initiate_referral.setScheduleDate(null);
			}
		}
		//persist referral to database
		initiate_referral.bind();
		
			//return saved referral
		return initiate_referral;
	}//end of saveProgramReferral()
	public static void populateFieldMaps(Collection fieldDataList, HashMap fieldIdDataMap, HashMap fldIdUsrEntOptNameMap)
	{
		Iterator iter = fieldDataList.iterator();
		while(iter.hasNext())
		{
			ReferralFormFieldValue formField = (ReferralFormFieldValue)iter.next();
			String fieldId = formField.getFieldId();
			
			if(formField.isMultiCheckBoxField())
			{
				fieldIdDataMap.put(fieldId, formField.getResponseIdsList());
				fldIdUsrEntOptNameMap.put(fieldId, formField.getUserEnteredOptionName());
			}
			else
			if(formField.getResponseText() != null)
			{
				fieldIdDataMap.put(fieldId, formField.getResponseText());
			}
			else
			{
				fieldIdDataMap.put(fieldId, formField.getResponseId());
				fldIdUsrEntOptNameMap.put(fieldId, formField.getUserEnteredOptionName());
			}
		}
	}
	/**
	 * 
	 * @param programReferral
	 */
	public static void recordCasenotesOnAutoExit(CSProgramReferral programReferral)
	{
		String defendantId = programReferral.getDefendantId();
		String criminalCaseId = programReferral.getCriminalCaseId();
		
    	String supPeriodId = getSupervisionPeriodForCase(defendantId, criminalCaseId);
    	
    	if(supPeriodId != null)
    	{
			 String notes = getNotes(programReferral);
			 
			 Collection subjects = new ArrayList();
			 SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.CASENOTE_SUBJECT,PDCodeTableConstants.PROGRAM_REFERRAL_SUBJECT_CD);
			 subjects.add((String) aCode.getOID());
				
			 UpdateCasenoteEvent updateCaseNoteEvent = new UpdateCasenoteEvent();
			 updateCaseNoteEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
			 updateCaseNoteEvent.setUserID(PDSecurityHelper.getLogonId());
			 updateCaseNoteEvent.setSuperviseeId(defendantId);
			 updateCaseNoteEvent.setSupervisionPeriodId(supPeriodId);
	//         casenote is associated to the program referral (Context = Program Referal)
			 updateCaseNoteEvent.setProgramReferralId(programReferral.getOID());
			 updateCaseNoteEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
			 updateCaseNoteEvent.setContextType(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
			 updateCaseNoteEvent.setCasenoteStatusId(CasenoteConstants.STATUS_COMPLETE);
			 updateCaseNoteEvent.setSaveAsDraft(false);
			 updateCaseNoteEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_NONE);
			 updateCaseNoteEvent.setEntryDate(new Date());
			 updateCaseNoteEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
	         
			 updateCaseNoteEvent.setNotes(notes);            
			 updateCaseNoteEvent.setSubjects(subjects);
			 
	         Casenote casenote = new Casenote();
	         casenote.updateCasenote(updateCaseNoteEvent);
    	}
	}//end recordCasenotesOnAutoExit()
	public static void referProgramReferral(ReferProgramReferralEvent referProgRefEvent)
	{
		String programReferralId = referProgRefEvent.getProgramReferralId();
		if(!StringUtil.isEmpty(programReferralId))
		{
			CSProgramReferral programReferral = CSProgramReferral.find(programReferralId);
			
			String progLocationId = getProgramLocationId(referProgRefEvent.getProgramId(), referProgRefEvent.getLocationId());
			if(progLocationId != null)
			{
				programReferral.setProgramLocationId(progLocationId);
			}
			
			programReferral.setScheduleDate(referProgRefEvent.getScheduleDateTime());
			
			programReferral.setSubmitComments(referProgRefEvent.getSubmitComments());
		}
	}
	/**
	 * Save program referral using info specified in event
	 * @param save_referrals_event
	 */
	public static CSProgramReferral saveProgramReferral(SaveProgramReferralsEvent saveReferralsEvent)
	{
		CSProgramReferral save_referral = null;
        
        	//check if programReferralId is set
		if (StringUtil.isEmpty(saveReferralsEvent.getProgramReferralId()))
		{
				// create a new referral and initialize status to 'INITIATED'
			save_referral = new CSProgramReferral();
			save_referral.setReferralStatusCode(
					CSAdministerProgramReferralsConstants.INITIATED_REFERRAL_STATUS);
			
			if ( save_referral.getReferralDate() == null ){
				
				save_referral.setReferralDate(new Date());
			}else{
				
				save_referral.setReferralDate( saveReferralsEvent.getReferralDate() );
			}

		}
		else
		{
				// update existing referral if referralId is set
			save_referral = 
			    CSProgramReferral.find(saveReferralsEvent.getProgramReferralId());
		}
		
			//set referral properties and update
		save_referral.setDefendantId(saveReferralsEvent.getDefendantId());
		save_referral.setCriminalCaseId(saveReferralsEvent.getCriminalCaseId());
		
//		set referral status code ******************************* 
		save_referral.setReferralTypeCode(saveReferralsEvent.getReferralTypeCode());
		
		String programLocationId = getProgramLocationId(saveReferralsEvent.getProgramId(), saveReferralsEvent.getLocationId());
		save_referral.setProgramLocationId(programLocationId);		
		
		save_referral.setNewServiceProviderName(saveReferralsEvent.getNewServiceProviderName());
		save_referral.setNewServiceProviderPhone(saveReferralsEvent.getNewServiceProviderPhone());
		save_referral.setNewServiceProviderFax(saveReferralsEvent.getNewServiceProviderFax());
		
		save_referral.setScheduleDate(saveReferralsEvent.getScheduleDate());
		
		save_referral.setProgramBeginDate(saveReferralsEvent.getProgramBeginDate());
		save_referral.setContractProgram(saveReferralsEvent.isContractProgram());
		save_referral.setTracerNumber(saveReferralsEvent.getTracerNumber());	
		save_referral.setPlacementReasonCd(saveReferralsEvent.getPlacementReasonCd());
		save_referral.setConfinementYears(saveReferralsEvent.getConfinementYears());
		save_referral.setConfinementMonths(saveReferralsEvent.getConfinementMonths());
		save_referral.setConfinementDays(saveReferralsEvent.getConfinementDays());
		save_referral.setSubmitComments(saveReferralsEvent.getSubmitComments());
		
		save_referral.setProgramEndDate(saveReferralsEvent.getProgramEndDate());
		save_referral.setDischargeReasonCd(saveReferralsEvent.getDischargeReasonCd());
		save_referral.setExitComments(saveReferralsEvent.getExitComments());
		
		//persist referral to database
		save_referral.bind();
		
			//return saved referral
		return save_referral;
	}//end of saveProgramReferral()
	public static void saveProgRefCasenote(SaveProgRefCasenoteEvent saveCasenoteEvent)
	{
		String programReferralId = saveCasenoteEvent.getProgramReferralId();
		
		if(programReferralId!=null)
		{
			CSProgramReferral programReferral = CSProgramReferral.find(programReferralId);
			if(programReferral!=null)
			{
				String defendantId = programReferral.getDefendantId();
				String criminalCaseId = programReferral.getCriminalCaseId();
				
		    	String supPeriodId = getSupervisionPeriodForCase(defendantId, criminalCaseId);
		    	
		    	if(supPeriodId != null)
		    	{
					 Collection subjects = new ArrayList();
					 SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.CASENOTE_SUBJECT,saveCasenoteEvent.getCasenoteSubjectCd());
					 subjects.add((String) aCode.getOID());
						
					 UpdateCasenoteEvent updateCaseNoteEvent = new UpdateCasenoteEvent();
					 updateCaseNoteEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
					 updateCaseNoteEvent.setUserID(PDSecurityHelper.getLogonId());
					 updateCaseNoteEvent.setSuperviseeId(defendantId);
					 updateCaseNoteEvent.setSupervisionPeriodId(supPeriodId);
					 
					 if(saveCasenoteEvent.getCasenoteContext().equalsIgnoreCase(CSAdministerProgramReferralsConstants.CASENOTE_CONTEXT_PROGRAM_REFERAL))
					 {
						 updateCaseNoteEvent.setProgramReferralId(programReferral.getOID());
					 }
					 updateCaseNoteEvent.setCasenoteTypeId(saveCasenoteEvent.getCasenoteTypeId());
					 updateCaseNoteEvent.setContextType(saveCasenoteEvent.getCasenoteTypeId());
					 updateCaseNoteEvent.setCasenoteStatusId(CasenoteConstants.STATUS_COMPLETE);
					 updateCaseNoteEvent.setSaveAsDraft(false);
					 updateCaseNoteEvent.setContactMethodId(saveCasenoteEvent.getContactMethodId());
					 updateCaseNoteEvent.setEntryDate(new Date());
					 updateCaseNoteEvent.setHowGeneratedId(saveCasenoteEvent.getHowGeneratedId());
			         
					 String notes = saveCasenoteEvent.getCasenoteComments();
					 if(notes!=null)
					 {
						 updateCaseNoteEvent.setNotes(notes);
					 }
					 updateCaseNoteEvent.setSubjects(subjects);
					 
			         Casenote casenote = new Casenote();
			         casenote.updateCasenote(updateCaseNoteEvent);
		    	}
			}
		}
	}
	public static void saveReferralForm(SaveReferralFormEvent saveRefFormEvt)
	{
		Collection fieldDataList = saveRefFormEvt.getFieldDataList();
		Iterator iter = fieldDataList.iterator();
		while(iter.hasNext())
		{
			ReferralFormFieldValue fieldValue = (ReferralFormFieldValue)iter.next();
			
			if(fieldValue.isMultiCheckBoxField())
			{
				List selOptionsList = fieldValue.getResponseIdsList();
				
				Iterator optionsIter = CSReferralFormFieldOption.findAll("referralFormFieldId", fieldValue.getFieldId());
				if(optionsIter != null)
				{
					while(optionsIter.hasNext())
					{
						CSReferralFormFieldOption fieldOption = (CSReferralFormFieldOption)optionsIter.next();
						
						CSReferralFormFieldData refFieldData = new CSReferralFormFieldData();
						refFieldData.setProgramReferralId(saveRefFormEvt.getProgramReferralId());
						refFieldData.setRefFormFieldId(fieldValue.getFieldId());
						refFieldData.setRefFormFieldValue(fieldOption.getOID());
						refFieldData.setIsOptionSelected(false);
						
						if(selOptionsList!=null && selOptionsList.size()>0)
						{
							if(selOptionsList.contains(fieldOption.getOID()))
							{
								refFieldData.setIsOptionSelected(true);
								if(fieldOption.getIsOptionEditable())
								{
									refFieldData.setUserEnteredOptionName(fieldValue.getUserEnteredOptionName());
								}
							}
						}
						refFieldData.bind();
					}
				}
			}
			else
			{
				CSReferralFormFieldData refFieldData = new CSReferralFormFieldData();
				refFieldData.setProgramReferralId(saveRefFormEvt.getProgramReferralId());
				refFieldData.setRefFormFieldId(fieldValue.getFieldId());
				if(fieldValue.getResponseText()!=null)
				{
					refFieldData.setRefFormFieldValue(fieldValue.getResponseText());
				}
				else
				{
					refFieldData.setRefFormFieldValue(fieldValue.getResponseId());
				}
				refFieldData.bind();
			}
		}
	}
	public static SubmitExitProgRefResponseEvent submitProgramReferral(SubmitProgramReferralEvent submitProgRefEvent)
	{
		SubmitExitProgRefResponseEvent responseEvent = new SubmitExitProgRefResponseEvent();
		
		String programReferralId = submitProgRefEvent.getProgramReferralId();
		if(!StringUtil.isEmpty(programReferralId))
		{
			CSProgramReferral programReferral = CSProgramReferral.find(programReferralId);
			
			programReferral.setReferralStatusCode(CSAdministerProgramReferralsConstants.OPEN_REFERRAL_STATUS);
			
			programReferral.setReferralDate( submitProgRefEvent.getReferralDate() );
			programReferral.setProgramBeginDate(submitProgRefEvent.getProgramBeginDate());
			programReferral.setContractProgram(submitProgRefEvent.isContractProgram());
			programReferral.setTracerNumber(submitProgRefEvent.getTracerNumber());
			programReferral.setPlacementReasonCd(submitProgRefEvent.getPlacementReasonCd());
			
			programReferral.setConfinementYears(submitProgRefEvent.getConfinementYears());
			programReferral.setConfinementMonths(submitProgRefEvent.getConfinementMonths());
			programReferral.setConfinementDays(submitProgRefEvent.getConfinementDays());
			
			programReferral.setSubmitComments(submitProgRefEvent.getSubmitComments());
			programReferral.setExitComments(submitProgRefEvent.getSubmitComments());
			
			programReferral.bind();
			
			responseEvent.setTRecordGenerated(false);
			
//			create T33 record, on submit of state reporting program referrals
			String referralTypeCode = programReferral.getReferralTypeCode();
			Iterator refTypeIter = CSProgramReferralType.findAll("referralTypeCode", referralTypeCode);
			if(refTypeIter!=null && refTypeIter.hasNext())
			{
				CSProgramReferralType programRefType = (CSProgramReferralType)refTypeIter.next();
//				If CTSCode on Referral Type is not "LOC"(Local), then it is state reporting
				if( !CSAdministerProgramReferralsConstants.NOT_STATE_REPORTING_CODE.equalsIgnoreCase(programRefType.getCtsCode() ))
				{
					String programLocationId = programReferral.getProgramLocationId();
					if(!StringUtil.isEmpty(programLocationId))
					{
						CSTSHelper.createCSTSRecordOnSubmit(programReferral, programRefType);							
						responseEvent.setTRecordGenerated(true);
					}
				}
			}
		}
		return responseEvent;
	}
	public static void updateProgramReferral(UpdateProgramReferralEvent updateProgRefEvent)
	{
		String programReferralId = updateProgRefEvent.getProgramReferralId();
		if(!StringUtil.isEmpty(programReferralId))
		{
			CSProgramReferral programReferral = CSProgramReferral.find(programReferralId);

			PreviousReferralData prevRefData = new PreviousReferralData();
			prevRefData.savePreviousReferralValues(programReferral);

			boolean dataChanged = false;
			
			if(updateProgRefEvent.isUpdateSubmitRef())
			{
				programReferral.setProgramBeginDate(updateProgRefEvent.getProgramBeginDate());
				programReferral.setContractProgram(updateProgRefEvent.isContractProgram());
				programReferral.setTracerNumber(updateProgRefEvent.getTracerNumber());
				programReferral.setPlacementReasonCd(updateProgRefEvent.getPlacementReasonCd());
				
				programReferral.setConfinementYears(updateProgRefEvent.getConfinementYears());
				programReferral.setConfinementMonths(updateProgRefEvent.getConfinementMonths());
				programReferral.setConfinementDays(updateProgRefEvent.getConfinementDays());
				dataChanged = prevRefData.hasSubmitDataChanged(updateProgRefEvent);
			}
//			update exit prog referral
			else
			{
				programReferral.setProgramEndDate(updateProgRefEvent.getProgramEndDate());
				programReferral.setDischargeReasonCd(updateProgRefEvent.getDischargeReasonCd());
				dataChanged = prevRefData.hasExitDataChanged(updateProgRefEvent);
			}
			CSProgramReferralType refTypeCode = getReferralType(programReferral.getReferralTypeCode());
//			If CTSCode on Referral Type is not "LOC"(Local), then it is state reporting
			if(refTypeCode == null || 
					CSAdministerProgramReferralsConstants.NOT_STATE_REPORTING_CODE.
						equalsIgnoreCase(refTypeCode.getCtsCode() )){
				return;
			}

			if (dataChanged){
				boolean sentToState = 
					CSTSHelper.isCSTSRecordBeenSentToState(
						programReferral, 
						updateProgRefEvent.isUpdateSubmitRef());
				if (sentToState){
					createTaskToCSTSWorkgroup(
						programReferral.getDefendantId(), 
						updateProgRefEvent.isUpdateSubmitRef(), 
						programReferral.getReferralTypeCode(),
						prevRefData.getChangeMessage());
				} else if (updateProgRefEvent.isUpdateSubmitRef()){
					CSTSHelper.updateReferralSubmitCSTSRecord(programReferral, refTypeCode);  
				} else {
					CSTSHelper.updateReferralExitCSTSRecord(programReferral, refTypeCode);   
				}
			}
		}
	}

	/**
	 * Update specified map of referrals                                                
	 * @param referralsMap
	 */
	public static void updateProgramReferrals(List referralsList)
	{
		Iterator refIter = referralsList.iterator();
		
		while(refIter.hasNext())
		{
			SaveProgramReferralsEvent saveRefEvent = (SaveProgramReferralsEvent)refIter.next();
			
			String progRefId = saveRefEvent.getProgramReferralId();
			
			if(progRefId != null)
			{
				CSProgramReferral programReferral = CSProgramReferral.find(progRefId);
				
				if(programReferral!=null)
				{
					if(programReferral.getReferralStatusCode().equalsIgnoreCase(CSAdministerProgramReferralsConstants.INITIATED_REFERRAL_STATUS))
					{
						String progLocationId = getProgramLocationId(saveRefEvent.getProgramId(), saveRefEvent.getLocationId());
						
						if(!StringUtil.isEmpty(progLocationId))
						{
							programReferral.setProgramLocationId(progLocationId);
							
//							set the program referral contract program=true, if its programLocation is associated to a contract program
							GetSPProgramLocationEvent spProgLocEvent = new GetSPProgramLocationEvent();
							spProgLocEvent.setProgLocationId(progLocationId);
							Iterator spIter = CSServiceProvider.findAll(spProgLocEvent);
							
							if(spIter!= null && spIter.hasNext())
							{
								CSServiceProvider spProgLoc = (CSServiceProvider)spIter.next();
								programReferral.setContractProgram(spProgLoc.getIsContractProgram());
							}
						}
						
						programReferral.setScheduleDate(saveRefEvent.getScheduleDate());
						programReferral.setReferralDate( saveRefEvent.getReferralDate() );
						programReferral.setNewServiceProviderName(saveRefEvent.getNewServiceProviderName());
						programReferral.setNewServiceProviderPhone(saveRefEvent.getNewServiceProviderPhone());
						programReferral.setNewServiceProviderFax(saveRefEvent.getNewServiceProviderFax());
					}	
					programReferral.bind();
				}
			}
		}
	}	
	
}//end of CSProgramReferralHelper class

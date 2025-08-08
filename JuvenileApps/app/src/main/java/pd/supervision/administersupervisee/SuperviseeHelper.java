package pd.supervision.administersupervisee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.ujac.util.BeanComparator;

import pd.codetable.Code;
import pd.codetable.SimpleCodeTableHelper;
import pd.contact.party.Party;
import pd.km.util.Name;
import pd.security.PDSecurityHelper;
import pd.supervision.Factory.OutOfCountyCaseFactory;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administercaseload.SuperviseeHistory;
import pd.supervision.administercaseload.SupervisionLevelOfServiceCode;
import pd.supervision.csts.CSTSHelper;
import pd.supervision.legacyupdates.ILegacyUpdates;
import pd.supervision.legacyupdates.LegacyUpdateLog;
import pd.supervision.legacyupdates.LegacyUpdatesImpl;
import pd.supervision.managesupervisioncase.OutOfCountyCaseHistory;
import pd.supervision.managesupervisioncase.OutOfCountyProbationCase;
import pd.supervision.managetask.PDTaskHelper;
import pd.supervision.manageworkgroup.WorkGroup;
import pd.supervision.manageworkgroup.WorkGroupHelper;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import pd.supervision.transfers.CSTransfer;
import naming.CSCAssessmentConstants;
import naming.CaseloadConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import messaging.administercaseload.UpdateLevelOfSupervisionEvent;
import messaging.administersupervisee.GetTransfersEvent;
import messaging.administersupervisee.reply.SupervisionLevelResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.legacyupdates.GetLegacyUpdatesEvent;
import messaging.legacyupdates.UpdateLevelOfSupervisionDataEvent;
import messaging.managetask.CreateCSTaskEvent;
import messaging.transfers.reply.TransferResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.security.helper.SecurityUtil;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;

public class SuperviseeHelper {
	
	/**
	 * @param event
	 * @return
	 */
	public static void updateSupervisionLevel(
			UpdateLevelOfSupervisionEvent event) {
		SuperviseeHistory history = new SuperviseeHistory(); 
    	
		if (event.isCreate()) {
			Supervisee supervisee = Supervisee.findByDefendantId(event
					.getDefendantId());
			if (supervisee != null) {
				supervisee.setLosEffectiveDate(event.getLosEffectiveDate());
				String oSupervisionLevelId = supervisee.getSupervisionLevelId();
				supervisee.setSupervisionLevelId(event.getLevelOfSupervisionId());
				String comments = event.getComments();
				if (comments != null) {
					supervisee.setComments(comments);
					history.setComments(comments);
				}
				history.setType(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_LOS);
				history.setSuperviseeId(supervisee.getOID());
				history.setLosEffectiveDate(supervisee.getLosEffectiveDate());
				history.setSupervisionLevelId(supervisee.getSupervisionLevelId());
				history.setAssignedProgramUnitId(supervisee.getAssignedProgramUnitId());
				history.setCaseloadCreditStaffPositionId(supervisee
						.getCaseloadCreditStaffPositionId());
				history.setAssignedStaffPositionId(supervisee
						.getAssignedStaffPositionId());
				history.setCurrentlySupervised(supervisee.isCurrentlySupervised());
				IHome home = new Home();
				home.bind(history);
				String superviseeHistoryId = history.getOID();
				sendSupervisionlevelResponseEvent(superviseeHistoryId);

				UpdateLevelOfSupervisionDataEvent reqEvent = new UpdateLevelOfSupervisionDataEvent();
	    		ILegacyUpdates losHandler = new LegacyUpdatesImpl();  
	    		reqEvent.setAction("A");
	    		reqEvent.setRecType("T20");
	    		reqEvent.setSpn(event.getDefendantId());
	    		if (event.getLevelOfSupervisionId() != null && !event.getLevelOfSupervisionId().equals(PDConstants.BLANK)){
	    			SupervisionLevelOfServiceCode supervisionLevel = (SupervisionLevelOfServiceCode) new mojo.km.persistence.Reference(
	    					event.getLevelOfSupervisionId(), SupervisionLevelOfServiceCode.class).getObject();
	    			if (supervisionLevel != null){
	    				reqEvent.setLos(supervisionLevel.getCstsCode());
	    			} else {
	    				reqEvent.setLos(PDConstants.BLANK);
	    			}
	    		} else {
	    			reqEvent.setLos(PDConstants.BLANK);
	    		}
	    		if (oSupervisionLevelId != null && !oSupervisionLevelId.equals(PDConstants.BLANK)){
	    			SupervisionLevelOfServiceCode oSupervisionLevel = (SupervisionLevelOfServiceCode) new mojo.km.persistence.Reference(
	    					oSupervisionLevelId, SupervisionLevelOfServiceCode.class).getObject();
	    			if (oSupervisionLevel != null){
	    				reqEvent.setOlos(oSupervisionLevel.getCstsCode());
	    			} else {
	    				reqEvent.setOlos(PDConstants.BLANK);
	    			}
	    		} else {
	    			reqEvent.setOlos(PDConstants.BLANK);
	    		}
	    		reqEvent.setTransactionDate(event.getLosEffectiveDate());
	    		reqEvent.setJims2SourceId(history.getOID());
	    		reqEvent.setLogonId(SecurityUtil.getCurrentUser().getJIMSLogonId());	    
	    		reqEvent.setComments(event.getComments());
	    		if (event.getCaseNum() != null && !event.getCaseNum().equals(PDConstants.BLANK)){
	    			reqEvent.setCriminalCaseId(event.getCaseNum());
	    		} else {
	    			reqEvent.setCriminalCaseId(SupervisionOrderHelper.getActiveCaseId(event.getDefendantId()));
	    		}
	    	   	try {
	    	   		//Defect 67724 - Build T20/Legacy Log record ONLY if CSTS LOS has changed.
	    	   		//Do not build a T20/Legacy Log record if LOS changed to BLANK.
	    	   		if (!reqEvent.getLos().equals(reqEvent.getOlos())
	    	   				&& !reqEvent.getLos().equals(PDConstants.BLANK)){
	    	   			losHandler.create(reqEvent,"T20");
	    	   		}
	    		} catch (Exception e) {		
	    			e.printStackTrace();
	    		}
		   	} else {
    			ErrorResponseEvent re = new ErrorResponseEvent();
    			re.setMessage("Supervisee record not found for spn: " + event.getDefendantId());
    			MessageUtil.postReply(re);
		   	}
		} else if (event.isUpdate()){
			SuperviseeHistory updateRecord = SuperviseeHistory.find(event.getSuperviseeHistoryId());
			boolean createTask = false;
			boolean effectiveDateChanged = false;
			boolean logRecordNotFound = false;
			if (updateRecord != null){
				//create T20 record
				try {
					boolean sentToState = CSTSHelper.hasCSTSRecordBeenSentToState(event.getDefendantId(), updateRecord);
					if (sentToState) {
						//ER 62908
						//A T20 is ADDED when a Correction operation changes the Supervision Level and the record has been sent to the state.
						//    The T20 added will be a "correction" operation.
						//If the "Correction" operation changes the Effective Date and the record has been sent to the state, a T20 is NOT added;
						//	instead a TASK will be sent to the CSTS Workgroup
						String histEffDate = DateUtil.dateToString(updateRecord.getLosEffectiveDate(), DateUtil.DATE_FMT_1);
						String newEffDate = DateUtil.dateToString(event.getLosEffectiveDate(), DateUtil.DATE_FMT_1);

						if (!histEffDate.equals(newEffDate)){
							effectiveDateChanged = true;
							createTask = true;
						} 
					} 
					if (!effectiveDateChanged){
						//Change T20 if sentToState and effective date has not changed.
						//Change T20 if csts snu exists in legacy log.  If log doesn't exist create a task.
						UpdateLevelOfSupervisionDataEvent reqEvent = new UpdateLevelOfSupervisionDataEvent();
						GetLegacyUpdatesEvent gEvent = new GetLegacyUpdatesEvent();
						gEvent.setRecordType("T20");
						gEvent.setSourceOID(updateRecord.getOID());
						gEvent.setSpn(event.getDefendantId());
						List list = LegacyUpdateLog.findAll(gEvent);
					
						if (list != null && list.size() > 0){
							LegacyUpdateLog log = (LegacyUpdateLog) list.get(0);
							if (log.getCstsSeqNo() != null && !log.getCstsSeqNo().equals(PDConstants.BLANK)){
								ILegacyUpdates losHandler = new LegacyUpdatesImpl();  
								reqEvent.setAction("C");
								reqEvent.setRecType("T20");
								reqEvent.setSpn(event.getDefendantId());
								SupervisionLevelOfServiceCode supervisionLevel = (SupervisionLevelOfServiceCode) new mojo.km.persistence.Reference(
									event.getLevelOfSupervisionId(), SupervisionLevelOfServiceCode.class).getObject();
								reqEvent.setLos(supervisionLevel.getCstsCode());
								SupervisionLevelOfServiceCode oSupervisionLevel = (SupervisionLevelOfServiceCode) new mojo.km.persistence.Reference(
									updateRecord.getSupervisionLevelId(), SupervisionLevelOfServiceCode.class).getObject();
								reqEvent.setOlos(oSupervisionLevel.getCstsCode());
								reqEvent.setTransactionDate(event.getLosEffectiveDate());
								reqEvent.setJims2SourceId(updateRecord.getOID());
								reqEvent.setLogonId(SecurityUtil.getCurrentUser().getJIMSLogonId());
								reqEvent.setComments(event.getComments());
								if (event.getCaseNum() != null && !event.getCaseNum().equals(PDConstants.BLANK)){
									reqEvent.setCriminalCaseId(event.getCaseNum());
								} else {
									reqEvent.setCriminalCaseId(SupervisionOrderHelper.getActiveCaseId(event.getDefendantId()));
								}
				    	   		//Defect 67724 - Build T20/Legacy Log record ONLY if CSTS LOS has changed.
				    	   		//Do not build a T20/Legacy Log record if LOS changed to BLANK.
				    	   		if (!reqEvent.getLos().equals(reqEvent.getOlos())
				    	   				&& !reqEvent.getLos().equals(PDConstants.BLANK)){

				    	   			losHandler.create(reqEvent,"T20");
				    	   		}
							} else {
								logRecordNotFound = true;
								createTask = true;  //Create task if no snu on log record.
							}
						} else {
							logRecordNotFound = true;
							createTask = true; //Create task if no legacy log record exists for supervisee history record.
						}
					}
					
				} catch(Exception e){
					e.printStackTrace();
				}
				if (createTask){
					createTaskToCSTSWorkgroup(
							event.getDefendantId(), 
							updateRecord, 
							event,
							logRecordNotFound);
				} 
				updateSuperviseeHistory(updateRecord, event);
			} else {
				ErrorResponseEvent re = new ErrorResponseEvent();
				re.setMessage("Supervisee History record not found for correction");
				MessageUtil.postReply(re);
			}
		} 
		else if(event.isDelete()) {
			SuperviseeHistory updateRecord = SuperviseeHistory.find(event.getSuperviseeHistoryId());
			if (updateRecord != null){
				updateRecord.delete();
				new Home().bind(updateRecord);
				ILegacyUpdates losHandler = new LegacyUpdatesImpl();  
				try {
					losHandler.delete(updateRecord.getOID(), "T20", event.getDefendantId());
				} catch (Exception e) {		
					e.printStackTrace();
				}
			} else {
				ErrorResponseEvent re = new ErrorResponseEvent();
				re.setMessage("Supervisee History record not found for delete");
				MessageUtil.postReply(re);
			}		
			updateSuperviseeLOSRecordWithMostCurrentEffectiveSuperviseeLOSHistory(event);
		}
	}
	
	private static void updateSuperviseeHistory(SuperviseeHistory updateRecord, UpdateLevelOfSupervisionEvent event){
		updateRecord.setLosEffectiveDate(event.getLosEffectiveDate());
		updateRecord.setSupervisionLevelId(event.getLevelOfSupervisionId());
		updateRecord.setComments(event.getComments());
		updateRecord.setType(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_LOS);
		
		IHome home = new Home();
		home.bind(updateRecord);
        
		updateRecord = SuperviseeHistory.find(event.getSuperviseeHistoryId());
		updateSuperviseeLOSRecordWithMostCurrentEffectiveSuperviseeLOSHistory(event);
	}
	
	/**
	 * update the superviseeRecord LOS information (SPVSEELOSCODE_ID and LOSEEFFDATE in CSSUPERVISEE table) with the most 
	 * current historical LOS record information based on effective date (LOSEFFDATE in CSSUPERVISEEHIST table of type UPD_LOS)
	 * @param event
	 */
	private static void updateSuperviseeLOSRecordWithMostCurrentEffectiveSuperviseeLOSHistory(UpdateLevelOfSupervisionEvent event){
		
		// RJC - update the supervisee record with the most up to date los supervisee history information 
		// (or set to blank if deleted is last one)
		Supervisee supervisee = Supervisee.findByDefendantId(event
				.getDefendantId());
		if(supervisee == null) {
			ErrorResponseEvent re = new ErrorResponseEvent();
			re.setMessage("Supervisee record not found for spn: " + event.getDefendantId());
			MessageUtil.postReply(re);
	   	}
		Iterator iter = null;			
		if (supervisee != null) {
			iter = SuperviseeHistory.findAll("superviseeId", supervisee.getOID());
			List<SuperviseeHistory> supervisorHistories = CollectionUtil.iteratorToList(iter);
			supervisorHistories = filterSuperviseeHistory(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_LOS,supervisorHistories);
			if(supervisorHistories.size() == 0){
				supervisee.setSupervisionLevelId(""); // deleted the last record so set to a blank value
			}else{	// set to the latest value
				Collections.sort(supervisorHistories, SuperviseeHistory.SuperviseeHistoryEffectiveDateComparator);
				// sort is asc order on oid, so take the last record in the sorted list
				SuperviseeHistory mostRecentHistory = (SuperviseeHistory) supervisorHistories.get(supervisorHistories.size() - 1);
				supervisee.setSupervisionLevelId(mostRecentHistory.getSupervisionLevelId());
				supervisee.setLosEffectiveDate(mostRecentHistory.getLosEffectiveDate());
			}
		}
	}
	
	private static void sendSupervisionlevelResponseEvent(String superviseeHistoryId)
	{
		SupervisionLevelResponseEvent event = new SupervisionLevelResponseEvent();		
		event.setSupervisionLevelHistoryId(superviseeHistoryId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(event);
	}
	public static SupervisionLevelResponseEvent getResponseEvent(SuperviseeHistory history){
		
		SupervisionLevelResponseEvent sre = new SupervisionLevelResponseEvent();
		sre.setComments(history.getComments());
		sre.setLosEffectiveDate(history.getLosEffectiveDate());
		sre.setSupervisionLevelHistoryId(history.getOID());
		sre.setSupervisionLevelId(history.getSupervisionLevelId());
		
		return sre;
		
	}
	private static String stripLeadingZeroes(String aString) {
		   StringBuffer sb = new StringBuffer(aString);
		   StringBuffer newSb = new StringBuffer();
		   for (int i = 0; i < aString.length(); i++) {
			   if (sb.charAt(i) != '0'){
				   newSb.append(sb.substring(i));
				   break;
			   }
		   }
		   return newSb.toString();
	   }
	private static final String YYYYMMDD = "yyyyMMdd";
	
	public static List getTransfers(GetTransfersEvent event){
		Iterator iter = CSTransfer.findAll(event);
		List csTransfers = CollectionUtil.iteratorToList(iter);
		//String agencyId = PDSecurityHelper.getUserAgencyId();
		CSTransfer csTransfer = null;
		List activeSupervisionOrders = new ArrayList();
		List transferRespEvents = new ArrayList();
		
		Map orderMap = new HashMap();
		if (csTransfers.size() > 0){
			activeSupervisionOrders = SupervisionOrderHelper.getActiveSupervisionOrders(event.getSuperviseeId());
			for (int i = 0; i < activeSupervisionOrders.size(); i++) {
				SupervisionOrder order = (SupervisionOrder) activeSupervisionOrders.get(i);
				orderMap.put(order.getCriminalCaseId(), order.getCriminalCaseId());
			}
		}
	    for (int i = 0; i < csTransfers.size(); i++) {
			csTransfer = (CSTransfer) csTransfers.get(i);
			
			if(csTransfer != null){
				TransferResponseEvent resp = new TransferResponseEvent();
				resp.setCdi(csTransfer.getCdi());
				resp.setCourtNum(csTransfer.getCourtNum());
				resp.setCaseNum(csTransfer.getCaseNum());
				Date aDate = DateUtil.stringToDate(csTransfer.getHcTransferInDate().trim(), YYYYMMDD);
				if (aDate != null){
					resp.setHcTransferInDate(DateUtil.dateToString(aDate, DateUtil.DATE_FMT_1));
				}
				aDate = DateUtil.stringToDate(csTransfer.getHcTransferOutDate().trim(), YYYYMMDD);
				if (aDate != null){
					resp.setHcTransferOutDate(DateUtil.dateToString(aDate, DateUtil.DATE_FMT_1));
				}
				String ctyStateId = csTransfer.getTransferringCountyState();
				Code aCode = null;;
				if (ctyStateId != null && !ctyStateId.trim().equals(PDConstants.BLANK)){
					ctyStateId = ctyStateId.trim();
					if (ctyStateId.length() == 2){
						aCode = Code.find(PDCodeTableConstants.STATE_ABBR, ctyStateId);
					} else {
						String countyDesc = "";						
						countyDesc = SimpleCodeTableHelper.getDescrByCodeId(PDCodeTableConstants.COUNTY, stripLeadingZeroes(ctyStateId));
						aCode = new Code();
						aCode.setDescription( countyDesc );
					}
					if (aCode != null){
						resp.setTransferringCountyStateName(aCode.getDescription());
					}
					resp.setTransferringCountyState(ctyStateId);
				}
				aDate = DateUtil.stringToDate(csTransfer.getOutOfCountyTransferInDate().trim(), YYYYMMDD);
				if (aDate != null){
					resp.setOutOfCountyTransferInDate(DateUtil.dateToString(aDate, DateUtil.DATE_FMT_1));
				}
				aDate = DateUtil.stringToDate(csTransfer.getOutOfCountyTransferOutDate().trim(), YYYYMMDD);
				if (aDate != null){
					resp.setOutOfCountyTransferOutDate(DateUtil.dateToString(aDate, DateUtil.DATE_FMT_1));
				}
				ctyStateId = csTransfer.getReceivingCountyState();
				if (ctyStateId != null && !ctyStateId.trim().equals(PDConstants.BLANK)){
					ctyStateId = ctyStateId.trim();
					if (ctyStateId.length() == 2){
						aCode = Code.find(PDCodeTableConstants.STATE_ABBR, ctyStateId);
					} else {
						String countyDesc = "";						
						countyDesc = SimpleCodeTableHelper.getDescrByCodeId(PDCodeTableConstants.COUNTY, stripLeadingZeroes(ctyStateId));
						aCode = new Code();
						aCode.setDescription( countyDesc );
					}
					if (aCode != null){
						resp.setReceivingCountyStateName(aCode.getDescription());
					}
					resp.setReceivingCountyState(ctyStateId);
				}
				
				if (orderMap.containsKey(csTransfer.getCriminalCaseId())){
					resp.setCaseHasActiveSupervisionOrder(true);
				} else {
					resp.setCaseHasActiveSupervisionOrder(false);
				}
				if (csTransfer.getCdi().equals("010") && csTransfer.getOutOfCountyTransferOutDate() != null){
					OutOfCountyProbationCase oocCase = (OutOfCountyProbationCase)OutOfCountyCaseFactory.find(csTransfer.getCaseNum(), csTransfer.getCdi());
					if (oocCase != null){
						Collection coll = oocCase.getUpdateHistory(false);
						if (coll.size() > 0){
							List histList = CollectionUtil.iteratorToList(coll.iterator());
							List sortedHist = new ArrayList(histList);
							ArrayList sortFields = new ArrayList();
							sortFields.add(new ReverseComparator(new BeanComparator("updateDate")));
							sortFields.add(new ReverseComparator(new BeanComparator("updateTime")));
							ComparatorChain multiSort = new ComparatorChain(sortFields);
							Collections.sort(sortedHist, multiSort);
					
							if (csTransfer.getOutOfCountyTransferOutDate() != null
									&& !csTransfer.getOutOfCountyTransferOutDate().trim().equals(PDConstants.BLANK)){
								Date csTransferOutDate = DateUtil.stringToDate(csTransfer.getOutOfCountyTransferOutDate(),"yyyyMMdd");
								if (csTransferOutDate != null){
									for (int j = 0; j < sortedHist.size(); j++) {
										OutOfCountyCaseHistory history = (OutOfCountyCaseHistory) sortedHist.get(j);
										if (history.getTransferDate() != null
												&& history.getTransferDate().equals(csTransferOutDate)
												&& history.getDispositionTypeId() != null 
												&& !history.getDispositionTypeId().equals(PDConstants.BLANK)){
											resp.setOutOfCountyClosureReason(history.getDispositionTypeId());
											break;
										} 
									}
									if (resp.getOutOfCountyClosureReason() == null){
										resp.setOutOfCountyClosureReason("Not Available");
									}
								} 
							} else {
								resp.setOutOfCountyClosureReason(PDConstants.BLANK);
							}
						} else if (oocCase.getDispositionTypeId().startsWith("C")){
							resp.setOutOfCountyClosureReason("Not Available");
						}
					}
				}
				resp.setOtherCountyStatePersonIdNumber(csTransfer.getOtherCountyStatePersonIdNumber().trim());
				resp.setOtherCountyCaseNumber(csTransfer.getOtherCountyCaseNumber().trim());
				resp.setRejected(csTransfer.getRejected().trim().equalsIgnoreCase("Y")?true:false);
				resp.setRejectedAsStr(csTransfer.getRejected().trim());
				resp.setClassificationSeqNumForTransferIn(csTransfer.getClassificationSeqNumForTransferIn().trim());
				resp.setClassificationSeqNumForTransferOut(csTransfer.getClassificationSeqNumForTransferOut().trim());
				resp.setSubclassificationSeqNumForTransferIn(csTransfer.getSubclassificationSeqNumForTransferIn().trim());
				resp.setSubclassificationSeqNumForTransferOut(csTransfer.getSubclassificationSeqNumForTransferOut().trim());
				
				transferRespEvents.add(resp);
			}
	    }		
	    return transferRespEvents;
	}
	private static final String DATE_MSG = "LOS Effective Date  has been corrected from ";
	private static final String LOS_MSG = "LOS has been corrected from ";
	private static final String SUBJ = "LOS Data Correction";
	private static final String SUBJ_NO_LOG = "LOS Correction-No CSTS SNU for LOS History Record";
	private static final String SEMI_COLON = "; ";

	/**
	 *  Create cstask only RRY
	 * @param defendantId
	 * @param oldRecInfo
	 * @param newRecInfo
	 * @param logRecordNotFound
	 */
	public static void createTaskToCSTSWorkgroup(
    		String defendantId, 
    		SuperviseeHistory oldRecInfo,
    		UpdateLevelOfSupervisionEvent newRecInfo,
    		boolean logRecordNotFound){
    	
		WorkGroup wg = WorkGroupHelper.fetchWorkgroupByName(PDSecurityHelper.getUserAgencyId(), CSTSHelper.SA, CSTSHelper.CSTS_WORKGROUP_NAME);
		
	    if(wg != null){
	    	String taskOwnerId = wg.getOID();
		    CreateCSTaskEvent createTask = new CreateCSTaskEvent();
		    //Set due date to 5 days from current date.
		    Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 5);
			createTask.setDueDate(cal.getTime());
			createTask.setStatusId( CSTSHelper.STATUS_O );
			StringBuffer text = new StringBuffer();
			StringBuffer subject = new StringBuffer();

			if (logRecordNotFound){
				subject.append(SUBJ_NO_LOG);
			} else {
				subject.append(SUBJ);
			}

			createTask.setTaskSubject(subject.toString());
			
			if (oldRecInfo.getLosEffectiveDate()!= null && !oldRecInfo.getLosEffectiveDate().equals(newRecInfo.getLosEffectiveDate())){
				text.append(DATE_MSG);
				text.append(DateUtil.dateToString(oldRecInfo.getLosEffectiveDate(), DateUtil.DATE_FMT_1));
				text.append(CSTSHelper.TO);
				text.append(DateUtil.dateToString(newRecInfo.getLosEffectiveDate(), DateUtil.DATE_FMT_1));
			}
			if (!oldRecInfo.getSupervisionLevelId().equals(newRecInfo.getLevelOfSupervisionId())){
				SupervisionLevelOfServiceCode supervisionLevel = (SupervisionLevelOfServiceCode) new mojo.km.persistence.Reference(
						oldRecInfo.getSupervisionLevelId(), SupervisionLevelOfServiceCode.class).getObject();
				String oldLOS = supervisionLevel.getLevelOfServiceLegacyCode();
				supervisionLevel = (SupervisionLevelOfServiceCode) new mojo.km.persistence.Reference(
						newRecInfo.getLevelOfSupervisionId(), SupervisionLevelOfServiceCode.class).getObject();
				String newLOS = supervisionLevel.getLevelOfServiceLegacyCode();
				if (!oldLOS.equals(newLOS)){
					if (text.length() > 0){
						text.append(SEMI_COLON);
					}
					text.append(LOS_MSG);
					text.append(oldLOS);
					text.append(CSTSHelper.TO);
					text.append(newLOS);
				}
			}
			if (text.length() > 0){
				text.append(CSTSHelper.FOR);
				text.append(defendantId);
				text.append(CSTSHelper.SPACE);
			
				createTask.setTastText( text.toString() );

			}
			createTask.setDefendantId( defendantId );
			createTask.setTopic(CSCAssessmentConstants.CSTASK_TOPIC_NOTIFY_CSTS_CHANGE);
			createTask.setWorkGroupId( taskOwnerId );
			
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
	 * filter a list of supervisees based on the type of each supervisee - only keep passed in type (for exampled CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_LOS)
	 * @param superviseeHistoryType
	 * @param superviseeList
	 * @return
	 */
	private static List<SuperviseeHistory> filterSuperviseeHistory(String superviseeHistoryType, List<SuperviseeHistory> superviseeList){
		List<SuperviseeHistory> filteredHistories = new ArrayList<SuperviseeHistory>();
		for(SuperviseeHistory history:superviseeList){
			if(history.getType().equalsIgnoreCase(superviseeHistoryType)){
				filteredHistories.add(history);
			}
		}
		return filteredHistories;
	}
}

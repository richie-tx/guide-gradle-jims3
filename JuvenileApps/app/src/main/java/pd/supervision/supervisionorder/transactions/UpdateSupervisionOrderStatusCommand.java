//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionorder\\transactions\\ActivateSupervisionOrderCommand.java

package pd.supervision.supervisionorder.transactions;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.cscdstaffposition.GetCourtStaffPositionEvent;
import messaging.managetask.CreateCSTaskEvent;
import messaging.managetask.GetCSTasksEvent;
import messaging.manageworkgroup.GetWorkGroupByExactNameEvent;
import messaging.supervisionorder.GetMigratedSupervisionOrderEvent;
import messaging.supervisionorder.GetMostCurrentSupervisionOrderForCaseEvent;
import messaging.supervisionorder.UpdateSupervisionOrderStatusEvent;
import messaging.supervisionorder.reply.ActivateSupervisionOrderErrorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.utilities.CollectionUtil;
import naming.CaseloadConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.SupervisionConstants;
import naming.UIConstants;
import pd.codetable.supervision.SupervisionCode;
import pd.common.util.PDUtil;
import pd.criminalcase.CriminalCase;
import pd.security.PDSecurityHelper;
import pd.supervision.administercaseload.CaseAssignment;
import pd.supervision.administercaseload.CaseAssignmentHelper;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administercaseload.SuperviseeHistory;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administercompliance.NonComplianceEventCasenote;
import pd.supervision.managetask.CSTask;
import pd.supervision.managetask.PDTaskHelper;
import pd.supervision.manageworkgroup.WorkGroup;
import pd.supervision.supervisionoptions.VariableElementType;
import pd.supervision.supervisionorder.OrderVersionSequence;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderConditionRel;
import pd.supervision.supervisionorder.SupervisionOrderConditionRelValue;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import pd.supervision.supervisionorder.SupervisionPeriod;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import pd.supervision.supervisionstaff.cscdstaffposition.CourtStaffPosition;


/**
 * @author dgibler
 *  
 */
public class UpdateSupervisionOrderStatusCommand implements ICommand {

    private static final String COURT_SERVICES_INTAKE = "CS Intake";
    private static final String OOC_COURT_SERVICES_INTAKE = "CS OOC Intake";
    private static final String ORDER_FILED = "New Order For Supervision";
	
	/**
	 * @roseuid 43B2E757005D
	 */
	public UpdateSupervisionOrderStatusCommand() {

	}

	/**
	 * @param event
	 * @roseuid 43B2B6F1007F
	 */
	public void execute(IEvent event) throws IOException {
		UpdateSupervisionOrderStatusEvent updateEvent = (UpdateSupervisionOrderStatusEvent) event;
		SupervisionOrder order = SupervisionOrder.find(updateEvent.getSupervisionOrderId());
		if (order != null) {
			String status = updateEvent.getStatus();
			order.setStatusChangeDate(updateEvent.getStatusChangeDate()); 
			if (status.equals(PDCodeTableConstants.STATUS_ACTIVE_ID)) {
				//Previous order chain will always be 1 since the order chain number isn't set until activation.
				//int prevOrderChainNum = order.getOrderChainNum();
				
				SupervisionOrder processedOrder = this.populateActivateAttributes(order);

				if (processedOrder == null){//Errors found
					return;
				}
				if (UIConstants.CSC.equals(PDSecurityHelper.getUserAgencyId())){
					this.createSupervisee(order);
				}
				if (order.getIsHistoricalOrder() && !SupervisionConstants.ORIGINAL.equals(order.getVersionTypeId())){
				    SupervisionOrder.createOriginalFromHistoricalOrder(order);
				}
				this.updateCaseAssignment(order.getCriminalCaseId(), order.getOID());
				if (SupervisionConstants.ORIGINAL.equals(order.getVersionTypeId())){ 
					//Set original judge name upon activate of original order.  
					//See populateActivateAttributes().
//					if (this.isCreatedFromMigratedOrder(order, prevOrderChainNum) 
//							|| order.getIsHistoricalOrder()){
					//Create activation task for historical/pretrial intervention orders.
					if (this.isCreatedFromMigratedOrder(order)){
						//ER 52277
						//No task needed on originals created from migrated orders.  A manual
						//task will have to be created if creating a new original when deferred 
						//adjudication is revoked.
						// Defect JIMS200050214 Task sent on an Historical Order
					} else {
						this.createOrderActivationTask(order, updateEvent);
					}
				} else {
					this.createOrderActivationTask(order, updateEvent);
				}
				//order.setIsHistoricalOrder(false); //Historical orders are now PTINs
				
				/*if (SupervisionConstants.ORIGINAL.equals(order.getVersionTypeId()))
				{
					CSProgramReferralHelper.createIncarcerationReferrals(order, updateEvent.getOrderConditionIdsList()); 
				} turn off auto generated referral RRY*/
				
			} else {
				order.setOrderStatusId(status);
				order.setStatusChangeDate(updateEvent.getStatusChangeDate()); 
			}
		}
	}

    /**
     * @param order
     * @param updateEvent
     * @throws IOException
     */
    private void createOrderActivationTask(SupervisionOrder order, UpdateSupervisionOrderStatusEvent updateEvent) throws IOException {
    	
		String cloId=UpdateSupervisionOrderStatusCommand.getCLOPositionIdForGivenCourt(order.getOrderCourtId());
		CSTask csTask = new CSTask();
		try {
			createTask( updateEvent, order, cloId );
		} catch (IOException e) {
			throw e;
		}
		if( updateEvent.getKillTaskId()!=null && !updateEvent.getKillTaskId().trim().equals("")){
			// Find out if current user Id is save as CLO;
			String cloUserId=UpdateSupervisionOrderStatusCommand.getCLOUserIdForGivenCourt(order.getOrderCourtId());
			if(cloUserId!=null && updateEvent.getCurrentUserId()!=null){
				if(cloUserId.equals(updateEvent.getCurrentUserId())){  // current user and clo of order must be the same to remove task
					CSTask task = csTask.find(updateEvent.getKillTaskId());
					if(task!=null){
						task.setStatusId("C");
					}
				}
			}
		}
		csTask = null;
	}

	/**
	 * @param order
	 * @param prevOrderChainNum
	 * @return
	 */
	private boolean isCreatedFromMigratedOrder(SupervisionOrder order) {
		boolean isCreatedFromMigratedOrder = false;
		
		GetMigratedSupervisionOrderEvent getMigratedOrderEvent = new GetMigratedSupervisionOrderEvent();
		int prevOrderChainNum = order.getOrderChainNum() - 1;
		
		if (prevOrderChainNum > 0){
			getMigratedOrderEvent.setAgencyId(order.getAgencyId());
			getMigratedOrderEvent.setCriminalCaseId(order.getCriminalCaseId());
			getMigratedOrderEvent.setOrderChainNum(prevOrderChainNum);
			getMigratedOrderEvent.setVersionTypeId(SupervisionConstants.ORIGINAL);
			Iterator iter = SupervisionOrder.findAll(getMigratedOrderEvent);
			if (iter != null && iter.hasNext()){
				isCreatedFromMigratedOrder = true;
			}
		}
		
		return isCreatedFromMigratedOrder;
	}

	/**
	 * @param getOrdersEvent
	 * @return
	 */
	private SupervisionOrder getSupervisionOrder(IEvent getOrdersEvent) {
		Iterator iter = SupervisionOrder.findAll(getOrdersEvent);
		SupervisionOrder supervisionOrder = null;
		if (iter != null && iter.hasNext()) {
			supervisionOrder = (SupervisionOrder) iter.next();
		}
		return supervisionOrder;
	}

	 /**
	 * @param agencyId
	 * @param type
	 * @param name
	 * @return
	 */
	public String fetchWorkgroupByName(String agencyId, String type, String name)
	    {

	        GetWorkGroupByExactNameEvent requestEvent = new GetWorkGroupByExactNameEvent();

	        requestEvent.setAgencyId(agencyId);
	        requestEvent.setName(name);
	        requestEvent.setType(type);
	        
			Iterator wgIter = WorkGroup.findAll( requestEvent );
			List aList = CollectionUtil.iteratorToList(wgIter);
			String wgOid = null;
			
			if (aList != null && aList.size() > 0){
				WorkGroup wg = (WorkGroup) aList.get(0);
				wgOid = wg.getOID();
			}
	        return wgOid;

	    }
	 
	/**
	 * @param orderCourtId
	 * @return
	 */
	public static String getCLOPositionIdForGivenCourt(String orderCourtId){
		String cloId=null;
		GetCourtStaffPositionEvent requestEvent = new GetCourtStaffPositionEvent();
		requestEvent.setCourtId(orderCourtId);
		requestEvent.setJobTitleId(PDCodeTableConstants.STAFF_JOB_TITLE_CLO);
		Iterator staffPositionIter = CSCDStaffPositionHelper.getCourtStaffPositions(requestEvent);
		if (staffPositionIter != null) {
			CourtStaffPosition staff=null;
			if (staffPositionIter.hasNext()) { // only one CLO per court suppossedly
				staff = (CourtStaffPosition) staffPositionIter.next();
				if(staff!=null){
					cloId=staff.getStaffPositionId();
					
				}
			}
		}
		return cloId;
	}
	
	/**
	 * @param orderCourtId
	 * @return
	 */
	public static String getCLOUserIdForGivenCourt(String orderCourtId){
		String cloUserId=null;
		GetCourtStaffPositionEvent requestEvent = new GetCourtStaffPositionEvent();
		requestEvent.setCourtId(orderCourtId);
		requestEvent.setJobTitleId(PDCodeTableConstants.STAFF_JOB_TITLE_CLO);
		Iterator staffPositionIter = CSCDStaffPositionHelper.getCourtStaffPositions(requestEvent);
		if (staffPositionIter != null) {
			CourtStaffPosition staff=null;
			if (staffPositionIter.hasNext()) { // only one CLO per court suppossedly
				staff = (CourtStaffPosition) staffPositionIter.next();
				if(staff!=null){
					cloUserId=staff.getUserProfileId();
				}
			}
		}
		return cloUserId;
	}
	 
	
	/**
	 * @param updateSupervisionOrderStatusEvent
	 * @param order
	 * @param cloId
	 * @throws IOException
	 */
	public  void createTask(UpdateSupervisionOrderStatusEvent updateSupervisionOrderStatusEvent,  SupervisionOrder order, String cloId) throws IOException{
	    
		 boolean cloIsAssigned=true;
		    boolean csoIsAssigned=false;
		    String caseAssignId=null;
		    String taskOwnerId=null;
		    CaseAssignment myAssignment=null;
		    if(order!=null && order.getCriminalCaseId()!=null){
				Iterator iterAssign=CaseAssignment.findAll("criminalCaseId", order.getCriminalCaseId()); // Get order's assigned person
		    	if(iterAssign!=null && iterAssign.hasNext()){
		    		myAssignment=(CaseAssignment)iterAssign.next();
		    		caseAssignId=myAssignment.getOID();
		    		myAssignment.setSupervisionOrderId(order.getOID());
		    		taskOwnerId = myAssignment.getAssignedStaffPositionId();
		    	}
		    }
		    //Create task for Modified or Amended orders
		    if(order.getVersionTypeId()!=null && !order.getVersionTypeId().equals(PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL)){
		    	cloIsAssigned=false;
	    		if(myAssignment!=null)
	    			taskOwnerId=myAssignment.getAssignedStaffPositionId(); // Get order's assigned person
		    		if(taskOwnerId!=null){
						if(cloId!=null && cloId.equals(taskOwnerId)){
							cloIsAssigned=true;
						} else{
							cloIsAssigned=false;
							// Find out if task owner is CSO
				            taskOwnerId = null;
				            CSCDStaffPosition assignedStaffPosition = myAssignment.getAssignedStaffPosition();
				            if (assignedStaffPosition != null){
				               	SupervisionCode aCode = assignedStaffPosition.getJobTitle();
				               	String jobTitle = aCode.getCode();
				               	if(jobTitle.equals(PDCodeTableConstants.STAFF_JOB_TITLE_CLO) 
		               					|| jobTitle.equals(PDCodeTableConstants.STAFF_JOB_TITLE_CLOFLOATER)){
									cloIsAssigned=true;
								} else {
									csoIsAssigned=true;
								}
				               	taskOwnerId = myAssignment.getAssignedStaffPositionId();
				          }
					}
			    }
		    }
		   //Retrieve workgroup if case is assigned to a clo
		   if(cloIsAssigned || (order.getVersionTypeId()!=null && order.getVersionTypeId().equals(PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL)) || taskOwnerId==null){
				if (this.isOutOfCountyCase(order.getCriminalCaseId())){
				    taskOwnerId = this.fetchWorkgroupByName(PDSecurityHelper.getUserAgencyId(), "IN", OOC_COURT_SERVICES_INTAKE);
			    } else {
			     	taskOwnerId = this.fetchWorkgroupByName(PDSecurityHelper.getUserAgencyId(), "IN", COURT_SERVICES_INTAKE);
			    }
		   }
		    if(taskOwnerId!=null){
			    CreateCSTaskEvent createTask = new CreateCSTaskEvent();
			    Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 1);
				createTask.setDueDate(cal.getTime());
				createTask.setStatusId("S");
				
				StringBuffer subject2 = new StringBuffer();
				subject2.append("New Active Order on case #: ")
				.append( order.getCriminalCaseId() );
				
				StringBuffer subject = new StringBuffer();
				StringBuffer text = new StringBuffer();
				if(order.getVersionTypeId()!=null && order.getVersionTypeId().equals(PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL)){
					subject.append( ORDER_FILED );
					text.append( ORDER_FILED );
					text.append( " for " );
					text.append( updateSupervisionOrderStatusEvent.getName().trim() );
					text.append( " SPN " );
					text.append( updateSupervisionOrderStatusEvent.getDefendantId() );
					text.append( ", CRT " );
					text.append( this.formatCourtNum(updateSupervisionOrderStatusEvent.getCourtId()) );
				}
				else{
					subject.append( "Version #" );
					subject.append( order.getVersionNum() );
					subject.append( " " );
					subject.append( order.getVersionType().getDescription() );
					subject.append( " Order filed" );
					if(!csoIsAssigned){
						text.append( subject.toString() );
						text.append( "	for " );
						text.append( updateSupervisionOrderStatusEvent.getName().trim() );
						text.append( " SPN " );
						text.append( updateSupervisionOrderStatusEvent.getDefendantId() );
						text.append( ", CRT " );
						text.append( this.formatCourtNum(updateSupervisionOrderStatusEvent.getCourtId()) );
					}else{
						text.append( subject.toString() );
						text.append( "	for " );
						text.append( updateSupervisionOrderStatusEvent.getName().trim() );
						text.append( " SPN " );
						text.append( updateSupervisionOrderStatusEvent.getDefendantId() );
						text.append( ", case " );
						text.append( order.getCriminalCaseId() );
						text.append( ", CRT " );
						text.append( this.formatCourtNum(updateSupervisionOrderStatusEvent.getCourtId()) );
					}
				}
				createTask.setTaskSubject( subject.toString() );
				createTask.setTastText( text.toString() );
				createTask.setDefendantId( updateSupervisionOrderStatusEvent.getDefendantId() );
				createTask.setSupervisionOrderId( updateSupervisionOrderStatusEvent.getSupervisionOrderId() );
				createTask.setCriminalCaseId( order.getCriminalCaseId() );
				createTask.setSuperviseeName( updateSupervisionOrderStatusEvent.getName() );
				createTask.setSubject2( subject2.toString() );
				createTask.setCourtId( formatCourtId( updateSupervisionOrderStatusEvent.getCourtId() ));
			     
				// Logic needed for creating a notification task
				if( caseAssignId != null )
					createTask.setCaseAssignId( caseAssignId );
				if( !csoIsAssigned ){
					createTask.setWorkGroupId( taskOwnerId );
					//Only original orders go through program unit assignment.
					if (order.getVersionTypeId().equals(PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL)){
						createTask.setTopic( CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU );
						createTask.setScenario( CaseloadConstants.ASSIGN_PROGRAM_UNIT_PAGEFLOW );
					} else {
						if( caseAssignId != null ){
							
							createTask.setTopic( CaseloadConstants.CSTASK_TOPIC_REASSIGN_CASES);
							createTask.setScenario( CaseloadConstants.REASSIGN_PROGRAM_UNIT_PAGEFLOW);
						} else {
							
							createTask.setTopic( CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU );
							createTask.setScenario( CaseloadConstants.ASSIGN_PROGRAM_UNIT_PAGEFLOW );
						}
					}
					
				}
				else{
					createTask.setTopic( CaseloadConstants.CSTASK_TOPIC_NOTIFY_SUPERVISEE_TO_OFF );
					createTask.setStaffPositionId( taskOwnerId );
					createTask.setDueDate(null);
				}
				
				// Close old tasks here... RRY
				closeOutDatedCSTasks( updateSupervisionOrderStatusEvent.getDefendantId(), order.getCriminalCaseId() );
				PDTaskHelper.createCSTask( createTask );
		    }
	}
	
	/**
	 * 
	 * @param spn
	 * @param crimCase
	 */
	private void closeOutDatedCSTasks ( String spn, String crimCase ){
		
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
	 * @param criminalCaseId
	 * @return
	 */
	private boolean isOutOfCountyCase(String criminalCaseId){
	     String cdi = criminalCaseId.substring(0,3);
	     boolean isOutOfCountyCase = false;
	     if (cdi.equals("010")){
	         isOutOfCountyCase = true;
	     }
	     return isOutOfCountyCase;
	 }
	
	
	private String formatCourtNum(String courtId){
		String courtNum = courtId;
	    if (!courtId.equals(PDConstants.BLANK) && (courtId.length()>2 )){
	         courtNum =  courtId.substring(3);
	         courtNum = PDUtil.removeLeadingZeros(courtNum);
	     } 
	    return courtNum;
	}
	
	private String formatCourtId( String courtId ){
		
		String courtNum = courtId;
		int courtNumInt;
	    if (!courtId.equals(PDConstants.BLANK) && (courtId.length()>2 )){
	    	     String crtIds [] = courtNum.split(" ");
	    	     if(crtIds != null && crtIds.length > 1){
	    	           courtNum = crtIds[1];
	    	     }
	    	     try {
	    	          courtNumInt = new Integer(courtNum);
	    	          courtNum = "" + courtNumInt;
	    	          while(courtNum.length() < 3){
	    	                courtNum = "0" + courtNum;
	    	          }
	    	     }catch(NumberFormatException e){
	    	    	 //do nothing, court number is not integer
	    	     }    
	     } 
	    return courtNum;
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
	 * @param order
	 * returns perviously active order
	 */
	private SupervisionOrder populateActivateAttributes(SupervisionOrder order) {
		//Determine if all condition details are set
		SupervisionOrder activeOrder=null;
		Date transferInDate = null;
		boolean isOutOfCountyCase = SupervisionOrderHelper.isOutOfCountyCase(order.getCriminalCaseId());
		if (SupervisionOrderHelper.isOutOfCountyCase(order.getCriminalCaseId())){
			transferInDate = SupervisionOrderHelper.getOutOfCountyTransferInDate(order.getDefendantId(), order.getCriminalCaseId());
		}
		boolean conditionDetailsAreSet = this.validateConditionDetails(order);
		if (!conditionDetailsAreSet 
				|| (isOutOfCountyCase && transferInDate == null)) {
			ActivateSupervisionOrderErrorEvent errorEvent = new ActivateSupervisionOrderErrorEvent();
			if (conditionDetailsAreSet 
					&& isOutOfCountyCase 
					&& transferInDate == null){
				errorEvent.setOocTransferInDateMissing(true);
			}
			IDispatch dispatch = EventManager
					.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errorEvent);
			order = null;
		} else {

			// Set any previous active order to inactive
			GetMostCurrentSupervisionOrderForCaseEvent theEvent = new GetMostCurrentSupervisionOrderForCaseEvent();
			theEvent.setAgencyId(order.getAgencyId());
			theEvent.setCaseId(order.getCriminalCaseId());
			theEvent.setOrderStatusId(PDCodeTableConstants.STATUS_ACTIVE_ID);
			activeOrder = this.getSupervisionOrder(theEvent);
			if (activeOrder != null) {
				// get all the attributes of Order
				activeOrder = SupervisionOrder.find(activeOrder.getOID());
				activeOrder.setOrderStatusId(PDCodeTableConstants.STATUS_INACTIVE_ID);
				activeOrder.setInactivationDate(new Date());
				activeOrder.setOrderFiledDate(activeOrder.getInactivationDate());
				activeOrder.bind();
				if(SupervisionConstants.ORIGINAL.equals(order.getVersionTypeId())){
					order.setOrderChainNum(activeOrder.getOrderChainNum()+1); // set the order chain value one level higher
					OrderVersionSequence orderVersionSeq = new OrderVersionSequence();
					orderVersionSeq.setTheCaseId(order.getCriminalCaseId());
					orderVersionSeq.setVersionTypeId(order.getVersionTypeId());
					orderVersionSeq.setOrderChainNum(order.getOrderChainNum());
					orderVersionSeq.setSeqNum(1);
				}
				else{  // at this point we know the new order is not an original and we know that that there is a previous active order
					// We are going to keep all the conditions that are the same between the old active and the new active compliance counters replicated
					Collection soconditionRels = order.getSupervisionOrderConditionRels();
					Collection activeSoConditionRels=activeOrder.getSupervisionOrderConditionRels();
					if (soconditionRels != null && activeSoConditionRels!=null) {
						Iterator iter = soconditionRels.iterator();
						SupervisionOrderConditionRel soCondRel = null;
						SupervisionOrderConditionRel activeSoCondRel = null;
						while (iter.hasNext()) {
							soCondRel = (SupervisionOrderConditionRel) iter.next();
							Iterator activeIter=null;
							activeIter=activeSoConditionRels.iterator();
							while(activeIter.hasNext()){
								activeSoCondRel=(SupervisionOrderConditionRel) activeIter.next();
								if(activeSoCondRel !=null && soCondRel!=null){
									if(activeSoCondRel.getSupervisionOrderCondition()!=null && soCondRel.getSupervisionOrderCondition()!=null){
										if(activeSoCondRel.getSupervisionOrderCondition().getConditionId().equals(soCondRel.getSupervisionOrderCondition().getConditionId())){
											soCondRel.setComplianceReasonId(activeSoCondRel.getComplianceReasonId());
											soCondRel.setNcCount(activeSoCondRel.getNcCount());
											soCondRel.setNonCompliant(activeSoCondRel.isNonCompliant());
											Iterator iterNCitems=NonComplianceEventCasenote.findAllByNumericParam("sprOrderConditionId",activeSoCondRel.getSupervisionOrderConditionId());
											if(iterNCitems!=null){
												while(iterNCitems.hasNext()){
													NonComplianceEventCasenote myNCEvent=(NonComplianceEventCasenote)iterNCitems.next();
													myNCEvent.setSprOrderConditionId(Integer.parseInt(soCondRel.getSupervisionOrderConditionId()));
												}
											}
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			if (isOutOfCountyCase){
				order.setTransferInDate(transferInDate);
				order.setActivationDate(transferInDate);
			} else {
				order.setActivationDate(new Date());
			}
			order.setOrderFiledDate(new Date());

			//It won't hurt anything if original judge name is populated for all order types.
			if (order.getOrigJudgeLastName() == null
					|| order.getOrigJudgeLastName().equals(PDConstants.BLANK)){
				order.setOrigJudgeFirstName(order.getSignedByFirstName());
				order.setOrigJudgeLastName(order.getSignedByLastName());
			} 
		
			order.setOrderStatusId(PDCodeTableConstants.STATUS_ACTIVE_ID);
			order.setOrderInProgress(false);
			this.setSupervisionPeriod(order);
			String modReason = order.getModificationReason();
			if (modReason != null && !modReason.equals("") && !order.getIsHistoricalOrder()) {
				this.createCasenote(order, modReason);
				//Don't remember why I was blanking out modification reason. Is needed for display in view order versions.
				//order.setModificationReason("");
			}
		}
		return order;
	}

	private void updateCaseAssignment(String criminalCaseId, String newOrderId) {
		Iterator iter = CaseAssignment.findAll("criminalCaseId", criminalCaseId);
		if (iter != null && iter.hasNext()){
			CaseAssignment caseAssignment = (CaseAssignment) iter.next();
			caseAssignment.setSupervisionOrderId(newOrderId);
			caseAssignment.setTerminationDate(null);
		}
	}

	/**
	 * @param order
	 * @param notes
	 */
	private void createCasenote(SupervisionOrder order, String notes) {

		Casenote.createOrderCasenote(order.getAgencyId(),
				order.getOID().toString(), order.getDefendantId(), order.getSupervisionPeriodId(), notes);
	}

	/**
	 * @param order
	 */
	private void setSupervisionPeriod(SupervisionOrder order) {
		SupervisionPeriod supervisionPeriod = SupervisionPeriod
				.findActiveSupervisionPeriod(order.getDefendantId(), order
						.getAgencyId());

		// if not present, create one
		if (supervisionPeriod == null) {
			supervisionPeriod = SupervisionPeriod.create(order);
		} else {// use this one
			//Reset begin date if orderActivation date falls before current supervision period begin date.
			//HC Cases - supervision begin date will always be case supervision begin date
			//OOC Cases - supervision begin date will be transfer in date.
			Date newOrderBeginDate = null;
			if (SupervisionOrderHelper.isOutOfCountyCase(order.getCriminalCaseId())){
				newOrderBeginDate = order.getTransferInDate();
			} else {
				newOrderBeginDate = order.getCaseSupervisionBeginDate();
			}
			if (newOrderBeginDate.before(supervisionPeriod.getSupervisionBeginDate())){
				supervisionPeriod.setSupervisionBeginDate(newOrderBeginDate);
			} else if (newOrderBeginDate.after(supervisionPeriod.getSupervisionBeginDate())){
				//Read through active orders to determine if supervisionPeriodBeginDate should
				//be changed to later date.
				List activeOrders = SupervisionOrderHelper.getActiveSupervisionOrders(order.getDefendantId(), order.getAgencyId());
				SupervisionOrder activeOrder = null;
				boolean isNoEarlierOrder = true;
				for (int i = 0; i < activeOrders.size(); i++) {
					activeOrder = (SupervisionOrder) activeOrders.get(i);
					Date activeOrderBegDate = null;
					if (SupervisionOrderHelper.isOutOfCountyCase(activeOrder.getCriminalCaseId())){
						if (activeOrder.getTransferInDate() != null){
							activeOrderBegDate = activeOrder.getTransferInDate();
						} else {
							activeOrderBegDate = activeOrder.getCaseSupervisionBeginDate();
						}
					} else {
						activeOrderBegDate = activeOrder.getCaseSupervisionBeginDate();
					}
					if (activeOrderBegDate.before(newOrderBeginDate)){
						//There is another order with an earlier activation date.
						isNoEarlierOrder = false;
						break;
					} 
				}
				if (isNoEarlierOrder){
					//Change sp begin date to later date when there are no orders activated 
					//prior to the order being activated.
					supervisionPeriod.setSupervisionBeginDate(newOrderBeginDate);
				}
			} else {
				//No Change to supervisionPeriodBeginDate
			}
		}
		order.setSupervisionPeriod(supervisionPeriod);
	}

	/**
	 * @param updateObject
	 * @roseuid 43B2E757007D
	 */
	public void update(Object updateObject) {

	}

	/**
	 * Determines whether condition details have been set for every variable
	 * element.
	 * 
	 * @param order
	 * @return
	 */
	private boolean validateConditionDetails(SupervisionOrder order) {
		Collection soconditionRels = order.getSupervisionOrderConditionRels();
		boolean conditionDetailsAreSet = true;
		if (soconditionRels != null) {
			Iterator iter = soconditionRels.iterator();
			SupervisionOrderConditionRel soCondRel = null;
			SupervisionOrderConditionRelValue soCondRelVal = null;
			Collection soCondRelValues = null;
			Iterator iter2 = null;
			VariableElementType variableElementType = null;
			while (iter.hasNext()) {
				soCondRel = (SupervisionOrderConditionRel) iter.next();
				soCondRelValues = soCondRel.getOrderConditionRelValues();
				if (soCondRelValues != null) {
					iter2 = soCondRelValues.iterator();
					while (iter2.hasNext()) {
						soCondRelVal = (SupervisionOrderConditionRelValue) iter2
								.next();
						variableElementType = soCondRelVal
								.getVariableElementType();
						if (!variableElementType.isReference()
								&& (soCondRelVal.getValue() == null || soCondRelVal
										.getValue().equals(""))) {
							if ((!variableElementType.getName().equals(UIConstants.VARIABLE_ELEMENT_NAME_COMMENTS))&& (!variableElementType.getName().equals(UIConstants.VARIABLE_ELEMENT_NAME_CLAIMAINT_ADDRESS))){
								conditionDetailsAreSet = false;
								break;
							}
						}
					}
				}
				if (!conditionDetailsAreSet) {
					break;
				}
			}
		}
		return conditionDetailsAreSet;
	}
	/**
	 * Create Supervisee if one does not yet exist.
	 * @param order
	 */
	private void createSupervisee(SupervisionOrder order) {
		
		Supervisee supervisee = Supervisee.findByDefendantId(order.getDefendantId());

		if (supervisee == null){
			supervisee = new Supervisee();
			supervisee.setDefendantId(order.getDefendantId());
			supervisee.setCurrentlySupervised(false);
			supervisee.setZipCode(this.getZipCode(order.getCriminalCaseId()));
			new Home().bind(supervisee); //get the new oid.
			SuperviseeHistory history = CaseAssignmentHelper.updateSuperviseeHistory(supervisee, CaseloadConstants.SUPERVISEE_HISTORY_CREATE);
			supervisee.insertHistory(history);
		} else {
			String zipCode = this.getZipCode(order.getCriminalCaseId());
			if (!zipCode.equals(supervisee.getZipCode())){
				supervisee.setZipCode(zipCode);
				SuperviseeHistory history = CaseAssignmentHelper.updateSuperviseeHistory(supervisee, CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_ZIP);
				supervisee.insertHistory(history);
			}
		}
	}
	private String getZipCode(String criminalCaseId){
		CriminalCase criminalCase = CriminalCase.find(criminalCaseId);
		String zipCode = "";
		if (criminalCase != null){
			zipCode = criminalCase.getZipCode();
		}
		return zipCode;
	}
}

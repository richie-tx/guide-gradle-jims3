//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionorder\\transactions\\UpdateSuperviseeSignatureCommand.java

package pd.supervision.supervisionorder.transactions;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;

import naming.CaseloadConstants;
import naming.PDCodeTableConstants;
import pd.supervision.managetask.PDTaskHelper;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import pd.supervision.supervisionstaff.cscdstaffposition.CourtStaffPosition;
import messaging.cscdstaffposition.GetCourtStaffPositionEvent;
import messaging.managetask.CreateCSTaskEvent;
import messaging.supervisionorder.UpdateSuperviseeSignatureEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author dphuyal
 *
 */
public class UpdateSuperviseeSignatureCommand implements ICommand 
{
   
   /**
    * @roseuid 4421684B01C2
    */
   public UpdateSuperviseeSignatureCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 442071B500D1
    */
   public void execute(IEvent event) throws IOException
   {
		UpdateSuperviseeSignatureEvent updateSigEvent = (UpdateSuperviseeSignatureEvent) event;
		SupervisionOrder order = SupervisionOrder.find(updateSigEvent.getSupervisionOrderId());
		if(order != null)
		{
			order.setOrderSignedByDefendant(updateSigEvent.isDefendantSignatureInd());
			order.setOrderSignedByDefendantDate(updateSigEvent.getSignedDate());
			order.setOrderSignedByJudgeDate(updateSigEvent.getJudgeSignedDate());
			//ER 56231
			// SEND task to CLO if person executing supervisee sig is not a CLO or CLO Floater.
			//Task generated from defendant signature required will be conditional on the user NOT being a 
			//CLO or CLO Floater.  

//			if(order.getOrderStatusId()!=null && order.getOrderStatusId().equals(PDCodeTableConstants.STATUS_PENDING_ID)){
//				if(order.getVersionTypeId()!=null 
//						&& (order.getVersionTypeId().equals(PDCodeTableConstants.VERSION_TYPE_ID_AMMENDED) 
//								|| order.getVersionTypeId().equals(PDCodeTableConstants.VERSION_TYPE_ID_MODIFIED))){
					// ER 56231 - checking if user is not CLO or CLO Floater if so send out task to CLO 
					String cloForCourtId = null;
					if(!updateSigEvent.isUserIsCLO()){
						cloForCourtId = this.getCLOForCourt(order.getOrderCourtId());
					}
					//if(!updateSigEvent.isUserIsCLO() && cloForCourtId != null && !cloForCourtId.equals("")){
						CreateCSTaskEvent createTask = new CreateCSTaskEvent();
					    Calendar cal = Calendar.getInstance();
						cal.add(Calendar.DATE, 5);
						createTask.setDueDate(cal.getTime());
						createTask.setStatusId("O");
						
						String subject;
						String text=null;
						subject="Order modification from field for " + order.getDefendantId();
						StringBuffer myBuf = new StringBuffer();

						if( order.getDefendant()!= null && order.getDefendant().getLastName()!= null){
							myBuf.append(order.getDefendant().getLastName());
						}
						myBuf.append(", ");
						myBuf.append(order.getDefendantId());
						myBuf.append(", ");
						myBuf.append(order.getOrderCourtId());
						text=myBuf.toString();
						createTask.setTaskSubject(subject);
						createTask.setTastText( text );
						createTask.setDefendantId( order.getDefendantId() );
						createTask.setSupervisionOrderId( order.getOID() );
						createTask.setCriminalCaseId( order.getCriminalCaseId() );
						createTask.setTopic(CaseloadConstants.CSTASK_TOPIC_NOTIFY_CLO_PROGRESS_ORDER);
						createTask.setStaffPositionId( cloForCourtId );
				     	//ER#56231 - remove next task action
						//createTask.addTaskStateItem(CaseloadConstants.SCENARIO, CaseloadConstants.CLO_PROGRESS_ORDER_PAGEFLOW );
						createTask.setSuperviseeName( order.getPrintedName() );
						
						PDTaskHelper.createCSTask( createTask );
							
				//}
			}
		}
   
   private String getCLOForCourt(String orderCourtId) {
		String cloId=null;
		GetCourtStaffPositionEvent requestEvent = new GetCourtStaffPositionEvent();
		requestEvent.setCourtId(orderCourtId);
		requestEvent.setJobTitleId(PDCodeTableConstants.STAFF_JOB_TITLE_CLO);
		Iterator staffPositionIter = CSCDStaffPositionHelper.getCourtStaffPositions(requestEvent);
		if (staffPositionIter != null) {
			CourtStaffPosition staff=null;
			if (staffPositionIter.hasNext()) { // only one CLO per court supposedly
				staff = (CourtStaffPosition) staffPositionIter.next();
				if(staff!=null){
					cloId=staff.getStaffPositionId();
					
				}
			}
		}
		return cloId;
	}


/**
    * @param event
    * @roseuid 442071B500D3
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 442071B500E1
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    * @roseuid 4421684B01E1
    */
   public void update(Object updateObject) 
   {
    
   }
}

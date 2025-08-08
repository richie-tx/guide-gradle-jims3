package pd.productionsupport.transactions;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralsEvent;
import messaging.productionsupport.RetrieveJuvenileProgramReferralByReferralNumberEvent;
import messaging.productionsupport.RetrieveJuvenileProgramReferralsEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import pd.supervision.programreferral.JuvenileProgramReferral;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;

public class GetProductionSupportJuvenileProgramReferralsCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportJuvenileProgramReferralsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetProductionSupportJuvenileProgramReferralsEvent getJuvenileReferralsEvent = (GetProductionSupportJuvenileProgramReferralsEvent) event;
		
		if(getJuvenileReferralsEvent.getCasefileId() != null && getJuvenileReferralsEvent.getCasefileId().length() > 0){
			RetrieveJuvenileProgramReferralsEvent retrievalEvent = new RetrieveJuvenileProgramReferralsEvent();
			retrievalEvent.setCasefileId(getJuvenileReferralsEvent.getCasefileId());
			Iterator juvenileReferralsIter = JuvenileProgramReferral.findAll(retrievalEvent);

			while(juvenileReferralsIter != null && juvenileReferralsIter.hasNext()){
				JuvenileProgramReferral juvenileProgramReferralResponse = (JuvenileProgramReferral)juvenileReferralsIter.next();	
				ProductionSupportJuvenileProgramReferralResponseEvent juvenileReferralResponseEvent = new ProductionSupportJuvenileProgramReferralResponseEvent();

				juvenileReferralResponseEvent.setJuvenileProgramReferralId(juvenileProgramReferralResponse.getOID());
				juvenileReferralResponseEvent.setAckDate(juvenileProgramReferralResponse.getAcknowledgementDate());
				juvenileReferralResponseEvent.setBeginDate(juvenileProgramReferralResponse.getBeginDate());
				juvenileReferralResponseEvent.setEndDate(juvenileProgramReferralResponse.getEndDate());
				juvenileReferralResponseEvent.setAssignedHours(juvenileProgramReferralResponse.getAssignedHours());
				juvenileReferralResponseEvent.setSentDate(juvenileProgramReferralResponse.getSentDate());
				juvenileReferralResponseEvent.setStatusCd(juvenileProgramReferralResponse.getReferralStatusCd());
				juvenileReferralResponseEvent.setStatusSubCd(juvenileProgramReferralResponse.getReferralSubStatusCd());
				juvenileReferralResponseEvent.setOutcomeCd(juvenileProgramReferralResponse.getProgramOutcomeCd());
				juvenileReferralResponseEvent.setProviderProgramId(juvenileProgramReferralResponse.getProvProgramId());
				juvenileReferralResponseEvent.setLastActionDate(juvenileProgramReferralResponse.getLastActionDate());
				juvenileReferralResponseEvent.setControllingReferralNum(juvenileProgramReferralResponse.getControllingReferralNum());
				juvenileReferralResponseEvent.setFundSource(juvenileProgramReferralResponse.getFundSource());
				
				if(juvenileProgramReferralResponse.getCreateUserID() != null){
					juvenileReferralResponseEvent.setCreateUserID(juvenileProgramReferralResponse.getCreateUserID());
				}
				if(juvenileProgramReferralResponse.getCreateTimestamp() != null){
					juvenileReferralResponseEvent.setCreateDate(new Date(juvenileProgramReferralResponse.getCreateTimestamp().getTime()));
				}
				if(juvenileProgramReferralResponse.getUpdateUserID() != null){
					juvenileReferralResponseEvent.setUpdateUser(juvenileProgramReferralResponse.getUpdateUserID());
				}
				if(juvenileProgramReferralResponse.getUpdateTimestamp() != null){
					juvenileReferralResponseEvent.setUpdateDate(new Date(juvenileProgramReferralResponse.getUpdateTimestamp().getTime()));
				}
				if(juvenileProgramReferralResponse.getCreateJIMS2UserID() != null){
					juvenileReferralResponseEvent.setCreateJIMS2UserID(juvenileProgramReferralResponse.getCreateJIMS2UserID());
				}
				if(juvenileProgramReferralResponse.getUpdateJIMS2UserID() != null){
					juvenileReferralResponseEvent.setUpdateJIMS2UserID(juvenileProgramReferralResponse.getUpdateJIMS2UserID());
				}
				
				dispatch.postEvent(juvenileReferralResponseEvent);
			}
		}else if (getJuvenileReferralsEvent.getReferralNum() != null 
				&& getJuvenileReferralsEvent.getReferralNum().length() > 0
				&& ! getJuvenileReferralsEvent.getIsFromCSJUVPROGREF() ){
			ProductionSupportJuvenileProgramReferralResponseEvent juvenileReferralResponseEvent = new ProductionSupportJuvenileProgramReferralResponseEvent();
			RetrieveJuvenileProgramReferralByReferralNumberEvent requestEvent = new RetrieveJuvenileProgramReferralByReferralNumberEvent();
			requestEvent.setReferralNum(getJuvenileReferralsEvent.getReferralNum());
			Iterator  juvenileProgramReferralIter = JuvenileProgramReferral.findAll(requestEvent);
			JuvenileProgramReferral juvenileProgramReferralRecord = new JuvenileProgramReferral();
			while(juvenileProgramReferralIter.hasNext()){
				juvenileProgramReferralRecord = (JuvenileProgramReferral)juvenileProgramReferralIter.next();

				juvenileReferralResponseEvent.setJuvenileProgramReferralId(juvenileProgramReferralRecord.getOID());
				juvenileReferralResponseEvent.setCaseFileId(juvenileProgramReferralRecord.getCasefileId());
				juvenileReferralResponseEvent.setAckDate(juvenileProgramReferralRecord.getAcknowledgementDate());
				juvenileReferralResponseEvent.setBeginDate(juvenileProgramReferralRecord.getBeginDate());
				juvenileReferralResponseEvent.setEndDate(juvenileProgramReferralRecord.getEndDate());
				juvenileReferralResponseEvent.setAssignedHours(juvenileProgramReferralRecord.getAssignedHours());
				juvenileReferralResponseEvent.setSentDate(juvenileProgramReferralRecord.getSentDate());
				juvenileReferralResponseEvent.setStatusCd(juvenileProgramReferralRecord.getReferralStatusCd());
				juvenileReferralResponseEvent.setStatusSubCd(juvenileProgramReferralRecord.getReferralSubStatusCd());
				juvenileReferralResponseEvent.setOutcomeCd(juvenileProgramReferralRecord.getProgramOutcomeCd());
				juvenileReferralResponseEvent.setOutcomeDescCd(juvenileProgramReferralRecord.getProgramOutcomeSubcategoryCd());
				juvenileReferralResponseEvent.setProviderProgramId(juvenileProgramReferralRecord.getProvProgramId());
				juvenileReferralResponseEvent.setLastActionDate(juvenileProgramReferralRecord.getLastActionDate());
				juvenileReferralResponseEvent.setControllingReferralNum(juvenileProgramReferralRecord.getControllingReferralNum());
				juvenileReferralResponseEvent.setFundSource(juvenileProgramReferralRecord.getFundSource());
				
				if(juvenileProgramReferralRecord.getCreateUserID() != null){
					juvenileReferralResponseEvent.setCreateUserID(juvenileProgramReferralRecord.getCreateUserID());
				}
				if(juvenileProgramReferralRecord.getCreateTimestamp() != null){
					juvenileReferralResponseEvent.setCreateDate(new Date(juvenileProgramReferralRecord.getCreateTimestamp().getTime()));
				}
				if(juvenileProgramReferralRecord.getUpdateUserID() != null){
					juvenileReferralResponseEvent.setUpdateUser(juvenileProgramReferralRecord.getUpdateUserID());
				}
				if(juvenileProgramReferralRecord.getUpdateTimestamp() != null){
					juvenileReferralResponseEvent.setUpdateDate(new Date(juvenileProgramReferralRecord.getUpdateTimestamp().getTime()));
				}
				if(juvenileProgramReferralRecord.getCreateJIMS2UserID() != null){
					juvenileReferralResponseEvent.setCreateJIMS2UserID(juvenileProgramReferralRecord.getCreateJIMS2UserID());
				}
				if(juvenileProgramReferralRecord.getUpdateJIMS2UserID() != null){
					juvenileReferralResponseEvent.setUpdateJIMS2UserID(juvenileProgramReferralRecord.getUpdateJIMS2UserID());
				}
				List<JuvenileProgramReferralAssignmentHistory> programRefAssignmentHistList = CollectionUtil.iteratorToList( JuvenileProgramReferralAssignmentHistory.findAll("programReferralId", juvenileProgramReferralRecord.getOID()));
				Collections.sort(programRefAssignmentHistList, new Comparator<JuvenileProgramReferralAssignmentHistory>(){
				    @Override
				    public int compare(JuvenileProgramReferralAssignmentHistory h1, JuvenileProgramReferralAssignmentHistory h2 ){
					return h2.getCreateTimestamp().compareTo(h1.getCreateTimestamp());
				    }
				});
				
				
				if ( programRefAssignmentHistList != null
					&& programRefAssignmentHistList.size() > 0) {
				    JuvenileProgramReferralAssignmentHistory programRefAssignmentHist = programRefAssignmentHistList.get(0) ;
				    //System.out.println("ProgramReferralAssignmentDate: " + programRefAssignmentHist.getProgramReferralAssignmentDate() );
				    juvenileReferralResponseEvent.setProgramReferralAssignmentDate(programRefAssignmentHist.getProgramReferralAssignmentDate());
				    juvenileReferralResponseEvent.setProgramReferralAssignmentId(programRefAssignmentHist.getOID());
				}
				dispatch.postEvent(juvenileReferralResponseEvent);
			}
		} else if ( getJuvenileReferralsEvent.getReferralNum() != null 
				&& getJuvenileReferralsEvent.getReferralNum().length() > 0
				&& getJuvenileReferralsEvent.getIsFromCSJUVPROGREF() ) {
		    ProductionSupportJuvenileProgramReferralResponseEvent juvenileReferralResponseEvent = new ProductionSupportJuvenileProgramReferralResponseEvent();
		    JuvenileProgramReferral juvenileProgramReferralRecord = JuvenileProgramReferral.find(getJuvenileReferralsEvent.getReferralNum());
		    juvenileReferralResponseEvent.setJuvenileNum( juvenileProgramReferralRecord.getJuvenileId() );
		    dispatch.postEvent(juvenileReferralResponseEvent);
		}

	}

	/**
	 * @param event
	 * @roseuid 4278C7B8034F
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80359
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4278C7B80364
	 */
	public void update(Object anObject) {

	}
}

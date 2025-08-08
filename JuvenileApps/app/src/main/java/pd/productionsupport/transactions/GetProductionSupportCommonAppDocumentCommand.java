package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.casefile.reply.CommonAppDocResponseEvent;
import messaging.productionsupport.GetProductionSupportBenefitAssessmentsEvent;
import messaging.productionsupport.GetProductionSupportCommonAppDocumentEvent;
import messaging.productionsupport.reply.ProductionSupportBenefitsAssessmentsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.casefile.CommonAppDocMetadata;
import pd.juvenilecase.family.BenefitsAssessment;

public class GetProductionSupportCommonAppDocumentCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportCommonAppDocumentCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		
		System.out.println("GetProductionSupportCommonAppDocumentCommand");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportCommonAppDocumentEvent getCommonAppEvent = (GetProductionSupportCommonAppDocumentEvent) event;
		Iterator commonAppMetadataIter = CommonAppDocMetadata.findAll("casefileId",getCommonAppEvent.getCasefileId());
		
		while(commonAppMetadataIter != null && commonAppMetadataIter.hasNext()){
			CommonAppDocMetadata commonAppMetadata = (CommonAppDocMetadata)commonAppMetadataIter.next();	
			CommonAppDocResponseEvent commonAppMetadataResponseEvent = new CommonAppDocResponseEvent();
			
			commonAppMetadataResponseEvent.setCommonAppDocId(commonAppMetadata.getOID());
			commonAppMetadataResponseEvent.setDocTypeCd(commonAppMetadata.getDocTypeCd());

			
			if(commonAppMetadata.getCreateUserID() != null){
				commonAppMetadataResponseEvent.setCreateUserID(commonAppMetadata.getCreateUserID());
			}
			if(commonAppMetadata.getCreateTimestamp() != null){
				commonAppMetadataResponseEvent.setCreateDate(new Date(commonAppMetadata.getCreateTimestamp().getTime()));
			}
			if(commonAppMetadata.getUpdateUserID() != null){
				commonAppMetadataResponseEvent.setUpdateUser(commonAppMetadata.getUpdateUserID());
			}
			if(commonAppMetadata.getUpdateTimestamp() != null){
				commonAppMetadataResponseEvent.setUpdateDate(new Date(commonAppMetadata.getUpdateTimestamp().getTime()));
			}
			if(commonAppMetadata.getCreateJIMS2UserID() != null){
				commonAppMetadataResponseEvent.setCreateJIMS2UserID(commonAppMetadata.getCreateJIMS2UserID());
			}
			if(commonAppMetadata.getUpdateJIMS2UserID() != null){
				commonAppMetadataResponseEvent.setUpdateJIMS2UserID(commonAppMetadata.getUpdateJIMS2UserID());
			}
			
			dispatch.postEvent(commonAppMetadataResponseEvent);
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

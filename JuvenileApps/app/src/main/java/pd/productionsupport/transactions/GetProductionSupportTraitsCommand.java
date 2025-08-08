package pd.productionsupport.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.productionsupport.GetProductionSupportTraitsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileTrait;

public class GetProductionSupportTraitsCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportTraitsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		boolean isValidData=false;
		GetProductionSupportTraitsEvent getTraitsEvent = (GetProductionSupportTraitsEvent) event;
		Iterator traitsIter = null;
		if(getTraitsEvent.getCasefileId() != null && !getTraitsEvent.getCasefileId().equals("")){
			traitsIter = JuvenileTrait.findAllByAttributeName("supervisionNum", getTraitsEvent.getCasefileId());
			isValidData=true;
		}else if(getTraitsEvent.getJuvenileId() != null && !getTraitsEvent.getJuvenileId().equals("")){
			traitsIter = JuvenileTrait.findAllByAttributeName("juvenileNum", getTraitsEvent.getJuvenileId());
			isValidData=true;
		}
		else if(getTraitsEvent.getTraitId() != null && !getTraitsEvent.getTraitId().equals("")){
			ArrayList<JuvenileTrait> traitList = new ArrayList<JuvenileTrait>();
			JuvenileTrait trait = JuvenileTrait.find(getTraitsEvent.getTraitId());
			traitList.add(trait);
			traitsIter = traitList.iterator();		
			isValidData=true;
		}
		if(isValidData){
			while(traitsIter != null && traitsIter.hasNext()){
				JuvenileTrait trait = (JuvenileTrait)traitsIter.next();	
				if(trait != null){
					JuvenileTraitResponseEvent traitResponseEvent = new JuvenileTraitResponseEvent();
					
					traitResponseEvent.setDispositionNum(trait.getDispositionNum());
					traitResponseEvent.setJuvenileNum(trait.getJuvenileNum());
					traitResponseEvent.setJuvenileTraitId(trait.getTraitId());
					traitResponseEvent.setStatusId(trait.getStatusId());
					if(trait.getStatus() != null && trait.getStatus().getDescription() != null){
						traitResponseEvent.setStatus(trait.getStatus().getDescription());
					}
					traitResponseEvent.setSupervisionNum(trait.getSupervisionNum());
					traitResponseEvent.setTraitComments(trait.getComments());
					traitResponseEvent.setTraitTypeId(trait.getTraitTypeId());
					traitResponseEvent.setTraitTypeName(trait.getTraitName());
					if(trait.getTraitType() != null && trait.getTraitType().getName() != null){
						traitResponseEvent.setTraitTypeDescription(trait.getTraitType().getName());
					}
					
					//production support 
					if(trait.getCreateUserID() != null){
						traitResponseEvent.setCreateUserID(trait.getCreateUserID());
					}
					if(trait.getCreatedDate() != null){
						traitResponseEvent.setCreateDate(new Date(trait.getCreatedDate().getTime()));
					}
					if(trait.getUpdateUserID() != null){
						traitResponseEvent.setUpdateUser(trait.getUpdateUserID());
					}
					if(trait.getUpdateTimestamp() != null){
						traitResponseEvent.setUpdateDate(new Date(trait.getUpdateTimestamp().getTime()));
					}
					if(trait.getCreateJIMS2UserID() != null){
						traitResponseEvent.setCreateJIMS2UserID(trait.getCreateJIMS2UserID());
					}
					if(trait.getUpdateJIMS2UserID() != null){
						traitResponseEvent.setUpdateJIMS2UserID(trait.getUpdateJIMS2UserID());
					}
					
					dispatch.postEvent(traitResponseEvent);				
				}
			}
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

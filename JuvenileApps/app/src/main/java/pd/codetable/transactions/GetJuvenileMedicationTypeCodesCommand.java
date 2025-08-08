/*
 * Created on May 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable.transactions;

import java.util.Iterator;

import naming.PDConstants;

import messaging.codetable.GetJuvenileMedicationTypeCodesEvent;
import messaging.codetable.criminal.reply.JuvenileMedicationTypeResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.criminal.JuvenileMedicationTypeCode;
import pd.supervision.administerserviceprovider.administerlocation.Location;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GetJuvenileMedicationTypeCodesCommand implements ICommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetJuvenileMedicationTypeCodesEvent reqEvent = (GetJuvenileMedicationTypeCodesEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		IHome home = new Home();
		MetaDataResponseEvent metaData = (MetaDataResponseEvent) home.findMeta(event, JuvenileMedicationTypeCode.class);
		 
		int totalRecords = metaData.getCount();
		 
		if (totalRecords > PDConstants.SEARCH_LIMIT){
			ErrorResponseEvent errorResp = new ErrorResponseEvent();
			errorResp.setMessage("error.max.limit.exceeded");			
			//IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errorResp);
		}
		else {
		
				// To fetch medication code description based upon the medication code.
				if (reqEvent.isFlagfind()) {
					//JuvenileMedicationTypeCode medicationCode = JuvenileMedicationTypeCode.find(reqEvent.getMedicationTypeId());
					Iterator iter = JuvenileMedicationTypeCode.findAll("medicationTypeId",reqEvent.getMedicationTypeId());
					while (iter.hasNext()) {
						JuvenileMedicationTypeCode medicationCode = (JuvenileMedicationTypeCode) iter.next();
						JuvenileMedicationTypeResponseEvent respEvent = new JuvenileMedicationTypeResponseEvent();
						respEvent.setMedication(medicationCode.getMedication());
						respEvent.setTradeName(medicationCode.getTradeName()); 
						respEvent.setMedicationTypeId(medicationCode.getMedicationTypeId());
						dispatch.postEvent(respEvent);
					}
				}
				
				// To fetch all the medication codes depending upon the search criteria.
				else {
					Iterator iter = JuvenileMedicationTypeCode.findAll(reqEvent);
					while (iter.hasNext()) {
						JuvenileMedicationTypeCode medicationTypeCode = (JuvenileMedicationTypeCode) iter.next();
						JuvenileMedicationTypeResponseEvent respEvent = new JuvenileMedicationTypeResponseEvent();
						respEvent.setMedication(medicationTypeCode.getMedication());
						respEvent.setTradeName(medicationTypeCode.getTradeName());
						respEvent.setDosageAdmin(medicationTypeCode.getDosageAdmin());
						respEvent.setStrength(medicationTypeCode.getStrength());
						respEvent.setUsage(medicationTypeCode.getUsage());
						respEvent.setIngredient(medicationTypeCode.getIngredient());
						respEvent.setCategoryId(medicationTypeCode.getCategoryId());
						respEvent.setMedicationTypeId(medicationTypeCode.getMedicationTypeId());
						dispatch.postEvent(respEvent);
					}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}

}

//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administercontract\\transactions\\GetContractsCommand.java

package pd.supervision.administerserviceprovider.administercontract.transactions;

import java.util.Iterator;

import messaging.administercontract.GetContractsEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.ResponseLocatorConstants;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.exception.ReflectionException;
import pd.supervision.administerserviceprovider.administercontract.Contract;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetContractsCommand implements ICommand 
{
   
   /**
    * @roseuid 451C4F6F029B
    */
   public GetContractsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C0A660209
    */
   public void execute(IEvent event) 
   {
	   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   	GetContractsEvent cEvent = (GetContractsEvent) event;

		ResponseContextFactory respFac = new ResponseContextFactory();
		ResponseCreator aCreator =  null;
   		try {
			aCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.CONTRACT_RESPONSE_LOCATOR);
		} catch (ReflectionException e) {
			e.printStackTrace();
		}
		IHome home = new Home();
		MetaDataResponseEvent metaData = (MetaDataResponseEvent) home.findMeta(cEvent, Contract.class);
		int totalRecords = metaData.getCount();
		if (totalRecords>2000){
			ErrorResponseEvent errorResp = new ErrorResponseEvent();
			errorResp.setMessage("error.max.limit.exceeded");			
			dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errorResp);
		}
		else { 
			Iterator iter = Contract.findAll(cEvent);
			while(iter.hasNext()){
				Contract contract = (Contract) iter.next();
				ContractResponseEvent resp = null;
				if(contract != null){
					resp = (ContractResponseEvent) aCreator.create(contract);
	  			    dispatch.postEvent(resp);
				}
			}
		}
   }

/**
    * @param event
    * @roseuid 451C0A660216
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C0A660218
    */
   public void onUnregister(IEvent event) 
   {
    
   }

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub
		
	}
}

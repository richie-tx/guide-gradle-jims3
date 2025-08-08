//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administercontract\\transactions\\GetContractsCommand.java

package pd.supervision.administerserviceprovider.administercontract.transactions;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

import messaging.administercontract.GetContractsEvent;
import messaging.administercontract.GetServiceProviderContractsEvent;
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
import pd.supervision.administerserviceprovider.administercontract.ServiceContractValue;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetServiceProviderContractsCommand implements ICommand 
{
   
   /**
    * @roseuid 451C4F6F029B
    */
   public GetServiceProviderContractsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C0A660209
    */
   public void execute(IEvent event) 
   {
	   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   	GetServiceProviderContractsEvent sEvent = (GetServiceProviderContractsEvent) event;
	   	GetContractsEvent cEvent = this.prepareGetContractsEvent(sEvent);

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
			HashMap map = new HashMap();
			while(iter.hasNext()){
				Contract contract = (Contract) iter.next();
				ContractResponseEvent resp = null;
				if(contract != null){
					resp = (ContractResponseEvent) aCreator.create(contract);
					double availableContractValue = contract.getTotalValue().doubleValue();
					Iterator valIter = ServiceContractValue.findAll("contractId",contract.getOID().toString());
					while(valIter.hasNext()){
						ServiceContractValue serviceVal = (ServiceContractValue) valIter.next();
						if(serviceVal != null){
							availableContractValue = availableContractValue - serviceVal.getServiceProviderValue().doubleValue();
							if((serviceVal.getServiceId()).equalsIgnoreCase(sEvent.getServiceId())){
								resp.setServiceProviderValue(formatDecimal(serviceVal.getServiceProviderValue().doubleValue()));
								resp.setContractServiceId(serviceVal.getOID().toString());
							}
						}
					}
					resp.setAvailableContractValue(formatDecimal(availableContractValue));
					dispatch.postEvent(resp);
				}
			}
		}
   }
 
	/**
	 * @param sEvent
	 * @return
	 */
	private GetContractsEvent prepareGetContractsEvent(GetServiceProviderContractsEvent sEvent) {
	   	GetContractsEvent cEvent = new GetContractsEvent(); 
	   	cEvent.setAgencyId(sEvent.getAgencyId());
	   	cEvent.setContractName(sEvent.getContractName());
	   	cEvent.setContractTypeId(sEvent.getContractTypeId());
	   	cEvent.setExpired(sEvent.isExpired());
	   	cEvent.setFromDate(sEvent.getFromDate());
	   	cEvent.setFundingProgranDescription(sEvent.getFundingProgranDescription());
	   	cEvent.setNumber(sEvent.getNumber());
	   	cEvent.setContractId(sEvent.getContractId());
		return cEvent;
	}
	
	/**
	 * @param d
	 * @return String
	 */
	private String formatDecimal(double d) {
		DecimalFormat myFormatter = new DecimalFormat("######0.00");
		return myFormatter.format(d);
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

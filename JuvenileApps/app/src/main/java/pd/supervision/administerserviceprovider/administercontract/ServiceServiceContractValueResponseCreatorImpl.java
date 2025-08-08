/*
 * Created on September 29, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.administercontract;

import java.util.Iterator;

import messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractServiceResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import pd.codetable.Code;
import pd.common.ResponseCreator;
import pd.supervision.administerserviceprovider.ServiceLocation;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceServiceContractValueResponseCreatorImpl implements ResponseCreator{
     
	/**
	 * Creates Contract response event from entity.
	 * @param object
	 * @return object ContractResponseEvent
	 */
	public Object create(Object object){
		ServiceServiceContractValueView value = (ServiceServiceContractValueView) object;
		ServiceResponseEvent respEvent = new ServiceResponseEvent();
	    respEvent.setServiceName(value.getServiceName());
	    respEvent.setServiceId(value.getServiceId());
	    pd.codetable.criminal.JuvenileEventTypeCode code = value.getServiceType();
		if(value.getServiceTypeId() != null && !value.getServiceTypeId().equals("") && code != null){
			respEvent.setServiceType(code.getDescription());
		}
		respEvent.setServiceCode(value.getServiceCode());
		StringBuffer loc = new StringBuffer();
		Iterator locIter = ServiceLocation.findAll("serviceId", value.getServiceId());
		while(locIter.hasNext()){
			ServiceLocation servLoc =  (ServiceLocation) locIter.next();
			if(servLoc != null){
				JuvLocationUnit locUnit = servLoc.getLocationUnit();
				if(locUnit != null){
					loc.append(locUnit.getLocationUnitName());
					if(locIter.hasNext()){
						loc.append(", ");
					}
				}
			}
		}
		respEvent.setLocationName(loc.toString());
        return respEvent;
	}

	/* (non-Javadoc)
	 * @see pd.common.ResponseCreator#createThin(java.lang.Object)
	 */
	public Object createThin(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pd.common.ResponseCreator#createThinest(java.lang.Object)
	 */
	public Object createThinest(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}


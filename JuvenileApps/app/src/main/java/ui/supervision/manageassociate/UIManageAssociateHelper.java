/*
 * Created on March 2, 2007
 *
 */
package ui.supervision.manageassociate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import messaging.manageassociate.AssociateAddressRequestEvent;
import messaging.manageassociate.CreateUpdateSuperviseeAssociateEvent;
import messaging.manageassociate.GetSuperviseeAssociateEvent;
import messaging.manageassociate.GetCopySuperviseeResidenceAddressEvent;
import messaging.manageassociate.GetSuperviseeAssociatesListBySuperviseeIdEvent;
import messaging.manageassociate.reply.AssociateResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AssociateControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import ui.common.SimpleCodeTableHelper;
import ui.supervision.manageassociate.form.AssociateForm;
import ui.supervision.manageassociate.form.AssociateForm.AssociateAddress;

/**
 * @author CC_Rsojitrawala
 *
 * This is a utility class for common operations required in Manage Associate - UI classes.
 *
 */
public final class UIManageAssociateHelper {

	public static CreateUpdateSuperviseeAssociateEvent getCreateUpdateAssociateEvent(AssociateForm af)
	{
		CreateUpdateSuperviseeAssociateEvent evt = (CreateUpdateSuperviseeAssociateEvent) EventFactory.getInstance(
				AssociateControllerServiceNames.CREATEUPDATESUPERVISEEASSOCIATE);
		
		if (af.getAction().equals(UIConstants.UPDATE) || af.getAction().equals(UIConstants.DELETE))
			evt.setAssociateId(af.getAssociateId());
		
		evt.setAssociateName(af.getAssociateName());
		evt.setRelationshipId(af.getRelationshipId());
		evt.setComments(af.getComments());
		evt.setHomePhone(af.getHomePhone());
		evt.setWorkPhone(af.getWorkPhone());
		evt.setCellPhone(af.getCellPhone());
		evt.setPager(af.getPager());
		evt.setEmail(af.getEmail());
		evt.setStatus(af.isStatus());
		evt.setSpn(af.getSpn());
		
		//	Primary Residence Address Event
		evt.addRequest(UIManageAssociateHelper.getAssociateAddressRequestEvent(af.getPrimaryResidenceAddress()));
		
		//	Other Address Event
		evt.addRequest(UIManageAssociateHelper.getAssociateAddressRequestEvent(af.getOtherAddress()));
		
		return evt;
	}
	
	public static AssociateAddressRequestEvent getAssociateAddressRequestEvent(AssociateAddress address) {
		AssociateAddressRequestEvent evt = new AssociateAddressRequestEvent();
		if (address == null)
			return evt;
		evt.setStreetNum(address.getStreetNumber());
		evt.setStreetName(address.getStreetName());
		evt.setStreetType(address.getStreetType());
		evt.setStreetTypeId(address.getStreetTypeId());
		evt.setAptNum(address.getAptNumber());
		evt.setCity(address.getCity());
		evt.setState(address.getState());
		evt.setStateId(address.getStateId());
		evt.setZipCode(address.getZipCode());
		evt.setAdditionalZipCode(address.getAdditionalZipCode());
		evt.setComplexName(address.getAddressComplexName());
		evt.setAddressType(address.getAddressType());
		evt.setAddressTypeId(address.getAddressTypeId());
		evt.setCounty(address.getCounty());
		evt.setCountyId(address.getCountyId());
		evt.setIsPrimaryResidenceAddress(address.getIsPrimaryResidenceAddress());
		return evt;
	}
	
	public static GetCopySuperviseeResidenceAddressEvent getCopySuperviseeResidenceAddressEvent(AssociateForm af)
	{
		GetCopySuperviseeResidenceAddressEvent evt = (GetCopySuperviseeResidenceAddressEvent) EventFactory.getInstance(
				AssociateControllerServiceNames.GETCOPYSUPERVISEERESIDENCEADDRESS);
		evt.setSpn(af.getSpn());
		
		return evt;
	}
	
	public static void setDisplayNames(Collection associatesList){
		
		Iterator iter = associatesList.iterator();
		while(iter.hasNext()){
			AssociateResponseEvent assocRespEvt = (AssociateResponseEvent) iter.next();
			assocRespEvt.setRelationship(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSCD_RELATIONSHIP, assocRespEvt.getRelationshipTypeId()));
			assocRespEvt.setDisplayLabel(assocRespEvt.getFormattedName() + " | " + assocRespEvt.getRelationship());
		}		
	}
	
	public static Collection fetchAssociatesList(String superviseeId) {
		GetSuperviseeAssociatesListBySuperviseeIdEvent event = new GetSuperviseeAssociatesListBySuperviseeIdEvent();
        event.setSuperviseeId(Integer.parseInt(superviseeId));
        
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(event);

        //	Get PD Response Event
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection associatesList = MessageUtil.compositeToCollection(response, AssociateResponseEvent.class);
		
		// display name for dropdown
		UIManageAssociateHelper.setDisplayNames(associatesList);
		
		return associatesList;
	}
	
	public static Collection fetchAssociatesListSortedOnDisplayName(String superviseeId) {
		Collection sortedAssociateList = UIManageAssociateHelper.fetchAssociatesList(superviseeId);
		Collections.sort((ArrayList)sortedAssociateList);

        return sortedAssociateList;
	}
	
	public static Collection fetchAssociatesListSortedOnStatusAndLastNameAndFirstName(String superviseeId) {
		Collection sortedAssociateList = UIManageAssociateHelper.fetchAssociatesList(superviseeId);
		Collections.sort((ArrayList)sortedAssociateList, new AssociateStatusComparator());
		Collections.sort((ArrayList)sortedAssociateList, new AssociateLastNameComparator());
		Collections.sort((ArrayList)sortedAssociateList, new AssociateFirstNameComparator());
		
		return sortedAssociateList;
	}
	
	public static AssociateResponseEvent getAssociate(String associateId)
	{
		
		GetSuperviseeAssociateEvent assocEvent = (GetSuperviseeAssociateEvent)EventFactory.getInstance(AssociateControllerServiceNames.GETSUPERVISEEASSOCIATE);
		assocEvent.setAssociateId(associateId);   	
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   		dispatch.postEvent(assocEvent);
		
   		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		
		AssociateResponseEvent resp = (AssociateResponseEvent)MessageUtil.filterComposite(compositeResponse, AssociateResponseEvent.class);
		return resp;		
	}
}// END CLASS

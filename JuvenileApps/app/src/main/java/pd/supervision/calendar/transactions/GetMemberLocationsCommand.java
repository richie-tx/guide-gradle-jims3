//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\GetMemberLocationsCommand.java

package pd.supervision.calendar.transactions;

import java.util.HashMap;
import java.util.Iterator;

import messaging.calendar.GetMemberLocationsEvent;
import messaging.juvenilecase.reply.FamilyMemberAddressViewResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.family.FamilyMemberAddressView;

public class GetMemberLocationsCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB50037
    */
   public GetMemberLocationsCommand() 
   {
    
   }


   /**
    * @param event
    * @roseuid 456F2D8700DE
    */
   public void execute(IEvent event) 
   {
   	GetMemberLocationsEvent requestEvent = (GetMemberLocationsEvent)event;
//	String juvenileNum = requestEvent.getJuvenileNum();
	Iterator iter = FamilyMemberAddressView.findAll(requestEvent);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	HashMap memberSet = new HashMap();
	while(iter.hasNext())
	{		
		FamilyMemberAddressView memAddressView =(FamilyMemberAddressView)iter.next();
		FamilyMemberAddressView mapAddress = (FamilyMemberAddressView)memberSet.get(memAddressView.getMemberId());
		//To select latest member address
		if (mapAddress == null || mapAddress.getCreateTimestamp().before(memAddressView.getCreateTimestamp())){
			memberSet.put(memAddressView.getMemberId(), memAddressView);
		}
	}   
	
	for(Iterator memberSetIter = memberSet.values().iterator();memberSetIter.hasNext();) {
		FamilyMemberAddressView latestAddress = (FamilyMemberAddressView)memberSetIter.next();
		dispatch.postEvent(getMemAddressViewResponseEvent(latestAddress));
	}  
   }
   
   
	private FamilyMemberAddressViewResponseEvent getMemAddressViewResponseEvent(FamilyMemberAddressView memAddressView)
	{
		FamilyMemberAddressViewResponseEvent reply = new FamilyMemberAddressViewResponseEvent();
		
		reply.setMemberFirstName(memAddressView.getMemberFirstName());
		reply.setMemberMiddleName("");
		reply.setMemberLastName(memAddressView.getMemberLastName());
		reply.setMemberId(memAddressView.getMemberId());
		reply.setMemberAddressId(memAddressView.getMemberAddressId());
		reply.setStreetNum(memAddressView.getStreetNum());
		reply.setStreetNumSuffixId(memAddressView.getStreetNumSuffixId());
		reply.setStreetNumSuffix(memAddressView.getStreetNumSuffixId());
		reply.setStreetName(memAddressView.getStreetName());
		reply.setStreetTypeId(memAddressView.getStreetTypeId());
		reply.setStreetType(memAddressView.getStreetTypeId());
		reply.setAptNum(memAddressView.getAptNum());
		reply.setCity(memAddressView.getCity());
		reply.setCountyId(memAddressView.getCountyId());
		reply.setStateId(memAddressView.getStateId());
		reply.setState(memAddressView.getStateId());
		reply.setCountryId(memAddressView.getCountryId());
		reply.setCountry(memAddressView.getCountryId());
		reply.setAddressTypeId(memAddressView.getAddressTypeId());
		reply.setAddressType(memAddressView.getAddressTypeId());
		reply.setZipCode(memAddressView.getZipCode());
		reply.setValidated(memAddressView.getValidatedDetail());
		reply.setAdditionalZipCode(memAddressView.getAdditionalZipCode());
		reply.setAddressId(memAddressView.getAddressId());
		reply.setRelationshipToJuvenile(memAddressView.getRelationshipToJuvenile().getDescription());
		return reply;

	}   
   /**
    * @param event
    * @roseuid 456F2D8700EC
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D8700EE
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 456F2D8700FB
    */
   public void update(Object anObject) 
   {
    
   }
   

}

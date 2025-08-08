//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\GetJuvenileAttendanceCommand.java

package pd.supervision.calendar.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import messaging.calendar.GetServiceEventCancellationListEvent;
import messaging.calendar.reply.ServiceEventCancellationResponseEvent;
import messaging.contact.to.PhoneNumberBean;
import messaging.juvenilecase.reply.FamilyConstellationGuardianResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.contact.Phone;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.FamilyMemberEmail;
import pd.juvenilecase.family.FamilyMemberPhone;
import pd.km.util.Name;
import pd.supervision.calendar.ServiceEventAttendance;


public class GetServiceEventCancellationListCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public GetServiceEventCancellationListCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) {
		
		GetServiceEventCancellationListEvent attEv = (GetServiceEventCancellationListEvent) event;		
		String serviceEventId = attEv.getServiceEventId();

		Iterator attendIter = ServiceEventAttendance.findAll("serviceEventId", serviceEventId);

		ArrayList juvenileContexts = new ArrayList();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while (attendIter.hasNext()) {
			ServiceEventAttendance eventAttendance = (ServiceEventAttendance) attendIter.next();
			ServiceEventCancellationResponseEvent respEvent = new ServiceEventCancellationResponseEvent();
			// Profile stripping fix - task 97643						
			JuvenileCore juv = eventAttendance.getJuvenile();
			//
			Name name = new Name(juv.getFirstName(), juv.getMiddleName(), juv.getLastName());
			respEvent.setJuvenileName(name.getFormattedName());	
			respEvent.setJuvenileId(juv.getJuvenileNum());
			FamilyConstellation constellation = juv.getCurrentFamilyConstellation();
			ArrayList guardianList = new ArrayList();
			if (constellation!=null){
				Iterator members = constellation.getFamilyConstellationMembers().iterator();

				while ( members.hasNext() )
				{
					FamilyConstellationMember constMember = (FamilyConstellationMember)members.next();
					FamilyMember famMember = constMember.getTheFamilyMember();
	
					if ( constMember.isGuardian() ){																				
						// Phone - get latest Home phone
						FamilyConstellationGuardianResponseEvent guar = new FamilyConstellationGuardianResponseEvent();
						guar.setFirstName(famMember.getFirstName());
						guar.setLastName(famMember.getLastName());
						guar.setMiddleName(famMember.getMiddleName());
						if (!famMember.getEmailAddresses().isEmpty()) {
							Collection guardianEmails = new ArrayList();
							Iterator guarEmail = famMember.getEmailAddresses().iterator();
							while (guarEmail.hasNext()) {
								guardianEmails.add(((FamilyMemberEmail)guarEmail.next()).getEmailAddress());
							}
							guar.setEmailAddresses(guardianEmails);	
						}
						Iterator phones = famMember.getPhoneNumbers().iterator();
						FamilyMemberPhone latestHomePhone = null;
						FamilyMemberPhone latestMobilePhone = null;
						FamilyMemberPhone latestWorkPhone = null;
						while ( phones.hasNext() ){
							FamilyMemberPhone fmPhone = (FamilyMemberPhone)phones.next();
							if ( "HM".equals(fmPhone.getPhoneTypeId()) &&
								(latestHomePhone == null || latestHomePhone.getCreateTimestamp().compareTo(fmPhone.getCreateTimestamp()) < 0 ) ){
									latestHomePhone = fmPhone;
							}
							
							if ( "WK".equals(fmPhone.getPhoneTypeId()) &&
									(latestWorkPhone == null || latestWorkPhone.getCreateTimestamp().compareTo(fmPhone.getCreateTimestamp()) < 0 ) ){
										latestWorkPhone = fmPhone;
							}
							
							if ( "MO".equals(fmPhone.getPhoneTypeId()) &&
									(latestMobilePhone == null || latestMobilePhone.getCreateTimestamp().compareTo(fmPhone.getCreateTimestamp()) < 0 ) ){
									latestMobilePhone = fmPhone;
							}
							
						}
						if ( latestHomePhone != null )
						{
							Phone phone = latestHomePhone.getPhoneMaster();
							PhoneNumberBean phoneNum = new PhoneNumberBean(phone.getPhoneNumber());
							StringBuffer phoneStr = new StringBuffer(); 
							phoneStr.append(phoneNum.getFormattedPhoneNumber());
							if(phone.getPhoneExt()!= null && !phone.getPhoneExt().equals(""))
								phoneStr.append(" Ext " + phone.getPhoneExt());
							guar.setHomePhoneNumber(phoneStr.toString());				
						}
						
						if ( latestWorkPhone != null )
						{
							Phone phone = latestWorkPhone.getPhoneMaster();
							PhoneNumberBean phoneNum = new PhoneNumberBean(phone.getPhoneNumber());
							StringBuffer phoneStr = new StringBuffer(); 
							phoneStr.append(phoneNum.getFormattedPhoneNumber());
							if(phone.getPhoneExt()!= null && !phone.getPhoneExt().equals(""))
								phoneStr.append(" Ext " + phone.getPhoneExt());
							guar.setWorkPhoneNumber(phoneStr.toString());				
						}

						if ( latestMobilePhone != null )
						{
							Phone phone = latestMobilePhone.getPhoneMaster();
							PhoneNumberBean phoneNum = new PhoneNumberBean(phone.getPhoneNumber());
							StringBuffer phoneStr = new StringBuffer(); 
							phoneStr.append(phoneNum.getFormattedPhoneNumber());
							if(phone.getPhoneExt()!= null && !phone.getPhoneExt().equals(""))
								phoneStr.append(" Ext " + phone.getPhoneExt());
							guar.setMobilePhoneNumber(phoneStr.toString());				
						}		
						guardianList.add(guar);
					}					
				}
			}
			respEvent.setGuardianResponseEvents(guardianList);
			dispatch.postEvent(respEvent);
		}				
	}
	
   
   /**
    * @param event
    * @roseuid 456F2D850272
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850274
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 456F2D850276
    */
   public void update(Object anObject) 
   {
    
   }
   

}

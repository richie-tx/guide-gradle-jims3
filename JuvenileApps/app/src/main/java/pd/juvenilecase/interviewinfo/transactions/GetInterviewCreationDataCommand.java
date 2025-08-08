package pd.juvenilecase.interviewinfo.transactions;

import java.util.Collection;
import java.util.Iterator;
import messaging.administerlocation.GetJuvLocationUnitsByAgencyEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.interviewinfo.GetInterviewCreationDataEvent;
import messaging.interviewinfo.reply.InterviewPersonResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileContact;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

public class GetInterviewCreationDataCommand implements ICommand 
{
   
   /**
    * 
    */
   public GetInterviewCreationDataCommand() 
   {
    
   }
   
   /**
    * @param event
    */
	public void execute(IEvent event) 
	{
		GetInterviewCreationDataEvent evt = (GetInterviewCreationDataEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		
		//Juvenile juvenile = null;
		JuvenileCore juvenile = null; 
		if ( evt.getJuvenileId() != null )
		{
		    	// Profile stripping fix task 97541
			//juvenile = Juvenile.find(evt.getJuvenileId()); 
		    	juvenile = JuvenileCore.findCore(evt.getJuvenileId()); 
		}
		else
		{
			JuvenileCasefile casefile = JuvenileCasefile.find( evt.getCasefileId() );
			juvenile = casefile.getJuvenile(); 
		}
		
		
		//Interview Person List may contain multiple occurance of 
		//people with same name i.e. parent and child may have same name.
		//(see Defect JIMS200048425)
		
		//Add juvenile as a person that can be interviewed
		InterviewPersonResponseEvent self = new InterviewPersonResponseEvent();
		self.setInterviewId(null);
		self.setFirstName( juvenile.getFirstName() );
		self.setMiddleName( juvenile.getMiddleName() );
		self.setLastName( juvenile.getLastName() );
		self.setRelationship( "SELF" );
		self.setTypeOfPerson(InterviewPersonResponseEvent.PERSON_SELF);
		dispatch.postEvent(self);
		
		FamilyConstellation famConst = juvenile.getCurrentFamilyConstellation();
		
		if (famConst!=null)
		{
			Iterator famMembers = famConst.getFamilyConstellationMembers().iterator();
			while ( famMembers.hasNext() )
			{
				FamilyConstellationMember constMember = (FamilyConstellationMember)famMembers.next();
				FamilyMember member = constMember.getTheFamilyMember();
				if (member != null && !member.isDeceased()) {
					InterviewPersonResponseEvent personResponse = new InterviewPersonResponseEvent();
					personResponse.setTypeOfPerson(InterviewPersonResponseEvent.PERSON_FAMILY);
					personResponse.setInterviewId(null);
					if(member.getFirstName()!=null)
						personResponse.setFirstName( member.getFirstName().trim() );
					if(member.getMiddleName()!=null)
						personResponse.setMiddleName( member.getMiddleName().trim() );
					if(member.getLastName()!=null)
						personResponse.setLastName( member.getLastName().trim() );
					if(constMember.getRelationshipToJuvenileId() != null && constMember.getRelationshipToJuvenileId().length() > 0) {
						personResponse.setRelationshipId(constMember.getRelationshipToJuvenileId());
						personResponse.setRelationship(constMember.getRelationshipToJuvenile().getDescription());
					}
					else {
						personResponse.setRelationship("FAMILY MEMBER");
					}
					dispatch.postEvent(personResponse);
				}	
			}
		}
		Collection juvenileContactList = juvenile.getJuvenileContactList();
		if (juvenileContactList!=null && juvenileContactList.size()>0)
		{
			Iterator iter = juvenileContactList.iterator();
			while (iter.hasNext()){
				JuvenileContact member = (JuvenileContact)iter.next();
				
				InterviewPersonResponseEvent personResponse = new InterviewPersonResponseEvent();
				personResponse.setTypeOfPerson(InterviewPersonResponseEvent.PERSON_CONTACT);
				personResponse.setInterviewId(null);
				personResponse.setFirstName( member.getFirstName() );
				personResponse.setMiddleName( member.getMiddleName() );
				personResponse.setLastName( member.getLastName() );
				
				if(member.getRelationshipId() != null && member.getRelationshipId().length() > 0) {
					personResponse.setRelationshipId(member.getRelationshipId());
					personResponse.setRelationship(member.getRelationship().getDescription());
				}
				else {
					personResponse.setRelationship( "CONTACT" );
				}
				dispatch.postEvent(personResponse);	
			}
		}
		
		
		
		GetJuvLocationUnitsByAgencyEvent locUnitsEvent = new GetJuvLocationUnitsByAgencyEvent();
		locUnitsEvent.setAgencyId( "JUV" );
		Iterator locUnits = JuvLocationUnit.findLocUnitByAgency(locUnitsEvent);
		while ( locUnits.hasNext() )
		{
			JuvLocationUnit juvLocUnit = (JuvLocationUnit) locUnits.next();
			if("A".equals(juvLocUnit.getLocationStatus()))
			{
				if ( "A".equals(juvLocUnit.getUnitStatusId()) )
				{
					LocationResponseEvent juvLocResponseEvent = new LocationResponseEvent();
					 juvLocResponseEvent.setLocationUnitName(juvLocUnit.getLocationUnitName());
					 juvLocResponseEvent.setJuvUnitCd(juvLocUnit.getJuvUnitCd());
					 juvLocResponseEvent.setLocationId(juvLocUnit.getLocationId());	
					 juvLocResponseEvent.setJuvLocationUnitId(juvLocUnit.getJuvLocUnitId());
					 dispatch.postEvent(juvLocResponseEvent);
				}
			}
		}
		
	}
   
   /**
    * @param event
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    */
   public void update(Object anObject) 
   {
    
   }
   
}

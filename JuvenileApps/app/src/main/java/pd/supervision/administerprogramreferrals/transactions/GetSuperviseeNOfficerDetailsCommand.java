package pd.supervision.administerprogramreferrals.transactions;

import java.util.Iterator;
import java.util.List;

import pd.contact.party.Party;
import pd.contact.user.UserProfile;
import pd.criminalcase.CriminalCase;
import pd.supervision.administercaseload.CaseAssignmentOrder;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administerprogramreferrals.CSCaseHelper;
import pd.supervision.supervisionstaff.Organization;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import messaging.administerprogramreferrals.GetSuperviseeNOfficerDetailsEvent;
import messaging.administerprogramreferrals.reply.SuperviseeNOfficerDetailsResponseEvent;
import messaging.party.GetPartyDataEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;



public class GetSuperviseeNOfficerDetailsCommand implements ICommand
{
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
		GetSuperviseeNOfficerDetailsEvent reqEvent = (GetSuperviseeNOfficerDetailsEvent)event;
		
		if(reqEvent != null)
		{
			SuperviseeNOfficerDetailsResponseEvent responseEvent = new SuperviseeNOfficerDetailsResponseEvent();
			responseEvent.setDefendantId(reqEvent.getDefendantId());
			
			Supervisee supervisee = Supervisee.findByDefendantId(reqEvent.getDefendantId());
		
			if(supervisee != null)
			{
				// Get probation Officer
				CSCDStaffPosition sp = CSCDStaffPosition.find(supervisee.getAssignedStaffPositionId());
				if(sp != null)
				{
					String probationOfficerId = sp.getUserProfileId();
					UserProfile officer = null;
					
					if(probationOfficerId != null && !probationOfficerId.trim().equals("") && probationOfficerId.indexOf("*") < 0)
					{
					    officer = UserProfile.find(probationOfficerId);
	                    if (officer != null)
	                    {
	                    	responseEvent.setOfficerName(officer.getName().getFormattedName());
	                    }
					}
					else
					{
						responseEvent.setOfficerName("No Officer Assigned");
					}
					
					responseEvent.setPositionPOI(sp.getProbationOfficerInd());
					responseEvent.setPositionName(sp.getPositionName());
					responseEvent.setOfficerPhoneNum(sp.getPhoneNum());
					
					// Get Program Unit
					Organization org = sp.getOrganization();
					if(org != null){
						responseEvent.setProgramUnitDesc(org.getDescription());
					}
				}			
			}
		
//			get supervisee details
			GetPartyDataEvent getPartyData = new GetPartyDataEvent();
			getPartyData.setSpn(reqEvent.getDefendantId());
			getPartyData.setCurrentNameInd("Y");
	
			Party party = Party.find(getPartyData);
			if(party != null)
			{
				responseEvent.setSuperviseeName(party.getFullNameWithLastNameFirst());		
				responseEvent.setSuperviseePhoneNum(party.getPhoneNum());
				responseEvent.setSuperviseeDOB(party.getDateOfBirth());
			} 	
			
			List active_cases = CSCaseHelper.getSuperviseeActiveCases(reqEvent.getDefendantId());
			Iterator iter = active_cases.iterator();
			while(iter.hasNext())
			{
				CaseAssignmentOrder activeCase = (CaseAssignmentOrder)iter.next();
				if(activeCase.getCriminalCaseId().equalsIgnoreCase(reqEvent.getCriminalCaseId()))
				{
					CriminalCase criminalCase = activeCase.getCriminalCase();
					
					if (criminalCase.getOffenseCode() != null)
					{
						responseEvent.setOffenseDesc(criminalCase.getOffenseCode().getDescription());	
						
						String completeCourtNumber = criminalCase.getCourtId();
						if(completeCourtNumber != null)
						{
							completeCourtNumber = completeCourtNumber.trim();
							String courtNum = completeCourtNumber.substring(completeCourtNumber.indexOf(" ")+1); 
							responseEvent.setCrt(courtNum);
						}
						else
						{
							responseEvent.setCrt("");
						}
					}
				}
			}
			MessageUtil.postReply(responseEvent);
		}
	}//end of execute()

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
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


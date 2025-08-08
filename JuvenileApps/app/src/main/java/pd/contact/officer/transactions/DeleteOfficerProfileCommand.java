//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\officer\\transactions\\DeleteOfficerProfileCommand.java

package pd.contact.officer.transactions;

import java.util.Iterator;

import messaging.codetable.reply.ICode;
import messaging.officer.DeleteOfficerProfileEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.AttributeEvent;
import pd.codetable.Code;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.OfficerProfileAddress;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.contact.officer.WorkDay;
import pd.juvenilewarrant.JuvenileWarrant;

/**
 * @author mchowdhury
 * @description delete an Officer Profile 
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class DeleteOfficerProfileCommand implements ICommand
{

	/**
	 * @roseuid 42E67C2301B6
	 */
	public DeleteOfficerProfileCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42E65EA80005
	 */
	public void execute(IEvent event)
	{
		DeleteOfficerProfileEvent officerProfileEvent = (DeleteOfficerProfileEvent) event;
		OfficerProfile officerProfile = OfficerProfile.find(officerProfileEvent.getOfficerProfileId());
		if ( officerProfile != null )
		{
			//delete WorkDays for the officer
			this.deleteWorkDays(officerProfile);
			//delete work Address for the officer
			this.deleteWorkAddress(officerProfile);
			//delete the Officer Profile
			//officerProfile.delete();
		/*	Code aCode = new Code();
			aCode.setCode("I");*/
			officerProfile.setStatusId("I");
		}
	}

	/**
	 * @param officerProfile
	 * @return true/false
	 * no longer used for validation 3-29-23
	 */
	private boolean checkOfficerWarrantsAssociation(OfficerProfile officerProfile)
	{
		boolean isOfficerWarrantsAssociated = false;
		AttributeEvent attr = new AttributeEvent();
		attr.setAttributeName("officerId");
		attr.setAttributeValue(officerProfile.getOfficerProfileId());
		Iterator iter = JuvenileWarrant.findAll(attr);
		while(iter.hasNext()){
			JuvenileWarrant warrant = (JuvenileWarrant) iter.next();
			PDOfficerProfileHelper.sendOfficerWarrantAssociationResponseEvent(warrant);
			isOfficerWarrantsAssociated = true;
			break;
		}
		return isOfficerWarrantsAssociated;
	}

	/**
	 * @param officerProfile
	 */
	private void deleteWorkDays(OfficerProfile officerProfile)
	{
		Iterator workDays = officerProfile.getWorkDays().iterator();
		while (workDays.hasNext())
		{
			WorkDay workDay = (WorkDay) workDays.next();
			if (workDay != null)
			{
				workDay.delete();
			}
		}
	}

	/**
	 * @param officerProfile
	 */
	private void deleteWorkAddress(OfficerProfile officerProfile)
	{
		Iterator addresses = officerProfile.getAddresses().iterator();
		while (addresses.hasNext())
		{
			OfficerProfileAddress workAddress = (OfficerProfileAddress) addresses.next();
			if (workAddress != null)
			{
				workAddress.delete();
			}
		}
	}

	/**
	 * @param event
	 * @roseuid 42E65EA80007
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42E65EA80009
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42E65EA8000B
	 */
	public void update(Object anObject)
	{

	}
}
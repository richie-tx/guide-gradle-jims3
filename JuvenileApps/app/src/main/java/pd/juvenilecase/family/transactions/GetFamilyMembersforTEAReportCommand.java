/*
 * Created on Feb 20, 2013
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.List;

import messaging.family.GetFamilyMembersforTEAReportEvent;
import messaging.juvenilecase.reply.FamilyMemberforTEAReportResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import pd.juvenilecase.family.FamilyMemberTEA;

/**
 * @author cShimek
 *
 */
public class GetFamilyMembersforTEAReportCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) 
	{
		GetFamilyMembersforTEAReportEvent request = (GetFamilyMembersforTEAReportEvent)event;
		FamilyMemberTEA familyMemberTEA = new FamilyMemberTEA();
		
		List <FamilyMemberTEA> aList = CollectionUtil.iteratorToList(familyMemberTEA.findAll("juvenileId",request.getJuvenileId()));

		if (aList != null)
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			FamilyMemberforTEAReportResponseEvent reply = new FamilyMemberforTEAReportResponseEvent();
			for (int x=0; x<aList.size(); x++)
			{
				FamilyMemberTEA fmTEA = (FamilyMemberTEA) aList.get(x);
				reply = new FamilyMemberforTEAReportResponseEvent();
				reply.setFirstName(fmTEA.getFirstName());
				reply.setLastName(fmTEA.getLastName());
				reply.setMiddleName(fmTEA.getMiddleName());
				reply.setRelationToJuvenileCd(fmTEA.getRelationToJuvenileCd());
				reply.setStreetNumber(fmTEA.getStreetNumber());
				reply.setStreetNumberSuffix(fmTEA.getStreetNumberSuffix());
				reply.setStreetName(fmTEA.getStreetName());
				reply.setStreetTypeCd(fmTEA.getStreetTypeCd());
				reply.setApartmentNumber(fmTEA.getApartmentNumber());
				reply.setCity(fmTEA.getCity());
				reply.setStateCd(fmTEA.getStateCd());
				reply.setZipCode(fmTEA.getZipCode());
				reply.setZipCodeExtension(fmTEA.getZipCodeExtension());
				reply.setPhoneNumber(fmTEA.getPhoneNumber());
				reply.setGuardian(fmTEA.isGuradian());
				reply.setInHome(fmTEA.isInHome());
				reply.setIncarcerated(fmTEA.isIncarcerated());
				reply.setDeceased(fmTEA.isDeceased());
				//ER Changes JIMS200074578 Starts
				reply.setMemberId(fmTEA.getMemberId());
				//ER Changes JIMS200074578 Starts
				dispatch.postEvent(reply);
			}
		}
		aList = null;
		request = null;
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
		// TODO Auto-generated method stub

	}

}

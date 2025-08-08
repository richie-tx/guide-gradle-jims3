/*
 * Created on Oct 4, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import naming.PDConstants;

import messaging.family.SaveFamilyMemberEvent;
import messaging.family.SaveFamilyMemberMaritalStatusEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.FamilyMemberMaritalStatus;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * @author athorat
 *
 */
public class SaveFamilyMemberCommand implements ICommand
{

	/**
	 * 
	 */
	public SaveFamilyMemberCommand()
	{
		super();
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		SaveFamilyMemberEvent saveRequest = (SaveFamilyMemberEvent) event;
		FamilyMember member = JuvenileFamilyHelper.updateFamilyMember(saveRequest);
		
		//Bug #48693
		if(!member.isOver21())
		{
			Iterator memberIter =FamilyConstellationMember.findAll("theFamilyMemberId",String.valueOf(member.getOID()));
			FamilyConstellation constellation = null;
			FamilyConstellationMember myMember = null;
			while(memberIter.hasNext())
			{
				FamilyConstellationMember constellationMember = (FamilyConstellationMember)memberIter.next();	
				constellation = constellationMember.getFamilyConstellation();
				if(constellation.isActive()){
					Iterator constCurrentMembers=constellation.getFamilyConstellationMembers().iterator();
					while(constCurrentMembers.hasNext()){
						myMember = (FamilyConstellationMember)constCurrentMembers.next();
						if(myMember.getTheFamilyMemberId().equals(member.getFamilyMemberId()))
						{
							constellationMember.setDetentionVisitation(false);
							break;
						}
					}
					
				}	
				
			}
		}
		
		IHome home = new Home();
		
		Collection coll = 	MessageUtil.compositeToCollection(saveRequest, SaveFamilyMemberMaritalStatusEvent.class);

		if (coll != null && coll.size() > 0)
		{
			List <SaveFamilyMemberMaritalStatusEvent> maritalStatusList = CollectionUtil.iteratorToList(coll.iterator());
			SaveFamilyMemberMaritalStatusEvent maritalStatus = null;
			FamilyMemberMaritalStatus theMemberMaritalStatus = null;
			
			for (int i = 0; i < maritalStatusList.size(); i++) 
			{
				maritalStatus = maritalStatusList.get(i);
				theMemberMaritalStatus = new FamilyMemberMaritalStatus();
				theMemberMaritalStatus.setTheFamilyMemberId(member.getOID());
				theMemberMaritalStatus.setEntryDate(new java.util.Date());
				theMemberMaritalStatus.setMaritalStatusId(maritalStatus.getMaritalStatusId());
				theMemberMaritalStatus.setMarriageDate(maritalStatus.getMarriageDate());
				theMemberMaritalStatus.setDivorceDate(maritalStatus.getDivorceDate());
				theMemberMaritalStatus.setNoOfChildren(maritalStatus.getNoOfChildren());
				if(maritalStatus!=null && maritalStatus.equals(PDConstants.BLANK)){
					theMemberMaritalStatus.setTheRelatedFamMemId(null);
				}
				else{
					theMemberMaritalStatus.setTheRelatedFamMemId(maritalStatus.getRelatedFamMemId());
				}
				home.bind(theMemberMaritalStatus);
			}
			maritalStatusList = null;
			maritalStatus = null;
			theMemberMaritalStatus = null;
		}

		FamilyMemberDetailResponseEvent reply = JuvenileFamilyHelper.getFamilyMemberDetailResponseEvent(member);
		MessageUtil.postReply(reply);
		JuvenileFamilyHelper.sendMaritalStatusList(member.getOID());
		
		saveRequest = null;
		coll = null;
		home = null;
		reply = null;
		member = null;
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
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
	public void update(Object updateObject)
	{
	}

}

package pd.juvenilecase.family.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.family.AssociatedJuvMemberView;
import pd.juvenilecase.family.FamilyMemberMatch;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.family.GetSuspiciousFamilyMemberMatchesEvent;
import messaging.juvenilecase.reply.AssociatedJuvenilesResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;

public class GetSuspiciousFamilyMemberMatchesCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		GetSuspiciousFamilyMemberMatchesEvent reqEvent = (GetSuspiciousFamilyMemberMatchesEvent) anEvent;

		Iterator iter = FamilyMemberMatch.findAll(JuvenileFamilyHelper.MEMBERA, reqEvent.getFamilyMemberId());
		List <FamilyMemberMatch> matches = CollectionUtil.iteratorToList(iter);
		FamilyMemberMatch fmm = null;
		String famMemberId = null;
		
		List <String> memberIds = new ArrayList();
		for (int i = 0; i < matches.size(); i++) {
			fmm = matches.get(i);
			memberIds.add(fmm.getMemberB());
		}
		
		JuvenileFamilyHelper.processFamilyMemberMatchWithAssocJuvenilesReplies(memberIds);
		
		if (fmm != null){
			memberIds.add(fmm.getMemberA());
		}
		
		List <AssociatedJuvMemberView> juvList = null;
		List juvReplies = new ArrayList();
		
		for (int i = 0; i < memberIds.size(); i++) {
		
			famMemberId = memberIds.get(i);
			iter = AssociatedJuvMemberView.find(famMemberId);
			juvList = CollectionUtil.iteratorToList(iter);
			
			AssociatedJuvMemberView associatedJuv = null;
			AssociatedJuvenilesResponseEvent ajre = null;
			
			for (int j = 0; j < juvList.size(); j++) {
				associatedJuv = juvList.get(j);
//				if (juvMap.get(associatedJuv.getJuvenileId()) == null){
//					juvMap.put(associatedJuv.getJuvenileId(), associatedJuv.getJuvenileId());
					ajre = this.getAssociatedJuvenileResponseEvent(associatedJuv);
					if (ajre != null){
						ajre.setFamMemberId(famMemberId);
						juvReplies.add(ajre);
//					}
				}
			}
			associatedJuv = null;
		}
		
		MessageUtil.postReplies(juvReplies);
		
		juvReplies = null;
		famMemberId = null;
		iter = null;
		matches = null;
		juvList = null;
		reqEvent = null;
	}
	
	/**
	 * @param associatedJuv
	 * @return
	 */
	private AssociatedJuvenilesResponseEvent getAssociatedJuvenileResponseEvent(AssociatedJuvMemberView associatedJuv) {
		
		Juvenile juvenile = Juvenile.findJCJuvenile(associatedJuv.getJuvenileId());
		JuvenileCore juvCore =  JuvenileCore.findCore(associatedJuv.getJuvenileId());

		AssociatedJuvenilesResponseEvent re = new AssociatedJuvenilesResponseEvent();
		re.setJuvId(associatedJuv.getJuvenileId());

		if (juvCore != null){
			IName aName = new NameBean();
			aName.setFirstName(juvCore.getFirstName());
			aName.setMiddleName(juvCore.getMiddleName());
			aName.setLastName(juvCore.getLastName());
			re.setJuvName(aName);
			re.setRaceCd(juvCore.getRaceId());
			re.setSexCd(juvCore.getSexId());
			re.setDateOfBirth(juvCore.getDateOfBirth());
			if (juvenile != null){
				re.setEthnicityCd(juvenile.getEthnicityId());
			}
			aName = null;
		}
		juvenile = null;
		juvCore = null;
		
		return re;
	}
	
	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}

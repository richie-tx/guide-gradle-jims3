package pd.juvenilecase.family.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import naming.PDConstants;

import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.family.AssociatedJuvMemberView;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.FamilyMemberMatch;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import messaging.family.GetSuspiciousFamilyMembersEvent;
import messaging.family.GetSuspiciousMembersByMemberIdEvent;
import messaging.juvenile.reply.JuvenileProfilesResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;
import mojo.km.context.multidatasource.Home;

public class GetSuspiciousFamilyMembersCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		GetSuspiciousFamilyMembersEvent reqEvent = (GetSuspiciousFamilyMembersEvent) anEvent;
		
		if (reqEvent.getJuvenileNum() != null 
				&& !reqEvent.getJuvenileNum().equals(PDConstants.BLANK)){
			this.processJuvenile(reqEvent.getJuvenileNum());
			this.processFamilyMembers(reqEvent.getJuvenileNum());
		} else {
			List memberList = CollectionUtil.iteratorToList(FamilyMember.findAll(reqEvent));
			JuvenileFamilyHelper.processFamilyMemberWithAssocJuvenilesReplies(memberList);
			memberList = null;
		}
		
		reqEvent = null;
	}

	private void processJuvenile(String juvenileNum) {
		
		Juvenile juvenile = Juvenile.findJCJuvenile(juvenileNum);
		JuvenileCore juvCore =  JuvenileCore.findCore(juvenileNum);

		JuvenileProfilesResponseEvent re = this.getJuvenileProfilesResponseEvent(juvenile, juvCore);
		MessageUtil.postReply(re);

		re = null;
	}

	private void processFamilyMembers(String juvenileNum){
		AssociatedJuvMemberView associatedMember = null;
		FamilyMember member = null;
		IHome home = new Home();
		List <AssociatedJuvMemberView> memberList = CollectionUtil.iteratorToList(home.findAll("juvenileId", juvenileNum, AssociatedJuvMemberView.class));
		Map memberMap = new HashMap();
		Map suspiciousMemberMap = new HashMap();
		GetSuspiciousMembersByMemberIdEvent reqEvent2 = new GetSuspiciousMembersByMemberIdEvent();
		
		for (int i = 0; i < memberList.size(); i++) {
			associatedMember = memberList.get(i);
			member = FamilyMember.find(associatedMember.getMemberNum());
			if (member.getSSN() != null && !member.getSSN().trim().equals(PDConstants.BLANK)
					&& !member.getSSN().equals(PDConstants.SSN_666666666)
					&& !member.getSSN().equals(PDConstants.SSN_777777777)
					&& !member.getSSN().equals(PDConstants.SSN_888888888)
					&& !member.getSSN().equals(PDConstants.SSN_999999999)){
				if (memberMap.get(member.getFamilyMemberId()) == null){
					memberMap.put(member.getFamilyMemberId(), member.getFamilyMemberId());
					reqEvent2.setMemberA(member.getOID());
					//List suspiciousMatches = CollectionUtil.iteratorToList(FamilyMember.findAll(reqEvent));
					List suspiciousMatches = FamilyMemberMatch.findAll(reqEvent2);
					suspiciousMatches = this.filterList(suspiciousMatches, suspiciousMemberMap);
					suspiciousMatches = this.getFamilyMembers(suspiciousMatches);
					JuvenileFamilyHelper.processFamilyMemberReplies(suspiciousMatches);
					if (suspiciousMatches.size() > 0){
						MessageUtil.postReply(JuvenileFamilyHelper.getFamilyMemberListResponseEvent(member));
					}
					suspiciousMatches = null;
				} 
			}
		}

		associatedMember = null;
		member = null;
		home = null;
		memberList = null;
		memberMap = null;
	}
	
	private List getFamilyMembers(List <FamilyMemberMatch> suspiciousMatches) {
		FamilyMemberMatch fmm = null;
		FamilyMember familyMember = null;
		List familyMemberList = new ArrayList();
		for (int i = 0; i < suspiciousMatches.size(); i++) {
			fmm = suspiciousMatches.get(i);
			familyMember = FamilyMember.find(fmm.getMemberB());
			familyMemberList.add(familyMember);
		}
		
		fmm = null;
		familyMember = null;
		
		return familyMemberList;
	}

	private List filterList(List suspiciousMatches, Map suspiciousMemberMap) {
		FamilyMemberMatch suspiciousMember = null;
		List filteredList = new ArrayList();
		
		for (int i = 0; i < suspiciousMatches.size(); i++) {
			suspiciousMember = (FamilyMemberMatch) suspiciousMatches.get(i);
			if (suspiciousMemberMap.get(suspiciousMember.getMemberB())== null){
				filteredList.add(suspiciousMember);
				suspiciousMemberMap.put(suspiciousMember.getMemberB(), suspiciousMember.getMemberB());
			}
			
		}
		suspiciousMember = null;
		return filteredList;
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
	/**
	 * @param juvenile
	 * @param juvCore
	 * @return
	 */
	private JuvenileProfilesResponseEvent getJuvenileProfilesResponseEvent(Juvenile juvenile, JuvenileCore juvCore) {
		
		JuvenileProfilesResponseEvent re = new JuvenileProfilesResponseEvent();
		if (juvCore != null){
			re.setFirstName(juvCore.getFirstName());
			re.setLastName(juvCore.getLastName());
			re.setRace(juvCore.getRaceId());
			re.setSex(juvCore.getSexId());
			re.setDateOfBirthAsDate(juvCore.getDateOfBirth());
		}

		if (juvenile != null){
			re.setSSN(juvenile.getSSN());
			re.setEthnicityCd(juvenile.getEthnicityId());
		}
		
		juvenile = null;
		juvCore = null;
		
		return re;
	}
	

}

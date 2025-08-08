//Source file: C:\\views\\maint_mjcs_07\\app\\src\\ui\\juvenilecase\\suspiciousMembers\\action\\DisplaySuspiciousMemberSelectAction.java

package ui.juvenilecase.suspiciousMembers.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.to.NameBean;
import messaging.contact.to.SocialSecurityBean;
import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.family.GetSuspiciousFamilyMemberMatchesEvent;
import messaging.family.GetSuspiciousFamilyMembersEvent;
import messaging.juvenile.reply.JuvenileProfilesResponseEvent;
import messaging.juvenilecase.reply.AssociatedJuvenilesResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberMartialStatusResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.suspiciousMembers.UISuspiciousMemberDetailsHelper;
import ui.juvenilecase.suspiciousMembers.form.SuspiciousMemberForm;

/*
 * 
 * @author cShimek
 * 
 */

public class DisplaySuspiciousMembersSearchResultsAction extends JIMSBaseAction
{

    /**
	 *  
	 */
    public DisplaySuspiciousMembersSearchResultsAction()
    {

    }

    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.submit", "findMembers");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.link", "connectNewFamilyMembers");

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward findMembers(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
	SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
	String forwardStr = UIConstants.SUCCESS;
	smForm.setSelectedOrigMemberNum(UIConstants.EMPTY_STRING);
	smForm.setAssociatedJuvenilesList(new ArrayList());
	smForm.setAllAssociatedJuvenilesList(new ArrayList());
	smForm.setMatchingMembersList(new ArrayList());
	smForm.setSearchResultsList(new ArrayList());
	smForm.setMultipleOrigMatches(false);
	smForm.setNoMembersFound(false);

	GetSuspiciousFamilyMembersEvent event = (GetSuspiciousFamilyMembersEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETSUSPICIOUSFAMILYMEMBERS);

	if (UIConstants.JUV.equals(smForm.getSearchTypeId()))
	{
	    // find members by juvenile number			
	    event.setJuvenileNum(smForm.getJuvenileNumber().trim());
	}
	else
	{
	    // find members by name and/or social security number
	    event.setMemberLastName(smForm.getLastName().trim());
	    event.setMemberFirstName(smForm.getFirstName().trim());
	    SocialSecurityBean ssn = new SocialSecurityBean(smForm.getSsn1(), smForm.getSsn2(), smForm.getSsn3());
	    if (!UIConstants.EMPTY_STRING.equals(ssn))
	    {
		event.setMemberSsn(ssn.toString());
	    }
	    event.setFamMemberId(smForm.getSearchMemberAId());
	}

	CompositeResponse cr = MessageUtil.postRequest(event);

	List members = MessageUtil.compositeToList(cr, FamilyMemberListResponseEvent.class);
	//members = UISuspiciousMemberDetailsHelper.eliminateDuplicateFamilyMembers(members); Stopping suspicious processing
	JuvenileProfilesResponseEvent juvRe = (JuvenileProfilesResponseEvent) MessageUtil.filterComposite(cr, JuvenileProfilesResponseEvent.class);

	if (members == null || members.isEmpty())
	{
	    smForm.setNoMembersFound(true);
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No members found for supplied search criteria"));
	    saveErrors(aRequest, errors);
	    forwardStr = UIConstants.NO_MATCHES_FOUND;
	}
	else
	{
	    loadMemberLiterals(members);
	    if (UIConstants.JUV.equals(smForm.getSearchTypeId()))
	    {
		if (members.size() > 1)
		{
		    smForm.setMatchingMembersList(members);
		}
		NameBean nameBean = new NameBean();
		nameBean.setFirstName(juvRe.getFirstName());
		nameBean.setMiddleName(juvRe.getMiddleName());
		nameBean.setLastName(juvRe.getLastName());
		smForm.setJuvenileName(nameBean.getFormattedName());
		nameBean = null;
		smForm.setDateOfBirthStr(DateUtil.dateToString(juvRe.getDateOfBirthAsDate(), DateUtil.DATE_FMT_1));
		smForm.setRaceLit(UIConstants.EMPTY_STRING);
		if (juvRe.getRace() != null)
		{
		    smForm.setRaceLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RACE, juvRe.getRace()));
		}
		smForm.setGenderLit(UIConstants.EMPTY_STRING);
		if (juvRe.getSex() != null)
		{
		    smForm.setGenderLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SEX, juvRe.getSex()));
		}
		if (juvRe.getEthnicityCd() != null)
		{
		    smForm.setEthnicityLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_ETHNICITY, juvRe.getEthnicityCd()));
		}
	    }

	    if (members.size() > 1)
	    {		
		smForm.setSearchResultsList(members);
		smForm.setMultipleOrigMatches(true);
		forwardStr = UIConstants.MULTIPLE_SUCCESS;
	    }
	    if (members.size() == 1)
	    {

		smForm.setAssociatedJuvenilesList(new ArrayList()); // should be AssociatedJuvenilesResponseEvent(s)
		FamilyMemberListResponseEvent fmre = (FamilyMemberListResponseEvent) members.get(0);
		smForm.setSelectedOrigMemberNum(fmre.getMemberNum());
		GetFamilyMemberDetailsEvent event1 = (GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
		event1.setMemberNum(fmre.getMemberNum());
		CompositeResponse response1 = MessageUtil.postRequest(event1);

		List<AssociatedJuvenilesResponseEvent> assocJuvsList = MessageUtil.compositeToList(response1, AssociatedJuvenilesResponseEvent.class);
		AssociatedJuvenilesResponseEvent ajre = null;

		for (int i = 0; i < assocJuvsList.size(); i++)
		{
		    ajre = assocJuvsList.get(i);
		    ajre.setEthnicityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_ETHNICITY, ajre.getEthnicityCd()));
		    ajre.setRaceDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RACE, ajre.getRaceCd()));
		    ajre.setSexDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SEX, ajre.getSexCd()));
		}
		smForm.setAssociatedJuvenilesList(UISuspiciousMemberDetailsHelper.eliminateDuplicateJuveniles(assocJuvsList));
		smForm.setAllAssociatedJuvenilesList(assocJuvsList);
		ajre = null;
		assocJuvsList = null;
		GetSuspiciousFamilyMemberMatchesEvent getMatchesEvt = (GetSuspiciousFamilyMemberMatchesEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETSUSPICIOUSFAMILYMEMBERMATCHES);
		getMatchesEvt.setFamilyMemberId(fmre.getMemberNum());
		CompositeResponse response = MessageUtil.postRequest(getMatchesEvt);
		getMatchesEvt = null;

		List membersB = MessageUtil.compositeToList(response, FamilyMemberListResponseEvent.class);
		// retrieve associated juveniles
		if (fmre.getMemberNum() != null && !UIConstants.EMPTY_STRING.equals(fmre.getMemberNum()))
		{
		    GetFamilyMemberDetailsEvent fdEvent = (GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
		    fdEvent.setMemberNum(fmre.getMemberNum());
		    response = MessageUtil.postRequest(fdEvent);
		    fdEvent = null;

		}
		if ("FAM".equals(smForm.getSearchTypeId()))
		{
		    smForm.setMatchingMembersList(new ArrayList());
		    smForm.setFamilyMemberInfoList(members);
		    // retrieve matching suspicious members 

		    if ( membersB.size() > 0 )
		    {
			smForm.setMatchingMembersList( membersB );
			//matchList = null;
			members = null;
		    }

		}
	    }
	}
	return aMapping.findForward(forwardStr);
    }

    private void loadMemberLiterals(List members)
    {
	Collections.sort(members);
	for (int x = 0; x < members.size(); x++)
	{
	    FamilyMemberListResponseEvent fmre = (FamilyMemberListResponseEvent) members.get(x);
	    if (fmre.getSexCd() != null)
	    {
		fmre.setSex(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SEX, fmre.getSexCd()));
	    }
	    if (fmre.getNationalityCd() != null)
	    {
		fmre.setNationalityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PLACE_OF_BIRTH, fmre.getNationalityCd()));
	    }
	    if (fmre.getEthnicityCd() != null)
	    {
		fmre.setEthnicityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_ETHNICITY, fmre.getEthnicityCd()));
	    }
	    fmre.setDateOfBirth(fmre.getDateOfBirth());
	}
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
	smForm.clear();
	return aMapping.findForward(UIConstants.CANCEL);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward connectNewFamilyMembers(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
	smForm.clearMemberInfo();
	String forwardStr = "connectSuccess";

	smForm.setAssociatedJuvenilesList(new ArrayList()); // should be AssociatedJuvenilesResponseEvent(s)

	smForm.setSelectedOrigMemberNum(smForm.getSearchMemberAId());
	GetFamilyMemberDetailsEvent event1 = (GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
	event1.setMemberNum(smForm.getSearchMemberAId());
	CompositeResponse response1 = MessageUtil.postRequest(event1);

	List<AssociatedJuvenilesResponseEvent> assocJuvsList = MessageUtil.compositeToList(response1, AssociatedJuvenilesResponseEvent.class);
	AssociatedJuvenilesResponseEvent ajre = null;
	FamilyMemberDetailResponseEvent fjre = null;

	List<FamilyMemberDetailResponseEvent> familyMembers = MessageUtil.compositeToList(response1, FamilyMemberDetailResponseEvent.class);

	for (int i = 0; i < assocJuvsList.size(); i++)
	{
	    ajre = assocJuvsList.get(i);
	    ajre.setEthnicityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_ETHNICITY, ajre.getEthnicityCd()));
	    ajre.setRaceDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RACE, ajre.getRaceCd()));
	    ajre.setSexDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SEX, ajre.getSexCd()));
	}
	smForm.setAssociatedJuvenilesList(UISuspiciousMemberDetailsHelper.eliminateDuplicateJuveniles(assocJuvsList));
	smForm.setAllAssociatedJuvenilesList(assocJuvsList);
	ajre = null;
	assocJuvsList = null;

	if (familyMembers.size() > 0)
	{

	    for (int i = 0; i < familyMembers.size(); i++)
	    {
		fjre = familyMembers.get(i);
		fjre.setEthnicityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_ETHNICITY, fjre.getEthnicityId()));
		fjre.setSexDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SEX, fjre.getSexId()));
	    }
	    smForm.setMatchingMembersList(new ArrayList());
	    smForm.setFamilyMemberInfoList(familyMembers);
	}
	else
	{

	    smForm.setNoMembersFound(true);
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Family Member Record Not found to Connect. Please Correct"));
	    saveErrors(aRequest, errors);
	    forwardStr = UIConstants.NO_MATCHES_FOUND;
	}

	return aMapping.findForward(forwardStr);
    }

}
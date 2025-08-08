//Source file: C:\\views\\juvenilecasework\\app\\src\\ui\\juvenilecase\\suspiciousMembers\\action\\DisplaySuspiciousMemberSearchAction.java

package ui.juvenilecase.suspiciousMembers.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.to.SocialSecurityBean;
import messaging.error.reply.ErrorResponseEvent;
import messaging.family.ConnectSuspiciousFamilyMemberEvent;
import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.juvenilecase.reply.AssociatedJuvenilesResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberMartialStatusResponseEvent;
import messaging.juvenilecase.reply.JuvenileFamilyMemberViewResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.SuspiciousFamilyMemberControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.suspiciousMembers.UISuspiciousMemberDetailsHelper;
import ui.juvenilecase.suspiciousMembers.form.SuspiciousMemberForm;

/*
 * 
 * @author cShimek
 * 
 */

public class HandleSuspiciousMembersSelectionAction extends JIMSBaseAction
{

    /**
	 *  
	 */
    public HandleSuspiciousMembersSelectionAction()
    {

    }

    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.merge", "merge");
	keyMap.put("button.correct", "correct");
	keyMap.put("button.removeFlag", "removeFlag");
	keyMap.put("button.next", "attachMemberB");
	keyMap.put("button.finish", "connectMembers");
	keyMap.put("button.replace", "replace");
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward merge(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
	SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;

	smForm.setSelectedFromId(UIConstants.EMPTY_STRING);
	smForm.setSelectedToId(UIConstants.EMPTY_STRING);
	smForm.setSecondaryAction(UIConstants.EMPTY_STRING);

	this.setJuvenileInfo(UISuspiciousMemberDetailsHelper.eliminateDuplicateJuveniles(smForm.getAllAssociatedJuvenilesList()));

	List wrkList = new ArrayList();
	for (int x = 0; x < smForm.getMatchingMembersList().size(); x++)
	{
	    FamilyMemberListResponseEvent fmre = (FamilyMemberListResponseEvent) smForm.getMatchingMembersList().get(x);
	    wrkList.add(fmre);
	}
	FamilyMemberListResponseEvent fmre = (FamilyMemberListResponseEvent) smForm.getFamilyMemberInfoList().get(0);
	wrkList.add(fmre);
	Collections.sort(wrkList);
	smForm.setMergeMembersList(wrkList);
	wrkList = null;

	return aMapping.findForward(UIConstants.MERGE_SUCCESS);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward correct(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
	SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
	smForm.setPopup(UIConstants.EMPTY_STRING);

	GetFamilyMemberDetailsEvent event = (GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
	event.setMemberNum(smForm.getSelectedValue());
	CompositeResponse response = MessageUtil.postRequest(event);

	FamilyMemberDetailResponseEvent resp = (FamilyMemberDetailResponseEvent) MessageUtil.filterComposite(response, FamilyMemberDetailResponseEvent.class);

	List maritalStatusList = MessageUtil.compositeToList(response, FamilyMemberMartialStatusResponseEvent.class);
	List assocJuvsList = MessageUtil.compositeToList(response, AssociatedJuvenilesResponseEvent.class);

	smForm.setMemberNumber(smForm.getSelectedValue());
	smForm.setMemberFirstName(resp.getFirstName());
	smForm.setMemberMiddleName(resp.getMiddleName());
	smForm.setMemberLastName(resp.getLastName());

	SocialSecurityBean ssn = new SocialSecurityBean(resp.getSsn());
	smForm.setMemberSSN1(ssn.getSsn1());
	smForm.setMemberSSN2(ssn.getSsn2());
	smForm.setMemberSSN3(ssn.getSsn3());
	smForm.setMemberDOB(DateUtil.dateToString(resp.getDateOfBirth(), DateUtil.DATE_FMT_1));
	smForm.setSexId(resp.getSexId());
	smForm.setSidNum(resp.getSidNum());
	smForm.setAlienRegNumber(resp.getAlienRegistrationNum());
	smForm.setUsCitizenId( resp.getIsUSCitizenId() );
	/*if (UIConstants.YES.equalsIgnoreCase(resp.getIsUSCitizenId()))
	{
	    smForm.setUsCitizenId(UIConstants.YES_FULL_TEXT);
	}
	if (UIConstants.NO.equalsIgnoreCase(resp.getIsUSCitizenId()))
	{
	    smForm.setUsCitizenId(UIConstants.NO_FULL_TEXT);
	}
	if ("L".equalsIgnoreCase(resp.getIsUSCitizenId()))
	{
	    smForm.setUsCitizenId("LEGAL RESIDENT");
	}
	if ("U".equalsIgnoreCase(resp.getIsUSCitizenId()))
	{
	    smForm.setUsCitizenId("UNKNOWN");
	}*/
	smForm.setNationalityId(resp.getNationalityId());
	smForm.setEthnicityId(resp.getEthnicityId());
	smForm.setPrimaryLanguageId(resp.getPrimarylanguageId());
	smForm.setSecondaryLanguageId(resp.getSecondaryLanguageId());

	smForm.setDeceasedLit(UIConstants.NO_FULL_TEXT);
	if (resp.isDeceasedInd() == true)
	{
	    smForm.setDeceasedLit(UIConstants.YES_FULL_TEXT);
	}
	smForm.setCauseOfDeathId(resp.getCauseOfDeathId());
	//		smForm.setJuvenileAgeAtDeath( resp.getJuvenileAgeAtDeath() );
	//		smForm.setIncarcetatedLit(UIConstants.NO_FULL_TEXT);
	smForm.setDeceased(new Boolean(resp.isDeceasedInd()).toString());
	smForm.setIncarcerated(new Boolean(resp.isIncarcerated()).toString());

	smForm.setComments(resp.getComments());

	// load driver license/Id card display info
	smForm.setDlNumber(resp.getDriverLicenceNumber());
	smForm.setDlStateId(resp.getDriverLicenceStateId());
	smForm.setDlExpDateStr(DateUtil.dateToString(resp.getDriverLicenceExpiryDate(), DateUtil.DATE_FMT_1));
	smForm.setDlClassId(resp.getDriverLicenceClassId());
	smForm.setStateIssuedCardNumber(resp.getIdCardNum());
	smForm.setStateIssuedCardStateId(resp.getIdCardStateId());
	// load Associated Juvenile list info
	this.setJuvenileInfo(assocJuvsList);
	smForm.setAssociatedJuvenilesList(assocJuvsList);

	// load Marital Status info
	if (maritalStatusList != null && !maritalStatusList.isEmpty())
	{
	    FamilyMemberMartialStatusResponseEvent msEvent = (FamilyMemberMartialStatusResponseEvent) maritalStatusList.get(0);
	    List wrkList = new ArrayList();
	    wrkList.add(msEvent);
	    smForm.setMaritalStatusDetailsList(wrkList);
	    smForm.setMaritalStatusId(msEvent.getMartialStatusId());
	    smForm.setRelationshipWithId(msEvent.getRelatedFamMemId());
	    smForm.setMarriageDateStr(DateUtil.dateToString(msEvent.getMarriageDate(), DateUtil.DATE_FMT_1));
	    smForm.setDivorceDateStr(DateUtil.dateToString(msEvent.getDivorceDate(), DateUtil.DATE_FMT_1));
	    smForm.setNumberOfChildren(msEvent.getNumberOfChildren());
	    wrkList = new ArrayList();
	    if (assocJuvsList != null)
	    {
		Map map = new TreeMap();
		for (int a = 0; a < assocJuvsList.size(); a++)
		{
		    AssociatedJuvenilesResponseEvent ajEvent = (AssociatedJuvenilesResponseEvent) assocJuvsList.get(a);
		    if (ajEvent.getJuvId() != null && !UIConstants.EMPTY_STRING.equals(ajEvent.getJuvId()))
		    {
			List temp = UIJuvenileFamilyHelper.getAllJuvenilesFamilyMembers(ajEvent.getJuvId());
			if (temp != null && temp.size() > 0)
			{
			    for (int b = 0; b < temp.size(); b++)
			    {
				JuvenileFamilyMemberViewResponseEvent fmre = (JuvenileFamilyMemberViewResponseEvent) temp.get(b);
				map.put(fmre.getMemberNum(), fmre);
				fmre = null;
			    }
			}
			temp = null;
		    }
		    ajEvent = null;
		}

		wrkList = new ArrayList(map.values());
		map = null;
	    }
	    Collections.sort(wrkList);
	    smForm.setRelationshipWithList(wrkList);
	    wrkList = null;
	}
	else
	{
	    smForm.setMaritalStatusId(UIConstants.EMPTY_STRING);
	    smForm.setRelationshipWithId(UIConstants.EMPTY_STRING);
	    smForm.setMarriageDateStr(UIConstants.EMPTY_STRING);
	    smForm.setDivorceDateStr(UIConstants.EMPTY_STRING);
	    smForm.setNumberOfChildren(UIConstants.EMPTY_STRING);
	}
	return aMapping.findForward(UIConstants.CORRECT_SUCCESS);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward removeFlag(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
	SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;

	/*if (smForm.getMatchingMembersList() != null && smForm.getMatchingMembersList().size() > 0)
	{
	    sendToErrorPage(aRequest, "error.generic", "Selected Member SSN Exists on Other Family Members");
	    return aMapping.findForward(UIConstants.FAILURE);
	}*/

	List filteredList = UISuspiciousMemberDetailsHelper.filterJuvenilesByMemberNumber(smForm.getSelectedValue(), UIConstants.EMPTY_STRING, smForm.getAllAssociatedJuvenilesList());
	this.setJuvenileInfo(filteredList);
	smForm.setAssociatedJuvenilesList(filteredList);
	FamilyMemberListResponseEvent currMember = (FamilyMemberListResponseEvent) smForm.getFamilyMemberInfoList().get(0);
	if( smForm.getMatchingMembersList() != null ){
	    smForm.getMatchingMembersList().add( currMember );
	}else{	    
	    smForm.setMatchingMembersList(new ArrayList());
	    smForm.getMatchingMembersList().add( currMember );	    
	}
	

	FamilyMemberListResponseEvent fmre = new FamilyMemberListResponseEvent();
	for (int x = 0; x < smForm.getMatchingMembersList().size(); x++)
	{
	    fmre = (FamilyMemberListResponseEvent) smForm.getMatchingMembersList().get(x);
	    if (fmre.getMemberNum().equals(smForm.getSelectedValue()))
	    {
		break;
	    }
	}
/*	if (fmre == null)
	{
	    fmre = (FamilyMemberListResponseEvent) smForm.getFamilyMemberInfoList().get(0);
	    if (!fmre.getMemberNum().equals(smForm.getSelectedValue()))
	    {
		fmre = null;
	    }
	}*/

	if (fmre != null)
	{
	    smForm.setMatchingMembersList(new ArrayList());
	    smForm.getMatchingMembersList().add(fmre);
	}

	filteredList = null;
	fmre = null;

	smForm.setSecondaryAction(UIConstants.SUMMARY);
	return aMapping.findForward(UIConstants.REMOVE_SUCCESS);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward replace(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
	SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
	smForm.setSelectedFromId(UIConstants.EMPTY_STRING);
	smForm.setSelectedToId(UIConstants.EMPTY_STRING);

	List wrkList = new ArrayList();
	for (int x = 0; x < smForm.getMatchingMembersList().size(); x++)
	{
	    FamilyMemberListResponseEvent fmre = (FamilyMemberListResponseEvent) smForm.getMatchingMembersList().get(x);
	    wrkList.add(fmre);
	}
	FamilyMemberListResponseEvent fmre = (FamilyMemberListResponseEvent) smForm.getFamilyMemberInfoList().get(0);
	wrkList.add(fmre);
	Collections.sort(wrkList);
	smForm.setProcessList(wrkList);
	wrkList = null;

	this.setJuvenileInfo(UISuspiciousMemberDetailsHelper.eliminateDuplicateJuveniles(smForm.getAllAssociatedJuvenilesList()));

	return aMapping.findForward(UIConstants.REPLACE_SUCCESS);

    }
    
    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     * @throws GeneralFeedbackMessageException
     */
    public ActionForward connectMembers(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
	SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
	
	ConnectSuspiciousFamilyMemberEvent updateEvent = (ConnectSuspiciousFamilyMemberEvent) 
				EventFactory.getInstance(SuspiciousFamilyMemberControllerServiceNames.CONNECTSUSPICIOUSFAMILYMEMBER);
        List MemAList = smForm.getFamilyMemberInfoList();
        FamilyMemberDetailResponseEvent familyMemA = null;
        
        if( MemAList.size() >0 ){
            
            familyMemA = (FamilyMemberDetailResponseEvent) MemAList.get(0);
            updateEvent.setMemberA( familyMemA );
        }
	
        
        List MemBList = smForm.getMatchingMembersList();
        FamilyMemberDetailResponseEvent familyMemB = null;
        
        if( MemBList.size() >0 ){
            
            familyMemB = (FamilyMemberDetailResponseEvent) MemBList.get(0);
            updateEvent.setMemberB(familyMemB);
        }
	
	updateEvent.setMemberA_Id( smForm.getSearchMemberAId());
	updateEvent.setMemberB_Id( smForm.getSearchMemberBId());
	
	
	 CompositeResponse compositeResponse = MessageUtil.postRequest( updateEvent );
	 Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
        if (errorResponse != null)
        {
            ErrorResponseEvent errEvt = (ErrorResponseEvent) errorResponse;
      
        }
            
          
           
	return aMapping.findForward(UIConstants.CONNECT_SUCCESS);

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
	String forward = UIConstants.BACK;
	if (smForm.getSearchResultsList().size() > 1)
	{
	    forward = UIConstants.BACK_TO_SELECT;
	}
	return aMapping.findForward(forward);
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
     * @return ActionForward
     */
    public ActionForward attachMemberB(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
	//smForm.clear

	GetFamilyMemberDetailsEvent event1 = (GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
	event1.setMemberNum(smForm.getSearchMemberBId());
	CompositeResponse response1 = MessageUtil.postRequest(event1);
	
	ErrorResponseEvent errorEvt = (ErrorResponseEvent) MessageUtil.filterComposite( response1 ,ErrorResponseEvent.class);
	if( errorEvt!=null ){
	    sendToErrorPage( aRequest,errorEvt.getMessage() );
	    errorEvt.getMessage();
	 return aMapping.findForward(UIConstants.SUCCESS);
	}

	FamilyMemberDetailResponseEvent fjre = null;

	List<FamilyMemberDetailResponseEvent> familyMembers = MessageUtil.compositeToList(response1, FamilyMemberDetailResponseEvent.class);

	for (int i = 0; i < familyMembers.size(); i++)
	{
	    fjre = familyMembers.get(i);
	    fjre.setEthnicityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_ETHNICITY, fjre.getEthnicityId()));
	    fjre.setSexDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SEX, fjre.getSexId()));
	}
	smForm.setMatchingMembersList(familyMembers);

	return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * @param associatedJuvenilesList
     */
    private void setJuvenileInfo(List<AssociatedJuvenilesResponseEvent> associatedJuvenilesList)
    {

	AssociatedJuvenilesResponseEvent ajre = null;

	for (int x = 0; x < associatedJuvenilesList.size(); x++)
	{
	    ajre = associatedJuvenilesList.get(x);
	    if (ajre.getJuvName() != null)
	    {
		ajre.setJuvFullName(ajre.getJuvName().getFullNameLast());
	    }
	    else
	    {
		ajre.setJuvFullName(UIConstants.EMPTY_STRING);
	    }
	    if (ajre.getRaceCd() != null)
	    {
		ajre.setRaceDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RACE, ajre.getRaceCd()));
	    }
	    if (ajre.getSexCd() != null)
	    {
		ajre.setSexDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SEX, ajre.getSexCd()));
	    }
	    if (ajre.getEthnicityCd() != null)
	    {
		ajre.setEthnicityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_ETHNICITY, ajre.getEthnicityCd()));
	    }
	}
    }
}
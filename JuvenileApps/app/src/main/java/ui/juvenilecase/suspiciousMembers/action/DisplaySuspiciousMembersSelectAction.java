//Source file: C:\\views\\juvenilecasework\\app\\src\\ui\\juvenilecase\\suspiciousMembers\\action\\DisplaySuspiciousMemberSearchAction.java

package ui.juvenilecase.suspiciousMembers.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.family.GetSuspiciousFamilyMemberMatchesEvent;
import messaging.family.GetSuspiciousFamilyMembersEvent;
import messaging.juvenilecase.reply.AssociatedJuvenilesResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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

public class DisplaySuspiciousMembersSelectAction extends JIMSBaseAction
{

    /**
	 *  
	 */
    public DisplaySuspiciousMembersSelectAction()
    {

    }

    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.next", "next");
	keyMap.put("button.cancel", "cancel");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     * @throws GeneralFeedbackMessageException
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
	SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
	String ssn = "";//added for Bug 14779
	String memberNumber = smForm.getSelectedOrigMemberNum();
	List infoList = new ArrayList();
	for (int x = 0; x < smForm.getSearchResultsList().size(); x++)
	{
	    FamilyMemberListResponseEvent fmre = (FamilyMemberListResponseEvent) smForm.getSearchResultsList().get(x);
	    if (memberNumber.equals(fmre.getMemberNum()))
	    {
		infoList.add(fmre);
		ssn = fmre.getOriginalSSN();//added for Bug 14779
		break;
	    }
	}
	smForm.setFamilyMemberInfoList(infoList);
	//changed for Bug 14779 - waiting for confirmation from Cassandra
	/*(GetSuspiciousFamilyMemberMatchesEvent event = 
		(GetSuspiciousFamilyMemberMatchesEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETSUSPICIOUSFAMILYMEMBERMATCHES);
	event.setFamilyMemberId(memberNumber);
	
	CompositeResponse response = MessageUtil.postRequest(event);
	
	Collection coll = MessageUtil.compositeToCollection(response, FamilyMemberListResponseEvent.class);
	List <FamilyMemberListResponseEvent> memberList = CollectionUtil.iteratorToList(coll.iterator());
	FamilyMemberListResponseEvent fmlre = null;*/

	//**************************************************************************************

	/*GetSuspiciousFamilyMembersEvent event = (GetSuspiciousFamilyMembersEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETSUSPICIOUSFAMILYMEMBERS);
	event.setMemberSsn(ssn);
	CompositeResponse cr = MessageUtil.postRequest(event);	
	Collection coll = MessageUtil.compositeToCollection(cr, FamilyMemberListResponseEvent.class);
	List <FamilyMemberListResponseEvent> memberList = CollectionUtil.iteratorToList(coll.iterator());
	FamilyMemberListResponseEvent fmlre = null;  
	//****************************************************************************************
	for (int i = 0; i < memberList.size(); i++) {
		fmlre = memberList.get(i);
		if(fmlre.getMemberNum().equals(memberNumber)) 
			memberList.remove(i);
		else
		{
	//****************************************************added for Bug 14779					
			fmlre.setEthnicityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_ETHNICITY, fmlre.getEthnicityCd()));
			fmlre.setNationalityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PLACE_OF_BIRTH, fmlre.getNationalityCd()));
			fmlre.setSex(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SEX, fmlre.getSexCd()));								
		}
	}*/
	
	    List <FamilyMemberListResponseEvent> memberList = null;
	    GetSuspiciousFamilyMemberMatchesEvent event = (GetSuspiciousFamilyMemberMatchesEvent) 
	    		EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETSUSPICIOUSFAMILYMEMBERMATCHES);
	    event.setFamilyMemberId( memberNumber );

	    List<FamilyMemberListResponseEvent> coll = MessageUtil.postRequestListFilter( event, FamilyMemberListResponseEvent.class );
    
	    
	    if( coll.size() > 0 ){
		
		FamilyMemberListResponseEvent famMemberB = coll.get(0);
		GetSuspiciousFamilyMembersEvent suspEvent = (GetSuspiciousFamilyMembersEvent) 
				EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETSUSPICIOUSFAMILYMEMBERS);
		suspEvent.setFamMemberId( famMemberB.getMemberNum() );
		
		CompositeResponse cr = MessageUtil.postRequest(suspEvent);	
		Collection col2 = MessageUtil.compositeToCollection(cr, FamilyMemberListResponseEvent.class);
		memberList = CollectionUtil.iteratorToList(col2.iterator());
	    }
	    
	    
	smForm.setMatchingMembersList(memberList);
	smForm.setAssociatedJuvenilesList(new ArrayList());
	smForm.setSelectedOrigMemberNum(memberNumber);
	GetFamilyMemberDetailsEvent event1 = (GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
	event1.setMemberNum(memberNumber);
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

	return aMapping.findForward(UIConstants.SUCCESS);
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
	smForm.clearSearch();
	return aMapping.findForward(UIConstants.CANCEL);
    }
}
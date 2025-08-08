package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyMembersEvent;
import messaging.family.SaveFamilyMemSSNUserAccessEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import messaging.juvenilecase.reply.JuvenileFamilyMemberViewResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDConstants;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.family.FamilyMember;

import ui.common.Name;
import ui.common.SocialSecurity;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileMemberSearchForm;

/**
 * @author jjose
 * 
 */
public class DisplayCreateFamilyMemberConfirmAction extends LookupDispatchAction
{
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.next", "next");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        keyMap.put("button.bypassMatch", "bypass");
        keyMap.put("button.go", "findJuvenileInfo");
        keyMap.put("button.update", "updateSSN");
        return keyMap;
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest, true);
        mySearchForm.setPopUp(false);
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest, true);
        mySearchForm.setPopUp(false);
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        
        //deceasedValue & incarceratedValue are used to capture the deceased and incarcerated radio button selections from the UI 
        //and then set here. Other processes are preventing the deceased property from being set the normal way
        //myForm.setDeceased(myForm.getDeceasedValue());
        //myForm.setIncarcerated(myForm.getIncarceratedValue());
        
        myForm.setSelectedValue(UIConstants.EMPTY_STRING);
        if(myForm.isDeceased() == false){
        	myForm.setCauseofDeathId(UIConstants.EMPTY_STRING);
        	myForm.setCauseofDeath(UIConstants.EMPTY_STRING);
        	myForm.setJuvenileAgeAtDeath(UIConstants.EMPTY_STRING);
        }
        
        //server side validation for deceased and incarcerated
        //if family member is deceased then member cannot be incarcerated
        if(myForm.isDeceased()){
            myForm.setIncarcerated(false);
        }       
        //if family member is incarcerated then member cannot be deceased
        if(myForm.isIncarcerated()){
            myForm.setDeceased(false);
        }        
        
        if(myForm.isOver21())
        	myForm.setOver21Str("Yes");
        else
        	myForm.setOver21Str("No");
        Name aName = myForm.getName();
        aName.setFirstName(aName.getFirstName().toUpperCase());
        aName.setMiddleName(aName.getMiddleName().toUpperCase());
        aName.setLastName(aName.getLastName().toUpperCase());
        myForm.setName(aName);
        JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest, true);
        mySearchForm.setPopUp(false);

        Collection mySimiliarMembers = doSearchForSimiliarMember(aMapping, aForm, aRequest, aResponse);
            if (mySimiliarMembers == null || mySimiliarMembers.isEmpty())
                ;
            else
            {
        	// US173689, Do not show popup in update rather allow edit and mark entries suspicious
        	if (myForm.getAction().equals("updateMember"))
        	{
        	    myForm.setBypassMatches(true);
        	}
        	else
        	{
                  sendToErrorPage(aRequest, "error.family.samePerson");
                  mySearchForm.setPopUp(true);
        	}
            }

        if (mySearchForm.isPopUp())
            return aMapping.findForward(UIConstants.FAILURE);
        Name myName = new Name();
        myForm.setRelatedFamMemName(myName);
        if (myForm.getRelatedFamMemId() != null && !myForm.getRelatedFamMemId().equals(""))
        {
            JuvenileFamilyMemberViewResponseEvent myRespEvt = UIJuvenileFamilyHelper.findJuvFamilyMem(myForm
                    .getRelatedFamMemId(), myForm.getMaritalRelWithList());
            if (myRespEvt != null)
            {
                myName.setFirstName(myRespEvt.getFirstName());
                myName.setLastName(myRespEvt.getLastName());
                myName.setMiddleName(myRespEvt.getMiddleName());
                // added 5/21/2013 per ER 74779
                if (!"".equals(myRespEvt.getJuvRelation() ) ) {
                	String str = myRespEvt.getMiddleName() + " (" + myRespEvt.getJuvRelation() + ")";
                	myName.setMiddleName(str);
                	str = null;
                }
            }
        }
        if (myForm.getAction().equals("createMember"))
            myForm.setAction("createMemberSummary");
        else if (myForm.getAction().equals("updateMember"))
            myForm.setAction("updateMemberSummary");
        else if( myForm.getAction().equals("findJuvenileInfo")
        	||  myForm.getAction().equals("findCreateMember")) //88726
            myForm.setAction("findCreateMemberSummary");
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        return forward;
    }
    public ActionForward bypass(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        myForm.setBypassMatches(true);
        myForm.setSuspiciousMemberMatchType("SSN");
        JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest, true);
        
        Name myName = new Name();
        myForm.setRelatedFamMemName(myName);
        if (myForm.getRelatedFamMemId() != null && !myForm.getRelatedFamMemId().equals(""))
        {
            JuvenileFamilyMemberViewResponseEvent myRespEvt = UIJuvenileFamilyHelper.findJuvFamilyMem(myForm
                    .getRelatedFamMemId(), myForm.getMaritalRelWithList());
            if (myRespEvt != null)
            {
                myName.setFirstName(myRespEvt.getFirstName());
                myName.setLastName(myRespEvt.getLastName());
                myName.setMiddleName(myRespEvt.getMiddleName());
                // added 5/21/2013 per ER 74779
                if (!"".equals(myRespEvt.getJuvRelation() ) ) {
                	String str = myRespEvt.getMiddleName() + " (" + myRespEvt.getJuvRelation() + ")";
                	myName.setMiddleName(str);
                	str = null;
                }
            }
        }

        if (myForm.getAction().equals("createMember"))
            myForm.setAction("createMemberSummary");
        else if (myForm.getAction().equals("updateMember"))
            myForm.setAction("updateMemberSummary");
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        return forward;
    }
    private Collection doSearchForSimiliarMember(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest, true);
        mySearchForm.clearSearchResults();
        
        
        if (myForm.getSsn().getSSN() != null && !myForm.getSsn().getSSN().equals(UIConstants.EMPTY_STRING)
        	&& !myForm.getSsn().getSSN().equals(PDConstants.SSN_111111111)
	        && !myForm.getSsn().getSSN().equals(PDConstants.SSN_222222222)
	        && !myForm.getSsn().getSSN().equals(PDConstants.SSN_333333333)
	        && !myForm.getSsn().getSSN().equals(PDConstants.SSN_444444444)
	        && !myForm.getSsn().getSSN().equals(PDConstants.SSN_555555555)
        	&& !myForm.getSsn().getSSN().equals(PDConstants.SSN_666666666)
                && !myForm.getSsn().getSSN().equals(PDConstants.SSN_777777777)
                && !myForm.getSsn().getSSN().equals(PDConstants.SSN_888888888)
                && !myForm.getSsn().getSSN().equals(PDConstants.SSN_999999999))
        {
            //IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
            // Sending PD Request Event
            GetFamilyMembersEvent event = (GetFamilyMembersEvent) EventFactory
                    .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERS);
            event.setMemberSsn(myForm.getSsn().getSSN());

            
            
            CompositeResponse response = MessageUtil.postRequest(event);

            // Getting PD Response Event
            //CompositeResponse response = (CompositeResponse) dispatch.getReply();
            // Perform Error handling
            //MessageUtil.processReturnException(response);
            Map dataMap = MessageUtil.groupByTopic(response);

            if (dataMap != null)
            {
        	//extract ssn matches
                Collection members = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_LIST_TOPIC);
                mySearchForm.setSuspiciousMemberMatch("SSN");
                addDLSuspiciousMembers(myForm, mySearchForm, members);
            }
            else
            {
        	 //No SSN Matches, Check if any DL Matches found.
        	 Collection members = Collections.emptyList(); 
        	 addDLSuspiciousMembers(myForm, mySearchForm, members);
            }
        }      
        else
        {
    	 //No SSN Matches, Check if any DL Matches found.
    	 Collection members = new ArrayList<>(); 
    	 addDLSuspiciousMembers(myForm, mySearchForm, members);
        }
        
        return mySearchForm.getMemberSearchResults();
    }

    private void addDLSuspiciousMembers(JuvenileMemberForm myForm, JuvenileMemberSearchForm mySearchForm, Collection members)
    {
	members = checkSuspiciousMembersByDLNum(myForm, members); 
	UIJuvenileHelper.setJuvMemberSearchFormFROMMemberListRespEvt(mySearchForm, members);
	 
	 
	 if (mySearchForm.getMemberSearchResults() != null && mySearchForm.getMemberSearchResults().size() > 0
	         && myForm.getMemberNumber() != null && !(myForm.getMemberNumber().equals("")))
	 { // removing the existing member from the search result list
	     Iterator iter = mySearchForm.getMemberSearchResults().iterator();
	     while (iter.hasNext())
	     {
	         JuvenileMemberSearchForm.MemberSearchResult mySearchResult = (JuvenileMemberSearchForm.MemberSearchResult) iter
	                 .next();
	         if (mySearchResult.getMemberNumber().equalsIgnoreCase(myForm.getMemberNumber()))
	             iter.remove();
	     }
	 }
    }

    private Collection checkSuspiciousMembersByDLNum(JuvenileMemberForm myForm, Collection members)
    {
	String DLNum = myForm.getDriverLicenseNum();

	if (DLNum != null && !DLNum.equals(""))
	{

	    Iterator<FamilyMember> familyMembersDL = JuvenileCaseHelper.checkFamilyMemberDriverLicenseNum(DLNum);
	    while (familyMembersDL.hasNext())
	    {
		if (members == null)
		    members = new ArrayList<>();
		    
		FamilyMember memberDL = (FamilyMember) familyMembersDL.next();

		if (memberDL != null)
		{
		    boolean exists = false;
		    for (FamilyMemberListResponseEvent member : (Collection<FamilyMemberListResponseEvent>) members)
		    {
			if (member.getMemberNum() == memberDL.getFamilyMemberId())
			{
			    exists = true;
			    myForm.setSuspiciousMemberMatchType("DL#");
			    break;
			}
		    }

		    //check if they have the same name
		    if (memberDL != null && exists == false)
		    {
			FamilyMemberListResponseEvent e = new FamilyMemberListResponseEvent();

			e.setMemberNum(memberDL.getFamilyMemberId());
			//changed for Bug# 15105
			e.setFirstName(memberDL.getFirstName());
			e.setMiddleName(memberDL.getMiddleName());
			e.setLastName(memberDL.getLastName());
			e.setDeceased(memberDL.isDeceased());
			e.setSSN(memberDL.getSSN());
			e.setSuspiciousMember(memberDL.getSuspiciousMember());
			e.setSex(memberDL.getSexId());
			e.setEthnicityCd(memberDL.getEthnicityId());
			e.setDateOfBirth(memberDL.getDateOfBirth());
			members.add(e);
		    }

		}
	    }
	}
	return members;
    }
    
    public ActionForward findJuvenileInfo(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm jmForm = (JuvenileMemberForm) aForm;
        jmForm.setAction("findJuvenileInfo"); //88726
        String juvenileNum = jmForm.getSearchJuvenileNumber();

        GetJuvenileProfileMainEvent requestEvent =
        	(GetJuvenileProfileMainEvent)EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

        requestEvent.setJuvenileNum(juvenileNum);
        CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);

        JuvenileProfileDetailResponseEvent details = 
        	(JuvenileProfileDetailResponseEvent)MessageUtil.filterComposite(replyEvent,JuvenileProfileDetailResponseEvent.class);

     // ERROR OCCURRED or no data found
        if (details == null)
        { 
            sendToErrorPage(aRequest, "No data found for this juvenile number");
        	return aMapping.findForward(UIConstants.FAILURE);
        }
		jmForm.getName().setFirstName( details.getFirstName() );
		jmForm.getName().setLastName( details.getLastName() );
		jmForm.getName().setMiddleName( details.getMiddleName() );
		if (details.getSSN() != null && !"".equals(details.getSSN() ) )
		{
			jmForm.getSsn().setSsn1(details.getSSN().substring(0, 3) );
			jmForm.getSsn().setSsn2(details.getSSN().substring(3,5) );
			jmForm.getSsn().setSsn3(details.getSSN().substring(5,9) );
		}	
		if( details.getDateOfBirth() != null && !"".equals(details.getDateOfBirth() ))
		{
			jmForm.setDateOfBirth( DateUtil.dateToString(details.getDateOfBirth(), DateUtil.DATE_FMT_1) ); 
		}
		jmForm.setSexId(details.getSexId() );
		jmForm.setSidNum(details.getSID());
        jmForm.setAlienNum( details.getAlienNumber() );
        jmForm.setIsUSCitizenId( details.getIsUSCitizen() );
        jmForm.setNationalityId( details.getNationality() );
        jmForm.setEthnicityId( details.getEthnicity() );
   		jmForm.setPrimaryLanguageId( details.getPrimaryLanguage() );
        jmForm.setSecondaryLanguageId( details.getSecondaryLanguage() );

        jmForm.setDriverLicenseNum( details.getDriverLicenseNumber() );
		jmForm.setDriverLicenseStateId( details.getDriverLicenseState() );
		jmForm.setDriverLicenseClassId( details.getDriverLicenseClassId() );
		if( details.getDriverLicenseExpireDate() != null && !"".equals(details.getDriverLicenseExpireDate() ))
		{
			jmForm.setDriverLicenseExpirationDate( DateUtil.dateToString(details.getDriverLicenseExpireDate(), DateUtil.DATE_FMT_1) ); 
		}
		jmForm.setStateIssuedIdNum("");
		jmForm.setIssuedByStateId("");
		jmForm.setCompleteSSN(new SocialSecurity(details.getCompleteSSN())); //88726
        return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
    }    

    /**
     * @param aRequest
     */
    private void sendToErrorPage(HttpServletRequest aRequest, String msg)
    {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
        saveErrors(aRequest, errors);
    }
    
    public ActionForward updateSSN(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	 JuvenileMemberForm jmForm = (JuvenileMemberForm) aForm;    	
    	//record who is looking at the SSN    	
    	SaveFamilyMemSSNUserAccessEvent memSSNAccessEvent = (SaveFamilyMemSSNUserAccessEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.SAVEFAMILYMEMSSNUSERACCESS);
    	memSSNAccessEvent.setJuvenileNum(jmForm.getJuvenileNumber());
    	memSSNAccessEvent.setSsn(jmForm.getCompleteSSN().getSSN());
    	memSSNAccessEvent.setFamilyMemID(jmForm.getMemberNumber());
    	memSSNAccessEvent.setAccessedBy(UIUtil.getCurrentUserID());
    	
    	IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( memSSNAccessEvent ) ;
		jmForm.setSelectedValue(jmForm.getCompleteSSN().getSSN());
        ActionForward forward = aMapping.findForward(UIConstants.UPDATE_SSN);
        return forward;
    }
}// END CLASS

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.family.GetJuvenileAgeAtfamilyMemberDeathEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.reply.AssociatedJuvenilesResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberFinancialResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberMartialStatusResponseEvent;
import messaging.juvenilecase.reply.JuvenileAgeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileLoadCodeTables;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileProfileForm;

public class DisplayFamilyMemberDetailsAction extends LookupDispatchAction
{

    //	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest
    // aRequest, HttpServletResponse aResponse)
    //		  {
    //			   return memberDetails(aMapping,aForm,aRequest,aResponse);
    //		  }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.details", "memberDetails");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        return keyMap;
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }

    public ActionForward memberDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	HttpSession session = aRequest.getSession();
        JuvenileMemberForm myMemForm = (JuvenileMemberForm) aForm;
        String myParam = aRequest.getParameter("clearFamAction");
        String memberNum = "";
        if ("true".equalsIgnoreCase(myParam)){
        	myMemForm.setSecondaryAction("");
        }

        String fromDashboard = aRequest.getParameter("fromDashboard");
        if (fromDashboard != null && !"".equals(fromDashboard) && fromDashboard.equals("viewMemberDetails")) {
        	String juvenileNum = aRequest.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM);

	        if (juvenileNum != null) {
		        GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory
		                .getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
		
		        requestEvent.setJuvenileNum(juvenileNum);
		
		        CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);
		
		        JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent,
		                JuvenileProfileDetailResponseEvent.class);        
	        
	        	UIJuvenileHelper.putHeaderForm(aRequest, detail);
	        	memberNum = aRequest.getParameter("selectedValue");
				JuvenileFamilyForm familyForm = UIJuvenileHelper.getFamilyForm(aRequest);
				if(familyForm == null)
					familyForm = new JuvenileFamilyForm();
				Map dataMap = UIJuvenileFamilyHelper.getActiveFamilyConstellation(juvenileNum);
				if(dataMap!=null) {
					Collection guardians=new ArrayList();
					Collection families = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
				    if (families != null && families.size() > 0) {
				       Iterator myIter = families.iterator(); 
				        while (myIter.hasNext()) {
				            FamilyConstellationListResponseEvent myFamily = (FamilyConstellationListResponseEvent) myIter.next();
				            if (myFamily.isActive()) {
				            	JuvenileFamilyForm.Constellation newFamily=new JuvenileFamilyForm.Constellation();
				            	Collection familiesMembers  = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
				            	newFamily.setFamilyNumber(myFamily.getFamilyNum());			            	
				            	familyForm.setCurrentConstellation(newFamily);
				            	UIJuvenileHelper.setJuvFamilyFormFROMMemberListRespEvt(newFamily,familiesMembers);
				            	Iterator iter =  newFamily.getMemberList().iterator();
					  			  while(iter.hasNext()) {
					  			  	JuvenileFamilyForm.MemberList myMember = (JuvenileFamilyForm.MemberList)iter.next();
					  			  	if(myMember.isGuardian())
					  			  	{
										String famConstellationMemberNum = myMember.getFamilyConstellationMemberNum();
										FamilyConstellationMemberFinancialResponseEvent financial = UIJuvenileFamilyHelper.getFamilyConstellationFinancial(famConstellationMemberNum);			
										JuvenileFamilyForm.Guardian myGuardian=UIJuvenileHelper.getFamilyFormGuardianFROMFamilyConstellationFinancialRespEvt(financial);
										myGuardian.setMemberNumber(myMember.getMemberNumber());
										myGuardian.setConstellationMemberId(myMember.getFamilyConstellationMemberNum());
										myGuardian.setName(myMember.getMemberName());
										myGuardian.setRelationshipToJuv(myMember.getRelationshipToJuv());
										myGuardian.setDeceased(myMember.getDeceasedYesNo());
										myGuardian.setInHomeStatus(myMember.isInHomeStatus());
										guardians.add(myGuardian);	
					  			  	}
					  			  }
					  			familyForm.getCurrentConstellation().setGuardiansList(UIJuvenileHelper.sortGuardianList((ArrayList)guardians));
				            }
				        }
				    }
				}
			
				session.setAttribute("juvenileFamilyForm", familyForm);
	        }
        }        
        else {
        	memberNum = myMemForm.getSelectedValue();
        }
        
        if (myParam != null && myParam.equals("true"))
        {
        	JuvenileFamilyForm myFamForm = UIJuvenileHelper.getFamilyForm(aRequest);
            if (myFamForm != null)
            {
                myFamForm.setAction("");
            }
        }
        
        myMemForm.clearAll();
        myMemForm.setAction("viewOnly");
        if (!(myMemForm.isListsSet()))
            UIJuvenileLoadCodeTables.getInstance().setJuvenileMemberForm(myMemForm);

        // Sending PD Request Event
        GetFamilyMemberDetailsEvent event = (GetFamilyMemberDetailsEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
        event.setMemberNum(memberNum);
        CompositeResponse response = MessageUtil.postRequest(event);

        FamilyMemberDetailResponseEvent resp = (FamilyMemberDetailResponseEvent) MessageUtil.filterComposite(response,
                FamilyMemberDetailResponseEvent.class);

        List martialStaus = MessageUtil.compositeToList(response, FamilyMemberMartialStatusResponseEvent.class);
        List assocJuvs = MessageUtil.compositeToList(response, AssociatedJuvenilesResponseEvent.class);
        if (myMemForm.getJuvenileNumber() == null || "".equals(myMemForm.getJuvenileNumber())) {
        	JuvenileProfileForm myJuvForm = (JuvenileProfileForm) UIJuvenileHelper.getHeaderForm(aRequest);
        	myMemForm.setJuvenileNumber(myJuvForm.getJuvenileNum());
        }
        UIJuvenileHelper.setJuvMemberFormFROMMemberDetailRespEvt(myMemForm, resp, martialStaus, assocJuvs);

        //retrieving juvenileage at death of a family member from JCFAMMEMJUVAGE.
        //#Defect JIMS200047286
        if(myMemForm.isDeceased()){
    	JuvenileProfileForm headerForm = (JuvenileProfileForm) session.getAttribute("juvenileProfileHeader");
        GetJuvenileAgeAtfamilyMemberDeathEvent reqEvent = (GetJuvenileAgeAtfamilyMemberDeathEvent)EventFactory
        				.getInstance(JuvenileFamilyControllerServiceNames.GETJUVENILEAGEATFAMILYMEMBERDEATH);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        reqEvent.setFamilyMemberId(memberNum);
        reqEvent.setAction("retrieve");
        reqEvent.setJuvenileNum(headerForm.getJuvenileNum());
        dispatch.postEvent(reqEvent);
        CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
        JuvenileAgeResponseEvent ageResp = (JuvenileAgeResponseEvent)MessageUtil.filterComposite(compositeResponse,
        		JuvenileAgeResponseEvent.class);
        if(ageResp != null && ageResp.getJuvenileAgeAtDeath()!= null)
        	myMemForm.setJuvenileAgeAtDeath(ageResp.getJuvenileAgeAtDeath());
        }
        //End
        
        
        boolean isGuardian = false;
        boolean isPrimaryAndInHome = false;        
        if(myMemForm.getJuvenileNumber() != null && !myMemForm.getJuvenileNumber().equals("") &&
        	myMemForm.getMemberNumber() != null && !myMemForm.getMemberNumber().equals("")){
            
            isGuardian = UIJuvenileHelper.isFamilyMemberAGuardian(myMemForm.getJuvenileNumber(), myMemForm.getMemberNumber());
            isPrimaryAndInHome = UIJuvenileHelper.isGuardianPrimaryContactAndInHome(myMemForm.getJuvenileNumber(), myMemForm.getMemberNumber());
            
            if(isGuardian && isPrimaryAndInHome)
            {
        	myMemForm.setGuardian(true);
            }
        }
        
        
        myMemForm.getOrigName().setFirstName(resp.getFirstName()); // do this for keeping track
        // of the original name and
        // SSN
        myMemForm.getOrigName().setLastName(resp.getLastName()); // do this for keeping track of
        // the original name and SSN
        myMemForm.getOrigName().setMiddleName(resp.getMiddleName()); // do this for keeping
        // track of the original
        // name and SSN
        myMemForm.getOrigSSN().setSSN(resp.getSsn()); // do this for keeping track of the
        // original name and SSN

        String forwardStr = UIConstants.SUCCESS;
        if ("popup".equalsIgnoreCase(myMemForm.getSecondaryAction())){
        	 forwardStr = "popupsuccess";
        }
        
        session.setAttribute("juvenileMemberForm", myMemForm);
        return aMapping.findForward(forwardStr);
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
}// END CLASS

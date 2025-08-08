/*
 * Created on Oct 5, 2005
 *
 */
package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileMemberSearchForm;
import ui.juvenilecase.form.JuvenileProfileForm;

/**
 * @author jjose
 *  
 */
public class SubmitCreateFamilyMemberAction extends LookupDispatchAction
{

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.finish", "finish");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        keyMap.put("button.returnToConstellation", "familyConstellationList");
        keyMap.put("button.continueToCreateConstellationList", "familyConstellationList");
        return keyMap;
    }

    public ActionForward familyConstellationList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest,true);
		mySearchForm.setPopUp(false);
        JuvenileFamilyForm myFamForm = UIJuvenileHelper.getFamilyForm(aRequest);
        JuvenileFamilyForm.Constellation myConstellation = myFamForm.getCurrentConstellation();
        ArrayList membersList = (ArrayList) myConstellation.getMemberList();
        JuvenileFamilyForm.MemberList myFamilyMember = new JuvenileFamilyForm.MemberList();
        myFamilyMember.setMemberNumber(myForm.getMemberNumber());
        myFamilyMember.setMemberName(myForm.getName());
        myFamilyMember.setGuardian(false);
        myFamilyMember.setDeceased(myForm.isDeceased());
        myFamilyMember.setOver21(myForm.isOver21());
        myFamilyMember.setDetentionVisitation(false);
        membersList.add(myFamilyMember);
        return aMapping.findForward("addSuccess");
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest,true);
		mySearchForm.setPopUp(false);
        return aMapping.findForward(UIConstants.CANCEL);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
    	JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest,true);    	
		mySearchForm.setPopUp(false);
		JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
		 if ( myForm.getAction().equals("createMemberSummary") )
    		 myForm.setAction("createMember");
         else if ( myForm.getAction().equals("updateMemberSummary") )
        	 myForm.setAction("updateMember");
         else if ( myForm.getAction().equals("findCreateMemberSummary") )
             	myForm.setAction("findCreateMember");
        return aMapping.findForward(UIConstants.FAILURE);
    }

    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        
        boolean isGuardian = UIJuvenileHelper.isFamilyMemberAGuardian(myForm.getJuvenileNumber(), myForm.getFamilyMemberNumber());
        boolean isPrimaryAndInHome = UIJuvenileHelper.isGuardianPrimaryContactAndInHome(myForm.getJuvenileNumber(), myForm.getFamilyMemberNumber());
        
        if(myForm.getDeceased() && isGuardian && isPrimaryAndInHome){
            
            ActionErrors errors = new ActionErrors();
            errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( "error.isDeceasedAndGuardian") );
            sendToErrorPage(aRequest, "error.common");
            saveErrors( aRequest, errors );
            return aMapping.findForward(UIConstants.FAILURE);
        }
        
        JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest,true);
		mySearchForm.setPopUp(false);
		List suspiciousMatches = new ArrayList();
		
		if (myForm.isBypassMatches()){
			JuvenileMemberSearchForm.MemberSearchResult suspiciousMember = null;
			for (int i = 0; i < mySearchForm.getMemberSearchResults().size(); i++) {
				suspiciousMember = (JuvenileMemberSearchForm.MemberSearchResult) mySearchForm.getMemberSearchResults().get(i);
				suspiciousMatches.add(suspiciousMember.getMemberNumber());
			}
			myForm.setBypassMatches(false);
		}
		myForm.setHasActiveCasefile(false);
		String juvNum = myForm.getJuvenileNumber();
		if ( juvNum == null || "".equals( juvNum ) ) {
			JuvenileProfileForm headerForm = (JuvenileProfileForm) aRequest.getSession().getAttribute("juvenileProfileHeader");
			juvNum = headerForm.getJuvenileNum();
		}
		SearchJuvenileCasefilesEvent search = (SearchJuvenileCasefilesEvent)
			EventFactory.getInstance( JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES );
		search.setSearchType( "JNUM" );
		search.setJuvenileNum( juvNum );
		
		CompositeResponse response = MessageUtil.postRequest( search );
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite( response, ErrorResponseEvent.class );
		if( error != null )
		{
			ActionErrors errors = new ActionErrors();
			errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( error.getMessage() ) );
			saveErrors( aRequest, errors );
			return aMapping.findForward(UIConstants.FAILURE);
		}
		else
		{
			List casefiles = MessageUtil.compositeToList( response, JuvenileCasefileSearchResponseEvent.class );
			if (casefiles != null)
			{	
				int len = casefiles.size();
				for (int c=0; c<len; c++)
				{
					JuvenileCasefileSearchResponseEvent jcEvent = (JuvenileCasefileSearchResponseEvent) casefiles.get(c);
					if ("ACTIVE".equalsIgnoreCase(jcEvent.getCaseStatus() ) )
					{
						myForm.setHasActiveCasefile(true);
						break;
					}
				}
			}
			casefiles = null;
		}	
		
		FamilyMemberDetailResponseEvent returnEvent = UIJuvenileFamilyHelper.saveMember(myForm, aRequest, suspiciousMatches);
        if (returnEvent == null)
        {
            sendToErrorPage(aRequest, "error.common");
            return aMapping.findForward(UIConstants.FAILURE);
        }
        else
        {
            myForm.setMemberNumber(returnEvent.getMemberId());

            // keep original name in synch with saved name
            myForm.getOrigName().setFirstName(myForm.getName().getFirstName());
            myForm.getOrigName().setMiddleName(myForm.getName().getMiddleName());
            myForm.getOrigName().setLastName(myForm.getName().getLastName());
            myForm.getOrigSSN().setSSN(myForm.getSsn().getSSN());
        }

        if ( myForm.getAction().equals("createMemberSummary")
        	|| myForm.getAction().equals("findCreateMemberSummary") )
        {
            myForm.setAction("createMemberConfirmation");
        }
        else if (myForm.getAction().equals("updateMemberSummary"))
        {
            myForm.setAction("updateMemberConfirmation");
        }
        juvNum = null;
        return aMapping.findForward(UIConstants.SUCCESS);

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

package ui.juvenilecase.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyMembersEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
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

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileMemberSearchForm;

/**
 * @author jjose
 *  
 */
public class DisplayFamilyMemberSSNSummaryAction extends LookupDispatchAction
{

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.next", "next");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");

        return keyMap;
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	 JuvenileMemberForm jmForm = (JuvenileMemberForm) aForm;
    	 jmForm.getCompleteSSN().setSSN(jmForm.getSelectedValue());
    	 jmForm.setAction("updateMember");
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	 JuvenileMemberForm jmForm = (JuvenileMemberForm) aForm;
    	 
    	 // US 173689.
    	 /* Collection mySimiliarMembers = doSearchForSimiliarMember(aMapping, aForm, aRequest, aResponse);
          if (mySimiliarMembers == null || mySimiliarMembers.isEmpty())
              ;
          else
          {
              sendToErrorPage(aRequest, "error.family.samePerson");
              return aMapping.findForward(UIConstants.FAILURE);
          }*/

  
    	 jmForm.setAction("update");
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        return forward;
    }

    private Collection doSearchForSimiliarMember(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest, true);
        mySearchForm.clearSearchResults();
        if (myForm.getCompleteSSN().getSSN() != null && !myForm.getCompleteSSN().getSSN().equals(UIConstants.EMPTY_STRING)
                && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_666666666)
                && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_777777777)
                && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_888888888)
                && !myForm.getCompleteSSN().getSSN().equals(PDConstants.SSN_999999999))
        {
            //IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
            // Sending PD Request Event
            GetFamilyMembersEvent event = (GetFamilyMembersEvent) EventFactory
                    .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERS);
            event.setMemberSsn(myForm.getCompleteSSN().getSSN());

            CompositeResponse response = MessageUtil.postRequest(event);

            // Getting PD Response Event
            //CompositeResponse response = (CompositeResponse) dispatch.getReply();
            // Perform Error handling
            //MessageUtil.processReturnException(response);
            Map dataMap = MessageUtil.groupByTopic(response);

            if (dataMap != null)
            {
                Collection members = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_LIST_TOPIC);

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
        }
        return mySearchForm.getMemberSearchResults();
    }
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {       
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;      
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

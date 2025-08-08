package ui.juvenilecase.caseplan.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.caseplan.form.CaseplanForm;

/**
 * 
 * @author awidjaja
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class DisplayUpdatePlacementInfoSummaryAction extends LookupDispatchAction
{
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42F79A090282
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CaseplanForm form = (CaseplanForm) aForm;
        aRequest.setAttribute("status", "summary");
        CaseplanForm.PlacementInfo placementInfo = form.getCurrentPlacementInfo();

        form.setPlacementInfoExist("Y");
        return aMapping.findForward(UIConstants.SUCCESS);
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
        CaseplanForm form = (CaseplanForm) aForm;
        form.setPlacementInfoExist("N");
        return aMapping.findForward(UIConstants.BACK);
    }

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

}
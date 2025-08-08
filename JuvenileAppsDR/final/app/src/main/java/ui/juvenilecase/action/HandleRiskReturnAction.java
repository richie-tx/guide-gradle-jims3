//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\HandleMAYSISelectionAction.java

package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

/**
 * @author dapte
 *  
 */
public class HandleRiskReturnAction extends LookupDispatchAction
{

    /**
     * @roseuid 42791FCF034B
     */
    public HandleRiskReturnAction()
    {

    }

    /**
     * @see LookupDispatchAction#getKeyMethodMap()
     * @return Map
     */
    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();
        buttonMap.put("button.returnToCasefile", "returnToCasefile");
        buttonMap.put("button.returnToRiskAnalysis", "returnToRiskAnalysis");
        buttonMap.put("button.questionSearch", "returnToRiskQuestionSearch");
        buttonMap.put("button.cancel", "returnToCasefile");
        buttonMap.put("button.back", "back");
        return buttonMap;
    }
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42791D570223
     */
    public ActionForward returnToRiskQuestionSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward("returnToRiskQuestionSearch");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42791D570223
     */
    public ActionForward returnToRiskAnalysis(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward("riskResultsSuccess");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42791D570223
     */
    public ActionForward returnToCasefile(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward("casefileSuccess");
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

}
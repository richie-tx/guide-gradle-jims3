//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\HandleSupervisionOrderSelectionAction.java

package ui.supervision.supervisionorder.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.UISupervisionOrderPrintHelper;

/**
 * @author dgibler
 *  
 */
public class SubmitSupervisionOrderPrintTemplateAction extends JIMSBaseAction {

    /**
     * @roseuid 438F240E01BC
     */
    public SubmitSupervisionOrderPrintTemplateAction() {

    }

    /**
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     * @return Map
     */
    public void addButtonMapping(Map buttonMap) 
    {
    	buttonMap.put("button.printSignature", "printSignature");
    	buttonMap.put("button.print", "printOrder");
    	buttonMap.put("button.printDraft", "printDraftOrder");
    	buttonMap.put("button.printSpanish", "printSpanishOrder");
    	buttonMap.put("button.printSpanishVersion", "printSpanishVersion");
    	buttonMap.put("button.back", "back");
    	buttonMap.put("button.cancel", "cancel");
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
        return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil.getCurrentUserAgencyID()));
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
        ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }

    public ActionForward printSignature(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception {
         UISupervisionOrderPrintHelper helper = new UISupervisionOrderPrintHelper();
		 helper.processSignaturePage(aForm, aRequest, aResponse);
        return (null);
    }
    public ActionForward printOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception {
    	 UISupervisionOrderPrintHelper helper = new UISupervisionOrderPrintHelper();
		 helper.processReport(aForm, aRequest, aResponse);
        return (null);
    }

    public ActionForward printDraftOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception {
    	 UISupervisionOrderPrintHelper helper = new UISupervisionOrderPrintHelper();
		 helper.processReport(aForm, aRequest, aResponse);
        return (null);
    }

    public ActionForward printSpanishOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
            throws Exception {
    	 UISupervisionOrderPrintHelper helper = new UISupervisionOrderPrintHelper();
		 helper.processReport(aForm, aRequest, aResponse);
        return (null);
    }

    public ActionForward printSpanishVersion(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
            throws Exception {
    	 UISupervisionOrderPrintHelper helper = new UISupervisionOrderPrintHelper();
		 helper.processReport(aForm, aRequest, aResponse);
        return (null);
    }
}

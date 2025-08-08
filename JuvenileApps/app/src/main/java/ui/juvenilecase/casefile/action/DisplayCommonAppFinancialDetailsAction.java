package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.casefile.GetCommonAppFinancialInfoEvent;
import messaging.casefile.reply.CommonAppFinancialResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.casefile.form.CommonAppForm;
import ui.juvenilecase.form.JuvenileCasefileForm;

public class DisplayCommonAppFinancialDetailsAction extends JIMSBaseAction
{

    /**
     * @roseuid 46CF1ACB0039
     */
    public DisplayCommonAppFinancialDetailsAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 46CF186B0367
     */
    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CommonAppForm appForm = (CommonAppForm) aForm;
        appForm.setAction("");
        if (!appForm.isFinancialHistoryExits())
        {
            HttpSession session = aRequest.getSession();
            JuvenileCasefileForm aJuvForm = (JuvenileCasefileForm) session.getAttribute("juvenileCasefileForm");
            getJuvenileFamilyDetails(appForm, aJuvForm);
        }
        return aMapping.findForward("success");
    }

    private void getJuvenileFamilyDetails(CommonAppForm aForm, JuvenileCasefileForm aJuvForm)
    {
        Collection guardians = new ArrayList();
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        GetCommonAppFinancialInfoEvent request = (GetCommonAppFinancialInfoEvent) EventFactory
                .getInstance(JuvenileCasefileControllerServiceNames.GETCOMMONAPPFINANCIALINFO);
        request.setJuvenileNum(aJuvForm.getJuvenileNum());
        dispatch.postEvent(request);
        CommonAppFinancialResponseEvent resp = (CommonAppFinancialResponseEvent) MessageUtil.filterComposite(
                (CompositeResponse) dispatch.getReply(), CommonAppFinancialResponseEvent.class);
        if (resp != null)
        {
            aForm.setGuardianList(resp.getFinacialInfo());
        }

    }

    public ActionForward save(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CommonAppForm appForm = (CommonAppForm) aForm;
        appForm.setAction("confirm");
        appForm.setFinancialHistoryExits(true);
        return aMapping.findForward("success");
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.CANCEL);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CommonAppForm commonAppForm = (CommonAppForm) aForm;
        commonAppForm.reset(aMapping, aRequest);
        return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
        keyMap.put("button.link", "link");
        keyMap.put("button.saveChanges", "save");
        keyMap.put("button.back", "back");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.refresh", "refresh");
    }
}

package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.GetJuvenileCasefilePetitionDispositionsEvent;
import messaging.juvenilecase.reply.JuvenileCourtDispositionResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.casefile.form.CommonAppForm;
import ui.juvenilecase.form.JuvenileCasefileForm;

/**
 * @author jjose
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayCommonAppCourtDispositionsAction extends LookupDispatchAction
{

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

    public ActionForward displayDispositionSelection(ActionMapping aMapping, ActionForm aForm,
            HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        CommonAppForm myCommonAppForm = (CommonAppForm) aForm;
        JuvenileCasefileForm myCasefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
        //#bug fix 35750 starts
        CasefileClosingForm myClosingForm = UIJuvenileHelper.getJuvenileCasefileClosingForm(aRequest,myCasefileForm.getSupervisionNum());
        myCommonAppForm.setCasefileId(myCasefileForm.getSupervisionNum());
        //#bug fix 35750 ends
        myCommonAppForm.setSelectedDisposition(myClosingForm.getSelectedDispostion());
        // TODO RETRIEVE Common App disposition Information
        myCommonAppForm.setAction("");
        myCommonAppForm.setSecondaryAction("");
        myCommonAppForm.setSelectedValue("");
        // GET Referral event first
        GetJuvenileCasefilePetitionDispositionsEvent myCasefilePetitionDispositionEvent = new GetJuvenileCasefilePetitionDispositionsEvent();
        myCasefilePetitionDispositionEvent.setJuvenileNum(myCasefileForm.getJuvenileNum());
        myCasefilePetitionDispositionEvent.setReferralNum(myClosingForm.getControllingReferral());

        List dispositions = new ArrayList();
        myCommonAppForm.setDispositions(dispositions);
        List dispositionResponses = MessageUtil.postRequestListFilter(myCasefilePetitionDispositionEvent, JuvenileCourtDispositionResponseEvent.class);
        if (dispositionResponses.size() > 0)
        {
            Iterator myIter = dispositionResponses.iterator();
            while (myIter.hasNext())
            {
                JuvenileCourtDispositionResponseEvent dispResponseEvent = (JuvenileCourtDispositionResponseEvent) myIter
                        .next();
                if (dispResponseEvent != null)
                {
                    CommonAppForm.Disposition disp = new CommonAppForm.Disposition();
                    UIJuvenileCasefileClosingHelper.setCommonAppDispositionFROMRespEvts(dispResponseEvent, disp);
                    dispositions.add(disp);
                }
            }
        }

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

    public ActionForward saveAndContinue(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	//#bug fix 35750 starts
    	CommonAppForm myCommonAppForm = (CommonAppForm) aForm;
        CasefileClosingForm myClosingForm = UIJuvenileHelper.getJuvenileCasefileClosingForm(aRequest,myCommonAppForm.getCasefileId());
        //#bug fix 35750 ends
        if (myCommonAppForm.getSelectedValue() == null || myCommonAppForm.getSelectedValue().trim().equals(""))
        {
            sendToErrorPage(aRequest, "error.commonAppDispositionSelection");
            return aMapping.findForward(UIConstants.FAILURE);

        }
        myCommonAppForm.setSelectedDisposition(myCommonAppForm.getSelectedValue());
        myClosingForm.setSelectedDispostion(myCommonAppForm.getSelectedDisposition());
        myCommonAppForm.setAction("");
        myCommonAppForm.setSecondaryAction("");
        myCommonAppForm.setSelectedValue("");
        return aMapping.findForward(UIConstants.NEXT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();
        buttonMap.put("button.cancel", "cancel");
        buttonMap.put("button.back", "back");
        buttonMap.put("button.link", "displayDispositionSelection");
        buttonMap.put("button.saveAndContinue", "saveAndContinue");
        return buttonMap;
    }

}// END CLASS

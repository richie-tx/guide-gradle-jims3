package ui.juvenilecase.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.reply.JuvenileContactResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.JuvenileContactForm;

public class DisplayJuvenileContactCreateAction extends JIMSBaseAction
{

    /*
     * (non-Javadoc)
     * 
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
        keyMap.put("button.addContact", "addNewContact");
        keyMap.put("button.update", "update");
    }

    /**
     * @roseuid 42AF408C03C8
    
    public DisplayJuvenileContactCreateAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42AF3EDF0292
     */
    public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
	
        JuvenileContactForm jcForm = (JuvenileContactForm) aForm;
        	
        JuvenileContactResponseEvent jcReponse = (JuvenileContactResponseEvent) aRequest.getSession().getAttribute("jContactResponse");
        if ( jcReponse != null ) {
            try {
        	jcForm.setContactProperties(jcReponse);
        	jcForm.setAddressStatus(jcReponse.getValidated());
            } catch (Exception e){}
        }
        
        jcForm.setAction(UIConstants.UPDATE);
        jcForm.setSecondaryAction(UIConstants.SUMMARY);
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42AF3EDF0292
     */
    public ActionForward addNewContact(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileContactForm jcForm = (JuvenileContactForm) aForm;
        int detVisitContactsCount = jcForm.getDetVisitContactsCount();
        boolean daVisit = jcForm.isDaVisit();
        boolean visitorCapRemoved = jcForm.isVisitorCapRemoved();
        jcForm.clear();
        jcForm.setAction(UIConstants.CREATE);
        jcForm.setSecondaryAction(UIConstants.SUMMARY);
        jcForm.setAgeOver21(false);
        jcForm.setDetVisitContactsCount(detVisitContactsCount);
        jcForm.setDaVisit(daVisit);
        jcForm.setVisitorCapRemoved(visitorCapRemoved);
        return aMapping.findForward(UIConstants.SUCCESS);
    }
}

package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.JuvenilePhysicalCharacteristicsForm;

public class DisplayJuvenileScarsTattoosCreateAction extends Action {
	
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenilePhysicalCharacteristicsForm jpForm = (JuvenilePhysicalCharacteristicsForm) aForm;
        jpForm.clear();
        // fetch tattoos collection

        return aMapping.findForward(UIConstants.SUCCESS);
    }

}

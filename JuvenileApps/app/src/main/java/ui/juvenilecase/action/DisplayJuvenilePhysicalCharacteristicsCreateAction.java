package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.JuvenilePhysicalCharacteristicsForm;

public class DisplayJuvenilePhysicalCharacteristicsCreateAction extends LookupDispatchAction
{

    /**
     * @roseuid 42AF409C02AF
     */
    public DisplayJuvenilePhysicalCharacteristicsCreateAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42AF3EE10021
     */
    public ActionForward addMoreCharacteristics(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenilePhysicalCharacteristicsForm jpForm = (JuvenilePhysicalCharacteristicsForm) aForm;        
        jpForm.clear();       
        // fetch tattoos collection

        return aMapping.findForward(UIConstants.SUCCESS);
    }
    
    public ActionForward updatescars(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenilePhysicalCharacteristicsForm jpForm = (JuvenilePhysicalCharacteristicsForm) aForm;
        jpForm.clear();      
        // fetch tattoos collection

        return aMapping.findForward(UIConstants.SCARSANDTATTOOS);
    }
    
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.updateScarsMarksTattoos", "updatescars");
		keyMap.put("button.addMoreCharacteristics", "addMoreCharacteristics");
		return keyMap;
	}
}

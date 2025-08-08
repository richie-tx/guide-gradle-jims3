package ui.juvenilewarrant.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.juvenilewarrant.reply.ActiveWarrantErrorEvent;
import messaging.juvenilewarrant.reply.InvalidWarrantTypeErrorEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import naming.JuvenileWarrantControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

import messaging.juvenilewarrant.GetVOPJuvenileWarrantForActivateEvent;

/**
 * @author ldeen
 */
public class DisplayWarrantVOPActivateSearchResultsAction extends Action
{
    /**
     * @roseuid 420CCB8B03A4
     */
    public DisplayWarrantVOPActivateSearchResultsAction()
    {
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 420CCB020164
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        String forward = UIConstants.SEARCH_FAILURE;
        JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
        GetVOPJuvenileWarrantForActivateEvent event = (GetVOPJuvenileWarrantForActivateEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETVOPJUVENILEWARRANTFORACTIVATE);
            event.setWarrantNum(form.getWarrantNum());
            CompositeResponse response=MessageUtil.postRequest(event);

            MessageUtil.processReturnException(response);

            ActionErrors errors = new ActionErrors();

            ActiveWarrantErrorEvent activeWarrantError = (ActiveWarrantErrorEvent) MessageUtil.filterComposite(response,
                    ActiveWarrantErrorEvent.class);

            InvalidWarrantTypeErrorEvent invalidWarrantError = (InvalidWarrantTypeErrorEvent) MessageUtil.filterComposite(response,
                    InvalidWarrantTypeErrorEvent.class);

            if (activeWarrantError != null)
            {
                // The warrant cannot be active
                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.warrant.requestactivation"));
                this.saveErrors(aRequest, errors);
            }
            else if (invalidWarrantError != null)
            {
                // Not a VOP warrant
                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.invalidwarranttype"));
                this.saveErrors(aRequest, errors);
            }
            else
            {
	            JuvenileWarrantResponseEvent juvenileEvent = (JuvenileWarrantResponseEvent) MessageUtil.filterComposite(response,
	                    JuvenileWarrantResponseEvent.class);
	
	            if (juvenileEvent == null)
	            {
	                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
	                this.saveErrors(aRequest, errors);
	            }
	            else
	            {
	                form.setProperties(response);
	                forward = UIConstants.SUCCESS;
	            }     
            }
        return aMapping.findForward(forward);
    }
}
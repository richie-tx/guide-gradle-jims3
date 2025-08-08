/*
 * Created on Feb 16, 2005
 *
 */
package ui.juvenilewarrant.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilewarrant.GetJuvenileWarrantsListEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDJuvenileWarrantConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author Jim Fisher
 *  
 */
public class DisplayVOPUpdateAction extends Action
{

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return forward
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception
    {
        // TODO handle cancel button

        // Default value to search failure in case no warrantType present
        String forwardStr = UIConstants.SEARCH_FAILURE;

        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

        // Fetch warrants based on search request
        int errorCode = this.setJuvenileForm(aRequest, jwForm);

        jwForm.setWarrantTypeUI(null);

        if ((errorCode & UIConstants.NO_ERRORS) == errorCode)
        {
            forwardStr = UIConstants.SUCCESS;
        }
        else if (errorCode == UIConstants.NO_WARRANT_FOUND)
        {
        	StringBuffer buffer = new StringBuffer(30);
			String warrantNum = jwForm.getWarrantNum();
	            buffer.append("");
	            
		    if(warrantNum != null && !warrantNum.equals(""))
		    {
		    	buffer.append(" Warrant # ");
		        buffer.append(warrantNum);
		    }
		    
		    String errorString = buffer.toString();
		    ActionMessage error = new ActionMessage("error.noRecordsFound", errorString);
		    ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			saveErrors(aRequest, errors);
        }
        else
        {
            ActionErrors errors = new ActionErrors();
            this.setErrors(errorCode, errors);

            saveErrors(aRequest, errors);
        }

        jwForm.setBackToWarrantUrl(aMapping.findForward(forwardStr).getPath());

        return aMapping.findForward(forwardStr);
    }

    /**
     * 
     * @param errorCode
     * @param errors
     */
    private void setErrors(int errorCode, ActionErrors errors)
    {
        // TODO This should be put in a constants file
        this.checkError(errorCode, UIConstants.NO_CHARGES, errors, "error.juvenilewarrant.nocharges");
        this.checkError(errorCode, UIConstants.IS_SIGNED, errors, "error.juvenilewarrant.issigned");
        this.checkError(errorCode, UIConstants.PRE_CONTITIONS_NOT_MATCHED, errors, "error.juvenilewarrant.warrant.oicupdate");
    }

    /**
     * 
     * @param errorCode
     * @param errorConstant
     * @param errors
     * @param key
     */
    private void checkError(int errorCode, int errorConstant, ActionErrors errors, String key)
    {
        if (errorConstant == errorCode)
        {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(key));
        }
    }

    /**
     * 
     * @param aForm
     * @return int errorCode
     */
    private int setJuvenileForm(HttpServletRequest aRequest, JuvenileWarrantForm form)
    {
        int errorCode = UIConstants.NO_ERRORS;

        // TODO Change to use GetJuvenileWarrant when this command is refactored
        GetJuvenileWarrantsListEvent getEvent = (GetJuvenileWarrantsListEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSLIST);
        getEvent.setWarrantNum(form.getWarrantNum());

        CompositeResponse response = MessageUtil.postRequest(getEvent);

        JuvenileWarrantResponseEvent warrantEvent = (JuvenileWarrantResponseEvent) MessageUtil.filterComposite(response,
                JuvenileWarrantResponseEvent.class);

        // Fix so query only returns VOP warrants OR present an error
        if (warrantEvent == null || PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equals(warrantEvent.getWarrantTypeId()))
        {
            // no juvenile warrant was found in the system
            errorCode = UIConstants.NO_WARRANT_FOUND;
        }
        else if (isPreConditionsMatched(warrantEvent))
        {
            form.setProperties(response);
            form.refreshSchool();
        }
        else
        {
            errorCode = UIConstants.PRE_CONTITIONS_NOT_MATCHED;
        }
        return errorCode;
    }

    /**
     * @param warrantEvent
     * @return
     */
    private boolean isPreConditionsMatched(JuvenileWarrantResponseEvent warrantEvent)
    {
        // TODO This should be put in the PD layer.

        // JMF - 7/17/2005
        // Just because there's no interaction with persistence does not mean
        // this
        // should not be ecapsulated with in a command.
        // Notice how the constants are "PD" constants.

        String warrantType = warrantEvent.getWarrantTypeId();
        String warrantStatus = warrantEvent.getWarrantStatusId();
        String warrantActivationStatus = warrantEvent.getWarrantActivationStatusId();
        String warrantNotSignedReason = warrantEvent.getUnsendNotSignedReason();

        if ((warrantType.equalsIgnoreCase(PDJuvenileWarrantConstants.WARRANT_TYPE_VOP))
                && (warrantStatus.equalsIgnoreCase(PDJuvenileWarrantConstants.WARRANT_STATUS_PENDING))
                && (warrantActivationStatus.equalsIgnoreCase(PDJuvenileWarrantConstants.WARRANT_UNSEND))
                && (warrantNotSignedReason != null && warrantNotSignedReason.length() > 0))
        {
            return true;
        }
        return false;
    }

}

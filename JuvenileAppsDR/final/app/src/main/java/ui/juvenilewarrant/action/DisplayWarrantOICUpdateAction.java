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
public class DisplayWarrantOICUpdateAction extends Action
{

    private static final int NO_ERRORS = 0;

    private static final int NO_CHARGES = 1;

    private static final int NO_WARRANT_FOUND = 2;

    private static final int PRE_CONTITIONS_NOT_MATCHED = 5;

    private static final int IS_SIGNED = 4;

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

        // default value to failure in case no warrantType present
        String forwardStr = UIConstants.FAILURE;

        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

        // fetch warrants based on search request
        int errorCode = this.setJuvenileForm(aRequest, jwForm);

        jwForm.setWarrantTypeUI(null);

        if ((errorCode & NO_ERRORS) == errorCode)
        {
            forwardStr = UIConstants.SUCCESS;
        }
        else
        {
            ActionErrors errors = new ActionErrors();
            this.setErrors(errorCode, errors, jwForm);

            saveErrors(aRequest, errors);

            forwardStr = UIConstants.SEARCH_FAILURE;
        }

        jwForm.setBackToWarrantUrl(aMapping.findForward(forwardStr).getPath());

        return aMapping.findForward(forwardStr);
    }

    /**
     * 
     * @param errorCode
     * @param errors
     */
    private void setErrors(int errorCode, ActionErrors errors, JuvenileWarrantForm jwForm)
    {
        // TODO This should be put in a constants file
        StringBuffer buffer = new StringBuffer(30);
	    String warrantNum = jwForm.getWarrantNum();
	           		            
	    if(warrantNum != null && !warrantNum.equals(""))
	    {
	    	buffer.append(" Warrant # ");
	        buffer.append(warrantNum);
	    }

	    String errorString = buffer.toString();
	    Object[] values = new Object[1];
	    values[0] = errorString;
	    if(errorCode == 2)
	    {
	    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecordsFound", values));
	    }
        this.checkError(errorCode, IS_SIGNED, errors, "error.juvenilewarrant.issigned");
        this.checkError(errorCode, PRE_CONTITIONS_NOT_MATCHED, errors, "error.juvenilewarrant.warrant.oicupdate");
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
        int errorCode = NO_ERRORS;

        // TODO Change to use GetJuvenileWarrant when this command is refactored
        GetJuvenileWarrantsListEvent getEvent = (GetJuvenileWarrantsListEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSLIST);
        getEvent.setWarrantNum(form.getWarrantNum());

        CompositeResponse response = MessageUtil.postRequest(getEvent);

        JuvenileWarrantResponseEvent warrantEvent = (JuvenileWarrantResponseEvent) MessageUtil.filterComposite(
                response, JuvenileWarrantResponseEvent.class);

        if (warrantEvent == null)
        {
            // no juvenile warrant was found in the system
            errorCode = NO_WARRANT_FOUND;
        }
        // check if warrantTypeId is OIC
        else if (form.getWarrantTypeId().equalsIgnoreCase(warrantEvent.getWarrantTypeId()) == false)
        {
            errorCode = NO_WARRANT_FOUND;
        }
        else if (isPreConditionsMatched(warrantEvent))
        {
            form.setProperties(response);
            
            form.refreshSchool();
            
            // TODO Check this exception
            if (form.getSelectedCharges() == null || form.getSelectedCharges().length == 0)
            {
                errorCode = NO_CHARGES;
            }
        }
        else
        {
            errorCode = PRE_CONTITIONS_NOT_MATCHED;
        }
        return errorCode;
    }

    /**
     * @param warrantEvent
     * @return
     */
    private boolean isPreConditionsMatched(JuvenileWarrantResponseEvent warrantEvent)
    {
        // TODO Auto-generated method stub
        String warrantType = warrantEvent.getWarrantTypeId();
        String warrantStatus = warrantEvent.getWarrantStatusId();
        String warrantActivationStatus = warrantEvent.getWarrantActivationStatusId();
        String warrantSignedStatus = warrantEvent.getWarrantSignedStatusId();
        String warrantNotSignedReason = warrantEvent.getUnsendNotSignedReason();
        boolean trueFalse = false;

        if ((warrantType.equalsIgnoreCase(PDJuvenileWarrantConstants.WARRANT_TYPE_OIC))
                && (warrantStatus.equalsIgnoreCase(PDJuvenileWarrantConstants.WARRANT_STATUS_PENDING))
                && (warrantActivationStatus.equalsIgnoreCase(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE))
                && (warrantSignedStatus.equalsIgnoreCase(PDJuvenileWarrantConstants.WARRANT_NOT_SIGNED))
                && (warrantNotSignedReason != null && warrantNotSignedReason.length() > 0))
        {
            trueFalse = true;
        }
        return trueFalse;
    }

}

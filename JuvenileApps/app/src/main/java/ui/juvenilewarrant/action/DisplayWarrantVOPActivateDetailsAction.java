/*
 * Created on Mar 4, 2005
 *
 */
package ui.juvenilewarrant.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.juvenilewarrant.GetVOPJuvenileWarrantForActivateEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import messaging.juvenilewarrant.reply.ActiveWarrantErrorEvent;
import messaging.juvenilewarrant.reply.InvalidWarrantTypeErrorEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
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
public class DisplayWarrantVOPActivateDetailsAction extends Action
{
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        // TODO Handle cancel
        String forward;

        JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;

        int status = this.processForm(aRequest, form);

        if (status == UIConstants.SINGLE_RESULT)
        {
            forward = UIConstants.SUCCESS;
        }
        else if (status == UIConstants.MULTIPLE_RESULTS)
        {
            forward = UIConstants.ACTVOP_LISTSUCCESS;
        }
        else
        {
            forward = UIConstants.SEARCH_FAILURE;
        }

        form.setBackToWarrantUrl(aMapping.findForward(forward).getPath());

        return aMapping.findForward(forward);
    }

    /**
     * 
     * @param aRequest
     * @param aForm
     * @return boolean success
     */
    private int processForm(HttpServletRequest aRequest, JuvenileWarrantForm form)
    {
        int status = UIConstants.ZERO_RESULTS;

        // Not using the common fetchWarrant because the VOP warrant search
        // has a specific command
        CompositeResponse response = this.fetchWarrant(form);

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
            List warrantsList = (List) MessageUtil.compositeToList(response,JuvenileWarrantResponseEvent.class);
            if(warrantsList.size()<=1){
	            JuvenileWarrantResponseEvent juvenileEvent = (JuvenileWarrantResponseEvent) MessageUtil.filterComposite(response,
	                    JuvenileWarrantResponseEvent.class);
	
	            if (juvenileEvent == null)
	            {
	            	StringBuffer buffer = new StringBuffer(30);
	    			String juvenileNumber = form.getJuvenileNum();
	    		    String referralNumber = form.getReferralNum();
	    		    String warrantNum = form.getWarrantNum();
   		            buffer.append("");
   		            
	    		    if(warrantNum != null && !warrantNum.equals(""))
	    		    {
	    		    	buffer.append(" Warrant # ");
	    		        buffer.append(warrantNum);
	    		    }

	    		    if(juvenileNumber != null && !juvenileNumber.equals(""))
	    		    {
	    		    	buffer.append(" Juvenile # ");
	    		    	buffer.append(juvenileNumber);
	    		    }
	    		    
	    		    if(referralNumber != null && !referralNumber.equals(""))
	    		    {
	    		    	buffer.append(" and Referral # ");
	    		    	buffer.append(referralNumber);
	    		    }
	    		    
	    		    String errorString = buffer.toString();
	    			ActionMessage error = new ActionMessage("error.noRecordsFound", errorString);
	    			errors.add(ActionErrors.GLOBAL_MESSAGE, error);	
	                this.saveErrors(aRequest, errors);
	                status = UIConstants.ZERO_RESULTS;
	            }
	            else
	            {
	                form.setProperties(response);
	                                
	                status = UIConstants.SINGLE_RESULT;
	            }
            }
            else{
                form.setWarrants(warrantsList);
                form.setSearchResultSize(Integer.toString(warrantsList.size()));
                status = UIConstants.MULTIPLE_RESULTS;
                
            }
        }

        return status;
    }

    /**
     * 
     * @param aForm
     * @return CompositeResponse
     */
    private CompositeResponse fetchWarrant(JuvenileWarrantForm aForm)
    {
        GetVOPJuvenileWarrantForActivateEvent event = (GetVOPJuvenileWarrantForActivateEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETVOPJUVENILEWARRANTFORACTIVATE);

        if (aForm.getJuvenileNum() != null && "".equals(aForm.getJuvenileNum()) == false && aForm.getReferralNum() != null
                && "".equals(aForm.getReferralNum()) == false)
        {
            event.setJuvenileNum(aForm.getJuvenileNum());
            event.setReferralNum(aForm.getReferralNum());
        }
        else
        {
            event.setWarrantNum(aForm.getWarrantNum());
        }

        return MessageUtil.postRequest(event);
    }

}

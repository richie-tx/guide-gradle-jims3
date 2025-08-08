/*
 * Created on Sep 13, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common.action;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import messaging.address.reply.AddressResponseEvent;
import messaging.addressValidation.AddressValidationEvent;
import messaging.addressValidation.reply.ValidateAddressResponseEvent;

import ui.common.form.AddressValidationForm;

/**
 * @author cshimek
 *  
 */
public class ValidateAddressAction extends Action
{
    /**
     * 
     *  
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        AddressValidationForm addrForm = (AddressValidationForm) aForm;
        String streetNum = addrForm.getValidStreetNum();
        streetNum = this.cleanStreetNum(streetNum);

        String streetName = addrForm.getValidStreetName();
        String zip = addrForm.getValidZipCode();
        String inputPage = addrForm.getInputPage();
        String aNum = addrForm.getValidAddrNum();

        AddressValidationEvent requestEvent = new AddressValidationEvent();

        requestEvent.setStreetNum(this.convertStreetNum(streetNum));
        requestEvent.setStreetName(streetName);
        if (zip != null && zip.equals("") == false)
        {
            requestEvent.setZipCode(new Integer(zip).intValue());
        }

        CompositeResponse response = MessageUtil.postRequest(requestEvent);

        ValidateAddressResponseEvent data = (ValidateAddressResponseEvent) MessageUtil.filterComposite(response,
                ValidateAddressResponseEvent.class);

        Collection addresses = addrForm.getAssociateAddresses();
        if (addresses != null && !aNum.equals(""))
        {
            Iterator ite = addrForm.getAssociateAddresses().iterator();

            int addrNum = Integer.parseInt(addrForm.getValidAddrNum());
            int i = 0;
            boolean done = false;
            while (done == false && ite.hasNext())
            {
                AddressResponseEvent address = (AddressResponseEvent) ite.next();
                if (addrNum == i)
                {
                    address.setAddressStatus(data.getValidAddressInd());
                    address.setAddressMessage(data.getReturnMessage());
                    done = true;
                }
                i++;
            }

        }
        else
        {
            addrForm.setAddressStatus(data.getValidAddressInd());
            addrForm.setValidAddressMessage(data.getReturnMessage());
        }

        return dispatch(inputPage);
    }

    /**
     * @param streetNum
     * @return
     */
    private String cleanStreetNum(String streetNum)
    {
        StringBuffer buffer = new StringBuffer();
        boolean done = false;
        for (int i = 0; i < streetNum.length() && done == false; i++)
        {
            char ch = streetNum.charAt(i);
            if (Character.isDigit(ch) == false)
            {
                done = true;
            }
            else
            {
                buffer.append(ch);
            }
        }
        Integer streetNumInt = new Integer(buffer.toString());
        return streetNumInt.toString();
    }

    /**
     * Converts a street number String into an int. The algorithm truncates off any non-numeric
     * digits.
     * 
     * @param streetNum
     * @return
     */
    private int convertStreetNum(String streetNum)
    {
        int streetNumInt;

        try
        {
            streetNumInt = Integer.parseInt(streetNum);
        }
        catch (NumberFormatException e)
        {
            StringBuffer buffer = new StringBuffer();
            boolean done = false;
            for (int i = 0; i < streetNum.length() && done == false; i++)
            {
                char ch = streetNum.charAt(i);
                if (ch >= '0' && ch <= '9')
                {
                    buffer.append(ch);
                }
                else
                {
                    done = true;
                }
            }
            streetNumInt = Integer.parseInt(buffer.toString());
        }

        return streetNumInt;
    }

    /**
     * @param errorkey
     * @return ActionForward
     */
    private ActionForward dispatchToErrorPage(String errorkey, ActionMapping aMapping, HttpServletRequest aRequest)
    {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorkey));
        saveErrors(aRequest, errors);
        return aMapping.findForward(UIConstants.FAILURE);
    }

    /**
     * @param ActionForward
     */
    private ActionForward dispatch(String returnPath)
    {
        System.out.println("returnPath = " + returnPath);
        ActionForward newForward = new ActionForward(returnPath);
        newForward.setRedirect(false);
        //		newForward.setName("warrantAssociateCreateUpdate.jsp");
        newForward.setName("juvenileAssociateForm");
        //		System.out.println("newForward = " + newForward);
        return newForward;
    }

}

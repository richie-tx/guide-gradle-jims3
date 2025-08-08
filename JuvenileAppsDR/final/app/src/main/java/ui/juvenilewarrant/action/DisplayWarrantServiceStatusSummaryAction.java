package ui.juvenilewarrant.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import naming.UIConstants;
//import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.contact.officer.OfficerProfile;

import ui.common.Address;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author ldeen
 *
 */
public class DisplayWarrantServiceStatusSummaryAction extends LookupDispatchAction
{

	/**
	 * @roseuid 41FFDF2F01B5
	 */
	public DisplayWarrantServiceStatusSummaryAction()
	{

	}

    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();
        buttonMap.put("button.next", UIConstants.NEXT);
        buttonMap.put("button.back", UIConstants.BACK);
        return buttonMap;
    }	
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 41FFDED7019B
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
		Address selectedAddress = null;
		
		// set current address from existing Associate if selected or individual input values
		if (!jwForm.getAddressId().equals("newAddress") && !jwForm.getSelectedAssociateAddressId().equals(""))
		{
		    selectedAddress = UIJuvenileHelper.getAddressFromAddressId(jwForm.getSelectedAssociateAddressId());
		    
				if (selectedAddress != null)
				{
					jwForm.setCurrentServiceAddress(
						UIUtil.formatAddress(
							selectedAddress.getStreetNum(),
							selectedAddress.getStreetName(),
							selectedAddress.getStreetType(),
							selectedAddress.getAptNum(),
							selectedAddress.getCity(),
							selectedAddress.getState(),
							selectedAddress.getZipCode(),
							selectedAddress.getAdditionalZipCode(),
							null));
					jwForm.setServiceAddressType(jwForm.getAddressType());
					jwForm.setCurrentServiceBadAddress("NO");
					if (jwForm.getSelectedBadAddress().equals(jwForm.getSelectedAssociateAddressId()))
					{
						jwForm.setCurrentServiceBadAddress("YES");
					}

				}
		}
				
		if (jwForm.getAddressId() != null && jwForm.getAddressId().equals("newAddress"))
		{
			jwForm.setCurrentServiceAddress(
				UIUtil.formatAddress(
					jwForm.getStreetNum(),
					jwForm.getStreetName(),
					jwForm.getStreetType(),
					jwForm.getApartmentNum(),
					jwForm.getCity(),
					jwForm.getState(),
					jwForm.getZipCode(),
					jwForm.getZipCode2(),
					null));
			jwForm.setServiceAddressType(jwForm.getAddressType());
			jwForm.setCurrentServiceBadAddress("NO");
		}
				
		
		// Parse date string and put it in currentServiceDate property on the form
		jwForm.initCurrentServiceDate();		

		jwForm.setAction("summary");
		String success = UIConstants.SUCCESS;

		return aMapping.findForward(success);
	}

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */	
	
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	    {
	    
	    	JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
	    	jwForm.clear();
	    	
	        return aMapping.findForward(UIConstants.BACK);
	    }

}

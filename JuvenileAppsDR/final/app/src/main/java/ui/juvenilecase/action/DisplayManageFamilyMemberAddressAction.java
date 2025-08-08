package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.address.reply.AddressResponseEvent;
import messaging.family.GetFamilyMemberAddressEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileMemberForm;

public class DisplayManageFamilyMemberAddressAction extends JIMSBaseAction
{
	/*
	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.CANCEL) );
	}

	/*
	 * 
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.BACK)) ;
	}

	/*
	 * 
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileMemberForm myForm = (JuvenileMemberForm)aForm;
		List newAddresses = new ArrayList();
	            if( myForm.getNewAddressList() != null && myForm.getNewAddressList().size() > 0 )
	            {	            	
	    		myForm.setNewAddressList(newAddresses);
	            }
//		myForm.clearAddress();

		 GetFamilyMemberAddressEvent event = (GetFamilyMemberAddressEvent)
					EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERADDRESS);
		  event.setMemberNum(myForm.getMemberNumber());
		CompositeResponse response = MessageUtil.postRequest(event);
		Map dataMap = MessageUtil.groupByTopic(response);

		ArrayList myNewList = new ArrayList();
		if( dataMap != null )
		{
			Collection addresses = (Collection)
					dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_ADDRESS_TOPIC);
			if( addresses != null && addresses.size() > 0 )
			{
				AddressResponseEvent responseEvt;
				Iterator iter = addresses.iterator();
				while( iter.hasNext() )
				{
					responseEvt = (AddressResponseEvent)iter.next();
					if( responseEvt != null )
					{
						JuvenileMemberForm.MemberAddress myAddress = 
								UIJuvenileHelper.getJuvMemberFormMemberAddresFROMAddrRespEvt(responseEvt);
						if( myAddress != null )
						{
							myNewList.add(myAddress);
						}
					}
				} // while
			}
			
		}

		myForm.setAddressList(UIJuvenileHelper.sortMemberAddressList(myNewList));
		if( myNewList.size() >0 ){

		JuvenileMemberForm.MemberAddress addrList = (JuvenileMemberForm.MemberAddress)myForm.getAddressList().get(0);
		
		    myForm.getCurrentAddress().setAdditionalZipCode(
				addrList.getAdditionalZipCode());
		    myForm.getCurrentAddress().setAddressTypeId(
				addrList.getAddressTypeId());
		    myForm.getCurrentAddress().setAptNumber(
				addrList.getAptNum());
		    myForm.getCurrentAddress().setCity(
				addrList.getCity());
		    myForm.getCurrentAddress().setCountyId(
				addrList.getCountyId());
		    myForm.getCurrentAddress().setStateId(
				addrList.getStateId());
		    myForm.getCurrentAddress().setStreetName(
				addrList.getStreetName());
		    myForm.getCurrentAddress().setStreetNumber(
				addrList.getStreetNum());
		    myForm.getCurrentAddress().setStreetTypeId(
				addrList.getStreetTypeId());
		    myForm.getCurrentAddress().setZipCode(
				addrList.getZipCode());
		    myForm.setAddressStatus(addrList.getValidated());
		    myForm.getCurrentAddress().setStreetNumSuffixId(
		    		addrList.getStreetNumSuffixId());
		}else{
		    
		   // no records found
		}
		
		//store current address as previous address
		myForm = UIJuvenileHelper.SetPreviousAddressFromCurrent(myForm);
		
		//US 187527
		String currentAction = aRequest.getParameter("action");
		if((currentAction == null || "".equals(currentAction)) && (myForm.getAction() == null || "".equals(myForm.getAction())))
		{
		    myForm.setAction("");
		}
		
		String source = aRequest.getParameter("source");
		if(source != null && !"".equals(source) && source.equalsIgnoreCase("MemberAddressBtn"))
		{
		    myForm.setAction("update");
		}
		

		return( aMapping.findForward(UIConstants.SUCCESS) );
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.link", "next");

		return keyMap;
	}

	@Override
	protected void addButtonMapping(Map keyMap)
	{
	}
}// END CLASS


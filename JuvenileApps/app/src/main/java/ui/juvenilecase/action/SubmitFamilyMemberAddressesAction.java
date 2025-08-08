package ui.juvenilecase.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.family.SaveFamilyMemberAdditionalInfoEvent;
import messaging.family.SaveFamilyMemberAddressEvent;
import messaging.family.SearchMemberAddressEvent;
import messaging.juvenilecase.reply.FamilyMemberAddressViewResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenile.JuvenileCore;

import ui.action.JIMSBaseAction;
import ui.common.AddressHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.security.SecurityUIHelper;

public class SubmitFamilyMemberAddressesAction extends JIMSBaseAction
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
		return( aMapping.findForward(UIConstants.BACK) );
	}

	/*
	 * 
	 */
	public ActionForward findSimilarAddresses(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		JuvenileFamilyForm myFamForm = UIJuvenileHelper.getFamilyForm(aRequest);
		SearchMemberAddressEvent event = new SearchMemberAddressEvent();

		JuvenileFamilyForm.Constellation myConstellation = myFamForm.getCurrentConstellation();
		if (myConstellation.getFamilyNumber() == null || "".equals(myConstellation.getFamilyNumber() ) ){
			return( aMapping.findForward("successSimilarAddresses") );
		} 
		event.setConstelltionId(myConstellation.getFamilyNumber());
		

		myFamForm.getCurrentConstellation().setMemberList(new ArrayList());

		List familiesMembersAddresses =
			MessageUtil.postRequestListFilter(event, FamilyMemberAddressViewResponseEvent.class);
		if( familiesMembersAddresses != null )
		{
			JuvenileFamilyForm.Constellation currentFamily = myFamForm.getCurrentConstellation();

			UIJuvenileHelper.setJuvFamilyFormFROMFamilyMemberAddressViewResponseEvent(
					currentFamily, familiesMembersAddresses);
			
			return( aMapping.findForward("successSimilarAddresses") );
		}

		sendToErrorPage(aRequest, "error.noAddressesToDisplay");

		return( aMapping.findForward("successSimilarAddresses") );
	}

	/*
	 * 
	 */
	public ActionForward selectMember(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileMemberForm myForm = (JuvenileMemberForm)aForm;
		String mySelectedMemberAddress = myForm.getSelectedValue();
		JuvenileFamilyForm myFamForm = UIJuvenileHelper.getFamilyForm(aRequest);
		
		JuvenileFamilyForm.Constellation myConstellation = myFamForm.getCurrentConstellation();

		if( notNullNotEmptyString(mySelectedMemberAddress) &&
				myConstellation.getMemberList() != null )
		{
			Iterator iter = myConstellation.getMemberList().iterator();
			while( iter.hasNext() )
			{
				JuvenileFamilyForm.MemberList myMember = (JuvenileFamilyForm.MemberList)iter.next();
				if( myMember.getCurrentAddress().getMemberAddressId().equalsIgnoreCase(
						mySelectedMemberAddress) )
				{
					myForm.getCurrentAddress().setAdditionalZipCode(
							myMember.getCurrentAddress().getAdditionalZipCode());
					myForm.getCurrentAddress().setAddressTypeId(
							myMember.getCurrentAddress().getAddressTypeId());
					myForm.getCurrentAddress().setAptNumber(
							myMember.getCurrentAddress().getAptNumber());
					myForm.getCurrentAddress().setCity(
							myMember.getCurrentAddress().getCity());
					myForm.getCurrentAddress().setCountyId(
							myMember.getCurrentAddress().getCountyId());
					myForm.getCurrentAddress().setStateId(
							myMember.getCurrentAddress().getStateId());
					myForm.getCurrentAddress().setStreetName(
							myMember.getCurrentAddress().getStreetName());
					myForm.getCurrentAddress().setStreetNumber(
							myMember.getCurrentAddress().getStreetNumber());
					myForm.getCurrentAddress().setStreetNumSuffixId(
							myMember.getCurrentAddress().getStreetNumSuffixId());
					myForm.getCurrentAddress().setStreetTypeId(
							myMember.getCurrentAddress().getStreetTypeId());
					myForm.getCurrentAddress().setZipCode(
							myMember.getCurrentAddress().getZipCode());
					myForm.setAddressStatus(myMember.getCurrentAddress().getValidated());
				}
			}
		}

		return( aMapping.findForward(UIConstants.ADD_SUCCESS) );
	}

	/*
	 * 
	 */
    public ActionForward removeAddress(ActionMapping aMapping, ActionForm aForm,
            HttpServletRequest aRequest, HttpServletResponse aResponse)
 {
       JuvenileMemberForm myForm = (JuvenileMemberForm)aForm;
       String myAddressPos = myForm.getSelectedValue();
       
       if( notNullNotEmptyString(myAddressPos) )
       {
            if( myForm.getNewAddressList() != null && myForm.getNewAddressList().size() > 0 )
            {
                 ((List)myForm.getNewAddressList()).remove( Integer.parseInt(myAddressPos) );
            }
       }

       myForm.clearAddress();

       return( aMapping.findForward(UIConstants.ADD_SUCCESS) );
 }

 /*
  * 
  */
 public ActionForward addAddress(ActionMapping aMapping, ActionForm aForm,
            HttpServletRequest aRequest, HttpServletResponse aResponse)
 {
       JuvenileMemberForm myForm = (JuvenileMemberForm)aForm;
       myForm.setAction(UIConstants.UPDATE);
       JuvenileMemberForm.MemberAddress myAddress = myForm.getCurrentAddress();
       
       String streetNumber = myAddress.getStreetNum();
       StringBuffer buffer = new StringBuffer();
       for (int i = 0; i < streetNumber.length(); i++) {
    	    char ch = streetNumber.charAt(i);
  			if (Character.isDigit(ch) == false) {
  				myAddress.setValidated("N");
  				break;
  			}
  			else {
  				if (ch >= '0' && ch <= '9') {
  				   buffer.append(ch);
  				} 
  				   AddressHelper.validateAddress(myAddress);
			}
  	   }
//       }		
//       AddressHelper.validateAddress(myAddress);
       if( myAddress != null )
       {
            if( notNullNotEmptyString(myAddress.getStreetName()) )
            {
                 if( myForm.getNewAddressList() == null )
                 {
                       myForm.setNewAddressList(new ArrayList());
                 }
                 myForm.getNewAddressList().add(myAddress);
            }
       }

      myForm.setNewAddressList(UIJuvenileHelper.sortMemberAddressList((ArrayList)myForm.getNewAddressList()));      
      
     myForm.clearAddress();
       
       return( aMapping.findForward(UIConstants.ADD_SUCCESS) );
 }

     /*
      * 
      */
     public ActionForward update(ActionMapping aMapping, ActionForm aForm,
                HttpServletRequest aRequest, HttpServletResponse aResponse)
     {
           JuvenileMemberForm myForm = (JuvenileMemberForm)aForm;     
    
           SaveFamilyMemberAdditionalInfoEvent event = (SaveFamilyMemberAdditionalInfoEvent)
                     EventFactory.getInstance(JuvenileFamilyControllerServiceNames.SAVEFAMILYMEMBERADDITIONALINFO);
           event.setMemberId(myForm.getMemberNumber());
    
           List myAddresses = myForm.getNewAddressList();
           if( myAddresses != null && myAddresses.size() > 0 )
           {
                JuvenileMemberForm.MemberAddress mySaveAddress;
                Iterator iter = myAddresses.iterator();
                while( iter.hasNext() )
                {
                     mySaveAddress = (JuvenileMemberForm.MemberAddress)iter.next();
                     if( mySaveAddress.getMemberAddressId() == null ||
                                mySaveAddress.getMemberAddressId().equals("") )
                     {
                         //repopulate current address as it was cleared by "addAddress" ActionForward
                         myForm.setCurrentAddress(mySaveAddress);
                         
                           SaveFamilyMemberAddressEvent saveAddressEvent = 
                                     UIJuvenileHelper.getSaveFamilyMemberAddressEvent(mySaveAddress);
                           event.addRequest(saveAddressEvent);
                     }
                } // while
           }
           
           
           MessageUtil.postRequest(event);
           myForm.setNewAddressList(new ArrayList());
           
           String familyMemberNumber = myForm.getMemberNumber() != null && !"".equals(myForm.getMemberNumber()) ? myForm.getMemberNumber() : null;
           String juvenileId = myForm.getJuvenileNumber() != null && !"".equals(myForm.getJuvenileNumber()) ? myForm.getJuvenileNumber() : null;
           String zipCode = myForm.getCurrentAddress().getZipCode() != null && !"".equals(myForm.getCurrentAddress().getZipCode()) ? myForm.getCurrentAddress().getZipCode() : null;
           
           boolean hasAddressChanged = UIJuvenileHelper.memberAddressChanged(myForm);
           
           if(hasAddressChanged){
               
        	   myForm.setHasFamilyMemberAddressChanged(hasAddressChanged);
        	   
        	   DocketEventResponseEvent dktEvent = UIJuvenileHelper.getJuvDocketEvent(myForm.getJuvenileNumber());
        	   if (dktEvent != null)
        	   {
        	       UIJuvenileHelper.sendAddressChangeEmailNofification(myForm);
        	   }
        	   
        	   //US 170153 - update JuvenileZip field in JJS_MS_Juvenile with primary guardian zip code
        	   UIJuvenileHelper.populateJuvenileTableWithZipCode(juvenileId, familyMemberNumber, zipCode);	   
    	   
           }
           
           return( aMapping.findForward(UIConstants.SUCCESS) );
     }

	/*
	 * 
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  (str.length() > 0) ) ;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.addToList", "addAddress");
		keyMap.put("button.remove", "removeAddress");
		keyMap.put("button.update", "update");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.select", "selectMember");
		keyMap.put("button.find", "findSimilarAddresses");
		return keyMap;
	}

	@Override
	protected void addButtonMapping(Map keyMap)
	{
	}
}


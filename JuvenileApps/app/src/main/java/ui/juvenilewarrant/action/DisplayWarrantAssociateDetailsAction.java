package ui.juvenilewarrant.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilewarrant.GetJuvenileAssociateDataEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.pattern.ui.IFormDirector;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileWarrantConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.SocialSecurity;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileMemberForm.MemberContact;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.juvenilewarrant.form.JuvenileAssociateBean;
import ui.juvenilewarrant.form.JuvenileAssociateForm;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.juvenilewarrant.helper.JuvenileWarrantFormDirector;

/**
 * @author ryoung
 * 
 */
public class DisplayWarrantAssociateDetailsAction extends Action
{

    /**
     * @roseuid 41E6CCE20232
     */
    public DisplayWarrantAssociateDetailsAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 41E59C150001
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileAssociateForm jaForm = (JuvenileAssociateForm) aForm;
 
        String relationshipId = aRequest.getParameter("relationshipId");
        String associateNum = aRequest.getParameter("associateNumber");
        String juvNumber = aRequest.getParameter("juvenileNumber");
        
        jaForm.clear();
        
//        GetFamilyMembersByJuvenileNumberEvent reqEvent = (GetFamilyMembersByJuvenileNumberEvent) 
//        	EventFactory.getInstance( JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERSBYJUVENILENUMBER);
//        reqEvent.setJuvenileNum(juvNumber);
//        
//        MessageUtil.postRequest(reqEvent);
        
        JuvenileAssociateBean famMember = null;
        
        if(juvNumber != null && !juvNumber.equals("") && associateNum != null && !associateNum.equals("")){                
        
            List<JuvenileAssociateBean> familyMemberList = new ArrayList();
            familyMemberList = UIJuvenileWarrantHelper.getAssociates(juvNumber);
            
            Iterator iter = familyMemberList.iterator();           
                        
            famMember = UIJuvenileWarrantHelper.GetJuvenileFamilyMemberById(juvNumber, associateNum);
          	
          	FamilyMemberDetailResponseEvent memberDetail = null;
          	
          	if(famMember != null && famMember.getAssociateNum() != null && !famMember.getAssociateNum().equals("")){
          	    
              	memberDetail = UIJuvenileWarrantHelper.getFamilyMemberDetails(famMember.getAssociateNum());
              	
              	if(memberDetail != null){
              	  famMember.setDateOfBirth(memberDetail.getDateOfBirth());
              	  famMember.setSsn(new SocialSecurity(memberDetail.getSsn(), true));
              	  famMember.setSexId(memberDetail.getSexId());          	  
              	  famMember.setEthnicityList(CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_ETHNICITY,true));
              	  famMember.setEthnicityId(memberDetail.getEthnicityId());     
              	  //set race/ethnicity on form
              	  jaForm.setEthnicity(famMember.getEthnicity());
              	}              	
                    
                  	List addressList = UIJuvenileWarrantHelper.getFamilyMemberAddresses(famMember.getAssociateNum());      	
                  	famMember.setAssociateAddresses(UIJuvenileHelper.sortMemberAddressList(addressList));
                  	
                  	List<MemberContact> contactList = UIJuvenileWarrantHelper.getFamilyMemberContacts(famMember.getAssociateNum());
                  	contactList = UIJuvenileHelper.sortMemberContactList(contactList);
                  	
                  	Iterator<MemberContact> contactIter = contactList.iterator();
                  	
                  	while(contactIter.hasNext()){
                  	    
                  	    MemberContact contact = (MemberContact)contactIter.next();
                  	    
                  	    if(contact.getContactType() != null){
                  		
                  		 if(contact.getContactType().toLowerCase().equals("mobile")){
                       		famMember.setCellPhoneNum(contact.getContactPhoneNumber());
                       	    }
                       	    
                       	    if(contact.getContactType().toLowerCase().equals("home")){
                       		famMember.setHomePhoneNum(contact.getContactPhoneNumber());
                       	    }
                           	    
                           	    if(contact.getContactType().toLowerCase().equals("email")){
                       		famMember.setEmail1(contact.getEmailAddress());
                       	    }
                  	    }
                  	   
                      	    
                      	    if(contact.getEmailContactType() != null && contact.getEmailContactType().toLowerCase().equals("personal")){
                		famMember.setEmail2(contact.getEmailAddress());
                	    }
                  	    
                  	}
              	
      		}
        }
      	
//        String flowInd = jaForm.getFlowInd();
/*        if (associateNum == null || associateNum.equals("")){
        	if (flowInd != null && flowInd.equalsIgnoreCase("popupWindow")){
        		associateNum = jaForm.getAssociateNum();
        	}
        } */
      	
      	         
      
        jaForm.setAssociateNum(associateNum);

        IFormDirector director = new JuvenileWarrantFormDirector(aRequest);
        JuvenileWarrantForm warrantForm = (JuvenileWarrantForm) director.getForm();

        jaForm.setWarrantNum(warrantForm.getWarrantNum());
        JuvenileAssociateBean associateBean = null;

        // for Initiate screens, the associate does not have a number yet...
        // and all the associate data has already been brought over.
        // and is a part of juvenileWarrantform as the associate bean instance
        // use the relationship to lookup the associate bean.

        if (relationshipId != null)
        {
            associateBean = warrantForm.getAssociateByRelationshipId(relationshipId);
            jaForm.setAssociateProperties(famMember);
        }
        else if (PDJuvenileWarrantConstants.MOTHER_ASSOCIATE_NUM.equals(associateNum)
                || PDJuvenileWarrantConstants.FATHER_ASSOCIATE_NUM.equals(associateNum)
                || PDJuvenileWarrantConstants.OTHER_ASSOCIATE_NUM.equals(associateNum))
        {
            associateBean = warrantForm.getAssociateByRelationshipId(associateNum);
            jaForm.setAssociateProperties(famMember);
        }
        else
        {
            GetJuvenileAssociateDataEvent assoRequestEvent = (GetJuvenileAssociateDataEvent) EventFactory
                    .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEASSOCIATEDATA);

            assoRequestEvent.setAssociateNumber(associateNum);
            CompositeResponse response = MessageUtil.postRequest(assoRequestEvent);

            jaForm.setProperties(response);
        }

        return aMapping.findForward(UIConstants.SUCCESS);
    }

    private void retrieveAssociateAddress(JuvenileAssociateForm jaForm, ActionMapping aMapping,
            JuvenileAssociateBean associateBean)
    {
        GetJuvenileAssociateDataEvent requestEvent = (GetJuvenileAssociateDataEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEASSOCIATEDATA);

        requestEvent.setAssociateNumber(jaForm.getAssociateNum());

        /* fetch Associate Data based on warrantNum & associateNum */
        CompositeResponse associateData = MessageUtil.postRequest(requestEvent);
        jaForm.clear();

        /* error handling per meeting of 2/8/05 */
        if (associateData == null)
        {
            aMapping.findForward(UIConstants.FAILURE);
        }

        // Get associate records out of response event & copy over to the form
        Collection associateRecords = MessageUtil.compositeToCollection(associateData, JuvenileAssociateResponseEvent.class);
        Iterator i = associateRecords.iterator();
        if (i.hasNext())
        {
            JuvenileAssociateResponseEvent jaResponseEvent = (JuvenileAssociateResponseEvent) i.next();
            associateBean.populateFromEventAttributes(jaResponseEvent);
        }

        /* Get the address records out of response event & copy over to the form */
        associateBean.clearAssociateAddresses();

        Collection addressRecords = MessageUtil.compositeToCollection(associateData, JuvenileAssociateAddressResponseEvent.class);
        i = addressRecords.iterator();
        while (i.hasNext())
        {
            JuvenileAssociateAddressResponseEvent addressResponseEvent = (JuvenileAssociateAddressResponseEvent) i.next();
            associateBean.insertAddress(addressResponseEvent);
        }
    }
}

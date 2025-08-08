//Source file: C:\\views\\Security\\app\\src\\ui\\security\\inquiries\\action\\DisplaySecurityInquiriesAgencyDetailsAction.java

package ui.security.inquiries.action;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.user.UserResponseEventComparator;
import messaging.inquiries.GetAgencySecurityInfoEvent;
import messaging.security.inquiries.reply.AgencySecurityInfoResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.InquiriesAdminControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.SecurityUIHelper;
import ui.security.inquiries.form.SecurityInquiriesForm;

public class DisplaySecurityInquiriesAgencyDetailsAction extends Action
{
   
   /**
    * @roseuid 44E9D1DD030D
    */
   public DisplaySecurityInquiriesAgencyDetailsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 44D2218C02BB
    */
   public ActionForward execute(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
		SecurityInquiriesForm securityInquiriesForm = (SecurityInquiriesForm) aForm; 
		String agencyId = securityInquiriesForm.getAgencyId();
		
		GetAgencySecurityInfoEvent requestEvent =
			(GetAgencySecurityInfoEvent) EventFactory.getInstance(InquiriesAdminControllerServiceNames.GETAGENCYSECURITYINFO);
	
		requestEvent.setAgencyId(agencyId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
		
		CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
		AgencySecurityInfoResponseEvent aRespEvent = (AgencySecurityInfoResponseEvent) MessageUtil.filterComposite(replyEvent, AgencySecurityInfoResponseEvent.class);	
	
		if (aRespEvent != null)
		{
			securityInquiriesForm.setAgencyName(aRespEvent.getAgencyName());
			securityInquiriesForm.setAgencyId(aRespEvent.getAgencyId());
			securityInquiriesForm.setSaUsers(aRespEvent.getSAUsers());
			if (securityInquiriesForm.getSaUsers() != null){
				Collections.sort((List) securityInquiriesForm.getSaUsers(),new UserResponseEventComparator());
			}
			securityInquiriesForm.setRoles(SecurityUIHelper.sortRoleNames(aRespEvent.getRoles()));
			securityInquiriesForm.setDepartments(SecurityUIHelper.sortDepartments(aRespEvent.getDepartments()));
			Collection depts = securityInquiriesForm.getDepartments();
			if (depts != null && !(depts.isEmpty())){
				Iterator iterDept = depts.iterator();
				while (iterDept.hasNext()){
					DepartmentResponseEvent deptResp = (DepartmentResponseEvent) iterDept.next();
					Collection asaUsers = deptResp.getAsaUsers();	
					if (asaUsers != null){
						Collections.sort((List) asaUsers, new UserResponseEventComparator());
					}	
					Collection laUsers = deptResp.getLiasonUsers();	
					if (laUsers != null){
						Collections.sort((List) laUsers, new UserResponseEventComparator());
					}	
				}
			}			
		}
		return aMapping.findForward(UIConstants.SUCCESS);
   }
}

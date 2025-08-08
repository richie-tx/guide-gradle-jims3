//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenileContactListAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetActiveFamilyConstellationEvent;
import messaging.family.GetFamilyConstellationDetailsEvent;
import messaging.juvenile.GetJuvenileContactsEvent;
import messaging.juvenile.reply.JuvenileContactResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenile.JuvenileHelper;

import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileContactForm;
import ui.juvenilecase.form.JuvenileProfileForm;


public class DisplayJuvenileContactListAction extends Action 
{
   
   /**
    * @roseuid 42A5E1CC008C
    */
   public DisplayJuvenileContactListAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42A5DD8F00AB
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {

		String returnVal = UIConstants.FAILURE;
	
		Collection contacts = new ArrayList();
		JuvenileContactForm jcForm = (JuvenileContactForm) aForm;
		GetJuvenileContactsEvent contactsEvent = (GetJuvenileContactsEvent)EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILECONTACTS);
		JuvenileProfileForm profileForm = UIJuvenileHelper.getHeaderForm(aRequest);
		contactsEvent.setJuvenileNum(profileForm.getJuvenileNum());
		CompositeResponse response = UIJuvenileHelper.getCompositeResponse(contactsEvent);
		contacts = (Collection) UIJuvenileHelper.fetchCollection(response, PDJuvenileCaseConstants.JUVENILE_CONTACT_TOPIC);
		
		int size = 0;
		if (contacts == null){
			return aMapping.findForward(returnVal);
		}else{
			Collections.sort((List)contacts);
			Collections.reverse((List)contacts);
			returnVal = UIConstants.SUCCESS;
			 size = contacts.size();
		}
		// start processing	
		jcForm.clear();
		jcForm.setAction(UIConstants.VIEW);
		jcForm.setSecondaryAction(UIConstants.SUMMARY);
		jcForm.reset(aMapping, aRequest);
		jcForm.setJuvenileNum(profileForm.getJuvenileNum());
		jcForm.setContacts(contacts);
		int detVisitContactsTotal = JuvenileHelper.getDetVisitContactsOnlyCount(contacts);
		int detVisitFamilyTotal = UIJuvenileFamilyHelper.getDetVisitFamMemberCount(profileForm.getJuvenileNum());
		boolean daVisit = JuvenileHelper.isDetDefenseAttorneyVisit(contacts);
		boolean visitorCapRemoved = JuvenileHelper.bypassDetentionVisitorCap(profileForm.getJuvenileNum());
		jcForm.setDaVisit(daVisit);
		jcForm.setVisitorCapRemoved(visitorCapRemoved);
		int detTotalCount = detVisitContactsTotal + detVisitFamilyTotal;
		jcForm.setDetVisitContactsCount(detTotalCount);
//		if (size > 0)
//		{
//			jcForm.setContacts(contacts);
//			success = UIConstants.LISTSUCCESS;
//		}else if (size == 0){
//			// no result found error
//			ActionErrors errors = new ActionErrors();
//			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.noRecords"));
//			saveErrors(aRequest, errors);
//			return aMapping.findForward(UIConstants.SEARCH_FAILURE);
//		}
		
		return aMapping.findForward(returnVal);
	   }
   	
}

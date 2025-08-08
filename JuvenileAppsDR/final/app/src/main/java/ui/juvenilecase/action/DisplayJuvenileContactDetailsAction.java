//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenileContactDetailsAction.java

package ui.juvenilecase.action;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.reply.JuvenileContactResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.JuvenileContactForm;

public class DisplayJuvenileContactDetailsAction extends Action  
{
   
   /**
    * @roseuid 42A5E1CA029F
    */
   public DisplayJuvenileContactDetailsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42A5DD8F02B1
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
   {
		ActionForward forward = new ActionForward();
		JuvenileContactForm jcForm = (JuvenileContactForm)aForm;
		
			
		// Get the contactNum off the request (set in the hyperlink)
		String contactNum = aRequest.getParameter("contactNum");
		if (contactNum == null || contactNum.equals("")){
			// some problem
			aMapping.findForward(UIConstants.FAILURE);
		}
					
		// get contact response event
		JuvenileContactResponseEvent jcResponseEvent = getJuvenileContactResponseEvent(contactNum, jcForm.getContacts());
		jcForm.setContactProperties(jcResponseEvent);
		jcForm.setAddressStatus(jcResponseEvent.getValidated());
		jcForm.setAction(UIConstants.VIEW);		
		jcForm.setSecondaryAction(UIConstants.SUMMARY);
		
		if (jcResponseEvent != null ) {
		    aRequest.getSession().setAttribute("jContactResponse", jcResponseEvent);
		}
		forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
   }
   
   private JuvenileContactResponseEvent getJuvenileContactResponseEvent(String contactNum, Collection contacts){
   	
		JuvenileContactResponseEvent contactRespEvent = null;
		Iterator iter = contacts.iterator();
		while(iter.hasNext()){
			contactRespEvent = (JuvenileContactResponseEvent)iter.next();
			if(contactRespEvent.getContactNum().equals(contactNum)){
				break;
			}
		}
		return contactRespEvent;
   }
   
}

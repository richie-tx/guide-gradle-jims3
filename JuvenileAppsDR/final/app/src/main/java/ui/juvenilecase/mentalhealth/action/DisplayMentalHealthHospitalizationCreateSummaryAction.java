//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\DisplayMentalHealthHospitalizationCreateSummaryAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.HospitalizationAdmissionTypeCdResponseEvent;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.Name;
import ui.juvenilecase.mentalhealth.form.HospitalizationForm;
import ui.security.SecurityUIHelper;

public class DisplayMentalHealthHospitalizationCreateSummaryAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 45B0DED60277
    */
   public DisplayMentalHealthHospitalizationCreateSummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 45B0CB760063
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	HospitalizationForm hpf = (HospitalizationForm)aForm;
/*
   	String comments = hpf.getHospRec().getHospitalizationReason();
	if (!comments.equals("") && comments != null) {
		IUserInfo user = SecurityUIHelper.getUser();
		Name userName = new Name(user.getFirstName(),"",user.getLastName());
		hpf.getHospRec().setHospitalizationReason(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
	}
	
*/   	hpf.setActionType("summary");
   	HospitalizationForm.HospitalizationRecord hospRec = hpf.getHospRec();
   	hospRec.setPhysicianPhoneStr(hospRec.getPhysicianPhone().toString());
   	StringBuffer buff = new StringBuffer();
   	buff.append(hospRec.getPhysicianLastName());
   	if(!hospRec.getPhysicianLastName().equals("") && !hospRec.getPhysicianFirstName().equals(""))
   		buff.append(", ");
   	buff.append(hospRec.getPhysicianFirstName() + " " + hospRec.getPhysicianMiddleName());
   	hospRec.setPhysicianNameStr(buff.toString());
   	hospRec.setAdmissionType(getAdmissionType(hpf, hospRec.getAdmissionTypeCd()));
   	ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
	return forward;
   }
   private String getAdmissionType(HospitalizationForm hpf, String admissionTypeId)   
   {
	   	Collection coll = hpf.getAdmissionTypes();
		Iterator iter = coll.iterator();
		while(iter.hasNext())
		{
			HospitalizationAdmissionTypeCdResponseEvent resp = ( HospitalizationAdmissionTypeCdResponseEvent)iter.next();
			 if(resp.getAdmissionTypeCode().equals(admissionTypeId))
			 {
			 	return resp.getDescription();
			 }
		}
   	 return null;
   }
   
   public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

   public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}
   
   /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");		
		return keyMap;
	}
}

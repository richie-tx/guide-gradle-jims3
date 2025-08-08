//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\SubmitMentalHealthHospitalizationCreateAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.mentalhealth.CreateHospitalizationHistoryEvent;
import messaging.mentalhealth.GetMentalHealthHospitalizationListEvent;
import messaging.mentalhealth.reply.HospitalizationResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.mentalhealth.form.HospitalizationForm;

public class SubmitMentalHealthHospitalizationCreateAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 45B0DED603A1
    */
   public SubmitMentalHealthHospitalizationCreateAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 45B0CB76009F
    */
   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   	HospitalizationForm hpf = (HospitalizationForm)aForm;
	   	HospitalizationForm.HospitalizationRecord hpRec = hpf.getHospRec();
	   	CreateHospitalizationHistoryEvent createEvent = (CreateHospitalizationHistoryEvent)EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.CREATEHOSPITALIZATIONHISTORY);
	   	createEvent.setFacilityName(hpRec.getFacilityName());
	   //	String admissionDate = hpRec.getAdmissionDate()+"/"
	   	createEvent.setAdmissionDate(DateUtil.stringToDate(hpRec.getAdmissionDate(),"MM/dd/yyyy"));
	   	createEvent.setAdmissionType(hpRec.getAdmissionTypeCd());
	   	createEvent.setHospitalizationReason(hpRec.getHospitalizationReason());
	   	StringBuffer buff = new StringBuffer();
	   	buff.append(hpRec.getPhysicianLastName());
	   	if(!hpRec.getPhysicianLastName().equals("") && !hpRec.getPhysicianFirstName().equals(""))
	   		buff.append(", ");
	   	buff.append(hpRec.getPhysicianFirstName() + " " + hpRec.getPhysicianMiddleName());
	   	createEvent.setPhysicianName(buff.toString());
	   	createEvent.setPhysicianPhone(hpRec.getPhysicianPhone().getPhoneNumber());
	   	createEvent.setReleaseDate(DateUtil.stringToDate(hpRec.getReleaseDate(),"MM/dd/yyyy"));
	   	createEvent.setJuvenileNum(hpf.getJuvNum());
	   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(createEvent);	
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response); 
		hpf.setActionType("confirm");
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;   	 
	  
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
   
   public ActionForward returnToHosp(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
   		HospitalizationForm hpf = (HospitalizationForm)aForm;
	   	GetMentalHealthHospitalizationListEvent listEvent = (GetMentalHealthHospitalizationListEvent)EventFactory.getInstance("GetMentalHealthHospitalizationList");
	   	listEvent.setJuvenileNum(hpf.getJuvNum());
		IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch1.postEvent(listEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch1.getReply();
		Collection coll = MessageUtil.compositeToCollection(compositeResponse, HospitalizationResponseEvent.class);
		Collections.sort((List)coll);
		hpf.setHospitalizationList(coll);		
		ActionForward forward = aMapping.findForward(UIConstants.RETURN_SUCCESS);
		return forward;
	}
   /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");		
		keyMap.put("button.returnToHospitalization", "returnToHosp");
		return keyMap;
	}
}

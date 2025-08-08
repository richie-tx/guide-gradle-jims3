//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\HandleMentalHealthHospitalizationSelectionAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetJuvenileAdmissionTypeCdEvent;
import messaging.mentalhealth.GetMentalHealthHospitalizationDataEvent;
import messaging.codetable.criminal.reply.HospitalizationAdmissionTypeCdResponseEvent;
import naming.CodeTableControllerServiceNames;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;
import naming.JuvenileMentalHealthControllerServiceNames;
import messaging.mentalhealth.reply.HospitalizationResponseEvent;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.PhoneNumber;
import ui.juvenilecase.mentalhealth.form.HospitalizationForm;

public class HandleMentalHealthHospitalizationSelectionAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 45B0DED60343
    */
   public HandleMentalHealthHospitalizationSelectionAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 45B0CB6C007E
    */
   public ActionForward addHospitalizationHistory(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		HospitalizationForm hpf = (HospitalizationForm)aForm;
   		hpf.clear();
   		Collection admTypes = getAdmissionTypes(hpf);
		if (admTypes != null)
		{
			Collections.sort((List)admTypes);
			hpf.setAdmissionTypes(admTypes);
		}
	   	ActionForward forward = aMapping.findForward(UIConstants.ADD_SUCCESS);
	    return forward;
   }
   public ActionForward view(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
   		HospitalizationForm hpf = (HospitalizationForm)aForm;
   		GetMentalHealthHospitalizationDataEvent dataEvent = (GetMentalHealthHospitalizationDataEvent)EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHHOSPITALIZATIONDATA);
   		dataEvent.setHospitalizationId(hpf.getSelectedValue());
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(dataEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Object obj = MessageUtil.filterComposite(compositeResponse, HospitalizationResponseEvent.class);
		HospitalizationForm.HospitalizationRecord hpRec = hpf.getHospRec();
		if(obj != null)
		{
			HospitalizationResponseEvent hosp = (HospitalizationResponseEvent)obj;
			hpRec.setAdmissionDate(DateUtil.dateToString(hosp.getAdmissionDate(), "MM/dd/yyyy"));
			hpRec.setReleaseDate(DateUtil.dateToString(hosp.getReleaseDate(), "MM/dd/yyyy"));
			hpRec.setFacilityName(hosp.getFacilityName());
			hpRec.setPhysicianNameStr(hosp.getAdmittingPhysicianName());
			PhoneNumber ph = new PhoneNumber(hosp.getPhysicianPhone());
			hpRec.setPhysicianPhoneStr(ph.getFormattedPhoneNumber());
			hpRec.setHospitalizationReason(hosp.getHospitalizationReason());
			if(hosp.getAdmissionType()!= null && !("".equals(hosp.getAdmissionType())))
			{
				Collection coll = new ArrayList();
				if(hpf.getAdmissionTypes()==null || hpf.getAdmissionTypes().size()==0)
					coll = getAdmissionTypes(hpf);				
				else
					coll = hpf.getAdmissionTypes();				
				
				Iterator iter = coll.iterator();
				while (iter.hasNext())
				{
					HospitalizationAdmissionTypeCdResponseEvent admResp = (HospitalizationAdmissionTypeCdResponseEvent)iter.next();
					if(admResp.getAdmissionTypeCode().equals(hosp.getAdmissionType()))
						hpRec.setAdmissionType(admResp.getDescription());	
				}
				
			}
		}
		hpf.setActionType("view");
		ActionForward forward = aMapping.findForward(UIConstants.VIEW_SUCCESS);
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
   private Collection getAdmissionTypes(HospitalizationForm hpf)
   {
   		GetJuvenileAdmissionTypeCdEvent dataEvent = (GetJuvenileAdmissionTypeCdEvent)EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEADMISSIONTYPECD);
		dataEvent.setCategoryId("MH");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(dataEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Collection admTypes = MessageUtil.compositeToCollection(compositeResponse, HospitalizationAdmissionTypeCdResponseEvent.class);
   		return admTypes;
   }
   /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.addHospitalizationHistory", "addHospitalizationHistory");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");		
		keyMap.put("button.view", "view");
		return keyMap;
	}
}

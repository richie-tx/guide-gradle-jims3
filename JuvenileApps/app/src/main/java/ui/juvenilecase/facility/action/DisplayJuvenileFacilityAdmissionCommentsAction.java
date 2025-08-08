package ui.juvenilecase.facility.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.facility.reply.JuvenileFacilityAdmissionCommentsResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.facility.form.AdmitReleaseForm;

public class DisplayJuvenileFacilityAdmissionCommentsAction extends LookupDispatchAction{

	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.link", "link");
		return keyMap;
	}
	
	/**
	 * fetch all the special attention details for the juvenile.
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward link(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		AdmitReleaseForm form =(AdmitReleaseForm) aForm;
		Collection<JuvenileFacilityAdmissionCommentsResponseEvent> comments = JuvenileFacilityHelper.getAllJuvFacAdmissionComments(form.getJuvenileNum());
		form.setFacilityAdmissionComments(comments);
		return( aMapping.findForward(UIConstants.SUCCESS) );
	}
	
}

package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.AddJuvenileAliasEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileAliasInfoForm;

public class SubmitJuvenileAliasInfoCreateAction extends Action {

	public static final String ALIAS_INFO_FORM = "juvenileAliasInfoForm";
	public final static String JUVENILE_DETAIL_FORM = "JuvenileDetailForm";
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward forward = null;
		if(request.getParameter("submitAction").equals("Cancel")){
			forward = mapping.findForward(UIConstants.CANCEL);
		} else if(request.getParameter("main") == null){
			JuvenileAliasInfoForm juvenileAliasInfoForm = (JuvenileAliasInfoForm)request.getSession().getAttribute(ALIAS_INFO_FORM);
			AddJuvenileAliasEvent juvEvent = (AddJuvenileAliasEvent) EventFactory.
														getInstance(JuvenileControllerServiceNames.ADDJUVENILEALIAS);
			JuvenileProfileDetailResponseEvent juvDetails = (JuvenileProfileDetailResponseEvent)request.getSession().getAttribute(JUVENILE_DETAIL_FORM);
			if(juvenileAliasInfoForm.getAction() != null && juvenileAliasInfoForm.getAction().equals(UIConstants.DELETE)){
				juvEvent.setOID(UIJuvenileHelper.decodeOID((juvenileAliasInfoForm.getId())));
				juvEvent.setDeleteFlag(true);
				// Code tables are not in use in Legacy. Hence, hard coding ALIAS as default.
				juvEvent.setJuvenileType("A");
				juvEvent.setJuvenileNum(juvenileAliasInfoForm.getJuvenileNum());
			} else {
				juvEvent.setJuvenileNum(juvDetails.getJuvenileNum());
				juvEvent.setRaceId(juvDetails.getRaceId());
				juvEvent.setSexId(juvDetails.getSexId());
				juvEvent.setDateOfBirth(juvDetails.getDateOfBirth());
				
				
				juvEvent.setFirstName(juvenileAliasInfoForm.getFirstName());
				juvEvent.setLastName(juvenileAliasInfoForm.getLastName());
				juvEvent.setMiddleName(juvenileAliasInfoForm.getMiddleName());
				juvEvent.setAliasNotes(juvenileAliasInfoForm.getNotes());
				juvEvent.setJuvenileType(juvenileAliasInfoForm.getJuvenileType());
				juvEvent.setAliasEntryDate(DateUtil.getCurrentDate());
			}
			

			MessageUtil.postRequest(juvEvent);
			if(juvenileAliasInfoForm.getAction() != null && juvenileAliasInfoForm.getAction().equals(UIConstants.DELETE)){
				juvenileAliasInfoForm.setAction(UIConstants.DELETE_SUCCESS);
			} else {
				juvenileAliasInfoForm.setAction(UIConstants.CONFIRM);
			} 
			request.getSession().setAttribute(ALIAS_INFO_FORM,juvenileAliasInfoForm);
			forward = mapping.findForward(UIConstants.SUCCESS);
		} else {
			
			forward = mapping.findForward(UIConstants.CANCEL);
		}
		return forward;
	}

}

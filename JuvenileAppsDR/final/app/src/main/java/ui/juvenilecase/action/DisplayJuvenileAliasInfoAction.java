package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.JuvenileAliasInfoForm;

public class DisplayJuvenileAliasInfoAction extends Action {

	public static final String ALIAS_INFO_FORM = "juvenileAliasInfoForm"; 

	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		// Create a new form bean and set it in session
		JuvenileAliasInfoForm juvenileAliasForm = new JuvenileAliasInfoForm();

		HttpSession session = request.getSession();
		
		session.setAttribute(ALIAS_INFO_FORM, juvenileAliasForm);
		return mapping.findForward("success");
		
		
	}

	
	
}

package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import naming.PDCodeTableConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.juvenilecase.form.JuvenileMainForm;


public class DisplayJuvenileJISInfoAction extends Action {

	public static final String ALIAS_INFO_FORM = "juvenileAliasInfoForm"; 

	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		// Create a new form bean and set it in session
		JuvenileMainForm jisForm =(JuvenileMainForm)form;
		if(jisForm==null)
		{
			jisForm = new JuvenileMainForm();
			HttpSession session = request.getSession();			
			session.setAttribute("juvenileProfileMainForm", jisForm);			
		}
		else
		{
			jisForm.setJISInfoList(CodeHelper.getActiveCodes(PDCodeTableConstants.JUVENILE_JIS_AGENCY, true));
			jisForm.getCurrentJISInfo().setOtherAgency("");
		}
		
		return mapping.findForward("success");
	}

	
	
}

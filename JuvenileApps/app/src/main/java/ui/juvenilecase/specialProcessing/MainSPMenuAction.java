package ui.juvenilecase.specialProcessing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;

public class MainSPMenuAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ProdSupportForm regform = (ProdSupportForm) form;
	
		String selectedItem =  request.getParameter("selectedMenuItem");
		
		regform.setSelectedMenuItem(selectedItem);

		return mapping.findForward("success");
	}
	
}

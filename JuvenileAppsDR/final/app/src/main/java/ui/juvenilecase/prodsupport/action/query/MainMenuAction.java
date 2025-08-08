package ui.juvenilecase.prodsupport.action.query;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;

public class MainMenuAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ProdSupportForm regform = (ProdSupportForm) form;
	
		String selectedItem = regform.getSelectedMenuItem();
		if (selectedItem!=null && (selectedItem.equalsIgnoreCase("juvenileSeal") ||selectedItem.equalsIgnoreCase("juvenilePurge")|| selectedItem.equalsIgnoreCase("referralSeal")))
		    selectedItem="success";
		
		else if (selectedItem==null || selectedItem.equals("") || selectedItem.equals("-1"))
			selectedItem = "success";	

		return mapping.findForward(selectedItem);
	}
	
}

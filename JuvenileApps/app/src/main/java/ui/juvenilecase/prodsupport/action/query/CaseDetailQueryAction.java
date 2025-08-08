package ui.juvenilecase.prodsupport.action.query;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;

/**
 * @author rcarter
 * 
 */

public class CaseDetailQueryAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		if (regform.getTableId()==null || regform.getTableId().equals(""))
		{	
			regform.setMsg("Selection was invalid.");
			return mapping.findForward("error");
		}
		
		regform.setMsg("");
		return mapping.findForward("success");
		
	}	
	

	
	
}

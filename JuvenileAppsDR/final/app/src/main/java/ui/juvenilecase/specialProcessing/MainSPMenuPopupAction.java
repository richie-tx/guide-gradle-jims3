package ui.juvenilecase.specialProcessing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;

public class MainSPMenuPopupAction extends Action
{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	ProdSupportForm regform = (ProdSupportForm) form;
	regform.setJuvenileId("");
	regform.setFromJuvenileId("");
	regform.setReferralNum("");
	regform.setMsg("");

	String selectedItem = regform.getSelectedMenuItem();
	if (selectedItem != null && !(selectedItem.equalsIgnoreCase("juvenileSeal") || selectedItem.equalsIgnoreCase("referralSeal") || selectedItem.equalsIgnoreCase("juvenilePurge")))
	    regform.setSelectedMenuItem("");

	return mapping.findForward(selectedItem);
    }

}

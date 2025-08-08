package ui.juvenilecase.prodsupport.action.update;

import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.productionsupport.UpdateProdSupportMoveJuvenileProgramReferralEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.supervision.programreferral.JuvenileEventReferral;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author This action performs the move Events To NewProgram via Prod Support screen
 */
public class PerformMoveEventToNewProgramAction extends Action
{

    private Logger log = Logger.getLogger("PerformMoveEventToNewProgramAction");

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	ProdSupportForm regform = (ProdSupportForm) form;
	String[] selectedRefIds = regform.getSelectedHists();
	if (selectedRefIds == null)
	{
	    regform.setMsg("Please select atlleast one SERVEVENT_ID by clicking the check box to move the events.");
	    return (mapping.findForward("showList"));
	}
	if ("getNewProgRefId" == regform.getAction() && regform.getNewProgramRefId() == null)
	{
	    regform.setNewProgramRefId("");
	    return mapping.findForward("getNewProgRefId");
	}
	String newJuvProgRefId = regform.getNewProgramRefId();
	if (newJuvProgRefId == null || newJuvProgRefId.equals(""))
	{
	    regform.setMsg("Please provide a valid New Program Refferral Id to move the events.");
	    return (mapping.findForward("error"));
	}
	try
	{
	    Integer.parseInt(newJuvProgRefId);
	}
	catch (Exception e)
	{
	    regform.setMsg("Please enter a valid Program Referral ID.");
	    return (mapping.findForward("error"));
	}

	// Check the New Program Referral ID exists in the table

	Iterator<JuvenileEventReferral> eventReferralIter = JuvenileEventReferral.findAll("programReferralId", newJuvProgRefId);
	if (!eventReferralIter.hasNext())
	{
	    regform.setMsg("The provided Program Referral Id "
		    + newJuvProgRefId
		    + " doesn't exist in the table. \n Please provide an existing Program Referral Id to move the events.");
	    //regform.setNewProgramRefId(""); // to clear the text field
	    return (mapping.findForward("error"));
	}
	// overwrite the selected juvprogramrefID with new programRefID
	for (int i = 0; i < selectedRefIds.length; i++)
	{
	    Iterator<JuvenileEventReferral> eventReferralIter2 = JuvenileEventReferral.findAll("serviceEventId", selectedRefIds[i]);
	    while (eventReferralIter2.hasNext())
	    {
		JuvenileEventReferral eventRef2 = eventReferralIter2.next();
		//check for the program referral ID on the matching event
		if (eventRef2.getProgramReferralId() != null && eventRef2.getProgramReferralId().equalsIgnoreCase(regform.getJuvprogrefId()))
		{
		    UpdateProdSupportMoveJuvenileProgramReferralEvent evt = (UpdateProdSupportMoveJuvenileProgramReferralEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODSUPPORTMOVEJUVENILEPROGRAMREFERRAL);
		    evt.setJuvProgRefId(newJuvProgRefId);
		    evt.setOID(eventRef2.getOID());
		    MessageUtil.postRequest(evt);
		    regform.setAction("updateSuccess");
		}
	    }
	}
	if (regform.getAction().equalsIgnoreCase("updateSuccess"))
	{
	  //Log the query attempt
		log.info("[" + new Date() + "] ProdSupport  Move Event to New Program Referral (LogonId: "
			+ SecurityUIHelper.getLogonId().toUpperCase() + ") - New Program Referral Query ID: " + newJuvProgRefId
			+ " Old Program Referral Query ID: " + regform.getJuvprogrefId());
		
	    return mapping.findForward("updateSuccess");
	}
	return (mapping.findForward("error"));
    }
}

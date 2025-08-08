package ui.juvenilecase.prodsupport.action.query;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.referral.GetVOPDetailsEvent;
import mojo.km.messaging.EventFactory;
import naming.JuvenileReferralControllerServiceNames;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pd.juvenilecase.referral.JCVOP;
import ui.juvenilecase.form.ProdSupportForm;

public class UpdateJCVOPRecordQueryAction extends Action
{
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
	ProdSupportForm regform = (ProdSupportForm) aForm;
	String forward = "success";

	String clrChk = aRequest.getParameter("clr");

	if (clrChk != null && clrChk.equalsIgnoreCase("Y"))
	{
	    regform.clearAllResults();
	    regform.setMsg("");
	    return aMapping.findForward("error");
	}

	if (clrChk != null && clrChk.equalsIgnoreCase("R"))
	{
	    regform.clearAllResults();
	    regform.setMsg("");
	    String juvenileNumber = regform.getOriginalJuvenileNumber();
	    if (juvenileNumber != null && juvenileNumber.length() > 0)
	    {
		regform.setJuvenileId(juvenileNumber);
	    }
	    String referralNumber = regform.getOriginalReferralNumber();
	    if (referralNumber != null && referralNumber.length() > 0)
	    {
		regform.setReferralId(referralNumber);
	    }
	    return aMapping.findForward("error");
	}

	String juvenileNumber = regform.getJuvenileId();
	regform.setOriginalJuvenileNumber(juvenileNumber);
	String referralNumber = regform.getReferralId();
	regform.setOriginalReferralNumber(referralNumber);
	//Collection<JuvenileReferralVOPResponseEvent> juvVOPRecords = new ArrayList<JuvenileReferralVOPResponseEvent>();

	if ((juvenileNumber != null && juvenileNumber.length() > 0) || (referralNumber != null && referralNumber.length() > 0))
	{
	    GetVOPDetailsEvent event = (GetVOPDetailsEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETVOPDETAILS);
	    event.setJuvenileNumber(juvenileNumber);
	    event.setReferralNumber(referralNumber);
	    JCVOP juvVOPrecord = (JCVOP) JCVOP.find(event);
	    JCVOP juvVOPrecordOriginal = juvVOPrecord;
	    if (juvVOPrecord != null)
	    {
		if (juvVOPrecord.getLocationIndicator() != null )
		{
		    if (juvVOPrecord.getLocationIndicator().equalsIgnoreCase("In County"))
		    {
			juvVOPrecord.setLocationIndicator("IIC");
		    }
		    else
			if (juvVOPrecord.getLocationIndicator().equalsIgnoreCase("Out Of County"))
			{
			    juvVOPrecord.setLocationIndicator("OOC");
			}
			else
			    if (juvVOPrecord.getLocationIndicator().equalsIgnoreCase("Out Of State"))
			    {
				juvVOPrecord.setLocationIndicator("OOS");
			    }
		}else {
		    juvVOPrecord.setLocationIndicator(""); 
		}
		regform.setJcVOP(juvVOPrecord);
		regform.setJcVOPOriginal(juvVOPrecordOriginal);
	    }
	    else
	    {
		regform.setMsg("No records found.");
		return aMapping.findForward("error");
	    }
	}
	else
	{
	    regform.setMsg("Please enter a valid Juvenile Number and a valid Referral Number.");
	    return aMapping.findForward("error");
	}
	regform.setMsg("");
	return aMapping.findForward(forward);
    }

}

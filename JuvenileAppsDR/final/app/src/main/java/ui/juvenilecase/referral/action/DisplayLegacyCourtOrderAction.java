package ui.juvenilecase.referral.action;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.referral.form.LegacyCourtOrdersForm;
import messaging.referral.GetLegacyCourtOrdersEvent;
import messaging.referral.reply.LegacyCourtOrderResponseEvent;
import mojo.km.messaging.EventFactory;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.JuvenileReferralControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import mojo.km.utilities.MessageUtil;

public class DisplayLegacyCourtOrderAction extends JIMSBaseAction
{
	public DisplayLegacyCourtOrderAction( )
	{
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward displayCourtOrders( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		LegacyCourtOrdersForm form = (LegacyCourtOrdersForm)aForm ;
		String forward = UIConstants.FAILURE ;
		String juvenileNum = aRequest.getParameter("juvenileNum");
		String petitionNum = aRequest.getParameter("petitionNum");
		
//		String juvenileNum = "337100";
//		String petitionNum = "999999996";

		
		GetLegacyCourtOrdersEvent LegCourtOrd = (GetLegacyCourtOrdersEvent) EventFactory
		.getInstance(JuvenileReferralControllerServiceNames.GETLEGACYCOURTORDERS);
		LegCourtOrd.setJuvenileNum(juvenileNum);
		LegCourtOrd.setPetitionNum(petitionNum);
		
		List courtOrders = MessageUtil.postRequestListFilter(LegCourtOrd, LegacyCourtOrderResponseEvent.class);
		
	//	Collections.sort(courtOrders);

		if (courtOrders == null)
		{
            return aMapping.findForward(forward);
        }
        else
        {
            forward = UIConstants.SUCCESS;
 //           size = courtOrders.size();
        }
		form.clear();
		form.reset(aMapping, aRequest);
        form.setPetitionNum(petitionNum);
        form.setCourtOrders(courtOrders);
		return aMapping.findForward( forward ) ;
	}
	
			
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put( "button.link", "displayCourtOrders" ) ;
	}
}

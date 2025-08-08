package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenileDSPlacementListEvent;
import messaging.juvenile.GetJuvenileDSservicesListEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import naming.JuvenileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenile.JuvenileDualStatusCPSServices;
import pd.juvenile.JuvenileDualStatusPlacement;
import pd.juvenilecase.districtCourtHearings.JJSCLAncillary;

import ui.juvenilecase.form.JuvenileAbuseForm;

public class DisplayJuvenileDualStatusDetailsAction extends Action
{

	/**
	 * @roseuid 42B1A2A600DA
	 */
	public DisplayJuvenileDualStatusDetailsAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42B03B36021D
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
		form.setMultiplePrep(null);

		ActionForward forward = null;

		//String dualstatusId = form.getDualstatusId();
		String dualstatusId = aRequest.getParameter("dualstatusId");
		//code to get the placements to JuvenileAbuseForm newplacements
		GetJuvenileDSPlacementListEvent event2 = (GetJuvenileDSPlacementListEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEDSPLACEMENTLIST);
		event2.setDualID(dualstatusId);
		IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch2.postEvent(event2);
		JuvenileDualStatusPlacement placement = null;
		Iterator placementsItr = JuvenileDualStatusPlacement.findJuvenileDSPlacements((GetJuvenileDSPlacementListEvent) event2);
		Collection placmnts = new ArrayList();
		if(placementsItr!=null)
		{
		    while(placementsItr.hasNext())
		    {
        		    placement = (JuvenileDualStatusPlacement) placementsItr.next();
        		    //code changes for US 164321 starts
        		    String formattedPlDate = placement.getPlacementDate();
        		    //formattedPlDate = new StringBuffer(formattedPlDate.trim()).insert(2, "/").toString(); //this works too: to add / in date
        		    formattedPlDate = formattedPlDate.substring(0, 2) + "/" + formattedPlDate.substring(2, formattedPlDate.length());
        		    placement.setPlacementDate(formattedPlDate);
        		    //code changes for US 164321 ENDS
        		    placmnts.add(placement);
		    }
		}
		form.setNewPlacements(placmnts);
		//

		String action = form.getAction();
		
		form.initDualStatus(dualstatusId);

		if (UIConstants.VIEW.equals(action))
		{
			forward = aMapping.findForward(UIConstants.SUCCESS);
		}
		else
		{
			forward = aMapping.findForward(UIConstants.SUCCESS);
		}

		return forward;
	}
}

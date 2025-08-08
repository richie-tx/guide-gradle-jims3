package ui.supervision.programReferral.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerprogramreferrals.GetProgramLocationsEvent;
import messaging.administerprogramreferrals.SaveProgRefCasenoteEvent;
import messaging.csserviceprovider.reply.CSProgramLocationResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.programReferral.CSCProgRefCasenoteUIHelper;
import ui.supervision.programReferral.CSCProgRefUIHelper;
import ui.supervision.programReferral.form.CSCProgRefForm;


/**
 * 
 * @author cc_bjangay
 *
 */
public class DisplayServiceProviderPacketAction extends JIMSBaseAction
{
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link","printPacket");
	}
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward printPacket(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progRefForm = (CSCProgRefForm)aForm;
		
		progRefForm.setSpPacketBeanList(new ArrayList());
		
		GetProgramLocationsEvent prog_loc_event = CSCProgRefUIHelper.getProgramLocationsEventForPrintPacket(progRefForm);
		if((prog_loc_event.getServiceProviderIds().size()>0) && (prog_loc_event.getReferralTypesCodeList().size()>0))
		{
			List filteredSPList = new ArrayList();
            String temp = (String) progRefForm.getSelectedSPIdsString();
            String delims = "[,]";
            String[] tokens = temp.split( delims );
            filteredSPList = Arrays.asList( tokens );

            prog_loc_event.setServiceProviderIds( filteredSPList );

			List sp_prog_loc_response = postRequestListFilter(prog_loc_event,CSProgramLocationResponseEvent.class);
			
			CSCProgRefUIHelper.convertProgramLocationRespEvtToPacketBean(progRefForm, sp_prog_loc_response);
		}
		
//		create auto casenotes
		SaveProgRefCasenoteEvent casenoteEvent = CSCProgRefCasenoteUIHelper.getSaveCasenoteEventForPrintPacket(progRefForm);
		this.postRequestEvent(casenoteEvent);
		
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}
	
}

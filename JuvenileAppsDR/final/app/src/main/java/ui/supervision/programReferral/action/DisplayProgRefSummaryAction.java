package ui.supervision.programReferral.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerprogramreferrals.GetInitOpenReferralsForRefTypesEvent;
import messaging.administerprogramreferrals.reply.ReferralWithRefTypeResponseEvent;
import naming.CSAdministerProgramReferralsConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.programReferral.CSCLocationInfoBean;
import ui.supervision.programReferral.CSCServiceProviderProgLocBean;
import ui.supervision.programReferral.form.CSCProgRefForm;

/**
 * 
 * @author cc_bjangay
 *
 */
public class DisplayProgRefSummaryAction extends JIMSBaseAction
{
	private static int INVALID = 0;
	private static int VALID = 1;
	
	@Override
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next","next");
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
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progRefForm = (CSCProgRefForm)aForm;
		
		if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_REREFERRAL))
		{
			String selProgLocId = progRefForm.getSelectedPrgmLocIds();
			
			CSCServiceProviderProgLocBean spProgLocBean = progRefForm.getReferralProgramLocBean();
			String programId = spProgLocBean.getProgramId();
			
			List progLocList = spProgLocBean.getProgramLocationsList();
			Iterator iter = progLocList.iterator();
			while(iter.hasNext())
			{
				CSCLocationInfoBean locationInfoBean = (CSCLocationInfoBean)iter.next();
				String progLocId = programId + "-" + locationInfoBean.getLocationId();
				if(progLocId.equalsIgnoreCase(selProgLocId))
				{
					spProgLocBean.setProgramLocationBean(locationInfoBean);
					break;
				}
			}
		}
		else
		if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_SUBMITREFERRAL))
		{
			int result = validate(progRefForm);
			if(result==INVALID)
			{
				String errorMsg = "A referral already exist for this referral type in an Initiated or Open status. You must exit the other referral prior to progressing this new referral";
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, errorMsg);
				return aMapping.findForward(UIConstants.FAILURE);
			}
		}
		
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	}
	
	
	
	/**
	 * 
	 * @param progRefForm
	 * @return
	 */
	private int validate(CSCProgRefForm progRefForm)
	{
//		query all the initiate and open referrals for the referral type of the referral to be submitted
		GetInitOpenReferralsForRefTypesEvent requestEvent = new GetInitOpenReferralsForRefTypesEvent();
		requestEvent.setDefendantId(progRefForm.getSpn());
		List selRefTypesCdList = new ArrayList();
		selRefTypesCdList.add(progRefForm.getReferralTypeCode()); 
		requestEvent.setRefTypesCdList(selRefTypesCdList);
		List responseList = this.postRequestListFilter(requestEvent, ReferralWithRefTypeResponseEvent.class);
		
//		get the RefTypeCds which are not progressed (because an Initiate or Open Referral is already present for the same RefType)
		if(responseList!=null && responseList.size()>0)
		{
			for(int index=0; index < responseList.size(); index++)
			{
				ReferralWithRefTypeResponseEvent respEvt = (ReferralWithRefTypeResponseEvent)responseList.get(index);
				
				if(!respEvt.getProgramReferralId().equalsIgnoreCase(progRefForm.getProgRefId()))
				{
					return INVALID;
				}
			}
		}
		return VALID;
	}
	
}

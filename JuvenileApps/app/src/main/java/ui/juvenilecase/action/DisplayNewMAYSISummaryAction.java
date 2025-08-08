//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayNewMAYSISummaryAction.java

package ui.juvenilecase.action;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.mentalhealth.GetMaysiRequestsEvent;
import messaging.mentalhealth.reply.MAYSISearchResultResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.juvenilecase.form.MentalHealthForm;

/**
 * @author dgibler
 * 
 */
public class DisplayNewMAYSISummaryAction extends JIMSBaseAction
{
	/**
	 * @roseuid 42791FCC02DE
	 */
	public DisplayNewMAYSISummaryAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42791D5702A2
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		MentalHealthForm mhForm = (MentalHealthForm)aForm;
		String tAction = mhForm.getAction() ;
		
		if( tAction != null  &&  !tAction.equalsIgnoreCase("update") )
		{
			mhForm.setMaysiId("");
			GetMaysiRequestsEvent requestMaysi = (GetMaysiRequestsEvent)
					EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMAYSIREQUESTS);

			requestMaysi.setJuvenileNumber(mhForm.getJuvenileNum());
			requestMaysi.setReferralNumber(mhForm.getReferralNum());

			GregorianCalendar myCal = new GregorianCalendar();
			myCal.add(Calendar.DATE, -1);
			requestMaysi.setDateForward(myCal.getTime());
			
			List myColl = MessageUtil.postRequestListFilter(
					requestMaysi, MAYSISearchResultResponseEvent.class);

			if( myColl != null && myColl.size() > 0 )
			{
				sendToErrorPage(aRequest, "error.newMaysiRequest");
				return aMapping.findForward(UIConstants.FAILURE);
			}
		}

		// get descriptions for dropdown codes.
		if( !mhForm.getAction().equalsIgnoreCase("update") )
		{
			mhForm.processCodeDescriptions();
		}
		else if( mhForm.getReasonNotDoneId() != null )
		{
			CodeResponseEvent evt = UIUtil.findCodeResponseEvent(
					mhForm.getReasonsNotDone().iterator(), mhForm.getReasonNotDoneId());
			mhForm.setReasonNotDone(evt.getDescription());
		}
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#cancel(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map buttonMap)
	{
		buttonMap.put("button.next", "next");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.cancel", "cancel");
	}
}

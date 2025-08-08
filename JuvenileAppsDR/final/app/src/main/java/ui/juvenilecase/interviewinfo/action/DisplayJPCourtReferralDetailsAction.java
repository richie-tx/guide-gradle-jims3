package ui.juvenilecase.interviewinfo.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.interviewinfo.to.JPCourtReferralTO;
import messaging.juvenilecase.GetJPCourtReferralsEvent;
import messaging.juvenilecase.reply.JPCourtReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.interviewinfo.form.SocialHistoryForm;

/**
 * 
 * @author awidjaja
 *
 * This action is for listing interviews by casefiles (used in the Profile view of
 * Interviews) 
 */
public class DisplayJPCourtReferralDetailsAction extends LookupDispatchAction
{
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		SocialHistoryForm form = (SocialHistoryForm) aForm;
		
		String juvNumber = form.getSelJuvNumber();
		JPCourtReferralTO selJPCourtReferral = null;
		
		Collection jpCourtReferrals = form.getSocialHistoryData().getJPCourtReferrals();
		for(Iterator iter = jpCourtReferrals.iterator();iter.hasNext();)
		{
			JPCourtReferralTO jpCourtReferral = (JPCourtReferralTO)iter.next();
			if(jpCourtReferral.getM204JuvNumber().equals(juvNumber))
			{
				selJPCourtReferral = jpCourtReferral;
			}
		}
		
		//if it's null, then the name doesn't match... may be manually feed
		//through browser with query string method 
		if(selJPCourtReferral != null)
		{
			//get list of JP Court Referrals by juvnum & casefileId
			GetJPCourtReferralsEvent event =
				(GetJPCourtReferralsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJPCOURTREFERRALS);
		
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

			//This is m204 juvenileId, not DB2
			event.setM204JuvNumber( selJPCourtReferral.getM204JuvNumber() );
			dispatch.postEvent(event);
			
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
		
			jpCourtReferrals =
				MessageUtil.compositeToCollection(compositeResponse, JPCourtReferralResponseEvent.class);
		
			form.setJpCourtReferrals(jpCourtReferrals);
		}
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.link", "link");
		return keyMap;
	}
	
	
	
		
	
}
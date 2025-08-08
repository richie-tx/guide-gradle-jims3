package ui.juvenilecase.facility.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetJuvenileOffenseCodeEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.facility.form.AdmitReleaseForm;

/**
 * 
 * @author sthyagarajan
 * //added for #51734
 *
 */
public class DisplayJuvenileFacilityHistoryAction  extends LookupDispatchAction {
	
	/**
	 * link
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		AdmitReleaseForm form = (AdmitReleaseForm) aForm;
		form.clear();
		form.setJuvenileNum("");
		UIJuvenileHelper.putHeaderForm( aRequest, new JuvenileProfileDetailResponseEvent() ); //reset the session.
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/**
	 * getFacility
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward getFacility(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		AdmitReleaseForm form = (AdmitReleaseForm) aForm;
		form.clear();
		String juvenileNum = form.getJuvenileNum();
		
		if(juvenileNum==null){
			ActionErrors errors = new ActionErrors();
  	        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Juvenile Number is Required."));
  	        saveErrors(aRequest, errors);
  	        return aMapping.findForward(UIConstants.FAILURE);
		}
		
		GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent)
				EventFactory.getInstance( JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN );

		requestEvent.setJuvenileNum( juvenileNum );
		CompositeResponse replyEvent = MessageUtil.postRequest( requestEvent );
		JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent)
				MessageUtil.filterComposite( replyEvent, JuvenileProfileDetailResponseEvent.class );
		
		if( detail == null )
		{
			detail = new JuvenileProfileDetailResponseEvent();
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage( UIConstants.NO_RECORDS_ERROR ));
			saveErrors(aRequest, errors);
			return( aMapping.findForward(UIConstants.SEARCH_FAILURE) );
		}else{
			UIJuvenileHelper.putHeaderForm( aRequest, detail ); //reset the session.
		}
		
		UIJuvenileHelper.setJuvenileBehaviorHistoryForm(aRequest, juvenileNum);
	
		//added for 51734
		Collection<JuvenileDetentionFacilitiesResponseEvent> facilityHistoryList = JuvenileFacilityHelper.getJuvFacilityDetails(form.getJuvenileNum(), null);
		if(facilityHistoryList!=null){
			// re-sort the child list based on facility seq num.
		    //	Collections.sort((List<JuvenileDetentionFacilitiesResponseEvent>)facilityHistoryList, Collections.reverseOrder(JuvenileDetentionFacilitiesResponseEvent.DetentionRespEventComparator));
			/*Collections.sort((List<JuvenileDetentionFacilitiesResponseEvent>)facilityHistoryList,new Comparator<JuvenileDetentionFacilitiesResponseEvent>() {
				@Override
				public int compare(JuvenileDetentionFacilitiesResponseEvent det1,JuvenileDetentionFacilitiesResponseEvent det2) {
					if(det1.getFacilitySequenceNumber()!=null && det2.getFacilitySequenceNumber()!=null)
						return det2.getFacilitySequenceNumber().compareTo(det1.getFacilitySequenceNumber());
					else 
						return -1;
				}
			});*/ //not needed // fixed as part of 88723
			//set offense information.
			List<JuvenileDetentionFacilitiesResponseEvent> newfacilityHistoryList = new ArrayList<JuvenileDetentionFacilitiesResponseEvent>(); 
			Iterator<JuvenileDetentionFacilitiesResponseEvent> facilityHistIterator = facilityHistoryList.iterator();
			while(facilityHistIterator.hasNext()){ //iterate detention recs.
				JuvenileDetentionFacilitiesResponseEvent detRec =	facilityHistIterator.next();
				if(detRec!=null){
					setBookingOffense(detRec);
					newfacilityHistoryList.add(detRec);
				}
			}//iterate detention recs ends.while(1)
			
			Collections.sort((List<JuvenileDetentionFacilitiesResponseEvent>)newfacilityHistoryList, Collections.reverseOrder(JuvenileDetentionFacilitiesResponseEvent.DetentionRespEventComparator));
			form.setFacilityHistoryList(newfacilityHistoryList);
		} //end of if
		//added for U.S 14780;
		aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY_PAGE,PDJuvenileCaseConstants.JUVENILE_PROFILE);
		aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY,PDJuvenileCaseConstants.JUVENILE_FACILITY);

		return( aMapping.findForward(UIConstants.SUCCESS) );
	}
	
	/**
	 * setBookingOffense
	 * @param evt
	 */
	private void setBookingOffense(JuvenileDetentionFacilitiesResponseEvent evt){
		GetJuvenileCasefileOffensesEvent jcoEvent = new GetJuvenileCasefileOffensesEvent();
		jcoEvent.setJuvenileNum(evt.getJuvNum());
		List<JJSOffenseResponseEvent> offenses = MessageUtil.postRequestListFilter(jcoEvent, JJSOffenseResponseEvent.class);
		
		//U.S #32320
		boolean isTransferredOffense=false;
		List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseResp = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(evt.getJuvNum());
		//sorts in descending order.
		Collections.sort((List<JJSOffenseResponseEvent>)offenses,Collections.reverseOrder(new Comparator<JJSOffenseResponseEvent>() {
			@Override
			public int compare(JJSOffenseResponseEvent evt1, JJSOffenseResponseEvent evt2) {
				return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
			}
		}));
		
		Iterator<JJSOffenseResponseEvent> offensesItr = offenses.iterator();
		while (offensesItr.hasNext())
		{
			JJSOffenseResponseEvent offenseResp= offensesItr.next();
			if(offenseResp !=null && JuvenileFacilityHelper.checkIfJJSFacilityHasMoreThanOneRecord(evt.getJuvNum()) && evt!=null 
					&& evt.getReferralNumber()!=null && !evt.getReferralNumber().isEmpty())
			{
				if(offenseResp.getReferralNum().equals(evt.getReferralNumber()))
				{
					//if a transferred offense
					if(offenseResp.getSeveritySubtype()!=null && offenseResp.getSeveritySubtype().equals("T")){
						//if there is a transferred offense added to the cart in SQL.
						if(transferredOffenseResp!=null && !transferredOffenseResp.isEmpty()){
							Iterator<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseIter = transferredOffenseResp.iterator();
							while (transferredOffenseIter.hasNext())
							{
								JuvenileCasefileTransferredOffenseResponseEvent transferredOffense= transferredOffenseIter.next();
								if(transferredOffense.getReferralNum().equals(evt.getReferralNumber())){
									evt.setBookingOffenseCd(transferredOffense.getOffenseCode());
									evt.setBookingOffenseDesc(transferredOffense.getOffenseShortDesc());
									isTransferredOffense = true;
									break;
								}
							}
							
						} //if there is not a transferred offense added to the cart in SQL.Retain the offense from m204 jjsoffense.
						if(!isTransferredOffense){
							evt.setBookingOffenseCd(offenseResp.getOffenseCodeId());
							
							GetJuvenileOffenseCodeEvent jocEvent = new  GetJuvenileOffenseCodeEvent();
							jocEvent.setAlphaCode(offenseResp.getOffenseCodeId());
							IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
							dispatch.postEvent(jocEvent);
							CompositeResponse response = (CompositeResponse)dispatch.getReply();
							
							JuvenileOffenseCodeResponseEvent juvOffenseCode =(JuvenileOffenseCodeResponseEvent)MessageUtil.filterComposite(response,JuvenileOffenseCodeResponseEvent.class);
							if(juvOffenseCode!=null){
								evt.setBookingOffenseDesc( juvOffenseCode.getLongDescription());
							}	
							   break;
						}
					}
					else // not a transferred offense.
					{
						evt.setBookingOffenseCd(offenseResp.getOffenseCodeId());
						
						GetJuvenileOffenseCodeEvent jocEvent = new  GetJuvenileOffenseCodeEvent();
						jocEvent.setAlphaCode(offenseResp.getOffenseCodeId());
						IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
						dispatch.postEvent(jocEvent);
						CompositeResponse response = (CompositeResponse)dispatch.getReply();
						
						JuvenileOffenseCodeResponseEvent juvOffenseCode =(JuvenileOffenseCodeResponseEvent)MessageUtil.filterComposite(response,JuvenileOffenseCodeResponseEvent.class);
						if(juvOffenseCode!=null){
							evt.setBookingOffenseDesc( juvOffenseCode.getLongDescription());
						}	
						   break;
					}
				} //end of if(2)			
			}//end of if(1)
		} //end of while(1)
	}
		
	@Override
	protected Map<String,String> getKeyMethodMap() {
		Map<String,String> keyMap = new HashMap<String,String>();
		keyMap.put("button.link", "link");
		keyMap.put("button.submit", "getFacility");
		return keyMap;
	}
}

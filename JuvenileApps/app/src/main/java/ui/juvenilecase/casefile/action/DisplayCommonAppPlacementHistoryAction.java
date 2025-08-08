//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\casefile\\action\\DisplayCommonAppPlacementHistoryAction.java

package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetNonDetentionJuvenileFacilitiesEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.juvenilecase.GetJuvenileDetentionFacilitiesEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.casefile.form.CommonAppForm;
import ui.juvenilecase.casefile.form.CommonAppForm.Placement;

public class DisplayCommonAppPlacementHistoryAction extends JIMSBaseAction {

	/**
	 * @roseuid 46CF1AD503C3
	 */
	public DisplayCommonAppPlacementHistoryAction() {

	}

	/**
	 * link
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 46CF186B0099
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm,	HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CommonAppForm commonAppForm = (CommonAppForm) aForm;
		if (!commonAppForm.isPlacementHistoryExists()) {
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			HashMap<String,JuvenileFacilityResponseEvent> facilityMap = new HashMap<String,JuvenileFacilityResponseEvent> ();
			
			GetNonDetentionJuvenileFacilitiesEvent facilityEvent = new GetNonDetentionJuvenileFacilitiesEvent();
			CompositeResponse response = MessageUtil.postRequest(facilityEvent);
			List<JuvenileFacilityResponseEvent> facilityList = MessageUtil.compositeToList(response, JuvenileFacilityResponseEvent.class);
			if(facilityList!=null){
				Iterator<JuvenileFacilityResponseEvent > facilityItr = facilityList.iterator();
				while(facilityItr.hasNext()){
					JuvenileFacilityResponseEvent facility = (JuvenileFacilityResponseEvent) facilityItr.next();
					if(facility.getJuvTJPCPlacementType()!=null && !facility.getJuvTJPCPlacementType().equals("D")){
						facilityMap.put(facility.getCode(), facility);
					}
				}
			}
			
			List< Placement> detentionFacilities = new ArrayList< Placement>();
			
			// get the referrals
			CasefileClosingForm closingForm = (CasefileClosingForm) aRequest.getSession().getAttribute("casefileClosingForm");
			if (closingForm != null) {
				Collection<CasefileClosingForm.Refferal> referralsCol = closingForm.getProfileReferrals();
				if (referralsCol != null) {
					Iterator<CasefileClosingForm.Refferal> refIter = referralsCol.iterator();
					while (refIter.hasNext()) {
						CasefileClosingForm.Refferal referral =  refIter.next();
						
						GetJuvenileDetentionFacilitiesEvent det = (GetJuvenileDetentionFacilitiesEvent)EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILEDETENTIONFACILITIES );
						String refNo = null;
						if(referral.getReferralNumber()!=null){
							if(referral.getReferralNumber().contains("-")){
								refNo = referral.getReferralNumber().split("-")[0];
							}else{
								refNo = referral.getReferralNumber();
							}
						}
						det.setReferralNum(refNo);
						det.setJuvenileNum(closingForm.getJuvenileNum());
						dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
						dispatch.postEvent(det);
						CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
						MessageUtil.processReturnException(compositeResponse);
						Collection<JuvenileDetentionFacilitiesResponseEvent> juvDetfacilities = MessageUtil.compositeToCollection(compositeResponse, JuvenileDetentionFacilitiesResponseEvent.class);
						
						if (juvDetfacilities != null) {
							Iterator<JuvenileDetentionFacilitiesResponseEvent> juvDetfacilitiesItr = juvDetfacilities.iterator();
							while (juvDetfacilitiesItr.hasNext()) {
								JuvenileDetentionFacilitiesResponseEvent detentionResp = juvDetfacilitiesItr.next();
								JuvenileFacilityResponseEvent facResp = facilityMap.get(detentionResp.getDetainedFacility());
								if(facResp!=null){
									detentionFacilities.add(getFacilityFromResp(commonAppForm,facResp,detentionResp));
								}
							} //while(2)
						}
					} //while(1)
				}
			}
			// commonAppForm.getCourtOrder().setSelectedPlacement(detentionFacilities);
			// remove duplicate detention facility names
			Iterator< Placement> iter = detentionFacilities.iterator();
			HashMap<String, Placement> facilitiesMap = new HashMap<String, Placement> ();
			while (iter.hasNext()) {
				Placement resp = iter.next();
				resp.setStayed(false);
				facilitiesMap.put(resp.getFacilityName(), resp);
			}
			commonAppForm.getCourtOrder().setSelectedPlacement(new ArrayList(facilitiesMap.values()));
		}
		return aMapping.findForward("success");
	}

	/**
	 * getFacilityFromResp
	 * @param commonAppForm
	 * @param facResp
	 * @param resp
	 * @return
	 */
	public static Placement getFacilityFromResp(CommonAppForm commonAppForm, JuvenileFacilityResponseEvent facResp,JuvenileDetentionFacilitiesResponseEvent resp )
	{
		final long DAYS_CONVERSION = (1000 * 3600 * 24) ;
		final String DAYS_STR = " Days" ;

		Placement place = new Placement();
		place.setAdmitReason( "Completed" );
		place.setFacilityName( resp.getDetainedFacilityDesc());
		place.setAdmitDate( DateUtil.dateToString( resp.getAdmitDate(), "MM/dd/yyyy" ) );
		place.setAdmitTime( resp.getAdmitTime()  );
		place.setReleaseTime( resp.getReleaseTime() );
		place.setReleaseDate( DateUtil.dateToString( resp.getReleaseDate(), "MM/dd/yyyy" ) );
		place.setStayed( false );
		if( resp.getReleaseDate() != null )
		{
			long days = ((resp.getReleaseDate().getTime() - 
					resp.getAdmitDate().getTime()) / DAYS_CONVERSION) + 1;
			place.setDetTime( days );
			place.setTotalTime( UIConstants.EMPTY_STRING + days + DAYS_STR );
		}
		else
		{
			long days = ((DateUtil.getCurrentDate().getTime() - 
					resp.getAdmitDate().getTime()) / DAYS_CONVERSION) + 1;
			place.setDetTime( days );
			place.setTotalTime( UIConstants.EMPTY_STRING + days + DAYS_STR );
		}

		place.setFacilityInfo( facResp );
		
		Collection<Placement> coll = commonAppForm.getCourtOrder().getSelectedPlacement();
		Iterator<Placement> iter = coll.iterator();
		while (iter.hasNext()) {
			Placement formPlacement = iter.next();
			if (formPlacement.getFacilityName().equals(place.getFacilityName())) {
				place.setContinuedStay(formPlacement.isContinuedStay());
			}
		}
		return place;
	}

	/**
	 * save
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward save(ActionMapping aMapping, ActionForm aForm,	HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CommonAppForm commonAppForm = (CommonAppForm) aForm;
		commonAppForm.setAction("confirm");
		commonAppForm.setPlacementHistoryExists(true);
		return aMapping.findForward("success");
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.CANCEL);
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm,	HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}
	
	/**
	 * refresh
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CommonAppForm commonAppForm = (CommonAppForm) aForm;
		Collection<Placement> coll = commonAppForm.getCourtOrder().getSelectedPlacement();
		Iterator<Placement> iter = coll.iterator();
		while (iter.hasNext()) {
			Placement place =  iter.next();
			place.setStayed(false);
			place.setContinuedStay(false);
		}
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.saveChanges", "save");
		keyMap.put("button.link", "link");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.refresh", "refresh");
	}
}
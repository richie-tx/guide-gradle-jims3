/*
 * Created on Mar 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerprogramreferrals.GetReferralFormsEvent;
import messaging.administerprogramreferrals.GetServiceProvidersByReferralTypesEvent;
import messaging.administerprogramreferrals.InitiateReferralsEvent;
import messaging.administerprogramreferrals.reply.GetServiceProvidersByReferralTypesResponseEvent;
import messaging.administerprogramreferrals.reply.InitiateReferralsResponseEvent;
import messaging.administerprogramreferrals.reply.ReferralFormResponseEvent;
import messaging.administerprogramreferrals.reply.ServiceProviderReftypeResponseEvent;
import mojo.km.messaging.EventFactory;
import naming.CSAdministerProgramReferralsConstants;
import naming.CSProgramReferralControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.programReferral.CSCProgRefUIHelper;
import ui.supervision.programReferral.CSCServiceProviderBean;
import ui.supervision.programReferral.ReferralTypeBean;
import ui.supervision.programReferral.form.CSCProgRefForm;

/**
 * 
 * @author cc_bjangay
 *
 */
public class DisplayProgRefRefTypeSelectAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.saveContinue","saveContinue");
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
		public ActionForward saveContinue(ActionMapping aMapping, ActionForm aForm,
						HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
		{
			CSCProgRefForm progRefForm =(CSCProgRefForm)aForm;
			
			clearList(progRefForm);
			// clean up attributes set for initiate flow
			aRequest.setAttribute("cautionMsg", "");
			aRequest.setAttribute("cautionMsg2", "");
			aRequest.setAttribute("confirmMsg", "");
			
			if (progRefForm.getAction().equals(CSAdministerProgramReferralsConstants.ACTION_CREATE))
			{
				CSCProgRefUIHelper.clearUserEnteredSP(progRefForm);
				
				List selectedRefTypesList = CSCProgRefUIHelper.getSelectedReferralTypes(progRefForm.getAvailableReferralTypesList());
				progRefForm.setSelectedReferralTypesList(selectedRefTypesList);
			}
//			UPDATE_INIT
			else
			{
				String selectedRefTypeCd = progRefForm.getReferralTypeCode();
				List availRefTypeslist = progRefForm.getAvailableReferralTypesList();
				List selectedRefTypesList = new ArrayList();
				
				for(int index=0; index < availRefTypeslist.size(); index++)
				{
					ReferralTypeBean refTypeBean = (ReferralTypeBean) availRefTypeslist.get(index);
					if(refTypeBean.getReferralTypeCode().equalsIgnoreCase(selectedRefTypeCd))
					{
						selectedRefTypesList.add(refTypeBean);
						break;
					}
				}
				progRefForm.setSelectedReferralTypesList(selectedRefTypesList);
				
				if(!selectedRefTypeCd.equalsIgnoreCase(progRefForm.getOldReferralTypeCd()))
				{
					progRefForm.setOldReferralTypeCd(selectedRefTypeCd);
					CSCProgRefUIHelper.clearReferralLocationDetails(progRefForm);
				}
			}

			InitiateReferralsEvent initiate_event = CSCProgRefUIHelper.getInitiateReferralsEvent(progRefForm);
			
//			Initiate the referrals and retrieve the Service Providers for all the selected referrals
			InitiateReferralsResponseEvent initiate_response_event = 
						(InitiateReferralsResponseEvent)postRequestEvent(initiate_event, InitiateReferralsResponseEvent.class);
			
//			populate the referral types map
			Map savedReferralTypeCdNRefIdMap = initiate_response_event.getSavedReferralMap();
			if(savedReferralTypeCdNRefIdMap!=null && savedReferralTypeCdNRefIdMap.size()>0)
			{
				progRefForm.setReferralDate((Date) savedReferralTypeCdNRefIdMap.get( "REFERRAL_DATE" ));
				savedReferralTypeCdNRefIdMap.remove( "REFERRAL_DATE" );
				progRefForm.calculateReferralTypes(savedReferralTypeCdNRefIdMap);
				progRefForm.setReferralTypeCdNPgmRefIdMap((Hashtable)savedReferralTypeCdNRefIdMap);
				
				if (progRefForm.getAction().equals(CSAdministerProgramReferralsConstants.ACTION_CREATE))
				{
					progRefForm.setProgRefIdsToDeleteList(progRefForm.getReferralTypeCdNPgmRefIdMap().values());
				}
			}
			
//			convert serviceProvider response Events to ServiceProviderBeans
			List service_provider_list = initiate_response_event.getServiceProviderRefTypeResponses();
			int spLen = service_provider_list.size();
			for(int i=0;i<spLen; i++)
			{
				ServiceProviderReftypeResponseEvent respEvent = (ServiceProviderReftypeResponseEvent)service_provider_list.get(i);
				CSCServiceProviderBean this_service_provider =
					CSCProgRefUIHelper.convertSPReftypeResponseTOServiceProviderBean(respEvent,progRefForm.getReferralTypeCdNDetailsMap());
				
				progRefForm.getAvailableSPList().add(this_service_provider);
			}
			progRefForm.setFilteredSPList(progRefForm.getAvailableSPList());
			
//			Retrieve the referral forms for the selected referral Types
			List selRefTypesList = progRefForm.getSelectedReferralTypesList();
			Iterator refTypeIter = selRefTypesList.iterator();
			while(refTypeIter.hasNext())
			{
				ReferralTypeBean refTypeBean = (ReferralTypeBean)refTypeIter.next();
				GetReferralFormsEvent referralFormsEvt = new GetReferralFormsEvent();
				referralFormsEvt.setReferralTypeCd(refTypeBean.getReferralTypeCode());
				List  referralFormsList = postRequestListFilter(referralFormsEvt, ReferralFormResponseEvent.class);
				CSCProgRefUIHelper.convertResponseEvtToReferralFormBean(refTypeBean, referralFormsList);
			}

			return aMapping.findForward(UIConstants.SUCCESS);
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
			CSCProgRefForm progRefForm =(CSCProgRefForm)aForm;	
			clearList(progRefForm);
			// clean up attributes set for initiate flow
			aRequest.setAttribute("cautionMsg", "");
			aRequest.setAttribute("cautionMsg2", "");
			aRequest.setAttribute("confirmMsg", "");
			
			CSCProgRefUIHelper.clearUserEnteredSP(progRefForm);
				
			List selectedRefTypesList = CSCProgRefUIHelper.getSelectedReferralTypes(progRefForm.getAvailableReferralTypesList());
			progRefForm.setSelectedReferralTypesList(selectedRefTypesList);

			Hashtable savedReferralTypeCdNRefIdMap = new Hashtable();
			Iterator srtIter = progRefForm.getSelectedReferralTypesList().iterator();
			while (srtIter.hasNext() ){
				ReferralTypeBean rtb = (ReferralTypeBean) srtIter.next();
				savedReferralTypeCdNRefIdMap.put(rtb.getReferralTypeDesc(), rtb.getReferralTypeId());
			}

			GetServiceProvidersByReferralTypesEvent get_event = (GetServiceProvidersByReferralTypesEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.GETSERVICEPROVIDERSBYREFERRALTYPES);

			get_event.setDefendantId(progRefForm.getSpn());
			get_event.setCriminalCaseId(progRefForm.getCriminalCaseId());
			List referralTypesCodeList = CSCProgRefUIHelper.getReferralTypeCodes(progRefForm.getSelectedReferralTypesList());
			get_event.setReferralTypes(referralTypesCodeList);
			
//			Initiate the referrals and retrieve the Service Providers for all the selected referrals
			GetServiceProvidersByReferralTypesResponseEvent get_response_event = 
						(GetServiceProvidersByReferralTypesResponseEvent)postRequestEvent(get_event, GetServiceProvidersByReferralTypesResponseEvent.class);

			if(savedReferralTypeCdNRefIdMap!=null && savedReferralTypeCdNRefIdMap.size()>0)
			{
				progRefForm.calculateReferralTypes(savedReferralTypeCdNRefIdMap);
				progRefForm.setReferralTypeCdNPgmRefIdMap((Hashtable)savedReferralTypeCdNRefIdMap);
				progRefForm.setProgRefIdsToDeleteList(progRefForm.getReferralTypeCdNPgmRefIdMap().values());
			}
			List referral_type_codes = new ArrayList();
			for (int i=0;i<progRefForm.getSelectedReferralTypesList().size();i++)
			{
				ReferralTypeBean this_referral_type = (ReferralTypeBean)progRefForm.getSelectedReferralTypesList().get(i);
				referral_type_codes.add(this_referral_type.getReferralTypeCode());
			}
//			convert serviceProvider response Events to ServiceProviderBeans
			List service_provider_list = get_response_event.getServiceProviderRefTypeResponses();
			int spLen = service_provider_list.size();
			for(int i=0;i<spLen; i++)
			{
				ServiceProviderReftypeResponseEvent respEvent = (ServiceProviderReftypeResponseEvent)service_provider_list.get(i);
				CSCServiceProviderBean this_service_provider =
					CSCProgRefUIHelper.convertSPReftypeResponseTOServiceProviderBean(respEvent,progRefForm.getReferralTypeCdNDetailsMap());
				
				progRefForm.getAvailableSPList().add(this_service_provider);
			}
			progRefForm.setFilteredSPList(progRefForm.getAvailableSPList());
			
//			Retrieve the referral forms for the selected referral Types
			List selRefTypesList = progRefForm.getSelectedReferralTypesList();
			Iterator refTypeIter = selRefTypesList.iterator();
			while(refTypeIter.hasNext())
			{
				ReferralTypeBean refTypeBean = (ReferralTypeBean)refTypeIter.next();
				GetReferralFormsEvent referralFormsEvt = new GetReferralFormsEvent();
				referralFormsEvt.setReferralTypeCd(refTypeBean.getReferralTypeCode());
				List  referralFormsList = postRequestListFilter(referralFormsEvt, ReferralFormResponseEvent.class);
				CSCProgRefUIHelper.convertResponseEvtToReferralFormBean(refTypeBean, referralFormsList);
			}
			return aMapping.findForward(UIConstants.PRINT_SUCCESS);
		}
	
		/**
		 * 
		 * @param progRefForm
		 */
		private void clearList(CSCProgRefForm progRefForm)
		{
			progRefForm.setAvailableSPList(new ArrayList());
			progRefForm.setFilteredSPList(new ArrayList());
			progRefForm.setSelectedSPList(new ArrayList());
			
			progRefForm.setReferralTypeCdNPgmRefIdMap(new Hashtable());
			progRefForm.setReferralTypeCdNDetailsMap(new Hashtable());
			progRefForm.setReferralTypesList(new ArrayList());
		}
}

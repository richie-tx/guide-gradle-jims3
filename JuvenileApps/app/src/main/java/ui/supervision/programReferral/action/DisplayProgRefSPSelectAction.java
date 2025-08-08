/*
 * Created on Mar 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerprogramreferrals.GetFilteredServiceProvidersEvent;
import messaging.administerprogramreferrals.GetInitOpenReferralsForRefTypesEvent;
import messaging.administerprogramreferrals.GetProgramLocationsEvent;
import messaging.administerprogramreferrals.reply.ReferralWithRefTypeResponseEvent;
import messaging.administerprogramreferrals.reply.ServiceProviderReftypeResponseEvent;
import messaging.csserviceprovider.reply.CSProgramLocationResponseEvent;
import naming.CSAdministerProgramReferralsConstants;
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
import ui.supervision.programReferral.form.CSCProgRefForm.RefTypeCodeNRefTypeNumNRefIdValue;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayProgRefSPSelectAction extends JIMSBaseAction 
{
	
	private static int INVALID = 0;
	private static int MIX = 1;
	private static int VALID = 2;
	private static final String SP_ID_SEPARATOR = "/";
	

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next","getProviderPrograms");
		keyMap.put("button.filter","filter");
		keyMap.put("button.refresh","refresh");
		keyMap.put("button.printPacket","getProviderPrograms");
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
	public ActionForward getProviderPrograms(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progRefForm=(CSCProgRefForm)aForm;
		
		progRefForm.setProgressedReferralTypesList(new ArrayList());
		progRefForm.setSelectedSPList(new ArrayList());
		
		Set selSPIdSet = new HashSet();
		
		if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_CREATE))
		{
			String selSPIdString = progRefForm.getSelectedSPIdsString();
			StringTokenizer st = new StringTokenizer(selSPIdString, SP_ID_SEPARATOR);
			while(st.hasMoreElements())
			{
				selSPIdSet.add(st.nextToken());
			}
			
			List selectedSPList = getSelectedServiceProviders(progRefForm.getFilteredSPList(), selSPIdSet);
			progRefForm.setSelectedSPList(selectedSPList);
			
			int result = validateCreate(progRefForm);
			if(INVALID == result)
			{
				String errorMsg = "None of the selected referral types will be progressed because selected service provider(s) do not offer programs for that referral type or there are existing program referrals (Initiated or Open status) for that referral type";
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, errorMsg);
				return aMapping.findForward(UIConstants.FAILURE);
			}
			else
			if(MIX == result)
			{
				String errorMsg = "Not all the selected referral types will be progressed because selected service provider(s) do not offer programs for that referral type or there are existing program referrals (Initiated or Open status) for that referral type.";
				aRequest.setAttribute("cautionMsg2", errorMsg);
			}
		}
//		for UPDATE
		else
		{
			Iterator selRefTypeIter = progRefForm.getSelectedReferralTypesList().iterator();
			while(selRefTypeIter.hasNext())
			{
				ReferralTypeBean refTypeBean = (ReferralTypeBean)selRefTypeIter.next();
				refTypeBean.setNotProgressedForSP(false);
			}
			
			int result = validateUpdate(progRefForm);
			if(INVALID == result)
			{
				String errorMsg = "The selected referral type will not be progressed because selected service provider do not offer programs for that referral type or there are existing program referrals (Initiated or Open status) for that referral type";
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, errorMsg);
				return aMapping.findForward(UIConstants.FAILURE);
			}
			
			String selSP = progRefForm.getServiceProviderId();
			if(selSP.equalsIgnoreCase(CSAdministerProgramReferralsConstants.USER_ENTERED_SP))
			{
				progRefForm.setUserEnteredServiceProvider(true);
				
				progRefForm.setScheduledDateAsStr("");
				progRefForm.setScheduledTime("");
			}
			else
			{
				selSPIdSet.add(progRefForm.getServiceProviderId());
				CSCProgRefUIHelper.clearUserEnteredSP(progRefForm);
			}
			
			List selectedSPList = getSelectedServiceProviders(progRefForm.getFilteredSPList(), selSPIdSet);
			progRefForm.setSelectedSPList(selectedSPList);
		}
		
			//construct event for retrieving list of program locations
		GetProgramLocationsEvent prog_loc_event = CSCProgRefUIHelper.getProgramLocationsEvent(progRefForm);
		if((prog_loc_event.getServiceProviderIds().size()>0) && (prog_loc_event.getReferralTypesCodeList().size()>0))
		{
			List sp_prog_loc_response = postRequestListFilter(prog_loc_event,CSProgramLocationResponseEvent.class);
		
			List serviceProviderProgramLocationBeanList = CSCProgRefUIHelper.convertProgramLocationRespEvtToBean(progRefForm, sp_prog_loc_response);
			progRefForm.setSelectedSPList(serviceProviderProgramLocationBeanList);
		}
		else
		{
			progRefForm.setSelectedSPList(new ArrayList());
		}
		
			// direct to program locations page
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}
	
	
	
	
	/**
	 * 
	 * @param progRefForm
	 * @return
	 */
	private int validateUpdate(CSCProgRefForm progRefForm)
	{
		Map selectedRefTypeMap = progRefForm.getReferralTypeCdNDetailsMap();
		Set selectedRefTypeCdSet = selectedRefTypeMap.keySet();
		
		HashSet unProgressedRefTypeCdSet = new HashSet();
		
//		query all the initiate and open referrals for selectedRefTypes
		GetInitOpenReferralsForRefTypesEvent requestEvent = new GetInitOpenReferralsForRefTypesEvent();
		requestEvent.setDefendantId(progRefForm.getSpn());
		List selRefTypesCdList = CSCProgRefUIHelper.getReferralTypeCodes(progRefForm.getSelectedReferralTypesList());
		requestEvent.setRefTypesCdList(selRefTypesCdList);
		List responseList = this.postRequestListFilter(requestEvent, ReferralWithRefTypeResponseEvent.class);
		
//		get the RefTypeCds which are not progressed (if an Initiate or Open Referral is already present for the same RefType)
		if(responseList!=null && responseList.size()>0)
		{
			for(int index=0; index < responseList.size(); index++)
			{
				ReferralWithRefTypeResponseEvent respEvt = (ReferralWithRefTypeResponseEvent)responseList.get(index);
				
				String refTypeCd = respEvt.getReferralTypeCd();
				
				RefTypeCodeNRefTypeNumNRefIdValue refTypeDetailsObj = (RefTypeCodeNRefTypeNumNRefIdValue)selectedRefTypeMap.get(refTypeCd);
				String selectedProgRefId = refTypeDetailsObj.getProgRefId();
				
				if(!respEvt.getProgramReferralId().equalsIgnoreCase(selectedProgRefId))
				{
					unProgressedRefTypeCdSet.add(refTypeCd);
					
//					if all the selected Referral Types are not progressed, then 
					if(unProgressedRefTypeCdSet.size() == selectedRefTypeCdSet.size())
					{
						return INVALID;
					}
				}
			}
		}

		progRefForm.setProgressedReferralTypesList(progRefForm.getSelectedReferralTypesList());
		return VALID;
	}
	
	
	
	/**
	 * 
	 * @param progRefForm
	 * @return
	 */
	private int validateCreate(CSCProgRefForm progRefForm)
	{
		Map selectedRefTypeMap = progRefForm.getReferralTypeCdNDetailsMap();
		Set selectedRefTypeCdSet = selectedRefTypeMap.keySet();
		
		HashSet unProgressedRefTypeCdSet = new HashSet();
		
//		query all the initiate and open referrals for selectedRefTypes
		GetInitOpenReferralsForRefTypesEvent requestEvent = new GetInitOpenReferralsForRefTypesEvent();
		requestEvent.setDefendantId(progRefForm.getSpn());
		List selRefTypesCdList = CSCProgRefUIHelper.getReferralTypeCodes(progRefForm.getSelectedReferralTypesList());
		requestEvent.setRefTypesCdList(selRefTypesCdList);
		List responseList = this.postRequestListFilter(requestEvent, ReferralWithRefTypeResponseEvent.class);
		
//		get the RefTypeCds which are not progressed (because an Initiate or Open Referral is already present for the same RefType)
		if(responseList!=null && responseList.size()>0)
		{
			for(int index=0; index < responseList.size(); index++)
			{
				ReferralWithRefTypeResponseEvent respEvt = (ReferralWithRefTypeResponseEvent)responseList.get(index);
				
				String refTypeCd = respEvt.getReferralTypeCd();
				
				RefTypeCodeNRefTypeNumNRefIdValue refTypeDetailsObj = (RefTypeCodeNRefTypeNumNRefIdValue)selectedRefTypeMap.get(refTypeCd);
				String selectedProgRefId = refTypeDetailsObj.getProgRefId();
				
				if(!respEvt.getProgramReferralId().equalsIgnoreCase(selectedProgRefId))
				{
					unProgressedRefTypeCdSet.add(refTypeCd);
					
//					if all the selected Referral Types are not progressed, then 
					if(unProgressedRefTypeCdSet.size() == selectedRefTypeCdSet.size())
					{
						return INVALID;
					}
				}
			}
		}

		Set spUnprogRefTypeCdSet = getSpUnprogRefTypeCds(progRefForm, selectedRefTypeCdSet);
		unProgressedRefTypeCdSet.addAll(spUnprogRefTypeCdSet);
		
//		if all the selected Referral Types are not progressed, then 
		if(unProgressedRefTypeCdSet.size() == selectedRefTypeCdSet.size())
		{
			return INVALID;
		}
		
//		In case of mix, mark unprogressed RefTypes in SelectedRefTypeList
		if(unProgressedRefTypeCdSet.size() > 0)
		{
			ArrayList progressedRefTypeList = new ArrayList();
			
			Iterator selRefTypeIter = progRefForm.getSelectedReferralTypesList().iterator();
			while(selRefTypeIter.hasNext())
			{
				ReferralTypeBean refTypeBean = (ReferralTypeBean)selRefTypeIter.next();
				if(unProgressedRefTypeCdSet.contains(refTypeBean.getReferralTypeCode()))
				{
					refTypeBean.setNotProgressedForSP(true);
				}
				else
				{
					refTypeBean.setNotProgressedForSP(false);
					progressedRefTypeList.add(refTypeBean);
				}
			}
			
			progRefForm.setProgressedReferralTypesList(progressedRefTypeList);
			return MIX;
		}
		
//		If all the referral types are progressed
		Iterator selRefTypeIter = progRefForm.getSelectedReferralTypesList().iterator();
		while(selRefTypeIter.hasNext())
		{
			ReferralTypeBean refTypeBean = (ReferralTypeBean)selRefTypeIter.next();
			refTypeBean.setNotProgressedForSP(false);
		}
			
		progRefForm.setProgressedReferralTypesList(progRefForm.getSelectedReferralTypesList());
		return VALID;
	}
	
	
	
	
	
	/**
	 * 
	 * @param progRefForm
	 * @param selectedRefTypeCdSet
	 * @return
	 */
	private Set getSpUnprogRefTypeCds(CSCProgRefForm progRefForm, Set selectedRefTypeCdSet)
	{
		HashSet progRefTypeCdSet = new HashSet();
		HashSet unprogRefTypeCdSet = new HashSet();
		
//		obtain all the progressed refTypes from the already-defined SPs
		List selectedSPList = progRefForm.getSelectedSPList();
		Iterator iter = selectedSPList.iterator();
		while(iter.hasNext())
		{
			CSCServiceProviderBean spBean = (CSCServiceProviderBean)iter.next();
			List refTypeCdsList = spBean.getServiceProviderRefTypeCdList();
			
			progRefTypeCdSet.addAll(refTypeCdsList);
		}
		
//		obtain all the progressed refTypes from the user-defined SP, if selected
		if(progRefForm.isUserEnteredServiceProvider())
		{
			progRefTypeCdSet.add(progRefForm.getUserEnteredServiceProviderRefTypeCd());
		}
		
//		obtain the unprogressed refTypeCds
		Iterator refTypeIter =  selectedRefTypeCdSet.iterator();
		while(refTypeIter.hasNext())
		{
			String selectedRefType = (String)refTypeIter.next();
			
			if(!progRefTypeCdSet.contains(selectedRefType))
			{
				unprogRefTypeCdSet.add(selectedRefType);
			}
		}
		return unprogRefTypeCdSet;
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
	public ActionForward filter(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progRefForm=(CSCProgRefForm)aForm;
		
		progRefForm.setFilteredSPList(new ArrayList());
		progRefForm.setSelectedSPList(new ArrayList());
		
		GetFilteredServiceProvidersEvent filterServiceProviderEvt = CSCProgRefUIHelper.getFilteredServiceProvidersEvent(progRefForm);
		
		List filtered_SP_Resp_Evt = postRequestListFilter(filterServiceProviderEvt, ServiceProviderReftypeResponseEvent.class);
		int num_service_providers = filtered_SP_Resp_Evt.size();
		
		for(int i=0;i<num_service_providers; i++)
		{
			CSCServiceProviderBean this_service_provider =
				CSCProgRefUIHelper.convertSPReftypeResponseTOServiceProviderBean(
						(ServiceProviderReftypeResponseEvent)filtered_SP_Resp_Evt.get(i),
						 progRefForm.getReferralTypeCdNDetailsMap());
			
			progRefForm.getFilteredSPList().add(this_service_provider);
		}
		
		ActionForward forward = aMapping.findForward(UIConstants.FILTER_SUCCESS);
		return forward;
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
	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progRefForm=(CSCProgRefForm)aForm;
		
		progRefForm.setFilteredSPList(progRefForm.getAvailableSPList());
		progRefForm.setSelectedSPList(new ArrayList());
		
		progRefForm.setSelectedRegionIds(new String[0]);
		progRefForm.setSelectedLanguagesIds(new String[0]);
		progRefForm.setSelectedSexSpecificId("");
		progRefForm.setSelectedContractProgram("");
		
		ActionForward forward = aMapping.findForward(UIConstants.FILTER_SUCCESS);
		return forward;
	}
	
	
	
	/**
	 * Determine which service providers have been selected
	 * @param availableReferralTypes
	 * @return
	 */
	private List getSelectedServiceProviders(List filteredSPs, Set selSPIdSet)
	{
		List selected_sps = new ArrayList();
		
		int num_sps = filteredSPs.size();
		for (int i=0;i<num_sps;i++)
		{
			CSCServiceProviderBean this_sp = (CSCServiceProviderBean)filteredSPs.get(i);

			if(selSPIdSet.contains(this_sp.getServiceProviderId()))
			{
				selected_sps.add(this_sp);
			}
		}
		return selected_sps;
	}
	
}//end of DisplayProgRefSPSelectAction class


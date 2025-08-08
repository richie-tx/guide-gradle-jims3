package ui.supervision.programReferral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import messaging.administerprogramreferrals.GetProgRefByCaseloadEvent;
import messaging.administerprogramreferrals.reply.ProgrRefByCaseloadResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import naming.CSAdministerProgramReferralsConstants;
import naming.CSProgramReferralControllerServiceNames;
import ui.common.PhoneNumber;
import ui.common.StringUtil;
import ui.supervision.programReferral.form.CSCProgRefCaseloadForm;

/**
 * 
 * @author cc_bjangay
 *
 */
public class CSCProgRefByCaseloadUIHelper 
{
	
	/**
	 * 
	 * @param prgRefCaseloadForm
	 * @return
	 */
	public static GetProgRefByCaseloadEvent getProgRefByCaseloadEvent(CSCProgRefCaseloadForm prgRefCaseloadForm)
	{
		GetProgRefByCaseloadEvent requestEvt = (GetProgRefByCaseloadEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.GETPROGREFBYCASELOAD);
		
		requestEvt.setSearchBy(prgRefCaseloadForm.getSearchBy());
		requestEvt.setDefendantIdsList(prgRefCaseloadForm.getDefendantIdsList());
		
		requestEvt.setServiceProviderName(prgRefCaseloadForm.getServiceProviderName());
		requestEvt.setIsInHouse(prgRefCaseloadForm.getInHouse());
		requestEvt.setRegionCd(prgRefCaseloadForm.getRegionCd());
		
		requestEvt.setProgramName(prgRefCaseloadForm.getProgramName());
		requestEvt.setCstsCode(prgRefCaseloadForm.getCstsCode());
		requestEvt.setProgramGroupId(prgRefCaseloadForm.getProgramGroupId());
		requestEvt.setProgramTypeId(prgRefCaseloadForm.getProgramTypeId());
		requestEvt.setProgramSubTypeId(prgRefCaseloadForm.getProgramSubTypeId());
		
		return requestEvt;
	}
	
	
	/**
	 * 
	 * @param progRefCaseloadForm
	 * @param responseEvtList
	 */
	public static void populateBeansForSPSearch(CSCProgRefCaseloadForm progRefCaseloadForm, List<ProgrRefByCaseloadResponseEvent> responseEvtList)
	{
		int activeReferrals = 0;
		int exitedReferrals = 0;
		HashMap spIdBeanMap = new HashMap();
		HashMap pgmIdBeanMap = new HashMap();
		HashMap locIdBeanMap = new HashMap();
		
		Map sortedMap = new TreeMap();
		
		progRefCaseloadForm.setSerProvIdBeanMap(spIdBeanMap);
		progRefCaseloadForm.setProgramIdBeanMap(pgmIdBeanMap);
		progRefCaseloadForm.setLocIdBeanMap(locIdBeanMap);
		
		for(int index = 0; index < responseEvtList.size(); index++)
		{
			ProgrRefByCaseloadResponseEvent responseEvt = responseEvtList.get(index);
			CSProgRefCaseloadBean progRefCaseloadBean = new CSProgRefCaseloadBean();
			progRefCaseloadBean.setProgramReferralId(responseEvt.getProgramReferralId());
			progRefCaseloadBean.setDefendantId(responseEvt.getDefendantId());
			progRefCaseloadBean.setSuperviseeName(responseEvt.getSuperviseeName());
			progRefCaseloadBean.setCriminalCaseId(responseEvt.getCriminalCaseId());
			progRefCaseloadBean.setCaseNumber(responseEvt.getCriminalCaseId().substring(3));
			progRefCaseloadBean.setReferralTypeCd(responseEvt.getReferralTypeCd());
			progRefCaseloadBean.setReferralTypeDesc(responseEvt.getReferralTypeDesc());
			progRefCaseloadBean.setReferralStatusCd(responseEvt.getReferralStatusCd());
			progRefCaseloadBean.setServiceProviderId(responseEvt.getServiceProviderId());
			progRefCaseloadBean.setProgramId(responseEvt.getProgramId());
			progRefCaseloadBean.setProgramIdentifier(responseEvt.getProgramIdentifier());
			progRefCaseloadBean.setReferralDateAsStr(DateUtil.dateToString(responseEvt.getReferralDate(), DateUtil.DATE_FMT_1));
			progRefCaseloadBean.setReferralBeginDateAsStr(DateUtil.dateToString(responseEvt.getReferralBeginDate(), DateUtil.DATE_FMT_1));
			progRefCaseloadBean.setReferralEndDateAsStr(DateUtil.dateToString(responseEvt.getReferralEndDate(), DateUtil.DATE_FMT_1));
			
			CSLocByPrgRefCaseloadBean locBean = null;
			CSProgramByPrgRefCaseloadBean prgmBean = null;
			CSServProvByPrgRefCaseloadBean spBean = null;
			
			if(!locIdBeanMap.containsKey(responseEvt.getLocationId()))
			{
				locBean = new CSLocByPrgRefCaseloadBean();
				locBean.setLocationId(responseEvt.getLocationId());
				locBean.setLocationName(responseEvt.getLocationName());
				locBean.setStreetNumber(responseEvt.getStreetNum());
				locBean.setStreetName(responseEvt.getStreetName());
				locBean.setStreetTypeCd(responseEvt.getStreetTypeCd());
				locBean.setAptNum(responseEvt.getAptNum());
				locBean.setCity(responseEvt.getCity());
				locBean.setState(responseEvt.getState());
				locBean.setZipCode(responseEvt.getZipCode());
				locBean.setLocationPhone(new PhoneNumber(responseEvt.getLocationPhone()));
				locBean.setLocPrgReferralList(new ArrayList());
				locBean.setOpenProgReferralList(new ArrayList());
				locBean.setAllProgReferralList(new ArrayList());
				
				locIdBeanMap.put(responseEvt.getLocationId(), locBean);
			}
			else
			{
				locBean = (CSLocByPrgRefCaseloadBean)locIdBeanMap.get(responseEvt.getLocationId());
			}
			
			if(!pgmIdBeanMap.containsKey(responseEvt.getProgramId()))
			{
				prgmBean = new CSProgramByPrgRefCaseloadBean();
				prgmBean.setProgramId(responseEvt.getProgramId());
				prgmBean.setProgramIdentifier(responseEvt.getProgramIdentifier());
				prgmBean.setProgramName(responseEvt.getProgramName());
				prgmBean.setServiceProviderId(responseEvt.getServiceProviderId());
				prgmBean.setServiceProviderName(responseEvt.getServiceProviderName());
				prgmBean.setCstsCode(responseEvt.getCtsCode());
				prgmBean.setReferralTypeCd(responseEvt.getReferralTypeCd());
				prgmBean.setReferralTypeDesc(responseEvt.getReferralTypeDesc());
				prgmBean.setLocationIdsSet(new HashSet());
				prgmBean.setLocationList(new ArrayList());
				prgmBean.setProgramPrgReferralList(new ArrayList());
				prgmBean.setOpenProgReferralList(new ArrayList());
				prgmBean.setAllProgReferralList(new ArrayList());
				
				prgmBean.getAllProgReferralList().add(progRefCaseloadBean);
				prgmBean.getOpenProgReferralList().add(progRefCaseloadBean);
				prgmBean.getProgramPrgReferralList().add(progRefCaseloadBean);
				prgmBean.getLocationIdsSet().add(locBean.getLocationId());
				prgmBean.getLocationList().add(locBean);
				
				pgmIdBeanMap.put(responseEvt.getProgramId(), prgmBean);
			}
			else
			{
				prgmBean = (CSProgramByPrgRefCaseloadBean)pgmIdBeanMap.get(responseEvt.getProgramId());
				
				prgmBean.getAllProgReferralList().add(progRefCaseloadBean);
				prgmBean.getOpenProgReferralList().add(progRefCaseloadBean);
				prgmBean.getProgramPrgReferralList().add(progRefCaseloadBean);
				if(!(prgmBean.getLocationIdsSet().contains(locBean.getLocationId())))
				{
					prgmBean.getLocationIdsSet().add(locBean.getLocationId());
					prgmBean.getLocationList().add(locBean);
				}
			}
			
			if(!spIdBeanMap.containsKey(responseEvt.getServiceProviderId()))
			{
				spBean = new CSServProvByPrgRefCaseloadBean();
				spBean.setServiceProviderId(responseEvt.getServiceProviderId());
				spBean.setServiceProviderName(responseEvt.getServiceProviderName());
				spBean.setInHouse(responseEvt.isInHouse()?"Yes":"No");
				spBean.setLocRegionCdsSet(new HashSet());
				spBean.setSpProgramIdsSet(new HashSet());
				spBean.setSpProgramsList(new ArrayList());
				spBean.setSpProgReferralList(new ArrayList());
				spBean.setOpenProgReferralList(new ArrayList());
				spBean.setAllProgReferralList(new ArrayList());
				
				if(!StringUtil.isEmpty(responseEvt.getRegionCd()))
				{
					spBean.getLocRegionCdsSet().add(responseEvt.getRegionCd());
				}
				
				spBean.getAllProgReferralList().add(progRefCaseloadBean);
				spBean.getOpenProgReferralList().add(progRefCaseloadBean);
				spBean.getSpProgReferralList().add(progRefCaseloadBean);
				spBean.getSpProgramIdsSet().add(prgmBean.getProgramId());
				spBean.getSpProgramsList().add(prgmBean);
				
				spIdBeanMap.put(responseEvt.getServiceProviderId(), spBean);
			}
			else
			{
				spBean = (CSServProvByPrgRefCaseloadBean)spIdBeanMap.get(responseEvt.getServiceProviderId());
				
				if(!StringUtil.isEmpty(responseEvt.getRegionCd()))
				{
					spBean.getLocRegionCdsSet().add(responseEvt.getRegionCd());
				}
				
				spBean.getAllProgReferralList().add(progRefCaseloadBean);
				spBean.getOpenProgReferralList().add(progRefCaseloadBean);
				spBean.getSpProgReferralList().add(progRefCaseloadBean);
				if(!(spBean.getSpProgramIdsSet().contains(prgmBean.getProgramId())))
				{
					spBean.getSpProgramIdsSet().add(prgmBean.getProgramId());
					spBean.getSpProgramsList().add(prgmBean);
				}
			}
			if(!progRefCaseloadBean.getReferralStatusCd().equalsIgnoreCase(CSAdministerProgramReferralsConstants.EXITED_REFERRAL_STATUS))
			{
				activeReferrals++;
			}else{
				exitedReferrals++;
			}
		}
		
		sortedMap = spIdBeanMap;
		progRefCaseloadForm.setServiceProvidersList(sortedMap.values());
		if(progRefCaseloadForm.getServiceProvidersList()!=null)
		{
			progRefCaseloadForm.setActiveReferrals(Integer.toString(activeReferrals));
			progRefCaseloadForm.setExitedReferrals(Integer.toString(exitedReferrals));
			progRefCaseloadForm.setServiceProvidersSize(Integer.toString(progRefCaseloadForm.getServiceProvidersList().size()));
		}
	}
	
	
	
	/**
	 * 
	 * @param progRefCaseloadForm
	 * @param responseEvtList
	 */
	public static void populateBeansForPgmSearch(CSCProgRefCaseloadForm progRefCaseloadForm, List<ProgrRefByCaseloadResponseEvent> responseEvtList)
	{
		HashMap pgmNameBeanMap = new HashMap();
		HashMap pgmIdBeanMap = new HashMap();
		
		progRefCaseloadForm.setProgramNameBeanMap(pgmNameBeanMap);
		progRefCaseloadForm.setProgramIdBeanMap(pgmIdBeanMap);
		
		for(int index = 0; index < responseEvtList.size(); index++)
		{
			ProgrRefByCaseloadResponseEvent responseEvt = responseEvtList.get(index);
			
			CSProgRefCaseloadBean progRefCaseloadBean = new CSProgRefCaseloadBean();
			progRefCaseloadBean.setProgramReferralId(responseEvt.getProgramReferralId());
			progRefCaseloadBean.setDefendantId(responseEvt.getDefendantId());
			progRefCaseloadBean.setSuperviseeName(responseEvt.getSuperviseeName());
			progRefCaseloadBean.setCriminalCaseId(responseEvt.getCriminalCaseId());
			progRefCaseloadBean.setCaseNumber(responseEvt.getCriminalCaseId().substring(3));
			progRefCaseloadBean.setReferralTypeCd(responseEvt.getReferralTypeCd());
			progRefCaseloadBean.setReferralTypeDesc(responseEvt.getReferralTypeDesc());
			progRefCaseloadBean.setReferralStatusCd(responseEvt.getReferralStatusCd());
			progRefCaseloadBean.setServiceProviderId(responseEvt.getServiceProviderId());
			progRefCaseloadBean.setProgramId(responseEvt.getProgramId());
			progRefCaseloadBean.setProgramIdentifier(responseEvt.getProgramIdentifier());
			progRefCaseloadBean.setReferralDateAsStr(DateUtil.dateToString(responseEvt.getReferralDate(), DateUtil.DATE_FMT_1));
			progRefCaseloadBean.setReferralBeginDateAsStr(DateUtil.dateToString(responseEvt.getReferralBeginDate(), DateUtil.DATE_FMT_1));
			progRefCaseloadBean.setReferralEndDateAsStr(DateUtil.dateToString(responseEvt.getReferralEndDate(), DateUtil.DATE_FMT_1));
			
			CSProgramByPrgRefCaseloadBean prgmIdBean = null;
			CSProgNameByPrgRefCaseloadBean prgmNameBean = null;
			
			if(!pgmIdBeanMap.containsKey(responseEvt.getProgramId()))
			{
				prgmIdBean = new CSProgramByPrgRefCaseloadBean();
				prgmIdBean.setProgramId(responseEvt.getProgramId());
				prgmIdBean.setProgramIdentifier(responseEvt.getProgramIdentifier());
				prgmIdBean.setProgramName(responseEvt.getProgramName());
				prgmIdBean.setServiceProviderId(responseEvt.getServiceProviderId());
				prgmIdBean.setServiceProviderName(responseEvt.getServiceProviderName());
				prgmIdBean.setCstsCode(responseEvt.getCtsCode());
				prgmIdBean.setReferralTypeCd(responseEvt.getReferralTypeCd());
				prgmIdBean.setReferralTypeDesc(responseEvt.getReferralTypeDesc());
				prgmIdBean.setProgramPrgReferralList(new ArrayList());
				prgmIdBean.setOpenProgReferralList(new ArrayList());
				prgmIdBean.setAllProgReferralList(new ArrayList());
				
				prgmIdBean.getAllProgReferralList().add(progRefCaseloadBean);
				/*if(!progRefCaseloadBean.getReferralStatusCd().equalsIgnoreCase(CSAdministerProgramReferralsConstants.EXITED_REFERRAL_STATUS))
				{*/
					prgmIdBean.getOpenProgReferralList().add(progRefCaseloadBean);
					prgmIdBean.getProgramPrgReferralList().add(progRefCaseloadBean);
				//}
				
				pgmIdBeanMap.put(responseEvt.getProgramId(), prgmIdBean);
			}
			else
			{
				prgmIdBean = (CSProgramByPrgRefCaseloadBean)pgmIdBeanMap.get(responseEvt.getProgramId());
				
				prgmIdBean.getAllProgReferralList().add(progRefCaseloadBean);
				/*if(!progRefCaseloadBean.getReferralStatusCd().equalsIgnoreCase(CSAdministerProgramReferralsConstants.EXITED_REFERRAL_STATUS))
				{*/
					prgmIdBean.getOpenProgReferralList().add(progRefCaseloadBean);
					prgmIdBean.getProgramPrgReferralList().add(progRefCaseloadBean);
				//}
			}
			
			if(!pgmNameBeanMap.containsKey(responseEvt.getProgramName()))
			{
				prgmNameBean = new CSProgNameByPrgRefCaseloadBean();
				prgmNameBean.setProgramName(responseEvt.getProgramName());
				prgmNameBean.setProgramIdsSet(new HashSet());
				prgmNameBean.setProgNamePrgReferralList(new ArrayList());
				prgmNameBean.setOpenProgReferralList(new ArrayList());
				prgmNameBean.setAllProgReferralList(new ArrayList());
				prgmNameBean.setProgramIdsBeanList(new ArrayList());
				
				prgmNameBean.getAllProgReferralList().add(progRefCaseloadBean);
				prgmNameBean.getProgramIdsSet().add(responseEvt.getProgramId());
				prgmNameBean.getProgramIdsBeanList().add(prgmIdBean);
				if(!progRefCaseloadBean.getReferralStatusCd().equalsIgnoreCase(CSAdministerProgramReferralsConstants.EXITED_REFERRAL_STATUS))
				{
					prgmNameBean.getOpenProgReferralList().add(progRefCaseloadBean);
					prgmNameBean.getProgNamePrgReferralList().add(progRefCaseloadBean);
					pgmNameBeanMap.put(responseEvt.getProgramName(), prgmNameBean);
				}
				
			}
			else
			{
				prgmNameBean = (CSProgNameByPrgRefCaseloadBean)pgmNameBeanMap.get(responseEvt.getProgramName());
				
				prgmNameBean.getAllProgReferralList().add(progRefCaseloadBean);
				if(!progRefCaseloadBean.getReferralStatusCd().equalsIgnoreCase(CSAdministerProgramReferralsConstants.EXITED_REFERRAL_STATUS))
				{
					prgmNameBean.getOpenProgReferralList().add(progRefCaseloadBean);
					prgmNameBean.getProgNamePrgReferralList().add(progRefCaseloadBean);
				}
				
				if(!prgmNameBean.getProgramIdsSet().contains(prgmIdBean.getProgramId()))
				{
					prgmNameBean.getProgramIdsSet().add(prgmIdBean.getProgramId());
					prgmNameBean.getProgramIdsBeanList().add(prgmIdBean);
				}
			}
		}
		
		List mapValues = new ArrayList( pgmNameBeanMap.values() );

		TreeSet sortedSet = new TreeSet( mapValues );

		progRefCaseloadForm.setProgramNamesList( sortedSet );
		if(progRefCaseloadForm.getProgramNamesList()!=null)
		{
			progRefCaseloadForm.setProgramNamesSize(Integer.toString(progRefCaseloadForm.getProgramNamesList().size()));
		}
	}
	
	
	
	/**
	 * 
	 * @param progRefCaseloadForm
	 * @param responseEvtList
	 */
	public static void populateBeansForProgLocationSearch(CSCProgRefCaseloadForm progRefCaseloadForm, List<ProgrRefByCaseloadResponseEvent> responseEvtList)
	{
		progRefCaseloadForm.setReferralsList(new ArrayList());
		progRefCaseloadForm.setAllReferralsList(new ArrayList());
		
		for(int index = 0; index < responseEvtList.size(); index++)
		{
			ProgrRefByCaseloadResponseEvent responseEvt = responseEvtList.get(index);
			
			CSProgRefCaseloadBean progRefCaseloadBean = new CSProgRefCaseloadBean();
			progRefCaseloadBean.setProgramReferralId(responseEvt.getProgramReferralId());
			progRefCaseloadBean.setDefendantId(responseEvt.getDefendantId());
			progRefCaseloadBean.setSuperviseeName(responseEvt.getSuperviseeName());
			progRefCaseloadBean.setCriminalCaseId(responseEvt.getCriminalCaseId());
			progRefCaseloadBean.setCaseNumber(responseEvt.getCriminalCaseId().substring(3));
			progRefCaseloadBean.setReferralTypeCd(responseEvt.getReferralTypeCd());
			progRefCaseloadBean.setReferralTypeDesc(responseEvt.getReferralTypeDesc());
			progRefCaseloadBean.setReferralStatusCd(responseEvt.getReferralStatusCd());
			progRefCaseloadBean.setServiceProviderId(responseEvt.getServiceProviderId());
			progRefCaseloadBean.setProgramId(responseEvt.getProgramId());
			progRefCaseloadBean.setProgramIdentifier(responseEvt.getProgramIdentifier());
			progRefCaseloadBean.setReferralDateAsStr(DateUtil.dateToString(responseEvt.getReferralDate(), DateUtil.DATE_FMT_1));
			progRefCaseloadBean.setReferralBeginDateAsStr(DateUtil.dateToString(responseEvt.getReferralBeginDate(), DateUtil.DATE_FMT_1));
			
			progRefCaseloadForm.getAllReferralsList().add(progRefCaseloadBean);
			if(!progRefCaseloadBean.getReferralStatusCd().equalsIgnoreCase(CSAdministerProgramReferralsConstants.EXITED_REFERRAL_STATUS))
			{
				progRefCaseloadForm.getReferralsList().add(progRefCaseloadBean);
			}
		}
		
		HashSet defendantIdsSet = new HashSet();
		List referralsList = (List)progRefCaseloadForm.getReferralsList();
		for(int index=0; index < referralsList.size(); index++)
		{
			CSProgRefCaseloadBean prgRefCaseloadBean = (CSProgRefCaseloadBean)referralsList.get(index);
			if(!defendantIdsSet.contains(prgRefCaseloadBean.getDefendantId()))
			{
				defendantIdsSet.add(prgRefCaseloadBean.getDefendantId());
			}
		}
		progRefCaseloadForm.setSuperviseeSize(Integer.toString(defendantIdsSet.size()));
	}
}
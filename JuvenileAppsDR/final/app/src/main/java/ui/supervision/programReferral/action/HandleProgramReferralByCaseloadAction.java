package ui.supervision.programReferral.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import messaging.administercaseload.GetCaseloadEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administerprogramreferrals.GetCSTSCodesEvent;
import messaging.administerprogramreferrals.GetProgRefByCaseloadEvent;
import messaging.administerprogramreferrals.GetSupervisionOrderByCaseEvent;
import messaging.administerprogramreferrals.reply.CSTSCodesResponseEvent;
import messaging.administerprogramreferrals.reply.ProgrRefByCaseloadResponseEvent;
import messaging.administerprogramreferrals.reply.SupervisionOrderByCaseResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.CSAdministerProgramReferralsConstants;
import naming.CSProgramReferralControllerServiceNames;
import naming.CaseloadConstants;
import naming.CaseloadControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administerserviceprovider.programreferral.action.UIProgramHierarchyBean;
import ui.supervision.managetasks.form.TasksSearchForm;
import ui.supervision.managetasks.helper.UIManagetasksHelper;
import ui.supervision.programReferral.CSCProgRefByCaseloadUIHelper;
import ui.supervision.programReferral.CSLocByPrgRefCaseloadBean;
import ui.supervision.programReferral.CSProgNameByPrgRefCaseloadBean;
import ui.supervision.programReferral.CSProgRefCaseloadBean;
import ui.supervision.programReferral.CSProgramByPrgRefCaseloadBean;
import ui.supervision.programReferral.CSServProvByPrgRefCaseloadBean;
import ui.supervision.programReferral.form.CSCProgRefCaseloadForm;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * 
 * @author cc_bjangay
 *
 */
public class HandleProgramReferralByCaseloadAction extends JIMSBaseAction
{

	@Override
	protected void addButtonMapping(Map keyMap) 
	{
		keyMap.put("button.link", "link");
		keyMap.put("button.submit", "submit");
		keyMap.put("button.viewSPCaseload", "viewServiceProviderCaseload");
		keyMap.put("button.viewProgramCaseload", "viewProgramCaseload");
		keyMap.put("button.caseloadSearch", "viewProgramCaseloadTask");
		keyMap.put("button.viewProgramNameCaseload", "viewProgramNameCaseload");
		keyMap.put("button.viewLocationCaseload", "viewLocationCaseload");
		keyMap.put("button.viewPrograms", "viewPrograms");
		keyMap.put("button.viewLocations", "viewLocations");
		keyMap.put("button.viewPgmReferral", "viewProgramReferral");
		keyMap.put("button.viewExitedReferrals", "viewAllReferrals");
		keyMap.put("button.viewOrderVersions", "viewOrderVersions");
		keyMap.put("button.cancel", "cancel");
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
	public ActionForward link(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefCaseloadForm progRefCaseloadForm = (CSCProgRefCaseloadForm)aForm;
		progRefCaseloadForm.clearAll();
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
		SuperviseeForm superviseeForm = (SuperviseeForm)this.getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_FORM, true);
		String fromPage = aRequest.getParameter("fromPage");
		if (fromPage == null){
			superviseeForm.setFromPage("");
		} else {
			superviseeForm.setFromPage(fromPage);
		}
		aRequest.setAttribute("fromPage","");

		progRefCaseloadForm.setOfficerNamePosition(caseAssignmentForm.getOfficerCaseload().getSelectedOfficerName());
		
		progRefCaseloadForm.setSearchBy(CSAdministerProgramReferralsConstants.SERVICE_PROVIDER_SEARCH);
		
		List regionsList = CodeHelper.getCodes(PDCodeTableConstants.LOCATION_REGION, true);	
		progRefCaseloadForm.setRegionsList(regionsList);
		
		GetCSTSCodesEvent cstsReqEvt = new GetCSTSCodesEvent();
		CSTSCodesResponseEvent cstsResponseEvt = (CSTSCodesResponseEvent)this.postRequestEvent(cstsReqEvt, CSTSCodesResponseEvent.class);
		
		progRefCaseloadForm.populateCSTSCodeBeans(cstsResponseEvt.getCstsCodesSet());
		
		List progGroups = SimpleCodeTableHelper.getCodesSortedByCode("CS_PROGRAM_GROUP");
		List progTypes = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("CS_PROGRAM_TYPE");
		UIProgramHierarchyBean hierarchyBean = new UIProgramHierarchyBean();

		List hierarchyBeans = new ArrayList();
		
		String parentCode = "";
		List children = new ArrayList();
		
		for ( int ctr = 0; ctr< progGroups.size();ctr++){
			
			CodeResponseEvent groupCode = (CodeResponseEvent) progGroups.get(ctr);
			if ( "ACTIVE".equalsIgnoreCase( groupCode.getStatus() )){

				parentCode = groupCode.getCode();	
				hierarchyBean = new UIProgramHierarchyBean();
				hierarchyBean.setParentCd( parentCode );
				hierarchyBean.setParentDesc(groupCode.getDescription());
				
				for (int cntr =0; cntr< progTypes.size(); cntr++)
				{
					JuvenileCodeTableChildCodesResponseEvent joscre = (JuvenileCodeTableChildCodesResponseEvent) progTypes.get( cntr );
	
					if ( "ACTIVE".equalsIgnoreCase(joscre.getStatus()) && parentCode.equalsIgnoreCase( joscre.getParentId() ) ) {
						
						children.add( joscre );
	
					}
				}	
				hierarchyBean.setChildEvents( children );
				hierarchyBeans.add( hierarchyBean );
				children = new ArrayList();
			}
		}
		progRefCaseloadForm.setProgramHeirarchyList( hierarchyBeans );
		
		return aMapping.findForward(CSAdministerProgramReferralsConstants.REFRRAL_CASELOAD_SUCCESS);
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
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefCaseloadForm progRefCaseloadForm = (CSCProgRefCaseloadForm)aForm;
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
		String returnString = "";
		List defendantIdsList = new ArrayList();
		List defendantsSupervisedList = caseAssignmentForm.getOfficerCaseload().getDefendantsSupervised();
		Iterator<CaseAssignmentResponseEvent> iter = defendantsSupervisedList.iterator();
		while(iter.hasNext())
		{
			CaseAssignmentResponseEvent responeEvt = iter.next();
			defendantIdsList.add(responeEvt.getDefendantId());
		}
		progRefCaseloadForm.setDefendantIdsList(defendantIdsList);
		
		GetProgRefByCaseloadEvent reqEvt = CSCProgRefByCaseloadUIHelper.getProgRefByCaseloadEvent(progRefCaseloadForm);
		List<ProgrRefByCaseloadResponseEvent> responseEvtList = this.postRequestListFilter(reqEvt, ProgrRefByCaseloadResponseEvent.class);
		
		if(progRefCaseloadForm.getSearchBy().equalsIgnoreCase(CSAdministerProgramReferralsConstants.SERVICE_PROVIDER_SEARCH))
		{
			CSCProgRefByCaseloadUIHelper.populateBeansForSPSearch(progRefCaseloadForm, responseEvtList);
			if(progRefCaseloadForm.getServiceProvidersList().size()==0)
			{
				this.sendToErrorPage(aRequest, "error.generic", "Caseload has no active referrals for selected criteria");	
				returnString = UIConstants.SEARCH_NORESULTS;				
			}else{	
				returnString = CSAdministerProgramReferralsConstants.SP_SEARCH_SUCCESS;
			}
		}else{
			CSCProgRefByCaseloadUIHelper.populateBeansForPgmSearch(progRefCaseloadForm, responseEvtList);
			if(progRefCaseloadForm.getProgramNamesList().size()==0)
			{
				this.sendToErrorPage(aRequest, "error.generic", "Caseload has no active referrals for selected criteria");	
				returnString = UIConstants.SEARCH_NORESULTS;				
			}else{
				returnString = CSAdministerProgramReferralsConstants.PGM_SEARCH_SUCCESS;
			}
		}
		return aMapping.findForward( returnString );
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
	public ActionForward viewServiceProviderCaseload(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefCaseloadForm progRefCaseloadForm = (CSCProgRefCaseloadForm)aForm;
		String selectedSpId = progRefCaseloadForm.getSelectedId();
		progRefCaseloadForm.clearSpCurrentCaseloadHeader();
		
		progRefCaseloadForm.setAction(CSAdministerProgramReferralsConstants.ACTION_VIEW_SP_PRGREF_CASELD);
		progRefCaseloadForm.setSelectedSpId(selectedSpId);
		
		Map spIdBeanMap = progRefCaseloadForm.getSerProvIdBeanMap();
		CSServProvByPrgRefCaseloadBean spBean = (CSServProvByPrgRefCaseloadBean)spIdBeanMap.get(selectedSpId);
		progRefCaseloadForm.setSpName(spBean.getServiceProviderName());
		progRefCaseloadForm.setReferralsList(spBean.getSpProgReferralList());
		progRefCaseloadForm.setAllReferralsList(spBean.getAllProgReferralList());
		
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
		
		return aMapping.findForward(CSAdministerProgramReferralsConstants.VIEW_PROGRAM_REFERRAL_SUCCESS);
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
	public ActionForward viewProgramCaseload(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefCaseloadForm progRefCaseloadForm = (CSCProgRefCaseloadForm)aForm;
		String selectedPgmId = progRefCaseloadForm.getSelectedId();
		progRefCaseloadForm.clearProgramCurrentCaseloadHeader();
		
		progRefCaseloadForm.setAction(CSAdministerProgramReferralsConstants.ACTION_VIEW_PGM_PRGREF_CASELD);
		progRefCaseloadForm.setSelectedProgramId(selectedPgmId);
		
		Map pgmIdBeanMap = progRefCaseloadForm.getProgramIdBeanMap();
		CSProgramByPrgRefCaseloadBean programBean = (CSProgramByPrgRefCaseloadBean)pgmIdBeanMap.get(selectedPgmId);
		progRefCaseloadForm.setSpName(programBean.getServiceProviderName());
		progRefCaseloadForm.setProgName(programBean.getProgramName());
		progRefCaseloadForm.setProgIdentifier(programBean.getProgramIdentifier());
		progRefCaseloadForm.setReferralsList(programBean.getProgramPrgReferralList());
		progRefCaseloadForm.setAllReferralsList(programBean.getAllProgReferralList());
		
		HashSet defendantIdsSet = new HashSet();
		int activeReferrals = 0;
		int exitedReferrals = 0;
		List referralsList = (List)progRefCaseloadForm.getReferralsList();
		for(int index=0; index < referralsList.size(); index++)
		{
			CSProgRefCaseloadBean prgRefCaseloadBean = (CSProgRefCaseloadBean)referralsList.get(index);
			if(!defendantIdsSet.contains(prgRefCaseloadBean.getDefendantId()))
			{
				defendantIdsSet.add(prgRefCaseloadBean.getDefendantId());
			}
			if(StringUtils.isNotEmpty(prgRefCaseloadBean.getReferralStatusCd()) && prgRefCaseloadBean.getReferralStatusCd().equals("I")){
				activeReferrals++;
			}else if(StringUtils.isNotEmpty(prgRefCaseloadBean.getReferralStatusCd()) && prgRefCaseloadBean.getReferralStatusCd().equals("E")){
				exitedReferrals++;
			}
		}
		progRefCaseloadForm.setSuperviseeSize(Integer.toString(defendantIdsSet.size()));
		progRefCaseloadForm.setActiveReferrals(Integer.toString(activeReferrals));
		progRefCaseloadForm.setExitedReferrals(Integer.toString(exitedReferrals));
		
		return aMapping.findForward(CSAdministerProgramReferralsConstants.VIEW_PROGRAM_REFERRAL_SUCCESS);
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
	public ActionForward viewProgramCaseloadTask(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefCaseloadForm progRefCaseloadForm = (CSCProgRefCaseloadForm)aForm;
		progRefCaseloadForm.clearProgramCurrentCaseloadHeader();
		TasksSearchForm  taskForm = (TasksSearchForm) this.getSessionForm(aMapping, aRequest, "tasksSearchForm" , true);
		UIManagetasksHelper tskHelper = UIManagetasksHelper.getInstance();
		
		String returnString = "";
		List defendantIdsList = new ArrayList();
		String selectedPgmId = taskForm.getSelectedValue();
		String programName = taskForm.getName();
		String staffPositionId = taskForm.getOfficerStaffId();
		String officerNamePosition = "";
		
		GetCaseloadEvent event = (GetCaseloadEvent) EventFactory
		.getInstance(CaseloadControllerServiceNames.GETCASELOAD);
		event.setWorkflowInd(CaseloadConstants.SEARCH_CASELOAD_BY_OFFICER);
		event.setOfficerPositionId(staffPositionId);
		List casesBySupervisee = MessageUtil.postRequestListFilter(event, CaseAssignmentResponseEvent.class);
		for(int index=0; index < casesBySupervisee.size(); index++ )
		{
			CaseAssignmentResponseEvent assignment = (CaseAssignmentResponseEvent) casesBySupervisee.get(index);
			defendantIdsList.add( assignment.getDefendantId() );
		}    	
    	List regionsList = CodeHelper.getCodes(PDCodeTableConstants.LOCATION_REGION, true);
		
		if ( StringUtils.isNotEmpty( staffPositionId ) ) {
			GetLightCSCDStaffForUserEvent reqEvt = new GetLightCSCDStaffForUserEvent();
			reqEvt.setStaffPositionId(staffPositionId);
			reqEvt.setOfficerNameNeeded(true);
			LightCSCDStaffResponseEvent staffPosRespEvt = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(reqEvt, LightCSCDStaffResponseEvent.class);
			
			//add logic to get Officer Name and Position
			if( staffPosRespEvt != null ) {
				if ( StringUtils.isNotEmpty( staffPosRespEvt.getOfficerNameQualifiedByPosition() ) ) {
					officerNamePosition = staffPosRespEvt.getOfficerNameQualifiedByPosition();
				}
			}
		}
		
		progRefCaseloadForm.setOfficerNamePosition(officerNamePosition);
		progRefCaseloadForm.setProgramName(programName);
		progRefCaseloadForm.setSearchBy(CSAdministerProgramReferralsConstants.PROGRAM_SEARCH);
		progRefCaseloadForm.setRegionsList(regionsList);
		
		GetCSTSCodesEvent cstsReqEvt = new GetCSTSCodesEvent();
		CSTSCodesResponseEvent cstsResponseEvt = (CSTSCodesResponseEvent)this.postRequestEvent(cstsReqEvt, CSTSCodesResponseEvent.class);
		
		progRefCaseloadForm.populateCSTSCodeBeans(cstsResponseEvt.getCstsCodesSet());
		progRefCaseloadForm.setDefendantIdsList(defendantIdsList);
		
		GetProgRefByCaseloadEvent reqEvt = CSCProgRefByCaseloadUIHelper.getProgRefByCaseloadEvent(progRefCaseloadForm);
		List<ProgrRefByCaseloadResponseEvent> responseEvtList = this.postRequestListFilter(reqEvt, ProgrRefByCaseloadResponseEvent.class);
		
		CSCProgRefByCaseloadUIHelper.populateBeansForPgmSearch(progRefCaseloadForm, responseEvtList);
		if(progRefCaseloadForm.getProgramNamesList().size()==0)
		{
			this.sendToErrorPage(aRequest, "error.no.search.results.found");	
			returnString = UIConstants.SEARCH_NORESULTS;				
		} else {
			progRefCaseloadForm.setAction(CSAdministerProgramReferralsConstants.ACTION_VIEW_PGM_PRGREF_CASELD);
			progRefCaseloadForm.setSelectedProgramId(selectedPgmId);
			
			Map pgmIdBeanMap = progRefCaseloadForm.getProgramIdBeanMap();
			CSProgramByPrgRefCaseloadBean programBean = (CSProgramByPrgRefCaseloadBean)pgmIdBeanMap.get(selectedPgmId);
			
			progRefCaseloadForm.setSpName(programBean.getServiceProviderName());
			progRefCaseloadForm.setProgName(programBean.getProgramName());
			progRefCaseloadForm.setProgIdentifier(programBean.getProgramIdentifier());
			progRefCaseloadForm.setReferralsList(programBean.getProgramPrgReferralList());
			progRefCaseloadForm.setAllReferralsList(programBean.getAllProgReferralList());
			
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
			
			if( referralsList.size() < 2){
	            //Update task to closed
	            tskHelper.updateTaskStatus(taskForm, UIConstants.CLOSED_STATUS_ID , "CLOSE");
	    	}
	    	
			progRefCaseloadForm.setSuperviseeSize(Integer.toString(defendantIdsSet.size()));
			returnString = CSAdministerProgramReferralsConstants.VIEW_PROGRAM_REFERRAL_SUCCESS;
		}
		return aMapping.findForward(returnString);
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
	public ActionForward viewProgramNameCaseload(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefCaseloadForm progRefCaseloadForm = (CSCProgRefCaseloadForm)aForm;
		String selectedPgmName = progRefCaseloadForm.getSelectedProgramName();
		progRefCaseloadForm.setSelectedProgramName("");
		
		progRefCaseloadForm.setAction(CSAdministerProgramReferralsConstants.ACTION_VIEW_PGM_NAME_PRGREF_CASELD);
		
		Map pgmNameBeanMap = progRefCaseloadForm.getProgramNameBeanMap();
		CSProgNameByPrgRefCaseloadBean programBean = (CSProgNameByPrgRefCaseloadBean)pgmNameBeanMap.get(selectedPgmName);
		progRefCaseloadForm.setProgName(programBean.getProgramName());
		progRefCaseloadForm.setReferralsList(programBean.getProgNamePrgReferralList());
		progRefCaseloadForm.setAllReferralsList(programBean.getAllProgReferralList());
		
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
		
		return aMapping.findForward(CSAdministerProgramReferralsConstants.VIEW_PROGRAM_REFERRAL_SUCCESS);
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
	public ActionForward viewLocationCaseload(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefCaseloadForm progRefCaseloadForm = (CSCProgRefCaseloadForm)aForm;
		String selectedLocId = progRefCaseloadForm.getSelectedId();
		progRefCaseloadForm.clearLocCurrentCaseloadHeader();
		
		progRefCaseloadForm.setAction(CSAdministerProgramReferralsConstants.ACTION_VIEW_LOC_PRGREF_CASELD);
		
		Map locIdBeanMap = progRefCaseloadForm.getLocIdBeanMap();
		CSLocByPrgRefCaseloadBean locationBean = (CSLocByPrgRefCaseloadBean)locIdBeanMap.get(selectedLocId);
		
		progRefCaseloadForm.setStreetNumber(locationBean.getStreetNumber());
		progRefCaseloadForm.setStreetName(locationBean.getStreetName());
		progRefCaseloadForm.setStreetTypeCd(locationBean.getStreetTypeCd());
		progRefCaseloadForm.setAptNum(locationBean.getAptNum());
		progRefCaseloadForm.setCity(locationBean.getCity());
		progRefCaseloadForm.setState(locationBean.getState());
		progRefCaseloadForm.setZipCode(locationBean.getZipCode());
		
		GetProgRefByCaseloadEvent requestEvt = (GetProgRefByCaseloadEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.GETPROGREFBYCASELOAD);
		requestEvt.setSearchBy(CSAdministerProgramReferralsConstants.PROGRAM_LOCATION_SEARCH);
		requestEvt.setDefendantIdsList(progRefCaseloadForm.getDefendantIdsList());
		requestEvt.setProgramId(progRefCaseloadForm.getSelectedProgramId());
		requestEvt.setLocationId(selectedLocId);
		List<ProgrRefByCaseloadResponseEvent> responseEvtList = this.postRequestListFilter(requestEvt, ProgrRefByCaseloadResponseEvent.class);
		CSCProgRefByCaseloadUIHelper.populateBeansForProgLocationSearch(progRefCaseloadForm, responseEvtList);
		
		
		return aMapping.findForward(CSAdministerProgramReferralsConstants.VIEW_PROGRAM_REFERRAL_SUCCESS);
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
	public ActionForward viewPrograms(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefCaseloadForm progRefCaseloadForm = (CSCProgRefCaseloadForm)aForm;
		
		progRefCaseloadForm.setProgramsList(new ArrayList());
		String selectedSpId = progRefCaseloadForm.getSelectedId();
		progRefCaseloadForm.clearSpCurrentCaseloadHeader();
		
		progRefCaseloadForm.setSelectedSpId(selectedSpId);
		
		if(!StringUtil.isEmpty(selectedSpId))
		{
			Map spIdBeanMap = progRefCaseloadForm.getSerProvIdBeanMap();
			if(spIdBeanMap!=null)
			{
				CSServProvByPrgRefCaseloadBean spBean = (CSServProvByPrgRefCaseloadBean)spIdBeanMap.get(selectedSpId);
				if(spBean!=null)
				{
					progRefCaseloadForm.setSpName(spBean.getServiceProviderName());
					if(spBean.getSpProgramsList()!=null)
					{
						progRefCaseloadForm.setProgramsList(spBean.getSpProgramsList());
					}
				}
			}
		}
		
		return aMapping.findForward(CSAdministerProgramReferralsConstants.SP_VIEW_PROGRAM_SUCCESS);
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
	public ActionForward viewLocations(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefCaseloadForm progRefCaseloadForm = (CSCProgRefCaseloadForm)aForm;
		
		progRefCaseloadForm.setLocationsList(new ArrayList());
		String selectedPgmId = progRefCaseloadForm.getSelectedId();
		progRefCaseloadForm.setSelectedId("");
		
		progRefCaseloadForm.setSelectedProgramId(selectedPgmId);
		
		if(!StringUtil.isEmpty(selectedPgmId))
		{
			Map pgmIdBeanMap = progRefCaseloadForm.getProgramIdBeanMap();
			if(pgmIdBeanMap!=null)
			{
				CSProgramByPrgRefCaseloadBean progBean = (CSProgramByPrgRefCaseloadBean)pgmIdBeanMap.get(selectedPgmId);
				if(progBean!=null)
				{
					progRefCaseloadForm.setSpName(progBean.getServiceProviderName());
					progRefCaseloadForm.setProgIdentifier(progBean.getProgramIdentifier());
					progRefCaseloadForm.setProgName(progBean.getProgramName());
					if(progBean.getLocationList()!=null)
					{
						progRefCaseloadForm.setLocationsList(progBean.getLocationList());
					}
				}
			}
		}
		
		return aMapping.findForward(CSAdministerProgramReferralsConstants.SP_VIEW_LOCATION_SUCCESS);
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
	public ActionForward viewProgramReferral(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		return aMapping.findForward(CSAdministerProgramReferralsConstants.VIEW_PRG_REF_DETAILS_SUCCESS);
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
	public ActionForward viewAllReferrals(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefCaseloadForm progRefCaseloadForm = (CSCProgRefCaseloadForm)aForm;
		
		SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
		progRefCaseloadForm.setReferralsList(progRefCaseloadForm.getAllReferralsList());
		
		List referralsList = (List)progRefCaseloadForm.getReferralsList();
		for(int index=0; index < referralsList.size(); index++)
		{
			CSProgRefCaseloadBean prgRefCaseloadBean = (CSProgRefCaseloadBean)referralsList.get(index);
			if (prgRefCaseloadBean.getProgramReferralId().equals(progRefCaseloadForm.getSelectedId())){
				myHeaderForm.setSuperviseeId(prgRefCaseloadBean.getDefendantId());
				UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
			}
		}
		aRequest.setAttribute("exitOnly", "Y");
		
		return aMapping.findForward("pgmRefListViewSuccess");	
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
	public ActionForward viewOrderVersions(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefCaseloadForm progRefCaseloadForm = (CSCProgRefCaseloadForm)aForm;
		String criminalCaseId = progRefCaseloadForm.getSelectedCriminalCaseId();
		String supervisionOrderId = null;
		
		GetSupervisionOrderByCaseEvent reqEvt = new GetSupervisionOrderByCaseEvent();
		reqEvt.setCriminalCaseId(criminalCaseId);
		SupervisionOrderByCaseResponseEvent responseEvent = (SupervisionOrderByCaseResponseEvent)this.postRequestEvent(reqEvt, SupervisionOrderByCaseResponseEvent.class);
		if(responseEvent!=null)
		{
			supervisionOrderId = responseEvent.getSupervisionOrderId();
		}
		SupervisionOrderForm supervisionOrderForm = (SupervisionOrderForm)this.getSessionForm(aMapping, aRequest, UIConstants.SUPERVISION_ORDER_FORM, true);
		supervisionOrderForm.setSelectedValue(supervisionOrderId);
		
		return aMapping.findForward(CSAdministerProgramReferralsConstants.VIEW_ORDER_VERSIONS_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.CANCEL);
	}	
}

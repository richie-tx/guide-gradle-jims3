/*
 * Created on Dec 12, 2007
 *
 */
package ui.supervision.administerserviceprovider.CSC.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.csserviceprovider.SearchByProgramEvent;
import messaging.csserviceprovider.SearchByServiceProviderEvent;
import messaging.csserviceprovider.reply.CSServiceProviderResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.supervision.administerserviceprovider.CSC.AdminServiceProviderHelper;
import ui.supervision.administerserviceprovider.CSC.ServiceProviderLightBean;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderSearchForm;
import ui.supervision.administerserviceprovider.programreferral.action.UIProgramHierarchyBean;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCSCServiceProviderSearchAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		// TODO Auto-generated method stub
		keyMap.put("button.link","link");
		keyMap.put("button.refresh","refresh");
		keyMap.put("button.submit","submit");
		keyMap.put("button.createServiceProvider","createSP");
	}
	
	public ActionForward link(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderSearchForm myForm=(ServiceProviderSearchForm)aForm;
		myForm.clear();
		myForm.setProgramTypes(new ArrayList());
		List hierarchyBeans = new ArrayList();
		UIProgramHierarchyBean hierarchyBean = new UIProgramHierarchyBean();

		List progGroups = SimpleCodeTableHelper.getCodesSortedByCode("CS_PROGRAM_GROUP");
		List progTypes = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("CS_PROGRAM_TYPE");
		
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
		myForm.setProgramHeirarchyList(hierarchyBeans);
		myForm.setAction(UIConstants.SEARCH);
		myForm.setSecondaryAction("");
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward refresh(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderSearchForm myForm=(ServiceProviderSearchForm)aForm;
		String searchById=myForm.getSearchById();
		myForm.clear();
		myForm.setAction(UIConstants.SEARCH);
		myForm.setSecondaryAction("");
		myForm.setSearchById(searchById);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward submit(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderSearchForm myForm=(ServiceProviderSearchForm)aForm;
		myForm.clearSearchResults();
		if(myForm.getSearchById()!=null && myForm.getSearchById().equals(PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_PROGRAM)){
			myForm.clearConsolidatedSearch();
			myForm.clearSPSearch();
			return searchByProgram(aMapping,myForm, aRequest,aResponse);
		}
		else if(myForm.getSearchById()!=null && myForm.getSearchById().equals(PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_SP)){
			myForm.clearConsolidatedSearch();
			myForm.clearProgramSearch();
			return searchBySP(aMapping,myForm, aRequest,aResponse);
		}
		else if(myForm.getSearchById()!=null && myForm.getSearchById().equals(PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_CONSOLIDATED)){
			myForm.clearSPSearch();
			myForm.clearProgramSearch();
			return searchByConsolidated(aMapping,myForm, aRequest,aResponse);
		}
		return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
	}
	
	private ActionForward searchByProgram(ActionMapping aMapping,
			ServiceProviderSearchForm myForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse){
			
			String myForward=UIConstants.SEARCH_SUCCESS;
			ArrayList mySearchResults=new ArrayList();
			myForm.setProgramSearchResults(mySearchResults);
			myForm.setSearchByFieldsString("");
			SearchByProgramEvent myEvent=new SearchByProgramEvent();
			myEvent.setContractProgram(myForm.getContractProgramId());
			myEvent.setProgramGroupCode(myForm.getProgramGroupId());
			myEvent.setProgramName(myForm.getProgramName().replaceAll("'", "''"));
			myEvent.setProgramStatus(myForm.getProgramStatusId());
			myEvent.setProgramTypeCode(myForm.getProgramTypeId());
			List myListOfRespEvts=postRequestListFilter(myEvent,CSServiceProviderResponseEvent.class);
			if(myListOfRespEvts!=null && myListOfRespEvts.size()>0){
				String sortFld = "";
				for (int loopX=0; loopX<myListOfRespEvts.size(); loopX++){
					CSServiceProviderResponseEvent myEvt=(CSServiceProviderResponseEvent)myListOfRespEvts.get(loopX);
					ServiceProviderLightBean myBean=AdminServiceProviderHelper.getSPLightBeanFromServProvRespEvt(myEvt);
					if(myBean!=null){
						if ("A".equals(myBean.getServiceProviderStatusId()) || "P".equals(myBean.getServiceProviderStatusId())){
							sortFld = "0" + myBean.getServiceProviderName();
						} else {
							sortFld = "1" + myBean.getServiceProviderName();
						}
						myBean.setSortId(sortFld);
						mySearchResults.add(myBean);
						myForm.setServiceProviderStatusDesc(myBean.getServiceProviderStatusDesc());
					}
				}
			}
			Collections.sort((List)mySearchResults);
			if(myForm.getProgramSearchResults()==null || myForm.getProgramSearchResults().isEmpty()){
				myForward=UIConstants.FAILURE;
				sendToErrorPage(aRequest,"error.no.search.results.found");
			}
			String fldStr = "";
			if (myForm.getProgramName() != null && !myForm.getProgramName().equalsIgnoreCase("")){
				fldStr = "Program Name: <b>" + myForm.getProgramName() + "</b>";
			}
			if (!myForm.getProgramGroupId().equalsIgnoreCase("")){
				List pgGrps = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.ASP_CS_PROGRAM_GROUP);
				if (pgGrps != null){
					for (int g=0; g < pgGrps.size(); g++){
						CodeResponseEvent cre = (CodeResponseEvent)pgGrps.get(g);
						if (cre.getCode().equalsIgnoreCase(myForm.getProgramGroupId())){
							if (fldStr != null && !fldStr.equals("")){
								fldStr += ", ";
							}
							fldStr += "Program Group: <b>" + cre.getDescription() + "</b>";
							break;
						}
					}
				}
				if (!myForm.getProgramTypeId().equalsIgnoreCase("")){
					List pgTypes = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.ASP_CS_PROGRAM_TYPE);
					if (pgTypes != null){
						for (int t=0; t < pgTypes.size(); t++){
							CodeResponseEvent cre2 = (CodeResponseEvent)pgTypes.get(t);
							if (cre2.getCode().equalsIgnoreCase(myForm.getProgramTypeId())){
								if (fldStr != null && !fldStr.equals("")){
									fldStr += ", ";
								}
								fldStr += "Program Type: <b>" + cre2.getDescription() + "</b>";
								break;
							}
						}
					}			
				}
			}
			if (!myForm.getContractProgramId().equalsIgnoreCase("")){
				if (fldStr != null && !fldStr.equals("")){
					fldStr += ", ";
				}
				fldStr += "Contract Program: <b>" + myForm.getContractProgramId()  + "</b>";
			}
			if (!myForm.getProgramStatusId().equalsIgnoreCase("")){
				if (fldStr != null && !fldStr.equals("")){
					fldStr += ", ";
				}
				String str = myForm.getProgramStatusId().toUpperCase(); 
				if (str.equals("A")){
					fldStr += "Staus: <b>ACTIVE </b> ";
				} else if (str.equals("I")){
					fldStr += "Staus: <b>INACTIVE </b> ";
					} else if (str.equals("P")){
						fldStr += "Staus: <b>PENDING </b> ";
					} else if (str.equals("S")){
						fldStr += "Staus: <b>SUSPENDED </b> ";
					} else {
						fldStr += "Staus: <b>UNDER INVESTIGATION </b> ";
					}
				}
			myForm.setSearchByFieldsString(fldStr);
			return aMapping.findForward(myForward);
	}
	
	private ActionForward searchBySP(ActionMapping aMapping,
			ServiceProviderSearchForm myForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse){
		
		String myForward=UIConstants.SEARCH_SUCCESS;
		ArrayList mySearchResults=new ArrayList();
		myForm.setServiceProviderSearchResults(mySearchResults);
		myForm.setSearchByFieldsString("");
		
		SearchByServiceProviderEvent myEvent=new SearchByServiceProviderEvent();
		myEvent.setIsInHouse(myForm.getServiceProviderInHouseId());
		myEvent.setHasContractProgram(myForm.getServiceProviderContractProgId());		
		myEvent.setServiceProviderName(myForm.getServiceProviderName().replaceAll("'", "''"));
		
		myEvent.setServiceProviderStatus(myForm.getServiceProviderStatusId());
		List myListOfRespEvts=postRequestListFilter(myEvent,CSServiceProviderResponseEvent.class);
		if(myListOfRespEvts!=null && myListOfRespEvts.size()>0){
			String sortFld = "";
			for (int loopX=0; loopX<myListOfRespEvts.size(); loopX++){
				CSServiceProviderResponseEvent myEvt=(CSServiceProviderResponseEvent)myListOfRespEvts.get(loopX);
				ServiceProviderLightBean myBean=AdminServiceProviderHelper.getSPLightBeanFromServProvRespEvt(myEvt);
				if(myBean!=null){
					if ("A".equals(myBean.getServiceProviderStatusId()) || "P".equals(myBean.getServiceProviderStatusId())){
						sortFld = "0" + myBean.getServiceProviderName();
					} else {
						sortFld = "1" + myBean.getServiceProviderName();
					}
					myBean.setSortId(sortFld);
					mySearchResults.add(myBean);
					myForm.setServiceProviderStatusDesc(myBean.getServiceProviderStatusDesc());
				}
			}
		}
		Collections.sort((List)mySearchResults);
		if(myForm.getServiceProviderSearchResults()==null || myForm.getServiceProviderSearchResults().isEmpty()){
			myForward=UIConstants.FAILURE;
			sendToErrorPage(aRequest,"error.no.search.results.found");
		}
		String fldStr = "";
		if (myForm.getServiceProviderName() != null && !myForm.getServiceProviderName().equalsIgnoreCase("")){
			fldStr = "Service Provider Name: <b>" + myForm.getServiceProviderName() + "</b>";
		}
		if (!myForm.getServiceProviderStatusId().equalsIgnoreCase("")){
			if (fldStr != null && !fldStr.equals("")){
				fldStr += ", ";
			}
			fldStr += " Status: <b>";
			if (myForm.getServiceProviderStatusId().equalsIgnoreCase("A")){
				fldStr += "ACTIVE </b>";
			} else if (myForm.getServiceProviderStatusId().equalsIgnoreCase("I")){
				fldStr += "INACTIVE </b>";
			} else{
				fldStr += "PENDING </b>";
			}
		}
		if (!myForm.getServiceProviderInHouseId().equalsIgnoreCase("")){
			if (fldStr != null && !fldStr.equals("")){
				fldStr += ", ";
			}
			fldStr += " In House: <b>" + myForm.getServiceProviderInHouseId() + "</b>";
		}
		if (!myForm.getServiceProviderContractProgId().equalsIgnoreCase("")){
			if (fldStr != null && !fldStr.equals("")){
				fldStr += ", ";
			}
			fldStr += " Contract Program: <b>" + myForm.getServiceProviderContractProgId()  + "</b>";
		}

		myForm.setSearchByFieldsString(fldStr);

		return aMapping.findForward(myForward);
		
	}
	
	private ActionForward searchByConsolidated(ActionMapping aMapping,
			ServiceProviderSearchForm myForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse){
		
		String myForward=UIConstants.FAILURE;
		sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Seach by Consolidated View is not yet supported");
		return aMapping.findForward(myForward);
		
	}
	
	public ActionForward createSP(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CREATE);
	}
	
	

}// END CLASS

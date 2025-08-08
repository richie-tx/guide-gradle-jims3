/*
 * Created on Mar 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerprogramreferrals.GetProgramReferralsEvent;
import messaging.administerprogramreferrals.GetSuperviseeActiveCasesEvent;
import messaging.administerprogramreferrals.reply.CSProgramReferralResponseEvent;
import messaging.administerprogramreferrals.reply.SuperviseeCaseResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.security.ISecurityManager;
import naming.Features;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.programReferral.CSCProgRefSearchBean;
import ui.supervision.programReferral.CSCProgRefUIHelper;
import ui.supervision.programReferral.form.CSCProgRefForm;
import ui.supervision.programReferral.form.CSCSearchProgRefForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayProgRefListAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "link");
		keyMap.put("button.refresh","refresh");
		keyMap.put("button.filter","filter");
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
		CSCSearchProgRefForm progRefSearchForm =(CSCSearchProgRefForm)aForm;
		CSCProgRefForm progRefForm=(CSCProgRefForm)getSessionForm(aMapping,aRequest,UIConstants.CS_PROGRAM_REFERRAL_FORM,true);
		
		String defendantId = progRefSearchForm.getSuperviseeId();
		String exitOnly = (String)aRequest.getAttribute("exitOnly");
		aRequest.setAttribute("exitOnly", "");
		if (exitOnly == null){
			exitOnly = "N";
		}
		SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
		if(defendantId==null || defendantId.trim().equals("") || "Y".equals(exitOnly))
		{
//			SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
			defendantId = myHeaderForm.getSuperviseeSpn();
			
			progRefSearchForm.clearSuperviseeId();
			progRefSearchForm.setSuperviseeId(defendantId);
		}
		while (defendantId.length() < 8) {
			defendantId = "0" + defendantId;
		}
// need to reload compliance value in case value has changed.		
		myHeaderForm.setCompliant(UICommonSupervisionHelper.isSuperviseeCompliant(defendantId));
		progRefSearchForm.clearAll();
		if ("Y".equals(exitOnly)){
			progRefSearchForm.setInitated(false);
			progRefSearchForm.setOpen(false);
			progRefSearchForm.setExited(true);
		}
		
		progRefForm.clearDefendantId();
		progRefForm.clearAll();

		progRefForm.setSpn(defendantId);
		
//		get all the Program Referrals for the defendant ID
		GetProgramReferralsEvent requestEvent = CSCProgRefUIHelper.getProgramReferralsEvent(defendantId);
		List myRespList=postRequestListFilter(requestEvent,CSProgramReferralResponseEvent.class);
		
		Set tempCasesSet = new HashSet();
		
		if(myRespList!=null && myRespList.size()>0)
		{
			int size = myRespList.size();
			for(int loopX = 0; loopX < size; loopX++)
			{
				CSProgramReferralResponseEvent myTempEvent=(CSProgramReferralResponseEvent)myRespList.get(loopX);
				
				CSCProgRefSearchBean progRefSearchBean= CSCProgRefUIHelper.convertCSProgramReferralResponseEventTOProgRefSearchBean(myTempEvent);
				
				progRefSearchForm.getAvailableProgReferralsList().add(progRefSearchBean);
				if ("Y".equals(exitOnly) && progRefSearchBean.isExited())
				{
					progRefSearchForm.getFilteredProgReferralsList().add(progRefSearchBean);
				} 
				if ("N".equals(exitOnly))
				{
					if(progRefSearchBean.isInitiated() || progRefSearchBean.isOpen())
					{
						progRefSearchForm.getFilteredProgReferralsList().add(progRefSearchBean);
					}
				}
				if(!(tempCasesSet.contains(progRefSearchBean.getCaseNum())))
				{
					tempCasesSet.add(progRefSearchBean.getCaseNum());
					progRefSearchForm.addCaseClassObj(progRefSearchBean.getCaseNum());
				}
			}
		}

//		get all the Active Cases for the defendant ID
		GetSuperviseeActiveCasesEvent activeCasesEvent = new GetSuperviseeActiveCasesEvent();
		activeCasesEvent.setDefendantId(progRefForm.getSpn());
		
		List activeCaseList = postRequestListFilter(activeCasesEvent,SuperviseeCaseResponseEvent.class);
		if(activeCaseList!=null && activeCaseList.size()>0)
		{
			int caseListSize = activeCaseList.size();
			for(int loopY = 0; loopY < caseListSize; loopY++)
			{
				SuperviseeCaseResponseEvent activeCase = (SuperviseeCaseResponseEvent)activeCaseList.get(loopY);
				if(!(tempCasesSet.contains(activeCase.getCaseNumber())))
				{
					tempCasesSet.add(activeCase.getCaseNumber());
					progRefSearchForm.addCaseClassObj(activeCase.getCaseNumber());
				}
			}
		}
		activeCasesEvent = null;
		activeCaseList = null;
		myRespList = null;
		requestEvent = null;
		tempCasesSet = null;
		Collections.sort(progRefSearchForm.getFilteredProgReferralsList());
		if(progRefSearchForm.getFilteredProgReferralsList()!=null)
		{
			progRefSearchForm.setProgRefSearchCount(Integer.toString(progRefSearchForm.getFilteredProgReferralsList().size()));
		}
		
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        Set userFeatures = securityManager.getFeatures();  
        if(userFeatures.contains(Features.CSCD_PRG_REF_REM_EXT_ENT_WITHOUT_RESTRC))
		{
        	progRefForm.setRemoveExtNEntryWithOutRestrc(true); 
		}

        if(userFeatures.contains(Features.CSCD_PRG_REF_REM_EXT_ENT_WITH_RESTRC))
		{
        	progRefForm.setRemoveExtNEntryWithRestrc(true);
		}
		
//		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
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
	public ActionForward filter(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
	    CSCSearchProgRefForm myForm=(CSCSearchProgRefForm)aForm;
	    
		myForm.setFilteredProgReferralsList(new ArrayList());
		String mySelectedCase=myForm.getCaseNum();
		
		List myAvailList=myForm.getAvailableProgReferralsList();
		
		boolean found=false;
		boolean foundByCase=false;
		
		if(myAvailList!=null && myAvailList.size()>0)
		{
			for(int loopX=0;loopX<myAvailList.size(); loopX++)
			{
				CSCProgRefSearchBean myTempBean=(CSCProgRefSearchBean)myAvailList.get(loopX);
				found=false;
				foundByCase=false;
				if(!StringUtil.isEmpty(mySelectedCase))
				{
					if(mySelectedCase.equals(myTempBean.getCaseNum()))
					{
						foundByCase=true;
					}
				}
				else{
					foundByCase=true;
				}
				if(foundByCase)
				{
					if(myForm.isInitated() || myForm.isOpen() || myForm.isExited())
					{
						
						if(myForm.isInitated()){
							if(myTempBean.isInitiated()) 
								found=true;
						}
						if(myForm.isOpen() && !found){
							if(myTempBean.isOpen()) 
								found=true;
						}
						if(myForm.isExited() && !found){
							if(myTempBean.isExited()) 
								found=true;
						}
					}
				}
				if(found){
					myForm.getFilteredProgReferralsList().add(myTempBean);
				}
			}
		}
		
		Collections.sort(myForm.getFilteredProgReferralsList());
		if(myForm.getFilteredProgReferralsList()!=null)
		{
			myForm.setProgRefSearchCount(Integer.toString(myForm.getFilteredProgReferralsList().size()));
		}
		
		// filter based on items selected
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
	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCSearchProgRefForm progRefSearchForm =(CSCSearchProgRefForm)aForm;
		progRefSearchForm.clearSearch();
		
		List availableProgRefList = progRefSearchForm.getAvailableProgReferralsList();
		List filteredList = new ArrayList(); 
		progRefSearchForm.setFilteredProgReferralsList(filteredList);
		for(int i =0; i < availableProgRefList.size(); i++)
		{
			CSCProgRefSearchBean progRefSearchBean = (CSCProgRefSearchBean)availableProgRefList.get(i);
			if(progRefSearchBean.isInitiated() || progRefSearchBean.isOpen())
			{
				filteredList.add(progRefSearchBean);
			}
		}
		
		progRefSearchForm.setCaseNum("");
		Collections.sort(progRefSearchForm.getFilteredProgReferralsList());
		if(progRefSearchForm.getFilteredProgReferralsList()!=null)
		{
			progRefSearchForm.setProgRefSearchCount(Integer.toString(progRefSearchForm.getFilteredProgReferralsList().size()));
		}
		
		// reset form to default values and initial results;
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	
}// END CLASS

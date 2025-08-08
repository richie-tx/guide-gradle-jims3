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
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.CSAdministerProgramReferralsConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.programReferral.CSCProgRefUIHelper;
import ui.supervision.programReferral.CSCServiceProviderProgLocBean;
import ui.supervision.programReferral.ReferralTypeBean;
import ui.supervision.programReferral.form.CSCProgRefForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayProgRefProgramSelectAction extends JIMSBaseAction
{

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) 
	{
		keyMap.put("button.next","showReferralSummary");
		keyMap.put("button.scheduleDateTime", "scheduleDateTime");
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
	public ActionForward showReferralSummary(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
		{
			CSCProgRefForm progRefForm =(CSCProgRefForm)aForm;
			progRefForm.setScheduleDateTimeSelected(false);
			
			List selSPProgLocBeanList = new ArrayList();
			
			String selectedProgLocIds = progRefForm.getSelectedPrgmLocIds();
			
			if((selectedProgLocIds!=null) && !(selectedProgLocIds.trim().equals("")))
			{
				ArrayList selectedProgLocIdsList = new ArrayList();
				
				StringTokenizer tokenizer = new StringTokenizer(selectedProgLocIds,",");
				while(tokenizer.hasMoreTokens())
				{
					String id = (String)tokenizer.nextToken();
					selectedProgLocIdsList.add(id);
				}
				selSPProgLocBeanList = CSCProgRefUIHelper.getSelectedServiceProviderProgLocList(progRefForm, selectedProgLocIdsList) ;
			}
			progRefForm.setSelectedServiceProviderProgLocList(selSPProgLocBeanList);
			
//			to identify unprogressed referral types 
			boolean isUnprogressedRefTypeExist = setUnprogressedRefTypes(progRefForm);
			if(isUnprogressedRefTypeExist)
			{
				String errorMsg = "Not all selected referral types will be progressed because programs selected do not cover all referral types.";
				aRequest.setAttribute("cautionMsg", errorMsg);
			}
			
				// direct to program locations page
			ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
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
	public ActionForward scheduleDateTime(ActionMapping aMapping, ActionForm aForm,
					HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progRefForm =(CSCProgRefForm)aForm;
		progRefForm.setScheduleDateTimeSelected(true);
		
		List selSPProgLocBeanList = new ArrayList();
		
		String selectedProgLocIds = progRefForm.getSelectedPrgmLocIds();
		
		if((selectedProgLocIds!=null) && !(selectedProgLocIds.trim().equals("")))
		{
			ArrayList selectedProgLocIdsList = new ArrayList();
			
			StringTokenizer tokenizer = new StringTokenizer(selectedProgLocIds,",");
			while(tokenizer.hasMoreTokens())
			{
				String id = (String)tokenizer.nextToken();
				selectedProgLocIdsList.add(id);
			}
			selSPProgLocBeanList = CSCProgRefUIHelper.getSelectedServiceProviderProgLocList(progRefForm, selectedProgLocIdsList) ;
		}
		progRefForm.setSelectedServiceProviderProgLocList(selSPProgLocBeanList);
		
//		to identify unprogressed referral types 
		boolean isUnprogressedRefTypeExist = setUnprogressedRefTypes(progRefForm);
		if(isUnprogressedRefTypeExist)
		{
			String errorMsg = "Not all selected referral types will be progressed because programs selected do not cover all referral types.";
			aRequest.setAttribute("cautionMsg", errorMsg);
		}
		
			// direct to program locations page
		ActionForward forward = aMapping.findForward(CSAdministerProgramReferralsConstants.SCHEDULE_SUCCESS);
		return forward;
	}
	
	
	
	/**
	 * 
	 * @param progRefForm
	 * @return
	 */
	private boolean setUnprogressedRefTypes(CSCProgRefForm progRefForm)
	{
		boolean isUnprogressedRefTypeExist = false;
		
		HashSet progressedPgmLocRefTypesSet = new HashSet();
		
//		obtain all the progressed refTypes from the already-defined SPs
		List selSPProgLocBeanList = progRefForm.getSelectedServiceProviderProgLocList();
		Iterator iter = selSPProgLocBeanList.iterator();
		while(iter.hasNext())
		{
			 CSCServiceProviderProgLocBean pgmLocBean = (CSCServiceProviderProgLocBean)iter.next();
			 progressedPgmLocRefTypesSet.add(pgmLocBean.getReferralTypeCd());
		}
		
//		obtain all the progressed refTypes from the user-defined SP, if selected
		if(progRefForm.isUserEnteredServiceProvider())
		{
			progressedPgmLocRefTypesSet.add(progRefForm.getUserEnteredServiceProviderRefTypeCd());
		}
		
//		set the progressedForPgmLoc flag for each RefeffalTypeBean
		Iterator refTypeIter = progRefForm.getSelectedReferralTypesList().iterator();
		while(refTypeIter.hasNext())
		{
			ReferralTypeBean refTypeBean = (ReferralTypeBean)refTypeIter.next();
			if(!progressedPgmLocRefTypesSet.contains(refTypeBean.getReferralTypeCode()))
			{
				refTypeBean.setNotProgressedForPgmLoc(true);
				isUnprogressedRefTypeExist = true;
			}
			else
			{
				refTypeBean.setNotProgressedForPgmLoc(false);
			}
		}
		
		return isUnprogressedRefTypeExist;
	}
}

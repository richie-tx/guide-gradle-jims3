/*
 * Created on Mar 4, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercasenotes.domintf.ICasenote;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import messaging.administerprogramreferrals.GetProgRefCasenotesEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.Casenote;
import ui.supervision.administercasenotes.UICasenoteHelper;
import ui.supervision.administercasenotes.form.CasenoteSearchForm;
import ui.supervision.programReferral.form.CSCProgRefForm;

/**
 * 
 * @author cc_bjangay
 *
 */
public class DisplayProgRefCasenoteAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) 
	{
		keyMap.put("button.next", "next");
		keyMap.put("button.view", "viewCasenotes");
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
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
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
	public ActionForward viewCasenotes(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm = (CSCProgRefForm)aForm;
		
		String selProgRefId = progReferralForm.getSelectedValue();
		
//		clear the previous values
		progReferralForm.setSelectedValue("");
		progReferralForm.setPrCasenotesList(new ArrayList());
		
		GetProgRefCasenotesEvent requestEvent = new GetProgRefCasenotesEvent();
		requestEvent.setProgramReferralId(selProgRefId);
		List casenoteResponseList = this.postRequestListFilter(requestEvent, CasenoteResponseEvent.class);
		
		ArrayList prCasenotesList = new ArrayList();
		if (!casenoteResponseList.isEmpty())
		{
			CasenoteSearchForm searchForm = new CasenoteSearchForm();
			Collection casenoteSubjectList = searchForm.getCasenoteSubjectList();
			Collection casenoteTypeList = searchForm.getCasenoteTypeList();
			Collection collateralList = searchForm.getCollateralList();
			Collection contactMethodList = searchForm.getContactMethodList();
			String currentUserId = UIUtil.getCurrentUserID();

			Iterator iter = casenoteResponseList.iterator();
			while (iter.hasNext())
			{
				ICasenote casenote = (CasenoteResponseEvent)iter.next();
				//prCasenotesList.add(UICasenoteHelper.getCasenote(casenote));
				Casenote newCasenote = UICasenoteHelper.getCasenote(casenote, currentUserId);
				newCasenote.setCasenoteTypeId(casenote.getCasenoteTypeId(), casenoteTypeList);
				String[] associatesArr = UICasenoteHelper.getArrayFromCollection(casenote.getAssociates());
				newCasenote.setAssociateIds(associatesArr, collateralList);
				String[] cnSubjs = UICasenoteHelper.getArrayFromCollection(casenote.getSubjects());
				newCasenote.setSubjectIds(cnSubjs, casenoteSubjectList);
				newCasenote.setAssociateIds(associatesArr, collateralList);
				newCasenote.setContactMethodId(casenote.getContactMethodId(), contactMethodList);
				prCasenotesList.add(newCasenote);
			}
			UICasenoteHelper.resolveCreatorNames(prCasenotesList);
		}
		Collections.sort(prCasenotesList);
		progReferralForm.setPrCasenotesList(prCasenotesList);
		
		return aMapping.findForward(UIConstants.VIEW_SUCCESS);
	}
}

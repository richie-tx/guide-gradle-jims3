/*
 * Created on Jan 25, 2007
 *
 */
package ui.supervision.supervisionorder.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.info.reply.CountInfoMessage;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.OffenseHelper;
import ui.common.UIUtil;
import ui.common.form.OffenseSearchForm;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplaySupervisionOrderSelectOffenseAction extends LookupDispatchAction {

	/**
	 * @see LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	public ActionForward submit(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OffenseSearchForm myForm = (OffenseSearchForm) aForm;
		myForm.setOffenseResultList(null);
		CompositeResponse myResp=OffenseHelper.retrieveOffenseCodes(myForm);
		if (MessageUtil.filterComposite(myResp, CountInfoMessage.class) != null){
		    this.sendToErrorPage(aRequest, "error.max.limit.exceeded");
		} else {
		    myForm.setOffenseResultList(MessageUtil.compositeToCollection(myResp,
		            OffenseCodeResponseEvent.class));
		    if(myForm.getOffenseResultList()==null || myForm.getOffenseResultList().size()<=0){
		        sendToErrorPage(aRequest,"error.no.search.results.found");
		    }
		}
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SEARCH_SUCCESS, UIUtil.getCurrentUserAgencyID()));
	}
	
	public ActionForward refresh(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
		OffenseSearchForm myForm = (OffenseSearchForm) aForm;

		myForm.clearSearchFields();
	
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.REFRESH_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		}
	
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS, UIUtil.getCurrentUserAgencyID()));
		OffenseSearchForm searchForm = (OffenseSearchForm) aForm;
		SupervisionOrderForm supOrderForm = (SupervisionOrderForm) UICommonSupervisionHelper.getSupervisionOrderForm(aRequest,true);

		// set the newly selected offense code, if any
		String newOffenseCode = searchForm.getSelectedValue();
		if (newOffenseCode != null && !newOffenseCode.equals(""))
		{
			supOrderForm.setOffenseId(newOffenseCode);
		}
		if(supOrderForm.getIsPretrialInterventionOrder()){
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.RETURN_SUCCESS + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
			
		}
		
		return forward;
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil.getCurrentUserAgencyID()));
		SupervisionOrderForm supOrderForm = (SupervisionOrderForm) UICommonSupervisionHelper.getSupervisionOrderForm(aRequest,true);
		if(supOrderForm.getIsPretrialInterventionOrder()){
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.RETURN_SUCCESS + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
			
		}
		
		return forward;
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
		SupervisionOrderForm supOrderForm = (SupervisionOrderForm) UICommonSupervisionHelper.getSupervisionOrderForm(aRequest,true);
		if(supOrderForm.getIsPretrialInterventionOrder()){
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.RETURN_SUCCESS + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
			
		}
		
		return forward;
	}

	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}

}

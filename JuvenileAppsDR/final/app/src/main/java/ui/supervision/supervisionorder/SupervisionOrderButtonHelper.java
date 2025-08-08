/*
 * Created on Mar 3, 2006
 *
 */
package ui.supervision.supervisionorder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import messaging.supervisionorder.GetSupervisionOrdersEvent;
import messaging.supervisionorder.GetUnfinishedOrdersEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;
import ui.supervision.supervisionorder.form.SupervisionOrderSearchForm;

/**
 * @author dgibler
 *
  */
public final class SupervisionOrderButtonHelper
{
	/**
	 * 
	 */
	private SupervisionOrderButtonHelper()
	{
		super();
	}

	/**
	 * @param aMapping
	 * @param sof
	 * @param statusId
	 * @param actionName
	 * @param forwardName
	 * @return
	 */
	public static ActionForward updateOrderStatus(
		ActionMapping aMapping,
		SupervisionOrderForm sof,
		String statusId,
		String actionName,
		String forwardName)
	{
		UISupervisionOrderHelper.postUpdateOrderStatusEvent(sof.getOrderId(), sof.getOrderStatusId(), sof.getStatusChangeDate(), statusId);
		
		sof.setOrderStatusId(statusId);
		sof.setAction(actionName);
		
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(forwardName,UIUtil.getCurrentUserAgencyID()));
		
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aRequest
	 * @return
	 */
	public static ActionForward getCaseOrderSearchResults(ActionMapping aMapping, HttpServletRequest aRequest)
	{

		ActionForward forward = new ActionForward();

		SupervisionOrderSearchForm searchForm =
			(SupervisionOrderSearchForm) aRequest.getSession().getAttribute("supervisionOrderSearchForm");

		List caseOrders=new ArrayList();
		if (searchForm.getCourt() == null || searchForm.getCourt().equals("")) {
			GetSupervisionOrdersEvent requestEvent =
				(GetSupervisionOrdersEvent) EventFactory.getInstance(
					SupervisionOrderControllerServiceNames.GETSUPERVISIONORDERS);
	
			requestEvent.setCaseNum(searchForm.getCaseNum());
			requestEvent.setCourtDivision(searchForm.getCdi());
			requestEvent.setSpn(searchForm.getSpn());
			requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());		
	
			 
			if ( ((searchForm.getSpn() != null && !searchForm.getSpn().equals("")) && 
					(searchForm.getCaseNum() == null ||  searchForm.getCaseNum().equals("")) &&
					(searchForm.getCdi() == null || searchForm.getCdi().equals("")) ) || 
				( (searchForm.getSpn() != null && !searchForm.getSpn().equals("")) && 
						(searchForm.getCaseNum() != null &&  !searchForm.getCaseNum().equals("")) &&
						(searchForm.getCdi() != null && !searchForm.getCdi().equals(""))) ) {
				caseOrders = MessageUtil.postRequestListFilter(requestEvent, CaseOrderResponseEvent.class);			
			}
		} else {
			GetUnfinishedOrdersEvent reqEvent =
				(GetUnfinishedOrdersEvent) EventFactory.getInstance(SupervisionOrderControllerServiceNames.GETUNFINISHEDORDERS);

			reqEvent.setCourtId(searchForm.getCourt());
			reqEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());

			caseOrders = MessageUtil.postRequestListFilter(reqEvent, CaseOrderResponseEvent.class);
		}
		if(caseOrders==null){
			caseOrders=new ArrayList();
		}
		int len = caseOrders.size();
		for(int i=0;i<len;i++)
		{
		    CaseOrderResponseEvent caseOrder = (CaseOrderResponseEvent) caseOrders.get(i);
		    if(PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL.equals(caseOrder.getVersionTypeId()) 
		            && PDCodeTableConstants.STATUS_WITHDRAWN_ID.equals(caseOrder.getOrderStatusId()) == false)
			{
		        searchForm.setOriginalOrderTitleId(caseOrder.getOrderTitleId());
		        
			}		    
			searchForm.setOffenseId(caseOrder.getOffenseId());
			caseOrder.setOffense(searchForm.getOffense());			
			searchForm.setOrderStatusId(caseOrder.getOrderStatusId());
			caseOrder.setOrderStatus(searchForm.getOrderStatus());				
		}		

		searchForm.setCaseOrderList(caseOrders);
		searchForm.setCaseOrderListSize(String.valueOf(caseOrders.size()));
		StringBuffer padCrt = null;
		
		for (Iterator iter = caseOrders.iterator(); iter.hasNext();) {
			CaseOrderResponseEvent responseEvent = (CaseOrderResponseEvent) iter.next();
			String originalCourtNum = responseEvent.getCurrentCourtNum();
			if (originalCourtNum == null){     
				responseEvent.setCurrentCourtNum("");
			} else if(originalCourtNum.length() < 3){
				padCrt = new StringBuffer(originalCourtNum);
		    	while (padCrt.length() < 3){
		    		padCrt.insert(0, "0");
		    	}
		    	responseEvent.setCurrentCourtNum(padCrt.toString());
			}  else if(originalCourtNum.length() > 3){
				padCrt = new StringBuffer(originalCourtNum);
		    	while (padCrt.length() > 3 && padCrt.substring(0, 1).equals("0")){
		    		padCrt = padCrt.delete(0, 1);
		    	}
		    	responseEvent.setCurrentCourtNum(padCrt.toString());
			}    
		}
		aRequest.getSession().setAttribute("supervisionOrderSearchForm",searchForm);
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CASE_SEARCH_SUCCESS,UIUtil.getCurrentUserAgencyID()));
		return forward;
	}	
}

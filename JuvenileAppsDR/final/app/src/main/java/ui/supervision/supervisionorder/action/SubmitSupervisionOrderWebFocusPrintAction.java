//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\HandleSupervisionOrderSelectionAction.java

package ui.supervision.supervisionorder.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionorder.GetSupervisionOrderDetailsEvent;
import messaging.supervisionorder.GetSupervisionOrderVersionsEvent;
import messaging.supervisionorder.reply.SupOrderConditionResponseEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import messaging.webfocus.GetWebFocusReportEvent;
import messaging.webfocus.reply.WebFocusReportCatalogResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.EventFactory;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;
import naming.UIConstants;
import naming.WebFocusReportCatalogControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.SupervisionOrderListHelper;
import ui.supervision.supervisionorder.SupervisionOrderReportingBean;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author dgibler
 * 
 */
public class SubmitSupervisionOrderWebFocusPrintAction extends JIMSBaseAction {

	public static final String OR = "OR";

	public static final String SPACE = " ";

	public static final String MODIFIED = "MODIFIED";

	public static final String ZERO = "0";

	private static final Object AMPERORDER = "&SPRVISIONORDER_ID=";

	private static final Object AMPERNAME = "&partyName=";

	private static final Object ADDCONDITION = "&ADDCONDITION=";

	private static final Object DELETECONDITION = "&DELETECONDITION=";

	private static final Object PREVIOUSORDER_ID = "&PREVIOUSORDER_ID=";

	private static final Object UPDATEDCONDITION = "&UPDATEDCONDITION=";

	/**
	 * @roseuid 438F240E01BC
	 */
	public SubmitSupervisionOrderWebFocusPrintAction() {

	}

	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.print", "printOrder");
		keyMap.put("button.printDraft", "printDraftOrder");
		keyMap.put("button.printSpanish", "printSpanishOrder");
		keyMap.put("button.printSpanishVersion", "printSpanishVersion");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UICommonSupervisionHelper
				.computeCSMultiDeptForward(UIConstants.BACK, UIUtil
						.getCurrentUserAgencyID()));
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper
				.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil
						.getCurrentUserAgencyID()));
		return forward;
	}

	public ActionForward printDraftOrder(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws Exception {
		printOrder(aMapping, aForm, aRequest, aResponse);
		return (null);
	}

	public ActionForward printSpanishOrder(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws Exception {
		printOrder(aMapping, aForm, aRequest, aResponse);
		return (null);
	}

	public ActionForward printSpanishVersion(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws Exception {
		printOrder(aMapping, aForm, aRequest, aResponse);
		return (null);
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param aRequest
	 * @param response
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward printOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest aRequest, HttpServletResponse response)
			throws GeneralFeedbackMessageException {

		SupervisionOrderForm supOrderForm = (SupervisionOrderForm) form;

		// Get user logged On
		ISecurityManager mgr = (ISecurityManager) ContextManager.getSession()
				.get("ISecurityManager");
		IUserInfo userInfo = mgr.getIUserInfo();

		String userId = userInfo.getJIMSLogonId();
	//	String passWord = userInfo.getJIMS2Password();

		String caseOrderId = "";
		String report = "";

		caseOrderId = aRequest.getParameter("compositeKey");
		if (caseOrderId.length() > 15) {

			String ssCaseOrderId = caseOrderId.substring(15);
			caseOrderId = ssCaseOrderId;
		}
		SupervisionOrderDetailResponseEvent soRE = getSupervisionOrderDetails(caseOrderId);
		SupervisionOrderReportingBean reqBean = new SupervisionOrderReportingBean();
		SupervisionOrderReportingBean reportBean = null;

		StringBuffer reportName = new StringBuffer();
		StringBuffer reportSb = new StringBuffer();
		String path = "";
		String partyName = "";
		String orderTitle = "";
		boolean isModified = false;

		if (soRE != null) {

			Name defName = soRE.getDefendantName();
			partyName = reverseName(defName);

			orderTitle = (SupervisionOrderListHelper
					.getOrderTitleName(supOrderForm.getAllOrderTitleList(),
							soRE.getOrderTitleId()));

			orderTitle = orderTitle.trim();
			reportName.append(soRE.getOrderCourtCategory()).append(orderTitle);

		}

		GetWebFocusReportEvent requestEvent = (GetWebFocusReportEvent) EventFactory
				.getInstance(WebFocusReportCatalogControllerServiceNames.GETWEBFOCUSREPORT);

		report = reportName.toString().replaceAll(" ", "");
		requestEvent.setWebFocusName(report);

		WebFocusReportCatalogResponseEvent reportResp = (WebFocusReportCatalogResponseEvent) MessageUtil
				.postRequest(requestEvent,
						WebFocusReportCatalogResponseEvent.class);

		if ("MODIFIED".equalsIgnoreCase(orderTitle)) {

			reportBean = populateReportingBean(soRE, reqBean);
			isModified = true;

		}

		reportSb = new StringBuffer();
		if (reportResp != null) {

			reportSb.append(reportResp.getUrl());
			reportSb.append(report.toLowerCase());
			reportSb.append(reportResp.getUserParm());
			reportSb.append(userId);
			reportSb.append(reportResp.getPasswordParm());
		//	reportSb.append(passWord);
			reportSb.append(AMPERORDER);
			reportSb.append(caseOrderId);
			reportSb.append(AMPERNAME);
			reportSb.append(partyName.toString());
			if (isModified) {

				StringBuffer condAdd = new StringBuffer();
				Collection condAdded = reportBean.getConditionsAdded();
				int SIZEC = 0;
				if (condAdded != null && condAdded.size() > 0) {

					Iterator condAddIter = reportBean.getConditionsAdded()
							.iterator();
					SIZEC = reportBean.getConditionsAdded().size();
					int countc = 1;
					while (condAddIter.hasNext()) {

						String addedCondition = (String) condAddIter.next();
						condAdd.append(addedCondition);
						if (countc < SIZEC) {
							condAdd.append(SPACE);
							condAdd.append(OR);
							condAdd.append(SPACE);
							++countc;
						}

					}
					reportSb.append(ADDCONDITION);
					reportSb.append(condAdd);
				}

				StringBuffer condDel = new StringBuffer();
				Collection condDelCol = reportBean.getConditionsAdded();
				int SIZED = 0;
				if (condDelCol != null && condDelCol.size() > 0) {

					Iterator condDelIter = reportBean.getConditionsRemoved()
							.iterator();
					SIZED = reportBean.getConditionsRemoved().size();
					int countd = 1;
					while (condDelIter.hasNext()) {

						String deletedCondition = (String) condDelIter.next();
						condDel.append(deletedCondition);
						if (countd < SIZED) {
							condDel.append(SPACE);
							condDel.append(OR);
							condDel.append(SPACE);
							++countd;
						}
					}
					reportSb.append(DELETECONDITION);
					reportSb.append(condDel);
					reportSb.append(PREVIOUSORDER_ID);
					reportSb.append(reportBean.getPreviousOrder_Id());

				}

				StringBuffer condUpt = new StringBuffer();
				Collection condUptColl = reportBean.getConditionsUpdated();
				int SIZEU = 0;
				if (condUptColl != null && condUptColl.size() > 0) {
					;

					Iterator condUpdtIter = reportBean.getConditionsUpdated()
							.iterator();
					SIZEU = reportBean.getConditionsUpdated().size();
					int countu = 1;
					while (condUpdtIter.hasNext()) {

						String updatedCondition = (String) condUpdtIter.next();
						condUpt.append(updatedCondition);
						if (countu < SIZEU) {
							condUpt.append(SPACE);
							condUpt.append(OR);
							condUpt.append(SPACE);
							++countu;
						}
					}
					reportSb.append(UPDATEDCONDITION);
					reportSb.append(condUpt);
				}

			}

		}
		path = reportSb.toString();

		if (path.length() < 50) {

			throw new GeneralFeedbackMessageException(
					"The Print Template "
							+ path
							+ " for Process Supervision Order cannot be found. Call System Admin to correct this problem");
		}

		ActionForward af = new ActionForward(path, true);

		return af;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	private String reverseName(Name name) {

		String lname = "";
		String fname = "";
		StringBuffer defendantName = new StringBuffer();
		if (name != null) {

			String nameToken = name.toString();
			StringTokenizer st = new StringTokenizer(nameToken, ",");
			lname = st.nextToken().trim();
			fname = st.nextToken().trim();
			defendantName.append(fname).append(" ").append(lname);
		}
		return defendantName.toString();

	}

	/**
	 * 
	 * @param soRE
	 * @param reqBean
	 * @return
	 */
	private SupervisionOrderReportingBean populateReportingBean(
			SupervisionOrderDetailResponseEvent soRE,
			SupervisionOrderReportingBean reqBean) {

		GetSupervisionOrderVersionsEvent reqEvent = new GetSupervisionOrderVersionsEvent();
		reqEvent.setAgencyId(soRE.getAgencyId());
		reqEvent.setCaseNum(soRE.getCriminalCaseid());
		reqEvent.setOrderId(soRE.getOrderId());
		reqEvent.setOrderChainNum(soRE.getOrderChainNum());
		getSupOrderVersions(reqBean, reqEvent);

		return reqBean;
	}

	/**
	 * 
	 * @param supervisionOrderId
	 * @return
	 */
	public SupervisionOrderDetailResponseEvent getSupervisionOrderDetails(
			String supervisionOrderId) {

		GetSupervisionOrderDetailsEvent requestEvent = new GetSupervisionOrderDetailsEvent();
		requestEvent.setSupervisionOrderId(supervisionOrderId);

		SupervisionOrderDetailResponseEvent responseEvent = (SupervisionOrderDetailResponseEvent) MessageUtil
				.postRequest(requestEvent,
						SupervisionOrderDetailResponseEvent.class);

		return responseEvent;
	}

	/**
	 * Sets the Added and Deleted conditions for a Modified order by comparing
	 * with the previous order.
	 */
	private SupervisionOrderReportingBean getSupOrderVersions(
			SupervisionOrderReportingBean reqBean,
			GetSupervisionOrderVersionsEvent reqEvent) {

		// get OrderResposneEvent
		Collection coll = MessageUtil.postRequestListFilter(reqEvent,
				SupervisionOrderDetailResponseEvent.class);

		// set collection in form
		List orderVersions = (List) coll;
		Collections.sort(orderVersions);
		SupervisionOrderDetailResponseEvent orderVersion, previousOrderVersion = null;

		String selectedOrderId = reqEvent.getOrderId();

		for (Iterator iter = orderVersions.iterator(); iter.hasNext();) {
			orderVersion = (SupervisionOrderDetailResponseEvent) iter.next();
			if (orderVersion.getOrderId().equals(selectedOrderId)) {
				// get its predecessor
				if (iter.hasNext()) {
					previousOrderVersion = (SupervisionOrderDetailResponseEvent) iter
							.next();
					reqBean.setPreviousOrder_Id(previousOrderVersion
							.getOrderId());
				}
				getViewVersionConditions(orderVersion, previousOrderVersion,
						reqBean);
				break;
			}
		}
		return reqBean;
	}

	/**
	 * 
	 * @param selectedOrder
	 * @param previousOrder
	 * @param reqBean
	 * @return
	 */
	private SupervisionOrderReportingBean getViewVersionConditions(
			SupervisionOrderDetailResponseEvent selectedOrder,
			SupervisionOrderDetailResponseEvent previousOrder,
			SupervisionOrderReportingBean reqBean) {
		Collection conditionsRemoved = new ArrayList();
		Collection selectedOrderConditions = selectedOrder.getConditions();

		// if there is no previous Order, conditions should be shown as if they
		// exist in both Orders
		if (previousOrder == null) {
			previousOrder = selectedOrder;
		}
		Collection previousOrderConditions = previousOrder.getConditions();

		// create a set of condition ids and a map
		List addCondList = new ArrayList();
		List updtCondList = new ArrayList();
		List deleCondList = new ArrayList();
		// create a map of conditionId and responseEvent
		Map prevCondMap = new HashMap();
		for (Iterator prevIter = previousOrderConditions.iterator(); prevIter
				.hasNext();) {
			SupOrderConditionResponseEvent previousOrderCondition = (SupOrderConditionResponseEvent) prevIter
					.next();
			// prevCondIds.add(previousOrderCondition.getConditionId());
			prevCondMap.put(previousOrderCondition.getConditionId(),
					previousOrderCondition);
		}

		// traverse through current Order
		for (Iterator selectedIter = selectedOrderConditions.iterator(); selectedIter
				.hasNext();) {
			SupOrderConditionResponseEvent selectedOrderCondition = (SupOrderConditionResponseEvent) selectedIter
					.next();
			// convert into ConditionDetailResponseEvent to add into collection
			// ConditionDetailResponseEvent selectedCondition =
			// convert(selectedOrderCondition);

			SupOrderConditionResponseEvent previousOrderCondition = (SupOrderConditionResponseEvent) prevCondMap
					.get(selectedOrderCondition.getConditionId());
			if (previousOrderCondition != null) { // condition exists in both
				// versions
				// get resolved descriptions
				String selectedResolveDesc = UIUtil.removeXMLtags(
						selectedOrderCondition.getResolvedDescription(), true);
				String previousResolveDesc = UIUtil.removeXMLtags(
						previousOrderCondition.getResolvedDescription(), true);
				// check if condition has been updated
				if (selectedResolveDesc == null || previousResolveDesc == null
						|| selectedResolveDesc.equals(previousResolveDesc)) { // unchanged
					// condition
					// selectedCondition.setCompareToPreviousVersion("");
				} else { // updated condition
					// conditionsUpdated.add(replaceParagraphTags(
					// selectedOrderCondition.getResolvedDescription()));
					updtCondList.add(selectedOrderCondition.getConditionId());
				}
				// remove it from the map
				prevCondMap.remove(selectedOrderCondition.getConditionId());
			} else { // added conditions
				// selectedCondition.setCompareToPreviousVersion("added");
				addCondList.add(selectedOrderCondition.getConditionId());
				//conditionsAdded.add(replaceParagraphTags(selectedOrderCondition
				// .getResolvedDescription()));
			}
		}

		// removed conditions
		Collection removedConditions = prevCondMap.values();
		if (removedConditions != null) {
			for (Iterator iter = removedConditions.iterator(); iter.hasNext();) {
				SupOrderConditionResponseEvent removedCondition = (SupOrderConditionResponseEvent) iter
						.next();
				// convert
				// ConditionDetailResponseEvent selectedCondition =
				// convert(removedCondition);
				conditionsRemoved.add(removedCondition);
				deleCondList.add(removedCondition.getConditionId());
			}
		}

		reqBean.setConditionsAdded(addCondList);
		reqBean.setConditionsRemoved(deleCondList);
		reqBean.setConditionsUpdated(updtCondList);
		return reqBean;
	}

	/**
	 * 
	 * @param detailEvent
	 * @param aViewAll
	 * @param supOrderForm
	 */
	public void commonViewOrderVersions(
			SupervisionOrderDetailResponseEvent detailEvent, boolean aViewAll,
			SupervisionOrderForm supOrderForm) {

		// post pd event
		GetSupervisionOrderVersionsEvent reqEvent = new GetSupervisionOrderVersionsEvent();
		reqEvent.setAgencyId(detailEvent.getAgencyId());
		reqEvent.setCaseNum(detailEvent.getCriminalCaseid());
		reqEvent.setOrderId(detailEvent.getOrderId());
		reqEvent.setOrderChainNum(detailEvent.getOrderChainNum());
		reqEvent.setAllOrderChains(aViewAll);

		List coll = (List) MessageUtil.postRequestListFilter(reqEvent,
				SupervisionOrderDetailResponseEvent.class);

		// set order title names and version types

		// set collection in form
		List orderVersions = (List) coll;
		Collections.sort(orderVersions);
		supOrderForm.setOrderVersionList(coll);
		supOrderForm
				.setSelectedOrderVersion((SupervisionOrderDetailResponseEvent) orderVersions
						.get(0));
		// previous version
		if (orderVersions.size() > 1) {
			supOrderForm
					.setPreviousOrderVersion((SupervisionOrderDetailResponseEvent) orderVersions
							.get(1));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		// TODO Auto-generated method stub

	}
}

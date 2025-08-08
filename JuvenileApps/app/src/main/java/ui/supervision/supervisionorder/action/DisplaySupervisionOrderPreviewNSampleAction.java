//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\DisplaySupervisionOrderAddRemoveConditionsAction.java

package ui.supervision.supervisionorder.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author dgibler
 *
 */
public class DisplaySupervisionOrderPreviewNSampleAction extends LookupDispatchAction
{
	private final static String COLLECTION_NAME = "conditionResultListIndex";
	private final static String KEY_FIELD_NAME = "conditionId";
	private final static String PERIOD = ".";
	/**
	 * @roseuid 438F23B6012F
	 */
	public DisplaySupervisionOrderPreviewNSampleAction()
	{

	}
	/**
	* @see LookupDispatchAction#getKeyMethodMap()
	* @return Map
	*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.viewSample", "viewSample");
		keyMap.put("button.hideSample", "hideSample");
		keyMap.put("button.previewOrder", "previewOrder");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil.getCurrentUserAgencyID()));
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward hideSample(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;

		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.HIDE_SAMPLE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward previewOrder(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		Collection originalList=sof.getConditionSelectedList();	
		Collection newList=UISupervisionOrderHelper.setResequenceCondition(sof.getConditionSelectedList(),sof.getResequencedOrderValue(),true);
		sof.setPreviewConditionSelectedList(newList);
		sof.setResequencedOrderValue("");
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.PREVIEW_SUCCESS, UIUtil.getCurrentUserAgencyID()));
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward viewSample(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		Collection selectedList = sof.getConditionSelectedList();
		Map selectedListMap =UISupervisionOrderHelper.buildResponseEventMap(selectedList);

		Collection resultList = sof.getConditionResultList();
		Map resultListMap = UISupervisionOrderHelper.buildResponseEventMap(resultList);
		if(selectedListMap!=null){
			if(selectedListMap.containsKey(sof.getConditionId())){
				ConditionDetailResponseEvent event=(ConditionDetailResponseEvent)selectedListMap.get(sof.getConditionId());
				if(event!=null){
					sof.setConditionName(event.getName());
					sof.setConditionLiteralPreview(event.getConditionLiteralPreview());
				}
			}
		}
		if(resultListMap!=null){
					if(resultListMap.containsKey(sof.getConditionId())){
						ConditionResponseEvent event=(ConditionResponseEvent)resultListMap.get(sof.getConditionId());
						if(event!=null){
							sof.setConditionName(event.getName());
							sof.setConditionLiteralPreview(event.getConditionLiteralPreview());
						}
					}
				}
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.VIEW_SAMPLE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		
		selectedListMap = null;
		resultListMap = null;
		
		return forward;
	}


	/**
	 * @param aRequest
	 * @param msg
	 */
	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}


}


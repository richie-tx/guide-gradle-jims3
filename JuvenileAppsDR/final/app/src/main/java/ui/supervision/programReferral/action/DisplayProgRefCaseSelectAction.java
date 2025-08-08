/*
 * Created on Mar 3, 2008
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
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerprogramreferrals.GetReferralTypeSelectionEvent;
import messaging.administerprogramreferrals.reply.ReferralTypeResponseEvent;
import messaging.administerprogramreferrals.reply.ReferralTypeSelectionResponseEvent;
import messaging.administerprogramreferrals.reply.SupervisionOrderConditionResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.programReferral.CSCProgRefUIHelper;
import ui.supervision.programReferral.ReferralTypeBean;
import ui.supervision.programReferral.SupervisionOrderConditionBean;
import ui.supervision.programReferral.form.CSCProgRefForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayProgRefCaseSelectAction extends JIMSBaseAction {

	
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next","next");
	
	}
	
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
		{
			CSCProgRefForm progRefForm =(CSCProgRefForm)aForm;
			CaseAssignmentForm caseAssignForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
			SuperviseeHeaderForm superviseeHeaderForm = (SuperviseeHeaderForm)this.getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
			
			progRefForm.setAvailableReferralTypesList(new ArrayList());
			progRefForm.setSelectedReferralTypesList(new ArrayList());
			progRefForm.setConditionsList(new ArrayList());

			GetReferralTypeSelectionEvent myEvent=new GetReferralTypeSelectionEvent();
			myEvent.setCriminalCaseId(progRefForm.getCriminalCaseId());

			List myRespList=postRequestListFilter(myEvent,ReferralTypeSelectionResponseEvent.class);
			if(myRespList!=null && myRespList.size()>0)
			{	
					//retrieve referral type response event
				ReferralTypeSelectionResponseEvent myTempEvent=
					(ReferralTypeSelectionResponseEvent)myRespList.get(0);
				
				//set order conditions list
				List orderCondRespEvtList = myTempEvent.getOrderConditions();
				
				if((orderCondRespEvtList!= null ) && (orderCondRespEvtList.size() > 0))
				{
					for(int i = 0; i < orderCondRespEvtList.size(); i++)
					{
						SupervisionOrderConditionResponseEvent respEvt = (SupervisionOrderConditionResponseEvent)orderCondRespEvtList.get(i);
						SupervisionOrderConditionBean conditionBean = CSCProgRefUIHelper.convertConditionResponseEventToConditionBean(respEvt);
						progRefForm.getConditionsList().add(conditionBean);
					}
				}
				//sort the conditions with respect to Sequence Number
				Collections.sort(progRefForm.getConditionsList(), SupervisionOrderConditionBean.conditionComp);
				
				//retrieve list of referral types
				List myReferralTypes=myTempEvent.getReferralTypes();
				
				if(myReferralTypes!=null && myReferralTypes.size()>0)
				{
					for(int loopX=0;loopX<myReferralTypes.size(); loopX++)
					{
						ReferralTypeResponseEvent myTemp=(ReferralTypeResponseEvent)myReferralTypes.get(loopX);
						ReferralTypeBean myBean = CSCProgRefUIHelper.convertReferralTypeResponseTOReferralTypeBean(myTemp);
						progRefForm.getAvailableReferralTypesList().add(myBean);
					}
					progRefForm.setAvailableReferralTypesList(sortReferralTypeBean(progRefForm.getAvailableReferralTypesList()));
					// rjc - populate set for group unique group list with the first part of the type (which is a combination of group and type separated by '/')
					List<ReferralTypeBean> availableReferralList = progRefForm.getAvailableReferralTypesList();
					Set<String> availableGroupDesc = new TreeSet<String>();
					for(ReferralTypeBean bean: availableReferralList){
						String[] splitDesc = bean.getReferralTypeDesc().split("/");
						String groupDesc = splitDesc[0];
						if(groupDesc != null){
							groupDesc.trim();
							bean.setReferralGroupDesc(groupDesc);
						}else{
							bean.setReferralGroupDesc("");
						}
						availableGroupDesc.add(groupDesc);	
					}
					List<String> uniqueGroups = new ArrayList<String>(availableGroupDesc);
					progRefForm.setAvailableReferralGroupDescriptionList(uniqueGroups);
				}
			}
			
//			set the supervisee header info
			CSCProgRefUIHelper.setSuperviseeHeaderInfo(caseAssignForm, progRefForm, superviseeHeaderForm);

			ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
			return forward;
		}
	
	/**
	 * @param referralBeanTypes
	 * @return List
	 */
	private static List sortReferralTypeBean(Collection refList){
		Iterator iter = refList.iterator();
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			ReferralTypeBean refBean = (ReferralTypeBean) iter.next();	
			map.put(refBean.getReferralTypeDesc(), refBean);
		}
		return new ArrayList(map.values());
	} 
	
}// END OF CLASS


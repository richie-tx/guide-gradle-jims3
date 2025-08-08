/*
 * Created on Jun 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.caseplan.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.caseplan.GetPreviousCaseplansEvent;
import messaging.caseplan.reply.CaseplanDetailsResponseEvent;
import messaging.caseplan.reply.CaseplanListResponseEvent;
import messaging.caseplan.reply.GoalListResponseEvent;
import messaging.caseplan.reply.PlacementInfoResponseEvent;
import messaging.caseplan.GetCaseplanEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CasePlanConstants;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.juvenilecase.UIJuvenileCaseplanHelper;
import ui.juvenilecase.caseplan.form.CaseplanForm;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayPreviousCaseplanVersionsAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "link");
		keyMap.put("button.view", "view");
		keyMap.put( "button.copyCaseplan", "copy" ) ;
	}
	
	public ActionForward link(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)	
	{
		CaseplanForm form = (CaseplanForm) aForm;
		HttpSession session = aRequest.getSession();
		form.setCpActivities(null);
		form.setCaseplanList(null);
		Collection activities = UIJuvenileCaseplanHelper.getCaseplanActivitiesList(form.getCasefileId());
		form.setCpActivities(activities);
		GetPreviousCaseplansEvent evt = new GetPreviousCaseplansEvent();
		evt.setCasefileId(form.getCasefileId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(evt);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);	
		Collection prevCaseplans = MessageUtil.compositeToCollection(compositeResponse, CaseplanListResponseEvent.class);
		form.setCaseplanList(prevCaseplans);
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward view(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)	
	{
		CaseplanForm form = (CaseplanForm) aForm;
		String caseplanId=form.getSelectedValue();
		if(caseplanId!=null && !caseplanId.equals("") && form.getCaseplanList()!=null && form.getCaseplanList().size()>0){
			Iterator iter=form.getCaseplanList().iterator();
			while(iter.hasNext()){
				CaseplanListResponseEvent myRespEvt=(CaseplanListResponseEvent)iter.next();
				if(myRespEvt.getCaseplanID().equals(caseplanId)){
					if(myRespEvt.getReport()!=null){
						String fileName="PreviousCaseplan_" + caseplanId;
						try{
							setPrintContentResp(aResponse,(byte[])myRespEvt.getReport(),fileName,UIConstants.PRINT_AS_PDF_DOC);
							return null;
						}
						catch(Exception e){
							return aMapping.findForward(UIConstants.SUCCESS);
						}
					}
				}
			}
		}
		
			return aMapping.findForward(UIConstants.SUCCESS);
		
	}
	
	public ActionForward copy(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)	
	{
		CaseplanForm cpForm = (CaseplanForm)aForm;
		cpForm.setAction("copy");
		//check if current caseplan is approved
		//if not, get the latest approved caseplan version to copy
		if(!cpForm.getCurrentCaseplan().getStatus().equals(CasePlanConstants.CP_STATUS_SIGNED))
		{
			Iterator iter = cpForm.getCaseplanList().iterator();
			
			while(iter.hasNext())
			{
				CaseplanListResponseEvent evt = (CaseplanListResponseEvent)iter.next();
				if(evt.getStatusId().equals(CasePlanConstants.CP_STATUS_SIGNED))
				{
					GetCaseplanEvent cp = new GetCaseplanEvent();
					cp.setCaseplanId(evt.getCaseplanID());
					 CompositeResponse compositeResponse = MessageUtil.postRequest(cp);				        
				        CaseplanDetailsResponseEvent cpEvt = (CaseplanDetailsResponseEvent) MessageUtil.filterComposite(
				                compositeResponse, CaseplanDetailsResponseEvent.class);
				        if (cpEvt != null)
				        {
				            cpForm.setReqReviewComments(cpEvt.getJPORequestReviewComments());
				            if (cpEvt.getCaseplanID() != null)
				            {
				                UIJuvenileCaseplanHelper.fillCaseplanDetails(cpForm.getCurrentCaseplan(), cpEvt);
				                cpForm.setCaseplanExist("Y");
				                List goalResponses = MessageUtil.compositeToList(compositeResponse,
				                        GoalListResponseEvent.class);
				                List temp = new ArrayList();
				                if (goalResponses != null)
				                {
				                    int len = goalResponses.size();
				                    for(int i=0;i<len;i++)
				                    {
				                        GoalListResponseEvent respEvt = (GoalListResponseEvent) goalResponses.get(i);
				                        String status = CodeHelper.getCodeDescriptionByCode(CodeHelper
				                                .getCodes(PDCodeTableConstants.GOAL_STATUS), respEvt.getStatusId());
				                        respEvt.setStatusId(status);
				                        temp.add(respEvt);
				                    }
				                    Collections.sort(temp);
				                    cpForm.getCurrentCaseplan().setGoalList(temp);
				                }
				                PlacementInfoResponseEvent placementResp = (PlacementInfoResponseEvent) MessageUtil.filterComposite(
				                        compositeResponse, PlacementInfoResponseEvent.class);
				                if (placementResp != null)
				                {
				                    cpForm.setPlacementInfoExist("Y");
				                    UIJuvenileCaseplanHelper.getPlacementList(cpForm);
				                    UIJuvenileCaseplanHelper.fillPlacementDetails(cpForm.getCurrentPlacementInfo(), placementResp);
				                }
				                else
				                {
				                    cpForm.setPlacementInfoExist("N");
				                }
				            }
				        }
				        break;
				}
			}
		}
		
		return aMapping.findForward(UIConstants.COPY);
	}
	

}

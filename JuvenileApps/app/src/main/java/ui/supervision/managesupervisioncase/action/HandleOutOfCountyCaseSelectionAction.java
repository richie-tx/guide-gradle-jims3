//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\managesupervisioncase\\action\\HandleOutOfCountyCaseSelectionAction.java

package ui.supervision.managesupervisioncase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.managesupervisioncase.GetOutOfCountyCaseEvent;
import messaging.managesupervisioncase.reply.OutOfCountyCaseEvent;
import messaging.supervisionorder.GetUnfinishedOrdersEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CloseCaseConstants;
import naming.OutOfCountyCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.SecurityUIHelper;
import ui.supervision.managesupervisioncase.form.OutOfCountyCaseForm;
import ui.supervision.managesupervisioncase.form.OutOfCountyCaseSearchForm;
import ui.supervision.managesupervisioncase.helper.OutOfCountyCaseUIHelper;

public class HandleOutOfCountyCaseSelectionAction extends LookupDispatchAction
{
	private static final String VIEW = "V";
	private static final String CREATE = "C";
	private static final String UPDATE = "U";
	private static final String REACTIVATE = "R";
	private static Map actions = new HashMap();
	
	static
	{
		Map forwards = new HashMap();
		//	Court determines which view to display
		String temp[] = {UIConstants.VIEW_PRETRIAL_SUCCESS, UIConstants.PRETRIAL_VIEW};
		forwards.put(PDCodeTableConstants.PTS, temp);
		String temp1[] = {UIConstants.VIEW_SUCCESS, UIConstants.VIEW};
		forwards.put(PDCodeTableConstants.CSCD, temp1);
		actions.put(VIEW, forwards);
		
		forwards = new HashMap();
		//	Agency determines which view to display
		String temp2[] = {UIConstants.CREATE_PRETRIAL_SUCCESS, UIConstants.PRETRIAL_CREATE};
		forwards.put(PDCodeTableConstants.PRETRIAL_AGENCY, temp2);
		String temp3[] = {UIConstants.CREATE_SUCCESS, UIConstants.CREATE};
		forwards.put(PDCodeTableConstants.CSCD_AGENCY, temp3);
		actions.put(CREATE, forwards);

		forwards = new HashMap();
		//	Court determines which view to display 
		String temp4[] = {UIConstants.UPDATE_PRETRIAL_SUCCESS, UIConstants.PRETRIAL_UPDATE};
		forwards.put(PDCodeTableConstants.PTS, temp4);
		String temp5[] = {UIConstants.UPDATE_SUCCESS, UIConstants.UPDATE};
		forwards.put(PDCodeTableConstants.CSCD, temp5);
		actions.put(UPDATE, forwards);

		forwards = new HashMap();
		//	Court determines which view to display 
		String temp6[] = {UIConstants.REACTIVATE_PRETRIAL_SUCCESS, UIConstants.PRETRIAL_REACTIVATE};
		forwards.put(PDCodeTableConstants.PTS, temp6);
		String temp7[] = {UIConstants.REACTIVATE_SUCCESS, UIConstants.REACTIVATE};
		forwards.put(PDCodeTableConstants.CSCD, temp7);
		actions.put(REACTIVATE, forwards);
	}

	/**
	 * @roseuid 4447CA7F033A
	 */
	public HandleOutOfCountyCaseSelectionAction()
	{
	}

	/**
	 * @see LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.createCase", "createCase");
		keyMap.put("button.updateCase", "updateCase");
		keyMap.put("button.reactivateCase", "reactivateCase");
		keyMap.put("button.closeCase", "closeCase");
		keyMap.put("button.updateClosure", "updateClosure");
		keyMap.put("button.view", "view");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	
	
	public ActionForward createCase(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OutOfCountyCaseForm ooc = (OutOfCountyCaseForm) aForm;
		// clear any old values
		ooc.clear();

		OutOfCountyCaseSearchForm searchForm = getSearchForm(aRequest);
		setPartyInfoOnForm(ooc, searchForm);
		ooc.setCurrentNamePtr(searchForm.getCurrentNamePtr());
		ooc.setNamePtr(searchForm.getCurrentNamePtr());
		ooc.setCurrentNameSeqNum(searchForm.getCurrentNameSeqNum());
		ooc.setNameSeqNum(searchForm.getCurrentNameSeqNum());
		
		String userAgency = SecurityUIHelper.getUserAgencyId();
		// determine which view to display
		ActionForward forward = new ActionForward();
		String[] info = this.getActionInfo(aMapping, userAgency, CREATE);
		if (info.length > 0)
		{
			forward = aMapping.findForward(info[0]);
			ooc.setAction(info[1]);
		}
		ooc.setCdi(getCourtDivision(userAgency));
		return forward;
	}

	public ActionForward updateCase(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OutOfCountyCaseForm ooc = (OutOfCountyCaseForm) aForm;

		OutOfCountyCaseEvent oocCase = getCase(ooc, false);

		ActionForward forward = new ActionForward();
		if (oocCase != null)
		{
			// process results from lookup
			forward = setFormValues(ooc, oocCase, aRequest, aMapping, UPDATE);
		}
		
		// cache original values for the case before the user makes any changes
		cacheCase(aRequest, oocCase);
			
		return forward;
	}
	
	
	public ActionForward closeCase(ActionMapping aMapping, ActionForm aForm,
            						HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		OutOfCountyCaseForm outOfCountyCaseForm = (OutOfCountyCaseForm)aForm;
		String forward = null;
		
		OutOfCountyCaseEvent oocCase = getCase(outOfCountyCaseForm, false);
		if(oocCase != null)
		{
//			 clear any old values
			outOfCountyCaseForm.clear();
			outOfCountyCaseForm.setAction(CloseCaseConstants.OOC_CASE_CLOSE);
			outOfCountyCaseForm.setOutOfCountyCaseValues(oocCase);

			String partyOID = oocCase.getPartyOid();
			if (partyOID == null)
			{
				sendToErrorPage(aRequest, "error.managesupervisioncase.partynotfound");
			}
			else
			{
				outOfCountyCaseForm.setPartyInfo(oocCase);
			}
		}
		GetUnfinishedOrdersEvent getUnfinishedOrdersEvent = (GetUnfinishedOrdersEvent)EventFactory.getInstance(SupervisionOrderControllerServiceNames.GETUNFINISHEDORDERS);
		getUnfinishedOrdersEvent.setAgencyId(outOfCountyCaseForm.getAgencyId());
		getUnfinishedOrdersEvent.setCriminalCaseId(oocCase.getPrimaryKey());
		CaseOrderResponseEvent core = (CaseOrderResponseEvent) MessageUtil.postRequest(getUnfinishedOrdersEvent, CaseOrderResponseEvent.class);
		if (core != null){
            sendToErrorPage(aRequest, "error.supervisionorder.unfinishedorders");
            return aMapping.findForward(UIConstants.VALIDATE_FAILURE);
		}
		OutOfCountyCaseUIHelper.populateCaseloadForOOCCase(outOfCountyCaseForm, outOfCountyCaseForm.getSpn(), outOfCountyCaseForm.getCriminalCaseId());
		
		ArrayList closeCaseAssignmentList = (ArrayList)outOfCountyCaseForm.getCloseCaseAssignmentList();
		if((closeCaseAssignmentList != null) && (closeCaseAssignmentList.size() > 0))
		{
			forward =  CloseCaseConstants.REVIEW_ACTIVE_CASE_SUCCESS;
		}
		else
		{
			forward = CloseCaseConstants.CLOSE_CASE_SUCCESS;
		}
		
		return aMapping.findForward(forward);
	}//end of closeCase()
	
	

	public ActionForward updateClosure(ActionMapping aMapping, ActionForm aForm,
            HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		OutOfCountyCaseForm outOfCountyCaseForm = (OutOfCountyCaseForm)aForm;
		String forward = CloseCaseConstants.CLOSE_CASE_SUCCESS;
		
		OutOfCountyCaseEvent oocCase = getCase(outOfCountyCaseForm, false);
		if(oocCase != null)
		{
//			 clear any old values
			outOfCountyCaseForm.clear();
			outOfCountyCaseForm.setAction(CloseCaseConstants.OOC_CASE_UPDATE_CLOSURE);
			outOfCountyCaseForm.setOutOfCountyCaseValues(oocCase);

			String partyOID = oocCase.getPartyOid();
			if (partyOID == null)
			{
				sendToErrorPage(aRequest, "error.managesupervisioncase.partynotfound");
			}
			else
			{
				outOfCountyCaseForm.setPartyInfo(oocCase);
			}
		}
		
		return aMapping.findForward(forward);
	}//end of updateClosure()
	
	
	public ActionForward reactivateCase(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OutOfCountyCaseForm ooc = (OutOfCountyCaseForm) aForm;

		OutOfCountyCaseEvent oocCase = getCase(ooc, true);

		ActionForward forward = new ActionForward();
		if (oocCase != null)
		{
			// process results from lookup
			forward = setFormValues(ooc, oocCase, aRequest, aMapping, REACTIVATE);
			
			Date dispositionDate = oocCase.getDispositionDate();
			if(dispositionDate!= null){
				String transferOutDtStr = DateUtil.dateToString(dispositionDate, DateUtil.DATE_FMT_1);
				ooc.setTransferOutDateStr(transferOutDtStr);
			}
			ooc.setTransferInDateAsString("");

		}
		return forward;
	}

	public ActionForward view(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OutOfCountyCaseForm ooc = (OutOfCountyCaseForm) aForm;
		
		OutOfCountyCaseEvent oocCase = getCase(ooc, false);

		ActionForward forward = new ActionForward();
		if (oocCase != null)
		{
			// process results from lookup
			forward = setFormValues(ooc, oocCase, aRequest, aMapping, VIEW);

		}
		else
		{
			forward = aMapping.findForward(UIConstants.VIEW_FAILURE);
		}
		
		// cache original values for the case before the user makes any changes
		cacheCase(aRequest, oocCase);
			
		return forward;
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}

	private OutOfCountyCaseEvent getCase(OutOfCountyCaseForm ooc, boolean isReactivate)
	{
		OutOfCountyCaseEvent oocCase = null;
		
		GetOutOfCountyCaseEvent request =
			(GetOutOfCountyCaseEvent) EventFactory.getInstance(
				OutOfCountyCaseControllerServiceNames.GETOUTOFCOUNTYCASE);

		// set the criteria from the form
		request.setCaseNum(ooc.getCaseNum());
		request.setCourtDivisionId(ooc.getCdi());
		// indicate whether the case is being read for reactivation
		request.setReactivate(isReactivate);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(request);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);

		Collection cases = MessageUtil.compositeToCollection(compositeResponse, OutOfCountyCaseEvent.class);

		if (!cases.isEmpty())
		{
			oocCase = (OutOfCountyCaseEvent) cases.iterator().next();
		}
		
		return oocCase;
	}
	
	private ActionForward setFormValues(OutOfCountyCaseForm ooc, OutOfCountyCaseEvent oocCase, HttpServletRequest aRequest,
						ActionMapping aMapping, String currentAction)
	{ 
		// clear any old values
		ooc.clear();
		ooc.setOutOfCountyCaseValues(oocCase);

		String partyOID = oocCase.getPartyOid();
		if (partyOID == null)
		{
			sendToErrorPage(aRequest, "error.managesupervisioncase.partynotfound");
		}
		else
		{
			ooc.setPartyInfo(oocCase);
		}

		ActionForward forward = new ActionForward();
		
		// determine which view to display
		String[] info = this.getActionInfo(aMapping, ooc.getCdi(), currentAction);
		if (info.length > 0)
		{
			forward = aMapping.findForward(info[0]);
			ooc.setAction(info[1]);
		}


		
		return forward;
	}
	
	private String[] getActionInfo(ActionMapping aMapping, String key, String action)
	{
		Map forwards = (Map)actions.get(action);
		
		String[] info = new String[2];
		if (forwards != null && forwards.containsKey(key))
		{
			info = (String[])forwards.get(key);
		}
		
		return info;
	}
	
	private String getCourtDivision(String userAgency)
	{
		//	Agency determines which view to display
		if (userAgency.equals(PDCodeTableConstants.PRETRIAL_AGENCY))
		{
			return PDCodeTableConstants.PTS;
		}
		else
		if (userAgency.equals(PDCodeTableConstants.CSCD_AGENCY))
		{
			return PDCodeTableConstants.CSCD;
		}
		return null;

	}
	
	private OutOfCountyCaseSearchForm getSearchForm(HttpServletRequest aRequest)
	{		
		return (OutOfCountyCaseSearchForm)aRequest.getSession().getAttribute("outOfCountyCaseSearchForm");
	}
		

	private void setPartyInfoOnForm(OutOfCountyCaseForm ooc, OutOfCountyCaseSearchForm searchForm)
	{
		ooc.setDefendantId(searchForm.getPartyOid());
		ooc.setFirstName(searchForm.getFirstName());
		ooc.setMiddleName(searchForm.getMiddleName());
		ooc.setLastName(searchForm.getLastName());
		ooc.setDateOfBirth(searchForm.getDateOfBirth());
		ooc.setRaceId(searchForm.getRaceId());
		ooc.setSexId(searchForm.getSexId());
		ooc.setSpn(searchForm.getSpn());
		ooc.setSid(searchForm.getSid());
		ooc.setSsn(searchForm.getSsn());
	}

	private void cacheCase(HttpServletRequest aRequest, OutOfCountyCaseEvent oocCase)
	{
		String caseKey = oocCase.getKey();
		Map cases = (Map)aRequest.getSession().getAttribute("outOfCountyCaseValues");
		if (cases == null)
		{
			cases = new Hashtable();
			aRequest.getSession().setAttribute("outOfCountyCaseValues", cases);
		}
		cases.put(caseKey, oocCase);
	}
}

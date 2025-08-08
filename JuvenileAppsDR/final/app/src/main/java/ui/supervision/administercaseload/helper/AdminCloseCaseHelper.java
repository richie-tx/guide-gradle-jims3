package ui.supervision.administercaseload.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.administercaseload.CloseCaseEvent;
import messaging.administercaseload.CloseCaseResponseEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import naming.CaseloadControllerServiceNames;
import naming.CloseCaseConstants;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

public class AdminCloseCaseHelper
{
	public static void setPaperfileAcknowledgement(CaseAssignmentForm caseAssignmentForm)
	{
		List closeCasesList = caseAssignmentForm.getCloseCasesList();
		Iterator it = closeCasesList.iterator();
		
		while(it.hasNext())
		{
			ICaseAssignment caseAssignment = (ICaseAssignment)it.next();
			caseAssignment.setAcknowledgeStatusCode(CaseloadConstants.ACKNOWLEDGMENT_STATUS_ASSUMED);
			
			LightCSCDStaffResponseEvent cscdStaff = getCSCDStaff();
			if(cscdStaff != null){
				caseAssignment.setAcknowledgePositionId(cscdStaff.getStaffPositionId());				
			}			
			caseAssignment.setAcknowledgeUserId(SecurityUIHelper.getLogonId());
			caseAssignment.setAcknowledgeDate(DateUtil.getCurrentDate());
		}
	}
	
	
	public static CloseCaseEvent getCloseCaseEvent(CaseAssignmentForm caseAssignmentForm)
	{
		CloseCaseEvent closeCaseEvt = (CloseCaseEvent)EventFactory.getInstance(CaseloadControllerServiceNames.CLOSECASE);
		List closeCasesList = caseAssignmentForm.getCloseCasesList();
		closeCaseEvt.setCloseCasesList(closeCasesList);
		return closeCaseEvt;
	}
	
	
	public static LightCSCDStaffResponseEvent getCSCDStaff() {
		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
		ev.setLogonId(SecurityUIHelper.getLogonId());		
		return (LightCSCDStaffResponseEvent) MessageUtil.postRequest(ev, LightCSCDStaffResponseEvent.class);
	} 
	
	
	public static void convertRespEvtToCaseCloseBean(CaseAssignmentForm caseAssignmentForm, List closeCaseRespEventList)
	{
		caseAssignmentForm.setCloseCasesSuccess(true);
		ArrayList beanList = new ArrayList();
		
		Iterator iter = closeCaseRespEventList.iterator();
		while(iter.hasNext())
		{
			CloseCaseResponseEvent closeCaseRespEvent = (CloseCaseResponseEvent)iter.next();
			
			CloseCaseResultBean beanObj = new CloseCaseResultBean();
			
			beanObj.setDefendantId(closeCaseRespEvent.getDefendantId());
			beanObj.setCaseNum(closeCaseRespEvent.getCriminalCaseId());
			String result = closeCaseRespEvent.getResult();
			if(result.equalsIgnoreCase(CloseCaseConstants.FAILURE))
			{
				caseAssignmentForm.setCloseCasesSuccess(false);
			}
			beanObj.setResult(result);
			beanObj.setReasonList(closeCaseRespEvent.getFailureReasonsList());
			
			beanList.add(beanObj);
		}
		
		caseAssignmentForm.setCloseCasesResultBeanList(beanList);
	}//end of convertRespEvtToCaseCloseBean()
}

/*
 * Created on Apr 29, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administersupervisionplan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import ui.supervision.administersupervisionplan.form.SupervisionPlanForm;

import messaging.administersupervisionplan.reply.SupervisionPlanDetailsResponseEvent;
import messaging.administersupervisionplan.reply.SupervisionPlanSummaryResponseEvent;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestSupervisionPlanExecution 
{
	public static Collection fakeSupervisionPlanSummaryResponseEvents()
	{
		ArrayList summaryRespEvtList = new ArrayList();
		
		SupervisionPlanSummaryResponseEvent summaryRespEvent1 = new SupervisionPlanSummaryResponseEvent();
		summaryRespEvent1.setSupervisionPlanId("1");
		summaryRespEvent1.setSupervisionPlanDate(new Date(System.currentTimeMillis() - 999999999));
		summaryRespEvent1.setLastChangeDate(new Date(System.currentTimeMillis() - 999999999));
		summaryRespEvent1.setLastChangeUserId("J2CS3");
		summaryRespEvent1.setStatusCd("A");
		summaryRespEvtList.add(summaryRespEvent1);
		
		SupervisionPlanSummaryResponseEvent summaryRespEvent2 = new SupervisionPlanSummaryResponseEvent();
		summaryRespEvent2.setSupervisionPlanId("2");
		summaryRespEvent2.setSupervisionPlanDate(new Date(System.currentTimeMillis() - 889999999));
		summaryRespEvent2.setLastChangeDate(new Date(System.currentTimeMillis() - 889999999));
		summaryRespEvent2.setLastChangeUserId("J2BNJ");
		summaryRespEvent2.setStatusCd("I");
		summaryRespEvtList.add(summaryRespEvent2);
		
		SupervisionPlanSummaryResponseEvent summaryRespEvent3 = new SupervisionPlanSummaryResponseEvent();
		summaryRespEvent3.setSupervisionPlanId("3");
		summaryRespEvent3.setSupervisionPlanDate(new Date(System.currentTimeMillis() - 779999999));
		summaryRespEvent3.setLastChangeDate(new Date(System.currentTimeMillis() - 779999999));
		summaryRespEvent3.setLastChangeUserId("JIDAG");
		summaryRespEvent3.setStatusCd("D");
		summaryRespEvtList.add(summaryRespEvent3);
		
		return summaryRespEvtList;
	}
	
	
	public static SupervisionPlanDetailsResponseEvent fakeSupervisionPlanDetailsResponseEvent(SupervisionPlanForm supervisionPlanForm)
	{
		SupervisionPlanDetailsResponseEvent supPlanDetailsRespEvt = new SupervisionPlanDetailsResponseEvent();
		
		supPlanDetailsRespEvt.setSupervisionPlanDate(new Date(System.currentTimeMillis() - 999999999));
		if(supervisionPlanForm.getSupervisionPlanId().equalsIgnoreCase("1"))
		{
			supPlanDetailsRespEvt.setStatusCd("A");
		}
		if(supervisionPlanForm.getSupervisionPlanId().equalsIgnoreCase("2"))
		{
			supPlanDetailsRespEvt.setStatusCd("I");
		}
		if(supervisionPlanForm.getSupervisionPlanId().equalsIgnoreCase("3"))
		{
			supPlanDetailsRespEvt.setStatusCd("D");
		}
		supPlanDetailsRespEvt.setLastChangeDate(new Date(System.currentTimeMillis() - 779999999));
		supPlanDetailsRespEvt.setLastChangeUserId("J2BNJ");
		supPlanDetailsRespEvt.setBehaviorObjective("Behavior Comments");
		supPlanDetailsRespEvt.setProblem("PROBLEM COMMENTS");
		supPlanDetailsRespEvt.setOffenderActionPlan("<b>Offender Comments</b>");
		supPlanDetailsRespEvt.setCsoActionPlan("<i>CSO Comments</i>");
		
		return supPlanDetailsRespEvt;
	}
}

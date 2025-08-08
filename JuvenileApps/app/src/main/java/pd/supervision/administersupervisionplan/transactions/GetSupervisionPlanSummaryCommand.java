//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administersupervisionplan\\transactions\\GetSupervisionPlanSummaryCommand.java

package pd.supervision.administersupervisionplan.transactions;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Calendar;

import pd.supervision.administersupervisionplan.SupervisionPlan;
import pd.supervision.administersupervisionplan.SupervisionPlanHelper;

import messaging.administersupervisionplan.GetSupervisionPlanSummaryEvent;
import messaging.administersupervisionplan.query.GetSupervisionPlansEvent;
import messaging.administersupervisionplan.reply.SupervisionPlanSummaryResponseEvent;

import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;

public class GetSupervisionPlanSummaryCommand implements ICommand 
{
	private static int ZERO=0;
	private static String BEGIN_DATE = "BEGIN_DATE";
	private static String END_DATE = "END_DATE";
	
   /**
    * @roseuid 4817675D03BE
    */
   public GetSupervisionPlanSummaryCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 480E2164036A
    */
   public void execute(IEvent event) 
   {
	   GetSupervisionPlanSummaryEvent reqEvent = (GetSupervisionPlanSummaryEvent)event;
	   
	   StringBuffer defendantId = new StringBuffer(reqEvent.getDefendantId());
	   while(defendantId.length() < 8)
	   {
		   defendantId.insert(ZERO, ZERO);
	   }
	   
	   Map dateRangeMap = null;
	   Date beginDate = null;
	   Date endDate = null;
	   
	   if(reqEvent.isSearchOnActiveSupervisionPeriod())
	   {
		   dateRangeMap = SupervisionPlanHelper.getActiveSupervisionPeriodDateRange(defendantId.toString());
	   }
	   else
	   {
		   dateRangeMap = SupervisionPlanHelper.getPriorSupervisionPeriodDateRange(defendantId.toString());
	   }
	   
	   beginDate = (Date)dateRangeMap.get(BEGIN_DATE);
	   endDate = (Date)dateRangeMap.get(END_DATE);
	   
	   if(beginDate != null)
	   {
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(beginDate);
		   //Clear out time on begin date of supervision period.  SupervisionPlans have time stored as 00:00:00
		   String beginDatestr = DateUtil.dateToString(beginDate, DateUtil.DATE_FMT_1);
		   beginDate = DateUtil.stringToDate(beginDatestr, DateUtil.DATE_FMT_1);
		   
		   GetSupervisionPlansEvent supervisionPlansEvt = new GetSupervisionPlansEvent();
		   supervisionPlansEvt.setDefendantId(defendantId.toString());
		   supervisionPlansEvt.setBeginDate(beginDate);
		   supervisionPlansEvt.setEndDate(endDate);
		   
		   List supervisionPlansList = SupervisionPlan.findAll(supervisionPlansEvt);
		   this.processSupervisionPlans(supervisionPlansList);
	   }
   }
   
   
   private void processSupervisionPlans(List supervisionPlansList)
   {
	   Iterator it = supervisionPlansList.iterator();
	   while(it.hasNext())
	   {
		   SupervisionPlan supervisionPlan = (SupervisionPlan) it.next();
		   SupervisionPlanSummaryResponseEvent responseEvt = new SupervisionPlanSummaryResponseEvent();
		   responseEvt.setSupervisionPlanId(supervisionPlan.getOID());
		   responseEvt.setSupervisionPlanDate(supervisionPlan.getSupervisionPlanDate());
		   responseEvt.setLastChangeDate(supervisionPlan.getLastChangeDate());
		   responseEvt.setLastChangeUserId(supervisionPlan.getLastChangeUserId());
		   responseEvt.setStatusCd(supervisionPlan.getStatusCd());
		 
		   MessageUtil.postReply(responseEvt);
	   }
   }
   
   /**
    * @param event
    * @roseuid 480E21640379
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 480E2164037B
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 480E2164037D
    */
   public void update(Object anObject) 
   {
    
   }
}

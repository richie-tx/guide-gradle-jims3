//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administersupervisionplan\\SupervisionPlanHelper.java

package pd.supervision.administersupervisionplan;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import messaging.administersupervisionplan.reply.SupervisionPlanDetailsResponseEvent;
import messaging.administersupervisionplan.reply.SupervisionPlanResponseEvent;
import messaging.supervisionorder.GetPriorSupervisionPeriodsEvent;
import mojo.km.utilities.CollectionUtil;

import pd.security.PDSecurityHelper;
import pd.supervision.supervisionorder.SupervisionPeriod;


public class SupervisionPlanHelper 
{
	private static String BEGIN_DATE = "BEGIN_DATE";
	private static String END_DATE = "END_DATE";
    
	
	
  public static Map getActiveSupervisionPeriodDateRange(String defendantId)
  {
      SupervisionPeriod sp = SupervisionPeriod.findActiveSupervisionPeriod(
              defendantId, PDSecurityHelper.getUserAgencyId());

      Map dateRangeMap = new HashMap();
      
      if (sp != null) {
          dateRangeMap.put(BEGIN_DATE, sp.getSupervisionBeginDate());
          dateRangeMap.put(END_DATE, new Date());
      }
      return dateRangeMap;
  }
  
 
  
  public static Map getPriorSupervisionPeriodDateRange(String defendantId)
  {
      GetPriorSupervisionPeriodsEvent theEvent = new GetPriorSupervisionPeriodsEvent();
      theEvent.setSpn(defendantId);

      List aList = CollectionUtil.iteratorToList(SupervisionPeriod.findAll(theEvent));

      SupervisionPeriod sp = null;
      Map dateRangeMap = new HashMap();

      for (int i = 0; i < aList.size(); i++) {
          
          sp = (SupervisionPeriod) aList.get(i);
          if (dateRangeMap.get(BEGIN_DATE) == null){
              dateRangeMap.put(BEGIN_DATE, sp.getSupervisionBeginDate());
              dateRangeMap.put(END_DATE, sp.getSupervisionEndDate());
          } else {
              Date begDate = (Date) dateRangeMap.get(BEGIN_DATE);
              if (sp.getSupervisionBeginDate().before(begDate)){
                  dateRangeMap.put(BEGIN_DATE, sp.getSupervisionBeginDate());
              }
              Date endDate = (Date) dateRangeMap.get(END_DATE);
              if (sp.getSupervisionEndDate().after(endDate)){
                  dateRangeMap.put(END_DATE, sp.getSupervisionEndDate());
              }
          }
      }

      return dateRangeMap;
  }
  
  
  
  public static void populateSupervisionPlanDetails(SupervisionPlanDetailsResponseEvent responseEvt, SupervisionPlan supervisionPlan)
  {
	  responseEvt.setSupervisionPlanDate(supervisionPlan.getSupervisionPlanDate());
	  responseEvt.setStatusCd(supervisionPlan.getStatusCd());
	  responseEvt.setLastChangeDate(supervisionPlan.getLastChangeDate());
	  responseEvt.setLastChangeUserId(supervisionPlan.getLastChangeUserId());
	  responseEvt.setBehaviorObjective(supervisionPlan.getBehaviorObjective());
	  responseEvt.setCsoActionPlan(supervisionPlan.getCsoActionPlan());
	  responseEvt.setOffenderActionPlan(supervisionPlan.getOffenderActionPlan());
	  responseEvt.setProblem(supervisionPlan.getProblem());
	  responseEvt.setSupervisionPlanId(supervisionPlan.getOID());
  }
  
  
  public static SupervisionPlanResponseEvent getSupervisionPlanResponseEvent(String oid)
  {
	  SupervisionPlanResponseEvent supervisionPlanRespEvt = new SupervisionPlanResponseEvent();
	  supervisionPlanRespEvt.setSupervisionPlanId(oid);
	  return supervisionPlanRespEvt;
  }
}

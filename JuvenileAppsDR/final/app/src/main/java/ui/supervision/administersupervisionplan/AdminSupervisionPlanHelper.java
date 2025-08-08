//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administersupervisionplan\\AdminSupervisionPlanHelper.java

package ui.supervision.administersupervisionplan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import naming.CSCAssessmentConstants;
import naming.PDCodeTableConstants;
import naming.SupervisionPlanControllerServiceNames;
import naming.UIConstants;
import messaging.administersupervisionplan.DeleteSupervisionPlanEvent;
import messaging.administersupervisionplan.GetSupervisionPlanDetailsEvent;
import messaging.administersupervisionplan.GetSupervisionPlanSummaryEvent;
import messaging.administersupervisionplan.UpdateSupervisionPlanEvent;
import messaging.administersupervisionplan.reply.SupervisionPlanDetailsResponseEvent;
import messaging.administersupervisionplan.reply.SupervisionPlanSummaryResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.RequestEvent;
import mojo.km.utilities.DateUtil;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.security.SecurityUIHelper;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administersupervisionplan.form.SupervisionPlanForm;


public class AdminSupervisionPlanHelper 
{
	
	/**
	 * 
	 * @param supervisionPlanForm
	 * @return
	 */
	public static RequestEvent getSupervisionPlanDetailsEvent(SupervisionPlanForm supervisionPlanForm)
	{
		GetSupervisionPlanDetailsEvent supPlanDetialsEvent = (GetSupervisionPlanDetailsEvent)EventFactory.getInstance(SupervisionPlanControllerServiceNames.GETSUPERVISIONPLANDETAILS);
		supPlanDetialsEvent.setSupervisionPlanId(supervisionPlanForm.getSupervisionPlanId());
		
		return supPlanDetialsEvent;
	}
	
	
	/**
	 * 
	 * @param supervisionPlanForm
	 * @param supPlanDetailsResponseEvent
	 */
	public static void populateSupervisionPlanDetailsResponseEvent(SupervisionPlanForm supervisionPlanForm, SupervisionPlanDetailsResponseEvent supPlanDetailsResponseEvent)
	{
		if((supervisionPlanForm.getAction().equalsIgnoreCase(UIConstants.VIEW)) ||
				(supervisionPlanForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)))
		{
			Date supervisionPlanDate = supPlanDetailsResponseEvent.getSupervisionPlanDate();
			supervisionPlanForm.setSupervisionPlanDate(supervisionPlanDate);
			supervisionPlanForm.setSupervisionPlanDateStr(DateUtil.dateToString(supervisionPlanDate,DateUtil.DATE_FMT_1));
		}
		
		if(supervisionPlanForm.getAction().equalsIgnoreCase(UIConstants.VIEW))
		{
			String statusCd = supPlanDetailsResponseEvent.getStatusCd();
			supervisionPlanForm.setStatusCd(statusCd);
			String statusDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSC_SUPERVISION_PLAN_STATUS, statusCd);
			supervisionPlanForm.setStatusDesc(statusDesc);
			
			Date lastChangeDate = supPlanDetailsResponseEvent.getLastChangeDate();
			supervisionPlanForm.setLastChageDateStr(DateUtil.dateToString(lastChangeDate, DateUtil.DATE_FMT_1));
			
			String lastChangeUserId = supPlanDetailsResponseEvent.getLastChangeUserId();
			supervisionPlanForm.setLastChangeUserId(lastChangeUserId);
			supervisionPlanForm.setLastChangeUserName(getUserNameFromUserId(lastChangeUserId));
			
		}
		
		supervisionPlanForm.setBehaviorObjective(supPlanDetailsResponseEvent.getBehaviorObjective());
		supervisionPlanForm.setProblem(supPlanDetailsResponseEvent.getProblem());
		supervisionPlanForm.setOffenderActionPlan(supPlanDetailsResponseEvent.getOffenderActionPlan());
		supervisionPlanForm.setCsoActionPlan(supPlanDetailsResponseEvent.getCsoActionPlan());
	}
	
	
	
	
  /**
   * 
   * @param assessmentForm
   * @return
   */ 
  public static RequestEvent getSupervisionPlanSummaryEvent(AssessmentForm assessmentForm)
  {
  	GetSupervisionPlanSummaryEvent supPlanSummaryEvent = (GetSupervisionPlanSummaryEvent)EventFactory.getInstance(SupervisionPlanControllerServiceNames.GETSUPERVISIONPLANSUMMARY);
  	
  	supPlanSummaryEvent.setDefendantId(assessmentForm.getDefendantId());
  	
  	if((assessmentForm.getSupervisionPeriod()).equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV))
	{
  		supPlanSummaryEvent.setSearchOnActiveSupervisionPeriod(true);
	}
	else
	{
		supPlanSummaryEvent.setSearchOnActiveSupervisionPeriod(false);
	}
  	return supPlanSummaryEvent;
  }
  
  
  /**
   * 
   *
   */
  public static void populateSupervisionPlanSummaryResponseEvent(AssessmentForm assessmentForm, Collection supPlanSummRespEventList)
  {
  	ArrayList supervisionPlanBeanList = new ArrayList();
  	  	
  	if(supPlanSummRespEventList.size() > 0)
  	{
  		Iterator respEvtiter = supPlanSummRespEventList.iterator();
  		
  		while(respEvtiter.hasNext())
  		{
  			SupervisionPlanSummaryResponseEvent summaryRespEvent = (SupervisionPlanSummaryResponseEvent)respEvtiter.next();
  			SupervisionPlanBean beanObj = new SupervisionPlanBean();
  			
  			beanObj.setSupervisionPlanId(summaryRespEvent.getSupervisionPlanId());
  			beanObj.setSupervisionPlanDate(summaryRespEvent.getSupervisionPlanDate());
  			beanObj.setLastChangeDate(summaryRespEvent.getLastChangeDate());
  			beanObj.setLastChangeUserId(summaryRespEvent.getLastChangeUserId());
  			beanObj.setLastChangeUserName(getUserNameFromUserId(beanObj.getLastChangeUserId()));
  			
  			String statusCd = summaryRespEvent.getStatusCd();
  			if(statusCd.equalsIgnoreCase(PDCodeTableConstants.CSC_SUPERVISION_PLAN_STATUS_DRAFT))
  			{
  				assessmentForm.setDraftSupPlanExists(true);
  			}
  			else
  			{
  				assessmentForm.setActvInactvSupPlanExists(true);
  			}
  			beanObj.setStatusCd(statusCd);
  			String statusDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSC_SUPERVISION_PLAN_STATUS,
  					summaryRespEvent.getStatusCd());
  			beanObj.setStatusDesc(statusDesc);
  			
  			supervisionPlanBeanList.add(beanObj);
  		}
		// add sorting for the supervisionPlans to sort on status (Active first and then Inactive), and then on Date (newest first)
		Collections.sort((ArrayList<SupervisionPlanBean>)supervisionPlanBeanList);
  	}
  	assessmentForm.setSupervisionPlansList(supervisionPlanBeanList);
  	
	if(assessmentForm.getSupervisionPeriod().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_INACTV))
	{
		assessmentForm.setAllHistoricalSupervisionPlansList(supervisionPlanBeanList);
	}
  }
  
  
  public static RequestEvent getUpdateSupervisionPlanEvent(SupervisionPlanForm supervisionPlanForm)
  {
  	UpdateSupervisionPlanEvent updateSupPlanEvent = (UpdateSupervisionPlanEvent)EventFactory.getInstance(SupervisionPlanControllerServiceNames.UPDATESUPERVISIONPLAN);
  	
  	updateSupPlanEvent.setDefendantId(supervisionPlanForm.getDefendantId());
  	updateSupPlanEvent.setSupervisionPlanId(supervisionPlanForm.getSupervisionPlanId());
  	updateSupPlanEvent.setSupervisionPlanDate(supervisionPlanForm.getSupervisionPlanDate());
  	updateSupPlanEvent.setStatusCd(supervisionPlanForm.getStatusCd());
  	updateSupPlanEvent.setProblem(supervisionPlanForm.getProblem());
  	updateSupPlanEvent.setBehaviorObjective(supervisionPlanForm.getBehaviorObjective());
  	updateSupPlanEvent.setOffenderActionPlan(supervisionPlanForm.getOffenderActionPlan());
  	updateSupPlanEvent.setCsoActionPlan(supervisionPlanForm.getCsoActionPlan());
  	updateSupPlanEvent.setTaskId(supervisionPlanForm.getTaskId());
  	
  	return updateSupPlanEvent;
  }
  
  
  public static RequestEvent getDeleteSupervisionPlanEvent(SupervisionPlanForm supervisionPlanForm)
  {
  		DeleteSupervisionPlanEvent deleteSupPlanEvent = (DeleteSupervisionPlanEvent)EventFactory.getInstance(SupervisionPlanControllerServiceNames.DELETESUPERVISIONPLAN);
  		deleteSupPlanEvent.setSupervisionPlanId(supervisionPlanForm.getSupervisionPlanId());
  		return deleteSupPlanEvent;
  }
  
  
  public static String getUserNameFromUserId(String userId)
  {
  	String nameDesc = "";
  	Name name = (Name)SecurityUIHelper.getUserName(userId);
	nameDesc = name.getLastName() + ", " + name.getFirstName() + " " + name.getMiddleName();
	return nameDesc;
  }
  
	  public static void initializeSupervisionPlanForm(AssessmentForm assessmentForm, SupervisionPlanForm supervisionPlanForm)
	  {
	  	supervisionPlanForm.setDefendantId(assessmentForm.getDefendantId());
	  	
	  	supervisionPlanForm.setActiveSupervisionPeriod(false);
	  	if(assessmentForm.getSupervisionPeriod().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV))
	  	{
	  		supervisionPlanForm.setActiveSupervisionPeriod(true);
	  	}
	  	
		supervisionPlanForm.setSupervisionBeginDate(assessmentForm.getSupervisionBeginDate());
		supervisionPlanForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
	  }
  
  
  
	/**
	 * 
	 * @param enteredSupPlanDate
	 * @param supervisionPlanForm
	 * @return
	 */
	public static boolean isSupPlanDateInSupervisionRange(Date enteredSupPlanDate, SupervisionPlanForm supervisionPlanForm)
	{
	   	Date supervisionBeginDate = supervisionPlanForm.getSupervisionBeginDate();
		Date supervisionEndDate = supervisionPlanForm.getSupervisionEndDate();
		boolean isSupPlanDateInRange = true;
		
	   	if((supervisionBeginDate != null) && (supervisionEndDate != null))
		{
	   		isSupPlanDateInRange = false;
			int result=0;
	   		
	   		if(enteredSupPlanDate != null)
	   		{
	   			result = DateUtil.compare(enteredSupPlanDate,supervisionBeginDate,DateUtil.DATE_FMT_1);
	   			if(result >= 0)
	   			{
	   				result = DateUtil.compare(enteredSupPlanDate,supervisionEndDate,DateUtil.DATE_FMT_1);
	   				if(result<=0)
	   				{
	   					isSupPlanDateInRange = true;
	   				}
	   			}
	   		}
		}
	   	return isSupPlanDateInRange;
	}//end of isSupPlanDateInSupervisionRange()
}

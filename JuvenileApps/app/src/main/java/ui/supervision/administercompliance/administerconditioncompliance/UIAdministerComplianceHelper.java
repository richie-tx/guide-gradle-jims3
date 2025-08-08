/*
 * Created on Jan 2, 2008
 *
 */
package ui.supervision.administercompliance.administerconditioncompliance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import messaging.administercasenotes.GetCasenotesBySuperviseeIdEvent;
import messaging.administercasenotes.GetConditionCasenotesForCaseOrderChainEvent;
import messaging.administercasenotes.domintf.ICasenote;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import messaging.administercompliance.reply.ComplianceConditionResponseEvent;
import messaging.administercompliance.reply.LikeComplianceConditionResponseEvent;
import messaging.administercompliance.GetComplianceConditionsEvent;
import messaging.supervisionoptions.GetAllGroupsEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import messaging.supervisionoptions.reply.OrderConditionAgencyPolicyResponseEvent;
import messaging.supervisionoptions.reply.OrderConditionCourtPolicyResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CasenoteControllerServiceNames;
import naming.ComplianceControllerServiceNames;
import naming.PDCodeTableConstants;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.supervision.Casenote;
import ui.supervision.administercasenotes.UICasenoteHelper;
import ui.supervision.administercasenotes.form.CasenoteSearchForm;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceForm;

/**
 * @author jjose
 * 
 */
public class UIAdministerComplianceHelper {

	public static String CASENOTE_JOURNAL="casenotejournal";
	public static String CASENOTE_SUPERVISEE_SEARCH="casenotesuperviseesearch";

    /**
     * @description: loads the complianceForm values
     * @param
     */
	public static void loadComplianceConditions(ComplianceForm compForm) {
		    GetComplianceConditionsEvent compEvent = (GetComplianceConditionsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETCOMPLIANCECONDITIONS);
	 		compEvent.setDefendantId(compForm.getDefendantId());
	//		compEvent.(Integer.parseInt(compForm.getDefendantId()));
	 		
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	        dispatch.postEvent(compEvent);
	
	        CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			
			List orderConditions = MessageUtil.compositeToList(compositeResponse, ComplianceConditionResponseEvent.class);
	        List likeOrderConditions = MessageUtil.compositeToList(compositeResponse, LikeComplianceConditionResponseEvent.class);
			if (orderConditions != null && !orderConditions.isEmpty() || likeOrderConditions != null && !likeOrderConditions.isEmpty())
			{
				UIAdministerComplianceHelper.loadComplianceFormConditionInfo(compForm, orderConditions,likeOrderConditions);
			}
	}

	/**
	 * @description: loads the complianceForm dropdown values
	 * @param
	 */
	public static void loadComplianceFormDropDowns(ComplianceForm compForm) {
	//retrieve group drop down collection values
			GetAllGroupsEvent event = new GetAllGroupsEvent();
			event.setAgencyId(compForm.getAgencyId());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(event);
			Collection groups = MessageUtil.compositeToCollection((CompositeResponse) dispatch.getReply(), GroupResponseEvent.class);
			Collections.sort((ArrayList) MessageUtil.processEmptyCollection(groups));
			compForm.setGroups(groups);
			Collection reasons = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.COMPLIANCE_REASON);
			compForm.setComplianceReasons(reasons);
	}

	/**
     * @description: loads the complianceForm values
     * @param
     */
	public static void loadComplianceFormConditionInfo(ComplianceForm compForm, List orderConditions, List likeOrderConditions) {
			List uConditions = sortConditionsList(orderConditions);
			String caseNumStr = "";
			if (uConditions != null && !uConditions.isEmpty()){
				Iterator uCondIter = uConditions.iterator();
				while (uCondIter.hasNext()){
					ComplianceConditionResponseEvent ccre = (ComplianceConditionResponseEvent) uCondIter.next();
					ccre.setDisplayCaseNum(removeCDIfromCaseNumber(ccre.getCaseNumber()));
					List courtPolicies = ccre.getCourtPolicies();	
					if (courtPolicies != null && courtPolicies.size() > 1){
						ccre.setCourtPolicies(sortCourtPolicies(courtPolicies));						
					}	
					List deptPolicies = ccre.getDepartmentPolicies();	
					if (deptPolicies != null && deptPolicies.size() > 1){
						ccre.setDepartmentPolicies(sortDepartmentPolicies(deptPolicies));						
					}	
				}
			}
	    	compForm.setUniqueConditions(uConditions);
			List lConditions = sortLikeConditionsList(likeOrderConditions);
			if (lConditions != null && !lConditions.isEmpty()){
				Iterator lCondIter = lConditions.iterator();
				while (lCondIter.hasNext()){
					LikeComplianceConditionResponseEvent lccre = (LikeComplianceConditionResponseEvent) lCondIter.next();					
					Iterator lCondIter2 = lccre.getComplianceConditionResponseEvents().iterator();
					while (lCondIter2.hasNext()){
						ComplianceConditionResponseEvent lccre2 = (ComplianceConditionResponseEvent) lCondIter2.next();
						lccre2.setDisplayCaseNum(removeCDIfromCaseNumber(lccre2.getCaseNumber()));
					}
					List courtPolicies = lccre.getCourtPolicies();	
					if (courtPolicies != null && courtPolicies.size() > 1){
						lccre.setCourtPolicies(sortCourtPolicies(courtPolicies));						
					}	
					List deptPolicies = lccre.getDepartmentPolicies();	
					if (deptPolicies != null && deptPolicies.size() > 1){
						lccre.setDepartmentPolicies(sortDepartmentPolicies(deptPolicies));						
					}	
				}
			}
			compForm.setLikeConditions(lConditions);
	// Save all conditions for filtering - View All        	
	    	compForm.setAllUniqueConditions(compForm.getUniqueConditions());
	       	compForm.setAllLikeConditions(compForm.getLikeConditions());     	
	// retrieve case numbers in sorted order       	
		   	compForm.setCaseNumbers(conditionCaseNumbers(orderConditions, likeOrderConditions ));
	    }

	/**
	 * @param unique condition List
	 * @param like condition List
	 * @return collection
	 */
   private static Collection conditionCaseNumbers(List orderConditions, List likeOrderConditions)
   {
       	TreeMap cn = new TreeMap();
       	String caseNumStr = "";
       	int len = orderConditions.size();
		for (int x=0; x < len; x++)
		{
			ComplianceConditionResponseEvent ccre = (ComplianceConditionResponseEvent)orderConditions.get(x);
			if (StringUtils.isNotEmpty(ccre.getCaseNumber()))
			{	
				caseNumStr = ccre.getCaseNumber().substring(3,ccre.getCaseNumber().length());
				cn.put(ccre.getCaseNumber(),caseNumStr);
			}
		}
       	len = likeOrderConditions.size();
//       	int len2 = 0;
		for (int x=0; x < len; x++)
		{
			LikeComplianceConditionResponseEvent ccre = (LikeComplianceConditionResponseEvent)likeOrderConditions.get(x);
			Iterator ccrIter = ccre.getComplianceConditionResponseEvents().iterator();
			while (ccrIter.hasNext())
			{
				ComplianceConditionResponseEvent ccre2 = (ComplianceConditionResponseEvent)ccrIter.next();
				if (StringUtils.isNotEmpty(ccre2.getCaseNumber()))
				{	
					caseNumStr = ccre2.getCaseNumber().substring(3,ccre2.getCaseNumber().length());
					cn.put(ccre2.getCaseNumber(),caseNumStr);
				}				
			}				
		} 
       	Set cnums = cn.entrySet();
   		return cnums;
   }

   public static boolean getSuperviseeComplianceStatus(Collection orderConditions, Collection likeOrderConditions)
   {
   	 boolean status = true;
   	 if(!orderConditions.isEmpty())
   	 {
		Iterator iter = orderConditions.iterator();
		while(iter.hasNext()){
			ComplianceConditionResponseEvent ccre = (ComplianceConditionResponseEvent) iter.next();
			if (ccre.isCompliant() == false){
				status = false;
				break;
			}
		}
   	 }
   	 if (status == true && !likeOrderConditions.isEmpty()){
		Iterator lgIter = likeOrderConditions.iterator();
		while (lgIter.hasNext()){
			LikeComplianceConditionResponseEvent lccre = (LikeComplianceConditionResponseEvent)lgIter.next();
			Iterator iter2 = lccre.getComplianceConditionResponseEvents().iterator();
			while (iter2.hasNext())	{	
				ComplianceConditionResponseEvent ccre2 = (ComplianceConditionResponseEvent)iter2.next();
				if (ccre2.isCompliant() == false){
					status = false;
					break;
				}					
			}
		}	
	}	
   	 return status;
   }
   
   /**
	 * @param supervision period Id
	 * @param compliance Form
	 * @return void
	 */   
	public static void setAllConditionCaseNotes(ComplianceForm compForm){
		GetConditionCasenotesForCaseOrderChainEvent cnEvent = (GetConditionCasenotesForCaseOrderChainEvent)EventFactory.getInstance(
				CasenoteControllerServiceNames.GETCONDITIONCASENOTESFORCASEORDERCHAIN);
		cnEvent.setOrderChainNumber(Integer.parseInt(compForm.getChainNum()));
		cnEvent.setCaseId(removeSpacefromCaseNumber(compForm.getCaseNumber()));
		cnEvent.setConditionId(Integer.parseInt(compForm.getConditionId()));
		
		CompositeResponse response = MessageUtil.postRequest(cnEvent);
		Collection casenotes = MessageUtil.compositeToCollection(response, CasenoteResponseEvent.class);		

		ArrayList currentCasenotes = new ArrayList();
		if (!casenotes.isEmpty())
		{
			CasenoteSearchForm searchForm = new CasenoteSearchForm();
			Collection casenoteSubjectList = searchForm.getCasenoteSubjectList();
			Collection casenoteTypeList = searchForm.getCasenoteTypeList();
			Collection collateralList = searchForm.getCollateralList();
			Collection contactMethodList = searchForm.getContactMethodList();
			String currentUserId = UIUtil.getCurrentUserID();

			Iterator iter = casenotes.iterator();
			while (iter.hasNext())
			{
				ICasenote casenote = (CasenoteResponseEvent)iter.next();
				//currentCasenotes.add(UICasenoteHelper.getCasenote(casenote));
				Casenote newCasenote = UICasenoteHelper.getCasenote(casenote, currentUserId);
				newCasenote.setCasenoteTypeId(casenote.getCasenoteTypeId(), casenoteTypeList);
				String[] associatesArr = UICasenoteHelper.getArrayFromCollection(casenote.getAssociates());
				newCasenote.setAssociateIds(associatesArr, collateralList);
				String[] cnSubjs = UICasenoteHelper.getArrayFromCollection(casenote.getSubjects());
				newCasenote.setSubjectIds(cnSubjs, casenoteSubjectList);
				newCasenote.setAssociateIds(associatesArr, collateralList);
				newCasenote.setContactMethodId(casenote.getContactMethodId(), contactMethodList);
				currentCasenotes.add(newCasenote);
			}
			UICasenoteHelper.resolveCreatorNames(currentCasenotes);
		}
		Collections.sort(currentCasenotes);
		compForm.setCurrentCasenotes(currentCasenotes);
	}
   
	/**
	 * Create Date from date and time Strings
	 * 
	 * @return Date
	 * @pre strDate != null
	 * @pre strTime != null
	 * @pre strDate = mm/dd/yyyy format
	 * @pre strDate.length() == 10
	 * @pre strTime.length() <= 5 (HH:MM)
	 */
	public static Date convertToDateTime(String strDate, String strTime) {

		Date date = null;
		try {
			int year = new Integer(strDate.substring(6, 10)).intValue();
			int month = new Integer(strDate.substring(0, 2)).intValue();
			int day = new Integer(strDate.substring(3, 5)).intValue();
			int hours = new Integer(strTime.substring(0, 2)).intValue();
			int minutes = new Integer(strTime.substring(3, 5)).intValue();
			int seconds = 0;
			Calendar calendar = Calendar.getInstance();
			calendar.setLenient(false);
			calendar.set(year, month - 1, day, hours, minutes, seconds);
			date = calendar.getTime();
		} catch (NumberFormatException e) {
		} catch (IndexOutOfBoundsException e) {
		} catch (IllegalArgumentException e) {
			//This exception is thrown if calendar was created with an invalid
			// date.
		}
		return date;
	}

	/**
	 * @param compForm
	 */
	public static void setAllCaseNotes(ComplianceForm compForm) {
		GetCasenotesBySuperviseeIdEvent cnEvent = (GetCasenotesBySuperviseeIdEvent)EventFactory.getInstance(
				CasenoteControllerServiceNames.GETCASENOTESBYSUPERVISEEID);
		cnEvent.setSuperviseeId(compForm.getDefendantId());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		
		dispatch.postEvent(cnEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		MessageUtil.processReturnException(response);
		Collection casenotes = MessageUtil.compositeToCollection(response, CasenoteResponseEvent.class);		
		ArrayList currentCasenotes = new ArrayList();
		if (!casenotes.isEmpty())
		{
			CasenoteSearchForm searchForm = new CasenoteSearchForm();
			Collection casenoteSubjectList = searchForm.getCasenoteSubjectList();
			Collection casenoteTypeList = searchForm.getCasenoteTypeList();
			Collection collateralList = searchForm.getCollateralList();
			Collection contactMethodList = searchForm.getContactMethodList();
			String currentUserId = UIUtil.getCurrentUserID();

			Iterator iter = casenotes.iterator();
			while (iter.hasNext())
			{
				ICasenote casenote = (CasenoteResponseEvent)iter.next();
				//currentCasenotes.add(UICasenoteHelper.getCasenote(casenote));
				Casenote newCasenote = UICasenoteHelper.getCasenote(casenote, currentUserId);
				newCasenote.setCasenoteTypeId(casenote.getCasenoteTypeId(), casenoteTypeList);
				String[] associatesArr = UICasenoteHelper.getArrayFromCollection(casenote.getAssociates());
				newCasenote.setAssociateIds(associatesArr, collateralList);
				String[] cnSubjs = UICasenoteHelper.getArrayFromCollection(casenote.getSubjects());
				newCasenote.setSubjectIds(cnSubjs, casenoteSubjectList);
				newCasenote.setAssociateIds(associatesArr, collateralList);
				newCasenote.setContactMethodId(casenote.getContactMethodId(), contactMethodList);
				currentCasenotes.add(newCasenote);
			}
			UICasenoteHelper.resolveCreatorNames(currentCasenotes);
		}
		Collections.sort(currentCasenotes);
		compForm.setCurrentCasenotes(currentCasenotes);
	}   

	/**
	 * @param conditionsList
	 * @return List
	 */
	public static List sortConditionsList(Collection conditionsList){
		Iterator iter = conditionsList.iterator();
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			ComplianceConditionResponseEvent ccre = (ComplianceConditionResponseEvent) iter.next();	
			map.put(ccre.isCompliant() + ccre.getOrderConditionName(), ccre);
		}
		return new ArrayList(map.values());
	} 
	/**
	 * @param conditionsList
	 * @return List
	 */
	public static List sortLikeConditionsList(Collection LikeConditionsList){
		Iterator iter = LikeConditionsList.iterator();
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			boolean isNonCompliant = false;
	        LikeComplianceConditionResponseEvent lccre = (LikeComplianceConditionResponseEvent) iter.next();
		    Iterator iter2 = lccre.getComplianceConditionResponseEvents().iterator();   
		    SortedMap map2 = new TreeMap();
		    int count = 0;
		    int count1 = 0;
		    while(iter2.hasNext()){
		        ComplianceConditionResponseEvent ccre = (ComplianceConditionResponseEvent) iter2.next();
		        map2.put(ccre.isCompliant() + ccre.getOrderConditionName() + (999999999 - ccre.getNcCount()) + ccre.getSprOrderConditionId(), ccre);
		        if(ccre.isCompliant() == false){
		        	isNonCompliant = true;
		        	lccre.setCompliant(false);
		        	
		        	if(ccre.getNcCount() > count){
			        	count = ccre.getNcCount();
			        }
		        }else{	
		        	if(ccre.getNcCount() > count1){
			        	count1 = ccre.getNcCount();
			        }
		        }
		    }
		    lccre.setNcCount(lccre.isCompliant()?count1:count);
		    lccre.setComplianceConditionResponseEvents(new ArrayList(map2.values()));
		    map.put("" + lccre.isCompliant() + (lccre.isCompliant()?(999999999 - count1):(999999999 - count)) + lccre.getOrderConditionName()  + lccre.getSprOrderConditionId(), lccre);
		}
		return new ArrayList(map.values());
    } 
	
	/**
	 * @param courtPolicies
	 * @return List
	 */
	public static List sortCourtPolicies(List courtPolicies){
		Iterator iter = courtPolicies.iterator();
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			OrderConditionCourtPolicyResponseEvent occpre = (OrderConditionCourtPolicyResponseEvent) iter.next();	
			map.put(occpre.getCourtPolicyName(), occpre);
		}
		return new ArrayList(map.values());
	}	
	/**
	 * @param courtPolicies
	 * @return List
	 */
	public static List sortDepartmentPolicies(List deptPolicies){
		Iterator iter = deptPolicies.iterator();
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			OrderConditionAgencyPolicyResponseEvent ocapre = (OrderConditionAgencyPolicyResponseEvent) iter.next();	
			map.put(ocapre.getAgencyPolicyName(), ocapre);
		}
		return new ArrayList(map.values());
	}

	/**
	 * @param courtPolicies
	 * @return List
	 */
	public static String removeCDIfromCaseNumber(String caseNumber){
		String displayCaseNum = "";
		if (StringUtils.isNotEmpty(caseNumber)){
			displayCaseNum = caseNumber.substring(3, caseNumber.length());
		}
		return displayCaseNum;
	}
	
	/**
	 * @param caseNumber
	 * @return String
	 */
	public static String removeSpacefromCaseNumber(String caseNumber){
		StringBuffer trimCaseNum = new StringBuffer();
		if (StringUtils.isNotEmpty(caseNumber)){
			trimCaseNum.append(caseNumber.substring(0, 3));
			trimCaseNum.append(caseNumber.substring(4, caseNumber.length()));
		}
		return trimCaseNum.toString();
	}
	
}// END CLASS

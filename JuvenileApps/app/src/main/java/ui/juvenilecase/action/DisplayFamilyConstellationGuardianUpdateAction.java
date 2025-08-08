/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyConstellationGuardianFinancialEvent;
import messaging.family.GetFamilyConstellationMemberLatestFinancialEvent;
import messaging.family.GetFamilyMemberBenefitsEvent;
import messaging.family.SaveFamilyConstellationEvent;
import messaging.family.SaveFamilyConstellationMemberInfoEvent;
import messaging.family.SaveFamilyMemberFinancialEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberFinancialResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberBenefitResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.ujac.util.BeanComparator;

import pd.juvenilecase.family.JuvenileFamilyHelper;

import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayFamilyConstellationGuardianUpdateAction extends LookupDispatchAction
{

		/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.update", "update");
		keyMap.put("button.next", "next");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.link", "updateGuardianInfo");
		keyMap.put("button.finish", "finish");
		return keyMap;
	}
	
	
				
	
		
	public ActionForward next(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
			{
			JuvenileFamilyForm myFamForm=(JuvenileFamilyForm)aForm;
			JuvenileFamilyForm.Constellation myConstellation=myFamForm.getCurrentConstellation();
			myFamForm.setSecondaryAction(UIConstants.VIEW);
			myFamForm.setNextGuardian(null);
			JuvenileFamilyForm.Guardian myCurrGuardian=myFamForm.getCurrentGuardian();
			JuvenileFamilyForm.Constellation currentConstellation=myFamForm.getCurrentConstellation();
			currentConstellation.setGuardiansList(new ArrayList());
			Collection members=currentConstellation.getMemberList();
			boolean foundMember=false;
			JuvenileFamilyForm.MemberList myMember=null;
			Iterator iter=null;
			if(members!=null){
			iter=members.iterator();
			JuvenileFamilyForm.MemberList tempMember=null;
			
			
			while(iter.hasNext()){
				tempMember=(JuvenileFamilyForm.MemberList)iter.next();
				if(foundMember && tempMember.isGuardian()){
					myMember=tempMember;
				}
				if(!foundMember && (tempMember.getMemberNumber().equalsIgnoreCase(myCurrGuardian.getMemberNumber()))){
					foundMember=true;	
				}
			}
			}
			if(!foundMember)
				return aMapping.findForward(UIConstants.FAILURE);
			JuvenileFamilyForm.Guardian myGuardian=setGuardian(myMember,currentConstellation);
			myFamForm.setCurrentGuardian(myGuardian);
			ActionForward forward = aMapping.findForward("displayGuardianInfoSummary");
			return forward;
	}
			
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileFamilyForm myFamForm=(JuvenileFamilyForm)aForm;
		JuvenileFamilyForm.Constellation myConstellation=myFamForm.getCurrentConstellation();
		JuvenileFamilyForm.Guardian myGuardian=myFamForm.getCurrentGuardian();
		boolean saveGuardian=true;
		if(myFamForm.getSecondaryAction().equalsIgnoreCase(UIConstants.UPDATE)){
			Collection guardians=myConstellation.getGuardiansList();
			if(guardians!=null && guardians.size()>0){
				JuvenileFamilyForm.Guardian tempGuardian=(JuvenileFamilyForm.Guardian)(((ArrayList)guardians).get(0));
				boolean isSame=UIJuvenileFamilyHelper.isGuardianTheSame(myGuardian, tempGuardian);
				saveGuardian=!isSame;
			}
			
		}
		if(saveGuardian){
			if(myConstellation==null)
				return aMapping.findForward(UIConstants.FAILURE);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				// Set Juvenile Number in main event
			SaveFamilyConstellationEvent event = new SaveFamilyConstellationEvent();
			event.setJuvNum(myFamForm.getJuvenileNumber());
			event.setConstellationNum(myFamForm.getCurrentConstellation().getFamilyNumber());
			// Set Members
			Collection members=myConstellation.getMemberList();
			JuvenileFamilyForm.MemberList myConstMember;
			if(members!=null){
				Iterator iter=members.iterator();
				while(iter.hasNext()){
					myConstMember=(JuvenileFamilyForm.MemberList)iter.next();
					if(myConstMember.getMemberNumber().equals(myGuardian.getMemberNumber())){
						SaveFamilyConstellationMemberInfoEvent myFamMemEvent=UIJuvenileHelper.getSaveFamilyConstellationMemberInfoEvent(myConstMember);
						event.addRequest(myFamMemEvent);
						SaveFamilyMemberFinancialEvent myGuardianEvent=UIJuvenileHelper.getSaveFamilyFinancialEvent(myGuardian,myConstellation.getFamilyNumber(),myConstMember.getMemberNumber());
						myFamMemEvent.addRequest(myGuardianEvent);
					}
				}
			}
			dispatch.postEvent(event);	   
			// Getting PD Response Event	
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			// Perform Error handling
			MessageUtil.processReturnException(response); 
		}
		
		
		myFamForm.setSecondaryAction(UIConstants.CONFIRM);
		ActionForward forward = aMapping.findForward("updateGuardianSuccess");
		return forward;
	}
		
	
	public ActionForward update(
						ActionMapping aMapping,
						ActionForm aForm,
						HttpServletRequest aRequest,
						HttpServletResponse aResponse)
					{
						JuvenileFamilyForm myFamForm=(JuvenileFamilyForm)aForm;
			if(myFamForm.getCurrentGuardian()!=null && myFamForm.getCurrentGuardian().getEmploymentInfoList()==null)
				UIJuvenileFamilyHelper.getEmploymentMemberInfo(myFamForm.getCurrentGuardian().getMemberNumber(), myFamForm.getCurrentGuardian());			
		
			myFamForm.setSecondaryAction(UIConstants.UPDATE);
			ActionForward forward =aMapping.findForward("updateGuardian");
			return forward;
	}
	
	public ActionForward updateGuardianInfo(
						ActionMapping aMapping,
						ActionForm aForm,
						HttpServletRequest aRequest,
						HttpServletResponse aResponse)
		{
			JuvenileFamilyForm myFamForm=(JuvenileFamilyForm)aForm;
			JuvenileFamilyForm.Constellation myConstellation=myFamForm.getCurrentConstellation();
			myFamForm.setSecondaryAction(UIConstants.VIEW);
			myFamForm.setNextGuardian(null);
			myFamForm.setCurrentGuardian(null);
			JuvenileFamilyForm.Constellation currentConstellation=myFamForm.getCurrentConstellation();
			currentConstellation.setGuardiansList(new ArrayList());
			Collection members=currentConstellation.getMemberList();
			Iterator iter=null;
			JuvenileFamilyForm.MemberList myMember=null;
			ArrayList tempGuardians = new ArrayList();
			boolean foundMember=false;
			if(members!=null){
				iter=members.iterator();
				JuvenileFamilyForm.MemberList tempMember=null;
				
				while(iter.hasNext()){
					tempMember=(JuvenileFamilyForm.MemberList)iter.next();
					if(foundMember && tempMember.isGuardian()){
						myFamForm.setNextGuardian(tempMember.getMemberNumber());
					}
					//changed for US 27023 - guardian should be primary guardian
					if(!foundMember)
					{
						if(tempMember.isGuardian() && tempMember.isPrimaryContact()){
							foundMember=true;	
							myMember=tempMember;
						}
						else if(tempMember.isGuardian())
							tempGuardians.add(tempMember);
							
					}
					
				}
			}
			//if no primary guardian - get the most recent guardian, sort the collection by createdate
			if(!foundMember)
			{
				//return aMapping.findForward(UIConstants.FAILURE);
				List unsorted = new ArrayList(tempGuardians);
				ArrayList sortedList = new ArrayList();
				sortedList.add(new ReverseComparator(new BeanComparator("confirmedDate")));
				ComparatorChain chain = new ComparatorChain(sortedList);
				Collections.sort(unsorted, chain);
				myMember=(JuvenileFamilyForm.MemberList)unsorted.get(0);
			}
			JuvenileFamilyForm.Guardian myGuardian=setGuardian(myMember,currentConstellation);
			myFamForm.setCurrentGuardian(myGuardian);
			ActionForward forward = aMapping.findForward("displayGuardianInfoSummary");
			return forward;
		}			
		
		
	private JuvenileFamilyForm.Guardian setGuardian(JuvenileFamilyForm.MemberList myMember,JuvenileFamilyForm.Constellation currentConstellation){
		JuvenileFamilyForm.Guardian myGuardian=new JuvenileFamilyForm.Guardian();
		if(myMember==null)
			return myGuardian;
		myGuardian.setMemberNumber(myMember.getMemberNumber());
		myGuardian.setConstellationMemberId(myMember.getFamilyConstellationMemberNum());
		myGuardian.setName(myMember.getMemberName());
		myGuardian.setRelationshipToJuv(myMember.getRelationshipToJuv());
		myGuardian.setDeceased(myMember.getDeceasedYesNo());
		myGuardian.setNumberInFamily(currentConstellation.getFamilyNumber());
		// Insert new functionality here
		GetFamilyConstellationMemberLatestFinancialEvent request =
				(GetFamilyConstellationMemberLatestFinancialEvent) EventFactory.getInstance(
						JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONMEMBERLATESTFINANCIAL);
		request.setConstelltionMemberId(myGuardian.getConstellationMemberId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(request);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(response);
		//Assert.assertTrue(message.toString(), obj != null);
		if (dataMap != null)
		{
			ArrayList guardList=new ArrayList();
			String topic =
				myGuardian.getConstellationMemberId()
					+ "_"
					+ PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_FINANCIAL_TOPIC;
			Collection guardians = (Collection) dataMap.get(topic);
			Iterator iterGuard=null;
			if(guardians!=null){
				iterGuard = guardians.iterator();
				while (iterGuard.hasNext())
				{
					FamilyConstellationMemberFinancialResponseEvent reply =
						(FamilyConstellationMemberFinancialResponseEvent) iterGuard.next();
		
					JuvenileFamilyForm.Guardian currGuardian=UIJuvenileHelper.getFamilyFormGuardianFROMFamilyConstellationFinancialRespEvt(reply);
					currGuardian.setNumberInFamily(currentConstellation.getFamilyNumber());
					guardList.add(currGuardian);
				}
			}
			if(guardList!=null && guardList.size()>0){
				// do nothing works as should
			}
			else{
				FamilyConstellationMemberFinancialResponseEvent respEvtFake=new FamilyConstellationMemberFinancialResponseEvent();
				respEvtFake.setEntryDate(new Date());
				JuvenileFamilyForm.Guardian currGuardian=UIJuvenileHelper.getFamilyFormGuardianFROMFamilyConstellationFinancialRespEvt(respEvtFake);
				guardList.add(currGuardian);  // in case migrated data doesn't have guardina records.
			}
				currentConstellation.setGuardiansList(UIJuvenileHelper.sortGuardianList(guardList));
				JuvenileFamilyForm.Guardian mostRecentGuardian=(JuvenileFamilyForm.Guardian)((ArrayList)currentConstellation.getGuardiansList()).get(0);
//				myGuardian.setAnnualNetIncome(mostRecentGuardian.getAnnualNetIncome());
				myGuardian.setChildSupportPayorName(mostRecentGuardian.getChildSupportPayorName());
				myGuardian.setFinancialId(mostRecentGuardian.getFinancialId());
				myGuardian.setFoodStamps(mostRecentGuardian.getFoodStamps());
				myGuardian.setGroceryExpenses(mostRecentGuardian.getGroceryExpenses());
				myGuardian.setIntangibleValue(mostRecentGuardian.getIntangibleValue());
//				myGuardian.setJobTitle(mostRecentGuardian.getJobTitle());
				myGuardian.setLifeInsurancePremium(mostRecentGuardian.getLifeInsurancePremium());
				myGuardian.setMedicalExpenses(mostRecentGuardian.getMedicalExpenses());
				myGuardian.setNumberInFamily(mostRecentGuardian.getNumberInFamily());
				myGuardian.setNumberLivingInHome(mostRecentGuardian.getNumberLivingInHome());
				myGuardian.setNumberOfDependents(mostRecentGuardian.getNumberOfDependents());
				myGuardian.setOtherIncome(mostRecentGuardian.getOtherIncome());
//				myGuardian.setPlaceOfEmpoyment(mostRecentGuardian.getPlaceOfEmpoyment());
				myGuardian.setPropertyValue(mostRecentGuardian.getPropertyValue());
				myGuardian.setRentExpenses(mostRecentGuardian.getRentExpenses());
				myGuardian.setSavings(mostRecentGuardian.getSavings());
				myGuardian.setSchoolExpenses(mostRecentGuardian.getSchoolExpenses());
				myGuardian.setTanfAfdc(mostRecentGuardian.getTanfAfdc()); //taken out for US 27023 - - added back for Bug #51352
				myGuardian.setTotalExpenses(mostRecentGuardian.getTotalExpenses());
				myGuardian.setUtilitiesExpenses(mostRecentGuardian.getUtilitiesExpenses());
				myGuardian.setChildSupportPaid(mostRecentGuardian.getChildSupportPaid());
				myGuardian.setChildSupportReceived(mostRecentGuardian.getChildSupportReceived());     //taken out for US 27023 - added back for Bug #51352
				myGuardian.setOtherAdultIncome(mostRecentGuardian.getOtherAdultIncome());
				myGuardian.setNotes(mostRecentGuardian.getNotes());
				myGuardian.setEntryDate(mostRecentGuardian.getEntryDate());
				myGuardian.setSsi(mostRecentGuardian.getSsi());     //taken out for US 27023 - added back for Bug #51352
				UIJuvenileFamilyHelper.getEmploymentMemberInfo(myMember.getMemberNumber(),myGuardian);
				
				//US 27023 - get the benefits
				
				 //initialize the benefits
				/* 07/19/2017 taken out for Bug #51352 - US 27023 on hold
		        myGuardian.setChildSupportReceived(UIUtil.formatCurrency( Double.toString( 0 ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
		        myGuardian.setChampus(UIUtil.formatCurrency( Double.toString( 0 ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
		        myGuardian.setVABenefits(UIUtil.formatCurrency( Double.toString( 0 ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
		        myGuardian.setTanfAfdc(UIUtil.formatCurrency( Double.toString( 0 ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
		        myGuardian.setSsi(UIUtil.formatCurrency( Double.toString( 0 ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
		        myGuardian.setCountyPaidFC(UIUtil.formatCurrency( Double.toString( 0 ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
		        
		        GetFamilyMemberBenefitsEvent benefitEvent = (GetFamilyMemberBenefitsEvent) 
	  		    		EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERBENEFITS);
	  			
	  			benefitEvent.setMemberId(myMember.getMemberNumber());
	  			List benefits = MessageUtil.postRequestListFilter(benefitEvent, FamilyMemberBenefitResponseEvent.class);
	  			 FamilyMemberBenefitResponseEvent benefitRespEvt;
	  			if(benefits.size()>0)
	  			{
	  				 JuvenileMemberForm.MemberBenefit myBenefit;
	  				 for(Iterator<FamilyMemberBenefitResponseEvent> benefitsIter = benefits.iterator(); benefitsIter.hasNext(); )
	  				 {
	  					benefitRespEvt=benefitsIter.next();
	  					if(benefitRespEvt!=null)
	  					{
	  						String eligibilityType = benefitRespEvt.getEligibilityTypeId(); 
	  						if(eligibilityType!=null)
	  						{
	  							if(eligibilityType.equalsIgnoreCase("CHSP"))
	  								myGuardian.setChildSupportReceived(UIUtil.formatCurrency( Double.toString( benefitRespEvt.getReceivedAmt() ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
	  						
	  							if(eligibilityType.equalsIgnoreCase("CHMP"))
	  								myGuardian.setChampus(UIUtil.formatCurrency( Double.toString( benefitRespEvt.getReceivedAmt() ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
	  						
	  							if(eligibilityType.equalsIgnoreCase("VABN"))
	  								myGuardian.setVABenefits(UIUtil.formatCurrency( Double.toString( benefitRespEvt.getReceivedAmt() ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
	  						
	  							if(eligibilityType.equalsIgnoreCase("TANF"))
	  								myGuardian.setTanfAfdc(UIUtil.formatCurrency( Double.toString( benefitRespEvt.getReceivedAmt() ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
	  							
	  							if(eligibilityType.equalsIgnoreCase("SSI"))
	  								myGuardian.setSsi(UIUtil.formatCurrency( Double.toString( benefitRespEvt.getReceivedAmt() ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
	  						
	  							if(eligibilityType.equalsIgnoreCase("COFC"))
	  								myGuardian.setCountyPaidFC(UIUtil.formatCurrency( Double.toString( benefitRespEvt.getReceivedAmt() ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
	  						
	  						}
	  					}
	  				 }
	  			}*/

		}
		// END of new stuff
		return myGuardian;
		
	}
	
	public ActionForward back(
					ActionMapping aMapping,
					ActionForm aForm,
					HttpServletRequest aRequest,
					HttpServletResponse aResponse)
				{
					ActionForward forward = aMapping.findForward(UIConstants.BACK);
					return forward;
				}
		
	public ActionForward cancel(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
			{
				ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
				return forward;
			}			
				
	/**
	* @param aRequest
	*/
   private void sendToErrorPage(HttpServletRequest aRequest, String msg)
   {
	   ActionErrors errors = new ActionErrors();
	   errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
	   saveErrors(aRequest, errors);
   }
	}// END CLASS
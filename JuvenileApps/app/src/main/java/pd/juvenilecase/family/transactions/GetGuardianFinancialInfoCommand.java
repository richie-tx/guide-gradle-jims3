/*
 * Created on Aug 28, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.family.transactions;

import java.util.HashMap;
import java.util.Iterator;

import naming.PDCodeTableConstants;

import messaging.address.GetAddressByIdEvent;
import messaging.address.reply.AddressResponseEvent;
import messaging.family.GetGuardianFinancialInfoEvent;
import messaging.juvenilecase.reply.GuardianFinancialInfoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import pd.juvenilecase.family.GuardianFinancialInfo;
import pd.codetable.PDCodeHelper;



/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetGuardianFinancialInfoCommand implements ICommand {

	/**
	 * 
	 */
	public GetGuardianFinancialInfoCommand() {
	}

	   /**
	    * @param event
	    */
/*
	public void execute(IEvent event) {
		GetGuardianFinancialInfoEvent reqEvent = (GetGuardianFinancialInfoEvent)event;

		Iterator iter = GuardianFinancialInfo.findAll(reqEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(iter.hasNext()){
			GuardianFinancialInfo guardianInfo = (GuardianFinancialInfo) iter.next();		
			GuardianFinancialInfoResponseEvent resEvent = new GuardianFinancialInfoResponseEvent();
			if(guardianInfo.isConstellationStatus()) {
				if(guardianInfo.isGuardian()) {
					resEvent.setFirstName(guardianInfo.getFirstName());
					resEvent.setMiddleName(guardianInfo.getMiddleName());
					resEvent.setLastName(guardianInfo.getLastName());
					resEvent.setDisabled(guardianInfo.isDisabled());
					resEvent.setOccupation(guardianInfo.getOccupation());
					resEvent.setEmployerName(guardianInfo.getEmployerName());
					resEvent.setSalary(guardianInfo.getSalary());
					resEvent.setSalaryRate(guardianInfo.getSalaryRate());
					resEvent.setEmployerAddressId(guardianInfo.getEmployerAddressId());
					resEvent.setOtherIncomeAmount(guardianInfo.getOtherIncomeAmount());
					resEvent.setFamMemberId(guardianInfo.getFamMemberId());
					resEvent.setAnnualNetIncome(guardianInfo.getAnnualNetIncome());
					resEvent.setFoodStamps(guardianInfo.isFoodStamps());
					resEvent.setIntangibleProperty(guardianInfo.getIntangibleProperty());
					resEvent.setMonthlyLifeInsurancePremium(guardianInfo.getMonthlyLifeInsurancePremium());
					resEvent.setPropertyValue(guardianInfo.getPropertyValue());
					resEvent.setTanf(guardianInfo.getTanf());
					dispatch.postEvent(resEvent);	 
				}
			}
		}		
	}
	*/
	
	public void execute(IEvent event) {
		GetGuardianFinancialInfoEvent reqEvent = (GetGuardianFinancialInfoEvent)event;
		Iterator iter = GuardianFinancialInfo.findAll(reqEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		HashMap guardians = new HashMap();
		
		while(iter.hasNext()){
			GuardianFinancialInfo guardianInfo = (GuardianFinancialInfo) iter.next();	
			if(guardianInfo.isConstellationStatus() && guardianInfo.isGuardian()) {
				if(guardians.get(new Integer(guardianInfo.getFamMemberId()).toString()) != null){
					GuardianFinancialInfoResponseEvent resEvent = 
						(GuardianFinancialInfoResponseEvent)guardians.get(new Integer(guardianInfo.getFamMemberId()).toString());
					resEvent.setAnnualNetIncome(resEvent.getAnnualNetIncome() + guardianInfo.getAnnualNetIncome());
					resEvent.setEmployerAddressId(guardianInfo.getEmployerAddressId());
					
					if(resEvent.getEmployerAddressId() != 0) {
						GetAddressByIdEvent addressEvent = (GetAddressByIdEvent)EventFactory.getInstance("GetAddressById");
						addressEvent.setAddressId(new Integer(resEvent.getEmployerAddressId()).toString());
						IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
				   		dispatch1.postEvent(addressEvent);
						CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
						MessageUtil.processReturnException(compositeResponse);
						AddressResponseEvent resp = (AddressResponseEvent)MessageUtil.filterComposite(compositeResponse, AddressResponseEvent.class);
						if(resp != null) {
							String streetType = "";
							if (resp.getStreetType() != null) {
								streetType = 
									PDCodeHelper.getCodeDescriptionByCode(PDCodeHelper.getCodes(
											PDCodeTableConstants.STREET_TYPE, false), resp.getStreetType());
							}
							String state = 
								PDCodeHelper.getCodeDescriptionByCode(PDCodeHelper.getCodes(
										PDCodeTableConstants.STATE_ABBR, false), resp.getStateId());
							
							resEvent.setEmployerName(resEvent.getEmployerName() + ": " + guardianInfo.getEmployerName() + ", " 
									+ resp.getStreetNum() + " " + resp.getStreetName() + " " + streetType + " "
									+ resp.getAptNum() + ", " + resp.getCity() + ", " + state + ", " 
									+ resp.getZipCode() + " " + resp.getAdditionalZipCode());
						}
					}
					resEvent.setEmployerName(resEvent.getEmployerName()  
							+ " Salary $ " + guardianInfo.getSalary() 
							+ " per "  
							+ PDCodeHelper.getCodeDescriptionByCode(PDCodeHelper.getCodes(
									PDCodeTableConstants.SALARY_RATE, false), guardianInfo.getSalaryRate()));
				}
				else {
					GuardianFinancialInfoResponseEvent resEvent = new GuardianFinancialInfoResponseEvent();
					resEvent.setFirstName(guardianInfo.getFirstName());
					resEvent.setMiddleName(guardianInfo.getMiddleName());
					resEvent.setLastName(guardianInfo.getLastName());
					resEvent.setDisabled(guardianInfo.isDisabled());
					resEvent.setOccupation(guardianInfo.getOccupation());
					resEvent.setEmployerName(guardianInfo.getEmployerName());
					resEvent.setSalary(guardianInfo.getSalary());
					resEvent.setSalaryRate(guardianInfo.getSalaryRate());
					resEvent.setEmployerAddressId(guardianInfo.getEmployerAddressId());
					
					if(resEvent.getEmployerAddressId() != 0) {					
						GetAddressByIdEvent addressEvent = (GetAddressByIdEvent)EventFactory.getInstance("GetAddressById");
						addressEvent.setAddressId(new Integer(resEvent.getEmployerAddressId()).toString());
						IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
				   		dispatch1.postEvent(addressEvent);
						CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
						MessageUtil.processReturnException(compositeResponse);
						AddressResponseEvent resp = (AddressResponseEvent)MessageUtil.filterComposite(compositeResponse, AddressResponseEvent.class);
						if(resp != null) {
							String streetType = "";
							if (resp.getStreetType() != null) {
								streetType = 
									PDCodeHelper.getCodeDescriptionByCode(PDCodeHelper.getCodes(
											PDCodeTableConstants.STREET_TYPE, false), resp.getStreetType());
							}
							String state = 
								PDCodeHelper.getCodeDescriptionByCode(PDCodeHelper.getCodes(
										PDCodeTableConstants.STATE_ABBR, false), resp.getStateId());							
							resEvent.setEmployerName(resEvent.getEmployerName() + ", " 
									+ resp.getStreetNum() + " " + resp.getStreetName() + " " + streetType + " "
									+ resp.getAptNum() + ", " + resp.getCity() + ", " + state + ", " 
									+ resp.getZipCode() + " " + resp.getAdditionalZipCode());
						}
					}					
					resEvent.setEmployerName(resEvent.getEmployerName() + " Salary $ " + resEvent.getSalary() 
							+ " per " 
							+ PDCodeHelper.getCodeDescriptionByCode(PDCodeHelper.getCodes(
							PDCodeTableConstants.SALARY_RATE, false), resEvent.getSalaryRate()));
					
					
					resEvent.setOtherIncomeAmount(guardianInfo.getOtherIncomeAmount());
					resEvent.setFamMemberId(guardianInfo.getFamMemberId());
					resEvent.setAnnualNetIncome(guardianInfo.getAnnualNetIncome());
					resEvent.setFoodStamps(guardianInfo.getFoodStamps());
					resEvent.setIntangibleProperty(guardianInfo.getIntangibleProperty());
					resEvent.setMonthlyLifeInsurancePremium(guardianInfo.getMonthlyLifeInsurancePremium());
					resEvent.setPropertyValue(guardianInfo.getPropertyValue());
					resEvent.setTanf(guardianInfo.getTanf());
					resEvent.setSsi(guardianInfo.getSsi());
					guardians.put(new Integer(guardianInfo.getFamMemberId()).toString(), resEvent);
				}
			}
		}
		
		for(Iterator gIter = guardians.values().iterator(); gIter.hasNext();) {
			dispatch.postEvent((GuardianFinancialInfoResponseEvent)gIter.next());
			
		}
			
	}
	
	/**
	    * @param event
	    * @roseuid 4395C23501A0
	    */
	   public void onRegister(IEvent event) 
	   {
	     
	   }
	   
	   /**
	    * @param event
	    * @roseuid 4395C23501E6
	    */
	   public void onUnregister(IEvent event) 
	   {
	    
	   }
	   
	   /**
	    * @param anObject
	    * @roseuid 4395BC250375
	    */
	   public void update(Object anObject) 
	   {
	    
	   }

}

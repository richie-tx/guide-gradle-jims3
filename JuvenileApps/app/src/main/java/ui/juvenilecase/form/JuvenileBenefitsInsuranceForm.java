package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import messaging.codetable.reply.CodeResponseEvent;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileLoadCodeTables;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileBenefitsInsuranceForm extends ActionForm {

	String juvNumber = "";
	
	Collection juvBenefits = new ArrayList();	//FamilyMemberBenefitsResposeEvent
	Collection juvInsurances = new ArrayList(); //FamilyMemberInsuranceResponseEvent
	
	Collection newJuvBenefits = new ArrayList();
	Collection newJuvInsurances = new ArrayList();
	
	//place holder for user input
	JuvenileMemberForm.MemberBenefit currentBenefit = new JuvenileMemberForm.MemberBenefit();
	JuvenileMemberForm.MemberInsurance currentInsurance = new JuvenileMemberForm.MemberInsurance();
	
    //Added for US 27022
	Collection benefitsReceivers = new ArrayList();
	private List<CodeResponseEvent> benefitStatuses;
	//selected benefit position to be removed
	String selectedValue = "";
	
	public JuvenileBenefitsInsuranceForm()
	{
		UIJuvenileLoadCodeTables.getInstance().setJuvenileMemberForm(new JuvenileMemberForm());	
	}
	
	public void reset(ActionMapping mapping, javax.servlet.http.HttpServletRequest request)
	{
		super.reset(mapping,request);
	}
			  
																																																																																																																																																																																					
	
	//clears the form
	public void clear()
	{	
		newJuvBenefits.clear();
		newJuvInsurances.clear();
		juvNumber = "";
		selectedValue = "";
		currentBenefit = new JuvenileMemberForm.MemberBenefit();
		currentInsurance = new JuvenileMemberForm.MemberInsurance();
			
	}
	
	

	/**
	 * @return
	 */
	public Collection getJuvBenefits()
	{
		return juvBenefits;
	}

	/**
	 * @return
	 */
	public Collection getJuvInsurances()
	{
		return juvInsurances;
	}

	/**
	 * @return
	 */
	public Collection getNewJuvBenefits()
	{
		return newJuvBenefits;
	}

	/**
	 * @return
	 */
	public Collection getNewJuvInsurances()
	{
		return newJuvInsurances;
	}

	/**
	 * @param collection
	 */
	public void setJuvBenefits(Collection collection)
	{
		juvBenefits = collection;
	}

	/**
	 * @param collection
	 */
	public void setJuvInsurances(Collection collection)
	{
		juvInsurances = collection;
	}

	/**
	 * @param collection
	 */
	public void setNewJuvBenefits(Collection collection)
	{
		newJuvBenefits = collection;
	}

	/**
	 * @param collection
	 */
	public void setNewJuvInsurances(Collection collection)
	{
		newJuvInsurances = collection;
	}

	/**
	 * @return
	 */
	public String getJuvNumber()
	{
		return juvNumber;
	}

	/**
	 * @param string
	 */
	public void setJuvNumber(String string)
	{
		juvNumber = string;
	}


	/**
	 * @return
	 */
	public JuvenileMemberForm.MemberBenefit getCurrentBenefit()
	{
		return currentBenefit;
	}

	/**
	 * @param benefit
	 */
	public void setCurrentBenefit(JuvenileMemberForm.MemberBenefit benefit)
	{
		currentBenefit = benefit;
	}

	/**
	 * @return
	 */
	public String getSelectedValue()
	{
		return selectedValue;
	}

	/**
	 * @param string
	 */
	public void setSelectedValue(String string)
	{
		selectedValue = string;
	}
	
	public Collection getBenefitEligibilityList()
	{
		return JuvenileMemberForm.benefitEligibilityList;	
	}
	
	
	public Collection getInsuranceTypeList()
	{
		return JuvenileMemberForm.insuranceTypeList;	
	}

	/**
	 * @return
	 */
	public JuvenileMemberForm.MemberInsurance getCurrentInsurance()
	{
		return currentInsurance;
	}

	/**
	 * @param insurance
	 */
	public void setCurrentInsurance(JuvenileMemberForm.MemberInsurance insurance)
	{
		currentInsurance = insurance;
	}

	/**
	 * @return the benefitsReceivers
	 */
	public Collection getBenefitsReceivers() {
		return benefitsReceivers;
	}

	/**
	 * @param benefitsReceivers the benefitsReceivers to set
	 */
	public void setBenefitsReceivers(Collection benefitsReceivers) {
		this.benefitsReceivers = benefitsReceivers;
	}

	public List<CodeResponseEvent> getBenefitStatuses() {
		return benefitStatuses;
	}

	public void setBenefitStatuses(List<CodeResponseEvent> benefitStatuses) {
		this.benefitStatuses = benefitStatuses;
	}

}
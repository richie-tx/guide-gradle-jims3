/*
 * Created on Oct 18, 2007
 *
 */
package ui.juvenilecase.interviewinfo.form;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.contact.to.PhoneNumberBean;
import messaging.juvenilecase.reply.GuardianFinancialInfoResponseEvent;
import ui.common.Name;
import ui.juvenilecase.form.JuvenileMemberForm;

/**
 * @author awidjaja
 *
 */
public class FamilyMemberDetailsBean {
	private String memberId;
	private int memberNumber;
	private Name name;
	private String nameString;
	private JuvenileMemberForm.MemberAddress address;
	private boolean isGuardian;
	private String relationshipToJuvId;
	private String relationshipToJuv;
	private PhoneNumberBean phone; //latest home phone number
	private List employmentRecord; //MemberEmployment
	private GuardianFinancialInfoResponseEvent financialInfo;
	private boolean isMarried;
	private String marriedToMemberId;
	private boolean inHome;

	public FamilyMemberDetailsBean() {
		memberId = "";
		memberNumber = 0;
		name = new Name();
		nameString = "";
		address = new JuvenileMemberForm.MemberAddress();
		isGuardian = false;
		relationshipToJuv = "";
		phone = new PhoneNumberBean("");
		employmentRecord = new ArrayList();
		financialInfo = new GuardianFinancialInfoResponseEvent();
		
		isMarried = false;
		marriedToMemberId = "";
		inHome = false;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}	
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public JuvenileMemberForm.MemberAddress getAddress() {
		return address;
	}
	public void setAddress(JuvenileMemberForm.MemberAddress address) {
		this.address = address;
	}
	public List getEmploymentRecord() {
		return employmentRecord;
	}
	public void setEmploymentRecord(List employmentRecord) {
		this.employmentRecord = employmentRecord;
	}
	public GuardianFinancialInfoResponseEvent getFinancialInfo() {
		return financialInfo;
	}
	public void setFinancialInfo(GuardianFinancialInfoResponseEvent financialInfo) {
		this.financialInfo = financialInfo;
	}
	public boolean isGuardian() {
		return isGuardian;
	}
	public void setGuardian(boolean isGuardian) {
		this.isGuardian = isGuardian;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}	
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public PhoneNumberBean getPhone() {
		return phone;
	}
	public void setPhone(PhoneNumberBean phone) {
		this.phone = phone;
	}
	public String getRelationshipToJuv() {
		return relationshipToJuv;
	}
	public void setRelationshipToJuv(String relationshipToJuv) {
		this.relationshipToJuv = relationshipToJuv;
	}
	public boolean isMarried() {
		return isMarried;
	}
	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}
	public String getMarriedToMemberId() {
		return marriedToMemberId;
	}
	public void setMarriedToMemberId(String marriedToMemberId) {
		this.marriedToMemberId = marriedToMemberId;
	}
	public String getRelationshipToJuvId() {
		return relationshipToJuvId;
	}
	public void setRelationshipToJuvId(String relationshipToJuvId) {
		this.relationshipToJuvId = relationshipToJuvId;
	}
	
	public double getTotalAnnualIncome() {
		double totalAnnualIncome = 0;
		for(Iterator emplIter = employmentRecord.iterator();
			emplIter.hasNext();) {
			JuvenileMemberForm.MemberEmployment empl = 
				(JuvenileMemberForm.MemberEmployment)emplIter.next();
			totalAnnualIncome += empl.getAnnualNetIncomeAsDouble();
		}		
		return totalAnnualIncome;
	}
	public boolean isInHome() {
		return inHome;
	}
	public void setInHome(boolean inHome) {
		this.inHome = inHome;
	}
	
	public boolean isBirthParent() {
		if("BF".equalsIgnoreCase(relationshipToJuvId) || 
				"BM".equalsIgnoreCase(relationshipToJuvId) ||
				"BIRTH FATHER".equalsIgnoreCase(relationshipToJuv) ||
				"BIRTH MOTHER".equalsIgnoreCase(relationshipToJuv))
			return true;
		else
			return false;
	}
	
	public boolean isStepParent() {
		if("SF".equalsIgnoreCase(relationshipToJuvId) ||
				"SM".equalsIgnoreCase(relationshipToJuvId) ||
				"STEP FATHER".equalsIgnoreCase(relationshipToJuv) ||
				"STEP MOTHER".equalsIgnoreCase(relationshipToJuv))
			return true;
		else
			return false;
	}
	
}

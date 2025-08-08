/*
 * Created on Jun 16, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.reply.SocialElementCodeResponseEvent;
import messaging.contact.to.PhoneNumberBean;
import messaging.interviewinfo.reply.JuvenileBenefitResponseEvent;
import messaging.interviewinfo.reply.JuvenileInsuranceResponseEvent;
import messaging.juvenile.reply.JuvenileContactResponseEvent;
import messaging.juvenile.reply.JuvenileDrugsResponseEvent;
import messaging.juvenile.reply.JuvenileGangsResponseEvent;
import messaging.juvenile.reply.JuvenileJobResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import pd.codetable.Code;
import pd.codetable.SimpleCodeTableHelper;
import pd.codetable.person.SocialElementCode;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.transferobjects.helper.CodeHelper;
import ui.common.Address;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.juvenilecase.UIJuvenileCaseworkHelper;

/**
 * @author asrvastava
 *
 */
/**
 * @author dgibler
 * 
 */
public class JuvenileHelper {

	/**
	 * Takes a JuvenileContact and returns a JuvenileContactResponseEvent based
	 * off the values within the JuvenileContact
	 * 
	 * @param contact
	 * @return JuvenileContactResponseEvent
	 */
	public static JuvenileContactResponseEvent getJuvenileContactResponseEvent(
			JuvenileContact contact) {
		JuvenileContactResponseEvent respEvent = new JuvenileContactResponseEvent();
		respEvent.setTopic(PDJuvenileCaseConstants.JUVENILE_CONTACT_TOPIC);

		Code code = contact.getAddressType();
		if (code != null) {
			respEvent.setAddressTypeId(code.getCode());
			respEvent.setAddressType(code.getDescription());
		}
		// agency name is a codetable entry
		code = contact.getAgencyName();
		if (code != null) {
			respEvent.setAgencyNameId(code.getCode());
			respEvent.setAgencyName(code.getDescription());
		}
		code = contact.getCounty();
		if (code != null) {
			respEvent.setCountyId(code.getCode());
			respEvent.setCounty(code.getDescription());
		}
		code = contact.getRelationship();
		if (code != null) {
			respEvent.setRelationshipId(code.getCode());
			respEvent.setRelationship(code.getDescription());
		}
		code = contact.getState();
		if (code != null) {
			respEvent.setStateId(code.getCode());
			respEvent.setState(code.getDescription());
		}
		code = contact.getTitle();
		if (code != null) {
			respEvent.setTitleId(code.getCode());
			respEvent.setTitle(code.getDescription());
		}
		code = contact.getStreetType();
		if (code != null) {
			respEvent.setStreetTypeId(code.getCode());
			respEvent.setStreetType(code.getDescription());
		}
		code = contact.getStreetNumSuffix();
		if (code != null) {
			respEvent.setStreetNumSuffixId(code.getCode());
			respEvent.setStreetNumSuffix(code.getDescription());
		}
		respEvent.setCity(contact.getCity());
		if (contact.getOID() != null)
			respEvent.setContactNum(contact.getOID().toString());
		respEvent.setPhonePriorityInd(contact.getPhonePriorityInd());
		respEvent.setJuvenileNum(contact.getJuvenileId());
		respEvent.setApartmentNum(contact.getApartmentNum());
		respEvent.setCellPhone(contact.getCellPhone());
		respEvent.setCurrentAgencyInvolvementId(contact.getCurrentAgencyInvolvmentId());
		if (contact.getCurrentAgencyInvolvment() != null)
			respEvent.setCurrentAgencyInvolvement(contact.getCurrentAgencyInvolvment().getDescription());
		respEvent.setEMail(contact.getEMail());
		respEvent.setEntryDate(contact.getEntryDate());
		respEvent.setFax(contact.getFax());
		respEvent.setFirstName(contact.getFirstName());
		respEvent.setLastName(contact.getLastName());
		respEvent.setMiddleName(contact.getMiddleName());
		respEvent.setPreviousAgencyInvolvementId(contact .getPreviousAgencyInvolvmentId());
		if (contact.getPreviousAgencyInvolvment() != null)
			respEvent.setPreviousAgencyInvolvement(contact.getPreviousAgencyInvolvment().getDescription());
		respEvent.setStreetName(contact.getStreetName());
		respEvent.setStreetNum(contact.getStreetNum());
		respEvent.setWorkPhone(contact.getWorkPhone());
		respEvent.setWorkPhoneExtn(contact.getWorkPhoneExtn());
		respEvent.setZipCode(contact.getZipCode());
		respEvent.setAdditionalZipCode(contact.getAdditionalZipCode());
		respEvent.setValidated(contact.getValidated());
		respEvent.setDriverLicenseNum(contact.getDriverLicenseNum());
		respEvent.setDriverLicenseExpirationDate(contact.getDriverLicenseExpirationDate());
		respEvent.setStateIssuedIdNum(contact.getStateIssuedIdNum());
		respEvent.setPassportNum(contact.getPassportNum());
		respEvent.setPassportExpirationDate(contact.getPassportExpirationDate());
		respEvent.setAgeOver21(contact.isAgeOver21());
		respEvent.setDetentionVisit(contact.isDetentionVisit());
		code = contact.getDriverLicenseState();
		if(code != null){
		    respEvent.setDriverLicenseStateId(code.getCode());
		    respEvent.setDriverLicenseState(code.getDescription());
		}
		
		code = contact.getIssuedByState();
		if (code != null) {
			respEvent.setIssuedByStateId(code.getCode());
			respEvent.setIssuedByState(code.getDescription());
		}
		
		code = contact.getDriverLicenseClass();
		
		if (code != null) {
		    respEvent.setDriverLicenseClassId(code.getCode());
		    respEvent.setDriverLicenseClass(code.getDescription());
		}
		
		code = contact.getCountryOfIssuance();
		
		if (code !=null) {
		    respEvent.setCountryOfIssuanceId(code.getCode());
		    respEvent.setCountryOfIssuance(code.getDescription());
		}
		
		respEvent.setContactMemberComments(contact.getContactMemberComments());

		return respEvent;
	}

	/**
	 * Takes a JuvenileJob and returns a JuvenileJobResponseEvent based off the
	 * values within the JuvenileJob
	 * 
	 * @param job
	 * @return JuvenileJobResponseEvent
	 */
	public static JuvenileJobResponseEvent getJuvenileJobResponseEvent(
			JuvenileJob job) {
		JuvenileJobResponseEvent respEvent = new JuvenileJobResponseEvent();

		respEvent.setJobNum(job.getOID().toString());
		respEvent.setEmploymentPlace(job.getEmploymentPlace());
		respEvent.setEntryDate(job.getEntryDate());
		respEvent.setJobDescription(job.getJobDescription());
		respEvent.setJuvenileNum(job.getJuvenileId());
		respEvent.setWorkHours(job.getWorkHours());
		respEvent.setSupervisorFirstName(job.getSupervisorFirstName());
		respEvent.setSupervisorMiddleName(job.getSupervisorMiddleName());
		respEvent.setSupervisorLastName(job.getSupervisorLastName());
		respEvent.setSupervisorFamilyNum(job.getSupervisorFamilyNum());
		// employment status
		Code code = job.getEmploymentStatus();
		if (code != null) {
			respEvent.setEmploymentStatusId(code.getCode());
			respEvent.setEmploymentStatus(code.getDescription());
		}
		respEvent.setSalary(job.getSalary());
		// salary rate
		Code codeSalaryRate = job.getSalaryRate();
		if (codeSalaryRate != null) {
			respEvent.setSalaryRateId(codeSalaryRate.getCode());
			respEvent.setSalaryRate(codeSalaryRate.getDescription());
		}
		respEvent.setAnnualSalary(InterviewHelper.calculateAnnualIncome(
				job.getSalary(), job.getSalaryRateId(), job.getWorkHours()));

		respEvent.setTopic(PDJuvenileCaseConstants.JUVENILE_JOB_TOPIC);
		return respEvent;
	}

	/**
	 * Takes a JuvenileDrugs and returns a JuvenileDrugsResponseEvent based off
	 * the values within the JuvenileDrugs
	 * 
	 * @param drugs
	 * @return JuvenileDrugsResponseEvent
	 */
	public static JuvenileDrugsResponseEvent getJuvenileDrugsResponseEvent(
			JuvenileDrugs job) {
		JuvenileDrugsResponseEvent respEvent = new JuvenileDrugsResponseEvent();
		respEvent.setJuvenileNum(job.getJuvenileId());
		respEvent.setOnsetAge(job.getOnsetAge());
		respEvent.setAmountSpent(job.getAmountSpent());

		Code code = job.getDegree();
		if (code != null) {
			respEvent.setDegree(code.getDescription());
		}
		code = job.getDrugType();
		if (code != null) {
			respEvent.setDrugType(code.getDescription());
		}
		code = job.getFrequency();
		if (code != null) {
			respEvent.setFrequency(code.getDescription());
		}
		code = job.getLocationOfUse();
		if (code != null) {
			respEvent.setLocationOfUse(code.getDescription());
		}
		Date date = job.getEntryDate();
		if (date != null) {
			respEvent.setEntryDate(job.getEntryDate());
		}

		respEvent.setTopic(PDJuvenileCaseConstants.JUVENILE_DRUG_TOPIC);
		return respEvent;
	}

	// ER:GANG-JIMS200074578 STARTS
	/**
	 * set the gang results to the JuvenileGangsResponseEvent
	 * @param JuvenileGangs juvGangs
	 * @return JuvenileGangsResponseEvent respEvent
	 */
	public static JuvenileGangsResponseEvent getJuvenileGangsResponseEvent(
			JuvenileGangs juvGangs) {

		JuvenileGangsResponseEvent respEvent = new JuvenileGangsResponseEvent();

		respEvent.setJuvenileNum(juvGangs.getJuvenileId());
		
		// Hidden field for other,on selection of other,show other text box.
		respEvent.setOtherGangName(juvGangs.getOtherGangName());
		respEvent.setCliqueSet(juvGangs.getOtherCliqueSet());
		
		// Hidden field for describe hybrid on selection of hybrid as the gangname
		respEvent.setDescHybrid(juvGangs.getDescHybrid());
		
		respEvent.setAliasMoniker(juvGangs.getAliasMoniker());
		respEvent.setRank(juvGangs.getRank());
		
		//For dropdowns.
		Code code = juvGangs.getGangName();
		if (code != null) {

			/*
			 * if other gangname is selected, set the gang name to other gang
			 * name instead of othr description OTHER.
			 */
			if (code.getCode().equalsIgnoreCase("OTHR")) {
				respEvent.setGangName(juvGangs.getOtherGangName());
			} else {
				respEvent.setGangName(code.getDescription());
			}
		}

		code = juvGangs.getCliqueSet();
		if (code != null) {
			/*
			 * if other CliqueSet is selected, set the CliqueSet to other
			 * CliqueSet instead of othr description OTHER.
			 */
			if (code.getCode().equalsIgnoreCase("OTHR")) {
				respEvent.setCliqueSet(juvGangs.getOtherCliqueSet());
			} else {
				respEvent.setCliqueSet(code.getDescription());
			}
		}

		code = juvGangs.getCurrentStatus();
		if (code != null) {
			respEvent.setCurrentStatus(code.getDescription());
		}

		code = juvGangs.getAssociationType();
		if (code != null) {
			respEvent.setAssociationType(code.getDescription());
		}

		Date date = juvGangs.getEntryDate();
		if (date != null) {
			respEvent.setEntryDate(juvGangs.getEntryDate());
		}

		respEvent.setTopic(PDJuvenileCaseConstants.JUVENILE_GANG_TOPIC);
		return respEvent;
	}

	// ER:GANG-JIMS200074578 ENDS

	/**
	 * @param string
	 */
	public static Collection getSocialElementCodes(String element) {
		Collection codes = new ArrayList();
		Iterator iter = SocialElementCode.findAll("element", element);
		while (iter.hasNext()) {
			SocialElementCode code = (SocialElementCode) iter.next();
			SocialElementCodeResponseEvent codeReply = new SocialElementCodeResponseEvent();
			codeReply.setTopic(PDCodeTableConstants
					.getSocialElementCodeTableTopic(code
							.getElementDescription()));
			codeReply.setInactiveInd(code.getInactiveInd());
			codeReply.setElement(code.getElement());
			codeReply.setElementDescription(code.getElementDescription());
			codeReply.setReportGroup(code.getReportGroup());
			codeReply.setCode(code.getCode());
			codeReply.setSocialElementCodeId(code.getSocialElementCodeId());
			codeReply.setDescription(code.getCodeDescription());
			codes.add(codeReply);
		}
		return codes;
	}

	/**
	 * @param date
	 * @return Date
	 * @throws ParseException
	 */
	public static Date toDate(String date) {

		Date toDate = null;
		if (date.length() > 1) {
			String formatDt = "MM/dd/yyyy";
			int slashIndex = date.indexOf("/");
			if (slashIndex < 0) {
				formatDt = "MMddyyyy";
			}
			toDate = DateUtil.stringToDate(date, formatDt);
		}
		return toDate;
	}

	/**
	 * @param benefit
	 * @return
	 */
	public static JuvenileBenefitResponseEvent getJuvenileBenefitResponseEvent(
			JuvenileBenefit benefit) {
		JuvenileBenefitResponseEvent benefitResponse = new JuvenileBenefitResponseEvent();
		// benefitResponse.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_BENEFIT_TOPIC);
		benefitResponse.setEligibilityTypeId(benefit.getEligibilityTypeId());
		benefitResponse.setReceivingBenefits(benefit.isReceivingBenefits());
		benefitResponse.setEligibleForBenefits(benefit.isEligibleForBenefits());
		benefitResponse.setEntryDate(benefit.getCreateTimestamp());
		benefitResponse.setBenefitId(benefit.getOID().toString());
		benefitResponse.setReceivedBy(new Name(benefit.getReceivedByFirstName(), benefit.getReceivedByMiddleName(), benefit.getReceivedByLastName()));
		benefitResponse.setReceivedAmt(benefit.getReceivedAmt());
		benefitResponse.setIdNumber(benefit.getIdNumber());
		benefitResponse.setBenefitStatus(SimpleCodeTableHelper.getDescrByCode("JUVENILE_BENEFIT_STATUS", benefit.getBenefitStatus()));
		return benefitResponse;

	}

	/**
	 * @param insurance
	 * @return
	 */
	public static JuvenileInsuranceResponseEvent getJuvenileInsuranceResponseEvent(
			JuvenileInsurance insurance) {
		JuvenileInsuranceResponseEvent insuranceReply = new JuvenileInsuranceResponseEvent();
		// insuranceReply.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_INSURANCE_TOPIC);
		insuranceReply.setInsuranceId(insurance.getOID().toString());
		insuranceReply.setCarrier(insurance.getCarrier());
		insuranceReply.setTypeId(insurance.getInsuranceTypeId());
		insuranceReply.setPolicyNum(insurance.getPolicyNum());
		insuranceReply.setEntryDate(insurance.getCreateTimestamp());
		return insuranceReply;
	}
	
	public static int getDetVisitContactsOnlyCount(Collection contacts) {
		int detVisitContactsTotal = 0;
		Iterator iter = contacts.iterator();
		while( iter.hasNext())
		{
		    JuvenileContactResponseEvent contact = (JuvenileContactResponseEvent) iter.next();
			if(contact.isDetentionVisit())
			{
			    detVisitContactsTotal++;
			}
			
		}
		return detVisitContactsTotal;
	}
	
	public static boolean isDetDefenseAttorneyVisit(Collection contacts){
	    boolean daVisit = false;
	    Iterator iter = contacts.iterator();
	    while( iter.hasNext() ) {
		JuvenileContactResponseEvent contact = (JuvenileContactResponseEvent) iter.next();
		if (contact.getRelationshipId().equals("DA") && contact.isDetentionVisit()){
		    daVisit = true;
		    break;
		}
		
	    }
	    
	    return daVisit;
	    
	}
	
	public static boolean bypassDetentionVisitorCap(String juvenileNum){
	    boolean visitorCapRemoved = false;
	    Collection<JuvenileTraitResponseEvent> juvTraits = UIJuvenileCaseworkHelper.fetchJuvTraits(juvenileNum);
	    List<JuvenileTraitResponseEvent>DstJuvTraits = new ArrayList<JuvenileTraitResponseEvent>();
	    Iterator<JuvenileTraitResponseEvent>juvTraitsIter = juvTraits.iterator();
	    while( juvTraitsIter.hasNext() ) {
		JuvenileTraitResponseEvent traitResp = juvTraitsIter.next();
		if ( "DST".equals(traitResp.getTraitTypeId()) ) {
		    DstJuvTraits.add(traitResp);
		}
	    }
	    
	    if ( DstJuvTraits.size() > 0
		    && "CU".equals( DstJuvTraits.get(0).getStatusId() )) {
		visitorCapRemoved  = true;
	    }
	    
	    return visitorCapRemoved;
	}
	
	
	
	/** Takes in a date in String formated 'yyyy-MM-dd 00:00:00.0000000' and returns it as string in  MM/dd/yyyy 
	 *  (For display on the Juvenile Contact details pages)*/
	public static String dateConvert (String dateToFormat) throws ParseException{

		String inputDate = dateToFormat.substring(0, 10);
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = (Date)formatter.parse(inputDate.toString());
		SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
		String editedDate = newFormat.format(date);
		return editedDate;
		//String date=new SimpleDateFormat("MM/dd/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(inputDate));
		//return date;
	}
	
	
	/**
	 * 
	 */
	public static String formatAddress(Address address)
	{
	    String streetType = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STREET_TYPE, address.getStreetTypeId());
	    
	    if (address.getAdditionalZipCode()!= null && address.getAdditionalZipCode().isEmpty()){
		return new StringAppender().append(address.getStreetNum(), "").append(address.getStreetNumSuffixId(), " ").append(address.getStreetName(), " ").append(streetType, " ").append(address.getAptNum(), " ").append(address.getAddress2(), ", ").append(address.getCity(), ", ").append(address.getState(), ", ").append(address.getZipCode(), " ").toString();
	    }else
		return new StringAppender().append(address.getStreetNum(), "").append(address.getStreetNumSuffixId(), " ").append(address.getStreetName(), " ").append(streetType, " ").append(address.getAptNum(), " ").append(address.getAddress2(), ", ").append(address.getCity(), ", ").append(address.getState(), ", ").append(address.getZipCode(), " ").toString()+ ("-")+(address.getAdditionalZipCode());
	    
	}
	
	public static String formatAddres(Address address)
	{
	    if (address.getAdditionalZipCode()!= null && address.getAdditionalZipCode().isEmpty()){
		return new StringAppender().append(address.getStreetNum(), "").append(address.getStreetNumSuffix(), " ").append(address.getStreetName(), " ").append(address.getStreetType(), " ").append(address.getAptNum(), " ").append(address.getAddress2(), ", ").append(address.getCity(), ", ").append(address.getStateId(), ", ").append(address.getZipCode(), " ").toString();
	    }else
		return new StringAppender().append(address.getStreetNum(), "").append(address.getStreetNumSuffix(), " ").append(address.getStreetName(), " ").append(address.getStreetType(), " ").append(address.getAptNum(), " ").append(address.getAddress2(), ", ").append(address.getCity(), ", ").append(address.getStateId(), ", ").append(address.getZipCode(), " ").toString()+ ("-")+(address.getAdditionalZipCode());
	}
	
	/**
	 * 
	 */
	public static String formatPhone(PhoneNumber phone)
	{
		PhoneNumberBean p = new PhoneNumberBean(phone.getPhoneNumber());
		return new StringAppender().append(p.getFormattedPhoneNumber(), "").append(phone.getExt(), " x").toString();
	}
	
	private static class StringAppender
	{
		private StringBuffer	buf	= new StringBuffer();

		/*
		 * 
		 */
		public StringAppender append(String value, String joiner)
		{
			if ( notNullNotEmptyString( value ))
			{
				if (buf.length() > 0 && joiner != null)
				{
					buf.append(joiner);
				}
				buf.append(value);
			}
			return this;
		}

		/*
		 * 
		 */
		public StringAppender append(Code code, String joiner)
		{
			if (code != null && code.getDescription() != null)
			{
				return append(code.getDescription(), joiner);
			}
			return this;
		}

		public String toString()
		{
			return buf.toString();
		}
	}
	
	private static boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  (str.length() > 0) ) ;
	}
}

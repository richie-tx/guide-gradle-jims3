package ui.juvenilewarrant.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;

import pd.contact.officer.OfficerProfile;
import pd.juvenilewarrant.Charge;

import messaging.contact.domintf.IName;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantChargeEvent;
import messaging.juvenilewarrant.reply.ChargeResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileJusticeSystemResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantServiceResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.juvenilewarrant.reply.SummaryOfFactsResponseEvent;
import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDJuvenileWarrantConstants;
import naming.SessionAttributeNames;

import ui.action.AbstractFormDirector;
import ui.common.LengthBean;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SocialSecurity;
import ui.common.UIUtil;
import ui.juvenilecase.form.JuvenileFamilyForm.MemberList;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.juvenilewarrant.form.JuvenileAssociateBean;
import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author Jim Fisher
 *  
 */
public class JuvenileWarrantFormDirector extends AbstractFormDirector
{
    private final int ALIAS_MAX_LENGTH = 255;

    private final String FIRST_NAME_FIELD = "First Name";

    private final int FIRSTNAME_MAX_LENGTH = 30;

    private JuvenileWarrantForm form;

    private final String HEIGHT_FIELD = "Height";

    private final String JUVENILE_PHONE_FIELD = "Juvenile Phone";

    private final String LAST_NAME_FIELD = "Last Name";

    private final int LASTNAME_MAX_LENGTH = 30;

    private final int MIDDLENAME_MAX_LENGTH = 20;

    private final String SSN_FIELD = "Social Security Number";

    private final String TRANSACTION_NUMBER_FIELD = "Transaction Number";

    private final String WEIGHT_FIELD = "Weight";

    public JuvenileWarrantFormDirector(HttpServletRequest aRequest)
    {
        this.initFromSession(aRequest);
    }

    public JuvenileWarrantFormDirector(JuvenileWarrantForm form)
    {
        this.form = form;
    }

    private void checkJJSOptionalNoneditableErrors(IEvent event, ActionErrors errors)
    {
        JuvenileJusticeSystemResponseEvent jjsEvent = (JuvenileJusticeSystemResponseEvent) event;

        // Scrub the middlename value to simplify validation logic
        String middleName = this.scrubString(jjsEvent.getJuvenileMiddleName());
        if (this.validateStringLength(middleName, MIDDLENAME_MAX_LENGTH))
        {
            form.setMiddleName(middleName);
        }

        String aliasName = this.scrubString(jjsEvent.getAliasName());
        if (this.validateStringLength(aliasName, ALIAS_MAX_LENGTH))
        {
            form.setAliasName(aliasName);
        }
    }

    private void checkJJSRequiredNoneditableErrors(IEvent event, ActionErrors errors)
    {
        // TODO Refactor with a better validation abstraction
        // TODO Enforce noneditable error contract on form abstraction

        JuvenileJusticeSystemResponseEvent jjsEvent = (JuvenileJusticeSystemResponseEvent) event;

        String firstName = this.scrubString(jjsEvent.getJuvenileFirstName());
        if (firstName == null)
        {
            //super.addNonEditableMissingActionError(FIRST_NAME_FIELD, errors);
            super.setUnrecoverableError(true);
        }
        else if (this.validateStringLength(firstName, FIRSTNAME_MAX_LENGTH))
        {
            form.setFirstName(firstName);
        }
        else
        {
            super.addFieldExceedsLengthActionError(FIRST_NAME_FIELD, jjsEvent.getJuvenileFirstName(), FIRSTNAME_MAX_LENGTH,
                    errors);
            super.setUnrecoverableError(true);
        }

        String lastName = this.scrubString(jjsEvent.getJuvenileLastName());
        if (lastName == null)
        {
            //super.addNonEditableMissingActionError(LAST_NAME_FIELD, errors);
            super.setUnrecoverableError(true);
        }
        else if (this.validateStringLength(lastName, LASTNAME_MAX_LENGTH))
        {
            form.setLastName(lastName);
        }
        else
        {
            super.addFieldExceedsLengthActionError(LAST_NAME_FIELD, jjsEvent.getJuvenileLastName(), LASTNAME_MAX_LENGTH, errors);
            super.setUnrecoverableError(true);
        }
    }

    private void checkJOTOptionalNoneditableErrors(IEvent event, ActionErrors errors)
    {
        JuvenileOffenderTrackingResponseEvent jotEvent = (JuvenileOffenderTrackingResponseEvent) event;

        // Scrub the middlename value to simplify validation logic
        String middleName = this.scrubString(jotEvent.getMiddleName());
        if (this.validateStringLength(middleName, MIDDLENAME_MAX_LENGTH))
        {
            form.setMiddleName(middleName);
        }

        String aliasName = this.scrubString(jotEvent.getAliasName());
        if (this.validateStringLength(aliasName, ALIAS_MAX_LENGTH))
        {
            form.setAliasName(aliasName);
        }
    }

    private void checkJOTRequiredNoneditableErrors(IEvent event, ActionErrors errors)
    {
        // TODO Refactor with a better validation abstraction
        // TODO Enforce noneditable error contract on form abstraction

        JuvenileOffenderTrackingResponseEvent jotEvent = (JuvenileOffenderTrackingResponseEvent) event;

        String transactionNumString = this.scrubString(jotEvent.getTransactionNum());

        if (transactionNumString == null)
        {
            super.addNonEditableMissingActionError(TRANSACTION_NUMBER_FIELD, errors);
            super.setUnrecoverableError(true);
        }
        else
        {
            try
            {
                Integer.parseInt(jotEvent.getTransactionNum());
                form.setTransactionNum(transactionNumString);
            } catch (Exception e)
            {
                super.addNonEditableActionError(TRANSACTION_NUMBER_FIELD, jotEvent.getTransactionNum(), errors);
                super.setUnrecoverableError(true);
            }
        }

        String firstName = this.scrubString(jotEvent.getFirstName());
        if (firstName == null)
        {
            //super.addNonEditableMissingActionError(FIRST_NAME_FIELD, errors);
            super.setUnrecoverableError(true);
        }
        else if (this.validateStringLength(firstName, FIRSTNAME_MAX_LENGTH))
        {
            form.setFirstName(firstName);
        }
        else
        {
            //super.addFieldExceedsLengthActionError(FIRST_NAME_FIELD, jotEvent.getFirstName(), FIRSTNAME_MAX_LENGTH, errors);
            super.setUnrecoverableError(true);
        }

        String lastName = this.scrubString(jotEvent.getLastName());
        if (lastName == null)
        {
            super.addNonEditableMissingActionError(LAST_NAME_FIELD, errors);
            super.setUnrecoverableError(true);
        }
        else if (this.validateStringLength(lastName, LASTNAME_MAX_LENGTH))
        {
            form.setLastName(lastName);
        }
        else
        {
            super.addFieldExceedsLengthActionError(LAST_NAME_FIELD, jotEvent.getLastName(), LASTNAME_MAX_LENGTH, errors);
            super.setUnrecoverableError(true);
        }
    }

    private String formatPhone(String number)
    {
        PhoneNumber phone = new PhoneNumber(number);
        return phone.getFormattedPhoneNumber();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ui.action.AbstractFormDirector#getForm()
     */
    public ActionForm getForm()
    {
        // TODO Auto-generated method stub
        return this.form;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ui.action.AbstractFormDirector#initFromSession(javax.servlet.http.HttpServletRequest)
     */
    public void initFromSession(HttpServletRequest aRequest)
    {
        Object formObj = aRequest.getSession().getAttribute(SessionAttributeNames.JUVENILE_WARRANT_FORM);
        if (formObj == null)
        {
            formObj = new JuvenileWarrantForm();
        }
        this.form = (JuvenileWarrantForm) formObj;
    }

    private String scrubString(String value)
    {
        if (value != null)
        {
            value = value.trim();
            if (value.equals(""))
            {
                value = null;
            }
        }
        return value;
    }

    /**
     * @param responseEvent
     */
    public void setActivatedWarrantProperties(CompositeResponse response)
    {
        JuvenileWarrantResponseEvent responseEvent = (JuvenileWarrantResponseEvent) MessageUtil.filterComposite(response,
                JuvenileWarrantResponseEvent.class);
        this.form.setWarrantActivationStatusId(responseEvent.getWarrantActivationStatusId());
        this.form.setWarrantSignedStatusId(responseEvent.getWarrantSignedStatusId());
        this.form.setWarrantStatusId(responseEvent.getWarrantStatusId());
        this.form.setWarrantActivationDate(new Date());
    }

    public void setAssociateAddresses(CompositeResponse aResponse)
    {
        Collection addresses = MessageUtil.compositeToCollection(aResponse, JuvenileAssociateAddressResponseEvent.class);
        this.form.setAssociateServiceAddresses(addresses);
    }

    public void setAssociates(CompositeResponse aResponse)
    {
        List associates = new ArrayList();
        Collection warrantAssociates = MessageUtil.compositeToCollection(aResponse, JuvenileAssociateResponseEvent.class);
        Iterator i = warrantAssociates.iterator();
        while (i.hasNext())
        {
            JuvenileAssociateResponseEvent other = (JuvenileAssociateResponseEvent) i.next();
            JuvenileAssociateBean otherbean = new JuvenileAssociateBean();
            otherbean.populateFromEventAttributes(other);
            associates.add(otherbean);
        }
        this.form.setAssociates(associates);
    }

    public void setJJSProperties(CompositeResponse response, ActionErrors errors)
    {
        JuvenileJusticeSystemResponseEvent jjsEvent = (JuvenileJusticeSystemResponseEvent) MessageUtil.filterComposite(response,
                PDJuvenileWarrantConstants.JUVENILE_JUSTICE_SYSTEM_EVENT_TOPIC);

        String searchCriteria = "none";
        if (PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equals(form.getWarrantTypeId()))
        {
            searchCriteria = "petition number=" + form.getPetitionNum() + ", referral number=" + form.getReferralNum();
        }
        else if (PDJuvenileWarrantConstants.WARRANT_TYPE_VOP.equals(form.getWarrantTypeId()))
        {
            searchCriteria = "juvenile number=" + form.getJuvenileNum() + ", referral number=" + form.getReferralNum();
        }

        this.checkJJSRequiredNoneditableErrors(jjsEvent, errors);
        this.checkJJSOptionalNoneditableErrors(jjsEvent, errors);

        // Validate editable fields
        form.setJuvenileNum(jjsEvent.getJuvenileNumber());
        form.setProbationOfficerOfRecordId(jjsEvent.getProbationOfficerOfRecordId());
        IName name = jjsEvent.getProbationOfficerOfRecordName();
        if (name != null)
        {
            form.setProbationOfficerOfRecordName(name.getFormattedName());
        }

        form.setDateOfBirth(jjsEvent.getDateOfBirth());

        String ssnString = this.scrubString(jjsEvent.getJuvenileSSN());
        SocialSecurity ssn = new SocialSecurity(ssnString);
        if (ssnString == null || ssn.isValid())
        {
            form.setSsn(ssn);
        }
        else
        {
            this.addEditableActionError(SSN_FIELD, jjsEvent.getJuvenileSSN(), errors);
        }

        Name juvName = new Name();
        juvName.setFirstName(jjsEvent.getJuvenileFirstName());
        juvName.setMiddleName(jjsEvent.getJuvenileMiddleName());
        juvName.setLastName(jjsEvent.getJuvenileLastName());
        form.setJuvenileName(juvName);

        // Fields not needing validation

        form.setAliasName(jjsEvent.getAliasName());
        form.setRaceId(jjsEvent.getRaceId());
        form.setSexId(jjsEvent.getSexId());

        form.setSchoolDistrictId(StringUtils.stripStart( jjsEvent.getSchoolDistrictId(), "0" ));
        form.setSchoolDistrictName(jjsEvent.getSchoolDistrict());
        form.setSchoolCodeId(StringUtils.stripStart( jjsEvent.getSchoolCodeId(), "0" ));
        form.setSchoolName(jjsEvent.getSchoolName());

        List charges = MessageUtil.compositeToList(response, ChargeResponseEvent.class);

        // ER JIMS200024013 Sort in order of descending severity level
        Collections.sort(charges);
        form.setCharges(charges);

        Iterator i = charges.iterator();
        if (i.hasNext())
        {
            PetitionResponseEvent petitionEvent = (PetitionResponseEvent) i.next();
            form.setReferralNum(petitionEvent.getReferralNum());
            form.setPetitionNum(petitionEvent.getPetitionNum());
        }

        if (form.getCharges().size() == 1)
        {
            form.setOnlyCharge(true);
        }

        // Associate information
        List associates = UIJuvenileWarrantHelper.getAssociates(jjsEvent.getJuvenileNumber()); //UIJuvenileWarrantHelper.filterAssociates(response);
        form.setAssociates(associates);

        form.setWarrantOriginatorId(UIUtil.getCurrentUserID());
        form.setWarrantOriginatorName(UIUtil.getCurrentUserName());
        form.setWarrantOriginatorAgencyName(UIUtil.getCurrentUserDepartmentName());        
    }

    public void setJOTProperties(CompositeResponse aResponse, ActionErrors errors)
    {
        JuvenileOffenderTrackingResponseEvent jotEvent = (JuvenileOffenderTrackingResponseEvent) MessageUtil.filterComposite(
                aResponse, JuvenileOffenderTrackingResponseEvent.class);

        if (jotEvent != null)
        {
            // TODO Provide a better abstraction for validating noneditable
            // fields -- use strategy pattern

            this.checkJOTRequiredNoneditableErrors(jotEvent, errors);
            this.checkJOTOptionalNoneditableErrors(jotEvent, errors);

            if (this.hasUnrecoverableError() == false)
            {
                // Validate editable fields
                String heightString = this.scrubString(jotEvent.getHeight());
                try
                {
                    LengthBean lengthBean = new LengthBean();
                    lengthBean.setLength(heightString, true);
                    form.setHeightFeet(lengthBean.getFeetString());
                    form.setHeightInch(lengthBean.getInchesString());
                } catch (Exception e)
                {
                    if (heightString != null)
                    {
                        this.addEditableActionError(HEIGHT_FIELD, jotEvent.getHeight(), errors);
                    }
                    form.setHeightFeet(null);
                    form.setHeightInch(null);
                }

                String phoneString = this.scrubString(jotEvent.getPhoneNum());
                PhoneNumber phone = new PhoneNumber(phoneString);
                if (phoneString == null || phone.isValid())
                {
                    form.setPhoneNum(phone);
                }
                else
                {
                    this.addEditableActionError(JUVENILE_PHONE_FIELD, jotEvent.getPhoneNum(), errors);
                }

                String ssnString = this.scrubString(jotEvent.getSsn());
                SocialSecurity ssn = new SocialSecurity(ssnString);
                if (ssnString == null || ssn.isValid())
                {
                    form.setSsn(ssn);
                }
                else
                {
                    this.addEditableActionError(SSN_FIELD, jotEvent.getSsn(), errors);
                }

                if (jotEvent.getWeight() != null && jotEvent.getWeight().equals("") == false)
                {
                    try
                    {
                        Integer.parseInt(jotEvent.getWeight());
                        form.setWeight(jotEvent.getWeight());
                    } catch (NumberFormatException e)
                    {
                        this.addEditableActionError(WEIGHT_FIELD, jotEvent.getWeight(), errors);
                        form.setWeight(null);
                    }
                }

                // Fields not needing validation
                Date DOB = null;
        	try
        	{
        	    if ( jotEvent.getDateOfBirth() > 0)
        		DOB = DateUtil.IntToDate(jotEvent.getDateOfBirth(), DateUtil.DATE_FMT_2);
        	}
        	catch (ParseRuntimeException e)
        	{
        	   // e.printStackTrace();
        	}
                form.setDateOfBirth( DOB );
                form.setEyeColorId(jotEvent.getEyeColorId());
                form.setHairColorId(jotEvent.getHairColorId());
                form.setComplexionId(jotEvent.getComplexionId());
                form.setSexId(jotEvent.getSexId());

                Name name = new Name();
                name.setFirstName(jotEvent.getFirstName());
                name.setLastName(jotEvent.getLastName());
                name.setMiddleName(jotEvent.getMiddleName());
                form.setJuvenileName(name);

                form.setAliasName(jotEvent.getAliasName());
                form.setRaceId(jotEvent.getRaceId());
                form.setBuildId(jotEvent.getBuildId());
                form.setTransactionNum(jotEvent.getTransactionNum());
                form.setJuvenileNum(jotEvent.getJuvenileNum());
                form.setFbiNum(jotEvent.getFbiNum());
                form.setStateIdNumber(jotEvent.getSid());

                form.setSchoolDistrictId(jotEvent.getSchoolDistrictId());
                form.setSchoolDistrictName(jotEvent.getSchoolDistrict());
                form.setSchoolCodeId(jotEvent.getSchoolCodeId());
                form.setSchoolName(jotEvent.getSchoolName());
            }
        }

        List charges = MessageUtil.compositeToList(aResponse, PetitionResponseEvent.class);
        form.setCharges(charges);

        if (form.getCharges() != null)
        {
            if (form.getCharges().size() == 1)
            {
                form.setOnlyCharge(true);
            }
        }

        SummaryOfFactsResponseEvent summaryEvent = (SummaryOfFactsResponseEvent) MessageUtil.filterComposite(aResponse, SummaryOfFactsResponseEvent.class);
        if (summaryEvent != null)
        {
            List facts = summaryEvent.getSummaryOfFacts();
            form.setSummaryOfFacts(facts);

            String summOfFactsSize = "None";
            if (facts != null)
            {
                if (facts.size() > 4)
                {
                    summOfFactsSize = "Partial";
                }
                else
                {
                    summOfFactsSize = "Full";
                }
            }

            form.setSummOfFactsDisplaySize(summOfFactsSize);
        }

        // TODO Move away from static helper methods
        List associates = UIJuvenileWarrantHelper.filterAssociates(aResponse);
        form.setAssociates(associates);

        // get current User Info and set on form for WarrantOriginator
        form.setWarrantOriginatorId(UIUtil.getCurrentUserID());
        form.setWarrantOriginatorName(UIUtil.getCurrentUserName());
        form.setWarrantOriginatorAgencyName(UIUtil.getCurrentUserDepartmentName());
    }

    /**
     * @param response
     */
    public void setProperties(CompositeResponse aResponse)
    {
        JuvenileWarrantResponseEvent warrantEvent = (JuvenileWarrantResponseEvent) MessageUtil.filterComposite(aResponse,
                JuvenileWarrantResponseEvent.class);

        form.setWarrantNum(warrantEvent.getWarrantNum());

        form.setDateOfBirth(warrantEvent.getDateOfBirth());
        form.setDateOfBirthSource(warrantEvent.getDateOfBirthSource());

        form.setAffidavitStatement(warrantEvent.getAffadivitStatement());

        form.setWarrantTypeId(warrantEvent.getWarrantTypeId());

        form.setDaLogNum(warrantEvent.getDaLogNum());

        form.setWarrantStatusId(warrantEvent.getWarrantStatusId());

        form.setFileStampDate(warrantEvent.getFileStampDate());
        form.setFileStampLogonId(warrantEvent.getFileStampLogonId());
        form.setFileStampFirstName(warrantEvent.getFileStampFirstName());
        form.setFileStampMiddleName(warrantEvent.getFileStampMiddleName());
        form.setFileStampLastName(warrantEvent.getFileStampLastName());

        form.setWarrantAcknowledgementDate(warrantEvent.getWarrantAcknowledgementDate());
        form.setWarrantAcknowledgeStatusId(warrantEvent.getWarrantAcknowledgeStatusId());

        form.setWarrantActivationDate(warrantEvent.getWarrantActivationDate());

        form.setWarrantActivationStatusId(warrantEvent.getWarrantActivationStatusId());

        form.setRecallReasonId(warrantEvent.getRecallReasonId());

        form.setRecallDate(warrantEvent.getRecallDate());

        form.setDateOfIssue(warrantEvent.getDateOfIssue());

        form.setJuvenileNum(warrantEvent.getJuvenileNum());
        form.setJuvRectype(warrantEvent.getJuvRectype());
        form.setReferralNum(warrantEvent.getReferralNumber());
        form.setTransactionNum(warrantEvent.getTransactionNum());
        form.setProbationOfficerOfRecordName(warrantEvent.getProbationOfficerOfRecordName());

        Name juvenileName = new Name(warrantEvent.getFirstName(), warrantEvent.getMiddleName(), warrantEvent.getLastName());
        form.setJuvenileName(juvenileName);

        form.setAliasName(warrantEvent.getAliasName());
        form.setBuildId(warrantEvent.getBuildId());

        SocialSecurity ssn = new SocialSecurity(warrantEvent.getSsn());
        form.setSsn(ssn);

        PhoneNumber phone = new PhoneNumber(warrantEvent.getPhoneNum());
        form.setPhoneNum(phone);

        form.setRaceId(warrantEvent.getRaceId());
        form.setSexId(warrantEvent.getSexId());

        if (warrantEvent.getHeight() != null)
        {
            LengthBean lengthBean = new LengthBean();
            lengthBean.setLength(warrantEvent.getHeight(), false);
            form.setHeightFeet(lengthBean.getFeetString());
            form.setHeightInch(lengthBean.getInchesString());
        }

        form.setWeight(warrantEvent.getWeight());
        form.setEyeColorId(warrantEvent.getEyeColorId());
        form.setHairColorId(warrantEvent.getHairColorId());
        form.setComplexionId(warrantEvent.getComplexionId());

        form.setFbiNum(warrantEvent.getFbiNum());

        form.setSid(warrantEvent.getSid());
        form.setSchoolDistrictId(warrantEvent.getSchoolDistrictId());
        form.setSchoolDistrictName(warrantEvent.getSchoolDistrict());
        form.setSchoolCodeId(warrantEvent.getSchoolId());
        form.setSchoolName(warrantEvent.getSchoolName());

        List charges = MessageUtil.compositeToList(aResponse, ChargeResponseEvent.class);
        //Charge oneCharge = Charge.find("warrantNum", warrantEvent.getWarrantNum());
        
        // ER JIMS200024013 Sort in order of descending severity level
        Collections.sort(charges);
        form.setCharges(charges);
        
        GetJuvenileWarrantChargeEvent req = new GetJuvenileWarrantChargeEvent();
        req.setWarrantNum(warrantEvent.getWarrantNum());
        ChargeResponseEvent chargeResp = (ChargeResponseEvent) MessageUtil.postRequest(req, ChargeResponseEvent.class);
        if( chargeResp!= null ){
            
            form.setSelectedCharge(chargeResp.getSequenceNum());
            form.setChargeCodeId(chargeResp.getOffenseCodeId());
            form.setChargeSeqNum(chargeResp.getSequenceNum());
            form.setPetitionNum(chargeResp.getPetitionNum());
            form.setChargeCourt(chargeResp.getCourt());
            form.setChargeDescription(chargeResp.getOffense());
       
        }
        String[] selectedCharges = warrantEvent.getCharges();
          
       /* String[] selectedCharges = warrantEvent.getCharges();
        form.setCharges(charges);
        if (charges != null)
        {
            int chargeCount = charges.size();
            selectedCharges = new String[chargeCount];
            if (chargeCount > 0)
            {
                Iterator i = charges.iterator();
                int index = 0;
                while (i.hasNext())
                {
                    ChargeResponseEvent charge = (ChargeResponseEvent) i.next();
                    selectedCharges[index] = charge.getSequenceNum();
                    index++;
                    if (chargeCount == 1)
                    {
                        form.setSelectedCharge(charge.getSequenceNum());
                        form.setChargeCodeId(charge.getOffenseCodeId());
                        form.setChargeSeqNum(charge.getSequenceNum());
                        
                        form.setChargeCourt(charge.getCourt());
                        form.setChargeDescription(charge.getOffense());
                    }
                    form.setPetitionNum(charge.getPetitionNum());
                }
            }
        }*/

        if ( selectedCharges != null && selectedCharges.length > 0)
        {
            form.setSelectedCharge(selectedCharges[0]);
            form.setSelectedCharges(selectedCharges);
            form.setOriginalCharge(selectedCharges[0]);
        }

        form.setSelectedScars(warrantEvent.getScarsMarks());
        form.setSelectedTattoos(warrantEvent.getTattoos());
        form.setSelectedCautions(warrantEvent.getCautions());

        form.setOriginalCharges(selectedCharges);
        form.setOriginalScars(warrantEvent.getScarsMarks());
        form.setOriginalTattoos(warrantEvent.getTattoos());
        form.setOriginalCautions(warrantEvent.getCautions());       
        
        form.setCautionComments(warrantEvent.getOtherCautionComments());

        form.setWarrantSignedStatusId(warrantEvent.getWarrantSignedStatusId());
        form.setSignatureCommandId(warrantEvent.getWarrantSignedStatusId());

        // Warrant Originator Info
        form.setCourtId(warrantEvent.getWarrantOriginatorCourt());
        form.setWarrantOriginatorJudge(warrantEvent.getWarrantOriginatorJudge());
        if (PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equals(warrantEvent.getWarrantTypeId()))
        {
            form.setWarrantOriginatorName(warrantEvent.getWarrantOriginatorJudge());
        }
        else
        {
            form.setWarrantOriginatorId(warrantEvent.getWarrantOriginatorId());
            form.setWarrantOriginatorName(warrantEvent.getWarrantOriginatorName());
            form.setWarrantOriginatorAgencyId(warrantEvent.getWarrantOriginatorAgencyId());
            form.setWarrantOriginatorAgencyName(warrantEvent.getWarrantOriginatorAgencyName());
        }

        form.setOfficerId(warrantEvent.getOfficerId()); // release properties
        form.setReleaseDecisionId(warrantEvent.getReleaseDecisionId());
        form.setReleaseDecisionLogonId(warrantEvent.getReleaseOfficerLogonId());
        form.setReleaseAssociateNum(warrantEvent.getReleaseAssociateNum());
        form.setTransferLocationId(warrantEvent.getTransferLocationId());

        form.setReleaseDecisionDate(warrantEvent.getReleaseDecisionDate());

        form.setTransferCustodyDate(warrantEvent.getTransferDate());
        form.setTransferOfficerId(warrantEvent.getTransferOfficerId());

        form.setUnsendNotSignedReason(warrantEvent.getUnsendNotSignedReason());

        form.setServiceReturnSignatureStatusId(warrantEvent.getServiceReturnSignatureStatusId());
        form.setServiceReturnSignatureStatus(warrantEvent.getServiceReturnSignatureStatus());
        form.setServiceReturnGeneratedStatusId(warrantEvent.getServiceReturnGeneratedStatusId());
        form.setServiceReturnGeneratedStatus(warrantEvent.getServiceReturnGeneratedStatus());

        OfficerProfileResponseEvent ore = (OfficerProfileResponseEvent) MessageUtil.filterComposite(aResponse,
                OfficerProfileResponseEvent.class);
        form.setOfficerProperties(ore);

        List associates = UIJuvenileWarrantHelper.getAssociates(warrantEvent.getJuvenileNum().toString());
        
        this.form.setAssociates(associates);
        
      //get jp officer info
        OfficerProfile jpOfficer = UIJuvenileWarrantHelper.GetOfficerByWarrantId(warrantEvent.getWarrantNum());
        if(jpOfficer != null && (jpOfficer.getEmail() != null || !jpOfficer.equals(""))){
            this.form.setJpOfficerEmail(jpOfficer.getEmail());
        }
        

        this.setWarrantServiceInfo(aResponse);
    }

    

    public void setWarrantServiceInfo(CompositeResponse aResponse)
    {
        Collection services = MessageUtil.compositeToCollection(aResponse, JuvenileWarrantServiceResponseEvent.class);

        Collection unsuccessfulServices = new ArrayList();

        Iterator i = services.iterator();

        while (i.hasNext())
        {
            JuvenileWarrantServiceResponseEvent response = (JuvenileWarrantServiceResponseEvent) i.next();

            String serviceStatus = response.getServiceStatusId();

            if (PDJuvenileWarrantConstants.WARRANT_SERVICE_SUCCESSFUL.equals(serviceStatus))
            {
                form.setWarrantServiceProperties(response);
            }
            else
            {
                response.setExecutorPhoneNum(formatPhone(response.getExecutorPhoneNum()));
                response.setExecutorCellNum(formatPhone(response.getExecutorCellNum()));
                response.setExecutorPager(formatPhone(response.getExecutorPager()));
                unsuccessfulServices.add(response);
            }
        }

        form.setServices(unsuccessfulServices);
    }

    private boolean validateStringLength(String value, int length)
    {
        if (value != null && value.length() <= length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

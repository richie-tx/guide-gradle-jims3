package pd.juvenilewarrant.transactions;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import naming.PDCodeTableConstants;
import naming.PDJuvenileWarrantConstants;
import naming.PDNotificationConstants;
import pd.codetable.Code;
import pd.codetable.person.ScarsMarksTattoosCode;
import pd.juvenilewarrant.Charge;
import pd.juvenilewarrant.JuvenileAssociate;
import pd.juvenilewarrant.JuvenileAssociateAddress;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import pd.juvenilewarrant.helper.JuvenileWarrantWorker;
import messaging.juvenilewarrant.reply.ActiveWarrantErrorEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import pd.km.util.AuthHelper;
import messaging.address.AddressRequestEvent;
import messaging.juvenilewarrant.ChargeRequestEvent;
import messaging.juvenilewarrant.JuvenileAssociateRequestEvent;
import messaging.juvenilewarrant.CreateJuvenileWarrantEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.messaging.Composite.CompositeRequest;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;

/**
 * @author ryoung
 * 
 */
public class CreateJuvenileWarrantCommand implements ICommand
{
    /**
     * @roseuid 416C3839019C
     */
    public CreateJuvenileWarrantCommand()
    {
    }

    /**
     * @param event
     * @roseuid 41658CBC009E
     */
    public void execute(IEvent event)
    {
        CreateJuvenileWarrantEvent createJWEvent = (CreateJuvenileWarrantEvent) event;

        boolean isJJSWarrant = this.isJJS(createJWEvent);

        String petitionNum = this.getPetitionNum(createJWEvent, isJJSWarrant);
        String daLogNum = this.getDaLogNum(createJWEvent, isJJSWarrant);

        // Check to see if an active warrant exists for this juvenile
        boolean activeWarrantExists = PDJuvenileWarrantHelper.findExistingWarrant(createJWEvent.getJuvenileNum(), String
                .valueOf(daLogNum), createJWEvent.getDateOfBirth(), createJWEvent.getFirstName(), createJWEvent.getLastName(),
                createJWEvent.getReferralNum(), petitionNum);

        if (activeWarrantExists == true)
        {
            ActiveWarrantErrorEvent error = new ActiveWarrantErrorEvent();
            error.setMessage("There already exists an active Juvenile Warrant for this juvenile: "
                    + createJWEvent.getJuvenileNum());
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(error);
        }
        else
        {
            JuvenileWarrant warrant = this.initiateWarrant(createJWEvent, isJJSWarrant);

            this.sendWarrant(warrant);
        }
    }

    /**
     * @param warrant
     */
    private void sendWarrant(JuvenileWarrant warrant)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        JuvenileWarrantResponseEvent response = new JuvenileWarrantResponseEvent();
        response.setWarrantNum(warrant.getWarrantNum());
        response.setFileStampDate(warrant.getFileStampDate());
        dispatch.postEvent(response);

        // Retrieve associates to make associateId assessible to the UI
        Collection associates = warrant.getJuvenileAssociates();

        for (Iterator iter = associates.iterator(); iter.hasNext();)
        {
            JuvenileAssociate associate = (JuvenileAssociate) iter.next();
            ResponseEvent jare = PDJuvenileWarrantHelper.getJuvenileAssociateResponseEvent(associate);
            dispatch.postEvent(jare);
        }

    }

    /**
     * @param createJWEvent
     * @return
     */
    private boolean isJJS(CreateJuvenileWarrantEvent createJWEvent)
    {
        return (PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equals(createJWEvent.getWarrantType()) || (PDJuvenileWarrantConstants.WARRANT_TYPE_VOP
                .equals(createJWEvent.getWarrantType())));
    }

    private JuvenileWarrant initiateWarrant(CreateJuvenileWarrantEvent createJWEvent, boolean isJJSWarrant)
    {

        JuvenileWarrant warrant = new JuvenileWarrant();

        setJuvenileWarrantFields(createJWEvent, warrant);
        // Execute the bind after the parent fields have been set to get the
        // warrant number to add to the children
        IHome home = new Home();
        home.bind(warrant);

        this.setChildFields(createJWEvent, warrant);

        // Send Notifications - Each warrant type will have its own
        // implementation of sentNotification
        JuvenileWarrantWorker worker = new JuvenileWarrantWorker();
        worker.sendNotification(warrant, PDNotificationConstants.CREATE_WARRANT);

        return warrant;
    }

    /**
     * @param createJWEvent
     * @return
     */
    private String getDaLogNum(CreateJuvenileWarrantEvent createJWEvent, boolean isJJSWarrant)
    {
        String daLogNum = "";

        if (isJJSWarrant == false)
        {
            daLogNum = createJWEvent.getDaLogNum();
        }

        return daLogNum;
    }

    /**
     * @param createJWEvent
     * @return
     */
    private String getPetitionNum(CreateJuvenileWarrantEvent createJWEvent, boolean isJJSWarrant)
    {
        String petitionNum = "";
        if (isJJSWarrant == true)
        {
            ChargeRequestEvent charge = (ChargeRequestEvent) MessageUtil.filterComposite(createJWEvent, ChargeRequestEvent.class);
            if (charge != null)
            {
                petitionNum = charge.getPetitionNum();
            }
        }

        return petitionNum;
    }

    private void setJuvenileWarrantFields(CreateJuvenileWarrantEvent anEvent, JuvenileWarrant juvenileWarrant)
    {
        juvenileWarrant.setAffidavitStatement(anEvent.getStatement());
        juvenileWarrant.setAliasName(anEvent.getAliasName());
        juvenileWarrant.setBuildId(anEvent.getBuild());
        juvenileWarrant.setComplexionId(anEvent.getComplexion());
        if (anEvent.getDaLogNum() != null && !anEvent.getDaLogNum().equals(""))
        {
            juvenileWarrant.setDaLogNumber(Integer.parseInt(anEvent.getDaLogNum()));
        }
        juvenileWarrant.setDateOfBirth(anEvent.getDateOfBirth());
        juvenileWarrant.setDateOfBirthSource(anEvent.getDateOfBirthSource());

        /*
         * Set dateOfIssue to current date. There is a trigger at the database
         * level also, but we need to set the date here so that it is present in
         * the juvenile warrant object when the warrant originator info is
         * updated for OIC and VOP warrants. Otherwise the dateOfIssue gets
         * wiped out.
         */
        juvenileWarrant.setDateOfIssue(DateUtil.getCurrentDate());
        juvenileWarrant.setEyeColorId(anEvent.getEyeColor());
        juvenileWarrant.setFbiNum(anEvent.getFbiNum());
        juvenileWarrant.setFirstName(anEvent.getFirstName());
        juvenileWarrant.setHairColorId(anEvent.getHairColor());
        juvenileWarrant.setHeight(anEvent.getHeight());
        juvenileWarrant.setJuvenileNum(anEvent.getJuvenileNum());
        juvenileWarrant.setLastName(anEvent.getLastName());
        juvenileWarrant.setMiddleName(anEvent.getMiddleName());
        juvenileWarrant.setOfficerId(anEvent.getOfficerId());
        juvenileWarrant.setOfficerDepartmentId(anEvent.getOfficerDepartmentId());
        juvenileWarrant.setOtherCautionComments(anEvent.getComments());
//        juvenileWarrant.setPhoneNum(anEvent.getPhone());
        juvenileWarrant.setProbationOfficerOfRecordId(anEvent.getProbationOfficerOfRecordId());
        juvenileWarrant.setRaceId(anEvent.getRace());
        juvenileWarrant.setReferralNum(anEvent.getReferralNum());

        if (anEvent.getSchoolDistrict() != null)
        {
            String district = pd.km.util.Formatter.pad(anEvent.getSchoolDistrict(), 3, '0', true);
            String schoolCode = pd.km.util.Formatter.pad(anEvent.getSchoolName(), 3, '0', true);
            juvenileWarrant.setSchoolCodeId(district + schoolCode);
        }
        juvenileWarrant.setSexId(anEvent.getSex());
        juvenileWarrant.setSidNum(anEvent.getSidNum());
        juvenileWarrant.setSsn(anEvent.getSsn());
        juvenileWarrant.setSuffix(anEvent.getNameSuffix());
        if (anEvent.getTransactionNum() != null && !anEvent.getTransactionNum().equals(""))
        {
            juvenileWarrant.setTransactionNum(Integer.parseInt(anEvent.getTransactionNum()));
        }
        juvenileWarrant.setWarrantActivationDate(anEvent.getWarrantActivationDate());
        juvenileWarrant.setWarrantActivationStatusId(anEvent.getWarrantActivationStatus());

        juvenileWarrant.setWarrantSignedStatusId(anEvent.getWarrantSignedStatus());
        juvenileWarrant.setWarrantStatusId(anEvent.getStatus());
        juvenileWarrant.setWarrantTypeId(anEvent.getWarrantType());

        if (PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equals(anEvent.getWarrantType()))
        {
            juvenileWarrant.setWarrantOriginatorCourt(anEvent.getCourt());
            juvenileWarrant.setWarrantOriginatorName(anEvent.getJudgeName());
            juvenileWarrant.setWarrantOriginatorUserId(anEvent.getWarrantOriginatorId());
        }
        else
        {
            juvenileWarrant.setWarrantOriginatorUserId(anEvent.getWarrantOriginatorId());
            juvenileWarrant.setWarrantOriginatorName(anEvent.getWarrantOriginatorName());
        }

        if (anEvent.getWeight() != null && !anEvent.getWeight().equals(""))
        {
            juvenileWarrant.setWeight(Integer.parseInt(anEvent.getWeight()));
        }

        // Default to PENDING status for Initiated Juvenile Warrants
        juvenileWarrant.setWarrantStatusId(PDJuvenileWarrantConstants.WARRANT_STATUS_PENDING);

        if (juvenileWarrant.getWarrantTypeId().equals(PDJuvenileWarrantConstants.WARRANT_TYPE_PCW))
        {
            // post-condition of probable cause warrant.
            juvenileWarrant.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE);
            juvenileWarrant.setWarrantStatusId(PDJuvenileWarrantConstants.WARRANT_STATUS_OPEN);
            juvenileWarrant.setWarrantActivationDate(new java.util.Date());
        }
        else
        {
            juvenileWarrant.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE);
        }

        if (anEvent.getWarrantType().equals(PDJuvenileWarrantConstants.WARRANT_TYPE_PCW))
        {
            juvenileWarrant.setWarrantSignedStatusId(PDJuvenileWarrantConstants.WARRANT_SIGNED);
        }
        else
        {
            juvenileWarrant.setWarrantSignedStatusId(PDJuvenileWarrantConstants.WARRANT_NOT_SIGNED);
        }

        if (anEvent.getWarrantType().equals(PDJuvenileWarrantConstants.WARRANT_TYPE_ARR)
                || anEvent.getWarrantType().equals(PDJuvenileWarrantConstants.WARRANT_TYPE_PCW))
        {
            juvenileWarrant.setWarrantAcknowledgementDate(new java.util.Date());
            juvenileWarrant.setWarrantAcknowledgeStatusId(PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_PRINTED);
        }
        else
        {
            juvenileWarrant.setWarrantAcknowledgeStatusId(PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_NOT_PRINTED);
        }
        juvenileWarrant.setFileStampDate(DateUtil.getCurrentDate());
        juvenileWarrant.setFileStampUserId(AuthHelper.getCurrentUser());
    }

    /**
     * Sets the values from the UpdateJuvenileWarrantEvent to JuvenileAssociate
     * 
     * @param updateJuvenileWarrantEvent
     * @param juvenileWarrant
     * @param isUpdate
     */
    private void setChildFields(CreateJuvenileWarrantEvent anEvent, JuvenileWarrant juvenileWarrant)
    {
        // If this is a create (not update), add the juvenile associates.
        // Updates to the juvenile warrant do not affect the associates,
        // this will be done throug the Juvenile Associate Use-Case

//        Map juvAssocMap = new Hashtable();
//        Map assocAddressMap = new Hashtable();

        //this.createJuvenileAssociateMap(anEvent, juvAssocMap);
        //this.createJuvenileAssociateAddressMap(anEvent, assocAddressMap);

        //Match up juvenile associates with their addresses and insert them on
        // the warrant
//        if (!juvAssocMap.isEmpty())
//        {
//            this.updateJuvenileAssociates(juvAssocMap, assocAddressMap, juvenileWarrant);
//        }

        this.updateCharge(anEvent, juvenileWarrant);

        this.updateCautions(anEvent, juvenileWarrant);

        this.updateScarsMarks(anEvent, juvenileWarrant);

        this.updateTattoos(anEvent, juvenileWarrant);
    }

    private void createJuvenileAssociateMap(CompositeRequest createJWEvent, Map juvAssocMap)
    {
        Collection juvAssociates = MessageUtil.compositeToCollection(createJWEvent, JuvenileAssociateRequestEvent.class);
        if (juvAssociates != null)
        {
            Iterator i = juvAssociates.iterator();
            while (i.hasNext())
            {
                JuvenileAssociateRequestEvent jaEvent = (JuvenileAssociateRequestEvent) i.next();
                JuvenileAssociate juvenileAssociate = new JuvenileAssociate();
                juvenileAssociate.setAssociateNum(jaEvent.getAssociateNum());
                juvenileAssociate.setDateOfBirth(jaEvent.getDateOfBirth());
                juvenileAssociate.setFirstName(jaEvent.getFirstName());
                juvenileAssociate.setLastName(jaEvent.getLastName());
                juvenileAssociate.setMiddleName(jaEvent.getMiddleName());
                juvenileAssociate.setRaceId(jaEvent.getRace());
                juvenileAssociate.setRelationshipToJuvenileId(jaEvent.getRelationshipToJuvenile());
                juvenileAssociate.setReleasedTo(jaEvent.getReleasedTo());
                juvenileAssociate.setSexId(jaEvent.getSex());
                juvenileAssociate.setSsn(jaEvent.getSsn());
                juvAssocMap.put(jaEvent.getTopic(), juvenileAssociate);
            }
        }
    }

    private void createJuvenileAssociateAddressMap(CompositeRequest createJWEvent, Map assocAddressMap)
    {
        JuvenileAssociateAddress associateAddress = null;
        Collection assocAddresses = MessageUtil.compositeToCollection(createJWEvent, AddressRequestEvent.class);
        if (assocAddresses != null)
        {
            Iterator i = assocAddresses.iterator();
            while (i.hasNext())
            {
                AddressRequestEvent aaEvent = (AddressRequestEvent) i.next();
                associateAddress = new JuvenileAssociateAddress();
                associateAddress.setAddress2(aaEvent.getAddress2());
                associateAddress.setAddressId(aaEvent.getAddressId());
                associateAddress.setAddressTypeId(aaEvent.getAddressType());
                associateAddress.setAptNum(aaEvent.getAptNum());
                associateAddress.setCity(aaEvent.getCity());
                associateAddress.setCountryId(aaEvent.getCountry());
                associateAddress.setCountyId(aaEvent.getCountyId());
                associateAddress.setKeymap(aaEvent.getKeymap());
                associateAddress.setStateId(aaEvent.getStateId());
                associateAddress.setStreetName(aaEvent.getStreetName());
                associateAddress.setStreetNum(aaEvent.getStreetNum());
                associateAddress.setStreetTypeId(aaEvent.getStreetTypeId());
                associateAddress.setZipCode(aaEvent.getZipCode());
                associateAddress.setAdditionalZipCode(aaEvent.getAdditionalZipCode());
                associateAddress.setAddressStatus(aaEvent.getAddressStatus());
                assocAddressMap.put(aaEvent.getTopic(), associateAddress);
            }
        }
    }

    private void updateJuvenileAssociates(Map juvAssocMap, Map assocAddressMap, JuvenileWarrant juvenileWarrant)
    {
        JuvenileAssociate juvAssociate = (JuvenileAssociate) juvAssocMap
                .get(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_FATHER_EVENT_TOPIC);
        JuvenileAssociateAddress juvAddress = (JuvenileAssociateAddress) assocAddressMap
                .get(PDJuvenileWarrantConstants.FATHER_ADDRESS_EVENT_TOPIC);
        IHome home = new Home();
        if (juvAssociate != null)
        {
            juvAssociate.setWarrantNum(juvenileWarrant.getWarrantNum());

            //Execute the bind after the parent fields have been set to get the
            // associate number to add to the children
            home.bind(juvAssociate);
            if (juvAddress != null)
            {
                juvAddress.setAssociateNum(juvAssociate.getAssociateNum());
                juvAssociate.insertAddresses(juvAddress);
            }
            juvenileWarrant.insertJuvenileAssociates(juvAssociate);
        }
        juvAssociate = (JuvenileAssociate) juvAssocMap.get(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_MOTHER_EVENT_TOPIC);
        juvAddress = (JuvenileAssociateAddress) assocAddressMap.get(PDJuvenileWarrantConstants.MOTHER_ADDRESS_EVENT_TOPIC);
        if (juvAssociate != null)
        {
            juvAssociate.setWarrantNum(juvenileWarrant.getWarrantNum());

            //Execute the bind after the parent fields have been set to get the
            // associate number to add to the children
            home.bind(juvAssociate);
            if (juvAddress != null)
            {
                juvAddress.setAssociateNum(juvAssociate.getAssociateNum());
                juvAssociate.insertAddresses(juvAddress);
            }
            juvenileWarrant.insertJuvenileAssociates(juvAssociate);
        }
        juvAssociate = (JuvenileAssociate) juvAssocMap.get(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_OTHER_EVENT_TOPIC);
        juvAddress = (JuvenileAssociateAddress) assocAddressMap.get(PDJuvenileWarrantConstants.OTHER_ADDRESS_EVENT_TOPIC);
        if (juvAssociate != null)
        {
            juvAssociate.setWarrantNum(juvenileWarrant.getWarrantNum());
            //Execute the bind after the parent fields have been set to get the
            // associate number to add to the children
            home.bind(juvAssociate);
            if (juvAddress != null)
            {
                juvAddress.setAssociateNum(juvAssociate.getAssociateNum());
                juvAssociate.insertAddresses(juvAddress);
            }
            juvenileWarrant.insertJuvenileAssociates(juvAssociate);
        }
        juvAssociate = (JuvenileAssociate) juvAssocMap.get(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_ALTER_EVENT_TOPIC);
        juvAddress = (JuvenileAssociateAddress) assocAddressMap.get(PDJuvenileWarrantConstants.ALTER_ADDRESS_EVENT_TOPIC);
        if (juvAssociate != null)
        {
            juvAssociate.setWarrantNum(juvenileWarrant.getWarrantNum());
            //Execute the bind after the parent fields have been set to get the
            // associate number to add to the children
            home.bind(juvAssociate);
            if (juvAddress != null)
            {
                juvAddress.setAssociateNum(juvAssociate.getAssociateNum());
                juvAssociate.insertAddresses(juvAddress);
            }
            juvenileWarrant.insertJuvenileAssociates(juvAssociate);
        }
    }

    private void updateCharge(CompositeRequest createJWEvent, JuvenileWarrant juvenileWarrant)
    {
        Collection charges = MessageUtil.compositeToCollection(createJWEvent, ChargeRequestEvent.class);
        if (charges != null)
        {
            Iterator i = charges.iterator();
            while (i.hasNext())
            {
                ChargeRequestEvent chargeEvent = (ChargeRequestEvent) i.next();
                Charge charge = new Charge();
                charge.setWarrantNum(juvenileWarrant.getWarrantNum());
                if (chargeEvent.getOffenseCodeId() == null)
                {
                    charge.setChargeDescription(chargeEvent.getChargeDescription());
                }
                else
                {
                    charge.setOffenseCodeId(chargeEvent.getOffenseCodeId());
                }
                charge.setCourtId(chargeEvent.getCourtId());
                charge.setPetitionNum(chargeEvent.getPetitionNum());
                charge.setSequenceNum(chargeEvent.getSequenceNum());
                charge.setOffenseDate(chargeEvent.getOffenseDate());
                juvenileWarrant.insertCharges(charge);
            }
        }
    }

    private void updateCautions(CreateJuvenileWarrantEvent anEvent, JuvenileWarrant juvenileWarrant)
    {
        String[] codes = anEvent.getCautions();
        if (codes != null && codes.length > 0)
        {
            int len = codes.length;
            for (int i = 0; i < len; i++)
            {
                Code code = Code.find(PDCodeTableConstants.CAUTIONS, codes[i]);
                if (code != null)
                {
                    juvenileWarrant.insertCautions(code);
                }
            }
        }
    }

    private void updateScarsMarks(CreateJuvenileWarrantEvent anEvent, JuvenileWarrant juvenileWarrant)
    {
        String[] codes = anEvent.getScarsMarks();
        if (codes != null && codes.length > 0)
        {
            int len = codes.length;
            for (int i = 0; i < len; i++)
            {
                ScarsMarksTattoosCode code = ScarsMarksTattoosCode.find(codes[i]);
                if (code != null)
                {
                    juvenileWarrant.insertScarsMarks(code);
                }
            }
        }
    }

    private void updateTattoos(CreateJuvenileWarrantEvent anEvent, JuvenileWarrant juvenileWarrant)
    {
        String[] codes = anEvent.getTattoos();
        if (codes != null && codes.length > 0)
        {
            int len = codes.length;
            for (int i = 0; i < len; i++)
            {
                ScarsMarksTattoosCode code = ScarsMarksTattoosCode.find(codes[i]);
                if (code != null)
                {
                    juvenileWarrant.insertTattoos(code);
                }
            }
        }
    }

    /**
     * @param event
     * @roseuid 41658CBC00A0
     */
    public void onRegister(IEvent event)
    {
    }

    /**
     * @param event
     * @roseuid 41658CBC00A2
     */
    public void onUnregister(IEvent event)
    {
    }

    /**
     * @param anObject
     * @roseuid 41658CBC00A4
     */
    public void update(Object anObject)
    {
    }
}
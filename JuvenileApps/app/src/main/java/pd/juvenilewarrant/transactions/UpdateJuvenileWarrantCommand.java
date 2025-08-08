package pd.juvenilewarrant.transactions;

import java.util.Iterator;

import naming.PDCodeTableConstants;
import naming.PDNotificationConstants;

import pd.codetable.Code;
import pd.codetable.person.ScarsMarksTattoosCode;
import pd.exception.InvalidWarrantTypeException;
import pd.juvenilewarrant.Charge;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.helper.JuvenileWarrantWorker;
import messaging.juvenilewarrant.ChargeRequestEvent;
import messaging.juvenilewarrant.UpdateJuvenileWarrantEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author glyons
 *  
 */
public class UpdateJuvenileWarrantCommand implements ICommand
{
    private static final String BLANK = "";

    /**
     * @roseuid 416C383601FA
     */
    public UpdateJuvenileWarrantCommand()
    {
    }

    /**
     * @param event
     * @roseuid 416BD36A0105
     */
    public void execute(IEvent event) throws InvalidWarrantTypeException
    {
        UpdateJuvenileWarrantEvent updateEvent = (UpdateJuvenileWarrantEvent) event;

        JuvenileWarrant warrant = JuvenileWarrant.find(updateEvent.getWarrantNum());

        this.setJuvenileWarrantFields(updateEvent, warrant);
        this.updateAssociations(updateEvent, warrant);

        //IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        //PDJuvenileWarrantHelper.postResponses(warrant.getChargeResponses());

        //Send Notifications
        JuvenileWarrantWorker worker = new JuvenileWarrantWorker();
        worker.sendNotification(warrant, PDNotificationConstants.UPDATE_WARRANT);
    }

    private void setJuvenileWarrantFields(UpdateJuvenileWarrantEvent event, JuvenileWarrant juvenileWarrant)
    {
        juvenileWarrant.setAffidavitStatement(event.getStatement());
        juvenileWarrant.setAliasName(event.getAliasName());
        juvenileWarrant.setBuildId(event.getBuild());
        juvenileWarrant.setComplexionId(event.getComplexion());
        if (event.getDaLogNum() != null)
        {
            juvenileWarrant.setDaLogNumber(Integer.parseInt(event.getDaLogNum()));
        }
        juvenileWarrant.setDateOfBirth(event.getDateOfBirth());
        juvenileWarrant.setEyeColorId(event.getEyeColor());
        juvenileWarrant.setFbiNum(event.getFbiNum());
        juvenileWarrant.setFirstName(event.getFirstName());
        juvenileWarrant.setHairColorId(event.getHairColor());
        juvenileWarrant.setHeight(event.getHeight());
        juvenileWarrant.setJuvenileNum(event.getJuvenileNum());
        juvenileWarrant.setLastName(event.getLastName());
        juvenileWarrant.setMiddleName(event.getMiddleName());
        juvenileWarrant.setOfficerId(event.getOfficerId());
        juvenileWarrant.setOtherCautionComments(event.getComments());
//        juvenileWarrant.setPhoneNum(event.getPhone());
        juvenileWarrant.setProbationOfficerOfRecordId(event.getProbationOfficerOfRecordId());
        juvenileWarrant.setRaceId(event.getRace());
        juvenileWarrant.setReferralNum(event.getReferralNum());

        String district = event.getSchoolDistrict();
        String schoolCode = event.getSchoolName();
        juvenileWarrant.setSchoolCodeId(district + schoolCode);

        juvenileWarrant.setSexId(event.getSex());
        juvenileWarrant.setSidNum(event.getSidNum());
        juvenileWarrant.setSsn(event.getSsn());
        juvenileWarrant.setSuffix(event.getNameSuffix());
        juvenileWarrant.setTransactionNum(Integer.parseInt(event.getTransactionNum()));
        juvenileWarrant.setWarrantAcknowledgementDate(event.getWarrantAcknowledgeDate());
        juvenileWarrant.setWarrantAcknowledgeStatusId(event.getWarrantAcknowledgeStatus());
        juvenileWarrant.setWarrantActivationDate(event.getWarrantActivationDate());
        juvenileWarrant.setWarrantActivationStatusId(event.getWarrantActivationStatus());
        juvenileWarrant.setWarrantOriginatorUserId(event.getWarrantOriginatorId());
        juvenileWarrant.setWarrantOriginatorName(event.getWarrantOriginatorName());
        juvenileWarrant.setWarrantSignedStatusId(event.getWarrantSignedStatus());
        juvenileWarrant.setWarrantStatusId(event.getStatus());
        juvenileWarrant.setWarrantTypeId(event.getWarrantType());
        if (event.getWeight() != null && event.getWeight().equals("") == false)
        {
            juvenileWarrant.setWeight(Integer.parseInt(event.getWeight()));
        }

        // Used for OIC Warrants
        juvenileWarrant.setWarrantOriginatorCourt(event.getWarrantOriginatorCourt());
        juvenileWarrant.setWarrantOriginatorName(event.getWarrantOriginatorName());
        //juvenileWarrant.setWarrantOriginatorJudge(event.getWarrantOriginatorJudgeName());
        
    }

    /**
     * @param event
     */
    private void replaceNullEventFieldsWithBlank(UpdateJuvenileWarrantEvent event)
    {
        if (event.getStatement() == null)
        {
            event.setStatement(BLANK);
        }
        if (event.getAliasName() == null)
        {
            event.setAliasName(BLANK);
        }
        if (event.getFirstName() == null)
        {
            event.setFirstName(BLANK);
        }
        if (event.getLastName() == null)
        {
            event.setLastName(BLANK);
        }
        if (event.getMiddleName() == null)
        {
            event.setMiddleName(BLANK);
        }
        if (event.getComments() == null)
        {
            event.setComments(BLANK);
        }
        if (event.getPhone() == null)
        {
            event.setPhone(BLANK);
        }
        if (event.getNameSuffix() == null)
        {
            event.setNameSuffix(BLANK);
        }
    }

    /**
     * Sets the values from the UpdateJuvenileWarrantEvent to JuvenileAssociate
     * 
     * @param updateJuvenileWarrantEvent
     * @param juvenileWarrant
     * @param isUpdate
     */
    private void updateAssociations(UpdateJuvenileWarrantEvent event, JuvenileWarrant juvenileWarrant)
    {
        //Update charges
        this.updateCharge(event, juvenileWarrant);

        //Update cautions
        this.updateCautions(event, juvenileWarrant);

        //Update scars marks
        this.updateScarsMarks(event, juvenileWarrant);

        //Update tattoos
        this.updateTattoos(event, juvenileWarrant);
    }

    /**
     *  
     */
    private void updateCharge(UpdateJuvenileWarrantEvent createJWEvent, JuvenileWarrant jw)
    {
        ChargeRequestEvent chargeEvent = (ChargeRequestEvent) MessageUtil
                .filterComposite(createJWEvent, ChargeRequestEvent.class);

        if (chargeEvent != null)
        {
            Iterator i = jw.getCharges().iterator();

            Charge charge = (Charge) i.next();

            charge.setWarrantNum(jw.getWarrantNum());
            charge.setOffenseCodeId(chargeEvent.getOffenseCodeId());
            charge.setCourtId(chargeEvent.getCourtId());
            charge.setPetitionNum(chargeEvent.getPetitionNum());
            charge.setSequenceNum(chargeEvent.getSequenceNum());
            if (chargeEvent.getSequenceNum() == null)
            {
                charge.setChargeDescription(chargeEvent.getChargeDescription());
            }
        }
    }

    /*
     * This method will examine what CautionRequestEvent have been posted. Any
     * that have been posted are the complete list of cautions for this
     * juvenile. This list should be compared against the existing cautions to
     * remove any that are no longer cautions and add ones that are new
     * cautions.
     */
    private void updateCautions(UpdateJuvenileWarrantEvent anEvent, JuvenileWarrant jw)
    {
        String[] cautions = anEvent.getCautions();
        if (cautions != null)
        {
            for (int i = 0; i < cautions.length; i++)
            {
                Code code = Code.find(PDCodeTableConstants.CAUTIONS, cautions[i]);
                jw.insertCautions(code);
            }

        }

        cautions = anEvent.getDeletedCautions();
        if (cautions != null)
        {
            for (int i = 0; i < cautions.length; i++)
            {
                Code code = Code.find(PDCodeTableConstants.CAUTIONS, cautions[i]);
                jw.removeCautions(code);
            }
        }
    }

    private void updateScarsMarks(UpdateJuvenileWarrantEvent anEvent, JuvenileWarrant jw)
    {
        String[] scarsMarks = anEvent.getScarsMarks();
        if (scarsMarks != null)
        {
            for (int i = 0; i < scarsMarks.length; i++)
            {
                ScarsMarksTattoosCode code = ScarsMarksTattoosCode.find(scarsMarks[i]);
                jw.insertScarsMarks(code);
            }

        }

        scarsMarks = anEvent.getDeletedScarsMarks();
        if (scarsMarks != null)
        {
            for (int i = 0; i < scarsMarks.length; i++)
            {
                ScarsMarksTattoosCode code = ScarsMarksTattoosCode.find(scarsMarks[i]);
                jw.removeScarsMarks(code);
            }
        }
    }

    private void updateTattoos(UpdateJuvenileWarrantEvent anEvent, JuvenileWarrant jw)
    {
        String[] tattoos = anEvent.getTattoos();
        if (tattoos != null)
        {
            for (int i = 0; i < tattoos.length; i++)
            {
                ScarsMarksTattoosCode code = ScarsMarksTattoosCode.find(tattoos[i]);
                jw.insertTattoos(code);
            }

        }

        tattoos = anEvent.getDeletedTattoos();
        if (tattoos != null)
        {
            for (int i = 0; i < tattoos.length; i++)
            {
                ScarsMarksTattoosCode code = ScarsMarksTattoosCode.find(tattoos[i]);
                jw.removeTattoos(code);
            }
        }
    }

    /**
     * @param event
     * @roseuid 416BD36A010F
     */
    public void onRegister(IEvent event)
    {
    }

    /**
     * @param event
     * @roseuid 416BD36A0111
     */
    public void onUnregister(IEvent event)
    {
    }

    /**
     * @param anObject
     * @roseuid 416BD36A0113
     */
    public void update(Object anObject)
    {
    }

}

/*
 * Created on Jul 18, 2005
 *
 */
package pd.juvenilewarrant;

import naming.PDJuvenileWarrantConstants;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.pattern.IBuilder;

/**
 * @author Jim Fisher
 *  
 */
public class JuvenileWarrantSimpleBuilder implements IBuilder
{
    private JuvenileWarrantResponseEvent responseEvent;

    private JuvenileWarrant warrant;

    /**
     *  
     */
    public JuvenileWarrantSimpleBuilder(JuvenileWarrant warrant)
    {
        this.warrant = warrant;
        this.responseEvent = new JuvenileWarrantResponseEvent();
    }

    /*
     * (non-Javadoc)
     * 
     * @see pd.pattern.IBuilder#build()
     */
    public void build()
    {
        // TODO Possibly remove this setTopic?
        responseEvent.setTopic(PDJuvenileWarrantConstants.JUVENILE_WARRANT_EVENT_TOPIC);
        responseEvent.setWarrantNum(warrant.getWarrantNum());
        responseEvent.setJuvenileNum(warrant.getJuvenileNum());
        responseEvent.setFirstName(warrant.getFirstName());
        responseEvent.setLastName(warrant.getLastName());
        responseEvent.setMiddleName(warrant.getMiddleName());
        responseEvent.setNameSuffix(warrant.getSuffix());
        responseEvent.setAliasName(warrant.getAliasName());
        responseEvent.setPhoneNum(warrant.getPhoneNum());
        responseEvent.setDateOfBirth(warrant.getDateOfBirth());
        responseEvent.setDateOfBirthSource(warrant.getDateOfBirthSource());
        responseEvent.setSsn(warrant.getSsn());
        responseEvent.setFbiNum(warrant.getFbiNum());
        responseEvent.setSid(warrant.getSidNum());
        responseEvent.setReferralNumber(warrant.getReferralNum());
        responseEvent.setDaLogNum(String.valueOf(warrant.getDaLogNumber()));
        responseEvent.setTransactionNum(String.valueOf(warrant.getTransactionNum()));
        responseEvent.setWarrantSignedStatusId(warrant.getWarrantSignedStatusId());
        responseEvent.setFileStampDate(warrant.getFileStampDate());
        responseEvent.setFileStampLogonId(warrant.getFileStampUserId());
        responseEvent.setWarrantTypeId(warrant.getWarrantTypeId());
        responseEvent.setWarrantStatusId(warrant.getWarrantStatusId());
        responseEvent.setBuildId(warrant.getBuildId());
        responseEvent.setComplexionId(warrant.getComplexionId());
        responseEvent.setEyeColorId(warrant.getEyeColorId());
        responseEvent.setHairColorId(warrant.getHairColorId());
        responseEvent.setRaceId(warrant.getRaceId());
        responseEvent.setSexId(warrant.getSexId());
        responseEvent.setWeight(String.valueOf(warrant.getWeight()));
        responseEvent.setReferralNumber(warrant.getReferralNum());
        responseEvent.setDateOfIssue(warrant.getDateOfIssue());
        responseEvent.setWarrantAcknowledgementDate(warrant.getWarrantAcknowledgementDate());
        responseEvent.setWarrantAcknowledgeStatusId(warrant.getWarrantAcknowledgeStatusId());
        responseEvent.setWarrantActivationDate(warrant.getWarrantActivationDate());
        responseEvent.setWarrantActivationStatusId(warrant.getWarrantActivationStatusId());
        responseEvent.setHeight(warrant.getHeight());

        responseEvent.setOfficerId(warrant.getOfficerId());

        //service
        responseEvent.setServiceReturnGeneratedStatusId(warrant.getServiceReturnGeneratedStatusId());
        responseEvent.setServiceReturnSignatureStatusId(warrant.getServiceReturnSignatureStatusId());

        //recall
        responseEvent.setRecallDate(warrant.getRecallDate());
        responseEvent.setRecallReasonId(warrant.getRecallReasonId());

        //release
        responseEvent.setReleaseDecisionId(warrant.getReleaseDecisionId());
        responseEvent.setReleaseOfficerLogonId(warrant.getReleaseDecisionUserId());
        responseEvent.setReleaseDecisionDate(warrant.getReleaseDecisionTimeStamp());

        //transfer
        responseEvent.setTransferDate(warrant.getTransferTimeStamp());
        if (PDJuvenileWarrantConstants.RELEASE_DECISION_TO_JUVENILE_PROBATION.equals(warrant.getReleaseDecisionId()))
        {
            responseEvent.setTransferOfficerId(warrant.getTransferOfficerId());
            responseEvent.setTransferOfficerDepartmentId(warrant.getTransferOfficerDepartmentId());
            responseEvent.setTransferLocationId(warrant.getTransferLocationId());
        }
        else if (PDJuvenileWarrantConstants.RELEASE_DECISION_TO_PERSON.equals(warrant.getReleaseDecisionId()))
        {
            responseEvent.setReleaseAssociateNum(warrant.getReleaseAssociateNum());
        }
        responseEvent.setTransferLocationId(warrant.getTransferLocationId());

        responseEvent.setTransactionNum(String.valueOf(warrant.getTransactionNum()));

        responseEvent.setUnsendNotSignedReason(warrant.getUnsendNotSignedReason());

        // originator
        responseEvent.setWarrantOriginatorCourt(warrant.getWarrantOriginatorCourt());
        String warrantOriginatorUserId = warrant.getWarrantOriginatorUserId();
        if (warrantOriginatorUserId != null && warrantOriginatorUserId.equals("") == false)
        {
            responseEvent.setWarrantOriginatorId(warrant.getWarrantOriginatorUserId());
        }
        responseEvent.setWarrantOriginatorName(warrant.getWarrantOriginatorName());

        responseEvent.setAffadivitStatement(warrant.getAffidavitStatement());
    }

    /*
     * (non-Javadoc)
     * 
     * @see pd.pattern.IBuilder#getResult()
     */
    public Object getResult()
    {
        // TODO Auto-generated method stub
        return this.responseEvent;
    }

}

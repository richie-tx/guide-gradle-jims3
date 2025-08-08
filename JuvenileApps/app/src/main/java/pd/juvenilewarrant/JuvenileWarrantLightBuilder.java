/*
 * Created on Nov 13, 2006
 *
 */
package pd.juvenilewarrant;

import java.util.Collection;
import java.util.Iterator;

import pd.codetable.Code;
import pd.codetable.criminal.JuvenileCourt;
import pd.codetable.person.JuvenileSchoolDistrictCode;
import pd.contact.agency.Department;
import pd.contact.user.UserProfile;
import naming.PDJuvenileWarrantConstants;
import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.utilities.Name;
import mojo.pattern.IBuilder;

/**
 * @author Jim Fisher
 */
public class JuvenileWarrantLightBuilder implements IBuilder
{
    protected JuvenileWarrantResponseEvent response;

    protected JuvenileWarrant warrant;

    public JuvenileWarrantLightBuilder()
    {
        this.response = new JuvenileWarrantResponseEvent();
    }

    public JuvenileWarrantLightBuilder(JuvenileWarrant aWarrant)
    {
        this.setWarrant(aWarrant);
    }

    public void build()
    {
        this.response.setTopic(PDJuvenileWarrantConstants.JUVENILE_WARRANT_EVENT_TOPIC);
        this.response.setJuvenileNum(warrant.getJuvenileNum());
        this.response.setFirstName(warrant.getFirstName());
        this.response.setLastName(warrant.getLastName());
        this.response.setMiddleName(warrant.getMiddleName());
        this.response.setNameSuffix(warrant.getSuffix());

        IName nameBean = new NameBean(warrant.getFirstName(), warrant.getMiddleName(), warrant.getLastName());
        nameBean.setSuffix(warrant.getSuffix());
        this.response.setJuvenileName(nameBean);

        this.response.setWarrantNum(warrant.getWarrantNum());
        this.response.setReferralNumber(warrant.getReferralNum());

        this.response.setDaLogNum(String.valueOf(warrant.getDaLogNumber()));
        this.response.setTransactionNum(String.valueOf(warrant.getTransactionNum()));
        this.response.setDateOfBirth(warrant.getDateOfBirth());
    }

    public Object getResult()
    {
        return this.response;
    }

    public void setFileStampValues()
    {
        if (warrant.getFileStampUserId() != null && warrant.getFileStampUserId().equals("") == false)
        {
            UserProfile userProfile = warrant.getFileStampUser();
            if (userProfile != null)
            {
	            this.response.setFileStampFirstName(userProfile.getFirstName());
	            this.response.setFileStampLastName(userProfile.getLastName());
	            this.response.setFileStampMiddleName(userProfile.getMiddleName());
	            this.response.setFileStampLogonId(userProfile.getLogonId());
            }
        }
        this.response.setFileStampDate(warrant.getFileStampDate());
    }

    public void setServiceDateValidationValues()
    {

        this.response.setWarrantActivationDate(warrant.getWarrantActivationDate());
        this.response.setDateOfIssue(warrant.getDateOfIssue());

    }

    public void setCautionCodes()
    {
        this.response.setCautions(warrant.getCautionCodes());
    }

    public void setCharges()
    {
        Collection charges = warrant.getCharges();
        String[] chargeArray = new String[charges.size()];
        Iterator i = charges.iterator();
        int index = 0;
        while (i.hasNext())
        {
            Charge charge = (Charge) i.next();
            chargeArray[index] = charge.getSequenceNum();
            this.response.setPetitionNum(charge.getPetitionNum());
            index++;
        }
        this.response.setCharges(chargeArray);
    }

    public void setScarsMarksTattoosCodes()
    {
        this.response.setScarsMarks(warrant.getScarsMarksCodes());
        this.response.setTattoos(warrant.getTattoosCodes());
    }

    private void setJudgeValue()
    {
        JuvenileCourt court = JuvenileCourt.find(warrant.getWarrantOriginatorCourt());
        if (court != null && "!Y".equalsIgnoreCase( court.getInactiveInd()))
        {
            this.response.setWarrantOriginatorJudge(court.getJudgesName());
        }
    }

    public void setJuvenileValues()
    {
        Code code = null;
        this.response.setBuildId(warrant.getBuildId());
        this.response.setComplexionId(warrant.getComplexionId());
        this.response.setEyeColorId(warrant.getEyeColorId());
        this.response.setHairColorId(warrant.getHairColorId());
        this.response.setRaceId(warrant.getRaceId());
        this.response.setSexId(warrant.getSexId());

        if (warrant.getSchoolCodeId() != null && !warrant.getSchoolCodeId().equals(""))
        {
            JuvenileSchoolDistrictCode schoolCode = warrant.getSchoolCode();
            if (schoolCode != null)
            {
                String oid = warrant.getSchoolCodeId();
                String district = oid.substring(0, 3).trim();
                String number = oid.substring(3).trim();
                this.response.setSchoolId(number);
                this.response.setSchoolName(schoolCode.getSchoolDescription());
                this.response.setSchoolDistrictId(district);
                this.response.setSchoolDistrict(schoolCode.getDistrictDescription());
            }
        }

        this.response.setHeight(warrant.getHeight());
        if (warrant.getWeight() != 0)
        {
            this.response.setWeight(String.valueOf(warrant.getWeight()));
        }

        this.response.setDateOfBirth(warrant.getDateOfBirth());
        this.response.setOtherCautionComments(warrant.getOtherCautionComments());

        this.response.setPhoneNum(warrant.getPhoneNum());
        this.response.setSsn(warrant.getSsn());

        this.response.setFbiNum(warrant.getFbiNum());

        this.response.setSid(warrant.getSidNum());

        this.response.setAliasName(warrant.getAliasName());
    }

    public void setSignatureValues()
    {
        this.response.setWarrantSignedStatusId(warrant.getWarrantSignedStatusId());
        this.response.setUnsendNotSignedReason(warrant.getUnsendNotSignedReason());
    }

    public void setOfficerValues()
    {
        if (warrant.isJJS())
        {
            UserProfile probationOfficerOR = warrant.getProbationOfficerOfRecord();
            if (probationOfficerOR != null)
            {
                Name name = new Name(probationOfficerOR.getFirstName(), "", probationOfficerOR.getLastName());
                this.response.setProbationOfficerOfRecordName(name.getFormattedName());
            }
            else
            {
                this.response.setProbationOfficerOfRecordName("NOT AVAILABLE");
            }
        }
        else
        {
            this.response.setProbationOfficerOfRecordName("NOT AVAILABLE");
        }
        this.response.setOfficerId(warrant.getOfficerId());
    }

    public void setWarrant(JuvenileWarrant aWarrant)
    {
        this.warrant = aWarrant;
        this.response = new JuvenileWarrantResponseEvent();
    }

    public void setWarrantActivationStatus()
    {
        Code code = this.warrant.getWarrantActivationStatus();
        if (code != null)
        {
	        response.setWarrantActivationStatusId(code.getCode());
	        response.setWarrantActivationStatus(code.getDescription());
        }
    }

    public void setWarrantDetails()
    {
        this.response.setAliasName(warrant.getAliasName());
        this.response.setPhoneNum(warrant.getPhoneNum());
        this.response.setDateOfBirth(warrant.getDateOfBirth());
        this.response.setDateOfBirthSource(warrant.getDateOfBirthSource());
        this.response.setSsn(warrant.getSsn());
        this.response.setFbiNum(warrant.getFbiNum());
        this.response.setSid(warrant.getSidNum());
        this.response.setWarrantSignedStatusId(warrant.getWarrantSignedStatusId());
        this.response.setFileStampDate(warrant.getFileStampDate());
        this.response.setFileStampLogonId(warrant.getFileStampUserId());
        this.response.setWarrantTypeId(warrant.getWarrantTypeId());
        this.response.setWarrantStatusId(warrant.getWarrantStatusId());
        this.response.setBuildId(warrant.getBuildId());
        this.response.setComplexionId(warrant.getComplexionId());
        this.response.setEyeColorId(warrant.getEyeColorId());
        this.response.setHairColorId(warrant.getHairColorId());
        this.response.setRaceId(warrant.getRaceId());
        this.response.setSexId(warrant.getSexId());
        this.response.setWeight(String.valueOf(warrant.getWeight()));
        this.response.setReferralNumber(warrant.getReferralNum());
        this.response.setDateOfIssue(warrant.getDateOfIssue());
        this.response.setWarrantAcknowledgementDate(warrant.getWarrantAcknowledgementDate());
        this.response.setWarrantAcknowledgeStatusId(warrant.getWarrantAcknowledgeStatusId());
        this.response.setWarrantActivationDate(warrant.getWarrantActivationDate());
        this.response.setWarrantActivationStatusId(warrant.getWarrantActivationStatusId());
        this.response.setHeight(warrant.getHeight());

        this.response.setOfficerId(warrant.getOfficerId());

        //service
        this.response.setServiceReturnGeneratedStatusId(warrant.getServiceReturnGeneratedStatusId());
        this.response.setServiceReturnSignatureStatusId(warrant.getServiceReturnSignatureStatusId());

        //recall
        this.response.setRecallDate(warrant.getRecallDate());
        this.response.setRecallReasonId(warrant.getRecallReasonId());

        //release
        this.response.setReleaseDecisionId(warrant.getReleaseDecisionId());
        this.response.setReleaseOfficerLogonId(warrant.getReleaseDecisionUserId());
        this.response.setReleaseDecisionDate(warrant.getReleaseDecisionTimeStamp());

        //transfer
        this.response.setTransferDate(warrant.getTransferTimeStamp());
        if (PDJuvenileWarrantConstants.RELEASE_DECISION_TO_JUVENILE_PROBATION.equals(warrant.getReleaseDecisionId()))
        {
            this.response.setTransferOfficerId(warrant.getTransferOfficerId());
            this.response.setTransferOfficerDepartmentId(warrant.getTransferOfficerDepartmentId());
        }
        else if (PDJuvenileWarrantConstants.RELEASE_DECISION_TO_PERSON.equals(warrant.getReleaseDecisionId()))
        {
            this.response.setReleaseAssociateNum(warrant.getReleaseAssociateNum());
        }
        this.response.setTransferLocationId(warrant.getTransferLocationId());

        this.response.setUnsendNotSignedReason(warrant.getUnsendNotSignedReason());

        // warrant originator info
        this.response.setWarrantOriginatorCourt(warrant.getWarrantOriginatorCourt());
        this.response.setWarrantOriginatorId(warrant.getWarrantOriginatorUserId());
        this.response.setWarrantOriginatorName(warrant.getWarrantOriginatorName());

        this.response.setAffadivitStatement(warrant.getAffidavitStatement());
    }

    public void setWarrantOriginator()
    {
        String userId = warrant.getWarrantOriginatorUserId();
        if (userId != null && userId.equals("") == false
                && !PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equals(warrant.getWarrantTypeId()))
        {
            UserProfile user = warrant.getWarrantOriginatorUser();
            if (user != null)
            {
                this.response.setWarrantOriginatorId(user.getLogonId());

                // TODO Refactor to use IName

                this.response.setWarrantOriginatorName(new pd.km.util.Name(user.getFirstName(), user.getMiddleName(),
                        user.getLastName()).toString());

                String departmentId = user.getDepartmentId();
                if (departmentId != null && departmentId.equals("") == false)
                {
                    Department department = user.getDepartment();
                    if (department != null)
                    {
                        response.setWarrantOriginatorAgencyId(department.getDepartmentId());
                        response.setWarrantOriginatorAgencyName(department.getDepartmentName());
                    }
                }
            }
        }
        else
        {
            response.setWarrantOriginatorName(warrant.getWarrantOriginatorName());
            
            String warrantOriginatorName = warrant.getWarrantOriginatorName();
            if (warrantOriginatorName != null && warrantOriginatorName.equals("") == false)
            {
                response.setWarrantOriginatorJudge(warrant.getWarrantOriginatorName());
            }
            else
            {
                this.response.setWarrantOriginatorJudge("NOT AVAILABLE");
            }
        }
    }

    public void setWarrantServiceStatuses()
    {
        Code code = warrant.getServiceReturnSignatureStatus();
        if (code != null)
        {
            response.setServiceReturnSignatureStatusId(code.getCode());
            response.setServiceReturnSignatureStatus(code.getDescription());
        }

        code = warrant.getServiceReturnGeneratedStatus();
        if (code != null)
        {
            response.setServiceReturnGeneratedStatusId(code.getCode());
            response.setServiceReturnGeneratedStatus(code.getDescription());
        }
    }

    public void setWarrantSignedStatus()
    {
        Code code = this.warrant.getWarrantSignedStatus();
        if (code != null)
        {
	        response.setWarrantSignedStatusId(code.getCode());
	        response.setWarrantSignedStatus(code.getDescription());
        }
    }

    public void setWarrantStatus()
    {
        Code code = this.warrant.getWarrantStatus();
        if (code != null)
        {
	        response.setWarrantStatusId(code.getCode());
	        response.setWarrantStatus(code.getDescription());
        }
    }

    public void setWarrantType()
    {
        Code code = this.warrant.getWarrantType();
        if (code != null)
        {
	        response.setWarrantTypeId(code.getCode());
	        response.setWarrantType(code.getDescription());
        }
    }

    public void setRecallInfo()
    {
        // Recall info
        if (warrant.getRecallUserId() != null)
        {
            UserProfile user = warrant.getRecallUser();
            if (user != null)
            {
                response.setRecallFirstName(user.getFirstName());
                response.setRecallLastName(user.getLastName());
                response.setRecallLogonId(warrant.getRecallUserId());
            }
        }
        response.setRecallReasonId(warrant.getRecallReasonId());

        response.setReleaseDecisionId(warrant.getReleaseDecisionId());
        response.setReleaseDecisionDate(warrant.getReleaseDecisionTimeStamp());
        response.setReleaseAssociateNum(warrant.getReleaseAssociateNum());
        response.setTransferDate(warrant.getTransferTimeStamp());
        response.setReleaseOfficerLogonId(warrant.getTransferOfficerId());
    }

}

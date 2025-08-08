/*
 * Created on Nov 16, 2006
 *
 */
package pd.juvenilewarrant;

import naming.PDJuvenileWarrantConstants;
import pd.codetable.Code;
import pd.contact.agency.Department;
import messaging.contact.to.PhoneNumberBean;
import messaging.contact.domintf.IPhoneNumber;
import messaging.juvenilewarrant.reply.ProcessReturnOfServiceResponseEvent;
import mojo.pattern.IBuilder;
import naming.PDCodeTableConstants;
import naming.PDOfficerProfileConstants;

/**
 * @author Jim Fisher
 *  
 */
public class ProcessReturnOfServiceBuilder implements IBuilder
{
    private ProcessReturnOfServiceResponseEvent response;

    private JuvenileWarrantServiceOfficer jwso;

    private boolean thin = false;

    private JuvenileWarrant warrant;

    public ProcessReturnOfServiceBuilder(boolean aThin)
    {
        this.thin = aThin;
    }

    public void setWarrantServiceOfficer(JuvenileWarrantServiceOfficer jwso)
    {
        this.jwso = jwso;
    }

    private void buildFull()
    {
        this.buildThin();

        this.response.setOfficerOtherIdNum(jwso.getOfficerOtherIdNum());

        IPhoneNumber phone = new PhoneNumberBean(jwso.getOfficerCellPhone());
        this.response.setOfficerCellPhone(phone);

        phone = new PhoneNumberBean(jwso.getOfficerWorkPhone());
        this.response.setOfficerWorkPhone(phone);

        phone = new PhoneNumberBean(jwso.getOfficerPager());
        this.response.setOfficerPager(phone);

        this.response.setOfficerEmail(jwso.getOfficerEmail());

        if (jwso.getOfficerDepartmentId() != null && jwso.getOfficerDepartmentId().equals("") == false)
        {
            Department officerDept = jwso.getOfficerDepartment();
            this.response.setOfficerDepartmentId(officerDept.getDepartmentId());
            this.response.setOfficerDepartment(officerDept.getDepartmentName());
        }
        this.response.setApartmentNum(jwso.getApartmentNum());
        this.response.setCity(jwso.getCity());
        this.response.setStreetAddress2(jwso.getStreetAddress2());
        this.response.setStateId(jwso.getStateId());
        this.response.setStreetName(jwso.getStreetName());
        this.response.setStreetNum(jwso.getStreetNum());
        this.response.setStreetTypeId(jwso.getStreetTypeId());
        
        String officerIdTypeId;
        if(jwso.getBadge() == null || jwso.getBadge().equals(""))
        {
            this.response.setOfficerNum(jwso.getOfficerOtherIdNum());
            officerIdTypeId = PDOfficerProfileConstants.OTHER_NUM_ID;
        }
        else
        {
            this.response.setOfficerNum(jwso.getBadge());
            officerIdTypeId = PDOfficerProfileConstants.BADGE_NUM_ID;
        }        

        Code officerIdType = Code.find(PDCodeTableConstants.OFFICER_ID_TYPE, officerIdTypeId);
        this.response.setOfficerIdTypeId(officerIdType.getCode());
        this.response.setOfficerIdType(officerIdType.getDescription());
        
        this.response.setServiceStatusId(PDJuvenileWarrantConstants.WARRANT_SERVICE_SUCCESSFUL);
        this.response.setServiceStatus(PDJuvenileWarrantConstants.WARRANT_SERVICE_SUCCESSFUL_DESC);
        
        this.response.setWarrantActivationDate(jwso.getWarrantActivationDate());
        // These two are used for printing only!
        this.response.setWarrantOriginatorCourtId(jwso.getWarrantOriginatorCourtId());
        this.response.setPetitionNum(jwso.getPetitionNum());
    }

    private void buildThin()
    {
        response.setWarrantNum(jwso.getWarrantNum());
        response.setJuvenileName(jwso.getJuvenileName());
        response.setServiceStatus(PDJuvenileWarrantConstants.WARRANT_SERVICE_SUCCESSFUL_DESC);
        response.setServiceTimeStamp(jwso.getServiceDate());
        response.setOfficerId(jwso.getOfficerId());
        response.setOfficerName(jwso.getExecutorName());
        response.setPetitionNum(jwso.getPetitionNum());
        response.setWarrantActivationDate(jwso.getCreateTimestamp());

        Code warrantType = jwso.getWarrantType();
        this.response.setWarrantTypeId(warrantType.getCode());
        this.response.setWarrantType(warrantType.getDescription());
    }

    public void build()
    {
        response = new ProcessReturnOfServiceResponseEvent();
        response.setTopic(PDJuvenileWarrantConstants.JUVENILE_WARRANT_PROCESS_SERVICE_EVENT_TOPIC);
        if (this.thin)
        {
            this.buildThin();
        }
        else
        {
            this.buildFull();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.pattern.IBuilder#getResult()
     */
    public Object getResult()
    {
        // TODO Auto-generated method stub
        return this.response;
    }

}

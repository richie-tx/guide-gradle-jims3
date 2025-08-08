/*
 * Created on Jul 18, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilewarrant;

import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import pd.codetable.Code;
import pd.contact.agency.Department;
import pd.contact.user.UserProfile;
import mojo.pattern.IBuilder;

/**
 * @author Jim Fisher
 *  
 */
public class JuvenileWarrantListBuilder implements IBuilder
{
    private JuvenileWarrantResponseEvent responseEvent;

    /**
     * @param warrant
     *            The warrant to set.
     */
    public void setWarrant(JuvenileWarrant warrant)
    {
        this.warrant = warrant;
    }

    private JuvenileWarrant warrant;

    public JuvenileWarrantListBuilder(JuvenileWarrant warrant)
    {
        this.warrant = warrant;
    }

    /**
     *  
     */
    public JuvenileWarrantListBuilder()
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see pd.pattern.IBuilder#build()
     */
    public void build()
    {
        this.setSimpleValues();
        this.setWarrantCodeValues();
    }

    /**
     *  
     */
    private void setSimpleValues()
    {
        IBuilder simpleBuilder = new JuvenileWarrantSimpleBuilder(warrant);
        simpleBuilder.build();
        this.responseEvent = (JuvenileWarrantResponseEvent) simpleBuilder.getResult();
    }

    /**
     *  
     */
    private void setWarrantCodeValues()
    {
        Code code = null;
        if (warrant.getWarrantStatusId() != null && !warrant.getWarrantStatusId().equals(""))
        {
            code = warrant.getWarrantStatus();
            if (code != null)
            {
                this.responseEvent.setWarrantStatusId(code.getCode());
                this.responseEvent.setWarrantStatus(code.getDescription());
            }
        }
        if (warrant.getWarrantTypeId() != null && !warrant.getWarrantTypeId().equals(""))
        {
            code = warrant.getWarrantType();
            if (code != null)
            {
                this.responseEvent.setWarrantTypeId(code.getCode());
                this.responseEvent.setWarrantType(code.getDescription());
            }
        }

        if (warrant.getWarrantActivationStatusId() != null && !warrant.getWarrantActivationStatusId().equals(""))
        {
            code = warrant.getWarrantActivationStatus();
            if (code != null)
            {
                this.responseEvent.setWarrantActivationStatusId(code.getCode());
                this.responseEvent.setWarrantActivationStatus(code.getDescription());
            }
        }

        String warrantOriginatorUserId = warrant.getWarrantOriginatorUserId();
        if (warrantOriginatorUserId != null && !warrantOriginatorUserId.equals(""))
        {
            UserProfile user = warrant.getWarrantOriginatorUser();
            if (user != null)
            {
                this.responseEvent.setWarrantOriginatorId(user.getLogonId());
                this.responseEvent.setWarrantOriginatorName(new pd.km.util.Name(user.getFirstName(), user.getMiddleName(), user
                        .getLastName()).toString());
                if ((user.getAgencyId() != null) && (!user.getAgencyId().equals("")))
                {
                    Department department = user.getDepartment();
                    if (department != null)
                    {
                        this.responseEvent.setWarrantOriginatorAgencyId(department.getDepartmentId());
                        this.responseEvent.setWarrantOriginatorAgencyName(department.getDepartmentName());
                    }
                }
            }
        }
        else
        {
            this.responseEvent.setWarrantOriginatorName(warrant.getWarrantOriginatorName());
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see pd.pattern.IBuilder#getResult()
     */
    public Object getResult()
    {
        return this.responseEvent;
    }

}

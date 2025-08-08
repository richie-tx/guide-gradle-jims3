/**
 * 
 */
package messaging.codetable.criminal.reply;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * @author ugopinath
 *
 */
public class JuvenileReferralDecisionResponseEvent extends ResponseEvent implements Comparable, ICode {

    private String code; 
    private String description;
    private String category;
    private String department;
    private String dpsCode;
    private String fund;
    private String TJPCCode;
    private String vendor;
    private String dispositionCode;
    private String inactiveInd;

    @Override
    public int compareTo(Object o)
    {
	// TODO Auto-generated method stub
	return 0;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getDpsCode()
    {
        return dpsCode;
    }

    public void setDpsCode(String dpsCode)
    {
        this.dpsCode = dpsCode;
    }

    public String getFund()
    {
        return fund;
    }

    public void setFund(String fund)
    {
        this.fund = fund;
    }

    public String getTJPCCode()
    {
        return TJPCCode;
    }

    public void setTJPCCode(String tJPCCode)
    {
        TJPCCode = tJPCCode;
    }

    public String getVendor()
    {
        return vendor;
    }

    public void setVendor(String vendor)
    {
        this.vendor = vendor;
    }

    public String getDispositionCode()
    {
        return dispositionCode;
    }

    public void setDispositionCode(String dispositionCode)
    {
        this.dispositionCode = dispositionCode;
    }

    public String getInactiveInd()
    {
        return inactiveInd;
    }

    public void setInactiveInd(String inactiveInd)
    {
        this.inactiveInd = inactiveInd;
    }	

	
}

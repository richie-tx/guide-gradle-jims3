/**
 * 
 */
package messaging.juvenilecase.reply;

/**
 * @author NEMathew
 *
 */
public class JuvReferralFamilyResponseEvent extends mojo.km.messaging.ResponseEvent
{
    private String juvenileNum;
    private String fathersAddress;
    private String fathersName;
    private String fathersPhone;
    private String mothersAddress;
    private String mothersName;
    private String mothersPhone;
    private String otherAddress;
    private String otherName;
    private String OtherPhone;
    
    //Bug 82329
    private String juvRefFamId;
    
    public String getJuvenileNum()
    {
        return juvenileNum;
    }
    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
    public String getFathersAddress()
    {
        return fathersAddress;
    }
    public void setFathersAddress(String fathersAddress)
    {
        this.fathersAddress = fathersAddress;
    }
    public String getFathersName()
    {
        return fathersName;
    }
    public void setFathersName(String fathersName)
    {
        this.fathersName = fathersName;
    }
    public String getFathersPhone()
    {
        return fathersPhone;
    }
    public void setFathersPhone(String fathersPhone)
    {
        this.fathersPhone = fathersPhone;
    }
    public String getMothersAddress()
    {
        return mothersAddress;
    }
    public void setMothersAddress(String mothersAddress)
    {
        this.mothersAddress = mothersAddress;
    }
    public String getMothersName()
    {
        return mothersName;
    }
    public void setMothersName(String mothersName)
    {
        this.mothersName = mothersName;
    }
    public String getMothersPhone()
    {
        return mothersPhone;
    }
    public void setMothersPhone(String mothersPhone)
    {
        this.mothersPhone = mothersPhone;
    }
    public String getOtherAddress()
    {
        return otherAddress;
    }
    public void setOtherAddress(String otherAddress)
    {
        this.otherAddress = otherAddress;
    }
    public String getOtherName()
    {
        return otherName;
    }
    public void setOtherName(String otherName)
    {
        this.otherName = otherName;
    }
    public String getOtherPhone()
    {
        return OtherPhone;
    }
    public void setOtherPhone(String otherPhone)
    {
        OtherPhone = otherPhone;
    }
    /**
     * @return the juvRefFamId
     */
    public String getJuvRefFamId()
    {
	return juvRefFamId;
    }
    /**
     * @param juvRefFamId the juvRefFamId to set
     */
    public void setJuvRefFamId(String juvRefFamId)
    {
	this.juvRefFamId = juvRefFamId;
    }
}

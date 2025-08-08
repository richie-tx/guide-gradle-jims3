// Source file:
// C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\ProcessReturnOfServiceNotificationEvent.java

package messaging.juvenilewarrant;

import messaging.contact.to.PhoneNumberBean;
import mojo.km.messaging.PersistentEvent;

public class ProcessReturnOfServiceNotificationEvent extends PersistentEvent
{

    private String emailFrom;

    private String emailTo;

    private String executorPhoneNum;

    private String nameFirstMiddleLastSuffix;
    
    private String nameLastFirstMiddleSuffix;

    private int notificationType;
    
    private String warrantNum;

    private String warrantTypeId;

    /**
     * @roseuid 4213993F02CE
     */
    public ProcessReturnOfServiceNotificationEvent()
    {

    }

    /**
     * @return
     */
    public String getEmailFrom()
    {
        return emailFrom;
    }

    /**
     * @return
     */
    public String getEmailTo()
    {
        return emailTo;
    }

    /**
     * @return
     */
    public String getExecutorPhoneNum()
    {
        return executorPhoneNum;
    }
    
    /**
     * 
     * @return Returns a formatted executorPhoneNum
     */
    public String getExecutorPhoneNumString()
    {
        StringBuffer buffer = new StringBuffer(50);
	    if(getExecutorPhoneNum() != null && !getExecutorPhoneNum().equals(""))
        {
//            PhoneNumberBean phoneNumFormatter;
            PhoneNumberBean executorPhoneNumber = new PhoneNumberBean(getExecutorPhoneNum());
            executorPhoneNumber = new PhoneNumberBean("A-P-F");
            buffer.append("work phone: ");
            buffer.append(executorPhoneNumber.getFormattedPhoneNumber());
        }
	    String executorPhoneNumString = buffer.toString();
	    return executorPhoneNumString;
    }

    /**
     * @return Returns formatted nameFirstMiddleLastSuffix.
     */
    public String getNameFirstMiddleLastSuffix()
    {
        return nameFirstMiddleLastSuffix;
    }
    
    /**
     * @return Returns formatted nameLastFirstMiddleSuffix.
     */
    public String getNameLastFirstMiddleSuffix()
    {
        return nameLastFirstMiddleSuffix;
    }

    /**
     * @return
     */
    public int getNotificationType()
    {
        return notificationType;
    }

    /**
     * @return
     */
    public String getWarrantNum()
    {
        return warrantNum;
    }

    /**
     * @return Returns the warrantTypeId.
     */
    public String getWarrantTypeId()
    {
        return warrantTypeId;
    }

    /**
     * @param string
     */
    public void setEmailFrom(String string)
    {
        emailFrom = string;
    }

    /**
     * @param string
     */
    public void setEmailTo(String string)
    {
        emailTo = string;
    }

    /**
     * @param string
     */
    public void setExecutorPhoneNum(String string)
    {
        executorPhoneNum = string;
    }

    /**
     * @param nameFirstMiddleLastSuffix 
     * 			The nameFirstMiddleLastSuffix to set.
     */
    public void setNameFirstMiddleLastSuffix(String nameFirstMiddleLastSuffix)
    {
        this.nameFirstMiddleLastSuffix = nameFirstMiddleLastSuffix;
    }
    
    /**
     * @param nameLastFirstMiddleSuffix
     *          The nameLastFirstMiddleSuffix to set.
     */
    public void setNameLastFirstMiddleSuffix(String nameLastFirstMiddleSuffix)
    {
        this.nameLastFirstMiddleSuffix = nameLastFirstMiddleSuffix;
    }

    /**
     * @param i
     */
    public void setNotificationType(int i)
    {
        notificationType = i;
    }

    /**
     * @param string
     */
    public void setWarrantNum(String string)
    {
        warrantNum = string;
    }

    /**
     * @param warrantTypeId
     *            The warrantTypeId to set.
     */
    public void setWarrantTypeId(String warrantTypeId)
    {
        this.warrantTypeId = warrantTypeId;
    }

}

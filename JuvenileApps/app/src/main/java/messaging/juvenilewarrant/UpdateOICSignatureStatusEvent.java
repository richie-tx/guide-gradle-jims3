//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\UpdateOICSignatureStatusEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class UpdateOICSignatureStatusEvent extends RequestEvent 
{
   public String warrantActivationStatus;
   public String warrantSignatureStatus;
   public Date warrantActivationDate;
   public String status;
   public String signatureOption;
   public String warrantNum;
   public String unSendNotSignedReason;
   
   /**
    * @roseuid 41990355031E
    */
   public UpdateOICSignatureStatusEvent() 
   {
    
   }
   
   /**
    * @param warrantActivationStatus
    * @roseuid 4199007C0204
    */
   public void setWarrantActivationStatus(String warrantActivationStatus) 
   {
    this.warrantActivationStatus = warrantActivationStatus;
   }
   
   /**
    * @return String
    * @roseuid 4199007C0206
    */
   public String getWarrantActivationStatus() 
   {
    return warrantActivationStatus;
   }
   
   /**
    * @param warrantSignatureStatus
    * @roseuid 4199007C0214
    */
   public void setWarrantSignatureStatus(String warrantSignatureStatus) 
   {
    this.warrantSignatureStatus = warrantSignatureStatus;
   }
   
   /**
    * @return String
    * @roseuid 4199007C0216
    */
   public String getWarrantSignatureStatus() 
   {
    return this.warrantSignatureStatus;
   }
   
   /**
    * @param warrantActivationDate
    * @roseuid 4199007C0218
    */
   public void setWarrantActivationDate(Date warrantActivationDate) 
   {
    this.warrantActivationDate = warrantActivationDate;
   }
   
   /**
    * @return Date
    * @roseuid 4199007C021A
    */
   public Date getWarrantActivationDate() 
   {
    return this.warrantActivationDate;
   }
   
   /**
    * @param status
    * @roseuid 4199007C0224
    */
   public void setStatus(String status) 
   {
    this.status = status;
   }
   
   /**
    * @return String
    * @roseuid 4199007C0226
    */
   public String getStatus() 
   {
    return this.status;
   }
   
   /**
    * @param signatureOption
    * @roseuid 4199007C0228
    */
   public void setSignatureOption(String signatureOption) 
   {
    this.signatureOption = signatureOption;
   }
   
   /**
    * @return String
    * @roseuid 4199007C022A
    */
   public String getSignatureOption() 
   {
    return this.signatureOption;
   }
   
   /**
    * @param warrantNum
    * @roseuid 4199007C0233
    */
   public void setWarrantNum(String warrantNum) 
   {
    this.warrantNum = warrantNum;
   }
   
   /**
    * @return String
    * @roseuid 4199007C0235
    */
   public String getWarrantNum() 
   {
    return this.warrantNum;
   }
   
   public String getUnSendNotSignedReason()
   {
   	return this.unSendNotSignedReason;
   }
   
   public void setUnSendNotSignedReason(String unSendNotSignedReason)
   {
   	this.unSendNotSignedReason = unSendNotSignedReason;
   }
}

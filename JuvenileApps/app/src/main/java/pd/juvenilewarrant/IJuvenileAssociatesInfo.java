/*
 * Created on Dec 15, 2004
 *
 */
package pd.juvenilewarrant;

import messaging.address.reply.AddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;

/**
 * @author glyons
 *  
 */
public interface IJuvenileAssociatesInfo
{
    JuvenileAssociateResponseEvent getFather();
    
    JuvenileAssociateResponseEvent getMother();
    
    JuvenileAssociateResponseEvent getOther();
    
    JuvenileAssociateResponseEvent getAlter();
    
    AddressResponseEvent getJuvenileAddress();
    
    /**
     * 
     * @return mothersFirstName
     */
    String getMothersFirstName();

    /**
     * 
     * @return mothersMiddleName
     */
    String getMothersMiddleName();

    /**
     * 
     * @return mothersLastName
     */
    String getMothersLastName();

    /**
     * 
     * @return fathersSsn
     */
    String getMothersSsn();

    /**
     * 
     * @return fathersFirstName
     */
    String getFathersFirstName();

    /**
     * 
     * @return fathersMiddleName
     */
    String getFathersMiddleName();

    /**
     * 
     * @return fathersLastName
     */
    String getFathersLastName();

    /**
     * 
     * @return fathersSsn
     */
    String getFathersSsn();

    /**
     * 
     * @return othersFirstName
     */
    String getOtherFirstName();
    
    /**
     * 
     * @return othersFirstName
     */
    String getAlterFirstName();

    /**
     * 
     * @return othersMiddleName
     */
    String getOtherMiddleName();
    
    /**
     * 
     * @return othersMiddleName
     */
    String getAlterMiddleName();

    /**
     * 
     * @return othersLastName
     */
    String getOtherLastName();
    
    /**
     * 
     * @return othersLastName
     */
    String getAlterLastName();

    /**
     * 
     * @return otherSsn
     */
    String getOtherSsn();

    /**
     * 
     * @return fathersPhoneNum
     */
    String getFathersPhoneNum();

    /**
     * 
     * @return fathersPhoneNum
     */
    String getMothersPhoneNum();

    /**
     * 
     * @return otherPhoneNum
     */
    String getOtherPhoneNum();
    
    /**
     * 
     * @return otherPhoneNum
     */
    String getAlterPhoneNum();

    /**
     * 
     * @param mothersFirstName
     */
    void setMothersFirstName(String mothersFirstName);

    /**
     * 
     * @param mothersMiddleName
     */
    void setMothersMiddleName(String mothersMiddleName);

    /**
     * 
     * @param mothersLastName
     */
    void setMothersLastName(String mothersLastName);

    /**
     * 
     * @param mothersPhoneNum
     */
    void setMothersPhoneNum(String mothersPhoneNum);

    /**
     * 
     * @param mothersSsn
     */
    void setMothersSsn(String mothersSsn);

    /**
     * 
     * @param fathersFirstName
     */
    void setFathersFirstName(String fathersFirstName);

    /**
     * 
     * @param fathersMiddleName
     */
    void setFathersMiddleName(String fathersMiddleName);

    /**
     * 
     * @param fathersLastName
     */
    void setFathersLastName(String fathersLastName);

    /**
     * 
     * @param fathersPhoneNum
     */
    void setFathersPhoneNum(String fathersPhoneNum);

    /**
     * 
     * @param fathersSsn
     */
    void setFathersSsn(String fathersSsn);

    /**
     * 
     * @param othersFirstName
     */
    void setOtherFirstName(String othersFirstName);

    /**
     * 
     * @param othersMiddleName
     */
    void setOtherMiddleName(String othersMiddleName);

    /**
     * 
     * @param othersLastName
     */
    void setOtherLastName(String othersLastName);

    /**
     * 
     * @param otherPhoneNum
     */
    void setOtherPhoneNum(String otherPhoneNum);

    /**
     * 
     * @param otherSsn
     */
    void setOtherSsn(String otherSsn);
    
    /**
     * 
     * @param othersFirstName
     */
    void setAlterFirstName(String othersFirstName);

    /**
     * 
     * @param othersMiddleName
     */
    void setAlterMiddleName(String othersMiddleName);

    /**
     * 
     * @param othersLastName
     */
    void setAlterLastName(String othersLastName);

    /**
     * 
     * @param otherPhoneNum
     */
    void setAlterPhoneNum(String otherPhoneNum);


}

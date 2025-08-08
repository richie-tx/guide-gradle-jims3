/*
 * Created on Mar 2, 2005
 *
 */
package ui.juvenilewarrant.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import naming.UIConstants;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SocialSecurity;
import ui.juvenilecase.form.JuvenileMemberForm;

import messaging.address.reply.AddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import messaging.juvenilewarrant.CreateJuvenileAssociateEvent;
import messaging.juvenilewarrant.JuvenileAssociateAddressRequestEvent;
import messaging.juvenilewarrant.JuvenileAssociateRequestEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.PropertyCopier;

/**
 * @author dapte
 * 
 * This class will encapsulate the associate data for one associate. It will
 * have all the associate attributes and all the addresses for that juvenile
 * associate.
 */
public class JuvenileAssociateBean
{
    private static List emptyColl = new ArrayList();
    
    private Name associateName = new Name();

    private String associateNum;
    private String associateNumAndRelationship;

    private Date dateOfBirth;

    private String dateOfBirthString;

    private String race;

    private String raceId;
    
    private String ethnicity;
    private String ethnicityId = "";
    private static List ethnicityList = emptyColl;

    private String relationshipToJuvenile;

    private String relationshipToJuvenileId;

    private String sex;

    private String sexId;

    private SocialSecurity ssn = new SocialSecurity("", true);

    private String warrantNum;

    private String topic;

    private String dlNumber;

    private String dlStateId;

    private String dlState;

    private PhoneNumber homePhoneNum = new PhoneNumber("");

    private PhoneNumber workPhoneNum = new PhoneNumber("");

    private PhoneNumber cellPhoneNum = new PhoneNumber("");

    private PhoneNumber pager = new PhoneNumber("");

    private PhoneNumber faxNum = new PhoneNumber("");

    private String faxLocation;

    private String email1;

    private String email2;

    private String email3;

    private List associateAddresses = new ArrayList();

    /**
     *  
     */
    public JuvenileAssociateBean()
    {

    }

    /**
     * @param evt
     */
    public void populateFromEventAttributes(JuvenileAssociateResponseEvent evt)
    {

        associateNum = evt.getAssociateNum();
        associateName = new Name(evt.getFirstName(), evt.getMiddleName(), evt.getLastName());
        dateOfBirth = evt.getDateOfBirth();
        race = evt.getRace();
        raceId = evt.getRaceId();
        relationshipToJuvenile = evt.getRelationshipToJuvenile();
        relationshipToJuvenileId = evt.getRelationshipToJuvenileId();
        sex = evt.getSex();
        sexId = evt.getSexId();
        ssn = new SocialSecurity(evt.getSsn());
        warrantNum = evt.getWarrantNum();
        homePhoneNum = new PhoneNumber(evt.getHomePhoneNum());
        workPhoneNum = new PhoneNumber(evt.getWorkPhoneNum());
        cellPhoneNum = new PhoneNumber(evt.getCellPhoneNum());
        pager = new PhoneNumber(evt.getPager());
        faxNum = new PhoneNumber(evt.getFaxNum());
        faxLocation = evt.getFaxLocation();
        email1 = evt.getEmail1();
        email2 = evt.getEmail2();
        email3 = evt.getEmail3();
        dlState = evt.getDlState();
        dlStateId = evt.getDlStateId();
        dlNumber = evt.getDlNumber();
        topic = evt.getTopic();
    }

    public static List getJuvenileAssociateBeans(List associateResponseEvents)
    {
        List beans = new ArrayList();

        int len = associateResponseEvents.size();
        for (int i = 0; i < len; i++)
        {
            JuvenileAssociateResponseEvent event = (JuvenileAssociateResponseEvent) associateResponseEvents.get(i);
            JuvenileAssociateBean bean = new JuvenileAssociateBean();
            bean.populateFromEventAttributes(event);
            beans.add(bean);
        }

        return beans;
    }

    /**
     * @return JuvenileAssociateRequestEvent
     */
    public JuvenileAssociateRequestEvent getJuvenileAssociateRequestEvent()
    {
        JuvenileAssociateRequestEvent request = new JuvenileAssociateRequestEvent();
        request.setAssociateNum(associateNum);
        request.setFirstName(associateName.getFirstName());
        request.setMiddleName(associateName.getMiddleName());
        request.setLastName(associateName.getLastName());
        request.setDateOfBirth(dateOfBirth);
        request.setRace(raceId);
        request.setRelationshipToJuvenile(relationshipToJuvenileId);
        request.setSex(sexId);
        request.setSsn(ssn.getSSN());
        request.setWarrantNum(warrantNum);
        request.setTopic(topic);
        return request;
    }

    /**
     * @return JuvenileAssociateResponseEvent
     */
    public JuvenileAssociateResponseEvent getJuvenileAssociateResponseEvent()
    {
        JuvenileAssociateResponseEvent response = new JuvenileAssociateResponseEvent();
        response.setAssociateNum(associateNum);
        response.setFirstName(associateName.getFirstName());
        response.setMiddleName(associateName.getMiddleName());
        response.setLastName(associateName.getLastName());
        response.setDateOfBirth(dateOfBirth);
        response.setRace(raceId);
        response.setRelationshipToJuvenile(relationshipToJuvenileId);
        response.setSex(sexId);
        response.setSsn(ssn.getSSN());
        response.setWarrantNum(warrantNum);
        response.setTopic(topic);
        return response;
    }

    /**
     * @return associateAddresses
     */
    public List getAssociateAddresses()
    {
        return associateAddresses;
    }

    /**
     * @param evt
     */
    public void insertAddress(JuvenileAssociateAddressResponseEvent evt)
    {
        associateAddresses.add(evt);
    }

    /**
     * @param evt
     */
    public void insertAddress(AddressResponseEvent evt)
    {
        JuvenileAssociateAddressResponseEvent base = new JuvenileAssociateAddressResponseEvent();

        base.setAdditionalZipCode(evt.getAdditionalZipCode());
        base.setAddress2(evt.getAddress2());
        base.setAddressId(evt.getAddressId());
        base.setAddressStatus(evt.getAddressStatus());
        base.setAddressTypeCode(evt.getAddressTypeId());
        base.setAddressType(evt.getAddressType());
        base.setAptNum(evt.getAptNum());
        base.setCity(evt.getCity());
        base.setCountryCode(evt.getCountryId());
        base.setCountry(evt.getCountry());
        base.setCountyCode(evt.getCountyId());
        base.setCounty(evt.getCounty());
        base.setCurrentAddressInd(evt.getCurrentAddressInd());
        base.setKeymap(evt.getKeymap());
        base.setLatitude(String.valueOf(evt.getLatitude()));
        base.setLongitude(String.valueOf(evt.getLongitude()));
        base.setStateCode(evt.getStateId());
        base.setState(evt.getState());
        base.setStreetNum(evt.getStreetNum());
        base.setStreetName(StringUtils.abbreviate(evt.getStreetName(),40));
        base.setStreetTypeCode(evt.getStreetTypeId());
        base.setStreetType(evt.getStreetTypeId());
        base.setZipCode(evt.getZipCode());
        base.setAddressStatus(evt.getAddressStatus());

        base.setTopic(evt.getTopic());
        associateAddresses.add(base);
    }

    /**
     * @return Collection
     */
    public List getAddressesAsRequestEvents()
    {
        JuvenileAssociateAddressResponseEvent response;
        JuvenileAssociateAddressRequestEvent request;
        List requests = new ArrayList();
        int len = associateAddresses.size();
        for (int i = 0; i < len; i++)
        {
            response = (JuvenileAssociateAddressResponseEvent) associateAddresses.get(i);
            request = new JuvenileAssociateAddressRequestEvent();
            request.setAdditionalZipCode(response.getAdditionalZipCode());
            request.setAddress2(response.getAddress2());
            request.setAddressId(response.getAddressId());
            request.setAddressStatus(response.getAddressStatus());
            request.setAddressType(response.getAddressTypeId());
            request.setAddressTypeId(response.getAddressTypeId());
            request.setAptNum(response.getAptNum());
            request.setAssociateNum(associateNum);
            request.setBadAddress(response.isBadAddress());
            request.setCountyId(response.getCountyId());
            request.setStreetTypeId(response.getStreetTypeId());
            request.setStreetNum(response.getStreetNum());
            request.setStreetName(response.getStreetName());
            request.setCity(response.getCity());
            request.setCountry(response.getCountryId());
            request.setCurrentAddressInd(response.getCurrentAddressInd());
            request.setKeymap(response.getKeymap());
            request.setLatitude(response.getLatitude());
            request.setLongitude(response.getLongitude());
            request.setStateId(response.getStateId());
            request.setZipCode(response.getZipCode());
            request.setTopic(response.getTopic());
            requests.add(request);
        }

        return requests;
    }

    /**
     *  
     */
    public void clearAssociateAddresses()
    {
        associateAddresses.clear();
    }

    public void clearAssociateInfo()
    {

        associateName.clear();
        dateOfBirth = null;
        dateOfBirthString = null;
        race = null;
        relationshipToJuvenile = null;
        ssn.clear();
        sex = null;
        homePhoneNum.clear();
        cellPhoneNum.clear();
        faxNum.clear();
        workPhoneNum.clear();
        pager.clear();
        faxLocation = null;
        email1 = null;
        email2 = null;
        email3 = null;
        dlStateId = null;
        dlState = null;
        dlNumber = null;
        this.associateNumAndRelationship = null;
    }

    /**
     * @return associateName
     */
    public Name getAssociateName()
    {
        return associateName;
    }

    /**
     * @return associateNum
     */
    public String getAssociateNum()
    {
        return associateNum;
    }
    
    public String getAssociateNumAndRelationship()
    {
	this.associateNumAndRelationship = this.associateNum + "-" + this.relationshipToJuvenile;
	
        return this.associateNumAndRelationship;
    }

    /**
     * @return cellPhoneNum
     */
    public PhoneNumber getCellPhoneNum()
    {
        return cellPhoneNum;
    }

    /**
     * @return dateOfBirth
     */
    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * @return email1
     */
    public String getEmail1()
    {
        return email1;
    }

    /**
     * @return email2
     */
    public String getEmail2()
    {
        return email2;
    }

    /**
     * @return email3
     */
    public String getEmail3()
    {
        return email3;
    }

    /**
     * @return faxLocation
     */
    public String getFaxLocation()
    {
        return faxLocation;
    }

    /**
     * @return faxNum
     */
    public PhoneNumber getFaxNum()
    {
        return faxNum;
    }

    /**
     * @return homePhoneNum
     */
    public PhoneNumber getHomePhoneNum()
    {
        return homePhoneNum;
    }

    /**
     * @return pager
     */
    public PhoneNumber getPager()
    {
        return pager;
    }

    /**
     * @return race
     */
    public String getRace()
    {
        return race;
    }

    /**
     * @return raceId
     */
    public String getRaceId()
    {
        return raceId;
    }

    /**
     * @return relationshipToJuvenile
     */
    public String getRelationshipToJuvenile()
    {
        return relationshipToJuvenile;
    }

    /**
     * @return relationshipToJuvenileId
     */
    public String getRelationshipToJuvenileId()
    {
        return relationshipToJuvenileId;
    }

    /**
     * @return sex
     */
    public String getSex()
    {
        return sex;
    }

    /**
     * @return sexId
     */
    public String getSexId()
    {
        return sexId;
    }

    /**
     * @return ssn
     */
    public SocialSecurity getSsn()
    {
        return ssn;
    }

    /**
     * @return warrantNum
     */
    public String getWarrantNum()
    {
        return warrantNum;
    }

    /**
     * @return workPhoneNum
     */
    public PhoneNumber getWorkPhoneNum()
    {
        return workPhoneNum;
    }

    /**
     * @param collection
     */
    public void setAssociateAddresses(List collection)
    {
        associateAddresses = collection;
    }

    /**
     * @param name
     */
    public void setAssociateName(Name name)
    {
        associateName = name;
    }

    /**
     * @param string
     */
    public void setAssociateNum(String string)
    {
        associateNum = string;
    }

    /**
     * @param number
     */
    public void setCellPhoneNum(PhoneNumber number)
    {
        cellPhoneNum = number;
    }

    /**
     * @param string
     */
    public void setDateOfBirth(Date date)
    {
        dateOfBirth = date;
    }

    /**
     * @param string
     */
    public void setEmail1(String string)
    {
        email1 = string;
    }

    /**
     * @param string
     */
    public void setEmail2(String string)
    {
        email2 = string;
    }

    /**
     * @param string
     */
    public void setEmail3(String string)
    {
        email3 = string;
    }

    /**
     * @param string
     */
    public void setFaxLocation(String string)
    {
        faxLocation = string;
    }

    /**
     * @param number
     */
    public void setFaxNum(PhoneNumber number)
    {
        faxNum = number;
    }

    /**
     * @param number
     */
    public void setHomePhoneNum(PhoneNumber number)
    {
        homePhoneNum = number;
    }

    /**
     * @param number
     */
    public void setPager(PhoneNumber number)
    {
        pager = number;
    }

    /**
     * @param string
     */
    public void setRace(String string)
    {
        race = string;
    }

    /**
     * @param string
     */
    public void setRaceId(String string)
    {
        raceId = string;
    }
    
    public String getEthnicity()
    {
        return ethnicity;
    }
    
    public void setEthnicity(String string)
    {
        ethnicity = string;
    }
    
    public List getEthnicityList()
    {
        return ethnicityList;
    }
    
    public void setEthnicityList(List collection)
    {
        ethnicityList = collection;
    }
    
    public String getEthnicityId()
    {
        return ethnicityId;
    }
    
    public void setEthnicityId(String string)
    {
        ethnicityId = string;
        if (ethnicityId == null || ethnicityId.equals(""))
        {
            ethnicity = "";
            return;
        }
        if (this.ethnicityList != null && this.ethnicityList.size() > 0)
        {
            ethnicity = CodeHelper.getCodeDescriptionByCode(this.ethnicityList, ethnicityId);
        }
    }

    /**
     * @param string
     */
    public void setRelationshipToJuvenile(String string)
    {
        relationshipToJuvenile = string;
    }

    /**
     * @param string
     */
    public void setRelationshipToJuvenileId(String string)
    {
        relationshipToJuvenileId = string;
    }

    /**
     * @param string
     */
    public void setSex(String string)
    {
        sex = string;
    }

    /**
     * @param string
     */
    public void setSexId(String string)
    {
        sexId = string;
    }

    /**
     * @param security
     */
    public void setSsn(SocialSecurity security)
    {
        ssn = security;
    }

    /**
     * @param string
     */
    public void setWarrantNum(String string)
    {
        warrantNum = string;
    }

    /**
     * @param number
     */
    public void setWorkPhoneNum(PhoneNumber number)
    {
        workPhoneNum = number;
    }

    /**
     * @return topic
     */
    public String getTopic()
    {
        return topic;
    }

    /**
     * @param string
     */
    public void setTopic(String string)
    {
        topic = string;
    }

    /**
     * @param form
     * @return JuvenileAssociateForm
     */
    public JuvenileAssociateForm populateJuvenileAssociateForm(JuvenileAssociateForm form)
    {
        // TODO Deprecate use of PropertyCopier
        PropertyCopier.copyProperties(this, form);
        Date dob = this.getDateOfBirth();
        if (dob != null)
        {
            form.setDateOfBirthString(DateUtil.dateToString(dob, UIConstants.DATE_FMT_1));
        }
        return form;
    }

    /**
     * @param form
     */
    public void populateAssociateBean(JuvenileAssociateForm form)
    {
        this.setWarrantNum(form.getWarrantNum());
        this.setAssociateName(form.getAssociateName());
        this.setSsn(form.getSsn());
        this.setRelationshipToJuvenile(form.getRelationshipToJuvenile());
        this.setRelationshipToJuvenileId(form.getRelationshipToJuvenileId());
        this.setRace(form.getRace());
        this.setRaceId(form.getRaceId());
        this.setSex(form.getSex());
        this.setSexId(form.getSexId());
        this.setDateOfBirth(form.getDateOfBirth());
        //Contact Information
        this.setHomePhoneNum(form.getHomePhoneNum());
        this.setWorkPhoneNum(form.getWorkPhoneNum());
        this.setFaxNum(form.getFaxNum());
        this.setEmail1(form.getEmail1());
        this.setEmail2(form.getEmail2());
        this.setEmail3(form.getEmail3());
        this.setDlState(form.getDlState());
        this.setDlStateId(form.getDlStateId());
        this.setDlNumber(form.getDlNumber());
        this.setCellPhoneNum(form.getCellPhoneNum());
        this.setFaxLocation(form.getFaxLocation());
        this.setPager(form.getPager());

        // copy addressess
        List addresses = form.getAssociateAddresses();
        int len = addresses.size();
        for (int i = 0; i < len; i++)
        {
            JuvenileAssociateAddressResponseEvent addressRespEvent = (JuvenileAssociateAddressResponseEvent) addresses.get(i);
            this.insertAddress(addressRespEvent);
        }
    }

    /**
     * @param requestEvent
     */
    public void populateCreateJuvenileAssociateCompositeEvent(CreateJuvenileAssociateEvent requestEvent)
    {
        requestEvent.setWarrantNum(this.getWarrantNum());
        Name name = this.getAssociateName();
        requestEvent.setAssociateFirstName(name.getFirstName());
        requestEvent.setAssociateLastName(name.getLastName());
        requestEvent.setAssociateMiddleName(name.getMiddleName());
        requestEvent.setAssociateSsn(this.getSsn().getSSN());
        requestEvent.setRelationshipToJuvenile(this.getRelationshipToJuvenileId());
        requestEvent.setAssociateRace(this.getRaceId());
        requestEvent.setAssociateSex(this.getSexId());
        requestEvent.setAssociateDateOfBirth(this.getDateOfBirth());

        //Contact Information
        requestEvent.setHomePhone(this.getHomePhoneNum().getPhoneNumber());
        requestEvent.setWorkPhone(this.getWorkPhoneNum().getPhoneNumber());
        requestEvent.setFax(this.getFaxNum().getPhoneNumber());
        requestEvent.setEmail1(this.getEmail1());
        requestEvent.setEmail2(this.getEmail2());
        requestEvent.setEmail3(this.getEmail3());
        requestEvent.setCellPhone(this.getCellPhoneNum().getPhoneNumber());
        requestEvent.setFaxLocation(this.getFaxLocation());
        requestEvent.setPager(this.getPager().getPhoneNumber());
        requestEvent.setDlState(this.getDlStateId());
        requestEvent.setDlNumber(this.getDlNumber());

        int len = this.getAddressesAsRequestEvents().size();
        for (int i = 0; i < len; i++)
        {
            requestEvent.addRequest((JuvenileAssociateAddressRequestEvent) this.getAddressesAsRequestEvents().get(i));
        }
    }

    /**
     * @return
     */
    public String getDateOfBirthString()
    {
        return dateOfBirthString;
    }

    /**
     * @param string
     */
    public void setDateOfBirthString(String string)
    {
        dateOfBirthString = string;
    }

    /**
     * @return
     */
    public void initDateOfBirth()
    {
        if (dateOfBirthString != null && "".equals(dateOfBirthString) == false)
        {
            Date dob = DateUtil.stringToDate(dateOfBirthString, UIConstants.DATE_FMT_1);
            this.setDateOfBirth(dob);
        }
    }

    /**
     * @return
     */
    public String getDlNumber()
    {
        return dlNumber;
    }

    /**
     * @return
     */
    public String getDlState()
    {
        return dlState;
    }

    /**
     * @return
     */
    public String getDlStateId()
    {
        return dlStateId;
    }

    /**
     * @param string
     */
    public void setDlNumber(String dlState)
    {
        this.dlNumber = dlState;
    }

    /**
     * @param string
     */
    public void setDlState(String dlState)
    {
        this.dlState = dlState;
    }

    /**
     * @param string
     */
    public void setDlStateId(String dlStateId)
    {
        this.dlStateId = dlStateId;
    }

}

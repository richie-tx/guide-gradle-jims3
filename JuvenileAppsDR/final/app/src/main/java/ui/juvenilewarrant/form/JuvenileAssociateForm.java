package ui.juvenilewarrant.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import messaging.juvenilewarrant.CreateJuvenileAssociateEvent;
import messaging.juvenilewarrant.JuvenileAssociateAddressRequestEvent;
import messaging.juvenilewarrant.UpdateJuvenileAssociateEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SocialSecurity;
import ui.common.form.AddressValidationForm;

/**
 * @author ldeen
 * 
 * This form contains all the attributes needed to create, update, and retrieve
 * a juvenile warrant.
 */
public class JuvenileAssociateForm extends AddressValidationForm
{
    private String action;

    private List associateAddresses = new ArrayList();

    private Name associateName;

    private String associateNum;

    private PhoneNumber cellPhoneNum = new PhoneNumber("");

    private Date dateOfBirth;

    private String dateOfBirthString;

    private String email1;

    private String email2;

    private String email3;

    private String faxLocation;

    private PhoneNumber faxNum = new PhoneNumber("");

    private boolean isDetails = false;

    private PhoneNumber homePhoneNum = new PhoneNumber("");

    // this will store the new address that the user types in on
    // the Manage Addresses button
    private JuvenileAssociateAddressResponseEvent newAddress;

    private PhoneNumber pager = new PhoneNumber("");

    private String raceId;

    private String relationshipToJuvenileId;

    private String sexId;

    private SocialSecurity ssn = new SocialSecurity("");

    private String validAddressInd;

    private String warrantNum;

    private PhoneNumber workPhoneNum = new PhoneNumber("");

    private String countyId;

    private String dlStateId;

    private String dlNumber;
    
    private String flowInd;
    private String ethnicity;

    /**
     * @param evt
     */
    public void clear()
    {
        associateName = new Name();
        associateNum = "";
        dateOfBirth = null;
        dateOfBirthString = "";
        raceId = "";
        relationshipToJuvenileId = "";
        sexId = "";
        ssn = new SocialSecurity("");
        warrantNum = "";
        homePhoneNum = new PhoneNumber("");
        workPhoneNum = new PhoneNumber("");
        cellPhoneNum = new PhoneNumber("");
        pager = new PhoneNumber("");
        faxNum = new PhoneNumber("");
        faxLocation = "";
        email1 = "";
        email2 = "";
        email3 = "";
        newAddress = null;
        associateAddresses = new ArrayList();
        countyId = "";
        validAddressInd = "";
        dlStateId = "";
        dlNumber = "";
        this.ethnicity = "";
    }

    /**
     * @return String action
     */
    public String getAction()
    {
        return action;
    }

    /**
     * 
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

            request.setAddressTypeId(response.getAddressTypeId());

            String addressType = CodeHelper.getCodeDescriptionByCode(CodeHelper.getAddressTypeCodes(false), response
                    .getAddressTypeId());
            request.setAddressType(addressType);

            request.setAssociateNum(associateNum);
            request.setBadAddress(response.isBadAddress());
            request.setTopic(response.getTopic());
            request.setAddressId(response.getAddressId());
            request.setAddressStatus(response.getAddressStatus());
            request.setAddressMessage(response.getAddressMessage());
            requests.add(request);
        }
        return requests;
    }

    /**
     * @return Collection associateAddresses
     */
    public List getAssociateAddresses()
    {
        return associateAddresses;
    }

    /**
     * @return Name
     */
    public Name getAssociateName()
    {
        return associateName;
    }

    /**
     * @return String associateNum
     */
    public String getAssociateNum()
    {
        if (associateNum != null && "".equals(associateNum) == false)
        {
            int num = Integer.parseInt(associateNum);
            if (num < 0)
            {
                associateNum = "";
            }
        }
        return associateNum;
    }

    /**
     * @return PhoneNumber cellPhoneNum
     */
    public PhoneNumber getCellPhoneNum()
    {
        return cellPhoneNum;
    }

    /* Begin Collections for Code Tables */
    /**
     * @return Collection countyCodes
     */
    public List getCounties()
    {
        return CodeHelper.getCountyCodes(true);
    }

    /**
     * @return county
     */
    public String getCounty()
    {
        String county = CodeHelper.getCodeDescription(PDCodeTableConstants.COUNTY, this.countyId);
        return county;
    }

    /**
     * 
     * @return CreateJuvenileAssociateEvent
     */
    public CreateJuvenileAssociateEvent getCreateAssociateCompositeEvent()
    {
        CreateJuvenileAssociateEvent request = new CreateJuvenileAssociateEvent();

        request.setAssociateDateOfBirth(this.getDateOfBirth());
        request.setAssociateFirstName(associateName.getFirstName());
        request.setAssociateLastName(associateName.getLastName());
        request.setAssociateMiddleName(associateName.getMiddleName());
        request.setAssociateRace(raceId);
        request.setAssociateSex(sexId);
        request.setAssociateSsn(ssn.getSSN());
        request.setCellPhone(cellPhoneNum.getPhoneNumber());
        request.setFax(faxNum.getPhoneNumber());
        request.setFaxLocation(faxLocation);
        request.setHomePhone(homePhoneNum.getPhoneNumber());
        request.setPager(pager.getPhoneNumber());
        request.setWorkPhone(workPhoneNum.getPhoneNumber());
        request.setEmail1(email1);
        request.setEmail2(email2);
        request.setEmail3(email3);
        request.setRelationshipToJuvenile(relationshipToJuvenileId);
        request.setWarrantNum(warrantNum);
        request.setDlNumber(dlNumber);
        request.setDlState(dlStateId);

        List addresses = getAddressesAsRequestEvents();
        int len = addresses.size();
        JuvenileAssociateAddressRequestEvent addr;
        for (int i = 0; i < len; i++)
        {
            addr = (JuvenileAssociateAddressRequestEvent) addresses.get(i);
            request.addRequest(addr);
        }

        return request;
    }

    /**
     * @return dateOfBirth
     */
    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * @return String email1
     */
    public String getEmail1()
    {
        return email1;
    }

    /**
     * @return String email2
     */
    public String getEmail2()
    {
        return email2;
    }

    /**
     * @return String email3
     */
    public String getEmail3()
    {
        return email3;
    }

    /**
     * @return String faxLocation
     */
    public String getFaxLocation()
    {
        return faxLocation;
    }

    /**
     * @return PhoneNumber faxNum
     */
    public PhoneNumber getFaxNum()
    {
        return faxNum;
    }

    /**
     * @return PhoneNumber homePhoneNum
     */
    public PhoneNumber getHomePhoneNum()
    {
        return homePhoneNum;
    }

    /**
     * @return JuvenileAssociateAddressResponseEvent newAddress
     */
    public JuvenileAssociateAddressResponseEvent getNewAddress()
    {
        if (newAddress == null)
        {
            newAddress = new JuvenileAssociateAddressResponseEvent();

            // TODO Refactor with code table reference
            newAddress.setAddressStatus("U");
        }
        return newAddress;
    }

    /**
     * @return PhoneNumber pager
     */
    public PhoneNumber getPager()
    {
        return pager;
    }

    /**
     * @return Code race
     */
    public String getRace()
    {
        String race = CodeHelper.getCodeDescriptionByCode(CodeHelper.getRaceCodes(false), this.raceId);
        return race;
    }

    /**
     * @return String raceId
     */
    public String getRaceId()
    {
        return raceId;
    }

    /**
     * @return Collection relationshipToJuvenileCodes
     */
    public List getRelationshipsToJuvenile()
    {
        return CodeHelper.getRelationshipsToJuvenileCodes(true);
    }

    /**
     * @return Code relationshipToJuvenile
     */
    public String getRelationshipToJuvenile()
    {
        String relationship = CodeHelper.getCodeDescription(PDCodeTableConstants.RELATIONSHIP_JUVENILE, relationshipToJuvenileId);
        return relationship;
    }

    /**
     * @return String ralationshipToJuvenileId
     */
    public String getRelationshipToJuvenileId()
    {
        return relationshipToJuvenileId;
    }

    /**
     * @return Code Sex
     */
    public String getSex()
    {
        String sex = CodeHelper.getCodeDescription(PDCodeTableConstants.SEX, this.sexId);
        return sex;
    }

    /**
     * @return String sexId
     */
    public String getSexId()
    {
        return sexId;
    }

    /**
     * @return SocialSecurity ssn
     */
    public SocialSecurity getSsn()
    {
        return ssn;
    }

    /**
     * 
     * @return UpdateJuvenileAssociateEvent
     */
    public UpdateJuvenileAssociateEvent getUpdateAssociateCompositeEvent()
    {
        UpdateJuvenileAssociateEvent request = new UpdateJuvenileAssociateEvent();
        request.setAssociateNumber(associateNum);
        request.setAssociateDateOfBirth(this.getDateOfBirth());
        request.setAssociateFirstName(associateName.getFirstName());
        request.setAssociateLastName(associateName.getLastName());
        request.setAssociateMiddleName(associateName.getMiddleName());
        request.setAssociateRace(raceId);
        request.setAssociateSex(sexId);
        request.setAssociateSsn(ssn.getSSN());
        request.setCellPhone(cellPhoneNum.getPhoneNumber());
        request.setFax(faxNum.getPhoneNumber());
        request.setFaxLocation(faxLocation);
        request.setHomePhone(homePhoneNum.getPhoneNumber());
        request.setPager(pager.getPhoneNumber());
        request.setWorkPhone(workPhoneNum.getPhoneNumber());
        request.setEmail1(email1);
        request.setEmail2(email2);
        request.setEmail3(email3);
        request.setRelationshipToJuvenile(relationshipToJuvenileId);
        request.setWarrantNum(warrantNum);
        request.setDlState(dlStateId);
        request.setDlNumber(dlNumber);

        List addresses = getAddressesAsRequestEvents();
        JuvenileAssociateAddressRequestEvent addr;
        int len = addresses.size();
        for(int i=0;i<len;i++)
        {
            addr = (JuvenileAssociateAddressRequestEvent) addresses.get(i);
            request.addRequest(addr);
        }

        return request;
    }

    /**
     * @return String warrantNum
     */
    public String getWarrantNum()
    {
        return warrantNum;
    }

    /**
     * @return PhoneNumber workPhoneNumber
     */
    public PhoneNumber getWorkPhoneNum()
    {
        return workPhoneNum;
    }

    /**
     * @param collection
     */
    public void insertAssociateAddress(JuvenileAssociateAddressResponseEvent evt)
    {
        associateAddresses.add(0, evt);
    }

    /**
     * @param event
     */
    public void populateAddressCodeDescriptionsFromIds(JuvenileAssociateAddressResponseEvent event)
    {
        if (event.getStreetTypeId() != null)
        {
            String streeType = CodeHelper.getCodeDescriptionByCode(CodeHelper.getStreetTypeCodes(false), event.getStreetTypeId());
            event.setStreetType(streeType);
        }

        if (event.getStateId() != null)
        {
            String state = CodeHelper.getCodeDescriptionByCode(CodeHelper.getStateCodes(false), event.getStateId());
            event.setState(state);
        }

        if (event.getAddressTypeId() != null)
        {
            String addressType = CodeHelper.getCodeDescriptionByCode(CodeHelper.getAddressTypeCodes(false), event
                    .getAddressTypeId());
            event.setAddressType(addressType);
        }

        if (event.getCountyId() != null)
        {
            String county = CodeHelper.getCodeDescriptionByCode(CodeHelper.getCountyCodes(false), event.getCountyId());
            event.setCounty(county);
        }
    }

    /**
     * @param evt
     */
    public void populateAddressValuesFromRequest(HttpServletRequest aRequest)
    {
        String[] streetNums = aRequest.getParameterValues("streetNum");
        String[] streetNames = aRequest.getParameterValues("streetName");
        String[] streetTypeIds = aRequest.getParameterValues("streetTypeId");
        String[] aptNums = aRequest.getParameterValues("aptNum");
        String[] cities = aRequest.getParameterValues("city");
        String[] stateIds = aRequest.getParameterValues("stateId");
        String[] zips = aRequest.getParameterValues("zipCode");
        String[] additionalZips = aRequest.getParameterValues("additionalZipCode");
        String[] addressTypeIds = aRequest.getParameterValues("addressTypeId");
        String[] countyIds = aRequest.getParameterValues("countyId");
        String[] addressStatuses = aRequest.getParameterValues("addressStatus");

        int index = 0;
        if (associateAddresses.size() > 0)
        {
            java.util.Iterator addrIte = associateAddresses.iterator();
            JuvenileAssociateAddressResponseEvent evt;
            while (addrIte.hasNext())
            {
                evt = (JuvenileAssociateAddressResponseEvent) addrIte.next();
                evt.setStreetNum(streetNums[index]);
                evt.setStreetName(streetNames[index]);
                evt.setStreetTypeId(streetTypeIds[index]);
                if (streetTypeIds[index] != null && !(streetTypeIds[index].equals("")))
                {
                    String streeType = CodeHelper.getCodeDescriptionByCode(CodeHelper.getStreetTypeCodes(false),
                            streetTypeIds[index]);
                    evt.setStreetType(streeType);
                }
                evt.setAptNum(aptNums[index]);
                evt.setCity(cities[index]);

                if (countyIds[index] != null && !(countyIds[index].equals("")))
                {
                    evt.setCountyId(countyIds[index]);
                    String county = CodeHelper.getCodeDescriptionByCode(CodeHelper.getCountyCodes(false), countyIds[index]);
                    evt.setCounty(county);
                }
                evt.setStateId(stateIds[index]);
                if (stateIds[index] != null && !(stateIds[index].equals("")))
                {
                    String state = CodeHelper.getCodeDescriptionByCode(CodeHelper.getStateCodes(false), stateIds[index]);
                    evt.setState(state);
                }
                evt.setZipCode(zips[index]);
                evt.setAdditionalZipCode(additionalZips[index]);
                evt.setAddressTypeId(addressTypeIds[index]);
                if (addressTypeIds[index] != null && !(addressTypeIds[index].equals("")))
                {
                    String addressType = CodeHelper.getCodeDescriptionByCode(CodeHelper.getAddressTypeCodes(false),
                            addressTypeIds[index]);
                    evt.setAddressType(addressType);
                }
                evt.setAddressStatus(addressStatuses[index]);
                index++;
            }
        }
    }

    /**
     * removeBlankAddresses
     *  
     */
    public void removeBlankAddresses()
    {
        List nonEmptyAddresses = new ArrayList();
        int len = associateAddresses.size();
        JuvenileAssociateAddressResponseEvent evt;
        for(int i=0;i<len;i++)
        {
            evt = (JuvenileAssociateAddressResponseEvent) associateAddresses.get(i);
            if (!evt.isBlank())
            {
                nonEmptyAddresses.add(evt);
            }
        }
        associateAddresses = nonEmptyAddresses;
    }

    /**
     * @see org.apache.struts.action.ActionForm#reset(ActionMapping,
     *      ServletRequest)
     */
    public void reset(ActionMapping aMapping, ServletRequest aRequest)
    {
        super.reset(aMapping, aRequest);
        clear();
    }

    /**
     * @param string
     */
    public void setAction(String string)
    {
        action = string;
    }

    /**
     * @param collection
     */
    public void setAssociateAddresses(List collection)
    {
        associateAddresses.clear();
        if (collection != null)
        {
            associateAddresses.addAll(collection);
        }
    }

    /**
     * @param name
     */
    public void setAssociateName(Name name)
    {
        associateName = new Name();
        associateName.setFirstName(name.getFirstName());
        associateName.setLastName(name.getLastName());
        associateName.setMiddleName(name.getMiddleName());
        associateName.setPrefix(name.getPrefix());
        associateName.setSuffix(name.getSuffix());
    }

    /**
     * @param string
     */
    public void setAssociateNum(String string)
    {
        associateNum = string;
    }

    /**
     * @param associateBean
     */
    public void setAssociateProperties(JuvenileAssociateBean bean)
    {
        this.setAssociateName(bean.getAssociateName());
        this.setRelationshipToJuvenileId(bean.getRelationshipToJuvenileId());
        this.setAssociateNum(bean.getAssociateNum());
        this.setDateOfBirth(bean.getDateOfBirth());
        this.setSexId(bean.getSexId());
        this.setSsn(bean.getSsn());
        this.setHomePhoneNum(bean.getHomePhoneNum());
        this.setWorkPhoneNum(bean.getWorkPhoneNum());
        this.setCellPhoneNum(bean.getCellPhoneNum());
        this.setFaxNum(bean.getFaxNum());
        this.setFaxLocation(bean.getFaxLocation());
        this.setEmail1(bean.getEmail1());
        this.setEmail2(bean.getEmail2());
        this.setEmail3(bean.getEmail3());
        this.setDlStateId(bean.getDlStateId());
        this.setDlNumber(bean.getDlNumber());
        this.setAssociateAddresses(bean.getAssociateAddresses());
    }

    /**
     * @param number
     */
    public void setCellPhoneNum(PhoneNumber number)
    {
        if (number != null)
        {
            this.cellPhoneNum = new PhoneNumber("");
            this.cellPhoneNum.setAreaCode(number.getAreaCode());
            this.cellPhoneNum.setLast4Digit(number.getLast4Digit());
            this.cellPhoneNum.setPrefix(number.getPrefix());
            this.cellPhoneNum.set4Digit(number.get4Digit());
        }
    }

    /**
     * @param string
     */
    public void setDateOfBirth(Date date)
    {
        dateOfBirth = date;
        if (dateOfBirth != null)
        {
            this.setDateOfBirthString(DateUtil.dateToString(dateOfBirth, UIConstants.DATE_FMT_1));
        }
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
        if (number != null)
        {
            this.faxNum = new PhoneNumber("");
            this.faxNum.setAreaCode(number.getAreaCode());
            this.faxNum.setLast4Digit(number.getLast4Digit());
            this.faxNum.setPrefix(number.getPrefix());
            this.faxNum.set4Digit(number.get4Digit());
        }
    }

    /**
     * @param number
     */
    public void setHomePhoneNum(PhoneNumber number)
    {
        if (number != null)
        {
            this.homePhoneNum = (PhoneNumber) number.clone();
        }
    }

    /**
     * @param event
     */
    public void setNewAddress(JuvenileAssociateAddressResponseEvent event)
    {
        newAddress = event;
    }

    /**
     * @param number
     */
    public void setPager(PhoneNumber number)
    {
        if (number != null)
        {
            this.pager = new PhoneNumber("");
            this.pager.setAreaCode(number.getAreaCode());
            this.pager.setLast4Digit(number.getLast4Digit());
            this.pager.setPrefix(number.getPrefix());
            this.pager.set4Digit(number.get4Digit());
        }
    }

    /**
     * @param dataMap
     */
    public void setProperties(CompositeResponse aResponse)
    {
        JuvenileAssociateResponseEvent associate = (JuvenileAssociateResponseEvent) MessageUtil.filterComposite(aResponse,
                JuvenileAssociateResponseEvent.class);

        this.setAssociateNum(associate.getAssociateNum());
        this.setDateOfBirth(associate.getDateOfBirth());
        this.setEmail1(associate.getEmail1());
        this.setEmail2(associate.getEmail2());
        this.setEmail3(associate.getEmail3());

        this.setFaxLocation(associate.getFaxLocation());

        if (associate.getCellPhoneNum() != null)
        {
            PhoneNumber number = new PhoneNumber(associate.getCellPhoneNum());
            this.setCellPhoneNum(number);
        }

        if (associate.getFaxNum() != null)
        {
            PhoneNumber number = new PhoneNumber(associate.getFaxNum());
            this.setFaxNum(number);
        }

        if (associate.getHomePhoneNum() != null)
        {
            PhoneNumber number = new PhoneNumber(associate.getHomePhoneNum());
            this.setHomePhoneNum(number);
        }

        if (associate.getWorkPhoneNum() != null)
        {
            PhoneNumber number = new PhoneNumber(associate.getWorkPhoneNum());
            this.setWorkPhoneNum(number);
        }

        if (associate.getPager() != null)
        {
            PhoneNumber number = new PhoneNumber(associate.getPager());
            this.setPager(number);
        }

        Name associateName = new Name();
        associateName.setFirstName(associate.getFirstName());
        associateName.setMiddleName(associate.getMiddleName());
        associateName.setLastName(associate.getLastName());
        this.setAssociateName(associateName);

        this.setRaceId(associate.getRaceId());

        this.setRelationshipToJuvenileId(associate.getRelationshipToJuvenileId());

        this.setSexId(associate.getSexId());
        this.setDlStateId(associate.getDlStateId());
        this.setDlNumber(associate.getDlNumber());

        SocialSecurity ssn = new SocialSecurity(associate.getSsn());
        this.setSsn(ssn);

        List addresses = MessageUtil.compositeToList(aResponse, JuvenileAssociateAddressResponseEvent.class);

        this.setAssociateAddresses(addresses);
    }

    /**
     * @param string
     */
    public void setRaceId(String string)
    {
        raceId = string;
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
    public void setSexId(String string)
    {
        sexId = string;
    }

    /**
     * @param security
     */
    public void setSsn(SocialSecurity security)
    {
        if (security != null)
        {
            this.ssn = new SocialSecurity(security.toString());
        }
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
        if (number != null)
        {
            this.workPhoneNum = new PhoneNumber("");
            this.workPhoneNum.setAreaCode(number.getAreaCode());
            this.workPhoneNum.setLast4Digit(number.getLast4Digit());
            this.workPhoneNum.setPrefix(number.getPrefix());
            this.workPhoneNum.set4Digit(number.get4Digit());
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
     *  
     */
    public void initDateOfBirth()
    {
        if (this.dateOfBirthString != null && "".equals(dateOfBirthString) == false)
        {
            this.dateOfBirth = DateUtil.stringToDate(this.dateOfBirthString, UIConstants.DATE_FMT_1);
        }
    }

    /**
     * @return
     */
    public String getValidAddressInd()
    {
        return validAddressInd;
    }

    /**
     * @param string
     */
    public void setValidAddressInd(String string)
    {
        validAddressInd = string;
    }
    
    public String getEthnicity()
    {
        return ethnicity;
    }
    
    public void setEthnicity(String string)
    {
        ethnicity = string;
    }

    /**
     * @return
     */
    //	public String getValidAddrNum()
    //	{
    //		return validAddrNum;
    //	}
    /**
     * @return
     */
    //	public String getValidStreetNum()
    //	{
    //		return validStreetNum;
    //	}
    /**
     * @return
     */
    //	public String getValidStreetName()
    //	{
    //		return validStreetName;
    //	}
    /**
     * @return
     */
    //	public String getValidZipCode()
    //	{
    //		return validZipCode;
    //	}
    /**
     * @param string
     */
    //	public void setValidAddrNum(String string)
    //	{
    //		validAddrNum = string;
    //	}
    /**
     * @param string
     */
    //	public void setValidStreetNum(String string)
    //	{
    //		validStreetNum = string;
    //	}
    /**
     * @param string
     */
    //	public void setValidStreetName(String string)
    //	{
    //		validStreetName = string;
    //	}
    /**
     * @param string
     */
    //	public void setValidZipCode(String string)
    //	{
    //		validZipCode = string;
    //	}
    /**
     * @return
     */
    //	public String getInputPage()
    //	{
    //		return inputPage;
    //	}
    /**
     * @param string
     */
    //	public void setInputPage(String string)
    //	{
    //		inputPage = string;
    //	}
    /**
     * @return
     */
    //	public String getAddressStatus()
    //	{
    //		return addressStatus;
    //	}
    /**
     * @param string
     */
    //	public void setAddressStatus(String string)
    //	{
    //		addressStatus = string;
    //	}
    /**
     * @return
     */
    public boolean isDetails()
    {
        return isDetails;
    }

    /**
     * @param b
     */
    public void setDetails(boolean b)
    {
        isDetails = b;
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
    public String getDlStateId()
    {
        return dlStateId;
    }

    /**
     * @param string
     */
    public void setDlNumber(String dlNumber)
    {
        this.dlNumber = dlNumber;
    }

    /**
     * @param string
     */
    public void setDlStateId(String dlStateId)
    {
        this.dlStateId = dlStateId;
    }

    /**
     * @return Code race
     */
    public String getDlState()
    {
        String state = CodeHelper.getCodeDescriptionByCode(CodeHelper.getStateCodes(false), this.dlStateId);
        return state;
    }

	/**
	 * @return Returns the flowInd.
	 */
	public String getFlowInd() {
		return flowInd;
	}
	/**
	 * @param flowInd The flowInd to set.
	 */
	public void setFlowInd(String flowInd) {
		this.flowInd = flowInd;
	}
}

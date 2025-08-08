package pd.juvenilewarrant;

import messaging.address.reply.AddressResponseEvent;
import messaging.contact.domintf.IAddress;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.Reference;
import naming.PDAddressConstants;
import naming.PDCodeTableConstants;
import naming.PDJuvenileWarrantConstants;
import pd.codetable.Code;
import pd.codetable.person.ScarsMarksTattoosCode;
import pd.codetable.person.JuvenileSchoolDistrictCode;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author ryoung
 */
public class JuvenileOffenderTrackingFamily extends PersistentObject implements IJuvenileAssociatesInfo
{
    public AddressResponseEvent getJuvenileAddress()
    {
        AddressResponseEvent are = null;
        if (this.hasValidJuvAddress())
        {
            are = new AddressResponseEvent();
            are.setTopic(PDAddressConstants.ADDRESS_EVENT_TOPIC);
            are.setAddressStatus("U");
            are.setAdditionalZipCode(this.getJuvAdditionalZipCode());
            are.setAddressTypeCode(this.getJuvAddressTypeId());
            are.setCity(this.getJuvCity());
            are.setKeymap(this.getJuvKeymap());
            are.setStateCode(this.getJuvStateId());
            are.setStreetName(this.getJuvStreetName());
            are.setStreetNum(this.getJuvStreetNum());
            are.setZipCode(this.getJuvZipCode());
        }
        return are;
    }

    public IAddress getFatherAddress()
    {
        AddressResponseEvent are = null;
        if (this.hasValidFatherAddress())
        {
            are = new AddressResponseEvent();
            are.setTopic(PDJuvenileWarrantConstants.FATHER_ADDRESS_EVENT_TOPIC);
            are.setAddressStatus("U");
            are.setAdditionalZipCode(this.getFatherAdditionalZipCode());
            are.setAddressTypeCode(this.getFatherAddressTypeId());
            are.setCity(this.getFatherCity());
            are.setKeymap(this.getFatherKeymap());
            are.setStateCode(this.getFatherStateId());
            are.setStreetName(this.getFatherStreetName());
            are.setStreetNum(this.getFatherStreetNum());
            are.setZipCode(this.getFatherZipCode());
        }
        return are;
    }

    public IAddress getMotherAddress()
    {
        AddressResponseEvent are = null;
        if (this.hasValidMotherAddress())
        {
            are = new AddressResponseEvent();
            are.setTopic(PDJuvenileWarrantConstants.MOTHER_ADDRESS_EVENT_TOPIC);
            are.setAddressStatus("U");
            are.setAdditionalZipCode(this.getMotherAdditionalZipCode());
            are.setAddressTypeCode(this.getMotherAddressTypeId());
            are.setCity(this.getMotherCity());
            are.setKeymap(this.getMotherKeymap());
            are.setStateCode(this.getMotherStateId());
            are.setStreetName(this.getMotherStreetName());
            are.setStreetNum(this.getMotherStreetNum());
            are.setZipCode(this.getMotherZipCode());
        }
        return are;
    }

    public IAddress getOtherAddress()
    {
        AddressResponseEvent are = null;
        if (this.hasValidOtherAddress())
        {
            are = new AddressResponseEvent();
            are.setTopic(PDJuvenileWarrantConstants.OTHER_ADDRESS_EVENT_TOPIC);
            are.setAddressStatus("U");
            are.setAdditionalZipCode(this.getOtherAdditionalZipCode());
            are.setAddressTypeCode(this.getOtherAddressTypeId());
            are.setCity(this.getOtherCity());
            are.setKeymap(this.getOtherKeymap());
            are.setStateCode(this.getOtherStateId());
            are.setStreetName(this.getOtherStreetName());
            are.setStreetNum(this.getOtherStreetNum());
            are.setZipCode(this.getOtherZipCode());
        }
        return are;
    }

    private boolean hasValidJuvAddress()
    {
        boolean valid = false;

        if (juvAdditionalZipCode != null || juvAddressTypeId != null || juvCity != null || juvKeymap != null
                || juvStateId != null || juvStreetName != null || juvStreetNum != null || juvZipCode != null)
        {
            valid = true;
        }

        return valid;
    }

    private boolean hasValidFatherAddress()
    {
        boolean valid = false;

        if (fatherAdditionalZipCode != null || fatherAddressTypeId != null || fatherCity != null || fatherKeymap != null
                || fatherStateId != null || fatherStreetName != null || fatherStreetNum != null || fatherZipCode != null)
        {
            valid = true;
        }

        return valid;
    }

    private boolean hasValidMotherAddress()
    {
        boolean valid = false;

        if (motherAdditionalZipCode != null || motherAddressTypeId != null || motherCity != null || motherKeymap != null
                || motherStateId != null || motherStreetName != null || motherStreetNum != null || motherZipCode != null)
        {
            valid = true;
        }

        return valid;
    }

    private boolean hasValidOtherAddress()
    {
        boolean valid = false;

        if (otherAdditionalZipCode != null || otherAddressTypeId != null || otherCity != null || otherKeymap != null
                || otherStateId != null || otherStreetName != null || otherStreetNum != null || otherZipCode != null)
        {
            valid = true;
        }

        return valid;
    }

    /**
     * @return Returns the fatherAdditionalZipCode.
     */
    public String getFatherAdditionalZipCode()
    {
        fetch();
        return fatherAdditionalZipCode;
    }

    /**
     * @param fatherAdditionalZipCode
     *            The fatherAdditionalZipCode to set.
     */
    public void setFatherAdditionalZipCode(String aFatherAdditionalZipCode)
    {
        if (this.fatherAdditionalZipCode == null || !this.fatherAdditionalZipCode.equals(aFatherAdditionalZipCode))
        {
            markModified();
        }
        this.fatherAdditionalZipCode = aFatherAdditionalZipCode;
    }

    /**
     * @return Returns the fatherAddressTypeId.
     */
    public String getFatherAddressTypeId()
    {
        fetch();
        return fatherAddressTypeId;
    }

    /**
     * @param fatherAddressTypeId
     *            The fatherAddressTypeId to set.
     */
    public void setFatherAddressTypeId(String aFatherAddressTypeId)
    {
        if (this.fatherAddressTypeId == null || !this.fatherAddressTypeId.equals(aFatherAddressTypeId))
        {
            markModified();
        }
        this.fatherAddressTypeId = aFatherAddressTypeId;
    }

    /**
     * @return Returns the fatherCity.
     */
    public String getFatherCity()
    {
        fetch();
        return fatherCity;
    }

    /**
     * @param fatherCity
     *            The fatherCity to set.
     */
    public void setFatherCity(String aFatherCity)
    {
        if (this.fatherCity == null || !this.fatherCity.equals(aFatherCity))
        {
            markModified();
        }
        this.fatherCity = aFatherCity;
    }

    /**
     * @return Returns the fatherKeymap.
     */
    public String getFatherKeymap()
    {
        fetch();
        return fatherKeymap;
    }

    /**
     * @param fatherKeymap
     *            The fatherKeymap to set.
     */
    public void setFatherKeymap(String aFatherKeymap)
    {
        if (this.fatherKeymap == null || !this.fatherKeymap.equals(aFatherKeymap))
        {
            markModified();
        }
        this.fatherKeymap = aFatherKeymap;
    }

    /**
     * @return Returns the fatherStateId.
     */
    public String getFatherStateId()
    {
        fetch();
        return fatherStateId;
    }

    /**
     * @param fatherStateId
     *            The fatherStateId to set.
     */
    public void setFatherStateId(String aFatherStateId)
    {
        if (this.fatherStateId == null || !this.fatherStateId.equals(aFatherStateId))
        {
            markModified();
        }
        this.fatherStateId = aFatherStateId;
    }

    /**
     * @return Returns the fatherStreetName.
     */
    public String getFatherStreetName()
    {
        fetch();
        return fatherStreetName;
    }

    /**
     * @param fatherStreetName
     *            The fatherStreetName to set.
     */
    public void setFatherStreetName(String aFatherStreetName)
    {
        if (this.fatherStreetName == null || !this.fatherStreetName.equals(aFatherStreetName))
        {
            markModified();
        }
        this.fatherStreetName = aFatherStreetName;
    }

    /**
     * @return Returns the fatherStreetNum.
     */
    public String getFatherStreetNum()
    {
        fetch();
        return fatherStreetNum;
    }

    /**
     * @param fatherStreetNum
     *            The fatherStreetNum to set.
     */
    public void setFatherStreetNum(String aFatherStreetNum)
    {
        if (this.fatherStreetNum == null || !this.fatherStreetNum.equals(aFatherStreetNum))
        {
            markModified();
        }
        this.fatherStreetNum = aFatherStreetNum;
    }

    /**
     * @return Returns the fatherZipCode.
     */
    public String getFatherZipCode()
    {
        fetch();
        return fatherZipCode;
    }

    /**
     * @param fatherZipCode
     *            The fatherZipCode to set.
     */
    public void setFatherZipCode(String aFatherZipCode)
    {
        if (this.fatherZipCode == null || !this.fatherZipCode.equals(aFatherZipCode))
        {
            markModified();
        }
        this.fatherZipCode = aFatherZipCode;
    }

    /**
     * @return Returns the juvAdditionalZipCode.
     */
    public String getJuvAdditionalZipCode()
    {
        fetch();
        return juvAdditionalZipCode;
    }

    /**
     * @param juvAdditionalZipCode
     *            The juvAdditionalZipCode to set.
     */
    public void setJuvAdditionalZipCode(String aJuvAdditionalZipCode)
    {
        if (this.juvAdditionalZipCode == null || !this.juvAdditionalZipCode.equals(aJuvAdditionalZipCode))
        {
            markModified();
        }
        this.juvAdditionalZipCode = aJuvAdditionalZipCode;
    }

    /**
     * @return Returns the juvAddressTypeId.
     */
    public String getJuvAddressTypeId()
    {
        fetch();
        return juvAddressTypeId;
    }

    /**
     * @param juvAddressTypeId
     *            The juvAddressTypeId to set.
     */
    public void setJuvAddressTypeId(String aJuvAddressTypeId)
    {
        if (this.juvAddressTypeId == null || !this.juvAddressTypeId.equals(aJuvAddressTypeId))
        {
            markModified();
        }
        this.juvAddressTypeId = aJuvAddressTypeId;
    }

    /**
     * @return Returns the juvCity.
     */
    public String getJuvCity()
    {
        fetch();
        return juvCity;
    }

    /**
     * @param juvCity
     *            The juvCity to set.
     */
    public void setJuvCity(String aJuvCity)
    {
        if (this.juvCity == null || !this.juvCity.equals(aJuvCity))
        {
            markModified();
        }
        this.juvCity = aJuvCity;
    }

    /**
     * @return Returns the juvKeymap.
     */
    public String getJuvKeymap()
    {
        fetch();
        return juvKeymap;
    }

    /**
     * @param juvKeymap
     *            The juvKeymap to set.
     */
    public void setJuvKeymap(String aJuvKeymap)
    {
        if (this.juvKeymap == null || !this.juvKeymap.equals(aJuvKeymap))
        {
            markModified();
        }
        this.juvKeymap = aJuvKeymap;
    }

    /**
     * @return Returns the juvStateId.
     */
    public String getJuvStateId()
    {
        fetch();
        return juvStateId;
    }

    /**
     * @param juvStateId
     *            The juvStateId to set.
     */
    public void setJuvStateId(String aJuvStateId)
    {
        if (this.juvStateId == null || !this.juvStateId.equals(aJuvStateId))
        {
            markModified();
        }
        this.juvStateId = aJuvStateId;
    }

    /**
     * @return Returns the juvStreetName.
     */
    public String getJuvStreetName()
    {
        fetch();
        return juvStreetName;
    }

    /**
     * @param juvStreetName
     *            The juvStreetName to set.
     */
    public void setJuvStreetName(String aJuvStreetName)
    {
        if (this.juvStreetName == null || !this.juvStreetName.equals(aJuvStreetName))
        {
            markModified();
        }
        this.juvStreetName = aJuvStreetName;
    }

    /**
     * @return Returns the juvStreetNum.
     */
    public String getJuvStreetNum()
    {
        fetch();
        return juvStreetNum;
    }

    /**
     * @param juvStreetNum
     *            The juvStreetNum to set.
     */
    public void setJuvStreetNum(String aJuvStreetNum)
    {
        if (this.juvStreetNum == null || !this.juvStreetNum.equals(aJuvStreetNum))
        {
            markModified();
        }
        this.juvStreetNum = aJuvStreetNum;
    }

    /**
     * @return Returns the juvZipCode.
     */
    public String getJuvZipCode()
    {
        fetch();
        return juvZipCode;
    }

    /**
     * @param juvZipCode
     *            The juvZipCode to set.
     */
    public void setJuvZipCode(String aJuvZipCode)
    {
        if (this.juvZipCode == null || !this.juvZipCode.equals(aJuvZipCode))
        {
            markModified();
        }
        this.juvZipCode = aJuvZipCode;
    }

    /**
     * @return Returns the motherAdditionalZipCode.
     */
    public String getMotherAdditionalZipCode()
    {
        fetch();
        return motherAdditionalZipCode;
    }

    /**
     * @param motherAdditionalZipCode
     *            The motherAdditionalZipCode to set.
     */
    public void setMotherAdditionalZipCode(String aMotherAdditionalZipCode)
    {
        if (this.motherAdditionalZipCode == null || !this.motherAdditionalZipCode.equals(aMotherAdditionalZipCode))
        {
            markModified();
        }
        this.motherAdditionalZipCode = aMotherAdditionalZipCode;
    }

    /**
     * @return Returns the motherAddressTypeId.
     */
    public String getMotherAddressTypeId()
    {
        fetch();
        return motherAddressTypeId;
    }

    /**
     * @param motherAddressTypeId
     *            The motherAddressTypeId to set.
     */
    public void setMotherAddressTypeId(String aMotherAddressTypeId)
    {
        if (this.motherAddressTypeId == null || !this.motherAddressTypeId.equals(aMotherAddressTypeId))
        {
            markModified();
        }
        this.motherAddressTypeId = aMotherAddressTypeId;
    }

    /**
     * @return Returns the motherCity.
     */
    public String getMotherCity()
    {
        fetch();
        return motherCity;
    }

    /**
     * @param motherCity
     *            The motherCity to set.
     */
    public void setMotherCity(String aMotherCity)
    {
        if (this.motherKeymap == null || !this.motherKeymap.equals(aMotherCity))
        {
            markModified();
        }
        this.motherCity = aMotherCity;
    }

    /**
     * @return Returns the motherKeymap.
     */
    public String getMotherKeymap()
    {
        fetch();
        return motherKeymap;
    }

    /**
     * @param motherKeymap
     *            The motherKeymap to set.
     */
    public void setMotherKeymap(String aMotherKeymap)
    {
        if (this.motherKeymap == null || !this.motherKeymap.equals(aMotherKeymap))
        {
            markModified();
        }
        this.motherKeymap = aMotherKeymap;
    }

    /**
     * @return Returns the motherStateId.
     */
    public String getMotherStateId()
    {
        fetch();
        return motherStateId;
    }

    /**
     * @param motherStateId
     *            The motherStateId to set.
     */
    public void setMotherStateId(String aMotherStateId)
    {
        if (this.motherStateId == null || !this.motherStateId.equals(aMotherStateId))
        {
            markModified();
        }
        this.motherStateId = aMotherStateId;
    }

    /**
     * @return Returns the motherStreetName.
     */
    public String getMotherStreetName()
    {
        fetch();
        return motherStreetName;
    }

    /**
     * @param motherStreetName
     *            The motherStreetName to set.
     */
    public void setMotherStreetName(String aMotherStreetName)
    {
        if (this.motherStreetName == null || !this.motherStreetName.equals(aMotherStreetName))
        {
            markModified();
        }
        this.motherStreetName = aMotherStreetName;
    }

    /**
     * @return Returns the motherStreetNum.
     */
    public String getMotherStreetNum()
    {
        fetch();
        return motherStreetNum;
    }

    /**
     * @param motherStreetNum
     *            The motherStreetNum to set.
     */
    public void setMotherStreetNum(String aMotherStreetNum)
    {
        if (this.motherStreetNum == null || !this.motherStreetNum.equals(aMotherStreetNum))
        {
            markModified();
        }
        this.motherStreetNum = aMotherStreetNum;
    }

    /**
     * @return Returns the motherZipCode.
     */
    public String getMotherZipCode()
    {
        fetch();
        return motherZipCode;
    }

    /**
     * @param motherZipCode
     *            The motherZipCode to set.
     */
    public void setMotherZipCode(String aMotherZipCode)
    {
        if (this.motherZipCode == null || !this.motherZipCode.equals(aMotherZipCode))
        {
            markModified();
        }
        this.motherZipCode = aMotherZipCode;
    }

    /**
     * @return Returns the otherAdditionalZipCode.
     */
    public String getOtherAdditionalZipCode()
    {
        fetch();
        return otherAdditionalZipCode;
    }

    /**
     * @param otherAdditionalZipCode
     *            The otherAdditionalZipCode to set.
     */
    public void setOtherAdditionalZipCode(String aOtherAdditionalZipCode)
    {
        if (this.otherAdditionalZipCode == null || !this.otherAdditionalZipCode.equals(aOtherAdditionalZipCode))
        {
            markModified();
        }
        this.otherAdditionalZipCode = aOtherAdditionalZipCode;
    }

    /**
     * @return Returns the otherAddressTypeId.
     */
    public String getOtherAddressTypeId()
    {
        fetch();
        return otherAddressTypeId;
    }

    /**
     * @param otherAddressTypeId
     *            The otherAddressTypeId to set.
     */
    public void setOtherAddressTypeId(String aOtherAddressTypeId)
    {
        if (this.otherAddressTypeId == null || !this.otherAddressTypeId.equals(aOtherAddressTypeId))
        {
            markModified();
        }
        this.otherAddressTypeId = aOtherAddressTypeId;
    }

    /**
     * @return Returns the otherCity.
     */
    public String getOtherCity()
    {
        fetch();
        return otherCity;
    }

    /**
     * @param otherCity
     *            The otherCity to set.
     */
    public void setOtherCity(String aOtherCity)
    {
        if (this.otherCity == null || !this.otherCity.equals(aOtherCity))
        {
            markModified();
        }
        this.otherCity = aOtherCity;
    }

    /**
     * @return Returns the otherKeymap.
     */
    public String getOtherKeymap()
    {
        fetch();
        return otherKeymap;
    }

    /**
     * @param otherKeymap
     *            The otherKeymap to set.
     */
    public void setOtherKeymap(String aOtherKeymap)
    {
        if (this.otherKeymap == null || !this.otherKeymap.equals(aOtherKeymap))
        {
            markModified();
        }
        this.otherKeymap = aOtherKeymap;
    }

    /**
     * @return Returns the otherStateId.
     */
    public String getOtherStateId()
    {
        fetch();
        return otherStateId;
    }

    /**
     * @param otherStateId
     *            The otherStateId to set.
     */
    public void setOtherStateId(String aOtherStateId)
    {
        if (this.otherStateId == null || !this.otherStateId.equals(aOtherStateId))
        {
            markModified();
        }
        this.otherStateId = aOtherStateId;
    }

    /**
     * @return Returns the otherStreetName.
     */
    public String getOtherStreetName()
    {
        fetch();
        return otherStreetName;
    }

    /**
     * @param otherStreetName
     *            The otherStreetName to set.
     */
    public void setOtherStreetName(String aOtherStreetName)
    {
        if (this.otherStreetName == null || !this.otherStreetName.equals(aOtherStreetName))
        {
            markModified();
        }
        this.otherStreetName = aOtherStreetName;
    }

    /**
     * @return Returns the otherStreetNum.
     */
    public String getOtherStreetNum()
    {
        fetch();
        return otherStreetNum;
    }

    /**
     * @param otherStreetNum
     *            The otherStreetNum to set.
     */
    public void setOtherStreetNum(String aOtherStreetNum)
    {
        if (this.otherStreetNum == null || !this.otherStreetNum.equals(aOtherStreetNum))
        {
            markModified();
        }
        this.otherStreetNum = aOtherStreetNum;
    }

    /**
     * @return Returns the otherZipCode.
     */
    public String getOtherZipCode()
    {
        fetch();
        return otherZipCode;
    }

    /**
     * @param otherZipCode
     *            The otherZipCode to set.
     */
    public void setOtherZipCode(String aOtherZipCode)
    {
        if (this.otherZipCode == null || !this.otherZipCode.equals(aOtherZipCode))
        {
            markModified();
        }
        this.otherZipCode = aOtherZipCode;
    }

    /**
     * @param charges
     *            The charges to set.
     */
    public void setCharges(java.util.Collection charges)
    {
        this.charges = charges;
    }

    /**
     * @param summaryOfFacts
     *            The summaryOfFacts to set.
     */
    public void setSummaryOfFacts(java.util.Collection summaryOfFacts)
    {
        this.summaryOfFacts = summaryOfFacts;
    }

    private String juvStreetNum;

    private String juvStreetName;

    private String juvZipCode;

    private String juvCity;

    private String juvKeymap;

    private String juvStateId;

    private String juvAddressTypeId;

    private String juvAdditionalZipCode;

    private String motherStreetNum;

    private String motherStreetName;

    private String motherZipCode;

    private String motherCity;

    private String motherKeymap;

    private String motherStateId;

    private String motherAddressTypeId;

    private String motherAdditionalZipCode;

    private String fatherStreetNum;

    private String fatherStreetName;

    private String fatherZipCode;

    private String fatherCity;

    private String fatherKeymap;

    private String fatherStateId;

    private String fatherAddressTypeId;

    private String fatherAdditionalZipCode;

    private String otherStreetNum;

    private String otherStreetName;

    private String otherZipCode;

    private String otherCity;

    private String otherKeymap;

    private String otherStateId;

    private String otherAddressTypeId;

    private String otherAdditionalZipCode;

    private String complexionId;

    private String fathersFirstName;

    private String fathersPhoneNum;

    private String height;

    private String mothersEmployer;

    private String otherPhoneNum;

    /**
     * Properties for charges
     * 
     * @referencedType pd.juvenilewarrant.JuvenileOffenderTrackingCharge
     * @detailerDoNotGenerate true
     */
    private java.util.Collection charges = null;

    private String mothersSsn;

    private String otherFirstName;

    /**
     * Properties for race
     * 
     * @referencedType pd.codetable.Code
     * @contextKey RACE
     * @detailerDoNotGenerate true
     */
    private Code race = null;

    private String otherLastName;

    private String fathersLastName;

    private String sexId;

    private String sidNum;

    private String mothersFirstName;

    private String phoneNum;

    /**
     * Properties for hairColor
     * 
     * @referencedType pd.codetable.Code
     * @contextKey HAIR_COLOR
     * @detailerDoNotGenerate true
     */
    private Code hairColor = null;

    /**
     * Properties for schoolName
     * 
     * @referencedType pd.codetable.person.JuvenileSchoolDistrictCode
     * @detailerDoNotGenerate true
     */
    private JuvenileSchoolDistrictCode schoolName = null;

    private String raceId;

    /**
     * Properties for summaryOfFacts
     * 
     * @referencedType pd.juvenilewarrant.JuvenileOffenderTrackingSummaryOfFacts
     * @detailerDoNotGenerate true
     */
    private java.util.Collection summaryOfFacts = null;

    /**
     * Properties for sex
     * 
     * @referencedType pd.codetable.Code
     * @contextKey SEX
     * @detailerDoNotGenerate true
     */
    private Code sex = null;

    private String lastName;

    /**
     * Properties for complexion
     * 
     * @referencedType pd.codetable.Code
     * @contextKey SKIN_TONE
     * @detailerDoNotGenerate true
     */
    private Code complexion = null;

    private String otherSsn;

    private String mothersEmployerPhoneNum;

    private String mothersPhoneNum;

    private String fbiNum;

    private String otherEmployer;

    private String fathersEmployer;

    private String fathersEmployerPhoneNum;

    private String hairColorId;

    /**
     * Properties for eyeColor
     * 
     * @referencedType pd.codetable.Code
     * @contextKey EYE_COLOR
     * @detailerDoNotGenerate true
     */
    private Code eyeColor = null;

    private String otherMiddleName;

    private String transactionNum;

    private String otherEmployerPhoneNum;

    private String scarsMarksId;

    private String fathersSsn;

    private String middleName;

    private String schoolCodeId;

    /**
     * Properties for scarsMarks
     * 
     * @referencedType pd.codetable.person.ScarsMarksTattoosCode
     * @detailerDoNotGenerate true
     */
    private ScarsMarksTattoosCode scarsMarks = null;

    private String mothersLastName;

    private String firstName;

    /**
     * Properties for build
     * 
     * @referencedType pd.codetable.Code
     * @contextKey BUILD
     * @detailerDoNotGenerate true
     */
    private Code build = null;

    private String aliasName;
    
    private Date arrestDate;
    
    private String arrestTime;
    
    private String coDefendants;

    private String weight;

    private String juvenileNum;

    private String buildId;

    private String eyeColorId;

    private String daLogNum;

    private Date dateOfBirth;

    private String mothersMiddleName;

    private String fathersMiddleName;

    private String ssn;
    
    /**
     * Poperties for Assigned Referrals
     *
     */
    private String afisNCICId;
    private String agency;
    private String broughtBack;
    private String comment1;
    private String comment2;
    private String compKnowsDef;
    private String lineupInd;
    private String oralConfess;
    private String photoArrayId;
    private String witnessKnowsDef;
    private String writtenConfess;
	private String arrestInd;

    /**
     * @roseuid 41C1DAAA01C5
     */
    public JuvenileOffenderTrackingFamily()
    {
    }

    /**
     * Clears all pd.juvenilewarrant.JuvenileOffenderTrackingCharge from class
     * relationship collection.
     */
    public void clearCharges()
    {
        initCharges();
        charges.clear();
    }

    /**
     * Clears all pd.juvenilewarrant.JuvenileOffenderTrackingSummaryOfFacts from
     * class relationship collection.
     */
    public void clearSummaryOfFacts()
    {
        initSummaryOfFacts();
        summaryOfFacts.clear();
    }

    /**
     * Access method for the aliasName property.
     * 
     * @return the current value of the aliasName property
     */
    public String getAliasName()
    {
        fetch();
        return aliasName;
    }

    /**
     * Gets referenced type pd.codetable.Code
     * 
     * @return Code build
     */
    public Code getBuild()
    {
        fetch();
        initBuild();
        return build;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String buildId
     */
    public String getBuildId()
    {
        fetch();
        return buildId;
    }

    /**
     * returns a collection of pd.juvenilewarrant.JuvenileOffenderTrackingCharge
     * 
     * @return Collection charges
     */
    public java.util.Collection getCharges()
    {
        fetch();
        initCharges();
        return charges;
    }

    /**
     * Gets referenced type pd.codetable.Code
     * 
     * @return Code complextion
     */
    public Code getComplexion()
    {
        fetch();
        initComplexion();
        return complexion;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String complexionId
     */
    public String getComplexionId()
    {
        fetch();
        return complexionId;
    }

    /**
     * Access method for the daLogNum property.
     * 
     * @return the current value of the daLogNum property
     */
    public String getDaLogNum()
    {
        fetch();
        return daLogNum;
    }

    /**
     * Access method for the dateOfBirth property.
     * 
     * @return the current value of the dateOfBirth property
     */
    public Date getDateOfBirth()
    {
        fetch();
        return dateOfBirth;
    }

    /**
     * Gets referenced type pd.codetable.Code
     * 
     * @return Code eyeColor
     */
    public Code getEyeColor()
    {
        fetch();
        initEyeColor();
        return eyeColor;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String eyeColorId
     */
    public String getEyeColorId()
    {
        fetch();
        return eyeColorId;
    }

    /**
     * Access method for the fathersEmployer property.
     * 
     * @return the current value of the fathersEmployer property
     */
    public String getFathersEmployer()
    {
        fetch();
        return fathersEmployer;
    }

    /**
     * Access method for the fathersEmployerPhoneNum property.
     * 
     * @return the current value of the fathersEmployerPhoneNum property
     */
    public String getFathersEmployerPhoneNum()
    {
        fetch();
        return fathersEmployerPhoneNum;
    }

    /**
     * Access method for the fathersFirstName property.
     * 
     * @return the current value of the fathersFirstName property
     */
    public String getFathersFirstName()
    {
        fetch();
        return fathersFirstName;
    }

    /**
     * Access method for the fathersLastName property.
     * 
     * @return the current value of the fathersLastName property
     */
    public String getFathersLastName()
    {
        fetch();
        return fathersLastName;
    }

    /**
     * Access method for the fathersMiddleName property.
     * 
     * @return the current value of the fathersMiddleName property
     */
    public String getFathersMiddleName()
    {
        fetch();
        return fathersMiddleName;
    }

    /**
     * Access method for the fathersPhoneNum property.
     * 
     * @return the current value of the fathersPhoneNum property
     */
    public String getFathersPhoneNum()
    {
        fetch();
        return fathersPhoneNum;
    }

    /**
     * Access method for the fathersSsn property.
     * 
     * @return the current value of the fathersSsn property
     */
    public String getFathersSsn()
    {
        fetch();
        return fathersSsn;
    }

    /**
     * Access method for the fbiNum property.
     * 
     * @return the current value of the fbiNum property
     */
    public String getFbiNum()
    {
        fetch();
        return fbiNum;
    }

    /**
     * Access method for the firstName property.
     * 
     * @return the current value of the firstName property
     */
    public String getFirstName()
    {
        fetch();
        return firstName;
    }

    /**
     * Gets referenced type pd.codetable.Code
     * 
     * @return Code hairColor
     */
    public Code getHairColor()
    {
        fetch();
        initHairColor();
        return hairColor;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String hairColorId
     */
    public String getHairColorId()
    {
        fetch();
        return hairColorId;
    }

    /**
     * Access method for the height property.
     * 
     * @return the current value of the height property
     */
    public String getHeight()
    {
        fetch();
        return height;
    }

    /**
     * Access method for the juvenileNum property.
     * 
     * @return the current value of the juvenileNum property
     */
    public String getJuvenileNum()
    {
        fetch();
        return juvenileNum;
    }

    /**
     * Access method for the lastName property.
     * 
     * @return the current value of the lastName property
     */
    public String getLastName()
    {
        fetch();
        return lastName;
    }

    /**
     * Access method for the middleName property.
     * 
     * @return the current value of the middleName property
     */
    public String getMiddleName()
    {
        fetch();
        return middleName;
    }

    /**
     * Access method for the mothersEmployer property.
     * 
     * @return the current value of the mothersEmployer property
     */
    public String getMothersEmployer()
    {
        fetch();
        return mothersEmployer;
    }

    /**
     * Access method for the mothersEmployerPhoneNum property.
     * 
     * @return the current value of the mothersEmployerPhoneNum property
     */
    public String getMothersEmployerPhoneNum()
    {
        fetch();
        return mothersEmployerPhoneNum;
    }

    /**
     * Access method for the mothersFirstName property.
     * 
     * @return the current value of the mothersFirstName property
     */
    public String getMothersFirstName()
    {
        fetch();
        return mothersFirstName;
    }

    /**
     * Access method for the mothersLastName property.
     * 
     * @return the current value of the mothersLastName property
     */
    public String getMothersLastName()
    {
        fetch();
        return mothersLastName;
    }

    /**
     * Access method for the mothersMiddleName property.
     * 
     * @return the current value of the mothersMiddleName property
     */
    public String getMothersMiddleName()
    {
        fetch();
        return mothersMiddleName;
    }

    /**
     * Access method for the mothersPhoneNum property.
     * 
     * @return the current value of the mothersPhoneNum property
     */
    public String getMothersPhoneNum()
    {
        fetch();
        return mothersPhoneNum;
    }

    /**
     * Access method for the mothersSsn property.
     * 
     * @return the current value of the mothersSsn property
     */
    public String getMothersSsn()
    {
        fetch();
        return mothersSsn;
    }

    /**
     * Access method for the otherEmployer property.
     * 
     * @return the current value of the otherEmployer property
     */
    public String getOtherEmployer()
    {
        fetch();
        return otherEmployer;
    }

    /**
     * Access method for the otherEmployerPhoneNum property.
     * 
     * @return the current value of the otherEmployerPhoneNum property
     */
    public String getOtherEmployerPhoneNum()
    {
        fetch();
        return otherEmployerPhoneNum;
    }

    /**
     * Access method for the otherFirstName property.
     * 
     * @return the current value of the otherFirstName property
     */
    public String getOtherFirstName()
    {
        fetch();
        return otherFirstName;
    }

    /**
     * Access method for the otherLastName property.
     * 
     * @return the current value of the otherLastName property
     */
    public String getOtherLastName()
    {
        fetch();
        return otherLastName;
    }

    /**
     * Access method for the otherMiddleName property.
     * 
     * @return the current value of the otherMiddleName property
     */
    public String getOtherMiddleName()
    {
        fetch();
        return otherMiddleName;
    }

    /**
     * Access method for the otherPhoneNum property.
     * 
     * @return the current value of the otherPhoneNum property
     */
    public String getOtherPhoneNum()
    {
        fetch();
        return otherPhoneNum;
    }

    /**
     * Access method for the otherSsn property.
     * 
     * @return the current value of the otherSsn property
     */
    public String getOtherSsn()
    {
        fetch();
        return otherSsn;
    }

    /**
     * Access method for the phoneNum property.
     * 
     * @return the current value of the phoneNum property
     */
    public String getPhoneNum()
    {
        fetch();
        return phoneNum;
    }

    /**
     * Gets referenced type pd.codetable.Code
     * 
     * @return Code race
     */
    public Code getRace()
    {
        fetch();
        initRace();
        return race;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String raceId
     */
    public String getRaceId()
    {
        fetch();
        return raceId;
    }

    /**
     * Gets referenced type pd.codetable.person.ScarsMarksTattoosCode
     * 
     * @return ScarsMarksTattoosCode scarsMarks
     */
    public ScarsMarksTattoosCode getScarsMarks()
    {
        fetch();
        initScarsMarks();
        return scarsMarks;
    }

    /**
     * Get the reference value to class ::
     * pd.codetable.person.ScarsMarksTattoosCode
     * 
     * @return String scarsMarksId
     */
    public String getScarsMarksId()
    {
        fetch();
        return scarsMarksId;
    }

    /**
     * Gets referenced type pd.codetable.person.JuvenileSchoolDistrictCode
     * 
     * @return JuvenileSchoolDistrictCode schoolName
     */
    public JuvenileSchoolDistrictCode getSchoolName()
    {
        initSchoolName();
        //initSchoolCode();
        return schoolName;
    }

    /**
     * Get the reference value to class ::
     * pd.codetable.person.JuvenileSchoolDistrictCode
     * 
     * @return String schoolCodeId
     */
    public String getSchoolCodeId()
    {
        fetch();
        return schoolCodeId;
    }

    /**
     * Gets referenced type pd.codetable.Code
     * 
     * @return Code sex
     */
    public Code getSex()
    {
        fetch();
        initSex();
        return sex;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String sexId
     */
    public String getSexId()
    {
        fetch();
        return sexId;
    }

    /**
     * Access method for the sidNum property.
     * 
     * @return the current value of the sidNum property
     */
    public String getSidNum()
    {
        fetch();
        return sidNum;
    }

    /**
     * Access method for the ssn property.
     * 
     * @return the current value of the ssn property
     */
    public String getSsn()
    {
        fetch();
        return ssn;
    }

    /**
     * returns a collection of
     * pd.juvenilewarrant.JuvenileOffenderTrackingSummaryOfFacts
     * 
     * @return Collection summaryOfFacts
     */
    public java.util.Collection getSummaryOfFacts()
    {
        fetch();
        initSummaryOfFacts();
        return summaryOfFacts;
    }

    /**
     * Access method for the transactionNum property.
     * 
     * @return the current value of the transactionNum property
     */
    public String getTransactionNum()
    {
        fetch();
        return transactionNum;
    }

    /**
     * Access method for the weight property.
     * 
     * @return the current value of the weight property
     */
    public String getWeight()
    {
        fetch();
        return weight;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initBuild()
    {
        if (build == null)
        {

            build = (Code) new mojo.km.persistence.Reference(buildId, Code.class, PDCodeTableConstants.BUILD).getObject();

        }
    }

    /**
     * Initialize class relationship implementation for
     * pd.juvenilewarrant.JuvenileOffenderTrackingCharge
     */
    private void initCharges()
    {
        if (charges == null)
        {
            if (this.getOID() == null)
            {
                new mojo.km.persistence.Home().bind(this);
            }

            charges = new mojo.km.persistence.ArrayList(JuvenileOffenderTrackingCharge.class, "daLogNum", ""
                    + getOID());

        }
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initComplexion()
    {
        if (complexion == null)
        {
            complexion = (Code) new mojo.km.persistence.Reference(complexionId, Code.class, PDCodeTableConstants.SKIN_TONE)
                    .getObject();
        }
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initEyeColor()
    {
        if (eyeColor == null)
        {
            eyeColor = (Code) new mojo.km.persistence.Reference(eyeColorId, Code.class, PDCodeTableConstants.EYE_COLOR)
                    .getObject();
        }
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initHairColor()
    {
        if (hairColor == null)
        {
            hairColor = (Code) new mojo.km.persistence.Reference(hairColorId, Code.class, PDCodeTableConstants.HAIR_COLOR)
                    .getObject();
        }
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initRace()
    {
        if (race == null)
        {
            race = (Code) new mojo.km.persistence.Reference(raceId, Code.class, PDCodeTableConstants.RACE).getObject();
        }
    }

    /**
     * Initialize class relationship to class
     * pd.codetable.person.ScarsMarksTattoosCode
     */
    private void initScarsMarks()
    {
        if (scarsMarks == null)
        {
            scarsMarks = (ScarsMarksTattoosCode) new mojo.km.persistence.Reference(scarsMarksId,
                    ScarsMarksTattoosCode.class).getObject();
        }
    }

    /**
     * Initialize class relationship to class
     * pd.codetable.person.JuvenileSchoolDistrictCode
     */
    private void initSchoolCode()
    {
        if (schoolName == null)
        {
            String sCode = StringUtils.stripStart(this.schoolCodeId, "0") ;
	    if( sCode.length()> 3 ){
		
		 String derivedOid = sCode.substring(0, 2) + "-" + sCode.substring(2);
	    
		 schoolName = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(derivedOid,
                    JuvenileSchoolDistrictCode.class).getObject();
	    }
        }
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initSex()
    {
        if (sex == null)
        {
            sex = (Code) new mojo.km.persistence.Reference(sexId, Code.class, "SEX").getObject();
        }
    }

    /**
     * Initialize class relationship implementation for
     * pd.juvenilewarrant.JuvenileOffenderTrackingSummaryOfFacts
     */
    private void initSummaryOfFacts()
    {
        if (summaryOfFacts == null)
        {
            if (this.getOID() == null)
            {
                new mojo.km.persistence.Home().bind(this);
            }

            summaryOfFacts = new mojo.km.persistence.ArrayList(JuvenileOffenderTrackingSummaryOfFacts.class,
                    "daLogNum", "" + getOID());

        }
    }

    /**
     * insert a pd.juvenilewarrant.JuvenileOffenderTrackingCharge into class
     * relationship collection.
     * 
     * @param anObject
     */
    public void insertCharges(JuvenileOffenderTrackingCharge anObject)
    {
        initCharges();
        charges.add(anObject);
    }

    /**
     * insert a pd.juvenilewarrant.JuvenileOffenderTrackingSummaryOfFacts into
     * class relationship collection.
     * 
     * @param anObject
     */
    public void insertSummaryOfFacts(JuvenileOffenderTrackingSummaryOfFacts anObject)
    {
        initSummaryOfFacts();
        summaryOfFacts.add(anObject);
    }

    /**
     * Removes a pd.juvenilewarrant.JuvenileOffenderTrackingCharge from class
     * relationship collection.
     */
    public void removeCharges(JuvenileOffenderTrackingCharge anObject)
    {
        initCharges();
        charges.remove(anObject);
    }

    /**
     * Removes a pd.juvenilewarrant.JuvenileOffenderTrackingSummaryOfFacts from
     * class relationship collection.
     * 
     * @param anObject
     */
    public void removeSummaryOfFacts(JuvenileOffenderTrackingSummaryOfFacts anObject)
    {
        initSummaryOfFacts();
        summaryOfFacts.remove(anObject);
    }

    /**
     * Sets the value of the aliasName property.
     * 
     * @param aAliasName
     *            the new value of the aliasName property
     */
    public void setAliasName(String aAliasName)
    {
        if (this.aliasName == null || !this.aliasName.equals(aAliasName))
        {
            markModified();
        }
        aliasName = aAliasName;
    }

    /**
     * set the type reference for class member build
     * 
     * @param build
     */
    public void setBuild(Code lbuild)
    {
        if (this.build == null || !this.build.equals(lbuild))
        {
            markModified();
        }
        if (build.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(lbuild);
        }
        setBuildId("" + lbuild.getOID());
        this.build = (Code) new mojo.km.persistence.Reference(lbuild).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     * 
     * @param buildId
     */
    public void setBuildId(String lbuildId)
    {
        if (this.buildId == null || !this.buildId.equals(lbuildId))
        {
            markModified();
        }
        build = null;
        this.buildId = lbuildId;
    }

    /**
     * set the type reference for class member complexion
     * 
     * @param complexion
     */
    public void setComplexion(Code lcomplexion)
    {
        if (this.complexion == null || !this.complexion.equals(lcomplexion))
        {
            markModified();
        }
        if (lcomplexion.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(lcomplexion);
        }
        setComplexionId("" + lcomplexion.getOID());
        this.complexion = (Code) new mojo.km.persistence.Reference(lcomplexion).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     * 
     * @param complexionId
     */
    public void setComplexionId(String lcomplexionId)
    {
        if (this.complexionId == null || !this.complexionId.equals(lcomplexionId))
        {
            markModified();
        }
        complexion = null;
        this.complexionId = lcomplexionId;
    }

    /**
     * Sets the value of the daLogNum property.
     * 
     * @param aDaLogNum
     *            the new value of the daLogNum property
     */
    public void setDaLogNum(String aDaLogNum)
    {
        if (this.daLogNum == null || !this.daLogNum.equals(aDaLogNum))
        {
            markModified();
        }
        daLogNum = aDaLogNum;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param aDateOfBirth
     *            the new value of the dateOfBirth property
     */
    public void setDateOfBirth(Date aDateOfBirth)
    {
        if (this.dateOfBirth == null || !this.dateOfBirth.equals(aDateOfBirth))
        {
            markModified();
        }
        dateOfBirth = aDateOfBirth;
    }

    /**
     * set the type reference for class member eyeColor
     * 
     * @param eyeColor
     */
    public void setEyeColor(Code leyeColor)
    {
        if (this.eyeColor == null || !this.eyeColor.equals(leyeColor))
        {
            markModified();
        }
        if (leyeColor.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(leyeColor);
        }
        setEyeColorId("" + leyeColor.getOID());
        this.eyeColor = (Code) new mojo.km.persistence.Reference(leyeColor).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     * 
     * @param eyeColorId
     */
    public void setEyeColorId(String leyeColorId)
    {
        if (this.eyeColorId == null || !this.eyeColorId.equals(leyeColorId))
        {
            markModified();
        }
        eyeColor = null;
        this.eyeColorId = leyeColorId;
    }

    /**
     * Sets the value of the fathersEmployer property.
     * 
     * @param aFathersEmployer
     *            the new value of the fathersEmployer property
     */
    public void setFathersEmployer(String aFathersEmployer)
    {
        if (this.fathersEmployer == null || !this.fathersEmployer.equals(aFathersEmployer))
        {
            markModified();
        }
        fathersEmployer = aFathersEmployer;
    }

    /**
     * Sets the value of the fathersEmployerPhoneNum property.
     * 
     * @param aFathersEmployerPhoneNum
     *            the new value of the fathersEmployerPhoneNum property
     */
    public void setFathersEmployerPhoneNum(String aFathersEmployerPhoneNum)
    {
        if (this.fathersEmployerPhoneNum == null || !this.fathersEmployerPhoneNum.equals(aFathersEmployerPhoneNum))
        {
            markModified();
        }
        fathersEmployerPhoneNum = aFathersEmployerPhoneNum;
    }

    /**
     * Sets the value of the fathersFirstName property.
     * 
     * @param aFathersFirstName
     *            the new value of the fathersFirstName property
     */
    public void setFathersFirstName(String aFathersFirstName)
    {
        if (this.fathersFirstName == null || !this.fathersFirstName.equals(aFathersFirstName))
        {
            markModified();
        }
        fathersFirstName = aFathersFirstName;
    }

    /**
     * Sets the value of the fathersLastName property.
     * 
     * @param aFathersLastName
     *            the new value of the fathersLastName property
     */
    public void setFathersLastName(String aFathersLastName)
    {
        if (this.fathersLastName == null || !this.fathersLastName.equals(aFathersLastName))
        {
            markModified();
        }
        fathersLastName = aFathersLastName;
    }

    /**
     * Sets the value of the fathersMiddleName property.
     * 
     * @param aFathersMiddleName
     *            the new value of the fathersMiddleName property
     */
    public void setFathersMiddleName(String aFathersMiddleName)
    {
        if (this.fathersMiddleName == null || !this.fathersMiddleName.equals(aFathersMiddleName))
        {
            markModified();
        }
        fathersMiddleName = aFathersMiddleName;
    }

    /**
     * Sets the value of the fathersPhoneNum property.
     * 
     * @param aFathersPhoneNum
     *            the new value of the fathersPhoneNum property
     */
    public void setFathersPhoneNum(String aFathersPhoneNum)
    {
        if (this.fathersPhoneNum == null || !this.fathersPhoneNum.equals(aFathersPhoneNum))
        {
            markModified();
        }
        fathersPhoneNum = aFathersPhoneNum;
    }

    /**
     * Sets the value of the fathersSsn property.
     * 
     * @param aFathersSsn
     *            the new value of the fathersSsn property
     */
    public void setFathersSsn(String aFathersSsn)
    {
        if (this.fathersSsn == null || !this.fathersSsn.equals(aFathersSsn))
        {
            markModified();
        }
        fathersSsn = aFathersSsn;
    }

    /**
     * Sets the value of the fbiNum property.
     * 
     * @param aFbiNum
     *            the new value of the fbiNum property
     */
    public void setFbiNum(String aFbiNum)
    {
        if (this.fbiNum == null || !this.fbiNum.equals(aFbiNum))
        {
            markModified();
        }
        fbiNum = aFbiNum;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param aFirstName
     *            the new value of the firstName property
     */
    public void setFirstName(String aFirstName)
    {
        if (this.firstName == null || !this.firstName.equals(aFirstName))
        {
            markModified();
        }
        firstName = aFirstName;
    }

    /**
     * set the type reference for class member hairColor
     * 
     * @param hairColor
     */
    public void setHairColor(Code lhairColor)
    {
        if (this.hairColor == null || !this.hairColor.equals(lhairColor))
        {
            markModified();
        }
        if (lhairColor.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(lhairColor);
        }
        setHairColorId("" + lhairColor.getOID());
        this.hairColor = (Code) new mojo.km.persistence.Reference(lhairColor).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     * 
     * @param hairColorId
     */
    public void setHairColorId(String lhairColorId)
    {
        if (this.hairColorId == null || !this.hairColorId.equals(lhairColorId))
        {
            markModified();
        }
        hairColor = null;
        this.hairColorId = lhairColorId;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param aHeight
     *            the new value of the height property
     */
    public void setHeight(String aHeight)
    {
        if (this.height == null || !this.height.equals(aHeight))
        {
            markModified();
        }
        height = aHeight;
    }

    /**
     * Sets the value of the juvenileNum property.
     * 
     * @param aJuvenileNum
     *            the new value of the juvenileNum property
     */
    public void setJuvenileNum(String aJuvenileNum)
    {
        if (this.juvenileNum == null || !this.juvenileNum.equals(aJuvenileNum))
        {
            markModified();
        }
        juvenileNum = aJuvenileNum;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param aLastName
     *            the new value of the lastName property
     */
    public void setLastName(String aLastName)
    {
        if (this.lastName == null || !this.lastName.equals(aLastName))
        {
            markModified();
        }
        lastName = aLastName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param aMiddleName
     *            the new value of the middleName property
     */
    public void setMiddleName(String aMiddleName)
    {
        if (this.middleName == null || !this.middleName.equals(aMiddleName))
        {
            markModified();
        }
        middleName = aMiddleName;
    }

    /**
     * Sets the value of the mothersEmployer property.
     * 
     * @param aMothersEmployer
     *            the new value of the mothersEmployer property
     */
    public void setMothersEmployer(String aMothersEmployer)
    {
        if (this.mothersEmployer == null || !this.mothersEmployer.equals(aMothersEmployer))
        {
            markModified();
        }
        mothersEmployer = aMothersEmployer;
    }

    /**
     * Sets the value of the mothersEmployerPhoneNum property.
     * 
     * @param aMothersEmployerPhoneNum
     *            the new value of the mothersEmployerPhoneNum property
     */
    public void setMothersEmployerPhoneNum(String aMothersEmployerPhoneNum)
    {
        if (this.mothersEmployerPhoneNum == null || !this.mothersEmployerPhoneNum.equals(aMothersEmployerPhoneNum))
        {
            markModified();
        }
        mothersEmployerPhoneNum = aMothersEmployerPhoneNum;
    }

    /**
     * Sets the value of the mothersFirstName property.
     * 
     * @param aMothersFirstName
     *            the new value of the mothersFirstName property
     */
    public void setMothersFirstName(String aMothersFirstName)
    {
        if (this.mothersFirstName == null || !this.mothersFirstName.equals(aMothersFirstName))
        {
            markModified();
        }
        mothersFirstName = aMothersFirstName;
    }

    /**
     * Sets the value of the mothersLastName property.
     * 
     * @param aMothersLastName
     *            the new value of the mothersLastName property
     */
    public void setMothersLastName(String aMothersLastName)
    {
        if (this.mothersLastName == null || !this.mothersLastName.equals(aMothersLastName))
        {
            markModified();
        }
        mothersLastName = aMothersLastName;
    }

    /**
     * Sets the value of the mothersMiddleName property.
     * 
     * @param aMothersMiddleName
     *            the new value of the mothersMiddleName property
     */
    public void setMothersMiddleName(String aMothersMiddleName)
    {
        if (this.mothersMiddleName == null || !this.mothersMiddleName.equals(aMothersMiddleName))
        {
            markModified();
        }
        mothersMiddleName = aMothersMiddleName;
    }

    /**
     * Sets the value of the mothersPhoneNum property.
     * 
     * @param aMothersPhoneNum
     *            the new value of the mothersPhoneNum property
     */
    public void setMothersPhoneNum(String aMothersPhoneNum)
    {
        if (this.mothersPhoneNum == null || !this.mothersPhoneNum.equals(aMothersPhoneNum))
        {
            markModified();
        }
        mothersPhoneNum = aMothersPhoneNum;
    }

    /**
     * Sets the value of the mothersSsn property.
     * 
     * @param aMothersSsn
     *            the new value of the mothersSsn property
     */
    public void setMothersSsn(String aMothersSsn)
    {
        if (this.mothersSsn == null || !this.mothersSsn.equals(aMothersSsn))
        {
            markModified();
        }
        mothersSsn = aMothersSsn;
    }

    /**
     * Sets the value of the otherEmployer property.
     * 
     * @param aOtherEmployer
     *            the new value of the otherEmployer property
     */
    public void setOtherEmployer(String aOtherEmployer)
    {
        if (this.otherEmployer == null || !this.otherEmployer.equals(aOtherEmployer))
        {
            markModified();
        }
        otherEmployer = aOtherEmployer;
    }

    /**
     * Sets the value of the otherEmployerPhoneNum property.
     * 
     * @param aOtherEmployerPhoneNum
     *            the new value of the otherEmployerPhoneNum property
     */
    public void setOtherEmployerPhoneNum(String aOtherEmployerPhoneNum)
    {
        if (this.otherEmployerPhoneNum == null || !this.otherEmployerPhoneNum.equals(aOtherEmployerPhoneNum))
        {
            markModified();
        }
        otherEmployerPhoneNum = aOtherEmployerPhoneNum;
    }

    /**
     * Sets the value of the otherFirstName property.
     * 
     * @param aOtherFirstName
     *            the new value of the otherFirstName property
     */
    public void setOtherFirstName(String aOtherFirstName)
    {
        if (this.otherFirstName == null || !this.otherFirstName.equals(aOtherFirstName))
        {
            markModified();
        }
        otherFirstName = aOtherFirstName;
    }

    /**
     * Sets the value of the otherLastName property.
     * 
     * @param aOtherLastName
     *            the new value of the otherLastName property
     */
    public void setOtherLastName(String aOtherLastName)
    {
        if (this.otherLastName == null || !this.otherLastName.equals(aOtherLastName))
        {
            markModified();
        }
        otherLastName = aOtherLastName;
    }

    /**
     * Sets the value of the otherMiddleName property.
     * 
     * @param aOtherMiddleName
     *            the new value of the otherMiddleName property
     */
    public void setOtherMiddleName(String aOtherMiddleName)
    {
        if (this.otherMiddleName == null || !this.otherMiddleName.equals(aOtherMiddleName))
        {
            markModified();
        }
        otherMiddleName = aOtherMiddleName;
    }

    /**
     * Sets the value of the otherPhoneNum property.
     * 
     * @param aOtherPhoneNum
     *            the new value of the otherPhoneNum property
     */
    public void setOtherPhoneNum(String aOtherPhoneNum)
    {
        if (this.otherPhoneNum == null || !this.otherPhoneNum.equals(aOtherPhoneNum))
        {
            markModified();
        }
        otherPhoneNum = aOtherPhoneNum;
    }

    /**
     * Sets the value of the otherSsn property.
     * 
     * @param aOtherSsn
     *            the new value of the otherSsn property
     */
    public void setOtherSsn(String aOtherSsn)
    {
        if (this.otherSsn == null || !this.otherSsn.equals(aOtherSsn))
        {
            markModified();
        }
        otherSsn = aOtherSsn;
    }

    /**
     * Sets the value of the phoneNum property.
     * 
     * @param aPhoneNum
     *            the new value of the phoneNum property
     */
    public void setPhoneNum(String aPhoneNum)
    {
        if (this.phoneNum == null || !this.phoneNum.equals(aPhoneNum))
        {
            markModified();
        }
        phoneNum = aPhoneNum;
    }

    /**
     * set the type reference for class member race
     * 
     * @param race
     */
    public void setRace(Code lrace)
    {
        if (this.race == null || !this.race.equals(lrace))
        {
            markModified();
        }
        if (lrace.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(lrace);
        }
        setRaceId("" + race.getOID());
        this.race = (Code) new mojo.km.persistence.Reference(lrace).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     * 
     * @param raceId
     */
    public void setRaceId(String lraceId)
    {
        if (this.raceId == null || !this.raceId.equals(lraceId))
        {
            markModified();
        }
        race = null;
        this.raceId = lraceId;
    }

    /**
     * set the type reference for class member scarsMarks
     * 
     * @param scarsMarks
     */
    public void setScarsMarks(ScarsMarksTattoosCode lscarsMarks)
    {
        if (this.scarsMarks == null || !this.scarsMarks.equals(lscarsMarks))
        {
            markModified();
        }
        if (lscarsMarks.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(lscarsMarks);
        }
        setScarsMarksId("" + scarsMarks.getOID());
        this.scarsMarks = (ScarsMarksTattoosCode) new mojo.km.persistence.Reference(lscarsMarks).getObject();
    }

    /**
     * Set the reference value to class ::
     * pd.codetable.person.ScarsMarksTattoosCode
     * 
     * @param scarsMarksId
     */
    public void setScarsMarksId(String lscarsMarksId)
    {
        if (this.scarsMarksId == null || !this.scarsMarksId.equals(lscarsMarksId))
        {
            markModified();
        }
        scarsMarks = null;
        this.scarsMarksId = lscarsMarksId;
    }

    /**
     * set the type reference for class member schoolName
     * 
     * @param schoolCode
     */
    public void setSchoolName(JuvenileSchoolDistrictCode lschoolCode)
    {
        if (this.schoolName == null || !this.schoolName.equals(lschoolCode))
        {
            markModified();
        }
        if (lschoolCode.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(lschoolCode);
        }
        setSchoolCodeId("" + lschoolCode.getOID());
        this.schoolName = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(lschoolCode)
                .getObject();
    }

    /**
     * Set the reference value to class ::
     * pd.codetable.person.JuvenileSchoolDistrictCode
     * 
     * @param schoolCodeId
     */
    public void setSchoolCodeId(String lschoolCodeId)
    {
        if (this.schoolCodeId == null || !this.schoolCodeId.equals(lschoolCodeId))
        {
            markModified();
        }
        schoolName = null;
        this.schoolCodeId = lschoolCodeId;
    }

    /**
     * set the type reference for class member sex
     * 
     * @param sex
     */
    public void setSex(Code lsex)
    {
        if (this.sex == null || !this.sex.equals(lsex))
        {
            markModified();
        }
        if (lsex.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(lsex);
        }
        setSexId("" + lsex.getOID());
        this.sex = (Code) new mojo.km.persistence.Reference(lsex).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     * 
     * @param sexId
     */
    public void setSexId(String lsexId)
    {
        if (this.sexId == null || !this.sexId.equals(lsexId))
        {
            markModified();
        }
        sex = null;
        this.sexId = lsexId;
    }

    /**
     * Sets the value of the sidNum property.
     * 
     * @param aSidNum
     *            the new value of the sidNum property
     */
    public void setSidNum(String aSidNum)
    {
        if (this.sidNum == null || !this.sidNum.equals(aSidNum))
        {
            markModified();
        }
        sidNum = aSidNum;
    }

    /**
     * Sets the value of the ssn property.
     * 
     * @param aSsn
     *            the new value of the ssn property
     */
    public void setSsn(String aSsn)
    {
        if (this.ssn == null || !this.ssn.equals(aSsn))
        {
            markModified();
        }
        ssn = aSsn;
    }

    /**
     * Sets the value of the transactionNum property.
     * 
     * @param aTransactionNum
     *            the new value of the transactionNum property
     */
    public void setTransactionNum(String aTransactionNum)
    {
        if (this.transactionNum == null || !this.transactionNum.equals(aTransactionNum))
        {
            markModified();
        }
        transactionNum = aTransactionNum;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param aWeight
     *            the new value of the weight property
     */
    public void setWeight(String aWeight)
    {
        if (this.weight == null || !this.weight.equals(aWeight))
        {
            markModified();
        }
        weight = aWeight;
    }

    /**
     * @return JuvenileOffenderTracking jot
     * @param daLogNum
     */
    static public JuvenileOffenderTrackingFamily find(String daLogNum)
    {
        IHome home = new Home();
        JuvenileOffenderTrackingFamily jot = (JuvenileOffenderTrackingFamily) home.find(daLogNum, JuvenileOffenderTrackingFamily.class);
        return jot;
    }

    /**
     * Initialize class relationship to class
     * pd.codetable.person.JuvenileSchoolDistrictCode
     */
    private void initSchoolName()
    {
        if (schoolName == null)
        {
            schoolName = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(schoolCodeId,
                    JuvenileSchoolDistrictCode.class).getObject();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see pd.juvenilewarrant.IJuvenileAssociatesInfo#getFather()
     */
    public JuvenileAssociateResponseEvent getFather()
    {
        JuvenileAssociateResponseEvent jare = new JuvenileAssociateResponseEvent();
        jare.setFirstName(this.getFathersFirstName());
        jare.setLastName(this.getFathersLastName());
        jare.setMiddleName(this.getFathersMiddleName());
        jare.setAssociateNum(PDJuvenileWarrantConstants.FATHER_ASSOCIATE_NUM);
        String relationshipToJuvenileId = "BF";
        Code juvCode = (Code) new Reference(relationshipToJuvenileId, Code.class, PDCodeTableConstants.RELATIONSHIP_JUVENILE)
                .getObject();
        if (juvCode != null)
        {
            jare.setRelationshipToJuvenileId(juvCode.getCode());
            jare.setRelationshipToJuvenile(juvCode.getDescription());
        }
        jare.setHomePhoneNum(this.getFathersPhoneNum());
        if (this.getFathersSsn() != null)
        {
            jare.setSsn(this.getFathersSsn());
        }
        jare.setAddress(this.getFatherAddress());
        jare.setTopic(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_FATHER_EVENT_TOPIC);
        return jare;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pd.juvenilewarrant.IJuvenileAssociatesInfo#getMother()
     */
    public JuvenileAssociateResponseEvent getMother()
    {
        JuvenileAssociateResponseEvent jare = new JuvenileAssociateResponseEvent();
        jare.setFirstName(this.getMothersFirstName());
        jare.setLastName(this.getMothersLastName());
        jare.setMiddleName(this.getMothersMiddleName());
        jare.setAssociateNum(PDJuvenileWarrantConstants.MOTHER_ASSOCIATE_NUM);
        String relationshipToJuvenileId = "BM";
        Code juvCode = (Code) new Reference(relationshipToJuvenileId, Code.class, PDCodeTableConstants.RELATIONSHIP_JUVENILE)
                .getObject();
        if (juvCode != null)
        {
            jare.setRelationshipToJuvenileId(juvCode.getCode());
            jare.setRelationshipToJuvenile(juvCode.getDescription());
        }
        jare.setHomePhoneNum(this.getMothersPhoneNum());
        if (this.getMothersSsn() != null)
        {
            jare.setSsn(this.getMothersSsn());
        }
        jare.setAddress(this.getMotherAddress());
        jare.setTopic(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_MOTHER_EVENT_TOPIC);
        return jare;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pd.juvenilewarrant.IJuvenileAssociatesInfo#getOther()
     */
    public JuvenileAssociateResponseEvent getOther()
    {
        JuvenileAssociateResponseEvent jare = new JuvenileAssociateResponseEvent();
        jare.setFirstName(this.getOtherFirstName());
        jare.setLastName(this.getOtherLastName());
        jare.setMiddleName(this.getOtherMiddleName());
        jare.setAssociateNum(PDJuvenileWarrantConstants.OTHER_ASSOCIATE_NUM);
        String relationshipToJuvenileId = "OR";
        Code juvCode = (Code) new Reference(relationshipToJuvenileId, Code.class, PDCodeTableConstants.RELATIONSHIP_JUVENILE)
                .getObject();
        if (juvCode != null)
        {
            jare.setRelationshipToJuvenileId(juvCode.getCode());
            jare.setRelationshipToJuvenile(juvCode.getDescription());
        }
        jare.setHomePhoneNum(this.getOtherPhoneNum());
        if (this.getOtherSsn() != null)
        {
            jare.setSsn(this.getOtherSsn());
        }
        jare.setAddress(this.getOtherAddress());
        jare.setTopic(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_OTHER_EVENT_TOPIC);
        return jare;
    }    
	/**
	 * @return Returns the arrestDate.
	 */
	public Date getArrestDate() {
		  fetch();
		return arrestDate;
	}
	/**
	 * @param arrestDate The arrestDate to set.
	 */
	public void setArrestDate(Date arrestDate) {
		 if (this.arrestDate == null || !this.arrestDate.equals(arrestDate))
	        {
	            markModified();
	        }
		this.arrestDate = arrestDate;
	}
	/**
	 * @return Returns the arrestTime.
	 */
	public String getArrestTime() {
		  fetch();
		return arrestTime;
	}
	/**
	 * @param arrestTime The arrestTime to set.
	 */
	public void setArrestTime(String arrestTime) {
		 if (this.arrestTime == null || !this.arrestTime.equals(arrestTime))
	        {
	            markModified();
	        }
		this.arrestTime = arrestTime;
	}
	/**
	 * @return Returns the coDefendants.
	 */
	public String getCoDefendants() {
		  fetch();
		return coDefendants;
	}
	/**
	 * @param coDefendants The coDefendants to set.
	 */
	public void setCoDefendants(String coDefendants) {
		 if (this.coDefendants == null || !this.coDefendants.equals(coDefendants))
	        {
	            markModified();
	        }
		this.coDefendants = coDefendants;
	}
	/**
	 * @return Returns the afisNCICId.
	 */
	public String getAfisNCICId() {
		  fetch();
		return afisNCICId;
	}
	/**
	 * @param afisNCICId The afisNCICId to set.
	 */
	public void setAfisNCICId(String aafisNCICId) {
		 if (this.afisNCICId == null || !this.afisNCICId.equals(aafisNCICId))
	        {
	            markModified();
	        }
	    this.afisNCICId = aafisNCICId;
	}
	/**
	 * @return Returns the agency.
	 */
	public String getAgency() {
		  fetch();
		return agency;
	}
	/**
	 * @param agency The agency to set.
	 */
	public void setAgency(String aagency) {
		 if (this.agency == null || !this.agency.equals(aagency))
	        {
	            markModified();
	        }
		this.agency = aagency;
	}
	/**
	 * @return Returns the broughtBack.
	 */
	public String getBroughtBack() {
		  fetch();
		return broughtBack;
	}
	/**
	 * @param broughtBack The broughtBack to set.
	 */
	public void setBroughtBack(String abroughtBack) {
		 if (this.broughtBack == null || !this.broughtBack.equals(abroughtBack))
	        {
	            markModified();
	        }
		this.broughtBack = abroughtBack;
	}
	/**
	 * @return Returns the comment1.
	 */
	public String getComment1() {
		  fetch();
		return comment1;
	}
	/**
	 * @param comment1 The comment1 to set.
	 */
	public void setComment1(String acomment1) {
		 if (this.comment1 == null || !this.comment1.equals(acomment1))
	        {
	            markModified();
	        }
		this.comment1 = acomment1;
	}
	/**
	 * @return Returns the comment2.
	 */
	public String getComment2() {
		  fetch();
		return comment2;
	}
	/**
	 * @param comment2 The comment2 to set.
	 */
	public void setComment2(String acomment2) {
		 if (this.comment2 == null || !this.comment2.equals(acomment2))
	        {
	            markModified();
	        }
		this.comment2 = acomment2;
	}
	/**
	 * @return Returns the compKnowsDef.
	 */
	public String getCompKnowsDef() {
		  fetch();
		return compKnowsDef;
	}
	/**
	 * @param compKnowsDef The compKnowsDef to set.
	 */
	public void setCompKnowsDef(String acompKnowsDef) {
		 if (this.compKnowsDef == null || !this.compKnowsDef.equals(acompKnowsDef))
	        {
	            markModified();
	        }
		this.compKnowsDef = acompKnowsDef;
	}
	/**
	 * @return Returns the lineupInd.
	 */
	public String getLineupInd() {
		  fetch();
		return lineupInd;
	}
	/**
	 * @param lineupInd The lineupInd to set.
	 */
	public void setLineupInd(String alineupInd) {
		 if (this.lineupInd == null || !this.lineupInd.equals(alineupInd))
	        {
	            markModified();
	        }
		this.lineupInd = alineupInd;
	}
	/**
	 * @return Returns the oralConfess.
	 */
	public String getOralConfess() {
		  fetch();
		return oralConfess;
	}
	/**
	 * @param oralConfess The oralConfess to set.
	 */
	public void setOralConfess(String aoralConfess) {
		 if (this.oralConfess == null || !this.oralConfess.equals(aoralConfess))
	        {
	            markModified();
	        }
		this.oralConfess = aoralConfess;
	}
	/**
	 * @return Returns the photoArrayId.
	 */
	public String getPhotoArrayId() {
		  fetch();
		return photoArrayId;
	}
	/**
	 * @param photoArrayId The photoArrayId to set.
	 */
	public void setPhotoArrayId(String aphotoArrayId) {
		 if (this.photoArrayId == null || !this.photoArrayId.equals(aphotoArrayId))
	        {
	            markModified();
	        }
		this.photoArrayId = aphotoArrayId;
	}
	/**
	 * @return Returns the writtenConfess.
	 */
	public String getWrittenConfess() {
		  fetch();
		return writtenConfess;
	}
	/**
	 * @param witnessConfess The witnessConfess to set.
	 */
	public void setWrittenConfess(String awrittenConfess) {
		 if (this.writtenConfess == null || !this.writtenConfess.equals(awrittenConfess))
	        {
	            markModified();
	        }
		this.writtenConfess = awrittenConfess;
	}
	/**
	 * @return Returns the witnessKnowsDef.
	 */
	public String getWitnessKnowsDef() {
		  fetch();
		return witnessKnowsDef;
	}
	/**
	 * @param witnessKnowsDef The witnessKnowsDef to set.
	 */
	public void setWitnessKnowsDef(String awitnessKnowsDef) {
		 if (this.witnessKnowsDef == null || !this.witnessKnowsDef.equals(awitnessKnowsDef))
	        {
	            markModified();
	        }
		this.witnessKnowsDef = awitnessKnowsDef;
	}
	
	/**
	 * @return Returns the arrestInd.
	 */
	public String getArrestInd() {
		  fetch();
		return arrestInd;
	}
	/**
	 * @param arrestInd The arrestInd to set.
	 */
	public void setArrestInd(String aarrestInd) {
		 if (this.arrestInd == null || !this.arrestInd.equals(aarrestInd))
	        {
	            markModified();
	        }
		this.arrestInd = aarrestInd;
	}

	@Override
	public JuvenileAssociateResponseEvent getAlter()
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public String getAlterFirstName()
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public String getAlterMiddleName()
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public String getAlterLastName()
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public String getAlterPhoneNum()
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public void setAlterFirstName(String othersFirstName)
	{
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void setAlterMiddleName(String othersMiddleName)
	{
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void setAlterLastName(String othersLastName)
	{
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void setAlterPhoneNum(String otherPhoneNum)
	{
	    // TODO Auto-generated method stub
	    
	}
}
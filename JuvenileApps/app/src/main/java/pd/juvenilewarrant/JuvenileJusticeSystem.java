package pd.juvenilewarrant;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.address.reply.AddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileJusticeSystemResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.Reference;
import naming.PDAddressConstants;
import naming.PDCodeTableConstants;
import naming.PDJuvenileWarrantConstants;
import pd.codetable.Code;
import pd.codetable.PDCodeTableHelper;
import pd.codetable.person.JuvenileSchoolDistrictCode;
import pd.contact.user.UserProfile;
import pd.km.util.Formatter;

/**
 * @author dnikolis
 */
public class JuvenileJusticeSystem extends PersistentObject implements IJuvenileAssociatesInfo
{
    private static final String FATHER_RELATION = "BF";

    private static final String MOTHER_RELATION = "BM";

    /**
     * @return Iterator jjs
     * @param raceId
     * @roseuid 41B753D3000F
     */
    static public JuvenileJusticeSystem find(IEvent event)
    {
        IHome home = new Home();
        Iterator i = (Iterator) home.findAll(event, JuvenileJusticeSystem.class);
        if (i.hasNext())
        {
            return (JuvenileJusticeSystem) i.next();
        }
        return null;
    }

    /**
     * @return JuvenileJusticeSystem jjs
     * @param petitionNum
     */
    static public JuvenileJusticeSystem find(String petitionNum)
    {
        JuvenileJusticeSystem jjs = null;
        IHome home = new Home();
        jjs = (JuvenileJusticeSystem) home.find(petitionNum, JuvenileJusticeSystem.class);
        return jjs;
    }

    private String alias;

    private Date dateOfBirth;

    private String fatherAddressId;

    private String fathersFirstName;

    private String fathersLastName;

    private String fathersMiddleName;

    private String fathersPhoneNum;

    private String fatherStreetName;

    private String firstName;

    private String juvAddressTypeId;

    private String juvenileNum;
    
    private String petitionNum;

    private String juvKeymap;

    private String juvStreetName;

    private String juvStreetNum;

    private String juvZipCode;

    private String lastName;

    private String middleName;

    private String mothersFirstName;

    private String mothersLastName;

    private String mothersMiddleName;

    private String mothersPhoneNum;

    private String motherStreetName;

    private String motherStreetNum;

    private String otherAdditionalZipCode;

    private String otherFirstName;

    private String otherLastName;

    private String otherMiddleName;

    private String otherPhoneNum;

    private String otherStreetName;

    /**
     * Properties for petitions
     * 
     * @referencedType pd.juvenilewarrant.JJSPetition
     * @detailerDoNotGenerate true
     */
    private Collection petitions = null;

    /**
     * Properties for probationOfficerOfRecord
     * 
     * @referencedType pd.contact.user.UserProfile
     * @detailerDoNotGenerate true
     */
    private UserProfile probationOfficerOfRecord = null;

    private String probationOfficerOfRecordId;

    /**
     * Properties for race
     * 
     * @referencedType pd.codetable.Code
     * @contextKey RACE
     * @detailerDoNotGenerate true
     */
    private Code race = null;

    private String raceId;

    private String referralNum;

    private String relationSsn1;

    private String relationSsn2;

    /**
     * Properties for schoolDistrict
     * 
     * @referencedType pd.codetable.person.JuvenileSchoolDistrictCode
     * @detailerDoNotGenerate true
     */
    private JuvenileSchoolDistrictCode schoolDistrict = null;

    private String schoolDistrictId;

    private String schoolId;

    /**
     * Properties for sex
     * 
     * @referencedType pd.codetable.Code
     * @contextKey SEX
     * @detailerDoNotGenerate true
     */
    private Code sex = null;

    private String sexId;

    private String ssn;

    private String ssn1;

    private String ssn2;

    private String title;

    /**
     * @roseuid 41B7540800B5
     */
    public JuvenileJusticeSystem()
    {
    }

    /**
     * Clears all pd.juvenilewarrant.JJSPetition from class relationship
     * collection.
     */
    public void clearPetitions()
    {
        initPetitions();
        petitions.clear();
    }

    private String formatPetitionOID(String petitionNum, String aReferralNum)
    {
        StringBuffer derivedOID = new StringBuffer();
        derivedOID.append(Formatter.pad((String) this.getOID(), 7, '0', true));

        if (aReferralNum != null)
        {
            derivedOID.append(Formatter.pad(aReferralNum, 4, '0', true));
        }
        else
        {
            derivedOID.append(Formatter.pad("", 4, '0', true));
        }

        if (petitionNum != null)
        {
            derivedOID.append(Formatter.pad(petitionNum, 12, '0', true));
        }
        else
        {
            derivedOID.append(Formatter.pad("", 12, '0', true));
        }

        return derivedOID.toString();
    }

    /**
     * Access method for the alias property.
     * 
     * @return the current value of the alias property
     */
    public String getAlias()
    {
        fetch();
        return alias;
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

    private AddressResponseEvent getFatherAddress()
    {
        AddressResponseEvent are = null;
        if (this.hasValidFatherAddress())
        {
            are = new AddressResponseEvent();
            are.setTopic(PDJuvenileWarrantConstants.FATHER_ADDRESS_EVENT_TOPIC);
            are.setAddressStatus("U");
            are.setStreetName(this.getFatherStreetName());
        }
        return are;
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

    /*
     * (non-Javadoc)
     * 
     * @see pd.juvenilewarrant.IJuvenileAssociatesInfo#getFathersSsn()
     */
    public String getFathersSsn()
    {
        String fathersSsn = null;
        if(FATHER_RELATION.equals(this.relationSsn1))
        {
            fathersSsn = this.ssn1;
        }
        else if(FATHER_RELATION.equals(this.relationSsn2))
        {
            fathersSsn = this.ssn2;
        }
        return fathersSsn;
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
     * @return Returns the juvAddressTypeId.
     */
    public String getJuvAddressTypeId()
    {
        fetch();
        return juvAddressTypeId;
    }

    public AddressResponseEvent getJuvenileAddress()
    {
        AddressResponseEvent are = null;
        if (this.hasValidJuvAddress())
        {
            are = new AddressResponseEvent();
            are.setTopic(PDAddressConstants.ADDRESS_EVENT_TOPIC);
            are.setAddressStatus("U");
            are.setAddressTypeId(this.getJuvAddressTypeId());
            are.setStreetName(this.getJuvStreetName());
            are.setStreetNum(this.getJuvStreetNum());
            are.setZipCode(this.getJuvZipCode());
            are.setKeymap(this.getJuvKeymap());
        }
        return are;
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
     * @return Returns the juvKeymap.
     */
    public String getJuvKeymap()
    {
        fetch();
        return juvKeymap;
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
     * @return Returns the juvStreetNum.
     */
    public String getJuvStreetNum()
    {
        fetch();
        return juvStreetNum;
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

    public AddressResponseEvent getMotherAddress()
    {
        AddressResponseEvent are = null;
        if (this.hasValidMotherAddress())
        {
            are = new AddressResponseEvent();
            are.setTopic(PDJuvenileWarrantConstants.MOTHER_ADDRESS_EVENT_TOPIC);
            are.setAddressStatus("U");
            are.setStreetName(this.getMotherStreetName());
        }
        return are;
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

    /*
     * (non-Javadoc)
     * 
     * @see pd.juvenilewarrant.IJuvenileAssociatesInfo#getMothersSsn()
     */
    public String getMothersSsn()
    {
        String mothersSsn = null;
        if(MOTHER_RELATION.equals(this.relationSsn1))
        {
            mothersSsn = this.ssn1;
        }
        else if(MOTHER_RELATION.equals(this.relationSsn2))
        {
            mothersSsn = this.ssn2;
        }
        return mothersSsn;
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
     * @return Returns the motherStreetNum.
     */
    public String getMotherStreetNum()
    {
        fetch();
        return motherStreetNum;
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
     * @return Returns the otherAdditionalZipCode.
     */
    public String getOtherAdditionalZipCode()
    {
        fetch();
        return otherAdditionalZipCode;
    }

    public AddressResponseEvent getOtherAddress()
    {
        AddressResponseEvent are = null;
        if (this.hasValidOtherAddress())
        {
            are = new AddressResponseEvent();
            are.setTopic(PDJuvenileWarrantConstants.OTHER_ADDRESS_EVENT_TOPIC);
            are.setAddressStatus("U");
            are.setStreetName(this.getOtherStreetName());
        }
        return are;
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

    /*
     * (non-Javadoc)
     * 
     * @see pd.juvenilewarrant.IJuvenileAssociatesInfo#getOtherSsn()
     */
    public String getOtherSsn()
    {
        // TODO Auto-generated method stub
        return null;
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
     * returns a collection of pd.juvenilewarrant.JJSPetition
     * 
     * @return Collection petitions
     */
    public Collection getPetitions()
    {
        fetch();
        initPetitions();
        return petitions;
    }

    /**
     * Initialize class relationship implementation for
     * pd.juvenilewarrant.JJSPetition
     * 
     * @return Collection petitions
     * @param petitionNum
     * @param areferralNum
     */
    public Collection getPetitions(String petitionNum, String aReferralNum)
    {
        fetch();
        initPetitions(petitionNum, aReferralNum);
        return petitions;
    }

    /**
     * Access method for the probationOfficerOfRecord property.
     * 
     * @return the current value of the probationOfficerOfRecord property
     */
    public UserProfile getProbationOfficerOfRecord()
    {
        initProbationOfficerOfRecord();
        fetch();
        return probationOfficerOfRecord;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getProbationOfficerOfRecordId()
    {
        fetch();
        return probationOfficerOfRecordId;
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
     * @return String referralNum
     */
    public String getReferralNum()
    {
        fetch();
        return referralNum;
    }

    /**
     * @return Returns the relationSsn1.
     */
    public String getRelationSsn1()
    {
        fetch();
        return relationSsn1;
    }

    /**
     * @return Returns the relationSsn2.
     */
    public String getRelationSsn2()
    {
        fetch();
        return relationSsn2;
    }

    /**
     * Gets referenced type pd.codetable.person.JuvenileSchoolDistrictCode
     * 
     * @return schoolDistrict
     */
    public JuvenileSchoolDistrictCode getSchoolDistrict()
    {
        fetch();
        initSchoolDistrict();
        return schoolDistrict;
    }

    /**
     * Get the reference value to class ::
     * pd.codetable.person.JuvenileSchoolDistrictCode
     * 
     * @return String schoolDistrictId
     */
    public String getSchoolDistrictId()
    {
        fetch();
        if (schoolDistrictId != null)
        {
            schoolDistrictId = Formatter.pad(schoolDistrictId, 3, '0', true);
        }
        return schoolDistrictId;
    }

    /**
     * return schoolid
     * 
     * @return schoolid
     */
    public String getSchoolId()
    {
        return this.schoolId;
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
     * @return Returns the ssn1.
     */
    public String getSsn1()
    {
        fetch();
        return ssn1;
    }

    /**
     * @return Returns the ssn2.
     */
    public String getSsn2()
    {
        fetch();
        return ssn2;
    }

    /**
     * Access method for the title property.
     * 
     * @return the current value of the title property
     */
    public String getTitle()
    {
        fetch();
        return title;
    }

    private boolean hasValidFatherAddress()
    {
        boolean valid = false;

        if (fatherStreetName != null)
        {
            valid = true;
        }

        return valid;
    }

    private boolean hasValidJuvAddress()
    {
        boolean valid = false;

        if (juvAddressTypeId != null || juvKeymap != null || juvStreetName != null || juvStreetNum != null || juvZipCode != null)
        {
            valid = true;
        }

        return valid;
    }

    private boolean hasValidMotherAddress()
    {
        boolean valid = false;

        if (motherStreetName != null)
        {
            valid = true;
        }

        return valid;
    }

    private boolean hasValidOtherAddress()
    {
        boolean valid = false;

        if (otherStreetName != null)
        {
            valid = true;
        }

        return valid;
    }

    /**
     * Initialize class relationship implementation for
     * pd.juvenilewarrant.JJSPetition
     */
    private void initPetitions()
    {
        if (petitions == null)
        {
            if (this.getOID() == null)
            {
                new mojo.km.persistence.Home().bind(this);
            }

            // pass in a null referral number
            String derivedOID = this.formatPetitionOID(null, null);

            petitions = new mojo.km.persistence.ArrayList(JJSPetition.class, "OID", derivedOID);
        }
    }

    /**
     * Initialize class relationship implementation for
     * pd.juvenilewarrant.JJSPetition
     */
    private void initPetitions(String petitionNum, String aReferralNum)
    {
        if (petitions == null)
        {
            if (this.getOID() == null)
            {
                new mojo.km.persistence.Home().bind(this);
            }

            // pass in a null referral number
           // String derivedOID = this.formatPetitionOID(petitionNum, aReferralNum);
            petitions = new mojo.km.persistence.ArrayList(JJSPetition.class, "petitionNum", petitionNum);

        }
    }


    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initProbationOfficerOfRecord()
    {
        if (probationOfficerOfRecord == null && this.probationOfficerOfRecordId != null
                && this.probationOfficerOfRecordId.equals("") == false)
        {
            probationOfficerOfRecord = UserProfile.find(this.probationOfficerOfRecordId);
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
     * pd.codetable.person.JuvenileSchoolDistrictCode
     */
    private void initSchoolDistrict()
    {
        if (schoolDistrict == null && (schoolDistrictId != null && !schoolDistrictId.equals("")))
        {
            int dCode = Integer.parseInt(schoolDistrictId);
	    int sCode = Integer.parseInt(schoolId);
	    String derivedOid = String.valueOf(dCode) + "-" + String.valueOf(sCode);
 
            schoolDistrict = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(derivedOid,
                    JuvenileSchoolDistrictCode.class).getObject();
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
     * insert a pd.juvenilewarrant.JJSPetition into class relationship
     * collection.
     * 
     * @param anObject
     */
    public void insertPetitions(JJSPetition anObject)
    {
        initPetitions();
        petitions.add(anObject);
    }

    /**
     * Removes a pd.juvenilewarrant.JJSPetition from class relationship
     * collection.
     */
    public void removePetitions(JJSPetition anObject)
    {
        initPetitions();
        petitions.remove(anObject);
    }

    /**
     * Sets the value of the alias property.
     * 
     * @param aSsn
     *            the new value of the alias property
     */
    public void setAlias(String aAlias)
    {
        if (this.alias == null || !this.alias.equals(aAlias))
        {
            markModified();
        }
        alias = aAlias;
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

    /*
     * (non-Javadoc)
     * 
     * @see pd.juvenilewarrant.IJuvenileAssociatesInfo#setFathersSsn(java.lang.String)
     */
    public void setFathersSsn(String fathersSsn)
    {
        // TODO Auto-generated method stub

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

    /*
     * (non-Javadoc)
     * 
     * @see pd.juvenilewarrant.IJuvenileAssociatesInfo#setMothersSsn(java.lang.String)
     */
    public void setMothersSsn(String mothersSsn)
    {
        // TODO Auto-generated method stub

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

    /*
     * (non-Javadoc)
     * 
     * @see pd.juvenilewarrant.IJuvenileAssociatesInfo#setOtherSsn(java.lang.String)
     */
    public void setOtherSsn(String otherSsn)
    {
        // TODO Auto-generated method stub

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
     * Sets the value of the probationOfficerOfRecord property.
     * 
     * @param aProbationOfficerOfRecord
     *            the new value of the probationOfficerOfRecord property
     */
     //87191
/*    public void setProbationOfficerOfRecord(pd.contact.user.UserProfile aProbationOfficerOfRecord)
    {
        if (this.probationOfficerOfRecord == null || !this.probationOfficerOfRecord.equals(aProbationOfficerOfRecord))
        {
            markModified();
        }
        if (aProbationOfficerOfRecord.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(aProbationOfficerOfRecord);
        }
        this.probationOfficerOfRecord = aProbationOfficerOfRecord;
        this.probationOfficerOfRecordId = aProbationOfficerOfRecord.getOID().toString();
    }*/

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setProbationOfficerOfRecordId(String aProbationOfficerOfRecordId)
    {
        if (this.probationOfficerOfRecordId == null || !this.probationOfficerOfRecordId.equals(aProbationOfficerOfRecordId))
        {
            markModified();
        }
        probationOfficerOfRecord = null;
        if (aProbationOfficerOfRecordId != null && aProbationOfficerOfRecordId.length() == 3)
        {
            aProbationOfficerOfRecordId = "UV" + aProbationOfficerOfRecordId;
        }
        this.probationOfficerOfRecordId = aProbationOfficerOfRecordId;
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
     * @param string
     */
    public void setReferralNum(String string)
    {
        if (this.referralNum == null || !this.referralNum.equals(string))
        {
            markModified();
        }
        referralNum = string;
    }

    /**
     * @param relationSsn1
     *            The relationSsn1 to set.
     */
    public void setRelationSsn1(String aRelationSsn1)
    {
        if (this.relationSsn1 == null || !this.relationSsn1.equals(aRelationSsn1))
        {
            markModified();
        }
        this.relationSsn1 = aRelationSsn1;
    }

    /**
     * @param relationSsn2
     *            The relationSsn2 to set.
     */
    public void setRelationSsn2(String aRelationSsn2)
    {
        if (this.relationSsn2 == null || !this.relationSsn2.equals(aRelationSsn2))
        {
            markModified();
        }
        this.relationSsn2 = aRelationSsn2;
    }

    /**
     * set the type reference for class member schoolDistrict
     * 
     * @param schoolDistrict
     */
    public void setSchoolDistrict(JuvenileSchoolDistrictCode lschoolDistrict)
    {
        if (this.schoolDistrict == null || !this.schoolDistrict.equals(lschoolDistrict))
        {
            markModified();
        }
        setSchoolDistrictId("" + lschoolDistrict.getOID());
        this.schoolDistrict = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(lschoolDistrict)
                .getObject();
    }

    /**
     * Set the reference value to class ::
     * pd.codetable.person.JuvenileSchoolDistrictCode
     * 
     * @param schoolDistrictId
     */
    public void setSchoolDistrictId(String lschoolDistrictId)
    {
        if (this.schoolDistrictId == null || !this.schoolDistrictId.equals(lschoolDistrictId))
        {
            markModified();
        }
        schoolDistrict = null;
        this.schoolDistrictId = lschoolDistrictId;
    }

    /**
     * Set schoolId
     * 
     * @param schoolId
     */
    public void setSchoolId(String lschoolId)
    {
        this.schoolId = lschoolId;
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
     * @param ssn1
     *            The ssn1 to set.
     */
    public void setSsn1(String aSsn1)
    {
        if (this.ssn1 == null || !this.ssn1.equals(aSsn1))
        {
            markModified();
        }
        this.ssn1 = aSsn1;
    }

    /**
     * @param ssn2
     *            The ssn2 to set.
     */
    public void setSsn2(String aSsn2)
    {
        if (this.ssn2 == null || !this.ssn2.equals(aSsn2))
        {
            markModified();
        }
        ssn2 = aSsn2;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param aTitle
     *            the new value of the title property
     */
    public void setTitle(String aTitle)
    {
        if (this.title == null || !this.title.equals(aTitle))
        {
            markModified();
        }
        title = aTitle;
    }

    public String getPetitionNum()
    {
	fetch();
        return petitionNum;
    }

    public void setPetitionNum(String petitionNum)
    {
        this.petitionNum = petitionNum;
    }

    public JuvenileJusticeSystemResponseEvent valueObject()
    {
        JuvenileJusticeSystemResponseEvent jjsEvent = new JuvenileJusticeSystemResponseEvent();
        jjsEvent.setTopic(PDJuvenileWarrantConstants.JUVENILE_JUSTICE_SYSTEM_EVENT_TOPIC);
        jjsEvent.setJuvenileNumber(this.getJuvenileNum());

        jjsEvent.setJuvenileFirstName(this.getFirstName());
        jjsEvent.setJuvenileLastName(this.getLastName());
        jjsEvent.setJuvenileMiddleName(this.getMiddleName());
        jjsEvent.setAliasName(this.getAlias());
        jjsEvent.setJuvenileTitle(this.getTitle());
        jjsEvent.setJuvenileSSN(this.getSsn());

        jjsEvent.setDateOfBirth(this.getDateOfBirth());

        String jpoId = this.getProbationOfficerOfRecordId();
        if (jpoId != null && jpoId.equals("") == false)
        {
            jjsEvent.setProbationOfficerOfRecordId(jpoId);
            UserProfile jpo = this.getProbationOfficerOfRecord();
            if (jpo != null)
            {
                jjsEvent.setProbationOfficerOfRecordName(jpo.getName());
            }
        }

        if (this.getSchoolDistrictId() != null && !this.getSchoolDistrictId().equals(""))
        {
            JuvenileSchoolDistrictCode schoolCode = this.getSchoolDistrict();
            if (schoolCode != null)
            {
                jjsEvent.setSchoolDistrictId(schoolCode.getDistrictCode());
                jjsEvent.setSchoolDistrict(schoolCode.getDistrictDescription());
                jjsEvent.setSchoolCodeId(schoolCode.getSchoolCode());
                jjsEvent.setSchoolName(schoolCode.getSchoolDescription());
            }

        }

        if (this.getSexId() != null && !this.getSexId().equals(""))
        {
            jjsEvent.setSexId(this.getSex().getCode());
            jjsEvent.setSex(this.getSex().getDescription());
        }

        if (PDCodeTableHelper.isCodeValid(this.getRaceId(), this.getRace()))
        {
            jjsEvent.setRaceId(this.getRace().getCode());
            jjsEvent.setRace(this.getRace().getDescription());
        }

        jjsEvent.setFathersFirstName(this.getFathersFirstName());
        jjsEvent.setFathersLastName(this.getFathersLastName());
        jjsEvent.setFathersMiddleName(this.getFathersMiddleName());
        jjsEvent.setMothersFirstName(this.getMothersFirstName());
        jjsEvent.setMothersLastName(this.getMothersLastName());
        jjsEvent.setMothersMiddleName(this.getMothersMiddleName());
        jjsEvent.setOthersFirstName(this.getOtherFirstName());
        jjsEvent.setOthersLastName(this.getOtherLastName());
        jjsEvent.setOthersMiddleName(this.getOtherMiddleName());

        return jjsEvent;
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
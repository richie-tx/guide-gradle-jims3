/*
 * Project: JIMS
 * Class:   messaging.juvenile.SaveJuvenileProfileMainEvent
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.juvenile;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import ui.common.Address;
import ui.juvenilecase.referral.JuvenileReferralMemberDetailsBean;

/**
 * Class SaveJuvenileProfileMainEvent.
 * 
 * @author Anand Thorat
 */
public class SaveJuvenileProfileMainEvent extends mojo.km.messaging.RequestEvent
{

    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------

    private String juvenileNum;

    private String birthCountryId;

    private String birthCityId;

    private String birthStateId;

    private String raceId;

    private String hispanic; //U.S 88526

    private String hispanicStr;

    private boolean multiracial;

    private Date dateOfBirth;
    private Date realDOB;

    private boolean verifiedDOB;

    private String birthCountyId;

    private String isUSCitizenId;

    private String ethnicityId;

    private String nationalityId;

    private String secondaryLanguageId;

    private String primaryLanguageId;

    private String driverLicenseNumber;

    private String driverLicenseStateId;

    private String SSN;

    private String SID;

    private String SONumber;

    private String educationId;

    private String studentId; //ER-76951-Add StudentId

    private String DHSNumber;

    private String PEIMSId;

    private String alienNumber;

    private String FBINumber;

    private String natualEyeColorId;

    private String naturalHairColorId;

    private String complexionId;

    private Collection tattoos;

    private Collection scarsAndMarks;

    private String parentsMaritalStatusId;

    private String religionId;

    private String DNASampleNumber;

    private Date dateSenttoDPS;

    private String sexId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String nameSuffix;
    
    private String preferredFirstName;

    private String driverLicenseClassId;

    private Date driverLicenseExpireDate;

    private boolean isAdopted = false;

    private String failedPlacements;

    private String adoptionComments;

    //added for referral conversion
    private String action;
    private String statusId;
    private List<JuvenileReferralMemberDetailsBean> memberDetailsBeanList;
    private Address juvAddress;
    private String comments;
    private String checkedOutTo;
    private String checkedOutDate;
    private String lastActionBy;
    private String lastUpdate;
    private String operator;
    private String juvFromM204Flag;
    //added for school section of create juvenile US 71172
    private String schoolId;
    private String schoolDistId;
    private String exitTypeId; //for enrollmentStatus
    private String pgmAttdngId;
    private String gradeLvlId;
    private String schoolAttendanceStatusId;
    private String schoolteaCode;
    
    private String TSDSId;
    
    private String lcuser;//US 71171 added to identify the M204 legacy created record in update juvenile
    private boolean updateSSN = false;
    private int juvExcludedReporting = 0;
    
    private String passportNum = ""; //added for passport
    private String countryOfIssuanceId; //added for passport
    private Date passportExpirationDate; //added for passport
    private String youthLivesWith; // Added for TJJD
    
    
    

    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     * @roseuid 42A74E0D029F
     */
    public SaveJuvenileProfileMainEvent()
    {

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.SaveJuvenileProfileMainEvent

    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------
    
    
    

    /**
     * @param birthCountryId
     *            @roseuid 42A5DD910167
     */
    public void setBirthCountryId(String birthCountryId)
    {
	this.birthCountryId = birthCountryId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setBirthCountryId

    /**
     * @return the comments
     */
    public String getComments()
    {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments)
    {
        this.comments = comments;
    }

   

    /**
     * @return the checkedOutTo
     */
    public String getCheckedOutTo()
    {
        return checkedOutTo;
    }

    /**
     * @param checkedOutTo the checkedOutTo to set
     */
    public void setCheckedOutTo(String checkedOutTo)
    {
        this.checkedOutTo = checkedOutTo;
    }

    /**
     * @return the checkedOutDate
     */
    public String getCheckedOutDate()
    {
        return checkedOutDate;
    }

    /**
     * @param checkedOutDate the checkedOutDate to set
     */
    public void setCheckedOutDate(String checkedOutDate)
    {
        this.checkedOutDate = checkedOutDate;
    }

    /**
     * @return the lastActionBy
     */
    public String getLastActionBy()
    {
        return lastActionBy;
    }

    /**
     * @param lastActionBy the lastActionBy to set
     */
    public void setLastActionBy(String lastActionBy)
    {
        this.lastActionBy = lastActionBy;
    }

    /**
     * @return the lastUpdate
     */
    public String getLastUpdate()
    {
        return lastUpdate;
    }

    /**
     * @param lastUpdate the lastUpdate to set
     */
    public void setLastUpdate(String lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return the operator
     */
    public String getOperator()
    {
        return operator;
    }

    /**
     * @param operator the operator to set
     */
    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    /**
     * @return String
     * @roseuid 42A5DD910169
     */
    public String getBirthCountryId()
    {
	return birthCountryId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getBirthCountryId

    /**
     * @param birthCityId
     *            @roseuid 42A5DD910177
     */
    public void setBirthCityId(String birthCityId)
    {
	this.birthCityId = birthCityId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setBirthCityId

    /**
     * @return String
     * @roseuid 42A5DD910179
     */
    public String getBirthCityId()
    {
	return birthCityId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getBirthCityId

    /**
     * @param birthStateId
     *            @roseuid 42A5DD91017B
     */
    public void setBirthStateId(String birthStateId)
    {
	this.birthStateId = birthStateId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setBirthStateId

    /**
     * @return String
     * @roseuid 42A5DD910187
     */
    public String getBirthStateId()
    {
	return birthStateId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getBirthStateId

    /**
     * @param raceId
     *            @roseuid 42A5DD910189
     */
    public void setRaceId(String raceId)
    {
	this.raceId = raceId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setRaceId

    /**
     * @return String
     * @roseuid 42A5DD910196
     */
    public String getRaceId()
    {
	return raceId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getRaceId

    /**
     * @param hispanic
     *            @roseuid 42A5DD910198
     */
    public void setHispanic(String hispanic)
    {
	this.hispanic = hispanic;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setHispanic

    /**
     * @return boolean
     * @roseuid 42A5DD91019A
     */
    public String getHispanic()
    {
	return hispanic;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getHispanic

    /**
     * @param multiracial
     *            @roseuid 42A5DD9101A5
     */
    public void setMultiracial(boolean multiracial)
    {
	this.multiracial = multiracial;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setMultiracial

    /**
     * @return boolean
     * @roseuid 42A5DD9101A7
     */
    public boolean getMultiracial()
    {
	return multiracial;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getMultiracial

    /**
     * @param dateOfBirth
     *            @roseuid 42A5DD9101B5
     */
    public void setDateOfBirth(Date dateOfBirth)
    {
	this.dateOfBirth = dateOfBirth;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setDateOfBirth

    /**
     * @return Date
     * @roseuid 42A5DD9101B7
     */
    public Date getDateOfBirth()
    {
	return dateOfBirth;
    } 
    
    public Date getRealDOB()
    {
	return this.realDOB;
    } 
    
    public void setRealDOB(Date realDOB)
    {
	this.realDOB = realDOB;

    } 

    /**
     * @param verifiedDOB
     *            @roseuid 42A5DD9101C5
     */
    public void setVerifiedDOB(boolean verifiedDOB)
    {
	this.verifiedDOB = verifiedDOB;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setVerifiedDOB

    /**
     * @return boolean
     * @roseuid 42A5DD9101C7
     */
    public boolean getVerifiedDOB()
    {
	return verifiedDOB;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getVerifiedDOB

    /**
     * @param birthCountyId
     *            @roseuid 42A5DD9101C9
     */
    public void setBirthCountyId(String birthCountyId)
    {
	this.birthCountyId = birthCountyId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setBirthCountyId

    /**
     * @return String
     * @roseuid 42A5DD9101D4
     */
    public String getBirthCountyId()
    {
	return birthCountyId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getBirthCountyId

    /**
     * @param ethnicityId
     *            @roseuid 42A5DD9101F4
     */
    public void setEthnicityId(String ethnicityId)
    {
	this.ethnicityId = ethnicityId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setEthnicityId

    /**
     * @return String
     * @roseuid 42A5DD9101F6
     */
    public String getEthnicityId()
    {
	return ethnicityId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getEthnicityId

    /**
     * @param nationalityId
     *            @roseuid 42A5DD910203
     */
    public void setNationalityId(String nationalityId)
    {
	this.nationalityId = nationalityId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setNationalityId

    /**
     * @return String
     * @roseuid 42A5DD910205
     */
    public String getNationalityId()
    {
	return nationalityId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getNationalityId

    /**
     * @param secondaryLanguageId
     *            @roseuid 42A5DD910207
     */
    public void setSecondaryLanguageId(String secondaryLanguageId)
    {
	this.secondaryLanguageId = secondaryLanguageId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setSecondaryLanguageId

    /**
     * @return String
     * @roseuid 42A5DD910213
     */
    public String getSecondaryLanguageId()
    {
	return secondaryLanguageId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getSecondaryLanguageId

    /**
     * @param primaryLanguageId
     *            @roseuid 42A5DD910222
     */
    public void setPrimaryLanguageId(String primaryLanguageId)
    {
	this.primaryLanguageId = primaryLanguageId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setPrimaryLanguageId

    /**
     * @return String
     * @roseuid 42A5DD910224
     */
    public String getPrimaryLanguageId()
    {
	return primaryLanguageId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getPrimaryLanguageId

    /**
     * @param driverLicenseNumber
     *            @roseuid 42A5DD910226
     */
    public void setDriverLicenseNumber(String driverLicenseNumber)
    {
	this.driverLicenseNumber = driverLicenseNumber;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setDriverLicenseNumber

    /**
     * @return String
     * @roseuid 42A5DD910233
     */
    public String getDriverLicenseNumber()
    {
	return driverLicenseNumber;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getDriverLicenseNumber

    /**
     * @param driverLicenseStateId
     *            @roseuid 42A5DD910235
     */
    public void setDriverLicenseStateId(String driverLicenseStateId)
    {
	this.driverLicenseStateId = driverLicenseStateId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setDriverLicenseStateId

    /**
     * @return String
     * @roseuid 42A5DD910243
     */
    public String getDriverLicenseStateId()
    {
	return driverLicenseStateId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getDriverLicenseStateId

    /**
     * @param SSN
     *            @roseuid 42A5DD910245
     */
    public void setSSN(String SSN)
    {
	this.SSN = SSN;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setSSN

    /**
     * @return String
     * @roseuid 42A5DD910251
     */
    public String getSSN()
    {
	return SSN;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getSSN

    /**
     * @param SID
     *            @roseuid 42A5DD910253
     */
    public void setSID(String SID)
    {
	this.SID = SID;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setSID

    /**
     * @return String
     * @roseuid 42A5DD910255
     */
    public String getSID()
    {
	return SID;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getSID

    /**
     * @param SONumber
     *            @roseuid 42A5DD910261
     */
    public void setSONumber(String SONumber)
    {
	this.SONumber = SONumber;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setSONumber

    /**
     * @return String
     * @roseuid 42A5DD910263
     */
    public String getSONumber()
    {
	return SONumber;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getSONumber

    /**
     * @return the educationId
     */
    public String getEducationId()
    {
	return educationId;
    }

    /**
     * @param educationId
     *            the educationId to set
     */
    public void setEducationId(String educationId)
    {
	this.educationId = educationId;
    }

    /**
     * @param DHSNumber
     *            @roseuid 42A5DD910271
     */
    public void setDHSNumber(String DHSNumber)
    {
	this.DHSNumber = DHSNumber;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setDHSNumber

    /**
     * @return String
     * @roseuid 42A5DD910273
     */
    public String getDHSNumber()
    {
	return DHSNumber;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getDHSNumber

    /**
     * @param PEIMSId
     *            @roseuid 42A5DD910280
     */
    public void setPEIMSId(String PEIMSId)
    {
	this.PEIMSId = PEIMSId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setPEIMSId

    /**
     * @return String
     * @roseuid 42A5DD910282
     */
    public String getPEIMSId()
    {
	return PEIMSId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getPEIMSId

    /**
     * @param alienNumber
     *            @roseuid 42A5DD910284
     */
    public void setAlienNumber(String alienNumber)
    {
	this.alienNumber = alienNumber;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setAlienNumber

    /**
     * @return String
     * @roseuid 42A5DD910290
     */
    public String getAlienNumber()
    {
	return alienNumber;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getAlienNumber

    /**
     * @param FBINumber
     *            @roseuid 42A5DD910292
     */
    public void setFBINumber(String FBINumber)
    {
	this.FBINumber = FBINumber;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setFBINumber

    /**
     * @return String
     * @roseuid 42A5DD910294
     */
    public String getFBINumber()
    {
	return FBINumber;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getFBINumber

    /**
     * @param natualEyeColorId
     *            @roseuid 42A5DD9102A0
     */
    public void setNatualEyeColorId(String natualEyeColorId)
    {
	this.natualEyeColorId = natualEyeColorId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setNatualEyeColorId

    /**
     * @return String
     * @roseuid 42A5DD9102A2
     */
    public String getNatualEyeColorId()
    {
	return natualEyeColorId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getNatualEyeColorId

    /**
     * @param naturalHairColorId
     *            @roseuid 42A5DD9102AF
     */
    public void setNaturalHairColorId(String naturalHairColorId)
    {
	this.naturalHairColorId = naturalHairColorId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setNaturalHairColorId

    /**
     * @return String
     * @roseuid 42A5DD9102B1
     */
    public String getNaturalHairColorId()
    {
	return naturalHairColorId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getNaturalHairColorId

    /**
     * @param complexionId
     *            @roseuid 42A5DD9102BF
     */
    public void setComplexionId(String complexionId)
    {
	this.complexionId = complexionId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setComplexionId

    /**
     * @return String
     * @roseuid 42A5DD9102C1
     */
    public String getComplexionId()
    {
	return complexionId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getComplexionId

    /**
     * @param parentsMaritalStatusId
     *            @roseuid 42A5DD9102EE
     */
    public void setParentsMaritalStatusId(String parentsMaritalStatusId)
    {
	this.parentsMaritalStatusId = parentsMaritalStatusId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setParentsMaritalStatusId

    /**
     * @return String
     * @roseuid 42A5DD9102F0
     */
    public String getParentsMaritalStatusId()
    {
	return parentsMaritalStatusId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getParentsMaritalStatusId

    /**
     * @param religionId
     *            @roseuid 42A5DD9102F2
     */
    public void setReligionId(String religionId)
    {
	this.religionId = religionId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setReligionId

    /**
     * @return String
     * @roseuid 42A5DD9102FE
     */
    public String getReligionId()
    {
	return religionId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getReligionId

    /**
     * @param DNASampleNumber
     *            @roseuid 42A5DD910300
     */
    public void setDNASampleNumber(String DNASampleNumber)
    {
	this.DNASampleNumber = DNASampleNumber;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setDNASampleNumber

    /**
     * @return String
     * @roseuid 42A5DD91030D
     */
    public String getDNASampleNumber()
    {
	return DNASampleNumber;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getDNASampleNumber

    /**
     * @param dateSenttoDPS
     *            @roseuid 42A5DD91030F
     */
    public void setDateSenttoDPS(Date dateSenttoDPS)
    {
	this.dateSenttoDPS = dateSenttoDPS;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setDateSenttoDPS

    /**
     * @return Date
     * @roseuid 42A5DD910311
     */
    public Date getDateSenttoDPS()
    {
	return dateSenttoDPS;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getDateSenttoDPS

    /**
     * @param sexId
     *            @roseuid 42A5DD91031C
     */
    public void setSexId(String sexId)
    {
	this.sexId = sexId;

    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setSexId

    /**
     * @return String
     * @roseuid 42A5DD91031E
     */
    public String getSexId()
    {
	return sexId;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getSexId

    /**
     * @return The juvenile num.
     */
    public String getJuvenileNum()
    {
	return juvenileNum;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getJuvenileNum

    /**
     * @param string
     *            The juvenile num.
     */
    public void setJuvenileNum(String string)
    {
	juvenileNum = string;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setJuvenileNum

    /**
     * @return The first name.
     */
    public String getFirstName()
    {
	return firstName;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getFirstName

    /**
     * @return The last name.
     */
    public String getLastName()
    {
	return lastName;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getLastName

    /**
     * @return The middle name.
     */
    public String getMiddleName()
    {
	return middleName;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getMiddleName

    /**
     * @return the nameSuffix
     */
    public String getNameSuffix()
    {
	return nameSuffix;
    }

    /**
     * @param string
     *            The first name.
     */
    public void setFirstName(String string)
    {
	firstName = string;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setFirstName

    /**
     * @param nameSuffix
     *            the nameSuffix to set
     */
    public void setNameSuffix(String nameSuffix)
    {
	this.nameSuffix = nameSuffix;
    }

    public String getPreferredFirstName()
    {
        return preferredFirstName;
    }

    public void setPreferredFirstName(String preferredFirstName)
    {
        this.preferredFirstName = preferredFirstName;
    }

    /**
     * @param string
     *            The last name.
     */
    public void setLastName(String string)
    {
	lastName = string;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setLastName

    /**
     * @param string
     *            The middle name.
     */
    public void setMiddleName(String string)
    {
	middleName = string;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setMiddleName

    /**
     * @return The scars and marks.
     */
    public Collection getScarsAndMarks()
    {
	return scarsAndMarks;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getScarsAndMarks

    /**
     * @return The tattoos.
     */
    public Collection getTattoos()
    {
	return tattoos;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.getTattoos

    /**
     * @param collection
     *            The scars and marks.
     */
    public void setScarsAndMarks(Collection collection)
    {
	scarsAndMarks = collection;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setScarsAndMarks

    /**
     * @param collection
     *            The tattoos.
     */
    public void setTattoos(Collection collection)
    {
	tattoos = collection;
    } //end of messaging.juvenile.SaveJuvenileProfileMainEvent.setTattoos

    /**
     * @return
     */
    public String getDriverLicenseClassId()
    {
	return driverLicenseClassId;
    }

    /**
     * @return
     */
    public Date getDriverLicenseExpireDate()
    {
	return driverLicenseExpireDate;
    }

    /**
     * @param string
     */
    public void setDriverLicenseClassId(String string)
    {
	driverLicenseClassId = string;
    }

    /**
     * @param date
     */
    public void setDriverLicenseExpireDate(Date date)
    {
	driverLicenseExpireDate = date;
    }

    /**
     * @return Returns the isUSCitizenId.
     */
    public String getIsUSCitizenId()
    {
	return isUSCitizenId;
    }

    /**
     * @param isUSCitizenId
     *            The isUSCitizenId to set.
     */
    public void setIsUSCitizenId(String isUSCitizenId)
    {
	this.isUSCitizenId = isUSCitizenId;
    }

    /**
     * @return Returns the failedPlacements.
     */
    public String getFailedPlacements()
    {
	return failedPlacements;
    }

    /**
     * @param failedPlacements
     *            The failedPlacements to set.
     */
    public void setFailedPlacements(String failedPlacements)
    {
	this.failedPlacements = failedPlacements;
    }

    /**
     * @return Returns the isAdopted.
     */
    public boolean isAdopted()
    {
	return isAdopted;
    }

    /**
     * @param isAdopted
     *            The isAdopted to set.
     */
    public void setAdopted(boolean isAdopted)
    {
	this.isAdopted = isAdopted;
    }

    /**
     * @return Returns the adoptionComments.
     */
    public String getAdoptionComments()
    {
	return adoptionComments;
    }

    /**
     * @param adoptionComments
     *            The adoptionComments to set.
     */
    public void setAdoptionComments(String adoptionComments)
    {
	this.adoptionComments = adoptionComments;
    }

    /**
     * @param studentId
     *            the studentId to set
     */
    public void setStudentId(String studentId)
    {
	this.studentId = studentId;
    }

    /**
     * @return the studentId
     */
    public String getStudentId()
    {
	return studentId;
    }

    public String getAction()
    {
	return action;
    }

    public void setAction(String action)
    {
	this.action = action;
    }

    public String getStatusId()
    {
	return statusId;
    }

    public void setStatusId(String statusId)
    {
	this.statusId = statusId;
    }

    public List<JuvenileReferralMemberDetailsBean> getMemberDetailsBeanList()
    {
	return memberDetailsBeanList;
    }

    public void setMemberDetailsBeanList(List<JuvenileReferralMemberDetailsBean> memberDetailsBeanList)
    {
	this.memberDetailsBeanList = memberDetailsBeanList;
    }

    public Address getJuvAddress()
    {
	return juvAddress;
    }

    public void setJuvAddress(Address juvAddress)
    {
	this.juvAddress = juvAddress;
    }

    public String getHispanicStr()
    {
	return hispanicStr;
    }

    public void setHispanicStr(String hispanicStr)
    {
	this.hispanicStr = hispanicStr;
    }

    public String getSchoolId()
    {
	return schoolId;
    }

    public void setSchoolId(String schoolId)
    {
	this.schoolId = schoolId;
    }

    public String getSchoolDistId()
    {
	return schoolDistId;
    }

    public void setSchoolDistId(String schoolDistId)
    {
	this.schoolDistId = schoolDistId;
    }

    public String getExitTypeId()
    {
	return exitTypeId;
    }

    public void setExitTypeId(String exitTypeId)
    {
	this.exitTypeId = exitTypeId;
    }

    public String getPgmAttdngId()
    {
	return pgmAttdngId;
    }

    public void setPgmAttdngId(String pgmAttdngId)
    {
	this.pgmAttdngId = pgmAttdngId;
    }

    public String getGradeLvlId()
    {
	return gradeLvlId;
    }

    public void setGradeLvlId(String gradeLvlId)
    {
	this.gradeLvlId = gradeLvlId;
    }

    public String getSchoolAttendanceStatusId()
    {
	return schoolAttendanceStatusId;
    }

    public void setSchoolAttendanceStatusId(String schoolAttendanceStatusId)
    {
	this.schoolAttendanceStatusId = schoolAttendanceStatusId;
    }

    public String getSchoolteaCode()
    {
	return schoolteaCode;
    }

    public void setSchoolteaCode(String schoolteaCode)
    {
	this.schoolteaCode = schoolteaCode;
    }

    public String getJuvFromM204Flag()
    {
	return juvFromM204Flag;
    }

    public void setJuvFromM204Flag(String juvFromM204Flag)
    {
	this.juvFromM204Flag = juvFromM204Flag;
    }

    public String getLcuser()
    {
	return lcuser;
    }

    public void setLcuser(String lcuser)
    {
	this.lcuser = lcuser;
    }

    public String getTSDSId()
    {
	return TSDSId;
    }

    public void setTSDSId(String tSDSId)
    {
	TSDSId = tSDSId;
    }

    public boolean getUpdateSSN()
    {
	return updateSSN;
    }

    public void setUpdateSSN(boolean updateSSN)
    {
	this.updateSSN = updateSSN;
    }

    public int getJuvExcludedReporting()
    {
	return juvExcludedReporting;
    }

    public void setJuvExcludedReporting(int juvExcludedReporting)
    {
	this.juvExcludedReporting = juvExcludedReporting;
    }

    public String getPassportNum()
    {
        return passportNum;
    }

    public void setPassportNum(String passportNum)
    {
        this.passportNum = passportNum;
    }

    public String getCountryOfIssuanceId()
    {
        return countryOfIssuanceId;
    }

    public void setCountryOfIssuanceId(String countryOfIssuanceId)
    {
        this.countryOfIssuanceId = countryOfIssuanceId;
    }

    public Date getPassportExpirationDate()
    {
        return passportExpirationDate;
    }

    public void setPassportExpirationDate(Date passportExpirationDate)
    {
        this.passportExpirationDate = passportExpirationDate;
    }

    public String getYouthLivesWith()
    {
        return youthLivesWith;
    }

    public void setYouthLivesWith(String youthLivesWith)
    {
        this.youthLivesWith = youthLivesWith;
    }
    
    
} // end SaveJuvenileProfileMainEvent

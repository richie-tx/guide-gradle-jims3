package messaging.districtCourtHearings.reply;/*
 * Create on May 24, 2006
 //Not in Use

package messaging.districtCourtHearings.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

import ui.common.Name;

public class JJSCLCourtActionDocketResponseEvent extends ResponseEvent implements Comparable
{
    *//**
	 * 
	 *//*
    private static final long serialVersionUID = 1L;
    private String sortOrder;
    private String docketEventId;
    private String clCourtId;
    private String clDetentionId;
    private String clAncillaryId;
    private Date eventDate;
    private Date eventDateWithTime;
    private String chainNumber; //added for facility
    private String courtTime;
    private String formattedCourtTime; //HHMM
    private String petitionNumber;
    private String court;
    private String allegation;
    private String allegationDesc;
    private String courtResult;
    private String prevCourtResult;
    private String courtResultDesc;
    private String disposition;
    private String dispositionDesc;
    private String hearingType;
    private String hearingTypeDesc;
    private String referralNum; //added for facility
    private String seqNum; //added for facility
    private String juvenileNumber;
    private String juvenileName;

    //added for court conversion to sql U.S #11645
    private String docketType;
    private String alternateSettingInd;
    private String attorneyName;
    private String barNum;
    private String updateFlag;
    private String attorneyConnection;
    private String attorneyConnectionDesc;
    private String comments;
    private String issueFlag;
    private String lcDate;
    private String lcTime;
    private String lcUser;

    //comes from juv object - derived.
    private String age;
    private String race;
    private String gender;
    private String raceId;
    private String sexId;
    private String hispanicInd;

    //new fields added for detention hearing.
    private String transferTo;
    private String resetReason;
    private String resetTo;

    //detention Hearing Display
    private String admitDate;
    private String probationOfficerCd;
    private String probationOfficer;
    private String probableCauseDate;
    private String lastCourtDate;
    private String petitionStatus;
    private String petitionStatusCd;
    private String petitionAmendment;
    private String petitionAmendmentDate;
    private String courtDate;
    private String petitionAllegation;
    private String petitionAllegationDesc;
    private String petitionType;
    private String assignedJudge;

    //adding for ancillary court
    private String filingDate;
    private String juryFlag;
    private String prevNotes;
    private String recType;
    private String resetHearingType;
    private String respondantName;
    private String settingReason;
    private String tjpcSeqNum;
    private String typeCase;

    //adding for court Docket Display PDFs
    private String jpoOfRecord;
    private Name father;
    private Name mother;
    private Name other;
    private String relationship; //other relationship
    private String facilityName;
    private String dob;
    private String amendment;
    private String offenseCode; //for CLS field for Court Calendar Records ONLY
    private String noJuvFamily = ""; //If a family constellation is not associated to the juvenile, list:  ** {JUV.NUM} ---NO FAMILY FOUND **
    private String optionFlag;

    //For UI
    private String setNote;
    private String prevHearingDate; //added for Court Docket Display, PRE field, JJSCLCOURT
    private String petitionNumUI; //added for Court DOcket UI, Detention Setting, Bug #68440
    private String[] selectedDocketsForUpdate;

    public String getSortOrder()
    {
	return sortOrder;
    }

    public void setSortOrder(String sortOrder)
    {
	this.sortOrder = sortOrder;
    }

    *//**
     * @return the filingDate
     *//*
    public String getFilingDate()
    {
	return filingDate;
    }

    *//**
     * @param filingDate
     *            the filingDate to set
     *//*
    public void setFilingDate(String filingDate)
    {
	this.filingDate = filingDate;
    }

    *//**
     * @return the juryFlag
     *//*
    public String getJuryFlag()
    {
	return juryFlag;
    }

    *//**
     * @param juryFlag
     *            the juryFlag to set
     *//*
    public void setJuryFlag(String juryFlag)
    {
	this.juryFlag = juryFlag;
    }

    *//**
     * @return the prevNotes
     *//*
    public String getPrevNotes()
    {
	return prevNotes;
    }

    *//**
     * @param prevNotes
     *            the prevNotes to set
     *//*
    public void setPrevNotes(String prevNotes)
    {
	this.prevNotes = prevNotes;
    }

    *//**
     * @return the recType
     *//*
    public String getRecType()
    {
	return recType;
    }

    *//**
     * @param recType
     *            the recType to set
     *//*
    public void setRecType(String recType)
    {
	this.recType = recType;
    }

    *//**
     * @return the resetHearingType
     *//*
    public String getResetHearingType()
    {
	return resetHearingType;
    }

    *//**
     * @param resetHearingType
     *            the resetHearingType to set
     *//*
    public void setResetHearingType(String resetHearingType)
    {
	this.resetHearingType = resetHearingType;
    }

    *//**
     * @return the respondantName
     *//*
    public String getRespondantName()
    {
	return respondantName;
    }

    *//**
     * @param respondantName
     *            the respondantName to set
     *//*
    public void setRespondantName(String respondantName)
    {
	this.respondantName = respondantName;
    }

    *//**
     * @return the settingReason
     *//*
    public String getSettingReason()
    {
	return settingReason;
    }

    *//**
     * @param settingReason
     *            the settingReason to set
     *//*
    public void setSettingReason(String settingReason)
    {
	this.settingReason = settingReason;
    }

    *//**
     * @return the tjpcSeqNum
     *//*
    public String getTjpcSeqNum()
    {
	return tjpcSeqNum;
    }

    *//**
     * @param tjpcSeqNum
     *            the tjpcSeqNum to set
     *//*
    public void setTjpcSeqNum(String tjpcSeqNum)
    {
	this.tjpcSeqNum = tjpcSeqNum;
    }

    *//**
     * @return the typeCase
     *//*
    public String getTypeCase()
    {
	return typeCase;
    }

    *//**
     * @param typeCase
     *            the typeCase to set
     *//*
    public void setTypeCase(String typeCase)
    {
	this.typeCase = typeCase;
    }

    *//**
     * @return the serialversionuid
     *//*
    public static long getSerialversionuid()
    {
	return serialVersionUID;
    }

    *//**
     * @return the age
     *//*
    public String getAge()
    {
	return age;
    }

    *//**
     * @param age
     *            the age to set
     *//*
    public void setAge(String age)
    {
	this.age = age;
    }

    *//**
     * @return the race
     *//*
    public String getRace()
    {
	return race;
    }

    *//**
     * @param race
     *            the race to set
     *//*
    public void setRace(String race)
    {
	this.race = race;
    }

    public String getRaceId()
    {
	return raceId;
    }

    public void setRaceId(String raceId)
    {
	this.raceId = raceId;
    }

    public String getHispanicInd()
    {
	return hispanicInd;
    }

    public void setHispanicInd(String hispanicInd)
    {
	this.hispanicInd = hispanicInd;
    }

    public String getSexId()
    {
	return sexId;
    }

    public void setSexId(String sexId)
    {
	this.sexId = sexId;
    }

    *//**
     * @return the gender
     *//*
    public String getGender()
    {
	return gender;
    }

    *//**
     * @param gender
     *            the gender to set
     *//*
    public void setGender(String gender)
    {
	this.gender = gender;
    }

    *//**
     * @return Returns the allegation.
     *//*
    public String getAllegation()
    {
	return allegation;
    }

    *//**
     * @param allegation
     *            The allegation to set.
     *//*
    public void setAllegation(String allegation)
    {
	this.allegation = allegation;
    }

    *//**
     * @return Returns the court.
     *//*
    public String getCourt()
    {
	return court;
    }

    *//**
     * @param court
     *            The court to set.
     *//*
    public void setCourt(String court)
    {
	this.court = court;
    }

    *//**
     * @return Returns the courtResult.
     *//*
    public String getCourtResult()
    {
	return courtResult;
    }

    *//**
     * @param courtResult
     *            The courtResult to set.
     *//*
    public void setCourtResult(String courtResult)
    {
	this.courtResult = courtResult;
    }

    *//**
     * @return
     *//*
    public String getPrevCourtResult()
    {
	return prevCourtResult;
    }

    *//**
     * Used to Jquery
     * 
     * @param prevCourtResult
     *//*
    public void setPrevCourtResult(String prevCourtResult)
    {
	this.prevCourtResult = prevCourtResult;
    }

    *//**
     * @return Returns the disposition.
     *//*
    public String getDisposition()
    {
	return disposition;
    }

    *//**
     * @param disposition
     *            The disposition to set.
     *//*
    public void setDisposition(String disposition)
    {
	this.disposition = disposition;
    }

    *//**
     * @return Returns the docketType.
     *//*
    public String getDocketType()
    {
	return docketType;
    }

    *//**
     * @param docketType
     *            The docketType to set.
     *//*
    public void setDocketType(String docketType)
    {
	this.docketType = docketType;
    }

    *//**
     * @return Returns the hearingType.
     *//*
    public String getHearingType()
    {
	return hearingType;
    }

    *//**
     * @param hearingType
     *            The hearingType to set.
     *//*
    public void setHearingType(String hearingType)
    {
	this.hearingType = hearingType;
    }

    *//**
     * @return Returns the petitionNumber.
     *//*
    public String getPetitionNumber()
    {
	return petitionNumber;
    }

    *//**
     * @param petitionNumber
     *            The petitionNumber to set.
     *//*
    public void setPetitionNumber(String petitionNumber)
    {
	this.petitionNumber = petitionNumber;
    }

     (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     
    public int compareTo(JJSCLCourtActionDocketResponseEvent obj)
    {
	if (obj == null)
	    return -1;

	JJSCLCourtActionDocketResponseEvent evt = obj;
	int result = 0;

	try
	{
	    if (this.getEventDate() != null || evt.getEventDate() != null)
	    {
		if (this.getEventDate() == null)
		{ 
		   * this makes any null objects go to the bottom. change this to 1 if you want
		   * the top of the list
		   
		    return -1;
		}

		if (evt.getEventDate() == null)
		{ 
		   * this makes any null objects go to the bottom. change this to -1 if you want
		   * the top of the list
		   
		    return 1;
		}
		result = this.getEventDate().compareTo(evt.getEventDate());
		// backwards in order to get list to show up most recent first
		//result = evt.getEventDate().compareTo(getEventDate());
	    }

	}
	catch (NumberFormatException e)
	{
	    result = 0;
	}

	return result;
    }

    *//**
     * @return Returns the eventDate.
     *//*
    public Date getEventDate()
    {
	return eventDate;
    }

    *//**
     * @param eventDate
     *            The eventDate to set.
     *//*
    public void setEventDate(Date eventDate)
    {
	this.eventDate = eventDate;
    }

    *//**
     * @return Returns the juvenileName.
     *//*
    public String getJuvenileName()
    {
	return juvenileName;
    }

    *//**
     * @param juvenileName
     *            The juvenileName to set.
     *//*
    public void setJuvenileName(String juvenileName)
    {
	this.juvenileName = juvenileName;
    }

    *//**
     * @return Returns the juvenileNumber.
     *//*
    public String getJuvenileNumber()
    {
	return juvenileNumber;
    }

    *//**
     * @param juvenileNumber
     *            The juvenileNumber to set.
     *//*
    public void setJuvenileNumber(String juvenileNumber)
    {
	this.juvenileNumber = juvenileNumber;
    }

    *//**
     * @return Returns the docketEventId.
     *//*
    public String getDocketEventId()
    {
	return docketEventId;
    }

    *//**
     * @param docketEventId
     *            The docketEventId to set.
     *//*
    public void setDocketEventId(String docketEventId)
    {
	this.docketEventId = docketEventId;
    }

    public String getClCourtId()
    {
	return clCourtId;
    }

    public void setClCourtId(String clCourtId)
    {
	this.clCourtId = clCourtId;
    }

    public String getClDetentionId()
    {
	return clDetentionId;
    }

    public void setClDetentionId(String clDetentionId)
    {
	this.clDetentionId = clDetentionId;
    }

    public String getClAncillaryId()
    {
	return clAncillaryId;
    }

    public void setClAncillaryId(String clAncillaryId)
    {
	this.clAncillaryId = clAncillaryId;
    }

    *//**
     * @return Returns the attorneyName.
     *//*
    public String getAttorneyName()
    {
	return attorneyName;
    }

    *//**
     * @param attorneyName
     *            The attorneyName to set.
     *//*
    public void setAttorneyName(String attorneyName)
    {
	this.attorneyName = attorneyName;
    }

    *//**
     * @return Returns the allegationDesc.
     *//*
    public String getAllegationDesc()
    {
	return allegationDesc;
    }

    *//**
     * @param allegationDesc
     *            The allegationDesc to set.
     *//*
    public void setAllegationDesc(String allegationDesc)
    {
	this.allegationDesc = allegationDesc;
    }

    *//**
     * @return Returns the courtResultDesc.
     *//*
    public String getCourtResultDesc()
    {
	return courtResultDesc;
    }

    *//**
     * @param courtResultDesc
     *            The courtResultDesc to set.
     *//*
    public void setCourtResultDesc(String courtResultDesc)
    {
	this.courtResultDesc = courtResultDesc;
    }

    *//**
     * @return Returns the dispositionDesc.
     *//*
    public String getDispositionDesc()
    {
	return dispositionDesc;
    }

    *//**
     * @param dispositionDesc
     *            The dispositionDesc to set.
     *//*
    public void setDispositionDesc(String dispositionDesc)
    {
	this.dispositionDesc = dispositionDesc;
    }

    *//**
     * @return Returns the hearingTypeDesc.
     *//*
    public String getHearingTypeDesc()
    {
	return hearingTypeDesc;
    }

    *//**
     * @param hearingTypeDesc
     *            The hearingTypeDesc to set.
     *//*
    public void setHearingTypeDesc(String hearingTypeDesc)
    {
	this.hearingTypeDesc = hearingTypeDesc;
    }

    *//**
     * @return Returns the courtTime.
     *//*
    public String getCourtTime()
    {
	return courtTime;
    }

    *//**
     * @param courtTime
     *            The courtTime to set.
     *//*
    public void setCourtTime(String courtTime)
    {
	this.courtTime = courtTime;
    }

    public Date getEventDateWithTime()
    {
	return eventDateWithTime;
    }

    public void setEventDateWithTime(Date eventDateWithTime)
    {
	this.eventDateWithTime = eventDateWithTime;
    }

    public String getReferralNum()
    {
	return referralNum;
    }

    public void setReferralNum(String referralNum)
    {
	this.referralNum = referralNum;
    }

    public String getSeqNum()
    {
	return seqNum;
    }

    public void setSeqNum(String seqNum)
    {
	this.seqNum = seqNum;
    }

    public String getChainNumber()
    {
	return chainNumber;
    }

    public void setChainNumber(String chainNumber)
    {
	this.chainNumber = chainNumber;
    }

    *//**
     * @return the attorneyConnection
     *//*
    public String getAttorneyConnection()
    {
	return attorneyConnection;
    }

    *//**
     * @param attorneyConnection
     *            the attorneyConnection to set
     *//*
    public void setAttorneyConnection(String attorneyConnection)
    {
	this.attorneyConnection = attorneyConnection;
    }

    *//**
     * @return the comments
     *//*
    public String getComments()
    {
	return comments;
    }

    *//**
     * @param comments
     *            the comments to set
     *//*
    public void setComments(String comments)
    {
	this.comments = comments;
    }

    *//**
     * @return the alternateSettingInd
     *//*
    public String getAlternateSettingInd()
    {
	return alternateSettingInd;
    }

    *//**
     * @param alternateSettingInd
     *            the alternateSettingInd to set
     *//*
    public void setAlternateSettingInd(String alternateSettingInd)
    {
	this.alternateSettingInd = alternateSettingInd;
    }

    *//**
     * @return the barNum
     *//*
    public String getBarNum()
    {
	return barNum;
    }

    *//**
     * @param barNum
     *            the barNum to set
     *//*
    public void setBarNum(String barNum)
    {
	this.barNum = barNum;
    }

    *//**
     * @return the transferTo
     *//*
    public String getTransferTo()
    {
	return transferTo;
    }

    *//**
     * @param transferTo
     *            the transferTo to set
     *//*
    public void setTransferTo(String transferTo)
    {
	this.transferTo = transferTo;
    }

    *//**
     * @return the resetReason
     *//*
    public String getResetReason()
    {
	return resetReason;
    }

    *//**
     * @param resetReason
     *            the resetReason to set
     *//*
    public void setResetReason(String resetReason)
    {
	this.resetReason = resetReason;
    }

    *//**
     * @return the resetTo
     *//*
    public String getResetTo()
    {
	return resetTo;
    }

    *//**
     * @param resetTo
     *            the resetTo to set
     *//*
    public void setResetTo(String resetTo)
    {
	this.resetTo = resetTo;
    }

    *//**
     * @return the updateFlag
     *//*
    public String getUpdateFlag()
    {
	return updateFlag;
    }

    *//**
     * @param updateFlag
     *            the updateFlag to set
     *//*
    public void setUpdateFlag(String updateFlag)
    {
	this.updateFlag = updateFlag;
    }

    *//**
     * @return the lcDate
     *//*
    public String getLcDate()
    {
	return lcDate;
    }

    *//**
     * @param lcDate
     *            the lcDate to set
     *//*
    public void setLcDate(String lcDate)
    {
	this.lcDate = lcDate;
    }

    *//**
     * @return the lcTime
     *//*
    public String getLcTime()
    {
	return lcTime;
    }

    *//**
     * @param lcTime
     *            the lcTime to set
     *//*
    public void setLcTime(String lcTime)
    {
	this.lcTime = lcTime;
    }

    *//**
     * @return the lcUser
     *//*
    public String getLcUser()
    {
	return lcUser;
    }

    *//**
     * @param lcUser
     *            the lcUser to set
     *//*
    public void setLcUser(String lcUser)
    {
	this.lcUser = lcUser;
    }

    *//**
     * @return the issueFlag
     *//*
    public String getIssueFlag()
    {
	return issueFlag;
    }

    *//**
     * @param issueFlag
     *            the issueFlag to set
     *//*
    public void setIssueFlag(String issueFlag)
    {
	this.issueFlag = issueFlag;
    }

    *//**
     * @return the admitDate
     *//*
    public String getAdmitDate()
    {
	return admitDate;
    }

    *//**
     * @param admitDate
     *            the admitDate to set
     *//*
    public void setAdmitDate(String admitDate)
    {
	this.admitDate = admitDate;
    }

    *//**
     * @return the probationOfficerCd
     *//*
    public String getProbationOfficerCd()
    {
	return probationOfficerCd;
    }

    *//**
     * @param probationOfficerCd
     *            the probationOfficerCd to set
     *//*
    public void setProbationOfficerCd(String probationOfficerCd)
    {
	this.probationOfficerCd = probationOfficerCd;
    }

    *//**
     * @return the probableCauseDate
     *//*
    public String getProbableCauseDate()
    {
	return probableCauseDate;
    }

    *//**
     * @param probableCauseDate
     *            the probableCauseDate to set
     *//*
    public void setProbableCauseDate(String probableCauseDate)
    {
	this.probableCauseDate = probableCauseDate;
    }

    *//**
     * @return the lastCourtDate
     *//*
    public String getLastCourtDate()
    {
	return lastCourtDate;
    }

    *//**
     * @param lastCourtDate
     *            the lastCourtDate to set
     *//*
    public void setLastCourtDate(String lastCourtDate)
    {
	this.lastCourtDate = lastCourtDate;
    }

    *//**
     * @return the petitionStatus
     *//*
    public String getPetitionStatus()
    {
	return petitionStatus;
    }

    *//**
     * @param petitionStatus
     *            the petitionStatus to set
     *//*
    public void setPetitionStatus(String petitionStatus)
    {
	this.petitionStatus = petitionStatus;
    }

    public String getPetitionStatusCd()
    {
	return petitionStatusCd;
    }

    public void setPetitionStatusCd(String petitionStatusCd)
    {
	this.petitionStatusCd = petitionStatusCd;
    }

    *//**
     * @return the petitionAmendment
     *//*
    public String getPetitionAmendment()
    {
	return petitionAmendment;
    }

    *//**
     * @param petitionAmendment
     *            the petitionAmendment to set
     *//*
    public void setPetitionAmendment(String petitionAmendment)
    {
	this.petitionAmendment = petitionAmendment;
    }

    *//**
     * @return the petitionAllegation
     *//*
    public String getPetitionAllegation()
    {
	return petitionAllegation;
    }

    *//**
     * @param petitionAllegation
     *            the petitionAllegation to set
     *//*
    public void setPetitionAllegation(String petitionAllegation)
    {
	this.petitionAllegation = petitionAllegation;
    }

    *//**
     * @return the petitionAllegationDesc
     *//*
    public String getPetitionAllegationDesc()
    {
	return petitionAllegationDesc;
    }

    *//**
     * @param petitionAllegationDesc
     *            the petitionAllegationDesc to set
     *//*
    public void setPetitionAllegationDesc(String petitionAllegationDesc)
    {
	this.petitionAllegationDesc = petitionAllegationDesc;
    }

    *//**
     * @return the courtDate
     *//*
    public String getCourtDate()
    {
	return courtDate;
    }

    *//**
     * @param courtDate
     *            the courtDate to set
     *//*
    public void setCourtDate(String courtDate)
    {
	this.courtDate = courtDate;
    }

    *//**
     * @return the assignedJudge
     *//*
    public String getAssignedJudge()
    {
	return assignedJudge;
    }

    *//**
     * @param assignedJudge
     *            the assignedJudge to set
     *//*
    public void setAssignedJudge(String assignedJudge)
    {
	this.assignedJudge = assignedJudge;
    }

    *//**
     * @return the setNote
     *//*
    public String getSetNote()
    {
	return setNote;
    }

    *//**
     * @param setNote
     *            the setNote to set
     *//*
    public void setSetNote(String setNote)
    {
	this.setNote = setNote;
    }

    public String getPetitionAmendmentDate()
    {
	return petitionAmendmentDate;
    }

    public void setPetitionAmendmentDate(String petitionAmendmentDate)
    {
	this.petitionAmendmentDate = petitionAmendmentDate;
    }

    public String getPetitionType()
    {
	return petitionType;
    }

    public void setPetitionType(String petitionType)
    {
	this.petitionType = petitionType;
    }

    @Override
    public int compareTo(Object o)
    {
	// TODO Auto-generated method stub
	return 0;
    }

    public String getProbationOfficer()
    {
	return probationOfficer;
    }

    public void setProbationOfficer(String probationOfficer)
    {
	this.probationOfficer = probationOfficer;
    }

    *//**
     * @return the prevHearingDate
     *//*
    public String getPrevHearingDate()
    {
	return prevHearingDate;
    }

    *//**
     * @param prevHearingDate
     *            the prevHearingDate to set
     *//*
    public void setPrevHearingDate(String prevHearingDate)
    {
	this.prevHearingDate = prevHearingDate;
    }

    *//**
     * @return the formattedCourtTime
     *//*
    public String getFormattedCourtTime()
    {
	return formattedCourtTime;
    }

    *//**
     * @param formattedCourtTime
     *            the formattedCourtTime to set
     *//*
    public void setFormattedCourtTime(String formattedCourtTime)
    {
	this.formattedCourtTime = formattedCourtTime;
    }

    *//**
     * @return the jpoOfRecord
     *//*
    public String getJpoOfRecord()
    {
	return jpoOfRecord;
    }

    *//**
     * @param jpoOfRecord
     *            the jpoOfRecord to set
     *//*
    public void setJpoOfRecord(String jpoOfRecord)
    {
	this.jpoOfRecord = jpoOfRecord;
    }

    public Name getFather()
    {
	return father;
    }

    public void setFather(Name father)
    {
	this.father = father;
    }

    public Name getMother()
    {
	return mother;
    }

    public void setMother(Name mother)
    {
	this.mother = mother;
    }

    public Name getOther()
    {
	return other;
    }

    public void setOther(Name other)
    {
	this.other = other;
    }

    *//**
     * @return the facilityStatus
     *//*
    public String getFacilityName()
    {
	return facilityName;
    }

    *//**
     * @param facilityStatus
     *            the facilityStatus to set
     *//*
    public void setFacilityName(String facilityName)
    {
	this.facilityName = facilityName;
    }

    *//**
     * @return the dob
     *//*
    public String getDob()
    {
	return dob;
    }

    *//**
     * @param dob
     *            the dob to set
     *//*
    public void setDob(String dob)
    {
	this.dob = dob;
    }

    *//**
     * @return the relationShip
     *//*
    public String getRelationship()
    {
	return relationship;
    }

    *//**
     * @param relationShip
     *            the relationShip to set
     *//*
    public void setRelationship(String relationship)
    {
	this.relationship = relationship;
    }

    *//**
     * @return the amendment
     *//*
    public String getAmendment()
    {
	return amendment;
    }

    *//**
     * @param amendment
     *            the amendment to set
     *//*
    public void setAmendment(String amendment)
    {
	this.amendment = amendment;
    }

    *//**
     * @return the offenseCode
     *//*
    public String getOffenseCode()
    {
	return offenseCode;
    }

    *//**
     * @param offenseCode
     *            the offenseCode to set
     *//*
    public void setOffenseCode(String offenseCode)
    {
	this.offenseCode = offenseCode;
    }

    *//**
     * @return the petitionNumUI
     *//*
    public String getPetitionNumUI()
    {
	return petitionNumUI;
    }

    *//**
     * @param petitionNumUI
     *            the petitionNumUI to set
     *//*
    public void setPetitionNumUI(String petitionNumUI)
    {
	this.petitionNumUI = petitionNumUI;
    }

    public String getAttorneyConnectionDesc()
    {
	return attorneyConnectionDesc;
    }

    public void setAttorneyConnectionDesc(String attorneyConnectionDesc)
    {
	this.attorneyConnectionDesc = attorneyConnectionDesc;
    }

    *//**
     * @return the noJuvFamily
     *//*
    public String getNoJuvFamily()
    {
	return noJuvFamily;
    }

    *//**
     * @param noJuvFamily
     *            the noJuvFamily to set
     *//*
    public void setNoJuvFamily(String noJuvFamily)
    {
	this.noJuvFamily = noJuvFamily;
    }

    *//**
     * @return the optionFlag
     *//*
    public String getOptionFlag()
    {
	return optionFlag;
    }

    *//**
     * @param optionFlag
     *            the optionFlag to set
     *//*
    public void setOptionFlag(String optionFlag)
    {
	this.optionFlag = optionFlag;
    }

    public String[] getSelectedDocketsForUpdate()
    {
	return selectedDocketsForUpdate;
    }

    public void setSelectedDocketsForUpdate(String[] selectedDocketsForUpdate)
    {
	this.selectedDocketsForUpdate = selectedDocketsForUpdate;
    }

}*/
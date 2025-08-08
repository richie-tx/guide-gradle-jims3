package ui.juvenilecase.districtCourtHearings;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;

import ui.common.Address;
import ui.common.Name;
import ui.juvenilecase.form.JuvenileMemberForm;

/**
 * 
 * SubpoenaReportBean
 *
 */
public class StandardDocketReportBean
{

    private static final long serialVersionUID = 1L;

    private Collection<DocketEventResponseEvent> dockets;
    private List docketsList;
    private String courtId;
    private String courtIdWithSuffix;
    private String courtDate;
    private Date courtDateWithTime;
    private String courtTime;
   
	private String jpoOfRecord;
	private Name father;
	private Name mother;
	private Name other;
	private String relationship; //other relationship
	private String facilityName;
	private String dob;
	
	private String juvenileNumber;
	private String juvenileName;
	private String petitionNumber;
	private String petitionAmendment;
	private String petitionAllegation;
	
	private String offenseCategory;
	private String juryLiteral;
	private String hearingType;
	private String courtJPO;
	private String attorneyName;
	private String attorneyConnection;
	private String recordType;
	private String prevNotes; //notes
	private String comments;
	private String display = "";
	
	public String getCourtId() {
		return courtId;
	}
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	public String getCourtIdWithSuffix() {
		return courtIdWithSuffix;
	}
	public void setCourtIdWithSuffix(String courtIdWithSuffix) {
		this.courtIdWithSuffix = courtIdWithSuffix;
	}
	public String getCourtDate() {
		return courtDate;
	}
	public void setCourtDate(String courtDate) {
		this.courtDate = courtDate;
	}
	public Date getCourtDateWithTime() {
		return courtDateWithTime;
	}
	public void setCourtDateWithTime(Date courtDateWithTime) {
		this.courtDateWithTime = courtDateWithTime;
	}
	public String getCourtTime() {
		return courtTime;
	}
	public void setCourtTime(String courtTime) {
		this.courtTime = courtTime;
	}
	public String getJpoOfRecord() {
		return jpoOfRecord;
	}
	public void setJpoOfRecord(String jpoOfRecord) {
		this.jpoOfRecord = jpoOfRecord;
	}
	public Name getFather() {
		return father;
	}
	public void setFather(Name father) {
		this.father = father;
	}
	public Name getMother() {
		return mother;
	}
	public void setMother(Name mother) {
		this.mother = mother;
	}
	public Name getOther() {
		return other;
	}
	public void setOther(Name other) {
		this.other = other;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getJuvenileNumber() {
		return juvenileNumber;
	}
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}
	public String getJuvenileName() {
		return juvenileName;
	}
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}
	public String getPetitionNumber() {
		return petitionNumber;
	}
	public void setPetitionNumber(String petitionNumber) {
		this.petitionNumber = petitionNumber;
	}
	public String getPetitionAmendment() {
		return petitionAmendment;
	}
	public void setPetitionAmendment(String petitionAmendment) {
		this.petitionAmendment = petitionAmendment;
	}
	public String getPetitionAllegation() {
		return petitionAllegation;
	}
	public void setPetitionAllegation(String petitionAllegation) {
		this.petitionAllegation = petitionAllegation;
	}
	public String getOffenseCategory() {
		return offenseCategory;
	}
	public void setOffenseCategory(String offenseCategory) {
		this.offenseCategory = offenseCategory;
	}
	public String getJuryLiteral() {
		return juryLiteral;
	}
	public void setJuryLiteral(String juryLiteral) {
		this.juryLiteral = juryLiteral;
	}
	public String getHearingType() {
		return hearingType;
	}
	public void setHearingType(String hearingType) {
		this.hearingType = hearingType;
	}
	public String getCourtJPO() {
		return courtJPO;
	}
	public void setCourtJPO(String courtJPO) {
		this.courtJPO = courtJPO;
	}
	public String getAttorneyName() {
		return attorneyName;
	}
	public void setAttorneyName(String attorneyName) {
		this.attorneyName = attorneyName;
	}
	public String getAttorneyConnection() {
		return attorneyConnection;
	}
	public void setAttorneyConnection(String attorneyConnection) {
		this.attorneyConnection = attorneyConnection;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getPrevNotes() {
		return prevNotes;
	}
	public void setPrevNotes(String prevNotes) {
		this.prevNotes = prevNotes;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the dockets
	 */
	public Collection<DocketEventResponseEvent> getDockets() {
		return dockets;
	}
	/**
	 * @param dockets the dockets to set
	 */
	public void setDockets(Collection<DocketEventResponseEvent> dockets) {
		this.dockets = dockets;
	}
	/**
	 * @return the docketsList
	 */
	public List getDocketsList() {
		return docketsList;
	}
	/**
	 * @param docketsList the docketsList to set
	 */
	public void setDocketsList(List docketsList) {
		this.docketsList = docketsList;
	}
	/**
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}
	/**
	 * @param display the display to set
	 */
	public void setDisplay(String display) {
		this.display = display;
	} 
    
}
  
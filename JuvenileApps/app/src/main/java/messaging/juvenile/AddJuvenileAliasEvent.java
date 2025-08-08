package messaging.juvenile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class AddJuvenileAliasEvent extends RequestEvent {
	
	private String firstName;
	private String lastName;
	private String middleName;
	private String raceId;
	private Date dateOfBirth;
	private String juvenileNum;
	private String juvenileType;
	private String sexId;
	private String OID;
	private Date aliasEntryDate;
	private String aliasNotes;
	private String race;
	private String sex;
	private boolean deleteFlag;
	
	
	
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public String getOID() {
		return OID;
	}
	
	public void setOID(String oid) {
		OID = oid;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getRaceId() {
		return raceId;
	}
	
	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getJuvenileNum() {
		return juvenileNum;
	}
	
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	
	public String getJuvenileType() {
		return juvenileType;
	}
	
	public void setJuvenileType(String juvenileType) {
		this.juvenileType = juvenileType;
	}
	
	public String getSexId() {
		return sexId;
	}
	
	public void setSexId(String sexId) {
		this.sexId = sexId;
	}
	
	public Date getAliasEntryDate() {
		return aliasEntryDate;
	}
	
	public void setAliasEntryDate(Date aliasEntryDate) {
		this.aliasEntryDate = aliasEntryDate;
	}
	
	public String getAliasNotes() {
		return aliasNotes;
	}
	
	public void setAliasNotes(String aliasNotes) {
		this.aliasNotes = aliasNotes;
	}
	
	public String getRace() {
		return race;
	}
	
	public void setRace(String race) {
		this.race = race;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
}

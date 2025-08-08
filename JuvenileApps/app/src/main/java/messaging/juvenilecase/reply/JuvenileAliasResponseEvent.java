package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class JuvenileAliasResponseEvent extends ResponseEvent {
	
    private Date entryDate;
	private String aliasName;
    // Use ENUM if needed
    private String juvenileType;
    private String notes;
    private String juvenileNum;
    
    private String id;
    
    
    
    public String getJuvenileNum() {
		return juvenileNum;
	}

	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	public String getId() {
		return id;
	}
    
	public void setId(String id) {
		this.id = id;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public String getJuvenileType() {
		return juvenileType;
	}
	public void setJuvenileType(String juvenileType) {
		this.juvenileType = juvenileType;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

}

/*
 * Created on Dec 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.legacyupdates;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetLegacyUpdatesEvent extends RequestEvent {
    private String recordType;
    private String sourceOID;
    private String spn;
    
	/**
	 * @return the spn
	 */
	public String getSpn() {
		return spn;
	}
	/**
	 * @param spn the spn to set
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}
	/**
	 * @return the recordType
	 */
	public String getRecordType() {
		return recordType;
	}
	/**
	 * @return the sourceOID
	 */
	public String getSourceOID() {
		return sourceOID;
	}
	/**
	 * @param recordType the recordType to set
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	/**
	 * @param sourceOID the sourceOID to set
	 */
	public void setSourceOID(String sourceOID) {
		this.sourceOID = sourceOID;
	}    
}

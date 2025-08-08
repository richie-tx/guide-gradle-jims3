package messaging.legacyupdates;

import mojo.km.messaging.PersistentEvent;
import mojo.km.messaging.RequestEvent;

public class LegacyUpdatesRequestEvent extends PersistentEvent {
	private String recType;
	private String spn;
	private String jims2SourceId;
	private String logonId;
	private String criminalCaseId;
	
    /**
	 * @return the jims2SourceId
	 */
	public String getJims2SourceId() {
		return jims2SourceId;
	}

	/**
	 * @return the logonId
	 */
	public String getLogonId() {
		return logonId;
	}

	/**
	 * @return the recType
	 */
	public String getRecType() {
		return recType;
	}

	/**
	 * @return the spn
	 */
	public String getSpn() {
		return spn;
	}

	/**
	 * @param jims2SourceId the jims2SourceId to set
	 */
	public void setJims2SourceId(String jims2SourceId) {
		this.jims2SourceId = jims2SourceId;
	}

	/**
	 * @param logonId the logonId to set
	 */
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}

	/**
	 * @param recType the recType to set
	 */
	public void setRecType(String recType) {
		this.recType = recType;
	}

	/**
	 * @param spn the spn to set
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}

	/**
	 * @return the criminalCaseId
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}

	/**
	 * @param criminalCaseId the criminalCaseId to set
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
}

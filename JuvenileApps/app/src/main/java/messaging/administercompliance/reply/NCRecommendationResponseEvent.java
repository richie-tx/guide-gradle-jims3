/*
 * Created on Apr 25, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administercompliance.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NCRecommendationResponseEvent extends ResponseEvent{
	
	private String courtActionCodeId;
	private String courtActionCodeDesc;
	private String ncResponseId;
	private String recommendationId;
	private String type;
	private boolean manual;
	
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the courtActionCodeDesc.
	 */
	public String getCourtActionCodeDesc() {
		return courtActionCodeDesc;
	}
	/**
	 * @param courtActionCodeDesc The courtActionCodeDesc to set.
	 */
	public void setCourtActionCodeDesc(String courtActionCodeDesc) {
		this.courtActionCodeDesc = courtActionCodeDesc;
	}
	/**
	 * @return Returns the courtActionCodeId.
	 */
	public String getCourtActionCodeId() {
		return courtActionCodeId;
	}
	/**
	 * @param courtActionCodeId The courtActionCodeId to set.
	 */
	public void setCourtActionCodeId(String courtActionCodeId) {
		this.courtActionCodeId = courtActionCodeId;
	}
	/**
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	/**
	 * @return Returns the recommendationId.
	 */
	public String getRecommendationId() {
		return recommendationId;
	}
	/**
	 * @param recommendationId The recommendationId to set.
	 */
	public void setRecommendationId(String recommendationId) {
		this.recommendationId = recommendationId;
	}
	public boolean isManual() {
		return manual;
	}
	public void setManual(boolean manual) {
		this.manual = manual;
	}
}

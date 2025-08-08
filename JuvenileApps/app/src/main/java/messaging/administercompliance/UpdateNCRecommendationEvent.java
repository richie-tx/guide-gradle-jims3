//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\UpdateNCResponseEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateNCRecommendationEvent extends RequestEvent 
{
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
	
	/**
	 * @return the manual
	 */
	public boolean isManual() {
		return manual;
	}
	/**
	 * @param manual the manual to set
	 */
	public void setManual(boolean manual) {
		this.manual = manual;
	}
	
    private String courtActionCodeId;
    private String courtActionCodeDesc;
    private String recommendationId;
    private String type;
    private boolean manual;
	/**
	 * @return the type
	 */
	public String getType() {
		if(type == null){
			return "";
		}else{
			return type;
		}
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}    
}

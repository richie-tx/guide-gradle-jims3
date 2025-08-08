/*
 * Created on Jan 26, 2007
 *
 */
package messaging.suggestedorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *  
 */
public class GetSuggestedOrdersForCourtCategoryEvent extends RequestEvent {
	private String agencyId;
	private String courtCategory;
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @return Returns the courtCategory.
	 */
	public String getCourtCategory() {
		return courtCategory;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	/**
	 * @param courtCategory The courtCategory to set.
	 */
	public void setCourtCategory(String courtCategory) {
		this.courtCategory = courtCategory;
	}
}

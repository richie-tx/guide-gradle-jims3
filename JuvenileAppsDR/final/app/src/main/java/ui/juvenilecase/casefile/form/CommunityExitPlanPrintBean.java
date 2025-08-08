/*
 * Created on Aug 29, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.casefile.form;
import java.util.Collection;

import ui.common.Name;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommunityExitPlanPrintBean {

	private Name juvenile;
	private String probationTermDate;
	private String terminationDate;
	private String currentDate;
	private Collection goalEndRecommendations;
	private Collection serviceProviderDetails;
	private Collection closingComments;
	private Collection closingEvalution;

	/**
	 * @return Returns the currentDate.
	 */
	public String getCurrentDate() {
		return currentDate;
	}
	/**
	 * @param currentDate The currentDate to set.
	 */
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	/**
	 * @return Returns the juvenile.
	 */
	public Name getJuvenile() {
		return juvenile;
	}
	/**
	 * @param juvenile The juvenile to set.
	 */
	public void setJuvenile(Name juvenile) {
		this.juvenile = juvenile;
	}
	/**
	 * @return Returns the probationTermDate.
	 */
	public String getProbationTermDate() {
		return probationTermDate;
	}
	/**
	 * @param probationTermDate The probationTermDate to set.
	 */
	public void setProbationTermDate(String probationTermDate) {
		this.probationTermDate = probationTermDate;
	}
	/**
	 * @return Returns the terminationDate.
	 */
	public String getTerminationDate() {
		return terminationDate;
	}
	/**
	 * @param terminationDate The terminationDate to set.
	 */
	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}
	/**
	 * @return Returns the goalEndRecommendations.
	 */
	public Collection getGoalEndRecommendations() {
		return goalEndRecommendations;
	}
	/**
	 * @param goalEndRecommendations The goalEndRecommendations to set.
	 */
	public void setGoalEndRecommendations(Collection goalEndRecommendations) {
		this.goalEndRecommendations = goalEndRecommendations;
	}
	/**
	 * @return Returns the serviceProviderDetails.
	 */
	public Collection getServiceProviderDetails() {
		return serviceProviderDetails;
	}
	/**
	 * @param serviceProviderDetails The serviceProviderDetails to set.
	 */
	public void setServiceProviderDetails(Collection serviceProviderDetails) {
		this.serviceProviderDetails = serviceProviderDetails;
	}
	public Collection getClosingComments() {
		return closingComments;
	}
	public void setClosingComments(Collection closingComments) {
		this.closingComments = closingComments;
	}
	public Collection getClosingEvalution() {
		return closingEvalution;
	}
	public void setClosingEvalution(Collection closingEvalution) {
		this.closingEvalution = closingEvalution;
	}
}

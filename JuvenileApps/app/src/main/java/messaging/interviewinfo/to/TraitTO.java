package messaging.interviewinfo.to;


/**
 *
 */
public class TraitTO extends EntryDateTO
{
	private String traitTypeName = "";
	private String traitTypeDescription = "";
	private String traitComments = "";
	private String statusId;
	private String status;
	
	/**
	 * @return Returns the traitComments.
	 */
	public String getTraitComments() {
		return traitComments;
	}
	/**
	 * @param traitComments The traitComments to set.
	 */
	public void setTraitComments(String traitComments) {
		this.traitComments = traitComments;
	}
	/**
	 * @return Returns the traitDescription.
	 */
	public String getTraitTypeDescription() {
		return traitTypeDescription;
	}
	/**
	 * @param traitDescription The traitDescription to set.
	 */
	public void setTraitTypeDescription(String traitDescription) {
		this.traitTypeDescription = traitDescription;
	}
	/**
	 * @return Returns the traitType.
	 */
	public String getTraitTypeName() {
		return traitTypeName;
	}
	/**
	 * @param traitType The traitType to set.
	 */
	public void setTraitTypeName(String traitType) {
		this.traitTypeName = traitType;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
}

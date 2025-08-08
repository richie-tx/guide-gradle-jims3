package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetActiveSuperviseeCasesEvent extends RequestEvent
{
    private String defendantId;
    private boolean isFilteredByCaseStatus;
    private boolean queryName;

    /**
     * @roseuid 46435F2003D4
     */
    public GetActiveSuperviseeCasesEvent()
    {

    }

    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId()
    {
        return defendantId;
    }

    /**
     * @param defendantId
     *        The defendantId to set.
     */
    public void setDefendantId(String defendantId)
    {
        this.defendantId = defendantId;
    }

	/**
	 * @return the isFilteredByCaseStatus
	 */
	public boolean isFilteredByCaseStatus() {
		return isFilteredByCaseStatus;
	}

	/**
	 * @param isFilteredByCaseStatus the isFilteredByCaseStatus to set
	 */
	public void setFilteredByCaseStatus(boolean isFilteredByCaseStatus) {
		this.isFilteredByCaseStatus = isFilteredByCaseStatus;
	}

	/**
	 * @return the queryName
	 */
	public boolean isQueryName() {
		return queryName;
	}

	/**
	 * @param queryName the queryName to set
	 */
	public void setQueryName(boolean queryName) {
		this.queryName = queryName;
	}
}

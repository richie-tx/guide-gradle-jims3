/*
 * Created on Jan 17, 2008
 *
 */
package messaging.viewassignment;

import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_rbhat
 *  
 */
public class CaseAssignmentReportResponseEvent extends ResponseEvent {
	/**
	 * Flag indicating if the search criteria resulted in rows > 2000
	 */
	private boolean resultSetExceedMaxLimit;

	private List results;

	/**
	 * @return Returns the results.
	 */
	public List getResults() {
		return results;
	}

	/**
	 * @return Returns the resultSetExceedMaxLimit.
	 */
	public boolean isResultSetExceedMaxLimit() {
		return resultSetExceedMaxLimit;
	}

	/**
	 * @param results
	 *            The results to set.
	 */
	public void setResults(List results) {
		this.results = results;
	}

	/**
	 * @param resultSetExceedMaxLimit
	 *            The resultSetExceedMaxLimit to set.
	 */
	public void setResultSetExceedMaxLimit(boolean resultSetExceedMaxLimit) {
		this.resultSetExceedMaxLimit = resultSetExceedMaxLimit;
	}
}

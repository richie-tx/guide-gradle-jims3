/*
 * Created on May 7, 2007
 */
package messaging.administercasenotes;

/**
 * @author mchowdhury
 */
public class GetSpnSplitCasenotesByCasenoteIdEvent extends GetCasenotesEvent {
	private int casenoteId;

	public int getCasenoteId() {
		return casenoteId;
	}

	public void setCasenoteId(int casenoteId) {
		this.casenoteId = casenoteId;
	}
}

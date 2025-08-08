package messaging.administersupervisee;

import mojo.km.messaging.RequestEvent;

public class GetTransfersEvent extends RequestEvent {
	   
	private String superviseeId;
	private String searchType;

	   /**
	    * @roseuid 484E803D0111
	    */
	   public GetTransfersEvent() 
	   {
	   }

	public String getSuperviseeId() {
		return superviseeId;
	}

	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

}

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetProgramByProgramCodeEvent extends RequestEvent  {
	private String programCode;
	
	public GetProgramByProgramCodeEvent(){
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
}

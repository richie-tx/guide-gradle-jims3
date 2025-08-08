//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\spnsplit\\ProcessSpnSplitEvent.java

package messaging.spnsplit;

import mojo.km.messaging.RequestEvent;

public class GetSpnSplitExceptionEvent extends RequestEvent 
{
    private String validSpn;
    private String erroneousSpn;
       
	public String getValidSpn() {
		return validSpn;
	}
	public void setValidSpn(String validSpn) {
		this.validSpn = validSpn;
	}
	public String getErroneousSpn() {
		return erroneousSpn;
	}
	public void setErroneousSpn(String erroneousSpn) {
		this.erroneousSpn = erroneousSpn;
	}       
}

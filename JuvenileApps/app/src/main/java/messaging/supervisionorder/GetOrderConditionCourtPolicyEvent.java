//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionorder\\CreateSpecialConditionEvent.java

package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetOrderConditionCourtPolicyEvent extends RequestEvent 
{
   private String defendantId;

	public String getDefendantId() {
		return defendantId;
	}
	
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
}

/*
 * Created on Nov 1, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProgressPrefillResponseEvent extends ResponseEvent
{
	/* private String riskPoint;
	private String supervisionLevelName;
	private String supervisionLevelId;*/
	private int supervisionMonths;

	public void setSupervisionMonths(int supervisionMonths) {
		this.supervisionMonths = supervisionMonths;
	}

	public int getSupervisionMonths() {
		return supervisionMonths;
	}
}

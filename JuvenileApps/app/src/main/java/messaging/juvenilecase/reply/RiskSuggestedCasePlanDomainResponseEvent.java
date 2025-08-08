/*
 * Created on Oct 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RiskSuggestedCasePlanDomainResponseEvent extends ResponseEvent
{
	private List suggestedCasePlanDomainNames;

	public RiskSuggestedCasePlanDomainResponseEvent () 
	{
		
	}

	public void setSuggestedCasePlanDomainNames(
			List suggestedCasePlanDomainNames) {
		this.suggestedCasePlanDomainNames = suggestedCasePlanDomainNames;
	}

	public List getSuggestedCasePlanDomainNames() {
		return suggestedCasePlanDomainNames;
	}

}

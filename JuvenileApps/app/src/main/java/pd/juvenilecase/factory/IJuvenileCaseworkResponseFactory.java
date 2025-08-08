/*
 * Created on Dec 8, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.factory;

import messaging.juvenilecase.reply.RuleDetailResponseEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import mojo.pattern.IResponseFactory;
import pd.juvenilecase.rules.RuleGroupConditionView;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface IJuvenileCaseworkResponseFactory extends IResponseFactory
{
	public RuleResponseEvent getRuleResponseEvent(RuleGroupConditionView rule);
	public RuleDetailResponseEvent getRuleDetailResponseEvent(RuleGroupConditionView rule);
}

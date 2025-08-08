/*
 * Created on Dec 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.supervision.Factory;


import java.util.Collection;

import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import mojo.pattern.IResponseFactory;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.CourtSupervisionOption;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ISupervisionOptionsFactory extends IResponseFactory
{
	ConditionDetailResponseEvent getConditionResponseEvent(Condition condition);	
	Collection getVariableElementResponses(CourtSupervisionOption option);
}

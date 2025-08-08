package pd.codetable.transactions;

import java.util.Iterator;

import org.apache.commons.beanutils.BeanUtils;

import messaging.codetable.reply.JuvenileRuleCompletionStatusCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.codetable.criminal.JuvenileRuleCompletionStatus;

public class GetJuvenileRuleCompletionStatusCodesCommand implements ICommand{

	public void execute(IEvent event) throws Exception {
        Iterator codes = JuvenileRuleCompletionStatus.findAll();
        while(codes.hasNext()){
        	JuvenileRuleCompletionStatus code = (JuvenileRuleCompletionStatus)codes.next();
        	JuvenileRuleCompletionStatusCodeResponseEvent resp = new JuvenileRuleCompletionStatusCodeResponseEvent();
        	// filter inactive codes. Bug #50154
        	if(code!=null && code.getStatus()!=null && (code.getStatus().equalsIgnoreCase("I")||code.getStatus().equalsIgnoreCase("INACTIVE"))){
        		continue;
        	} // filter inactive codes. Bug #50154
            BeanUtils.copyProperties(resp, code);
            MessageUtil.postReply(resp);
        }
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
	}

}

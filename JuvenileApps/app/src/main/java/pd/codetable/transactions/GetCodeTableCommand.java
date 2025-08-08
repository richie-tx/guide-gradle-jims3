/*
 * Created on Jun 17, 2004
 *
 */
package pd.codetable.transactions;

import java.util.Collection;
import java.util.Iterator;
import naming.PDCodeTableConstants;
import pd.codetable.Code;
import messaging.codetable.GetCodeTableEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.codetable.reply.CodeTableResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author Jim Fisher
 * Retrieves CodeTable object
 *
 */
public class GetCodeTableCommand implements ICommand
{

	/**
	 * @param event
	 * @precondition ((GetCodeTableEvent)event).getCodeTableId() != null)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetCodeTableEvent codeTableEvent = (GetCodeTableEvent) event;

		String codeTableName = codeTableEvent.getCodeTableName();

		if (codeTableName != null)
		{
			Collection codes = Code.findAll(codeTableName);
			this.sendCodeTable(codeTableName, codes);
		}
	}

	private void sendCodeTable(String codeTableName, Collection codes)
	{
		if (codes != null)
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

			Iterator i = codes.iterator();

			// Send the code table's codes
			while (i.hasNext())
			{
				CodeResponseEvent codeReply = new CodeResponseEvent();

				Code code = (Code) i.next();				

				codeReply.setCodeId(code.getCodeId());
				codeReply.setActiveDate(code.getActiveDate());
				codeReply.setCode(code.getCode());
				codeReply.setCodeTableName(codeTableName);
				codeReply.setDescription(code.getDescription());
				codeReply.setInactiveDate(code.getInactiveDate());
				codeReply.setInactiveEffectiveDate(code.getInactiveEffectiveDate());
				codeReply.setStatus(code.getStatus());

				codeReply.setTopic(PDCodeTableConstants.getCodeTableTopic(codeTableName));

				dispatch.postEvent(codeReply);
			}

			CodeTableResponseEvent codeTableResponse = new CodeTableResponseEvent();

			codeTableResponse.setTopic(PDCodeTableConstants.CODE_TABLE);
			codeTableResponse.setCodeTableName(codeTableName);

			dispatch.postEvent(codeTableResponse);

		}
	}

	/** 
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
	}

	/** 
	 * @see mojo.km.context.ICommand#update(Object)
	 */
	public void update(Object updateObject)
	{
	}

}

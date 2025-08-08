//Source file: C:\\views\\dev\\app\\src\\pd\\codetable\\transactions\\GetCodeTableRecordsCommand.java

package pd.codetable.transactions;

import java.util.Collection;
import java.util.Iterator;

import naming.PDCodeTableConstants;

import pd.codetable.Code;
import pd.codetable.CodeTable;
import pd.codetable.PDCodeHelper;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.codetable.reply.CodeTableResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 * Retrieves and posts Code objects for a given code table
 */
public class GetCodeTableRecordsCommand implements ICommand
{

	/**
	@roseuid 40CF4703016B
	 */
	public GetCodeTableRecordsCommand()
	{

	}

	/**
	 * Retrieve Codes for a given code table.
	 * @param event
	 * @precondition ((GetCodeTableRecordsEvent)event).getCodeTableId() != null)
	 * @roseuid 40B4CECA022E
	 */
	public void execute(IEvent event)
	{
		Iterator codeTables = CodeTable.findAll(event);
		
		this.sendCodesTable(codeTables);							
	}

	/**
	 * Posts CodeTable response events.
	 * @param codeTables
	 */
	private void sendCodesTable(Iterator codeTables)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while (codeTables.hasNext())
		{
			CodeTableResponseEvent codeTableResponse =
				new CodeTableResponseEvent();

			// Set the Topic for the Posted event
			codeTableResponse.setTopic(PDCodeTableConstants.CODE_TABLE);

			CodeTable codeTable = (CodeTable) codeTables.next();

			codeTableResponse.setCodeTableName(codeTable.getCodeTableName());

			dispatch.postEvent(codeTableResponse);
			
			Collection codes = PDCodeHelper.getInstance().getCodes(codeTable.getCodeTableName());
			
			this.sendCodes(codeTable.getCodeTableName(), codes);
		}

	}

	/**
	 * Posts Code response event objects.
	 * @param codeTableName
	 * @param codes
	 */
	private void sendCodes(String codeTableName, Collection codes)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator it = codes.iterator();
		while (it.hasNext())
		{
			CodeResponseEvent codeResponse = new CodeResponseEvent();

			// Set the Topic for the Posted event
			codeResponse.setTopic(
				PDCodeTableConstants.getCodeTableTopic(codeTableName));

			Code code = (Code) it.next();

			codeResponse.setCodeId(code.getCodeId());
			codeResponse.setActiveDate(code.getActiveDate());
			codeResponse.setCode(code.getCode());
			codeResponse.setCodeTableName(code.getCodeTableName());
			codeResponse.setDescription(code.getDescription());
			codeResponse.setInactiveDate(code.getInactiveDate());
			codeResponse.setInactiveEffectiveDate(
				code.getInactiveEffectiveDate());
			codeResponse.setStatus(code.getStatus());

			dispatch.postEvent(codeResponse);
		}

	}

	/**
	@param event
	@roseuid 40B4CECA0230
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	@param event
	@roseuid 40B4CECA0237
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

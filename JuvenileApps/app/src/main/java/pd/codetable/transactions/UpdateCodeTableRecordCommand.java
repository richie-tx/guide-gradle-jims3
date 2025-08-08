package pd.codetable.transactions;

import naming.PDCodeTableConstants;
import messaging.codetable.UpdateCodeTableRecordEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.codetable.Code;
import pd.codetable.CodeTable;

/**
 * @author dgibler
 * Updates codes.
 */
public class UpdateCodeTableRecordCommand implements ICommand
{

	/**
	 * @roseuid 40E574610159
	 * @precondition ((UpdateCodeTableRecordEvent)event).getCodeId() != null)
	 */
	public UpdateCodeTableRecordCommand()
	{

	}

	/**
	@param event
	@roseuid 40E57279000F
	 */
	public void execute(IEvent event)
	{
		UpdateCodeTableRecordEvent updateEvent = (UpdateCodeTableRecordEvent) event;

		CodeTable codeTable = CodeTable.find(updateEvent.getCodeTableName());

		Code code = null;
		// This checks to see if the transaction is an UPDATE or a CREATE
		if (updateEvent.getCodeId() == null)
		{
			// CREATE Transaction
			code = new Code();
			String codeId = updateEvent.getCodeTableName() 
					+ PDCodeTableConstants.CODE_ID_SEPARATOR
					+ updateEvent.getCode();
			code.setCodeId(codeId);
			code.setCode(updateEvent.getCode());
			codeTable.insertCode(code);
		}
		else
		{
			// UPDATE Transaction
			code = Code.find(updateEvent.getCodeId());
		}

		code.setDescription(updateEvent.getDescription());
		code.setActiveDate(updateEvent.getActiveDate());
		code.setInactiveDate(updateEvent.getInactiveEffectiveDate());
		code.setInactiveEffectiveDate(updateEvent.getInactiveEffectiveDate());
		code.setStatus(updateEvent.getStatus());
		code.setCodeTableName(updateEvent.getCodeTableName());
	}

	/**
	@param event
	@roseuid 40E572790017
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	@param event
	@roseuid 40E572790019
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	@param updateObject
	@roseuid 40E574610163
	 */
	public void update(Object updateObject)
	{

	}
}

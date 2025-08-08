//Source file: C:\\views\\dev\\app\\src\\pd\\codetable\\transactions\\GetCodeTableSearchInfoCommand.java

package pd.codetable.transactions;

import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 */
public class GetCodeTableSearchInfoCommand implements ICommand
{
	/**
	@roseuid 40CF47030369
	 */
	public GetCodeTableSearchInfoCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 40B4CECB0012
	 */
	public void execute(IEvent event)
	{
		// TODO Code Table Types and Subtypes depend on Appshell
		
		/*GetCodeTableEvent getCodeTableEvent = new GetCodeTableEvent();

		getCodeTableEvent.setCodeTableName(
			PDCodeTableConstants.CODE_TABLE_TYPE);

		Iterator codeTables = CodeTable.findAll(getCodeTableEvent);

		if (codeTables.hasNext())
		{
			CodeTable codeTable = (CodeTable) codeTables.next();
			Iterator codes = codeTable.getCodes();
			this.sendCodeTable(codeTable);
		}*/
	}

	/**
	@param event
	@roseuid 40B4CECB0014
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	@param event
	@roseuid 40B4CECB0016
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

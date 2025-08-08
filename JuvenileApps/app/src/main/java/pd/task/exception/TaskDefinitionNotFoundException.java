/*
 * Created on Mar 16, 2006
 *
 */
package pd.task.exception;

/**
 * @author Jim Fisher
 *
 */
public class TaskDefinitionNotFoundException extends RuntimeException
{
	public TaskDefinitionNotFoundException(String msg)
	{		
		super(msg);
	}
}

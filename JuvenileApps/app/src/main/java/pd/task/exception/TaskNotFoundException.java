/*
 * Created on Mar 16, 2006
 *
 */
package pd.task.exception;

/**
 * @author Jim Fisher
 *
 */
public class TaskNotFoundException extends RuntimeException
{
	public TaskNotFoundException(String msg)
	{		
		super(msg);
	}
}

/*
 * Created on Mar 16, 2006
 *
 */
package ui.task;

import messaging.task.domintf.ITask;

/**
 * @author Jim Fisher
 *
 */
public interface ITaskRestorable
{
	void restore(ITask task);
}

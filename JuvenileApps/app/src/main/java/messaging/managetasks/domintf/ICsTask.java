/*
 * Created on Mar 12, 2007
 */
package messaging.managetasks.domintf;

import messaging.task.domintf.ITask;

/**
 * @author hrodriguez
 */
	public interface ICsTask extends ITask
	{
		String getName();
		String getCourt();
		String getSpn();
		void setName(String string);
		void setCourt(String string);
		void setSpn(String string);

}

/*
 * Created on Feb 2, 2006
 *
 */
package pd.pattern;

import java.util.Iterator;
import java.util.List;

import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;

/**
 * @author Jim Fisher
 * 
 * This abstract class, an implementation of the "Template Method" pattern, 
 * provides a contract for handling query results, in an ICommand class, that 
 * vary in count by zero, single, or multiple records.
 *  
 * For reference, the "Template Method" pattern defines the skeleton of an 
 * algorithm in terms of abstract operations which subclasses override to 
 * provide concrete behavior.
 */
public abstract class AbstractResultsTemplateCommand implements ICommand
{
	abstract public void handleZeroResult(IEvent event);

	abstract public void handleSingleResult(IEvent event, Object obj);

	abstract public void handleMultipleResults(IEvent event, List i);

	protected void processResults(IEvent event, Iterator i)
	{
		List list = CollectionUtil.iteratorToList(i);
		if (list.size() == 0)
		{
			this.handleZeroResult(event);
		}
		else if (list.size() == 1)
		{
			this.handleSingleResult(event, list.get(0));
		}
		else
		{
			this.handleMultipleResults(event, list);
		}
	}

	protected void processResults(IEvent event, Object obj)
	{
		if (obj == null)
		{
			this.handleZeroResult(event);
		}
		else
		{
			this.handleSingleResult(event, obj);
		}
	}
}

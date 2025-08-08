/*
 * Created on Aug 04, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.task.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.task.reply.TaskNextActionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.task.TaskNextAction;

/**
 * @author mchowdhury
 *
 */
public class GetNextTaskActionCommand implements ICommand
{
	/**
	 * Required fields: topic, creatorId
	 * Optional fields: ownerId, taskState, dueDate
	 */
	public void execute(IEvent anEvent) throws Exception
	{
		Iterator iter = TaskNextAction.findAll();
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			TaskNextAction t = (TaskNextAction) iter.next();
			if(!map.containsKey(t.getParentAction())){
				TaskNextActionResponseEvent resp = new TaskNextActionResponseEvent();
				resp.setParentAction(t.getParentAction());
				CodeResponseEvent response = new CodeResponseEvent();
				response.setCode(t.getTopic());
				response.setDescription(t.getAction());
				Collection col = new ArrayList();
				col.add(response);
				resp.setNextActions(col);
				map.put(t.getParentAction(), resp);
			}else{
				TaskNextActionResponseEvent resp = (TaskNextActionResponseEvent) map.get(t.getParentAction());
				Collection col = resp.getNextActions();
				CodeResponseEvent response = new CodeResponseEvent();
				response.setCode(t.getTopic());
				response.setDescription(t.getAction());
				col.add(response);
				Collections.sort((ArrayList) col);
				resp.setNextActions(col);
				map.put(t.getParentAction(), resp);
			}
		}
		
		Iterator iterator = map.values().iterator();
		while(iterator.hasNext()){
			TaskNextActionResponseEvent resp = (TaskNextActionResponseEvent) iterator.next();
			MessageUtil.postReply(resp);
		}
	}	


	public void onRegister(IEvent event)
	{
	}
	
	public void onUnregister(IEvent event)
	{
	}

	public void update(Object updateObject)
	{
	}
}

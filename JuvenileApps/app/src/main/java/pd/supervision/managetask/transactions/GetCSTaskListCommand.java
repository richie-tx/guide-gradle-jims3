// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managetask\\transactions\\GetCSTaskListCommand.java

package pd.supervision.managetask.transactions;

import java.util.ArrayList;
import java.util.List;
import messaging.managetask.GetCSTaskListEvent;
import messaging.managetask.reply.CSTaskResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import pd.supervision.managetask.CSTask;

public class GetCSTaskListCommand implements ICommand
{

    /**
     * @roseuid 463F300000E9
     */
    public GetCSTaskListCommand()
    {
    }

    /**
     * @param event
     * @roseuid 463F171F0176
     */
    public void execute(IEvent event)
    {
        GetCSTaskListEvent evt = (GetCSTaskListEvent) event;
        IHome home = new Home();
        CSTask task = null;
        CSTask csTask = new CSTask();
        CSTaskResponseEvent respEvt = null;
        List taskList = new ArrayList();
        
        if (evt.getDiscriminant().equals(GetCSTaskListEvent.TASKS_FOR_WORKGROUP_DISCRIMINANT))
		{
            taskList = CollectionUtil.iteratorToList( home.findAll( evt, CSTask.class ) );
        	
            for( int j=0; j < taskList.size(); j++){
    			
    			task = (CSTask) taskList.get( j );
    			respEvt = task.getResponseEvent( );
    			MessageUtil.postReply(respEvt);
    		}
        }
        else if (evt.getDiscriminant().equals(GetCSTaskListEvent.TASKS_FOR_POSITION_DISCRIMINANT))
        {
        	//  TODO : Add in code to retrieve tasks for position
           taskList = csTask.findAllByContext("staffPositionId", evt.getPositionId() ,"NOTCLOSEDCSTASKS");
             
        	for ( int z =0; z < taskList.size(); z++ ){
            	task = ( CSTask ) taskList.get( z );
                respEvt = task.getResponseEvent( );
                MessageUtil.postReply( respEvt );
        	}

        }
        else if (evt.getDiscriminant().equals(GetCSTaskListEvent.TASKS_FOR_DEFENDANT_DISCRIMINANT))
        {
       
            taskList = csTask.findAllByContext("defendantId", evt.getDefendantId() ,"NOTCLOSEDCSTASKS");
            
        	for ( int z =0; z < taskList.size(); z++ ){
            	task = ( CSTask ) taskList.get( z );
                respEvt = task.getResponseEvent( );
                MessageUtil.postReply( respEvt );
        	}
    }
        evt = null;
      	task = null;
      	csTask = null;
      	respEvt = null;
      	taskList.clear();
      	taskList = null;
    }

    
    /**
     * @param event
     * @roseuid 463F171F0178
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 463F171F017A
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 463F300000F8
     */
    public void update(Object updateObject)
    {

    }
}

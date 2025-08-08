// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managetask\\transactions\\GetCSTaskListAdvancedSearchCommand.java

package pd.supervision.managetask.transactions;

import java.util.List;

import messaging.administerserviceprovider.GetProviderProgramsEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.managetask.GetCSTaskListAdvancedSearchEvent;
import messaging.managetask.reply.CSTaskResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDConstants;
import pd.supervision.administerserviceprovider.SearchServiceProvider;
import pd.supervision.managetask.CSTask;

public class GetCSTaskListAdvancedSearchCommand implements ICommand {

	/**
	 * @roseuid 463F300000E9
	 */
	public GetCSTaskListAdvancedSearchCommand() {
	}

	/**
	 * @param event
	 * @roseuid 463F171F0176
	 */
	public void execute(IEvent event) {
		GetCSTaskListAdvancedSearchEvent evt = (GetCSTaskListAdvancedSearchEvent) event;
		CSTask task = null;
		CSTaskResponseEvent respEvt = null;
		
		// check that the query will not be over 2000 records
	   	IHome home = new Home();
		MetaDataResponseEvent metaData = (MetaDataResponseEvent) home.findMeta(evt, CSTask.class);	 
		int retrievedRecords = metaData.getCount();
		if( retrievedRecords > PDConstants.SEARCH_LIMIT ) {
			ErrorResponseEvent errorResp = new ErrorResponseEvent();
			errorResp.setMessage("error.max.limit.exceeded");
			MessageUtil.postReply(errorResp);
			return;
		}		
		List taskList = CollectionUtil.iteratorToList( new Home().findAll( evt, CSTask.class ));
		
		for( int j=0; j < taskList.size(); j++){
			
			task = (CSTask) taskList.get( j );
			respEvt = task.getResponseEvent( );
			MessageUtil.postReply(respEvt);
		}
		
		respEvt = null;
		task = null;
		evt = null;
		
		taskList.clear();
		taskList = null;

	}


	/**
	 * @param event
	 * @roseuid 463F171F0178
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 463F171F017A
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 463F300000F8
	 */
	public void update(Object updateObject) {

	}
}

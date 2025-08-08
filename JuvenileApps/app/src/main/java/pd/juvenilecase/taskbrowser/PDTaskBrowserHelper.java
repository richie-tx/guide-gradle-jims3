/*
 * Created on December 07, 2005
 * @author mchowdhury
 */
package pd.juvenilecase.taskbrowser;

import java.util.Iterator;

import pd.notification.Task;
import pd.notification.TaskParam;

import messaging.casefile.UpdateCasefileClosingEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.juvenilecase.reply.TaskResponseEvent;

import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import mojo.km.config.AppProperties;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import naming.PDJuvenileCaseConstants;

public final class PDTaskBrowserHelper
{
	/**
	 * 
	 */
	private PDTaskBrowserHelper()
	{
	}

	/**
	 * @param casefileClosing
	 */
	public static void sendTaskResponseEvent(Task aTask)
	{
		if(aTask != null){
			TaskResponseEvent response = new TaskResponseEvent();
			response.setCreationDate(aTask.getCreationDate());
			response.setTitle(aTask.getTitle());
			response.setTaskID(aTask.getOID().toString());
			response.setType(aTask.getType().getDescription());
			response.setURL(PDTaskBrowserHelper.getTaskURL(aTask));
			response.setJuvenileName(aTask.getJuvenileName());
			response.setTopic(PDJuvenileCaseConstants.ACTIVE_TASK_EVENT_TOPIC);
			sendResponseEvent(response);
		}
	}
	
	public static String getTaskURL(Task aTask) {
		StringBuffer URL = new StringBuffer();
		URL = URL.append(aTask.getType().getURL()).append("?");
		Iterator ite = aTask.getParamList().iterator();
		while(ite.hasNext()) {
			TaskParam param = (TaskParam)ite.next();
			URL = URL.append(param.getParamName()).append("=").append(param.getParamValue()).append("&");
		}
		String finalURL = URL.toString();
		if(!finalURL.equals("")) { // get the extra trailing "&" or "?" out
			finalURL = finalURL.substring(0, finalURL.length()-1);
		}
		return finalURL;
	}
	
	/**
	* @param event IEvent
	*/
	public static void sendResponseEvent(IEvent event)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(event);
	}
	
	
	public static void createTaskForActivateCasefile(String casefileID, String userID, String juvenileName) {
		Task acaTask = new Task();
		acaTask.setTaskStatusId("P");
		acaTask.setTaskUserId(userID);
		acaTask.setTypeId("ACA");
		acaTask.setTitle("Activate Casefile Assignment: Supervision Number: " + casefileID);
		acaTask.setJuvenileName(juvenileName);
		Home home = new mojo.km.persistence.Home();
		home.bind(acaTask);
		TaskParam tParam = new TaskParam();
		tParam.setTaskId(acaTask.getOID().toString());
		tParam.setParamName("submitAction");
		tParam.setParamValue("Link");
		home.bind(tParam);
		acaTask.insertParamList(tParam);
		TaskParam tParam2 = new TaskParam();
		tParam2.setTaskId(acaTask.getOID().toString());
		tParam2.setParamName("casefileID");
		tParam2.setParamValue(casefileID);
		home.bind(tParam2);
		acaTask.insertParamList(tParam2);
	}

	public static void createTaskForReviewBenefits(String casefileID, String userID, String assessmentID, String juvName) {
		Task acaTask = new Task();
		acaTask.setTaskStatusId("P");
		acaTask.setTaskUserId(userID);
		acaTask.setTypeId("RBA");
		acaTask.setJuvenileName(juvName);
		acaTask.setTitle("Review Benefits Assessment: Supervision Number: " + casefileID);
		Home home = new mojo.km.persistence.Home();
		home.bind(acaTask);
		TaskParam tParam = new TaskParam();
		tParam.setTaskId(acaTask.getOID().toString());
		tParam.setParamName("review");
		tParam.setParamValue("true");
		home.bind(tParam);
		acaTask.insertParamList(tParam);
		TaskParam tParam2 = new TaskParam();
		tParam2.setTaskId(acaTask.getOID().toString());
		tParam2.setParamName("currentAssessment.assessmentId");
		tParam2.setParamValue(assessmentID);
		home.bind(tParam2);
		acaTask.insertParamList(tParam2);
	}




}

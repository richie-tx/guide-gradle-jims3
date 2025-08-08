package pd.juvenilecase.transactions;    /*MIGRATED SQL-PART OF REFERRAL CONVERSION*/
// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
/*//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\ProcessCaseFileAssignmentsCommand.java

package pd.juvenilecase.transactions;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import messaging.juvenilecase.GetNewJuvenileCasefileAssignmentsEvent;
import messaging.juvenilecase.ProcessJuvenileCasefileAssignmentsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.scheduling.Job;
import mojo.km.scheduling.JobReportBean;
import mojo.km.scheduling.Log;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import naming.PDJuvenileCaseConstants;
import pd.juvenile.JuvenilePhoto;
import pd.juvenilecase.CasefileExtractionUtility;
import pd.juvenilecase.JJSService;
import pd.juvenilecase.ProcessCasefileExtraction;

*//**
 * @author dgibler
 *
 * Extracts new case file assignments from M204 and creates a CaseFile for each.
 *//*
public class ProcessJuvenileCasefileAssignmentsCommand extends CasefileExtractionUtility implements ICommand
{

	*//**
	 * @roseuid 4277C459031C
	 *//*
	public ProcessJuvenileCasefileAssignmentsCommand()
	{

	}

	*//**
	 * @param event
	 * @roseuid 425BF1D503C6
	 *//*
	public void execute(IEvent event)
	{
		ProcessJuvenileCasefileAssignmentsEvent requestEvent = (ProcessJuvenileCasefileAssignmentsEvent) event;
		Job job = null;
		JobReportBean bean = null;
		ProcessCasefileExtraction extr = null;
		Timestamp startTime = null;	
		Iterator newAssignmentsIter = null;
		
		try{
			startTime = new Timestamp(DateUtil.getCurrentDate().getTime());
			job = Job.createJob(0, 0, 0, Integer.parseInt(requestEvent.getProcessId()), startTime, null, PDJuvenileCaseConstants.CASEFILE_CREATOR, PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
			newAssignmentsIter = JJSService.findAll((IEvent) new GetNewJuvenileCasefileAssignmentsEvent());	
		
			if (newAssignmentsIter.hasNext()){
				//add sort - Defect #52119
				List jjsServices = CollectionUtil.iteratorToList(newAssignmentsIter);
		        Collections.sort(jjsServices, new Comparator()
		        {
		            public int compare(Object o1, Object o2)
		            {
		            	JJSService jjs1 = (JJSService) o1;
		            	JJSService jjs2 = (JJSService) o2;
		                return jjs2.getOID().compareTo(jjs1.getOID());
		            }
		        }
		        );    
				Iterator sortedAssignments = jjsServices.iterator();
				extr = new ProcessCasefileExtraction();
				bean = extr.associateAssignmentsToCaseFile(sortedAssignments, Integer.parseInt(job.getOID().toString()));
				job.setJob(bean.getSkippedCount(), bean.getProcessedCount(), bean.getErrorCount(), new Timestamp(DateUtil.getCurrentDate().getTime()));
			}else{
				this.writeError(job, 0, 0, 0, Integer.parseInt(requestEvent.getProcessId()), startTime, "No JJSService was found. Closing the operation.");
			}
		}catch(RuntimeException e){
			e.printStackTrace();
			try {
				this.writeError(job, 0, 0, 0, Integer.parseInt(requestEvent.getProcessId()), startTime, e.getMessage());
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (RuntimeException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			try {
				this.writeError(job, 0, 0, 0, Integer.parseInt(requestEvent.getProcessId()), startTime, e.getMessage());
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (RuntimeException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}finally{
			job = null;
			bean = null;
			extr = null;
			startTime = null;	
			newAssignmentsIter = null;	
			requestEvent = null;
		}
	}
	
	*//**
	 * @param jobId
	 * @param skippedCount
     * @param processedCount
     * @param errorCount
     * @param message
	 * @return
	 *//*
	protected void writeLog(String jobId, int skippedCount, int processedCount, int errorCount, int processId, String message) throws RuntimeException, Exception{
		Timestamp currentTime = new Timestamp(DateUtil.getCurrentDate().getTime());
		Job job = Job.find(jobId);
		if(job != null){
			job.setJob(skippedCount,processedCount,errorCount,currentTime);
			Log.createLog(Integer.parseInt(jobId), currentTime, message, "L", PDJuvenileCaseConstants.CASEFILE_CREATOR);
		}
		currentTime = null;
	}
	
	*//**
	 * @param job
	 * @param skippedCount
     * @param processedCount
     * @param errorCount
     * @param processId
     * @param startTime
     * @param message
	 * @return
	 *//*
	protected void writeError(Job job, int skippedCount, int processedCount, int errorCount, int processId, Timestamp startTime, String message) throws RuntimeException, Exception{
		Timestamp currentTime = new Timestamp(DateUtil.getCurrentDate().getTime());
		if(job != null){
			job.setJob(skippedCount,processedCount,errorCount,currentTime);
			Log.createLog(Integer.parseInt(job.getOID().toString()), startTime, message, "E", PDJuvenileCaseConstants.CASEFILE_CREATOR);
		}
		currentTime = null;
	}

	*//**
	 * @param event
	 * @roseuid 425BF1D503C8
	 *//*
	public void onRegister(IEvent event)
	{

	}

	*//**
	 * @param event
	 * @roseuid 425BF1D503CA
	 *//*
	public void onUnregister(IEvent event)
	{

	}

	*//**
	 * @param updateObject
	 * @roseuid 4277C459032C
	 *//*
	public void update(Object updateObject)
	{

	}
}
*/
package messaging.juvenile;

import mojo.km.messaging.RequestEvent;


public class GetJuvenileJobEvent extends RequestEvent 
{
   private String jobId;
   
   /**
    * @roseuid 42B196830128
    */
   public GetJuvenileJobEvent() 
   {
   }
   
	/**
	 * @return Returns the jobId.
	 */
	public String getJobId() {
		return jobId;
	}
	
	/**
	 * @param jobId The jobId to set.
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}

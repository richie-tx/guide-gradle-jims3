//Source file: C:\\views\\dev\\app\\src\\messaging\\report\\WarrantPrintTemplateRequestEvent.java

package messaging.supervisionorder;

import java.util.Vector;

import mojo.km.messaging.reporting.ReportRequestEvent;


public class GetPrintTemplatesEvent extends ReportRequestEvent 
{
		private String replyEvent;
		private String replyTopic;
		private Vector reportData = new Vector();
		private String agencyId;
	
   /**
    * @roseuid 42FBA487006D
    */
   public GetPrintTemplatesEvent() 
   {
    
   }
		/**
		 * @return
		 */
		public String getReplyEvent()
		{
			return replyEvent;
		}

		/**
		 * @return
		 */
		public String getReplyTopic()
		{
			return replyTopic;
		}

		/**
		 * @return
		 */
		public Vector getReportData()
		{
			return reportData;
		}

		/**
		 * @param string
		 */
		public void setReplyEvent(String replyEvent)
		{
			this.replyEvent = replyEvent;
		}

		/**
		 * @param string
		 */
		public void setReplyTopic(String replyTopic)
		{
			this.replyTopic = replyTopic;
		}

		/**
		 * @param vector
		 */
		public void setReportData(Vector reportData)
		{
			this.reportData = reportData;
		}

		/**
		 * @return
		 */
		public String getAgencyId()
		{
			return agencyId;
		}

		/**
		 * @param string
		 */
		public void setAgencyId(String string)
		{
			agencyId = string;
		}

}

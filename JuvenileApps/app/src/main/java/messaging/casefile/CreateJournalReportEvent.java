package messaging.casefile;

import messaging.journal.to.JournalTO;
import mojo.km.messaging.reporting.ReportRequestEvent;


public class CreateJournalReportEvent extends ReportRequestEvent 
{
	private String casefileId;
	private JournalTO journalReportDataTO;
	
	/**
	* 
	*/
	public CreateJournalReportEvent() 
	{
    
	}
	
	/**
	 * @return
	 */
	public String getCasefileId()
	{
		return casefileId;
	}

	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
	}

	/**
	 * @return Returns the journalReportDataTO.
	 */
	public JournalTO getJournalDataTO() 
	{
		return journalReportDataTO;
	}
	
	/**
	 * @param journalReportDataTO The journalDataTO to set.
	 */
	public void setJournalDataTO( JournalTO journalDataTO ) 
	{
		this.journalReportDataTO = journalDataTO;
	}
}

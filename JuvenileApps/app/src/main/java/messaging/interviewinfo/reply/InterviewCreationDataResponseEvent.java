package messaging.interviewinfo.reply;

import java.util.ArrayList;
import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class InterviewCreationDataResponseEvent extends ResponseEvent
{
	private String casefileId;
	private List personsToInterview = new ArrayList();


	/**
	 * @return
	 */
	public String s()
	{
		return casefileId;
	}

	/**
	 * @return
	 */
	public List getPersonsToInterview()
	{
		return personsToInterview;
	}

	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
	}

}

/*
 * Created on Oct 29, 2004
 */
package messaging.juvenilewarrant.reply;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dnikolis
 */
public class SummaryOfFactsResponseEvent extends ResponseEvent
{
	private String daLogNum;
	private String seqNum;
	private Map summaryOfFacts;

	public SummaryOfFactsResponseEvent()
	{
		this.summaryOfFacts = new TreeMap();
	}

	/**
	 * @return
	 */
	public String getDaLogNum()
	{
		return daLogNum;
	}

	/**
	 * @return
	 */
	public String getSeqNum()
	{
		return seqNum;
	}

	/**
	 * @param daLogNum
	 */
	public void setDaLogNum(String daLogNum)
	{
		this.daLogNum = daLogNum;
	}

	/**
	 * @param seqNum
	 */
	public void setSeqNum(String seqNum)
	{
		this.seqNum = seqNum;
	}

	/**
	 * @return
	 */
	public List getSummaryOfFacts()
	{
		List facts;
		if (summaryOfFacts != null)
		{
			facts = new ArrayList(this.summaryOfFacts.values());
		}
		else
		{
		    facts = new ArrayList();
		}
		return facts;
	}

	/**
	 * @param list
	 */
	public void addSummaryOfFact(String seqNum, String fact)
	{
		try
		{
			Integer seq = new Integer(seqNum);
			summaryOfFacts.put(seq, fact);
		}
		catch (NumberFormatException e)
		{
			// TODO Handle exception
			e.printStackTrace();
		}
	}

}
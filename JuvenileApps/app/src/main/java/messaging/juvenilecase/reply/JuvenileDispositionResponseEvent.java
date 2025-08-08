/*
 * Created on Apr 25, 2005
 */
package messaging.juvenilecase.reply;

import java.text.SimpleDateFormat;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * A fully populated JuvenileDispositionResponseEvent value object
 * 
 * @author glyons
 */
public class JuvenileDispositionResponseEvent extends ResponseEvent
{
	private Date dispositionDate;
	private String judgement;
	private Date judgementDate;
	private String probationTime;
	private String tycTime;
	private String petitionNum;
	private String juvenileNum;
	private String courtId;

	/**
	 * @return dispositionDate
	 */
	public Date getDispositionDate()
	{
		return dispositionDate;
	}

	/**
	 * @return judgement
	 */
	public String getJudgement()
	{
		return judgement;
	}

	/**
	 * @return judgementDate
	 */
	public Date getJudgementDate()
	{
		return judgementDate;
	}

	/**
	 * formatted version of judgement date mm/dd/yyyy
	 * @return
	 */
	public String getJudgementDateFormatted()
	{
		if (this.judgementDate != null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			return formatter.format(this.judgementDate);
		}
		return "";
	}

	/**
	 * @return juvenileNum
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return petitionNum
	 */
	public String getPetitionNum()
	{
		return petitionNum;
	}

	/**
	 * @return probationTime
	 */
	public String getProbationTime()
	{
		return probationTime;
	}

	/**
	 * @return tycTime
	 */
	public String getTYCTime()
	{
		return tycTime;
	}

	/**
	 * @param date
	 */
	public void setDispositionDate(Date date)
	{
		dispositionDate = date;
	}

	/**
	 * formated version of dispositionDate mm/dd/yyyy
	 * @return
	 */
	public String getDispositionDateFormatted()
	{
		if (this.dispositionDate != null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			return formatter.format(this.dispositionDate);
		}
		return "";
	}

	/**
	 * @param string
	 */
	public void setJudgement(String string)
	{
		judgement = string;
	}

	/**
	 * @param date
	 */
	public void setJudgementDate(Date date)
	{
		judgementDate = date;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param string
	 */
	public void setPetitionNum(String string)
	{
		petitionNum = string;
	}

	/**
	 * @param string
	 */
	public void setProbationTime(String string)
	{
		probationTime = string;
	}

	/**
	 * @param string
	 */
	public void setTYCTime(String string)
	{
		tycTime = string;
	}


	/**
	 * @return
	 */
	public String getCourtId()
	{
		return courtId;
	}

	/**
	 * @param string
	 */
	public void setCourtId(String string)
	{
		courtId = string;
	}

}
/*
 * Created on Apr 25, 2005
 */
package messaging.juvenilecase.reply;

import java.text.SimpleDateFormat;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * A fully populated JuvenileCourtDispositionResponseEvent value object
 * 
 * @author mchowdhury
 */
public class JuvenileCourtDispositionResponseEvent extends ResponseEvent
{
	private Date dispositionDate;
	private Date judgementDate;
	private String petitionNum;
	private String juvenileNum;
	private String courtId;
	private String hearingTypeId;
	private Date courtDate;
	private String courtTime;
	private String dispositionNumber;

	/**
	 * @return dispositionDate
	 */
	public Date getDispositionDate()
	{
		return dispositionDate;
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
	public String getCourtDateTimeFormatted()
	{
		if (this.courtDate != null)
		{
			StringBuffer buf = new StringBuffer();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			buf.append(formatter.format(this.courtDate));
			if(this.courtTime != null){
			   buf.append(" ");
			   String hour = this.courtTime.substring(0,2);
			   buf.append(hour);
			   buf.append(":");
			   buf.append(this.courtTime.substring(2));
			   if(Integer.parseInt(hour) < 12){
				   buf.append("am"); 	
			   }else{
				   buf.append("pm"); 	
			   }
			}
			return buf.toString();
		}
		return "";
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

	/**
	 * @return
	 */
	public String getHearingTypeId()
	{
		return hearingTypeId;
	}

	/**
	 * @param string
	 */
	public void setHearingTypeId(String string)
	{
		hearingTypeId = string;
	}

	/**
	 * @return
	 */
	public Date getCourtDate()
	{
		return courtDate;
	}

	/**
	 * @return
	 */
	public String getCourtTime()
	{
		return courtTime;
	}

	/**
	 * @param date
	 */
	public void setCourtDate(Date date)
	{
		courtDate = date;
	}

	/**
	 * @param string
	 */
	public void setCourtTime(String string)
	{
		courtTime = string;
	}

	/**
	 * @return
	 */
	public String getDispositionNumber()
	{
		return dispositionNumber;
	}

	/**
	 * @param string
	 */
	public void setDispositionNumber(String string)
	{
		dispositionNumber = string;
	}

}
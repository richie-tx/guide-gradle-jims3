//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionorder\\WithdrawSupervisionOrderEvent.java

package messaging.supervisionorder;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class WithdrawSupervisionOrderEvent extends RequestEvent 
{
	private String notes; 	
	private String supervisionOrderId;
	private Date withdrawDate;    
	private String withdrawReasonId;
   /**
    * @roseuid 44216993023F
    */
   public WithdrawSupervisionOrderEvent() 
   {

   }
	/**
	 * @return
	 */
	public String getNotes()
	{
		return notes;
	}

	/**
	 * @return
	 */
	public String getSupervisionOrderId()
	{
		return supervisionOrderId;
	}

	/**
	 * @return
	 */
	public Date getWithdrawDate()
	{
		return withdrawDate;
	}

	/**
	 * @return
	 */
	public String getWithdrawReasonId()
	{
		return withdrawReasonId;
	}

	/**
	 * @param string
	 */
	public void setNotes(String string)
	{
		notes = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisionOrderId(String string)
	{
		supervisionOrderId = string;
	}

	/**
	 * @param date
	 */
	public void setWithdrawDate(Date date)
	{
		withdrawDate = date;
	}

	/**
	 * @param string
	 */
	public void setWithdrawReasonId(String string)
	{
		withdrawReasonId = string;
	}

}

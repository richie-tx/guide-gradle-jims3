package messaging.administercaseload.reply;

import java.util.Comparator;
import java.util.Date;

import messaging.casefile.reply.ActivityResponseEvent;
import mojo.km.messaging.ResponseEvent;


public class DisposedCaseResponseEvent extends ResponseEvent {
   
	private String cdi;
	private String criminalCaseId;
	private String dispositionCd;
	private String offenseDate; //priorOffenseDate
	private String offenseCd;
	private String offenseDesc ="";
	private String dispositionDesc ="";
	
	public String getCdi() {
		return cdi;
	}
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
	public String getOffenseDesc() {
		return offenseDesc;
	}
	public void setOffenseDesc(String offenseDesc) {
		this.offenseDesc = offenseDesc;
	}
	public String getDispositionDesc() {
		return dispositionDesc;
	}
	public void setDispositionDesc(String dispositionDesc) {
		this.dispositionDesc = dispositionDesc;
	}
	private Date priorOffenseDate;
	
	public String getDispositionCd() {
		return dispositionCd;
	}
	public void setDispositionCd(String dispositionCd) {
		this.dispositionCd = dispositionCd;
	}
	public String getOffenseDate() {
		return offenseDate;
	}
	public void setOffenseDate(String offenseDate) {
		this.offenseDate = offenseDate;
	}
	public String getOffenseCd() {
		return offenseCd;
	}
	public void setOffenseCd(String offenseCd) {
		this.offenseCd = offenseCd;
	}
	public Date getPriorOffenseDate() {
		return priorOffenseDate;
	}
	public void setPriorOffenseDate(Date priorOffenseDate) {
		this.priorOffenseDate = priorOffenseDate;
	}
	
	public static Comparator PriorOffenseDateComparator = new Comparator() {
		public int compare(Object priorOffenseDate, Object otherpriorOffenseDate) {
		  int result = 0;
		  Date bPriorOffenseDate = ((DisposedCaseResponseEvent)priorOffenseDate).getPriorOffenseDate();
		  Date bOtherpriorOffenseDate = ((DisposedCaseResponseEvent)otherpriorOffenseDate).getPriorOffenseDate();
		  
		  if(bPriorOffenseDate == null)
		  {
			  result = -1;
		  }
		  else if(bOtherpriorOffenseDate == null)
		  {
			  result = -1;
		  }
		  else 
		  {
			  result = bPriorOffenseDate.compareTo(bOtherpriorOffenseDate);
		  }
		  return result;
		}	
	};

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object arg0, Object arg1)
	{
		int result = 0;

		ActivityResponseEvent obj1 = (ActivityResponseEvent)arg0;
		ActivityResponseEvent obj2 = (ActivityResponseEvent)arg1;
		
		if(obj2 == null || obj2.getActivityDate() == null) 
		{
			result = -1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
		}
		else if(obj1 == null || obj1.getActivityDate() == null) 
		{
			result = 1;// this makes any null objects go to the bottom change this to 1 if you want the top of the list
		}
		else
		{
			result = obj1.getActivityDate().compareTo(obj2.getActivityDate());
		}
		return result;
	}
	
}

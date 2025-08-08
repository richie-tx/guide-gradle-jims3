package pd.juvenilecase.referral;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.AttributeEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;

/**
* @roseuid 42A99C94029A
*/
public class Disposition extends PersistentObject {
	private Date judgementDate;
	private String petitionNum;
	private String probationTime;
	private String probationTimeInd;
	private Date dispositionDate;
	private String tYCTime;
	private String tYCTimeInd;
	private String judgement;
	private String daLogNum;
	/**
	* @roseuid 42A99C94029A
	*/
	public Disposition() {
	}
	/**
	* Access method for the dispositionDate property.
	* @return the current value of the dispositionDate property
	*/
	public Date getDispositionDate() {
		fetch();
		return dispositionDate;
	}
	/**
	* Sets the value of the dispositionDate property.
	* @param aDispositionDate the new value of the dispositionDate property
	*/
	public void setDispositionDate(Date aDispositionDate) {
		this.dispositionDate = aDispositionDate;
	}
	/**
	* Access method for the judgement property.
	* @return the current value of the judgement property
	*/
	public String getJudgement() {
		fetch();
		return judgement;
	}
	/**
	* Sets the value of the judgement property.
	* @param aJudgement the new value of the judgement property
	*/
	public void setJudgement(String aJudgement) {
		this.judgement = aJudgement;
	}
	/**
	* Access method for the judgementDate property.
	* @return the current value of the judgementDate property
	*/
	public Date getJudgementDate() {
		fetch();
		return judgementDate;
	}
	/**
	* Sets the value of the judgementDate property.
	* @param aJudgementDate the new value of the judgementDate property
	*/
	public void setJudgementDate(Date aJudgementDate) {
		this.judgementDate = aJudgementDate;
	}
	/**
	* Access method for the probationMonths property.
	* @return the current value of the probationMonths property
	*/
	public String getProbationTime() {
		fetch();
		return probationTime;
	}
	/**
	* Sets the value of the probationMonths property.
	* @param aProbationMonths the new value of the probationMonths property
	*/
	public void setProbationTime(String aProbationTime) {
		this.probationTime = aProbationTime;
	}
	/**
	* Access method for the tYCMonths property.
	* @return the current value of the tYCMonths property
	*/
	public String getTYCTime() {
		fetch();
		return tYCTime;
	}
	/**
	* Sets the value of the tYCMonths property.
	* @param aTYCMonths the new value of the tYCMonths property
	*/
	public void setTYCTime(String aTYCTime) {
		this.tYCTime = aTYCTime;
	}
	/**
	* Access method for the peititionNum property.
	* @return the current value of the peititionNum property
	*/
	public String getPetitionNum() {
		fetch();
		return petitionNum;
	}
	/**
	* Sets the value of the peititionNum property.
	* @param aPeititionNum the new value of the peititionNum property
	*/
	public void setPetitionNum(String aPetitionNum) {
		this.petitionNum = aPetitionNum;
	}
	/**
	* Access method for the daLogNum property.
	* @return the current value of the daLogNum property
	*/
	public String getDaLogNum() {
		fetch();
		return daLogNum;
	}
	/**
	* Sets the value of the daLogNum property.
	* @param aJudgement the new value of the daLogNum property
	*/
	public void setDaLogNum(String aDaLogNum) {
		this.daLogNum = aDaLogNum;
	}
	
	/**
	* Access method for the daLogNum property.
	* @return the current value of the daLogNum property
	*/
	public String getTYCTimeInd() {
		fetch();
		return tYCTimeInd;
	}
	/**
	* Sets the value of the daLogNum property.
	* @param aJudgement the new value of the daLogNum property
	*/
	public void setTYCTimeInd(String aTYCTimeInd) {
		this.tYCTimeInd = aTYCTimeInd;
	}
	
	/**
	* Access method for the daLogNum property.
	* @return the current value of the daLogNum property
	*/
	public String getProbationTimeInd() {
		fetch();
		return probationTimeInd;
	}
	/**
	* Sets the value of the daLogNum property.
	* @param aJudgement the new value of the daLogNum property
	*/
	public void setProbationTimeInd(String aProbationTimeInd) {
		this.probationTimeInd = aProbationTimeInd;
	}
	/**
	* @roseuid 42A99B990375
	* @return iterator of Dispositions
	*/
	public static Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, Disposition.class);			
	}
	
	public static Iterator findAllByDaLogNum(String daLogNum)
	{
		AttributeEvent event = new AttributeEvent();
		event.setAttributeName("daLogNum");
		event.setAttributeValue(daLogNum);
		return Disposition.findAll(event);
	}
}

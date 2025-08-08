/*
 * Created on July 10, 2007
 */
package messaging.juvenilewarrant.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author ugopinath
 */
public class JuvenileOffenderTrackingDispositionResponseEvent extends ResponseEvent implements Comparable
{
	private String assignedSanction;
	private String deviationReason;
	private Date dispositionDate;
	private String disposition;
	private String guidelineSanction;
	private Date judgementDate;
	private String judgement;
	private String probationTime;
	private String tYCTime;	
	private String dispositionOrder;
	private String daLogNum;
	private String petitionNum;
	private String seqNum;	
	/**
	 * @return Returns the assignedSanction.
	 */
	public String getAssignedSanction() {
		return assignedSanction;
	}
	/**
	 * @param assignedSanction The assignedSanction to set.
	 */
	public void setAssignedSanction(String assignedSanction) {
		this.assignedSanction = assignedSanction;
	}
	/**
	 * @return Returns the daLogNum.
	 */
	public String getDaLogNum() {
		return daLogNum;
	}
	/**
	 * @param daLogNum The daLogNum to set.
	 */
	public void setDaLogNum(String daLogNum) {
		this.daLogNum = daLogNum;
	}
	/**
	 * @return Returns the deviationReason.
	 */
	public String getDeviationReason() {
		return deviationReason;
	}
	/**
	 * @param deviationReason The deviationReason to set.
	 */
	public void setDeviationReason(String deviationReason) {
		this.deviationReason = deviationReason;
	}
	/**
	 * @return Returns the disposition.
	 */
	public String getDisposition() {
		return disposition;
	}
	/**
	 * @param disposition The disposition to set.
	 */
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	/**
	 * @return Returns the dispositionDate.
	 */
	public Date getDispositionDate() {
		return dispositionDate;
	}
	/**
	 * @param dispositionDate The dispositionDate to set.
	 */
	public void setDispositionDate(Date dispositionDate) {
		this.dispositionDate = dispositionDate;
	}
	/**
	 * @return Returns the dispositionOrder.
	 */
	public String getDispositionOrder() {
		return dispositionOrder;
	}
	/**
	 * @param dispositionOrder The dispositionOrder to set.
	 */
	public void setDispositionOrder(String dispositionOrder) {
		this.dispositionOrder = dispositionOrder;
	}
	/**
	 * @return Returns the guidelineSanction.
	 */
	public String getGuidelineSanction() {
		return guidelineSanction;
	}
	/**
	 * @param guidelineSanction The guidelineSanction to set.
	 */
	public void setGuidelineSanction(String guidelineSanction) {
		this.guidelineSanction = guidelineSanction;
	}
	/**
	 * @return Returns the judgement.
	 */
	public String getJudgement() {
		return judgement;
	}
	/**
	 * @param judgement The judgement to set.
	 */
	public void setJudgement(String judgement) {
		this.judgement = judgement;
	}
	/**
	 * @return Returns the judgementDate.
	 */
	public Date getJudgementDate() {
		return judgementDate;
	}
	/**
	 * @param judgementDate The judgementDate to set.
	 */
	public void setJudgementDate(Date judgementDate) {
		this.judgementDate = judgementDate;
	}
	/**
	 * @return Returns the petitionNum.
	 */
	public String getPetitionNum() {
		return petitionNum;
	}
	/**
	 * @param petitionNum The petitionNum to set.
	 */
	public void setPetitionNum(String petitionNum) {
		this.petitionNum = petitionNum;
	}
	/**
	 * @return Returns the probationTime.
	 */
	public String getProbationTime() {
		return probationTime;
	}
	/**
	 * @param probationTime The probationTime to set.
	 */
	public void setProbationTime(String probationTime) {
		this.probationTime = probationTime;
	}
	/**
	 * @return Returns the seqNum.
	 */
	public String getSeqNum() {
		return seqNum;
	}
	/**
	 * @param seqNum The seqNum to set.
	 */
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
	/**
	 * @return Returns the tYCTime.
	 */
	public String getTYCTime() {
		return tYCTime;
	}
	/**
	 * @param time The tYCTime to set.
	 */
	public void setTYCTime(String time) {
		tYCTime = time;
	}
	
	public int compareTo(Object obj)
	{
		if(obj==null)
			return -1;
		JuvenileOffenderTrackingDispositionResponseEvent evt = (JuvenileOffenderTrackingDispositionResponseEvent) obj;
		return evt.getSeqNum().compareToIgnoreCase(seqNum);		
	}	
	
}
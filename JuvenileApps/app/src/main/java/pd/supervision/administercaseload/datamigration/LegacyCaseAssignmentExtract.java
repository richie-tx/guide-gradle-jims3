package pd.supervision.administercaseload.datamigration;

import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import java.util.Date;

/**
 * 
 * @author dgibler
 */
public class LegacyCaseAssignmentExtract 
{
	private String criminalCaseId;
	private boolean currPoi;
	private String poi;
	private String pmJzpSeqNum;
	private String spn;
	private Date transactionDate;
	private int recordSeqNum;
	private String statusCd;

	/**
	 * @return Returns the criminalCaseId.
	 */
	public String getCriminalCaseId()
	{
		return criminalCaseId;
	}

	/**
	 * 
	 * @return Returns the currPoiInd.
	 */
	public boolean isCurrPoi()
	{
		return currPoi;
	}

	/**
	 * 
	 * @return Returns the poi.
	 */
	public String getPoi()
	{
		return poi;
	}

	/**
	 * 
	 * @return Returns the pmJzpSeqNum.
	 */
	public String getPmJzpSeqNum()
	{
		return pmJzpSeqNum;
	}

	/**
	 * 
	 * @return Returns the spn.
	 */
	public String getSpn()
	{
		return spn;
	}

	/**
	 * 
	 * @return Returns the transactionDate.
	 */
	public Date getTransactionDate()
	{
		return transactionDate;
	}

	/**
	 * @param criminalCaseId The criminalCaseId to set.
	 */
	public void setCriminalCaseId(String criminalCaseId)
	{
		this.criminalCaseId = criminalCaseId;
	}

	/**
	 * 
	 * @param currPoiInd The currPoiInd to set.
	 */
	public void setCurrPoi(boolean currPoiInd)
	{
		this.currPoi = currPoiInd;
	}

	/**
	 * 
	 * @param poi The poi to set.
	 */
	public void setPoi(String poi)
	{
		this.poi = poi;
	}

	/**
	 * 
	 * @param pmJzpSeqNum The pmJzpSeqNum to set.
	 */
	public void setPmJzpSeqNum(String seqNum)
	{
		this.pmJzpSeqNum = seqNum;
	}

	/**
	 * 
	 * @param spn The spn to set.
	 */
	public void setSpn(String spn)
	{
		this.spn = spn;
	}

	/**
	 * 
	 * @param transactionDate The transactionDate to set.
	 */
	public void setTransactionDate(Date transactionDate)
	{
		this.transactionDate = transactionDate;
	}

	public String toString()
	{
		final String PIPE = "|";
		StringBuffer sb = new StringBuffer();
//		sb.append(PIPE);
		sb.append(this.getRecordSeqNum());
		sb.append(PIPE);
		sb.append(this.spn);
		sb.append(PIPE);
		sb.append(this.criminalCaseId);
		sb.append(PIPE);
		sb.append(this.pmJzpSeqNum);
		sb.append(PIPE);
		sb.append(this.poi);
		sb.append(PIPE);
		sb.append(DateUtil.dateToString(this.transactionDate, UIConstants.DATE_FMT_1));
		sb.append(PIPE);
		return sb.toString();
	}
	/**
	 * @return Returns the recordSeqNum.
	 */
	public int getRecordSeqNum()
	{
		return recordSeqNum;
	}

	/**
	 * @param recordSeqNum The recordSeqNum to set.
	 */
	public void setRecordSeqNum(int recordSeqNum)
	{
		this.recordSeqNum = recordSeqNum;
	}

	/**
	 * @return Returns the statusCd.
	 */
	public String getStatusCd()
	{
		return statusCd;
	}

	/**
	 * @param statusCd The statusCd to set.
	 */
	public void setStatusCd(String statusCd)
	{
		this.statusCd = statusCd;
	}

}

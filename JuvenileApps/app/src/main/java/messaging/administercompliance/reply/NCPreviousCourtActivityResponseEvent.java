/*
 * Created on Dec 03, 2007
 */
package messaging.administercompliance.reply;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class NCPreviousCourtActivityResponseEvent extends ResponseEvent 
{
    private Timestamp occurenceDate;
    private String occurenceDateStr;
    private String typeOfCourtActionComment;
    private String summaryOfCourtAction;
    private String shortTruncatedSummaryOfCourtAction;
    private String disposition;
    private String type;
    private String ncResponseId;
    private String previousCourtActivityId;
    private String subType;
    private boolean manualAdded;
    
    private static final String SUBTYPE_VIOLATION_REPORT="VIOLATION";
    private static final String SUBTYPE_MOTION="MOTION";
    private static final String SUBTYPE_OTHER="OTHER";
    
	/**
	 * @return the manualAdded
	 */
	public boolean isManualAdded() {
		return manualAdded;
	}
	/**
	 * @param manualAdded the manualAdded to set
	 */
	public void setManualAdded(boolean manualAdded) {
		this.manualAdded = manualAdded;
	}
	/**
	 * @return the subType
	 */
	public String getSubType() {
		return subType;
	}
	/**
	 * @param subType the subType to set
	 */
	public void setSubType(String subType) {
		this.subType = subType;
	}
	/**
	 * @return Returns the typeOfCourtActionComment.
	 */
	public String getTypeOfCourtActionComment() {
		return typeOfCourtActionComment;
	}

	/**
	 * @param typeOfCourtActionComment the typeOfCourtActionComment to set
	 */
	public void setTypeOfCourtActionComment(String typeOfCourtActionComment) {
		this.typeOfCourtActionComment = typeOfCourtActionComment;
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
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	/**
	 * @return Returns the occurenceDate.
	 */
	public Timestamp getOccurenceDate() {
		return occurenceDate;
	}
	/**
	 * 
	 * @return
	 */
	public String getOccurenceDateStr() {
		return occurenceDateStr;
	}
	/**
	 * 
	 * @param occurenceDateStr
	 */
	public void setOccurenceDateStr(String occurenceDateStr) {
		this.occurenceDateStr = occurenceDateStr;
	}
	/**
	 * @param occurenceDate The occurenceDate to set.
	 */
	public void setOccurenceDate(Timestamp occurenceDate) {
		this.occurenceDate = occurenceDate;
	}
	/**
	 * @return Returns the summaryOfCourtAction.
	 */
	public String getSummaryOfCourtAction() {
		return summaryOfCourtAction;
	}
	/**
	 * @param summaryOfCourtAction The summaryOfCourtAction to set.
	 */
	public void setSummaryOfCourtAction(String summaryOfCourtAction) {
		this.summaryOfCourtAction = summaryOfCourtAction;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getShortTruncatedSummaryOfCourtAction() {
		return shortTruncatedSummaryOfCourtAction;
	}
	
	/**
	 * 
	 * @param shortTruncatedSummaryOfCourtAction
	 */
	public void setShortTruncatedSummaryOfCourtAction(
			String shortTruncatedSummaryOfCourtAction) {
		this.shortTruncatedSummaryOfCourtAction = shortTruncatedSummaryOfCourtAction;
	}
	
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the previousCourtActivityId.
	 */
	public String getPreviousCourtActivityId() {
		return previousCourtActivityId;
	}
	/**
	 * @param previousCourtActivityId The previousCourtActivityId to set.
	 */
	public void setPreviousCourtActivityId(String previousCourtActivityId) {
		this.previousCourtActivityId = previousCourtActivityId;
	}
	
	
	/**
	 * sort based on the type of court activity for print - ViolationReport, then Motion, Then Others
	 * with a secondary sort based on the date
	 */
	public static Comparator NCPReviousCourtActivityResponseEventSubTypeComparator = new Comparator() {
		public int compare(Object courtActivityVersion, Object previousCourtActivityVersion){
		
		if(courtActivityVersion == null || previousCourtActivityVersion == null || !(courtActivityVersion instanceof NCPreviousCourtActivityResponseEvent) 
				||  !(previousCourtActivityVersion instanceof NCPreviousCourtActivityResponseEvent)){
			return 1;
		}
		NCPreviousCourtActivityResponseEvent courtActivity = (NCPreviousCourtActivityResponseEvent)courtActivityVersion;
		NCPreviousCourtActivityResponseEvent previousCourtActivity = (NCPreviousCourtActivityResponseEvent)previousCourtActivityVersion;
		String courtActivitySubType = courtActivity.getSubType();
		String previousCourtActivitySubType = previousCourtActivity.getSubType();
		Date courtActivityOccurrencedate = courtActivity.getOccurenceDate();
		Date previousCourtActivityOccurrencedate = previousCourtActivity.getOccurenceDate();
		int resultSortOrder = 0;
		// step 1: check on the subtype.... 
		if(courtActivitySubType != null && previousCourtActivitySubType != null){
			resultSortOrder = determinePreviousCourtActivitySubtypeOrder(courtActivitySubType, previousCourtActivitySubType);
		// step 2: If same subType, then sort based on OccurenceDate
		}if(courtActivityOccurrencedate != null && previousCourtActivityOccurrencedate != null && resultSortOrder == 0) {
				resultSortOrder = previousCourtActivityOccurrencedate.compareTo(courtActivityOccurrencedate);
		}	
		return resultSortOrder;

	}
	};

	
	/**
	 * sort two previous subtype values - 1) VIOLATION REPORT 2) MOTION 3) OTHER
	 * @param courtActivitySubType
	 * @param previousCourtActivitySubType
	 * @return
	 */
	private static int determinePreviousCourtActivitySubtypeOrder(String courtActivitySubType, String previousCourtActivitySubType){
		int resultSortOrder = 0;
		if(courtActivitySubType.equals(previousCourtActivitySubType)){
			resultSortOrder = 0;
		}else{
			if(courtActivitySubType.equals(SUBTYPE_VIOLATION_REPORT)){
				resultSortOrder = -1;
			}else if(courtActivitySubType.equals(SUBTYPE_OTHER)){
				resultSortOrder = 1;
			}else if(courtActivitySubType.equals(SUBTYPE_MOTION)){
				if(previousCourtActivitySubType.equals(SUBTYPE_VIOLATION_REPORT)){
					resultSortOrder = 1;
				}else{
					resultSortOrder = -1;
				}
			}
		}
		return resultSortOrder;
	}
 }

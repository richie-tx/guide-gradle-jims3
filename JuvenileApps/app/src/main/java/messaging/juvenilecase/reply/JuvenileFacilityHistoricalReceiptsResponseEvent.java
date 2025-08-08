/*
 * Created on Apr 25, 2005
 */
package messaging.juvenilecase.reply;

import java.util.Comparator;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * A fully populated JuvenileFacilityHistoricalReceiptsResponseEvent value object
 * 
 * @author rcarter
 */
public class JuvenileFacilityHistoricalReceiptsResponseEvent extends ResponseEvent implements Comparable<JuvenileFacilityHistoricalReceiptsResponseEvent>
{
	private String admitReason;
	private Date admitDate;
	private String facilityCode;	
	private String referralNumber;
	private Date releaseDate;
	private String facilityName;
	private String lockerNumber;
	private String receiptNumber;
	private String juvenileNumber;


	/**
	 * @return Returns the admitDate.
	 */
	public Date getAdmitDate() {
		return admitDate;
	}
	/**
	 * @param admitDate The admitDate to set.
	 */
	public void setAdmitDate(Date admitDate) {
		
		this.admitDate = admitDate;
	}
	/**
	 * @return Returns the admitReason.
	 */
	public String getAdmitReason() {
		return admitReason;
	}
	/**
	 * @param admitReason The admitReason to set.
	 */
	public void setAdmitReason(String admitReason) {
		this.admitReason = admitReason;
	}

	/**
	 * @return Returns the facilityCode.
	 */
	public String getFacilityCode() {
		return facilityCode;
	}
	/**
	 * @param facilityCode The facilityCode to set.
	 */
	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}

	/**
	 * @return Returns the referralNumber.
	 */
	public String getReferralNumber() {
		return referralNumber;
	}
	/**
	 * @param referralNumber The referralNumber to set.
	 */
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}

	/**
	 * @return Returns the releaseDate.
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	/**
	 * @param releaseDate The releaseDate to set.
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
    /**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}
	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	
	/**
	 * @return the lockerNumber
	 */
	public String getLockerNumber() {
		return lockerNumber;
	}
	/**
	 * @param lockerNumber the lockerNumber to set
	 */
	public void setLockerNumber(String lockerNumber) {
		this.lockerNumber = lockerNumber;
	}
	/**
	 * @return the receiptNumber
	 */
	public String getReceiptNumber() {
		return receiptNumber;
	}
	/**
	 * @param receiptNumber the receiptNumber to set
	 */
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	
	public String getJuvenileNumber() {
		return juvenileNumber;
	}
	
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}
	
	public int compareTo(JuvenileFacilityHistoricalReceiptsResponseEvent o) {
    	return Integer.valueOf(o.referralNumber) - Integer.valueOf(this.referralNumber) ;
	}
	
	public static Comparator ReleaseDateComparator = new Comparator(){

		public int compare(Object obj1, Object obj2){

			if(obj1==null || !(obj1 instanceof JuvenileFacilityHistoricalReceiptsResponseEvent))
	
			return 0;
	
			if(obj2==null || !(obj2 instanceof JuvenileFacilityHistoricalReceiptsResponseEvent))
	
			return 0;
	
			Date code1= ((JuvenileFacilityHistoricalReceiptsResponseEvent)obj1).getReleaseDate();
	
			Date code2= ((JuvenileFacilityHistoricalReceiptsResponseEvent)obj2).getReleaseDate();
	
			if(code1==null) return 1;
	
			if(code2==null) return -1;

			return code2.compareTo(code1);

		}

	};


	
}
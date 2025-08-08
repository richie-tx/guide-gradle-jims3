/**
 * 
 */
package pd.supervision.administerserviceprovider;

import java.util.Date;
import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author ugopinath
 *
 */
public class ProgramSourceFund extends PersistentObject{
	
	private String provProgramId;
	
	private String programSourceFund;
	
	private Date fundStartDate;
	
	private Date fundEndDate;
	
	private String fundStatus;
	
	private Date fundEntryDate;
	
	private String programSourceFundId;

	public ProgramSourceFund() {
	
	}


	public void createOID() {
		new Home().bind(this);
	}
	
	/**
	* @return pd.supervision.administerserviceprovider.ProgramSourceFund
	* @param programCode
	* @roseuid 4107B06D01B5
	*/
	static public ProgramSourceFund find(String programSourceFundId) {
		ProgramSourceFund programFund = null;
		IHome home = new Home();
		programFund = (ProgramSourceFund) home.find(programSourceFundId, ProgramSourceFund.class);
		return programFund;
	}
	/**
	* @return pd.supervision.administerserviceprovider.ProgramSourceFund
	* @param programCode
	* @roseuid 4107B06D01B5
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		Iterator programSourceFund = null;
		IHome home = new Home();
		programSourceFund = home.findAll(attrName, attrValue, ProgramSourceFund.class);
		return programSourceFund;
	}
	/**
	 * @return the programSourceFund
	 */
	public String getProgramSourceFund() {
		fetch();
		return programSourceFund;
	}



	/**
	 * @param programSourceFund the programSourceFund to set
	 */
	public void setProgramSourceFund(String aProgramSourceFund) {
		if (this.programSourceFund == null || !this.programSourceFund.equals(aProgramSourceFund)) {
			markModified();
		}
		this.programSourceFund = aProgramSourceFund;
	}



	/**
	 * @return the fundStartDate
	 */
	public Date getFundStartDate() {
		fetch();
		return fundStartDate;
	}



	/**
	 * @param fundStartDate the fundStartDate to set
	 */
	public void setFundStartDate(Date aFundStartDate) {
		if (this.fundStartDate == null || !this.fundStartDate.equals(aFundStartDate)) {
			markModified();
		}
		this.fundStartDate = aFundStartDate;
	}



	/**
	 * @return the fundEndDate
	 */
	public Date getFundEndDate() {
		fetch();
		return fundEndDate;
	}



	/**
	 * @param fundEndDate the fundEndDate to set
	 */
	public void setFundEndDate(Date aFundEndDate) {
		if (this.fundEndDate == null || !this.fundEndDate.equals(aFundEndDate)) {
			markModified();
		}
		this.fundEndDate = aFundEndDate;
	}



	/**
	 * @return the fundStatus
	 */
	public String getFundStatus() {
		fetch();
		return fundStatus;
	}



	/**
	 * @param fundStatus the fundStatus to set
	 */
	public void setFundStatus(String aFundStatus) {
		if (this.fundStatus == null || !this.fundStatus.equals(aFundStatus)) {
			markModified();
		}
		this.fundStatus = aFundStatus;
	}


	public Date getFundEntryDate() {
		fetch();
		return fundEntryDate;
	}


	public void setFundEntryDate(Date aFundEntryDate) {
		if (this.fundEntryDate == null || !this.fundEntryDate.equals(aFundEntryDate)) {
			markModified();
		}
		this.fundEntryDate = aFundEntryDate;
	}


	public String getProvProgramId() {
		fetch();
		return provProgramId;
	}


	public void setProvProgramId(String aProvProgramId) {
		if (this.provProgramId == null || !this.provProgramId.equals(aProvProgramId)) {
			markModified();
		}
		this.provProgramId = aProvProgramId;
	}


	public String getProgramSourceFundId() {
		return programSourceFundId;
	}


	public void setProgramSourceFundId(String programSourceFundId) {
		this.programSourceFundId = programSourceFundId;
	}


}

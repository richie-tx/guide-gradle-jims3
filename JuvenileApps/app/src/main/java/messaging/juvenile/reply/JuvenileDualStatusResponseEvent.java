/*
 * Created on Jun 17, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import pd.juvenile.JuvenileAbusePerpatrator;

import mojo.km.messaging.ResponseEvent;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileDualStatusResponseEvent extends ResponseEvent
{
	private final String DATE_FORMAT_1 = "M/d/yyyy";
	private Date entryDate;
	private String referralRegion;
	private String custodyStatus;
	private String cpslevelofCare;
	private String parentalrightsTermination;
	private String dualstatusId;
	private String CPSServices;
	private String dualstatusComments;	
	private String traitTypeId;
	private String traitTypeName;
	private String informationBasisId;
	private String informationSrcCd; 
	private String informationSrcDesc;
	//
	private String juvenileNumber;
	
	
	
	public String getDualstatusId()
	{
	    return dualstatusId;
	}

	public void setDualstatusId(String dualstatusId)
	{
	    this.dualstatusId = dualstatusId;
	}
	public String getReferralRegion()
	{
	    return referralRegion;
	}

	public void setReferralRegion(String referralRegion)
	{
	    this.referralRegion = referralRegion;
	}
	public String getCustodyStatus()
	{
	    return custodyStatus;
	}

	public void setCustodyStatus(String custodyStatus)
	{
	    this.custodyStatus = custodyStatus;
	}
	public String getCpslevelofCare()
	{
	    return cpslevelofCare;
	}

	public void setCpslevelofCare(String cpslevelofCare)
	{
	    this.cpslevelofCare = cpslevelofCare;
	}
	public String getParentalrightsTermination()
	{
	    return parentalrightsTermination;
	}

	public void setParentalrightsTermination(String parentalrightsTermination)
	{
	    this.parentalrightsTermination = parentalrightsTermination;
	}
	public Date getEntryDate()
	{
		return entryDate;
	}
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}
	public String getEntryDateString()
	{
		String dateString = null;
		if (entryDate != null)
		{
			SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT_1);
			dateString = fmt.format(entryDate);
		}
		return dateString;
	}
	public String getCPSServices()
	{
	    return CPSServices;
	}

	public void setCPSServices(String cPSServices)
	{
	    CPSServices = cPSServices;
	}	
	
	public String getDualstatusComments()
	{
	    return dualstatusComments;
	}

	public void setDualstatusComments(String dualstatusComments)
	{
	    this.dualstatusComments = dualstatusComments;
	}
	
	/**
	 * @return
	 */
	public String getTraitTypeId()
	{
		return traitTypeId;
	}

	/**
	 * @return
	 */
	public String getTraitTypeName()
	{
		return traitTypeName;
	}



	/**
	 * @param string
	 */
	public void setTraitTypeId(String string)
	{
		traitTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setTraitTypeName(String string)
	{
		traitTypeName = string;
	}

	/**
	 * @return Returns the informationBasisId.
	 */
	public String getInformationBasisId() {
		return informationBasisId;
	}
	/**
	 * @param informationBasisId The informationBasisId to set.
	 */
	public void setInformationBasisId(String informationBasisId) {
		this.informationBasisId = informationBasisId;
	}
	public String getInformationSrcCd() {
		return informationSrcCd;
	}

	public void setInformationSrcCd(String informationSrcCd) {
		this.informationSrcCd = informationSrcCd;
	}

	public String getInformationSrcDesc() {
		return informationSrcDesc;
	}

	public void setInformationSrcDesc(String informationSrcDesc) {
		this.informationSrcDesc = informationSrcDesc;
	}
	public String getJuvenileNumber()
	{
	    return juvenileNumber;
	}

	public void setJuvenileNumber(String juvenileNumber)
	{
	    this.juvenileNumber = juvenileNumber;
	}

}

/*
 * Project: JIMS
 * Class:   ui.juvenilecase.form.JuvenileProfileSearchForm
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package ui.juvenilecase.programreferral.form;

import java.util.Collection;

import messaging.juvenile.SearchJuvenileProfilesEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.JuvenileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;
import ui.common.CodeHelper;

/**
 *  
 * @author  dwilliamson programReferralSearchForm
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProgramReferralSearchForm extends ProgramReferralForm //org.apache.struts.action.ActionForm
{
	// ------------------------------------------------------------------------
	// --- fields                                                           ---
	// ------------------------------------------------------------------------

	private String fromBeginDate;
	private String jpoJuvenileNameInd;
	private String juvenileNum;
	private String firstName;
	private String lastName;
	private String middleName;
	private String providerProgramName;
	private String referralStatusDescription;
	private String serviceProviderName;
	private String toBeginDate;
	private String fromCloseDate;
	private String toCloseDate;
	private Collection programs;
	private Collection programsReferralStatus;
	
	int aSearchResultSize;
	
	private Collection juvenileProfiles;
	
	public String getFromBeginDate() {
		return fromBeginDate;
	}

	public void setFromBeginDate(String fromBeginDate) {
		this.fromBeginDate = fromBeginDate;
	}

	public String getJpoJuvenileNameInd() {
		return jpoJuvenileNameInd;
	}

	public void setJpoJuvenileNameInd(String jpoJuvenileNameInd) {
		this.jpoJuvenileNameInd = jpoJuvenileNameInd;
	}

	public String getProviderProgramName() {
		return providerProgramName;
	}

	public void setProviderProgramName(String providerProgramName) {
		this.providerProgramName = providerProgramName;
	}

	public String getReferralStatusDescription() {
		return referralStatusDescription;
	}

	public void setReferralStatusDescription(String referralStatusDescription) {
		this.referralStatusDescription = referralStatusDescription;
	}

	public String getServiceProviderName() {
		return serviceProviderName;
	}

	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	public String getToBeginDate() {
		return toBeginDate;
	}

	public void setToBeginDate(String toBeginDate) {
		this.toBeginDate = toBeginDate;
	}

	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	public void clear()
	{
		firstName = null;
		fromBeginDate = null;
		jpoJuvenileNameInd = null;
		juvenileNum = null;
		lastName = null;
		middleName = null;
		providerProgramName = null;
		referralStatusDescription = null;
		serviceProviderName = null;
		toBeginDate = null;
		fromCloseDate = null;
		toCloseDate = null;
		aSearchResultSize = 0;


	} //end clear

	/**
	 *  
	 * @return  The search result size.
	 */
	public int getSearchResultSize()
	{
		return aSearchResultSize;
	} //end getSearchResultSize

	/**
	 *  
	 * @param i The search result size.
	 */
	public void setSearchResultSize(int i)
	{
		aSearchResultSize = i;
	} //end of setSearchResultSize

	
	/**
	 *  
	 * @return  The first name.
	 */
	public String getFirstName()
	{
		if (firstName != null)
		{
			return firstName.trim();
		}
		return firstName;
	} //end getFirstName

	/**
	 *  
	 * @return  The juvenile num.
	 */
	public String getJuvenileNum()
	{
		if (juvenileNum != null)
		{
			return juvenileNum.trim();
		}
		return juvenileNum;
	} //end getJuvenileNum

	/**
	 *  
	 * @return  The last name.
	 */
	public String getLastName()
	{
		if (lastName != null)
		{
			return lastName.trim();
		}
		return lastName;
	} //end getLastName

	/**
	 *  
	 * @return  The middle name.
	 */
	public String getMiddleName()
	{
		if (middleName != null)
		{
			return middleName.trim();
		}
		return middleName;
	} //end getMiddleName

	

	

	/**
	 *  
	 * @param string The first name.
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	} //end setFirstName

	/**
	 *  
	 * @param string The juvenile num.
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	} //end setJuvenileNum

	/**
	 *  
	 * @param string The last name.
	 */
	public void setLastName(String string)
	{
		lastName = string;
	} //end setLastName

	/**
	 *  
	 * @param string The middle name.
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	} //end setMiddleName

	public void setPrograms(Collection programs) {
		this.programs = programs;
	}

	public Collection getPrograms() {
		return programs;
	}

	public void setProgramsReferralStatus(Collection programsReferralStatus) {
		this.programsReferralStatus = programsReferralStatus;
	}

	public Collection getProgramsReferralStatus() {
		return programsReferralStatus;
	}

	public String getFromCloseDate()
	{
	    return fromCloseDate;
	}

	public void setFromCloseDate(String fromCloseDate)
	{
	    this.fromCloseDate = fromCloseDate;
	}

	public String getToCloseDate()
	{
	    return toCloseDate;
	}

	public void setToCloseDate(String toCloseDate)
	{
	    this.toCloseDate = toCloseDate;
	}

	//public void clearAll() {
		// TODO Auto-generated method stub
		
	//}

	

} // end JuvenileProfileSearchForm

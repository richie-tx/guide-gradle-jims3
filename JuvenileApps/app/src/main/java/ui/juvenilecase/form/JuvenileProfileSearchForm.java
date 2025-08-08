/*
 * Project: JIMS
 * Class:   ui.juvenilecase.form.JuvenileProfileSearchForm
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import pd.codetable.criminal.JuvenileMasterStatus;

import messaging.juvenile.SearchJuvenileProfilesEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.JuvenileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;
import ui.common.CodeHelper;
import ui.common.SocialSecurity;

/**
 *  
 * @author  athorat
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileProfileSearchForm extends org.apache.struts.action.ActionForm
{
	// ------------------------------------------------------------------------
	// --- fields                                                           ---
	// ------------------------------------------------------------------------

	private String alienNumber;
	private String firstName;
	private String lastName;
	private String raceId;
	private String dateOfBirth;
	private String statusId;
	private String juvenileNum;
	private String supervisionNumber;
	private String middleName;
	private String searchType;
	private String sexId;
	private String juvenileDOB;
	//task 171828
	private String dalogNum;
	
	

	private SocialSecurity ssn = new SocialSecurity("");
	int aSearchResultSize;

	private Collection juvenileProfiles;
	
	
	//Facility Changes Starts
	private String facilityAction;
	
	//added for referrals
	private String referralAction;
	
	//Added for US 32107
	private String restrictedAccessFeature;

	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	public void clear()
	{
		juvenileProfiles = null;
		aSearchResultSize = 0;

		alienNumber = null;
		firstName = null;
		lastName = null;
		raceId = null;
		dateOfBirth = null;
		statusId = null;
		juvenileNum = null;
		supervisionNumber = null;
		middleName = null;
		searchType = null;
		sexId = null;
		juvenileDOB = null;
		facilityAction=null;
		referralAction=null;
		ssn = new SocialSecurity("");
		dalogNum=null;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.clear

	/**
	 *  
	 * @return  The search result size.
	 */
	public int getSearchResultSize()
	{
		return aSearchResultSize;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getSearchResultSize

	/**
	 *  
	 * @param i The search result size.
	 */
	public void setSearchResultSize(int i)
	{
		aSearchResultSize = i;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.setSearchResultSize

	/**
	 * Returns the collection code response events for the sex types
	 *  
	 * @return  The sex types.
	 */
	public Collection getSexTypes()
	{
		return CodeHelper.getJJSSexCodes();
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getSexTypes

	/**
	 * Returns the collection code response events for the race types
	 *  
	 * @return  The race types.
	 */
	public Collection getRaceTypes()
	{
		return CodeHelper.getJJSRaces(true);
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getRaceTypes

	/**
	 * Returns the collection code response events for the juvenile case statues
	 *  
	 * @return  The juvenile case statuses.
	 */
	public Collection getJuvenileCaseStatuses()
	{
		return CodeHelper.getJuvenileProfileStatuses();
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getJuvenileCaseStatuses

	/**
	 * Returns the collection code response events for the juvenile profile statues
	 *  
	 * @return  juvProfileStatusCodes.
	 */
	public Collection getJuvProfileStatusCodes()
	{
		return CodeHelper.getJuvenileProfileStatuses();
	}
	public Collection getJuvMasterStatusCodes()
	{
	    ArrayList<JuvenileMasterStatus> masterStatusCodes = new ArrayList<JuvenileMasterStatus>();
	    
	    Iterator codes = JuvenileMasterStatus.findAll();
	    while ( codes.hasNext() ) {
		masterStatusCodes.add( (JuvenileMasterStatus)codes.next() );
	    }
	    
	    return masterStatusCodes;
	}
	

	/**
	 *  
	 * @return  The alien number.
	 */
	public String getAlienNumber()
	{
		if (alienNumber != null)
		{
			return alienNumber.trim();
		}
		return alienNumber;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getAlienNumber

	/**
	 *  
	 * @return  The date of birth.
	 */
	public String getDateOfBirth()
	{
		return dateOfBirth;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getDateOfBirth

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
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getFirstName

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
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getJuvenileNum

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
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getLastName

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
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getMiddleName

	/**
	 *  
	 * @return  The race id.
	 */
	public String getRaceId()
	{
		return raceId;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getRaceId

	/**
	 *  
	 * @return  The search type.
	 */
	public String getSearchType()
	{
		return searchType;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getSearchType

	/**
	 *  
	 * @return  The sex id.
	 */
	public String getSexId()
	{
		return sexId;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getSexId

	/**
	 *  
	 * @return  The status id.
	 */
	public String getStatusId()
	{
		return statusId;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getStatusId

	/**
	 *  
	 * @return  The supervision number.
	 */
	public String getSupervisionNumber()
	{
		if (supervisionNumber != null)
		{
			return supervisionNumber.trim();
		}
		return supervisionNumber;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getSupervisionNumber

	/**
	 *  
	 * @param string The alien number.
	 */
	public void setAlienNumber(String string)
	{
		alienNumber = string;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.setAlienNumber

	/**
	 *  
	 * @param date The date of birth.
	 */
	public void setDateOfBirth(String date)
	{
		dateOfBirth = date;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.setDateOfBirth

	/**
	 *  
	 * @param string The first name.
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.setFirstName

	/**
	 *  
	 * @param string The juvenile num.
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.setJuvenileNum

	/**
	 *  
	 * @param string The last name.
	 */
	public void setLastName(String string)
	{
		lastName = string;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.setLastName

	/**
	 *  
	 * @param string The middle name.
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.setMiddleName

	/**
	 *  
	 * @param string The race id.
	 */
	public void setRaceId(String string)
	{
		raceId = string;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.setRaceId

	/**
	 *  
	 * @param string The search type.
	 */
	public void setSearchType(String string)
	{
		searchType = string;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.setSearchType

	/**
	 *  
	 * @param string The sex id.
	 */
	public void setSexId(String string)
	{
		sexId = string;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.setSexId

	/**
	 *  
	 * @param string The status id.
	 */
	public void setStatusId(String string)
	{
		statusId = string;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.setStatusId

	/**
	 *  
	 * @param string The supervision number.
	 */
	public void setSupervisionNumber(String string)
	{
		supervisionNumber = string;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.setSupervisionNumber

	
	/**
	 * @return the juvenileDOB
	 */
	public String getJuvenileDOB() {
		return juvenileDOB;
	}

	/**
	 * @param juvenileDOB the juvenileDOB to set
	 */
	public void setJuvenileDOB(String date) {
			this.juvenileDOB = date;
//	public void setJuvenileDOB(String juvenileDOB) {
//		this.juvenileDOB = juvenileDOB;
	}

	/**
	 * Returns the collection code response events for the search types
	 *  
	 * @return  The search types.
	 */
	public Collection getSearchTypes()
	{
		return CodeHelper.getJuvenileProfileSearchTypes();
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getSearchTypes

	/**
	 * Returns the collection code response events for the race types
	 *  
	 * @return  The races.
	 */
	public Collection getRaces()
	{
		return CodeHelper.getRaces();
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getRaces

	/**
	 * Returns the collection code response events for the sex types
	 *  
	 * @return  The sexes.
	 */
	public Collection getSexes()
	{
		return CodeHelper.getJJSSexCodes();
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getSexes

	/**
	 * Returns the collection code response events for the juvenile status types
	 *  
	 * @return  The statuses.
	 */
	public Collection getStatuses()
	{
		return CodeHelper.getJuvenileCaseStatuses();
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getStatuses

	/**
	 *  
	 * @return  The juvenile profiles.
	 */
	public Collection getJuvenileProfiles()
	{
		return juvenileProfiles;
	} //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getJuvenileProfiles

	/**
	 *  
	 * @param collection The juvenile profiles.
	 */
	public void setJuvenileProfiles(Collection collection)
	{
		juvenileProfiles = collection;
	}

	/**
	 * @return
	 */
	public IEvent createSearchJuvenileProfileEvent()
	{
		SearchJuvenileProfilesEvent searchEvent = (SearchJuvenileProfilesEvent) 
				EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILES);

		if( this.getSearchType().equals(PDJuvenileCaseConstants.SEARCH_JUVENILE_NAME) )
		{
			// "alias" string: include alias records  
			searchEvent.setSearchType( "alias" ) ; 

			searchEvent.setAlienNumber(this.getAlienNumber());
			if( notNullNotEmptyString( this.getDateOfBirth() ) )
			{
				searchEvent.setDateOfBirth(DateUtil.stringToDate(this.getDateOfBirth(), UIConstants.DATE_FMT_1));
			}
			if( StringUtils.isNotEmpty(this.getFirstName())) {
			    
			    String lastName = this.getFirstName().replaceAll("'", "''");
			    searchEvent.setFirstName( lastName );
			}
			    
			if( StringUtils.isNotEmpty(this.getMiddleName())){
			    
			    String middleName = this.getMiddleName().replaceAll("'", "''");
			    searchEvent.setMiddleName(middleName);
			}			
			
			if( StringUtils.isNotEmpty(this.getLastName())){
			    
			    String lastName = this.getLastName().replaceAll("'", "''");
			    searchEvent.setLastName(lastName);
			}
			
			searchEvent.setSexId(this.getSexId());
			searchEvent.setStatusId(this.getStatusId());
			searchEvent.setRaceId(this.getRaceId());
		}
		if( this.getSearchType().equals(PDJuvenileCaseConstants.SEARCH_JUVENILE_NUMBER) )
		{
			searchEvent.setJuvenileNum(this.getJuvenileNum());
		}
		if( this.getSearchType().equals(PDJuvenileCaseConstants.SEARCH_DATE_OF_BIRTH) )
		{
			//searchEvent.setLastName("*"); not sure why ? May m204 needed? TODO recheck with regina or uma.70830
			searchEvent.setDateOfBirth(DateUtil.stringToDate(this.getJuvenileDOB(), UIConstants.DATE_FMT_1));
		}
		//add ssn as a search parameter
		if( this.getSearchType().equals(PDJuvenileCaseConstants.SEARCH_JUVENILE_SSN) )
		{
			searchEvent.setSsn(this.getSsn().getSSN());
		}
		//task 171828
		if( this.getSearchType().equals(PDJuvenileCaseConstants.SEARCH_JUVENILE_DALOG) )
		{
			searchEvent.setDalogNum(this.getDalogNum());
		}
		

		return searchEvent;
	}
	
	/*
	 * given a String, return true if it's not null and not empty
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  (str.length() > 0) ) ; 
	}

	/**
	 * @param action the action to set
	 */
	public void setFacilityAction(String facilityAction) {
		this.facilityAction = facilityAction;
	}

	/**
	 * @return the action
	 */
	public String getFacilityAction() {
		return facilityAction;
	}

	/**
	 * @return the ssn
	 */
	public SocialSecurity getSsn() {
		return ssn;
	}

	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(SocialSecurity ssn) {
		this.ssn = ssn;
	}

	public String getRestrictedAccessFeature() {
		return restrictedAccessFeature;
	}

	public void setRestrictedAccessFeature(String restrictedAccessFeature) {
		this.restrictedAccessFeature = restrictedAccessFeature;
	}

	/**
	 * @return the referralAction
	 */
	public String getReferralAction()
	{
	    return referralAction;
	}

	/**
	 * @param referralAction the referralAction to set
	 */
	public void setReferralAction(String referralAction)
	{
	    this.referralAction = referralAction;
	}
	public String getDalogNum()
	{
	    return dalogNum;
	}

	public void setDalogNum(String dalogNum)
	{
	    this.dalogNum = dalogNum;
	}
} // end JuvenileProfileSearchForm

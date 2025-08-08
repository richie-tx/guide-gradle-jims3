/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.RequestEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetFamilyMembersEvent extends RequestEvent
{

	private String memberLastName;
	private String memberFirstName;
	private String memberMiddleName;
	private String memberSexId;
	private String memberSsn;
	private Date   memberDateOfBirth;
	private String juvenileNumber;
	private boolean isEqualNameSearch;
	private List  associatedJuveniles;
	private boolean loadAssocatiedJuveniles;

	/**
	 * @return
	 */
	public Date getMemberDateOfBirth()
	{
		return memberDateOfBirth;
	}

	/**
	 * @return
	 */
	public String getMemberFirstName()
	{
		return memberFirstName;
	}

	/**
	 * @return
	 */
	public String getMemberLastName()
	{
		return memberLastName;
	}

	/**
	 * @return
	 */
	public String getMemberMiddleName()
	{
		return memberMiddleName;
	}

	/**
	 * @return
	 */
	public String getMemberSexId()
	{
		return memberSexId;
	}

	/**
	 * @return
	 */
	public String getMemberSsn()
	{
		return memberSsn;
	}


	/**
	 * @param date
	 */
	public void setMemberDateOfBirth(Date date)
	{
		memberDateOfBirth = date;
	}

	/**
	 * @param string
	 */
	public void setMemberFirstName(String string)
	{
		memberFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setMemberLastName(String string)
	{
		memberLastName = string;
	}

	/**
	 * @param string
	 */
	public void setMemberMiddleName(String string)
	{
		memberMiddleName = string;
	}

	/**
	 * @param string
	 */
	public void setMemberSexId(String string)
	{
		memberSexId = string;
	}

	/**
	 * @param string
	 */
	public void setMemberSsn(String string)
	{
		memberSsn = string;
	}

	/**
	 * @return
	 */
	public String getJuvenileNumber()
	{
		return juvenileNumber;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNumber(String string)
	{
		juvenileNumber = string;
	}

	/**
	 * @return the associatedJuveniles
	 */
	public List getAssociatedJuveniles() {
		return associatedJuveniles;
	}

	/**
	 * @param associatedJuveniles the associatedJuveniles to set
	 */
	public void setAssociatedJuveniles(List associatedJuveniles) {
		this.associatedJuveniles = associatedJuveniles;
	}

	/**
	 * @return the loadAssocatiedJuveniles
	 */
	public boolean isLoadAssocatiedJuveniles() {
		return loadAssocatiedJuveniles;
	}

	/**
	 * @param loadAssocatiedJuveniles the loadAssocatiedJuveniles to set
	 */
	public void setLoadAssocatiedJuveniles(boolean loadAssocatiedJuveniles) {
		this.loadAssocatiedJuveniles = loadAssocatiedJuveniles;
	}

	public void setEqualNameSearch(boolean isEqualNameSearch) {
		this.isEqualNameSearch = isEqualNameSearch;
	}

	public boolean isEqualNameSearch() {
		return isEqualNameSearch;
	}

}

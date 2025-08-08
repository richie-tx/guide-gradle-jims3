package pd.contact;

import java.util.Iterator;

import naming.PDCodeTableConstants;

import messaging.administercompliance.reply.NCEmploymentResponseEvent;
import mojo.km.context.multidatasource.ObjectLockedException;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import pd.address.Address;
import pd.codetable.Code;

/**
* @author dgibler
*/
public class Employer extends PersistentObject
{
	private String occupation;
	/**
	* Properties for Employment Status
	* @referencedType pd.codetable.Code
	* @contextKey EMPLOYMENT_STATUS
	*/
	private Code employmentStatus = null;
	private String employerId;
	private String employmentStatusId;
	private String addressId;
	/**
	* Properties for address
	* @useParent true
	* @referencedType pd.address.Address
	*/
	private Address address = null;
	private String partyId;
	private String employerName;
	private String currentEmployerInd;
	private String phoneNum;
	private String seqNum;
	/**
	* @roseuid 419A2465031C
	*/
	public Employer()
	{
	}
	/**
	* @roseuid 41B084C203D9
	*/
	public void bind()
	{
		markModified();
	}
	
	/**
	* @roseuid 41B084C203D9
	*/
	public static Iterator findAll(IEvent event)
	{
		return new Home().findAll(event, Employer.class);
	}
	/**
	* Gets referenced type pd.address.Address
	* @return pd.address.Address
	*/
	public Address getAddress()
	{
		initAddress();
		return address;
	}
	/**
	* Get the reference value to class :: pd.address.Address
	* @return java.lang.String
	*/
	public String getAddressId()
	{
		fetch();
		return addressId;
	}
	/**
	* @return java.lang.String
	* @roseuid 41B07FDC037B
	*/
	public String getCurrentEmployerInd()
	{
		fetch();
		return currentEmployerInd;
	}
	/**
	* Get the reference value to class :: pd.contact.party.Party
	* @return java.lang.String
	* @roseuid 419A2DFF02DE
	*/
	public String getEmployerId()
	{
		return "" + getOID();
	}
	/**
	* Access method for the employerName property.
	* @return the current value of the employerName property
	*/
	public String getEmployerName()
	{
		fetch();
		return employerName;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return pd.codetable.Code
	* @roseuid 41B07FDC0292
	*/
	public Code getEmploymentStatus()
	{
		fetch();
		initEmploymentStatus();
		return employmentStatus;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return java.lang.String
	* @roseuid 419A2D1C038A
	*/
	public String getEmploymentStatusId()
	{
		fetch();
		return employmentStatusId;
	}
	/**
	* @return java.lang.String
	* @roseuid 41B07FDC035C
	*/
	public String getPartyId()
	{
		fetch();
		return partyId;
	}
	/**
	* Access method for the phoneNum property.
	* @return the current value of the phoneNum property
	*/
	public String getPhoneNum()
	{
		fetch();
		return phoneNum;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	* @roseuid 419A2D1C039A
	*/
	private void initEmploymentStatus()
	{
		if (employmentStatus == null)
		{
			try
			{
				employmentStatus =
					(Code) new mojo
						.km
						.persistence
						.Reference(
							employmentStatusId,
							Code.class,
							PDCodeTableConstants.EMPLOYMENT_STATUS)
						.getObject();
			}
			catch (Throwable t)
			{
				employmentStatus = null;
			}
		}
	}
	/**
	* set the type reference for class member address
	* @param address
	*/
	public void setAddress(Address anAddress)
	{
		if (this.address == null || !this.address.equals(address))
		{
			markModified();
		}
		this.address = anAddress;
	}
	/**
	* Set the reference value to class :: pd.address.Address
	*/
	public void setAddressId(String anAddressId)
	{
		if (this.addressId == null || !this.addressId.equals(addressId))
		{
			markModified();
		}
		address = null;
		this.addressId = anAddressId;
	}
	/**
	* @param anEmployerInd
	* @roseuid 41B07FDC038B
	*/
	public void setCurrentEmployerInd(String anEmployerInd)
	{
		if (this.currentEmployerInd == null
			|| !this.currentEmployerInd.equals(anEmployerInd))
		{
			markModified();
		}
		currentEmployerInd = anEmployerInd;
	}
	/**
	* Set the reference value to class :: pd.contact.party.Party
	* @param aEmployerId
	* @roseuid 419A2DFF02CE
	*/
	public void setEmployerId(String aEmployerId)
	{
		if (this.employerId == null || !this.employerId.equals(aEmployerId))
		{
			try
			{
				markModified();
			}
			catch (ObjectLockedException e)
			{
				return;
			}
		}
		employerId = aEmployerId;
	}
	/**
	* Sets the value of the employerName property.
	* @param aEmployerName the new value of the employerName property
	*/
	public void setEmployerName(String aEmployerName)
	{
		if (this.employerName == null || !this.employerName.equals(aEmployerName))
		{
			try
			{
				markModified();
			}
			catch (ObjectLockedException e)
			{
				return;
			}
		}
		employerName = aEmployerName;
	}
	/**
	* set the type reference for class member employmentStatus
	* @param aEmploymentStatus
	* @roseuid 41B07FDC030E
	*/
	public void setEmploymentStatus(Code aEmploymentStatus)
	{
		if (this.employmentStatus == null
			|| !this.employmentStatus.equals(aEmploymentStatus))
		{
			try
			{
				markModified();
			}
			catch (ObjectLockedException e)
			{
				return;
			}
		}
		if (employmentStatus.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(employmentStatus);
		}
		setEmploymentStatusId("" + employmentStatus.getOID());
		this.employmentStatus =
			(Code) new mojo
				.km
				.persistence
				.Reference(employmentStatus)
				.getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param aEmploymentStatusId
	* @roseuid 419A2D1D000F
	*/
	public void setEmploymentStatusId(String aEmploymentStatusId)
	{
		if (this.employmentStatusId == null
			|| !this.employmentStatusId.equals(aEmploymentStatusId))
		{
			try
			{
				markModified();
			}
			catch (ObjectLockedException e)
			{
				return;
			}
		}
		employmentStatus = null;
		this.employmentStatusId = aEmploymentStatusId;
	}
	/**
	* @param aPartyId
	* @roseuid 41B07FDC036C
	*/
	public void setPartyId(String aPartyId)
	{
		if (this.partyId == null || !this.partyId.equals(aPartyId))
		{
			markModified();
		}
		partyId = aPartyId;
	}
	/**
	* Sets the value of the phoneNum property.
	* @param aPhoneNum the new value of the phoneNum property
	*/
	public void setPhoneNum(String aPhoneNum)
	{
		if (this.phoneNum == null || !this.phoneNum.equals(aPhoneNum))
		{
			try
			{
				markModified();
			}
			catch (ObjectLockedException e)
			{
				return;
			}
		}
		phoneNum = aPhoneNum;
	}
	/**
	* @return java.lang.String
	*/
	public String getOccupation()
	{
		fetch();
		return occupation;
	}
	/**
	* @param aOccupation
	*/
	public void setOccupation(String aOccupation)
	{
		if (this.occupation == null || !this.occupation.equals(aOccupation))
		{
			markModified();
		}
		occupation = aOccupation;
	}
	/**
	* Initialize class relationship to class pd.address.Address
	*/
	private void initAddress()
	{
		if (address == null)
		{
			try 
			{
				address =
					(Address) new mojo
						.km
						.persistence
						.Reference(
							partyId,
							Address.class,
							(mojo.km.persistence.PersistentObject) this,
							"employerAddress")
						.getObject();
			}
			catch (Throwable t)
			{
				address = null;
			}
		}
	}
	
	
	public String getSeqNum() {
		fetch();
		return seqNum;
	}
	
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
	/**
	 * @return
	 */
	public NCEmploymentResponseEvent getResponse() {
		NCEmploymentResponseEvent resp = new NCEmploymentResponseEvent();
		resp.setEmployerName(this.getEmployerName());
		resp.setJobTitle(this.getOccupation());
		resp.setStatusId(this.getEmploymentStatusId());
		resp.setEmploymentId(this.getOID());
		resp.setStatusDesc(this.getEmploymentStatus().getDescription());
		resp.setSeqNum( this.getSeqNum() );
		return resp;
	}
}

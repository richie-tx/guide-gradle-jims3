/*
 * Created on Feb 17, 2006
 */
package messaging.supervisionorder.reply;

import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.Name;

/**
 * @author dgibler
 *
 */
public class MagistrateResponseEvent extends ResponseEvent implements Comparable
{
	private String firstName;
	private String lastName;
	private String middleName;
	private String magistrateId;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		MagistrateResponseEvent mre = (MagistrateResponseEvent) arg0;
		int comparisonResult = 0;

		this.initializeNullAttributes(mre);

		if (this.getLastName().compareTo(mre.getLastName()) == 0)
		{
			if (this.getFirstName().compareTo(mre.getFirstName()) == 0)
			{
				comparisonResult = this.getMiddleName().compareTo(mre.getMiddleName());
			}
			else
			{
				comparisonResult = this.getFirstName().compareTo(mre.getFirstName());
			}
		}
		else
		{
			comparisonResult = this.getLastName().compareTo(mre.getLastName());
		}

		return comparisonResult;
	}

	/**
	 * @param mre
	 */
	private void initializeNullAttributes(MagistrateResponseEvent mre)
	{
		String newString = "";
		if (this.getFirstName() == null)
		{
			this.setFirstName(newString);
		}
		if (this.getLastName() == null)
		{
			this.setLastName(newString);
		}
		if (this.getMiddleName() == null)
		{
			this.setMiddleName(newString);
		}
		if (mre.getFirstName() == null)
		{
			mre.setFirstName(newString);
		}
		if (mre.getLastName() == null)
		{
			mre.setLastName(newString);
		}
		if (mre.getMiddleName() == null)
		{
			mre.setMiddleName(newString);
		}

	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @param aFirstName
	 */
	public void setFirstName(String aFirstName)
	{
		firstName = aFirstName;
	}

	/**
	 * @param aLastName
	 */
	public void setLastName(String aLastName)
	{
		lastName = aLastName;
	}

	/**
	 * @param aMiddleName
	 */
	public void setMiddleName(String aMiddleName)
	{
		middleName = aMiddleName;
	}

	/**
	 * @return
	 */
	public String getMagistrateId()
	{
		return magistrateId;
	}

	/**
	 * @param string
	 */
	public void setMagistrateId(String string)
	{
		magistrateId = string;
	}
	/**
	 * @return
	 */
	public String getFormattedName()
	{
		Name fullName = new Name(this.firstName, this.middleName, this.lastName);
		return (fullName.getFormattedName());
	}
}

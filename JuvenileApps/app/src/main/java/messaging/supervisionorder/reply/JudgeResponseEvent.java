/*
 * Created on Feb 17, 2006
 *
 */
package messaging.supervisionorder.reply;

import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.Name;

/**
 * @author dgibler
 * This response event was created so that 
 */
public class JudgeResponseEvent extends ResponseEvent implements Comparable
{
	private String courtId;
	private String firstName;
	private String lastName;
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		JudgeResponseEvent jre = (JudgeResponseEvent) arg0;

		this.initializeNullAttributes(this, jre);

		int comparisonResult = 0;

		if (this.getLastName().compareTo(jre.getLastName()) == 0)
		{
			comparisonResult = this.getFirstName().compareTo(jre.getFirstName());
		}
		else
		{
			comparisonResult = this.getLastName().compareTo(jre.getLastName());
		}

		return comparisonResult;
	}

	/**
	 * @param event
	 * @param jre
	 */
	private void initializeNullAttributes(JudgeResponseEvent jre1, JudgeResponseEvent jre2)
	{
		String newString = "";
//		if (jre1.getCourtNumber() == null)
//		{
//			jre1.setCourtNumber(newString);
//		}
		if (jre1.getFirstName() == null)
		{
			jre1.setFirstName(newString);
		}
		if (jre1.getLastName() == null)
		{
			jre1.setLastName(newString);
		}
//		if (jre2.getCourtNumber() == null)
//		{
//			jre2.setCourtNumber(newString);
//		}
		if (jre2.getFirstName() == null)
		{
			jre2.setFirstName(newString);
		}
		if (jre2.getLastName() == null)
		{
			jre2.setLastName(newString);
		}

	}

	/**
	 * @return
	 */
	public String getCourtNumber()
	{
		String courtNumber = "";
		if (this.getCourtId() != null){
			if (this.getCourtId().length() > 4){
				courtNumber = courtId.substring(4);
			}
		}
		return courtNumber;
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
	 * @param aJudgeFirstName
	 */
	public void setFirstName(String aJudgeFirstName)
	{
		firstName = aJudgeFirstName;
	}

	/**
	 * @param aJudgeLastName
	 */
	public void setLastName(String aJudgeLastName)
	{
		lastName = aJudgeLastName;
	}
	/**
	 * @return
	 */
	public String getFormattedName()
	{
		Name aName = null;
		String formattedName = null;
		String upperLastName = this.lastName.toUpperCase();
		if (upperLastName.indexOf("NAME NOT ON FILE FOR COURT") < 0)
		{
			aName = new Name(this.firstName, "", this.lastName);
			formattedName = aName.getFormattedName();
		}
		else
		{
			formattedName = this.lastName;
		}
		return (formattedName);
	}

	public String getCourtId() {
		return courtId;
	}

	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
}

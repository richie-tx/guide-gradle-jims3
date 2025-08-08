package pd.codetable.criminal;

import java.util.Iterator;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JuvenileDates extends PersistentObject {

	private String code; //holidayDate
	private String description;
	private String resetDate;
	private String inactiveInd;
	
	public String getDescription() {
		fetch();
		return description;
	}
	public void setDescription(String description) {
		if (this.description == null || !this.description.equals(description))
		{
			markModified();
		}
		this.description = description;
	}
	public String getResetDate() {
		fetch();
		return resetDate;
	}
	public void setResetDate(String resetDate) {
		if (this.resetDate == null || !this.resetDate.equals(resetDate))
		{
			markModified();
		}
		this.resetDate = resetDate;
	}
	public String getInactiveInd() {
		fetch();
		return inactiveInd;
	}
	public void setInactiveInd(String inactiveInd) {
		if (this.inactiveInd == null || !this.inactiveInd.equals(inactiveInd))
		{
			markModified();
		}
		this.inactiveInd = inactiveInd;
	}
	public String getCode() {
		fetch();
		return code;
	}
	public void setCode(String code) {
		if (this.code == null || !this.code.equals(code))
		{
			markModified();
		}
		this.code = code;
	}
	/**
	 * Find all Juvenile Dates.
	 * @return java.util.Iterator
	 */
	public static Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(JuvenileDates.class);
	}
	/**
	* @roseuid 41AC81DE0186
	* @param courtCode
	* @return a Juvenile Dates object
	*/
	public static JuvenileDates find(String holidayDate)
	{
		JuvenileDates jc = null;
		IHome home = new Home();

		jc = (JuvenileDates) home.find(holidayDate, JuvenileDates.class);

		return jc;
	}

	/**
	 * Find all Juvenile Dates.
	 * @return java.util.Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, attributeValue, JuvenileDates.class);
	}
}

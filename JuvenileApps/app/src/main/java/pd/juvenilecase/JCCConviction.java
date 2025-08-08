package pd.juvenilecase;

import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;


/**
* 
*/
public class JCCConviction extends PersistentObject 
{
	private String m204JuvNumber;
	private String caseNumber;
	private String courtId;
	private Code court;
	private int convictionDate;
	private int dispositionDate;
	private int filingDate;
	private String offenseId;
	private String disposition;
	private String offenseDescription;
	
	/**
	 * 
	 */
	public static Iterator findAll( IEvent evt )
	{
		return new Home().findAll( evt, JCCConviction.class );
	}

	
	/**
	 * @return Returns the courtId.
	 */
	public String getCourtId() 
	{
		fetch();
		return courtId;
	}
	
	/**
	 * @param courtId The courtId to set.
	 */
	public void setCourtId(String courtId) 
	{
		this.courtId = courtId;
	}
	
	/**
	 * @return Returns the raceCode.
	 */
	public Code getCourt() {
		fetch();
		initCourt();
		return court;
	}
	
	private void initCourt() {
		if( court == null ) {
			court = (Code)
				new mojo.km.persistence.Reference
				(courtId, Code.class, "JPCOURT").getObject();
		}
	}
	
	
	/**
	 * @return Returns the offenseId.
	 */
	public String getOffenseId() 
	{
		fetch();
		return offenseId;
	}
	
	/**
	 * @param offenseId The offenseId to set.
	 */
	public void setOffenseId(String offenseId) 
	{
		this.offenseId = offenseId;
	}
	
	/**
	 * @return Returns the convictionDate.
	 */
	public int getConvictionDate() 
	{
		fetch();
		return convictionDate;
	}
	
	/**
	 * @param convictionDate The convictionDate to set.
	 */
	public void setConvictionDate(int convictionDate) 
	{
		this.convictionDate = convictionDate;
	}
	
	/**
	 * @return Returns the dispositionDate.
	 */
	public int getDispositionDate() 
	{
		fetch();
		return dispositionDate;
	}
	
	/**
	 * @param dispositionDate The dispositionDate to set.
	 */
	public void setDispositionDate(int dispositionDate) 
	{
		this.dispositionDate = dispositionDate;
	}
	
	/**
	 * @return Returns the filingDate.
	 */
	public int getFilingDate() 
	{
		fetch();
		return filingDate;
	}
	
	/**
	 * @param filingDate The filingDate to set.
	 */
	public void setFilingDate(int filingDate) 
	{
		this.filingDate = filingDate;
	}
	
	/**
	 * @return Returns the m204JuvNumber.
	 */
	public String getM204JuvNumber() {
		fetch();
		return m204JuvNumber;
	}
	/**
	 * @param juvNumber The m204JuvNumber to set.
	 */
	public void setM204JuvNumber(String m204JuvNumber) {
		this.m204JuvNumber = m204JuvNumber;
	}
	/**
	 * @return Returns the caseNumber.
	 */
	public String getCaseNumber() {
		fetch();
		return caseNumber;
	}
	/**
	 * @param caseNumber The caseNumber to set.
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	/**
	 * @return Returns the disposition.
	 */
	public String getDisposition() {
		return disposition;
	}
	/**
	 * @param disposition The disposition to set.
	 */
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	/**
	 * @return Returns the offenseDescription.
	 */
	public String getOffenseDescription() {
		return offenseDescription;
	}
	/**
	 * @param offenseDescription The offenseDescription to set.
	 */
	public void setOffenseDescription(String offenseDescription) {
		this.offenseDescription = offenseDescription;
	}
}

package pd.juvenilecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import messaging.juvenilecase.GetJCCConvictionEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.juvenile.Juvenile;


/**
* 
*/
public class JCCDefendant extends PersistentObject 
{
	private String m204JuvNumber;
	private String firstName;
	private String middleName;
	private String lastName;
	private int dateOfBirth;
	private String raceId;
	private Code race;
	private List convictions = null;
	
	
	/**
	 * @return Returns the raceId.
	 */
	public String getRaceId() {
		fetch();
		return raceId;
	}
	/**
	 * @param raceId The raceId to set.
	 */
	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}
	/**
	 * @return Returns the raceCode.
	 */
	public Code getRace() {
		fetch();
		initRace();
		return race;
	}
	
	private void initRace() {
		if( race == null ) {
			race = (Code)
				new mojo.km.persistence.Reference
				(raceId, Code.class, PDCodeTableConstants.RACE).getObject();
		}
	}
	
	/**
	 * 
	 */
	public static Iterator findAll( IEvent evt )
	{
		return new Home().findAll( evt, JCCDefendant.class );
	}

	/**
	 * 
	 */
	public static JCCDefendant findByJuvenile( Juvenile juvenile )
	{
		return null;
	}

	/**
	 * 
	 * @return A collection of JCCConviction.
	 */
	public Collection getConvictions()
	{
		if ( convictions == null )
		{
			convictions = new ArrayList();
			GetJCCConvictionEvent queryEvt = new GetJCCConvictionEvent(); 
			queryEvt.setM204JuvNumber( m204JuvNumber );
			Iterator iter = JCCConviction.findAll( queryEvt );
			while ( iter.hasNext() )
			{
				convictions.add( iter.next() );
			}
		}
		return convictions; 
	}
	

	
	/**
	 * @return Returns the dateOfBirth.
	 */
	public int getDateOfBirth() 
	{
		fetch();
		return dateOfBirth;
	}
	
	/**
	 * @param dateOfBirth The dateOfBirth to set.
	 */
	public void setDateOfBirth(int dateOfBirth) 
	{
		this.dateOfBirth = dateOfBirth;
	}
	
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() 
	{
		fetch();
		return firstName;
	}
	
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() 
	{
		fetch();
		return lastName;
	}
	
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	
	/**
	 * @return Returns the middleName.
	 */
	public String getMiddleName() 
	{
		fetch();
		return middleName;
	}
	
	/**
	 * @param middleName The middleName to set.
	 */
	public void setMiddleName(String middleName) 
	{
		this.middleName = middleName;
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
}

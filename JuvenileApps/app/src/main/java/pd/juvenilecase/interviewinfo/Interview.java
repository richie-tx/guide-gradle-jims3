package pd.juvenilecase.interviewinfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import messaging.contact.domintf.IAddress;
import messaging.interviewinfo.GetInterviewsByJuvenileEvent;
import messaging.interviewinfo.domintf.IInterviewDetail;
import messaging.interviewinfo.domintf.IInterviewPerson;
import messaging.interviewinfo.domintf.IInterviewSummary;
import messaging.interviewinfo.reply.InterviewPersonResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.address.Address;
import pd.codetable.Code;
import pd.common.calendar.CalendarEventContext;
import pd.juvenilecase.JuvenileCasefile;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

/**
* @roseuid 448EC1ED011F
*/
public class Interview extends PersistentObject implements Comparable
{
	private static final String INTERVIEWTYPE_CODETABLE = "INTERVIEW_TYPE";
	
	/**
	 * Data and time of the interview. The date and time are managed through
	 * separate methods since the time can be update but the date cannot. 
	 */
	private Date interviewDateTime;
	
	/**
	* Properties for inventoryRecords
	* @referencedType pd.juvenilecase.interviewinfo.InterviewInventoryRecord
	*/
	private Collection inventoryRecords = null;
	
	/**
	 *  
	 */
	private String otherInventoryRecords;
	
	/**
	* Properties for interviewPersons
	* @referencedType pd.juvenilecase.interviewinfo.InterviewPerson
	*/
	private Collection interviewPersons = null;
	
	/**
	 * juvLocUnitId is a Code but may be null if a custom address is entered (See below).
	 * If a juvLocUnitId is entered then the custom address id should be null.
	 */	
	private String juvLocUnitId;
	private JuvLocationUnit juvLocUnit;
	
	/**
	* Reference to a custom address if one was entered. Entering a custom address
	* requires that the locationId is null.
	*/
	private String customAddressId;
	private Address customAddress = null;
	
	/**
	 * Indicates that the custom address has been validated and is valid. False
	 * may indicate that the address has not been validated.
	 */
	private boolean isCustomAddressValid;
	
	/**
	 * Indicates that a checklist has been generated. This will be true in two conditions:
	 * 
	 *	1. 	A task list was generated in response to interview questions.
	 * 
	 *	2. 	A 'Classic' interview was chosen, thus the empty checklist 
	 * 		indicates a completed interview.   
	 */
	//private boolean hasChecklist;
	
	/**
	* Properties for interviewType
	*/
	private Code interviewType = null;
	private String interviewTypeId;
	
	private Code interviewStatus;
	private String interviewStatusCd;
	
	
	/**
	 * 
	 */
	private String casefileId;
	private JuvenileCasefile casefile;
	
	/**
	 * 
	 */
	private String summaryNotes;
	
	/**
	 * 
	 */
	private Collection tasks = null;
	
	private String calEventId;
	
	
	/**
	 *
	 */
	public static Interview find( String interviewId )
	{
		return (Interview)new Home().find( interviewId, Interview.class );
	}
	
	/**
	 *
	 */
	public static Iterator findAllForJuvenile( String juvenileId )
	{
		GetInterviewsByJuvenileEvent evt = new GetInterviewsByJuvenileEvent();
		evt.setJuvenileId( juvenileId );
		return new Home().findAll( evt, Interview.class );
	}

	/**
	 *
	 */
	public static Iterator findAllForCasefile( String casefileId )
	{
		return new Home().findAll( "casefileId", casefileId, Interview.class );
	}
	
	public static Iterator findByCalEventId( String calEventId )
	{
		return new Home().findAll( "calEventId", calEventId, Interview.class );
	}
	
	/**
	 * Does a find by attribute name/value.  Convenience method
	 * @param attributeName
	 * @param attributeValue
	 * @return iterator
	 */
	public static Iterator findAll(String attributeName, Object attributeValue) 
	{
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue, Interview.class);
	}

	/**
	 *
	 */
	public static Interview findLastForCasefile( String casefileId )
	{
		Iterator iter = new Home().findAll( "casefileId", casefileId, Interview.class );
		List list = new ArrayList();
		while ( iter.hasNext() )
		{
			list.add( iter.next() );
		}
		if ( list.size() > 0 )
		{
			Collections.sort( list );
			return (Interview)list.get( list.size()-1 );
		}
		return null;
	}

	/**
	* @roseuid 448EC1ED011F
	*/
	public Interview() 
	{
	}

	/**
	 * 
	 */
	public int compareTo( Object other )
	{
		if ( other == null )
		{
			return 1;
		}
		return compareTo( (Interview)other );
	}
	
	/**
	 * 
	 */
	public int compareTo( Interview other )
	{
		if ( getInterviewDateTime() == null )
		{
			if ( other.getInterviewDateTime() == null )
			{
				return 0;
			}
			return -1;
		}
		else
		{
			if ( other.getInterviewDateTime() == null )
			{
				return 1;
			}
			return getInterviewDateTime().compareTo(other.getInterviewDateTime());
		}
	}
	
	/**
	 * createDetail() directly sets all attributes that are non-modifiable after
	 * the initial creation. updateDetail() is called to set all other attributes.
	 */
	public static Interview createDetail( IInterviewDetail detail )
	{
		Interview interview = new Interview();

		interview.setCasefileId( detail.getCasefileId() );
				
		interview.setInterviewDate( detail.getInterviewDate() );
		interview.setInterviewTypeId( detail.getInterviewTypeId() );
		interview.setInterviewStatusCd(detail.getInterviewStatusCd());
				
		Iterator persons = detail.getInterviewPersons().iterator();
		while ( persons.hasNext() )
		{
			IInterviewPerson detailPerson = (IInterviewPerson)persons.next();
			
			InterviewPerson person = new InterviewPerson();
			person.setFirstName( detailPerson.getFirstName() );
			person.setMiddleName( detailPerson.getMiddleName() );
			person.setLastName( detailPerson.getLastName() );
			interview.insertInterviewPersons( person );
		}

		interview.setSummaryNotes( detail.getSummaryNotes() );
		interview.setCalEventId( detail.getCalEventId() );
		interview.updateDetail( detail );
		
		return interview;
	}
	
	
	public void updateInterviewInventory(IInterviewDetail detail ){
		setOtherInventoryRecords( detail.getOtherInventoryRecords() );
		updateInventoryRecords( detail.getInventoryRecordsIds() );
	}
	/**
	 *
	 */
	public void updateDetail( IInterviewDetail detail )
	{
		setInterviewTime( detail.getInterviewDate() );
		setOtherInventoryRecords( detail.getOtherInventoryRecords() );
		setSummaryNotes( detail.getSummaryNotes() );
		
		setJuvLocUnitId(detail.getJuvLocUnitId());
		if ( detail.getAddress() != null )
		{
			updateCustomAddress( detail.getAddress() );
		}
		else
		{
			setCustomAddressId(null);
		}

		updateInventoryRecords( detail.getInventoryRecordsIds() );
		if(detail.getInterviewStatusCd()!=null && !"".equals(detail.getInterviewStatusCd())) {
			setInterviewStatusCd(detail.getInterviewStatusCd());
		}
		//should not be able to update event that is related to this interview
		//setCalEventId( detail.getCalEventId() );

	}
	
	/**
	 *
	 */
	public void fillSummary( IInterviewSummary summary )
	{
		summary.setCasefileId( getCasefileId() );
		summary.setInterviewDate( getInterviewDate() );
		summary.setInterviewId( getOID().toString() );
		summary.setInterviewTypeId( getInterviewTypeId() );
		summary.setLocationDescription( getLocationDescription() );
		if (this.getInterviewStatusCd()!=null && !"".equals(this.getInterviewStatusCd())){
			summary.setInterviewStatusCd(this.getInterviewStatusCd());
			summary.setInterviewStatusDescription(this.getInterviewStatus().getDescription());
		}
		
		summary.setSummaryNotes(this.getSummaryNotes());
		summary.setCalEventId(this.getCalEventId());
	}
	
	/**
	 *
	 */
	public void fillDetail( IInterviewDetail detail )
	{
		fillSummary( detail );
		
		detail.setJuvLocUnitId(getJuvLocUnitId());
		detail.setOtherInventoryRecords( getOtherInventoryRecords() );
		detail.setSummaryNotes( getSummaryNotes() );
		if (this.getInterviewStatusCd()!=null && !"".equals(this.getInterviewStatusCd())){
			detail.setInterviewStatusCd(this.getInterviewStatusCd());
			detail.setInterviewStatusDescription(this.getInterviewStatus().getDescription());
		}
		

		Iterator records = getInventoryRecords().iterator();
		while ( records.hasNext() )
		{
			detail.getInventoryRecordsIds().add( ((InterviewInventoryRecord)records.next()).getRecordsInventoryId() );
		}
		
		Iterator persons = getInterviewPersons().iterator();
		while ( persons.hasNext() )
		{
			InterviewPerson person = (InterviewPerson)persons.next();
			InterviewPersonResponseEvent personEvt = new InterviewPersonResponseEvent();
			
			person.fill( personEvt );
			detail.getInterviewPersons().add( personEvt );
		}
		
		if ( detail.getAddress() != null && getCustomAddressId() != null )
		{
			getCustomAddress().fillAddress( detail.getAddress() );
		}
	}
	
	/**
	* @roseuid 448D7EED028D
	*/
/*	public void complete() 
	{
		if ( hasChecklist() )
		{
			Iterator taskIter = getTasks().iterator();
			while ( taskIter.hasNext() )
			{
				InterviewTask task = (InterviewTask)taskIter.next();
				if ( ! task.completed() )
				{
					task.complete();
					break;
				}
			}
		}
		else
		{
			setHasChecklist(true);
		}
	}
*/	
	/**
	 * Create the task for the Interview. Task may only be created once. 
	 */
	public Collection createTasks( Collection questions )
	{
		
		String juvId = getCasefile().getJuvenileId();
		
		Iterator questionIter = questions.iterator();
		while ( questionIter.hasNext() )
		{
			InterviewQuestion question = (InterviewQuestion)questionIter.next();
			getTasks().addAll( question.createTasks( getCasefileId(), juvId, getTasks() ) );
		}
				
		return getTasks();
	}

	/**
	* 
	*/
	public void completeTask( String taskId ) 
	{
		Iterator taskIter = getTasks().iterator();
		while ( taskIter.hasNext() )
		{
			InterviewTask task = (InterviewTask)taskIter.next();
			if ( task.getOID().toString().equals(taskId) )
			{
				task.complete();
				break;
			}
		}
	}
	
	/**
	* 
	*/
/*	public boolean completed() 
	{
		if ( ! hasChecklist() )
		{
			return false;
		}
		
		Iterator taskIter = getTasks().iterator();
		while ( taskIter.hasNext() )
		{
			InterviewTask task = (InterviewTask)taskIter.next();
			if ( ! task.completed() )
			{
				return false;
			}
		}
		return true;
	}
*/	
	
	/**
	* Initialize class relationship implementation for pd.juvenilecase.interviewinfo.InterviewInventoryRecord
	*/
	private void initInventoryRecords() 
	{
		if (inventoryRecords == null) 
		{
			if (this.getOID() == null) 
			{
				new mojo.km.persistence.Home().bind(this);
			}
			inventoryRecords = new mojo.km.persistence.ArrayList(InterviewInventoryRecord.class, "interviewId", "" + getOID());
		}
	}
	
	/**
	* returns a collection of pd.juvenilecase.interviewinfo.InterviewInventoryRecord
	*/
	public Collection getInventoryRecords()
	{
		initInventoryRecords();
		return inventoryRecords;
	}
	
	/**
	* insert a pd.juvenilecase.interviewinfo.InterviewInventoryRecord into class relationship collection.
	*/
	public void insertInventoryRecords(InterviewInventoryRecord anObject)
	{
		initInventoryRecords();
		inventoryRecords.add(anObject);
	}
	
	/**
	* Removes a pd.juvenilecase.interviewinfo.InterviewInventoryRecord from class relationship collection.
	*/
	public void removeInventoryRecords(InterviewInventoryRecord anObject)
	{
		initInventoryRecords();
		inventoryRecords.remove(anObject);
	}
	
	/**
	* Clears all pd.juvenilecase.interviewinfo.InterviewInventoryRecord from class relationship collection.
	*/
	public void clearInventoryRecords() 
	{
		initInventoryRecords();
		ArrayList tmp = new ArrayList(inventoryRecords);
		Iterator iter = tmp.iterator();
		while ( iter.hasNext() )
		{
			inventoryRecords.remove(iter.next());
		}
	}

	/**
	 * 
	 */
	public void updateInventoryRecords( List ids )
	{
		clearInventoryRecords();
		Iterator idIter = ids.iterator();
		while ( idIter.hasNext() )
		{
			String id = (String)idIter.next();
			InterviewInventoryRecord rec = new InterviewInventoryRecord();
			rec.setRecordsInventoryId( id );
			insertInventoryRecords( rec );
		}
	}

	/**
	 *  
	 */
	public String getOtherInventoryRecords()
	{
		fetch();
		return otherInventoryRecords;
	}

	/**
	 *  
	 */
	public void setOtherInventoryRecords( String inventoryRecords )
	{
		if ( otherInventoryRecords == null || ! otherInventoryRecords.equals(inventoryRecords) ) 
		{
			markModified();
			otherInventoryRecords = inventoryRecords;
		}
	}
	
	/**
	* Initialize class relationship implementation for pd.juvenilecase.interviewinfo.InterviewPerson
	*/
	private void initInterviewPersons() 
	{
		if (interviewPersons == null) 
		{
			if (this.getOID() == null) 
			{
				new mojo.km.persistence.Home().bind(this);
			}
			interviewPersons = new mojo.km.persistence.ArrayList(InterviewPerson.class, "interviewId", "" + getOID());
		}
	}
	
	/**
	* returns a collection of pd.juvenilecase.interviewinfo.InterviewPerson
	*/
	public Collection getInterviewPersons()
	{
		initInterviewPersons();
		return interviewPersons;
	}
	
	/**
	* insert a pd.juvenilecase.interviewinfo.InterviewPerson into class relationship collection.
	*/
	public void insertInterviewPersons(InterviewPerson anObject)
	{
		initInterviewPersons();
		interviewPersons.add(anObject);
	}
	
	/**
	* Removes a pd.juvenilecase.interviewinfo.InterviewPerson from class relationship collection.
	*/
	public void removeInterviewPersons(InterviewPerson anObject)
	{
		initInterviewPersons();
		interviewPersons.remove(anObject);
	}
	
	/**
	* Clears all pd.juvenilecase.interviewinfo.InterviewPerson from class relationship collection.
	*/
	public void clearInterviewPersons() 
	{
		initInterviewPersons();
		interviewPersons.clear();
	}
	
	/**
	 * For internal and persistence use only.
	 * 
	 * Once the address is created it should not be reset, the interview should always 
	 * reference the same address. To change the address, the address values themselves 
	 * should be modified. If the Location is set then if will opverride the address but the 
	 * address refernce will remain.   
	 */
	public void setCustomAddressId(String customInterviewAddressId) 
	{
		if (this.customAddressId == null || !this.customAddressId.equals(customInterviewAddressId)) 
		{
			markModified();
			customAddress = null;
			this.customAddressId = customInterviewAddressId;
		}
	}
	
	/**
	* Get the reference value to class :: pd.address.Address
	*/
	public String getCustomAddressId() 
	{
		fetch();
		return customAddressId;
	}
	
	/**
	* Initialize class relationship to class pd.address.Address
	*/
	private void initCustomAddress() 
	{
		if ( customAddress == null && customAddressId != null ) 
		{
			customAddress = (Address) new mojo.km.persistence.Reference(customAddressId, Address.class).getObject();
		}
	}
	
	/**
	* Gets referenced type pd.address.Address
	*/
	public Address getCustomAddress()
	{
		initCustomAddress();
		return customAddress;
	}
	
	/**
	* set the type reference for class member customInterviewAddress
	*/
	public void updateCustomAddress( IAddress customInterviewAddress ) 
	{
		if ( customAddressId == null ) 
		{
			Address newAddr = new Address();
			new Home().bind(newAddr);
			setCustomAddressId( newAddr.getOID() );
		}
		 
		getCustomAddress().updateAddress( customInterviewAddress );
	}

	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setInterviewStatusCd(String interviewStatusCd) 
	{
		if (this.interviewStatusCd == null || !this.interviewStatusCd.equals(interviewStatusCd)) {
			markModified();
		}
		this.interviewStatusCd = interviewStatusCd;
	}
	
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getInterviewStatusCd() 
	{
		fetch();
		return interviewStatusCd;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initInterviewStatus() 
	{
		if (interviewStatus == null) {
			interviewStatus = (Code) new mojo.km.persistence.Reference(interviewStatusCd, Code.class, "INTERVIEW_STATUS").getObject();
		}
	}
		
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getInterviewStatus()
	{
		initInterviewStatus();
		return interviewStatus;
	}
	
	
	/**
	* set the type reference for class member interviewStatus
	*/
	public void setInterviewStatus(Code interviewStatus)
	{
		if (this.interviewStatus == null || !this.interviewStatus.equals(interviewStatus)) {
			markModified();
		}
		if (interviewStatus.getOID() == null) {
			new mojo.km.persistence.Home().bind(interviewStatus);
		}
		setInterviewStatusCd("" + interviewStatus.getOID());
		this.interviewStatus = (Code) new mojo.km.persistence.Reference(interviewStatus).getObject();
	}
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setInterviewTypeId(String interviewTypeId) 
	{
		if (this.interviewTypeId == null || !this.interviewTypeId.equals(interviewTypeId)) {
			markModified();
		}
		interviewType = null;
		this.interviewTypeId = interviewTypeId;
	}
	
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getInterviewTypeId() 
	{
		fetch();
		return interviewTypeId;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initInterviewType() 
	{
		if (interviewType == null) {
			interviewType = (Code) new mojo.km.persistence.Reference(interviewTypeId, Code.class, INTERVIEWTYPE_CODETABLE).getObject();
		}
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getInterviewType()
	{
		initInterviewType();
		return interviewType;
	}
	
	/**
	* set the type reference for class member interviewType
	*/
	public void setInterviewType(Code interviewType)
	{
		if (this.interviewType == null || !this.interviewType.equals(interviewType)) {
			markModified();
		}
		if (interviewType.getOID() == null) {
			new mojo.km.persistence.Home().bind(interviewType);
		}
		setInterviewTypeId("" + interviewType.getOID());
		this.interviewType = (Code) new mojo.km.persistence.Reference(interviewType).getObject();
	}
	
	/**
	 * 
	 */
	public String getSummaryNotes()
	{
		fetch();
		return summaryNotes;
	}
	
	/**
	 * 
	 */
	public void setSummaryNotes( String notes )
	{
		if ( summaryNotes == null || ! summaryNotes.equals(notes) ) 
		{
			markModified();
			summaryNotes = notes;
		}
	}
	
	/**
	 * 
	 */
	public String getJuvLocUnitId()
	{
		fetch();
		return juvLocUnitId;
	}
	
	/**
	 * @param string
	 */
	public void setJuvLocUnitId(String string)
	{
		if ( juvLocUnitId == null || ! juvLocUnitId.equals(string) ) 
		{
			markModified();
			juvLocUnitId = string;
			juvLocUnit = null;
		}
	}

	/**
	 * 
	 */
	public JuvLocationUnit getJuvLocUnit()
	{
		initJuvLocationUnit();
		return juvLocUnit;
	}
			
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initJuvLocationUnit() 
	{
		if ( juvLocUnit == null && juvLocUnitId != null) 
		{
			juvLocUnit = (JuvLocationUnit) new mojo.km.persistence.Reference(juvLocUnitId, JuvLocationUnit.class).getObject();
		}
	}
	
	/**
	 * 
	 */
	public String getLocationDescription()
	{
		/*if ( getLocationId() != null )
		{
			// use the location
			return getLocation().getLocationName();
		}*/
		if ( StringUtils.isNotEmpty(getJuvLocUnitId()) )
		{
			// use the location unit
			return getJuvLocUnit().getLocationUnitName();
		}
		else if ( getCustomAddressId() != null )
		{
			//use the address
			Address addr = getCustomAddress();
			StringBuffer buffer = new StringBuffer();
			buffer.append( addr.getStreetNum() );
			buffer.append( " " );
			if (addr.getStreetNumSuffix() != null){
				buffer.append(addr.getStreetNumSuffix().getDescription());
				buffer.append(" ");
			}
			buffer.append( addr.getStreetName() );
			buffer.append( " " );
			Code streetType = addr.getStreetType();
			if ( streetType != null )
			{
				buffer.append( streetType.getDescription() );
			}
			buffer.append( ", " );
			if ( addr.getAddress2() != null )
			{
				buffer.append( addr.getAddress2() );
				buffer.append( ", " );
			}
			if ( addr.getCity() != null )
			{
				buffer.append( addr.getCity() );
				buffer.append( ", " );
			}
			if ( addr.getState() != null )
			{
				buffer.append( addr.getState().getDescription() );
				buffer.append( " " );
			}
			if(addr.getZipCode() != null)
			{
				buffer.append( addr.getZipCode() );
				buffer.append( " " );
			}
			return buffer.toString();
		}
		
		return "";
	}
	
	/**
	 * @return
	 */
	public String getCasefileId()
	{
		fetch();
		return casefileId;
	}

	/**
	 * @return
	 */
	public JuvenileCasefile getCasefile()
	{
		return JuvenileCasefile.find( getCasefileId() );
	}

	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		if ( casefileId == null || ! casefileId.equals(string) ) 
		{
			markModified();
			casefileId = string;
		}
	}

	/**
	 * FOR USE BY PERSISTENCE ONLY.
	 */
	public Date getInterviewDateTime()
	{
		return interviewDateTime;
	}
	
	/**
	 * @return
	 */
	public Date getInterviewDate()
	{
		fetch();
		return interviewDateTime;
	}

	/**
	 * FOR USE BY PERSISTENCE ONLY.
	 */
	public void setInterviewDateTime(Date date)
	{
		interviewDateTime = date;
	}
	
	/**
	 * @param date
	 */
	public void setInterviewDate(Date date)
	{
		if ( date == null )
		{
			return;
		}
		
		if ( getInterviewDate() == null )
		{
			interviewDateTime = new Date();
			markModified();
		}
		
		Calendar newDate = Calendar.getInstance();
		newDate.setTime( date );
		int newMonth = newDate.get( Calendar.MONTH );
		int newDay = newDate.get( Calendar.DAY_OF_MONTH );
		int newYear = newDate.get( Calendar.YEAR );
		
		Calendar oldDate = Calendar.getInstance();
		oldDate.setTime( interviewDateTime );
		int oldMonth = oldDate.get( Calendar.MONTH );
		int oldDay = oldDate.get( Calendar.DAY_OF_MONTH );
		int oldYear = oldDate.get( Calendar.YEAR );
		
		if ( oldMonth != newMonth || oldDay != newDay || oldYear != newYear )
		{
			oldDate.set( Calendar.MONTH, newMonth );
			oldDate.set( Calendar.DAY_OF_MONTH, newDay );
			oldDate.set( Calendar.YEAR, newYear );
			interviewDateTime = oldDate.getTime();
			markModified();
		}
	}

	/**
	 * @return
	 */
	public Date getInterviewTime()
	{
		return getInterviewDate();
	}

	/**
	 * @param date
	 */
	public void setInterviewTime(Date date)
	{
		if ( date == null )
		{
			return;
		}
		
		if ( getInterviewDate() == null )
		{
			interviewDateTime = new Date();
			markModified();
		}
		
		Calendar newTime = Calendar.getInstance();
		newTime.setTime( date );
		int newHour = newTime.get( Calendar.HOUR_OF_DAY );
		int newMinute = newTime.get( Calendar.MINUTE );
		
		Calendar oldTime = Calendar.getInstance();
		oldTime.setTime( interviewDateTime );
		int oldHour = oldTime.get( Calendar.HOUR_OF_DAY );
		int oldMinute = oldTime.get( Calendar.MINUTE );
		
		if ( oldHour != newHour || oldMinute != newMinute )
		{
			oldTime.set( Calendar.HOUR_OF_DAY, newHour );
			oldTime.set( Calendar.MINUTE, newMinute );
			oldTime.set( Calendar.SECOND, 0 );
			interviewDateTime = oldTime.getTime();
			markModified();
		}
	}
	
	/**
	* Initialize class relationship implementation for pd.juvenilecase.interviewinfo.InterviewTask
	*/
	private void initTasks() 
	{
		if ( tasks == null) 
		{
			if (this.getOID() == null) 
			{
				new mojo.km.persistence.Home().bind(this);
			}
			tasks = new mojo.km.persistence.ArrayList(InterviewTask.class, "interviewId", "" + getOID());
		}
	}
	
	/**
	* returns a collection of pd.juvenilecase.interviewinfo.InterviewTask
	*/
	public Collection getTasks() 
	{
		initTasks();
		return tasks;
	}
	
	/**
	 * @return Returns the hasChecklist.
	 */
/*	public boolean getHasChecklist() 
	{
		fetch();
		return hasChecklist;
	}
*/

	/**
	 *  
	 */
/*	public boolean hasChecklist() 
	{
		return getHasChecklist();
	}
	
	/**
	 * @param hasChecklist The hasChecklist to set.
	 */
/*	public void setHasChecklist(boolean checklist) 
	{
		this.hasChecklist = checklist;
		markModified();
	}
*/	
	/**
	 * @return Returns the isCustomAddressValid.
	 */
	public boolean getIsCustomAddressValid() 
	{
		fetch();
		return isCustomAddressValid;
	}
	
	/**
	 *  
	 */
	public boolean isCustomAddressValid() 
	{
		return getIsCustomAddressValid();
	}
	
	/**
	 * @param isCustomAddressValid The isCustomAddressValid to set.
	 */
	public void setIsCustomAddressValid(boolean isCustomAddressValid) 
	{
		this.isCustomAddressValid = isCustomAddressValid;
		markModified();
	}
	
	/**
	 * @return
	 */
	public String getCalEventId()
	{
		fetch();
		return calEventId;
	}

	/**
	 * @param string
	 */
	public void setCalEventId(String string)
	{
		if ( calEventId == null || ! calEventId.equals(string) ) 
		{
			markModified();
			calEventId = string;
		}
	}
	
}

/*
 * Created on Oct 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.common.calendar;

import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.common.calendar.CalendarEvent;
import pd.contact.officer.OfficerProfile;
import pd.juvenile.Juvenile;
import pd.juvenilecase.JuvenileCasefile;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalendarEventContext extends PersistentObject
{

	/**
	* Properties for calendarEvent
	* @referencedType pd.common.calendar.CalendarEvent
	* @detailerDoNotGenerate true
	*/	
	private CalendarEvent calendarEvent = null;
	private Integer calendarEventId;
	
	/**
	* Properties for probationOfficer
	* @detailerDoNotGenerate false
	* @referencedType pd.contact.user.UserProfile
	*/
	private OfficerProfile probationOfficer = null;
	private String probationOfficerId;
	
	/**
	* @referencedType pd.juvenilecase.CaseFile
	* @detailerDoNotGenerate false
	*/
	private JuvenileCasefile caseFile = null;
	private String caseFileId;
	
	/**
	* Properties for juvenile
	* @detailerDoNotGenerate false
	* @referencedType pd.juvenilecase.Juvenile
	*/
	private Juvenile juvenile = null;
	private String juvenileId;
	
	
	/**
	 * @return calendarEventContextId
	 */
	public Integer getCalendarEventContextId()
	{
		//return (Integer) this.getOID();
		
		try{
			return Integer.valueOf(this.getOID());
		}catch (NumberFormatException ex){ // !! unchecked exception
			return null;
		}
		
	}

	/**
	 * @param string
	 */
	public void setCalendarEventContextId(Integer calEventContextId)
	{
		this.setOID(String.valueOf(calEventContextId));
	}
	
	/**
	 * @return calendarEventId
	 */
	public Integer getCalendarEventId()
	{
		return calendarEventId;
	}
	
	/**
	* Gets referenced type pd.common.calendar.CalendarEvent
	* @return CalendarEvent calendarEvent
	*/
	public CalendarEvent getCalendarEvent()
	{
		fetch();
		initCalendarEvent();
		return calendarEvent;
	}	

	/**
	 * @param string
	 */
	public void setCalendarEventId(Integer calEventId)
	{
		calendarEventId = calEventId;
	}

	/**
	* Initialize class relationship to class pd.common.calendar.CalendarEvent
	*/	
	private void initCalendarEvent() 
	{
		if (calendarEvent == null && calendarEventId != null)
		{
			calendarEvent =
				(CalendarEvent) new mojo
					.km
					.persistence
					.Reference(calendarEventId.toString(), CalendarEvent.class)
					.getObject();
		}		
	}
	
	/**
	* Access method for the probationOfficer property.
	* @return the current value of the probationOfficer property
	*/
	public OfficerProfile getProbationOfficer()
	{
		initProbationOfficer();
		fetch();
		return probationOfficer;
	}
	/**
	* Get the reference value to class :: pd.contact.user.UserProfile
	* @roseuid 4277CAAD001F
	* @return java.lang.String
	*/
	public String getProbationOfficerId()
	{
		fetch();
		return probationOfficerId;
	}
	
	/**
	* set the type reference for class member probationOfficer
	* @roseuid 4277CAAD004E
	* @param probationOfficer
	*/
	public void setProbationOfficer(OfficerProfile aProbationOfficer)
	{
		if (this.probationOfficer == null || !this.probationOfficer.equals(aProbationOfficer))
		{
			markModified();
		}
		if (aProbationOfficer.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aProbationOfficer);
		}
		setProbationOfficerId("" + aProbationOfficer.getOID());
		this.probationOfficer = (OfficerProfile) new mojo.km.persistence.Reference(aProbationOfficer).getObject();
	}
	/**
	* Set the reference value to class :: pd.contact.user.UserProfile
	* @roseuid 4277CAAD000F
	* @param probationOfficerId
	*/
	public void setProbationOfficerId(String aProbationOfficerId)
	{
		if (this.probationOfficerId == null || !this.probationOfficerId.equals(aProbationOfficerId))
		{
			markModified();
		}
		probationOfficer = null;
		this.probationOfficerId = aProbationOfficerId;
	}
	
	/**
	* Initialize class relationship to class pd.contact.user.UserProfile
	* @roseuid 4277CAAD003E
	*/
	private void initProbationOfficer()
	{
		if (probationOfficer == null)
		{
			probationOfficer =
				(OfficerProfile) new mojo
					.km
					.persistence
					.Reference(probationOfficerId, OfficerProfile.class)
					.getObject();
		}
	}
	
	/**
	* Access method for the caseFile property.
	* @return the current value of the caseFile property
	*/
	public JuvenileCasefile getCaseFile()
	{
		fetch();
		initCaseFile();
		return caseFile;
	}
	
	/**
	 * @return
	 */
	public String getCaseFileId()
	{
		fetch();
		return caseFileId;
	}
	
	/**
	* set the type reference for class member caseFile
	*/
	public void setCaseFile(JuvenileCasefile theCaseFile)
	{
		if (this.caseFile == null || !this.caseFile.equals(theCaseFile))
		{
			markModified();
		}
		if (theCaseFile.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(theCaseFile);
		}
		setCaseFileId("" + theCaseFile.getOID());
		this.caseFile = (JuvenileCasefile) new mojo.km.persistence.Reference(theCaseFile).getObject();
	}
	
	/**
	 * @param string
	 */
	public void setCaseFileId(String string)
	{
		caseFileId = string;
		caseFile = null;
		markModified();
	}
	
	/**
	* Initialize class relationship to class pd.juvenilecase.CaseFile
	*/
	private void initCaseFile()
	{
		if (caseFile == null)
		{
			try
			{
				caseFile =
					(JuvenileCasefile) new mojo
						.km
						.persistence
						.Reference(caseFileId, JuvenileCasefile.class)
						.getObject();
			}
			catch (Throwable t)
			{
				caseFile = null;
			}
		}
	}
	
	/**
	* Access method for the juvenile property.
	* @return the current value of the juvenile property
	*/
	public Juvenile getJuvenile()
	{
		initJuvenile();
		fetch();
		return juvenile;
	}
	
	/**
	 * Get the reference value to class :: pd.juvenile.Juvenile
	 */
	public String getJuvenileId()
	{
		fetch();
		return juvenileId;
	}
	
	/**
	 * set the type reference for class member juvenile
	 */
	public void setJuvenile(Juvenile juvenile)
	{
		if (this.juvenile == null || !this.juvenile.equals(juvenile))
		{
			markModified();
		}
		if (juvenile.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(juvenile);
		}
		setJuvenileId("" + juvenile.getOID());
		this.juvenile = (Juvenile) new mojo.km.persistence.Reference(juvenile).getObject();
	}
	
	/**
	 * Set the reference value to class :: pd.juvenile.Juvenile
	 */
	public void setJuvenileId(String juvenileId)
	{
		if (this.juvenileId == null || !this.juvenileId.equals(juvenileId))
		{
			markModified();
		}
		juvenile = null;
		this.juvenileId = juvenileId;
	}
	
	/**
	 * Initialize class relationship to class pd.juvenile.Juvenile
	 */
	private void initJuvenile()
	{
		if (juvenile == null)
		{
			juvenile = (Juvenile) new mojo.km.persistence.Reference(juvenileId, Juvenile.class)
					.getObject();
		}
	}
	
	/**
	 * Does a find by OID.  Convenience method
	 * @param calendarEventContextId
	 * @return CalendarEventContext
	 */
	public static CalendarEventContext find(Integer calendarEventContextId)
	{
		IHome home = new Home();
		CalendarEventContext context = (CalendarEventContext) home.find(String.valueOf(calendarEventContextId), CalendarEventContext.class);
		return context;
	}	
	
	/**
	 * Finds all for a specific event
	 * @param event
	 * @return iterator
	 */
	public static Iterator findAll(IEvent event) 
	{
		IHome home = new Home();
		return home.findAll(event, CalendarEventContext.class);
	}
	
	/**
	 * Returns the first record for a specific event if found.  Else
	 * returns null
	 * @param event
	 * @return calendareventcontext
	 */
	public static CalendarEventContext find(IEvent event) 
	{
		CalendarEventContext context = null;
		IHome home = new Home();		
		Iterator finds = home.findAll(event, CalendarEventContext.class);
		if (finds.hasNext()) {
			context = (CalendarEventContext) finds.next();
		}
		return context;
	}
	
	/**
	 * Returns all CalendarEventContext entities with the associated
	 * CalendarEventId
	 * @param calendarEventId
	 * @return iterator
	 */
	public static Iterator findByCalendarEventId(Integer calendarEventId) 
	{
		IHome home = new Home();
		return home.findAll("calendarEventId", calendarEventId, CalendarEventContext.class);		
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
		return home.findAll(attributeName, attributeValue, CalendarEventContext.class);
	}
	
	/**
	 * Returns the realized Entity for this Context.  It is guaranteed to implement
	 * the ICalendarContext interface since all Contexts are required to implement
	 * this interface and all Contexts are Entities.
	 * @see pd.common.calendar.ICalendarContext
	 * @return realized entity
	 */
	//public Object getContextInstance() {
	//	return new CalendarEventHelper().getContextInstance(getContextType(), getContextId());
	//}

}

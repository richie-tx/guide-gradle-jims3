package pd.juvenilecase.casefile;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import messaging.casefile.reply.ActivityResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileActivityTypeCode;
import pd.contact.PDContactHelper;

/**
 * @roseuid 4521319D004C
 */
public class Activity extends PersistentObject implements Comparable
{
	private String	activityCategoryId;
	private String	activityCodeId;
	private Date	activityDate;
	private Date	inactiveDate;
	private String	activityTypeId;

	private String	comments;
	private String	supervisionNumber;
	private String	title;

	private Date	startDate;
	private Date	endDate;
	private String	detentionId;
	private String time; // field to save the release and return time from the screen
	private String activityTime;
	private String latitude;
	private String longitude;
	private String updateComments;
	//private String detentionTime;
	private String activityEndTime;		

	/**
	 * @roseuid 4521319D004C
	 */
	public Activity()
	{
	}

	static public Activity find( String activityId )
	{
		IHome home = new Home();
		Activity activity = (Activity)home.find( activityId, Activity.class );
		return activity;
	}

	static public Iterator findAll( IEvent event )
	{
		IHome home = new Home();
		return home.findAll( event, Activity.class );
	}
	static public Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue, Activity.class);
	}
	
	public int compareTo( Object o ) throws ClassCastException
	{
		Activity evt = (Activity)o;
		if( evt == null || evt.getActivityDate() == null )
			return 1; // this makes any null objects go to the bottom change this to
								// -1 if you want
		// the top of the list
		if( this.activityDate == null )
			return -1; // this makes any null objects go to the bottom change this to
									// 1 if you want
		// the top of the list

		return evt.getActivityDate().compareTo( activityDate );
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getActivityCategoryId()
	{
		fetch();
		return activityCategoryId;
	}

	/**
	 * @return Returns the activityCodeId.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getActivityCodeId()
	{
		fetch();
		return activityCodeId;
	}

	/**
	 * Access method for the activityDate property.
	 * 
	 * @return the current value of the activityDate property
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getActivityDate()
	{
		fetch();
		return activityDate;
	}
	
	/**
	 * Access method for the activityDate property.
	 * 
	 * @return the current value of the inactiveDate property
	 * @methodInvocation fetch
	 */
	public Date getInactiveDate()
	{
		fetch();
		return inactiveDate;
	}

	/**
	 * @return Returns the activityTypeId.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getActivityTypeId()
	{
		fetch();
		return activityTypeId;
	}

	/**
	 * Access method for the comments property.
	 * 
	 * @return the current value of the comments property
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getComments()
	{
		fetch();
		return comments;
	}

	/**
	 * Access method for the supervisionNumber property.
	 * 
	 * @return the current value of the supervisionNumber property
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getSupervisionNumber()
	{
		fetch();
		return supervisionNumber;
	}

	/**
	 * @return Returns the title.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getTitle()
	{
		fetch();
		return title;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setActivityCategoryId( String activityCategoryId )
	{
		// THIS IS NOT A PERSISTED FIELD
		if( this.activityCategoryId == null || 
				!this.activityCategoryId.equals( activityCategoryId ) )
		{
			// markModified();
		}
		this.activityCategoryId = activityCategoryId;
	}

	/**
	 * @param activityCodeId
	 *          The activityCodeId to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setActivityCodeId( String activityCodeId )
	{
		if( this.activityCodeId == null || 
				!this.activityCodeId.equals( activityCodeId ) )
		{
			markModified();
		}
		this.activityCodeId = activityCodeId;
	}

	/**
	 * Sets the value of the activityDate property.
	 * 
	 * @param aActivityDate
	 *          the new value of the activityDate property
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setActivityDate( Date aActivityDate )
	{
		if( this.activityDate == null || 
				!this.activityDate.equals( aActivityDate ) )
		{
			markModified();
		}
		activityDate = aActivityDate;
	}
	
	/**
	 * Sets the value of the inactiveDate property.
	 * 
	 * @param aInactiveDate
	 *          the new value of the inactiveDate property
	 * @methodInvocation markModified
	 */
	public void setInactiveDate( Date aInactiveDate )
	{
		if( this.inactiveDate == null || 
				!this.inactiveDate.equals( aInactiveDate ) )
		{
			markModified();
		}
		inactiveDate = aInactiveDate;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @methodInvocation markModified
	 */
	public void setActivityTypeId( String activityTypeId )
	{
		this.activityTypeId = activityTypeId;
	}

	/**
	 * Sets the value of the comments property.
	 * 
	 * @param aComments
	 *          the new value of the comments property
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setComments( String aComments )
	{
		if( this.comments == null || !this.comments.equals( aComments ) )
		{
			markModified();
		}
		comments = aComments;
	}

	/**
	 * Sets the value of the supervisionNumber property.
	 * 
	 * @param aSupervisionNumber
	 *          the new value of the supervisionNumber property
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSupervisionNumber( String aSupervisionNumber )
	{
		if( this.supervisionNumber == null || 
				!this.supervisionNumber.equals( aSupervisionNumber ) )
		{
			markModified();
		}
		supervisionNumber = aSupervisionNumber;
	}

	/**
	 * @param title
	 *          The title to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setTitle( String title )
	{
		if( this.title == null || !this.title.equals( title ) )
		{
			markModified();
		}
		this.title = title;
	}

	/*
	 * 
	 */
	public ActivityResponseEvent valueObject()
	{
		ActivityResponseEvent response = new ActivityResponseEvent();
		String activityTimePattern = new String("hh:mm a");
		String activityTime24Pattern = new String ("HH:mm");
		Date   actTime    = null;
		Date   actEndTime = null;
		response.setActivityDate( activityDate );
		response.setActivityId( this.getOID() );
		response.setCasefileId( this.supervisionNumber );
		response.setInactiveDate( inactiveDate );

		response.setCodeId( this.activityCodeId );
		// production support
		if(this.getCreateUserID() != null){
			response.setCreateUserID(this.getCreateUserID());
		}
		if(this.getCreateTimestamp() != null){
			response.setCreateDate(new Date(this.getCreateTimestamp().getTime()));
		}
		if(this.getUpdateUserID() != null){
			response.setUpdateUser(this.getUpdateUserID());
		}
		if(this.getUpdateTimestamp() != null){
			response.setUpdateDate(new Date(this.getUpdateTimestamp().getTime()));
		}
		if(this.getCreateJIMS2UserID() != null){
			response.setCreateJIMS2UserID(this.getCreateJIMS2UserID());
		}
		if(this.getUpdateJIMS2UserID() != null){
			response.setUpdateJIMS2UserID(this.getUpdateJIMS2UserID());
		}

		Iterator<JuvenileActivityTypeCode> iter = JuvenileActivityTypeCode.findAll( "code", this.activityCodeId );
		if( iter.hasNext() )
		{
			JuvenileActivityTypeCode activityCode = iter.next();
			
			response.setActivityDesc( activityCode.getDescription() );

			Code code = activityCode.getCategory();
			if( code != null )
			{
				response.setCategoryId( code.getCode() );
				response.setCategoryDesc( code.getDescription() );
			}
			else
			{
				response.setCategoryDesc( UIConstants.EMPTY_STRING );
			}

			code = activityCode.getType();
			if( code != null )
			{
				response.setTypeId( code.getCode() );
				response.setTypeDesc( code.getDescription() );
			}
			else
			{
				response.setTypeDesc( UIConstants.EMPTY_STRING );
			}
			
			code = activityCode.getPermission();
			if( code != null )
			{
				response.setPermissionId( code.getCode() );
				response.setPermissionDesc( code.getDescription() );
			}
			else
			{
				response.setPermissionDesc( UIConstants.EMPTY_STRING );
			}
		}
		else
		{
			response.setActivityDesc( UIConstants.EMPTY_STRING );
			response.setCategoryId( UIConstants.EMPTY_STRING );
			response.setCategoryDesc( UIConstants.EMPTY_STRING );
			response.setTypeId( UIConstants.EMPTY_STRING );
			response.setTypeDesc(UIConstants.EMPTY_STRING );
			response.setPermissionId(UIConstants.EMPTY_STRING);
			response.setPermissionDesc(UIConstants.EMPTY_STRING);
		}

		response.setTitle( this.title );
		response.setComments( this.comments );
		//US 105411
		String strTime = this.time;
		if (strTime != null && strTime.length()>=5 ) 
		{
		    response.setTime( strTime.substring(0, 5) );
		}
		
		if (this.activityTime != null ) {
		    actTime = DateUtil.stringToDate(this.activityTime, activityTime24Pattern);
		    response.setActivityTime(DateUtil.dateToString(actTime, activityTimePattern));
		}
		
		String userLogonId = this.getCreateUserID();
		if (userLogonId != null) {
			String fullName = PDContactHelper.getUserProfileName(userLogonId);
			response.setCreatedBy(fullName);
		}
		
		if ( this.activityEndTime != null ) {
		    actEndTime = DateUtil.stringToDate(this.activityEndTime, activityTime24Pattern);
		    response.setActivityendTime(DateUtil.dateToString(actEndTime, activityTimePattern));
		}
		response.setUpdateComments(this.updateComments);
		response.setLatitude(this.latitude);
		response.setLongitude(this.longitude);
		
		return( response );
	}

	/**
	 * @param activityMap
	 * @return
	 */
	public ActivityResponseEvent valueObject( Map activityMap )
	{
		ActivityResponseEvent response = new ActivityResponseEvent();
		String activityTimePattern = new String("hh:mm a");
		String activityTime24Pattern = new String ("HH:mm");
		Date   actTime    = null;
		response.setActivityDate( activityDate );
		response.setInactiveDate( inactiveDate );
		response.setActivityId( this.getOID() );
		response.setCasefileId( this.supervisionNumber );
		
		if (this.activityTime != null ) {
		    actTime = DateUtil.stringToDate(this.activityTime, activityTime24Pattern);
		    response.setActivityTime(DateUtil.dateToString(actTime, activityTimePattern));
		}

		response.setCodeId( this.activityCodeId );
		// production support
		if(this.getCreateUserID() != null){
			response.setCreateUserID(this.getCreateUserID());
		}
		if(this.getCreateTimestamp() != null){
			response.setCreateDate(new Date(this.getCreateTimestamp().getTime()));
		}
		if(this.getUpdateUserID() != null){
			response.setUpdateUser(this.getUpdateUserID());
		}
		if(this.getUpdateTimestamp() != null){
			response.setUpdateDate(new Date(this.getUpdateTimestamp().getTime()));
		}
		if(this.getCreateJIMS2UserID() != null){
			response.setCreateJIMS2UserID(this.getCreateJIMS2UserID());
		}
		if(this.getUpdateJIMS2UserID() != null){
			response.setUpdateJIMS2UserID(this.getUpdateJIMS2UserID());
		}
		
		JuvenileActivityTypeCode activityCode = (JuvenileActivityTypeCode)activityMap.get( this.activityCodeId );
		
		if( activityCode == null )
		{
			response.setActivityDesc( UIConstants.EMPTY_STRING );
			response.setCategoryDesc( UIConstants.EMPTY_STRING );
			response.setTypeDesc( UIConstants.EMPTY_STRING  );
			response.setPermissionId(UIConstants.EMPTY_STRING);
			response.setPermissionDesc(UIConstants.EMPTY_STRING);
		}
		else
		{
			response.setActivityDesc( activityCode.getDescription() );

			Code code = activityCode.getCategory();
			if( code != null )
			{
				response.setCategoryId( code.getCode() );
				response.setCategoryDesc( code.getDescription() );
			}
			else
			{
				response.setCategoryDesc( UIConstants.EMPTY_STRING );
			}

			code = activityCode.getType();
			if( code != null )
			{
				response.setTypeId( code.getCode() );
				response.setTypeDesc( code.getDescription() );
			}
			else
			{
				response.setTypeDesc( UIConstants.EMPTY_STRING  );
			}
			
			code = activityCode.getPermission();
			if( code != null )
			{
				response.setPermissionId( code.getCode() );
				response.setPermissionDesc( code.getDescription() );
			}
			else
			{
				response.setPermissionDesc( UIConstants.EMPTY_STRING );
			}
		}

		response.setTitle( this.title );
		response.setComments( this.comments );
		
		return response;
	}

	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * @param endDate
	 *          The endDate to set.
	 */
	public void setEndDate( Date endDate )
	{
		this.endDate = endDate;
	}

	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * @param startDate
	 *          The startDate to set.
	 */
	public void setStartDate( Date startDate )
	{
		this.startDate = startDate;
	}
	public String getDetentionId()
	{
	    return detentionId;
	}

	public void setDetentionId(String detentionId)
	{
	    this.detentionId = detentionId;
	}
	public String getTime()
	{
	    return time;
	}

	public void setTime(String time)
	{
	    this.time = time;
	}

	public String getActivityTime()
	{
	    fetch();
	    return activityTime;
	}

	public void setActivityTime(String activityTime)
	{
	    if( this.activityTime == null || !this.activityTime.equals( activityTime ) )
	    {
		markModified();
	    }
	    this.activityTime = activityTime;
	}

	public String getLatitude()
	{
	    fetch();
	    return latitude;
	}

	public void setLatitude(String latitude)
	{
	    this.latitude = latitude;
	}

	public String getLongitude()
	{
	    fetch();
	    return longitude;
	}

	public void setLongitude(String longitude)
	{
	    this.longitude = longitude;
	}

	public String getUpdateComments()
	{
	    fetch();
	    return updateComments;
	}

	public void setUpdateComments(String updateComments)
	{
	    if( this.updateComments == null || !this.updateComments.equals( updateComments ) )
	    {
		markModified();
	    }
	    this.updateComments = updateComments;
	}
	/*public String getDetentionTime()
	{
	    fetch();
	    return detentionTime;
	}

	public void setDetentionTime(String detentionTime)
	{
	    if( this.detentionTime == null || !this.detentionTime.equals( detentionTime ) )
	    {
		markModified();
	    }
	    this.detentionTime = detentionTime;
	}*/
	public String getActivityEndTime()
	{
	    fetch();
	    return activityEndTime;
	}

	public void setActivityEndTime(String activityEndTime)
	{
	    if( this.activityEndTime == null || !this.activityEndTime.equals( activityEndTime ) )
	    {
		markModified();
	    }
	    this.activityEndTime = activityEndTime;
	}
}

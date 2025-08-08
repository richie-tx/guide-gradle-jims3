/*
 * Created on Apr 25, 2005
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;

/**
 * 
 * 
 * @author anpillai
 */
@SuppressWarnings("unused")
public class JuvenileCasefileActivityResponseEvent extends ResponseEvent implements IAddressable,Comparable{
    
	private String casefileId;
	private String juvenileId;
	private String supervisionTypeId;
	private String officerLogonIdData;
	private String officerfullName;	
	private String activityCodeId;
	private String activitycodeDesc;	
	private Date activityDate;
	private String activityTime;	
	private String typeId;
	private String typeDesc;	
	private String categoryId;
	private String categoryDesc;	
	private String permissionId;	
	private String juvenilefirstName;	
	private String juvenilelastName;	
	private String juvenilemiddleName;	
	private String officerLastNameData;	
	private String officerFirstNameData;
	private String officerMiddleNameData;	
	private String juvLocationId;	
	private String logonId;	
	private String createUser;
	private String createuserLiteral;
	private String latitude;
	private String longitude;
	private String activityEndTime;
	private Date activityCreateDate;
	private long daysDiff;
	

	public String getCasefileId()
	{
	    return casefileId;
	}

	public void setCasefileId(String casefileId)
	{
	    this.casefileId = casefileId;
	}
	public String getJuvenileId()
	{
	    return juvenileId;
	}

	public void setJuvenileId(String juvenileId)
	{
	    this.juvenileId = juvenileId;
	}
	public String getSupervisionTypeId()
	{
	    return supervisionTypeId;
	}

	public void setSupervisionTypeId(String supervisionTypeId)
	{
	    this.supervisionTypeId = supervisionTypeId;
	}
	public String getActivityCodeId()
	{
	    return activityCodeId;
	}
	public String getOfficerLogonIdData()
	{
	    return officerLogonIdData;
	}

	public void setOfficerLogonIdData(String officerLogonIdData)
	{
	    this.officerLogonIdData = officerLogonIdData;
	}
	public void setActivityCodeId(String activityCodeId)
	{
	    this.activityCodeId = activityCodeId;
	}
	public String getActivityTime()
	{
	    return activityTime;
	}

	public void setActivityTime(String activityTime)
	{
	    this.activityTime = activityTime;
	}
	public String getCategoryId()
	{
	    return categoryId;
	}

	public void setCategoryId(String categoryId)
	{
	    this.categoryId = categoryId;
	}
	public String getPermissionId()
	{
	    return permissionId;
	}

	public void setPermissionId(String permissionId)
	{
	    this.permissionId = permissionId;
	}
	public String getJuvenilefirstName()
	{
	    return juvenilefirstName;
	}

	public void setJuvenilefirstName(String juvenilefirstName)
	{
	    this.juvenilefirstName = juvenilefirstName;
	}
	public String getJuvenilemiddleName()
	{
	    return juvenilemiddleName;
	}

	public void setJuvenilemiddleName(String juvenilemiddleName)
	{
	    this.juvenilemiddleName = juvenilemiddleName;
	}
	public String getOfficerLastNameData()
	{
	    return officerLastNameData;
	}

	public void setOfficerLastNameData(String officerLastNameData)
	{
	    this.officerLastNameData = officerLastNameData;
	}
	public String getJuvLocationId()
	{
	    return juvLocationId;
	}

	public void setJuvLocationId(String juvLocationId)
	{
	    this.juvLocationId = juvLocationId;
	}
	public String getLogonId()
	{
	    return logonId;
	}

	public void setLogonId(String logonId)
	{
	    this.logonId = logonId;
	}
	public Date getActivityDate()
	{
	    return activityDate;
	}

	public void setActivityDate(Date activityDate)
	{
	    this.activityDate = activityDate;
	}
	
	public Date getActivityCreateDate()
	{
	   return activityCreateDate;
	}
	
	public void setActivityCreateDate( Date activityCreateDate )
	{
		this.activityCreateDate = activityCreateDate;
	}
	
	public String getTypeId()
	{
	    return typeId;
	}

	public void setTypeId(String typeId)
	{
	    this.typeId = typeId;
	}
	public String getJuvenilelastName()
	{
	    return juvenilelastName;
	}

	public void setJuvenilelastName(String juvenilelastName)
	{
	    this.juvenilelastName = juvenilelastName;
	}
	public String getOfficerFirstNameData()
	{
	    return officerFirstNameData;
	}

	public void setOfficerFirstNameData(String officerFirstNameData)
	{
	    this.officerFirstNameData = officerFirstNameData;
	}
	
	public String getOfficerMiddleNameData()
	{
	    return officerMiddleNameData;
	}

	public void setOfficerMiddleNameData(String officerMiddleNameData)
	{
	    this.officerMiddleNameData = officerMiddleNameData;
	}
	public String getCreateUser()
	{
	    return createUser;
	}

	public void setCreateUser(String createUser)
	{
	    this.createUser = createUser;
	}
	public String getCategoryDesc()
	{
	    return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc)
	{
	    this.categoryDesc = categoryDesc;
	}
	public String getTypeDesc()
	{
	    return typeDesc;
	}

	public void setTypeDesc(String typeDesc)
	{
	    this.typeDesc = typeDesc;
	}
	public String getActivitycodeDesc()
	{
	    return activitycodeDesc;
	}

	public void setActivitycodeDesc(String activitycodeDesc)
	{
	    this.activitycodeDesc = activitycodeDesc;
	}
	public String getOfficerfullName()
	{
	    return officerfullName;
	}

	public void setOfficerfullName(String officerfullName)
	{
	    this.officerfullName = officerfullName;
	}
	public String getCreateuserLiteral()
	{
	    return createuserLiteral;
	}

	public void setCreateuserLiteral(String createuserLiteral)
	{
	    this.createuserLiteral = createuserLiteral;
	}
	
	
	
	public String getLatitude()
	{
	    return latitude;
	}

	public void setLatitude(String latitude)
	{
	    this.latitude = latitude;
	}

	public String getLongitude()
	{
	    return longitude;
	}

	public void setLongitude(String longitude)
	{
	    this.longitude = longitude;
	}

	public String getActivityEndTime()
	{
	    return activityEndTime;
	}

	public void setActivityEndTime(String activityEndTime)
	{
	    this.activityEndTime = activityEndTime;
	}
	
	public long getDaysDiff()
	{
	    return this.daysDiff;
	}

	public void setDaysDiff(long daysdiff)
	{
	    this.daysDiff = daysdiff;
	}
	

	@Override
	public int compareTo(Object o)
	{
	    if(o==null)
		return -1;
	    JuvenileCasefileActivityResponseEvent evt = (JuvenileCasefileActivityResponseEvent) o;
	return casefileId.compareToIgnoreCase(evt.casefileId);	
	    //return 0;
	}
	

	
}


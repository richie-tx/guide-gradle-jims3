package messaging.productionsupport.reply;

import java.util.Date;

import messaging.calendaring.reply.CalendarResponseEvent;

/**
 * @author rcarter
 */
public class ProductionSupportCalendarResponseEvent extends CalendarResponseEvent
{

	private Integer calendarEventId;
	private Date calendarEventDate;
	private Integer calendarSeriesId;
	
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJims2User;
	private String updateJims2User;

	public Integer getCalendarEventId() {
		return calendarEventId;
	}

	public void setCalendarEventId(Integer calendarEventId) {
		this.calendarEventId = calendarEventId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreateJims2User() {
		return createJims2User;
	}

	public void setCreateJims2User(String createJims2User) {
		this.createJims2User = createJims2User;
	}

	public String getUpdateJims2User() {
		return updateJims2User;
	}

	public void setUpdateJims2User(String updateJims2User) {
		this.updateJims2User = updateJims2User;
	}

	public Date getCalendarEventDate() {
		return calendarEventDate;
	}

	public void setCalendarEventDate(Date calendarEventDate) {
		this.calendarEventDate = calendarEventDate;
	}

	public Integer getCalendarSeriesId() {
		return calendarSeriesId;
	}

	public void setCalendarSeriesId(Integer calendarSeriesId) {
		this.calendarSeriesId = calendarSeriesId;
	}
	
	
}




/*
 * Created on Nov 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.calendar.reply;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.calendar.ICalendarContext;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalendarEventContextResponse extends ResponseEvent implements ICalendarContext
{
	private Integer calendarEventContextId;
	private String probationOfficerId;
	private String caseFileId;
	private String juvenileId;
	private Integer calendarEventId;	
	
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJims2User;
	private String updateJims2User;
	private List<ProductionSupportJuvenileProgramReferralResponseEvent> programReferralRespList;
	
	/**
	 * @return calendarEventContextId
	 */
	public Integer getCalendarEventContextId()
	{
		return calendarEventContextId;
	}
	/**
	 * @param integer
	 */
	public void setCalendarEventContextId(Integer integer)
	{
		calendarEventContextId = integer;
	}
	/* (non-Javadoc)
	 * @see messaging.calendar.ICalendarContext#getProbationOfficerId()
	 */
	public String getProbationOfficerId() {
		return probationOfficerId;
	}
	/**
	 * @param probationOfficerId
	 */
	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
	}
	/* (non-Javadoc)
	 * @see messaging.calendar.ICalendarContext#getCaseFileId()
	 */
	public String getCaseFileId() {
		return caseFileId;
	}
	/**
	 * @param caseFileId
	 */
	public void setCaseFileId(String caseFileId) {
		this.caseFileId = caseFileId;
	}
	/* (non-Javadoc)
	 * @see messaging.calendar.ICalendarContext#getJuvenileId()
	 */
	public String getJuvenileId() {
		return juvenileId;
	}
	/**
	 * @param juvenileId
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	/**
	 * @return calendarEventId
	 */
	public Integer getCalendarEventId()
	{
		return calendarEventId;
	}
	/**
	 * @param integer
	 */
	public void setCalendarEventId(Integer integer)
	{
		calendarEventId = integer;
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
	public List<ProductionSupportJuvenileProgramReferralResponseEvent> getProgramReferralRespList()
	{
	    return programReferralRespList;
	}
	public void setProgramReferralRespList(List<ProductionSupportJuvenileProgramReferralResponseEvent> programReferralRespList)
	{
	    this.programReferralRespList = programReferralRespList;
	}
	
	
	
	

}

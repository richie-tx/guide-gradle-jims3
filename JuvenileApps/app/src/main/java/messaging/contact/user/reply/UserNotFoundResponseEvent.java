/*
 * Created on Aug 14, 2006
 */
package messaging.contact.user.reply;

import java.util.Date;
import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class UserNotFoundResponseEvent extends ResponseEvent
{
	private String userId;
	private String departmentId;
	private String message;
	private String errorKey;
	/**
	 * @return Returns the departmentId.
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId The departmentId to set.
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return Returns the errorKey.
	 */
	public String getErrorKey() {
		return errorKey;
	}
	/**
	 * @param errorKey The errorKey to set.
	 */
	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}
}
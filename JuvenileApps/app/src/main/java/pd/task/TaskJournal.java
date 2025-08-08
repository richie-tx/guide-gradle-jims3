/*
 * Created on Mar 27, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.task;

import pd.contact.user.UserProfile;
import pd.transferobjects.helper.UserProfileHelper;
import messaging.contact.domintf.IName;
import messaging.task.domintf.ITaskJournal;
import messaging.contact.to.NameBean;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TaskJournal extends PersistentObject
{
	private String taskId;
	private String journalEntry;

	public static TaskJournal find(String taskJournalId)
	{
		IHome home = new Home();
		return (TaskJournal) home.find(taskJournalId, TaskJournal.class);
	}

	/**
	* Properties for creator
	* @referencedType pd.contact.user.UserProfile
	* @detailerDoNotGenerate true
	*/
	private UserProfile creator;

	/**
	* Initialize class relationship to class pd.contact.user.UserProfile
	*/
	private void initCreator()
	{
		if (creator == null)
		{
		//87191
			creator = UserProfileHelper.getUserProfileFromJUCode(super.getCreateUserID());
				/*(pd.contact.user.UserProfile) new mojo
					.km
					.persistence
					.Reference(super.getCreateUserID(), pd.contact.user.UserProfile.class)
					.getObject();*/
		}
	}

	/**
	* Gets referenced type pd.contact.user.UserProfile
	* @return UserProfile recallUser
	*/
	public UserProfile getCreator()
	{
		fetch();
		initCreator();
		return this.creator;
	}
	/**
	 * @return
	 */
	public String getJournalEntry()
	{
		fetch();
		return journalEntry;
	}

	/**
	 * @return
	 */
	public String getTaskId()
	{
		fetch();
		return taskId;
	}

	/**
	 * @param string
	 */
	public void setJournalEntry(String string)
	{
		if (this.journalEntry == null || !this.journalEntry.equals(string))
		{
			markModified();
		}
		this.journalEntry = string;
	}

	/**
	 * @param string
	 */
	public void setTaskId(String string)
	{
		if (this.taskId == null || !this.taskId.equals(string))
		{
			markModified();
		}
		this.taskId = string;
	}

	/**
	 * @param journalEntryBean
	 */
	public void fill(ITaskJournal journalEntryBean)
	{
		journalEntryBean.setJournalEntry(this.getJournalEntry());
		journalEntryBean.setCreateDate(this.getCreateTimestamp());
		UserProfile creator = this.getCreator();
		IName creatorName = new NameBean();
		creatorName.setFirstName(creator.getFirstName());
		creatorName.setMiddleName(creator.getMiddleName());
		creatorName.setLastName(creator.getLastName());
		journalEntryBean.setCreator(creatorName);
	}

}

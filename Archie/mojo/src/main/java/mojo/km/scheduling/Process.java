package mojo.km.scheduling;

import java.util.Collection;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
* @author mchowdhury
*/
public class Process extends PersistentObject
{

	private String processId;
	private String name;
	private String type;
	private boolean active;
	private String subsSystem;
	
	/**
	* Properties for jobs
	* @referencedType mojo.km.scheduling.Job
	* @detailerDoNotGenerate false
	*/
	private Collection jobs = null;

	/**
	* @roseuid 4107BF8402AF
	*/
	public Process()
	{
	}

	/**
	* Clears all mojo.km.scheduling.Job from class relationship collection.
	* @roseuid 4107DFB603E7
	*/
	public void clearJobs()
	{
		initJobs();
		jobs.clear();
	}
	
	/**
	* returns a collection of mojo.km.scheduling.Job
	* @return java.util.Collection
	* @roseuid 4107DFB603A1
	*/
	public Collection getJobs()
	{
		fetch();
		initJobs();
		return jobs;
	}

	
	/**
	* Initialize class relationship implementation for mojo.km.scheduling.Job
	* @roseuid 4107DFB60397
	*/
	private void initJobs()
	{
		if (jobs == null)
		{
			if (this.getOID() == null)
			{
				new Home().bind(this);
			}
			try
			{
				jobs =
					new mojo.km.persistence.ArrayList(Job.class, "progessId", "" + getOID());
			}
			catch (Throwable t)
			{
				jobs = new java.util.ArrayList();
			}
		}
	}

	/**
	* insert a mojo.km.scheduling.Job into class relationship collection.
	* @param anObject
	* @roseuid 4107DFB603B5
	*/
	public void insertJobs(Job anObject)
	{
		initJobs();
		jobs.add(anObject);
	}

	/**
	* Removes a mojo.km.scheduling.Job from class relationship collection.
	* @param anObject
	* @roseuid 4107DFB603C9
	*/
	public void removeJobs(Job anObject)
	{
		initJobs();
		jobs.remove(anObject);
	}

	/**
	* @return mojo.km.scheduling.Process
	* @param processId
	* @roseuid 4107B06D01B5
	*/
	static public Process find(String processId)
	{
		return (Process) new Home().find(processId, Process.class);
	}
	/**
	* @return java.util.Iterator
	* @roseuid 41123AE00111
	*/
	static public Iterator findAll()
	{
		return new Home().findAll(Process.class);
	}
	
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @roseuid 4236ED950316
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		return new Home().findAll(attrName,attrValue,Process.class);
	}
		
		
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4107B06D01BB
	*/
	static public Iterator findAll(IEvent event)
	{
		return new Home().findAll(event, Process.class);
	}
	
	/**
	* @roseuid 4107B06D01BB
	*/
	public void create(){
		new Home().bind(this);
	}
	/**
	 * @return Returns the active.
	 */
	public boolean isActive() {
		fetch();
		return active;
	}
	/**
	 * @param active The active to set.
	 */
	public void setActive(boolean active) {
		if(this.active == false)
		{
			markModified();
		}
		this.active = active;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		fetch();
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		if (this.name == null || !this.name.equals(name))
		{
			markModified();
		}
		this.name = name;
	}
	/**
	 * @return Returns the processId.
	 */
	public String getProcessId() {
		fetch();
		return this.getOID().toString();
	}
	/**
	 * @param processId The processId to set.
	 */
	public void setProcessId(String processId) {
		if (this.processId == null || !this.processId.equals(processId))
		{
			markModified();
		}
		this.setOID(processId);
		this.processId = processId;
	}
	/**
	 * @return Returns the subsSystem.
	 */
	public String getSubsSystem() {
		fetch();
		return subsSystem;
	}
	/**
	 * @param subsSystem The subsSystem to set.
	 */
	public void setSubsSystem(String subsSystem) {
		if (this.subsSystem == null || !this.subsSystem.equals(subsSystem))
		{
			markModified();
		}
		this.subsSystem = subsSystem;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		fetch();
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		if (this.type == null || !this.type.equals(type))
		{
			markModified();
		}
		this.type = type;
	}
}
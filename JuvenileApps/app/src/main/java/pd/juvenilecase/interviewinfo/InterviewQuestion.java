package pd.juvenilecase.interviewinfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.codetable.ICodetable;
import pd.codetable.criminal.JuvenileEventTypeCode;
import pd.task.TaskDef;

/**
* @roseuid 448EC364011D
*/
public class InterviewQuestion extends PersistentObject implements ICodetable
{
	
	private static final String ATTR_InterviewQuestionCategoryId = "interviewQuestionCategoryId";
	/**
	 * 
	 */
	private String text;
	
	/**
	* Properties for interviewQuestionCategory
	*/
	private Code interviewQuestionCategory = null;
	private String interviewQuestionCategoryId;

	private Collection taskDefs = null;

	
	/**
	 * 
	 */
	public static InterviewQuestion find( String questionId )
	{
		return (InterviewQuestion)new Home().find( questionId, InterviewQuestion.class );
	}
	
	/**
	 * 
	 */
	public static Iterator findAllByCategoryId( String categoryId )
	{
		return new Home().findAll( ATTR_InterviewQuestionCategoryId, categoryId, InterviewQuestion.class );
	}
	
	/**
	* @roseuid 448EC364011D
	*/
	public InterviewQuestion() 
	{
	}

	/**
	 * 
	 */
	public Collection createTasks( String casefileId, String juvId, Collection existingTasks )
	{
		// This list allows for a check against the
		// Task topic so that duplicates are not created.
		ArrayList taskTopics = new ArrayList();
		if ( existingTasks != null )
		{
			Iterator existingIter = existingTasks.iterator();
			while ( existingIter.hasNext() )
			{
				InterviewTask task = (InterviewTask)existingIter.next();
				taskTopics.add( task.getTopic() );
			}
		}

		// List for new tasks.
		ArrayList newTasks = new ArrayList();

		Iterator taskDefIter = getTaskDefs().iterator();
		while ( taskDefIter.hasNext() )
		{
			TaskDef taskDef = (TaskDef)taskDefIter.next();
			
			if ( ! taskTopics.contains(taskDef.getTopic()) )
			{
				try
				{
					InterviewTask task = InterviewTask.createTask( taskDef, casefileId, juvId );
					newTasks.add( task );
				}
				catch ( IOException ex )
				{
					ex.printStackTrace();
				}
			}
		}
		
		return newTasks;
	}
	
	/**
	 * 
	 */
	public void setText( String string )
	{
		if ( text == null || ! text.equals(string) )
		{
			text = string;
			markModified(); 
		}
	}
	
	/**
	 * 
	 */
	public String getText()
	{
		fetch();
		return text; 
	}
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setInterviewQuestionCategoryId(String interviewQuestionCategoryId) {
		if (this.interviewQuestionCategoryId == null || !this.interviewQuestionCategoryId.equals(interviewQuestionCategoryId)) {
			markModified();
		}
		interviewQuestionCategory = null;
		this.interviewQuestionCategoryId = interviewQuestionCategoryId;
	}
	
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getInterviewQuestionCategoryId() {
		fetch();
		return interviewQuestionCategoryId;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initInterviewQuestionCategory() {
		if (interviewQuestionCategory == null) {
			interviewQuestionCategory = (Code) new mojo.km.persistence.Reference(interviewQuestionCategoryId, Code.class).getObject();
		}
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getInterviewQuestionCategory() {
		initInterviewQuestionCategory();
		return interviewQuestionCategory;
	}
	
	/**
	* set the type reference for class member interviewQuestionCategory
	*/
	public void setInterviewQuestionCategory(Code interviewQuestionCategory) {
		if (this.interviewQuestionCategory == null || !this.interviewQuestionCategory.equals(interviewQuestionCategory)) {
			markModified();
		}
		if (interviewQuestionCategory.getOID() == null) {
			new mojo.km.persistence.Home().bind(interviewQuestionCategory);
		}
		setInterviewQuestionCategoryId("" + interviewQuestionCategory.getOID());
		this.interviewQuestionCategory = (Code) new mojo.km.persistence.Reference(interviewQuestionCategory).getObject();
	}
	
	/**
	 * 
	 */
	public Collection getTaskDefs()
	{
		initTaskDefs();
		
		ArrayList retVal = new ArrayList();
		Iterator i = taskDefs.iterator();
		while ( i.hasNext() ) 
		{
			InterviewQuestionTaskDefAssoc actual = (InterviewQuestionTaskDefAssoc) i.next();
			retVal.add(actual.getTaskDef());
		}
		
		return retVal;
	}
	
	/**
	 * 
	 */
	private void initTaskDefs()
	{
		if ( taskDefs == null )
		{
			if ( this.getOID() == null ) 
			{
				new mojo.km.persistence.Home().bind(this);
			}
			taskDefs = new mojo.km.persistence.ArrayList(InterviewQuestionTaskDefAssoc.class, "questionId",  getOID().toString());
		}
	}

	/* (non-Javadoc)
	 * @see pd.codetable.ICodetable#findAll()
	 */
	public Iterator findAll() {
		return new Home().findAll(InterviewQuestion.class);
	}
	
	/**
	   * Finds all InterviewQuestion by an attribute value
	   * @param attributeName
	   * @param attributeValue
	   * @return 
	   */
	   static public Iterator findAll(String attributeName, String attributeValue) {
		   IHome home = new Home();
		   return home.findAll(attributeName, attributeValue, InterviewQuestion.class);
	   }

	/* (non-Javadoc)
	 * @see pd.codetable.ICodetable#inActivate()
	 */
	public void inActivate() {
		// TODO Auto-generated method stub
		
	}
}

/*
 * Created on July 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.casefile.reply; 

import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 * Class holding data for each individual jsp.
 */
 
public class QuestionGroupResponseEvent extends ResponseEvent{
	private String id;
	private int sequence;
	private String selectedResponseId;
	private String dependentQuestionId;
	private Collection QuestionResponseEvents;

	
	/**
	 * Consturctor
	 */
	public QuestionGroupResponseEvent(){
	}
	
	/**
	 * @return
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @return
	 */
	public Collection getQuestionResponseEvents()
	{
		return QuestionResponseEvents;
	}

	/**
	 * @param string
	 */
	public void setId(String string)
	{
		id = string;
	}

	/**
	 * @param collection
	 */
	public void setQuestionResponseEvents(Collection collection)
	{
		QuestionResponseEvents = collection;
	}

	/**
	 * @return
	 */
	public int getSequence()
	{
		return sequence;
	}

	/**
	 * @param i
	 */
	public void setSequence(int i)
	{
		sequence = i;
	}

	/**
	 * @return
	 */
	public String getDependentQuestionId()
	{
		return dependentQuestionId;
	}

	/**
	 * @return
	 */
	public String getSelectedResponseId()
	{
		return selectedResponseId;
	}

	/**
	 * @param string
	 */
	public void setDependentQuestionId(String string)
	{
		dependentQuestionId = string;
	}

	/**
	 * @param string
	 */
	public void setSelectedResponseId(String string)
	{
		selectedResponseId = string;
	}

}
